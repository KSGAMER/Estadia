/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.Alertas;

import Utilerias.AWTUtilities;
import controladores.ControladorHabitaciones;
import controladores.ControladorIncidencias;
import controladores.ControladorEstadoIncidencia;
import vistas.ModuloCamarista.Pn_Alert_DesHabitacionVistaCamarista;
import ds.desktop.notify.DesktopNotify;
import java.awt.Frame;
import java.util.TimerTask;
import java.util.Timer;
import java.awt.Toolkit;
import objetos.ObjetoEstadoIncidencia;
import objetos.ObjetoHabitacion;
import objetos.ObjetoIncidencia;

/**
 *
 * @author fenix
 */
public class Pn_Alert_DescripcionHabitacion extends javax.swing.JDialog {

    private ControladorHabitaciones ch = new ControladorHabitaciones();
    private ControladorIncidencias cin = new ControladorIncidencias();
    private ControladorEstadoIncidencia cesin = new ControladorEstadoIncidencia();
    private Timer timer = null;
    private TimerTask task;
    private int i = 32;
    //NECESARIO PARA EL USO DE LA NOTIFICACION DINAMICA DE BOTON ELIMINAR ()
    Frame Principal;
//FIN
    //variable public sobre la cual podra acceder el controlador que maneja los componentes de las recepciones para
    //asignarle un nombre de habitacion y cargar asi los datos del metodo validar validarExistenciaIncidencias();
    public static String NombreHabitacion = "";

    //fin
    /**
     * Creates new form AlertSuccess
     */
    public Pn_Alert_DescripcionHabitacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setPantalla();
        initComponents();
//inicia los datos necesarios, en este caso el boton de incidencias en caso de que la base de datos no tenga datos
        datosInciales();
//necesarios para cargar datos en las variables ubicadas en el metodo de validarExistencias...
        ch.tablaHabitaciones();
        cesin.tablaEstadoIncidencia();
        cin.tablaIncidencias();
//fin

        AWTUtilities.setOpaque(this, false);
        Ubicar(0);
        datosInciales();
        jta_observaciones.setEnabled(false);
        //para ajustar el tecto al tamaño del jtextarea
        jta_observaciones.setLineWrap(true);
        //FIN

    }

    private void datosInciales() {
        btn_edoIncidencias.setEnabled(false);
    }

    /*método que valida si existe alguna incidencia comparando el id de la habitacion con la tabla de incidencias
validarExistenciaIncidencias(NombreHabitacion);*/
//SE MANDA A LLAMAR DESDE EL EVENTO CLICK DEL COMPONENTE RESERVACIONES EN EL ControladorRecepciones
    public void validarExistenciaIncidencias(String Habitacion) {

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
                                    btn_edoIncidencias.setEnabled(true);
                                    break;
                                }
                            }
                        }

                    }
                }
            }

        }
    }

    private void setPantalla() {

        //para eliminar el tittle bar
        this.setUndecorated(true);
        //para que no le cambien el tamaño
        this.setResizable(false);

    }

    public void validarCambioEstado() {
        if (!(lb_Estado.getText().equals("Limpieza"))) {
            cb_status.setEnabled(false);
        } else {
            cb_status.setEnabled(true);
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

        jPanel1 = new javax.swing.JPanel();
        cb_status = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cancelar = new principal.MaterialButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lb_close = new javax.swing.JButton();
        lb_NombreCategoria = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lb_NombreHabitacion = new javax.swing.JLabel();
        lb_Estado = new javax.swing.JLabel();
        jta_observaciones = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        materialButton1 = new principal.MaterialButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_nuevaIncidencia = new principal.MaterialButton();
        btn_edoIncidencias = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(36, 47, 65));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cb_status.setBackground(new java.awt.Color(84, 110, 122));
        cb_status.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cb_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Estatus", "Disponible" }));
        cb_status.setBorder(null);
        cb_status.setFocusable(false);
        cb_status.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_statusItemStateChanged(evt);
            }
        });
        jPanel1.add(cb_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 190, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(58, 159, 171));
        jLabel6.setText("Estatus:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, -1, -1));

        cancelar.setBackground(new java.awt.Color(211, 18, 18));
        cancelar.setForeground(new java.awt.Color(255, 255, 255));
        cancelar.setText("CANCELAR");
        cancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelar.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        jPanel1.add(cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 460, 180, 46));

        jPanel3.setBackground(new java.awt.Color(233, 235, 238));

        jLabel2.setBackground(new java.awt.Color(36, 47, 65));
        jLabel2.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Descripción General");

        lb_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/icons8_Cancel_32px.png"))); // NOI18N
        lb_close.setToolTipText("Close");
        lb_close.setBorder(null);
        lb_close.setBorderPainted(false);
        lb_close.setContentAreaFilled(false);
        lb_close.setFocusable(false);
        lb_close.setRequestFocusEnabled(false);
        lb_close.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/icons8_Cancel_30px_3.png"))); // NOI18N
        lb_close.setVerifyInputWhenFocusTarget(false);
        lb_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lb_closeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addGap(21, 21, 21)
                .addComponent(lb_close, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lb_close, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 50));

        lb_NombreCategoria.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_NombreCategoria.setForeground(new java.awt.Color(255, 255, 255));
        lb_NombreCategoria.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_NombreCategoria.setText("Categoria");
        jPanel1.add(lb_NombreCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, 90, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(58, 159, 171));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Descripción:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 80, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(58, 159, 171));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Nombre Habitación");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 130, -1));

        lb_NombreHabitacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_NombreHabitacion.setForeground(new java.awt.Color(255, 255, 255));
        lb_NombreHabitacion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_NombreHabitacion.setText("Habitacion");
        jPanel1.add(lb_NombreHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 130, -1));

        lb_Estado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_Estado.setForeground(new java.awt.Color(255, 255, 255));
        lb_Estado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_Estado.setText("Estado");
        jPanel1.add(lb_Estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, 130, -1));

        jta_observaciones.setColumns(20);
        jta_observaciones.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jta_observaciones.setForeground(new java.awt.Color(153, 153, 153));
        jta_observaciones.setRows(5);
        jta_observaciones.setText("Ingresar Observaciones");
        jta_observaciones.setBorder(null);
        jta_observaciones.setFocusable(false);
        jta_observaciones.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jta_observacionesFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jta_observacionesFocusLost(evt);
            }
        });
        jta_observaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jta_observacionesMouseClicked(evt);
            }
        });
        jta_observaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jta_observacionesKeyPressed(evt);
            }
        });
        jPanel1.add(jta_observaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 370, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(58, 159, 171));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Estado Actual:");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 90, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(58, 159, 171));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("Categoria:");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 90, -1));

        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setText("Solo puede cambiar el estado, si el \"Estado Actual\" es igual a limpieza. ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        materialButton1.setBackground(new java.awt.Color(40, 180, 99));
        materialButton1.setForeground(new java.awt.Color(255, 255, 255));
        materialButton1.setText("ACEPTAR");
        materialButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        materialButton1.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        materialButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materialButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(materialButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 180, 46));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Cambio de estado");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Generar Incidencia");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, -1, -1));

        btn_nuevaIncidencia.setBackground(new java.awt.Color(0, 153, 0));
        btn_nuevaIncidencia.setBorder(null);
        btn_nuevaIncidencia.setForeground(new java.awt.Color(255, 255, 255));
        btn_nuevaIncidencia.setText("+");
        btn_nuevaIncidencia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_nuevaIncidencia.setFont(new java.awt.Font("Roboto Medium", 3, 36)); // NOI18N
        btn_nuevaIncidencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevaIncidenciaActionPerformed(evt);
            }
        });
        jPanel1.add(btn_nuevaIncidencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 60, 40));

        btn_edoIncidencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/IconBaseIncidencia48x48.png"))); // NOI18N
        btn_edoIncidencias.setBorder(null);
        btn_edoIncidencias.setBorderPainted(false);
        btn_edoIncidencias.setContentAreaFilled(false);
        btn_edoIncidencias.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_edoIncidencias.setFocusPainted(false);
        btn_edoIncidencias.setFocusable(false);
        btn_edoIncidencias.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/IconSecundarioIncidencia48x48.png"))); // NOI18N
        btn_edoIncidencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edoIncidenciasActionPerformed(evt);
            }
        });
        jPanel1.add(btn_edoIncidencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 60, 40));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 400, 10));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 400, 10));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Información por Habitación");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Información de incidencias  por Habitación");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Ver incidencias");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    

    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        setVisible(false);
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void materialButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materialButton1ActionPerformed

        if (cb_status.isEnabled() == true && cb_status.getSelectedIndex() == 1) {

            ch.updateHabitacion(lb_NombreHabitacion.getText(), String.valueOf(cb_status.getSelectedItem()));
            Cerrar();

        } else if (cb_status.isEnabled() == false && cb_status.getSelectedIndex() == 0) {
            Cerrar();

        } else if (cb_status.isEnabled() == true && cb_status.getSelectedIndex() == 0) {
            DesktopNotify.showDesktopMessage("Error", "Debe seleccionar un estado valido", DesktopNotify.ERROR);

        }
    }//GEN-LAST:event_materialButton1ActionPerformed

    private void jta_observacionesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jta_observacionesFocusGained

    }//GEN-LAST:event_jta_observacionesFocusGained

    private void jta_observacionesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jta_observacionesFocusLost

    }//GEN-LAST:event_jta_observacionesFocusLost

    private void jta_observacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jta_observacionesMouseClicked

    }//GEN-LAST:event_jta_observacionesMouseClicked

    private void jta_observacionesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jta_observacionesKeyPressed

    }//GEN-LAST:event_jta_observacionesKeyPressed

    private void cb_statusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_statusItemStateChanged

        /* if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.idStatus = cb_status.getSelectedIndex();
        }*/
    }//GEN-LAST:event_cb_statusItemStateChanged

    private void lb_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lb_closeActionPerformed
        Cerrar();
    }//GEN-LAST:event_lb_closeActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        Cerrar();
    }//GEN-LAST:event_cancelarActionPerformed

    private void btn_nuevaIncidenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevaIncidenciaActionPerformed
        try {
            this.dispose();
            Pn_Alert_DesHabitacionVistaCamarista aleDesc = new Pn_Alert_DesHabitacionVistaCamarista(Principal, false);
            aleDesc.lb_NombreHabitacion.setText(lb_NombreHabitacion.getText());
            aleDesc.lb_NombreCategoria.setText(lb_NombreCategoria.getText());
            aleDesc.lb_Estado.setText(lb_Estado.getText());
            aleDesc.setVisible(true);
        } catch (Exception e) {
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_nuevaIncidenciaActionPerformed

    private void btn_edoIncidenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edoIncidenciasActionPerformed

        try {
            this.dispose();
            Pn_Alert_TabladeIncidencias aleTablain = new Pn_Alert_TabladeIncidencias(Principal, false);
            aleTablain.NombreHabitacion = lb_NombreHabitacion.getText();
            aleTablain.setVisible(true);
        } catch (Exception e) {
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_edoIncidenciasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pn_Alert_DescripcionHabitacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pn_Alert_DescripcionHabitacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pn_Alert_DescripcionHabitacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pn_Alert_DescripcionHabitacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Pn_Alert_DescripcionHabitacion dialog = new Pn_Alert_DescripcionHabitacion(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btn_edoIncidencias;
    private principal.MaterialButton btn_nuevaIncidencia;
    private principal.MaterialButton cancelar;
    private javax.swing.JComboBox<String> cb_status;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public static javax.swing.JTextArea jta_observaciones;
    public static javax.swing.JLabel lb_Estado;
    public static javax.swing.JLabel lb_NombreCategoria;
    public static javax.swing.JLabel lb_NombreHabitacion;
    private javax.swing.JButton lb_close;
    private principal.MaterialButton materialButton1;
    // End of variables declaration//GEN-END:variables

    private void Cerrar() {
        this.dispose();

    }

    private void Ubicar(int y) {

        Toolkit tk = Toolkit.getDefaultToolkit();
        int xsize = (int) tk.getScreenSize().getWidth() / 3;
        this.setLocation(xsize, y - 120);

    }

    private void Trasparencia(float trasp) {
        AWTUtilities.setOpacity(this, trasp);
    }
}
