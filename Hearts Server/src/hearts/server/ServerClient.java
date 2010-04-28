/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.server;

import hearts.defs.actions.Action;
import hearts.defs.actions.ActionListener;
import hearts.defs.actions.ErrorAction;
import hearts.defs.actions.GUIAction;
import hearts.defs.protocol.UserSocket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author orbit
 */
public class ServerClient implements UserSocket {

    private Socket socket;
    private ObjectInputStream input = null;
    private ObjectOutputStream output = null;
    private ArrayList<ActionListener> listeners = null;
    private int id;
    private String name = "";

    /**
     * <p>Tworzy nowego klienta serwera. Obiekt nasłuchuje na sockecie klienta.</p>
     * @param socket Socket klienta.
     * @throws IOException Wyrzucany w momencie gdy nie uda utworzyć się ObjectStreamów
     */
    public ServerClient(Socket socket) throws IOException {
        this.socket = socket;

        input = new ObjectInputStream(socket.getInputStream());
        output = new ObjectOutputStream(socket.getOutputStream());

        listeners = new ArrayList<ActionListener>();
        Logger.getLogger(ServerClient.class.getName()).log(Level.INFO, "ServerClient utworzony.");
    }

    public void run() {
        try {
            while (true) {
                Action action = (Action) input.readObject();
                if (action == null) {
                    break;
                }
                Logger.getLogger(ServerClient.class.getName()).log(Level.INFO, "Otrzymano akcję.");
                notifyListeners(action);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerClient.class.getName()).log(Level.SEVERE, "Klient mógł się rozłączyć.", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerClient.class.getName()).log(Level.SEVERE, "Błąd odbierania danych.", ex);
        } finally {
            try {
                input.close();
                output.close();
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(ServerClient.class.getName()).log(Level.SEVERE, "Błąd zamykania socketów.", ex);
            }
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void addActionListener(ActionListener listener) {
        this.listeners.add(listener);
    }

    public void actionReceived(Action a) {
        if (a instanceof GUIAction || a instanceof ErrorAction) {
            try {
                this.output.writeObject(a);
                this.output.flush();
            } catch (IOException ex) {
                Logger.getLogger(ServerClient.class.getName()).log(Level.SEVERE, "Bład wysyłania akcji.", ex);
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Powiadamia actionListenerów.
     * @param action
     */
    public void notifyListeners(Action action) {
        for (ActionListener listener : this.listeners) {
            listener.actionReceived(action);
        }
    }

    /**
     * Rozłącza użytkownika przez brutalne zamknięcie socketa i spowodowanie wyjątku w pętli głowej wątku.
     */
    public void disconnect() {
        try {
            if (!socket.isClosed()) {
                socket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerClient.class.getName()).log(Level.SEVERE, "Bład zamykania socketu przy rozłączniu użytkownika.", ex);
        }
    }
}
