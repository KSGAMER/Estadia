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
public class ControladorCFDI extends ModeloCFDI {
    
    public DefaultTableModel tablaCFDI() {
        return cargarTabla();
    }
    
    public ArrayList<ObjetoCFDI> selectCFDI() {
        return selectCFDIs();
    }
}
