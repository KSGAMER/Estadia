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
public class ObjetoTipoPago {
    //Creación de los atributos del objeto Tipo Pago
    //+------------+--------+
    //| IdTipoPago | Nombre | <- (Extracción de Base de Datos)
    //+------------+--------+
    private int idTipoPago;
    private String nombre;

    //Creación del constructor para inicializar las variables idTipoPago y nombre
    public ObjetoTipoPago(int idTipoPago, String nombre) {
        this.idTipoPago = idTipoPago;
        this.nombre = nombre;
    }

    //Método que devuelve el "ID" del Tipo de Pago
    public int getIdTipoPago() {
        return idTipoPago;
    }

    //Método que devuelve el "Nombre" del Tipo de Pago (Efectivo o Tarjeta de Credito)
    public String getNombre() {
        return nombre;
    }
    
}
