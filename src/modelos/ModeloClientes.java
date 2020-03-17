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
import objetos.ObjetoCliente;

/**
 *
 * @author KSGAMER
 */

//Se aplica Herencia de la clase Padre BD
public class ModeloClientes extends BD {

    //Declaración de las variables a utilizar
    private ModeloCFDI cf = new ModeloCFDI(); // Variable para extracción de la información generada en la clase ModeloCFDI
    private ResultSet rs; //Variable para guardar valores extraidos de una consulta
    private PreparedStatement st; //Variable para realizar consultas prepadas

    private ArrayList<ObjetoCliente> listClient = new ArrayList<>(); // Variable para guardar resultados y usar en las diferentes clases del proyecto

    //Método para cargar una tabla pasando un parametro usando sobrecarga de operadores
    protected DefaultTableModel cargarTabla(String buscar) {
        //Precargamos los valores a utilizar
        cf.cargarTabla();
        //Declaramos las columnas de la tabla
        String[] titulos = {"#", "Nombre", "RFC", "Dirección", "Telefono", "Email", "CFDI"};
        //Declaramos una variable tabla y pasamos los titulos de las columnas
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Declaramos una variable de tipo objeto que actuara como nuestra fila
        Object[] fila = new Object[7];

        try {
            //Instanciamos la conexión a la base de datos y realizamos una consulta preparada
            this.st = conectar().prepareStatement("SELECT * FROM Cliente WHERE Nombre like CONCAT('%', ? ,'%') or RFC like CONCAT('%', ? ,'%') or Direccion like CONCAT('%', ? ,'%') or telefono like CONCAT('%', ? ,'%') or Email like CONCAT('%', ? ,'%')");
            //Pasamos los parametros previamente especificados y en el orden requerido en la consulta
            this.st.setString(1, buscar);
            this.st.setString(2, buscar);
            this.st.setString(3, buscar);
            this.st.setString(4, buscar);
            this.st.setString(5, buscar);
            //Ejecutamos el Query
            this.rs = st.executeQuery();
            //Iteramos los resultados obtenidos de la consulta
            while (this.rs.next()) {
                //Agregamos los resultados de la consulta al objeto fila
                fila[0] = rs.getInt("IdCliente");
                fila[1] = rs.getString("Nombre");
                fila[2] = rs.getString("RFC");
                fila[3] = rs.getString("Direccion");
                fila[4] = rs.getString("Telefono");
                fila[5] = rs.getString("Email");
                //Realizamos un recorridos de los valores obtenidos al ejecutar el método cargarTabla()
                for (int i = 0; i < cf.selectCFDIs().size(); i++) {
                    //Realizamos una comparación donde el Identificador es igual Identificador del metodo cargarTabla()
                    if (rs.getInt("IdCFDI") == cf.selectCFDIs().get(i).getIdCFDI()) {
                        //Si se cumple la condición obtenemos en el nombre del CFDI y lo metemos al objeto fila
                        fila[6] = cf.selectCFDIs().get(i).getNombre();
                    }
                }
                //El objeto fila se agrega como fila de la tabla previamente creada
                tb.addRow(fila);
                //Se agregan los resultados al Array
                this.listClient.add(new ObjetoCliente(rs.getInt("IdCliente"), rs.getString("Nombre"), rs.getString("RFC"), rs.getString("Direccion"), rs.getString("Telefono"), rs.getString("Email"), rs.getInt("IdCFDI")));
            }
            //Se cierra la conexión
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retorna la tabla
        return tb;
    }
    
    //Método para cargar una tabla usando sobrecarga de operadores
    protected DefaultTableModel cargarTabla() {
        //Precargamos los valores a utilizar
        cf.cargarTabla();
        //Declaramos las columnas de la tabla
        String[] titulos = {"#", "Nombre", "RFC", "Dirección", "Telefono", "Email", "CFDI"};
        //Declaramos una variable tabla y pasamos los titulos de las columnas
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Declaramos una variable de tipo objeto que actuara como nuestra fila
        Object[] fila = new Object[7];

        try {
            //Instanciamos la conexión a la base de datos y realizamos una consulta preparada
            this.st = conectar().prepareStatement("SELECT * FROM Cliente");
            //Ejecutamos el Query
            this.rs = st.executeQuery();

            while (this.rs.next()) {
                //Agregamos los resultados de la consulta al objeto fila
                fila[0] = rs.getInt("IdCliente");
                fila[1] = rs.getString("Nombre");
                fila[2] = rs.getString("RFC");
                fila[3] = rs.getString("Direccion");
                fila[4] = rs.getString("Telefono");
                fila[5] = rs.getString("Email");
                //Realizamos un recorridos de los valores obtenidos al ejecutar el método cargarTabla()
                for (int i = 0; i < cf.selectCFDIs().size(); i++) {
                    //Realizamos una comparación donde el Identificador es igual Identificador del metodo cargarTabla()
                    if (rs.getInt("IdCFDI") == cf.selectCFDIs().get(i).getIdCFDI()) {
                        //Si se cumple la condición obtenemos en el nombre del CFDI y lo metemos al objeto fila
                        fila[6] = cf.selectCFDIs().get(i).getNombre();
                    }
                }
                //El objeto fila se agrega como fila de la tabla previamente creada
                tb.addRow(fila);
                //Se agregan los resultados al Array
                this.listClient.add(new ObjetoCliente(rs.getInt("IdCliente"), rs.getString("Nombre"), rs.getString("RFC"), rs.getString("Direccion"), rs.getString("Telefono"), rs.getString("Email"), rs.getInt("IdCFDI")));
            }
            //Se cierra la conexión
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retorna la tabla
        return tb;
    }

    //Método que retorna un arreglo previamente cargado con los valores extraidos de base de datos
    protected ArrayList<ObjetoCliente> selectClientes() {
        //Se retorna el arreglo
        return this.listClient;
    }

    //Método que inserta un cliente
    //Parametros nombre -> Nombre del cliente, rfc -> RFC del cliente, dirección -> Dirección del cliente, telefono -> Télefono del cliente, email -> Email del cliente, CFDI -> CFDI del cliente con el que se le facturará
    protected void insertClientes(String nombre, String rfc, String direccion, String telefono, String email, String CFDI) {
        //Precargamos los valores a utilizar
        cf.cargarTabla();
        try {
            //Instanciamos la conexión a la base de datos y realizamos una consulta preparada
            this.st = conectar().prepareStatement("INSERT INTO Cliente (Nombre, RFC, Direccion, Telefono, Email, IdCFDI) VALUES (?,?,?,?,?,?)");
            //Pasamos los parametros previamente especificados y en el orden requerido en la consulta
            this.st.setString(1, nombre);
            this.st.setString(2, rfc);
            this.st.setString(3, direccion);
            this.st.setString(4, telefono);
            this.st.setString(5, email);
            //Realizamos un recorridos de los valores obtenidos al ejecutar el método cargarTabla()
            for (int i = 0; i < cf.selectCFDIs().size(); i++) {
                //Realizamos una comparación donde el Identificador extraido de base de datos es igual al Identificador extrado del metodo cargarTabla() si estos coinciden se prosigue
                if (CFDI.equals(cf.selectCFDIs().get(i).getNombre())) {
                    //Se pasa el Identificador del CFDI correspondiente
                    this.st.setInt(6, cf.selectCFDIs().get(i).getIdCFDI());
                }
            }
            //Se ejecuta la consulta
            this.st.execute();
            //Se cierra la conexión
            conectar().close();
            //Notificación de éxito si este no ha generado ningun problema
            DesktopNotify.showDesktopMessage("Exito", "Los datos del cliente han sido insertados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            //Notificación de error si este ha generado algun problema
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar insertar los datos del cliente, intentelo nuevamente y asegurese de que todos los campos obligatorios este llenos", DesktopNotify.ERROR);
        }
    }

    //Método que actualiza los datos de un cliente con pase de parametros
    protected void updateClientes(String nombre, String rfc, String direccion, String telefono, String email, String CFDI, int id) {
        //Precargamos los valores a utilizar
        cf.cargarTabla();
        try {
            //Instanciamos la conexión a la base de datos y realizamos una consulta preparada
            this.st = conectar().prepareStatement("UPDATE Cliente SET Nombre = ?, RFC = ?, Direccion = ?, Telefono = ?, Email = ?, IdCFDI = ? WHERE IdCliente = ?");
            //Pasamos los parametros previamente especificados y en el orden requerido en la consulta
            this.st.setString(1, nombre);
            this.st.setString(2, rfc);
            this.st.setString(3, direccion);
            this.st.setString(4, telefono);
            this.st.setString(5, email);
            //Realizamos un recorridos de los valores obtenidos al ejecutar el método cargarTabla()
            for (int i = 0; i < cf.selectCFDIs().size(); i++) {
                //Realizamos una comparación donde el Identificador extraido de base de datos es igual al Identificador extrado del metodo cargarTabla() si estos coinciden se prosigue
                if (CFDI.equals(cf.selectCFDIs().get(i).getNombre())) {
                    //Se pasa el Identificador del CFDI correspondiente
                    this.st.setInt(6, cf.selectCFDIs().get(i).getIdCFDI());
                }
            }
            this.st.setInt(7, id);
            //Se ejecuta el Query
            this.st.executeUpdate();
            //Se cierra la conexión
            conectar().close();
            //Notificación de éxito si este no ha generado ningun problema
            DesktopNotify.showDesktopMessage("Exito", "Los datos del cliente han sido actualizados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            //Notificación de error si este ha generado algun problema
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar actualizar los datos del cliente, intentelo nuevamente y asegurese de que todos los campos obligatorios este llenos", DesktopNotify.ERROR);
        }
    }

    //Método para eliminar los datos de un cliente con pase de parametros
    protected void deleteClientes(int id) {
        try {
            //Instanciamos la conexión a la base de datos y realizamos una consulta preparada
            this.st = conectar().prepareStatement("DELETE FROM Cliente WHERE IdCliente = ?");
            //Pasamos los parametros previamente especificados y en el orden requerido en la consulta
            this.st.setInt(1, id);
            //Se ejecuta el Query
            this.st.execute();
            //Se cierra la conexión
            conectar().close();
            //Notificación de éxito si este no ha generado ningun problema
            DesktopNotify.showDesktopMessage("Exito", "Los datos del cliente han sido eliminados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            //Notificación de error si este ha generado algun problema
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar eliminar los datos del cliente, un cliente previamente utilizada no puede ser eliminada", DesktopNotify.ERROR);
        }
    }
}
