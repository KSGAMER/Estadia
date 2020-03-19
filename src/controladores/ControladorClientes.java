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

//Se aplica Herencia desde la clase padre Modelo Clientes
public class ControladorClientes extends ModeloClientes{
    //Método que retorna una tabla pasando el parametro texto para poder filtrar los resultados
    public DefaultTableModel tablaClientes(JTextField texto) {
        return cargarTabla(texto.getText());
    }
    
    //Método que retorna una tabla al igual que otra usando sobrecarga de operadores
    public DefaultTableModel tablaClientes() {
        return cargarTabla();
    }
    
    //Método que retorna un arreglo de tipo Objeto Cliente
    public ArrayList<ObjetoCliente> selectCliente() {
        return selectClientes();
    }
    
    //Método que pasa los parametros nombre, rfc, dirección, telefono, email y CFDI para agregar un nuevo cliente
    public void insertCliente(String nombre, String rfc, String direccion, String telefono, String email, String CFDI) {
        insertClientes(nombre, rfc, direccion, telefono, email, CFDI);
    }
    
    //Método que pasa los parametros nombre, rfc, direccion, telefono, email, CFDI, id para actualizar los datos de un cliente
    public void updateCliente(String nombre, String rfc, String direccion, String telefono, String email, String CFDI, int id) {
        updateClientes(nombre, rfc, direccion, telefono, email, CFDI, id);
    }
    
    //Método que pasa los parametros id para eliminar los datos de un cliente
    public void deleteCliente(int id) {
        deleteClientes(id);
    }
}
