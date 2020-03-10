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
public class ControladorUsuarios extends ModeloUsuarios{
    public DefaultTableModel tablaUsuarios() {
        return cargarTabla();
    }
    
    public ArrayList<ObjetoUsuario> selectUsuario() {
        return selectUsuarios();
    }
    
    public void insertUsuario(String username, String password, String empleado, String telefono, String direccion, String estatusUsuario) {
        insertUsuarios(username, password, empleado, telefono, direccion, estatusUsuario);
    }
    
    public void updateUsuario(String password, String empleado, String telefono, String direccion, String estatusUsuario, String username) {
        updateUsuarios(password, empleado, telefono, direccion, estatusUsuario, username);
    }
    
    public void deleteUsuario(String username) {
        deleteUsuarios(username);
    }
}
