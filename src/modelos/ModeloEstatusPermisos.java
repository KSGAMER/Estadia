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
import objetos.ObjetoEstatusPermiso;

/**
 *
 * @author KSGAMER
 */

//Se aplica Herencia atraves de la clase padre BD
public class ModeloEstatusPermisos extends BD{
    //Se declaran las variables de resultado y consultas preparadas
    private ResultSet rs;
    private PreparedStatement st;
    //Se declara un arreglo de tipo objeto Estatus Permiso
    private ArrayList<ObjetoEstatusPermiso> listStatusAccess = new ArrayList<>();
    
    //Método que extrae la información de base de datos
    protected DefaultTableModel cargarTabla() {
        //Se declaran las columnas de la tabla
        String[] titulos = {"#", "Nombre"};
        //Se declara la tabla pasando las columnas
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Se declara un objeto que actuara como fila de la tabla
        Object[] fila = new Object[7];

        try {
            //Se instancia la conexión a base de datos y se pasa la consulta preparada
            this.st = conectar().prepareStatement("SELECT * FROM EstatusPermiso");
            //Se ejecuta el Query
            this.rs = st.executeQuery();
            //Se iteran los resultados
            while (this.rs.next()) {
                //Se pasan los resultados al objeto
                fila[0] = rs.getInt("IdEstatusPermiso");
                fila[1] = rs.getString("Nombre");
                //Se agrega el objeto a la tabla
                tb.addRow(fila);
                //Se agregan los resultados al arreglo creando un objeto
                this.listStatusAccess.add(new ObjetoEstatusPermiso(rs.getInt("IdEstatusPermiso"), rs.getString("Nombre")));
            }
            //Se cierra la conexión
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retorna la tabla
        return tb;
    }
    
    //Método que guarda la información en formato arreglo
    protected ArrayList<ObjetoEstatusPermiso> selectEstatusPermisos() {
        return listStatusAccess;
    }
}
