/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloCajas;
import objetos.ObjetoCaja;

/**
 *
 * @author KSGAMER
 */
public class ControladorCaja extends ModeloCajas{
    public DefaultTableModel tablaCaja() {
        return cargarTabla();
    }
    
    public ArrayList<ObjetoCaja> seleccionarCaja() {
        return selectCaja();
    }
    
    public void insertarCaja(String fechaApertura, double montoApertura, String fechaCierre, double montoCierre, String usuario, String estadoCaja) {
        insertCaja(fechaApertura, montoApertura, fechaCierre, montoCierre, usuario, estadoCaja);
    }
    
    public void actualizarAperturaCaja(double montoApertura, String usuario, int id) {
        updateAperturaCaja(montoApertura, usuario, id);
    }
     public void actualizarCiereCaja(String fechaCierre, Double montoCierre,String estadoCaja, String Usuario) {
        updateCierreCaja(fechaCierre, montoCierre,estadoCaja,Usuario);
    }
    
    public void eliminarCaja(int id) {
        deleteCaja(id);
    }
}
