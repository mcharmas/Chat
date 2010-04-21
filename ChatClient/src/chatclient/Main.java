/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient;

import gui.ChatPanel;
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
            Logger.getLogger(ChatPanel.class.getName()).log(Level.SEVERE, null, ex);
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex1) {
                Logger.getLogger(ChatPanel.class.getName()).log(Level.SEVERE, null, ex1);

            }
        }

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
