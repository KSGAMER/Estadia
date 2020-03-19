/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloFacturaciones;
import objetos.ObjetoFacturacion;

/**
 *
 * @author KSGAMER
 */

//Se aplica Herencia de la clase padre Modelo Facturaciones
public class ControladorFacturaciones extends ModeloFacturaciones{
    //Método que retorna una tabla
    public DefaultTableModel tablaFacturaciones() {
        return cargarTabla();
    }
    
    //Método que retorna un arreglo de tipo Objeto Facturacion
    public ArrayList<ObjetoFacturacion> selectFacturacion() {
        return selectFacturaciones();
    }
}
