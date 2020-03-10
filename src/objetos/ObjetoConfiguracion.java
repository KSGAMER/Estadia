/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import controladores.ControladorConfiguracion;

/**
 *
 * @author KSGAMER
 */
public class ObjetoConfiguracion {
    //Creación de las variables para generar objetos Configuración
    //{
    //"PORT" : "",
    //"DATABASE" : "",
    //"PASSWORD" : "",      <- (Archivo JSON)
    //"SERVER" : "",
    //"AUTH" : "",
    //"INSTANCE" : "",
    //"USER" : ""
    //}
    private String Server;
    private String Instance;
    private String Port;
    private String DataBase;
    private String Auth;
    private String User;
    private String Password;
    
    //Se crea una variable de tipo ControladorConfiguracion 
    private final ControladorConfiguracion contConfig = new ControladorConfiguracion();

    //Constructor del objeto configuración e instancia de las variables de la Clase
    public ObjetoConfiguracion () {
        //Se extrae el servidor de la variable contConfig e iguala a la variable Server con el método getServer
        this.Server = contConfig.getServer();
        //Se extrae la instancia de la variable contConfig e iguala a la variable Intance con el método getInstance
        this.Instance = contConfig.getInstance();
        //Se extrae el puerto de la variable contConfig e iguala a la variable Port con el método getPort
        this.Port = contConfig.getPort();
        //Se extrae el nombre de la Base de Datos de la variable contConfig e iguala a la variable DataBase con el método getDatabase
        this.DataBase = contConfig.getDatabase();
        //Se extrae la bandera de autentificación de la variable contConfig e iguala a la variable Auth con el método getAuth
        this.Auth = contConfig.getAuth();
        //Se extrae el usuario de la variable contConfig e iguala a la variable User con el método getUser
        this.User = contConfig.getUser();
        //Se extrae la contraseña de la variable contConfig e iguala a la variable Password con el método getPassword
        this.Password = contConfig.getPassword();
    }

    //Método que devuelve la instancia de la Base de Datos
    public String getInstance() {
        return Instance;
    }
    
    //Método que devuelve el nombre / ip del servidor de la Base de Datos
    public String getServer() {
        return Server;
    }
    
    //Método que devuelve el puerto que usa la Base de Datos
    public String getPort() {
        return Port;
    }

    //Método que devuelve el nombre de la Base de Datos
    public String getDataBase() {
        return DataBase;
    }

    //Método que devuelve la bandera de autenticación de la Base de Datos
    public String getAuth() {
        return Auth;
    }

    //Método que devuelve el nombre del usuario de la Base de Datos
    public String getUser() {
        return User;
    }

    //Método que devuelve la contraseña de la Base de Datos
    public String getPassword() {
        return Password;
    }
    
}
