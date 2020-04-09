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
import objetos.ObjetoCargo;

/**
 *
 * @author KSGAMER
 */
public class ModeloCargos extends BD{
    private ResultSet rs;
    private PreparedStatement st;
    private ArrayList<ObjetoCargo> list = new ArrayList<>();
    
    protected DefaultTableModel cargarTabla() {
        String[] titulo = {"#", "Cargo", "Observaciones"};
        
        DefaultTableModel tb = new DefaultTableModel(null, titulo);
        Object[] fila = new Object[3];
        
        try {
            this.st = conectar().prepareStatement("SELECT * FROM Cargo");
            this.rs = st.executeQuery();
            while(this.rs.next()) {
                fila[0] = rs.getInt(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                tb.addRow(fila);
                this.list.add(new ObjetoCargo(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCargos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tb;
    }
    
    protected ArrayList<ObjetoCargo> selectCargos() {
        return this.list;
    }
    
    protected void insertCargos(String nombre, String observaciones) {
        try {
            this.st = conectar().prepareStatement("INSERT INTO Cargo(Nombre, Observaciones) VALUES (?,?)");
            this.st.setString(1, nombre);
            this.st.setString(2, observaciones);
            this.st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCargos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void updateCargos(String nombre, String observaciones, int id) {
        try {
            this.st = conectar().prepareStatement("UPDATE Cargo SET Nombre = ?, Observaciones = ? WHERE IdCargo = ?");
            this.st.setString(1, nombre);
            this.st.setString(2, observaciones);
            this.st.setInt(3, id);
            this.st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCargos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void deleteCargos(int id) {
        try {
            this.st = conectar().prepareStatement("DELETE FROM Cargo WHERE IdCargo = ?");
            this.st.setInt(1, id);
            this.st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCargos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
