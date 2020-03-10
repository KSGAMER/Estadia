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
public class ControladorEstatusPermisos extends ModeloEstatusPermisos{
    public DefaultTableModel tablaEstatusPermisos() {
        return cargarTabla();
    }
    
    public ArrayList<ObjetoEstatusPermiso> selectEstatusPermiso() {
        return selectEstatusPermisos();
    }
}
