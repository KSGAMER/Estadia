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
public class ObjetoInventario {
    private int idInventario;
    private String nombre;
    private int stock;
    private String observaciones;

    public ObjetoInventario(int idInventario, String nombre, int stock, String observaciones) {
        this.idInventario = idInventario;
        this.nombre = nombre;
        this.stock = stock;
        this.observaciones = observaciones;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public String getNombre() {
        return nombre;
    }

    public int getStock() {
        return stock;
    }

    public String getObservaciones() {
        return observaciones;
    }
    
}
