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
public class ObjetoEstadoCaja {
    private int idEstadoCaja;
    private String nombre;

    public ObjetoEstadoCaja(int idEstadoCaja, String nombre) {
        this.idEstadoCaja = idEstadoCaja;
        this.nombre = nombre;
    }

    public int getIdEstadoCaja() {
        return idEstadoCaja;
    }

    public String getNombre() {
        return nombre;
    }
        
}
