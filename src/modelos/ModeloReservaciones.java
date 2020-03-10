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
import objetos.ObjetoReservacion;

/**
 *
 * @author KSGAMER
 */
public class ModeloReservaciones extends BD {

    private ModeloClientes mc = new ModeloClientes();
    private ModeloHabitaciones mh = new ModeloHabitaciones();

    private ResultSet rs;
    private PreparedStatement st;

    private ArrayList<ObjetoReservacion> listReservations = new ArrayList<>();

    protected DefaultTableModel cargarTabla() {
        mc.cargarTabla();
        mh.cargarTabla();

        String[] titulos = {"#", "Cliente", "Habitacion", "Fecha de Ingreso", "Fecha de Salida"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[5];

        try {
            this.st = conectar().prepareStatement("SELECT * FROM Reservacion");
            this.rs = st.executeQuery();

            while (rs.next()) {
                fila[0] = rs.getInt("IdReservacion");
                fila[1] = rs.getString("Nombre");
                for (int i = 0; i < mh.selectHabitaciones().size(); i++) {
                    if (rs.getInt("IdHabitacion") == mh.selectHabitaciones().get(i).getIdHabitacion()) {
                        fila[2] = mh.selectHabitaciones().get(i).getNombre();
                    }
                }
                fila[3] = rs.getString("FechaIngreso");
                fila[4] = rs.getString("FechaSalida");
                tb.addRow(fila);
                this.listReservations.add(new ObjetoReservacion(rs.getInt("IdReservacion"), rs.getString("Nombre"), rs.getInt("IdHabitacion"), rs.getString("FechaIngreso"), rs.getString("FechaSalida")));
            }
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloReservaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }

    protected ArrayList<ObjetoReservacion> selectReservaciones() {
        return this.listReservations;
    }

    protected void insertReservaciones(String nombre, String habitacion, String fechaIngreso, String fechaSalida) {
        mh.cargarTabla();
        try {
            this.st = conectar().prepareStatement("INSERT INTO Reservacion(Nombre, IdHabitacion, FechaIngreso, FechaSalida) VALUES (?,?,?,?)");
            this.st.setString(1, nombre);
            for (int i = 0; i < mh.selectHabitaciones().size(); i++) {
                if(mh.selectHabitaciones().get(i).getNombre().equals(habitacion)) {
                    this.st.setInt(2, mh.selectHabitaciones().get(i).getIdHabitacion());
                }
            }
            this.st.setString(3, fechaIngreso);
            this.st.setString(4, fechaSalida);
            this.st.execute();
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloReservaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void updateReservaciones(String nombre, String habitacion, String fechaIngreso, String fechaSalida, int id) {
        mh.cargarTabla();
        try {
            this.st = conectar().prepareStatement("UPDATE Reservacion SET Nombre = ?, IdHabitacion = ?, FechaIngreso = ?, FechaSalida = ? WHERE IdReservacion = ?");
            this.st.setString(1, nombre);
            for (int i = 0; i < mh.selectHabitaciones().size(); i++) {
                if(mh.selectHabitaciones().get(i).getNombre().equals(habitacion)) {
                    this.st.setInt(2, mh.selectHabitaciones().get(i).getIdHabitacion());
                }
            }
            this.st.setString(3, fechaIngreso);
            this.st.setString(4, fechaSalida);
            this.st.setInt(5, id);
            this.st.executeUpdate();
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloReservaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void deleteReservaciones(int id) {
        try {
            this.st = conectar().prepareStatement("DELETE FROM Reservacion WHERE IdReservacion = ?");
            this.st.setInt(1, id);
            this.st.execute();
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloReservaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
