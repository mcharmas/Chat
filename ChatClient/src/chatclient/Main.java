/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chatclient;

import client.Client;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author orbit
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Client c = new Client("127.0.0.1", 9999, "DUPA");
            c.start();            
            c.sendMessage("DUPA!");
            c.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
