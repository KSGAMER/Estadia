/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloEstatusUsuarios;
import objetos.ObjetoEstatusUsuario;

/**
 *
 * @author KSGAMER
 */

//Se aplica Herencia de la clase padre Modelo Estatus Usuario
public class ControladorEstatusUsuarios extends ModeloEstatusUsuarios{
    //Método que retorna una tabla
    public DefaultTableModel tablaEstatusUsuarios() {
        return cargarTabla();
    }
    
    //Método que retorna un arreglo de tipo Objeto Estatus Usuario
    public ArrayList<ObjetoEstatusUsuario> selectEstatusUsuario() {
        return selectEstatusUsuarios();
    }
}
