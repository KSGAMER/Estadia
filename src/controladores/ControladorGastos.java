/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javax.swing.table.DefaultTableModel;
import modelos.ModeloGastos;

/**
 *
 * @author KSGAMER
 */
public class ControladorGastos extends ModeloGastos{
    public DefaultTableModel tablaGastos() {
        return cargarTabla();
    }
    
    public DefaultTableModel tablaGastos(String usuario) {
        return cargarTabla(usuario);
    }
    
    public DefaultTableModel tablaGastosCompleta() {
        return cargarGastos();
    }
    
    public void insertarGatos(String gasto, double cantidad, String descripcion, String usuario, String fecha) {
        insertGastos(gasto, cantidad, descripcion, usuario, fecha);
    }
    
    public void eliminarGatos(int id) {
        deleteGastos(id);
    }
}
