/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.ValidadorDePrivilegios;

import controladores.ControladorEstatusPermisos;
import controladores.ControladorModulos;
import controladores.ControladorPermisos;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import objetos.ObjetoEstatusPermiso;
import objetos.ObjetoModulo;
import objetos.ObjetoPermiso;
import vistas.*;

/**
 *
 * @author fenix
 */

/*
ESTE CONTROLADOR REALIZA LA VALIDACION DE LOS PRIVILEGIOS QUE SE HAYAN ASIGNADO EN EL MODULO DE ADMINISTRADOR
PARA ASIGNAR LOS PRIVILEGIOS A CADA MODULO , ESTO INCLUYE LA POSIBILIDAD DE BLOQUEAR O DESBLOQUEA EL USO DE BOTONES
Y LA POSIBILIDAD DE REALIZAR O NO BUSQUEDAS DENTRO DE DEL MODULO 
 */
//NECESARIO PARA VALIDAR LOS PERMISOS 
public class ControladorPrivilegiosCliente {

    ControladorPermisos cperm = new ControladorPermisos();
    ControladorModulos cmod = new ControladorModulos();
    ControladorEstatusPermisos eperm = new ControladorEstatusPermisos();

    public void validarPermisos(String NombreModulo) {
        //CARGA DE TODOS LOS ELEMENTOS NECESARIOS PARA LOS FOR 
        cperm.tablaPermisos();
        cmod.tablaModulos();
        eperm.tablaEstatusPermisos();

        //FOR PARA EL ANALISIS Y COMPARACION DE CAMBIOS EN LOS PRIVILEGIOS
        for (ObjetoPermiso permiso : cperm.selectPermiso()) {
            for (ObjetoModulo modulo : cmod.selectModulo()) {
                for (ObjetoEstatusPermiso estado : eperm.selectEstatusPermiso()) {
                    //se analiza que el usuario sea el mismo que el que inciio sesion 
                    if (permiso.getUsermane().equals(Principal.User)) {
                        //se compara el id del modulo que esta en permisos con el de la tabla permisos 
                        if (permiso.getIdModulo() == modulo.getIdModulo()) {
//si lo anterior esta bien , se analiza que el modulo del sistema concuerde con el modulo de permisos 
//MODULO DE CLIENTE
                            if (modulo.getNombre().equals(NombreModulo)) {
                                //VALIDAR PERMISO DE CONSULTA
                                if (permiso.getConsultar() == estado.getIdEstatusPermiso()) {
                                    if (estado.getNombre().equals("Permitido")) {
                                        if (Pn_NuevoCliente.jt_Buscar.isEnabled() && Pn_NuevoCliente.jt_Buscar.isEditable()) {
                                            //si esta activado no hara nada
                                        } else {
                                            //si el boton estaba desactivado no activará
                                            Pn_NuevoCliente.jt_Buscar.setEnabled(true);
                                            Pn_NuevoCliente.jt_Buscar.setEditable(true);
                                        }
                                    } else if (estado.getNombre().equals("Denegado")) {
                                        //no importa su estado , desactivará el textfield
                                        Pn_NuevoCliente.jt_Buscar.setEnabled(false);
                                        //no importa su estado , no se podrá escribir
                                        Pn_NuevoCliente.jt_Buscar.setEditable(false);
                                        Pn_NuevoCliente.jt_Buscar.addMouseListener(new MouseListener() {
                                            @Override
                                            public void mouseClicked(MouseEvent e) {

                                            }

                                            @Override
                                            public void mousePressed(MouseEvent e) {
                                                //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                            }

                                            @Override
                                            public void mouseReleased(MouseEvent e) {
                                                //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                            }

                                            @Override
                                            public void mouseEntered(MouseEvent e) {
                                                //      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                            }

                                            @Override
                                            public void mouseExited(MouseEvent e) {
                                                //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                            }

                                        });

                                    }
                                }

                                //VALIDAR PERMISO DE INSERCION 
                                if (permiso.getInsertar() == estado.getIdEstatusPermiso()) {
                                    if (estado.getNombre().equals("Permitido")) {
                                        if (Pn_NuevoCliente.btn_Ingresar.isEnabled()) {
                                            //si esta activado no hara nada
                                        } else {
                                            //si el boton estaba desactivado no activará
                                            Pn_NuevoCliente.btn_Ingresar.setEnabled(true);
                                        }
                                    } else if (estado.getNombre().equals("Denegado")) {

                                        //no importa su estado , desactivará al boton
                                        Pn_NuevoCliente.btn_Ingresar.setEnabled(false);
                                    }
                                }
                                //VALIDAR PERMISO DE ACTUALIZACION
                                if (permiso.getActualizar() == estado.getIdEstatusPermiso()) {
                                    if (estado.getNombre().equals("Permitido")) {
                                        if (Pn_NuevoCliente.btn_Modificar.isEnabled()) {
                                            //si esta activado no hara nada
                                        } else {
                                            //si el boton estaba desactivado no activará
                                            Pn_NuevoCliente.btn_Modificar.setEnabled(true);
                                        }
                                    } else if (estado.getNombre().equals("Denegado")) {
                                        //no importa su estado , desactivará al boton
                                        Pn_NuevoCliente.btn_Modificar.setEnabled(false);
                                    }
                                }
                                //VALIDAR PERMISO DE ELIMINACION
                                if (permiso.getEliminar() == estado.getIdEstatusPermiso()) {
                                    if (estado.getNombre().equals("Permitido")) {
                                        if (Pn_NuevoCliente.btn_Eliminar.isEnabled()) {
                                            //si esta activado no hara nada
                                        } else {
                                            //si el boton estaba desactivado no activará
                                            Pn_NuevoCliente.btn_Eliminar.setEnabled(true);
                                        }
                                    } else if (estado.getNombre().equals("Denegado")) {
                                        //no importa su estado , desactivará al boton
                                        Pn_NuevoCliente.btn_Eliminar.setEnabled(false);
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}
