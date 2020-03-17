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
import objetos.ObjetoPermiso;

/**
 *
 * @author KSGAMER
 */

//Se aplica Herencia de la clase padre BD
public class ModeloPermisos extends BD{
    //Se declaran las variables a utilizar para el reemplazo de información
    private ModeloEstatusPermisos mep = new ModeloEstatusPermisos();
    private ModeloModulos mm = new ModeloModulos();
    //Se declaran las variables de resultado y de consultas preparadas
    private ResultSet rs;
    private PreparedStatement st;
    //Se declara el arreglo de tipo Objeto Permiso
    private ArrayList<ObjetoPermiso> listAccess = new ArrayList<>();
    
    //Método que extrae la información directamente de base de datos
    protected DefaultTableModel cargarTabla() {
        //Se cargan los resultados a utilizar
        mep.cargarTabla();
        mm.cargarTabla();
        //Se declaran las columnas de la tabla
        String[] titulos = {"#", "Usuario", "Modulo", "Consultar", "Insertar", "Actualizar", "Eliminar"};
        //Se declara la tabla pasando las columnas
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Se declara el objeto que actuara como la fila de la tabla
        Object[] fila = new Object[7];

        try {
            //Se instancia la conexión a base de datos pasando la consulta preparada
            this.st = conectar().prepareStatement("SELECT * FROM Permiso");
            //Se ejecuta el Query
            this.rs = st.executeQuery();
            //Se iteran los resultados
            while (this.rs.next()) {
                //Se agregan los resultados al objeto
                fila[0] = rs.getInt("IdPermiso");
                fila[1] = rs.getString("Username");
                //Se recorre el resultado con un for
                for (int i = 0; i < mm.selectModulos().size(); i++) {
                    //Si el ID del resultado es igual al de la clase se prosigue
                    if(rs.getInt("IdModulo") == mm.selectModulos().get(i).getIdModulo()) {
                        //Se reemplaza el valor por el nombre
                        fila[2] = mm.selectModulos().get(i).getNombre();
                    }
                }
                //Se recorre el resultado con un for
                for (int i = 0; i < mep.selectEstatusPermisos().size(); i++) {
                    //Si el ID del resultado es igual al de la clase se prosigue
                    if(rs.getInt("Consultar") == mep.selectEstatusPermisos().get(i).getIdEstatusPermiso()) {
                        //Se reemplaza el valor por el nombre
                        fila[3] = mep.selectEstatusPermisos().get(i).getNombre();
                    }
                    //Si el ID del resultado es igual al de la clase se prosigue
                    if(rs.getInt("Insertar") == mep.selectEstatusPermisos().get(i).getIdEstatusPermiso()) {
                        //Se reemplaza el valor por el nombre
                        fila[4] = mep.selectEstatusPermisos().get(i).getNombre();
                    }
                    //Si el ID del resultado es igual al de la clase se prosigue
                    if(rs.getInt("Actualizar") == mep.selectEstatusPermisos().get(i).getIdEstatusPermiso()) {
                        //Se reemplaza el valor por el nombre
                        fila[5] = mep.selectEstatusPermisos().get(i).getNombre();
                    }
                    //Si el ID del resultado es igual al de la clase se prosigue
                    if(rs.getInt("Eliminar") == mep.selectEstatusPermisos().get(i).getIdEstatusPermiso()) {
                        //Se reemplaza el valor por el nombre
                        fila[6] = mep.selectEstatusPermisos().get(i).getNombre();
                    }
                }
                //Se agrega el objeto a la tabla
                tb.addRow(fila);
                //Se agregan los resultados al arreglo atraves de un nuevo objeto
                this.listAccess.add(new ObjetoPermiso(rs.getInt("IdPermiso"), rs.getString("Username"), rs.getInt("IdModulo"), rs.getInt("Consultar"), rs.getInt("Insertar"), rs.getInt("Actualizar"), rs.getInt("Eliminar")));
            }
            //Se cierra la conexión
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retorna la tabla
        return tb;
    }
    
    //Método que retorna la información en formato de arreglo
    protected ArrayList<ObjetoPermiso> selectPermisos() {
        return this.listAccess;
    }
    
    //Método que inserta un permiso
    protected void insertPermisos(String username, String modulo, String consultar, String insertar, String actualizar, String eliminar) {
        //Se cargan las datos a utilizar
        mep.cargarTabla();
        mm.cargarTabla();
        try {
            //Se instancia la conexión a base de datos pasando la consulta preparada
            this.st = conectar().prepareStatement("INSERT INTO Permiso(Username, IdModulo, Consultar, Insertar, Actualizar, Eliminar) VALUES (?,?,?,?,?,?)");
            //Se pasan los parametros a la consulta
            this.st.setString(1, username);
            //Se recorre el resultado con un for
            for (int i = 0; i < mm.selectModulos().size(); i++) {
                //Si el Nombre del resultado es igual al de la clase se prosigue
                if(modulo.equals(mm.selectModulos().get(i).getNombre())) {
                    //Se reemplaza el valor por el ID
                    this.st.setInt(2, mm.selectModulos().get(i).getIdModulo());
                }
            }
            //Se recorre el resultado con un for
            for (int i = 0; i < mep.selectEstatusPermisos().size(); i++) {
                //Si el Nombre del resultado es igual al de la clase se prosigue
                if(consultar.equals(mep.selectEstatusPermisos().get(i).getNombre())) {
                    //Se reemplaza el valor por el ID
                    this.st.setInt(3, mep.selectEstatusPermisos().get(i).getIdEstatusPermiso());
                }
                //Si el Nombre del resultado es igual al de la clase se prosigue
                if(insertar.equals(mep.selectEstatusPermisos().get(i).getNombre())) {
                    //Se reemplaza el valor por el ID
                    this.st.setInt(4, mep.selectEstatusPermisos().get(i).getIdEstatusPermiso());
                }
                //Si el Nombre del resultado es igual al de la clase se prosigue
                if(actualizar.equals(mep.selectEstatusPermisos().get(i).getNombre())) {
                    //Se reemplaza el valor por el ID
                    this.st.setInt(5, mep.selectEstatusPermisos().get(i).getIdEstatusPermiso());
                }
                //Si el Nombre del resultado es igual al de la clase se prosigue
                if(eliminar.equals(mep.selectEstatusPermisos().get(i).getNombre())) {
                    //Se reemplaza el valor por el ID
                    this.st.setInt(6, mep.selectEstatusPermisos().get(i).getIdEstatusPermiso());
                }
            }
            //Se ejecuta el Query
            this.st.execute();
            //Se cierra la conexión
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPermisos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Método que actualiza un permiso
    protected void updatePermisos(String username, String modulo, String consultar, String insertar, String actualizar, String eliminar, int id) {
        //Se cargan las datos a utilizar
        mep.cargarTabla();
        mm.cargarTabla();
        try {
            //Se instancia la conexión a base de datos pasando la consulta preparada
            this.st = conectar().prepareStatement("UPDATE Permiso SET Username = ?, IdModulo = ?, Consultar = ?, Insertar = ?, Actualizar = ?, Eliminar = ? WHERE IdPermiso = ?");
            //Se pasan los parametros a la consulta
            this.st.setString(1, username);
            //Se recorre el resultado con un for
            for (int i = 0; i < mm.selectModulos().size(); i++) {
                //Si el Nombre del resultado es igual al de la clase se prosigue
                if(modulo.equals(mm.selectModulos().get(i).getNombre())) {
                    //Se reemplaza el valor por el ID
                    this.st.setInt(2, mm.selectModulos().get(i).getIdModulo());
                }
            }
            //Se recorre el resultado con un for
            for (int i = 0; i < mep.selectEstatusPermisos().size(); i++) {
                //Si el Nombre del resultado es igual al de la clase se prosigue
                if(consultar.equals(mep.selectEstatusPermisos().get(i).getNombre())) {
                    //Se reemplaza el valor por el ID
                    this.st.setInt(3, mep.selectEstatusPermisos().get(i).getIdEstatusPermiso());
                }
                //Si el Nombre del resultado es igual al de la clase se prosigue
                if(insertar.equals(mep.selectEstatusPermisos().get(i).getNombre())) {
                    //Se reemplaza el valor por el ID
                    this.st.setInt(4, mep.selectEstatusPermisos().get(i).getIdEstatusPermiso());
                }
                //Si el Nombre del resultado es igual al de la clase se prosigue
                if(actualizar.equals(mep.selectEstatusPermisos().get(i).getNombre())) {
                    //Se reemplaza el valor por el ID
                    this.st.setInt(5, mep.selectEstatusPermisos().get(i).getIdEstatusPermiso());
                }
                //Si el Nombre del resultado es igual al de la clase se prosigue
                if(eliminar.equals(mep.selectEstatusPermisos().get(i).getNombre())) {
                    //Se reemplaza el valor por el ID
                    this.st.setInt(6, mep.selectEstatusPermisos().get(i).getIdEstatusPermiso());
                }
            }
            this.st.setInt(7, id);
            //Se ejecuta el Query
            this.st.executeUpdate();
            //Se cierra la conexión
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPermisos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Método que elimina un permiso
    protected void deletePermisos(int id) {
        try {
            //Se instancia la conexión a base de datos pasando la consulta preparada
            this.st = conectar().prepareStatement("DELETE FROM Permiso WHERE IdPermiso = ?");
            //Se pasan los parametros a la consulta
            this.st.setInt(1, id);
            //Se ejecuta el Query
            this.st.execute();
            //Se cierra la conexión
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPermisos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
