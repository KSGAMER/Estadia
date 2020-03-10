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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import objetos.ObjetoCliente;

/**
 *
 * @author KSGAMER
 */
public class ModeloClientes extends BD {

    private ModeloCFDI cf = new ModeloCFDI();
    private ResultSet rs;
    private PreparedStatement st;

    private ArrayList<ObjetoCliente> listClient = new ArrayList<>();

    protected DefaultTableModel cargarTabla(String buscar) {
        cf.cargarTabla();
        String[] titulos = {"#", "Nombre", "RFC", "Dirección", "Telefono", "Email", "CFDI"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[7];

        try {
            this.st = conectar().prepareStatement("SELECT * FROM Cliente WHERE Nombre like CONCAT('%', ? ,'%') or RFC like CONCAT('%', ? ,'%') or Direccion like CONCAT('%', ? ,'%') or telefono like CONCAT('%', ? ,'%') or Email like CONCAT('%', ? ,'%')");
            this.st.setString(1, buscar);
            this.st.setString(2, buscar);
            this.st.setString(3, buscar);
            this.st.setString(4, buscar);
            this.st.setString(5, buscar);
            this.rs = st.executeQuery();

            while (this.rs.next()) {
                fila[0] = rs.getInt("IdCliente");
                fila[1] = rs.getString("Nombre");
                fila[2] = rs.getString("RFC");
                fila[3] = rs.getString("Direccion");
                fila[4] = rs.getString("Telefono");
                fila[5] = rs.getString("Email");
                for (int i = 0; i < cf.selectCFDIs().size(); i++) {
                    if (rs.getInt("IdCFDI") == cf.selectCFDIs().get(i).getIdCFDI()) {
                        fila[6] = cf.selectCFDIs().get(i).getNombre();
                    }
                }
                tb.addRow(fila);
                this.listClient.add(new ObjetoCliente(rs.getInt("IdCliente"), rs.getString("Nombre"), rs.getString("RFC"), rs.getString("Direccion"), rs.getString("Telefono"), rs.getString("Email"), rs.getInt("IdCFDI")));
            }
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
    
    protected DefaultTableModel cargarTabla() {
        cf.cargarTabla();
        String[] titulos = {"#", "Nombre", "RFC", "Dirección", "Telefono", "Email", "CFDI"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[7];

        try {
            this.st = conectar().prepareStatement("SELECT * FROM Cliente");
            this.rs = st.executeQuery();

            while (this.rs.next()) {
                fila[0] = rs.getInt("IdCliente");
                fila[1] = rs.getString("Nombre");
                fila[2] = rs.getString("RFC");
                fila[3] = rs.getString("Direccion");
                fila[4] = rs.getString("Telefono");
                fila[5] = rs.getString("Email");
                for (int i = 0; i < cf.selectCFDIs().size(); i++) {
                    if (rs.getInt("IdCFDI") == cf.selectCFDIs().get(i).getIdCFDI()) {
                        fila[6] = cf.selectCFDIs().get(i).getNombre();
                    }
                }
                tb.addRow(fila);
                this.listClient.add(new ObjetoCliente(rs.getInt("IdCliente"), rs.getString("Nombre"), rs.getString("RFC"), rs.getString("Direccion"), rs.getString("Telefono"), rs.getString("Email"), rs.getInt("IdCFDI")));
            }
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }

    protected ArrayList<ObjetoCliente> selectClientes() {
        return this.listClient;
    }

    protected void insertClientes(String nombre, String rfc, String direccion, String telefono, String email, String CFDI) {
        cf.cargarTabla();
        try {
            this.st = conectar().prepareStatement("INSERT INTO Cliente (Nombre, RFC, Direccion, Telefono, Email, IdCFDI) VALUES (?,?,?,?,?,?)");
            this.st.setString(1, nombre);
            this.st.setString(2, rfc);
            this.st.setString(3, direccion);
            this.st.setString(4, telefono);
            this.st.setString(5, email);
            for (int i = 0; i < cf.selectCFDIs().size(); i++) {
                if (CFDI.equals(cf.selectCFDIs().get(i).getNombre())) {
                    this.st.setInt(6, cf.selectCFDIs().get(i).getIdCFDI());
                }
            }
            this.st.execute();
            conectar().close();
            DesktopNotify.showDesktopMessage("Exito", "Los datos del cliente han sido insertados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar insertar los datos del cliente, intentelo nuevamente y asegurese de que todos los campos obligatorios este llenos", DesktopNotify.ERROR);
        }
    }

    protected void updateClientes(String nombre, String rfc, String direccion, String telefono, String email, String CFDI, int id) {
        cf.cargarTabla();
        try {
            this.st = conectar().prepareStatement("UPDATE Cliente SET Nombre = ?, RFC = ?, Direccion = ?, Telefono = ?, Email = ?, IdCFDI = ? WHERE IdCliente = ?");
            this.st.setString(1, nombre);
            this.st.setString(2, rfc);
            this.st.setString(3, direccion);
            this.st.setString(4, telefono);
            this.st.setString(5, email);
            for (int i = 0; i < cf.selectCFDIs().size(); i++) {
                if (CFDI.equals(cf.selectCFDIs().get(i).getNombre())) {
                    this.st.setInt(6, cf.selectCFDIs().get(i).getIdCFDI());
                }
            }
            this.st.setInt(7, id);
            this.st.executeUpdate();
            conectar().close();
            DesktopNotify.showDesktopMessage("Exito", "Los datos del cliente han sido actualizados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar actualizar los datos del cliente, intentelo nuevamente y asegurese de que todos los campos obligatorios este llenos", DesktopNotify.ERROR);
        }
    }

    protected void deleteClientes(int id) {
        try {
            this.st = conectar().prepareStatement("DELETE FROM Cliente WHERE IdCliente = ?");
            this.st.setInt(1, id);
            this.st.execute();
            conectar().close();
            DesktopNotify.showDesktopMessage("Exito", "Los datos del cliente han sido eliminados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar eliminar los datos del cliente, un cliente previamente utilizada no puede ser eliminada", DesktopNotify.ERROR);
        }
    }
}
