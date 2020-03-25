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
import objetos.ObjetoCaja;
import objetos.ObjetoEstadoCaja;

/**
 *
 * @author KSGAMER
 */
public class ModeloCajas extends BD{
    
    private ModeloEstatusCaja mec = new ModeloEstatusCaja();
    
    private ResultSet rs;
    private PreparedStatement st;
    
    private ArrayList<ObjetoCaja> list = new ArrayList<>();
    
    public DefaultTableModel cargarTabla() {
        mec.cargarTabla();
        String[] titulos = {"#", "Fecha de Apertura", "Monto de Apertura", "Fecha de Cierre", "Monto sin Cerrar", "Usuario Responsable", "Estado"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[7];
        try {
            this.st = conectar().prepareStatement("SELECT c.IdCaja, CONVERT(DATE, c.FechaApertura, 103) as FechaApertura, c.MontoApertura, CONVERT(DATE, c.FechaCierre, 103) as FechaCierre, c.MontoCierre, c.Username, c.IdEstadoCaja FROM Caja c ORDER BY CONVERT(DATE, c.FechaApertura, 103) DESC");
            this.rs = st.executeQuery();
            while (this.rs.next()) {
                fila[0] = rs.getInt(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getInt(3);
                fila[3] = rs.getString(4);
                fila[4] = rs.getInt(5);
                fila[5] = rs.getString(6);
                for (ObjetoEstadoCaja objetoEstadoCaja : mec.selectEstadoCaja()) {
                    if(objetoEstadoCaja.getIdEstadoCaja() == rs.getInt(7)) {
                        fila[6] = objetoEstadoCaja.getNombre();
                    }
                }
                tb.addRow(fila);
                this.list.add(new ObjetoCaja(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getInt(7)));
            }
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
    
    public ArrayList<ObjetoCaja> selectCaja() {
        return this.list;
    }
    
    public void insertCaja(String fechaApertura, double montoApertura, String fechaCierre, double montoCierre, String usuario, String estadoCaja) {
        mec.cargarTabla();
        try {
            this.st = conectar().prepareStatement("INSERT INTO Caja(FechaApertura, MontoApertura, FechaCierre, MontoCierre, Username, IdEstadoCaja) VALUES (?,?,?,?,?,?)");
            this.st.setString(1, fechaApertura);
            this.st.setDouble(2, montoApertura);
            this.st.setString(3, fechaCierre);
            this.st.setDouble(4, montoCierre);
            this.st.setString(5, usuario);
            for (ObjetoEstadoCaja objetoEstadoCaja : mec.selectEstadoCaja()) {
                if(objetoEstadoCaja.getNombre().equals(estadoCaja)) {
                    this.st.setInt(5, objetoEstadoCaja.getIdEstadoCaja());
                }
            }
            this.st.execute();
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCajas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateCaja(double montoApertura, String usuario, int id) {
        mec.cargarTabla();
        try {
            this.st = conectar().prepareStatement("UPDATE Caja SET MontoApertura = ?, Username = ? WHERE IdCaja = ?");
            this.st.setDouble(1, montoApertura);
            this.st.setString(2, usuario);
            this.st.setInt(3, id);
            this.st.executeUpdate();
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCajas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteCaja(int id) {
        try {
            this.st = conectar().prepareStatement("DELETE FROM Caja WHERE IdCaja = ?");
            this.st.setInt(1, id);
            this.st.execute();
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCajas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
