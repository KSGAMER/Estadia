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
//Se aplica Herencia de la clase padre Modelo Reservaciones
public class ControladorReservaciones extends ModeloReservaciones {
    //Método que retorna una tabla pasando el parametro a filtrar de los resultados
    public DefaultTableModel tablaReservaciones(JTextField texto) {
        return cargarTabla(texto.getText());
    }

    //Método que retorna una tabla usando sobrecarga de operadores
    public DefaultTableModel tablaReservaciones() {
        return cargarTabla();
    }

    //Método que retorna un arreglo de tipo Objeto Reservacion
    public ArrayList<ObjetoReservacion> selectReservacion() {
        return selectReservaciones();
    }

    //Método que inserta una nueva reservacion pasando los parametros nombre del cliente, nombre de la habitacion, fecha de ingreso, fecha de salida y el usuario que da de alta la reservacion
    public void insertReservacion(String nombre, String habitacion, String fechaIngreso, String fechaSalida, String usuario, String estado) {
        insertReservaciones(nombre, habitacion, fechaIngreso, fechaSalida, usuario, estado);
    }

    //Método que actualiza una reservacion pasando los parametros nombre del cliente, nombre de la habitacion, fecha de ingreso, fecha de salida y el id de la reservacion
    public void updateReservacion(String nombre, String habitacion, String fechaIngreso, String fechaSalida, String estado,int id) {
        updateReservaciones(nombre, habitacion, fechaIngreso, fechaSalida, estado, id);
    }

    //Método que elimina una reservacion pasando los parametros id de la reservacion
    public void deleteReservacion(int id) {
        deleteReservaciones(id);
    }
}
