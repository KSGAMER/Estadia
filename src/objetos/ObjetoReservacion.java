/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.util.Date;

/**
 *
 * @author KSGAMER
 */
public class ObjetoReservacion {
    //Creación de los atributos del objeto Reservación
    //+---------------+--------+--------------+--------------+-------------+
    //| IdReservación | Nombre | IdHabitación | FechaIngreso | FechaSalida | <- (Extracción de Base de Datos)
    //+---------------+--------+--------------+--------------+-------------+
    private int idReservacion;
    private String nombre;
    private int idHabitacion;
    private String fechaIngresoCompleta;
    private String fechaSalidaCompleta;

    //Creación del constructor para inicializar las variables idReservación, nombre, fechaIngresoCompleta, fechaSalidaCompleta
    public ObjetoReservacion(int idReservacion, String nombre, int idHabitacion, String fechaIngresoCompleta, String fechaSalidaCompleta) {
        this.idReservacion = idReservacion;
        this.nombre = nombre;
        this.idHabitacion = idHabitacion;
        this.fechaIngresoCompleta = fechaIngresoCompleta;
        this.fechaSalidaCompleta = fechaSalidaCompleta;
    }

    //Método que devuelve el "ID" de la Reservación
    public int getIdReservacion() {
        return idReservacion;
    }

    //Método que devuelve el "Nombre del Cliente que reservo" de la tabla Reservación
    public String getNombre() {
        return nombre;
    }

    //Método que devuelve el "ID de la Habitación" de la tabla Reservación
    public int getIdHabitacion() {
        return idHabitacion;
    }

    //Método que devuele la "Fecha de Ingreso del Cliente" de la tabla Reservación
    public String getFechaIngresoCompleta() {
        return fechaIngresoCompleta;
    }

    //Método que devuele la "Fecha de Salida del Cliente" de la tabla Reservación
    public String getFechaSalidaCompleta() {
        return fechaSalidaCompleta;
    }
    
}
