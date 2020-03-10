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
public class ObjetoFacturacion {
    //Creación de los atributos del objeto Facturación
    //+---------------+--------+
    //| IdFacturación | Nombre | <- (Extracción de Base de Datos de la tabla Facturación)
    //+---------------+--------+
    private int idFacturacion;
    private String nombre;

    //Creación del consytuctor para inicializar las variables idFacturacion y nombre
    public ObjetoFacturacion(int idFacturacion, String nombre) {
        this.idFacturacion = idFacturacion;
        this.nombre = nombre;
    }

    //Método que devuelve el "ID" de la facturación
    public int getIdFacturacion() {
        return idFacturacion;
    }

    //Método que devuelve el "Nombre" de la facturación (Con Facturación, Sin Facturación)
    public String getNombre() {
        return nombre;
    }
    
}
