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

//Se aplica Herencia de la clase padre Modelo Pisos
public class ControladorPisos extends ModeloPisos{
    //Método que retorna una tabla pasando un parametro para filtrar los resultados
    public DefaultTableModel tablaPisos(JTextField texto) {
        return cargarTabla(texto.getText());
    }
    
    //Método que retorna una tabla usando sobrecarga de operadores
    public DefaultTableModel tablaPisos() {
        return cargarTabla();
    }
    
    //Método que retorna un arreglo de tipo Objeto Piso
    public ArrayList<ObjetoPiso> selectPiso() {
        return selectPisos();
    }
    
    //Método que inserta un nuevo piso pasando nombre del piso y las observaciones
    public void insertPiso(String nombre, String observaciones) {
        insertPisos(nombre, observaciones);
    }
    
    //Método que actualiza un piso pasando el nombre del piso, las observaciones y el id del piso
    public void updatePiso(String nombre, String observaciones, int id) {
        updatePisos(nombre, observaciones, id);
    }
    
    //Método que elimina un piso pasando el id del piso
    public void deletePiso(int id) {
        deletePisos(id);
    }
}
