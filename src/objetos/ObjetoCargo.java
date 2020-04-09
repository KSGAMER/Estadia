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
public class ObjetoCargo {
    private int idCargo;
    private String nombre;
    private String observaciones;

    public ObjetoCargo(int idCargo, String nombre, String observaciones) {
        this.idCargo = idCargo;
        this.nombre = nombre;
        this.observaciones = observaciones;
    }

    public int getIdCargo() {
        return idCargo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getObservaciones() {
        return observaciones;
    }
    
}
