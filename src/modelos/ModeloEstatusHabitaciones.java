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
import objetos.ObjetoEstadoHabitacion;

/**
 *
 * @author KSGAMER
 */

//Se aplica Herencia de la clase padre BD
public class ModeloEstatusHabitaciones extends BD{
    //Se declaran las variables tanto de resultado como de consultas preparadas
    private ResultSet rs;
    private PreparedStatement st;
    //Se declara una arreglo de tipo Objeto Estado Habitación
    private ArrayList<ObjetoEstadoHabitacion> listStatusRoom = new ArrayList<>();
    
    //Método que retorna la información extraida de base de datos
    protected DefaultTableModel cargarTabla() {
        //Se declaran las columnas de la tabla
        String[] titulos = {"#", "Nombre"};
        //Se declara la variable de tipo tabla pasando las columnas de la tabla
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Se declara un objeto que actuara como la fila de la tabl
        Object[] fila = new Object[2];

        try {
            //Se intancia la conexión a base de datos y se pasa la consulta a realizar
            this.st = conectar().prepareStatement("SELECT * FROM EstadoHabitacion");
            //Se ejecuta el Query
            this.rs = st.executeQuery();
            //Se iteran los resultados
            while (this.rs.next()) {
                //Se agregan los resultados al objeto
                fila[0] = rs.getInt("IdEstadoHabitacion");
                fila[1] = rs.getString("Nombre");
                //Se agrega el objeto a la fila de la tabla
                tb.addRow(fila);
                //Se agregan los resultados al arreglo creando un objeto de tipo Objeto Estado Habitación
                this.listStatusRoom.add(new ObjetoEstadoHabitacion(rs.getInt("IdEstadoHabitacion"), rs.getString("Nombre")));
            }
            //Se cierra la conexión
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retorna la tabla
        return tb;
    }
    
    //Método que retorna la información de la tabla en formato de arreglo
    protected ArrayList<ObjetoEstadoHabitacion> selectEstadoHabitaciones() {
        return this.listStatusRoom;
    }
}
