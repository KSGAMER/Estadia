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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import objetos.ObjetoEstatusUsuario;

/**
 *
 * @author KSGAMER
 */

//Se aplica Herencia de la clase padre BD
public class ModeloEstatusUsuarios extends BD{
    //Se declaran las variables de resultado y consultas preparadas
    private ResultSet rs;
    private PreparedStatement st;
    //Se declara un arreglod de tipo Objeto Estatus Usuario
    private ArrayList<ObjetoEstatusUsuario> listStatusUser = new ArrayList<>();
    
    //Método que extrae la información directamente de Base de Datos
    protected DefaultTableModel cargarTabla() {
        //Se declaran las columnas de la tabla
        String[] titulos = {"#", "Nombre"};
        //Se declara la tabla pasan las columnas
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Se declara un objeto que actuara como la fila de la tabla
        Object[] fila = new Object[2];
        try {
            //Se instancia la conexión a base de datos pasando la consulta preparada
            this.st = conectar().prepareStatement("SELECT * FROM EstatusUsuario");
            //Se ejecuta el Query
            this.rs = st.executeQuery();
            //Se iteran los resultados
            while (this.rs.next()) {
                //Se agregan los resultados al objeto
                fila[0] = rs.getInt("IdEstatusUsuario");
                fila[1] = rs.getString("Nombre");
                //Se agrega el objeto a la tabla
                tb.addRow(fila);
                //Se agregan los resultados al arreglo atraves de un objeto nuevo
                this.listStatusUser.add(new ObjetoEstatusUsuario(rs.getInt("IdEstatusUsuario"), rs.getString("Nombre")));
            }
            //Se cierra la conexión
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retorna la tabla
        return tb;
    }
    
    //Método que guarda los resultados obtenidos en formato de arreglo
    protected ArrayList<ObjetoEstatusUsuario> selectEstatusUsuarios() {
        return this.listStatusUser;
    }
}
