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
import objetos.ObjetoCFDI;

/**
 *
 * @author KSGAMER
 */
/**
 * Se aplica Herencia para que esta clase pueda utilizar el Método de conectar
 * sin tener que crear la variable ya que está en modo protegido el Método
 */
public class ModeloCFDI extends BD {

    /**
     * Se crea la variable para iterar los resultados de la tabla
     */
    private ResultSet rs;
    /**
     * Se crea la variable para realizar las consulta
     */
    private PreparedStatement st;
    /**
     * Se crea la variable de tipo ArrayList el cual contendra solo objetos de
     * tipo Objeto CFDI donde se guardaran los resultados de la consulta y uso
     * posterior
     */
    private ArrayList<ObjetoCFDI> list = new ArrayList<>();

    /**
     * Se crea el Método para la carga de la tabla en este caso de los CFDI el
     * cual se encuentra protegido para que las clases que Hereden de ella las
     * puedan utilizar, devuelve un DefaultTableModel tb
     * @return
     */
    protected DefaultTableModel cargarTabla() {
        /**
         * Se crean los Headers de la tabla y se guardan en la variable titulos
         */
        String[] titulos = {"#", "Nombre"};
        /**
         * Se declara una variable de tipo DefaultTableModel donde se colocaran
         * los Headers de la tabla
         */
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        /**
         * Se declara una variable de tipo Objeto el cual usaremos para guardar los resultados de la consulta
         */
        Object[] fila = new Object[2];

        try {
            /**
             * Se iguala la variable a la conexión usando el Método
             * prepareStatement (Solo para consultas Preparadas)
             * +--------+--------+ | IdCFDI | Nombre | <- (Esto significa el *
             * declarado dentro del Método, los campos totales de la tabla)
             * +--------+--------+
             */
            this.st = conectar().prepareStatement("SELECT * FROM CFDI");
            /**
             * Se ejecuta la consulta con el Método executeQuery() y esto a su
             * vez lo guarda en la variable rs (Variable de Resultados)
             */
            this.rs = st.executeQuery();
            /**
             * Se utiliza un While para poder Iterar los resultados guardados en
             * rs aplicando el Método next()
             */
            while (this.rs.next()) {
                /**
                 * En la Objeto Fila en la posición especificada se guardaran
                 * los resultados para poder agregarlos a la tabla
                 */
                fila[0] = rs.getInt("IdCFDI");
                fila[1] = rs.getString("Nombre");
                /**
                 * Se agrega la fila dentro de tb (Variable de Tipo
                 * DefaultTableModel) con el Método addRow()
                 */
                tb.addRow(fila);
                /**
                 * Al final se agrega a la variable list creando un nuevo objeto de tipo CFDI y pasando los resultados a
                 * travez del Método getInt / getString
                 */
                this.list.add(new ObjetoCFDI(rs.getInt("IdCFDI"), rs.getString("Nombre")));
            }
            /**
             * Se cierra la conexión
             */
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        /**
         * Se retorna la tabla creada con los datos obtenidos de la Base de Datos
         */
        return tb;
    }

    /**
     * Retorna los CFDI en formato ArrayList para su posterior uso, ArrayList list
     * @return 
     */
    protected ArrayList<ObjetoCFDI> selectCFDIs() {
        return this.list;
    }
}
