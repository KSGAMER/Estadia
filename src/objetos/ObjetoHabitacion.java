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
public class ObjetoHabitacion {
    //Creación de los atributos del objeto Habitación
    //+--------------+--------+--------+-------------+----------------+-----------------+--------------------+
    //| IdHabitación | Nombre | IdPiso | IdCategoria | PrecioSugerido | Caracteristicas | IdEstadoHabitacion | <- (Extracción de Base de Datos)
    //+--------------+--------+--------+-------------+----------------+-----------------+--------------------+
    private int idHabitacion;
    private String nombre;
    private int idPiso;
    private int idCategoria;
    private double precioSugerido;
    private String caracteristicas;
    private int idEstadoHabitacion;
    private Double precioxHora;

  
    //Creación del constructor para inicializar las variables idHabitación, nombre, idPiso, idCategoria, precioSugerido, caracteristicas e idEstadoHabitacion
    public ObjetoHabitacion(int idHabitacion, String nombre, int idPiso, int idCategoria, double precioSugerido,Double precioxHora, String caracteristicas, int idEstadoHabitacion) {
        this.idHabitacion = idHabitacion;
        this.nombre = nombre;
        this.idPiso = idPiso;
        this.idCategoria = idCategoria;
        this.precioSugerido = precioSugerido;
        this.caracteristicas = caracteristicas;
        this.idEstadoHabitacion = idEstadoHabitacion;
        this.precioxHora=precioxHora;
    }

    //Método que devuelve el "ID" de la habitación
    public int getIdHabitacion() {
        return idHabitacion;
    }

    //Método que devuelve el "Nombre" de la habitación
    public String getNombre() {
        return nombre;
    }

    //Método que devuelve el "ID" del piso alojado en la tabla Habitación
    public int getIdPiso() {
        return idPiso;
    }

    //Método que devuelve el "ID" de la categoria alojado en la tabla Habitación
    public int getIdCategoria() {
        return idCategoria;
    }

    //Método que devuelve el "Precio Sugerido" de la Habitación
    public double getPrecioSugerido() {
        return precioSugerido;
    }

    //Método que devuelve las "Caracteristicas" de la Habitación
    public String getCaracteristicas() {
        return caracteristicas;
    }

    //Método que devuelve el "ID" del Estado de la Habitación alojado en la tabla Habitación
    public int getIdEstadoHabitacion() {
        return idEstadoHabitacion;
    }
      public double getPrecioxHora() {
        return precioxHora;
    }


}
