/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.ValidadorDePrivilegios;

import controladores.ControladorCargos;
import controladores.ControladorUsuarios;
import objetos.ObjetoCargo;
import objetos.ObjetoUsuario;
import static vistas.Principal.*;

/**
 *
 * @author fenix
 */
public class ControladorPrivilegioMenuInicial {

    private ControladorCargos ccargo = new ControladorCargos();
    private ControladorUsuarios cuser = new ControladorUsuarios();

    public void comparadorPrivilegios(String Usuario) {
        for (ObjetoUsuario objetoUsuario : cuser.selectUsuario()) {
            for (ObjetoCargo objetoCargo : ccargo.seleccionarCargo()) {
                 if (objetoUsuario.getUsername().equals(Usuario)) { 
                     if (objetoUsuario.getIdCargo() == objetoCargo.getIdCargo()) {
                  
                        if (!(objetoCargo.equals("Administrador") && !(objetoCargo.equals("Camarista")))) { //para cualquier otro usuario
                            Administrador.setVisible(false);
                            Configuracion.setVisible(false);
                            AdministracionCaja.setVisible(false);
                            Reportes.setVisible(false);
                            CalendarioReservas.setVisible(true);
                            Reservaciones.setVisible(true);
                            Recepcion.setVisible(true);
                            Clientes.setVisible(true);
                            Facturas.setVisible(true);
                            GastosHotel.setVisible(true);
                            RecepcionCamarista.setVisible(false);
                            break;
                        } else if (objetoCargo.equals("Camarista")) {
                            Administrador.setVisible(false);
                            Configuracion.setVisible(false);
                            AdministracionCaja.setVisible(false);
                            Reportes.setVisible(false);
                            CalendarioReservas.setVisible(false);
                            Reservaciones.setVisible(false);
                            Recepcion.setVisible(false);
                            Clientes.setVisible(false);
                            Facturas.setVisible(false);
                            GastosHotel.setVisible(false);
                            RecepcionCamarista.setVisible(true);
                            break;
                        } else if (objetoCargo.equals("Administrador")) {
                            Administrador.setVisible(true);
                            Configuracion.setVisible(true);
                            AdministracionCaja.setVisible(true);
                            Reportes.setVisible(true);
                            CalendarioReservas.setVisible(true);
                            Reservaciones.setVisible(true);
                            Recepcion.setVisible(true);
                            Clientes.setVisible(true);
                            Facturas.setVisible(true);
                            GastosHotel.setVisible(true);
                            RecepcionCamarista.setVisible(false);
                            break;
                        }
                    }
                }
            }
        }

    }

}
