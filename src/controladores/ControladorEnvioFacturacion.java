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
//Se aplica Herencia desde la clase padre ModeloEnvioFacturación
public class ControladorEnvioFacturacion extends ModeloEnvioFacturacion {
    //Método que retorna una tabla pasando un parametro para filtrar entre los resultados
    public DefaultTableModel tablaEnvioFacturacion(JTextField Buscar) {
        return cargarTabla(Buscar.getText());
    }

    //Método que retorna una tabla al que el anterior método usando sobrecarga de operadores
    public DefaultTableModel tablaEnvioFacturacion() {
        return cargarTabla();
    }

    //Método que retorna una tabla pasando los parametros fecha Inicial y fecha Final
    public DefaultTableModel RangoFechaCobroFacturacion(String fechaCobroInicial, String fechaCobroFinal) {
        return cargarTabla(fechaCobroInicial, fechaCobroFinal);
    }

    //Método que retorna un arreglo de tipo Objeto Envio Facturación
    public ArrayList<objetoEnvioFacturacion> selectEnvioFacturacion() {
        return selectEnvioFacturaciones();
    }
}
