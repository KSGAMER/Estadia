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
        //Se pasa el codigo extraido del evento de presionar una tecla
        tecla = evt.getKeyChar();
        //Se cambia la letra a una en letra mayuscula
       // toUppercase(evt, tecla);
        //Si la letra tecleada es diferente de una letra y la tecla contiene un espacio y la tecla es diferente de un evento de teclear enter prosigue
        if(!Character.isLetter(tecla) && tecla == KeyEvent.VK_BACK_SPACE && tecla != KeyEvent.VK_ENTER) {
            //Elimina la letra ingresada
            evt.consume();
        } else {
            //Elimina el borde del TextField
            campo.setBorder(null);
        }
    }
    
    //Método que permite letras y espacios aplicado a un TextField
    public void typedCharsAndSpace(java.awt.event.KeyEvent evt, JTextField campo) {
        //Obtiene el codigo de la tecla presionada
        tecla = evt.getKeyChar();
        //Reemplaza la letra por una Mayúscula
       // toUppercase(evt, tecla);
        //Si el caracteres es diferentes de una letra y es igual a un digito y la letra es diferente de la barra espaciadora y la letra es diferente del enter prosigue
        if((!Character.isLetter(tecla) && Character.isDigit(tecla)) && (tecla != KeyEvent.VK_BACK_SPACE) && (tecla != KeyEvent.VK_ENTER)) {
            //Se elimina el caracter
            evt.consume();
        } else {
            //Elimina el borde del TextField
            campo.setBorder(null);
        }
    }
    
    //Método que permite letras y digitos aplicado a un TextField
    public void typedCharsAndDigits(java.awt.event.KeyEvent evt, JTextField campo) {
        //Se obtiene el codigo de la tecla presionada
        tecla = evt.getKeyChar();
        //Se reemplaza la letra por una Mayúscula
       // toUppercase(evt, tecla);
        //Si el caracter es diferente de un digito y el caracter es diferente de una letra y el caracter es diferente del evento de la barra espaciadora y el caracter es diferente del evento de presionar el Enter prosigue
        if(!Character.isDigit(tecla) && !Character.isLetter(tecla) && tecla != KeyEvent.VK_BACK_SPACE && tecla != KeyEvent.VK_ENTER) {
            //Se elimina el caracter
            evt.consume();
        } else {
            //Se elimina el borde del TextField
            campo.setBorder(null);
        }
    }
    
    //Método que permite digitos aplicado a un TextField
    public void typedDigits(java.awt.event.KeyEvent evt, JTextField campo) {
        //Se obtiene el codigo de la tecla presionada
        tecla = evt.getKeyChar();
        //Si el caracter es diferente de un digito y es diferente de la barra espaciadora y es diferente de la tecla enter y es igual al punto (.) prosigue
        if (!Character.isDigit(tecla) && tecla != KeyEvent.VK_BACK_SPACE && tecla != KeyEvent.VK_ENTER && tecla == KeyEvent.VK_PERIOD) {
            //Se elimina el caracter
            evt.consume();
        } else {
            //Se elimina el borde
            campo.setBorder(null);
        }
    }
    
    //Método que permite digitos con decimales
    public void typedMoney(java.awt.event.KeyEvent evt, JTextField campo){
        //Se obtiene el codigo de la tecla presionada
        tecla = evt.getKeyChar();
        //Si el caracter es diferente de un digito y es diferente de la tecla de espacio y de enter y es diferente del punto (.) prosigue
        if (!Character.isDigit(tecla) && tecla != KeyEvent.VK_BACK_SPACE && tecla != KeyEvent.VK_ENTER && tecla != KeyEvent.VK_PERIOD) {
            //Se elimina el caracter
            evt.consume();
        } else {
            //Se elimina el borde del TextField
            campo.setBorder(null);
        }
    }
    
    //Método que permite direcciones de email aplicado a un TextField
    public void typedEmail(java.awt.event.KeyEvent evt, JTextField campo) {
        //Se obtiene el codigo de la tecla presionada
        tecla = evt.getKeyChar();
        //Si el caracter es diferente de la tecla de espaciado y es diferente de un digito y es diferente de una letra y es diferente de la tecla alt presionada y del caracter punto (.) se prosigue
        if(tecla != KeyEvent.VK_BACK_SPACE && (!Character.isDigit(tecla) && !Character.isLetter(tecla) && !evt.isAltGraphDown() && tecla != KeyEvent.VK_PERIOD)) {
            //Se elimna el caracter
            evt.consume();
        } else {
            //Se elimina el borde del TextField
            campo.setBorder(null);
        }
    }
    
    //Método que permite direcciones
    public void typedAddress(java.awt.event.KeyEvent evt, JTextField campo) {
        //Se obtiene el codigo de la tecla presionada
        tecla = evt.getKeyChar();
        //Se cambia la letra a una en letra mayuscula
       // toUppercase(evt, tecla);
        //Si el caracter es diferente de un digito y es diferente de una letra y es diferente de la tecla de espaciado y es diferente de la tecla enter prosigue
        if (!Character.isDigit(tecla) && !Character.isLetter(tecla) && tecla == KeyEvent.VK_BACK_SPACE && tecla != KeyEvent.VK_ENTER) {
            //Se elimina el caracter
            evt.consume();
        } else {
            //Se elimina el borde del TextField
            campo.setBorder(null);
        }
    }
    
    //Método que reemplaza la letra con una letra Mayúscula
    private void toUppercase(java.awt.event.KeyEvent evt, char tecla) {
        //Si el caracter es una letra prosigue
        if(Character.isLetter(tecla)) {
            //Se reemplaza la letra minuscula por una mayuscula pasando la letra
            evt.setKeyChar(Character.toUpperCase(tecla));
        }
    }
}
