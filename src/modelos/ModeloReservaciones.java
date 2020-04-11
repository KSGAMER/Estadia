/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import ds.desktop.notify.DesktopNotify;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import objetos.ObjetoEstadoReservacion;
import objetos.ObjetoReservacion;
/**
 *
 * @author fenix
 */
//Se aplica Herencia de la clase padre BD
public class ModeloReservaciones extends BD {
    
    //Se declaran las variables de datos
    private ModeloClientes mc = new ModeloClientes();
    private ModeloHabitaciones mh = new ModeloHabitaciones();
    private ModeloEstadoReservacion mr = new ModeloEstadoReservacion();
    //Se declaran las variables de resultado y consultas preparadas
    private ResultSet rs;
    private PreparedStatement st;
    //Se declara el arreglo de tipo Objeto Reservación
    private ArrayList<ObjetoReservacion> listReservations = new ArrayList<>();

    //Método que extrae la información directamente de base de datos con pase de parametros
    protected DefaultTableModel cargarTabla(String buscar) {
        //Se cargan los datos a utilizar
        mc.cargarTabla();
        mh.cargarTabla();
        mr.cargarTabla();
        //Se declaran las columnas de la tabla
        String[] titulos = {"#", "Cliente", "Habitacion", "Fecha de Ingreso", "Fecha de Salida", "Estado"};
        //Se declara la tabla pasando las columnas
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Se declara un objeto que actuara como la fila de la tabla
        Object[] fila = new Object[6];

        try {
            //Se instancia la conexión a base de datos pasando la consulta preparada
            this.st = conectar().prepareStatement("SELECT * FROM Reservacion WHERE Nombre like CONCAT('%',?,'#') ORDER BY IdEstadoReservacion");
            //Se pasan los parametros a la consulta
            this.st.setString(1, buscar);
            //Se ejecuta el Query
            this.rs = st.executeQuery();
            //Se iteran los resultados
            while (rs.next()) {
                //Se agregan los resultados al objeto
                fila[0] = rs.getInt("IdReservacion");
                fila[1] = rs.getString("Nombre");
                //Se recorre el resultado con un for
                for (int i = 0; i < mh.selectHabitaciones().size(); i++) {
                    //Si el resultado es igual al ID de la clase se prosigue
                    if (rs.getInt("IdHabitacion") == mh.selectHabitaciones().get(i).getIdHabitacion()) {
                        //Se reemplaza el valor con el nombre
                        fila[2] = mh.selectHabitaciones().get(i).getNombre();
                    }
                }
                fila[3] = rs.getString("FechaIngreso");
                fila[4] = rs.getString("FechaSalida");
                for (ObjetoEstadoReservacion estadoReservacion : mr.selectEstadoReservacion()) {
                    if(estadoReservacion.getIdEstadoReservacion() == rs.getInt("IdEstadoReservacion")) {
                        fila[5] = estadoReservacion.getNombre();
                    }
                }
                //Se agrega el objeto a la fila
                tb.addRow(fila);
                //Se agregan los resultados del arreglo atraves de un nuevo objeto
                this.listReservations.add(new ObjetoReservacion(rs.getInt("IdReservacion"), rs.getString("Nombre"), rs.getInt("IdHabitacion"), rs.getString("FechaIngreso"), rs.getString("FechaSalida")));
            }
            //Se cierra la conexión
        } catch (SQLException ex) {
            Logger.getLogger(ModeloReservaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retorna la tabla
        return tb;
    }

    //Método que extrae la información directamente de base de datos con sobrecarga de operadores
    protected DefaultTableModel cargarTabla() {
        //Se cargan los datos a utilizar
        mc.cargarTabla();
        mh.cargarTabla();
        mr.cargarTabla();
        //Se declaran las columnas de la tabla
        String[] titulos = {"#", "Cliente", "Habitacion", "Fecha de Ingreso", "Fecha de Salida", "Estado"};
        //Se declara la tabla pasando las columnas
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Se declara un objeto que actuara como la fila de la tabla
        Object[] fila = new Object[6];

        try {
            //Se instancia la conexión a base de datos pasando la consulta preparada
            this.st = conectar().prepareStatement("SELECT * FROM Reservacion ORDER BY IdEstadoReservacion");
            //Se ejecuta el Query
            this.rs = st.executeQuery();
            //Se iteran los resultados
            while (rs.next()) {
                //Se agregan los resultados al objeto
                fila[0] = rs.getInt("IdReservacion");
                fila[1] = rs.getString("Nombre");
                //Se recorre el resultado con un for
                for (int i = 0; i < mh.selectHabitaciones().size(); i++) {
                    //Si el resultado es igual al ID de la clase se prosigue
                    if (rs.getInt("IdHabitacion") == mh.selectHabitaciones().get(i).getIdHabitacion()) {
                        //Se reemplaza el valor con el nombre
                        fila[2] = mh.selectHabitaciones().get(i).getNombre();
                    }
                }
                fila[3] = rs.getString("FechaIngreso");
                fila[4] = rs.getString("FechaSalida");
                for (ObjetoEstadoReservacion estadoReservacion : mr.selectEstadoReservacion()) {
                    if(estadoReservacion.getIdEstadoReservacion() == rs.getInt("IdEstadoReservacion")) {
                        fila[5] = estadoReservacion.getNombre();
                    }
                }
                //Se agrega el objeto a la fila
                tb.addRow(fila);
                //Se agregan los resultados del arreglo atraves de un nuevo objeto
                this.listReservations.add(new ObjetoReservacion(rs.getInt("IdReservacion"), rs.getString("Nombre"), rs.getInt("IdHabitacion"), rs.getString("FechaIngreso"), rs.getString("FechaSalida")));
            }
            //Se cierra la conexión
        } catch (SQLException ex) {
            Logger.getLogger(ModeloReservaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retorna la tabla
        return tb;
    }

    //Método que retorna la información en formato de arreglo
    protected ArrayList<ObjetoReservacion> selectReservaciones() {
        return this.listReservations;
    }

    //Método que insertar una nueva reservación
    protected void insertReservaciones(String nombre, String habitacion, String fechaIngreso, String fechaSalida, String Usuario, String estado) {
        //Se cargan los datos a utilizar
        mh.cargarTabla();
        mr.cargarTabla();
        try {
            //Se instancia la conexión a base de datos pasando la consulta preparada
            this.st = conectar().prepareStatement("INSERT INTO Reservacion(Nombre, IdHabitacion, FechaIngreso, FechaSalida, Username, IdEstadoReservacion) VALUES (?,?,?,?,?,?)");
            //Se pasan los parametros a la consulta
            this.st.setString(1, nombre);
            //Se recorre el valor con un for
            for (int i = 0; i < mh.selectHabitaciones().size(); i++) {
                //Si el valor es igual al nombre de la clase se prosigue
                if (mh.selectHabitaciones().get(i).getNombre().equals(habitacion)) {
                    //Se reemplaza el valor con el ID
                    this.st.setInt(2, mh.selectHabitaciones().get(i).getIdHabitacion());
                }
            }
            this.st.setString(3, fechaIngreso);
            this.st.setString(4, fechaSalida);
            this.st.setString(5, Usuario);
            for (ObjetoEstadoReservacion estadoReservacion : mr.selectEstadoReservacion()) {
                if(estadoReservacion.getNombre().equals(estado)) {
                    this.st.setInt(6, estadoReservacion.getIdEstadoReservacion());
                }
            }
            //Se ejecuta el Query
            this.st.execute();
            //Se cierra la conexión
            DesktopNotify.showDesktopMessage("Exito", "Datos de la reservacion agregados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrió un error al intentar ingresar los datos de la nueva reservación,"
                    + "por favor intente de nuevo o revise su conexión", DesktopNotify.ERROR);
        }
    }

    //Método que actualiza una reservación
    protected void updateReservaciones(String nombre, String habitacion, String fechaIngreso, String fechaSalida, String estado,int id) {
        //Se cargan los datos a utilizar
        mh.cargarTabla();
        mr.cargarTabla();
        try {
            //Se instancia la conexión a base de datos pasando la consulta preparada
            this.st = conectar().prepareStatement("UPDATE Reservacion SET Nombre = ?, IdHabitacion = ?, FechaIngreso = ?, FechaSalida = ?, IdEstadoReservacion = ? WHERE IdReservacion = ?");
            //Se pasan los parametros a la consulta
            this.st.setString(1, nombre);
            //Se recorre el valor con un for
            for (int i = 0; i < mh.selectHabitaciones().size(); i++) {
                //Si el valor es igual al nombre de la clase se prosigue
                if (mh.selectHabitaciones().get(i).getNombre().equals(habitacion)) {
                    //Se reemplaza el valor con el ID
                    this.st.setInt(2, mh.selectHabitaciones().get(i).getIdHabitacion());
                }
            }
            this.st.setString(3, fechaIngreso);
            this.st.setString(4, fechaSalida);
            for (ObjetoEstadoReservacion estadoReservacion : mr.selectEstadoReservacion()) {
                if(estadoReservacion.getNombre().equals(estado)) {
                    this.st.setInt(5, estadoReservacion.getIdEstadoReservacion());
                }
            }
            this.st.setInt(6, id);
            //Se ejecuta el Query
            this.st.executeUpdate();
            //Se cierra la conexión
            DesktopNotify.showDesktopMessage("Exito", "Datos de la reservacion actualizados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrió un error al intentar actualizar los datos de la reservación, por favor intente de nuevo o revise su conexión", DesktopNotify.ERROR);
        }
    }

    //Método que elimina una reservación
    protected void deleteReservaciones(int id) {
        try {
            //Se instancia la conexión a base de datos pasando la consulta preparada
            this.st = conectar().prepareStatement("DELETE FROM Reservacion WHERE IdReservacion = ?");
            //Se pasan los parametros a la consulta
            this.st.setInt(1, id);
            //Se ejecuta el Query
            this.st.execute();
            //Se cierra la conexión
          //  DesktopNotify.showDesktopMessage("Exito", "Datos de la reservacion eliminados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
         //   DesktopNotify.showDesktopMessage("Error", "Ocurrió un error al intentar eliminar  los datos de la reservación, por favor intente de nuevo o revise su conexión", DesktopNotify.ERROR);
        }
    }
}
