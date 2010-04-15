/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author orbit
 */
public class Server extends Thread {

    int port = 0;
    private ServerSocket serverSocket = null;


    public Server(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(this.port);
        this.start();
    }

    @Override
    public void run() {
        Logger.getLogger(Server.class.getName()).log(Level.INFO, "Server started.");
        while(true) {
            try {
                Socket clientSock = serverSocket.accept();
                Client c = new Client(clientSock);
                Logger.getLogger(Server.class.getName()).log(Level.INFO, "Accepted client connection.");
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, "Error client connection.", ex);
            }
        }
    }
}
