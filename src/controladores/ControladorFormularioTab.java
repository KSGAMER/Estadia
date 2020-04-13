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
    //Método que entra cuando el Textfield gana focus
    public void formFocusGain(JTextField txt) {
        //Si Si el textfield contiene "Ingresa" o "Busca" prosigue
        if (txt.getText().contains("Ingresa") || txt.getText().contains("Busca")) {
            //Se reemplaza el texto por nada
            txt.setText("");
        }
    }

    //Método que entra en acción cuando pierde focus el textfield
    public void formFocusLostJTextField(JTextField actual, String texto) {
        //Si el textfield actual contiene nada entra
        if (actual.getText().trim().equals("")) {
            //Se reemplaza el texto por el texto que contiene la variable
            actual.setText(texto);
            //Se pinta el texto con el siguiente color
            actual.setForeground(new Color(204,204,204));
        }
    }

    //Método que actua cuando el textarea pierde focus
    public void formFocusLostJTextArea(JTextArea actual, String texto) {
        //Si el textarea esta vacio prosigue
        if (actual.getText().trim().equals("")) {
            //Se reemplaza el texto con el texto de la variable
            actual.setText(texto);
            //Se reemplaza el color del fondo del textarea
            actual.setForeground(new Color(204,204,204));
        }
    }

    //Método que entra en acción cuando el textfield pierde focus
    public void formFocusLostJTextField(JTextField siguiente) {
        //Se asigna el focus al siguiente textfield
        siguiente.requestFocus();
    }
    
    //Método que entra en acción cuando el textfield pierde focus
    public void formFocusLostJTextField(JComboBox siguiente) {
        //Se asigna el focus al siguiente combobox
        siguiente.requestFocus();
    }
    
    //Método que entra en acción cuando se teclea TAB
    public void formTab(java.awt.event.KeyEvent evt, JTextField siguiente) {
        //Sie el evento es igual al evento TAB
        if(evt.getKeyCode() == KeyEvent.VK_TAB) {
            //Se asigna el focus al siguiente textfield
            siguiente.requestFocus();
        }
    }

    //Método que entra en acción cuando el textarea gana focus
    public void formFocusGainJTextArea(JTextArea txt) {
        //Si el textarea contiene "ingresa" o "busca" prosigue
        if (txt.getText().contains("Ingresa") || txt.getText().contains("Busca")) {
            //Se reemplaza el texto del textarea
            txt.setText("");
        }
    }
}
