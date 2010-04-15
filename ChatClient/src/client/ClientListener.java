/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.util.ArrayList;
import packet.Msg;

/**
 *
 * @author orbit
 */
public interface ClientListener {
    enum State { CONNECTED, DISCONNECTED }
    public void connectionStateChanged(State state);
    public void gotMessage(Msg message);
    public void gotUserList(ArrayList<String> userList);
}
