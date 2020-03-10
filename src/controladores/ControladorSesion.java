/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import vistas.Principal;
import static vistas.sesion.Username;

/**
 *
 * @author KSGAMER
 */
public class ControladorSesion {

    private ControladorUsuarios cu = new ControladorUsuarios();

    public void autentificarUsuario(JTextField usuario, JPasswordField contraseña, JLabel Lb_notificacion, JFrame sesion) {
        cu.tablaUsuarios();
        for (int i = 0; i < cu.selectUsuario().size(); i++) {
            if (cu.selectUsuario().get(i).getUsername().equals(usuario.getText())) {
                if (cu.selectUsuario().get(i).getPassword().equals(contraseña.getText())) {
                    if (cu.selectUsuario().get(i).getUsername().equals(usuario.getText()) && cu.selectUsuario().get(i).getPassword().equals(contraseña.getText())) {
                        Username = usuario.getText();
                        Principal p = new Principal();
                        p.setVisible(true);
                        sesion.setVisible(false);
                        break;
                    }
                } else {
                    Lb_notificacion.setText("La contraseña es incorrecta");
                }
            } else {
                Lb_notificacion.setText("Por favor, ingrese un usuario valido");
            }
        }
    }
}
