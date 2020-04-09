/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.ModuloAdmin;

import vistas.Alertas.Pn_Alert_Eliminar;
import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;
import objetos.*;
import controladores.*;
import ds.desktop.notify.DesktopNotify;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import vistas.*;

/**
 *
 * @author fenix
 */
public class Pn_PermisosAccesos extends javax.swing.JPanel {
//NECESARIO PARA FUNCIONES DE ESTE MODULO 

    ControladorEstatusPermisos eperm = new ControladorEstatusPermisos();
    ControladorPermisos cperm = new ControladorPermisos();
    ControladorUsuarios cuser = new ControladorUsuarios();
    ControladorModulos cmod = new ControladorModulos();
    DefaultTableModel NewTable = new DefaultTableModel();
    //fin

    //NECESARIO PARA EL USO DE LA NOTIFICACION DINAMICA DE BOTON ELIMINAR ()
    Frame Principal;
    //FIN

    /**
     * Creates new form NuevoEmpleado1
     */
    public Pn_PermisosAccesos() {
        initComponents();
        eperm.tablaEstatusPermisos();
        cperm.tablaPermisos();
        cuser.tablaUsuarios();
        cmod.tablaModulos();
        cTabla();
        cargarUsuarios();
        cargarModulo();
        cargarConsultar();
        cargarAgregar();
        cargarActualizar();
        cargarEliminar();

    }

    private void datosIniciales() {
        lb_Id.setText("*");
        cb_usuario.setSelectedIndex(0);
        cb_modulo.setSelectedIndex(0);
        cb_consultar.setSelectedIndex(0);
        cb_agregar.setSelectedIndex(0);
        cb_actualizar.setSelectedIndex(0);
        cb_eliminar.setSelectedIndex(0);
        btn_Ingresar.setEnabled(true);
        btn_Modificar.setEnabled(false);
        btn_Eliminar.setEnabled(false);
    }

    private void bloquearComponentes() {
        cb_modulo.setEnabled(false);
        cb_consultar.setEnabled(false);
        cb_agregar.setEnabled(false);
        cb_actualizar.setEnabled(false);
        cb_eliminar.setEnabled(false);
        btn_Modificar.setEnabled(false);
        btn_Eliminar.setEnabled(false);
    }

    private void desbloquearComponentes() {
        cb_modulo.setEnabled(true);
        cb_consultar.setEnabled(true);
        cb_agregar.setEnabled(true);
        cb_actualizar.setEnabled(true);
        cb_eliminar.setEnabled(true);
        btn_Modificar.setEnabled(true);
        btn_Eliminar.setEnabled(true);
    }

    private void cTabla() {
        jt_Privilegios.setModel(cperm.tablaPermisos());

    }

    private void cargarUsuarios() {

        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        cb.addElement("Seleccionar Usuario");
        for (ObjetoUsuario campos : cuser.selectUsuario()) {
            cb.addElement(campos.getUsername());
        }
        cb_usuario.setModel(cb);

    }

    private void cargarModulo() {

        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        cb.addElement("Seleccionar Módulo");
        for (ObjetoModulo campos : cmod.selectModulo()) {
            cb.addElement(campos.getNombre());
        }
        cb_modulo.setModel(cb);

    }

    private void cargarConsultar() {

        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        cb.addElement("Seleccionar Permiso");
        for (ObjetoEstatusPermiso campos : eperm.selectEstatusPermiso()) {
            cb.addElement(campos.getNombre());
        }
        cb_consultar.setModel(cb);

    }

    private void cargarAgregar() {

        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        cb.addElement("Seleccionar Permiso");
        for (ObjetoEstatusPermiso campos : eperm.selectEstatusPermiso()) {
            cb.addElement(campos.getNombre());
        }

        cb_agregar.setModel(cb);

    }

    private void cargarActualizar() {

        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        cb.addElement("Seleccionar Permiso");
        for (ObjetoEstatusPermiso campos : eperm.selectEstatusPermiso()) {
            cb.addElement(campos.getNombre());
        }

        cb_actualizar.setModel(cb);

    }

    private void cargarEliminar() {

        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        cb.addElement("Seleccionar Permiso");
        for (ObjetoEstatusPermiso campos : eperm.selectEstatusPermiso()) {
            cb.addElement(campos.getNombre());
        }

        cb_eliminar.setModel(cb);
    }

    private Boolean validarSeleccion() {
        Boolean val = true;
        if (!(cb_usuario.getSelectedIndex() == 0)) {

            lb_errorUsuario.setForeground(new Color(233, 235, 238));//[233,235,238]
        } else {

            lb_errorUsuario.setForeground(Color.RED);

            val = false;
        }
        if (!(cb_modulo.getSelectedIndex() == 0)) {

            lb_errorModulo.setForeground(new Color(233, 235, 238));//[233,235,238]
        } else {

            lb_errorModulo.setForeground(Color.RED);

            val = false;
        }
        if (!(cb_consultar.getSelectedIndex() == 0)) {

            lb_errorConsultar.setForeground(new Color(233, 235, 238));//[233,235,238]
        } else {

            lb_errorConsultar.setForeground(Color.RED);

            val = false;
        }
        if (!(cb_agregar.getSelectedIndex() == 0)) {

            lb_errorAgregar.setForeground(new Color(233, 235, 238));//[233,235,238]
        } else {

            lb_errorAgregar.setForeground(Color.RED);

            val = false;
        }
        if (!(cb_actualizar.getSelectedIndex() == 0)) {

            lb_errorModificar.setForeground(new Color(233, 235, 238));//[233,235,238]
        } else {

            lb_errorModificar.setForeground(Color.RED);

            val = false;
        }
        if (!(cb_eliminar.getSelectedIndex() == 0)) {

            lb_errorEliminar.setForeground(new Color(233, 235, 238));//[233,235,238]
        } else {

            lb_errorEliminar.setForeground(Color.RED);

            val = false;
        }

        return val;

    }

    private Boolean validarCajasAbiertas() {
        Boolean val = true;

        for (ObjetoPermiso objetoPermiso : cperm.selectPermiso()) {

            if (objetoPermiso.getUsermane().equals(String.valueOf(cb_usuario.getSelectedItem()))) {
                if (objetoPermiso.getIdModulo() == 1 && objetoPermiso.getIdModulo() == 2) {
                    DesktopNotify.showDesktopMessage("Error", "Ya se han generado todos"
                            + "los permisos para este usuario", DesktopNotify.ERROR);
                    val = true;
                    break;
                } else {
                    DesktopNotify.showDesktopMessage("NOTA", "Falta por agregar 1 o mas permisos", DesktopNotify.TIP);

                    val = false;
                }
            }
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_Privilegios = new javax.swing.JTable();
        btn_Ingresar = new principal.MaterialButton();
        btn_Modificar = new principal.MaterialButton();
        btn_Eliminar = new principal.MaterialButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lb_Id = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cb_modulo = new javax.swing.JComboBox<>();
        cb_usuario = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        cb_consultar = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        cb_actualizar = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        cb_agregar = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        cb_eliminar = new javax.swing.JComboBox<>();
        lb_errorUsuario = new javax.swing.JLabel();
        lb_errorEliminar = new javax.swing.JLabel();
        lb_errorModulo = new javax.swing.JLabel();
        lb_errorAgregar = new javax.swing.JLabel();
        lb_errorConsultar = new javax.swing.JLabel();
        lb_errorModificar = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jch_facturacion = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jb_limpiarCampos = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(84, 110, 122));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(84, 110, 122));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("VISTA PARA EL CONTROL DE ACCESOS ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Inicio > Administrador > Control de Accesos");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 252, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(66, 66, 66))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 50));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 1010, 10));

        jt_Privilegios.setModel(new javax.swing.table.DefaultTableModel(
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
        jt_Privilegios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_PrivilegiosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jt_Privilegios);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 960, 180));

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
        jPanel1.add(btn_Ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 360, 140, 40));

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
        jPanel1.add(btn_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 410, 140, 40));

        btn_Eliminar.setBackground(new java.awt.Color(211, 18, 18));
        btn_Eliminar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Eliminar.setText("Eliminar");
        btn_Eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Eliminar.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 460, 140, 40));

        jPanel3.setBackground(new java.awt.Color(233, 235, 238));
        jPanel3.setToolTipText("");
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(36, 47, 65));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Formulario de Cambios");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                .addComponent(lb_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
            .addComponent(lb_Id, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 30));

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Privilegios");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 100, -1));

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Modulo");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 50, -1));

        cb_modulo.setBackground(new java.awt.Color(84, 110, 122));
        cb_modulo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Modulo" }));
        cb_modulo.setBorder(null);
        cb_modulo.setFocusable(false);
        cb_modulo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_moduloItemStateChanged(evt);
            }
        });
        jPanel3.add(cb_modulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 150, -1));

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
        jPanel3.add(cb_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 150, -1));

        jLabel21.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("Usuario");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 50, -1));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 390, 10));

        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("Consultar");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 60, -1));

        cb_consultar.setBackground(new java.awt.Color(84, 110, 122));
        cb_consultar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Privilegio" }));
        cb_consultar.setBorder(null);
        cb_consultar.setFocusable(false);
        cb_consultar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_consultarItemStateChanged(evt);
            }
        });
        jPanel3.add(cb_consultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 170, -1));

        jLabel20.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Modificar");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 60, -1));

        cb_actualizar.setBackground(new java.awt.Color(84, 110, 122));
        cb_actualizar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Privilegio" }));
        cb_actualizar.setBorder(null);
        cb_actualizar.setFocusable(false);
        cb_actualizar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_actualizarItemStateChanged(evt);
            }
        });
        jPanel3.add(cb_actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 170, -1));

        jLabel19.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Agregar");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 60, -1));

        cb_agregar.setBackground(new java.awt.Color(84, 110, 122));
        cb_agregar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Privilegio" }));
        cb_agregar.setBorder(null);
        cb_agregar.setFocusable(false);
        cb_agregar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_agregarItemStateChanged(evt);
            }
        });
        jPanel3.add(cb_agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 170, -1));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Eliminar");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 50, -1));

        cb_eliminar.setBackground(new java.awt.Color(84, 110, 122));
        cb_eliminar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Privilegio" }));
        cb_eliminar.setBorder(null);
        cb_eliminar.setFocusable(false);
        cb_eliminar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_eliminarItemStateChanged(evt);
            }
        });
        jPanel3.add(cb_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 170, -1));

        lb_errorUsuario.setForeground(new java.awt.Color(233, 235, 238));
        lb_errorUsuario.setText("*");
        jPanel3.add(lb_errorUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, 20));

        lb_errorEliminar.setForeground(new java.awt.Color(233, 235, 238));
        lb_errorEliminar.setText("*");
        jPanel3.add(lb_errorEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, -1, 20));

        lb_errorModulo.setForeground(new java.awt.Color(233, 235, 238));
        lb_errorModulo.setText("*");
        jPanel3.add(lb_errorModulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, -1, 20));

        lb_errorAgregar.setForeground(new java.awt.Color(233, 235, 238));
        lb_errorAgregar.setText("*");
        jPanel3.add(lb_errorAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, -1, 20));

        lb_errorConsultar.setForeground(new java.awt.Color(233, 235, 238));
        lb_errorConsultar.setText("*");
        jPanel3.add(lb_errorConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, -1, 20));

        lb_errorModificar.setForeground(new java.awt.Color(233, 235, 238));
        lb_errorModificar.setText("*");
        jPanel3.add(lb_errorModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, -1, 20));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 390, 280));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tabla de Registros");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Recomendaciones");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 330, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Habilitará la posibilidad de seleccionar el usuario ");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 380, -1, 20));

        jch_facturacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jch_facturacionItemStateChanged(evt);
            }
        });
        jPanel1.add(jch_facturacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 510, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Reestablecer Valores");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 315, -1, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("los Privilegios");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 520, -1, 20));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("\"Permitido\" para el usuario que se seleccionó");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 430, -1, 20));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("1. Al hacer click en \"Generar Todos los permisos\" , el sistema");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 360, -1, 20));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("2. Generar , automaticamente crea todos los privilegios en modo");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 410, -1, 20));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Generar Todos");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 500, -1, 20));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("de los privilegios por usuario");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 510, -1, 20));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("3. No se deben generar 2 veces los pasos 1 y 2");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 460, -1, 20));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Se recomienda generar todos los privilegios del usuario primero");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 560, -1, 20));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("4. Se pueden hacer los cambios individualmente asi como la creación");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 490, -1, 20));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("!IMPORTANTE!");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 540, -1, 20));

        jb_limpiarCampos.setBackground(new java.awt.Color(84, 110, 122));
        jb_limpiarCampos.setForeground(new java.awt.Color(84, 110, 122));
        jb_limpiarCampos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/clean24x24.png"))); // NOI18N
        jb_limpiarCampos.setBorder(null);
        jb_limpiarCampos.setBorderPainted(false);
        jb_limpiarCampos.setContentAreaFilled(false);
        jb_limpiarCampos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_limpiarCampos.setFocusPainted(false);
        jb_limpiarCampos.setFocusable(false);
        jb_limpiarCampos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/cleanSeleccionar24x24.png"))); // NOI18N
        jb_limpiarCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_limpiarCamposActionPerformed(evt);
            }
        });
        jPanel1.add(jb_limpiarCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 320, 40, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cb_moduloItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_moduloItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            //this.idPiso = cb_modulo.getSelectedIndex();
        }
        //  System.out.println(cb_piso.getSelectedItem());
    }//GEN-LAST:event_cb_moduloItemStateChanged

    private void cb_consultarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_consultarItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_consultarItemStateChanged

    private void cb_eliminarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_eliminarItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_eliminarItemStateChanged

    private void cb_usuarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_usuarioItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_usuarioItemStateChanged

    private void cb_agregarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_agregarItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_agregarItemStateChanged

    private void cb_actualizarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_actualizarItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_actualizarItemStateChanged

    private void cb_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_usuarioActionPerformed

    private void btn_IngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_IngresarActionPerformed
        try {
            if (btn_Ingresar.getText().equals("Generar")) {
                if (!validarCajasAbiertas() == true) {
                    System.out.println("entro");
                } else {
//codigo de generacion
                    for (ObjetoModulo mod : cmod.selectModulo()) {

                        cperm.insertPermiso(cb_usuario.getSelectedItem().toString(), mod.getNombre(), "Permitido", "Permitido", "Permitido", "Permitido");
                        NewTable = new DefaultTableModel();
                        cTabla();

                    }
                }

            } else if (btn_Ingresar.getText().equals("Agregar") && validarSeleccion() == true) {
                cperm.insertPermiso(String.valueOf(cb_usuario.getSelectedItem()), String.valueOf(cb_modulo.getSelectedItem()), String.valueOf(cb_consultar.getSelectedItem()), String.valueOf(cb_agregar.getSelectedItem()), String.valueOf(cb_actualizar.getSelectedItem()), String.valueOf(cb_eliminar.getSelectedItem()));
                NewTable = new DefaultTableModel();
                cTabla();

            } else if (btn_Ingresar.getText().equals("Agregar") && !validarSeleccion() == true) {
                DesktopNotify.showDesktopMessage("Error", "REVISAR CAMPOS OBLIGATORIOS", DesktopNotify.ERROR);

            }

        } catch (Exception e) {
            //DesktopNotify.showDesktopMessage("Error", "algo paso", DesktopNotify.ERROR);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_IngresarActionPerformed

    private void btn_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModificarActionPerformed
        try {
            if (lb_Id.getText().equals("*")) {

                DesktopNotify.showDesktopMessage("Error", "DEBE SELECCIONAR UN ELEMENTO DE LA TABLA PARA PODER SER ELIMINADO", DesktopNotify.ERROR);

            } else {
                cperm.updatePermiso(String.valueOf(cb_usuario.getSelectedItem()), String.valueOf(cb_modulo.getSelectedItem()), String.valueOf(cb_consultar.getSelectedItem()), String.valueOf(cb_agregar.getSelectedItem()), String.valueOf(cb_actualizar.getSelectedItem()), String.valueOf(cb_eliminar.getSelectedItem()), Integer.parseInt(lb_Id.getText()));
                NewTable = new DefaultTableModel();
                cTabla();
                datosIniciales();
            }
        } catch (Exception e) {
            // DesktopNotify.showDesktopMessage("Error", "algo paso", DesktopNotify.ERROR);
        }


    }//GEN-LAST:event_btn_ModificarActionPerformed

    private void jt_PrivilegiosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_PrivilegiosMouseClicked
        int seleccion = jt_Privilegios.rowAtPoint(evt.getPoint());
        btn_Ingresar.setEnabled(false); //se desactiva este boton para so
        //variable que guarda el id del la fila del los privilegios de algun modulo
        btn_Eliminar.setEnabled(true);
        btn_Modificar.setEnabled(true);
        lb_Id.setText(String.valueOf(jt_Privilegios.getValueAt(seleccion, 0)));
        cb_usuario.getModel().setSelectedItem(jt_Privilegios.getValueAt(seleccion, 1));
        cb_modulo.getModel().setSelectedItem(jt_Privilegios.getValueAt(seleccion, 2));
        cb_consultar.getModel().setSelectedItem(jt_Privilegios.getValueAt(seleccion, 3));
        cb_agregar.getModel().setSelectedItem(jt_Privilegios.getValueAt(seleccion, 4));
        cb_actualizar.getModel().setSelectedItem(jt_Privilegios.getValueAt(seleccion, 5));
        cb_eliminar.getModel().setSelectedItem(jt_Privilegios.getValueAt(seleccion, 6));
    }//GEN-LAST:event_jt_PrivilegiosMouseClicked

    private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarActionPerformed

        try {
            if (lb_Id.getText().equals("*")) {

                DesktopNotify.showDesktopMessage("Error", "DEBE SELECCIONAR UN ELEMENTO DE LA TABLA PARA PODER SER ELIMINADO", DesktopNotify.ERROR);

            } else {
                Pn_Alert_Eliminar ale = new Pn_Alert_Eliminar(Principal, true);
                ale.lb_titulo.setText("¿Esta seguro de eliminar este elemento?");
                ale.jb_aceptar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        cperm.deletePermiso(Integer.parseInt(lb_Id.getText()));
                        NewTable = new DefaultTableModel();
                        cTabla();
                        datosIniciales();

                    }
                });
                ale.setVisible(true);
            }

        } catch (Exception e) {

        }


    }//GEN-LAST:event_btn_EliminarActionPerformed

    private void jch_facturacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jch_facturacionItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
//            validador = 1;
            btn_Ingresar.setText("Generar");
            bloquearComponentes();
        } else {//checkbox has been deselected
            btn_Ingresar.setText("Agregar");
            //        validador = 0;
            desbloquearComponentes();
        }       // TODO add your handling code here:
    }//GEN-LAST:event_jch_facturacionItemStateChanged

    private void jb_limpiarCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_limpiarCamposActionPerformed
        datosIniciales();        // TODO add your handling code here:
    }//GEN-LAST:event_jb_limpiarCamposActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private principal.MaterialButton btn_Eliminar;
    private principal.MaterialButton btn_Ingresar;
    private principal.MaterialButton btn_Modificar;
    private javax.swing.JComboBox<String> cb_actualizar;
    private javax.swing.JComboBox<String> cb_agregar;
    private javax.swing.JComboBox<String> cb_consultar;
    private javax.swing.JComboBox<String> cb_eliminar;
    private javax.swing.JComboBox<String> cb_modulo;
    private javax.swing.JComboBox<String> cb_usuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton jb_limpiarCampos;
    private javax.swing.JCheckBox jch_facturacion;
    private javax.swing.JTable jt_Privilegios;
    private javax.swing.JLabel lb_Id;
    private javax.swing.JLabel lb_errorAgregar;
    private javax.swing.JLabel lb_errorConsultar;
    private javax.swing.JLabel lb_errorEliminar;
    private javax.swing.JLabel lb_errorModificar;
    private javax.swing.JLabel lb_errorModulo;
    private javax.swing.JLabel lb_errorUsuario;
    // End of variables declaration//GEN-END:variables
}
