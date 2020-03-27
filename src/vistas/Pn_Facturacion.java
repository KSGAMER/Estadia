/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controladores.ControladorCFDI;
import controladores.ControladorCategorias;
import controladores.ControladorClientes;
import controladores.ControladorEmail;
import controladores.ControladorEnvioFacturacion;
import controladores.ControladorEscritura;
import controladores.ControladorFormularioTab;
import controladores.ControladorReservaciones;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import ds.desktop.notify.DesktopNotify;
import java.awt.Frame;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.table.TableColumnModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.mail.AuthenticationFailedException;
import objetos.ObjetoCFDI;
import objetos.ObjetoCliente;


/*
    NOTA: SE DEBE CONFIGURAR GMAIL PARA PERMITIR EL ACCESO A APLICACIONES MENOS SEGURAS
    PASOS:
   (1)ICONO DE GMAIL
   (2)CLICK EN "MI CUENTA"
   (3)CLICK EN "INICIO DE SESIÓN Y SEGURIDAD"
   (4)ACTIVAR "PERMITIR EL ACCESO DE APLICACIONES MENOS SEGURAS"
 */
public class Pn_Facturacion extends javax.swing.JPanel {

    private ControladorReservaciones cr = new ControladorReservaciones();
    private ControladorClientes mc = new ControladorClientes();
    private ControladorCFDI cf = new ControladorCFDI();
    //controlador necesario para extraer datos de las tablas reservaciones/cobro reservaciones
    private ControladorEnvioFacturacion cenf = new ControladorEnvioFacturacion();
    private ControladorEmail cemail = new ControladorEmail();
    private ControladorCategorias ccat = new ControladorCategorias();
    private ControladorEscritura ce = new ControladorEscritura();
    DefaultTableModel NewTable = new DefaultTableModel();
    private ControladorFormularioTab cft = new ControladorFormularioTab();
    private String correoRemitente, passwordRemitente, correoReceptor, asunto, mensaje;

    //necesario para dar formato al jdateChooser
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private boolean flag = false;
  //NECESARIO PARA EL USO DE LA NOTIFICACION DINAMICA DE BOTON ELIMINAR ()
    Frame Principal;
//FIN
    /**
     * Creates new form Pn_NuevaCategoria
     */
    public Pn_Facturacion() {
        initComponents();
        mouseOnClickGananciasFechas();
        rangoFechas();
        cargarDatosPorFechaCobro();
        datosIniciales();
        RowHeaderApariencia();
        RowApariencia();
        cTabla();
        mc.tablaClientes();
        cf.tablaCFDI();
        //para ajustar el tecto al tamaño del jtextarea
        jta_observaciones.setLineWrap(true);
        tamañoTabla();

    }

    private void tamañoTabla() {
        TableColumnModel columnModel = jt_Facturacion.getColumnModel();

    }

    private void cTabla() {
        jt_Facturacion.setModel(cenf.tablaEnvioFacturacion());
        jt_t_registros.setText(String.valueOf(jt_Facturacion.getRowCount()));
    }

    private void RowApariencia() {

        jt_Facturacion.setFocusable(false);
        //espacio entre comulnas
        jt_Facturacion.setIntercellSpacing(new Dimension(0, 1));
        //altura de columnas 
        jt_Facturacion.setRowHeight(25);
        //margen entre filas
        jt_Facturacion.setRowMargin(0);
        //sin lineas verticles
        jt_Facturacion.setShowVerticalLines(false);
        jt_Facturacion.setSelectionBackground(new Color(97, 212, 195));

    }

    private void RowHeaderApariencia() {
        jt_Facturacion.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 14));
        jt_Facturacion.getTableHeader().setOpaque(false);
        jt_Facturacion.getTableHeader().setBackground(Color.BLACK);
        jt_Facturacion.getTableHeader().setForeground(new Color(255, 255, 255));

    }

    private void datosIniciales() {
        lb_Destinatario.setText(cemail.getEmailDestinatario());
        jt_Asunto.setText("Datos de Facturación");
        lb_RazonSocial.setText("Razón Social");
        lb_RFC.setText("RFC");
        lb_Direccion.setText("Dirección");
        jt_telefono.setText("Telefono");
        jt_email.setText("Email");
        lb_CFDI.setText("CFDI");
        lb_monto.setText("Monto");
        lb_TipoPago.setText("Tipo de Pago");
        //COLORES INICIALES
        lb_errorEmail.setForeground(new Color(255, 255, 255));
        lb_errorTelefono.setForeground(new Color(255, 255, 255));
        lb_errorAsunto.setForeground(new Color(255, 255, 255));
    }

    private Boolean validarEscritura() {
        Boolean val = true;
        //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(jt_email.getText().equals("Email")) && !(jt_email.getText().equals(""))) {
            lb_errorEmail.setForeground(new Color(255, 255, 255));
        } else {
            lb_errorEmail.setForeground(Color.RED);
            val = false;
        }
        //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(jt_telefono.getText().equals("Telefono")) && !(jt_telefono.getText().equals(""))) {
            lb_errorTelefono.setForeground(new Color(255, 255, 255));
        } else {
            lb_errorTelefono.setForeground(Color.RED);
            val = false;
        }
        //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(jt_Asunto.getText().equals(""))) {
            lb_errorAsunto.setForeground(new Color(255, 255, 255));
        } else {
            lb_errorAsunto.setForeground(Color.RED);
            val = false;
        }

        return val;
    }

    private void cargarDatosPorFechaCobro() {

        jt_Facturacion.setModel(cenf.RangoFechaCobroFacturacion(dateFormat.format(jd_fechaCobroInicial.getDate()), dateFormat.format(jd_fechaCobroFinal.getDate())));
        jt_t_registros.setText(String.valueOf(cenf.selectEnvioFacturacion().size()));
    }

    public void mouseOnClickGananciasFechas() {
        jd_fechaCobroInicial.getComponent(0).addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                flag = true;
            }
        });
        jd_fechaCobroInicial.getComponent(1).addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                flag = true;
            }
        });
        jd_fechaCobroFinal.getComponent(0).addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                flag = true;
            }
        });
        jd_fechaCobroFinal.getComponent(1).addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                flag = true;
            }
        });
    }

    public void rangoFechas() {
        Calendar fecha = Calendar.getInstance();
        jd_fechaCobroFinal.setDate(fecha.getTime());
        fecha.add(Calendar.DATE, -14);
        jd_fechaCobroInicial.setDate(fecha.getTime());
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
        jb_limpiarCampos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_Facturacion = new javax.swing.JTable();
        btn_Ingresar = new principal.MaterialButton();
        jLabel4 = new javax.swing.JLabel();
        jt_t_registros = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        lb_Configs = new javax.swing.JLabel();
        jt_Buscar = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        lb_Destinatario = new javax.swing.JLabel();
        jt_Asunto = new javax.swing.JTextField();
        lb_RazonSocial = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lb_RFC = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lb_Direccion = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jt_telefono = new javax.swing.JTextField();
        Celular2 = new javax.swing.JLabel();
        jt_email = new javax.swing.JTextField();
        cfdi = new javax.swing.JLabel();
        lb_CFDI = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lb_monto = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        cfdi2 = new javax.swing.JLabel();
        cfdi1 = new javax.swing.JLabel();
        lb_TipoPago = new javax.swing.JLabel();
        Celular1 = new javax.swing.JLabel();
        jta_observaciones = new javax.swing.JTextArea();
        lb_errorEmail = new javax.swing.JLabel();
        lb_errorTelefono = new javax.swing.JLabel();
        lb_errorAsunto = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jd_fechaCobroFinal = new com.toedter.calendar.JDateChooser();
        jd_fechaCobroInicial = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(84, 110, 122));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel1.add(jb_limpiarCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 230, 40, -1));

        jt_Facturacion.setModel(new javax.swing.table.DefaultTableModel(
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
        jt_Facturacion.getTableHeader().setResizingAllowed(false);
        jt_Facturacion.getTableHeader().setReorderingAllowed(false);
        jt_Facturacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_FacturacionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jt_FacturacionMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jt_Facturacion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 1000, 110));

        btn_Ingresar.setBackground(new java.awt.Color(40, 180, 99));
        btn_Ingresar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Ingresar.setText("Enviar ");
        btn_Ingresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Ingresar.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_Ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_IngresarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 270, 250, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Total de registros ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 230, 100, 20));

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
        jPanel1.add(jt_t_registros, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 230, 30, 20));

        jPanel2.setBackground(new java.awt.Color(84, 110, 122));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Módulo para envio de Correos");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Inicio > Facturación > Nueva Factura");

        lb_Configs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/configuracion-24x24.png"))); // NOI18N
        lb_Configs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lb_Configs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_ConfigsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1020, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(lb_Configs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(75, 75, 75))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel5))
                    .addComponent(lb_Configs))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 50));

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
        jPanel1.add(jt_Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 70, 150, 20));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(36, 47, 65));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mensaje Nuevo");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, -1));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(170, 136, 119));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Destinatario");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 33, 89, -1));

        jLabel20.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(170, 136, 119));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Asunto");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 89, -1));

        jSeparator4.setBackground(new java.awt.Color(236, 239, 241));
        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 500, 10));

        jSeparator11.setBackground(new java.awt.Color(236, 239, 241));
        jSeparator11.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 52, 500, 10));

        lb_Destinatario.setBackground(new java.awt.Color(255, 255, 255));
        lb_Destinatario.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        lb_Destinatario.setForeground(new java.awt.Color(153, 153, 153));
        lb_Destinatario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_Destinatario.setText("Destinatario");
        jPanel3.add(lb_Destinatario, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 34, 400, -1));

        jt_Asunto.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jt_Asunto.setForeground(new java.awt.Color(153, 153, 153));
        jt_Asunto.setText("Datos de Facturación");
        jt_Asunto.setBorder(null);
        jt_Asunto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jt_AsuntoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_AsuntoFocusLost(evt);
            }
        });
        jt_Asunto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_AsuntoMouseClicked(evt);
            }
        });
        jt_Asunto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_AsuntoKeyTyped(evt);
            }
        });
        jPanel3.add(jt_Asunto, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 240, -1));

        lb_RazonSocial.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        lb_RazonSocial.setForeground(new java.awt.Color(153, 153, 153));
        lb_RazonSocial.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_RazonSocial.setText("Razón Social");
        jPanel3.add(lb_RazonSocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 190, -1));

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Registro Federal de Controbuyentes (RFC)");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 250, -1));

        lb_RFC.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        lb_RFC.setForeground(new java.awt.Color(153, 153, 153));
        lb_RFC.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_RFC.setText("RFC");
        jPanel3.add(lb_RFC, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 190, -1));

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Direccion Fiscal ");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 190, -1));

        lb_Direccion.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        lb_Direccion.setForeground(new java.awt.Color(153, 153, 153));
        lb_Direccion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_Direccion.setText("Dirección");
        jPanel3.add(lb_Direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 190, -1));

        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("Telefono");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 50, -1));

        jt_telefono.setForeground(new java.awt.Color(153, 153, 153));
        jt_telefono.setText("Telefono");
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
        jPanel3.add(jt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 170, -1));

        Celular2.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        Celular2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Celular2.setText("Correo Electronico (Email)");
        jPanel3.add(Celular2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 140, -1));

        jt_email.setForeground(new java.awt.Color(153, 153, 153));
        jt_email.setText("Email");
        jt_email.setBorder(null);
        jt_email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jt_emailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_emailFocusLost(evt);
            }
        });
        jt_email.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_emailMouseClicked(evt);
            }
        });
        jt_email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_emailKeyTyped(evt);
            }
        });
        jPanel3.add(jt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 190, -1));

        cfdi.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        cfdi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cfdi.setText("Uso de CFDI");
        jPanel3.add(cfdi, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 140, -1));

        lb_CFDI.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        lb_CFDI.setForeground(new java.awt.Color(153, 153, 153));
        lb_CFDI.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_CFDI.setText("CFDI");
        jPanel3.add(lb_CFDI, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 190, -1));

        jLabel21.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("Nombre y/o Razon Social ");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 190, -1));

        lb_monto.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        lb_monto.setForeground(new java.awt.Color(153, 153, 153));
        lb_monto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_monto.setText("Monto");
        jPanel3.add(lb_monto, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 70, 20));

        jLabel29.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("$");
        jPanel3.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 10, -1));

        cfdi2.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        cfdi2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cfdi2.setText("Monto");
        jPanel3.add(cfdi2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 40, -1));

        cfdi1.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        cfdi1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cfdi1.setText("T. Pago ");
        jPanel3.add(cfdi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, -1, -1));

        lb_TipoPago.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        lb_TipoPago.setForeground(new java.awt.Color(153, 153, 153));
        lb_TipoPago.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_TipoPago.setText("Tipo de pago");
        jPanel3.add(lb_TipoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, 90, -1));

        Celular1.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        Celular1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Celular1.setText("Observaciones :");
        jPanel3.add(Celular1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 190, -1));

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
        jPanel3.add(jta_observaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 260, 80));

        lb_errorEmail.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorEmail.setForeground(new java.awt.Color(255, 255, 255));
        lb_errorEmail.setText("*");
        jPanel3.add(lb_errorEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 10, -1));

        lb_errorTelefono.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorTelefono.setForeground(new java.awt.Color(255, 255, 255));
        lb_errorTelefono.setText("*");
        jPanel3.add(lb_errorTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 10, -1));

        lb_errorAsunto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorAsunto.setForeground(new java.awt.Color(255, 255, 255));
        lb_errorAsunto.setText("*");
        jPanel3.add(lb_errorAsunto, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 10, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 530, 330));

        jLabel19.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Fecha final");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 100, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("NINGUN CAMPO \"EDITABLE\" PODRÁ ESTAR VACIO");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 490, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Recomendaciones");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 360, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Debe seleccionar la fila del cliente al cual se desea generar una factura");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 410, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("El correo del destinatario no es editable es este módulo");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 430, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("El asunto puede dejarse por default o editarse en preferencia");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 450, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("El campo de telefono , email del cliente y observaciones son editables ");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 470, -1, -1));

        jd_fechaCobroFinal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jd_fechaCobroFinalPropertyChange(evt);
            }
        });
        jPanel1.add(jd_fechaCobroFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, 190, -1));

        jd_fechaCobroInicial.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jd_fechaCobroInicialPropertyChange(evt);
            }
        });
        jPanel1.add(jd_fechaCobroInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 190, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/campo-buscar.png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, -1, 40));

        jLabel22.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("LImpia \"Nuevo Mensaje\"");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 230, 190, 30));

        jLabel23.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("Fecha inicial ");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 100, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1066, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jt_FacturacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_FacturacionMouseClicked
        int seleccion = jt_Facturacion.rowAtPoint(evt.getPoint());
        for (ObjetoCliente cl : mc.selectCliente()) {
            for (ObjetoCFDI cfd : cf.selectCFDI()) {
                if (cl.getNombre().equals(String.valueOf(jt_Facturacion.getValueAt(seleccion, 0)))) {

                    if (cfd.getIdCFDI() == cl.getIdCFDI()) {
                        lb_RazonSocial.setText(cl.getNombre());
                        lb_RFC.setText(cl.getRfc());
                        lb_Direccion.setText(cl.getDireccion());
                        jt_telefono.setText(cl.getTelefono());
                        jt_email.setText(cl.getEmail());
                        lb_CFDI.setText(cfd.getNombre());
                        lb_monto.setText(String.valueOf(jt_Facturacion.getValueAt(seleccion, 6)));
                        lb_TipoPago.setText(String.valueOf(jt_Facturacion.getValueAt(seleccion, 2)));
                    }

                }
            }

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jt_FacturacionMouseClicked

    private void btn_IngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_IngresarActionPerformed
        /*
    NOTA: SE DEBE CONFIGURAR GMAIL PARA PERMITIR EL ACCESO A APLICACIONES MENOS SEGURAS
    PASOS:
   (1)ICONO DE GMAIL
   (2)CLICK EN "MI CUENTA"
   (3)CLICK EN "INICIO DE SESIÓN Y SEGURIDAD"
   (4)ACTIVAR "PERMITIR EL ACCESO DE APLICACIONES MENOS SEGURAS"
         */

        try {
            if (!validarEscritura() == true) {

                DesktopNotify.showDesktopMessage("Error", "DEBE SELECCIONAR UN CLIENTE EN LA TABLA"
                        + " Y NO DEBE DEJAR NINGUN CAMPO VACIO (ASUNTO/EMAIL/TELEFONO)", DesktopNotify.ERROR);

            } else {
                //PROPIEDADES DE LA CONEXION AL CORREO ELECTRONICO
                Properties props = new Properties();
                props.setProperty("mail.smtp.host", "smtp.gmail.com");  //TIPO DE CUENTA (gmail, hotmail, etc)
                props.setProperty("mail.smtp.starttls.enable", "true"); //INDICA SI SE OCUPA TLS debido a que es gmail
                props.setProperty("mail.smtp.port", "587");  //PUERTO DE SALIDA QUE USA GMAIL
                props.setProperty("mail.smtp.auth", "true"); //AUTENTIFICACION DIRECTA CON EL SERVIDOR DE GMAIL4
                //LA SIGUIENTE LINEA CORRIGE EL ERROR DE VALIDACION POR SSL como se muestra abajo             
                props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//ERROR//
                /*   javax.net.ssl.SSLHandshakeException: sun.security.validator.ValidatorException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target
    at Test.main(Test.java:43)
Caused by: javax.mail.MessagingException: Could not convert socket to TLS;
  nested exception is:
    javax.net.ssl.SSLHandshakeException: sun.security.validator.ValidatorException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target
    at com.sun.mail.smtp.SMTPTransport.startTLS(SMTPTransport.java:1907)
    at com.sun.mail.smtp.SMTPTransport.protocolConnect(SMTPTransport.java:666)
    at javax.mail.Service.connect(Service.java:317)
    at javax.mail.Service.connect(Service.java:176)
    at javax.mail.Service.connect(Service.java:125)
    at javax.mail.Transport.send0(Transport.java:194)
    at javax.mail.Transport.send(Transport.java:124)
    at Test.main(Test.java:38)
Caused by: javax.net.ssl.SSLHandshakeException: sun.security.validator.ValidatorException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target
                 */

                //sesion para preparar las propiedades
                Session session = Session.getDefaultInstance(props);
//FIN DE LA PREPARACION DE LA CONEXION 

//DATOS DE CORREO QUE ENVIA
                correoRemitente = cemail.getEmailRemitente();//AQUI VA EL CORREO DE QUIEN ENVIA
                passwordRemitente = cemail.getPassword();//AQUI VA LA CONTRASEÑA DEL CORREO DE QUIEN ENVIA

//DATOS DE CORREO QUE RECIBE            
                correoReceptor = lb_Destinatario.getText(); //aqui va el correo de quien recibira el mensaje

//CONTENIDO DEL CORREO
                asunto = jt_Asunto.getText();
                mensaje
                        = "Razón Social: " + lb_RazonSocial.getText() + "\n"
                        + "RFC: " + lb_RFC.getText() + "\n"
                        + "Dirección: " + lb_Direccion.getText() + "\n"
                        + "Telefono: " + jt_telefono.getText() + "\n"
                        + "Email: " + jt_email.getText() + "\n"
                        + "Uso de CFDI: " + lb_CFDI.getText() + "\n"
                        + "Monto: $ " + lb_monto.getText() + "\n"
                        + "Tipo de Pago: " + lb_TipoPago.getText() + "\n"
                        + "Observaciones: " + jta_observaciones.getText();

//CONTRUCCION DEL MENSAJE
                MimeMessage message = new MimeMessage(session);  //al mensaje se le agrega el contenido y las propiedades de conexion
                message.setFrom(new InternetAddress(correoRemitente)); //se asigna el correo de quien envia 

                message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor)); //se usa RecipientType.to PARA INDICAR QUE ES EL CORREO PRINCIPAL
                //TAMBIEN EXISTE LA OPCION DE RecipientType.CC para enviar una copia a otros correos
                //o TAMBIEN EXISTE LA OPCION DE RecipientType.BCC para enviar una copia soloa una persona especifica
                message.setSubject(asunto);
                message.setText(mensaje);

//SE CREA LA VARIABLE DE TIPO TRANSPORT PARA ENVIAR EL CORREO ELECTRONICO
                Transport t = session.getTransport("smtp");
                //se crea la conexion para ingresar los datos del correo remitente
                t.connect(correoRemitente, passwordRemitente);
                //se envia todo el mensaje 
                t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
                //se cierra la conexion
                t.close();

                DesktopNotify.showDesktopMessage("Exito", "Datos de Facturación enviados correctamente al correo " + correoReceptor, DesktopNotify.SUCCESS);

                datosIniciales();
            }
        } catch (AuthenticationFailedException ex) {
            // Logger.getLogger(Pn_Facturacion.class.getName()).log(Level.SEVERE, null, ex);
            DesktopNotify.showDesktopMessage("Error", "Error al acceder al correo electronico: " + correoRemitente + ""
                    + " favor de revisar su conexión a internet", DesktopNotify.ERROR);

        } catch (MessagingException ex) {
            //  Logger.getLogger(Pn_Facturacion.class.getName()).log(Level.SEVERE, null, ex);
            DesktopNotify.showDesktopMessage("Error", "Error al enviar el mensaje al correo electronico: " + correoReceptor, DesktopNotify.ERROR);

        }

    }//GEN-LAST:event_btn_IngresarActionPerformed

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

    private void jt_BuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_BuscarKeyReleased
        this.jt_Facturacion.setModel(cenf.tablaEnvioFacturacion(jt_Buscar));
        tamañoTabla();
    }//GEN-LAST:event_jt_BuscarKeyReleased

    private void jt_BuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_BuscarFocusGained
        cft.formFocusGain(jt_Buscar);
    }//GEN-LAST:event_jt_BuscarFocusGained

    private void jt_telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_telefonoKeyTyped
        ce.typedDigits(evt, jt_telefono);
    }//GEN-LAST:event_jt_telefonoKeyTyped

    private void jt_telefonoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_telefonoMouseClicked
        cft.formFocusGain(jt_telefono);
    }//GEN-LAST:event_jt_telefonoMouseClicked

    private void jt_telefonoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_telefonoFocusLost
        cft.formFocusLostJTextField(jt_telefono, "Ingresar Telefono");
    }//GEN-LAST:event_jt_telefonoFocusLost

    private void jt_telefonoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_telefonoFocusGained
        cft.formFocusGain(jt_telefono);
    }//GEN-LAST:event_jt_telefonoFocusGained

    private void jta_observacionesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jta_observacionesFocusGained
        cft.formFocusGainJTextArea(jta_observaciones);
    }//GEN-LAST:event_jta_observacionesFocusGained

    private void jta_observacionesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jta_observacionesFocusLost
        cft.formFocusLostJTextArea(jta_observaciones, "Ingresar Observaciones");
    }//GEN-LAST:event_jta_observacionesFocusLost

    private void jta_observacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jta_observacionesMouseClicked
        cft.formFocusGainJTextArea(jta_observaciones);
    }//GEN-LAST:event_jta_observacionesMouseClicked

    private void jta_observacionesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jta_observacionesKeyPressed
        cft.formTab(evt, jt_Buscar);
    }//GEN-LAST:event_jta_observacionesKeyPressed

    private void jt_emailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_emailKeyTyped
        ce.typedEmail(evt, jt_email);
    }//GEN-LAST:event_jt_emailKeyTyped

    private void jt_emailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_emailMouseClicked
        cft.formFocusGain(jt_email);
    }//GEN-LAST:event_jt_emailMouseClicked

    private void jt_emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_emailFocusLost
        cft.formFocusLostJTextField(jt_email, "Ingresar Email");
    }//GEN-LAST:event_jt_emailFocusLost

    private void jt_emailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_emailFocusGained
        cft.formFocusGain(jt_email);
    }//GEN-LAST:event_jt_emailFocusGained

    private void jt_AsuntoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_AsuntoFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_AsuntoFocusGained

    private void jt_AsuntoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_AsuntoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_AsuntoFocusLost

    private void jt_AsuntoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_AsuntoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_AsuntoMouseClicked

    private void jt_AsuntoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_AsuntoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_AsuntoKeyTyped

    private void jt_telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_telefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_telefonoActionPerformed

    private void jt_FacturacionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_FacturacionMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_FacturacionMouseEntered

    private void jd_fechaCobroFinalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jd_fechaCobroFinalPropertyChange

        if (flag == true) {
            cargarDatosPorFechaCobro();
        }//
        // cTabla();

        // TODO add your handling code here:
    }//GEN-LAST:event_jd_fechaCobroFinalPropertyChange

    private void jd_fechaCobroInicialPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jd_fechaCobroInicialPropertyChange
        if (flag == true) {
            cargarDatosPorFechaCobro();
        }
        //   
    }//GEN-LAST:event_jd_fechaCobroInicialPropertyChange

    private void jb_limpiarCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_limpiarCamposActionPerformed
        datosIniciales();        // TODO add your handling code here:
    }//GEN-LAST:event_jb_limpiarCamposActionPerformed

    private void lb_ConfigsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_ConfigsMouseClicked
        Pn_EmailConfiguracion ale = new Pn_EmailConfiguracion(Principal, true);
        ale.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_ConfigsMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Celular1;
    private javax.swing.JLabel Celular2;
    private principal.MaterialButton btn_Ingresar;
    private javax.swing.JLabel cfdi;
    private javax.swing.JLabel cfdi1;
    private javax.swing.JLabel cfdi2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JButton jb_limpiarCampos;
    private com.toedter.calendar.JDateChooser jd_fechaCobroFinal;
    private com.toedter.calendar.JDateChooser jd_fechaCobroInicial;
    private javax.swing.JTextField jt_Asunto;
    private javax.swing.JTextField jt_Buscar;
    private javax.swing.JTable jt_Facturacion;
    private javax.swing.JTextField jt_email;
    private javax.swing.JTextField jt_t_registros;
    private javax.swing.JTextField jt_telefono;
    private javax.swing.JTextArea jta_observaciones;
    public static javax.swing.JLabel lb_CFDI;
    private javax.swing.JLabel lb_Configs;
    public static javax.swing.JLabel lb_Destinatario;
    public static javax.swing.JLabel lb_Direccion;
    public static javax.swing.JLabel lb_RFC;
    public static javax.swing.JLabel lb_RazonSocial;
    public static javax.swing.JLabel lb_TipoPago;
    private javax.swing.JLabel lb_errorAsunto;
    private javax.swing.JLabel lb_errorEmail;
    private javax.swing.JLabel lb_errorTelefono;
    public static javax.swing.JLabel lb_monto;
    // End of variables declaration//GEN-END:variables
}
