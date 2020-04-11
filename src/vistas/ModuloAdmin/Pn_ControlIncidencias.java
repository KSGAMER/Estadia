/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.ModuloAdmin;

import vistas.Alertas.Pn_Alert_Eliminar;
import controladores.ControladorEscritura;
import controladores.ControladorFormularioTab;
import controladores.ControladorIncidencias;
import controladores.ControladorEstadoIncidencia;
import controladores.ControladorHabitaciones;
import ds.desktop.notify.DesktopNotify;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import objetos.ObjetoEstadoIncidencia;
import objetos.ObjetoHabitacion;
import vistas.Principal;

/**
 *
 * @author fenix
 */
public class Pn_ControlIncidencias extends javax.swing.JPanel {
//NECESARIO PARA FUNCIONES DE ESTE MODULO 

    private ControladorIncidencias cin = new ControladorIncidencias();
    private ControladorEstadoIncidencia cesin = new ControladorEstadoIncidencia();
    private ControladorEscritura ce = new ControladorEscritura();
    private ControladorFormularioTab cft = new ControladorFormularioTab();
    private ControladorHabitaciones ch = new ControladorHabitaciones();
    DefaultTableModel NewTable;

    //FIN
    //NECESARIO PARA EL USO DE LA NOTIFICACION DINAMICA DE BOTON ELIMINAR ()
    Frame principal;
//FIN
    private int seleccion;
    //necesario para obtener la fecha con hora para las nuevas cajas
    Date now = new Date(); // java.util.Date, NOT java.sql.Date or java.sql.Timestamp!
    String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(now);
    String horaActual = new SimpleDateFormat("HH:mm:ss").format(now);

    /**
     * Creates new form Pn_NuevoNivel
     */
    public Pn_ControlIncidencias() {
        initComponents();
        //INICIA LOS VALORES DEL FORMULARIO A SU VALOR ORIGINAL
        datosIniciales();
        //FIN
        //APARIENCIA DE LA TABLA
        RowHeaderApariencia();
        RowApariencia();
        //FIN
        ch.tablaHabitaciones();
        cesin.tablaEstadoIncidencia();
        cargarEstadosIncidencias();
        cargarHabitaciones();
        //CARGA LOS VALORES EN LA TABLA
        cTabla();
        //FIN
        //ASIGNA TAMAÑOS DE ANCHURA A LAS COLUMNAS
        tamañoTabla();
        //FIN
        //para ajustar el tecto al tamaño del jtextarea
        jta_observaciones.setLineWrap(true);
        //FIN
    }

    private void cTabla() {
        jtabla_incidencias.setModel(cin.tablaIncidencias());
        jt_t_registros.setText(String.valueOf(jtabla_incidencias.getRowCount()));
    }

    private void tamañoTabla() {
        TableColumnModel columnModel = jtabla_incidencias.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(5);
        columnModel.getColumn(1).setPreferredWidth(50);
        columnModel.getColumn(2).setPreferredWidth(300);

    }

   
    private void RowApariencia() {

        jtabla_incidencias.setFocusable(false);

        //espacio entre comulnas
        jtabla_incidencias.setIntercellSpacing(new Dimension(0, 1));
        //altura de columnas 
        jtabla_incidencias.setRowHeight(25);
        //margen entre filas
        jtabla_incidencias.setRowMargin(0);
//sin lineas verticles
        jtabla_incidencias.setShowVerticalLines(false);
        jtabla_incidencias.setSelectionBackground(new Color(97, 212, 195));

    }

    private void RowHeaderApariencia() {
        jtabla_incidencias.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 14));
        jtabla_incidencias.getTableHeader().setOpaque(false);
        jtabla_incidencias.getTableHeader().setBackground(new Color(32, 136, 203));
        jtabla_incidencias.getTableHeader().setForeground(new Color(255, 255, 255));

    }

    private void datosIniciales() {
        lb_errorNombre.setText("*");
        lb_errorNombre.setForeground(new Color(84, 110, 122));

        lb_Id.setText("*");
        jt_nombre.setText("Ingresar Nombre");
        jta_observaciones.setText("Ingresar Observaciones");
        cb_Habitacion.setSelectedIndex(0);
        cb_estadoIncidencia.setSelectedIndex(0);
        btn_Ingresar.setEnabled(true);
        btn_Modificar.setEnabled(false);
        btn_Eliminar.setEnabled(false);
        lb_errorNombre.setForeground(new Color(84, 110, 122));
        lb_errorObservaciones.setForeground(new Color(84, 110, 122));//[84,110,122]
        lb_errorEstadoIncidencia.setForeground(new Color(84, 110, 122));
        lb_errorHabitacion.setForeground(new Color(84, 110, 122));
    }

    private Boolean validarEscritura() {
        Boolean val = true;
        //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(jt_nombre.getText().equals("Ingresar Nombre")) && !(jt_nombre.getText().equals(""))) {
            lb_errorNombre.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorNombre.setForeground(Color.RED);
            val = false;
        }
           //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(jta_observaciones.getText().equals("Ingresar Observaciones")) && !(jta_observaciones.getText().equals(""))) {
            lb_errorObservaciones.setForeground(new Color(84, 110, 122));//[84,110,122]
        } else {
            lb_errorObservaciones.setForeground(Color.RED);
            val = false;
        }


        return val;
    }
    
    private Boolean validarSeleccion() {
        Boolean val = true;
        if (!(cb_estadoIncidencia.getSelectedIndex() == 0)) {
            lb_errorEstadoIncidencia.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorEstadoIncidencia.setForeground(Color.RED);
            val = false;
        }
        if (!(cb_Habitacion.getSelectedIndex() == 0)) {
            lb_errorHabitacion.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorHabitacion.setForeground(Color.RED);
            val = false;
        }
        return val;

    }
private void cargarEstadosIncidencias(){
      DefaultComboBoxModel cb = new DefaultComboBoxModel();
        cb.addElement("Seleccionar Estado");
        for (ObjetoEstadoIncidencia campos : cesin.seleccionarEstadoIncidencia()) {

            cb.addElement(campos.getNombre());
        }
        cb_estadoIncidencia.setModel(cb);
}
  private void cargarHabitaciones() {
        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        cb.addElement("Seleccionar Habitación");

        for (ObjetoHabitacion campos : ch.selectHabitacion()) {
            if (!(campos.getIdEstadoHabitacion() == 1)) {
                //break;
            } else {
                cb.addElement(campos.getNombre());
                
            }
        }
        cb_Habitacion.setModel(cb);
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
        jtabla_incidencias = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jt_nombre = new javax.swing.JTextField();
        jta_observaciones = new javax.swing.JTextArea();
        jSeparator4 = new javax.swing.JSeparator();
        btn_Modificar = new principal.MaterialButton();
        btn_Eliminar = new principal.MaterialButton();
        lb_Id = new javax.swing.JLabel();
        lb_errorNombre = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jt_t_registros = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jb_limpiarCampos2 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        cb_estadoIncidencia = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        btn_Ingresar = new principal.MaterialButton();
        cb_Habitacion = new javax.swing.JComboBox<>();
        lb_errorObservaciones = new javax.swing.JLabel();
        lb_errorEstadoIncidencia = new javax.swing.JLabel();
        lb_errorHabitacion = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(84, 110, 122));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtabla_incidencias.setModel(new javax.swing.table.DefaultTableModel(
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
        jtabla_incidencias.getTableHeader().setResizingAllowed(false);
        jtabla_incidencias.getTableHeader().setReorderingAllowed(false);
        jtabla_incidencias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtabla_incidenciasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtabla_incidencias);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 970, 100));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Nombre ");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 140, -1));

        jt_nombre.setBackground(new java.awt.Color(84, 110, 122));
        jt_nombre.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
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
        jPanel1.add(jt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 210, -1));

        jta_observaciones.setColumns(20);
        jta_observaciones.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jta_observaciones.setForeground(new java.awt.Color(153, 153, 153));
        jta_observaciones.setRows(5);
        jta_observaciones.setText("Ingresar Observaciones");
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
        jPanel1.add(jta_observaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 390, 80));

        jSeparator4.setBackground(new java.awt.Color(128, 128, 131));
        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 190, 10));

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
        jPanel1.add(btn_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 380, 220, 40));

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
        jPanel1.add(btn_Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 440, 220, 40));

        lb_Id.setForeground(new java.awt.Color(84, 110, 122));
        jPanel1.add(lb_Id, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 30, 20));

        lb_errorNombre.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorNombre.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorNombre.setText("*");
        jPanel1.add(lb_errorNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 10, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Total de registros ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 230, 100, 20));

        jt_t_registros.setEditable(false);
        jt_t_registros.setBackground(new java.awt.Color(84, 110, 122));
        jt_t_registros.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jt_t_registros.setForeground(new java.awt.Color(255, 255, 255));
        jt_t_registros.setBorder(null);
        jPanel1.add(jt_t_registros, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 230, 30, 20));

        jPanel2.setBackground(new java.awt.Color(84, 110, 122));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("VISTA GENERAL DE INCIDENCIAS");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Inicio > Adminstrador >Control de Incidencias");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 327, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(91, 91, 91))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 50));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Formulario de Cambios");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tabla de incidencias");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1090, 10));

        jb_limpiarCampos2.setBackground(new java.awt.Color(84, 110, 122));
        jb_limpiarCampos2.setForeground(new java.awt.Color(84, 110, 122));
        jb_limpiarCampos2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/clean24x24.png"))); // NOI18N
        jb_limpiarCampos2.setBorder(null);
        jb_limpiarCampos2.setBorderPainted(false);
        jb_limpiarCampos2.setContentAreaFilled(false);
        jb_limpiarCampos2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_limpiarCampos2.setFocusPainted(false);
        jb_limpiarCampos2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/cleanSeleccionar24x24.png"))); // NOI18N
        jb_limpiarCampos2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_limpiarCampos2ActionPerformed(evt);
            }
        });
        jPanel1.add(jb_limpiarCampos2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 260, 40, -1));

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Observaciones");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 130, -1));

        jLabel22.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("Estado de Incidencia:");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, 130, -1));

        cb_estadoIncidencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Estado" }));
        cb_estadoIncidencia.setBorder(null);
        cb_estadoIncidencia.setFocusable(false);
        jPanel1.add(cb_estadoIncidencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 490, 190, -1));

        jLabel23.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("Habitación : ");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 130, -1));

        btn_Ingresar.setBackground(new java.awt.Color(40, 180, 99));
        btn_Ingresar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Ingresar.setText("Agregar");
        btn_Ingresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Ingresar.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_Ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_IngresarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 320, 220, 40));

        cb_Habitacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Habitación" }));
        cb_Habitacion.setBorder(null);
        cb_Habitacion.setFocusable(false);
        jPanel1.add(cb_Habitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 440, 190, -1));

        lb_errorObservaciones.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorObservaciones.setText("*");
        jPanel1.add(lb_errorObservaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 10, -1));

        lb_errorEstadoIncidencia.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorEstadoIncidencia.setText("*");
        jPanel1.add(lb_errorEstadoIncidencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 10, -1));

        lb_errorHabitacion.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorHabitacion.setText("*");
        jPanel1.add(lb_errorHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 10, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarActionPerformed
        try {
            if (lb_Id.getText().equals("*")) {

                DesktopNotify.showDesktopMessage("Error", "DEBE SELECCIONAR UN ELEMENTO DE LA TABLA PARA PODER SER ELIMINADO", DesktopNotify.ERROR);

            } else {
                
                Pn_Alert_Eliminar ale = new Pn_Alert_Eliminar(principal, true);
                ale.lb_titulo.setText("¿Esta seguro de eliminar este elemento?");
                ale.jb_aceptar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        cin.eliminarIncidencias(Integer.parseInt(lb_Id.getText()));
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

    private void btn_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModificarActionPerformed
        try {
            if (!validarEscritura() == true || !validarSeleccion() == true) {

                DesktopNotify.showDesktopMessage("Error", "REVISAR CAMPOS OBLIGATORIOS", DesktopNotify.ERROR);

            } else {
                cin.actualizarIncidencias(jt_nombre.getText(), jta_observaciones.getText(), fechaActual, horaActual, Principal.User, String.valueOf(cb_Habitacion.getSelectedItem()), String.valueOf(cb_estadoIncidencia.getSelectedItem()), Integer.parseInt(lb_Id.getText()));
                NewTable = new DefaultTableModel();
                cTabla();
                tamañoTabla();
                datosIniciales();
            }

        } catch (Exception e) {

       
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ModificarActionPerformed

    private void jta_observacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jta_observacionesMouseClicked
        cft.formFocusGainJTextArea(jta_observaciones);
    }//GEN-LAST:event_jta_observacionesMouseClicked

    private void jta_observacionesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jta_observacionesFocusLost
        cft.formFocusLostJTextArea(jta_observaciones, "Ingresar Observaciones");
    }//GEN-LAST:event_jta_observacionesFocusLost

    private void jt_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_nombreKeyTyped
        ce.typedCharsAndSpaceAndDigits(evt, jt_nombre);
    }//GEN-LAST:event_jt_nombreKeyTyped

    private void jt_nombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_nombreMouseClicked
        cft.formFocusGain(jt_nombre);
    }//GEN-LAST:event_jt_nombreMouseClicked

    private void jt_nombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_nombreFocusLost
        cft.formFocusLostJTextField(jt_nombre, "Ingresar Nombre");
    }//GEN-LAST:event_jt_nombreFocusLost

    private void jtabla_incidenciasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtabla_incidenciasMouseClicked
        seleccion = jtabla_incidencias.rowAtPoint(evt.getPoint());
        btn_Ingresar.setEnabled(false);
        btn_Modificar.setEnabled(true);
        btn_Eliminar.setEnabled(true);

        lb_Id.setText(String.valueOf(jtabla_incidencias.getValueAt(seleccion, 0)));
        jt_nombre.setText(String.valueOf(jtabla_incidencias.getValueAt(seleccion, 1)));
        jta_observaciones.setText(String.valueOf(jtabla_incidencias.getValueAt(seleccion, 2)));
        cb_Habitacion.getModel().setSelectedItem(jtabla_incidencias.getValueAt(seleccion, 6));
        cb_estadoIncidencia.getModel().setSelectedItem(jtabla_incidencias.getValueAt(seleccion, 7));
    }//GEN-LAST:event_jtabla_incidenciasMouseClicked

    private void jta_observacionesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jta_observacionesKeyPressed
       
    }//GEN-LAST:event_jta_observacionesKeyPressed

    private void jt_nombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_nombreFocusGained
        cft.formFocusGain(jt_nombre);
    }//GEN-LAST:event_jt_nombreFocusGained

    private void jta_observacionesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jta_observacionesFocusGained
        cft.formFocusGainJTextArea(jta_observaciones);
    }//GEN-LAST:event_jta_observacionesFocusGained

    private void jb_limpiarCampos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_limpiarCampos2ActionPerformed
        datosIniciales();        // TODO add your handling code here:
    }//GEN-LAST:event_jb_limpiarCampos2ActionPerformed

    private void btn_IngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_IngresarActionPerformed
        try {
            if (!validarEscritura() == true || !validarSeleccion() == true) {

                DesktopNotify.showDesktopMessage("Error", "REVISAR CAMPOS OBLIGATORIOS", DesktopNotify.ERROR);

            } else {
                cin.insertarIncidencias(jt_nombre.getText(), jta_observaciones.getText(), fechaActual, horaActual, Principal.User, String.valueOf(cb_Habitacion.getSelectedItem()), String.valueOf(cb_estadoIncidencia.getSelectedItem()));
                NewTable = new DefaultTableModel();
                cTabla();
                tamañoTabla();
                datosIniciales();
            }

        } catch (Exception e) {

       
        }

    }//GEN-LAST:event_btn_IngresarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static principal.MaterialButton btn_Eliminar;
    private principal.MaterialButton btn_Ingresar;
    public static principal.MaterialButton btn_Modificar;
    private javax.swing.JComboBox<String> cb_Habitacion;
    private javax.swing.JComboBox<String> cb_estadoIncidencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JButton jb_limpiarCampos2;
    private javax.swing.JTextField jt_nombre;
    private javax.swing.JTextField jt_t_registros;
    private javax.swing.JTextArea jta_observaciones;
    private javax.swing.JTable jtabla_incidencias;
    private javax.swing.JLabel lb_Id;
    private javax.swing.JLabel lb_errorEstadoIncidencia;
    private javax.swing.JLabel lb_errorHabitacion;
    private javax.swing.JLabel lb_errorNombre;
    private javax.swing.JLabel lb_errorObservaciones;
    // End of variables declaration//GEN-END:variables
}
