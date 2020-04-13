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
        String[] titulos = {"#", "Producto", "Proveedor", "Precio", "Cantidad", "Observaciones"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[6];
        try {
            this.st = conectar().prepareStatement("SELECT * FROM Producto)");
            this.rs = this.st.executeQuery();
            while (this.rs.next()) {
                fila[0] = rs.getInt(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3] = rs.getDouble(4);
                fila[4] = rs.getInt(5);
                fila[5] = rs.getString(6);
                tb.addRow(fila);
                this.list.add(new ObjetoProducto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getString(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }

    protected DefaultTableModel cargarTabla(String filtro) {
        String[] titulos = {"#", "Producto", "Proveedor", "Precio", "Cantidad", "Observaciones"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[6];
        try {
            this.st = conectar().prepareStatement("SELECT * FROM Producto WHERE Nombre like CONCAT('%',?,'%')");
            this.st.setString(1, filtro);
            this.rs = this.st.executeQuery();
            while (this.rs.next()) {
                fila[0] = rs.getInt(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3] = rs.getDouble(4);
                fila[4] = rs.getInt(5);
                fila[5] = rs.getString(6);
                tb.addRow(fila);
                this.list.add(new ObjetoProducto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getString(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
    
    protected DefaultTableModel productosCantidad(String filtro) {
        String[] titulos = {"Producto", "Cantidad"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[2];
        try {
            this.st = conectar().prepareStatement("SELECT p.Nombre, p.Cantidad FROM Producto p WHERE Nombre like CONCAT('%',?,'%')");
            this.st.setString(1, filtro);
            this.rs = this.st.executeQuery();
            while (this.rs.next()) {
                fila[0] = rs.getString(1);
                fila[1] = rs.getInt(2);
                tb.addRow(fila);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
    
    protected DefaultTableModel productosCantidad() {
        String[] titulos = {"Producto", "Cantidad"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[2];
        try {
            this.st = conectar().prepareStatement("SELECT p.Nombre, p.Cantidad FROM Producto p");
            this.rs = this.st.executeQuery();
            while (this.rs.next()) {
                fila[0] = rs.getInt(1);
                fila[1] = rs.getString(2);
                tb.addRow(fila);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
    
    protected ArrayList<ObjetoProducto> selectProducto() {
        return this.list;
    }

    protected void insertProducto(String producto, String proveedor, double precio, int cantidad, String observaciones) {
        try {
            this.st = conectar().prepareStatement("INSERT INTO Producto(Nombre, Proveedor, Precio, Cantidad, Observaciones) VALUES (?,?,?,?,?)");
            this.st.setString(1, producto);
            this.st.setString(2, proveedor);
            this.st.setDouble(3, precio);
            this.st.setInt(4, cantidad);
            this.st.setString(5, observaciones);
            this.st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void updateProducto(String producto, String proveedor, double precio, int cantidad, String observaciones, int id) {
        try {
            this.st = conectar().prepareStatement("UPDATE Producto SET Nombre = ?, Proveedor = ?, Precio = ?, Cantidad = ?, Observaciones = ? WHERE IdProducto = ?");
            this.st.setString(1, producto);
            this.st.setString(2, proveedor);
            this.st.setDouble(3, precio);
            this.st.setInt(4, cantidad);
            this.st.setString(5, observaciones);
            this.st.setInt(6, id);
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
