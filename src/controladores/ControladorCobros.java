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
//Se aplica Herencia desde la clase padre Modelo Cobros
public class ControladorCobros extends ModeloCobros {

    //Método que retorna una tabla
    public DefaultTableModel tablaCobros() {
        return cargarTabla();
    }
    
    public DefaultTableModel tablaCobros(String usuario) {
        return cargarTabla(usuario);
    }

    /*  //Método que retorna una tabla pasando un texto para poder filtrar los resultados
    public DefaultTableModel tablaCobros(String buscar) {
        return cargarTabla(buscar);
    }*/
    //Método que retorna un arreglo de tipo Objeto Cobro
    public ArrayList<ObjetoCobro> selectCobro() {
        return selectCobros();
    }

    //Método que pasa los parametros id de la reservación, monto, tipo de pago, rfc, correo, si requiere facturación y el usuario para agregar un nuevo cobro
    public void insertCobro(double monto, String tipoPago, String rfc, String correo, String usuario, String Nombre, String NombreHabitacion, String FechaIngreso, String FechaSalida, String IdFacturacion) {
        insertCobros(monto, tipoPago, rfc, correo, usuario, Nombre, NombreHabitacion, FechaIngreso, FechaSalida, IdFacturacion);
    }

    /* //Método que que pasa los parametros monto, el tipo de pago, el rfc, el correo, si requiere facturacion, y el id para actualizar los datos del cobro
    public void updateCobro(double monto, String tipoPago, String rfc, String correo, int IdFacturacion, String usuario, String Nombre, int IdHabitacion, String FechaIngreso, String FechaSalida, int id) {
        updateCobros( monto,  tipoPago,  rfc,  correo,  IdFacturacion,  usuario,  Nombre,  IdHabitacion,  FechaIngreso,  FechaSalida, id);
    }
     */
    //Método que pasa los parametros id para eliminar los datos de un cobro
    /*  public void deleteCobro(int id) {
        deleteCobros(id);
    }*/
}
