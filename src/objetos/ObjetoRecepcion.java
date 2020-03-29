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
public class ObjetoRecepcion {
    //Creación de los atributos del objeto Recepción
    //+--------------+-----------+--------+-------------------+--------+----------------+
    //| IdHabitacion | Categoria | IdPiso | EstatusHabitación | Nombre | PrecioSugerido | <- (Extracción de Base de Datos)
    //+--------------+-----------+--------+-------------------+--------+----------------+
    private int idHabitacion;
    private String categoria;
    private int idPiso;
    private String estatusHabitacion;
    private String nombre;
    private double precioSugerido;
    private double precioxHora;
    private String Descripcion;


    //Creación del constructor para inicializar las variables idHabitacion, categoria (Pre-Procesado), idPiso, estatusHabitacion (Pre-Procesado), nombre, precioSugerido
    public ObjetoRecepcion(int idHabitacion, String categoria, int idPiso, String estatusHabitacion, String nombre, double precioSugerido, double precioxHora,String Descripcion) {
        this.idHabitacion = idHabitacion;
        this.categoria = categoria;
        this.idPiso = idPiso;
        this.estatusHabitacion = estatusHabitacion;
        this.nombre = nombre;
        this.precioSugerido = precioSugerido;
        this.precioxHora= precioxHora;
        this.Descripcion= Descripcion;
    }
    

    //Método que devuelve el "ID" de la Habitación dentro de la consulta Recepción
    public int getIdHabitacion() {
        return idHabitacion;
    }
    
    //Método que devuelve la "Categoria" de la consulta Recepción

    public String getCategoria() {
        return categoria;
    }

    //Método que devuelve el "ID" del Piso dentro de la consulta Recepción
    public int getIdPiso() {
        return idPiso;
    }

    //Método que devuelve el "Estatus de la Habitación" de la consulta Recepción
    public String getEstatusHabitacion() {
        return estatusHabitacion;
    }

    //Método que devuelve el "Nombre de la Habitación" de la consulta Recepción 
    public String getNombre() {
        return nombre;
    }

    //Método que devuelve el "Precio Sugerido" de la Habitación de la consulta Recepción
    public double getPrecioSugerido() {
        return precioSugerido;
    }
     public double getPrecioxHora() {
        return precioxHora;
    }
     
    public String getDescripcion() {
        return Descripcion;
    }

   
}
