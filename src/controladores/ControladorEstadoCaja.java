/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloEstatusCaja;
import objetos.ObjetoEstadoCaja;

/**
 *
 * @author KSGAMER
 */
public class ControladorEstadoCaja extends ModeloEstatusCaja{
    
    public DefaultTableModel tablaCaja() {
        return cargarTabla();
    }
    
    public ArrayList<ObjetoEstadoCaja> seleccionarCajas() {
        return selectEstadoCaja();
    }
    
}
