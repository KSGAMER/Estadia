/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.ValidadorDePrivilegios;

import Utilerias.CambiaPanel;
import controladores.ControladorCargos;
import controladores.ControladorUsuarios;
import objetos.ObjetoCargo;
import objetos.ObjetoUsuario;
import vistas.ModuloCamarista.Pn_RecepcionVistaCamarista;
import vistas.Pn_CalendarioReservaciones;
import vistas.Principal;
import static vistas.Principal.*;


/**
 *
 * @author fenix
 * Este controlador delimita la aparicion de algunos botones del menu dependiendo del usuario que este logueado
 * en funcion de su puesto o cargo dentro del hotel 
 */
public class ControladorPrivilegioMenuInicial {

    private ControladorCargos ccargo = new ControladorCargos();
    private ControladorUsuarios cuser = new ControladorUsuarios();

    public void comparadorPrivilegios(String Usuario) {
        //siempre necesarios para cargar los datos de las variables cuser, ccargo
        // y obtengas asi datos 
        cuser.tablaUsuarios();
        ccargo.tablaCargos();
        //fin
        
        //ambos for son necesarios para realizar las comparaciones del cargo asignado a usuario y el cargo guardado en 
        //la tabla cargos 
        for (ObjetoUsuario objetoUsuario : cuser.selectUsuario()) {
            for (ObjetoCargo objetoCargo : ccargo.seleccionarCargo()) {
                 if (objetoUsuario.getUsername().equals(Usuario)) { 
                     if (objetoUsuario.getIdCargo() == objetoCargo.getIdCargo()) {
                         //se valida si el cargo del usuario
                        if (objetoCargo.getNombre().equals("Recepcionista")) { //para cualquier otro usuario
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
                            //PARA QUE APAREZCA LA PANTALLE DE INICIO AL COMENZAR LA APLICACION
                            //new CambiaPanel(pnlPrincipal, new Pn_Recepcion());
                            //PARA QUE APARECA LA PANTALLA DEL CALENDARIO AL INICAR LA APLICACION 
                            new CambiaPanel(Principal.pnlPrincipal, new Pn_CalendarioReservaciones());
                            //asigna la imagen de icono de la aplicacion que se muestra en la barra de tareas

                            break;
                        } else if (objetoCargo.getNombre().equals("Camarista")) {
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
                            //PARA QUE APAREZCA LA PANTALLE DE INICIO AL COMENZAR LA APLICACION
                            //new CambiaPanel(pnlPrincipal, new Pn_Recepcion());
                            //PARA QUE APARECA LA PANTALLA DEL CALENDARIO AL INICAR LA APLICACION 
                            new CambiaPanel(pnlPrincipal, new Pn_RecepcionVistaCamarista());
                            break;
                        } else if (objetoCargo.getNombre().equals("Administrador")) {
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
                             //PARA QUE APAREZCA LA PANTALLE DE INICIO AL COMENZAR LA APLICACION
                             //new CambiaPanel(pnlPrincipal, new Pn_Recepcion());
                             //PARA QUE APARECA LA PANTALLA DEL CALENDARIO AL INICAR LA APLICACION 
                             new CambiaPanel(Principal.pnlPrincipal, new Pn_CalendarioReservaciones());
                             //asigna la imagen de icono de la aplicacion que se muestra en la barra de tareas

                             break;
                         }
                    }
                }
            }
        }

    }

}
