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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import objetos.ObjetoPiso;

/**
 *
 * @author KSGAMER
 */
public class ModeloPisos extends BD{
    
    private ResultSet rs;
    private PreparedStatement st;
    
    private ArrayList<ObjetoPiso> listBackground = new ArrayList<>();
    
    protected DefaultTableModel cargarTabla(String buscar) {
        String[] titulos = {"#", "Nombre", "Observaciones"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[3];

        try {
            this.st = conectar().prepareStatement("SELECT * FROM Piso WHERE Nombre like CONCAT('%', ? ,'%') or Observaciones like CONCAT('%', ? ,'%')");
            this.st.setString(1, buscar);
            this.st.setString(2, buscar);
            this.rs = st.executeQuery();

            while (this.rs.next()) {
                fila[0] = rs.getInt("IdPiso");
                fila[1] = rs.getString("Nombre");
                fila[2] = rs.getString("Observaciones");
                tb.addRow(fila);
                this.listBackground.add(new ObjetoPiso(rs.getInt("IdPiso"), rs.getString("Nombre"), rs.getString("Observaciones")));
            }
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
    
    protected DefaultTableModel cargarTabla() {
        String[] titulos = {"#", "Nombre", "Observaciones"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[3];

        try {
            this.st = conectar().prepareStatement("SELECT * FROM Piso");
            this.rs = st.executeQuery();

            while (this.rs.next()) {
                fila[0] = rs.getInt("IdPiso");
                fila[1] = rs.getString("Nombre");
                fila[2] = rs.getString("Observaciones");
                tb.addRow(fila);
                this.listBackground.add(new ObjetoPiso(rs.getInt("IdPiso"), rs.getString("Nombre"), rs.getString("Observaciones")));
            }
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
    
    protected ArrayList<ObjetoPiso> selectPisos() {
        return this.listBackground;
    }
    
    protected void insertPisos(String nombre, String observaciones) {
        try {
            this.st = conectar().prepareStatement("INSERT INTO Piso(Nombre, Observaciones) VALUES (?,?)");
            this.st.setString(1, nombre);
            this.st.setString(2, observaciones);
            this.st.execute();
            conectar().close();
            DesktopNotify.showDesktopMessage("Exito", "Los datos del piso han sido insertados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar insertar los datos del piso, intentelo nuevamente y asegurese de que todos los campos obligatorios este llenos", DesktopNotify.ERROR);
        }
    }
    
    protected void updatePisos(String nombre, String observaciones, int id) {
        try {
            this.st = conectar().prepareStatement("UPDATE Piso SET Nombre = ?, Observaciones = ? WHERE IdPiso = ?");
            this.st.setString(1, nombre);
            this.st.setString(2, observaciones);
            this.st.setInt(3, id);
            this.st.executeUpdate();
            conectar().close();
            DesktopNotify.showDesktopMessage("Exito", "Los datos del piso han sido actualizados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar actualizar los datos del piso, intentelo nuevamente y asegurese de que todos los campos obligatorios este llenos", DesktopNotify.ERROR);
        }
    }
    
    protected void deletePisos(int id) {
        try {
            this.st = conectar().prepareStatement("DELETE FROM Piso WHERE IdPiso = ?");
            this.st.setInt(1, id);
            this.st.execute();
            conectar().close();
            DesktopNotify.showDesktopMessage("Exito", "Los datos del piso han sido eliminados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar eliminar los datos del piso, un piso previamente utilizado no puede ser eliminado", DesktopNotify.ERROR);
        }
    }
}
