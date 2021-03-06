/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloProducto;
import objetos.ObjetoProducto;

/**
 *
 * @author KSGAMER
 */
public class ControladorProductos extends ModeloProducto{
    public DefaultTableModel tablaProducto() {
        return cargarTabla();
    }
    
    public DefaultTableModel tablaProducto(String buscar) {
        return cargarTabla(buscar);
    }
    
    public DefaultTableModel cantidadProductos() {
        return productosCantidad();
    }
    
    public DefaultTableModel cantidadProductos(String buscar) {
        return productosCantidad(buscar);
    }
    
    public ArrayList<ObjetoProducto> seleccionarProducto() {
        return selectProducto();
    }
    
    public void insertarProducto(String producto, String proveedor, double precio, int cantidad, String observaciones) {
        insertProducto(producto, proveedor, precio, cantidad, observaciones);
    }
    
    public void actualizarProducto(String producto, String proveedor, double precio, int cantidad, String observaciones, int id) {
        updateProducto(producto, proveedor, precio, cantidad, observaciones, id);
    }
    
    public void updateCantidad(String producto, int cantidad) {
        updateStock(producto, cantidad);
    }
    
    public void eliminarProducto(int id) {
        deleteProducto(id);
    }
}
