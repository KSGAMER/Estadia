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
public class ObjetoCFDI {
    //Creación de los atributos del objeto CFDI
    //+--------+--------+
    //| IdCFDI | Nombre | <- (Extracción de Base de Datos tabla Categoria)
    //+--------+--------+
    private int idCFDI;
    private String nombre;

    //Constructor de CFDI para inicializar las variables idCFDI y nombre
    public ObjetoCFDI(int idCFDI, String nombre) {
        this.idCFDI = idCFDI;
        this.nombre = nombre;
    }

    //Método que devuelve el "ID" del CFDI
    public int getIdCFDI() {
        return idCFDI;
    }

    //Método que devuelve el "Nombre" del CFDI
    public String getNombre() {
        return nombre;
    }
    
}
