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

//Se aplica Herencia de la clase padre Modelo Habitaciones
public class ControladorHabitaciones extends ModeloHabitaciones{
    //Método que retorna una tabla con pase de parametros
    public DefaultTableModel tablaHabitaciones(JTextField texto) {
        return cargarTabla(texto.getText());
    }
    
    //Métod que retorna una tabla como el anterior método con sobrecarga de operadores
    public DefaultTableModel tablaHabitaciones() {
        return cargarTabla();
    }
    
    //Método que retorna un arreglo de tipo Objeto Habitacion
    public ArrayList<ObjetoHabitacion> selectHabitacion() {
        return selectHabitaciones();
    }
    
    //Método que inserta una nueva habitacion pasando el nombre de la habitacion, el piso de la habitacion, la categoria de habitacion, el precio sugerido, las caracteristicas de la habitacion y el estatus de la habitaci´n
    public void insertHabitacion(String nombre, String piso, String categoria, double precioSugerido, double precioHora,String caracteristicas, String estatus) {
        insertHabitaciones(nombre, piso, categoria, precioSugerido, precioHora, caracteristicas, estatus);
    }
    
    //Método que actualiza una habitacion pasando el nombre de la habotacion, el piso de la habitacion, la categoria de la habitacion, el precio sugerido, las caracteristicas de la habitacion, el estatus de la habitacion y el id de la habitacion
    public void updateHabitacion(String nombre, String piso, String categoria, double precioSugerido, double precioHora, String caracteristicas, String estatus, int id) {
        updateHabitaciones(nombre, piso, categoria, precioSugerido, precioHora, caracteristicas, estatus, id);
    }
    
    //Método que actualiza una habitacion pasando el nombre de la habitacion y el estatus de la habitacion
    public void updateHabitacion(String nombre, String estatusHabitacion) {
        updateHabitaciones(nombre, estatusHabitacion);
    }
    
    //Método que actualiza una habitacion pasando el id de la habitacion y el estatus de la habitacion
    public void updateHabitacion(int habitacion, String estatusHabitacion) {
        updateHabitaciones(habitacion, estatusHabitacion);
    }
    
    //Método que elimina una habitacion pasando el id de la habitacion
    public void deleteHabitacion(int id) {
        deleteHabitaciones(id);
    }
    
}
