/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.ModuloAdmin;

import controladores.ControladorCaja;
import controladores.ControladorEscritura;
import controladores.ControladorFormularioTab;
import controladores.ControladorUsuarios;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import ds.desktop.notify.DesktopNotify;
import javax.swing.table.TableColumnModel;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import objetos.ObjetoUsuario;
import vistas.Pn_Alert_Eliminar;
import java.text.SimpleDateFormat;
import java.util.Date;
import objetos.ObjetoCaja;
/*
    NOTA: SE DEBE CONFIGURAR GMAIL PARA PERMITIR EL ACCESO A APLICACIONES MENOS SEGURAS
    PASOS:
   (1)ICONO DE GMAIL
   (2)CLICK EN "MI CUENTA"
   (3)CLICK EN "INICIO DE SESIÓN Y SEGURIDAD"
   (4)ACTIVAR "PERMITIR EL ACCESO DE APLICACIONES MENOS SEGURAS"
 */
public class Pn_MovimientoAbrirCaja extends javax.swing.JPanel {

    ControladorUsuarios cuser = new ControladorUsuarios();
 private ControladorFormularioTab cft = new ControladorFormularioTab();
    ControladorEscritura ce = new ControladorEscritura();
    DefaultTableModel NewTable = new DefaultTableModel();
    ControladorCaja cecaja = new ControladorCaja();

    private int seleccion;
    //NECESARIO PARA EL USO DE LA NOTIFICACION DINAMICA DE BOTON ELIMINAR ()
    Frame principal;

    //fin
    //necesario para obtener la fecha con hora para las nuevas cajas
    Date now = new Date(); // java.util.Date, NOT java.sql.Date or java.sql.Timestamp!
    String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(now);
    String horaActual = new SimpleDateFormat("HH:mm:ss").format(now);
    /**
     * Creates new form Pn_NuevaCategoria
     */
    public Pn_MovimientoAbrirCaja() {
        initComponents();
        RowHeaderApariencia();
        RowApariencia();
        cuser.tablaUsuarios();
        cTabla();
        cargarUsuarios();
        tamañoTabla();
        datosIniciales();

    }

    private void cargarUsuarios() {

        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        cb.addElement("Seleccionar Usuario");
        for (ObjetoUsuario campos : cuser.selectUsuario()) {
            cb.addElement(campos.getUsername());
        }
        cb_usuario.setModel(cb);

    }

    private void tamañoTabla() {
        TableColumnModel columnModel = jtabla_Cajas.getColumnModel();
        jtabla_Cajas.setEnabled(false);
         columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(120);
        columnModel.getColumn(4).setPreferredWidth(150);
        columnModel.getColumn(5).setPreferredWidth(80);
        columnModel.getColumn(6).setPreferredWidth(90);
    }

    private void cTabla() {
jtabla_Cajas.setModel(cecaja.tablaCaja());
    }
    private void bloquearBotones(){
        btn_Modificar.setEnabled(false);
        btn_Eliminar.setEnabled(false);
    }

    private void RowApariencia() {

        jtabla_Cajas.setFocusable(false);
        //espacio entre comulnas
        jtabla_Cajas.setIntercellSpacing(new Dimension(0, 1));
        //altura de columnas 
        jtabla_Cajas.setRowHeight(25);
        //margen entre filas
        jtabla_Cajas.setRowMargin(0);
        //sin lineas verticles
        jtabla_Cajas.setShowVerticalLines(false);
        jtabla_Cajas.setSelectionBackground(new Color(97, 212, 195));

    }

    private void RowHeaderApariencia() {
        jtabla_Cajas.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 14));
        jtabla_Cajas.getTableHeader().setOpaque(false);
        jtabla_Cajas.getTableHeader().setBackground(Color.BLACK);
        jtabla_Cajas.getTableHeader().setForeground(new Color(255, 255, 255));

    }

    private Boolean validarMonto() {

        Boolean val = true;
        if (!(jt_MontoInicial.getText().equals("Ingresar Monto Inicial")) && !(jt_MontoInicial.getText().equals(""))) {
            lb_errorMonto.setForeground(new Color(84, 110, 122));

        } else {
            lb_errorMonto.setForeground(Color.RED);
            val = false;
        }

        return val;
    }

    private Boolean validarUsuario() {
        Boolean val = true;
        if (!(cb_usuario.getSelectedItem().equals("Seleccionar Usuario"))) {
            lb_errorUsuario.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorUsuario.setForeground(Color.RED);
            val = false;
        }

        return val;
    }

    

    private void datosIniciales() {
        lb_errorUsuario.setText("*");
        lb_errorMonto.setText("*");
        lb_Id.setText("*");
        lb_errorUsuario.setForeground(new Color(84, 110, 122));
        lb_errorMonto.setForeground(new Color(84, 110, 122));
        jt_MontoInicial.setText("Ingresar Monto");
        cb_usuario.setSelectedIndex(0);
         btn_Modificar.setEnabled(false);
        btn_Eliminar.setEnabled(false);
        btn_AperturaCaja.setEnabled(true);
    }

    private Boolean validarCajasAbiertas() {
        Boolean val = true;
        for (ObjetoCaja caja : cecaja.seleccionarCaja()) {
            if (caja.getIdEstadoCaja() == 1) {
                val = false;
            } else {
                val = true;
            }
        }
        return val;
    }
    
    
    

    /*
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtabla_Cajas = new javax.swing.JTable();
        btn_AperturaCaja = new principal.MaterialButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jt_MontoInicial = new javax.swing.JTextField();
        cb_usuario = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_Modificar = new principal.MaterialButton();
        btn_Eliminar = new principal.MaterialButton();
        lb_errorMonto = new javax.swing.JLabel();
        lb_errorUsuario = new javax.swing.JLabel();
        lb_Id = new javax.swing.JLabel();
        jb_limpiarCampos = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(84, 110, 122));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1020, 10));

        jtabla_Cajas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtabla_Cajas.getTableHeader().setResizingAllowed(false);
        jtabla_Cajas.getTableHeader().setReorderingAllowed(false);
        jtabla_Cajas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtabla_CajasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jtabla_CajasMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jtabla_Cajas);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, 640, 430));

        btn_AperturaCaja.setBackground(new java.awt.Color(40, 180, 99));
        btn_AperturaCaja.setForeground(new java.awt.Color(255, 255, 255));
        btn_AperturaCaja.setText("Aperturar  Caja");
        btn_AperturaCaja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_AperturaCaja.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_AperturaCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AperturaCajaActionPerformed(evt);
            }
        });
        jPanel1.add(btn_AperturaCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 310, 40));

        jPanel2.setBackground(new java.awt.Color(84, 110, 122));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Modulo de Apertura y Cierre de Cajas");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Inicio > Caja Admin > Apertura de Caja");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 320, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(104, 104, 104))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 50));

        jPanel3.setBackground(new java.awt.Color(84, 110, 122));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Usuario ");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 60, -1));

        jLabel20.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Monto Inicial");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 80, -1));

        jSeparator4.setBackground(new java.awt.Color(236, 239, 241));
        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 200, 10));

        jt_MontoInicial.setBackground(new java.awt.Color(84, 110, 122));
        jt_MontoInicial.setForeground(new java.awt.Color(204, 204, 204));
        jt_MontoInicial.setText("Ingresar Monto");
        jt_MontoInicial.setBorder(null);
        jt_MontoInicial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jt_MontoInicialFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_MontoInicialFocusLost(evt);
            }
        });
        jt_MontoInicial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_MontoInicialMouseClicked(evt);
            }
        });
        jt_MontoInicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_MontoInicialKeyTyped(evt);
            }
        });
        jPanel3.add(jt_MontoInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 190, -1));

        cb_usuario.setBackground(new java.awt.Color(84, 110, 122));
        cb_usuario.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cb_usuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Usuario" }));
        cb_usuario.setBorder(null);
        cb_usuario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_usuarioItemStateChanged(evt);
            }
        });
        cb_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_usuarioActionPerformed(evt);
            }
        });
        jPanel3.add(cb_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 190, 20));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 320, 110));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Formulario de Apertura");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Lista de Cajas abierta/Cerradas");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, -1, -1));

        btn_Modificar.setBackground(new java.awt.Color(255, 153, 0));
        btn_Modificar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Modificar.setText("Modificar");
        btn_Modificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Modificar.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 140, 40));

        btn_Eliminar.setBackground(new java.awt.Color(211, 18, 18));
        btn_Eliminar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Eliminar.setText("eliminar");
        btn_Eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Eliminar.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 300, 140, 40));

        lb_errorMonto.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorMonto.setText("*");
        jPanel1.add(lb_errorMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 10, -1));

        lb_errorUsuario.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorUsuario.setText("*");
        jPanel1.add(lb_errorUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 10, -1));

        lb_Id.setForeground(new java.awt.Color(84, 110, 122));
        lb_Id.setText("*");
        jPanel1.add(lb_Id, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jb_limpiarCampos.setBackground(new java.awt.Color(84, 110, 122));
        jb_limpiarCampos.setForeground(new java.awt.Color(84, 110, 122));
        jb_limpiarCampos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/clean24x24.png"))); // NOI18N
        jb_limpiarCampos.setBorder(null);
        jb_limpiarCampos.setBorderPainted(false);
        jb_limpiarCampos.setContentAreaFilled(false);
        jb_limpiarCampos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_limpiarCampos.setFocusPainted(false);
        jb_limpiarCampos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/cleanSeleccionar24x24.png"))); // NOI18N
        jb_limpiarCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_limpiarCamposActionPerformed(evt);
            }
        });
        jPanel1.add(jb_limpiarCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 40, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1066, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtabla_CajasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtabla_CajasMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jtabla_CajasMouseEntered

    private void jtabla_CajasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtabla_CajasMouseClicked
        seleccion = jtabla_Cajas.rowAtPoint(evt.getPoint());
        btn_AperturaCaja.setEnabled(false); //se desactiva este boton para so
        //variable que guarda el id del la fila del los privilegios de algun modulo
        btn_Eliminar.setEnabled(true);
        btn_Modificar.setEnabled(true);
        lb_Id.setText(String.valueOf(jtabla_Cajas.getValueAt(seleccion, 0)));
        // TODO add your handling code here:
    }//GEN-LAST:event_jtabla_CajasMouseClicked

    private void cb_usuarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_usuarioItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_usuarioItemStateChanged

    private void cb_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_usuarioActionPerformed

    private void btn_AperturaCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AperturaCajaActionPerformed
        try {
            if (!validarUsuario() == true || !validarMonto() == true ) {
                DesktopNotify.showDesktopMessage("Error", "REVISAR CAMPOS OBLIGATORIOS", DesktopNotify.ERROR);

            } else if (!validarCajasAbiertas() == true) {
                DesktopNotify.showDesktopMessage("Error", "EXISTE AÚN, UNA CAJA ABIERTA, REALIZE SU CORTE CORRESPONDIENTE"
                        + "E INTENTELO DE NUEVO", DesktopNotify.ERROR);
            } else {
                cecaja.insertarCaja(fechaActual, Double.valueOf(jt_MontoInicial.getText()), fechaActual,horaActual, 0.0, String.valueOf(cb_usuario.getSelectedItem()), "Abierto");
                tamañoTabla();
                NewTable = new DefaultTableModel();
                cTabla();
                datosIniciales();
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_btn_AperturaCajaActionPerformed

    private void btn_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModificarActionPerformed
          try {
            if (!validarUsuario() == true || !validarMonto() == true) {
                DesktopNotify.showDesktopMessage("Error", "REVISAR CAMPOS OBLIGATORIOS", DesktopNotify.ERROR);

            } else {
                cecaja.actualizarAperturaCaja(Double.valueOf(jt_MontoInicial.getText()), String.valueOf(cb_usuario.getSelectedItem()), Integer.parseInt(lb_Id.getText()));
                tamañoTabla();
                NewTable = new DefaultTableModel();
                cTabla();
                datosIniciales();
            }
        } catch (Exception e) {
        }


        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ModificarActionPerformed

    private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarActionPerformed
        try {
            if (lb_Id.getText().equals("*")) {

                DesktopNotify.showDesktopMessage("Error", "DEBE SELECCIONAR UN ELEMENTO DE LA TABLA PARA PODER SER ELIMINADO", DesktopNotify.ERROR);

            } else {

                Pn_Alert_Eliminar ale = new Pn_Alert_Eliminar(principal, true);
                ale.lb_titulo.setText("¿Esta seguro de eliminar este Corte?");
                ale.jb_aceptar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        cecaja.deleteCaja(Integer.parseInt(lb_Id.getText()));
                        tamañoTabla();
                        NewTable = new DefaultTableModel();
                        cTabla();
                        datosIniciales();

                    }
                });
                ale.setVisible(true);
            }

        } catch (Exception e) {

        }
             // TODO add your handling code here:
    }//GEN-LAST:event_btn_EliminarActionPerformed

    private void jt_MontoInicialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_MontoInicialFocusGained
        cft.formFocusGain(jt_MontoInicial);        // TODO add your handling code here:
    }//GEN-LAST:event_jt_MontoInicialFocusGained

    private void jt_MontoInicialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_MontoInicialFocusLost
        cft.formFocusLostJTextField(jt_MontoInicial, "Ingresar Monto");        // TODO add your handling code here:
    }//GEN-LAST:event_jt_MontoInicialFocusLost

    private void jt_MontoInicialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_MontoInicialKeyTyped
        ce.typedDigits(evt, jt_MontoInicial);        // TODO add your handling code here:
    }//GEN-LAST:event_jt_MontoInicialKeyTyped

    private void jt_MontoInicialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_MontoInicialMouseClicked
        cft.formFocusGain(jt_MontoInicial);        // TODO add your handling code here:
    }//GEN-LAST:event_jt_MontoInicialMouseClicked

    private void jb_limpiarCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_limpiarCamposActionPerformed
        datosIniciales();        // TODO add your handling code here:
    }//GEN-LAST:event_jb_limpiarCamposActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private principal.MaterialButton btn_AperturaCaja;
    public static principal.MaterialButton btn_Eliminar;
    public static principal.MaterialButton btn_Modificar;
    private javax.swing.JComboBox<String> cb_usuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JButton jb_limpiarCampos;
    private javax.swing.JTextField jt_MontoInicial;
    private javax.swing.JTable jtabla_Cajas;
    private javax.swing.JLabel lb_Id;
    private javax.swing.JLabel lb_errorMonto;
    private javax.swing.JLabel lb_errorUsuario;
    // End of variables declaration//GEN-END:variables
}
