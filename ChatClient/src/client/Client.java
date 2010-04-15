/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import packet.Msg;

/**
 *
 * @author orbit
 */
public class Client extends Thread {

    private String addr;
    private int port;
    private Socket socket=null;
    private ObjectInputStream inObjStr=null;
    private ObjectOutputStream outObjStr=null;
    private String username;
    ClientListener listener=null;

    public Client(String addr, int port, String username) throws IOException {
        this.addr = addr;
        this.username = username;
        this.port = port;
        Logger.getLogger(Client.class.getName()).log(Level.INFO, "Client connected.");        
    }


    public void connect() throws IOException {
        socket = new Socket(addr, port);
        outObjStr = new ObjectOutputStream(socket.getOutputStream());
        inObjStr = new ObjectInputStream(socket.getInputStream());
       
        Logger.getLogger(Client.class.getName()).log(Level.INFO, "Client connected.");
        notifyConnectionStateChange(ClientListener.State.CONNECTED);
        this.start();
    }

    public void disconnect() {
        try {
            outObjStr.close();
            inObjStr.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.INFO, "Closing connection.");
        }
    }

    @Override
    public void run() {
        Msg loginPacket = new Msg(true, Boolean.FALSE);
        loginPacket.setFrom(username);
        sendMessage(loginPacket);

        try {
            Msg m = null;
            while ((m = (Msg) inObjStr.readObject())!=null) {
                Logger.getLogger(Client.class.getName()).log(Level.INFO, "Got data.");
                if(m.getUserList()) {
                    Logger.getLogger(Client.class.getName()).log(Level.INFO, "Got user list.");
                    notifyUserList(m);
                } else {
                    notifyNewMessage(m);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.INFO, "Getting data interrupted.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                notifyConnectionStateChange(ClientListener.State.DISCONNECTED);
                inObjStr.close();
                outObjStr.close();
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, "Error closing socket.", ex);
            }
        }
    }

    public void sendMessage(Msg message) {
        try {
            outObjStr.writeObject(message);
            outObjStr.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, "Error sending message.", ex);
        }
    }

    public void setListener(ClientListener listener) {
        this.listener = listener;
    }

    public void notifyNewMessage(Msg message) {
        if(this.listener!=null) {
            this.listener.gotMessage(message);
        }
    }

    public void notifyUserList(Msg message) {
        if(this.listener!=null) {
            this.listener.gotUserList(message.getList());
        }
    }

    public void notifyConnectionStateChange(ClientListener.State state) {
        if(this.listener!=null) {
            this.listener.connectionStateChanged(state);
        }
    }

    public String getUsername() {
        return username;
    }
    
}
