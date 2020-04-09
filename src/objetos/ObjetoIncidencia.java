/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

/**
 *
 * @author KSGAMER
 */
public class ObjetoIncidencia {
    private int idIncidencia;
    private String nombre;
    private String observaciones;
    private String fechaIncidencia;
    private String horaIncidencia;
    private String usuario;
    private int idHabitacion;
    private int idEstadoIncidencia;

    public ObjetoIncidencia(int idIncidencia, String nombre, String observaciones, String fechaIncidencia, String horaIncidencia, String usuario, int idHabitacion, int idEstadoIncidencia) {
        this.idIncidencia = idIncidencia;
        this.nombre = nombre;
        this.observaciones = observaciones;
        this.fechaIncidencia = fechaIncidencia;
        this.horaIncidencia = horaIncidencia;
        this.usuario = usuario;
        this.idHabitacion = idHabitacion;
        this.idEstadoIncidencia = idEstadoIncidencia;
    }

    public int getIdIncidencia() {
        return idIncidencia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getFechaIncidencia() {
        return fechaIncidencia;
    }

    public String getHoraIncidencia() {
        return horaIncidencia;
    }

    public String getUsuario() {
        return usuario;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public int getIdEstadoIncidencia() {
        return idEstadoIncidencia;
    }
    
}
