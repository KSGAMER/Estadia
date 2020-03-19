/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
 * @author KSGAMER
 */
public class ControladorEscritura {
    //Se declara una variable que actuara como el controlador del teclado
    private char tecla;
    
    //Método que permite el ingreso de letras, espacios y digitos aplicado a un TextField
    public void typedCharsAndSpaceAndDigits(java.awt.event.KeyEvent evt, JTextField campo) {
        //Se pasa el caracter extraido del evento de presionar una tecla
        tecla = evt.getKeyChar();
        //Se cambia la letra a una en letra mayuscula
        toUppercase(evt, tecla);
        //Si la letra tecleada es diferente de una letra y la tecla contiene un espacio y la tecla es diferente de un evento de teclear enter prosigue
        if(!Character.isLetter(tecla) && tecla == KeyEvent.VK_BACK_SPACE && tecla != KeyEvent.VK_ENTER) {
            //Elimina la letra ingresada
            evt.consume();
        } else {
            //
            campo.setBorder(null);
        }
    }
    
    public void typedCharsAndSpace(java.awt.event.KeyEvent evt, JTextField campo) {
        tecla = evt.getKeyChar();
        toUppercase(evt, tecla);
        
        if((!Character.isLetter(tecla) && Character.isDigit(tecla)) && (tecla != KeyEvent.VK_BACK_SPACE) && (tecla != KeyEvent.VK_ENTER)) {
            evt.consume();
        } else {
            campo.setBorder(null);
        }
    }
    
    public void typedCharsAndDigits(java.awt.event.KeyEvent evt, JTextField campo) {
        tecla = evt.getKeyChar();
        toUppercase(evt, tecla);
        
        if(!Character.isDigit(tecla) && !Character.isLetter(tecla) && tecla != KeyEvent.VK_BACK_SPACE && tecla != KeyEvent.VK_ENTER) {
            evt.consume();
        } else {
            campo.setBorder(null);
        }
    }
    
    public void typedDigits(java.awt.event.KeyEvent evt, JTextField campo) {
        tecla = evt.getKeyChar();
        
        if (!Character.isDigit(tecla) && tecla != KeyEvent.VK_BACK_SPACE && tecla != KeyEvent.VK_ENTER && tecla == KeyEvent.VK_PERIOD) {
            evt.consume();
        } else {
            campo.setBorder(null);
        }
    }
    
    public void typedMoney(java.awt.event.KeyEvent evt, JTextField campo){
        tecla = evt.getKeyChar();
        
        if (!Character.isDigit(tecla) && tecla != KeyEvent.VK_BACK_SPACE && tecla != KeyEvent.VK_ENTER && tecla != KeyEvent.VK_PERIOD) {
            evt.consume();
        } else {
            campo.setBorder(null);
        }
    }
    
    public void typedEmail(java.awt.event.KeyEvent evt, JTextField campo) {
        tecla = evt.getKeyChar();
        
        
        if(tecla != KeyEvent.VK_BACK_SPACE && (!Character.isDigit(tecla) && !Character.isLetter(tecla) && !evt.isAltGraphDown() && tecla != KeyEvent.VK_PERIOD)) {
            evt.consume();
        } else {
            campo.setBorder(null);
        }
    }
    
    public void typedAddress(java.awt.event.KeyEvent evt, JTextField campo) {
        tecla = evt.getKeyChar();
        toUppercase(evt, tecla);
        
        if (!Character.isDigit(tecla) && !Character.isLetter(tecla) && tecla == KeyEvent.VK_BACK_SPACE && tecla != KeyEvent.VK_ENTER) {
            evt.consume();
        } else {
            campo.setBorder(null);
        }
    }
    
    
    private void toUppercase(java.awt.event.KeyEvent evt, char tecla) {
        if(Character.isLetter(tecla)) {
            evt.setKeyChar(Character.toUpperCase(tecla));
        }
    }
}
