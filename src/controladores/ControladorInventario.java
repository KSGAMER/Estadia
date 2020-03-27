/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloInventario;
import objetos.ObjetoInventario;

/**
 *
 * @author KSGAMER
 */
public class ControladorInventario extends ModeloInventario{
    public DefaultTableModel tablaInventario(String filtro) {
        return cargarTabla(filtro);
    }
    
    public ArrayList<ObjetoInventario> seleccionarInventario() {
        return selectInventario();
    }
    
    public void insertarInventario(String producto, int cantidad, String observaciones) {
        insertInventario(producto, cantidad, observaciones);
    }
    
    public void actualizarInventario(String producto, int cantidad, String observaciones, int id) {
        updateInventario(producto, cantidad, observaciones, id);
    }
    
    public void eliminarInventario(int id) {
        deleteInventario(id);
    }
}
