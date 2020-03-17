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
import objetos.ObjetoHabitacion;

/**
 *
 * @author KSGAMER
 */

//Se aplica Herencia de la clase padre BD
public class ModeloHabitaciones extends BD {
    //Se declaran las instancias de los datos a utilizar
    private ModeloCategorias cc = new ModeloCategorias();
    private ModeloPisos cp = new ModeloPisos();
    private ModeloEstatusHabitaciones meh = new ModeloEstatusHabitaciones();
    //Se declaran las variables de resultado y de consultas preparadas
    private ResultSet rs;
    private PreparedStatement st;
    //Se declara un arreglo de tipo Objeto Habitación
    private ArrayList<ObjetoHabitacion> listRoom = new ArrayList<>();

    //Método que extrae la información diretamente de base de datos pasando un parametro
    protected DefaultTableModel cargarTabla(String buscar) {
        //Se cargan los resultados a utilizar
        cc.cargarTabla();
        cp.cargarTabla();
        meh.cargarTabla();
        //Se declaran los titulos de la tabla
        String[] titulos = {"#", "Nombre", "Piso", "Categoria", "Precio Sugerido", "Caracteriscas", "Estatus"};
        //Se declara la tabla pasando las columnas de la tabla
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Se declara un objeto que actuara como la fila de la tabla
        Object[] fila = new Object[7];

        try {
            //Se instancia la conexión a la base de datos pasando la consulta preparada
            this.st = conectar().prepareStatement("SELECT * FROM Habitacion WHERE Nombre like CONCAT('%', ? ,'%') or PrecioSugerido like CONCAT('%', ? ,'%') or Caracteristicas like CONCAT('%', ?, '%')");
            //Se pasan los parametros a la consulta preparada
            this.st.setString(1, buscar);
            this.st.setString(2, buscar);
            this.st.setString(3, buscar);
            //Se ejecuta el Query
            this.rs = st.executeQuery();
            //Se iteran los resultados de la consulta
            while (this.rs.next()) {
                //Se agregan los resultados al objeto
                fila[0] = rs.getInt("IdHabitacion");
                fila[1] = rs.getString("Nombre");
                //Se recorre el resultado obtenido con un for
                for (int i = 0; i < cp.selectPisos().size(); i++) {
                    //Si el ID del resultado obtenido es igual al ID de la instancia se prosigue
                    if (cp.selectPisos().get(i).getIdPiso() == rs.getInt("IdPiso")) {
                        //Se reemplaza el resultado por el nombre
                        fila[2] = cp.selectPisos().get(i).getNombre();
                    }
                }
                //Se recorre el resultado obtenido con un for
                for (int i = 0; i < cc.selectCategorias().size(); i++) {
                    //Si el ID del resultado obtenido es igual al ID de la instancia se prosigue
                    if (cc.selectCategorias().get(i).getIdCategoria() == rs.getInt("IdCategoria")) {
                        //Se reemplaza el resultado por el nombre
                        fila[3] = cc.selectCategorias().get(i).getNombre();
                    }
                }
                fila[4] = rs.getDouble("PrecioSugerido");
                fila[5] = rs.getString("Caracteristicas");
                //Se recorre el resultado obtenido con un for
                for (int i = 0; i < meh.selectEstadoHabitaciones().size(); i++) {
                    //Si el ID del resultado obtenido es igual al ID de la instancia se prosigue
                    if (meh.selectEstadoHabitaciones().get(i).getIdEstadoHabitacion() == rs.getInt("IdEstadoHabitacion")) {
                        //Se reemplaza el resultado por el nombre
                        fila[6] = meh.selectEstadoHabitaciones().get(i).getNombre();
                    }
                }
                //Se agrega el objeto a la tabla
                tb.addRow(fila);
                //Se agregan los resultados al arreglo atraves de un nuevo objeto
                this.listRoom.add(new ObjetoHabitacion(rs.getInt("IdHabitacion"), rs.getString("Nombre"), rs.getInt("IdPiso"), rs.getInt("IdCategoria"), rs.getDouble("PrecioSugerido"), rs.getString("Caracteristicas"), rs.getInt("IdEstadoHabitacion")));
            }
            //Se cierra la conexión
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retona la tabla
        return tb;
    }

    //Método que extrae la información directamente de base de datos
    protected DefaultTableModel cargarTabla() {
        //Se cargan los resultados a utilizar
        cc.cargarTabla();
        cp.cargarTabla();
        meh.cargarTabla();
        //Se declaran las columnas de la tabla
        String[] titulos = {"#", "Nombre", "Piso", "Categoria", "Precio Sugerido", "Caracteriscas", "Estatus"};
        //Se declara la tabla pasando las columnas
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Se declara un objeto que actuara como la fila de la tabla
        Object[] fila = new Object[7];

        try {
            //Se instancia la conexión a base de datos y se pasa la consulta preparada
            this.st = conectar().prepareStatement("SELECT * FROM Habitacion");
            //Se ejecuta el Query
            this.rs = st.executeQuery();
            //Se iteran los resultados
            while (this.rs.next()) {
                //Se agregan los resultados al objeto
                fila[0] = rs.getInt("IdHabitacion");
                fila[1] = rs.getString("Nombre");
                //Se recorre el resultado obtenido con un for
                for (int i = 0; i < cp.selectPisos().size(); i++) {
                    //Si el ID del resultado obtenido es igual al ID de la instancia se prosigue
                    if (cp.selectPisos().get(i).getIdPiso() == rs.getInt("IdPiso")) {
                        //Se reemplaza el resultado por el nombre
                        fila[2] = cp.selectPisos().get(i).getNombre();
                    }
                }
                //Se recorre el resultado obtenido con un for
                for (int i = 0; i < cc.selectCategorias().size(); i++) {
                    //Si el ID del resultado obtenido es igual al ID de la instancia se prosigue
                    if (cc.selectCategorias().get(i).getIdCategoria() == rs.getInt("IdCategoria")) {
                        //Se reemplaza el resultado por el nombre
                        fila[3] = cc.selectCategorias().get(i).getNombre();
                    }
                }
                fila[4] = rs.getDouble("PrecioSugerido");
                fila[5] = rs.getString("Caracteristicas");
                //Se recorre el resultado obtenido con un for
                for (int i = 0; i < meh.selectEstadoHabitaciones().size(); i++) {
                    //Si el ID del resultado obtenido es igual al ID de la instancia se prosigue
                    if (meh.selectEstadoHabitaciones().get(i).getIdEstadoHabitacion() == rs.getInt("IdEstadoHabitacion")) {
                        //Se reemplaza el resultado por el nombre
                        fila[6] = meh.selectEstadoHabitaciones().get(i).getNombre();
                    }
                }
                //Se agrega el objeto a la tabla
                tb.addRow(fila);
                //Se agregan los resultados al arreglo atraves de un nuevo objeto
                this.listRoom.add(new ObjetoHabitacion(rs.getInt("IdHabitacion"), rs.getString("Nombre"), rs.getInt("IdPiso"), rs.getInt("IdCategoria"), rs.getDouble("PrecioSugerido"), rs.getString("Caracteristicas"), rs.getInt("IdEstadoHabitacion")));
            }
            //Se cierra la conexión
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retorna la tabla
        return tb;
    }

    //Método que devuelve la información en formato de arreglo
    protected ArrayList<ObjetoHabitacion> selectHabitaciones() {
        return this.listRoom;
    }

    //Método que inserta una nueva habitación
    protected void insertHabitaciones(String nombre, String piso, String categoria, double precioSugerido, String caracteristicas, String estatus) {
        //Se cargan los resultados a utilizar
        cc.cargarTabla();
        cp.cargarTabla();
        meh.cargarTabla();
        try {
            //Se instancia la conexión a base de datos y se pasa la consulta preparada
            this.st = conectar().prepareStatement("INSERT INTO Habitacion(Nombre, IdPiso, IdCategoria, PrecioSugerido, Caracteristicas, IdEstadoHabitacion) VALUES (?,?,?,?,?,?)");
            //Se pasan los parametros a la consulta preparada
            this.st.setString(1, nombre);
            //Se recorre el resultado obtenido con un for
            for (int i = 0; i < cp.selectPisos().size(); i++) {
                //Si el Nombre del resultado obtenido es igual al Nombre de la instancia se prosigue
                if (piso.equals(cp.selectPisos().get(i).getNombre())) {
                    //Se reemplaza el resultado por el ID
                    this.st.setInt(2, cp.selectPisos().get(i).getIdPiso());
                }
            }
            //Se recorre el resultado obtenido con un for
            for (int i = 0; i < cc.selectCategorias().size(); i++) {
                //Si el Nombre del resultado obtenido es igual al Nombre de la instancia se prosigue
                if (categoria.equals(cc.selectCategorias().get(i).getNombre())) {
                    //Se reemplaza el resultado por el ID
                    this.st.setInt(3, cc.selectCategorias().get(i).getIdCategoria());
                }
            }
            this.st.setDouble(4, precioSugerido);
            this.st.setString(5, caracteristicas);
            //Se recorre el resultado obtenido con un for
            for (int i = 0; i < meh.selectEstadoHabitaciones().size(); i++) {
                //Si el Nombre del resultado obtenido es igual al Nombre de la instancia se prosigue
                if (estatus.equals(meh.selectEstadoHabitaciones().get(i).getNombre())) {
                    //Se reemplaza el resultado por el ID
                    this.st.setInt(6, meh.selectEstadoHabitaciones().get(i).getIdEstadoHabitacion());
                }
            }
            //Se ejecuta el Query
            this.st.execute();
            //Se cierra la conexión
            conectar().close();
            DesktopNotify.showDesktopMessage("Exito", "Los datos de la habitación han sido insertados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar insertar los datos de la habitación, intentelo nuevamente y asegurese de que todos los campos obligatorios este llenos", DesktopNotify.ERROR);
        }
    }

    //Método que actualiza una Habitación
    protected void updateHabitaciones(String nombre, String piso, String categoria, double precioSugerido, String caracteristicas, String estatus, int id) {
        //Se cargan los resultados a utilizar
        cc.cargarTabla();
        cp.cargarTabla();
        meh.cargarTabla();
        try {
            //Se instancia la conexión a base de datos y se pasa la consulta preparada
            this.st = conectar().prepareStatement("UPDATE Habitacion SET Nombre = ?, IdPiso = ?, IdCategoria = ?, PrecioSugerido = ?, Caracteristicas = ?, IdEstadoHabitacion = ? WHERE IdHabitacion = ?");
            //Se pasan los parametros a la consulta preparada
            this.st.setString(1, nombre);
            //Se recorre el resultado obtenido con un for
            for (int i = 0; i < cp.selectPisos().size(); i++) {
                //Si el Nombre del resultado obtenido es igual al Nombre de la instancia se prosigue
                if (piso.equals(cp.selectPisos().get(i).getNombre())) {
                    //Se reemplaza el resultado por el ID
                    this.st.setInt(2, cp.selectPisos().get(i).getIdPiso());
                }
            }
            //Se recorre el resultado obtenido con un for
            for (int i = 0; i < cc.selectCategorias().size(); i++) {
                //Si el Nombre del resultado obtenido es igual al Nombre de la instancia se prosigue
                if (categoria.equals(cc.selectCategorias().get(i).getNombre())) {
                    //Se reemplaza el resultado por el ID
                    this.st.setInt(3, cc.selectCategorias().get(i).getIdCategoria());
                }
            }
            this.st.setDouble(4, precioSugerido);
            this.st.setString(5, caracteristicas);
            //Se recorre el resultado obtenido con un for
            for (int i = 0; i < meh.selectEstadoHabitaciones().size(); i++) {
                //Si el Nombre del resultado obtenido es igual al Nombre de la instancia se prosigue
                if (estatus.equals(meh.selectEstadoHabitaciones().get(i).getNombre())) {
                    //Se reemplaza el resultado por el ID
                    this.st.setInt(6, meh.selectEstadoHabitaciones().get(i).getIdEstadoHabitacion());
                }
            }
            this.st.setInt(7, id);
            //Se ejecuta el Query
            this.st.executeUpdate();
            //Se cierra la conexión
            conectar().close();
            DesktopNotify.showDesktopMessage("Exito", "Los datos de la habitación han sido actualizados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar actualizar los datos de la habitación, intentelo nuevamente y asegurese de que todos los campos obligatorios este llenos", DesktopNotify.ERROR);
        }
    }

    //Método que actualiza el estado de una Habitación
    protected void updateHabitaciones(String habitacion, String estatusHabitacion) {
        //Se cargan los resultados a utilizar
        cargarTabla();
        meh.cargarTabla();
        try {
            //Se instancia la conexión a base de datos y se pasa la consulta preparada
            this.st = conectar().prepareStatement("UPDATE Habitacion SET IdEstadoHabitacion = ? WHERE IdHabitacion = ?");
            //Se pasan los parametros a la consulta preparada
            //Se recorre el resultado obtenido con un for
            for (int i = 0; i < this.selectHabitaciones().size(); i++) {
                //Si el Nombre del resultado obtenido es igual al Nombre de la instancia se prosigue
                if (this.selectHabitaciones().get(i).getNombre().equals(habitacion)) {
                    //Se reemplaza el resultado por el ID
                    this.st.setInt(1, this.selectHabitaciones().get(i).getIdHabitacion());
                }
            }
            //Se recorre el resultado obtenido con un for
            for (int i = 0; i < meh.selectEstadoHabitaciones().size(); i++) {
                //Si el Nombre del resultado obtenido es igual al Nombre de la instancia se prosigue
                if (meh.selectEstadoHabitaciones().get(i).getNombre().equals(estatusHabitacion)) {
                    //Se reemplaza el resultado por el ID
                    this.st.setInt(2, meh.selectEstadoHabitaciones().get(i).getIdEstadoHabitacion());
                }
            }
            //Se ejecuta el Query
            this.st.executeUpdate();
            //Se cierra la conexión
            conectar().close();
            DesktopNotify.showDesktopMessage("Exito", "Los datos de la habitación han sido actualizados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar actualizar los datos de la habitación, intentelo nuevamente y asegurese de que todos los campos obligatorios este llenos", DesktopNotify.ERROR);
        }
    }
    
    //Método que actualiza el estado de una Habitación
    public void updateHabitaciones(int idHabitacion, String estatus) {
        //Se cargan los resultados a utilizar
        cargarTabla();
        meh.cargarTabla();
        try {
            //Se instancia la conexión a base de datos y se pasa la consulta preparada
            this.st = conectar().prepareStatement("UPDATE Habitacion SET IdEstadoHabitacion = ? WHERE IdHabitacion = ?");
            //Se pasan los parametros a la consulta preparada
            this.st.setInt(2, idHabitacion);
            //Se recorre el resultado obtenido con un for
            for (int i = 0; i < meh.selectEstadoHabitaciones().size(); i++) {
                //Si el Nombre del resultado obtenido es igual al Nombre de la instancia se prosigue
                if (meh.selectEstadoHabitaciones().get(i).getNombre().equals(estatus)) {
                    //Se reemplaza el resultado por el ID
                    this.st.setInt(1, meh.selectEstadoHabitaciones().get(i).getIdEstadoHabitacion());
                }
            }
            //Se ejecuta el Query
            this.st.executeUpdate();
            //Se cierra la conexión
            conectar().close();
            DesktopNotify.showDesktopMessage("Exito", "Los datos de la habitación han sido actualizados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar actualizar los datos de la habitación, intentelo nuevamente y asegurese de que todos los campos obligatorios este llenos", DesktopNotify.ERROR);
        }
    }

    //Método que elimina una Habitación
    protected void deleteHabitaciones(int id) {
        try {
            //Se instancia la conexión a base de datos y se pasa la consulta preparada
            this.st = conectar().prepareStatement("DELETE FROM Habitacion WHERE IdHabitacion = ?");
            //Se pasan los parametros a la consulta preparada
            this.st.setInt(1, id);
            //Se ejecuta el Query
            this.st.execute();
            //Se cierra la conexión
            conectar().close();
            DesktopNotify.showDesktopMessage("Exito", "Los datos de la habitación han sido eliminados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar eliminar los datos de la habitación, una habitación previamente utilizada no puede ser eliminada", DesktopNotify.ERROR);
        }
    }

}
