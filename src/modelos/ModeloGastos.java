/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

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
        String[] titulos = {"Gasto", "Cantiadad", "Descripción"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[3];
        try {
            this.st = conectar().prepareStatement("SELECT Nombre, Cantidad, Descripcion FROM Gastos");
            this.rs = st.executeQuery();
            while (this.rs.next()) {
                fila[0] = rs.getInt(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                tb.addRow(fila);
            }
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
    
    public DefaultTableModel cargarGastos() {
        String[] titulos = {"#", "Gasto", "Cantiadad", "Descripción", "Usuario", "Fecha"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[6];
        try {
            this.st = conectar().prepareStatement("SELECT * FROM Gastos");
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
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
}
