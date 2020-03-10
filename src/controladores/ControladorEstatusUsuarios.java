/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloEstatusUsuarios;
import objetos.ObjetoEstatusUsuario;

/**
 *
 * @author KSGAMER
 */
public class ControladorEstatusUsuarios extends ModeloEstatusUsuarios{
    public DefaultTableModel tablaEstatusUsuarios() {
        return cargarTabla();
    }
    
    public ArrayList<ObjetoEstatusUsuario> selectEstatusUsuario() {
        return selectEstatusUsuarios();
    }
}
