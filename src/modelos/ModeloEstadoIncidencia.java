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

/**
 *
 * @author KSGAMER
 */
public class ModeloEstadoIncidencia extends BD {

    private ResultSet rs;
    private PreparedStatement st;
    private ArrayList<ObjetoEstadoIncidencia> list = new ArrayList<>();

    protected DefaultTableModel cargarTabla() {
        String[] titulos = {"#", "Estado Incidencia"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[2];
        try {
            this.st = conectar().prepareStatement("SELECT * FROM Cargo");
            this.rs = st.executeQuery();
            while (this.rs.next()) {
                fila[0] = rs.getInt(1);
                fila[1] = rs.getString(2);
                tb.addRow(fila);
                this.list.add(new ObjetoEstadoIncidencia(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloEstadoIncidencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }

    public ArrayList<ObjetoEstadoIncidencia> selectEstadoIncidencias() {
        return this.list;
    }
}
