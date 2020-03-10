/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloClientes;
import objetos.ObjetoCliente;

/**
 *
 * @author KSGAMER
 */
public class ControladorClientes extends ModeloClientes{
    
    public DefaultTableModel tablaClientes(JTextField texto) {
        return cargarTabla(texto.getText());
    }
    
    public DefaultTableModel tablaClientes() {
        return cargarTabla();
    }
    
    public ArrayList<ObjetoCliente> selectCliente() {
        return selectClientes();
    }
    
    public void insertCliente(String nombre, String rfc, String direccion, String telefono, String email, String CFDI) {
        insertClientes(nombre, rfc, direccion, telefono, email, CFDI);
    }
    
    public void updateCliente(String nombre, String rfc, String direccion, String telefono, String email, String CFDI, int id) {
        updateClientes(nombre, rfc, direccion, telefono, email, CFDI, id);
    }
    
    public void deleteCliente(int id) {
        deleteClientes(id);
    }
}
