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
public class objetoEnvioFacturacion {
    private String nombre;
    private String correo;
    private String tipoPago;
    private String fechaIngreso;
    private String fechaSalida;
    private String fechaCobro;
    private double monto;

    public objetoEnvioFacturacion(String nombre, String correo, String tipoPago, String fechaIngreso, String fechaSalida, String fechaCobro, double monto) {
        this.nombre = nombre;
        this.correo = correo;
        this.tipoPago = tipoPago;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.fechaCobro = fechaCobro;
        this.monto = monto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public String getFechaCobro() {
        return fechaCobro;
    }

    public double getMonto() {
        return monto;
    }
    
}
