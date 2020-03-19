/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloCFDI;
import objetos.ObjetoCFDI;

/**
 *
 * @author KSGAMER
 */

//Se aplica Herencia de la clase padre Modelo CFDI
public class ControladorCFDI extends ModeloCFDI {
    //Método que retorna una tabla
    public DefaultTableModel tablaCFDI() {
        return cargarTabla();
    }
    
    //Método que retorna un arreglo de tipo Objeto CFDI
    public ArrayList<ObjetoCFDI> selectCFDI() {
        return selectCFDIs();
    }
}
