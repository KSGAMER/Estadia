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
import objetos.ObjetoEstatusUsuario;

/**
 *
 * @author KSGAMER
 */
public class ModeloEstatusUsuarios extends BD{
    private ResultSet rs;
    private PreparedStatement st;
    
    private ArrayList<ObjetoEstatusUsuario> listStatusUser = new ArrayList<>();
    
    protected DefaultTableModel cargarTabla() {
        String[] titulos = {"#", "Nombre"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[2];

        try {
            this.st = conectar().prepareStatement("SELECT * FROM EstatusUsuario");
            this.rs = st.executeQuery();

            while (this.rs.next()) {
                fila[0] = rs.getInt("IdEstatusUsuario");
                fila[1] = rs.getString("Nombre");
                tb.addRow(fila);
                this.listStatusUser.add(new ObjetoEstatusUsuario(rs.getInt("IdEstatusUsuario"), rs.getString("Nombre")));
            }
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
    
    protected ArrayList<ObjetoEstatusUsuario> selectEstatusUsuarios() {
        return this.listStatusUser;
    }
}
