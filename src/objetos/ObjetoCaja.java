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
public class ObjetoCaja {
    private int idCaja;
    private String fechaApertura;
    private double montoApertura;
    private String fechaCierre;
    private double montoCierre;
    private String usuario;
    private int idEstadoCaja;

    public ObjetoCaja(int idCaja, String fechaApertura, double montoApertura, String fechaCierre, double montoCierre, String usuario, int idEstadoCaja) {
        this.idCaja = idCaja;
        this.fechaApertura = fechaApertura;
        this.montoApertura = montoApertura;
        this.fechaCierre = fechaCierre;
        this.montoCierre = montoCierre;
        this.usuario = usuario;
        this.idEstadoCaja = idEstadoCaja;
    }

    public int getIdCaja() {
        return idCaja;
    }

    public String getFechaApertura() {
        return fechaApertura;
    }

    public double getMontoApertura() {
        return montoApertura;
    }

    public String getFechaCierre() {
        return fechaCierre;
    }

    public double getMontoCierre() {
        return montoCierre;
    }

    public String getUsuario() {
        return usuario;
    }

    public int getIdEstadoCaja() {
        return idEstadoCaja;
    }
        
}
