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
import objetos.ObjetoHabitacion;
import objetos.ObjetoInventarioHabitacion;
import objetos.ObjetoProducto;

/**
 *
 * @author KSGAMER
 */
public class ModeloInventarioHabitacion extends BD{
    private ResultSet rs;
    private PreparedStatement st;
    private ArrayList<ObjetoInventarioHabitacion> list = new ArrayList<>();
    private ModeloProducto productos = new ModeloProducto();
    private ModeloHabitaciones habitaciones = new ModeloHabitaciones();
    
    protected DefaultTableModel cargarTabla(String filtro) {
        String[] titulos = {"#", "Producto", "Total", "Habitacion", "Usuario"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[5];
        try {
            this.st = conectar().prepareStatement("SELECT ih.IdInventarioHabitacion, p.Nombre as Producto, ih.Total, h.Nombre as Habitacion, ih.Username FROM InventarioHabitacion ih INNER JOIN Producto p on p.IdProducto = ih.IdProducto INNER JOIN Habitacion h on h.IdHabitacion = ih.IdHabitacion WHERE h.Nombre like CONCAT('%',?,'%') and p.Nombre like CONCAT('%',?,'%') and ih.Username like CONCAT('%',?,'%')");
            this.st.setString(1, filtro);
            this.st.setString(2, filtro);
            this.st.setString(3, filtro);
            this.rs = this.st.executeQuery();
            while(this.rs.next()) {
                fila[0] = rs.getInt(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getInt(3);
                fila[3] = rs.getString(4);
                fila[4] = rs.getString(5);
                tb.addRow(fila);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloInventarioHabitacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
    
    protected DefaultTableModel cargarTabla() {
        String[] titulos = {"#", "Producto", "Total", "Habitacion", "Usuario"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[5];
        try {
            this.st = conectar().prepareStatement("SELECT ih.IdInventarioHabitacion, p.Nombre as Producto, ih.Total, h.Nombre as Habitacion, ih.Username FROM InventarioHabitacion ih INNER JOIN Producto p on p.IdProducto = ih.IdProducto INNER JOIN Habitacion h on h.IdHabitacion = ih.IdHabitacion");
            this.rs = this.st.executeQuery();
            while(this.rs.next()) {
                fila[0] = rs.getInt(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getInt(3);
                fila[3] = rs.getString(4);
                fila[4] = rs.getString(5);
                tb.addRow(fila);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloInventarioHabitacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
    
    protected void insertInventarioHabitacion(String producto, int total, String habitacion, String usuario) {
        productos.cargarTabla();
        habitaciones.cargarTabla();
        try {
            this.st = conectar().prepareStatement("INSERT INTO InventarioHabitacion(IdProducto, Total, IdHabitacion, Username) VALUES (?,?,?,?)");
            for (ObjetoProducto objetoProducto : productos.selectProducto()) {
                if(objetoProducto.getNombre().equals(producto)) {
                    this.st.setInt(1, objetoProducto.getIdProducto());
                }
            }
            this.st.setInt(2, total);
            for (ObjetoHabitacion objetoHabitaciones : habitaciones.selectHabitaciones()) {
                if(objetoHabitaciones.getNombre().equals(habitacion)) {
                    this.st.setInt(3, objetoHabitaciones.getIdHabitacion());
                }
            }
            this.st.setString(4, usuario);
            this.st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloInventarioHabitacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void updateInventarioHabitacion(String producto, int total, String habitacion, String usuario, int id) {
        productos.cargarTabla();
        habitaciones.cargarTabla();
        try {
            this.st = conectar().prepareStatement("UPDATE InventarioHabitacion SET IdProducto = ?, Total = ?, IdHabitacion = ?, Username = ? WHERE IdInventarioHabitacion = ?");
            for (ObjetoProducto objetoProducto : productos.selectProducto()) {
                if(objetoProducto.getNombre().equals(producto)) {
                    this.st.setInt(1, objetoProducto.getIdProducto());
                }
            }
            this.st.setInt(2, total);
            for (ObjetoHabitacion objetoHabitaciones : habitaciones.selectHabitaciones()) {
                if(objetoHabitaciones.getNombre().equals(habitacion)) {
                    this.st.setInt(3, objetoHabitaciones.getIdHabitacion());
                }
            }
            this.st.setString(4, usuario);
            this.st.setInt(5, id);
            this.st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloInventarioHabitacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void deleteInventarioHabitacion(int id) {
        try {
            this.st = conectar().prepareStatement("DELETE FROM InventarioHabitacion WHERE IdInventarioHabitacion = ?");
            this.st.setInt(1, id);
            this.st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloInventarioHabitacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
