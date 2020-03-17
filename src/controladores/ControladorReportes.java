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
public class ControladorReportes extends ModeloReportes{
    
    public DefaultTableModel habitacionPopular() {
        return popularHabitacion();
    }
    
    public DefaultTableModel ganancias() {
        return gananciasPorFechas();
    }
    
    public DefaultTableModel facturacionIndice() {
        return indiceFacturacion();
    }
    
    public DefaultTableModel ReservacionesIndice() {
        return indiceReservaciones();
    }
    
    public DefaultTableModel porcentajeGanancias() {
        return gananciasHoy();
    }
    
    public DefaultTableModel porcentajeReservacion() {
        return porcentajeReservaciones();
    }
    
    public DefaultTableModel fechasGanancias(String fechaInicial, String fechaFinal, String modo) {
        return gananciasFechas(fechaInicial, fechaFinal, modo);
    }
    
    public DefaultTableModel gananciasHabitacion(String habitacion, String modo) {
        if(habitacion.equals("Todas las habitaciones")) {
            return gananciasHabitaciones("", modo);
        } else {
            return gananciasHabitaciones(habitacion, modo);
        }
    }
    
    public void exportExcel(JTable table) {
        excel(table);
    }
    
    public void reporteGenerar(String archivo) {
        generarReporte(archivo);
    }
    
    public void reporteGenerar(String archivo, HashMap parametros) {
        generarReporte(archivo, parametros);
    }
}
