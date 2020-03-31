/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadia;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import vistas.sesion;

/**
 *
 * @author KSGAMER
 */
public class estadia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            sesion s = new sesion();
            s.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
