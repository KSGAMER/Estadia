/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import objetos.objetoEnvioFacturacion;

/**
 *
 * @author KSGAMER
 */
public class ModeloEnvioFacturacion extends BD{
    private ResultSet rs;
    private PreparedStatement st;
    private ArrayList<objetoEnvioFacturacion> list = new ArrayList<>();
    
    protected DefaultTableModel cargarTabla(){
        String [] titulos = {"Razon Social", "Email","Tipo Pago", "Fecha Ingreso", "Fecha Salida", "Fecha Cobro", "Monto"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[7];
        try {
            this.st = conectar().prepareStatement("SELECT r.Nombre ,c.Correo, tp.Nombre as TipoPago, r.FechaIngreso, r.FechaSalida, c.FechaCobro, c.Monto FROM Cobro c INNER JOIN Reservacion r on r.IdReservacion = c.IdReservacion INNER JOIN TipoPago tp on tp.IdTipoPago = c.IdTipoPago WHERE c.IdFacturacion = 1");
            this.rs = st.executeQuery();
            while(rs.next()) {
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3] = rs.getString(4);
                fila[4] = rs.getString(5);
                fila[5] = rs.getString(6);
                fila[6] = rs.getDouble(7);
                tb.addRow(fila);
                list.add(new objetoEnvioFacturacion(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7)));
            }
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
       protected DefaultTableModel cargarTabla(String fechaCobro) {
        String[] titulos = {"Razon Social", "Email", "Tipo Pago", "Fecha Ingreso", "Fecha Salida", "Fecha Cobro", "Monto"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
      Object[] fila = new Object[7];
        try {
            this.st = conectar().prepareStatement("SELECT r.Nombre ,c.Correo, tp.Nombre as TipoPago, r.FechaIngreso, r.FechaSalida, c.FechaCobro, c.Monto FROM Cobro c INNER JOIN Reservacion r on r.IdReservacion = c.IdReservacion INNER JOIN TipoPago tp on tp.IdTipoPago = c.IdTipoPago WHERE c.IdFacturacion = 1 and CONVERT(DATE, c.FechaCobro, 103) >= CONVERT(DATE, ?, 103)");
            this.st.setString(1, fechaCobro);
            this.rs = st.executeQuery();
            while(rs.next()) {
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3] = rs.getString(4);
                fila[4] = rs.getString(5);
                fila[5] = rs.getString(6);
                fila[6] = rs.getDouble(7);
                tb.addRow(fila);
                list.add(new objetoEnvioFacturacion(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7)));
            }
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
              
        return tb;
    }
    protected ArrayList<objetoEnvioFacturacion> selectEnvioFacturaciones() {
        return list;
    }
}
