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
import objetos.ObjetoUsuario;

/**
 *
 * @author KSGAMER
 */
//Se aplica Herencia de la clase padre BD
public class ModeloUsuarios extends BD {

    //Se declaran las variables de reemplazo de informacion
    private ModeloEstatusUsuarios meu = new ModeloEstatusUsuarios();
    //Se declaran las variables de resultado y de consultas preparadas
    private ResultSet rs;
    private PreparedStatement st;
    //Se declara un arreglo de tipo Objeto Usuario
    private ArrayList<ObjetoUsuario> listUsers = new ArrayList<>();

    //Método que extrae la información directamente de base de datos
    protected DefaultTableModel cargarTabla() {
        //Se cargan los datos a utilizar
        meu.cargarTabla();
        //Se declaran las columnas de la tabla
        String[] titulos = {"Usuario", "Password", "Empleado", "Telefono", "Direccion", "Estatus"};
        //Se declara la tabla pasando las columnas
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Se declara un objeto que actuara como la fila de la tabla
        Object[] fila = new Object[6];

        try {
            //Se instancia la conexión a base de datos pasando la consulta preparada
            this.st = conectar().prepareStatement("SELECT * FROM Usuario");
            //Se ejecuta el Query
            this.rs = st.executeQuery();
            //Se iteran los resultados
            while (this.rs.next()) {
                //Se agregan los resultados al objeto
                fila[0] = rs.getString("Username");
                fila[1] = rs.getString("Password");
                fila[2] = rs.getString("Empleado");
                fila[3] = rs.getString("Telefono");
                fila[4] = rs.getString("Direccion");
                //Se recorre el resultado con un for
                for (int i = 0; i < meu.selectEstatusUsuarios().size(); i++) {
                    //Si el Id del resultado es igual al de la clase prosigue
                    if (rs.getInt("IdEstatusUsuario") == meu.selectEstatusUsuarios().get(i).getIdEstatusUsuario()) {
                        //Se reemplaza el valor por el nombre
                        fila[5] = meu.selectEstatusUsuarios().get(i).getNombre();
                    }
                }
                //Se agrega el objeto a la tabla
                tb.addRow(fila);
                //Se agregan los resultados al arreglo atraves de un nuevo objeto
                this.listUsers.add(new ObjetoUsuario(rs.getString("Username"), rs.getString("Password"), rs.getString("Empleado"), rs.getString("Telefono"), rs.getString("Direccion"), rs.getInt("IdEstatusUsuario")));
            }
            //Se cierra la conexión
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retorna la tabla
        return tb;
    }

    //Método que retorna la información en formato de arreglo
    protected ArrayList<ObjetoUsuario> selectUsuarios() {
        return this.listUsers;
    }

    //Método que inserta un nuevo usuario
    protected void insertUsuarios(String username, String password, String empleado, String telefono, String direccion, String estatusUsuario) {
        //Se cargan los datos a utlizar
        meu.cargarTabla();
        try {
            //Se instancia la conexión a base de datos y se pasa la consulta preparada
            this.st = conectar().prepareStatement("INSERT INTO Usuario(Username, Password, Empleado, Telefono, Direccion, IdEstatusUsuario) VALUES (?,?,?,?,?,?)");
            //Se pasan los parametros a la consulta
            this.st.setString(1, username);
            this.st.setString(2, password);
            this.st.setString(3, empleado);
            this.st.setString(4, telefono);
            this.st.setString(5, direccion);
            //Se recorre el valor con un for
            for (int i = 0; i < meu.selectEstatusUsuarios().size(); i++) {
                //Si el valor es igual al nombre de la clase se prosigue
                if (estatusUsuario.equals(meu.selectEstatusUsuarios().get(i).getNombre())) {
                    //Se reemplaza el valor con el ID
                    this.st.setInt(6, meu.selectEstatusUsuarios().get(i).getIdEstatusUsuario());
                }
            }
            //Se ejecuta la consulta
            this.st.execute();
            //Se cierra la conexión
            DesktopNotify.showDesktopMessage("Exito", "Datos del empleado agregados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrió un error al intentar agregar los datos del nuevo empleado, por favor intente de nuevo o revise su conexión", DesktopNotify.ERROR);
        }
    }

    //Método que actualiza un usuario
    protected void updateUsuarios(String password, String empleado, String telefono, String direccion, String estatusUsuario, String username) {
        //Se cargan los datos a utilizar
        meu.cargarTabla();
        try {
            //Se instancia la conexión a la base de datos y se pasa la consulta preparada
            this.st = conectar().prepareStatement("UPDATE Usuario SET Password = ?, Empleado = ?, Telefono = ?, Direccion = ?, IdEstatusUsuario = ? WHERE Username = ?");
            //Se pasan los parametros a la consulta
            this.st.setString(1, password);
            this.st.setString(2, empleado);
            this.st.setString(3, telefono);
            this.st.setString(4, direccion);
            //Se recorre el valor con un for
            for (int i = 0; i < meu.selectEstatusUsuarios().size(); i++) {
                //Si el valor es igual al nombre de la clase se prosigue
                if (estatusUsuario.equals(meu.selectEstatusUsuarios().get(i).getNombre())) {
                    //Se reemplaza el valor con el ID
                    this.st.setInt(5, meu.selectEstatusUsuarios().get(i).getIdEstatusUsuario());
                }
            }
            this.st.setString(6, username);
            //Se ejecuta el Query
            this.st.executeUpdate();
            //Se cierra la conexión
            DesktopNotify.showDesktopMessage("Exito", "Datos del empleado actualizados con éxito.", DesktopNotify.SUCCESS);

        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrió un error al intentar actualizar los datos del empleado por favor intente de nuevo o revise su conexión", DesktopNotify.ERROR);
        }
    }

    //Método que elimina un usuario
    protected void deleteUsuarios(String username) {
        try {
            //Se instancia la conexión a la base de datos y se pasa la consulta preparada
            this.st = conectar().prepareStatement("DELETE FROM Usuario WHERE Username = ?");
            //Se pasan los parametros a la consulta
            this.st.setString(1, username);
            //Se ejecuta el Query
            this.st.execute();
            //Se cierra la conexión
            DesktopNotify.showDesktopMessage("Exito", "Datos del empleado eliminados con éxito.", DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            DesktopNotify.showDesktopMessage("Error", "Ocurrió un error al intentar eliminar los datos del empleado por favor intente de nuevo o revise su conexión", DesktopNotify.ERROR);
        }
    }
}
