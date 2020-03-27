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
    private int idEstadoInventarioHabitacion;
    private String nombre;

    public ObjetoEstadoInvetararioHabitacion(int idEstadoInventarioHabitacion, String nombre) {
        this.idEstadoInventarioHabitacion = idEstadoInventarioHabitacion;
        this.nombre = nombre;
    }

    public int getIdEstadoInventarioHabitacion() {
        return idEstadoInventarioHabitacion;
    }

    public String getNombre() {
        return nombre;
    }
    
}
