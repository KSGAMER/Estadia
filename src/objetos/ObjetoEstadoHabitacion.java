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
public class ObjetoEstadoHabitacion {

    //Creación de los atributos del objeto Estado Habitación
    //+--------------------+--------+
    //| IdEstadoHabitacion | Nombre |   <- (Extracción de Base de Datos de la tabla EstadoHabitacion)
    //+--------------------+--------+
    private int idEstadoHabitacion;
    private String nombre;

    //Creación del constructor para inicializar las variables idEstadoHabitación y nombre
    public ObjetoEstadoHabitacion(int idEstadoHabitacion, String nombre) {
        this.idEstadoHabitacion = idEstadoHabitacion;
        this.nombre = nombre;
    }

    //Método que devuelve el "Id" del Estado de la Habitación
    public int getIdEstadoHabitacion() {
        return idEstadoHabitacion;
    }

    //Método que devuelve el "Nombre" del Estado de la Habitación (Disponible / Reservado / Limpieza)
    public String getNombre() {
        return nombre;
    }
    
}
