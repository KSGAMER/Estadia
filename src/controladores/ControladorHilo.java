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
public class ControladorHilo extends Thread {

    private ControladorRecepciones recepciones = new ControladorRecepciones();

    public void run() {
        while(true) {
            recepciones.actualizarEstatus();
        }
    }
}
