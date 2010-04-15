/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import packet.Msg;

/**
 *
 * @author orbit
 */

public interface UserListener {
    public enum Status { LoggedIn, LoggedOut }

    public void userStatusChanged(Status status, Client c);
    public void userSendMessage(Msg message);

}
