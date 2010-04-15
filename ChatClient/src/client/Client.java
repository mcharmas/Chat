/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.Msg;

/**
 *
 * @author orbit
 */
public class Client extends Thread {
    private Socket socket=null;
    private ObjectInputStream inObjStr=null;
    private ObjectOutputStream outObjStr=null;
    private String username;

    public Client(String addr, int port, String username) throws IOException {
        socket = new Socket(addr, port);
        outObjStr = new ObjectOutputStream(socket.getOutputStream());
        inObjStr = new ObjectInputStream(socket.getInputStream());

        this.username = username;
        Logger.getLogger(Client.class.getName()).log(Level.INFO, "Client connected.");
    }

    @Override
    public void run() {
        Msg msg = null;
        try {
            msg = (Msg) inObjStr.readObject();
            System.out.println(msg.getLogin());
            outObjStr.close();
            inObjStr.close();
            socket.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
