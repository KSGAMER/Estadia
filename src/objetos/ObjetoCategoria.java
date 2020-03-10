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
public class ObjetoCategoria {
    //Creación de los atributos del objeto Categoria
    //+-------------+----------+----------------+---------+
    //| IdCategoria |  Nombre  |  Observaciones | Mostrar | <- (Extracción de Base de Datos tabla Categoria)
    //+-------------+----------+----------------+---------+
    private int idCategoria;
    private String nombre;
    private String observaciones;
    private String mostrar;

    //Constructor de Categoria e instanciar los valores de la Clase
    public ObjetoCategoria(int idCategoria, String nombre, String observaciones, String mostrar) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.observaciones = observaciones;
        this.mostrar = mostrar;
    }

    //Método para obtener el "ID" de la Categoria
    public int getIdCategoria() {
        return idCategoria;
    }

    //Método para obtener el "Nombre" de la Categoria
    public String getNombre() {
        return nombre;
    }

    //Método para obtener las "Observaciones" de la Categoria
    public String getObservaciones() {
        return observaciones;
    }

    //Método para obtener la bandera "Mostrar" de la Categoria
    public String getMostrar() {
        return mostrar;
    }
    
}
