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
public class ObjetoCobro {
    //Creación de las variables para generar objetos Cobro
    //+---------------+-------+------------+-----+--------+------------+---------------+
    //| IdReservacion | Monto | IdTipoPago | RFC | Correo | FechaCobro | IdFacturacion | <- (Extracción de Base de Datos tabla Cobro)
    //+---------------+-------+------------+-----+--------+------------+---------------+

    private double monto;
    private int idTipoPago;
    private String rfc;
    private String correo;
    private String fechaCobro;
    private int idFacturacion;
    private int idHabitacion;

    //Constructor de Cobro e instanciar los valores de la Clase

    public ObjetoCobro(double monto, int idTipoPago, String rfc, String correo, String fechaCobro, int idFacturacion, int idHabitacion) {
        this.monto = monto;
        this.idTipoPago = idTipoPago;
        this.rfc = rfc;
        this.correo = correo;
        this.fechaCobro = fechaCobro;
        this.idFacturacion = idFacturacion;
        this.idHabitacion = idHabitacion;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }
    
    //Método para obtener el "Monto" del Cobro
    public double getMonto() {
        return monto;
    }

    //Método para obtener el "IdTipoPago" del Cobro
    public int getIdTipoPago() {
        return idTipoPago;
    }

    //Método para obtener el "RFC" del Cobro
    public String getRfc() {
        return rfc;
    }

    //Método para obtener el "Correo" del Cobro
    public String getCorreo() {
        return correo;
    }

    //Método para obtener la "FechaCobro" del Cobro
    public String getFechaCobro() {
        return fechaCobro;
    }

    //Método para obtener el "IdFacturacion" del Cobro
    public int getIdFacturacion() {
        return idFacturacion;
    }
    
}
