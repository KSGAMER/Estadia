/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controladores.ValidadorDePrivilegios.*;
import controladores.ControladorCategorias;
import controladores.ControladorEscritura;
import controladores.ControladorEstatusPermisos;
import controladores.ControladorFormularioTab;
import controladores.ControladorModulos;
import controladores.ControladorPermisos;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultCaret;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author fenix
 */
public class Pn_NuevaCategoria extends javax.swing.JPanel {

//NECESARIO PARA FUNCIONES DE ESTE MODULO 
    ControladorCategorias ccat = new ControladorCategorias();
    ControladorEscritura ce = new ControladorEscritura();
    DefaultTableModel NewTable = new DefaultTableModel();
    private ControladorFormularioTab cft = new ControladorFormularioTab();
    ////FIN

    //NECESARIO PARA EXTRAER LOS PRIVILEGIOS DENTRO DE ESTE MODULO, EN FUNCION AL USUARIO ACTUAL 
    ControladorPrivilegiosCategorias analisis = new ControladorPrivilegiosCategorias();
    //FIN
//NECESARIO PARA HACER LA COMPRACION Y EXTRACCION DE LOS PRIVILEGIOS DE ESTE MODULO
    private String NombreModulo = "Categorias";
    //FIN

    //NECESARIO PARA EL USO DE LA NOTIFICACION DINAMICA DE BOTON ELIMINAR ()
    Frame Principal;
//FIN

    /**
     * Creates new form Pn_NuevaCategoria
     */
    public Pn_NuevaCategoria() {
        initComponents();
        //EXTRAE LOS PRIVILEGIOS DE ESTE MODULO
        analisis.validarPermisos(NombreModulo);
        //FIN
        //INICIA LOS VALORES DEL FORMULARIO A SU VALOR ORIGINAL
        datosIniciales();
        //FIN
        //APARIENCIA DE LA TABLA
        RowHeaderApariencia();
        RowApariencia();
        //FIN
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

    public void tamañoTabla() {
        TableColumnModel columnModel = jt_categorias.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(5);
        columnModel.getColumn(1).setPreferredWidth(50);
        columnModel.getColumn(2).setPreferredWidth(250);

    }

    public void cTabla() {
        this.jt_categorias.setModel(ccat.tablaCategorias());
        jt_t_registros.setText(String.valueOf(ccat.selectCategoria().size()));

    }

    public void RowApariencia() {

        jt_categorias.setFocusable(false);

        //espacio entre comulnas
        jt_categorias.setIntercellSpacing(new Dimension(0, 1));
        //altura de columnas 
        jt_categorias.setRowHeight(25);
        //margen entre filas
        jt_categorias.setRowMargin(0);
//sin lineas verticles
        jt_categorias.setShowVerticalLines(false);
        jt_categorias.setSelectionBackground(new Color(97, 212, 195));

    }

    public void RowHeaderApariencia() {
        jt_categorias.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 14));
        jt_categorias.getTableHeader().setOpaque(false);
        jt_categorias.getTableHeader().setBackground(Color.BLACK);
        jt_categorias.getTableHeader().setForeground(new Color(255, 255, 255));

    }

    public void datosIniciales() {
        lb_Id.setText("*");
        lb_errorNombre.setText("*");
        lb_errorNombre.setForeground(new Color(84, 110, 122));
        jt_nombre.setText("Ingresar Nombre");
        jta_observaciones.setText("Ingresar Observaciones");
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
        jt_categorias = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jt_nombre = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jta_observaciones = new javax.swing.JTextArea();
        jSeparator4 = new javax.swing.JSeparator();
        btn_Ingresar = new principal.MaterialButton();
        btn_Modificar = new principal.MaterialButton();
        btn_Eliminar = new principal.MaterialButton();
        lb_errorNombre = new javax.swing.JLabel();
        lb_Id = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jt_t_registros = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jt_Buscar = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        chk_mostrar = new javax.swing.JCheckBox();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(84, 110, 122));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jt_categorias.setModel(new javax.swing.table.DefaultTableModel(
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
        jt_categorias.getTableHeader().setResizingAllowed(false);
        jt_categorias.getTableHeader().setReorderingAllowed(false);
        jt_categorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_categoriasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jt_categorias);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 140, 560, 360));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Nombre");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 210, -1));

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
        jPanel1.add(jt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 210, -1));

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Observaciones");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 130, -1));

        jta_observaciones.setColumns(20);
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
        jPanel1.add(jta_observaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 390, 140));

        jSeparator4.setBackground(new java.awt.Color(128, 128, 131));
        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 210, 10));

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
        jPanel1.add(btn_Ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 140, 40));

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
        jPanel1.add(btn_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 390, 140, 40));

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
        jPanel1.add(btn_Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 390, 140, 40));

        lb_errorNombre.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorNombre.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorNombre.setText("*");
        jPanel1.add(lb_errorNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 10, -1));

        lb_Id.setForeground(new java.awt.Color(84, 110, 122));
        jPanel1.add(lb_Id, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 30, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Total de registros ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 510, 100, 20));

        jt_t_registros.setEditable(false);
        jt_t_registros.setBackground(new java.awt.Color(84, 110, 122));
        jt_t_registros.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jt_t_registros.setForeground(new java.awt.Color(255, 255, 255));
        jt_t_registros.setBorder(null);
        jt_t_registros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jt_t_registrosActionPerformed(evt);
            }
        });
        jPanel1.add(jt_t_registros, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 510, 30, 20));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("Buscar Categoria:");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 110, -1, -1));

        jt_Buscar.setBackground(new java.awt.Color(84, 110, 122));
        jt_Buscar.setForeground(new java.awt.Color(204, 204, 204));
        jt_Buscar.setText("Buscar Nombre");
        jt_Buscar.setBorder(null);
        jt_Buscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jt_BuscarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_BuscarFocusLost(evt);
            }
        });
        jt_Buscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_BuscarMouseClicked(evt);
            }
        });
        jt_Buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jt_BuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_BuscarKeyTyped(evt);
            }
        });
        jPanel1.add(jt_Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 110, 210, 20));

        jPanel2.setBackground(new java.awt.Color(84, 110, 122));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("VISTA GENERAL DE CATEGORIAS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(680, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 50));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Formulario de Cambios");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Detalle de las Categorias");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1090, 10));

        chk_mostrar.setBackground(new java.awt.Color(84, 110, 122));
        chk_mostrar.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        chk_mostrar.setForeground(new java.awt.Color(255, 255, 255));
        chk_mostrar.setText("Mostrar");
        chk_mostrar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chk_mostrarItemStateChanged(evt);
            }
        });
        jPanel1.add(chk_mostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 110, -1, 20));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 130, 210, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/limpiarCampos 24x24.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jt_nombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_nombreFocusLost
        cft.formFocusLostJTextField(jt_nombre, "Ingresar Nombre");
    }//GEN-LAST:event_jt_nombreFocusLost

    private void jt_nombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_nombreMouseClicked
        cft.formFocusGain(jt_nombre);
    }//GEN-LAST:event_jt_nombreMouseClicked

    private void jt_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_nombreKeyTyped
        ce.typedCharsAndSpaceAndDigits(evt, jt_nombre);
    }//GEN-LAST:event_jt_nombreKeyTyped

    private void jta_observacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jta_observacionesMouseClicked
        cft.formFocusGainJTextArea(jta_observaciones);
    }//GEN-LAST:event_jta_observacionesMouseClicked

    private void jta_observacionesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jta_observacionesFocusLost
        cft.formFocusLostJTextArea(jta_observaciones, "Ingresar Observaciones");
    }//GEN-LAST:event_jta_observacionesFocusLost

    private void jt_categoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_categoriasMouseClicked
        int seleccion = jt_categorias.rowAtPoint(evt.getPoint());
        lb_Id.setText(String.valueOf(jt_categorias.getValueAt(seleccion, 0)));
        jt_nombre.setText(String.valueOf(jt_categorias.getValueAt(seleccion, 1)));
        jta_observaciones.setText(String.valueOf(jt_categorias.getValueAt(seleccion, 2)));

        // TODO add your handling code here:
    }//GEN-LAST:event_jt_categoriasMouseClicked

    private void btn_IngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_IngresarActionPerformed
        try {
            if (!validarEscritura() == true) {

                DesktopNotify.showDesktopMessage("Error", "REVISAR CAMPOS OBLIGATORIOS", DesktopNotify.ERROR);

            } else {

                ccat.insertCategoria(jt_nombre.getText(), jta_observaciones.getText());
                //DesktopNotify.showDesktopMessage("Exito", "Datos de la categoria " + jt_nombre.getText() + " agregados con éxito.", DesktopNotify.SUCCESS);
                NewTable = new DefaultTableModel();
                cTabla();
                tamañoTabla();
                datosIniciales();
            }

        } catch (Exception e) {

            DesktopNotify.showDesktopMessage("Error", "Ocurrió un error al intentar agregar los datos de la nueva categoria,"
                    + "por favor intente de nuevo o revise su conexión", DesktopNotify.ERROR);

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_IngresarActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        datosIniciales();
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void btn_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModificarActionPerformed
        try {
            if (!validarEscritura() == true) {

                DesktopNotify.showDesktopMessage("Error", "REVISAR CAMPOS OBLIGATORIOS", DesktopNotify.ERROR);

            } else {

                ccat.updateCategoria(jt_nombre.getText(), jta_observaciones.getText(), Integer.valueOf(lb_Id.getText()));
                //DesktopNotify.showDesktopMessage("Exito", "Datos de la categoria " + jt_nombre.getText() + " actualizados con éxito.", DesktopNotify.SUCCESS);
                NewTable = new DefaultTableModel();
                cTabla();
                datosIniciales();
                tamañoTabla();
            }

        } catch (Exception e) {

            DesktopNotify.showDesktopMessage("Error", "Ocurrió un error al intentar actualizar los datos de la categoria " + jt_nombre.getText()
                    + "por favor intente de nuevo o revise su conexión", DesktopNotify.ERROR);

        }


    }//GEN-LAST:event_btn_ModificarActionPerformed

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
                        ccat.deleteCategoria(Integer.valueOf(lb_Id.getText()));
                        //DesktopNotify.showDesktopMessage("Exito", "Datos de la categoria " + jt_nombre.getText() + " eliminados con éxito.", DesktopNotify.SUCCESS);
                        tamañoTabla();
                        NewTable = new DefaultTableModel();
                        cTabla();
                        datosIniciales();

                    }
                });
                ale.setVisible(true);
            }

        } catch (Exception e) {

            DesktopNotify.showDesktopMessage("Error", "Ocurrió un error al intentar eliminar los datos de la categoria " + jt_nombre.getText()
                    + "por favor intente de nuevo o revise su conexión", DesktopNotify.ERROR);

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_EliminarActionPerformed

    private void jt_t_registrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_t_registrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_t_registrosActionPerformed

    private void jt_BuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_BuscarFocusLost
        cft.formFocusLostJTextField(jt_Buscar, "Buscar Nombre");
    }//GEN-LAST:event_jt_BuscarFocusLost

    private void jt_BuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_BuscarMouseClicked
        cft.formFocusGain(jt_Buscar);
    }//GEN-LAST:event_jt_BuscarMouseClicked

    private void jt_BuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_BuscarKeyTyped

        char tecla;
        tecla = evt.getKeyChar();
        //Convertir a letras mayusculas
        if (Character.isLetter(tecla)) {
            evt.setKeyChar(Character.toUpperCase(tecla));

        }

        // TODO add your handling code here:
        //filtro(jt_Buscar.getText(), jt_categorias);

    }//GEN-LAST:event_jt_BuscarKeyTyped

    private void chk_mostrarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chk_mostrarItemStateChanged
        if (jt_Buscar.getText().equals("Buscar Nombre") || jt_Buscar.getText().equals("")) {
            jt_Buscar.setText("");
        } else {
            String buscar = jt_Buscar.getText();
            jt_Buscar.setText(buscar);
        }
        this.jt_categorias.setModel(ccat.tablaCategorias(jt_Buscar, chk_mostrar.isSelected()));
        tamañoTabla();
    }//GEN-LAST:event_chk_mostrarItemStateChanged

    private void jt_BuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_BuscarKeyReleased
        this.jt_categorias.setModel(ccat.tablaCategorias(jt_Buscar, chk_mostrar.isSelected()));
        tamañoTabla();
    }//GEN-LAST:event_jt_BuscarKeyReleased

    private void jta_observacionesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jta_observacionesKeyPressed
        cft.formTab(evt, jt_Buscar);
    }//GEN-LAST:event_jta_observacionesKeyPressed

    private void jt_nombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_nombreFocusGained
        cft.formFocusGain(jt_nombre);
    }//GEN-LAST:event_jt_nombreFocusGained

    private void jta_observacionesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jta_observacionesFocusGained
        cft.formFocusGainJTextArea(jta_observaciones);
    }//GEN-LAST:event_jta_observacionesFocusGained

    private void jt_BuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_BuscarFocusGained
        cft.formFocusGain(jt_Buscar);
    }//GEN-LAST:event_jt_BuscarFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static principal.MaterialButton btn_Eliminar;
    public static principal.MaterialButton btn_Ingresar;
    public static principal.MaterialButton btn_Modificar;
    private javax.swing.JCheckBox chk_mostrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    public static javax.swing.JTextField jt_Buscar;
    private javax.swing.JTable jt_categorias;
    private javax.swing.JTextField jt_nombre;
    private javax.swing.JTextField jt_t_registros;
    private javax.swing.JTextArea jta_observaciones;
    private javax.swing.JLabel lb_Id;
    private javax.swing.JLabel lb_errorNombre;
    // End of variables declaration//GEN-END:variables
}
