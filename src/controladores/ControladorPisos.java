/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloPisos;
import objetos.ObjetoPiso;

/**
 *
 * @author KSGAMER
 */
public class ControladorPisos extends ModeloPisos{
    
    public DefaultTableModel tablaPisos(JTextField texto) {
        return cargarTabla(texto.getText());
    }
    
    public DefaultTableModel tablaPisos() {
        return cargarTabla();
    }
    
    public ArrayList<ObjetoPiso> selectPiso() {
        return selectPisos();
    }
    
    public void insertPiso(String nombre, String observaciones) {
        insertPisos(nombre, observaciones);
    }
    
    public void updatePiso(String nombre, String observaciones, int id) {
        updatePisos(nombre, observaciones, id);
    }
    
    public void deletePiso(int id) {
        deletePisos(id);
    }
}
