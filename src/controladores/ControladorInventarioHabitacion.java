/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javax.swing.table.DefaultTableModel;
import modelos.ModeloInventarioHabitacion;

/**
 *
 * @author KSGAMER
 */
public class ControladorInventarioHabitacion extends ModeloInventarioHabitacion{
    public DefaultTableModel tablaInventarioHabitacion() {
        return cargarTabla();
    }
    
    public DefaultTableModel tablaInventarioHabitacion(String buscar) {
        return cargarTabla(buscar);
    }
    
    public void insertarInventarioHabitacion(String producto, int total, String habitacion, String usuario) {
        insertInventarioHabitacion(producto, total, habitacion, usuario);
    }
    
    public void actualizarInventarioHabitacion(String producto, int total, String habitacion, String usuario, int id) {
        updateInventarioHabitacion(producto, total, habitacion, usuario, id);
    }
    
    public void eliminarInventarioHabitacion(int id) {
        deleteInventarioHabitacion(id);
    }
}
