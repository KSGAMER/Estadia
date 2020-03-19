/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author KSGAMER
 */
public class ControladorEventosSesion {
    //Se declara una variable de datos
    private ControladorSesion cs = new ControladorSesion();

    //Método que entra en acción cuando el TextField es selecionado
    //Se pasa el evento de focus del textfield, el textfiel que actua como usuario y el passwordfield que actua como contraseña
    public void campoGainFocus(java.awt.event.FocusEvent evt, JTextField usuario, JPasswordField contraseña) {
        //Si el usuario es igual a la cadena "Ingresa tu usuario" o esta vacio y ademas de eso el componente es igual a la cadena class javax.swing.JTextField prosigue
        if ((usuario.getText().equals("Ingresa tu usuario") || usuario.getText().isEmpty()) && evt.getComponent().getClass().toString().equals("class javax.swing.JTextField")) {
            //Reemplaza el texto con nada
            usuario.setText("");
            //Reemplaza el color del textfiel con el siguiente color
            usuario.setBackground(new Color(28,37,47));
            //Si el texto de passwordfield esta vacio prosigue
            if (contraseña.getText().isEmpty()) {//28,37,47
                //Se reemplaza el texto del passwordfield con la siguiente cadena
                contraseña.setText("password");
                //Se reemplaza el color del passwordfield con el siguiente
                contraseña.setBackground(new Color(28,37,47));
            } else { //De no cumplirse lo anterior 
                //Se reemplaza el color del passwordfield con el siguiente color
                contraseña.setBackground(new Color(28,37,47));
            }
        //Si la clase no corresponde con la cadena "class javax.swing.JPasswordField" y el passwordfield no esta vacio prosigue
        } else if (!evt.getComponent().getClass().toString().equals("class javax.swing.JPasswordField") && !contraseña.getText().isEmpty()) {
            //Se reemplaza el color del passwordfield
            contraseña.setBackground(new Color(28,37,47));
        //Si el passwordfield esta vacio prosigue
        } else if (contraseña.getText().isEmpty()) {
            //Se reemplaza el texto del passowrdfield con passowrd
            contraseña.setText("password");
            //Se reemplaza el color del fondo del passwordfield
            contraseña.setBackground(new Color(28,37,47));
        }
        //Si la contraseña es igual a passowrd o la contraseña esta vacia y la clase no corresponde con la cadena "class javax.swing.JPasswordField"
        if ((contraseña.getText().equals("password") || contraseña.getText().isEmpty()) && evt.getComponent().getClass().toString().equals("class javax.swing.JPasswordField")) {
            //Se reemplaza el texto con nada
            contraseña.setText("");
            //Se reemplaza el color del fondo con el siguiente
            contraseña.setBackground(new Color(28,37,47));
            //Si el usuario esta vacio prosigue
            if (usuario.getText().isEmpty()) {
                //Se reemplaza el texto con el siguiente
                usuario.setText("Ingresa tu usuario");
                //Se reemplaza el color con el siguiente
                usuario.setBackground(new Color(28,37,47));
            } else { //En caso contrario de no cumplirse nada de lo anterior prosigue
                //Se cambia el color del textfield con el siguiente
                usuario.setBackground(new Color(28,37,47));
            }
        //Si la clase no corresponde con la cadena "class javax.swing.JTextField" y el usuario no esta vacio
        } else if (!evt.getComponent().getClass().toString().equals("class javax.swing.JTextField") && !usuario.getText().isEmpty()) {
            //Se cambia el color del textfield con el siguiente
            usuario.setBackground(new Color(28,37,47));
        }
    }

    //Método que entra en accion cuando se da click pasando el usuario y la contraseña
    //Aplica lo antes comentado arriba
    public void camposMouseClick(java.awt.event.MouseEvent evt, JTextField usuario, JPasswordField contraseña) {
        if (usuario.getText().equals("Ingresa tu usuario") && evt.getComponent().getClass().toString().equals("class javax.swing.JTextField")) {
            usuario.setText("");
            usuario.setBackground(new Color(28,37,47));
            if (contraseña.getText().isEmpty()) {
                contraseña.setText("password");
                contraseña.setBackground(new Color(28,37,47));
            }
        } else if (!evt.getComponent().getClass().toString().equals("class javax.swing.JPasswordField")) {
            contraseña.setBackground(new Color(28,37,47));
        }
        if (contraseña.getText().equals("password") && evt.getComponent().getClass().toString().equals("class javax.swing.JPasswordField")) {
            contraseña.setText("");
            contraseña.setBackground(new Color(28,37,47));
            if (usuario.getText().isEmpty()) {
                usuario.setText("Ingresa tu usuario");
                usuario.setBackground(new Color(28,37,47));
            }
        } else if (!evt.getComponent().getClass().toString().equals("class javax.swing.JTextField")) {
            usuario.setBackground(new Color(28,37,47));
        }
    }
    
    //Método para autentificar el usuario y la contraseña con un Enter
    public void logearWithEnter(java.awt.event.KeyEvent evt, JTextField usuario, JPasswordField contraseña, JLabel Lb_notificacion, JFrame sesion) {
        //Si el evento es igual al evento de dar enter prosigue
        if(evt.getKeyCode() ==  KeyEvent.VK_ENTER) {
            //Se pasa el usuario, la contraseña, el label de notificación y el frame actual
            cs.autentificarUsuario(usuario, contraseña, Lb_notificacion, sesion);
        }
    }
    
    //Método que evalua si los campos estan vacios
    public void nullCampos(JTextField usuario, JPasswordField contraseña, JLabel Lb_notificacion) {
        //Se extrae el contenido del passwordfield
        String password = String.valueOf(contraseña.getPassword());
        //Si el textfield y el passwordfield no estan vacios no hace nada
        if (!usuario.getText().equals("Ingresa tu usuario") && !password.equals("password")) {

        } else { //Si estan vacios prosigue
            //Se reemplaza el texto de la notificación con la siguiente cadena
            Lb_notificacion.setText("Debes ingresar usuario y contraseña");
        }
    }
}
