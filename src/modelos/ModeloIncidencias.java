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
import objetos.ObjetoEstadoIncidencia;
import objetos.ObjetoHabitacion;
import objetos.ObjetoIncidencia;

/**
 *
 * @author KSGAMER
 */
public class ModeloIncidencias extends BD {

    private ResultSet rs;
    private PreparedStatement st;
    private ArrayList<ObjetoIncidencia> list = new ArrayList<>();
    private ModeloEstadoIncidencia estadoIncidencia = new ModeloEstadoIncidencia();
    private ModeloHabitaciones habitacion = new ModeloHabitaciones();

    protected DefaultTableModel cargarTabla() {
        this.estadoIncidencia.cargarTabla();
        this.habitacion.cargarTabla();
        String[] titulos = {"#", "Incidencia", "Observaciones", "Fecha Incidencia", "Hora Incidencia", "Usuario", "Habitacion", "Estado Incidencia"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[8];
        try {
            this.st = conectar().prepareStatement("SELECT * FROM Incidencias");
            this.rs = st.executeQuery();
            while (this.rs.next()) {
                fila[0] = rs.getInt(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3] = rs.getString(4);
                fila[4] = rs.getString(5);
                fila[5] = rs.getString(6);
                for (ObjetoHabitacion habitaciones : habitacion.selectHabitaciones()) {
                    if (habitaciones.getIdHabitacion() == rs.getInt(7)) {
                        fila[6] = habitaciones.getNombre();
                    }
                }
                for (ObjetoEstadoIncidencia estado : estadoIncidencia.selectEstadoIncidencias()) {
                    if (estado.getIdEstadoIncidencia() == rs.getInt(8)) {
                        fila[7] = estado.getNombre();
                    }
                }
                tb.addRow(fila);
                this.list.add(new ObjetoIncidencia(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloIncidencias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
    
    protected DefaultTableModel cargarTabla(String filtro) {
        String[] titulos = {"Incidencia", "Observaciones"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[2];
        try {
            this.st = conectar().prepareStatement("SELECT i.Nombre, i.Observaciones FROM Incidencias i INNER JOIN Habitacion h on h.IdHabitacion = i.IdHabitacion WHERE h.Nombre like CONCAT('%',?,'%')");
            this.st.setString(1, filtro);
            this.rs = st.executeQuery();
            while (this.rs.next()) {
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                tb.addRow(fila);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloIncidencias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
    
    protected ArrayList<ObjetoIncidencia> selectIncidencias() {
        return this.list;
    }
    
    protected void insertIncidencias(String incidencia, String observaciones, String fecha, String hora, String usuario, String habitacion, String estado) {
        this.estadoIncidencia.cargarTabla();
        this.habitacion.cargarTabla();
        try {
            this.st = conectar().prepareStatement("INSERT INTO Incidencias(Nombre, Observaciones, FechaIncidencia, HoraIncidencia, Username, IdHabitacion, IdEstadoIncidencia) VALUES (?,?,?,?,?,?,?)");
            this.st.setString(1, incidencia);
            this.st.setString(2, observaciones);
            this.st.setString(3, fecha);
            this.st.setString(4, hora);
            this.st.setString(5, usuario);
            for (ObjetoHabitacion habitaciones : this.habitacion.selectHabitaciones()) {
                if(habitaciones.getNombre().equals(habitacion)) {
                    this.st.setInt(6, habitaciones.getIdHabitacion());
                }
            }
            for (ObjetoEstadoIncidencia estadoIncidencia : estadoIncidencia.selectEstadoIncidencias()) {
                if(estadoIncidencia.getNombre().equals(estado)) {
                    this.st.setInt(7, estadoIncidencia.getIdEstadoIncidencia());
                }
            }
            this.st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloIncidencias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void updateIncidencias(String incidencia, String observaciones, String fecha, String hora, String usuario, String habitacion, String estado, int id) {
        this.estadoIncidencia.cargarTabla();
        this.habitacion.cargarTabla();
        try {
            this.st = conectar().prepareStatement("UPDATE Incidencias SET Nombre = ?, Observaciones = ?, FechaIncidencia = ?, HoraIncidencia = ?, Username = ?, IdHabitacion = ?, IdEstadoIncidencia = ? WHERE IdIndicencias = ?");
            this.st.setString(1, incidencia);
            this.st.setString(2, observaciones);
            this.st.setString(3, fecha);
            this.st.setString(4, hora);
            this.st.setString(5, usuario);
            for (ObjetoHabitacion habitaciones : this.habitacion.selectHabitaciones()) {
                if(habitaciones.getNombre().equals(habitacion)) {
                    this.st.setInt(6, habitaciones.getIdHabitacion());
                }
            }
            for (ObjetoEstadoIncidencia estadoIncidencia : estadoIncidencia.selectEstadoIncidencias()) {
                if(estadoIncidencia.getNombre().equals(estado)) {
                    this.st.setInt(7, estadoIncidencia.getIdEstadoIncidencia());
                }
            }
            this.st.setInt(8, id);
            this.st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloIncidencias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteIncidencias(int id) {
        try {
            this.st = conectar().prepareStatement("DELETE FROM Incidencias WHERE IdIndicencias = ?");
            this.st.setInt(1, id);
            this.st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloIncidencias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
