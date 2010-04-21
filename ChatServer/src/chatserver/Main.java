/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chatserver;

import gui.ServerConfig;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author orbit
 */
public class Main {

    private static void createAndShowGUI() {
        //Create and set up the window.

        try {
            //System.out.println(Arrays.toString(UIManager.getInstalledLookAndFeels()));
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex1) {
            }
        }

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
