/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloPermisos;
import objetos.ObjetoPermiso;

/**
 *
 * @author KSGAMER
 */
public class ControladorPermisos extends ModeloPermisos{
    public DefaultTableModel tablaPermisos() {
        return cargarTabla();
    }
    
    public ArrayList<ObjetoPermiso> selectPermiso() {
        return selectPermisos();
    }
    
    public void insertPermiso(String username, String modulo, String consultar, String insertar, String actualizar, String eliminar) {
        insertPermisos(username, modulo, consultar, insertar, actualizar, eliminar);
    }
    
    public void updatePermiso(String username, String modulo, String consultar, String insertar, String actualizar, String eliminar, int id) {
        updatePermisos(username, modulo, consultar, insertar, actualizar, eliminar, id);
    }
    
    public void deletePermiso(int id) {
        deletePermisos(id);
    }
}
