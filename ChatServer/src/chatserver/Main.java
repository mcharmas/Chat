/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chatserver;

import gui.ServerConfig;
import javax.swing.JFrame;

/**
 *
 * @author orbit
 */
public class Main {

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Chat Server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ServerConfig sc = new ServerConfig();
        frame.getContentPane().add(sc);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        createAndShowGUI();
    }
    

}
