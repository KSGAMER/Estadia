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
public class ControladorEstatusHabitaciones extends ModeloEstatusHabitaciones{
    
    public DefaultTableModel tablaEstadoHabiaciones() {
        return cargarTabla();
    }
    
    public ArrayList<ObjetoEstadoHabitacion> selectEstadoHabitacion() {
        return selectEstadoHabitaciones();
    }
}
