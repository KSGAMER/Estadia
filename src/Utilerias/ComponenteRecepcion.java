/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilerias;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *esta clase es necesaria para crear componentes dinámicos en la pantalla de recepcion y son las caracteristicas de las habitaciones
 * @author fenix
 */
public class ComponenteRecepcion extends javax.swing.JPanel {

    public ComponenteRecepcion() {
        initComponents();

    }

    public JPanel ComponenteRecepcionDatos(String Habitacion, String TipoHabitacion, String StatusHabitacion, String PrecioNoche, ActionListener act, Color background) {
        ImageIcon IconCama = new ImageIcon(getClass().getResource("/Imagenes/icons/bed.png"));

        //MODIFICACIONES DEL BOTON VISUALES
        if (!Habitacion.isEmpty()) {
            lb_IconoCama.setIcon(IconCama);
            lb_Habitacion.setText(Habitacion);
            lb_TipoHabitacion.setText(TipoHabitacion);
            lb_StatusHabitacion.setText(StatusHabitacion);
            lb_Precio.setText("$" + PrecioNoche + "0");
           
            this.setBackground(background);
        } else {
            lb_Habitacion.setText("");
            lb_TipoHabitacion.setText("");
            lb_StatusHabitacion.setText("");
            lb_Precio.setText("");
            this.setBackground(background);
        }

        return this;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_IconoCama = new javax.swing.JLabel();
        lb_Habitacion = new javax.swing.JLabel();
        lb_TipoHabitacion = new javax.swing.JLabel();
        lb_StatusHabitacion = new javax.swing.JLabel();
        lb_Precio = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(230, 155));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_IconoCama.setForeground(new java.awt.Color(255, 255, 255));
        add(lb_IconoCama, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 70, 60));

        lb_Habitacion.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        lb_Habitacion.setForeground(new java.awt.Color(255, 255, 255));
        lb_Habitacion.setText("Habitacion");
        add(lb_Habitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, -1, -1));

        lb_TipoHabitacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_TipoHabitacion.setForeground(new java.awt.Color(255, 255, 255));
        lb_TipoHabitacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_TipoHabitacion.setText("Tip de Habitacion");
        add(lb_TipoHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 120, -1));

        lb_StatusHabitacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_StatusHabitacion.setForeground(new java.awt.Color(255, 255, 255));
        lb_StatusHabitacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_StatusHabitacion.setText("Tip de Habitacion");
        add(lb_StatusHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 130, 20));

        lb_Precio.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_Precio.setForeground(new java.awt.Color(255, 255, 255));
        lb_Precio.setText("jLabel1");
        add(lb_Precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lb_Habitacion;
    private javax.swing.JLabel lb_IconoCama;
    private javax.swing.JLabel lb_Precio;
    private javax.swing.JLabel lb_StatusHabitacion;
    private javax.swing.JLabel lb_TipoHabitacion;
    // End of variables declaration//GEN-END:variables
}
