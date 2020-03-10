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
import objetos.ObjetoPermiso;

/**
 *
 * @author KSGAMER
 */
public class ModeloPermisos extends BD{
    
    private ModeloEstatusPermisos mep = new ModeloEstatusPermisos();
    private ModeloModulos mm = new ModeloModulos();
    
    private ResultSet rs;
    private PreparedStatement st;
    
    private ArrayList<ObjetoPermiso> listAccess = new ArrayList<>();
    
    protected DefaultTableModel cargarTabla() {
        mep.cargarTabla();
        mm.cargarTabla();
        String[] titulos = {"#", "Usuario", "Modulo", "Consultar", "Insertar", "Actualizar", "Eliminar"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[7];

        try {
            this.st = conectar().prepareStatement("SELECT * FROM Permiso");
            this.rs = st.executeQuery();

            while (this.rs.next()) {
                fila[0] = rs.getInt("IdPermiso");
                fila[1] = rs.getString("Username");
                for (int i = 0; i < mm.selectModulos().size(); i++) {
                    if(rs.getInt("IdModulo") == mm.selectModulos().get(i).getIdModulo()) {
                        fila[2] = mm.selectModulos().get(i).getNombre();
                    }
                }
                for (int i = 0; i < mep.selectEstatusPermisos().size(); i++) {
                    if(rs.getInt("Consultar") == mep.selectEstatusPermisos().get(i).getIdEstatusPermiso()) {
                        fila[3] = mep.selectEstatusPermisos().get(i).getNombre();
                    }
                    if(rs.getInt("Insertar") == mep.selectEstatusPermisos().get(i).getIdEstatusPermiso()) {
                        fila[4] = mep.selectEstatusPermisos().get(i).getNombre();
                    }
                    if(rs.getInt("Actualizar") == mep.selectEstatusPermisos().get(i).getIdEstatusPermiso()) {
                        fila[5] = mep.selectEstatusPermisos().get(i).getNombre();
                    }
                    if(rs.getInt("Eliminar") == mep.selectEstatusPermisos().get(i).getIdEstatusPermiso()) {
                        fila[6] = mep.selectEstatusPermisos().get(i).getNombre();
                    }
                }
                tb.addRow(fila);
                this.listAccess.add(new ObjetoPermiso(rs.getInt("IdPermiso"), rs.getString("Username"), rs.getInt("IdModulo"), rs.getInt("Consultar"), rs.getInt("Insertar"), rs.getInt("Actualizar"), rs.getInt("Eliminar")));
            }
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
    
    protected ArrayList<ObjetoPermiso> selectPermisos() {
        return this.listAccess;
    }
    
    protected void insertPermisos(String username, String modulo, String consultar, String insertar, String actualizar, String eliminar) {
        mep.cargarTabla();
        mm.cargarTabla();
        try {
            this.st = conectar().prepareStatement("INSERT INTO Permiso(Username, IdModulo, Consultar, Insertar, Actualizar, Eliminar) VALUES (?,?,?,?,?,?)");
            this.st.setString(1, username);
            for (int i = 0; i < mm.selectModulos().size(); i++) {
                if(modulo.equals(mm.selectModulos().get(i).getNombre())) {
                    this.st.setInt(2, mm.selectModulos().get(i).getIdModulo());
                }
            }
            for (int i = 0; i < mep.selectEstatusPermisos().size(); i++) {
                if(consultar.equals(mep.selectEstatusPermisos().get(i).getNombre())) {
                    this.st.setInt(3, mep.selectEstatusPermisos().get(i).getIdEstatusPermiso());
                }
                if(insertar.equals(mep.selectEstatusPermisos().get(i).getNombre())) {
                    this.st.setInt(4, mep.selectEstatusPermisos().get(i).getIdEstatusPermiso());
                }
                if(actualizar.equals(mep.selectEstatusPermisos().get(i).getNombre())) {
                    this.st.setInt(5, mep.selectEstatusPermisos().get(i).getIdEstatusPermiso());
                }
                if(eliminar.equals(mep.selectEstatusPermisos().get(i).getNombre())) {
                    this.st.setInt(6, mep.selectEstatusPermisos().get(i).getIdEstatusPermiso());
                }
            }
            this.st.execute();
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPermisos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void updatePermisos(String username, String modulo, String consultar, String insertar, String actualizar, String eliminar, int id) {
        mep.cargarTabla();
        mm.cargarTabla();
        try {
            this.st = conectar().prepareStatement("UPDATE Permiso SET Username = ?, IdModulo = ?, Consultar = ?, Insertar = ?, Actualizar = ?, Eliminar = ? WHERE IdPermiso = ?");
            this.st.setString(1, username);
            for (int i = 0; i < mm.selectModulos().size(); i++) {
                if(modulo.equals(mm.selectModulos().get(i).getNombre())) {
                    this.st.setInt(2, mm.selectModulos().get(i).getIdModulo());
                }
            }
            for (int i = 0; i < mep.selectEstatusPermisos().size(); i++) {
                if(consultar.equals(mep.selectEstatusPermisos().get(i).getNombre())) {
                    this.st.setInt(3, mep.selectEstatusPermisos().get(i).getIdEstatusPermiso());
                }
                if(insertar.equals(mep.selectEstatusPermisos().get(i).getNombre())) {
                    this.st.setInt(4, mep.selectEstatusPermisos().get(i).getIdEstatusPermiso());
                }
                if(actualizar.equals(mep.selectEstatusPermisos().get(i).getNombre())) {
                    this.st.setInt(5, mep.selectEstatusPermisos().get(i).getIdEstatusPermiso());
                }
                if(eliminar.equals(mep.selectEstatusPermisos().get(i).getNombre())) {
                    this.st.setInt(6, mep.selectEstatusPermisos().get(i).getIdEstatusPermiso());
                }
            }
            this.st.setInt(7, id);
            this.st.executeUpdate();
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPermisos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void deletePermisos(int id) {
        try {
            this.st = conectar().prepareStatement("DELETE FROM Permiso WHERE IdPermiso = ?");
            this.st.setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPermisos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
