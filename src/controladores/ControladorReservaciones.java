/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloReservaciones;
import objetos.ObjetoReservacion;

/**
 *
 * @author KSGAMER
 */
public class ControladorReservaciones extends ModeloReservaciones{
    
     public DefaultTableModel tablaReservaciones(JTextField texto) {
        return cargarTabla(texto.getText());
    }
    public DefaultTableModel tablaReservaciones() {
        return cargarTabla();
    }
    
    public ArrayList<ObjetoReservacion> selectReservacion() {
        return selectReservaciones();
    }
    
    public void insertReservacion(String nombre, String habitacion, String fechaIngreso, String fechaSalida, String usuario) {
        insertReservaciones(nombre, habitacion, fechaIngreso, fechaSalida, usuario);
    }
    
    public void updateReservacion(String nombre, String habitacion, String fechaIngreso, String fechaSalida, int id) {
        updateReservaciones(nombre, habitacion, fechaIngreso, fechaSalida, id);
    }
    
    public void deleteReservacion(int id) {
        deleteReservaciones(id);
    }
}
