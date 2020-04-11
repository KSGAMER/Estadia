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
public class ObjetoEstadoReservacion {
    private int idEstadoReservacion;
    private String nombre;

    public ObjetoEstadoReservacion(int idEstadoReservacion, String nombre) {
        this.idEstadoReservacion = idEstadoReservacion;
        this.nombre = nombre;
    }

    public int getIdEstadoReservacion() {
        return idEstadoReservacion;
    }

    public String getNombre() {
        return nombre;
    }
    
}
