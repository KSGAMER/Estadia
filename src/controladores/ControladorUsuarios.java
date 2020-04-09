/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloUsuarios;
import objetos.ObjetoUsuario;

/**
 *
 * @author KSGAMER
 */

//Se aplica Herencia de la clase padre Modelo Usuarios
public class ControladorUsuarios extends ModeloUsuarios{
    //Método que retorna una tabla
    public DefaultTableModel tablaUsuarios() {
        return cargarTabla();
    }
    
    //Método que retorna un arreglo de tipo Objeto Usuario
    public ArrayList<ObjetoUsuario> selectUsuario() {
        return selectUsuarios();
    }
    
    //Método que inserta un nuevo usuario pasando los parametros nombre de usuario, contraseña del usuario, nombre del empleado, telefono, dirección del empleado y estatus del usuario (Activo o inactivo)
    public void insertUsuario(String username, String password, String empleado, String telefono, String direccion, String imagen, String cargo, String estatusUsuario) {
        insertUsuarios(username, password, empleado, telefono, direccion, imagen, cargo, estatusUsuario);
    }
    
    //Método que actualiza un usuario pasando los parametros contraseña del usuario, telefono, direccion, estatus del usuario y el nombre del usuario
    public void updateUsuario(String password, String empleado, String telefono, String direccion, String imagen, String cargo, String estatusUsuario, String username) {
        updateUsuarios(password, empleado, telefono, direccion, imagen, cargo, estatusUsuario, username);
    }
    
    //Método que elimina un usuario pasando el nombre de usuario
    public void deleteUsuario(String username) {
        deleteUsuarios(username);
    }
}
