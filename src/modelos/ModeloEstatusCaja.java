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
import objetos.ObjetoEstadoCaja;

/**
 *
 * @author KSGAMER
 */
public class ModeloEstatusCaja extends BD{
    private ResultSet rs;
    private PreparedStatement st;
    
    private ArrayList<ObjetoEstadoCaja> list = new ArrayList<>();
    
    protected DefaultTableModel cargarTabla() {
        String[] titulos = {"#", "Nombre"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[2];
        try {
            this.st = conectar().prepareStatement("SELECT * FROM EstadoCaja");
            this.rs = st.executeQuery();
            while (this.rs.next()) {
                fila[0] = rs.getInt(1);
                fila[1] = rs.getString(2);
                tb.addRow(fila);
                this.list.add(new ObjetoEstadoCaja(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
    
    protected ArrayList<ObjetoEstadoCaja> selectEstadoCaja() {
        return this.list;
    }
}
