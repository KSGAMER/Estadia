/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloCargos;
import objetos.ObjetoCargo;

/**
 *
 * @author KSGAMER
 */
public class ControladorCargos extends ModeloCargos{
    public DefaultTableModel tablaCargos() {
        return cargarTabla();
    }
    
    public ArrayList<ObjetoCargo> seleccionarCargo() {
        return selectCargos();
    }
    
    public void insertarCargo(String nombre, String observaciones) {
        insertCargos(nombre, observaciones);
    }
    
    public void actualizarCargo(String nombre, String observaciones, int id) {
        updateCargos(nombre, observaciones, id);
    }
    
    public void eleiminarCargo(int id) {
        deleteCargos(id);
    }
}
