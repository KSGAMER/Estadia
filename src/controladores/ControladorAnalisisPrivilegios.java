/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

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
public class ControladorAnalisisPrivilegios {
    //necesario para validar permisos

    ControladorPermisos cperm = new ControladorPermisos();
    ControladorModulos cmod = new ControladorModulos();
    ControladorEstatusPermisos eperm = new ControladorEstatusPermisos();

    public void validarPermisos() {
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
                            if (modulo.getNombre().equals("Categorias")) {

                                //VALIDAR PERMISO DE CONSULTA
                                if (permiso.getConsultar() == estado.getIdEstatusPermiso()) {
                                    if (estado.getNombre().equals("Permitido")) {
                                        if (Pn_NuevaCategoria.jt_Buscar.isEnabled() && Pn_NuevaCategoria.jt_Buscar.isEditable()) {
                                            //si esta activado no hara nada
                                        } else {
                                            //si el boton estaba desactivado no activará
                                            Pn_NuevaCategoria.jt_Buscar.setEnabled(true);
                                            Pn_NuevaCategoria.jt_Buscar.setEditable(true);
                                        }
                                    } else if (estado.getNombre().equals("Denegado")) {
                                        //no importa su estado , desactivará el textfield
                                        Pn_NuevaCategoria.jt_Buscar.setEnabled(false);
                                        //no importa su estado , no se podrá escribir
                                        Pn_NuevaCategoria.jt_Buscar.setEditable(false);
                                        Pn_NuevaCategoria.jt_Buscar.addMouseListener(new MouseListener() {
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
                                        if (Pn_NuevaCategoria.btn_Ingresar.isEnabled()) {
                                            //si esta activado no hara nada
                                        } else {
                                            //si el boton estaba desactivado no activará
                                            Pn_NuevaCategoria.btn_Ingresar.setEnabled(true);
                                        }
                                    } else if (estado.getNombre().equals("Denegado")) {
                                        //no importa su estado , desactivará al boton
                                        Pn_NuevaCategoria.btn_Ingresar.setEnabled(false);
                                    }
                                }
                                //VALIDAR PERMISO DE ACTUALIZACION
                                if (permiso.getActualizar() == estado.getIdEstatusPermiso()) {
                                    if (estado.getNombre().equals("Permitido")) {
                                        if (Pn_NuevaCategoria.btn_Modificar.isEnabled()) {
                                            //si esta activado no hara nada
                                        } else {
                                            //si el boton estaba desactivado no activará
                                            Pn_NuevaCategoria.btn_Modificar.setEnabled(true);
                                        }
                                    } else if (estado.getNombre().equals("Denegado")) {
                                        //no importa su estado , desactivará al boton
                                        Pn_NuevaCategoria.btn_Modificar.setEnabled(false);
                                    }
                                }
                                //VALIDAR PERMISO DE ELIMINACION
                                if (permiso.getActualizar() == estado.getIdEstatusPermiso()) {
                                    if (estado.getNombre().equals("Permitido")) {
                                        if (Pn_NuevaCategoria.btn_Eliminar.isEnabled()) {
                                            //si esta activado no hara nada
                                        } else {
                                            //si el boton estaba desactivado no activará
                                            Pn_NuevaCategoria.btn_Eliminar.setEnabled(true);
                                        }
                                    } else if (estado.getNombre().equals("Denegado")) {
                                        //no importa su estado , desactivará al boton
                                        Pn_NuevaCategoria.btn_Eliminar.setEnabled(false);
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
