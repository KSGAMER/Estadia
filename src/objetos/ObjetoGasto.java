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
public class ObjetoGasto {
    private int idGastos;
    private String nombre;
    private double cantidad;
    private String descripcion;
    private String usuario;
    private String fechaActual;

    public ObjetoGasto(int idGastos, String nombre, double cantidad, String descripcion, String usuario, String fechaActual) {
        this.idGastos = idGastos;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.fechaActual = fechaActual;
    }

    public int getIdGastos() {
        return idGastos;
    }

    public String getNombre() {
        return nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getFechaActual() {
        return fechaActual;
    }
        
}
