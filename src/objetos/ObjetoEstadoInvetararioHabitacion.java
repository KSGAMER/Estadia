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
public class ObjetoEstadoInvetararioHabitacion {
    private int idEstadoHabitacion;
    private String nombre;

    public ObjetoEstadoInvetararioHabitacion(int idEstadoHabitacion, String nombre) {
        this.idEstadoHabitacion = idEstadoHabitacion;
        this.nombre = nombre;
    }

    public int getIdEstadoHabitacion() {
        return idEstadoHabitacion;
    }

    public String getNombre() {
        return nombre;
    }
    
}
