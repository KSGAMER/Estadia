/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

/**
 *
 * @author KSGAMER
 */
public class ObjetoEstatusUsuario {
    //Creación de los atributos del objeto Estatus Usuario
    //+------------------+--------+
    //| IdEstatusUsuario | Nombre | <- (Extracción de Base de Datos) 
    //+------------------+--------+
    private int idEstatusUsuario;
    private String nombre;

    //Creación del constructor para inicializar las variables idEstatusUsuario y nombre
    public ObjetoEstatusUsuario(int idEstatusUsuario, String nombre) {
        this.idEstatusUsuario = idEstatusUsuario;
        this.nombre = nombre;
    }

    //Método que devuelve el "ID" del estatus del usuario
    public int getIdEstatusUsuario() {
        return idEstatusUsuario;
    }

    //Método que devuelve el "Nombre" del estatus del usuario
    public String getNombre() {
        return nombre;
    }
    
}
