/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloEnvioFacturacion;
import objetos.objetoEnvioFacturacion;

/**
 *
 * @author KSGAMER
 */
public class ControladorEnvioFacturacion extends ModeloEnvioFacturacion{
     public DefaultTableModel tablaEnvioFacturacion(JTextField Buscar) {
        return cargarTabla(Buscar.getText());
    }
    public DefaultTableModel tablaEnvioFacturacion() {
        return cargarTabla();
    }
      public DefaultTableModel RangoFechaCobroFacturacion(String fechaCobro,String fechaCobroFinal) {
        return cargarTabla(fechaCobro,fechaCobroFinal);
    }
    
    
    public ArrayList<objetoEnvioFacturacion> selectEnvioFacturacion() {
        return selectEnvioFacturaciones();
    }
}
