/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.HashMap;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloReportes;

/**
 *
 * @author KSGAMER
 */

//Se aplica Herencia de la clase padre Modelo Reportes
public class ControladorReportes extends ModeloReportes{
    //Método que retorna una tabla
    public DefaultTableModel habitacionPopular() {
        return popularHabitacion();
    }
    
    //Método que retorna una tabla
    public DefaultTableModel ganancias() {
        return gananciasPorFechas();
    }
    
    public DefaultTableModel facturacionIndice() {
        return indiceFacturacion();
    }
    
    //Método que retorna una tabla
    public DefaultTableModel ReservacionesIndice() {
        return indiceReservaciones();
    }
    
    //Método que retorna una tabla
    public DefaultTableModel porcentajeGanancias() {
        return gananciasHoy();
    }
    
    //Método que retorna una tabla
    public DefaultTableModel porcentajeReservacion() {
        return porcentajeReservaciones();
    }
    
    //Método que retorna una tabla pasando los parametros fecha Inicial, fecha Final y modo (Modo contiene dos estados "Tabla o Grafica")
    public DefaultTableModel fechasGanancias(String fechaInicial, String fechaFinal, String modo) {
        return gananciasFechas(fechaInicial, fechaFinal, modo);
    }
    
    //Método que retorna una tabla pasando los parametros habitacion y modo (Modo contiene dos estados "Tabla o Grafica")
    public DefaultTableModel gananciasHabitacion(String habitacion, String modo) {
        //Si habitacion es igual a la cadena "Todas las habitaciones"
        if(habitacion.equals("Todas las habitaciones")) {
            //Se manda nada y el modo
            return gananciasHabitaciones("", modo);
        } else {// de lo contrario
            //Se manda la habitacion y el modo
            return gananciasHabitaciones(habitacion, modo);
        }
    }
    
    //Método que retorna una tabla pasando los parametros usuario para filtrar los resultados
    public DefaultTableModel cobroPorUsuarios(String usuario) {
        return cobroUsuario(usuario);
    }
    
    //Método que exporta a Excel pasando una tabla
    public void exportExcel(JTable table) {
        excel(table);
    }
    
    //Método que genera un archivo JasperReport pasando el nombre del reporte
    public void reporteGenerar(String archivo) {
        generarReporte(archivo);
    }
    
    //Método que genera un archivo JasperReport pasando el nombre del reporte y los parametros a filtrar
    public void reporteGenerar(String archivo, HashMap parametros) {
        generarReporte(archivo, parametros);
    }
}
