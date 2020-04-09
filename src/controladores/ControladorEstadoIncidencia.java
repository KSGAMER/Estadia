/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloEstadoIncidencia;
import objetos.ObjetoEstadoIncidencia;

/**
 *
 * @author KSGAMER
 */
public class ControladorEstadoIncidencia extends ModeloEstadoIncidencia{
    public DefaultTableModel tablaEstadoIncidencia() {
        return cargarTabla();
    }
    
    public ArrayList<ObjetoEstadoIncidencia> seleccionarEstadoIncidencia() {
        return selectEstadoIncidencias();
    }
}
