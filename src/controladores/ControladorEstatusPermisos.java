/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloEstatusPermisos;
import objetos.ObjetoEstatusPermiso;

/**
 *
 * @author KSGAMER
 */

//Se aplica Herencia de la clase Modelo Estatus Permiso
public class ControladorEstatusPermisos extends ModeloEstatusPermisos{
    //Método que retorna una tabla
    public DefaultTableModel tablaEstatusPermisos() {
        return cargarTabla();
    }
    
    //Método que retorna un arreglo de tipo Objeto Estatus Permiso
    public ArrayList<ObjetoEstatusPermiso> selectEstatusPermiso() {
        return selectEstatusPermisos();
    }
}
