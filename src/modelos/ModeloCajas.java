/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import controladores.ControladorUsuarios;
import ds.desktop.notify.DesktopNotify;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import objetos.ObjetoCaja;
import objetos.ObjetoEstadoCaja;
import objetos.ObjetoUsuario;

/**
 *
 * @author KSGAMER
 */
public class ModeloCajas extends BD{
    
    private ModeloEstatusCaja mec = new ModeloEstatusCaja();
    private ModeloUsuarios cuser = new ControladorUsuarios();
    
    private ResultSet rs;
    private PreparedStatement st;
    
    private ArrayList<ObjetoCaja> list = new ArrayList<>();
    
    public DefaultTableModel cargarTabla() {
        mec.cargarTabla();
        String[] titulos = {"#", "Fecha Apertura", "Monto Apertura", "Fecha Cierre", "Hora Cierre","Monto Cierre", "Usuario", "Estado"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[8];
        try {
            this.st = conectar().prepareStatement("SELECT c.IdCaja, CONVERT(DATE, c.FechaApertura, 103) as FechaApertura, c.MontoApertura, CONVERT(DATE, c.FechaCierre, 103) as FechaCierre, c.HoraCierre, c.MontoCierre, c.Username, c.IdEstadoCaja FROM Caja c ORDER BY CONVERT(DATE, c.FechaApertura, 103) DESC");
            this.rs = st.executeQuery();
            while (this.rs.next()) {
                fila[0] = rs.getInt(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getDouble(3);
                fila[3] = rs.getString(4);
                fila[4] = rs.getString(5);
                fila[5] = rs.getDouble(6);
                fila[6] = rs.getString(7);
                for (ObjetoEstadoCaja objetoEstadoCaja : mec.selectEstadoCaja()) {
                    if(objetoEstadoCaja.getIdEstadoCaja() == rs.getInt(8)) {
                        fila[7] = objetoEstadoCaja.getNombre();
                    }
                }
                tb.addRow(fila);
                this.list.add(new ObjetoCaja(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getDouble(6), rs.getString(7), rs.getInt(8)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
    
    public ArrayList<ObjetoCaja> selectCaja() {
        return this.list;
    }
    
    public void insertCaja(String fechaApertura, double montoApertura, String fechaCierre, String horaCierre,double montoCierre, String usuario, String estadoCaja) {
        mec.cargarTabla();
        try {
            this.st = conectar().prepareStatement("INSERT INTO Caja(FechaApertura, MontoApertura, FechaCierre, HoraCierre, MontoCierre, Username, IdEstadoCaja) VALUES (?,?,?,?,?,?,?)");
            this.st.setString(1, fechaApertura);
            this.st.setDouble(2, montoApertura);
            this.st.setString(3, fechaCierre);
            this.st.setString(4, horaCierre);
            this.st.setDouble(5, montoCierre);
            this.st.setString(6, usuario);
            for (ObjetoEstadoCaja objetoEstadoCaja : mec.selectEstadoCaja()) {
                if(objetoEstadoCaja.getNombre().equals(estadoCaja)) {
                    this.st.setInt(7, objetoEstadoCaja.getIdEstadoCaja());
                }
            }
            this.st.execute();
                        DesktopNotify.showDesktopMessage("Exito", "Apertura correcta de caja", DesktopNotify.SUCCESS);
      
        } catch (SQLException ex) {
            System.out.println(ex);
            DesktopNotify.showDesktopMessage("Error", "Ocurrió un error al intentar "
                    + "realizar la apertura de caja", DesktopNotify.ERROR);
       }
    }
    
    public void updateCierreCaja(String fechaCierre, String horaCierre, Double montoCierre, String estadoCaja, String Usuario) {
        mec.cargarTabla();
        cuser.cargarTabla();
        try {
            this.st = conectar().prepareStatement("UPDATE Caja SET FechaCierre = ?, HoraCierre = ?, MontoCierre = ?, IdEstadoCaja=? WHERE IdEstadoCaja=1  and Username = ?");
            this.st.setString(1, fechaCierre);
            this.st.setString(2, horaCierre);
            this.st.setDouble(3, montoCierre);
            for (ObjetoEstadoCaja objetoEstadoCaja : mec.selectEstadoCaja()) {
                if (objetoEstadoCaja.getNombre().equals(estadoCaja)) {
                    this.st.setInt(4, objetoEstadoCaja.getIdEstadoCaja());
                }
            }            
            this.st.setString(5, Usuario);
            this.st.executeUpdate();
           DesktopNotify.showDesktopMessage("Exito", "Cierre de caja realizado correctamente", DesktopNotify.SUCCESS);
      
        } catch (SQLException ex) {
              DesktopNotify.showDesktopMessage("Error", "Ocurrió un error al intentar "
                    + "el cierre de caja o no existe una caja abierta para este usuario", DesktopNotify.ERROR);
      }
    }
    
       public void updateAperturaCaja(double montoApertura, String usuario, int id) {
        mec.cargarTabla();
        try {
            this.st = conectar().prepareStatement("UPDATE Caja SET MontoApertura = ?, Username = ? WHERE IdCaja = ?");
            this.st.setDouble(1, montoApertura);
            this.st.setString(2, usuario);
            this.st.setInt(3, id);
            this.st.executeUpdate();
              DesktopNotify.showDesktopMessage("Exito", "Datos de caja actualizados correctamente", DesktopNotify.SUCCESS);
      
        } catch (SQLException ex) {
             DesktopNotify.showDesktopMessage("Error", "Ocurrió un error al intentar "
                    + "actualizar los datos de la caja", DesktopNotify.ERROR);
      }
    }
    
    public void deleteCaja(int id) {
        try {
            this.st = conectar().prepareStatement("DELETE FROM Caja WHERE IdCaja = ?");
            this.st.setInt(1, id);
            this.st.execute();
              DesktopNotify.showDesktopMessage("Exito", "Datos eliminados correctamente", DesktopNotify.SUCCESS);
      
        } catch (SQLException ex) {
              DesktopNotify.showDesktopMessage("Error", "Ocurrió un error al intentar "
                    + "eliminar los detalles de la caja", DesktopNotify.ERROR);
      }
    }
}
