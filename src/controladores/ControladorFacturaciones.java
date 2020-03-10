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
public class ControladorFacturaciones extends ModeloFacturaciones{
    
    public DefaultTableModel tablaFacturaciones() {
        return cargarTabla();
    }
    
    public ArrayList<ObjetoFacturacion> selectFacturacion() {
        return selectFacturaciones();
    }
}
