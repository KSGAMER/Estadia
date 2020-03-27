/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloInventarioHabitacion;
import objetos.ObjetoInventarioHabitacion;

/**
 *
 * @author KSGAMER
 */
public class ControladorInventarioHabitacion extends ModeloInventarioHabitacion{
    public DefaultTableModel tablaInventarioHabitacion() {
        return cargarTabla();
    }
    
    public ArrayList<ObjetoInventarioHabitacion> seleccionarInventarioHabitacion() {
        return selectInventarioHabitacion();
    }
    
    public void insertarInventarioHabitacion(String habitacion, String producto, int consumo, String estado) {
        insertInventarioHabitacion(habitacion, producto, consumo, estado);
    }
    
    public void actualizarInventarioHabitacion(String habitacion, String producto, int consumo, String estado, int id) {
        updateInventarioHabitacion(habitacion, producto, consumo, estado, id);
    }
    
    public void eliminarInventarioHabitacion(int id) {
        deleteInventarioHabitacion(id);
    }
}
