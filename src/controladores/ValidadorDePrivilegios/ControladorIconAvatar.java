/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.ValidadorDePrivilegios;

import java.awt.Image;
import javax.swing.ImageIcon;
import controladores.ControladorUsuarios;
import objetos.ObjetoUsuario;
import static vistas.Principal.IconImage;
import static vistas.Principal.User;

/**
 *
 * @author fenix esta clase sirve para llamar la ruta y mostrar la imagen de
 * avatar que se haya asignado al usuario y en caso de no coincidir la ruta o no
 * haber agregardo una imagen de usuario esta clase le asigna por default una
 * imagen de avatar
 */
public class ControladorIconAvatar {

    private ControladorUsuarios cuser = new ControladorUsuarios();

    public void ajustarImagen() {
        cuser.tablaUsuarios();
        for (ObjetoUsuario user : cuser.selectUsuario()) {
            if (User.equals(user.getUsername())) {
              
                if (user.getImagen().equals("")) {
                    //se utiliza para obtener la ruta de la imagen 
                    ImageIcon icon = new ImageIcon("Avatars/default.png");
                    //se utilizar para obtener el tamaño de jlaben que contendra la imagen y 
                    //despues se reacomda la imagen automaticamente 
                    Image image = icon.getImage().getScaledInstance(IconImage.getWidth(), IconImage.getHeight(), Image.SCALE_SMOOTH);
//AQUI AGREGAMOS LAS IMAGENES AL LABEL COMO ICONO
                    IconImage.setIcon(new ImageIcon(image));
                    IconImage.repaint();
                    break;
                } else {
                    
//se utiliza para obtener la ruta de la imagen 
                    ImageIcon icon = new ImageIcon(user.getImagen());
                    //se utilizar para obtener el tamaño de jlaben que contendra la imagen y 
                    //despues se reacomda la imagen automaticamente 
                    Image image = icon.getImage().getScaledInstance(IconImage.getWidth(), IconImage.getHeight(), Image.SCALE_SMOOTH);
//AQUI AGREGAMOS LAS IMAGENES AL LABEL COMO ICONO
                    IconImage.setIcon(new ImageIcon(image));
                    IconImage.repaint();
                    break;
                }
            }
        }
    }
}
