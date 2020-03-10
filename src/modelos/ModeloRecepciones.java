/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import objetos.ObjetoRecepcion;

/**
 *
 * @author KSGAMER
 */
public class ModeloRecepciones extends BD {

    private ResultSet rs;
    private PreparedStatement st;
    private ArrayList<ObjetoRecepcion> list = new ArrayList<>();

    protected DefaultTableModel cargarTabla() {
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Calendar fecha = new GregorianCalendar();
        String[] titulos = {"#", "Categoria", "Piso", "Estado Habitacion", "Nombre", "Reservacion", "Fecha Ingreso", "Fecha Salida"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[9];

        try {
            this.st = conectar().prepareStatement("SELECT h.IdHabitacion, c.Nombre, h.IdPiso, eh.Nombre, h.Nombre, h.PrecioSugerido FROM Habitacion as h INNER JOIN Categoria as c on c.IdCategoria = h.IdCategoria INNER JOIN EstadoHabitacion as eh on eh.IdEstadoHabitacion = h.IdEstadoHabitacion ORDER BY h.IdHabitacion ASC");
            this.rs = st.executeQuery();

            while (this.rs.next()) {
                fila[0] = rs.getInt(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getInt(3);
                fila[3] = rs.getString(4);
                fila[4] = rs.getString(5);
                fila[5] = rs.getDouble(6);
                tb.addRow(fila);
                this.list.add(new ObjetoRecepcion(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getDouble(6)));
            }
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }

    protected ArrayList<ObjetoRecepcion> selectRecepciones() {
        return this.list;
    }
}
