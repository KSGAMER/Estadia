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
public class ObjetoUsuario {
    //Creación de los atributos del objeto Usuario
    //+----------+----------+----------+----------+-----------+------------------+
    //| Username | Password | Empleado | Telefono | Direccion | IdEstatusUsuario | <- (Extracción de Base de Datos)
    //+----------+----------+----------+----------+-----------+------------------+
    private String username;
    private String password;
    private String empleado;
    private String telefono;
    private String direccion;
    private String imagen;
    private int idEstatusUsuario;
    private int idCargo;

    //Creación del constructor para inicializar las variables username, passoword, empleado, telefono, dirección, idEstatusUsuario

    public ObjetoUsuario(String username, String password, String empleado, String telefono, String direccion, String imagen, int idEstatusUsuario, int idCargo) {
        this.username = username;
        this.password = password;
        this.empleado = empleado;
        this.telefono = telefono;
        this.direccion = direccion;
        this.imagen = imagen;
        this.idEstatusUsuario = idEstatusUsuario;
        this.idCargo = idCargo;
    }
    

    //Método que devuelve el "Usuario" de la tabla Usuario
    public String getUsername() {
        return username;
    }

    //Método que devuelve la "Contraseña" de la tabla Usuario
    public String getPassword() {
        return password;
    }

    //Método que devuelve el "Nombre completo del Empleado" de la tabla Usuario
    public String getEmpleado() {
        return empleado;
    }

    //Método que devuelve el "Telefono" de la tabla Usuario
    public String getTelefono() {
        return telefono;
    }

    //Método que devuelve la "Dirección" de la tabla Usuario
    public String getDireccion() {
        return direccion;
    }

    //Método que devuelve el "ID" del Estatus del Usuario de la tabla Usuario
    public int getIdEstatusUsuario() {
        return idEstatusUsuario;
    }

    public String getImagen() {
        return imagen;
    }

    public int getIdCargo() {
        return idCargo;
    }
    
}
