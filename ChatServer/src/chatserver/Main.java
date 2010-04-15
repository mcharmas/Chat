/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chatserver;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import server.Server;

/**
 *
 * @author orbit
 */
public class Main {

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Chat Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //frame.getContentPane().add(cp);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Server s = new Server(9999);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
