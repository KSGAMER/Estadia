/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloTipoPagos;
import objetos.ObjetoTipoPago;

/**
 *
 * @author KSGAMER
 */
public class ControladorTipoPagos extends ModeloTipoPagos{
    
    public DefaultTableModel tablaTipoPagos() {
        return cargarTabla();
    }
    
    public ArrayList<ObjetoTipoPago> selectTipoPago() {
        return selectTipoPagos();
    }
}
