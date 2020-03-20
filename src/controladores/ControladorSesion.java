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
    //Se declaran las clases a utilizar
    private ControladorUsuarios cu = new ControladorUsuarios();
    
    //Método que autentifica el usuario y la contraseña
    public void autentificarUsuario(JTextField usuario, JPasswordField contraseña, JLabel Lb_notificacion, JFrame sesion) {
        //Se cargan los datos a utilizar
        cu.tablaUsuarios();
        //Se recorre el arreglo de usuarios
        for (int i = 0; i < cu.selectUsuario().size(); i++) {
            //Si el usuario es igual al usuario extraido a base de datos prosigue
            if (cu.selectUsuario().get(i).getUsername().equals(usuario.getText())) {
                //Si la contraseña es igual a la contraseña extraida de base de datos prosigue
                if (cu.selectUsuario().get(i).getPassword().equals(contraseña.getText())) {
                    //Si el usuario y la contraseña son iguales al usuario y contraseña extraidos de base de datos prosigue
                    if (cu.selectUsuario().get(i).getUsername().equals(usuario.getText()) && cu.selectUsuario().get(i).getPassword().equals(contraseña.getText())) {
                        //Guarda el usuario en la variable publica Username
                        Username = usuario.getText();
                        //Instancia la ventana princial
                        Principal p = new Principal();
                        //Muestra la ventana principal
                        p.setVisible(true);
                        //Oculta la venta de sesion
                        sesion.setVisible(false);
                        //Rompe el ciclo
                        break;
                    } else {//De lo contrario manda la siguiente notificación
                        Lb_notificacion.setText("No coinciden las credenciales");
                    }
                } else {//De lo contrario manda la siguiente notificación
                    Lb_notificacion.setText("La contraseña es incorrecta");
                }
            } else {//De lo contrario manda la siguiente notificación
                Lb_notificacion.setText("Por favor, ingrese un usuario valido");
            }
        }
    }
}
