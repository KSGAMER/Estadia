/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloIncidencias;
import objetos.ObjetoIncidencia;

/**
 *
 * @author KSGAMER
 */
public class ControladorIncidencias extends ModeloIncidencias{
    public DefaultTableModel tablaIncidencias() {
        return cargarTabla();
    }
    
    public DefaultTableModel tablaIncidencias(String habitacion) {
        return cargarTabla(habitacion);
    }
    
    public ArrayList<ObjetoIncidencia> seleccionarIncidencias() {
        return selectIncidencias();
    }
    
    public void insertarIncidencias(String incidencia, String observaciones, String fecha, String hora, String usuario, String habitacion, String estado) {
        insertIncidencias(incidencia, observaciones, fecha, hora, usuario, habitacion, estado);
    }
    
    public void actualizarIncidencias(String incidencia, String observaciones, String fecha, String hora, String usuario, String habitacion, String estado, int id) {
        updateIncidencias(incidencia, observaciones, fecha, hora, usuario, habitacion, estado, id);
    }
    
    public void eliminarIncidencias(int id) {
        deleteIncidencias(id);
    }
}
