/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

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
            //Método a ocupar
            recepciones.actualizarEstatus();
        }
    }
}
