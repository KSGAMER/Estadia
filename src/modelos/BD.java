/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import ds.desktop.notify.DesktopNotify;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import objetos.ObjetoConfiguracion;
import vistas.sesion;

/**
 *
 * @author KSGAMER
 */
public class BD extends ObjetoConfiguracion {

    //Declaración de la clase ModeloConfiguracion para inicializar
    private ObjetoConfiguracion ObjConfig = new ObjetoConfiguracion();
    private Connection conect = null;

    //Creación del metodo conectar encargado de la conexion a la base de datos SQL Server = Estadia
    protected Connection conectar() {
        //Creación de variable de conexion a la Base de Datos

        try {
            //Variable que contiene la cadena de conexión a Base de Datos
            //Se extraen los valores apartir de un JSON alojado dentro de la aplicación en la carpeta configuraciones
            //ObjConfig.getServer() == "10.10.6.12" IP usada en el desarrollo de la aplicación
            //ObjConfig.getInstance() == "KSGAMER17" El nombre de la instancia de SQL Server
            //ObjConfig.getPort() == "1433" El puerto donde se conecta SQL a la aplicación de Java
            //ObjConfig.getDataBase() == "Estadia" Nombre de la Base de Datos utilizada
            //ObjConfig.getAuth() == "false" La bandera que sirve para indicar si necesita autentificación de windows o no (En caso de ser TRUE la Base de Datos debe de estar dentro del equipo de computo de lo contrario no conectara a Base de Datos"
            //ObjConfig.getUser() == "Estadia" El nombre del Usuario dado de alta dentro del Gestor de Base de Datos SQL Server
            //ObjConfig.getPassword() == "1234" La contraseña del Usuario dado de alta dentro del Gestor de Base de Datos SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://" + ObjConfig.getServer() + "\\" + ObjConfig.getInstance() + ":" + ObjConfig.getPort() + ";databaseName=" + ObjConfig.getDataBase() + ";user=" + ObjConfig.getUser() + ";password=" + ObjConfig.getPassword();
            //Se iguala a conect usando el Driver Manager con su método getConnecion() agregando la cadena de conexión para poder obtener respuesta del Servidor y entablar el medio de las transacción atraves del puerto 1433
            conect = DriverManager.getConnection(connectionUrl);
        } catch (SQLException ex) {
            //En caso de no poder entablar la conexión mostrará una notificación en la esquina inferior derecha mostrando el mensaje con un icono de ERROR
            DesktopNotify.showDesktopMessage("Error", "Ocurrio un error inesperado, asegurece de que se encuentra conectado a internet o dentro de la misma red", DesktopNotify.ERROR);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Retornamos la conexión para poder utilizarla en las demás Clases de Modelo (BD es la Clase PADRE de donde heredaran los metodos de conectar para poder conectarse a SQL Server)
        return conect;
    }
}
