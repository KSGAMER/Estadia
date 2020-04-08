/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import ds.desktop.notify.DesktopNotify;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author KSGAMER
 */
public class ControladorConfiguracion {

    //Se declaran las variables para la conexión
    private String server;
    private String instance;
    private String port;
    private String database;
    private String auth;
    private String user;
    private String password;

    //Se declara una variable que contiene la ruta del archivo de configuración
    private File fichero = new File("src/configuraciones/Configuracion.json");
    //Constructor que inicializa las variables
    public ControladorConfiguracion() {
        try {
            //Variable para conversión de datos
            JSONParser parser = new JSONParser();
            //Variable de objeto para leer la información en formato json extrayendo la ruta absoluta del fichero
            Object obj = parser.parse(new FileReader(fichero.getAbsoluteFile()));
            //Variable para extraer la información del archivo json
            JSONObject jsonobject = (JSONObject) obj;
            //Extracción de los datos del arreglo del archivo json
            server = (String) jsonobject.get("SERVER");
            instance = (String) jsonobject.get("INSTANCE");
            port = (String) jsonobject.get("PORT");
            database = (String) jsonobject.get("DATABASE");
            auth = (String) jsonobject.get("AUTH");
            user = (String) jsonobject.get("USER");
            password = (String) jsonobject.get("PASSWORD");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControladorConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControladorConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ControladorConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (java.lang.NullPointerException e) {
            Logger.getLogger(ControladorConfiguracion.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //Método que actualiza los datos dentro del archivo JSON pasando el servidor, la instancia, el puerto de conexión, el nombre de la base de datos, la bandera de autentificación, el usuario de la base de datos y la contraseña
    public void modificarConexion(String server, String instance, String port, String database, String auth, String user, String password) {
        //Se declara una variable de tipo JSONObjecto para poder armar el JSON
        JSONObject obj = new JSONObject();
        //Se formatea el JSON como se requiere y se pasan los valores
        obj.put("SERVER", server);
        obj.put("INSTANCE", instance);
        obj.put("PORT", port);
        obj.put("DATABASE", database);
        obj.put("AUTH", auth);
        obj.put("USER", user);
        obj.put("PASSWORD", password);

        try {
            //Se declara una variable de escritura pasando la ruta donde se encuentra el archivo JSON
            FileWriter json = new FileWriter(fichero.getAbsoluteFile());
            //Se escriben los valores previamente cargador
            json.write(obj.toJSONString());
            //Se establete la conexión para la escritura del archivo
            json.flush();
            //Se cierra la conexión
            json.close();
        } catch (IOException ex) {
            Logger.getLogger(ControladorConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Método que devuelve el nombre del Servidor (IP en caso de que la base de datos no este de manera local)
    public String getServer() {
        return server;
    }

    //Método que devuelve el nombre del a Instancia del servidor de base de datos
    public String getInstance() {
        return instance;
    }

    //Método que devuelve el Puerto de conexión
    public String getPort() {
        return port;
    }

    //Método que devuelve el Nombre de la Base de Datos
    public String getDatabase() {
        return database;
    }

    //Método que devuelve la Bandera de la disposición de autentificación (¿Requiere autentificación? TRUE o FALSE)
    public String getAuth() {
        return auth;
    }

    //Método que devuelve el Usuario de la base de datos
    public String getUser() {
        return user;
    }

    //Método que devuelve la Contraseña del Usuario de la Base de Datos
    public String getPassword() {
        return password;
    }

}
