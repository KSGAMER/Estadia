/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.ModuloAdmin;

import vistas.*;
import Utilerias.AWTUtilities;
import controladores.ControladorCaja;
import controladores.ControladorCobros;
import controladores.ControladorGastos;
import controladores.ControladorUsuarios;
import ds.desktop.notify.DesktopNotify;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import objetos.ObjetoCaja;
import objetos.ObjetoUsuario;

/**
 *
 * @author fenix
 */
public class Pn_CortedeCaja extends javax.swing.JDialog {

    private ControladorGastos cega = new ControladorGastos();
    private ControladorCobros ccob = new ControladorCobros();
    private ControladorUsuarios cuser = new ControladorUsuarios();
    Timer timer = null;
    TimerTask task;
    int i = 32;
    private int sumaCobroReservaciones, sumaGastos, sumaTotalCobrosGastos;
    DefaultTableModel modeloCobros, modeloGastos;
    ControladorCaja cecaja = new ControladorCaja();
    //necesario para obtener la fecha con hora para las nuevas cajas
    Date now = new Date(); // java.util.Date, NOT java.sql.Date or java.sql.Timestamp!
    String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(now);
String horaActual = new SimpleDateFormat("HH:mm:ss").format(now);
    /**
     * Creates new form AlertSuccess
     */
    public Pn_CortedeCaja(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        AWTUtilities.setOpaque(this, false);
        Ubicar(0);
        //APARIENCIA DE LA TABLA
        cuser.tablaUsuarios();
        cecaja.tablaCaja();
        cargarUsuarios();
        RowHeaderAparienciaTCobros();
        RowAparienciaTCobros();
        headerTablaCobros();
        RowHeaderAparienciaTGastos();
        RowAparienciaTGastos();
        headerTablaGastos();


    }
    private void datosIniciales(){
        lb_totalCobros.setText("0");
        lb_totalGastos.setText("0");
        lb_montoAsignado.setText("0");
        lb_sumaGastosCobros.setText("0");
        lb_Total.setText("0");
    }
    private void headerTablaCobros() {
        String titulos[] = new String[3];//cabeceras de la tabla
        titulos[0] = "Habitación";
        titulos[1] = "Monto";
        titulos[2] = "Cliente";
        /*  for (int i = 1; i <= titulos.length; i++) {
            titulos[i] = String.valueOf(i);
        }*/

        modeloCobros = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {//para evitar que las celdas sean editables
                return false;
            }
        };
        jtabla_Cobros.setModel(modeloCobros);
    }

    private void headerTablaGastos() {
        String titulos[] = new String[3];//cabeceras de la tabla
        titulos[0] = "Gasto";
        titulos[1] = "Cantidad";
        titulos[2] = "Descripción";

        /*  for (int i = 1; i <= titulos.length; i++) {
            titulos[i] = String.valueOf(i);
        }*/
        modeloGastos = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {//para evitar que las celdas sean editables
                return false;
            }
        };
        jtabla_Gastos.setModel(modeloGastos);
    }

    private void RowAparienciaTCobros() {

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

    private void RowHeaderAparienciaTCobros() {
        jtabla_Cobros.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 14));
        jtabla_Cobros.getTableHeader().setOpaque(false);
        jtabla_Cobros.getTableHeader().setBackground(new Color(32, 136, 203));
        jtabla_Cobros.getTableHeader().setForeground(new Color(255, 255, 255));

    }

    private void RowAparienciaTGastos() {

        //MODIFICACION DE TABLA GASTOS
        jtabla_Gastos.setFocusable(false);

        //espacio entre comulnas
        jtabla_Gastos.setIntercellSpacing(new Dimension(0, 1));
        //altura de columnas 
        jtabla_Gastos.setRowHeight(25);
        //margen entre filas
        jtabla_Gastos.setRowMargin(0);
//sin lineas verticles
        jtabla_Gastos.setShowVerticalLines(false);
        jtabla_Gastos.setSelectionBackground(new Color(97, 212, 195));

    }

    private void RowHeaderAparienciaTGastos() {
         //APARIENCIA ROWS DE GASTOS
        jtabla_Gastos.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 14));
        jtabla_Gastos.getTableHeader().setOpaque(false);
        jtabla_Gastos.getTableHeader().setBackground(new Color(32, 136, 203));
        jtabla_Gastos.getTableHeader().setForeground(new Color(255, 255, 255));

    }

    private void cTablaCobros(String user) {
        jtabla_Cobros.setModel(ccob.tablaCobros(user));
    }

    private void cTablaGastos(String user) {
        jtabla_Gastos.setModel(cega.tablaGastos(user));
    }

    private void cargarUsuarios() {

        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        cb.addElement("Seleccionar Usuario");
        for (ObjetoUsuario campos : cuser.selectUsuario()) {
            cb.addElement(campos.getUsername());
        }
        cb_usuario.setModel(cb);

    }
private Double totalizarTablaCobros(){
        double t = 0;
        double p = 0;
        if (jtabla_Cobros.getRowCount() > 0) {
            for (int j = 0; j < jtabla_Cobros.getRowCount(); j++) {
                p = Double.parseDouble(jtabla_Cobros.getValueAt(j, 1).toString());
                t += p;
            }
         
        }
          return t;
    }

    private Double totalizarTablaGastos() {
        double t = 0;
        double p = 0;
        if (jtabla_Gastos.getRowCount() > 0) {
            for (int j = 0; j < jtabla_Gastos.getRowCount(); j++) {
                p = Double.parseDouble(jtabla_Gastos.getValueAt(j, 1).toString());
                t += p;
            }
      
        }
        return t;
    }
    private void sumarTotales() {
        //se resta los gastos , porque es una salida de dinero 
        lb_Total.setText(String.valueOf(Double.valueOf(lb_montoAsignado.getText()) - (totalizarTablaCobros() + totalizarTablaGastos())));
        lb_sumaGastosCobros.setText(String.valueOf(totalizarTablaCobros() + totalizarTablaGastos()));

    }

    private Boolean validarCajasAbiertas() {
        Boolean val = true;
        for (ObjetoCaja caja : cecaja.seleccionarCaja()) {
            if (caja.getIdEstadoCaja() == 1 && caja.getUsuario().equals(String.valueOf(cb_usuario.getSelectedItem()))) {
                val = true;
                break;
            } else {
                DesktopNotify.showDesktopMessage("Error", "No existe ninguna caja abierta para el usuario: " + String.valueOf(cb_usuario.getSelectedItem()), DesktopNotify.ERROR);

                val = false;
                break;
            }
        }
        return val;
    }

    private Boolean validarSeleccion() {
        Boolean val = true;
        if (!(cb_usuario.getSelectedItem().equals("Seleccionar usuario"))) {
          
        } else {
               DesktopNotify.showDesktopMessage("Error", "Debe seleccionar un usuario para realizar el corte de caja", DesktopNotify.ERROR);

            val = false;
        }
        return val;
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
        lb_close = new javax.swing.JButton();
        lb_titulo1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtabla_Cobros = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtabla_Gastos = new javax.swing.JTable();
        btn_CorteCaja = new principal.MaterialButton();
        lb_Total = new javax.swing.JLabel();
        lb_titulo2 = new javax.swing.JLabel();
        lb_totalCobros = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lb_totalGastos = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cb_usuario = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lb_montoAsignado = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lb_sumaGastosCobros = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();

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

        jPanel1.setBackground(new java.awt.Color(233, 235, 238));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/icons8_Cancel_32px.png"))); // NOI18N
        lb_close.setToolTipText("Close");
        lb_close.setBorder(null);
        lb_close.setBorderPainted(false);
        lb_close.setContentAreaFilled(false);
        lb_close.setFocusPainted(false);
        lb_close.setRequestFocusEnabled(false);
        lb_close.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/icons8_Cancel_30px_3.png"))); // NOI18N
        lb_close.setVerifyInputWhenFocusTarget(false);
        lb_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lb_closeActionPerformed(evt);
            }
        });
        jPanel1.add(lb_close, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, -1, -1));

        lb_titulo1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_titulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_titulo1.setText("CORTE POR TURNO");
        jPanel1.add(lb_titulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 50));

        jPanel3.setBackground(new java.awt.Color(36, 47, 65));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 440, 120));

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

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 440, 120));

        btn_CorteCaja.setBackground(new java.awt.Color(40, 180, 99));
        btn_CorteCaja.setForeground(new java.awt.Color(255, 255, 255));
        btn_CorteCaja.setText("Realizar Corte");
        btn_CorteCaja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_CorteCaja.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_CorteCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CorteCajaActionPerformed(evt);
            }
        });
        jPanel3.add(btn_CorteCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 520, 194, 40));

        lb_Total.setBackground(new java.awt.Color(204, 204, 204));
        lb_Total.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_Total.setForeground(new java.awt.Color(204, 204, 204));
        lb_Total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_Total.setText("0.00");
        jPanel3.add(lb_Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 520, 90, 32));

        lb_titulo2.setBackground(new java.awt.Color(204, 204, 204));
        lb_titulo2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb_titulo2.setForeground(new java.awt.Color(204, 204, 204));
        lb_titulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_titulo2.setText("TOTAL A RETIRAR  $");
        jPanel3.add(lb_titulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 150, 31));

        lb_totalCobros.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_totalCobros.setForeground(new java.awt.Color(255, 255, 255));
        lb_totalCobros.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_totalCobros.setText("0.0");
        jPanel3.add(lb_totalCobros, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 200, 70, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Seleciona el usuario a realizar corte -->");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 240, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Total       $");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, 70, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Total       $");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 370, 70, -1));

        lb_totalGastos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_totalGastos.setForeground(new java.awt.Color(255, 255, 255));
        lb_totalGastos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_totalGastos.setText("0.0");
        jPanel3.add(lb_totalGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 370, 70, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Reservaciones Cobradas");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 160, -1));

        cb_usuario.setBackground(new java.awt.Color(84, 110, 122));
        cb_usuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar usuario" }));
        cb_usuario.setBorder(null);
        cb_usuario.setFocusable(false);
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
        jPanel3.add(cb_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 180, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Suma total (Cobros/Gastos)    $");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 180, 20));
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 500, 80, 10));

        lb_montoAsignado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_montoAsignado.setForeground(new java.awt.Color(255, 255, 255));
        lb_montoAsignado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_montoAsignado.setText("0");
        jPanel3.add(lb_montoAsignado, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 430, 80, 20));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Gastos Realizados");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 160, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Informacion General");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 400, 130, -1));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, 470, 10));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Cantidad de Apertura                 $");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 434, 180, 20));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 450, 80, 10));

        lb_sumaGastosCobros.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_sumaGastosCobros.setForeground(new java.awt.Color(255, 255, 255));
        lb_sumaGastosCobros.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_sumaGastosCobros.setText("0");
        jPanel3.add(lb_sumaGastosCobros, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 480, 80, -1));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 470, 10));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 470, 570));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_CorteCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CorteCajaActionPerformed
        try {
            if(!validarCajasAbiertas()==true || !validarSeleccion()==true){
         
            } else {
                cecaja.actualizarCiereCaja(fechaActual, horaActual, Double.valueOf(lb_Total.getText()), "Cerrado", String.valueOf(cb_usuario.getSelectedItem()));
                Cerrar();
            }

        } catch (Exception e) {
        }

    }//GEN-LAST:event_btn_CorteCajaActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        task = new TimerTask() {
            @Override
            public void run() {
                if (i == 352) {
                    timer.cancel();
                } else {
                    Ubicar(i);
                    i += 32;
                    Trasparencia((float) i / 352);
                }
            }
        };
        timer = new java.util.Timer();
        timer.schedule(task, 0, 2);
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        setVisible(false);
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void jtabla_CobrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtabla_CobrosMouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_jtabla_CobrosMouseClicked

    private void jtabla_CobrosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtabla_CobrosMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jtabla_CobrosMouseEntered

    private void jtabla_GastosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtabla_GastosMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jtabla_GastosMouseEntered

    private void jtabla_GastosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtabla_GastosMouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_jtabla_GastosMouseClicked

    private void lb_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lb_closeActionPerformed
        Cerrar();

    }//GEN-LAST:event_lb_closeActionPerformed

    private void cb_usuarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_usuarioItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED && !(cb_usuario.getSelectedIndex() == 0)) {
            try {
                if (!validarCajasAbiertas() == true || !validarSeleccion() == true) {
                    datosIniciales();
                    cTablaCobros(String.valueOf(cb_usuario.getSelectedItem()));
                    cTablaGastos(String.valueOf(cb_usuario.getSelectedItem()));
                    lb_totalCobros.setText(String.valueOf(totalizarTablaCobros()));
                    lb_totalGastos.setText(String.valueOf(totalizarTablaGastos()));
                } else {
                    datosIniciales();
                    cecaja.tablaCaja();
                    cTablaCobros(String.valueOf(cb_usuario.getSelectedItem()));
                    cTablaGastos(String.valueOf(cb_usuario.getSelectedItem()));
                    lb_totalCobros.setText(String.valueOf(totalizarTablaCobros()));
                    lb_totalGastos.setText(String.valueOf(totalizarTablaGastos()));

                    for (ObjetoCaja objetoCaja : cecaja.seleccionarCaja()) {
                        if (objetoCaja.getUsuario().equals(cb_usuario.getSelectedItem()) && (objetoCaja.getIdEstadoCaja() == 1)) {
                            lb_montoAsignado.setText(String.valueOf(objetoCaja.getMontoApertura()));
                            System.out.println(objetoCaja.getMontoApertura());
                            break;
                        }
                    }
                    sumarTotales();
                }

            } catch (Exception e) {
            }


            
            
            
            
            
            
            
            
          
        }
    }//GEN-LAST:event_cb_usuarioItemStateChanged

    private void cb_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_usuarioActionPerformed

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
            java.util.logging.Logger.getLogger(Pn_CortedeCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pn_CortedeCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pn_CortedeCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pn_CortedeCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Pn_CortedeCaja dialog = new Pn_CortedeCaja(new javax.swing.JFrame(), true);
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
    public static principal.MaterialButton btn_CorteCaja;
    private javax.swing.JComboBox<String> cb_usuario;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jtabla_Cobros;
    private javax.swing.JTable jtabla_Gastos;
    public static javax.swing.JLabel lb_Total;
    private javax.swing.JButton lb_close;
    private javax.swing.JLabel lb_montoAsignado;
    private javax.swing.JLabel lb_sumaGastosCobros;
    public static javax.swing.JLabel lb_titulo1;
    public static javax.swing.JLabel lb_titulo2;
    private javax.swing.JLabel lb_totalCobros;
    private javax.swing.JLabel lb_totalGastos;
    // End of variables declaration//GEN-END:variables

    private void Cerrar() {
        this.dispose();
        timer = null;
        task = null;
    }

    private void Trasparencia(float trasp) {
        AWTUtilities.setOpacity(this, trasp);
    }

    private void Ubicar(int y) {

        Toolkit tk = Toolkit.getDefaultToolkit();
        int xsize = (int) tk.getScreenSize().getWidth() / 3;
        this.setLocation(xsize, y - 200);

    }
}
