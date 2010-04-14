/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

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
    private Socket socket=null;
    private ObjectInputStream inObjStr=null;
    private ObjectOutputStream outObjStr=null;
    private String username;

    public Client(String addr, int port, String username) throws IOException {
        socket = new Socket(addr, port);
        //inObjStr = new ObjectInputStream(socket.getInputStream());
        //outObjStr = new ObjectOutputStream(socket.getOutputStream());

        this.username = username;
        Logger.getLogger(Client.class.getName()).log(Level.INFO, "Client connected.");
    }

    @Override
    public void run() {
        Msg m = new Msg(Boolean.FALSE, Boolean.FALSE);
        m.setMessage("DUPA");
    }

    public void sendMessage(String message) throws IOException {
    }
    
}
