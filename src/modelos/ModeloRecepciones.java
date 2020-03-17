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

//Se aplica Herencia de la clase padre BD
public class ModeloRecepciones extends BD {
    //Se declaran las variable de resultado y de consultas preparadas
    private ResultSet rs;
    private PreparedStatement st;
    //Se declara un arreglo de tipo Objeto Recepción
    private ArrayList<ObjetoRecepcion> list = new ArrayList<>();

    //Método que extrae la información directamente de base de datos
    protected DefaultTableModel cargarTabla() {
        //Se declara una variable para formatear las fechas en el formato establecido
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        //Se declara una variable de tipo Calendario instanciando un calendario gergoriano (Calendario estandar Occidental)
        Calendar fecha = new GregorianCalendar();
        //Se declaran las columnas de la tabla
        String[] titulos = {"#", "Categoria", "Piso", "Estado Habitacion", "Nombre", "Reservacion", "Fecha Ingreso", "Fecha Salida"};
        //Se declara la tabla pasando las columnas
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Se declara un objeto que actuara como la fila de la tabla
        Object[] fila = new Object[9];

        try {
            //Se instancia la conexión a base de datos pasando la consulta preparada
            this.st = conectar().prepareStatement("SELECT h.IdHabitacion, c.Nombre, h.IdPiso, eh.Nombre, h.Nombre, h.PrecioSugerido FROM Habitacion as h INNER JOIN Categoria as c on c.IdCategoria = h.IdCategoria INNER JOIN EstadoHabitacion as eh on eh.IdEstadoHabitacion = h.IdEstadoHabitacion ORDER BY h.IdHabitacion ASC");
            //Se ejecuta el Query
            this.rs = st.executeQuery();
            //Se iteran los resultados
            while (this.rs.next()) {
                //Se agregan los resultados al objeto
                fila[0] = rs.getInt(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getInt(3);
                fila[3] = rs.getString(4);
                fila[4] = rs.getString(5);
                fila[5] = rs.getDouble(6);
                //Se agrega el objeto a la tabla
                tb.addRow(fila);
                //Se agregan los resultados al arreglo atraves de un nuevo objeto
                this.list.add(new ObjetoRecepcion(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getDouble(6)));
            }
            //Se cierra la conexión
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retorna la tabla
        return tb;
    }

    //Método que retorna la información en formato de arreglo
    protected ArrayList<ObjetoRecepcion> selectRecepciones() {
        return this.list;
    }
}
