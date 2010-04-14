/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author orbit
 */
public class Client extends Thread {

    private Socket clientSocket=null;
    private String username;
    private ObjectInputStream inObjStr=null;
    private ObjectOutputStream outObjStr=null;

    public Client(Socket client) throws IOException {
        this.clientSocket = client;
        inObjStr = new ObjectInputStream(clientSocket.getInputStream());
        outObjStr = new ObjectOutputStream(clientSocket.getOutputStream());
        Logger.getLogger(Client.class.getName()).log(Level.INFO, "Client created.");
    }

    @Override
    public void run() {
        try {
            Msg m = null;
            while ((m = (Msg)inObjStr.readObject())!=null) {
                Logger.getLogger(Client.class.getName()).log(Level.INFO, "Got message.");
                
            }
            Logger.getLogger(Client.class.getName()).log(Level.INFO, "Client disconnected.");
            this.clientSocket.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, "Client data read error.", ex);
        }

    }

    public void sendMessage(Message m) {
    }
}
