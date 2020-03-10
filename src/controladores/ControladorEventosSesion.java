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
    
    private ControladorSesion cs = new ControladorSesion();

    public void campoGainFocus(java.awt.event.FocusEvent evt, JTextField usuario, JPasswordField contraseña) {
        if ((usuario.getText().equals("Ingresa tu usuario") || usuario.getText().isEmpty()) && evt.getComponent().getClass().toString().equals("class javax.swing.JTextField")) {
            usuario.setText("");
            usuario.setBackground(Color.WHITE);
            if (contraseña.getText().isEmpty()) {
                contraseña.setText("password");
                contraseña.setBackground(new Color(36, 47, 65));
            } else {
                contraseña.setBackground(new Color(36, 47, 65));
            }
        } else if (!evt.getComponent().getClass().toString().equals("class javax.swing.JPasswordField") && !contraseña.getText().isEmpty()) {
            contraseña.setBackground(new Color(36, 47, 65));
        } else if (contraseña.getText().isEmpty()) {
            contraseña.setText("password");
            contraseña.setBackground(new Color(36, 47, 65));
        }

        if ((contraseña.getText().equals("password") || contraseña.getText().isEmpty()) && evt.getComponent().getClass().toString().equals("class javax.swing.JPasswordField")) {
            contraseña.setText("");
            contraseña.setBackground(Color.WHITE);
            if (usuario.getText().isEmpty()) {
                usuario.setText("Ingresa tu usuario");
                usuario.setBackground(new Color(36, 47, 65));
            } else {
                usuario.setBackground(new Color(36, 47, 65));
            }
        } else if (!evt.getComponent().getClass().toString().equals("class javax.swing.JTextField") && !usuario.getText().isEmpty()) {
            usuario.setBackground(new Color(36, 47, 65));
        }
    }

    public void camposMouseClick(java.awt.event.MouseEvent evt, JTextField usuario, JPasswordField contraseña) {
        if (usuario.getText().equals("Ingresa tu usuario") && evt.getComponent().getClass().toString().equals("class javax.swing.JTextField")) {
            usuario.setText("");
            usuario.setBackground(Color.WHITE);
            if (contraseña.getText().isEmpty()) {
                contraseña.setText("password");
                contraseña.setBackground(new Color(36, 47, 65));
            }
        } else if (!evt.getComponent().getClass().toString().equals("class javax.swing.JPasswordField")) {
            contraseña.setBackground(new Color(36, 47, 65));
        }
        if (contraseña.getText().equals("password") && evt.getComponent().getClass().toString().equals("class javax.swing.JPasswordField")) {
            contraseña.setText("");
            contraseña.setBackground(Color.WHITE);
            if (usuario.getText().isEmpty()) {
                usuario.setText("Ingresa tu usuario");
                usuario.setBackground(new Color(36, 47, 65));
            }
        } else if (!evt.getComponent().getClass().toString().equals("class javax.swing.JTextField")) {
            usuario.setBackground(new Color(36, 47, 65));
        }
    }
    
    public void logearWithEnter(java.awt.event.KeyEvent evt, JTextField usuario, JPasswordField contraseña, JLabel Lb_notificacion, JFrame sesion) {
        if(evt.getKeyCode() ==  KeyEvent.VK_ENTER) {
            cs.autentificarUsuario(usuario, contraseña, Lb_notificacion, sesion);
        }
    }
    
    public void nullCampos(JTextField usuario, JPasswordField contraseña, JLabel Lb_notificacion) {
        String password = String.valueOf(contraseña.getPassword());
        String user = usuario.getText();
        if (!usuario.getText().equals("Ingresa tu usuario") && !password.equals("password")) {

        } else {

            Lb_notificacion.setText("Debes ingresar usuario y contraseña");
        }
    }
}
