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

//Se aplica Herencia de la clase padre Modelo Permisos
public class ControladorPermisos extends ModeloPermisos{
    //Método que retorna una tabla
    public DefaultTableModel tablaPermisos() {
        return cargarTabla();
    }
    
    //Método que retorna un arreglo de tipo Objeto Permiso
    public ArrayList<ObjetoPermiso> selectPermiso() {
        return selectPermisos();
    }
    
    //Método que inserta un nuevo permiso pasando el usuario, el modulo, la bandera de consultar, la bandera de insertar, la bandera de actualizar, la bandera de eliminar
    public void insertPermiso(String username, String modulo, String consultar, String insertar, String actualizar, String eliminar) {
        insertPermisos(username, modulo, consultar, insertar, actualizar, eliminar);
    }
    
    //Método que actualiza un permiso pasando el usuario, el modulo, la bandera de consultar, la bandera de insertarm la bandera de actualizar, la bandera de eliminar y el id del permiso
    public void updatePermiso(String username, String modulo, String consultar, String insertar, String actualizar, String eliminar, int id) {
        updatePermisos(username, modulo, consultar, insertar, actualizar, eliminar, id);
    }
    
    //Método que elimina un permiso pasando el id del permiso
    public void deletePermiso(int id) {
        deletePermisos(id);
    }
}
