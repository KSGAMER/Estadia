/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloHabitaciones;
import objetos.ObjetoHabitacion;

/**
 *
 * @author KSGAMER
 */
public class ControladorHabitaciones extends ModeloHabitaciones{

    public DefaultTableModel tablaHabitaciones(JTextField texto) {
        return cargarTabla(texto.getText());
    }
    
    public DefaultTableModel tablaHabitaciones() {
        return cargarTabla();
    }
    
    public ArrayList<ObjetoHabitacion> selectHabitacion() {
        return selectHabitaciones();
    }
    
    public void insertHabitacion(String nombre, String piso, String categoria, double precioSugerido, String caracteristicas, String estatus) {
        insertHabitaciones(nombre, piso, categoria, precioSugerido, caracteristicas, estatus);
    }
    
    public void updateHabitacion(String nombre, String piso, String categoria, double precioSugerido, String caracteristicas, String estatus, int id) {
        updateHabitaciones(nombre, piso, categoria, precioSugerido, caracteristicas, estatus, id);
    }
    
    public void updateHabitacion(String nombre, String estatusHabitacion) {
        updateHabitaciones(nombre, estatusHabitacion);
    }
    
    public void updateHabitacion(int habitacion, String estatusHabitacion) {
        updateHabitaciones(habitacion, estatusHabitacion);
    }
    
    public void deleteHabitacion(int id) {
        deleteHabitaciones(id);
    }
    
}
