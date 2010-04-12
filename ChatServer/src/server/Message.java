/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

/**
 *
 * @author orbit
 */
public class Message {
    private String from;
    private String content;

    public Message(String from, String content) {
        this.from = from;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getFrom() {
        return from;
    }

}
