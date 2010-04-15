/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author orbit
 */
public class Client extends Thread {

    private Socket clientSocket=null;
    private ObjectInputStream inObjStr=null;
    private ObjectOutputStream outObjStr=null;

    public Client(Socket client) {
        this.clientSocket = client;
        try {
            outObjStr = new ObjectOutputStream(clientSocket.getOutputStream());
            inObjStr = new ObjectInputStream(clientSocket.getInputStream());
        } catch (Exception ex) {
            try {
                clientSocket.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        Logger.getLogger(Client.class.getName()).log(Level.INFO, "Client created.");
        this.start();
    }

    @Override
    public void run() {
        try {
            outObjStr.writeObject(new Msg(Boolean.TRUE, Boolean.FALSE));
            outObjStr.flush();
            outObjStr.close();
            inObjStr.close();
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, "Client data read error.", ex);
        }
    }
}
