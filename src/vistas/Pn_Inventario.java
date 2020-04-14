/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import vistas.Alertas.Pn_Alert_Eliminar;
import controladores.ControladorEscritura;
import controladores.ControladorFormularioTab;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.table.TableColumnModel;
import controladores.ControladorProductos;
import ds.desktop.notify.DesktopNotify;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import javax.swing.table.DefaultTableModel;


/*
    NOTA: SE DEBE CONFIGURAR GMAIL PARA PERMITIR EL ACCESO A APLICACIONES MENOS SEGURAS
    PASOS:
   (1)ICONO DE GMAIL
   (2)CLICK EN "MI CUENTA"
   (3)CLICK EN "INICIO DE SESIÓN Y SEGURIDAD"
   (4)ACTIVAR "PERMITIR EL ACCESO DE APLICACIONES MENOS SEGURAS"
 */
public class Pn_Inventario extends javax.swing.JPanel {

    private ControladorFormularioTab cft = new ControladorFormularioTab();
    private ControladorEscritura ce = new ControladorEscritura();
    private ControladorProductos cpro = new ControladorProductos();
    DefaultTableModel NewTable;
    //NECESARIO PARA EL USO DE LA NOTIFICACION DINAMICA DE BOTON ELIMINAR ()
    Frame Principal;
//VARIABLE NECESARIA PARA VALIDAR SI SE ACTUALIZA INGRESANDO MAS PRODUCTO (METODO DE SUMA AUTOMATICA) O SE CAMBIA MANUALMENTE 
    //LA CANTIDAD   opciones: 1== el usuario ingresa mas producto y la suma es automatica, opcion 0 == el usuario cambia
    //la cantidad del producto manualmente
    private int ValidadorNuevoIngreso=0;
    /**
     * Creates new form Pn_NuevaCategoria
     */
    public Pn_Inventario() {
        initComponents();
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
        //ASIGNA TAMAÑOS DE ANCHURA A LAS COLUMNAS
        tamañoTabla();
        //FIN
        //para ajustar el tecto al tamaño del jtextarea
        jta_observaciones.setLineWrap(true);
        //FIN
    }

    private void datosIniciales() {
        lb_Id.setText("*");
        lb_errorNombreProducto.setText("*");
        lb_errorStock.setText("*");
        lb_errorObservaciones.setText("*");
        lb_errorProveedor.setText("*");
        lb_errorPrecio.setText("*");
        jt_nombre.setText("Ingresar Nombre");
        jt_Precio.setText("Ingresar Precio");
        jt_CantidadStock.setText("Ingresar Cantidad");
        jta_observaciones.setText("Ingresar Observaciones");
        //bloqueo inicial de botones no necesarios hasta la seleccion de un dato 
        btn_Modificar.setEnabled(false);
        btn_Eliminar.setEnabled(false);
        btn_Ingresar.setEnabled(true);
        //
ocultarFormNuevosIngresos();
        lb_errorNombreProducto.setForeground(new Color(84, 110, 122));
        lb_errorStock.setForeground(new Color(84, 110, 122));
        lb_errorPrecio.setForeground(new Color(84, 110, 122));
        lb_errorObservaciones.setForeground(new Color(84, 110, 122));
        lb_errorProveedor.setForeground(new Color(84, 110, 122));
    }
private void mostrarFormNuevosIngresos(){
    lb_titulo1.setVisible(true);
    lb_titulo2.setVisible(true);
    lb_titulo3.setVisible(true);
    jc_nuevoIngreso.setVisible(true);
    jt_NuevoIngreso.setVisible(true);
    jt_NuevoIngreso.setEnabled(false);
    jseparador_nuevoIngreso.setVisible(true);
}
    private void ocultarFormNuevosIngresos() {
        lb_titulo1.setVisible(false);
        lb_titulo2.setVisible(false);
        lb_titulo3.setVisible(false);
        jc_nuevoIngreso.setVisible(false);
        jc_nuevoIngreso.setSelected(false);
        jt_NuevoIngreso.setVisible(false);
        jt_NuevoIngreso.setEnabled(false);
        jseparador_nuevoIngreso.setVisible(false);

    }

    private void tamañoTabla() {
        TableColumnModel columnModel = jtabla_Productos.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(5);
        columnModel.getColumn(1).setPreferredWidth(80);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(60);
        columnModel.getColumn(4).setPreferredWidth(70);
        columnModel.getColumn(5).setPreferredWidth(200);
    }

    private void cTabla() {
        this.jtabla_Productos.setModel(cpro.tablaProducto(""));
        jt_t_registros.setText(String.valueOf(this.jtabla_Productos.getRowCount()));

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
        jtabla_Productos.getTableHeader().setBackground(new Color(32, 136, 203));
        jtabla_Productos.getTableHeader().setForeground(new Color(255, 255, 255));

    }

    private Boolean validarEscritura() {
        Boolean val = true;
        //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(jt_nombre.getText().equals("Ingresar Nombre")) && !(jt_nombre.getText().equals(""))) {
            lb_errorNombreProducto.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorNombreProducto.setForeground(Color.RED);
            val = false;
        }
        //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(jt_nombreProveedor.getText().equals("Ingresar Nombre")) && !(jt_nombreProveedor.getText().equals(""))) {
            lb_errorProveedor.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorProveedor.setForeground(Color.RED);
            val = false;
        }
        //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(jt_Precio.getText().equals("Ingresar Precio")) && !(jt_Precio.getText().equals(""))) {
            lb_errorPrecio.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorPrecio.setForeground(Color.RED);
            val = false;
        }
        //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(jt_CantidadStock.getText().equals("Ingresar Cantidad")) && !(jt_CantidadStock.getText().equals(""))) {
            lb_errorStock.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorStock.setForeground(Color.RED);
            val = false;
        }
        //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(jta_observaciones.getText().equals("Ingresar Observaciones")) && !(jt_nombre.getText().equals(""))) {
            lb_errorObservaciones.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorObservaciones.setForeground(Color.RED);
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
        jt_nombre = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jta_observaciones = new javax.swing.JTextArea();
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
        jLabel17 = new javax.swing.JLabel();
        jt_Precio = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jb_limpiarCampos = new javax.swing.JButton();
        lb_errorObservaciones = new javax.swing.JLabel();
        lb_errorNombreProducto = new javax.swing.JLabel();
        jt_NuevoIngreso = new javax.swing.JTextField();
        lb_errorStock = new javax.swing.JLabel();
        jt_CantidadStock = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        lb_errorPrecio = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jt_nombreProveedor = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        lb_errorProveedor = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jseparador_nuevoIngreso = new javax.swing.JSeparator();
        lb_titulo2 = new javax.swing.JLabel();
        lb_titulo1 = new javax.swing.JLabel();
        lb_titulo3 = new javax.swing.JLabel();
        jc_nuevoIngreso = new javax.swing.JCheckBox();
        jSeparator9 = new javax.swing.JSeparator();

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 560, 350));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Nombre del producto ");
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
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 130, -1));

        jta_observaciones.setColumns(20);
        jta_observaciones.setForeground(new java.awt.Color(153, 153, 153));
        jta_observaciones.setRows(5);
        jta_observaciones.setText("Ingresar Observaciones");
        jta_observaciones.setAlignmentX(1.0F);
        jta_observaciones.setAlignmentY(1.0F);
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
        jPanel1.add(jta_observaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 390, 110));

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
        jPanel1.add(btn_Ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 140, 40));

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
        jPanel1.add(btn_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 460, 140, 40));

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
        jPanel1.add(btn_Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 460, 140, 40));

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
        jLabel1.setText("VISIÓN GENERAL DE REGISTRO DE PRODUCTOS");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Inicio > Inventario > Registro de Productos");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(92, 92, 92))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
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

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("$");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 10, -1));

        jt_Precio.setBackground(new java.awt.Color(84, 110, 122));
        jt_Precio.setForeground(new java.awt.Color(204, 204, 204));
        jt_Precio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jt_Precio.setText("Ingresar Precio");
        jt_Precio.setBorder(null);
        jt_Precio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jt_PrecioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_PrecioFocusLost(evt);
            }
        });
        jt_Precio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_PrecioMouseClicked(evt);
            }
        });
        jt_Precio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_PrecioKeyTyped(evt);
            }
        });
        jPanel1.add(jt_Precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 100, -1));

        jSeparator5.setBackground(new java.awt.Color(128, 128, 131));
        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 100, 10));

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
        jPanel1.add(jb_limpiarCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, 40, -1));

        lb_errorObservaciones.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorObservaciones.setText("*");
        jPanel1.add(lb_errorObservaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        lb_errorNombreProducto.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorNombreProducto.setText("*");
        jPanel1.add(lb_errorNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jt_NuevoIngreso.setBackground(new java.awt.Color(84, 110, 122));
        jt_NuevoIngreso.setForeground(new java.awt.Color(204, 204, 204));
        jt_NuevoIngreso.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jt_NuevoIngreso.setText("Ingresar Cantidad");
        jt_NuevoIngreso.setBorder(null);
        jt_NuevoIngreso.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jt_NuevoIngresoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_NuevoIngresoFocusLost(evt);
            }
        });
        jt_NuevoIngreso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_NuevoIngresoMouseClicked(evt);
            }
        });
        jt_NuevoIngreso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_NuevoIngresoKeyTyped(evt);
            }
        });
        jPanel1.add(jt_NuevoIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 260, 90, -1));

        lb_errorStock.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorStock.setText("*");
        jPanel1.add(lb_errorStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, -1, -1));

        jt_CantidadStock.setBackground(new java.awt.Color(84, 110, 122));
        jt_CantidadStock.setForeground(new java.awt.Color(204, 204, 204));
        jt_CantidadStock.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jt_CantidadStock.setText("Ingresar Cantidad");
        jt_CantidadStock.setBorder(null);
        jt_CantidadStock.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jt_CantidadStockFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_CantidadStockFocusLost(evt);
            }
        });
        jt_CantidadStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_CantidadStockMouseClicked(evt);
            }
        });
        jt_CantidadStock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_CantidadStockKeyTyped(evt);
            }
        });
        jPanel1.add(jt_CantidadStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 90, -1));

        jLabel19.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Precio ");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 210, -1));

        lb_errorPrecio.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorPrecio.setText("*");
        jPanel1.add(lb_errorPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        jLabel21.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("Nombre del Proveedor");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 210, -1));

        jt_nombreProveedor.setBackground(new java.awt.Color(84, 110, 122));
        jt_nombreProveedor.setForeground(new java.awt.Color(204, 204, 204));
        jt_nombreProveedor.setText("Ingresar Nombre");
        jt_nombreProveedor.setBorder(null);
        jt_nombreProveedor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jt_nombreProveedorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_nombreProveedorFocusLost(evt);
            }
        });
        jt_nombreProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_nombreProveedorMouseClicked(evt);
            }
        });
        jt_nombreProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_nombreProveedorKeyTyped(evt);
            }
        });
        jPanel1.add(jt_nombreProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 210, -1));

        jSeparator7.setBackground(new java.awt.Color(128, 128, 131));
        jSeparator7.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 210, 10));

        lb_errorProveedor.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorProveedor.setText("*");
        jPanel1.add(lb_errorProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 10, -1));

        jLabel20.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Cantidad ");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, 100, -1));

        jseparador_nuevoIngreso.setBackground(new java.awt.Color(128, 128, 131));
        jseparador_nuevoIngreso.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jseparador_nuevoIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 280, 90, 10));

        lb_titulo2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_titulo2.setForeground(java.awt.Color.orange);
        lb_titulo2.setText("productos entrantes");
        jPanel1.add(lb_titulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, -1, -1));

        lb_titulo1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_titulo1.setForeground(java.awt.Color.orange);
        lb_titulo1.setText("Para ingresar nuevos ");
        jPanel1.add(lb_titulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, -1, -1));

        lb_titulo3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_titulo3.setForeground(java.awt.Color.orange);
        lb_titulo3.setText("Seleccione la siguiente opción");
        jPanel1.add(lb_titulo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, -1, -1));

        jc_nuevoIngreso.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jc_nuevoIngresoItemStateChanged(evt);
            }
        });
        jPanel1.add(jc_nuevoIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 260, -1, -1));

        jSeparator9.setBackground(new java.awt.Color(128, 128, 131));
        jSeparator9.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 90, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jt_nombreProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_nombreProveedorKeyTyped
        ce.typedCharsAndSpaceAndDigits(evt, jt_nombreProveedor);        // TODO add your handling code here:
    }//GEN-LAST:event_jt_nombreProveedorKeyTyped

    private void jt_nombreProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_nombreProveedorMouseClicked
        cft.formFocusGain(jt_nombreProveedor);        // TODO add your handling code here:
    }//GEN-LAST:event_jt_nombreProveedorMouseClicked

    private void jt_nombreProveedorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_nombreProveedorFocusLost
        cft.formFocusLostJTextField(jt_nombreProveedor, "Ingresar Nombre");        // TODO add your handling code here:
    }//GEN-LAST:event_jt_nombreProveedorFocusLost

    private void jt_nombreProveedorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_nombreProveedorFocusGained
        cft.formFocusGain(jt_nombreProveedor);        // TODO add your handling code here:
    }//GEN-LAST:event_jt_nombreProveedorFocusGained

    private void jt_CantidadStockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_CantidadStockKeyTyped

        ce.typedMoney(evt, jt_CantidadStock);      // TODO add your handling code here:
    }//GEN-LAST:event_jt_CantidadStockKeyTyped

    private void jt_CantidadStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_CantidadStockMouseClicked
        cft.formFocusGain(jt_CantidadStock);           // TODO add your handling code here:
    }//GEN-LAST:event_jt_CantidadStockMouseClicked

    private void jt_CantidadStockFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_CantidadStockFocusLost
        cft.formFocusLostJTextField(jt_Precio, "Ingresar Cantidad");        // TODO add your handling code here:
    }//GEN-LAST:event_jt_CantidadStockFocusLost

    private void jt_CantidadStockFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_CantidadStockFocusGained
        cft.formFocusGain(jt_CantidadStock);        // TODO add your handling code here:
    }//GEN-LAST:event_jt_CantidadStockFocusGained

    private void jb_limpiarCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_limpiarCamposActionPerformed
        datosIniciales();        // TODO add your handling code here:
    }//GEN-LAST:event_jb_limpiarCamposActionPerformed

    private void jt_PrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_PrecioKeyTyped
        ce.typedMoney(evt, jt_Precio);        // TODO add your handling code here:
    }//GEN-LAST:event_jt_PrecioKeyTyped

    private void jt_PrecioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_PrecioMouseClicked
        cft.formFocusGain(jt_Precio);            // TODO add your handling code here:
    }//GEN-LAST:event_jt_PrecioMouseClicked

    private void jt_PrecioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_PrecioFocusLost
        cft.formFocusLostJTextField(jt_Precio, "Ingresar Precio");
    }//GEN-LAST:event_jt_PrecioFocusLost

    private void jt_PrecioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_PrecioFocusGained
        cft.formFocusGain(jt_Precio);
    }//GEN-LAST:event_jt_PrecioFocusGained

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

    private void jt_BuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_BuscarKeyReleased
        this.jtabla_Productos.setModel(cpro.tablaProducto(jt_Buscar.getText()));//        this.jt_categorias.setModel(ccat.tablaCategorias(jt_Buscar, chk_mostrar.isSelected()));
        tamañoTabla();
    }//GEN-LAST:event_jt_BuscarKeyReleased

    private void jt_BuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_BuscarMouseClicked
        cft.formFocusGain(jt_Buscar);
    }//GEN-LAST:event_jt_BuscarMouseClicked

    private void jt_BuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_BuscarFocusLost
        cft.formFocusLostJTextField(jt_Buscar, "Buscar Nombre");
    }//GEN-LAST:event_jt_BuscarFocusLost

    private void jt_BuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_BuscarFocusGained
        cft.formFocusGain(jt_Buscar);
    }//GEN-LAST:event_jt_BuscarFocusGained

    private void jt_t_registrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_t_registrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_t_registrosActionPerformed

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
                        cpro.eliminarProducto(Integer.parseInt(lb_Id.getText()));
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

    private void btn_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModificarActionPerformed
        try {
            if (!validarEscritura() == true) {

                DesktopNotify.showDesktopMessage("Error", "REVISAR CAMPOS OBLIGATORIOS", DesktopNotify.ERROR);

            } else {
                
                if(ValidadorNuevoIngreso==1){
                    int NuevoStock=Integer.parseInt(jt_CantidadStock.getText())+Integer.parseInt(jt_NuevoIngreso.getText());
                    
                    cpro.actualizarProducto(jt_nombre.getText(), jt_nombreProveedor.getText(), Double.valueOf(jt_Precio.getText()), NuevoStock, jta_observaciones.getText(), Integer.parseInt(lb_Id.getText()));
                    NewTable = new DefaultTableModel();
                    cTabla();
                    tamañoTabla();
                    datosIniciales(); 
                    
                }else{
                    cpro.actualizarProducto(jt_nombre.getText(), jt_nombreProveedor.getText(), Double.valueOf(jt_Precio.getText()), Integer.parseInt(jt_CantidadStock.getText()), jta_observaciones.getText(), Integer.parseInt(lb_Id.getText()));
                    NewTable = new DefaultTableModel();
                    cTabla();
                    tamañoTabla();
                    datosIniciales();
                }
               
            }

        } catch (Exception e) {

        }
    }//GEN-LAST:event_btn_ModificarActionPerformed

    private void btn_IngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_IngresarActionPerformed
        try {
            if (!validarEscritura() == true) {
                DesktopNotify.showDesktopMessage("Error", "REVISAR CAMPOS OBLIGATORIOS", DesktopNotify.ERROR);
            } else {
                cpro.insertarProducto(jt_nombre.getText(),jt_nombreProveedor.getText(),Double.valueOf(jt_Precio.getText()), Integer.parseInt(jt_CantidadStock.getText()),jta_observaciones.getText());
                NewTable = new DefaultTableModel();
                cTabla();
                tamañoTabla();
                datosIniciales();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_IngresarActionPerformed

    private void jta_observacionesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jta_observacionesKeyPressed

    }//GEN-LAST:event_jta_observacionesKeyPressed

    private void jta_observacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jta_observacionesMouseClicked
        cft.formFocusGainJTextArea(jta_observaciones);
    }//GEN-LAST:event_jta_observacionesMouseClicked

    private void jta_observacionesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jta_observacionesFocusLost
        cft.formFocusLostJTextArea(jta_observaciones, "Ingresar Observaciones");
    }//GEN-LAST:event_jta_observacionesFocusLost

    private void jta_observacionesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jta_observacionesFocusGained
        cft.formFocusGainJTextArea(jta_observaciones);
    }//GEN-LAST:event_jta_observacionesFocusGained

    private void jt_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_nombreKeyTyped
        ce.typedCharsAndSpaceAndDigits(evt, jt_nombre);
    }//GEN-LAST:event_jt_nombreKeyTyped

    private void jt_nombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_nombreMouseClicked
        cft.formFocusGain(jt_nombre);
    }//GEN-LAST:event_jt_nombreMouseClicked

    private void jt_nombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_nombreFocusLost
        cft.formFocusLostJTextField(jt_nombre, "Ingresar Nombre");
    }//GEN-LAST:event_jt_nombreFocusLost

    private void jt_nombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_nombreFocusGained
        cft.formFocusGain(jt_nombre);
    }//GEN-LAST:event_jt_nombreFocusGained

    private void jtabla_ProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtabla_ProductosMouseClicked
        int seleccion = jtabla_Productos.rowAtPoint(evt.getPoint());
        //activacion de botones al elegir un dato
        btn_Modificar.setEnabled(true);
        btn_Eliminar.setEnabled(true);
        btn_Ingresar.setEnabled(false);
        //
mostrarFormNuevosIngresos();
        lb_Id.setText(String.valueOf(jtabla_Productos.getValueAt(seleccion, 0)));
        jt_nombre.setText(String.valueOf(jtabla_Productos.getValueAt(seleccion, 1)));
        jt_nombreProveedor.setText(String.valueOf(jtabla_Productos.getValueAt(seleccion, 2)));
        jt_Precio.setText(String.valueOf(jtabla_Productos.getValueAt(seleccion, 3)));
        jt_CantidadStock.setText(String.valueOf(jtabla_Productos.getValueAt(seleccion, 4)));
        jta_observaciones.setText(String.valueOf(jtabla_Productos.getValueAt(seleccion, 5)));

        // TODO add your handling code here:
    }//GEN-LAST:event_jtabla_ProductosMouseClicked

    private void jt_NuevoIngresoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_NuevoIngresoFocusGained
    cft.formFocusGain(jt_NuevoIngreso);           // TODO add your handling code here:
    }//GEN-LAST:event_jt_NuevoIngresoFocusGained

    private void jt_NuevoIngresoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_NuevoIngresoFocusLost
  cft.formFocusLostJTextField(jt_NuevoIngreso, "Ingresar Cantidad");          // TODO add your handling code here:
    }//GEN-LAST:event_jt_NuevoIngresoFocusLost

    private void jt_NuevoIngresoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_NuevoIngresoMouseClicked
 cft.formFocusGain(jt_NuevoIngreso);          // TODO add your handling code here:
    }//GEN-LAST:event_jt_NuevoIngresoMouseClicked

    private void jt_NuevoIngresoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_NuevoIngresoKeyTyped
  ce.typedMoney(evt, jt_NuevoIngreso);         // TODO add your handling code here:
    }//GEN-LAST:event_jt_NuevoIngresoKeyTyped

    private void jc_nuevoIngresoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jc_nuevoIngresoItemStateChanged
       if (evt.getStateChange() == ItemEvent.SELECTED) {
            ValidadorNuevoIngreso = 1;
            jt_CantidadStock.setEnabled(false);
            jt_NuevoIngreso.setEnabled(true);

        } else {
            ValidadorNuevoIngreso = 0;
            jt_CantidadStock.setEnabled(true);
            jt_NuevoIngreso.setEnabled(false);

        }        // TODO add your handling code here:
    }//GEN-LAST:event_jc_nuevoIngresoItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static principal.MaterialButton btn_Eliminar;
    public static principal.MaterialButton btn_Ingresar;
    public static principal.MaterialButton btn_Modificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
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
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JButton jb_limpiarCampos;
    private javax.swing.JCheckBox jc_nuevoIngreso;
    private javax.swing.JSeparator jseparador_nuevoIngreso;
    public static javax.swing.JTextField jt_Buscar;
    private javax.swing.JTextField jt_CantidadStock;
    private javax.swing.JTextField jt_NuevoIngreso;
    private javax.swing.JTextField jt_Precio;
    private javax.swing.JTextField jt_nombre;
    private javax.swing.JTextField jt_nombreProveedor;
    private javax.swing.JTextField jt_t_registros;
    private javax.swing.JTextArea jta_observaciones;
    private javax.swing.JTable jtabla_Productos;
    private javax.swing.JLabel lb_Id;
    private javax.swing.JLabel lb_errorNombreProducto;
    private javax.swing.JLabel lb_errorObservaciones;
    private javax.swing.JLabel lb_errorPrecio;
    private javax.swing.JLabel lb_errorProveedor;
    private javax.swing.JLabel lb_errorStock;
    private javax.swing.JLabel lb_titulo1;
    private javax.swing.JLabel lb_titulo2;
    private javax.swing.JLabel lb_titulo3;
    // End of variables declaration//GEN-END:variables
}
