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
public class ObjetoEstatusPermiso {
    //Creación de los atributos del objeto EstatusPermiso
    //+------------------+--------+
    //| IdEstatusPermiso | Nombre | <- (Extracción de Base de Datos de la tabla EstatusPermiso)
    //+------------------+--------+
    private int idEstatusPermiso;
    private String nombre;

    //Creación del constructor para inicializar las variables idEstatusPermiso y nombre
    public ObjetoEstatusPermiso(int idEstatusPermiso, String nombre) {
        this.idEstatusPermiso = idEstatusPermiso;
        this.nombre = nombre;
    }

    //Método que devuelve el "ID" del estatus del permiso
    public int getIdEstatusPermiso() {
        return idEstatusPermiso;
    }

    //Método que devuelve el "Nombre" del estatus del permiso
    public String getNombre() {
        return nombre;
    }
    
}
