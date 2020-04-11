/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloEstadoReservacion;
import objetos.ObjetoEstadoReservacion;

/**
 *
 * @author KSGAMER
 */
public class ControladorEstadoReservacion extends ModeloEstadoReservacion{
    public DefaultTableModel tablaEstadoReservacion() {
        return cargarTabla();
    }
    
    public ArrayList<ObjetoEstadoReservacion> seleccionarEstadoReservacion() {
        return selectEstadoReservacion();
    }
}
