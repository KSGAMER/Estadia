/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloEnvioFacturacion;
import objetos.objetoEnvioFacturacion;

/**
 *
 * @author KSGAMER
 */
public class ControladorEnvioFacturacion extends ModeloEnvioFacturacion{
    public DefaultTableModel tablaEnvioFacturacion() {
        return cargarTabla();
    }
      public DefaultTableModel RangoFechaCobroFacturacion(String fechaCobro) {
        return cargarTabla(fechaCobro);
    }
    
    
    public ArrayList<objetoEnvioFacturacion> selectEnvioFacturacion() {
        return selectEnvioFacturaciones();
    }
}
