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
import objetos.ObjetoEstadoInvetararioHabitacion;
import objetos.ObjetoInventario;

/**
 *
 * @author KSGAMER
 */
public class ModeloEstadoInventarioHabitacion extends BD{
    private ResultSet rs;
    private PreparedStatement st;
    
    private ArrayList<ObjetoEstadoInvetararioHabitacion> list = new ArrayList<>();
    
    protected DefaultTableModel cargarTabla() {
        //Se declaran los titulos de la tabla
        String[] titulos = {"#", "Producto", "Cantidad", "Observaciones"};
        //Se declara la tabla pasando las columnas de la tabla
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Se declara un objeto que actuara como la fila de la tabla
        Object[] fila = new Object[4];

        try {
            //Se instancia la conexión a la base de datos pasando la consulta preparada
            this.st = conectar().prepareStatement("SELECT * FROM EstadoInventarioHabitacion");
            //Se ejecuta el Query
            this.rs = st.executeQuery();
            //Se iteran los resultados de la consulta
            while (this.rs.next()) {
                //Se agregan los resultados al objeto
                fila[0] = rs.getInt(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getInt(3);
                fila[3] = rs.getString(4);
                //Se agrega el objeto a la tabla
                tb.addRow(fila);
                //Se agregan los resultados al arreglo atraves de un nuevo objeto
            }
            //Se cierra la conexión
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloInventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retona la tabla
        return tb;
    }
}
