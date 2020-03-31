/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import ds.desktop.notify.DesktopNotify;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author KSGAMER
 */
public class ModeloGastos extends BD{
    private ResultSet rs;
    private PreparedStatement st;
    
    public DefaultTableModel cargarTabla() {
        String[] titulos = {"Gasto", "Cantidad", "Descripción"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[3];
        try {
            this.st = conectar().prepareStatement("SELECT Nombre, Cantidad, Descripcion FROM Gastos WHERE CONVERT(DATE, FechaActual, 103) = CONVERT(DATE, GETDATE(), 103) ORDER BY CONVERT(DATE, FechaActual, 103), Username DESC");
            this.rs = st.executeQuery();
            while (this.rs.next()) {
                fila[0] = rs.getString(1);
                fila[1] = rs.getDouble(2);
                fila[2] = rs.getString(3);
                tb.addRow(fila);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
    
    public DefaultTableModel cargarTabla(String usuario) {
        String[] titulos = {"Gasto", "Cantidad", "Descripción"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[3];
        try {
            this.st = conectar().prepareStatement("SELECT Nombre, Cantidad, Descripcion FROM Gastos WHERE Username like CONCAT('%',?,'%') and CONVERT(DATE, FechaActual, 103) = CONVERT(DATE, GETDATE(), 103) ORDER BY CONVERT(DATE, FechaActual, 103), Username DESC");
            this.st.setString(1, usuario);
            this.rs = st.executeQuery();
            while (this.rs.next()) {
                fila[0] = rs.getString(1);
                fila[1] = rs.getDouble(2);
                fila[2] = rs.getString(3);
                tb.addRow(fila);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
    
    public DefaultTableModel cargarGastos(String filtro) {
        String[] titulos = {"#", "Gasto", "Cantidad", "Descripción", "Usuario", "Fecha"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[6];
        try {
            this.st = conectar().prepareStatement("SELECT * FROM Gastos WHERE Username like CONCAT('%',?,'%') ORDER BY CONVERT(DATE, FechaActual, 103) DESC");
            this.st.setString(1, filtro);
            this.rs = st.executeQuery();
            while (this.rs.next()) {
                fila[0] = rs.getInt(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3] = rs.getString(4);
                fila[4] = rs.getString(5);
                fila[5] = rs.getString(6);
                tb.addRow(fila);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
    
    public void insertGastos(String gasto, double cantidad, String descripcion, String usuario, String fecha) {
        try {
            this.st = conectar().prepareStatement("INSERT Gastos(Nombre, Cantidad, Descripcion, Username, FechaActual) VALUES (?,?,?,?,?)");
            this.st.setString(1, gasto);
            this.st.setDouble(2, cantidad);
            this.st.setString(3, descripcion);
            this.st.setString(4, usuario);
            this.st.setString(5, fecha);
            this.st.execute();
                  DesktopNotify.showDesktopMessage("Exito", "Gasto Creado con exito", DesktopNotify.SUCCESS);

        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Error al generar el gasto, intente de nuevo", DesktopNotify.ERROR);
        }
    }
    
    public void deleteGastos(int id) {
        try {
            this.st = conectar().prepareStatement("DELETE FROM Gastos WHERE IdGastos = ?");
            this.st.setInt(1, id);
            this.st.execute();
                DesktopNotify.showDesktopMessage("Exito", "Gasto eliminado con exito", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
             DesktopNotify.showDesktopMessage("Error", "Error al eliminar el gasto, intente de nuevo", DesktopNotify.ERROR);
        }
    }
}
