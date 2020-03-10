/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author KSGAMER
 */
public class ControladorConfiguracion {
    
    private String server;
    private String instance;
    private String port;
    private String database;
    private String auth;
    private String user;
    private String password;
    
    private File fichero = new File("src/configuraciones/Configuracion.json");

    public ControladorConfiguracion() {
        try {
            
            JSONParser parser = new JSONParser();
            
            Object obj = parser.parse(new FileReader(fichero.getAbsoluteFile()));
            
            JSONObject jsonobject = (JSONObject) obj;
            
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
    
    public void modificarConexion(String server, String instance, String port, String database, String auth, String user, String password) {
        JSONObject obj = new JSONObject();
		obj.put("SERVER" , server);
		obj.put("INSTANCE" , instance);
		obj.put("PORT" , port);
		obj.put("DATABASE" , database);
		obj.put("AUTH" , auth);
		obj.put("USER" , user);
		obj.put("PASSWORD" , password);
        
        try {
            FileWriter json = new FileWriter(fichero.getAbsoluteFile());
            json.write(obj.toJSONString());
            json.flush();
            json.close();
        } catch (IOException ex) {
            Logger.getLogger(ControladorConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String getServer() {
        return server;
    }
    
    public String getInstance() {
        return instance;
    }
    
    public String getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

    public String getAuth() {
        return auth;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
    
}
