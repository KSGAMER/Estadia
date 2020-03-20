/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KSGAMER
 */

//Se aplica Herencia de la clase padre Thread (Para crear hilos)
public class ControladorHilo extends Thread {
   //Se instancia la clase a ocupar 
   ControladorRecepciones recepciones = new ControladorRecepciones();
   //Se usa el método run para poder usar Thread y este se use en un nucleo aparte
    public void run() {
        //Operación para que se repita la funcion establecida dentro
        while (true) {
            try {
                //Método a ocupar
                recepciones.actualizarEstatus();
                //Hacemos que el while tenga un intervalo de 1000 milisegundos entre cada recorrido
                sleep(700);
            } catch (InterruptedException ex) {
                Logger.getLogger(ControladorHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
