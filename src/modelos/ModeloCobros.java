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
import objetos.ObjetoCobro;

/**
 *
 * @author KSGAMER
 */
public class ModeloCobros extends BD {

    private ResultSet rs;
    private PreparedStatement st;
    private ArrayList<ObjetoCobro> listPay = new ArrayList<>();

    private ModeloReservaciones mr = new ModeloReservaciones();
    private ModeloTipoPagos mtp = new ModeloTipoPagos();
    private ModeloFacturaciones mf = new ModeloFacturaciones();

    protected DefaultTableModel cargarTabla(String buscar) {
        mtp.cargarTabla();
        mf.cargarTabla();
        String[] titulos = {"#", "Monto", "Tipo Pago", "Nombre", "RFC", "Correo", "Fecha Cobro", "Facturación"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[8];

        try {
            this.st = conectar().prepareStatement("SELECT C.IdReservacion, C.Monto, C.IdTipoPago, R.Nombre, C.RFC, C.Correo, C.FechaCobro, C.IdFacturacion FROM Cobro C INNER JOIN Reservacion R on C.IdReservacion = R.IdReservacion WHERE C.Monto like CONCAT('%', ? ,'%') or C.RFC like CONCAT('%', ? ,'%') or C.FechaCobro like CONCAT('%', ? ,'%') or R.Nombre like CONCAT('%', ? ,'%')");
            this.st.setString(1, buscar);
            this.st.setString(2, buscar);
            this.st.setString(3, buscar);
            this.st.setString(4, buscar);
            this.rs = st.executeQuery();

            while (this.rs.next()) {
                fila[0] = rs.getInt("C.IdReservacion");
                fila[1] = rs.getInt("C.Monto");
                for (int i = 0; i < mtp.selectTipoPagos().size(); i++) {
                    if (rs.getInt("C.IdTipoPago") == mtp.selectTipoPagos().get(i).getIdTipoPago()) {
                        fila[2] = mtp.selectTipoPagos().get(i).getNombre();
                    }
                }
                fila[3] = rs.getString("R.Nombre");
                fila[4] = rs.getString("C.RFC");
                fila[5] = rs.getString("C.Correo");
                fila[6] = rs.getString("C.FechaCobro");
                for (int i = 0; i < mf.selectFacturaciones().size(); i++) {
                    if (rs.getInt("C.IdFacturacion") == mf.selectFacturaciones().get(i).getIdFacturacion()) {
                        fila[7] = mf.selectFacturaciones().get(i).getNombre();
                    }
                }
                tb.addRow(fila);
            }
            this.listPay.add(new ObjetoCobro(rs.getInt("C.IdReservacion"), rs.getInt("C.Monto"), rs.getInt("C.IdTipoPago"), rs.getString("C.RFC"), rs.getString("C.Correo"), rs.getString("C.FechaCobro"), rs.getInt("C.IdFacturacion")));
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }

    protected DefaultTableModel cargarTabla() {
        mr.cargarTabla();
        mtp.cargarTabla();
        mf.cargarTabla();
        String[] titulos = {"#", "Monto", "Tipo Pago", "Nombre", "RFC", "Correo", "Fecha Cobro", "Facturación"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[8];

        try {
            this.st = conectar().prepareStatement("SELECT * FROM Cobro");
            this.rs = st.executeQuery();

            while (this.rs.next()) {
                fila[0] = rs.getInt("IdReservacion");
                fila[1] = rs.getInt("Monto");
                for (int i = 0; i < mtp.selectTipoPagos().size(); i++) {
                    if (rs.getInt("IdTipoPago") == mtp.selectTipoPagos().get(i).getIdTipoPago()) {
                        fila[2] = mtp.selectTipoPagos().get(i).getNombre();
                    }
                }
                for (int i = 0; i < mr.selectReservaciones().size(); i++) {
                    if (rs.getInt("IdReservacion") == mr.selectReservaciones().get(i).getIdReservacion()) {
                        fila[3] = mr.selectReservaciones().get(i).getNombre();
                    }
                }
                fila[4] = rs.getString("RFC");
                fila[5] = rs.getString("Correo");
                fila[6] = rs.getString("FechaCobro");
                for (int i = 0; i < mf.selectFacturaciones().size(); i++) {
                    if (rs.getInt("IdFacturacion") == mf.selectFacturaciones().get(i).getIdFacturacion()) {
                        fila[7] = mf.selectFacturaciones().get(i).getNombre();
                    }
                }
                tb.addRow(fila);
                this.listPay.add(new ObjetoCobro(rs.getInt("IdReservacion"), rs.getInt("Monto"), rs.getInt("IdTipoPago"), rs.getString("RFC"), rs.getString("Correo"), rs.getString("FechaCobro"), rs.getInt("IdFacturacion")));
            }

            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }

    protected ArrayList<ObjetoCobro> selectCobros() {
        return this.listPay;
    }

    protected void insertCobros(int idReservacion, double monto, String tipoPago, String rfc, String correo, String facturacion) {
        mtp.cargarTabla();
        mf.cargarTabla();
        try {
            this.st = conectar().prepareStatement("INSERT INTO Cobro (IdReservacion, Monto, IdTipoPago, RFC, Correo, FechaCobro, IdFacturacion) VALUES (?,?,?,?,?, convert(varchar, getdate(), 103) ,?)");
            this.st.setInt(1, idReservacion);
            this.st.setDouble(2, monto);
            for (int i = 0; i < mtp.selectTipoPagos().size(); i++) {
                if (tipoPago.equals(mtp.selectTipoPagos().get(i).getNombre())) {
                    this.st.setInt(3, mtp.selectTipoPagos().get(i).getIdTipoPago());
                }
            }
            this.st.setString(4, rfc);
            this.st.setString(5, correo);
            for (int i = 0; i < mf.selectFacturaciones().size(); i++) {
                if (facturacion.equals(mf.selectFacturaciones().get(i).getNombre())) {
                    this.st.setInt(6, mf.selectFacturaciones().get(i).getIdFacturacion());
                }
            }
            this.st.execute();
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void updateCobros(double monto, String tipoPago, String rfc, String correo, String facturacion, int id) {
        mtp.cargarTabla();
        mf.cargarTabla();
        try {
            this.st = conectar().prepareStatement("UPDATE Cobro SET  Monto = ?, IdTipoPago = ?, RFC = ?, Correo = ?, FechaCobro = GETDATE(), IdFacturacion = ? WHERE IdReservacion = ?");
            this.st.setDouble(1, monto);
            for (int i = 0; i < mtp.selectTipoPagos().size(); i++) {
                if (tipoPago.equals(mtp.selectTipoPagos().get(i).getNombre())) {
                    this.st.setInt(2, mtp.selectTipoPagos().get(i).getIdTipoPago());
                }
            }
            this.st.setString(3, rfc);
            this.st.setString(4, correo);
            for (int i = 0; i < mf.selectFacturaciones().size(); i++) {
                if (facturacion.equals(mf.selectFacturaciones().get(i).getNombre())) {
                    this.st.setInt(5, mf.selectFacturaciones().get(i).getIdFacturacion());
                }
            }
            this.st.setInt(6, id);
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCobros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void deleteCobros(int id) {
        try {
            this.st = conectar().prepareStatement("DELETE FROM Cobro WHERE IdCobro = ?");
            this.st.setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCobros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
