/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilerias;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import controladores.ControladorHabitaciones;
import controladores.ControladorIncidencias;
import controladores.ControladorEstadoIncidencia;
import objetos.ObjetoEstadoIncidencia;
import objetos.ObjetoHabitacion;
import objetos.ObjetoIncidencia;

/**
 * esta clase es necesaria para crear componentes dinámicos en la pantalla de
 * recepcion y son las caracteristicas de las habitaciones
 *
 * @author fenix
 */
public class ComponenteRecepcion extends javax.swing.JPanel {

    private ControladorHabitaciones ch = new ControladorHabitaciones();
    private ControladorIncidencias cin = new ControladorIncidencias();
    private ControladorEstadoIncidencia cesin = new ControladorEstadoIncidencia();

    public ComponenteRecepcion() {
        initComponents();

    }

    private ActionListener act;

    public JPanel ComponenteRecepcionDatos(String Habitacion, String TipoHabitacion, String StatusHabitacion, String PrecioNoche, String PrecioxHora, ActionListener act, Color background) {
        cargarAlertaIncidencia();
//necesarios para cargar datos en las variables ubicadas en el metodo de validarExistencias...
        ch.tablaHabitaciones();
        cesin.tablaEstadoIncidencia();
        cin.tablaIncidencias();
//fin
        ImageIcon IconCama = new ImageIcon(getClass().getResource("/Imagenes/icons/bed80x80.png"));

//MODIFICACIONES DEL BOTON VISUALES
        if (!Habitacion.isEmpty()) {
            lb_IconoCama.setIcon(IconCama);
            lb_Habitacion.setText(Habitacion);
            lb_TipoHabitacion.setText(TipoHabitacion);
            lb_StatusHabitacion.setText(StatusHabitacion);
            lb_NombrexNoche.setText("x Noche");
            lb_NombrexHora.setText("x Hora");
            lb_Precio.setText("$" + PrecioNoche + "0");
            lb_PrecioxHora.setText("$" + PrecioxHora + "0");
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
            this.setBackground(background);
            /*metodo que busca y encuentra si hay incidencias en la habitacion y muestra o deja oculta la notificacion de 
warning o incidencia*/
            validarExistenciaIncidencias(Habitacion);
//fin
//permite el click en este jpanel
            if (act != null) {
                this.act = act;
            }
//fin
        } else {
            lb_Habitacion.setText("");
            lb_TipoHabitacion.setText("");
            lb_StatusHabitacion.setText("");
            lb_NombrexNoche.setText("");
            lb_NombrexHora.setText("");
            lb_Precio.setText("");
            lb_PrecioxHora.setText("");
            this.setBackground(background);
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            lb_alertaIncidencia.setVisible(false);
        }

        return this;
    }
    ///TERMINA MODIFICACIONES DEL BOTON

    private void cargarAlertaIncidencia() {
        lb_alertaIncidencia.setVisible(false);
    }

    /*método que valida si existe alguna incidencia comparando el id de la habitacion con la tabla de incidencias
validarExistenciaIncidencias(NombreHabitacion);*/
    private void validarExistenciaIncidencias(String Habitacion) {
        //recorre las habitaciones
        for (ObjetoHabitacion habitacion : ch.selectHabitacion()) {
            //recorre la yabla de incidencias
            for (ObjetoIncidencia Incidencia : cin.seleccionarIncidencias()) {
                //reccorre el estado de las incidencias
                for (ObjetoEstadoIncidencia edoIncidencia : cesin.seleccionarEstadoIncidencia()) {
                    //si lo anterior se recorrio correctamente , se compara que la habitacion sea la misma que la 
                    //agregada en la tabla de incidencias 
                    if (Incidencia.getIdHabitacion() == habitacion.getIdHabitacion()) {
                        //luego de la comparacion anterior , se comprara que el estado de la incidencia sea igual 
                        //al agregado en la tabla de incidencias 
                        if (Incidencia.getIdEstadoIncidencia() == edoIncidencia.getIdEstadoIncidencia()) {
                            //tambien se compara que el nombre ede la habitacion del componente de recepciones 
                            //sea el mismo que la ubicada en la tabla de habitaciones , por consiguiente 
                            //la misma registrada en la tabla de incidencias 
                            if (habitacion.getNombre().equals(Habitacion)) {
                                //finalmente se compara que el nombre del estado de la incidencia sera igual a
                                //pendiente, que es el valor importante para hacer aparecer el boton de incidencias
                                //de lo contrario no aparecerá nada y el boton seguira bloqueado
                                if (edoIncidencia.getNombre().equals("Pendiente")) {
                                    lb_alertaIncidencia.setVisible(true);
                                    break;
                                }
                            }
                        }

                    }
                }
            }

        }
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
        lb_PrecioxHora = new javax.swing.JLabel();
        lb_Precio = new javax.swing.JLabel();
        lb_NombrexHora = new javax.swing.JLabel();
        lb_NombrexNoche = new javax.swing.JLabel();
        lb_alertaIncidencia = new javax.swing.JLabel();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(230, 155));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_IconoCama.setForeground(new java.awt.Color(255, 255, 255));
        add(lb_IconoCama, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 80, 80));

        lb_Habitacion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_Habitacion.setForeground(new java.awt.Color(255, 255, 255));
        lb_Habitacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_Habitacion.setText("Habitacion");
        add(lb_Habitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 130, -1));

        lb_TipoHabitacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_TipoHabitacion.setForeground(new java.awt.Color(255, 255, 255));
        lb_TipoHabitacion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_TipoHabitacion.setText("Tip de Habitacion");
        add(lb_TipoHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 100, -1));

        lb_StatusHabitacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_StatusHabitacion.setForeground(new java.awt.Color(255, 255, 255));
        lb_StatusHabitacion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_StatusHabitacion.setText("Tip de Habitacion");
        add(lb_StatusHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 110, 20));

        lb_PrecioxHora.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lb_PrecioxHora.setForeground(new java.awt.Color(255, 255, 255));
        lb_PrecioxHora.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_PrecioxHora.setText("jLabel1");
        add(lb_PrecioxHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 50, -1));

        lb_Precio.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lb_Precio.setForeground(new java.awt.Color(255, 255, 255));
        lb_Precio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_Precio.setText("jLabel1");
        add(lb_Precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 50, -1));

        lb_NombrexHora.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_NombrexHora.setForeground(new java.awt.Color(255, 255, 255));
        lb_NombrexHora.setText("x Hora");
        add(lb_NombrexHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 130, 50, -1));

        lb_NombrexNoche.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_NombrexNoche.setForeground(new java.awt.Color(255, 255, 255));
        lb_NombrexNoche.setText("x Noche ");
        add(lb_NombrexNoche, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 110, 60, -1));

        lb_alertaIncidencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/IconIncidenciaRecepcion32x32.png"))); // NOI18N
        add(lb_alertaIncidencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        if (act != null) {
            act.actionPerformed(null);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_formMousePressed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if (act != null) {
            act.actionPerformed(null);
        }          // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lb_Habitacion;
    private javax.swing.JLabel lb_IconoCama;
    private javax.swing.JLabel lb_NombrexHora;
    private javax.swing.JLabel lb_NombrexNoche;
    private javax.swing.JLabel lb_Precio;
    private javax.swing.JLabel lb_PrecioxHora;
    private javax.swing.JLabel lb_StatusHabitacion;
    private javax.swing.JLabel lb_TipoHabitacion;
    private javax.swing.JLabel lb_alertaIncidencia;
    // End of variables declaration//GEN-END:variables
}
