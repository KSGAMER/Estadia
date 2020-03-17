package vistas;

import controladores.*;
import ds.desktop.notify.DesktopNotify;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import static java.util.Collections.list;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import objetos.ObjetoCFDI;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fenix
 */
public class Pn_SeleccionarClientes extends javax.swing.JFrame {

    ControladorClientes mc = new ControladorClientes();
    ControladorCFDI cf = new ControladorCFDI();
    ControladorEscritura ce = new ControladorEscritura();
    Pn_Reservaciones reserv ;
    DefaultTableModel NewTable;
    private int seleccion;
  //  private int idCfdi = 0;
    /**
     * Creates new form Pn_SeleccionClientes
     */
    public Pn_SeleccionarClientes() {
        setPantalla();
        initComponents();
        setIconSystem();
        centrarPantalla();
        datosIniciales();
        RowHeaderApariencia();
        RowApariencia();
        cf.tablaCFDI();
        cTabla();
        tamañoTabla();
        cargarCFDI();


    }
    public void setPantalla() {

        //para eliminar el tittle bar
        this.setUndecorated(true);
        //para que no le cambien el tamaño
        this.setResizable(false);

    }

    public void setIconSystem() {
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logosmall.png")).getImage());
    }

    public void centrarPantalla() {
        //para dejar el menu centrado y estatico
        this.setLocationRelativeTo(null);

    }

     public void cTabla() {
        jt_Clientes.setModel(mc.tablaClientes());
            jt_t_registros.setText(String.valueOf(mc.selectCliente().size()));
    }

    public void cargarCFDI() {
        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        cb.addElement("Seleccionar CFDI");
        for (ObjetoCFDI campos : cf.selectCFDI()) {
            cb.addElement(campos.getNombre());

        }
        cb_cfdi.setModel(cb);
    
    }

    public void tamañoTabla() {
        TableColumnModel columnModel = jt_Clientes.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(120);
        columnModel.getColumn(3).setPreferredWidth(240);
        columnModel.getColumn(4).setPreferredWidth(100);
        columnModel.getColumn(5).setPreferredWidth(170);
        columnModel.getColumn(6).setPreferredWidth(80);

    }
    public void RowApariencia() {

        jt_Clientes.setFocusable(false);

        //espacio entre comulnas
        jt_Clientes.setIntercellSpacing(new Dimension(0, 1));
        //altura de columnas 
        jt_Clientes.setRowHeight(25);
        //margen entre filas
        jt_Clientes.setRowMargin(0);
//sin lineas verticles
        jt_Clientes.setShowVerticalLines(false);
        jt_Clientes.setSelectionBackground(new Color(97, 212, 195));

    }

    public void RowHeaderApariencia() {
        jt_Clientes.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 14));
        jt_Clientes.getTableHeader().setOpaque(false);
        jt_Clientes.getTableHeader().setBackground(new Color(32, 136, 203));
        jt_Clientes.getTableHeader().setForeground(new Color(255, 255, 255));

    }

    public void datosIniciales() {
        lb_Id.setText("*");
        lb_errorCFDI.setText("*");
        lb_errorDireccion.setText("*");
        lb_errorEmail.setText("*");
        lb_errorNombre.setText("*");
        lb_errorRFC.setText("*");
        lb_errorTelefono.setText("*");
        //colores
        lb_errorCFDI.setForeground(new Color(84, 110, 122));
        lb_errorDireccion.setForeground(new Color(84, 110, 122));
        lb_errorEmail.setForeground(new Color(84, 110, 122));
        lb_errorNombre.setForeground(new Color(84, 110, 122));
        lb_errorRFC.setForeground(new Color(84, 110, 122));
        lb_errorTelefono.setForeground(new Color(84, 110, 122));

        
        
        
        
        
        jt_nombre.setText("Ingresar Nombre");
        jt_rfc.setText("Ingresar RFC");
        jt_direccion.setText("Ingresar Direccion Fiscal");
        jt_telefono.setText("Ingresar Telefono");
        jt_email.setText("Ingresar Email");
        cb_cfdi.setSelectedIndex(0);
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
        jt_Clientes = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jt_nombre = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jt_rfc = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jt_direccion = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jt_telefono = new javax.swing.JTextField();
        Celular1 = new javax.swing.JLabel();
        jt_email = new javax.swing.JTextField();
        Celular2 = new javax.swing.JLabel();
        cb_cfdi = new javax.swing.JComboBox<>();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        btn_Cancelar = new principal.MaterialButton();
        lb_Id = new javax.swing.JLabel();
        lb_errorCFDI = new javax.swing.JLabel();
        lb_errorNombre = new javax.swing.JLabel();
        lb_errorRFC = new javax.swing.JLabel();
        lb_errorDireccion = new javax.swing.JLabel();
        lb_errorTelefono = new javax.swing.JLabel();
        lb_errorEmail = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jt_Buscar = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        pn_cerrar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btn_Ingresar1 = new principal.MaterialButton();
        jt_t_registros = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(84, 110, 122));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jt_Clientes.setModel(new javax.swing.table.DefaultTableModel(
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
        jt_Clientes.getTableHeader().setResizingAllowed(false);
        jt_Clientes.getTableHeader().setReorderingAllowed(false);
        jt_Clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_ClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jt_Clientes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 720, 320));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Nombre y/o Razon Social ");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 190, -1));

        jt_nombre.setBackground(new java.awt.Color(84, 110, 122));
        jt_nombre.setForeground(new java.awt.Color(204, 204, 204));
        jt_nombre.setText("Ingresar Nombre");
        jt_nombre.setBorder(null);
        jt_nombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_nombreFocusLost(evt);
            }
        });
        jt_nombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_nombreMouseClicked(evt);
            }
        });
        jt_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jt_nombreActionPerformed(evt);
            }
        });
        jt_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_nombreKeyTyped(evt);
            }
        });
        jPanel1.add(jt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 170, -1));

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Registro Federal de Controbuyentes (RFC)");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 250, -1));

        jt_rfc.setBackground(new java.awt.Color(84, 110, 122));
        jt_rfc.setForeground(new java.awt.Color(204, 204, 204));
        jt_rfc.setText("Ingresar RFC");
        jt_rfc.setBorder(null);
        jt_rfc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_rfcFocusLost(evt);
            }
        });
        jt_rfc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_rfcMouseClicked(evt);
            }
        });
        jt_rfc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jt_rfcActionPerformed(evt);
            }
        });
        jt_rfc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_rfcKeyTyped(evt);
            }
        });
        jPanel1.add(jt_rfc, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 170, -1));

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Direccion Fiscal ");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 190, -1));

        jt_direccion.setBackground(new java.awt.Color(84, 110, 122));
        jt_direccion.setForeground(new java.awt.Color(204, 204, 204));
        jt_direccion.setText("Ingresar Direccion Fiscal");
        jt_direccion.setBorder(null);
        jt_direccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_direccionFocusLost(evt);
            }
        });
        jt_direccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_direccionMouseClicked(evt);
            }
        });
        jt_direccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jt_direccionActionPerformed(evt);
            }
        });
        jt_direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_direccionKeyTyped(evt);
            }
        });
        jPanel1.add(jt_direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 170, -1));

        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("Telefono");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 190, -1));

        jt_telefono.setBackground(new java.awt.Color(84, 110, 122));
        jt_telefono.setForeground(new java.awt.Color(204, 204, 204));
        jt_telefono.setText("Ingresar Telefono");
        jt_telefono.setBorder(null);
        jt_telefono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_telefonoFocusLost(evt);
            }
        });
        jt_telefono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_telefonoMouseClicked(evt);
            }
        });
        jt_telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jt_telefonoActionPerformed(evt);
            }
        });
        jt_telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_telefonoKeyTyped(evt);
            }
        });
        jPanel1.add(jt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 170, -1));

        Celular1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        Celular1.setForeground(new java.awt.Color(255, 255, 255));
        Celular1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Celular1.setText("Correo Electronico (Email)");
        jPanel1.add(Celular1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 190, -1));

        jt_email.setBackground(new java.awt.Color(84, 110, 122));
        jt_email.setForeground(new java.awt.Color(204, 204, 204));
        jt_email.setText("Ingresar Email");
        jt_email.setBorder(null);
        jt_email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_emailFocusLost(evt);
            }
        });
        jt_email.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_emailMouseClicked(evt);
            }
        });
        jt_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jt_emailActionPerformed(evt);
            }
        });
        jt_email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_emailKeyTyped(evt);
            }
        });
        jPanel1.add(jt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 190, -1));

        Celular2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        Celular2.setForeground(new java.awt.Color(255, 255, 255));
        Celular2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Celular2.setText("Uso de CFDI");
        jPanel1.add(Celular2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 140, -1));

        cb_cfdi.setBackground(new java.awt.Color(84, 110, 122));
        cb_cfdi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar CFDI" }));
        cb_cfdi.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_cfdiItemStateChanged(evt);
            }
        });
        cb_cfdi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_cfdiActionPerformed(evt);
            }
        });
        jPanel1.add(cb_cfdi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 190, -1));

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 190, 10));

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 190, 10));

        jSeparator6.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 190, 10));

        jSeparator9.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 190, 10));

        jSeparator10.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 190, 10));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Total de registros ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 590, 100, 20));

        btn_Cancelar.setBackground(new java.awt.Color(211, 18, 18));
        btn_Cancelar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Cancelar.setText("Cancelar");
        btn_Cancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Cancelar.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_Cancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_CancelarMouseClicked(evt);
            }
        });
        btn_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 370, 110, 40));

        lb_Id.setForeground(new java.awt.Color(84, 110, 122));
        jPanel1.add(lb_Id, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 30, 20));

        lb_errorCFDI.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorCFDI.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorCFDI.setText("*");
        jPanel1.add(lb_errorCFDI, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 10, -1));

        lb_errorNombre.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorNombre.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorNombre.setText("*");
        jPanel1.add(lb_errorNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 10, -1));

        lb_errorRFC.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorRFC.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorRFC.setText("*");
        jPanel1.add(lb_errorRFC, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 10, -1));

        lb_errorDireccion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorDireccion.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorDireccion.setText("*");
        jPanel1.add(lb_errorDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 10, -1));

        lb_errorTelefono.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorTelefono.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorTelefono.setText("*");
        jPanel1.add(lb_errorTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 10, -1));

        lb_errorEmail.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorEmail.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorEmail.setText("*");
        jPanel1.add(lb_errorEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 10, -1));

        jLabel27.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("Buscar Cliente:");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 30, -1, -1));

        jt_Buscar.setBackground(new java.awt.Color(84, 110, 122));
        jt_Buscar.setForeground(new java.awt.Color(204, 204, 204));
        jt_Buscar.setText("Buscar Nombre");
        jt_Buscar.setBorder(null);
        jt_Buscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_BuscarFocusLost(evt);
            }
        });
        jt_Buscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_BuscarMouseClicked(evt);
            }
        });
        jt_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jt_BuscarActionPerformed(evt);
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
        jPanel1.add(jt_Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 30, 210, 20));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 50, 210, 10));

        pn_cerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pn_cerrarMouseEntered(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/closeWindow 24x24.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout pn_cerrarLayout = new javax.swing.GroupLayout(pn_cerrar);
        pn_cerrar.setLayout(pn_cerrarLayout);
        pn_cerrarLayout.setHorizontalGroup(
            pn_cerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_cerrarLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pn_cerrarLayout.setVerticalGroup(
            pn_cerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_cerrarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(pn_cerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 10, -1, 20));

        btn_Ingresar1.setBackground(new java.awt.Color(40, 180, 99));
        btn_Ingresar1.setForeground(new java.awt.Color(255, 255, 255));
        btn_Ingresar1.setText("Usar");
        btn_Ingresar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Ingresar1.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_Ingresar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_Ingresar1MouseClicked(evt);
            }
        });
        btn_Ingresar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Ingresar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Ingresar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 110, 40));

        jt_t_registros.setEditable(false);
        jt_t_registros.setBackground(new java.awt.Color(84, 110, 122));
        jt_t_registros.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jt_t_registros.setForeground(new java.awt.Color(255, 255, 255));
        jt_t_registros.setBorder(null);
        jPanel1.add(jt_t_registros, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 380, 20, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Total de registros ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 380, -1, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jt_ClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_ClientesMouseClicked
        seleccion = jt_Clientes.rowAtPoint(evt.getPoint());
        lb_Id.setText(String.valueOf(jt_Clientes.getValueAt(seleccion, 0)));
        jt_nombre.setText(String.valueOf(jt_Clientes.getValueAt(seleccion, 1)));
        jt_rfc.setText(String.valueOf(jt_Clientes.getValueAt(seleccion, 2)));
        jt_direccion.setText(String.valueOf(jt_Clientes.getValueAt(seleccion, 3)));
        jt_telefono.setText(String.valueOf(jt_Clientes.getValueAt(seleccion, 4)));
        jt_email.setText(String.valueOf(jt_Clientes.getValueAt(seleccion, 5)));

        cb_cfdi.getModel().setSelectedItem(jt_Clientes.getValueAt(seleccion, 6));

        // TODO add your handling code here:
    }//GEN-LAST:event_jt_ClientesMouseClicked

    private void jt_nombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_nombreFocusLost
        // TODO add your handling code here:
        if (jt_nombre.getText().trim().equals("")) {
            jt_nombre.setText("Ingresar Nombre");
            jt_nombre.setForeground(new Color(153, 153, 153));

            // TODO add your handling code here:
        }
    }//GEN-LAST:event_jt_nombreFocusLost

    private void jt_nombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_nombreMouseClicked
        // TODO add your handling code here:
        // t_control.setText("");
        if (!jt_nombre.getText().equals("Ingresar Nombre")) {

        } else {
            jt_nombre.setText("");
        }
    }//GEN-LAST:event_jt_nombreMouseClicked

    private void jt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_nombreActionPerformed
        // TODO add your handling code here:
        jt_nombre.setText("");
    }//GEN-LAST:event_jt_nombreActionPerformed

    private void jt_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_nombreKeyTyped
        ce.typedCharsAndSpaceAndDigits(evt, jt_nombre);
    }//GEN-LAST:event_jt_nombreKeyTyped

    private void jt_rfcFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_rfcFocusLost

        // TODO add your handling code here:
        if (jt_rfc.getText().trim().equals("")) {
            jt_rfc.setText("Ingresar RFC");
            jt_rfc.setForeground(new Color(153, 153, 153));

            // TODO add your handling code here:
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jt_rfcFocusLost

    private void jt_rfcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_rfcMouseClicked
        if (!jt_rfc.getText().equals("Ingresar RFC")) {

        } else {
            jt_rfc.setText("");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jt_rfcMouseClicked

    private void jt_rfcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_rfcActionPerformed
        jt_rfc.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_rfcActionPerformed

    private void jt_rfcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_rfcKeyTyped
        ce.typedCharsAndDigits(evt, jt_rfc);
    }//GEN-LAST:event_jt_rfcKeyTyped

    private void jt_direccionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_direccionFocusLost
        // TODO add your handling code here:
        if (jt_direccion.getText().trim().equals("")) {
            jt_direccion.setText("Ingresar Direccion Fiscal");
            jt_direccion.setForeground(new Color(153, 153, 153));

            // TODO add your handling code here:
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jt_direccionFocusLost

    private void jt_direccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_direccionMouseClicked
        if (!jt_direccion.getText().equals("Ingresar Direccion Fiscal")) {

        } else {
            jt_direccion.setText("");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jt_direccionMouseClicked

    private void jt_direccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_direccionActionPerformed
        jt_direccion.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_direccionActionPerformed

    private void jt_direccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_direccionKeyTyped
        ce.typedAddress(evt, jt_direccion);
    }//GEN-LAST:event_jt_direccionKeyTyped

    private void jt_telefonoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_telefonoFocusLost
        // TODO add your handling code here:
        if (jt_telefono.getText().trim().equals("")) {
            jt_telefono.setText("Ingresar Telefono");
            jt_telefono.setForeground(new Color(153, 153, 153));

            // TODO add your handling code here:
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jt_telefonoFocusLost

    private void jt_telefonoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_telefonoMouseClicked
        if (!jt_telefono.getText().equals("Ingresar Telefono")) {

        } else {
            jt_telefono.setText("");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jt_telefonoMouseClicked

    private void jt_telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_telefonoActionPerformed
        jt_telefono.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_telefonoActionPerformed

    private void jt_telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_telefonoKeyTyped
        ce.typedDigits(evt, jt_telefono);
    }//GEN-LAST:event_jt_telefonoKeyTyped

    private void jt_emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_emailFocusLost
        // TODO add your handling code here:
        if (jt_email.getText().trim().equals("")) {
            jt_email.setText("Ingresar Email");
            jt_email.setForeground(new Color(153, 153, 153));

            // TODO add your handling code here:
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jt_emailFocusLost

    private void jt_emailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_emailMouseClicked
        if (!jt_email.getText().equals("Ingresar Email")) {

        } else {
            jt_email.setText("");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jt_emailMouseClicked

    private void jt_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_emailActionPerformed
        jt_email.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_emailActionPerformed

    private void jt_emailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_emailKeyTyped
        ce.typedEmail(evt, jt_email);
    }//GEN-LAST:event_jt_emailKeyTyped

    private void cb_cfdiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_cfdiItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
//            this.idCfdi=cb_cfdi.getSelectedIndex();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_cb_cfdiItemStateChanged

    private void cb_cfdiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_cfdiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_cfdiActionPerformed

    private void btn_CancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_CancelarMouseClicked

    }//GEN-LAST:event_btn_CancelarMouseClicked

    private void btn_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CancelarActionPerformed
    dispose();
      
    }//GEN-LAST:event_btn_CancelarActionPerformed

    private void jt_BuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_BuscarFocusLost
        if (jt_Buscar.getText().trim().equals("")) {
            jt_Buscar.setText("Buscar Nombre");
            jt_Buscar.setForeground(new Color(153, 153, 153));

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jt_BuscarFocusLost

    private void jt_BuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_BuscarMouseClicked
        // TODO add your handling code here:

        jt_Buscar.setText("");
    }//GEN-LAST:event_jt_BuscarMouseClicked

    private void jt_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_BuscarActionPerformed
        jt_Buscar.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_BuscarActionPerformed

    private void jt_BuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_BuscarKeyReleased
        jt_Clientes.setModel(mc.tablaClientes(jt_Buscar));
        tamañoTabla();
    }//GEN-LAST:event_jt_BuscarKeyReleased

    private void jt_BuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_BuscarKeyTyped

        char tecla;
        tecla = evt.getKeyChar();
        //Convertir a letras mayusculas
        if (Character.isLetter(tecla)) {
            evt.setKeyChar(Character.toUpperCase(tecla));

        }
    }//GEN-LAST:event_jt_BuscarKeyTyped

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseEntered

    private void pn_cerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_cerrarMouseEntered
pn_cerrar.setBackground(Color.red);
        // TODO add your handling code here:
    }//GEN-LAST:event_pn_cerrarMouseEntered

    private void btn_Ingresar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Ingresar1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_Ingresar1MouseClicked

    private void btn_Ingresar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Ingresar1ActionPerformed

reserv.jt_nombre.setText(jt_nombre.getText());
dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_Ingresar1ActionPerformed

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
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pn_SeleccionarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pn_SeleccionarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pn_SeleccionarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pn_SeleccionarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pn_SeleccionarClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Celular1;
    private javax.swing.JLabel Celular2;
    private principal.MaterialButton btn_Cancelar;
    private principal.MaterialButton btn_Ingresar1;
    private javax.swing.JComboBox<String> cb_cfdi;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jt_Buscar;
    private javax.swing.JTable jt_Clientes;
    private javax.swing.JTextField jt_direccion;
    private javax.swing.JTextField jt_email;
    private javax.swing.JTextField jt_nombre;
    private javax.swing.JTextField jt_rfc;
    private javax.swing.JTextField jt_t_registros;
    private javax.swing.JTextField jt_telefono;
    private javax.swing.JLabel lb_Id;
    private javax.swing.JLabel lb_errorCFDI;
    private javax.swing.JLabel lb_errorDireccion;
    private javax.swing.JLabel lb_errorEmail;
    private javax.swing.JLabel lb_errorNombre;
    private javax.swing.JLabel lb_errorRFC;
    private javax.swing.JLabel lb_errorTelefono;
    private javax.swing.JPanel pn_cerrar;
    // End of variables declaration//GEN-END:variables
}
