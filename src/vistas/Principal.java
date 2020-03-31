/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import Utilerias.CambiaPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import Utilerias.MenuItem;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import controladores.*;
import vistas.ModuloAdmin.*;

/**
 *
 * @
 */
public class Principal extends javax.swing.JFrame implements Runnable {
    //se isa en la validacion de cajas abiertas para que el sistema no se pueda cerrar 

    private ControladorCaja cecaja = new ControladorCaja();
    private ControladorEstadoCaja cescaja = new ControladorEstadoCaja();
    private Pn_NuevaCategoria p = new Pn_NuevaCategoria();
    private String hora, minutos, segundos;
    private Thread hilo;
    private ControladorRecepciones crp = new ControladorRecepciones();
    private ControladorCFDI cf = new ControladorCFDI();
    private ControladorCategorias cc = new ControladorCategorias();
    private ControladorClientes ccl = new ControladorClientes();
    private ControladorConfiguracion cco = new ControladorConfiguracion();
    private ControladorHabitaciones ch = new ControladorHabitaciones();
    private ControladorPisos cp = new ControladorPisos();
    private ControladorReservaciones cr = new ControladorReservaciones();
    private ControladorCobros ccobro = new ControladorCobros();
    private ControladorEstatusHabitaciones cesHabi = new ControladorEstatusHabitaciones();
    //necesario para validar permisos
    private ControladorPermisos cperm = new ControladorPermisos();
    private ControladorModulos cmod = new ControladorModulos();
    private ControladorEstatusPermisos eperm = new ControladorEstatusPermisos();
//    Pn_NuevaCategoria p = new Pn_NuevaCategoria();
    public static String User = sesion.Username;
    //necesario para el control del administrador unicamente
    MenuItem Administrador;
    MenuItem Configuracion;
    MenuItem AdministracionCaja;
    //fin 
    //para abrir la ventana de sesion al dar click en cerrar sesion 
    sesion se = new sesion();
//fin
    Date now = new Date(); // java.util.Date, NOT java.sql.Date or java.sql.Timestamp!
    String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(now);

    /**
     * Creates new form Main
     */
    public Principal() {
        //ajusta caracteristicas de la pantalla como quitar el decorado por default y 
        //la posibilidad de arrastrar la aplicacion dentro del escritorio
        setPantalla();
        //fin
        sesion.ventana = true;
        initComponents();
        //PARA QUE APAREZCA LA PANTALLE DE INICIO AL COMENZAR LA APLICACION
        //new CambiaPanel(pnlPrincipal, new Pn_Recepcion());
        //PARA QUE APARECA LA PANTALLA DEL CALENDARIO AL INICAR LA APLICACION 
        new CambiaPanel(pnlPrincipal, new Pn_CalendarioReservaciones());
        //asigna la imagen de icono de la aplicacion que se muestra en la barra de tareas
        setIconSystem();
        //fin
        //metodo para asignar el tamaño de la aplicacion 
        full_Screen();
        //fin
        //ajusta la aplicacion al centro de la pantalla
        centrarPantalla();
        //fin
        //ajusta la visibilidad y velocidad del movimiento con el scroll
        ajustesDeScroll();
        //fin
        //muestra el nombre del usuario logueado dentro de la aplicacion
        Bienvenida.setText("Bienvenido " + User);
        //
        //ejecuta la funciones necesarias para crear el menu lateral con todos los accesos
        execute();
        //
        cperm.tablaPermisos();
        cmod.tablaModulos();
        eperm.tablaEstatusPermisos();
        cr.tablaReservaciones();
        cf.tablaCFDI();
        cc.tablaCategorias();
        ccl.tablaClientes();
        ch.tablaHabitaciones();
        cp.tablaPisos();
        cesHabi.tablaEstadoHabiaciones();
        ccobro.tablaCobros2("");

        //muestra dependiendo del usuario, lo modulos que le corresponden
        comparadorPrivilegios();
        //fin
        //muestra datos de fecha y tiempo actuales
        mostrarFecha();
        mostrarHora();
        //fin

    }

    public void comparadorPrivilegios() {
        if (!(User.equals("admin"))) { //para cualquier otro usuario
            Administrador.setVisible(false);
            Configuracion.setVisible(false);
            AdministracionCaja.setVisible(false);
        } else {
            Administrador.setVisible(true);
            Configuracion.setVisible(true);
            AdministracionCaja.setVisible(true);
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

        pnlCentro = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        scrollPane_pnPrncipal = new javax.swing.JScrollPane();
        pnlPrincipal = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lb_AcercaDe = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lb_fecha = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lb_hora = new javax.swing.JLabel();
        lb_min = new javax.swing.JButton();
        lb_close = new javax.swing.JButton();
        panelMenu = new javax.swing.JPanel();
        scroll_Menu = new javax.swing.JScrollPane();
        menus = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Bienvenida = new javax.swing.JLabel();
        jl_Icon_User = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlCentro.setBackground(new java.awt.Color(0, 0, 0));

        jPanel9.setBackground(new java.awt.Color(84, 110, 122));
        jPanel9.setToolTipText("");

        scrollPane_pnPrncipal.setBackground(new java.awt.Color(84, 110, 122));
        scrollPane_pnPrncipal.setBorder(null);

        pnlPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        pnlPrincipal.setLayout(new java.awt.BorderLayout());
        scrollPane_pnPrncipal.setViewportView(pnlPrincipal);

        jPanel3.setBackground(new java.awt.Color(233, 235, 238));
        jPanel3.setToolTipText("");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 34)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(28, 37, 47));
        jLabel6.setText("Sistema de Control para Hoteles");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 10)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(28, 37, 47));
        jLabel7.setText("Dirección: Calle 7 Pte 621, Niños Héroes, 75760 Tehuacán, Pue. Teléfono: 238 138 5348");

        lb_AcercaDe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/about32x32.png"))); // NOI18N
        lb_AcercaDe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lb_AcercaDe.setFocusable(false);
        lb_AcercaDe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_AcercaDeMouseClicked(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/date24x24.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel10.setText("Fecha Actual");

        lb_fecha.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lb_fecha.setForeground(new java.awt.Color(128, 128, 131));
        lb_fecha.setText("dd/mm/yy");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/time24x24.png"))); // NOI18N
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel12.setText("Hora actual");

        lb_hora.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lb_hora.setForeground(new java.awt.Color(128, 128, 131));
        lb_hora.setText("00:00:00");

        lb_min.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/icons8_Minus_32px_1.png"))); // NOI18N
        lb_min.setToolTipText("Minimize");
        lb_min.setBorder(null);
        lb_min.setBorderPainted(false);
        lb_min.setContentAreaFilled(false);
        lb_min.setFocusPainted(false);
        lb_min.setFocusable(false);
        lb_min.setRequestFocusEnabled(false);
        lb_min.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/icons8_Minus_30px_3.png"))); // NOI18N
        lb_min.setVerifyInputWhenFocusTarget(false);
        lb_min.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_minMouseClicked(evt);
            }
        });
        lb_min.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lb_minActionPerformed(evt);
            }
        });

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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(jLabel6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(87, 87, 87)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel10)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 8, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_fecha)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lb_hora)
                                .addGap(48, 48, 48)))
                        .addComponent(lb_AcercaDe)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(lb_min, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_close, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_min, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_close, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11)
                                    .addComponent(lb_hora, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb_fecha, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(lb_AcercaDe))
                                .addGap(5, 5, 5))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollPane_pnPrncipal)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollPane_pnPrncipal, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlCentroLayout = new javax.swing.GroupLayout(pnlCentro);
        pnlCentro.setLayout(pnlCentroLayout);
        pnlCentroLayout.setHorizontalGroup(
            pnlCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCentroLayout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        pnlCentroLayout.setVerticalGroup(
            pnlCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(pnlCentro, java.awt.BorderLayout.CENTER);

        panelMenu.setBackground(new java.awt.Color(28, 37, 47));
        panelMenu.setPreferredSize(new java.awt.Dimension(250, 384));

        scroll_Menu.setBackground(new java.awt.Color(28, 37, 47));
        scroll_Menu.setBorder(null);

        menus.setBackground(new java.awt.Color(28, 37, 47));
        menus.setFocusable(false);
        menus.setLayout(new javax.swing.BoxLayout(menus, javax.swing.BoxLayout.Y_AXIS));
        scroll_Menu.setViewportView(menus);

        jPanel2.setBackground(new java.awt.Color(28, 37, 47));

        Bienvenida.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        Bienvenida.setForeground(new java.awt.Color(128, 128, 131));
        Bienvenida.setText("HOTEL PARAISO INN");

        jl_Icon_User.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/archivodepartamento.png"))); // NOI18N
        jl_Icon_User.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jl_Icon_User))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(Bienvenida)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jl_Icon_User)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bienvenida))
        );

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Navegacion");

        jPanel6.setBackground(new java.awt.Color(28, 37, 47));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(58, 159, 171));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("VERSION 1.0");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(58, 159, 171));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("© DERECHOS RESERVADOS 2020");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(58, 159, 171));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("SISTEMA DE CONTROL HOTELERO");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(29, 29, 29)
                .addComponent(jLabel16)
                .addContainerGap())
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                    .addContainerGap(42, Short.MAX_VALUE)
                    .addComponent(jLabel18)
                    .addGap(34, 34, 34)))
        );

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scroll_Menu)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addComponent(jLabel8)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(4, 4, 4)
                .addComponent(scroll_Menu, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
        );

        getContentPane().add(panelMenu, java.awt.BorderLayout.LINE_START);

        setSize(new java.awt.Dimension(1243, 836));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lb_AcercaDeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_AcercaDeMouseClicked
        Pn_About about = new Pn_About(this, true);
        about.setVisible(true);

    }//GEN-LAST:event_lb_AcercaDeMouseClicked

    private void lb_minMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_minMouseClicked
        this.setState(ICONIFIED);
    }//GEN-LAST:event_lb_minMouseClicked

    private void lb_minActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lb_minActionPerformed

    }//GEN-LAST:event_lb_minActionPerformed

    private void lb_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lb_closeActionPerformed
        Pn_Alert_Warning_Salir wa = new Pn_Alert_Warning_Salir(this, true);
        wa.lb_titulo.setText("¿ESTAS SEGURO QUE DESEA SALIR?");
        wa.jb_aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        wa.setVisible(true);

    }//GEN-LAST:event_lb_closeActionPerformed
//inicializa el menu lateral 

    private void execute() {
        //ICONOS PARA LOS BOTONES PRINCIPALES DEL MENU LATERAL
        ImageIcon iconCalendario = new ImageIcon(getClass().getResource("/Imagenes/icons/calendario32x32.png"));
        ImageIcon iconRecepcion = new ImageIcon(getClass().getResource("/Imagenes/icons/recep32x32.png"));
        ImageIcon iconClientes = new ImageIcon(getClass().getResource("/Imagenes/icons/cliente-32x32.png"));
        ImageIcon iconConfiguracion = new ImageIcon(getClass().getResource("/Imagenes/icons/settingsMenu32x32.png"));
        ImageIcon iconReservaciones = new ImageIcon(getClass().getResource("/Imagenes/icons/reservaciones32x32.png"));
        ImageIcon iconReportes = new ImageIcon(getClass().getResource("/Imagenes/reporte32x32.png"));
        ImageIcon iconFacturas = new ImageIcon(getClass().getResource("/Imagenes/icons/facturas32x32.png"));
        ImageIcon iconAdministrador = new ImageIcon(getClass().getResource("/Imagenes/icons/administrador32x32.png"));
        ImageIcon iconAdminCaja = new ImageIcon(getClass().getResource("/Imagenes/icons/cajaAdmin32x32.png"));
        ImageIcon iconSesion = new ImageIcon(getClass().getResource("/Imagenes/icons/cerrarSesion32x32.png"));
        //ICONO PARA LOS SUBMENUS
        ImageIcon subMenus = new ImageIcon(getClass().getResource("/Imagenes/newarrow20x20.png"));
        //  SUBMENU PARA RESERVACIONES
        MenuItem NuevaReservacion = new MenuItem(subMenus, "Nueva Reservación", 10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new CambiaPanel(pnlPrincipal, new Pn_Reservaciones());

            }
        });
//  SUBMENU PARA CLIENTES

        MenuItem AgregarCliente = new MenuItem(subMenus, "Nuevo Cliente", 10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new CambiaPanel(pnlPrincipal, new Pn_NuevoCliente());
            }
        });

// SUBMENU DE CONFIGURACION
        MenuItem NuevoNivel = new MenuItem(subMenus, "Nuevo Piso", 10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new CambiaPanel(pnlPrincipal, new Pn_NuevoPiso());
            }
        });
        MenuItem NuevaCategoria = new MenuItem(subMenus, "Nueva Categoria", 10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new CambiaPanel(pnlPrincipal, new Pn_NuevaCategoria());
            }
        });
        MenuItem NuevaHabitacion = new MenuItem(subMenus, "Nueva Habitación", 10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new CambiaPanel(pnlPrincipal, new Pn_NuevaHabitacion());
            }
        });
        //  SUBMENU PARA FACTURACION
        MenuItem NuevaFactura = new MenuItem(subMenus, "Nueva Factura", 10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new CambiaPanel(pnlPrincipal, new Pn_Facturacion());
            }
        });
        //  SUBMENU PARA REPORTES
        MenuItem GenerarReportes = new MenuItem(subMenus, "Reportes/Estadisticas", 10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new CambiaPanel(pnlPrincipal, new Pn_NuevoReportes());
                //  new CambiaPanel(pnlPrincipal, new Pn_CalendarioReservaciones());
            }
        });
        //  SUBMENU PARA Administrador
        MenuItem NuevoEmpleado = new MenuItem(subMenus, "Nuevo Empleado", 10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                new CambiaPanel(pnlPrincipal, new Pn_NuevoEmpleado());
            }
        });
        MenuItem ConfiguraciónServidor = new MenuItem(subMenus, "Conexión", 10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                new CambiaPanel(pnlPrincipal, new Pn_ConfServer());
            }
        });
        MenuItem Permisos = new MenuItem(subMenus, "Permisos", 10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                new CambiaPanel(pnlPrincipal, new Pn_PermisosAccesos());
            }
        });
        //  SUBMENU PARA Administracion de caja
        MenuItem AbrirCaja = new MenuItem(subMenus, "Apertura de Caja", 10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                new CambiaPanel(pnlPrincipal, new Pn_MovimientoAbrirCaja());
            }
        });
        MenuItem CerrarCaja = new MenuItem(subMenus, "Cierre de Caja", 10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                new CambiaPanel(pnlPrincipal, new Pn_MovimientoCerrarCaja());
            }
        });
        //submenu gastos 
        MenuItem NuevoGasto = new MenuItem(subMenus, "Nuevo Gasto", 35, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Pn_Alert_NuevoGasto gasto = new Pn_Alert_NuevoGasto(Principal.this, true);
                gasto.setVisible(true);

            }
        });
        /*submenu inventario SIN USO AUN 
 MenuItem NuevoProducto = new MenuItem(subMenus, "Nuevo Producto", 35, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                  new CambiaPanel(pnlPrincipal, new Pn_Inventario());
            }
        });
  MenuItem ProductoxHabitacion = new MenuItem(subMenus, "Asinación de Productos", 35, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                  new CambiaPanel(pnlPrincipal, new Pn_InventarioxHabitacion());
            }
        });
         *///FIN SIN USO AUN 
//APARTADO DE TODOS LOS MENUS
        MenuItem CalendarioReservas = new MenuItem(iconCalendario, "Calendario", 35, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new CambiaPanel(pnlPrincipal, new Pn_CalendarioReservaciones());
            }
        });

        MenuItem Reservaciones = new MenuItem(iconReservaciones, "Reservaciones", 35, null, NuevaReservacion);

        MenuItem Recepcion = new MenuItem(iconRecepcion, "Recepción", 35, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new CambiaPanel(pnlPrincipal, new Pn_Recepcion());
            }
        });

        MenuItem Clientes = new MenuItem(iconClientes, "Clientes", 35, null, AgregarCliente);
        Configuracion = new MenuItem(iconConfiguracion, "Hotel", 35, null, NuevoNivel, NuevaCategoria, NuevaHabitacion);
        MenuItem Facturas = new MenuItem(iconFacturas, "Facturacion", 35, null, NuevaFactura);
        MenuItem GastosHotel = new MenuItem(iconAdminCaja, "Caja", 35, null, NuevoGasto);
        MenuItem Reportes = new MenuItem(iconReportes, "Reportes", 35, null, GenerarReportes);
        AdministracionCaja = new MenuItem(iconAdminCaja, "Caja Admin", 35, null, AbrirCaja, CerrarCaja);
        /*SIN USO AUN
        MenuItem Inventario = new MenuItem(iconAdministrador, "Inventario", 35, null, NuevoProducto,ProductoxHabitacion);
         */
        Administrador = new MenuItem(iconAdministrador, "Administrador", 35, null, NuevoEmpleado, ConfiguraciónServidor, Permisos);
        MenuItem Sesion = new MenuItem(iconSesion, "Cerrar Sesión", 35, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Pn_Alert_Warning_Salir wa = new Pn_Alert_Warning_Salir(Principal.this, true);
                wa.lb_titulo.setText("¿ESTAS SEGURO DE CERRAR SESIÓN?");
                wa.jb_aceptar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        Principal.this.dispose();
                        sesion.ventana = false;
                        se.setVisible(true);

                    }
                });
                wa.setVisible(true);
            }
        });
        //AQUI SE AGREGAR TODOS LOS NUEVOS MENUS Y SUBMENUS
        addMenu(CalendarioReservas, Recepcion, Reservaciones, Clientes, Facturas, Reportes, GastosHotel, Configuracion, Administrador, AdministracionCaja, Sesion); //Configuracion);
    }

    private void addMenu(MenuItem... menu) {
        for (int i = 0; i < menu.length; i++) {
            menus.add(menu[i]);
            ArrayList<MenuItem> subMenu = menu[i].getSubMenu();
            for (MenuItem m : subMenu) {
                addMenu(m);
            }
        }
        menus.revalidate();
    }
//metodo para eliminar el scrollbar

    private void ajustesDeScroll() {
        //scroll_Menu es el nombre del jscrollpane que contiene el panel del menu
        scroll_Menu.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        //incrementa a velocidad de scroleo
        scroll_Menu.getVerticalScrollBar().setUnitIncrement(20);
        //elimina el scrollBar vertical 

        //ELIMINA LA POSIBILIDAD DE REALIZAR SCROLL HORIZONTALMENTE
        scrollPane_pnPrncipal.setHorizontalScrollBarPolicy(scrollPane_pnPrncipal.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private void setPantalla() {

        //para eliminar el tittle bar
        this.setUndecorated(true);
        //para que no le cambien el tamaño
        this.setResizable(false);

    }

    private void setIconSystem() {
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logosmall.png")).getImage());
    }

    private void centrarPantalla() {
        //para dejar el menu centrado y estatico
        this.setLocationRelativeTo(null);

    }

    private void full_Screen() {
        //   Toolkit tk = Toolkit.getDefaultToolkit();
//        int ysize = (int) tk.getScreenSize().getHeight();
        //   this.setSize(xsize, ysize);
        //this.setExtendedState(6);
        this.setSize(1280, 720);
    }

    public void run() {
        // Thread current= Thread.currentThread();
        for (int i = 1; i > 0; i++) {
            if (i > 0) {
                //  validarPermisos();
                hora();
                lb_hora.setText(hora + ":" + minutos + ":" + segundos);
            }
        }
    }

    private String fecha() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MM/YYYY");
        return formatofecha.format(fecha);
    }

    private void hora() {
        Calendar calendario = new GregorianCalendar();
        Date horaactual = new Date();
        calendario.setTime(horaactual);
        hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);

    }

    private void mostrarFecha() {
        //componente para fecha
        lb_fecha.setText(fecha());
    }

    private void mostrarHora() {
        //componentes para la hora
        hilo = new Thread(this);
        hilo.start();
        setVisible(true);
    }

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Bienvenida;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel jl_Icon_User;
    private javax.swing.JLabel lb_AcercaDe;
    private javax.swing.JButton lb_close;
    private javax.swing.JLabel lb_fecha;
    private javax.swing.JLabel lb_hora;
    private javax.swing.JButton lb_min;
    private javax.swing.JPanel menus;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel pnlCentro;
    public javax.swing.JPanel pnlPrincipal;
    private javax.swing.JScrollPane scrollPane_pnPrncipal;
    private javax.swing.JScrollPane scroll_Menu;
    // End of variables declaration//GEN-END:variables
}
