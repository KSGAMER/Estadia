/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Utilerias.ComponenteRecepcion;
import java.awt.Color;
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

/**
 *
 * @author KSGAMER
 */

//Se aplica Herencia de la clase padre Modelo Recepciones
public class ControladorRecepciones extends ModeloRecepciones {
    //Se declaran las clases a ocupar para reemplazar la información
    private ControladorHabitaciones habitaciones = new ControladorHabitaciones();
    private ControladorReservaciones reservaciones = new ControladorReservaciones();

    //Método que actualiza el estatus de las Habitaciones que cuenten con una reservacion
    public void actualizarEstatus() {
        //Se cargan los datos a utilizar
        reservaciones.tablaReservaciones();
        //Se crea una variable para formatear las fechas en dia, mes , año
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");
        //Se crea una variable que actua como fecha actual
        String fecha = formato.format(new Date());
        
        for (ObjetoReservacion objetoReservacion : reservaciones.selectReservacion()) {
            try {
                if (formato.parse(fecha).equals(formato.parse(objetoReservacion.getFechaIngresoCompleta()))) {
                    habitaciones.updateHabitacion(objetoReservacion.getIdHabitacion(), "Reservado");
                }
            } catch (ParseException ex) {
                Logger.getLogger(ControladorRecepciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void cargarRecepcion(JPanel contenedor, ComponenteRecepcion componenteRecepcion, Color disponible, Color reservado, Color limpieza, Color estilo, int idPiso, String estatus) {
        contenedor.removeAll();
        for (ObjetoRecepcion objetoRecepcion : selectRecepcion()) {
            if (estatus.equals("Todos los Estatus")) {
                if (idPiso == 0) {
                    switch (objetoRecepcion.getEstatusHabitacion()) {
                        case "Disponible": {
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), null, disponible));
                            break;
                        }
                        case "Reservado": {
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), null, reservado));
                            break;
                        }
                        case "Limpieza": {
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), null, limpieza));
                            break;
                        }
                    }
                } else if (idPiso == objetoRecepcion.getIdPiso()) {
                    switch (objetoRecepcion.getEstatusHabitacion()) {
                        case "Disponible": {
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), null, disponible));
                            break;
                        }
                        case "Reservado": {
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), null, reservado));
                            break;
                        }
                        case "Limpieza": {
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), null, limpieza));
                            break;
                        }
                    }
                }
            } else if (estatus.equals(objetoRecepcion.getEstatusHabitacion())) {
                if (idPiso == 0) {
                    switch (objetoRecepcion.getEstatusHabitacion()) {
                        case "Disponible": {
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), null, disponible));
                            break;
                        }
                        case "Reservado": {
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), null, reservado));
                            break;
                        }
                        case "Limpieza": {
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), null, limpieza));
                            break;
                        }
                    }
                } else if (idPiso == objetoRecepcion.getIdPiso()) {
                    switch (objetoRecepcion.getEstatusHabitacion()) {
                        case "Disponible": {
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), null, disponible));
                            break;
                        }
                        case "Reservado": {
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), null, reservado));
                            break;
                        }
                        case "Limpieza": {
                            contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos(objetoRecepcion.getNombre(), objetoRecepcion.getCategoria(), objetoRecepcion.getEstatusHabitacion(), String.valueOf(objetoRecepcion.getPrecioSugerido()), null, limpieza));
                            break;
                        }
                    }
                }
            }
        }
        if (contenedor.getComponentCount() <= 11) {
            for (int i = contenedor.getComponentCount(); i < 12; i++) {
                contenedor.add(new ComponenteRecepcion().ComponenteRecepcionDatos("", "", "", "", null, estilo));
            }
        }
        contenedor.updateUI();
    }

    public DefaultTableModel tablaReservaciones() {
        return cargarTabla();
    }

    public ArrayList<ObjetoRecepcion> selectRecepcion() {
        return selectRecepciones();
    }

    
}
