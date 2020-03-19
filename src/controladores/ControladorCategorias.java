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

//Se aplica Herencia desde la clase padre Modelo Categorias
public class ControladorCategorias extends ModeloCategorias{
    //Método que retorna una tabla pasando dos parametros, el primero es un buscador y el segundo es una bandera
    public DefaultTableModel tablaCategorias(JTextField texto, boolean bool) {
        return cargarTabla(texto.getText(), bool);
    }
    
    //Método que retorna una tabla, en este método como el anterior se utiliza la sobrecarga de operadores
    public DefaultTableModel tablaCategorias() {
        return cargarTabla();
    }
    
    //Método que retorna un arreglo de tipo Objeto Categoria
    public ArrayList<ObjetoCategoria> selectCategoria() {
        return selectCategorias();
    }
    
    //Método que pasa los parametros nombre y observaciones para agregar una nueva categoria
    public void insertCategoria(String nombre, String observaciones) {
        insertarCategorias(nombre, observaciones);
    }
    
    //Método que pasa los parametros nombre, observaciones y el id de la categoria para poder actualizarla
    public void updateCategoria(String nombre, String observaciones, int id) {
        updateCategorias(nombre, observaciones, id);
    }
    
    //Método que pasa los parametros id para eliminar una categoria
    public void deleteCategoria(int id) {
        deleteCategorias(id);
    }
}
