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
public class ObjetoCliente {
    //Creación de los atributos del objeto Cliente
    //+-----------+--------+-----+-----------+----------+-------+--------+
    //| IdCliente | Nombre | RFC | Direccion | Telefono | Email | IdCFDI | <- (Extracción de Base de Datos tabla Cliente)
    //+-----------+--------+-----+-----------+----------+-------+--------+
    private int idCliente;
    private String nombre;
    private String rfc;
    private String direccion;
    private String telefono;
    private String email;
    private int idCFDI;

    //Constructor de Cliente e instanciar los valores de la Clase
    public ObjetoCliente(int idCliente, String nombre, String rfc, String direccion, String telefono, String email, int idCFDI) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.rfc = rfc;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.idCFDI = idCFDI;
    }

    //Método que devuelve el "ID" de la tabla Cliente
    public int getIdCliente() {
        return idCliente;
    }

    //Método para obtener el "Nombre" de la tabla Cliente (Nombre del Cliente)
    public String getNombre() {
        return nombre;
    }

    //Método para obtener el "RFC" de la tabla Cliente 
    public String getRfc() {
        return rfc;
    }

    //Método para obtener la "Dirección" de la tabla Cliente
    public String getDireccion() {
        return direccion;
    }

    //Método para obtener el "Teléfono" de la tabla Cliente
    public String getTelefono() {
        return telefono;
    }

    //Método para obtener el "Email" de la tabla Cliente
    public String getEmail() {
        return email;
    }

    //Método para obtener el "CFDI" de la tabla Cliente
    public int getIdCFDI() {
        return idCFDI;
    }
    
}
