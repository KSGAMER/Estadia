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
import objetos.ObjetoPiso;

/**
 *
 * @author KSGAMER
 */

//Se aplica Herencia de la clase padre BD
public class ModeloPisos extends BD{
    //Se declaran las variables de resultado y de consultas prepadaras
    private ResultSet rs;
    private PreparedStatement st;
    //Se declara un arreglo de tipo objeto Piso
    private ArrayList<ObjetoPiso> listBackground = new ArrayList<>();
    
    //Método que extrae la información directamente de base de datos con pase de parametros
    protected DefaultTableModel cargarTabla(String buscar) {
        //Se declaran las columnas de las tablas
        String[] titulos = {"#", "Nombre", "Observaciones"};
        //Se declara la tabla pasando las columnas
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Se crea un objeto que actuara como la fila de la tabla
        Object[] fila = new Object[3];

        try {
            //Se instancia la conexión a base de datos pasando la consulta preparada
            this.st = conectar().prepareStatement("SELECT * FROM Piso WHERE Nombre like CONCAT('%', ? ,'%') or Observaciones like CONCAT('%', ? ,'%')");
            //Se pasan los parametros a la consulta
            this.st.setString(1, buscar);
            this.st.setString(2, buscar);
            //Se ejecuta el Query
            this.rs = st.executeQuery();
            //Se iteran los resultados
            while (this.rs.next()) {
                //Se agregan los resultados al objeto
                fila[0] = rs.getInt("IdPiso");
                fila[1] = rs.getString("Nombre");
                fila[2] = rs.getString("Observaciones");
                //Se agrega el objeto a la tabla
                tb.addRow(fila);
                //Se agregan los resltados al arreglo atraves de un nuevo objeto
                this.listBackground.add(new ObjetoPiso(rs.getInt("IdPiso"), rs.getString("Nombre"), rs.getString("Observaciones")));
            }
            //Se cierra la conexión
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retornta la tabla
        return tb;
    }
    
    //Método que extrae la información directamente de base de datos
    protected DefaultTableModel cargarTabla() {
        //Se declaran las columnas de la tabla
        String[] titulos = {"#", "Nombre", "Observaciones"};
        //Se declara la tabla pasan las columnas
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Se declara un objeto que actuara como la fila de la tabla
        Object[] fila = new Object[3];

        try {
            //Se instancia la conexión a base de datos pasando la consulta preparada
            this.st = conectar().prepareStatement("SELECT * FROM Piso");
            //Se ejecuta el Query
            this.rs = st.executeQuery();
            //Se iteran los resultados
            while (this.rs.next()) {
                //Se agregan los resultados al objeto
                fila[0] = rs.getInt("IdPiso");
                fila[1] = rs.getString("Nombre");
                fila[2] = rs.getString("Observaciones");
                //Se agrega el objeto a la tabla
                tb.addRow(fila);
                //Se agregan los resultados al arreglo atraves de un nuevo objeto
                this.listBackground.add(new ObjetoPiso(rs.getInt("IdPiso"), rs.getString("Nombre"), rs.getString("Observaciones")));
            }
            //Se cierra la conexión
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retorna la conexión
        return tb;
    }
    
    //Método que retorna la información obtenida en formato de arreglo
    protected ArrayList<ObjetoPiso> selectPisos() {
        return this.listBackground;
    }
    
    //Método que inserta un nuevo Piso
    protected void insertPisos(String nombre, String observaciones) {
        try {
            //Se instancia la conexión a base de datos pasando la consulta preparada
            this.st = conectar().prepareStatement("INSERT INTO Piso(Nombre, Observaciones) VALUES (?,?)");
            //Se pasan los parametros
            this.st.setString(1, nombre);
            this.st.setString(2, observaciones);
            //Se ejecuta el Query
            this.st.execute();
            //Se cierra la conexión
            conectar().close();
            DesktopNotify.showDesktopMessage("Exito", "Los datos del piso han sido insertados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar insertar los datos del piso, intentelo nuevamente y asegurese de que todos los campos obligatorios este llenos", DesktopNotify.ERROR);
        }
    }
    
    //Método que actualza un Piso
    protected void updatePisos(String nombre, String observaciones, int id) {
        try {
            //Se instancia la conexión a base de datos pasando la consulta preparada
            this.st = conectar().prepareStatement("UPDATE Piso SET Nombre = ?, Observaciones = ? WHERE IdPiso = ?");
            //Se pasan los parametros a la consulta
            this.st.setString(1, nombre);
            this.st.setString(2, observaciones);
            this.st.setInt(3, id);
            //Se ejecuta el Query
            this.st.executeUpdate();
            //Se cierra la conexión
            conectar().close();
            DesktopNotify.showDesktopMessage("Exito", "Los datos del piso han sido actualizados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar actualizar los datos del piso, intentelo nuevamente y asegurese de que todos los campos obligatorios este llenos", DesktopNotify.ERROR);
        }
    }
    
    //Método que elimina un Piso
    protected void deletePisos(int id) {
        try {
            //Se instancia la conexión a base de datos pasando la consulta preparada
            this.st = conectar().prepareStatement("DELETE FROM Piso WHERE IdPiso = ?");
            //Se pasan los parametros a la consulta
            this.st.setInt(1, id);
            //Se ejecuta el Query
            this.st.execute();
            //Se cierra la conexión
            conectar().close();
            DesktopNotify.showDesktopMessage("Exito", "Los datos del piso han sido eliminados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error al intentar eliminar los datos del piso, un piso previamente utilizado no puede ser eliminado", DesktopNotify.ERROR);
        }
    }
}
