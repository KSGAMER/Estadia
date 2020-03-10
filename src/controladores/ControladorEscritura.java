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
    
    private char tecla;
    
    public void typedCharsAndSpaceAndDigits(java.awt.event.KeyEvent evt, JTextField campo) {
        tecla = evt.getKeyChar();
        toUppercase(evt, tecla);
        
        if(!Character.isLetter(tecla) && tecla == KeyEvent.VK_BACK_SPACE && tecla != KeyEvent.VK_ENTER) {
            evt.consume();
        } else {
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
