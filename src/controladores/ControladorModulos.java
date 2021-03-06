/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloModulos;
import objetos.ObjetoModulo;

/**
 *
 * @author KSGAMER
 */

//Se aplica Herencia de la clase padre Modelo Modulos
public class ControladorModulos extends ModeloModulos{
    //Método que retorna una tabla 
    public DefaultTableModel tablaModulos() {
        return cargarTabla();
    }
    
    //Método que retorna un arreglo de tipo Objeto Modulo
    public ArrayList<ObjetoModulo> selectModulo() {
        return selectModulos();
    }
}
