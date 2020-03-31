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
import objetos.ObjetoEstadoInvetararioHabitacion;
import objetos.ObjetoHabitacion;
import objetos.ObjetoInventario;
import objetos.ObjetoInventarioHabitacion;

/**
 *
 * @author KSGAMER
 */
public class ModeloInventarioHabitacion extends BD {

    private ResultSet rs;
    private PreparedStatement st;

    private ArrayList<ObjetoInventarioHabitacion> list = new ArrayList<>();

    private ModeloHabitaciones habitacion = new ModeloHabitaciones();
    private ModeloInventario inventario = new ModeloInventario();
    private ModeloEstadoInventarioHabitacion estado = new ModeloEstadoInventarioHabitacion();

    protected DefaultTableModel cargarTabla() {
        this.habitacion.cargarTabla();
        this.inventario.cargarTabla("");
        this.estado.cargarTabla();
        //Se declaran los titulos de la tabla
        String[] titulos = {"#", "Habitacion", "Producto", "Consumo", "Estado"};
        //Se declara la tabla pasando las columnas de la tabla
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Se declara un objeto que actuara como la fila de la tabla
        Object[] fila = new Object[5];

        try {
            //Se instancia la conexión a la base de datos pasando la consulta preparada
            this.st = conectar().prepareStatement("SELECT * FROM InventarioHabitacion");
            //Se ejecuta el Query
            this.rs = st.executeQuery();
            //Se iteran los resultados de la consulta
            while (this.rs.next()) {
                //Se agregan los resultados al objeto
                fila[0] = rs.getInt(1);
                for (ObjetoHabitacion objetoHabitacion : habitacion.selectHabitaciones()) {
                    if (rs.getInt(2) == objetoHabitacion.getIdHabitacion()) {
                        fila[1] = objetoHabitacion.getNombre();
                    }
                }
                for (ObjetoInventario objetoInventario : inventario.selectInventario()) {
                    if (rs.getInt(3) == objetoInventario.getIdInventario()) {
                        fila[2] = objetoInventario.getNombre();
                    }
                }
                fila[3] = rs.getInt(4);
                for (ObjetoEstadoInvetararioHabitacion objetoEstadoInvetararioHabitacion : estado.selectEstadoInventarioHabitacion()) {
                    if (rs.getInt(5) == objetoEstadoInvetararioHabitacion.getIdEstadoInventarioHabitacion()) {
                        fila[4] = objetoEstadoInvetararioHabitacion.getNombre();
                    }
                }
                //Se agrega el objeto a la tabla
                tb.addRow(fila);
                //Se agregan los resultados al arreglo atraves de un nuevo objeto
                this.list.add(new ObjetoInventarioHabitacion(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)));
            }
            //Se cierra la conexión
        } catch (SQLException ex) {
            Logger.getLogger(ModeloInventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retona la tabla
        return tb;
    }
    
    public ArrayList<ObjetoInventarioHabitacion> selectInventarioHabitacion() {
        return this.list;
    }

    public void insertInventarioHabitacion(String habitacion, String producto, int consumo, String estado) {
        this.habitacion.cargarTabla();
        this.inventario.cargarTabla("");
        this.estado.cargarTabla();
        try {
            this.st = conectar().prepareStatement("INSERT INTO InventarioHabitacion(IdHabitacion, IdInventario, Consumo, IdEstadoInventarioHabitacion) VALUES (?,?,?,?)");
            for (ObjetoHabitacion objetoHabitacion : this.habitacion.selectHabitaciones()) {
                if (habitacion.equals(objetoHabitacion.getNombre())) {
                    this.st.setInt(1, objetoHabitacion.getIdHabitacion());
                }
            }
            for (ObjetoInventario objetoInventario : this.inventario.selectInventario()) {
                if (producto.equals(objetoInventario.getNombre())) {
                    this.st.setInt(2, objetoInventario.getIdInventario());
                }
            }
            this.st.setInt(3, consumo);
            for (ObjetoEstadoInvetararioHabitacion objetoEstadoInvetararioHabitacion : this.estado.selectEstadoInventarioHabitacion()) {
                if (estado.equals(objetoEstadoInvetararioHabitacion.getNombre())) {
                    this.st.setInt(4, objetoEstadoInvetararioHabitacion.getIdEstadoInventarioHabitacion());
                }
            }
            this.st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloInventarioHabitacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateInventarioHabitacion(String habitacion, String producto, int consumo, String estado, int id) {
        this.habitacion.cargarTabla();
        this.inventario.cargarTabla("");
        this.estado.cargarTabla();
        try {
            this.st = conectar().prepareStatement("UPDATE InventarioHabitacion SET IdHabitacion = ?, IdInventario = ?, Consumo = ?, IdEstadoInventarioHabitacion = ? WHERE IdInventarioHabitacion = ?");
            for (ObjetoHabitacion objetoHabitacion : this.habitacion.selectHabitaciones()) {
                if (habitacion.equals(objetoHabitacion.getNombre())) {
                    this.st.setInt(1, objetoHabitacion.getIdHabitacion());
                }
            }
            for (ObjetoInventario objetoInventario : this.inventario.selectInventario()) {
                if (producto.equals(objetoInventario.getNombre())) {
                    this.st.setInt(2, objetoInventario.getIdInventario());
                }
            }
            this.st.setInt(3, consumo);
            for (ObjetoEstadoInvetararioHabitacion objetoEstadoInvetararioHabitacion : this.estado.selectEstadoInventarioHabitacion()) {
                if (estado.equals(objetoEstadoInvetararioHabitacion.getNombre())) {
                    this.st.setInt(4, objetoEstadoInvetararioHabitacion.getIdEstadoInventarioHabitacion());
                }
            }
            this.st.setInt(5, id);
            this.st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloInventarioHabitacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteInventarioHabitacion(int id) {
        try {
            this.st = conectar().prepareStatement("DELETE FROM InventarioHabitacion WHERE IdInventarioHabitacion = ?");
            this.st.setInt(1, id);
            this.st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloInventarioHabitacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
