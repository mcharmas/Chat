/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author orbit
 */
public class Server extends Thread {

    int port = 0;
    private ServerSocket serverSocket = null;
    private ArrayList<Client> clients = new ArrayList<Client>();

    public Server(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        Logger.getLogger(Server.class.getName()).log(Level.INFO, "Server started.");
        while(true) {
            try {
                Socket clientSock = serverSocket.accept();
                Client c = new Client(clientSock);
                c.start();
                clients.add(c);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, "Error client connection.", ex);
            }
        }
    }






}
