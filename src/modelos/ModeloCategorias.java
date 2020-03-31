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
import objetos.ObjetoCategoria;

/**
 *
 * @author KSGAMER
 */

//Se aplica herencia de la clase Padre BD
public class ModeloCategorias extends BD {

    //Variable de resultado
    private ResultSet rs;
    //Variable de consulta
    private PreparedStatement st;
    //Arreglo de tipo Objeto Categoria
    private ArrayList<ObjetoCategoria> listCategory = new ArrayList<>();

    //Método para cargar la tabla con bandera y un buscador usando sobrecarga de métodos
    protected DefaultTableModel cargarTabla(String buscar, boolean bool) {
        //Declaración de las columnas de la tabla
        String[] titulos = {"#", "Nombre", "Observaciones"};
        //Declaración de la tabla pasando las columnas de las tablas
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Declaración de la variable que funje como las filas de la tabla
        Object[] fila = new Object[3];

        try {
            //Instanciamos la conexión para realizar la consulta
            this.st = conectar().prepareStatement("SELECT * FROM Categoria WHERE Nombre like CONCAT('%', ? ,'%') or Observaciones like CONCAT('%', ? ,'%')");
            //Pasamos los parametros por los cuales deseamos filtrar
            this.st.setString(1, buscar);
            this.st.setString(2, buscar);
            //Ejecutamos el Query
            this.rs = st.executeQuery();
            //Iteramos los resultados
            while (this.rs.next()) {
                //Igualamos la bandera a false si se cumple la condición prosigue
                if (bool == false) {
                    //Si el resultado es igual a 1 prosigue filtrando los resultados con la bandera mostrar
                    if (rs.getString("Mostrar").equals("1")) {
                        //Se agregan los resultados al objeto fila
                        fila[0] = rs.getInt("IdCategoria");
                        fila[1] = rs.getString("Nombre");
                        fila[2] = rs.getString("Observaciones");
                        //Se agrega el objeto fila a la tabla
                        tb.addRow(fila);
                        //Se agregan los resultados al Arreglo de tipo objeto
                        this.listCategory.add(new ObjetoCategoria(rs.getInt("IdCategoria"), rs.getString("Nombre"), rs.getString("Observaciones"), rs.getString("Mostrar")));
                    }
                    //En caso contrario de que la bandera sea verdadera prosigue
                } else {
                    //Se agregan los resultados al objeto fila
                    fila[0] = rs.getInt("IdCategoria");
                    fila[1] = rs.getString("Nombre");
                    fila[2] = rs.getString("Observaciones");
                    //Se agrega el objeto fila a la tabla
                    tb.addRow(fila);
                    //Se agregan los resultados al Arreglo de tipo objeto
                    this.listCategory.add(new ObjetoCategoria(rs.getInt("IdCategoria"), rs.getString("Nombre"), rs.getString("Observaciones"), rs.getString("Mostrar")));
                }
            }
            //Se cierra la conexión
        } catch (SQLException ex) {

        }
        //Se retorna la tabla
        return tb;
    }

    //Método para cargar una tabla usando sobrecarga de Métodos
    protected DefaultTableModel cargarTabla() {
        //Declaración de las columnas de la tabla
        String[] titulos = {"#", "Nombre", "Observaciones"};
        //Declaración de la tabla pasando las columnas de la tabla
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Declaramos un variable tipo objeto con una longitud de 3
        Object[] fila = new Object[3];
        try {
            //Instanciamos la conexión de la base de datos y realizando la consulta
            this.st = conectar().prepareStatement("SELECT * FROM Categoria");
            //Ejecutamos el Query y guardamos los resultados en la variable rs
            this.rs = st.executeQuery();
            //Iteramos los resultados
            while (this.rs.next()) {
                //Si el resultado es igual a 1 prosigue filtrando los resultados con la bandera mostrar
                if (rs.getString("Mostrar").equals("1")) {
                    //Se agregan los resultados al objeto fila
                    fila[0] = rs.getInt("IdCategoria");
                    fila[1] = rs.getString("Nombre");
                    fila[2] = rs.getString("Observaciones");
                    //Se agrega el objeto fila a la tabla
                    tb.addRow(fila);
                    //Se agregan los resultados al Arreglo de tipo objeto
                    this.listCategory.add(new ObjetoCategoria(rs.getInt("IdCategoria"), rs.getString("Nombre"), rs.getString("Observaciones"), rs.getString("Mostrar")));
                }
            }
            //Se cierra la conexión
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retorna la tabla
        return tb;
    }

    //Método que retorna el arreglo previamente cargado
    protected ArrayList<ObjetoCategoria> selectCategorias() {
        //Se retorna el arreglo con los resultados almacenados dentro de el
        return this.listCategory;
    }

    //Método para insertar una nueva categoria
    //Parametros nombre -> Nombre de la categoria, observaciones -> Texto de las observaciones de la categoria (Se puede dejar en blanco)
    protected void insertarCategorias(String nombre, String observaciones) {
        try {
            //Instaciamos la conexión a la base de datos y realizamos una consulta preparada
            this.st = conectar().prepareStatement("INSERT INTO Categoria(Nombre, Observaciones, Mostrar) VALUES(?,?, 1)");
            //Pasamos los parametros como se vayan requiriendo y en el orden especificado en la consulta
            this.st.setString(1, nombre);
            this.st.setString(2, observaciones);
            //Ejcutamos el Query
            this.st.execute();
            //Cerramos la conexión
            //Notificación de éxito cuando la consulta no haya provado algun error inesperado
            DesktopNotify.showDesktopMessage("Exito", "Los datos de la categoria han sido insertados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            //Notificación de error cuando la consulta haya provado algun error inesperado
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar insertar los datos de la categoria, intentelo nuevamente y asegurese de que todos los campos obligatorios este llenos", DesktopNotify.ERROR);
        }
    }

    //Método para actualizar alguna categoria
    //Parametros nombre -> Nombre de la categoria, observaciones -> Texto de las observaciones de la categoria (Se puede dejar en blanco), id -> Identificador de categoria
    protected void updateCategorias(String nombre, String observaciones, int id) {
        try {
            //Instanciamos la conexión a la base de datos y realizamos una consulta preparada
            this.st = conectar().prepareStatement("UPDATE Categoria SET Nombre = ?, Observaciones = ? WHERE IdCategoria = ?");
            //Pasamos los parametros como se vayan requiriendo y en el orden especificado en la consulta
            this.st.setString(1, nombre);
            this.st.setString(2, observaciones);
            this.st.setInt(3, id);
            //Ejecutamos la consulta
            this.st.executeUpdate();
            //Cerramos la conexión
            //Notificación de éxito cuando la consulta no haya provado algun error inesperado
            DesktopNotify.showDesktopMessage("Exito", "Los datos de la categoria han sido actualizados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            //Notificación de error cuando la consulta haya provocado algun error inesperado
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar actualizar los datos de la categoria, intentelo nuevamente y asegurese de que todos los campos obligatorios este llenos", DesktopNotify.ERROR);
        }
    }

    //Método para "eliminar" alguna categoria (cambio en el estado de la Bandera)d
    protected void deleteCategorias(int id) {
        try {
            //Instanciamos la conexión y realizamos la consulta preparada
            this.st = conectar().prepareStatement("UPDATE Categoria SET Mostrar = 0 WHERE IdCategoria = ?");
            //Pasamos los parametros como se vayan requiriendo y en el orden especificado en la consulta
            this.st.setInt(1, id);
            //Ejcutamos la consulta
            this.st.executeUpdate();
            //Cerramos la conexión
            //Notificación de éxito cuando la consulta no haya provado algun error inesperado
            DesktopNotify.showDesktopMessage("Exito", "Los datos de la categoria han sido eliminados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            //Notificación de error cuando la consulta haya provado algun error inesperado
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar eliminar los datos de la categoria, una categoria previamente utilizada no puede ser eliminada", DesktopNotify.ERROR);
        }
    }
}
