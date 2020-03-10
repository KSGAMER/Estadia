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
public class ControladorModulos extends ModeloModulos{
    public DefaultTableModel tablaModulos() {
        return cargarTabla();
    }
    
    public ArrayList<ObjetoModulo> selectModulo() {
        return selectModulos();
    }
}
