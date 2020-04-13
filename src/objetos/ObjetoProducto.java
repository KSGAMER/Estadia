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
public class ObjetoProducto {
    private int idProducto;
    private String nombre;
    private String proveedor;
    private double precio;
    private int cantidad;
    private String observaciones;

    public ObjetoProducto(int idProducto, String nombre, String proveedor, double precio, int cantidad, String observaciones) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.precio = precio;
        this.cantidad = cantidad;
        this.observaciones = observaciones;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getObservaciones() {
        return observaciones;
    }
}
