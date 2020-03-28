/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.ModuloAdmin;

import controladores.ControladorEscritura;
import controladores.ControladorFormularioTab;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.table.TableColumnModel;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import vistas.Pn_Alert_Eliminar;
import controladores.ControladorGastos;
import controladores.ControladorCobros;
import ds.desktop.notify.DesktopNotify;

public class Pn_MovimientoCerrarCaja extends javax.swing.JPanel {
//NECESARIO PARA EL USO DE LA NOTIFICACION DINAMICA DE BOTON ELIMINAR ()

    Frame principal;
    //validadores de escrituraa 
    ControladorEscritura ce = new ControladorEscritura();
    private ControladorFormularioTab cft = new ControladorFormularioTab();
    private ControladorGastos cega = new ControladorGastos();
    private ControladorCobros ccob = new ControladorCobros();

    //variable para obtener la posicion de la fila en la tablaS
    private int seleccionCobros, seleccionGastos;
    DefaultTableModel NewTable;

    public Pn_MovimientoCerrarCaja() {
        initComponents();
        RowHeaderApariencia();
        RowApariencia();
        cTablaCobros();
        cTablaGastos();
        bloquearEliminadores();
        tamañoTablaCobros();
        tamañoTablaGastos();
    }

    private void bloquearEliminadores() {
        btn_EliminarCobro.setEnabled(false);
        btn_EliminarGasto.setEnabled(false);
    }

    private void tamañoTablaCobros() {
        TableColumnModel columnModel = jtabla_Cobros.getColumnModel();

    }

    private void tamañoTablaGastos() {
        TableColumnModel columnModel = jtabla_Gastos.getColumnModel();

    }

    private void cTablaCobros() {
jtabla_Cobros.setModel(ccob.tablaCobros2(""));
    }

    private void cTablaGastos() {
jtabla_Gastos.setModel(cega.tablaGastosCompleta(""));
    }

    private void RowApariencia() {

        jtabla_Cobros.setFocusable(false);
        //espacio entre comulnas
        jtabla_Cobros.setIntercellSpacing(new Dimension(0, 1));
        //altura de columnas 
        jtabla_Cobros.setRowHeight(25);
        //margen entre filas
        jtabla_Cobros.setRowMargin(0);
        //sin lineas verticles
        jtabla_Cobros.setShowVerticalLines(false);
        jtabla_Cobros.setSelectionBackground(new Color(97, 212, 195));

    }

    private void RowHeaderApariencia() {
        jtabla_Cobros.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 14));
        jtabla_Cobros.getTableHeader().setOpaque(false);
        jtabla_Cobros.getTableHeader().setBackground(Color.BLACK);
        jtabla_Cobros.getTableHeader().setForeground(new Color(255, 255, 255));

    }
private void datosIniciales() {
        lb_idGasto.setText("*");
        lb_idCobroReservacion.setText("*");
    }

    private void datosInicialesGastos() {
        lb_idGasto.setText("*");
        btn_EliminarGasto.setEnabled(false);
    }

    private void datosInicialesCobros() {
        lb_idCobroReservacion.setText("*");
        btn_EliminarCobro.setEnabled(false);
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
        jb_limpiarFolioGastos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtabla_Cobros = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btn_AperturaCaja1 = new principal.MaterialButton();
        btn_EliminarGasto = new principal.MaterialButton();
        jt_BuscarCobrosHabitaciones = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtabla_Gastos = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jt_BuscarGastos = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btn_EliminarCobro = new principal.MaterialButton();
        lb_idGasto = new javax.swing.JLabel();
        lb_idCobroReservacion = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jb_limpiarFolioCobros = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(84, 110, 122));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jb_limpiarFolioGastos.setBackground(new java.awt.Color(84, 110, 122));
        jb_limpiarFolioGastos.setForeground(new java.awt.Color(84, 110, 122));
        jb_limpiarFolioGastos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/clean24x24.png"))); // NOI18N
        jb_limpiarFolioGastos.setBorder(null);
        jb_limpiarFolioGastos.setBorderPainted(false);
        jb_limpiarFolioGastos.setContentAreaFilled(false);
        jb_limpiarFolioGastos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_limpiarFolioGastos.setFocusPainted(false);
        jb_limpiarFolioGastos.setFocusable(false);
        jb_limpiarFolioGastos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/cleanSeleccionar24x24.png"))); // NOI18N
        jb_limpiarFolioGastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_limpiarFolioGastosActionPerformed(evt);
            }
        });
        jPanel1.add(jb_limpiarFolioGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 440, 40, -1));

        jtabla_Cobros.setModel(new javax.swing.table.DefaultTableModel(
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
        jtabla_Cobros.getTableHeader().setResizingAllowed(false);
        jtabla_Cobros.getTableHeader().setReorderingAllowed(false);
        jtabla_Cobros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtabla_CobrosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jtabla_CobrosMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jtabla_Cobros);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 780, 180));

        jPanel2.setBackground(new java.awt.Color(84, 110, 122));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CORTE DE CAJA");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Inicio > Caja Admin > Cierre de Caja");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1020, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(42, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(116, 116, 116))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 50));

        jPanel5.setBackground(new java.awt.Color(36, 47, 65));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("LISTA DE COBROS REALIZADOS POR DIA ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(372, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 780, -1));

        btn_AperturaCaja1.setBackground(new java.awt.Color(40, 180, 99));
        btn_AperturaCaja1.setForeground(new java.awt.Color(255, 255, 255));
        btn_AperturaCaja1.setText("CORTE DE CAJA");
        btn_AperturaCaja1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_AperturaCaja1.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_AperturaCaja1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AperturaCaja1ActionPerformed(evt);
            }
        });
        jPanel1.add(btn_AperturaCaja1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 220, 40));

        btn_EliminarGasto.setBackground(new java.awt.Color(211, 18, 18));
        btn_EliminarGasto.setForeground(new java.awt.Color(255, 255, 255));
        btn_EliminarGasto.setText("ELIMINAR");
        btn_EliminarGasto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_EliminarGasto.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_EliminarGasto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarGastoActionPerformed(evt);
            }
        });
        jPanel1.add(btn_EliminarGasto, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 390, 220, 40));

        jt_BuscarCobrosHabitaciones.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jt_BuscarCobrosHabitaciones.setForeground(new java.awt.Color(102, 102, 102));
        jt_BuscarCobrosHabitaciones.setText("Buscar");
        jt_BuscarCobrosHabitaciones.setBorder(null);
        jt_BuscarCobrosHabitaciones.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jt_BuscarCobrosHabitacionesFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_BuscarCobrosHabitacionesFocusLost(evt);
            }
        });
        jt_BuscarCobrosHabitaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_BuscarCobrosHabitacionesMouseClicked(evt);
            }
        });
        jt_BuscarCobrosHabitaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jt_BuscarCobrosHabitacionesKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_BuscarCobrosHabitacionesKeyTyped(evt);
            }
        });
        jPanel1.add(jt_BuscarCobrosHabitaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 130, 150, 20));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/campo-buscar.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 120, -1, 40));

        jtabla_Gastos.setModel(new javax.swing.table.DefaultTableModel(
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
        jtabla_Gastos.getTableHeader().setResizingAllowed(false);
        jtabla_Gastos.getTableHeader().setReorderingAllowed(false);
        jtabla_Gastos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtabla_GastosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jtabla_GastosMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(jtabla_Gastos);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 780, 200));

        jPanel6.setBackground(new java.awt.Color(36, 47, 65));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("LISTA DE GASTOS REALIZADOS POR DIA ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(372, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 780, -1));

        jt_BuscarGastos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jt_BuscarGastos.setForeground(new java.awt.Color(102, 102, 102));
        jt_BuscarGastos.setText("Buscar ");
        jt_BuscarGastos.setBorder(null);
        jt_BuscarGastos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jt_BuscarGastosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_BuscarGastosFocusLost(evt);
            }
        });
        jt_BuscarGastos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_BuscarGastosMouseClicked(evt);
            }
        });
        jt_BuscarGastos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jt_BuscarGastosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_BuscarGastosKeyTyped(evt);
            }
        });
        jPanel1.add(jt_BuscarGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 350, 150, 20));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/campo-buscar.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 340, -1, 40));

        btn_EliminarCobro.setBackground(new java.awt.Color(211, 18, 18));
        btn_EliminarCobro.setForeground(new java.awt.Color(255, 255, 255));
        btn_EliminarCobro.setText("ELIMINAR");
        btn_EliminarCobro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_EliminarCobro.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_EliminarCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarCobroActionPerformed(evt);
            }
        });
        jPanel1.add(btn_EliminarCobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 220, 40));

        lb_idGasto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_idGasto.setForeground(new java.awt.Color(255, 255, 255));
        lb_idGasto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_idGasto.setText("*");
        jPanel1.add(lb_idGasto, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 440, 20, 20));

        lb_idCobroReservacion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_idCobroReservacion.setForeground(new java.awt.Color(255, 255, 255));
        lb_idCobroReservacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_idCobroReservacion.setText("*");
        jPanel1.add(lb_idCobroReservacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 220, 20, 20));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Folio de Cobro");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 210, -1, 40));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 240, 20, 10));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 460, 20, 10));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Folio de Gasto");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 430, -1, 40));

        jb_limpiarFolioCobros.setBackground(new java.awt.Color(84, 110, 122));
        jb_limpiarFolioCobros.setForeground(new java.awt.Color(84, 110, 122));
        jb_limpiarFolioCobros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/clean24x24.png"))); // NOI18N
        jb_limpiarFolioCobros.setBorder(null);
        jb_limpiarFolioCobros.setBorderPainted(false);
        jb_limpiarFolioCobros.setContentAreaFilled(false);
        jb_limpiarFolioCobros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_limpiarFolioCobros.setFocusPainted(false);
        jb_limpiarFolioCobros.setFocusable(false);
        jb_limpiarFolioCobros.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/cleanSeleccionar24x24.png"))); // NOI18N
        jb_limpiarFolioCobros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_limpiarFolioCobrosActionPerformed(evt);
            }
        });
        jPanel1.add(jb_limpiarFolioCobros, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 210, 40, -1));

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

    private void jtabla_CobrosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtabla_CobrosMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jtabla_CobrosMouseEntered

    private void jtabla_CobrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtabla_CobrosMouseClicked
        seleccionCobros = jtabla_Cobros.rowAtPoint(evt.getPoint());
        btn_EliminarCobro.setEnabled(true);
        lb_idCobroReservacion.setText(String.valueOf(jtabla_Cobros.getValueAt(seleccionCobros, 0)));
        // TODO add your handling code here:
    }//GEN-LAST:event_jtabla_CobrosMouseClicked

    private void btn_AperturaCaja1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AperturaCaja1ActionPerformed

        Pn_CortedeCaja wa = new Pn_CortedeCaja(principal, true);
        wa.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_AperturaCaja1ActionPerformed

    private void btn_EliminarGastoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarGastoActionPerformed
        try {
            if (lb_idGasto.getText().equals("*")) {
DesktopNotify.showDesktopMessage("Error", "Debe seleccionar un gastos realizado", DesktopNotify.ERROR);

            } else {

                Pn_Alert_Eliminar ale = new Pn_Alert_Eliminar(principal, true);
                ale.lb_titulo.setText("¿Esta seguro de eliminar este elemento?");
                ale.jb_aceptar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        cega.eliminarGatos(Integer.parseInt(lb_idGasto.getText()));
                        tamañoTablaGastos();
                        NewTable = new DefaultTableModel();
                        cTablaGastos();
                        datosIniciales();

                    }
                });
                ale.setVisible(true);
            }
        } catch (Exception e) {
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btn_EliminarGastoActionPerformed

    private void jt_BuscarCobrosHabitacionesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_BuscarCobrosHabitacionesFocusGained
        cft.formFocusGain(jt_BuscarCobrosHabitaciones);
    }//GEN-LAST:event_jt_BuscarCobrosHabitacionesFocusGained

    private void jt_BuscarCobrosHabitacionesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_BuscarCobrosHabitacionesFocusLost
        cft.formFocusLostJTextField(jt_BuscarCobrosHabitaciones, "Buscar");
    }//GEN-LAST:event_jt_BuscarCobrosHabitacionesFocusLost

    private void jt_BuscarCobrosHabitacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_BuscarCobrosHabitacionesMouseClicked
        cft.formFocusGain(jt_BuscarCobrosHabitaciones);
    }//GEN-LAST:event_jt_BuscarCobrosHabitacionesMouseClicked

    private void jt_BuscarCobrosHabitacionesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_BuscarCobrosHabitacionesKeyReleased
          jtabla_Cobros.setModel(ccob.tablaCobros2(jt_BuscarCobrosHabitaciones.getText()));
        //   tamañoTabla();
    }//GEN-LAST:event_jt_BuscarCobrosHabitacionesKeyReleased

    private void jt_BuscarCobrosHabitacionesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_BuscarCobrosHabitacionesKeyTyped

        char tecla;
        tecla = evt.getKeyChar();
        //Convertir a letras mayusculas
        if (Character.isLetter(tecla)) {
            evt.setKeyChar(Character.toUpperCase(tecla));

        }
    }//GEN-LAST:event_jt_BuscarCobrosHabitacionesKeyTyped

    private void jtabla_GastosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtabla_GastosMouseClicked
        seleccionGastos = jtabla_Gastos.rowAtPoint(evt.getPoint());
        btn_EliminarGasto.setEnabled(true);
        lb_idGasto.setText(String.valueOf(jtabla_Gastos.getValueAt(seleccionGastos, 0)));

        // TODO add your handling code here:
    }//GEN-LAST:event_jtabla_GastosMouseClicked

    private void jtabla_GastosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtabla_GastosMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jtabla_GastosMouseEntered

    private void jt_BuscarGastosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_BuscarGastosFocusGained
        cft.formFocusGain(jt_BuscarGastos);
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_BuscarGastosFocusGained

    private void jt_BuscarGastosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_BuscarGastosFocusLost
        cft.formFocusLostJTextField(jt_BuscarGastos, "Buscar");
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_BuscarGastosFocusLost

    private void jt_BuscarGastosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_BuscarGastosMouseClicked
        cft.formFocusGain(jt_BuscarGastos);        // TODO add your handling code here:
    }//GEN-LAST:event_jt_BuscarGastosMouseClicked

    private void jt_BuscarGastosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_BuscarGastosKeyReleased
jtabla_Gastos.setModel(cega.tablaGastosCompleta(jt_BuscarGastos.getText()));        // TODO add your handling code here:
    }//GEN-LAST:event_jt_BuscarGastosKeyReleased

    private void jt_BuscarGastosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_BuscarGastosKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        //Convertir a letras mayusculas
        if (Character.isLetter(tecla)) {
            evt.setKeyChar(Character.toUpperCase(tecla));

        }        // TODO add your handling code here:
    }//GEN-LAST:event_jt_BuscarGastosKeyTyped

    private void btn_EliminarCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarCobroActionPerformed
        try {
            if (lb_idCobroReservacion.getText().equals("*")) {
 DesktopNotify.showDesktopMessage("Error", "Debe seleccionar un cobro realizado", DesktopNotify.ERROR);

            } else {

                Pn_Alert_Eliminar ale = new Pn_Alert_Eliminar(principal, true);
                ale.lb_titulo.setText("¿Esta seguro de eliminar este elemento?");
                ale.jb_aceptar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        ccob.deleteCobro(Integer.parseInt(lb_idCobroReservacion.getText()));
                        tamañoTablaCobros();
                        NewTable = new DefaultTableModel();
                        cTablaCobros();
                        datosIniciales();

                    }
                });
                ale.setVisible(true);
            }
        } catch (Exception e) {
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_EliminarCobroActionPerformed

    private void jb_limpiarFolioGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_limpiarFolioGastosActionPerformed
        datosInicialesGastos();        // TODO add your handling code here:
    }//GEN-LAST:event_jb_limpiarFolioGastosActionPerformed

    private void jb_limpiarFolioCobrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_limpiarFolioCobrosActionPerformed
    datosInicialesCobros();      // TODO add your handling code here:
    }//GEN-LAST:event_jb_limpiarFolioCobrosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private principal.MaterialButton btn_AperturaCaja1;
    private principal.MaterialButton btn_EliminarCobro;
    private principal.MaterialButton btn_EliminarGasto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton jb_limpiarFolioCobros;
    private javax.swing.JButton jb_limpiarFolioGastos;
    public static javax.swing.JTextField jt_BuscarCobrosHabitaciones;
    public static javax.swing.JTextField jt_BuscarGastos;
    private javax.swing.JTable jtabla_Cobros;
    private javax.swing.JTable jtabla_Gastos;
    private javax.swing.JLabel lb_idCobroReservacion;
    private javax.swing.JLabel lb_idGasto;
    // End of variables declaration//GEN-END:variables
}
