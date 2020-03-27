/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloEstadoInventarioHabitacion;
import objetos.ObjetoEstadoInvetararioHabitacion;

/**
 *
 * @author KSGAMER
 */
public class ControladorEstadoInventarioHabitacion extends ModeloEstadoInventarioHabitacion{
    public DefaultTableModel tablaEstadoInventarioHabitacion() {
        return cargarTabla();
    }
    
    public ArrayList<ObjetoEstadoInvetararioHabitacion> seleccionarEstadoInventarioHabitacion() {
        return selectEstadoInventarioHabitacion();
    }
}
