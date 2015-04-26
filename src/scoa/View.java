/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scoa;

import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author JoãoVitor
 */
public class View {
    private static JFrame frame;
    
    public static void make(JFrame frame){
        
        if(View.frame != null){
            // Closing other screen
            View.frame.setVisible(false);
            View.frame.dispose();
        }

        
        View.frame = frame;
        View.frame.setVisible(true);
        // Centers the frame
        View.frame.setLocationRelativeTo(null);
    }
}
