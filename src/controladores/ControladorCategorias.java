/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloCategorias;
import objetos.ObjetoCategoria;

/**
 *
 * @author KSGAMER
 */
public class ControladorCategorias extends ModeloCategorias{
    
    public DefaultTableModel tablaCategorias(JTextField texto, boolean bool) {
        return cargarTabla(texto.getText(), bool);
    }
    
    public DefaultTableModel tablaCategorias() {
        return cargarTabla();
    }
    
    public ArrayList<ObjetoCategoria> selectCategoria() {
        return selectCategorias();
    }
    
    public void insertCategoria(String nombre, String observaciones) {
        insertarCategorias(nombre, observaciones);
    }
    
    public void updateCategoria(String nombre, String observaciones, int id) {
        updateCategorias(nombre, observaciones, id);
    }
    
    public void deleteCategoria(int id) {
        deleteCategorias(id);
    }
}
