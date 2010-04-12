/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author orbit
 */
public class Client extends Thread {
    Socket socket=null;
    BufferedWriter writer=null;
    BufferedReader reader=null;
    String username;

    public Client(String addr, int port, String username) throws IOException {
        socket = new Socket(addr, port);
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.username = username;
        Logger.getLogger(Client.class.getName()).log(Level.INFO, "Client connected.");
    }

    @Override
    public void run() {

        try {
            Logger.getLogger(Client.class.getName()).log(Level.INFO, "Trying to log in...");
            this.sendMessage("{" + username + "}");
            Logger.getLogger(Client.class.getName()).log(Level.INFO, "Success!");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, "Error logging in.", ex);
        }

        try {
            Logger.getLogger(Client.class.getName()).log(Level.INFO, "Client started listening for messages.");
            String m = "";
            while ((m = reader.readLine()) != null) {
                Logger.getLogger(Client.class.getName()).log(Level.INFO, "Got message: " + m);
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, "Connection error.", ex);
        } finally { 
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, "Error closing socket.", ex);
            }
        }

    }

    public void sendMessage(String message) throws IOException {
        writer.write(message, 0, message.length());
        writer.newLine();
        writer.flush();
    }
    
}
