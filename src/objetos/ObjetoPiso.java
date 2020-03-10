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
public class ObjetoPiso {
    //Creación de los atributos del objeto Piso
    //+--------+--------+---------------+
    //| IdPiso | Nombre | Observaciones | <- (Extracción de Base de Datos)
    //+--------+--------+---------------+
    private int idPiso;
    private String nombre;
    private String observaciones;

    //Creación del constructor para inicializar las variables idPiso, nombre y observaciones
    public ObjetoPiso(int idPiso, String nombre, String observaciones) {
        this.idPiso = idPiso;
        this.nombre = nombre;
        this.observaciones = observaciones;
    }

    //Método que devuelve el "ID" del Piso
    public int getIdPiso() {
        return idPiso;
    }

    //Método que devuelve el "Nombre" del Piso
    public String getNombre() {
        return nombre;
    }

    //Método que devuelve las "Observaciones" del Piso
    public String getObservaciones() {
        return observaciones;
    }
    
}
