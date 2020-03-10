/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author KSGAMER
 */
public class ControladorFormularioTab {

    public void formFocusGain(JTextField txt) {
        if (txt.getText().contains("Ingresa") || txt.getText().contains("Busca")) {
            txt.setText("");
        }
    }

    public void formFocusLostJTextField(JTextField actual, String texto) {
        if (actual.getText().trim().equals("")) {
            actual.setText(texto);
            actual.setForeground(new Color(153, 153, 153));
        }
    }

    public void formFocusLostJTextArea(JTextArea actual, String texto) {
        if (actual.getText().trim().equals("")) {
            actual.setText(texto);
            actual.setForeground(new Color(153, 153, 153));
        }
    }

    public void formFocusLostJTextField(JTextField siguiente) {
        siguiente.requestFocus();
    }
    
    public void formFocusLostJTextField(JComboBox siguiente) {
        siguiente.requestFocus();
    }
    
    public void formTab(java.awt.event.KeyEvent evt, JTextField siguiente) {
        if(evt.getKeyCode() == KeyEvent.VK_TAB) {
            siguiente.requestFocus();
        }
    }

    public void formFocusGainJTextArea(JTextArea txt) {
        if (txt.getText().contains("Ingresa") || txt.getText().contains("Busca")) {
            txt.setText("");
        }
    }
}
