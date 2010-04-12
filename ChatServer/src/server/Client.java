/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

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

    private Socket clientSocket=null;
    private BufferedReader reader=null;
    private BufferedWriter writer=null;
    private String username;

    public Client(Socket client) throws IOException {
        this.clientSocket = client;
        reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        Logger.getLogger(Client.class.getName()).log(Level.INFO, "Client created.");
    }

    @Override
    public void run() {
        try {
            String m = "";
            while ((m = reader.readLine()) != null) {
                Logger.getLogger(Client.class.getName()).log(Level.INFO, "Got message.");
                if(m.startsWith("{") && m.endsWith("}")) { //user is logging in
                    this.username = m.substring(1, m.length()-1);
                }

                Message msg = new Message(username, m);
                System.out.println(username + " " + m);
                //TODO send msg dalej
            }
            Logger.getLogger(Client.class.getName()).log(Level.INFO, "Client disconnected.");
            this.clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, "Client data read error.", ex);
        }

    }

    public void sendMessage(Message m) {
        try {
            writer.write(m.getFrom() + ": " + m.getContent());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, "Error sending message to:" + this.username, ex);
        }
    }




}
