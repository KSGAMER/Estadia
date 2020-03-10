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
import objetos.ObjetoHabitacion;

/**
 *
 * @author KSGAMER
 */
public class ModeloHabitaciones extends BD {

    private ModeloCategorias cc = new ModeloCategorias();
    private ModeloPisos cp = new ModeloPisos();
    private ModeloEstatusHabitaciones meh = new ModeloEstatusHabitaciones();

    private ResultSet rs;
    private PreparedStatement st;

    private ArrayList<ObjetoHabitacion> listRoom = new ArrayList<>();

    protected DefaultTableModel cargarTabla(String buscar) {
        cc.cargarTabla();
        cp.cargarTabla();
        meh.cargarTabla();
        String[] titulos = {"#", "Nombre", "Piso", "Categoria", "Precio Sugerido", "Caracteriscas", "Estatus"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[7];

        try {
            this.st = conectar().prepareStatement("SELECT * FROM Habitacion WHERE Nombre like CONCAT('%', ? ,'%') or PrecioSugerido like CONCAT('%', ? ,'%') or Caracteristicas like CONCAT('%', ?, '%')");
            this.st.setString(1, buscar);
            this.st.setString(2, buscar);
            this.st.setString(3, buscar);
            this.rs = st.executeQuery();

            while (this.rs.next()) {
                fila[0] = rs.getInt("IdHabitacion");
                fila[1] = rs.getString("Nombre");
                for (int i = 0; i < cp.selectPisos().size(); i++) {
                    if (cp.selectPisos().get(i).getIdPiso() == rs.getInt("IdPiso")) {
                        fila[2] = cp.selectPisos().get(i).getNombre();
                    }
                }
                for (int i = 0; i < cc.selectCategorias().size(); i++) {
                    if (cc.selectCategorias().get(i).getIdCategoria() == rs.getInt("IdCategoria")) {
                        fila[3] = cc.selectCategorias().get(i).getNombre();
                    }
                }
                fila[4] = rs.getDouble("PrecioSugerido");
                fila[5] = rs.getString("Caracteristicas");
                for (int i = 0; i < meh.selectEstadoHabitaciones().size(); i++) {
                    if (meh.selectEstadoHabitaciones().get(i).getIdEstadoHabitacion() == rs.getInt("IdEstadoHabitacion")) {
                        fila[6] = meh.selectEstadoHabitaciones().get(i).getNombre();
                    }
                }
                tb.addRow(fila);
                this.listRoom.add(new ObjetoHabitacion(rs.getInt("IdHabitacion"), rs.getString("Nombre"), rs.getInt("IdPiso"), rs.getInt("IdCategoria"), rs.getDouble("PrecioSugerido"), rs.getString("Caracteristicas"), rs.getInt("IdEstadoHabitacion")));
            }
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }

    protected DefaultTableModel cargarTabla() {
        cc.cargarTabla();
        cp.cargarTabla();
        meh.cargarTabla();
        String[] titulos = {"#", "Nombre", "Piso", "Categoria", "Precio Sugerido", "Caracteriscas", "Estatus"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[7];

        try {
            this.st = conectar().prepareStatement("SELECT * FROM Habitacion");
            this.rs = st.executeQuery();

            while (this.rs.next()) {
                fila[0] = rs.getInt("IdHabitacion");
                fila[1] = rs.getString("Nombre");
                for (int i = 0; i < cp.selectPisos().size(); i++) {
                    if (cp.selectPisos().get(i).getIdPiso() == rs.getInt("IdPiso")) {
                        fila[2] = cp.selectPisos().get(i).getNombre();
                    }
                }
                for (int i = 0; i < cc.selectCategorias().size(); i++) {
                    if (cc.selectCategorias().get(i).getIdCategoria() == rs.getInt("IdCategoria")) {
                        fila[3] = cc.selectCategorias().get(i).getNombre();
                    }
                }
                fila[4] = rs.getDouble("PrecioSugerido");
                fila[5] = rs.getString("Caracteristicas");
                for (int i = 0; i < meh.selectEstadoHabitaciones().size(); i++) {
                    if (meh.selectEstadoHabitaciones().get(i).getIdEstadoHabitacion() == rs.getInt("IdEstadoHabitacion")) {
                        fila[6] = meh.selectEstadoHabitaciones().get(i).getNombre();
                    }
                }
                tb.addRow(fila);
                this.listRoom.add(new ObjetoHabitacion(rs.getInt("IdHabitacion"), rs.getString("Nombre"), rs.getInt("IdPiso"), rs.getInt("IdCategoria"), rs.getDouble("PrecioSugerido"), rs.getString("Caracteristicas"), rs.getInt("IdEstadoHabitacion")));
            }
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }

    protected ArrayList<ObjetoHabitacion> selectHabitaciones() {
        return this.listRoom;
    }

    protected void insertHabitaciones(String nombre, String piso, String categoria, double precioSugerido, String caracteristicas, String estatus) {
        cc.cargarTabla();
        cp.cargarTabla();
        meh.cargarTabla();
        try {
            this.st = conectar().prepareStatement("INSERT INTO Habitacion(Nombre, IdPiso, IdCategoria, PrecioSugerido, Caracteristicas, IdEstadoHabitacion) VALUES (?,?,?,?,?,?)");
            this.st.setString(1, nombre);
            for (int i = 0; i < cp.selectPisos().size(); i++) {
                if (piso.equals(cp.selectPisos().get(i).getNombre())) {
                    this.st.setInt(2, cp.selectPisos().get(i).getIdPiso());
                }
            }
            for (int i = 0; i < cc.selectCategorias().size(); i++) {
                if (categoria.equals(cc.selectCategorias().get(i).getNombre())) {
                    this.st.setInt(3, cc.selectCategorias().get(i).getIdCategoria());
                }
            }
            this.st.setDouble(4, precioSugerido);
            this.st.setString(5, caracteristicas);
            for (int i = 0; i < meh.selectEstadoHabitaciones().size(); i++) {
                if (estatus.equals(meh.selectEstadoHabitaciones().get(i).getNombre())) {
                    this.st.setInt(6, meh.selectEstadoHabitaciones().get(i).getIdEstadoHabitacion());
                }
            }
            this.st.execute();
            conectar().close();
            DesktopNotify.showDesktopMessage("Exito", "Los datos de la habitación han sido insertados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar insertar los datos de la habitación, intentelo nuevamente y asegurese de que todos los campos obligatorios este llenos", DesktopNotify.ERROR);
        }
    }

    protected void updateHabitaciones(String nombre, String piso, String categoria, double precioSugerido, String caracteristicas, String estatus, int id) {
        cc.cargarTabla();
        cp.cargarTabla();
        meh.cargarTabla();
        try {
            this.st = conectar().prepareStatement("UPDATE Habitacion SET Nombre = ?, IdPiso = ?, IdCategoria = ?, PrecioSugerido = ?, Caracteristicas = ?, IdEstadoHabitacion = ? WHERE IdHabitacion = ?");
            this.st.setString(1, nombre);
            for (int i = 0; i < cp.selectPisos().size(); i++) {
                if (piso.equals(cp.selectPisos().get(i).getNombre())) {
                    this.st.setInt(2, cp.selectPisos().get(i).getIdPiso());
                }
            }
            for (int i = 0; i < cc.selectCategorias().size(); i++) {
                if (categoria.equals(cc.selectCategorias().get(i).getNombre())) {
                    this.st.setInt(3, cc.selectCategorias().get(i).getIdCategoria());
                }
            }
            this.st.setDouble(4, precioSugerido);
            this.st.setString(5, caracteristicas);
            for (int i = 0; i < meh.selectEstadoHabitaciones().size(); i++) {
                if (estatus.equals(meh.selectEstadoHabitaciones().get(i).getNombre())) {
                    this.st.setInt(6, meh.selectEstadoHabitaciones().get(i).getIdEstadoHabitacion());
                }
            }
            this.st.setInt(7, id);
            this.st.executeUpdate();
            conectar().close();
            DesktopNotify.showDesktopMessage("Exito", "Los datos de la habitación han sido actualizados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar actualizar los datos de la habitación, intentelo nuevamente y asegurese de que todos los campos obligatorios este llenos", DesktopNotify.ERROR);
        }
    }

    protected void updateHabitaciones(String habitacion, String estatusHabitacion) {
        cargarTabla();
        meh.cargarTabla();
        try {
            this.st = conectar().prepareStatement("UPDATE Habitacion SET IdEstadoHabitacion = ? WHERE IdHabitacion = ?");
            for (int i = 0; i < this.selectHabitaciones().size(); i++) {
                if (this.selectHabitaciones().get(i).getNombre().equals(habitacion)) {
                    this.st.setInt(1, this.selectHabitaciones().get(i).getIdHabitacion());
                }
            }
            for (int i = 0; i < meh.selectEstadoHabitaciones().size(); i++) {
                if (meh.selectEstadoHabitaciones().get(i).getNombre().equals(estatusHabitacion)) {
                    this.st.setInt(2, meh.selectEstadoHabitaciones().get(i).getIdEstadoHabitacion());
                }
            }
            this.st.executeUpdate();
            DesktopNotify.showDesktopMessage("Exito", "Los datos de la habitación han sido actualizados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar actualizar los datos de la habitación, intentelo nuevamente y asegurese de que todos los campos obligatorios este llenos", DesktopNotify.ERROR);
        }
    }
    
    public void updateHabitaciones(int idHabitacion, String estatus) {
        cargarTabla();
        meh.cargarTabla();
        try {
            this.st = conectar().prepareStatement("UPDATE Habitacion SET IdEstadoHabitacion = ? WHERE IdHabitacion = ?");
            this.st.setInt(2, idHabitacion);
            for (int i = 0; i < meh.selectEstadoHabitaciones().size(); i++) {
                if (meh.selectEstadoHabitaciones().get(i).getNombre().equals(estatus)) {
                    this.st.setInt(1, meh.selectEstadoHabitaciones().get(i).getIdEstadoHabitacion());
                }
            }
            this.st.executeUpdate();
            //DesktopNotify.showDesktopMessage("Exito", "Los datos de la habitación han sido actualizados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
//            System.out.println(ex);
            //DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar actualizar los datos de la habitación, intentelo nuevamente y asegurese de que todos los campos obligatorios este llenos", DesktopNotify.ERROR);
        }
    }

    protected void deleteHabitaciones(int id) {
        try {
            this.st = conectar().prepareStatement("DELETE FROM Habitacion WHERE IdHabitacion = ?");
            this.st.setInt(1, id);
            this.st.execute();
            conectar().close();
            DesktopNotify.showDesktopMessage("Exito", "Los datos de la habitación han sido eliminados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar eliminar los datos de la habitación, una habitación previamente utilizada no puede ser eliminada", DesktopNotify.ERROR);
        }
    }

}
