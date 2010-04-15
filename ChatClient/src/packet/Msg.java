/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package packet;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author orbit
 */
public class Msg implements Serializable{
    Boolean login;
    Boolean userList;
    ArrayList<String> list = null;
    String message=null;
    String to=null;
    String from=null;

    public Msg() {
        this.login = false;
        this.userList = false;
    }

    public Msg(Boolean login, Boolean userList) {
        this.login = login;
        this.userList = userList;
        if(userList) {
            list = new ArrayList<String>();
        }
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void addToUserList(String user) {
        if(this.list != null) {
            this.list.add(user);
        }
    }

    public String getFrom() {
        return from;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public Boolean getLogin() {
        return login;
    }

    public String getMessage() {
        return message;
    }

    public String getTo() {
        return to;
    }

    public Boolean getUserList() {
        return userList;
    }
}
