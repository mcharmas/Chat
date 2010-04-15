/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.util.ArrayList;
import packet.Msg;

/**
 *
 * @author orbit
 */
public interface ServerListener {
    enum Status { UP, DOWN }
    void serverStatusChanged(Status status);
    void userConnectedDisconnected(ArrayList<String> userList);
    void gotMessage(Msg message);
}
