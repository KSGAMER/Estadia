/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloEstatusHabitaciones;
import objetos.ObjetoEstadoHabitacion;

/**
 *
 * @author KSGAMER
 */

//Se aplica Herencia de la clase padre Modelo Estatus Habitacion
public class ControladorEstatusHabitaciones extends ModeloEstatusHabitaciones{
    //Método que retorna una tabla
    public DefaultTableModel tablaEstadoHabiaciones() {
        return cargarTabla();
    }
    
    //Método que retorna un arreglo de tipo Objeto Estado Habitacion
    public ArrayList<ObjetoEstadoHabitacion> selectEstadoHabitacion() {
        return selectEstadoHabitaciones();
    }
}
