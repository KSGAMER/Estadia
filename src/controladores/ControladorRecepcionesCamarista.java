/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Utilerias.ComponenteRecepcion;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import modelos.ModeloRecepciones;
import objetos.ObjetoRecepcion;
import objetos.ObjetoReservacion;
import vistas.ModuloCamarista.Pn_Alert_DesHabitacionVistaCamarista;

/**
 *
 * @author KSGAMER
 */
//Se aplica Herencia de la clase padre Modelo Recepciones
public class ControladorRecepcionesCamarista extends ModeloRecepciones {

    //Se declaran las clases a ocupar para reemplazar la información
    private ControladorHabitaciones habitaciones = new ControladorHabitaciones();
    private ControladorReservaciones reservaciones = new ControladorReservaciones();
    //NECESARIO PARA EL USO DE LA NOTIFICACION DINAMICA DE BOTON ELIMINAR ()
    Frame Principal;
//FIN
    //Método que actualiza el estatus de las Habitaciones que cuenten con una reservacion

    public void actualizarEstatus() {
        //Se cargan los datos a utilizar
        reservaciones.tablaReservaciones();
        //Se crea una variable para formatear las fechas en dia, mes , año
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");
        //Se crea una variable que actua como fecha actual
        String fecha = formato.format(new Date());
        //Se recorren los resultados de la clase previamente inicializada
        for (ObjetoReservacion objetoReservacion : reservaciones.selectReservacion()) {
            try {
                //Si la fecha corresponde con la fecha extraida de la clase prosigue
                if (formato.parse(fecha).equals(formato.parse(objetoReservacion.getFechaIngresoCompleta()))) {
                    //Se actualiza la habitacion a estado "Reservado"
                    habitaciones.updateHabitacion(objetoReservacion.getIdHabitacion(), "Reservado");
                }
            } catch (ParseException ex) {
                Logger.getLogger(ControladorRecepcionesCamarista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

      //Método que carga las habitaciones y pintandolas dependiendo del estado en el que se encuentren
    public void cargarRecepcion(JPanel contenedor, Color disponible, Color reservado, Color limpieza, Color mantenimiento, Color estilo, int idPiso, String estatus) {
       //Se remueve el contenido del panel
        contenedor.removeAll();
        //Se recorre las recepciones
        for (ObjetoRecepcion objetoRecepcion : selectRecepcion()) {
            //Si el la bandera es igual a la cadena "Todos los Estatus" prosigue
            if (estatus.equals("Todos los Estatus")) {
                //Si la bandera es igual a 0 prosigue
                if (idPiso == 0) {
                    //Se carga el estado a comparar
                    switch (objetoRecepcion.getEstatusHabitacion()) {
                        //Si el estado es igual a "Disponible" prosigue
                        case "Disponible": {
                            //Se agrega al contenedor un nuevo componente con los datos previamente cargados y mostrando el estado "Disponible"
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), String.valueOf(objetoRecepcion.getPrecioxHora()), new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    //NECESARIO PARA MOSTRAR LA DESCRIPCION DE LAS HABITACIONES EN OTRA VENTANA
                                    Pn_Alert_DesHabitacionVistaCamarista aleDesc = new Pn_Alert_DesHabitacionVistaCamarista(Principal, true);
                                    aleDesc.lb_NombreHabitacion.setText(objetoRecepcion.getNombre());
                                    aleDesc.lb_Estado.setText(objetoRecepcion.getEstatusHabitacion());
                                    aleDesc.lb_NombreCategoria.setText(objetoRecepcion.getCategoria());
                                    aleDesc.setVisible(true);
                                }
                            }, disponible));
                            break;
                        }
                        //Si el estado es igual a "Reservado" prosigue
                        case "Reservado": {
                            //Se agrega al contenedor un nuevo componente con los datos previamente cargados y mostrando el estado "Reservado"
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), String.valueOf(objetoRecepcion.getPrecioxHora()), new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    //NECESARIO PARA MOSTRAR LA DESCRIPCION DE LAS HABITACIONES EN OTRA VENTANA
                                    Pn_Alert_DesHabitacionVistaCamarista aleDesc = new Pn_Alert_DesHabitacionVistaCamarista(Principal, true);
                                    aleDesc.lb_NombreHabitacion.setText(objetoRecepcion.getNombre());
                                    aleDesc.lb_Estado.setText(objetoRecepcion.getEstatusHabitacion());
                                    aleDesc.lb_NombreCategoria.setText(objetoRecepcion.getCategoria());
                                    aleDesc.setVisible(true);
                                }
                            }, reservado));
                            break;
                        }
                        //Si el estado es igual a "Limpieza" prosigue
                        case "Limpieza": {
                            //Se agrega al contenedor un nuevo componente con los datos previamente cargados y mostrando el estado "Limpieza"
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), String.valueOf(objetoRecepcion.getPrecioxHora()), new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    //NECESARIO PARA MOSTRAR LA DESCRIPCION DE LAS HABITACIONES EN OTRA VENTANA
                                    Pn_Alert_DesHabitacionVistaCamarista aleDesc = new Pn_Alert_DesHabitacionVistaCamarista(Principal, true);
                                    aleDesc.lb_NombreHabitacion.setText(objetoRecepcion.getNombre());
                                    aleDesc.lb_Estado.setText(objetoRecepcion.getEstatusHabitacion());
                                    aleDesc.lb_NombreCategoria.setText(objetoRecepcion.getCategoria());
                                    aleDesc.setVisible(true);
                                }
                            }, limpieza));
                            break;
                        }
                              //Si el estado es igual a "Mantenimiento" prosigue
                        case "Mantenimiento": {
                            //Se agrega al contenedor un nuevo componente con los datos previamente cargados y mostrando el estado "Limpieza"
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), String.valueOf(objetoRecepcion.getPrecioxHora()), new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    //NECESARIO PARA MOSTRAR LA DESCRIPCION DE LAS HABITACIONES EN OTRA VENTANA
                                    Pn_Alert_DesHabitacionVistaCamarista aleDesc = new Pn_Alert_DesHabitacionVistaCamarista(Principal, true);
                                    aleDesc.lb_NombreHabitacion.setText(objetoRecepcion.getNombre());
                                    aleDesc.lb_Estado.setText(objetoRecepcion.getEstatusHabitacion());
                                    aleDesc.lb_NombreCategoria.setText(objetoRecepcion.getCategoria());
                                    aleDesc.setVisible(true);
                                }
                            }, mantenimiento));
                            break;
                        }
                    }
                    //Si la bandera es igual a cualquiera de los id del piso prosigue
                } else if (idPiso == objetoRecepcion.getIdPiso()) {
                    //Se carga el estado a comparar
                    switch (objetoRecepcion.getEstatusHabitacion()) {
                        //Si el estado es igual a "Disponible" prosigue
                        case "Disponible": {
                            //Se agrega al contenedor un nuevo componente con los datos previamente cargados y mostrando el estado "Disponible"
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), String.valueOf(objetoRecepcion.getPrecioxHora()), new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    //NECESARIO PARA MOSTRAR LA DESCRIPCION DE LAS HABITACIONES EN OTRA VENTANA
                                    Pn_Alert_DesHabitacionVistaCamarista aleDesc = new Pn_Alert_DesHabitacionVistaCamarista(Principal, true);
                                    aleDesc.lb_NombreHabitacion.setText(objetoRecepcion.getNombre());
                                    aleDesc.lb_Estado.setText(objetoRecepcion.getEstatusHabitacion());
                                    aleDesc.lb_NombreCategoria.setText(objetoRecepcion.getCategoria());
                                    aleDesc.setVisible(true);
                                }
                            }, disponible));
                            break;
                        }
                        //Si el estado es igual a "Reservado" prosigue
                        case "Reservado": {
                            //Se agrega al contenedor un nuevo componente con los datos previamente cargados y mostrando el estado "Reservado"
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), String.valueOf(objetoRecepcion.getPrecioxHora()), new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    //NECESARIO PARA MOSTRAR LA DESCRIPCION DE LAS HABITACIONES EN OTRA VENTANA
                                    Pn_Alert_DesHabitacionVistaCamarista aleDesc = new Pn_Alert_DesHabitacionVistaCamarista(Principal, true);
                                    aleDesc.lb_NombreHabitacion.setText(objetoRecepcion.getNombre());
                                    aleDesc.lb_Estado.setText(objetoRecepcion.getEstatusHabitacion());
                                    aleDesc.lb_NombreCategoria.setText(objetoRecepcion.getCategoria());

                                    aleDesc.setVisible(true);
                                }
                            }, reservado));
                            break;
                        }
                        //Si el estado es igual a "Limpieza" prosigue
                        case "Limpieza": {
                            //Se agrega al contenedor un nuevo componente con los datos previamente cargados y mostrando el estado "Limpieza"
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), String.valueOf(objetoRecepcion.getPrecioxHora()), new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    //NECESARIO PARA MOSTRAR LA DESCRIPCION DE LAS HABITACIONES EN OTRA VENTANA
                                    Pn_Alert_DesHabitacionVistaCamarista aleDesc = new Pn_Alert_DesHabitacionVistaCamarista(Principal, true);
                                    aleDesc.lb_NombreHabitacion.setText(objetoRecepcion.getNombre());
                                    aleDesc.lb_Estado.setText(objetoRecepcion.getEstatusHabitacion());
                                    aleDesc.lb_NombreCategoria.setText(objetoRecepcion.getCategoria());
                                    aleDesc.setVisible(true);
                                }
                            }, limpieza));
                            break;
                        }
                                    //Si el estado es igual a "Mantenimiento" prosigue
                        case "Mantenimiento": {
                            //Se agrega al contenedor un nuevo componente con los datos previamente cargados y mostrando el estado "Limpieza"
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), String.valueOf(objetoRecepcion.getPrecioxHora()), new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    //NECESARIO PARA MOSTRAR LA DESCRIPCION DE LAS HABITACIONES EN OTRA VENTANA
                                    Pn_Alert_DesHabitacionVistaCamarista aleDesc = new Pn_Alert_DesHabitacionVistaCamarista(Principal, true);
                                    aleDesc.lb_NombreHabitacion.setText(objetoRecepcion.getNombre());
                                    aleDesc.lb_Estado.setText(objetoRecepcion.getEstatusHabitacion());
                                    aleDesc.lb_NombreCategoria.setText(objetoRecepcion.getCategoria());
                                    aleDesc.setVisible(true);
                                }
                            }, mantenimiento));
                            break;
                        }
                    }
                }
                //Si la bandera es igual a cualquiera de los estatus de habitación prosigue
            } else if (estatus.equals(objetoRecepcion.getEstatusHabitacion())) {
                //Si la bandera es igual a 0 prosigue
                if (idPiso == 0) {
                    //Se carga el estado a comparar
                    switch (objetoRecepcion.getEstatusHabitacion()) {
                        //Si el estado es igual a "Disponible" prosigue
                        case "Disponible": {
                            //Se agrega al contenedor un nuevo componente con los datos previamente cargados y mostrando el estado "Disponible"
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), String.valueOf(objetoRecepcion.getPrecioxHora()), new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    //NECESARIO PARA MOSTRAR LA DESCRIPCION DE LAS HABITACIONES EN OTRA VENTANA
                                    Pn_Alert_DesHabitacionVistaCamarista aleDesc = new Pn_Alert_DesHabitacionVistaCamarista(Principal, true);
                                    aleDesc.lb_NombreHabitacion.setText(objetoRecepcion.getNombre());
                                    aleDesc.lb_Estado.setText(objetoRecepcion.getEstatusHabitacion());
                                    aleDesc.lb_NombreCategoria.setText(objetoRecepcion.getCategoria());
                                    aleDesc.setVisible(true);
                                }
                            }, disponible));
                            break;
                        }
                        //Si el estado es igual a "Reservado" prosigue
                        case "Reservado": {
                            //Se agrega al contenedor un nuevo componente con los datos previamente cargados y mostrando el estado "Reservado"
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), String.valueOf(objetoRecepcion.getPrecioxHora()), new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    //NECESARIO PARA MOSTRAR LA DESCRIPCION DE LAS HABITACIONES EN OTRA VENTANA
                                    Pn_Alert_DesHabitacionVistaCamarista aleDesc = new Pn_Alert_DesHabitacionVistaCamarista(Principal, true);
                                    aleDesc.lb_NombreHabitacion.setText(objetoRecepcion.getNombre());
                                    aleDesc.lb_Estado.setText(objetoRecepcion.getEstatusHabitacion());
                                    aleDesc.lb_NombreCategoria.setText(objetoRecepcion.getCategoria());
                                    aleDesc.setVisible(true);
                                }
                            }, reservado));
                            break;
                        }
                        //Si el estado es igual a "Limpieza" prosigue
                        case "Limpieza": {
                            //Se agrega al contenedor un nuevo componente con los datos previamente cargados y mostrando el estado "Limpieza"
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), String.valueOf(objetoRecepcion.getPrecioxHora()), new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    //NECESARIO PARA MOSTRAR LA DESCRIPCION DE LAS HABITACIONES EN OTRA VENTANA
                                    Pn_Alert_DesHabitacionVistaCamarista aleDesc = new Pn_Alert_DesHabitacionVistaCamarista(Principal, true);
                                    aleDesc.lb_NombreHabitacion.setText(objetoRecepcion.getNombre());
                                    aleDesc.lb_Estado.setText(objetoRecepcion.getEstatusHabitacion());
                                    aleDesc.lb_NombreCategoria.setText(objetoRecepcion.getCategoria());
                                    aleDesc.setVisible(true);
                                }
                            }, limpieza));
                            break;
                        }
                                    //Si el estado es igual a "Mantenimiento" prosigue
                        case "Mantenimiento": {
                            //Se agrega al contenedor un nuevo componente con los datos previamente cargados y mostrando el estado "Limpieza"
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), String.valueOf(objetoRecepcion.getPrecioxHora()), new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    //NECESARIO PARA MOSTRAR LA DESCRIPCION DE LAS HABITACIONES EN OTRA VENTANA
                                    Pn_Alert_DesHabitacionVistaCamarista aleDesc = new Pn_Alert_DesHabitacionVistaCamarista(Principal, true);
                                    aleDesc.lb_NombreHabitacion.setText(objetoRecepcion.getNombre());
                                    aleDesc.lb_Estado.setText(objetoRecepcion.getEstatusHabitacion());
                                    aleDesc.lb_NombreCategoria.setText(objetoRecepcion.getCategoria());
                                    aleDesc.setVisible(true);
                                }
                            }, mantenimiento));
                            break;
                        }
                    }
                    //Si la bandera es igual a quiera de los id de piso prosigue
                } else if (idPiso == objetoRecepcion.getIdPiso()) {
                    //Se carga el estado a comparar
                    switch (objetoRecepcion.getEstatusHabitacion()) {
                        //Si el estado es igual a "Disponible" prosigue
                        case "Disponible": {
                            //Se agrega al contenedor un nuevo componente con los datos previamente cargados y mostrando el estado "Disponible"
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), String.valueOf(objetoRecepcion.getPrecioxHora()), new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    //NECESARIO PARA MOSTRAR LA DESCRIPCION DE LAS HABITACIONES EN OTRA VENTANA
                                    Pn_Alert_DesHabitacionVistaCamarista aleDesc = new Pn_Alert_DesHabitacionVistaCamarista(Principal, true);
                                    aleDesc.lb_NombreHabitacion.setText(objetoRecepcion.getNombre());
                                    aleDesc.lb_Estado.setText(objetoRecepcion.getEstatusHabitacion());
                                    aleDesc.lb_NombreCategoria.setText(objetoRecepcion.getCategoria());
                                    aleDesc.setVisible(true);
                                }
                            }, disponible));
                            break;
                        }
                        //Si el estado es igual a "Reservado" prosigue
                        case "Reservado": {
                            //Se agrega al contenedor un nuevo componente con los datos previamente cargados y mostrando el estado "Reservado"
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), String.valueOf(objetoRecepcion.getPrecioxHora()), new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    //NECESARIO PARA MOSTRAR LA DESCRIPCION DE LAS HABITACIONES EN OTRA VENTANA
                                    Pn_Alert_DesHabitacionVistaCamarista aleDesc = new Pn_Alert_DesHabitacionVistaCamarista(Principal, true);
                                    aleDesc.lb_NombreHabitacion.setText(objetoRecepcion.getNombre());
                                    aleDesc.lb_Estado.setText(objetoRecepcion.getEstatusHabitacion());
                                    aleDesc.lb_NombreCategoria.setText(objetoRecepcion.getCategoria());
                                    aleDesc.setVisible(true);
                                }
                            }, reservado));
                            break;
                        }
                        //Si el estado es igual a "Limpieza" prosigue
                        case "Limpieza": {
                            //Se agrega al contenedor un nuevo componente con los datos previamente cargados y mostrando el estado "Limpieza"
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), String.valueOf(objetoRecepcion.getPrecioxHora()), new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    //NECESARIO PARA MOSTRAR LA DESCRIPCION DE LAS HABITACIONES EN OTRA VENTANA
                                    Pn_Alert_DesHabitacionVistaCamarista aleDesc = new Pn_Alert_DesHabitacionVistaCamarista(Principal, true);
                                    aleDesc.lb_NombreHabitacion.setText(objetoRecepcion.getNombre());
                                    aleDesc.lb_Estado.setText(objetoRecepcion.getEstatusHabitacion());
                                    aleDesc.lb_NombreCategoria.setText(objetoRecepcion.getCategoria());
                                    aleDesc.setVisible(true);
                                }
                            }, limpieza));
                            break;
                        }
                                    //Si el estado es igual a "Mantenimiento" prosigue
                        case "Mantenimiento": {
                            //Se agrega al contenedor un nuevo componente con los datos previamente cargados y mostrando el estado "Limpieza"
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), String.valueOf(objetoRecepcion.getPrecioxHora()), new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    //NECESARIO PARA MOSTRAR LA DESCRIPCION DE LAS HABITACIONES EN OTRA VENTANA
                                    Pn_Alert_DesHabitacionVistaCamarista aleDesc = new Pn_Alert_DesHabitacionVistaCamarista(Principal, true);
                                    aleDesc.lb_NombreHabitacion.setText(objetoRecepcion.getNombre());
                                    aleDesc.lb_Estado.setText(objetoRecepcion.getEstatusHabitacion());
                                    aleDesc.lb_NombreCategoria.setText(objetoRecepcion.getCategoria());
                                    aleDesc.setVisible(true);
                                }
                            }, mantenimiento));
                            break;
                        }
                    }
                }
            }
        }
        //Si el contenedor tiene menos de 11 componentes dentro prosigue
        if (contenedor.getComponentCount() <= 11) {
            //Se recorre empezando desde el conteo total de componentes hasta que sea mayor a 12
            for (int i = contenedor.getComponentCount(); i < 12; i++) {
                //Se agregan un nuevo componente al contenedor
                contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos("", "", "", "", "", null, estilo));
            }
        }
        //Se actualiza la interfaz
        contenedor.updateUI();
    }

    //Método que retorna una tabla
    public DefaultTableModel tablaReservaciones() {
        return cargarTabla();
    }

    //Método que retorna un arreglo de tipo Objeto Recepcion
    public ArrayList<ObjetoRecepcion> selectRecepcion() {
        return selectRecepciones();
    }

}
