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
import objetos.ObjetoCobro;

/**
 *
 * @author KSGAMER
 */
//Se aplica Herencia haciendo que la clase Modelo Cobros sea hijo de BD para heredar los métodos antes realizados
public class ModeloCobros extends BD {

    //Se declaran las variables tanto de resultados como los de consultas preparadas
    private ResultSet rs;
    private PreparedStatement st;
    //Se declara un arreglo de tipo Objeto Cobro para poder usarlo en las diferentes clases
    private ArrayList<ObjetoCobro> listPay = new ArrayList<>();

    //Se intancian las variables a utilizar para comparar información
    private ModeloReservaciones mr = new ModeloReservaciones();
    private ModeloTipoPagos mtp = new ModeloTipoPagos();
    private ModeloFacturaciones mf = new ModeloFacturaciones();
    private ModeloHabitaciones mh = new ModeloHabitaciones();
    private ModeloCategorias mcat = new ModeloCategorias();

    //Se declara un método que retorna una tabla usando sobrecarga de operadores
    protected DefaultTableModel cargarTabla() {
        //Se instancian los resultados a utilizar para reemplazarlos en la consulta
        mr.cargarTabla();
        mtp.cargarTabla();
        mf.cargarTabla();
        mh.cargarTabla();
        mcat.cargarTabla();
        //Se agregan los titulos de la tabla
        String[] titulos = {"Monto", "Tipo Pago", "Nombre", "RFC", "Correo", "Fecha Cobro", "Facturación"};
        //Se crea una variable de tipo tabla pasando los titulos de la columna
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Se declara un objeto que actuara como la fila de la tabla
        Object[] fila = new Object[7];

        try {
            //Se instacia la conexión a la base de datos y se declara la consulta preparada a realizar
            this.st = conectar().prepareStatement("SELECT * FROM Cobro");
            //Se ejecuta el Query
            this.rs = st.executeQuery();
            //Se iteran los resultados obtenidos de la consulta preparada
            while (this.rs.next()) {
                fila[0] = rs.getInt("Monto");
                //Se recorre el id obtenido de la consulta preparada y se compara con el resultado obtenido de la instancia de la variable cargada con el metodo cargarTabla()
                for (int i = 0; i < mtp.selectTipoPagos().size(); i++) {
                    //Si el Id es igual al Id de la clase previamente cargada se prosigue
                    if (rs.getInt("IdTipoPago") == mtp.selectTipoPagos().get(i).getIdTipoPago()) {
                        //Se extrae el resultado y se remplaza por el nombre
                        fila[1] = mtp.selectTipoPagos().get(i).getNombre();
                    }
                }
                fila[2] = rs.getString("Nombre");
                fila[3] = rs.getString("RFC");
                fila[4] = rs.getString("Correo");
                fila[5] = rs.getString("FechaCobro");
                //Se recorre el id obtenido de la consulta preparada y se compara con el resultado obtenido de la instancia de la variable cargada con el metodo cargarTabla()
                for (int i = 0; i < mf.selectFacturaciones().size(); i++) {
                    //Si el Id es igual al Id de la clase previamente cargada se prosigue
                    if (rs.getInt("IdFacturacion") == mf.selectFacturaciones().get(i).getIdFacturacion()) {
                        //Se extrae el resultado y se remplaza por el nombre
                        fila[6] = mf.selectFacturaciones().get(i).getNombre();
                    }
                }
                //Se agrega el objeto fila a la tabla
                tb.addRow(fila);
                //Se agrega el resultado al arreglo
                this.listPay.add(new ObjetoCobro(rs.getInt("Monto"), rs.getInt("IdTipoPago"), rs.getString("RFC"), rs.getString("Correo"), rs.getString("FechaCobro"), rs.getInt("IdFacturacion")));
            }
            //Se cierra la conexión
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retorna la tabla
        return tb;
    }
    
    public DefaultTableModel cargarTabla(String usuario) {
             //Se instancian los resultados a utilizar para reemplazarlos en la consulta
        mr.cargarTabla();
        mtp.cargarTabla();
        mf.cargarTabla();
        mh.cargarTabla();
        mcat.cargarTabla();
        //Se agregan los titulos de la tabla
        String[] titulos = {"#", "Monto", "Tipo Pago", "Nombre", "RFC", "Correo", "Fecha Cobro", "Facturación"};
        //Se crea una variable de tipo tabla pasando los titulos de la columna
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Se declara un objeto que actuara como la fila de la tabla
        Object[] fila = new Object[8];

        try {
            //Se instacia la conexión a la base de datos y se declara la consulta preparada a realizar
            this.st = conectar().prepareStatement("SELECT h.Nombre, h.PrecioSugerido, c.Nombre FROM Cobro c INNER JOIN Habitacion h on h.IdHabitacion = c.IdHabitacion WHERE CONVERT(DATE, GETDATE(), 103) = CONVERT(DATE, c.FechaCobro, 103) and c.Username = ? ORDER BY CONVERT(DATE, c.FechaCobro, 103) DESC, c.Username");
            //Se pasan los parametros a la consulta
            this.st.setString(1, usuario);
            //Se ejecuta el Query
            this.rs = st.executeQuery();
            //Se iteran los resultados obtenidos de la consulta preparada
            while (this.rs.next()) {
                fila[0] = rs.getString(1);
                fila[1] = rs.getDouble(2);
                fila[2] = rs.getString(3);
                //Se agrega el objeto fila a la tabla
                tb.addRow(fila);
                //Se agrega el resultado al arreglo
                this.listPay.add(new ObjetoCobro(rs.getInt("Monto"), rs.getInt("IdTipoPago"), rs.getString("RFC"), rs.getString("Correo"), rs.getString("FechaCobro"), rs.getInt("IdFacturacion")));
            }
            //Se cierra la conexión
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retorna la tabla
        return tb;
    }

    //Este método retorna el arreglo de los resultados previamentes cargados de Cobro
    protected ArrayList<ObjetoCobro> selectCobros() {
        return this.listPay;
    }

    //Método que inserta un cobro a la tabla
    protected void insertCobros(double monto, String tipoPago, String rfc, String correo, String usuario, String Nombre, String NombreHabitacion, String FechaIngreso, String FechaSalida, String IdFacturacion) {
        //Se instancian los resultados a utilizar para reemplazarlos en la consulta
        mtp.cargarTabla();
        mf.cargarTabla();
        mh.cargarTabla();
        mcat.cargarTabla();
        try {
            //Se instacia la conexión a la base de datos y se declara la consulta preparada a realizar
            this.st = conectar().prepareStatement("INSERT INTO Cobro ("
                    + "Monto,"
                    + "IdTipoPago,"
                    + "RFC,"
                    + "Correo,"
                    + "FechaCobro,"
                    + "Username,"
                    + "Nombre,"
                    + "IdHabitacion,"
                    + "FechaIngreso,"
                    + "FechaSalida,"
                    + "IdFacturacion)"
                    + " VALUES (?,?,?,?, convert(varchar, getdate(), 103),?,?,?,?,?,?)");
            this.st.setDouble(1, monto);
            //Se recorre el id obtenido de la consulta preparada y se compara con el resultado obtenido de la instancia de la variable cargada con el metodo cargarTabla()
            for (int i = 0; i < mtp.selectTipoPagos().size(); i++) {
                //Si el Nombre es igual al Nombre de la clase previamente cargada se prosigue
                if (tipoPago.equals(mtp.selectTipoPagos().get(i).getNombre())) {
                    //Se extrae el resultado y se remplaza por el ID
                    this.st.setInt(2, mtp.selectTipoPagos().get(i).getIdTipoPago());
                }
            }
            this.st.setString(3, rfc);
            this.st.setString(4, correo);
            this.st.setString(5, usuario);
            this.st.setString(6, Nombre);
            //Se recorre el id obtenido de la consulta preparada y se compara con el resultado obtenido de la instancia de la variable cargada con el metodo cargarTabla()
         
            for (int i = 0; i < mh.selectHabitaciones().size(); i++) {
                //Si el Nombre es igual al Nombre de la clase previamente cargada se prosigue
                System.out.println(mh.selectHabitaciones().get(i).getNombre());
                if (NombreHabitacion.equals(mh.selectHabitaciones().get(i).getNombre())) {
                    //Se extrae el resultado y se remplaza por el ID
                    System.out.println(mh.selectHabitaciones().get(i).getNombre());
                    this.st.setInt(7, mh.selectHabitaciones().get(i).getIdHabitacion());
                }
            }

            this.st.setString(8, FechaIngreso);
            this.st.setString(9, FechaSalida);
//Se recorre el id obtenido de la consulta preparada y se compara con el resultado obtenido de la instancia de la variable cargada con el metodo cargarTabla()
            for (int i = 0; i < mf.selectFacturaciones().size(); i++) {

                //Si el Nombre es igual al Nombre de la clase previamente cargada se prosigue
                if (IdFacturacion.equals(mf.selectFacturaciones().get(i).getNombre())) {
                    //Se extrae el resultado y se remplaza por el ID
                    this.st.setInt(10, mf.selectFacturaciones().get(i).getIdFacturacion());
                }
            }

            //Se ejecuta el Query
            this.st.execute();
            //Se cierra la conexión
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    //Método para actualizar un Cobro
    protected void updateCobros(double monto, String tipoPago, String rfc, String correo, int IdFacturacion, String usuario, String Nombre, int IdHabitacion, String FechaIngreso, String FechaSalida,String Facturacion, int id) {
        //Se instancian los resultados a utilizar para reemplazarlos en la consulta
        mtp.cargarTabla();
        mf.cargarTabla();
        try {
    
              //Se instacia la conexión a la base de datos y se declara la consulta preparada a realizar
            this.st = conectar().prepareStatement("UPDATE Cobro SET Monto=?, IdTipoPago=?, RFC=?, Correo=?, FechaCobro = GETDATE(), IdFacturacion=?, Username=?,Nombre=?,IdHabitacion=?,FechaIngreso=?,FechaSalida=?,Facturacion=? WHERE id_Cobro=?");
           
            this.st.setDouble(1, monto);
            //Se recorre el id obtenido de la consulta preparada y se compara con el resultado obtenido de la instancia de la variable cargada con el metodo cargarTabla()
            for (int i = 0; i < mtp.selectTipoPagos().size(); i++) {
                //Si el Nombre es igual al Nombre de la clase previamente cargada se prosigue
                if (tipoPago.equals(mtp.selectTipoPagos().get(i).getNombre())) {
                    //Se extrae el resultado y se remplaza por el ID
                    this.st.setInt(2, mtp.selectTipoPagos().get(i).getIdTipoPago());
                }
            }
            this.st.setString(3, rfc);
            this.st.setString(4, correo);
            this.st.setInt(6, IdFacturacion);
            this.st.setString(7, usuario);
            this.st.setString(8, Nombre);
            this.st.setInt(9, IdHabitacion);
            this.st.setString(10, FechaIngreso);
            this.st.setString(11, FechaSalida);
            this.st.setString(12, Facturacion);
            this.st.setInt(13, id);
            this.st.executeUpdate();
            //Se cierra la conexión
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCobros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

  
    protected void deleteCobros(int id) {
        try {
            //Se instacia la conexión a la base de datos y se declara la consulta preparada a realizar
            this.st = conectar().prepareStatement("DELETE FROM Cobro WHERE IdCobro = ?");
            //Se pasan los parametros a la consulta
            this.st.setInt(1, id);
            //Se ejecuta el Query
            this.st.execute();
            //Se cierra la conexión
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCobros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
