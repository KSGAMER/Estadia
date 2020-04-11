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
import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import objetos.*;
import controladores.*;
import ds.desktop.notify.DesktopNotify;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import vistas.Alertas.Pn_Alert_Eliminar;
/**
 *
 * @author fenix
 */
public class Pn_NuevoEmpleado extends javax.swing.JPanel {
    //NECESARIO PARA FUNCIONES DE ESTE MODULO 
    ControladorEstatusUsuarios usr = new ControladorEstatusUsuarios();
    ControladorCargos ccargo = new ControladorCargos();
    ControladorUsuarios cusr = new ControladorUsuarios();
    ControladorEscritura ce = new ControladorEscritura();
    private ControladorFormularioTab cft = new ControladorFormularioTab();
    DefaultTableModel NewTable;
    //fin
    //necesario para obtener el numero de fila de la jtable
    private int seleccion;
    //fin
     //NECESARIO PARA HACER LA COMPRACION Y EXTRACCION DE LOS PRIVILEGIOS DE ESTE MODULO
    private String NombreModulo = "Empleados";
    //FIN
  //NECESARIO PARA EL USO DE LA NOTIFICACION DINAMICA DE BOTON ELIMINAR ()
    Frame Principal;
//FIN
/* necesario para ectraer la ruta e informacion de las imagenes de usuario
avatars es la carpeta que esta al nivel de los arvhicos de la aplicacion y en la cual se guardan las imagenes predeterminadas 
parauna imagen de avatar si no se escribe nada, por default JFileChooser usara "mis documentos o home (unix)" como carpeta
    inicial de aperura de archivos*/
    JFileChooser seleccionar = new JFileChooser("Avatars");
    File archivo;
    String ruta = null;
//fin 
//delimita la cantidad maxima que se puede digitar al escribir una contraseña
    private int limitePass = 20;
//fin

    /**
     * Creates new form NuevoEmpleado1
     */
    public Pn_NuevoEmpleado() {
        initComponents();
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
        //carga los elementos del estado de los usuarios para llenar el cbox dentro del metodo cargarStatus..()
        usr.tablaEstatusUsuarios();
        cargarStatusEmpleados();
        ccargo.tablaCargos();
        cargarPuestosEmpleados();
        //FIN
        tamañoTabla();
        //RECORDATORIO 
        DesktopNotify.showDesktopMessage("Recordatorio", "El password puede ser de maximo 20 caracteres ", DesktopNotify.TIP);
    }

    public void cTabla() {
        jt_Empleados.setModel(cusr.tablaUsuarios());
        jt_t_registros.setText(String.valueOf(jt_Empleados.getRowCount()));
    }

    public void tamañoTabla() {
        TableColumnModel columnModel = jt_Empleados.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(50);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(70);
        columnModel.getColumn(4).setPreferredWidth(150);
        columnModel.getColumn(5).setPreferredWidth(50);
    }

    public void datosIniciales() {

        jt_usuario.setText("Ingresar Usuario");
        jt_password.setText("Ingresar Password");
        jt_empleado.setText("Ingresar Nombre");
        jt_telefono.setText("Ingresar Telefono");
        jt_direccion.setText("Ingresar Dirección");
        lb_rutaImagen.setText("");
        lb_imagenPrevia.setIcon(null);
        cb_status.setSelectedIndex(0);
        cb_Cargo.setSelectedIndex(0);
        btn_Modificar.setEnabled(false);
        btn_Eliminar.setEnabled(false);
        btn_Ingresar.setEnabled(true);

        lb_errorUsuario.setForeground(new Color(84, 110, 122));
        lb_errorPassword.setForeground(new Color(84, 110, 122));
        lb_errorNombre.setForeground(new Color(84, 110, 122));
        lb_errorTelefono.setForeground(new Color(84, 110, 122));
        lb_errorDireccion.setForeground(new Color(84, 110, 122));
        lb_errorStatus.setForeground(new Color(84, 110, 122));

    }

    public void RowApariencia() {

        jt_Empleados.setFocusable(false);

        //espacio entre comulnas
        jt_Empleados.setIntercellSpacing(new Dimension(0, 1));
        //altura de columnas 
        jt_Empleados.setRowHeight(25);
        //margen entre filas
        jt_Empleados.setRowMargin(0);
//sin lineas verticles
        jt_Empleados.setShowVerticalLines(false);
        jt_Empleados.setSelectionBackground(new Color(97, 212, 195));

    }

    public void RowHeaderApariencia() {
        jt_Empleados.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 14));
        jt_Empleados.getTableHeader().setOpaque(false);
        jt_Empleados.getTableHeader().setBackground(new Color(32, 136, 203));
        jt_Empleados.getTableHeader().setForeground(new Color(255, 255, 255));

    }

    public void cargarStatusEmpleados() {

        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        cb.addElement("Seleccionar Estatus");
        for (ObjetoEstatusUsuario campos : usr.selectEstatusUsuario()) {
            cb.addElement(campos.getNombre());
        }
        cb_status.setModel(cb);

    }

    public void cargarPuestosEmpleados() {

        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        cb.addElement("Seleccionar Puesto");
        for (ObjetoCargo campos : ccargo.seleccionarCargo()) {
            cb.addElement(campos.getNombre());
        }
        cb_Cargo.setModel(cb);

    }

    public Boolean validarEscritura() {
        Boolean val = true;
        //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(jt_usuario.getText().equals("Ingresar Usuario")) && !(jt_usuario.getText().equals(""))) {
            lb_errorUsuario.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorUsuario.setForeground(Color.RED);
            val = false;
        }
        //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(jt_password.getText().equals("Ingresar Password")) && !(jt_password.getText().equals(""))) {
            lb_errorPassword.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorPassword.setForeground(Color.RED);
            val = false;
        }
        //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(jt_empleado.getText().equals("Ingresar Nombre")) && !(jt_empleado.getText().equals(""))) {
            lb_errorNombre.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorNombre.setForeground(Color.RED);
            val = false;
        }
        //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(jt_telefono.getText().equals("Ingresar Telefono")) && !(jt_telefono.getText().equals(""))) {
            lb_errorTelefono.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorTelefono.setForeground(Color.RED);
            val = false;
        }
        //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(jt_direccion.getText().equals("Ingresar Dirección")) && !(jt_usuario.getText().equals(""))) {
            lb_errorDireccion.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorDireccion.setForeground(Color.RED);
            val = false;
        }
        //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(lb_rutaImagen.getText().equals(""))) {
            lb_errorImagen.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorImagen.setForeground(Color.RED);
            val = false;
        }
        return val;
    }

    public Boolean validarSeleccion() {
        Boolean val = true;
        if (!(cb_status.getSelectedIndex() == 0)) {

            lb_errorStatus.setForeground(new Color(84, 110, 122));
        } else {

            lb_errorStatus.setForeground(Color.RED);

            val = false;
        }
        return val;
    }
//obtiene la ruta que se extrae del selector de archivo y muestra una vista previa en el jlabel asignado en 
    //linea 242
    private void ajustarImagen(String path) {
        //se utiliza para obtener la ruta de la imagen 
        ImageIcon icon = new ImageIcon(path);

        //se utilizar para obtener el tamaño de jlaben que contendra la imagen y 
        //despues se reacomda la imagen automaticamente 
        Image image = icon.getImage().getScaledInstance(lb_imagenPrevia.getWidth(), lb_imagenPrevia.getHeight(), Image.SCALE_SMOOTH);
//AQUI AGREGAMOS LAS IMAGENES AL LABEL COMO ICONO
        lb_imagenPrevia.setIcon(new ImageIcon(image));
        this.repaint();
    }
public void abrirArchivoImagen(){
     //se usa para obtener un valor de aprovacion cuando la ventana de dialogo abre
        int ap = seleccionar.showOpenDialog(this);
        if (ap == JFileChooser.APPROVE_OPTION) {
            //se asignan los datos del archivo a la variable archivo que es de tipo "file"
            archivo = seleccionar.getSelectedFile();
            //se verifica si es posible leer este archivo , el cual debe ser una imagen
            if (archivo.canRead()) {
                //se validan los tipos de terminacion del archivo para imagen 
                if (archivo.getName().endsWith("jpg") || archivo.getName().endsWith("png") || archivo.getName().endsWith("jpeg") || archivo.getName().endsWith("JPG") || archivo.getName().endsWith("PNG") || archivo.getName().endsWith("JPEG")) {
                   //se asigna la ruta completa del archivo
                    ruta = archivo.getAbsolutePath();
                    System.out.println(archivo.getName());
                    //se muetra un previo ajustando la imagen y mostrandola en un jlabel llamado "lb_imagenPrevia"
                    ajustarImagen(ruta);
                    //se asigna la ruta completa de la imagen a un jlabel para ser observado
                    lb_rutaImagen.setText(ruta);
                } else {
                    DesktopNotify.showDesktopMessage("RECOMENDACIÓN", "Solo se permiten archivos de imagen "
                            + "con terminación jpg,png o jpeg", DesktopNotify.ERROR);
                }
            } else {
                DesktopNotify.showDesktopMessage("ERROR", "Archivo no compatible", DesktopNotify.ERROR);
            }
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_Empleados = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        btn_Ingresar = new principal.MaterialButton();
        btn_Eliminar = new principal.MaterialButton();
        btn_Modificar = new principal.MaterialButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jt_empleado = new javax.swing.JTextField();
        jt_usuario = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jt_telefono = new javax.swing.JTextField();
        lb_rutaImagen = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jt_direccion = new javax.swing.JTextField();
        cb_status = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jt_password = new javax.swing.JPasswordField();
        jch_mostrar = new javax.swing.JCheckBox();
        jt_Buscar = new javax.swing.JTextField();
        lb_errorStatus = new javax.swing.JLabel();
        lb_errorUsuario = new javax.swing.JLabel();
        lb_errorPassword = new javax.swing.JLabel();
        lb_errorNombre = new javax.swing.JLabel();
        lb_errorTelefono = new javax.swing.JLabel();
        lb_errorDireccion = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jb_limpiarCampos = new javax.swing.JButton();
        jt_t_registros = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        btn_clientes = new principal.MaterialButton();
        jSeparator9 = new javax.swing.JSeparator();
        lb_imagenPrevia = new javax.swing.JLabel();
        lb_errorImagen = new javax.swing.JLabel();
        cb_Cargo = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(84, 110, 122));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jt_Empleados.setModel(new javax.swing.table.DefaultTableModel(
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
        jt_Empleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_EmpleadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jt_Empleados);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 970, 120));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Usuario");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 190, -1));

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 190, 10));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Total de registros ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 260, 100, 20));

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
        jPanel1.add(btn_Ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 340, 200, 40));

        btn_Eliminar.setBackground(new java.awt.Color(211, 18, 18));
        btn_Eliminar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Eliminar.setText("eliminar");
        btn_Eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Eliminar.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_Eliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_EliminarMouseClicked(evt);
            }
        });
        btn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 460, 200, 40));

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
        jPanel1.add(btn_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 400, 200, 40));

        jPanel2.setBackground(new java.awt.Color(84, 110, 122));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("VISTA GENERAL DE EMPLEADOS");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Inicio > Administrador > Nuevo Empleado");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 377, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(35, 35, 35))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 50));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Formulario de Cambios");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Detalle de Empleados");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 1040, 10));

        jLabel19.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Password");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 190, -1));

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 190, 10));

        jLabel20.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Nombre Empleado");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 190, -1));

        jSeparator6.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 490, 190, 10));

        jt_empleado.setBackground(new java.awt.Color(84, 110, 122));
        jt_empleado.setForeground(new java.awt.Color(204, 204, 204));
        jt_empleado.setText("Ingresar Nombre");
        jt_empleado.setBorder(null);
        jt_empleado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jt_empleadoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_empleadoFocusLost(evt);
            }
        });
        jt_empleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_empleadoMouseClicked(evt);
            }
        });
        jt_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jt_empleadoActionPerformed(evt);
            }
        });
        jt_empleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_empleadoKeyTyped(evt);
            }
        });
        jPanel1.add(jt_empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 170, -1));

        jt_usuario.setBackground(new java.awt.Color(84, 110, 122));
        jt_usuario.setForeground(new java.awt.Color(204, 204, 204));
        jt_usuario.setText("Ingresar Usuario");
        jt_usuario.setBorder(null);
        jt_usuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jt_usuarioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_usuarioFocusLost(evt);
            }
        });
        jt_usuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_usuarioMouseClicked(evt);
            }
        });
        jt_usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_usuarioKeyTyped(evt);
            }
        });
        jPanel1.add(jt_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 170, -1));

        jLabel21.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("Telefono");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 190, -1));

        jSeparator7.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, 190, 10));

        jt_telefono.setBackground(new java.awt.Color(84, 110, 122));
        jt_telefono.setForeground(new java.awt.Color(204, 204, 204));
        jt_telefono.setText("Ingresar Telefono");
        jt_telefono.setBorder(null);
        jt_telefono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jt_telefonoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_telefonoFocusLost(evt);
            }
        });
        jt_telefono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_telefonoMouseClicked(evt);
            }
        });
        jt_telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_telefonoKeyTyped(evt);
            }
        });
        jPanel1.add(jt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 170, -1));

        lb_rutaImagen.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lb_rutaImagen.setForeground(new java.awt.Color(255, 255, 255));
        lb_rutaImagen.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel1.add(lb_rutaImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 470, 190, 20));

        jSeparator8.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 570, 190, 10));

        jt_direccion.setBackground(new java.awt.Color(84, 110, 122));
        jt_direccion.setForeground(new java.awt.Color(204, 204, 204));
        jt_direccion.setText("Ingresar Dirección");
        jt_direccion.setBorder(null);
        jt_direccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jt_direccionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_direccionFocusLost(evt);
            }
        });
        jt_direccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_direccionMouseClicked(evt);
            }
        });
        jt_direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_direccionKeyTyped(evt);
            }
        });
        jPanel1.add(jt_direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 550, 170, -1));

        cb_status.setBackground(new java.awt.Color(84, 110, 122));
        cb_status.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cb_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Estatus" }));
        cb_status.setBorder(null);
        cb_status.setFocusable(false);
        cb_status.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_statusItemStateChanged(evt);
            }
        });
        cb_status.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cb_statusFocusLost(evt);
            }
        });
        jPanel1.add(cb_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 410, 190, -1));

        jLabel23.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("Dirección ");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, 190, -1));

        jt_password.setBackground(new java.awt.Color(84, 110, 122));
        jt_password.setForeground(new java.awt.Color(204, 204, 204));
        jt_password.setText("Ingresar Password");
        jt_password.setBorder(null);
        jt_password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jt_passwordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_passwordFocusLost(evt);
            }
        });
        jt_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_passwordKeyTyped(evt);
            }
        });
        jPanel1.add(jt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 170, -1));

        jch_mostrar.setBackground(new java.awt.Color(84, 110, 122));
        jch_mostrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jch_mostrar.setForeground(new java.awt.Color(255, 255, 255));
        jch_mostrar.setText("Mostrar");
        jch_mostrar.setContentAreaFilled(false);
        jch_mostrar.setFocusPainted(false);
        jch_mostrar.setFocusable(false);
        jch_mostrar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jch_mostrarItemStateChanged(evt);
            }
        });
        jch_mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jch_mostrarActionPerformed(evt);
            }
        });
        jPanel1.add(jch_mostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 400, -1, -1));

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
        jPanel1.add(jt_Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 150, 20));

        lb_errorStatus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorStatus.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorStatus.setText("*");
        jPanel1.add(lb_errorStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 390, 10, -1));

        lb_errorUsuario.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorUsuario.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorUsuario.setText("*");
        jPanel1.add(lb_errorUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 10, -1));

        lb_errorPassword.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorPassword.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorPassword.setText("*");
        jPanel1.add(lb_errorPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 10, -1));

        lb_errorNombre.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorNombre.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorNombre.setText("*");
        jPanel1.add(lb_errorNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 10, -1));

        lb_errorTelefono.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorTelefono.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorTelefono.setText("*");
        jPanel1.add(lb_errorTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 10, -1));

        lb_errorDireccion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorDireccion.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorDireccion.setText("*");
        jPanel1.add(lb_errorDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 10, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/campo-buscar.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 90, -1, 40));

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
        jPanel1.add(jb_limpiarCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 310, 40, -1));

        jt_t_registros.setEditable(false);
        jt_t_registros.setBackground(new java.awt.Color(84, 110, 122));
        jt_t_registros.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jt_t_registros.setForeground(new java.awt.Color(255, 255, 255));
        jt_t_registros.setBorder(null);
        jPanel1.add(jt_t_registros, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 260, 30, 20));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1040, 10));

        jLabel24.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("Cargo");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 340, 190, -1));

        jLabel25.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("Seleccionar imagen de perfil");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 440, 190, -1));

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
        jPanel1.add(btn_clientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 460, 30, 30));

        jSeparator9.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 190, 10));

        lb_imagenPrevia.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));
        jPanel1.add(lb_imagenPrevia, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 500, 190, 120));

        lb_errorImagen.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorImagen.setText("*");
        jPanel1.add(lb_errorImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 440, 10, -1));

        cb_Cargo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cb_Cargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Puesto" }));
        jPanel1.add(cb_Cargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 360, 190, -1));

        jLabel26.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("Estatus");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 390, 190, -1));

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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jt_EmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_EmpleadosMouseClicked

        seleccion = jt_Empleados.rowAtPoint(evt.getPoint());
        btn_Modificar.setEnabled(true);
        btn_Eliminar.setEnabled(true);
        btn_Ingresar.setEnabled(false);
        jt_usuario.setText(String.valueOf(jt_Empleados.getValueAt(seleccion, 0)));
        jt_password.setText(String.valueOf(jt_Empleados.getValueAt(seleccion, 1)));
        jt_empleado.setText(String.valueOf(jt_Empleados.getValueAt(seleccion, 2)));
        jt_telefono.setText(String.valueOf(jt_Empleados.getValueAt(seleccion, 3)));
        jt_direccion.setText(String.valueOf(jt_Empleados.getValueAt(seleccion, 4)));
        try {
            lb_rutaImagen.setText(String.valueOf(jt_Empleados.getValueAt(seleccion, 5)));
            ajustarImagen(String.valueOf(jt_Empleados.getValueAt(seleccion, 5)));
        } catch (Exception e) {
        }

        cb_Cargo.getModel().setSelectedItem(jt_Empleados.getValueAt(seleccion, 6));
        cb_status.getModel().setSelectedItem(jt_Empleados.getValueAt(seleccion, 7));
// TODO add your handling code here:
    }//GEN-LAST:event_jt_EmpleadosMouseClicked

    private void btn_IngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_IngresarMouseClicked

    }//GEN-LAST:event_btn_IngresarMouseClicked

    private void btn_IngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_IngresarActionPerformed

        
        try {
            if (!validarEscritura() == true && !validarSeleccion() == true) {
                DesktopNotify.showDesktopMessage("Error", "REVISAR CAMPOS OBLIGATORIOS", DesktopNotify.ERROR);
            } else {
                cusr.insertUsuario(jt_usuario.getText(), String.valueOf(jt_password.getPassword()), jt_empleado.getText(), jt_telefono.getText(), jt_direccion.getText(), lb_rutaImagen.getText(), String.valueOf(cb_Cargo.getSelectedItem()), String.valueOf(cb_status.getSelectedItem()));
                NewTable = new DefaultTableModel();
                cTabla();
                tamañoTabla();
                datosIniciales();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_IngresarActionPerformed

    private void btn_EliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_EliminarMouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_EliminarMouseClicked

    private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarActionPerformed

        try {
            if (jt_usuario.getText().equals("Ingresar Usuario")) {
                DesktopNotify.showDesktopMessage("Error", "DEBE SELECCIONAR UN ELEMENTO DE LA TABLA PARA PODER SER ELIMINADO", DesktopNotify.ERROR);
            } else {

                Pn_Alert_Eliminar ale = new Pn_Alert_Eliminar(Principal, true);
                ale.lb_titulo.setText("¿Esta seguro de eliminar este elemento?");
                ale.jb_aceptar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {

                        cusr.deleteUsuario(jt_usuario.getText());
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

    private void btn_ModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ModificarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ModificarMouseClicked

    private void jt_empleadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_empleadoFocusGained
        cft.formFocusGain(jt_empleado);
    }//GEN-LAST:event_jt_empleadoFocusGained

    private void jt_empleadoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_empleadoFocusLost
        cft.formFocusLostJTextField(jt_empleado, "Ingresar Nombre");
    }//GEN-LAST:event_jt_empleadoFocusLost

    private void jt_empleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_empleadoMouseClicked
        cft.formFocusGain(jt_empleado);
    }//GEN-LAST:event_jt_empleadoMouseClicked

    private void jt_empleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_empleadoKeyTyped
        ce.typedCharsAndSpaceAndDigits(evt, jt_empleado);
    }//GEN-LAST:event_jt_empleadoKeyTyped

    private void jt_usuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_usuarioFocusGained
        cft.formFocusGain(jt_usuario);        // TODO add your handling code here:
    }//GEN-LAST:event_jt_usuarioFocusGained

    private void jt_usuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_usuarioFocusLost
        cft.formFocusLostJTextField(jt_usuario, "Ingresar Usuario");        // TODO add your handling code here:
    }//GEN-LAST:event_jt_usuarioFocusLost

    private void jt_usuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_usuarioMouseClicked
        cft.formFocusGain(jt_usuario);        // TODO add your handling code here:
    }//GEN-LAST:event_jt_usuarioMouseClicked

    private void jt_usuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_usuarioKeyTyped
      ce.typedCharsAndSpaceAndDigits(evt, jt_usuario);
    }//GEN-LAST:event_jt_usuarioKeyTyped

    private void jt_telefonoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_telefonoFocusGained
        cft.formFocusGain(jt_telefono);
    }//GEN-LAST:event_jt_telefonoFocusGained

    private void jt_telefonoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_telefonoFocusLost
        cft.formFocusLostJTextField(jt_telefono, "Ingresar Telefono");
    }//GEN-LAST:event_jt_telefonoFocusLost

    private void jt_telefonoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_telefonoMouseClicked
        cft.formFocusGain(jt_telefono);
    }//GEN-LAST:event_jt_telefonoMouseClicked

    private void jt_telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_telefonoKeyTyped
        ce.typedDigits(evt, jt_telefono);
    }//GEN-LAST:event_jt_telefonoKeyTyped

    private void jt_direccionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_direccionFocusGained
        cft.formFocusGain(jt_direccion);
    }//GEN-LAST:event_jt_direccionFocusGained

    private void jt_direccionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_direccionFocusLost
        cft.formFocusLostJTextField(jt_direccion, "Ingresar Dirección");
    }//GEN-LAST:event_jt_direccionFocusLost

    private void jt_direccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_direccionMouseClicked
        cft.formFocusGain(jt_direccion);
    }//GEN-LAST:event_jt_direccionMouseClicked

    private void jt_direccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_direccionKeyTyped
        ce.typedAddress(evt, jt_direccion);
    }//GEN-LAST:event_jt_direccionKeyTyped

    private void cb_statusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_statusItemStateChanged

    }//GEN-LAST:event_cb_statusItemStateChanged

    private void cb_statusFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_statusFocusLost
      
    }//GEN-LAST:event_cb_statusFocusLost

    private void jch_mostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jch_mostrarActionPerformed
        
        
    }//GEN-LAST:event_jch_mostrarActionPerformed

    private void jch_mostrarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jch_mostrarItemStateChanged
     if (evt.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
            jt_password.setEchoChar((char)0);
           
        } else {//checkbox has been deselected
         jt_password.setEchoChar('\u25cf');
        }  
        // TODO add your handling code here:
    }//GEN-LAST:event_jch_mostrarItemStateChanged

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
    
    }//GEN-LAST:event_jt_BuscarKeyReleased

    private void jt_BuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_BuscarKeyTyped

        char tecla;
        tecla = evt.getKeyChar();
        //Convertir a letras mayusculas
        if (Character.isLetter(tecla)) {
            evt.setKeyChar(Character.toUpperCase(tecla));

        }
    }//GEN-LAST:event_jt_BuscarKeyTyped

    private void jt_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_empleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_empleadoActionPerformed

    private void jt_passwordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_passwordFocusGained
   cft.formFocusGain(jt_password);        // TODO add your handling code here:
    }//GEN-LAST:event_jt_passwordFocusGained

    private void jt_passwordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_passwordFocusLost
   cft.formFocusLostJTextField(jt_password,"Ingresar Password");   
     jt_password.setEchoChar('\u25cf');
   // TODO add your handling code here:
    }//GEN-LAST:event_jt_passwordFocusLost

    private void btn_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModificarActionPerformed
     try {
            if (!validarEscritura() == true && !validarSeleccion() == true) {
                DesktopNotify.showDesktopMessage("Error", "REVISAR CAMPOS OBLIGATORIOS", DesktopNotify.ERROR);
            } else {
                cusr.updateUsuario(String.valueOf(jt_password.getPassword()), jt_empleado.getText(), jt_telefono.getText(), jt_direccion.getText(),lb_rutaImagen.getText(), String.valueOf(cb_Cargo.getSelectedItem()), String.valueOf(cb_status.getSelectedItem()), jt_usuario.getText());
                NewTable = new DefaultTableModel();
                cTabla();
                tamañoTabla();
                datosIniciales();
            }
        } catch (Exception e) {
      }        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ModificarActionPerformed

    private void jt_passwordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_passwordKeyTyped
       //compara tamaño
        if (jt_password.getText().length() == limitePass) {
            evt.consume();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jt_passwordKeyTyped

    private void jb_limpiarCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_limpiarCamposActionPerformed
        datosIniciales();        // TODO add your handling code here:
    }//GEN-LAST:event_jb_limpiarCamposActionPerformed

    private void btn_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_clientesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_clientesMouseClicked

    private void btn_clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clientesActionPerformed
       abrirArchivoImagen();
    }//GEN-LAST:event_btn_clientesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private principal.MaterialButton btn_Eliminar;
    private principal.MaterialButton btn_Ingresar;
    private principal.MaterialButton btn_Modificar;
    private principal.MaterialButton btn_clientes;
    private javax.swing.JComboBox<String> cb_Cargo;
    private javax.swing.JComboBox<String> cb_status;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JButton jb_limpiarCampos;
    private javax.swing.JCheckBox jch_mostrar;
    private javax.swing.JTextField jt_Buscar;
    private javax.swing.JTable jt_Empleados;
    private javax.swing.JTextField jt_direccion;
    private javax.swing.JTextField jt_empleado;
    private javax.swing.JPasswordField jt_password;
    private javax.swing.JTextField jt_t_registros;
    private javax.swing.JTextField jt_telefono;
    private javax.swing.JTextField jt_usuario;
    private javax.swing.JLabel lb_errorDireccion;
    private javax.swing.JLabel lb_errorImagen;
    private javax.swing.JLabel lb_errorNombre;
    private javax.swing.JLabel lb_errorPassword;
    private javax.swing.JLabel lb_errorStatus;
    private javax.swing.JLabel lb_errorTelefono;
    private javax.swing.JLabel lb_errorUsuario;
    private javax.swing.JLabel lb_imagenPrevia;
    private javax.swing.JLabel lb_rutaImagen;
    // End of variables declaration//GEN-END:variables
}
