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
import javax.xml.bind.annotation.XmlElement;
import objetos.ObjetoCategoria;

/**
 *
 * @author KSGAMER
 */
public class ModeloCategorias extends BD {

    private ResultSet rs;
    private PreparedStatement st;

    private ArrayList<ObjetoCategoria> listCategory = new ArrayList<>();

    protected DefaultTableModel cargarTabla(String buscar, boolean bool) {
        String[] titulos = {"#", "Nombre", "Observaciones"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[3];

        try {
            this.st = conectar().prepareStatement("SELECT * FROM Categoria WHERE Nombre like CONCAT('%', ? ,'%') or Observaciones like CONCAT('%', ? ,'%')");
            this.st.setString(1, buscar);
            this.st.setString(2, buscar);
            this.rs = st.executeQuery();
            while (this.rs.next()) {
                if (bool == false) {
                    if (rs.getString("Mostrar").equals("1")) {
                        fila[0] = rs.getInt("IdCategoria");
                        fila[1] = rs.getString("Nombre");
                        fila[2] = rs.getString("Observaciones");
                        tb.addRow(fila);
                        this.listCategory.add(new ObjetoCategoria(rs.getInt("IdCategoria"), rs.getString("Nombre"), rs.getString("Observaciones"), rs.getString("Mostrar")));
                    }
                } else {
                    fila[0] = rs.getInt("IdCategoria");
                    fila[1] = rs.getString("Nombre");
                    fila[2] = rs.getString("Observaciones");
                    tb.addRow(fila);
                    this.listCategory.add(new ObjetoCategoria(rs.getInt("IdCategoria"), rs.getString("Nombre"), rs.getString("Observaciones"), rs.getString("Mostrar")));
                }
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
            this.st = conectar().prepareStatement("SELECT * FROM Categoria");
            this.rs = st.executeQuery();
            while (this.rs.next()) {
                if (rs.getString("Mostrar").equals("1")) {
                    fila[0] = rs.getInt("IdCategoria");
                    fila[1] = rs.getString("Nombre");
                    fila[2] = rs.getString("Observaciones");
                    tb.addRow(fila);
                    this.listCategory.add(new ObjetoCategoria(rs.getInt("IdCategoria"), rs.getString("Nombre"), rs.getString("Observaciones"), rs.getString("Mostrar")));
                }
            }
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }

    protected ArrayList<ObjetoCategoria> selectCategorias() {
        return this.listCategory;
    }

    protected void insertarCategorias(String nombre, String observaciones) {
        try {
            this.st = conectar().prepareStatement("INSERT INTO Categoria(Nombre, Observaciones, Mostrar) VALUES(?,?, 1)");
            this.st.setString(1, nombre);
            this.st.setString(2, observaciones);
            this.st.execute();
            conectar().close();
            DesktopNotify.showDesktopMessage("Exito", "Los datos de la categoria han sido insertados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar insertar los datos de la categoria, intentelo nuevamente y asegurese de que todos los campos obligatorios este llenos", DesktopNotify.ERROR);
        }
    }

    protected void updateCategorias(String nombre, String observaciones, int id) {
        try {
            this.st = conectar().prepareStatement("UPDATE Categoria SET Nombre = ?, Observaciones = ? WHERE IdCategoria = ?");
            this.st.setString(1, nombre);
            this.st.setString(2, observaciones);
            this.st.setInt(3, id);
            this.st.executeUpdate();
            conectar().close();
            DesktopNotify.showDesktopMessage("Exito", "Los datos de la categoria han sido actualizados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar actualizar los datos de la categoria, intentelo nuevamente y asegurese de que todos los campos obligatorios este llenos", DesktopNotify.ERROR);
        }
    }

    protected void deleteCategorias(int id) {
        try {
            this.st = conectar().prepareStatement("UPDATE Categoria SET Mostrar = 0 WHERE IdCategoria = ?");
            this.st.setInt(1, id);
            this.st.executeUpdate();
            conectar().close();
            DesktopNotify.showDesktopMessage("Exito", "Los datos de la categoria han sido eliminados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar eliminar los datos de la categoria, una categoria previamente utilizada no puede ser eliminada", DesktopNotify.ERROR);
        }
    }
}
