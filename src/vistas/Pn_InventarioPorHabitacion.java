/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import vistas.Alertas.Pn_Alert_Eliminar;
import vistas.Alertas.Pn_SeleccionarProducto;
import controladores.ControladorEscritura;
import controladores.ControladorFormularioTab;
import controladores.ControladorHabitaciones;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.table.TableColumnModel;
import controladores.ControladorInventarioHabitacion;
import controladores.ControladorProductos;
import ds.desktop.notify.DesktopNotify;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import objetos.ObjetoHabitacion;
import objetos.ObjetoProducto;


/*
    NOTA: SE DEBE CONFIGURAR GMAIL PARA PERMITIR EL ACCESO A APLICACIONES MENOS SEGURAS
    PASOS:
   (1)ICONO DE GMAIL
   (2)CLICK EN "MI CUENTA"
   (3)CLICK EN "INICIO DE SESIÓN Y SEGURIDAD"
   (4)ACTIVAR "PERMITIR EL ACCESO DE APLICACIONES MENOS SEGURAS"
 */
public class Pn_InventarioPorHabitacion extends javax.swing.JPanel {

    private ControladorProductos cpro = new ControladorProductos();
    private ControladorFormularioTab cft = new ControladorFormularioTab();
    private ControladorEscritura ce = new ControladorEscritura();
    private ControladorInventarioHabitacion cinhab = new ControladorInventarioHabitacion();
    private ControladorHabitaciones ch = new ControladorHabitaciones();
    DefaultTableModel NewTable;
    //NECESARIO PARA EL USO DE LA NOTIFICACION DINAMICA DE BOTON ELIMINAR ()
    Frame principal;

    /**
     * Creates new form Pn_NuevaCategoria
     */
    public Pn_InventarioPorHabitacion() {
        initComponents();
        ch.tablaHabitaciones();
        //APARIENCIA DE LA TABLA
        RowHeaderApariencia();
        RowApariencia();
        //FIN 
        //INICIA LOS VALORES DEL FORMULARIO A SU VALOR ORIGINAL
        datosIniciales();
        //FIN
        //CARGA LOS VALORES EN LA TABLA
      
        cTabla();
        //FIN
        //CARGA EL CB CON LAS HABITACIONES
        cargarHabitaciones();
        //FIN
        //ASIGNA TAMAÑOS DE ANCHURA A LAS COLUMNAS
        tamañoTabla();
        //FIN
       
    }

    private void datosIniciales() {
        lb_Id.setText("*");
        lb_nombreProducto.setText("Producto No seleccionado");
        lb_cantidadSeleccionada.setText("Producto No seleccionado");
        cb_Habitacion.setSelectedIndex(0);
        lb_errorNombreProducto.setText("*");
        lb_errorHabitacion.setText("*");
        lb_errorCantidadSeleccionada.setText("*");
        //bloqueo inicial de botones no necesarios hasta la seleccion de un dato 
        btn_Modificar.setEnabled(false);
        btn_Eliminar.setEnabled(false);
        btn_Ingresar.setEnabled(true);
        //
        
        lb_errorNombreProducto.setForeground(new Color(84, 110, 122));
        lb_errorCantidadSeleccionada.setForeground(new Color(84, 110, 122));
        lb_errorHabitacion.setForeground(new Color(84, 110, 122));
        
    }

    private void tamañoTabla() {
        TableColumnModel columnModel = jtabla_Productos.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(5);
        columnModel.getColumn(1).setPreferredWidth(80);
        columnModel.getColumn(2).setPreferredWidth(70);
        columnModel.getColumn(2).setPreferredWidth(70);
        columnModel.getColumn(2).setPreferredWidth(60);

    }

    private void cTabla() {
        this.jtabla_Productos.setModel(cinhab.tablaInventarioHabitacion(""));
        jt_t_registros.setText(String.valueOf(this.jtabla_Productos.getRowCount()));

    }

    private void cargarHabitaciones() {
        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        cb.addElement("Seleccionar Habitación");

        for (ObjetoHabitacion campos : ch.selectHabitacion()) {
            
                cb.addElement(campos.getNombre());

            
        }
        cb_Habitacion.setModel(cb);
    }
    private void RowApariencia() {

        jtabla_Productos.setFocusable(false);

        //espacio entre comulnas
        jtabla_Productos.setIntercellSpacing(new Dimension(0, 1));
        //altura de columnas 
        jtabla_Productos.setRowHeight(25);
        //margen entre filas
        jtabla_Productos.setRowMargin(0);
//sin lineas verticles
        jtabla_Productos.setShowVerticalLines(false);
        jtabla_Productos.setSelectionBackground(new Color(97, 212, 195));

    }

    private void RowHeaderApariencia() {
        jtabla_Productos.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 14));
        jtabla_Productos.getTableHeader().setOpaque(false);
        jtabla_Productos.getTableHeader().setBackground(Color.BLACK);
        jtabla_Productos.getTableHeader().setForeground(new Color(255, 255, 255));

    }
   private Boolean validarEscritura() {
        Boolean val = true;
        //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(lb_nombreProducto.getText().equals("Producto No seleccionado"))) {
            lb_errorNombreProducto.setForeground(new Color(84,110,122));
        } else {
            lb_errorNombreProducto.setForeground(Color.RED);
            val = false;
        }
          //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(lb_cantidadSeleccionada.getText().equals("Producto No Seleccionado"))) {
            lb_errorCantidadSeleccionada.setForeground(new Color(84,110,122));
        } else {
            lb_errorCantidadSeleccionada.setForeground(Color.RED);
            val = false;
        }
      
      
        return val;
    }
     private Boolean validarSeleccion() {
        Boolean val = true;
        if (!(cb_Habitacion.getSelectedIndex()==0)) {

            lb_errorHabitacion.setForeground(new Color(84, 110, 122));
        } else {

            lb_errorHabitacion.setForeground(Color.RED);

            val = false;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jtabla_Productos = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        btn_Ingresar = new principal.MaterialButton();
        btn_Modificar = new principal.MaterialButton();
        btn_Eliminar = new principal.MaterialButton();
        lb_Id = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jt_t_registros = new javax.swing.JTextField();
        jt_Buscar = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jb_limpiarCampos = new javax.swing.JButton();
        lb_errorNombreProducto = new javax.swing.JLabel();
        lb_errorHabitacion = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lb_errorCantidadSeleccionada = new javax.swing.JLabel();
        btn_clientes = new principal.MaterialButton();
        lb_nombreProducto = new javax.swing.JLabel();
        lb_cantidadSeleccionada = new javax.swing.JLabel();
        cb_Habitacion = new javax.swing.JComboBox<>();

        jPanel1.setBackground(new java.awt.Color(84, 110, 122));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtabla_Productos.setModel(new javax.swing.table.DefaultTableModel(
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
        jtabla_Productos.getTableHeader().setResizingAllowed(false);
        jtabla_Productos.getTableHeader().setReorderingAllowed(false);
        jtabla_Productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtabla_ProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtabla_Productos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 150, 560, 350));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Nombre del producto ");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 210, -1));

        jSeparator4.setBackground(new java.awt.Color(128, 128, 131));
        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 210, 10));

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
        jPanel1.add(btn_Ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 140, 40));

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
        jPanel1.add(btn_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 360, 140, 40));

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
        jPanel1.add(btn_Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 360, 140, 40));

        lb_Id.setForeground(new java.awt.Color(84, 110, 122));
        jPanel1.add(lb_Id, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 30, 20));

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

        jt_Buscar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jt_Buscar.setForeground(new java.awt.Color(102, 102, 102));
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
        jPanel1.add(jt_Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 120, 150, 20));

        jPanel2.setBackground(new java.awt.Color(84, 110, 122));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("VISTA GENERAL DEL INVENTARIO POR HABITACIONES");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Inicio > Inventario > Inventario por Habitación");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(94, 94, 94))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 50));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Formulario de Cambios");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1090, 10));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/campo-buscar.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 110, -1, 40));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Detalle de los productos");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, -1, -1));

        jSeparator5.setBackground(new java.awt.Color(128, 128, 131));
        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 210, 10));

        jb_limpiarCampos.setBackground(new java.awt.Color(84, 110, 122));
        jb_limpiarCampos.setForeground(new java.awt.Color(84, 110, 122));
        jb_limpiarCampos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/clean24x24.png"))); // NOI18N
        jb_limpiarCampos.setBorder(null);
        jb_limpiarCampos.setBorderPainted(false);
        jb_limpiarCampos.setContentAreaFilled(false);
        jb_limpiarCampos.setFocusPainted(false);
        jb_limpiarCampos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/cleanSeleccionar24x24.png"))); // NOI18N
        jb_limpiarCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_limpiarCamposActionPerformed(evt);
            }
        });
        jPanel1.add(jb_limpiarCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 40, -1));

        lb_errorNombreProducto.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorNombreProducto.setText("*");
        jPanel1.add(lb_errorNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        lb_errorHabitacion.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorHabitacion.setText("*");
        jPanel1.add(lb_errorHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("Habitación");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 210, -1));

        jLabel19.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Cantidad Seleccionada");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 210, -1));

        lb_errorCantidadSeleccionada.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorCantidadSeleccionada.setText("*");
        jPanel1.add(lb_errorCantidadSeleccionada, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        btn_clientes.setBackground(new java.awt.Color(233, 235, 238));
        btn_clientes.setBorder(null);
        btn_clientes.setText("...");
        btn_clientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_clientes.setFocusable(false);
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
        jPanel1.add(btn_clientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, 30, 30));

        lb_nombreProducto.setForeground(new java.awt.Color(204, 204, 204));
        lb_nombreProducto.setText("Producto No seleccionado");
        jPanel1.add(lb_nombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 210, 20));

        lb_cantidadSeleccionada.setForeground(new java.awt.Color(204, 204, 204));
        lb_cantidadSeleccionada.setText("Producto No Seleccionado");
        jPanel1.add(lb_cantidadSeleccionada, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 210, 20));

        cb_Habitacion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cb_Habitacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Habitación" }));
        cb_Habitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_HabitacionActionPerformed(evt);
            }
        });
        jPanel1.add(cb_Habitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 210, -1));

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

    private void jtabla_ProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtabla_ProductosMouseClicked
        int seleccion = jtabla_Productos.rowAtPoint(evt.getPoint());
        //activacion de botones al elegir un dato
        btn_Modificar.setEnabled(true);
        btn_Eliminar.setEnabled(true);
         btn_Ingresar.setEnabled(false);
        //
        lb_Id.setText(String.valueOf(jtabla_Productos.getValueAt(seleccion, 0)));
        lb_nombreProducto.setText(String.valueOf(jtabla_Productos.getValueAt(seleccion, 1)));
        lb_cantidadSeleccionada.setText(String.valueOf(jtabla_Productos.getValueAt(seleccion, 2)));

        // TODO add your handling code here:
    }//GEN-LAST:event_jtabla_ProductosMouseClicked

    private void btn_IngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_IngresarActionPerformed
     try {
            if (!validarEscritura() == true || !validarSeleccion()==true) {
                DesktopNotify.showDesktopMessage("Error", "REVISAR CAMPOS OBLIGATORIOS", DesktopNotify.ERROR);
            } else {
                
                
                    //iteracion necesaria para actualizar el nuevo stock , restando los agregados 
                for (ObjetoProducto objetoProducto : cpro.seleccionarProducto()) {
                    if (objetoProducto.getNombre().equals(lb_nombreProducto.getText())) {
                        int totalStock=objetoProducto.getCantidad();
                        int nuevoStock=objetoProducto.getCantidad()-(Integer.parseInt(lb_cantidadSeleccionada.getText()));
                        
                        
                               
                        break;
                    }
                }
                
                
              NewTable = new DefaultTableModel();
                cTabla();
                tamañoTabla();
                datosIniciales();
            }
        } catch (Exception e) {
       }
    }//GEN-LAST:event_btn_IngresarActionPerformed

    private void btn_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModificarActionPerformed
   try {
            if (!validarEscritura() == true) {

           DesktopNotify.showDesktopMessage("Error", "REVISAR CAMPOS OBLIGATORIOS", DesktopNotify.ERROR);

       } else {
           NewTable = new DefaultTableModel();
           cTabla();
           tamañoTabla();
           datosIniciales();
       }

        } catch (Exception e) {

       
        }
    }//GEN-LAST:event_btn_ModificarActionPerformed

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
    }//GEN-LAST:event_btn_EliminarActionPerformed

    private void jt_t_registrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_t_registrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_t_registrosActionPerformed

    private void jt_BuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_BuscarFocusGained
        cft.formFocusGain(jt_Buscar);
    }//GEN-LAST:event_jt_BuscarFocusGained

    private void jt_BuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_BuscarFocusLost
        cft.formFocusLostJTextField(jt_Buscar, "Buscar Nombre");
    }//GEN-LAST:event_jt_BuscarFocusLost

    private void jt_BuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_BuscarMouseClicked
        cft.formFocusGain(jt_Buscar);
    }//GEN-LAST:event_jt_BuscarMouseClicked

    private void jt_BuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_BuscarKeyReleased
  this.jtabla_Productos.setModel(cinhab.tablaInventarioHabitacion(jt_Buscar.getText()));//        this.jt_categorias.setModel(ccat.tablaCategorias(jt_Buscar, chk_mostrar.isSelected()));
    tamañoTabla();
    }//GEN-LAST:event_jt_BuscarKeyReleased

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

    private void jb_limpiarCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_limpiarCamposActionPerformed
        datosIniciales();        // TODO add your handling code here:
    }//GEN-LAST:event_jb_limpiarCamposActionPerformed

    private void btn_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_clientesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_clientesMouseClicked

    private void btn_clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clientesActionPerformed
       Pn_SeleccionarProducto select = new Pn_SeleccionarProducto(principal, true);
       select.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_clientesActionPerformed

    private void cb_HabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_HabitacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_HabitacionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static principal.MaterialButton btn_Eliminar;
    public static principal.MaterialButton btn_Ingresar;
    public static principal.MaterialButton btn_Modificar;
    private principal.MaterialButton btn_clientes;
    private javax.swing.JComboBox<String> cb_Habitacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JButton jb_limpiarCampos;
    public static javax.swing.JTextField jt_Buscar;
    private javax.swing.JTextField jt_t_registros;
    private javax.swing.JTable jtabla_Productos;
    private javax.swing.JLabel lb_Id;
    public static javax.swing.JLabel lb_cantidadSeleccionada;
    private javax.swing.JLabel lb_errorCantidadSeleccionada;
    private javax.swing.JLabel lb_errorHabitacion;
    private javax.swing.JLabel lb_errorNombreProducto;
    public static javax.swing.JLabel lb_nombreProducto;
    // End of variables declaration//GEN-END:variables
}
