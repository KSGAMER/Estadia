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
import objetos.ObjetoInventario;

/**
 *
 * @author KSGAMER
 */
public class ModeloInventario extends BD{
    private ResultSet rs;
    private PreparedStatement st;
    
    private ArrayList<ObjetoInventario> list = new ArrayList<>();
    
    protected DefaultTableModel cargarTabla(String filtro) {
        //Se declaran los titulos de la tabla
        String[] titulos = {"#", "Producto", "Cantidad", "Observaciones"};
        //Se declara la tabla pasando las columnas de la tabla
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Se declara un objeto que actuara como la fila de la tabla
        Object[] fila = new Object[4];

        try {
            //Se instancia la conexión a la base de datos pasando la consulta preparada
            this.st = conectar().prepareStatement("SELECT * FROM Inventario WHERE Nombre like CONCAT('%',?,'%')");
            this.st.setString(1, filtro);
            //Se ejecuta el Query
            this.rs = st.executeQuery();
            //Se iteran los resultados de la consulta
            while (this.rs.next()) {
                //Se agregan los resultados al objeto
                fila[0] = rs.getInt(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getInt(3);
                fila[3] = rs.getString(4);
                //Se agrega el objeto a la tabla
                tb.addRow(fila);
                //Se agregan los resultados al arreglo atraves de un nuevo objeto
                this.list.add(new ObjetoInventario(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4)));
            }
            //Se cierra la conexión
        } catch (SQLException ex) {
            Logger.getLogger(ModeloInventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retona la tabla
        return tb;
    }
    
    protected ArrayList<ObjetoInventario> selectInventario() {
        return list;
    }
    
    protected void insertInventario(String producto, int cantidad, String observaciones) {
        try {
            this.st = conectar().prepareStatement("INSERT INTO Inventario(Nombre, Stock, Observaciones) VALUES (?,?,?)");
            this.st.setString(1, producto);
            this.st.setInt(2, cantidad);
            this.st.setString(3, observaciones);
            this.st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloInventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void updateInventario(String producto, int cantidad, String observaciones, int id) {
        try {
            this.st = conectar().prepareStatement("UPDATE Inventario SET Nombre = ?, Stock = ?, Observaciones = ? WHERE IdInventario = ?");
            this.st.setString(1, producto);
            this.st.setInt(2, cantidad);
            this.st.setString(3, observaciones);
            this.st.setInt(4, id);
            this.st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloInventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void deleteInventario(int id) {
        try {
            this.st = conectar().prepareStatement("DELETE FROM Inventario WHERE IdInventario = ?");
            this.st.setInt(1, id);
            this.st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloInventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
