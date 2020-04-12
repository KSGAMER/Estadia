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
import objetos.ObjetoProducto;

/**
 *
 * @author KSGAMER
 */
public class ModeloProducto extends BD {

    private ResultSet rs;
    private PreparedStatement st;
    private ArrayList<ObjetoProducto> list = new ArrayList<>();

    protected DefaultTableModel cargarTabla() {
        String[] titulos = {"#", "Producto", "Precio", "Cantidad", "Observaciones"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[5];
        try {
            this.st = conectar().prepareStatement("SELECT * FROM Producto)");
            this.rs = this.st.executeQuery();
            while (this.rs.next()) {
                fila[0] = rs.getInt(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getDouble(3);
                fila[3] = rs.getInt(4);
                fila[4] = rs.getString(5);
                tb.addRow(fila);
                this.list.add(new ObjetoProducto(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }

    protected DefaultTableModel cargarTabla(String filtro) {
        String[] titulos = {"#", "Producto", "Precio", "Cantidad", "Observaciones"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[5];
        try {
            this.st = conectar().prepareStatement("SELECT * FROM Producto WHERE Nombre like CONCAT('%',?,'%')");
            this.st.setString(1, filtro);
            this.rs = this.st.executeQuery();
            while (this.rs.next()) {
                fila[0] = rs.getInt(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getDouble(3);
                fila[3] = rs.getInt(4);
                fila[4] = rs.getString(5);
                tb.addRow(fila);
                this.list.add(new ObjetoProducto(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
    
    protected ArrayList<ObjetoProducto> selectProducto() {
        return this.list;
    }

    protected void insertProducto(String producto, double precio, int cantidad, String observaciones) {
        try {
            this.st = conectar().prepareStatement("INSERT INTO Producto(Nombre, Precio, Cantidad, Observaciones) VALUES (?,?,?,?)");
            this.st.setString(1, producto);
            this.st.setDouble(2, precio);
            this.st.setInt(3, cantidad);
            this.st.setString(4, observaciones);
            this.st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void updateProducto(String producto, double precio, int cantidad, String observaciones, int id) {
        try {
            this.st = conectar().prepareStatement("UPDATE Producto SET Nombre = ?, Precio = ?, Cantidad = ?, Observaciones = ? WHERE IdProducto = ?");
            this.st.setString(1, producto);
            this.st.setDouble(2, precio);
            this.st.setInt(3, cantidad);
            this.st.setString(4, observaciones);
            this.st.setInt(5, id);
            this.st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void deleteProducto(int id) {
        try {
            this.st = conectar().prepareStatement("DELETE FROM Producto WHERE IdProducto = ?");
            this.st.setInt(1, id);
            this.st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
