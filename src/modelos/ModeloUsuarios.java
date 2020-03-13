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
import objetos.ObjetoUsuario;

/**
 *
 * @author KSGAMER
 */
public class ModeloUsuarios extends BD{
    private ModeloEstatusUsuarios meu = new ModeloEstatusUsuarios();
    private ResultSet rs;
    private PreparedStatement st;
    
    private ArrayList<ObjetoUsuario> listUsers = new ArrayList<>();
    
    protected DefaultTableModel cargarTabla() {
        meu.cargarTabla();
        String[] titulos = {"Usuario","Password", "Empleado", "Telefono", "Direccion", "Estatus"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[6];

        try {
            this.st = conectar().prepareStatement("SELECT * FROM Usuario");
            this.rs = st.executeQuery();

            while (this.rs.next()) {
                fila[0] = rs.getString("Username");
                fila[1] = rs.getString("Password");
                fila[2] = rs.getString("Empleado");
                fila[3] = rs.getString("Telefono");
                fila[4] = rs.getString("Direccion");
                for (int i = 0; i < meu.selectEstatusUsuarios().size(); i++) {
                    if(rs.getInt("IdEstatusUsuario") == meu.selectEstatusUsuarios().get(i).getIdEstatusUsuario()) {
                        fila[5] = meu.selectEstatusUsuarios().get(i).getNombre();
                    }
                }
                tb.addRow(fila);
                this.listUsers.add(new ObjetoUsuario(rs.getString("Username"), rs.getString("Password"),rs.getString("Empleado"), rs.getString("Telefono"), rs.getString("Direccion"), rs.getInt("IdEstatusUsuario")));
            }
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
    
    protected ArrayList<ObjetoUsuario> selectUsuarios() {
        return this.listUsers;
    }
    
    protected void insertUsuarios(String username, String password, String empleado, String telefono, String direccion, String estatusUsuario) {
        meu.cargarTabla();
        try {
            this.st = conectar().prepareStatement("INSERT INTO Usuario(Username, Password, Empleado, Telefono, Direccion, IdEstatusUsuario) VALUES (?,?,?,?,?,?)");
            this.st.setString(1, username);
            this.st.setString(2, password);
            this.st.setString(3, empleado);
            this.st.setString(4, telefono);
            this.st.setString(5, direccion);
            for (int i = 0; i < meu.selectEstatusUsuarios().size(); i++) {
                if(estatusUsuario.equals(meu.selectEstatusUsuarios().get(i).getNombre())) {
                    this.st.setInt(6, meu.selectEstatusUsuarios().get(i).getIdEstatusUsuario());
                }
            }
            this.st.execute();
            conectar().close();
               DesktopNotify.showDesktopMessage("Exito", "Datos del empleado agregados con éxito.", DesktopNotify.SUCCESS);

        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrió un error al intentar agregar los datos del nuevo empleado,"
                    + "por favor intente de nuevo o revise su conexión", DesktopNotify.ERROR);
        }
    }
    
    protected void updateUsuarios(String password, String empleado, String telefono, String direccion, String estatusUsuario, String username) {
        meu.cargarTabla();
        try {
            this.st = conectar().prepareStatement("UPDATE Usuario SET Password = ?, Empleado = ?, Telefono = ?, Direccion = ?, IdEstatusUsuario = ? WHERE Username = ?");
            this.st.setString(1, password);
            this.st.setString(2, empleado);
            this.st.setString(3, telefono);
            this.st.setString(4, direccion);
            for (int i = 0; i < meu.selectEstatusUsuarios().size(); i++) {
                if(estatusUsuario.equals(meu.selectEstatusUsuarios().get(i).getNombre())) {
                    this.st.setInt(5, meu.selectEstatusUsuarios().get(i).getIdEstatusUsuario());
                }
            }
            this.st.setString(6, username);
            this.st.executeUpdate();
            conectar().close();
            DesktopNotify.showDesktopMessage("Exito", "Datos del empleado actualizados con éxito.", DesktopNotify.SUCCESS);

        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrió un error al intentar actualizar los datos del empleado por favor intente de nuevo o revise su conexión", DesktopNotify.ERROR);
        }
    }
    
    protected void deleteUsuarios(String username) {
        meu.cargarTabla();
        try {
            this.st = conectar().prepareStatement("DELETE FROM Usuario WHERE Username = ?");
            this.st.setString(1, username);
            this.st.execute();
            conectar().close();
            DesktopNotify.showDesktopMessage("Exito", "Datos del empleado eliminados con éxito.", DesktopNotify.SUCCESS);

        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrió un error al intentar eliminar los datos del empleado por favor intente de nuevo o revise su conexión", DesktopNotify.ERROR);
        }
    }
}
