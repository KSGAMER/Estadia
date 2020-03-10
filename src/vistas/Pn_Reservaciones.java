/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import Utilerias.CambiaPanel;
import controladores.*;
import ds.desktop.notify.DesktopNotify;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import objetos.ObjetoCliente;
import objetos.ObjetoHabitacion;

/**
 *
 * @author fenix
 */
public class Pn_Reservaciones extends javax.swing.JPanel {

    private ControladorEscritura ce = new ControladorEscritura();
    private ControladorHabitaciones ch = new ControladorHabitaciones();
    private ControladorClientes mc = new ControladorClientes();
    private ControladorReservaciones cr = new ControladorReservaciones();
    private ControladorFormularioTab cft = new ControladorFormularioTab();
    private Pn_SeleccionarClientes select = new Pn_SeleccionarClientes();
    private Pn_CobrarReservacion CobrarReserv = new Pn_CobrarReservacion();
    private DefaultTableModel NewTable;

    //necesario para obtener el numero de fila de la jtable
    private int seleccion;
    //necesario para dar formato al jdateChooser
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
   
 private int m = 0;
    /**
     * Creates new form Pn_Reservaciones1
     */
    public Pn_Reservaciones() {
        initComponents();
        cr.tablaReservaciones();
        mc.tablaClientes();
        ch.tablaHabitaciones();
        datosIniciales();
        RowHeaderApariencia();
        RowApariencia();
        cTabla();
        cargarHabitaciones();
             
    }

    public void cargarHabitaciones() {
        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        cb.addElement("Seleccionar Habitacion");

        for (ObjetoHabitacion campos : ch.selectHabitacion()) {
           /* if (campos.getIdEstadoHabitacion() != 1) {

            } else {*/
                cb.addElement(campos.getNombre());
            //}
        }
        cb_Habitacion.setModel(cb);
    }

    public void cTabla() {
        jt_Reservas.setModel(cr.tablaReservaciones());
        jt_t_registros.setText(String.valueOf(cr.selectReservacion().size()));

    }

    public void tamañoTabla() {
        TableColumnModel columnModel = jt_Reservas.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(120);
        columnModel.getColumn(3).setPreferredWidth(240);
        columnModel.getColumn(4).setPreferredWidth(100);

    }

    public void datosIniciales() {

        jt_nombre.setText("Ingresar Nombre");
        cb_Habitacion.setSelectedIndex(0);
        jd_Ingreso.setCalendar(null);
        jd_Salida.setCalendar(null);
    }

    public void RowApariencia() {

        jt_Reservas.setFocusable(false);

        //espacio entre comulnas
        jt_Reservas.setIntercellSpacing(new Dimension(0, 1));
        //altura de columnas 
        jt_Reservas.setRowHeight(25);
        //margen entre filas
        jt_Reservas.setRowMargin(0);
//sin lineas verticles
        jt_Reservas.setShowVerticalLines(false);
        jt_Reservas.setSelectionBackground(new Color(97, 212, 195));

    }

    public void RowHeaderApariencia() {
        jt_Reservas.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 14));
        jt_Reservas.getTableHeader().setOpaque(false);
        jt_Reservas.getTableHeader().setBackground(new Color(32, 136, 203));
        jt_Reservas.getTableHeader().setForeground(new Color(255, 255, 255));

    }

    public void bloquearComponentes() {

        jt_nombre.setEnabled(false);
        jt_nombre.setText("Ingresar Nombre");
        //datos no editables
        jt_nombre.setEditable(false);
    }

    public void desbloquearComponentes() {
        jt_nombre.setEnabled(true);
        //datos no editables
        jt_nombre.setEditable(true);
    }

    public Boolean validarEscritura() {
        Boolean val = true;
        //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(jt_nombre.getText().equals("Ingresar Nombre")) && !(jt_nombre.getText().equals(""))) {
            lb_errorNombre.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorNombre.setForeground(Color.RED);
            val = false;
        }

        //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(jd_Ingreso.getDate() == null)) {
            lb_errorFechaIngreso.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorFechaIngreso.setForeground(Color.RED);
            val = false;
        }
        //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(jd_Salida.getDate() == null)) {
            lb_errorFechaSalida.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorFechaSalida.setForeground(Color.RED);
            val = false;
        }

        return val;
    }

    public Boolean validarSeleccion() {
        Boolean val = true;

        if (!(cb_Habitacion.getSelectedIndex() == 0)) {
            lb_errorIngreso.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorIngreso.setForeground(Color.RED);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_Reservas = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jt_nombre = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_Ingresar = new principal.MaterialButton();
        btn_Cobrar = new principal.MaterialButton();
        btn_Modificar = new principal.MaterialButton();
        cb_Habitacion = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jd_Ingreso = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        jd_Salida = new com.toedter.calendar.JDateChooser();
        jt_t_registros = new javax.swing.JLabel();
        lb_errorFechaSalida = new javax.swing.JLabel();
        lb_errorIngreso = new javax.swing.JLabel();
        lb_errorNombre = new javax.swing.JLabel();
        lb_errorFechaIngreso = new javax.swing.JLabel();
        lb_errorStatusReserva = new javax.swing.JLabel();
        lb_errorHabitacion = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btn_clientes = new principal.MaterialButton();
        btn_Eliminar2 = new principal.MaterialButton();
        lb_Id = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(84, 110, 122));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jt_Reservas.setModel(new javax.swing.table.DefaultTableModel(
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
        jt_Reservas.getTableHeader().setResizingAllowed(false);
        jt_Reservas.getTableHeader().setReorderingAllowed(false);
        jt_Reservas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_ReservasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jt_Reservas);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 650, 340));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Nombre");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 190, -1));

        jt_nombre.setBackground(new java.awt.Color(84, 110, 122));
        jt_nombre.setForeground(new java.awt.Color(204, 204, 204));
        jt_nombre.setText("Ingresar Nombre");
        jt_nombre.setBorder(null);
        jt_nombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jt_nombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_nombreFocusLost(evt);
            }
        });
        jt_nombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_nombreMouseClicked(evt);
            }
        });
        jt_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_nombreKeyTyped(evt);
            }
        });
        jPanel1.add(jt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 170, -1));

        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("Fecha de ingreso");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 190, -1));

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 190, 10));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Total de registros ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 470, 100, 20));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/limpiarCampos 24x24.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, -1, -1));

        btn_Ingresar.setBackground(new java.awt.Color(40, 180, 99));
        btn_Ingresar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Ingresar.setText("Agregar");
        btn_Ingresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Ingresar.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_Ingresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_IngresarMouseClicked(evt);
            }
        });
        btn_Ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_IngresarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 100, 40));

        btn_Cobrar.setBackground(new java.awt.Color(51, 51, 51));
        btn_Cobrar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Cobrar.setText("Cobrar");
        btn_Cobrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Cobrar.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_Cobrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_CobrarMouseClicked(evt);
            }
        });
        btn_Cobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CobrarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Cobrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 440, 100, 40));

        btn_Modificar.setBackground(new java.awt.Color(255, 153, 0));
        btn_Modificar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Modificar.setText("Modificar");
        btn_Modificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Modificar.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_Modificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ModificarMouseClicked(evt);
            }
        });
        btn_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 95, 40));

        cb_Habitacion.setBackground(new java.awt.Color(84, 110, 122));
        cb_Habitacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Habitacion" }));
        cb_Habitacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_HabitacionItemStateChanged(evt);
            }
        });
        cb_Habitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_HabitacionActionPerformed(evt);
            }
        });
        jPanel1.add(cb_Habitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 190, -1));

        jPanel2.setBackground(new java.awt.Color(84, 110, 122));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("VISTA GENERAL DE LAS HABITACIONES RESERVADAS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(381, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 50));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Formulario de Cambios");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Detalle de Reservaciones");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1030, 10));
        jPanel1.add(jd_Ingreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 190, -1));

        jLabel20.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Fecha de Salida");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 190, -1));
        jPanel1.add(jd_Salida, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 190, -1));

        jt_t_registros.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jt_t_registros.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jt_t_registros, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 470, 30, 20));

        lb_errorFechaSalida.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorFechaSalida.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorFechaSalida.setText("*");
        jPanel1.add(lb_errorFechaSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 10, -1));

        lb_errorIngreso.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorIngreso.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorIngreso.setText("*");
        jPanel1.add(lb_errorIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 10, -1));

        lb_errorNombre.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorNombre.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorNombre.setText("*");
        jPanel1.add(lb_errorNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 10, -1));

        lb_errorFechaIngreso.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorFechaIngreso.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorFechaIngreso.setText("*");
        jPanel1.add(lb_errorFechaIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 10, -1));

        lb_errorStatusReserva.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorStatusReserva.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorStatusReserva.setText("*");
        jPanel1.add(lb_errorStatusReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 10, -1));

        lb_errorHabitacion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorHabitacion.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorHabitacion.setText("*");
        jPanel1.add(lb_errorHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 10, -1));

        jLabel21.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("Habitación");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 190, -1));

        btn_clientes.setBackground(new java.awt.Color(233, 235, 238));
        btn_clientes.setText("...");
        btn_clientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_clientes.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_clientesMouseClicked(evt);
            }
        });
        btn_clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clientesActionPerformed(evt);
            }
        });
        jPanel1.add(btn_clientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 30, 30));

        btn_Eliminar2.setBackground(new java.awt.Color(211, 18, 18));
        btn_Eliminar2.setForeground(new java.awt.Color(255, 255, 255));
        btn_Eliminar2.setText("eliminar");
        btn_Eliminar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Eliminar2.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_Eliminar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_Eliminar2MouseClicked(evt);
            }
        });
        btn_Eliminar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Eliminar2ActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Eliminar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 100, 40));

        lb_Id.setForeground(new java.awt.Color(84, 110, 122));
        jPanel1.add(lb_Id, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 30, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 979, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jt_ReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_ReservasMouseClicked

        seleccion = jt_Reservas.rowAtPoint(evt.getPoint());
        lb_Id.setText(String.valueOf(jt_Reservas.getValueAt(seleccion, 0)));
        jt_nombre.setText(String.valueOf(jt_Reservas.getValueAt(seleccion, 1)));
        cb_Habitacion.getModel().setSelectedItem(jt_Reservas.getValueAt(seleccion, 2));
        try {
            jd_Ingreso.setDate(dateFormat.parse((String) jt_Reservas.getValueAt(seleccion, 3).toString()));
            jd_Salida.setDate(dateFormat.parse((String) jt_Reservas.getValueAt(seleccion, 4).toString()));
        } catch (ParseException ex) {
            Logger.getLogger(Pn_Reservaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jt_ReservasMouseClicked

    private void jt_nombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_nombreFocusLost
        cft.formFocusLostJTextField(jt_nombre, "Ingresar Nombre");
        cft.formFocusLostJTextField(cb_Habitacion);
    }//GEN-LAST:event_jt_nombreFocusLost

    private void jt_nombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_nombreMouseClicked
        cft.formFocusGain(jt_nombre);
    }//GEN-LAST:event_jt_nombreMouseClicked

    private void jt_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_nombreKeyTyped
        ce.typedCharsAndSpace(evt, jt_nombre);
    }//GEN-LAST:event_jt_nombreKeyTyped

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        datosIniciales();
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void btn_IngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_IngresarMouseClicked

    }//GEN-LAST:event_btn_IngresarMouseClicked

    private void btn_IngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_IngresarActionPerformed

        try {
            if (!validarEscritura() == true && !validarSeleccion() == true) {

                DesktopNotify.showDesktopMessage("Error", "REVISAR CAMPOS OBLIGATORIOS", DesktopNotify.ERROR);

            } else {

                cr.insertReservacion(jt_nombre.getText(), String.valueOf(cb_Habitacion.getSelectedItem()), dateFormat.format(jd_Ingreso.getDate()), dateFormat.format(jd_Salida.getDate()));
                for (int i = 0; i < ch.selectHabitacion().size(); i++) {
                    if (ch.selectHabitacion().get(i).getNombre().equals(String.valueOf(cb_Habitacion.getSelectedItem()))) {

                    }
                }

                DesktopNotify.showDesktopMessage("Exito", "Datos de la reservacion agregados con éxito.", DesktopNotify.SUCCESS);
                NewTable = new DefaultTableModel();
                cTabla();
                datosIniciales();
            }

        } catch (Exception e) {
            System.out.println(e);

            DesktopNotify.showDesktopMessage("Error", "Ocurrió un error al intentar ingresar los datos de la nueva reservación,"
                    + "por favor intente de nuevo o revise su conexión", DesktopNotify.ERROR);

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_IngresarActionPerformed

    private void btn_CobrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_CobrarMouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_CobrarMouseClicked

    private void btn_CobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CobrarActionPerformed
        if (!validarEscritura() && !validarSeleccion()) {
            DesktopNotify.showDesktopMessage("Error", "REVISAR CAMPOS OBLIGATORIOS", DesktopNotify.ERROR);

        } else {
            CobrarReserv.setVisible(true);
            CobrarReserv.lb_FolioReservaciones.setText(lb_Id.getText());
            CobrarReserv.lb_nombreCliente.setText(jt_nombre.getText());
            CobrarReserv.lb_NombreHabitacion.setText(String.valueOf(cb_Habitacion.getSelectedItem()));
            CobrarReserv.lb_FechaIngreso.setText(dateFormat.format(jd_Ingreso.getDate()));
            CobrarReserv.lb_FechaSalida.setText(dateFormat.format(jd_Salida.getDate()));
            for (int i = 0; i < ch.selectHabitacion().size(); i++) {
                if (ch.selectHabitacion().get(i).getNombre().equals(String.valueOf(cb_Habitacion.getSelectedItem()))) {
                    CobrarReserv.lb_PrecioHabitacion.setText(String.valueOf(ch.selectHabitacion().get(i).getPrecioSugerido()));
                    //AQUI VA ELCODIGO PARA ACTUALIZAR ESTADO DE HABITACION
                }
            }
            datosIniciales();
        }

    }//GEN-LAST:event_btn_CobrarActionPerformed

    private void btn_ModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ModificarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ModificarMouseClicked

    private void cb_HabitacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_HabitacionItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_HabitacionItemStateChanged

    private void cb_HabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_HabitacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_HabitacionActionPerformed

    private void btn_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_clientesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_clientesMouseClicked

    private void btn_clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clientesActionPerformed

        select.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_clientesActionPerformed

    private void btn_Eliminar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Eliminar2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_Eliminar2MouseClicked

    private void btn_Eliminar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Eliminar2ActionPerformed
        try {
            if (!validarEscritura() == true && !validarSeleccion() == true) {

                DesktopNotify.showDesktopMessage("Error", "REVISAR CAMPOS OBLIGATORIOS", DesktopNotify.ERROR);

            } else {
                cr.deleteReservacion(Integer.valueOf(lb_Id.getText()));
                DesktopNotify.showDesktopMessage("Exito", "Datos de la reservacion eliminados con éxito.", DesktopNotify.SUCCESS);
                NewTable = new DefaultTableModel();
                cTabla();
                datosIniciales();
            }

        } catch (Exception e) {
            System.out.println(e);

            DesktopNotify.showDesktopMessage("Error", "Ocurrió un error al intentar eliminar  los datos de la reservación,"
                    + "por favor intente de nuevo o revise su conexión", DesktopNotify.ERROR);

        }
    }//GEN-LAST:event_btn_Eliminar2ActionPerformed

    private void btn_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModificarActionPerformed

        try {
            if (!validarEscritura() == true && !validarSeleccion() == true) {

                DesktopNotify.showDesktopMessage("Error", "REVISAR CAMPOS OBLIGATORIOS", DesktopNotify.ERROR);

            } else {

                cr.updateReservacion(jt_nombre.getText(), String.valueOf(cb_Habitacion.getSelectedItem()), dateFormat.format(jd_Ingreso.getDate()), dateFormat.format(jd_Salida.getDate()), Integer.valueOf(lb_Id.getText()));

                DesktopNotify.showDesktopMessage("Exito", "Datos de la reservacion actualizados con éxito.", DesktopNotify.SUCCESS);
                NewTable = new DefaultTableModel();
                cTabla();
                datosIniciales();
            }

        } catch (Exception e) {
            System.out.println(e);

            DesktopNotify.showDesktopMessage("Error", "Ocurrió un error al intentar actualizar los datos de la reservación,"
                    + "por favor intente de nuevo o revise su conexión", DesktopNotify.ERROR);

        }
    }//GEN-LAST:event_btn_ModificarActionPerformed

    private void jt_nombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_nombreFocusGained
        cft.formFocusGain(jt_nombre);
    }//GEN-LAST:event_jt_nombreFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private principal.MaterialButton btn_Cobrar;
    private principal.MaterialButton btn_Eliminar2;
    private principal.MaterialButton btn_Ingresar;
    private principal.MaterialButton btn_Modificar;
    private principal.MaterialButton btn_clientes;
    private javax.swing.JComboBox<String> cb_Habitacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator4;
    private com.toedter.calendar.JDateChooser jd_Ingreso;
    private com.toedter.calendar.JDateChooser jd_Salida;
    private javax.swing.JTable jt_Reservas;
    public static javax.swing.JTextField jt_nombre;
    private javax.swing.JLabel jt_t_registros;
    private javax.swing.JLabel lb_Id;
    private javax.swing.JLabel lb_errorFechaIngreso;
    private javax.swing.JLabel lb_errorFechaSalida;
    private javax.swing.JLabel lb_errorHabitacion;
    private javax.swing.JLabel lb_errorIngreso;
    private javax.swing.JLabel lb_errorNombre;
    private javax.swing.JLabel lb_errorStatusReserva;
    // End of variables declaration//GEN-END:variables
}
