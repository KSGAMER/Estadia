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
public class ObjetoPermiso {
    //Creación de los atributos del objeto Permiso
    //+-----------+----------+----------+-----------+----------+------------+----------+
    //| IdPermiso | Username | IdModulo | Consultar | Insertar | Actualizar | Eliminar | <- (Extracción de Base de Datos)
    //+-----------+----------+----------+-----------+----------+------------+----------+
    private int idPermiso;
    private String usermane;
    private int idModulo;
    private int consultar;
    private int insertar;
    private int actualizar;
    private int eliminar;

    //Creación del constructor para inicializar las variables idPermiso, username, idModulo, consultar, insertar, actualizar y eliminar
    public ObjetoPermiso(int idPermiso, String usermane, int idModulo, int consultar, int insertar, int actualizar, int eliminar) {
        this.idPermiso = idPermiso;
        this.usermane = usermane;
        this.idModulo = idModulo;
        this.consultar = consultar;
        this.insertar = insertar;
        this.actualizar = actualizar;
        this.eliminar = eliminar;
    }

    //Método que devuelve el "ID" del Permiso
    public int getIdPermiso() {
        return idPermiso;
    }

    //Método que devuelve el "Username" del Usuario de la tabla Permiso
    public String getUsermane() {
        return usermane;
    }

    //Método que devuelve el "ID" del Módulo de la tabla Permiso
    public int getIdModulo() {
        return idModulo;
    }

    //Método que devueleve la "ID" del Estatus del Permiso de la tabla Permiso 
    public int getConsultar() {
        return consultar;
    }

    //Método que devueleve la "ID" del Estatus del Permiso de la tabla Permiso 
    public int getInsertar() {
        return insertar;
    }

    //Método que devueleve la "ID" del Estatus del Permiso de la tabla Permiso 
    public int getActualizar() {
        return actualizar;
    }

    //Método que devueleve la "ID" del Estatus del Permiso de la tabla Permiso 
    public int getEliminar() {
        return eliminar;
    }
    
}
