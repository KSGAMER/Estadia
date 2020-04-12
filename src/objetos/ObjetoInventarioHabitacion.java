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
    private int idProducto;
    private int total;
    private int idHabitacion;
    private String usuario;

    public ObjetoInventarioHabitacion(int idInventarioHabitacion, int idProducto, int total, int idHabitacion, String usuario) {
        this.idInventarioHabitacion = idInventarioHabitacion;
        this.idProducto = idProducto;
        this.total = total;
        this.idHabitacion = idHabitacion;
        this.usuario = usuario;
    }

    public int getIdInventarioHabitacion() {
        return idInventarioHabitacion;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public int getTotal() {
        return total;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public String getUsuario() {
        return usuario;
    }
}
