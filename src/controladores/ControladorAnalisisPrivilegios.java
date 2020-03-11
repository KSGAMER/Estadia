/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.ControladorPermisos;
import objetos.ObjetoPermiso;

/**
 *
 * @author fenix
 */
public class ControladorAnalisisPrivilegios {
    private   //necesario para validar permisos
    ControladorPermisos cperm = new ControladorPermisos();
    
        public void validarPermisos() {
        for (ObjetoPermiso permiso : cperm.selectPermiso()) {
            if (permiso.getUsermane()== "SEBAS") {
                System.out.println("Hola ");
            }
        }
    }
}
