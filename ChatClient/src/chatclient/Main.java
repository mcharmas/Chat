/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chatclient;

import gui.ChatPanel;
import javax.swing.JFrame;

/**
 *
 * @author orbit
 */
public class Main {

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Chat Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ChatPanel cp = new ChatPanel();
        frame.getContentPane().add(cp);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        createAndShowGUI();
    }


}
