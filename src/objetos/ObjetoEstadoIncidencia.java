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
public class ObjetoEstadoIncidencia {
    private int idEstadoIncidencia;
    private String nombre;

    public ObjetoEstadoIncidencia(int idEstadoIncidencia, String nombre) {
        this.idEstadoIncidencia = idEstadoIncidencia;
        this.nombre = nombre;
    }

    public int getIdEstadoIncidencia() {
        return idEstadoIncidencia;
    }

    public String getNombre() {
        return nombre;
    }
    
}
