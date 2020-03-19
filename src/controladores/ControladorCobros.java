/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloCobros;
import objetos.ObjetoCobro;

/**
 *
 * @author KSGAMER
 */
public class ControladorCobros extends ModeloCobros{
    
    public DefaultTableModel tablaCobros() {
        return cargarTabla();
    }
    
    public DefaultTableModel tablaCobros(String buscar) {
        return cargarTabla(buscar);
    }
    
    public ArrayList<ObjetoCobro> selectCobro() {
        return selectCobros();
    }
    
    public void insertCobro(int idReservacion, double monto, String tipoPago, String rfc, String correo, String facturacion, String usuario) {
        insertCobros(idReservacion, monto, tipoPago, rfc, correo, facturacion, usuario);
    }
    
    public void updateCobro(double monto, String tipoPago, String rfc, String correo, String facturacion, int id) {
        updateCobros(monto, tipoPago, rfc, correo, facturacion, id);
    }
    
    public void deleteCobro(int id) {
        deleteCobros(id);
    }
}
