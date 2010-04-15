/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import packet.Msg;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.UserListener.Status;

/**
 *
 * @author orbit
 */
public class Client extends Thread {

    private Socket clientSocket=null;
    private String username=null;
    private ObjectInputStream inObjStr=null;
    private ObjectOutputStream outObjStr=null;
    private UserListener listener=null;

    public Client(Socket client) {
        this.clientSocket = client;
        try {
            outObjStr = new ObjectOutputStream(clientSocket.getOutputStream());
            inObjStr = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, "Error creating streams.", ex);
            try {
                clientSocket.close();
            } catch (IOException ex1) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, "Error closing socket", ex1);
            }
        }
        Logger.getLogger(Client.class.getName()).log(Level.INFO, "Client created.");        
    }

    @Override
    public void run() {
        try {
            Msg m = null;
            while ((m = (Msg) inObjStr.readObject()) != null) {
                Logger.getLogger(Client.class.getName()).log(Level.INFO, "Got data.");
                if(m.getLogin()) {
                    username = m.getFrom();
                    notifyLogin(Status.LoggedIn);
                    Logger.getLogger(Client.class.getName()).log(Level.INFO, "Client logged in: "+username);
                } else {
                    Logger.getLogger(Client.class.getName()).log(Level.INFO, "Received message from: "+username);
                    notifyMessage(m);
                }
            }
            Logger.getLogger(Client.class.getName()).log(Level.INFO, "Client disconnected.");
        } catch (EOFException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.INFO, "Socket closed by other side.");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, "Error reading socket.", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, "Error loading packet class.", ex);
        } finally {
            try {
                outObjStr.close();
                inObjStr.close();
                clientSocket.close();
                notifyLogin(Status.LoggedOut);
                Logger.getLogger(Client.class.getName()).log(Level.INFO, "Socket closed.");
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, "Error closing socket.", ex);
            }
        }
    }

    public void sendMessage(Msg m) {
        try {
            outObjStr.writeObject(m);
            outObjStr.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, "Error sending message.", ex);
        }
    }

    public void setListener(UserListener listener) {
        this.listener = listener;
    }

    private void notifyLogin(Status status) {
        if(this.listener!=null) {
            this.listener.userStatusChanged(status, this);
        }
    }

    private void notifyMessage(Msg message) {
        if(this.listener!=null) {
            this.listener.userSendMessage(message);
        }
    }

    public String getUsername() {
        return username;
    }

}
