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
import packet.Msg;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author orbit
 */
public class Server extends Thread implements UserListener{

    int port = 0;
    private ServerSocket serverSocket = null;
    private ArrayList<Client> clients = new ArrayList<Client>();

    public Server(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(this.port);
        Logger.getLogger(Server.class.getName()).log(Level.INFO, "Server created.");
        this.start();
    }

    @Override
    public void run() {
        Logger.getLogger(Server.class.getName()).log(Level.INFO, "Server started.");
        while(true) {
            try {
                Socket clientSock = serverSocket.accept();
                Client c = new Client(clientSock);
                c.setListener(this);
                c.start();
                clients.add(c);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, "Error client connection.", ex);
            }
        }
    }

    public void userStatusChanged(Status status, Client c) {
        if (status == Status.LoggedOut) {
            clients.remove(c);
        }
        broadcastUserList();
    }

    public void userSendMessage(Msg message) {
        if(message.getTo()==null) {
            broadcastMessage(message);
        } else {
            sendPrivateMessage(message);
        }
    }

    private void broadcastUserList() {
        Msg userList = new Msg(Boolean.FALSE, Boolean.TRUE);
        for(Client c: clients) {
            String username = c.getUsername();
            if(username!=null) {
                userList.addToUserList(username);
            }
        }
        Logger.getLogger(Server.class.getName()).log(Level.INFO, "Broadcasting user list.");
        broadcastMessage(userList);
    }

    private void broadcastMessage(Msg message) {
        for(Client c: clients) {
            c.sendMessage(message);
        }
    }

    private void sendPrivateMessage(Msg message) {
        throw new NotImplementedException();
    }

}
