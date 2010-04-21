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
public class Server extends Thread implements UserListener {

    int port = 0;
    private ServerSocket serverSocket = null;
    private ArrayList<Client> clients = new ArrayList<Client>();
    private ServerListener listener = null;
    private boolean running = false;

    public Server(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(this.port);
        Logger.getLogger(Server.class.getName()).log(Level.INFO, "Server created.");
    }

    @Override
    public void run() {
        running = true;
        notifyStatusChanged(ServerListener.Status.UP);
        Logger.getLogger(Server.class.getName()).log(Level.INFO, "Server started.");
        try {
            while (true) {
                Socket clientSock = serverSocket.accept();
                Client c = new Client(clientSock);
                c.setListener(this);
                c.start();
                clients.add(c);
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.INFO, "Server shutdown.");
            notifyStatusChanged(ServerListener.Status.DOWN);
            running = false;
        }
    }

    public void shutdown() {
        try {
            serverSocket.close();
            disconnectAllClients();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, "Error closing listening socket.", ex);
        }
    }

    public void disconnectAllClients() {
        for (Client c : clients) {
            c.disconnect();
        }
    }

    public void disconnectClient(String name) {
        for (Client c : clients) {
            if (c.getUsername().equals(name)) {
                c.disconnect();
            }
        }
    }

    public void userStatusChanged(Status status, Client c) {
        if (status == Status.LoggedOut) {
            clients.remove(c);
        }
        notifyUserList();
        broadcastUserList();
    }

    public void userSendMessage(Msg message) {
        if (message.getTo() == null) {
            broadcastMessage(message);
        } else {
            sendPrivateMessage(message);
        }
        notifyGotMessage(message);
    }

    private ArrayList<String> getUserList() {
        ArrayList<String> users = new ArrayList<String>();
        for (Client c : clients) {
            String username = c.getUsername();
            if (username != null) {
                users.add(username);
            }
        }
        return users;
    }

    private void broadcastUserList() {
        Msg userList = new Msg(Boolean.FALSE, Boolean.TRUE);
        for (Client c : clients) {
            String username = c.getUsername();
            if (username != null) {
                userList.addToUserList(username);
            }
        }
        Logger.getLogger(Server.class.getName()).log(Level.INFO, "Broadcasting user list.");
        broadcastMessage(userList);
    }

    private void broadcastMessage(Msg message) {
        for (Client c : clients) {
            c.sendMessage(message);
        }
    }

    private void sendPrivateMessage(Msg message) {
        for (Client c : clients) {
            if (c.getUsername().equals(message.getTo())) {
                c.sendMessage(message);
            }
        }
    }

    private void notifyGotMessage(Msg message) {
        if (listener != null) {
            listener.gotMessage(message);
        }
    }

    private void notifyStatusChanged(ServerListener.Status status) {
        if (listener != null) {
            listener.serverStatusChanged(status);
        }
    }

    private void notifyUserList() {
        if (listener != null) {
            listener.userConnectedDisconnected(getUserList());
        }
    }

    public void setListener(ServerListener listener) {
        this.listener = listener;
    }

    public boolean isRunning() {
        return running;
    }
}
