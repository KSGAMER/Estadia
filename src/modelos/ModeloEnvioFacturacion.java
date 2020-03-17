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
import objetos.objetoEnvioFacturacion;

/**
 *
 * @author KSGAMER
 */

//Se aplica Herencia de la clase padre BD a Modelo Envio Facturación
public class ModeloEnvioFacturacion extends BD{
    //Se declaran las variables de resultado y de consultas preparadas
    private ResultSet rs;
    private PreparedStatement st;
    //Se declara un arreglo de tipo ObjetoFacturacion parar poder usarlo en las demás clases
    private ArrayList<objetoEnvioFacturacion> list = new ArrayList<>();
    
    //Método que extrae la información directamente de Base de Datos
    protected DefaultTableModel cargarTabla(){
        //Se declaran las columnas de la tabla
        String [] titulos = {"Razon Social", "Email","Tipo Pago", "Fecha Ingreso", "Fecha Salida", "Fecha Cobro", "Monto"};
        //Se crea una variable de tipo tabla y se pasan las columnas
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Se declara un objeto que actuara como la fila de tabla
        Object[] fila = new Object[7];
        try {
            //Se instancia la conexión a Base de Datos y se pasa la consulta preparada
            this.st = conectar().prepareStatement("SELECT r.Nombre ,c.Correo, tp.Nombre as TipoPago, r.FechaIngreso, r.FechaSalida, c.FechaCobro, c.Monto FROM Cobro c INNER JOIN Reservacion r on r.IdReservacion = c.IdReservacion INNER JOIN TipoPago tp on tp.IdTipoPago = c.IdTipoPago WHERE c.IdFacturacion = 1");
            //Se ejecuta el Query
            this.rs = st.executeQuery();
            //Se iteran los resultados
            while(rs.next()) {
                //Se agregan los resultados al objeto fila
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3] = rs.getString(4);
                fila[4] = rs.getString(5);
                fila[5] = rs.getString(6);
                fila[6] = rs.getDouble(7);
                //El objeto fila se agrega a la tabla
                tb.addRow(fila);
                //Los resultados se agregan al arreglo
                list.add(new objetoEnvioFacturacion(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7)));
            }
            //Se cierra la conexión
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retorna la tabla
        return tb;
    }
    
    //Métdo que retorna un arreglo con los resultados obtenidos de base de datos
    protected ArrayList<objetoEnvioFacturacion> selectEnvioFacturaciones() {
        return list;
    }
}
