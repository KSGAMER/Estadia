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
public class ObjetoModulo {
    //Creación de los atributos del objeto Módulo
    //+----------+--------+
    //| IdModulo | Nombre | <- (Extracción de Base de Datos)
    //+----------+--------+
    private int idModulo;
    private String nombre;

    //Creación del constructor para inicializar las variables idModulo y nombre
    public ObjetoModulo(int idModulo, String nombre) {
        this.idModulo = idModulo;
        this.nombre = nombre;
    }

    //Método que devuelve el "ID" del Módulo
    public int getIdModulo() {
        return idModulo;
    }

    //Método que devuelve el "Nombre" del Módulo
    public String getNombre() {
        return nombre;
    }
    
}
