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
public class ObjetoInventarioHabitacion {
    private int idInventarioHabitacion;
    private int idHabitacion;
    private int idInventario;
    private int consumo;
    private int idEstadoInventarioHabitacion;

    public ObjetoInventarioHabitacion(int idInventarioHabitacion, int idHabitacion, int idInventario, int consumo, int idEstadoInventarioHabitacion) {
        this.idInventarioHabitacion = idInventarioHabitacion;
        this.idHabitacion = idHabitacion;
        this.idInventario = idInventario;
        this.consumo = consumo;
        this.idEstadoInventarioHabitacion = idEstadoInventarioHabitacion;
    }

    public int getIdInventarioHabitacion() {
        return idInventarioHabitacion;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public int getConsumo() {
        return consumo;
    }

    public int getIdEstadoInventarioHabitacion() {
        return idEstadoInventarioHabitacion;
    }
    
}
