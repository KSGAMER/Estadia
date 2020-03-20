package vistas;

import controladores.*;
import ds.desktop.notify.DesktopNotify;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import objetos.ObjetoTipoPago;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fenix
 */
public class Pn_CobrarReservacion extends javax.swing.JFrame {
//necesario para funciones de este modulo
    ControladorClientes mc = new ControladorClientes();
     private ControladorReservaciones cr = new ControladorReservaciones();
    ControladorCFDI cf = new ControladorCFDI();
    ControladorEscritura ce = new ControladorEscritura();
    private ControladorFormularioTab cft = new ControladorFormularioTab();
    Pn_SeleccionarClientesCobro selectCC = new Pn_SeleccionarClientesCobro();
    ControladorTipoPagos ct = new ControladorTipoPagos();
    ControladorCobros cco = new ControladorCobros();
    ControladorHabitaciones ch  = new ControladorHabitaciones();
    DefaultTableModel NewTable;
    private int validador;
//FIN
    /**
     * Creates new form Pn_SeleccionClientes
     */
    public Pn_CobrarReservacion() {
        setPantalla();
        initComponents();
          //INICIA LOS VALORES DEL FORMULARIO A SU VALOR ORIGINAL
        datosIniciales();
        //FIN
         //asigna la imagen de icono de la aplicacion que se muestra en la barra de tareas
        setIconSystem();
        //fin
        //ajusta la aplicacion al centro de la pantalla
        centrarPantalla();
        //fin
       
        cf.tablaCFDI();
        ct.tablaTipoPagos();
        ch.tablaHabitaciones();
        //FIN
       
      //carga los datos del tipo de pago en el combo box
        cargarTiposdePago();
        //fin
        //bloquea componentes no necesarios en el formulario
        bloquearComponentes();
        //fin
        //oculta elementos no requeridos en la app , a menos que se manden a visualizar
        ocultarElementosFacturas();
//fin
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

    private void cargarTiposdePago() {
        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        cb.addElement("Seleccionar Tipo de Pago");
        for (ObjetoTipoPago campos : ct.selectTipoPago()) {
            cb.addElement(campos.getNombre());

        }
        cb_TipoPago.setModel(cb);

    }


    /*public void cargarTabla() {
        //DESACTIVAR LA EDICION DE LAS CELDAS
        DefaultTableModel NewTable = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        ArrayList<Object> columna = new ArrayList<Object>();
        columna.add("#");
        columna.add("Razón Social");
        columna.add("RFC");
        columna.add("Dirección");
        columna.add("Telefono");
        columna.add("Email");
        columna.add("CFDI");

        for (Object col : columna) {
            NewTable.addColumn(col);

        }

        this.jt_Clientes.setModel(NewTable);

        //se recorre el aarray list que tiene los clientes, se guardan en un objeto llamado filas 
        //para porteriormente guardarse como una fila completa y al terminar el for se añade al modelo de la tabla ya 
        Object[] fila = new Object[jt_Clientes.getColumnCount()];
        for (int i = 0; i < mc.selectCliente().size(); i++) {

            fila[0] = mc.selectCliente().get(i).getIdCliente();
            fila[1] = mc.selectCliente().get(i).getNombre();
            fila[2] = mc.selectCliente().get(i).getRfc();
            fila[3] = mc.selectCliente().get(i).getDireccion();
            fila[4] = mc.selectCliente().get(i).getTelefono();
            fila[5] = mc.selectCliente().get(i).getEmail();
            for (int j = 0; j < cf.selectCFDI().size(); j++) {
                if(cf.selectCFDI().get(j).getIdCFDI().equals(mc.selectCliente().get(i).getIdCFDI())) {
                    fila[6] = cf.selectCFDI().get(j).getNombre();
                }
            }
            NewTable.addRow(fila);
        };

        this.jt_Clientes.setModel(NewTable);
    }*/
 

    private void datosIniciales() {

        lb_errorCFDI.setText("*");

        //colores
        lb_errorCFDI.setForeground(new Color(84, 110, 122));

        cb_TipoPago.setSelectedIndex(0);
    }

    private void bloquearComponentes() {
        btn_SeleccionarClientes.setEnabled(false);
        jt_email.setEnabled(false);
    }

    private void desbloquearComponentes() {
        btn_SeleccionarClientes.setEnabled(true);
        jt_email.setEnabled(true);
    }

    private void mostrarElementosFacturas() {
        pn_showFacturas.setVisible(true);
        lb_NombreRazonSocial.setVisible(true);
        lb_NombreRFC.setVisible(true);
        lb_NombreEmail.setVisible(true);
        btn_SeleccionarClientes.setVisible(true);
        jsep_RazonSocial.setVisible(true);
        jsep_RFC.setVisible(true);
        jsep_Email.setVisible(true);
    }

    private void ocultarElementosFacturas() {
        pn_showFacturas.setVisible(false);
        lb_NombreRazonSocial.setVisible(false);
        lb_NombreRFC.setVisible(false);
        lb_NombreEmail.setVisible(false);
        btn_SeleccionarClientes.setVisible(false);
        jsep_RazonSocial.setVisible(false);
        jsep_RFC.setVisible(false);
        jsep_Email.setVisible(false);
    }

    private void ReposicionarBotones() {
        btn_Cobrar.setLocation(680, 280);
        btn_Salir.setLocation(680, 330);
    }

    private void PosicionOriginalBotones() {
        btn_Cobrar.setLocation(680, 420);
        btn_Salir.setLocation(680, 470);
    }

    private Boolean validarEscritura() {
        Boolean val = true;
        return val;
    }
/*
    private Boolean validarSeleccion() {
        Boolean val = true;
        if (!(cb_TipoPago.getSelectedIndex() == 0)) {

            lb_errorCFDI.setForeground(new Color(84, 110, 122));
        } else {

            lb_errorCFDI.setForeground(Color.RED);

            val = false;
        }
        return val;

    }*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        btn_Cobrar = new principal.MaterialButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pn_cerrar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lb_errorCFDI = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        btn_Salir = new principal.MaterialButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        lb_FolioReservaciones = new javax.swing.JLabel();
        cb_TipoPago = new javax.swing.JComboBox<>();
        Celular2 = new javax.swing.JLabel();
        jt_Monto = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        lb_CostoCalculado = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        pn_showFacturas = new javax.swing.JPanel();
        lb_NombreRazonSocial = new javax.swing.JLabel();
        lb_NombreRFC = new javax.swing.JLabel();
        lb_NombreEmail = new javax.swing.JLabel();
        jsep_Email = new javax.swing.JSeparator();
        jt_email = new javax.swing.JTextField();
        jsep_RFC = new javax.swing.JSeparator();
        lb_rfc = new javax.swing.JLabel();
        lb_razonSocial = new javax.swing.JLabel();
        jsep_RazonSocial = new javax.swing.JSeparator();
        btn_SeleccionarClientes = new principal.MaterialButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jch_facturacion = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        lb_nombreCliente = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lb_TipoHabitacion = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        lb_PrecioHabitacion = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lb_NombreHabitacion = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        lb_FechaSalida = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jPanel11 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lb_FechaIngreso = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(84, 110, 122));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(204, 204, 204));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 249, 190, -1));

        btn_Cobrar.setBackground(new java.awt.Color(40, 180, 99));
        btn_Cobrar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Cobrar.setText("Terminar Reservación");
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
        jPanel1.add(btn_Cobrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 420, 310, 40));

        jPanel2.setBackground(new java.awt.Color(84, 110, 122));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Proceso de Check Out");

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
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );
        pn_cerrarLayout.setVerticalGroup(
            pn_cerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_cerrarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 807, Short.MAX_VALUE)
                .addComponent(pn_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pn_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 50));

        lb_errorCFDI.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorCFDI.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorCFDI.setText("*");
        jPanel1.add(lb_errorCFDI, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 419, 10, -1));

        jLabel26.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 329, 30, 20));

        btn_Salir.setBackground(new java.awt.Color(211, 18, 18));
        btn_Salir.setForeground(new java.awt.Color(255, 255, 255));
        btn_Salir.setText("SALIR");
        btn_Salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Salir.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_Salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_SalirMouseClicked(evt);
            }
        });
        btn_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalirActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 470, 310, 40));

        jPanel3.setBackground(new java.awt.Color(233, 235, 238));
        jPanel3.setToolTipText("");
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(84, 110, 122));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Datos Generales");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 521, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, -1));

        jSeparator4.setToolTipText("");
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 30, 10));

        lb_FolioReservaciones.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lb_FolioReservaciones.setForeground(new java.awt.Color(153, 153, 153));
        lb_FolioReservaciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel3.add(lb_FolioReservaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 30, 20));

        cb_TipoPago.setBackground(new java.awt.Color(233, 235, 238));
        cb_TipoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Tipo de Pago" }));
        cb_TipoPago.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_TipoPagoItemStateChanged(evt);
            }
        });
        cb_TipoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_TipoPagoActionPerformed(evt);
            }
        });
        jPanel3.add(cb_TipoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, 150, -1));

        Celular2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        Celular2.setForeground(new java.awt.Color(255, 255, 255));
        Celular2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Celular2.setText("Forma de pago");
        jPanel3.add(Celular2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 100, -1));

        jt_Monto.setBackground(new java.awt.Color(233, 235, 238));
        jt_Monto.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jt_Monto.setForeground(new java.awt.Color(153, 153, 153));
        jt_Monto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jt_Monto.setText("0.0");
        jt_Monto.setToolTipText("");
        jt_Monto.setBorder(null);
        jt_Monto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jt_MontoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_MontoFocusLost(evt);
            }
        });
        jt_Monto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_MontoMouseClicked(evt);
            }
        });
        jt_Monto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_MontoKeyTyped(evt);
            }
        });
        jPanel3.add(jt_Monto, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 100, 15));

        jLabel30.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("$");
        jPanel3.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 10, -1));

        jLabel28.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("1.-");
        jPanel3.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 20, -1));

        jLabel24.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(153, 153, 153));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("0.0");
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 230, 90, 20));

        jLabel31.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Costo Calculado");
        jPanel3.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 100, -1));

        jLabel32.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("Monto a cobrar ");
        jPanel3.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 100, -1));

        jLabel33.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("#");
        jPanel3.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 20, -1));

        jPanel12.setBackground(new java.awt.Color(84, 110, 122));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 31, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, -1, -1));

        lb_CostoCalculado.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        lb_CostoCalculado.setForeground(new java.awt.Color(153, 153, 153));
        lb_CostoCalculado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_CostoCalculado.setText("Detalles");
        jPanel3.add(lb_CostoCalculado, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 100, -1));

        jLabel29.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("$");
        jPanel3.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 10, 20));

        jLabel27.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("Folio de Reservación");
        jPanel3.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 130, 20));

        jLabel35.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("Total a cobrar: $");
        jPanel3.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 230, 100, 20));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 640, 260));

        pn_showFacturas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_NombreRazonSocial.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lb_NombreRazonSocial.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_NombreRazonSocial.setText("Razón Social");
        pn_showFacturas.add(lb_NombreRazonSocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 80, -1));

        lb_NombreRFC.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lb_NombreRFC.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_NombreRFC.setText("RFC");
        pn_showFacturas.add(lb_NombreRFC, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 80, -1));

        lb_NombreEmail.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lb_NombreEmail.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_NombreEmail.setText("Email");
        pn_showFacturas.add(lb_NombreEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 80, -1));
        pn_showFacturas.add(jsep_Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 190, 10));

        jt_email.setBackground(new java.awt.Color(233, 235, 238));
        jt_email.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jt_email.setForeground(new java.awt.Color(153, 153, 153));
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
        pn_showFacturas.add(jt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 190, -1));
        pn_showFacturas.add(jsep_RFC, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 190, 10));

        lb_rfc.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        lb_rfc.setForeground(new java.awt.Color(153, 153, 153));
        lb_rfc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_rfc.setText("RFC");
        pn_showFacturas.add(lb_rfc, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 190, -1));

        lb_razonSocial.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lb_razonSocial.setForeground(new java.awt.Color(153, 153, 153));
        lb_razonSocial.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_razonSocial.setText("Razon Social");
        pn_showFacturas.add(lb_razonSocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 170, -1));
        pn_showFacturas.add(jsep_RazonSocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 170, 10));

        btn_SeleccionarClientes.setBackground(new java.awt.Color(204, 204, 204));
        btn_SeleccionarClientes.setText("...");
        btn_SeleccionarClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_SeleccionarClientes.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_SeleccionarClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_SeleccionarClientesMouseClicked(evt);
            }
        });
        btn_SeleccionarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SeleccionarClientesActionPerformed(evt);
            }
        });
        pn_showFacturas.add(btn_SeleccionarClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 30, 30));

        jPanel1.add(pn_showFacturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 270, 310, 120));

        jPanel6.setBackground(new java.awt.Color(84, 110, 122));

        jLabel20.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Requiere Factura? ");

        jch_facturacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jch_facturacionItemStateChanged(evt);
            }
        });
        jch_facturacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jch_facturacionMouseClicked(evt);
            }
        });
        jch_facturacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jch_facturacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addComponent(jch_facturacion)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jch_facturacion)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 240, 310, 30));

        jPanel8.setBackground(new java.awt.Color(233, 235, 238));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("Nombre  Cliente:");
        jPanel8.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 49, 108, -1));

        jPanel9.setBackground(new java.awt.Color(84, 110, 122));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Detalles del Cliente");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 141, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        jPanel8.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, -1));

        jSeparator9.setToolTipText("");
        jPanel8.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 190, 10));

        lb_nombreCliente.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        lb_nombreCliente.setForeground(new java.awt.Color(153, 153, 153));
        lb_nombreCliente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_nombreCliente.setText("Nombre");
        jPanel8.add(lb_nombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 190, -1));

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 310, 150));

        jPanel5.setBackground(new java.awt.Color(233, 235, 238));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 61, 120, 10));

        jLabel21.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("Nombre Habitación:");
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 41, 122, -1));

        jLabel23.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("Tipo de Habitación :");
        jPanel5.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 122, -1));

        lb_TipoHabitacion.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        lb_TipoHabitacion.setForeground(new java.awt.Color(153, 153, 153));
        lb_TipoHabitacion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_TipoHabitacion.setText("Detalles");
        jPanel5.add(lb_TipoHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 120, -1));
        jPanel5.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 122, 10));

        jPanel7.setBackground(new java.awt.Color(84, 110, 122));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Detalles de la Habitación");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 141, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, -1));
        jPanel5.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 120, 10));

        lb_PrecioHabitacion.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        lb_PrecioHabitacion.setForeground(new java.awt.Color(153, 153, 153));
        lb_PrecioHabitacion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_PrecioHabitacion.setText("0.0");
        jPanel5.add(lb_PrecioHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 120, -1));

        jLabel22.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("Precio Habitación");
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 110, -1));

        lb_NombreHabitacion.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        lb_NombreHabitacion.setForeground(new java.awt.Color(153, 153, 153));
        lb_NombreHabitacion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_NombreHabitacion.setText("Detalles");
        jPanel5.add(lb_NombreHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 120, -1));

        jLabel34.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("$");
        jPanel5.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 10, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 310, 150));

        jPanel10.setBackground(new java.awt.Color(233, 235, 238));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_FechaSalida.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        lb_FechaSalida.setForeground(new java.awt.Color(153, 153, 153));
        lb_FechaSalida.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_FechaSalida.setText("Fecha Salida");
        jPanel10.add(lb_FechaSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 190, -1));

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("F. Salida");
        jPanel10.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 70, -1));
        jPanel10.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 190, 10));
        jPanel10.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 190, 10));

        jPanel11.setBackground(new java.awt.Color(84, 110, 122));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Datos de Ingreso y Salida");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 141, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        jPanel10.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, -1));

        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("F. Ingreso");
        jPanel10.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 70, -1));

        lb_FechaIngreso.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        lb_FechaIngreso.setForeground(new java.awt.Color(153, 153, 153));
        lb_FechaIngreso.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_FechaIngreso.setText("Fecha ingreso");
        jPanel10.add(lb_FechaIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 190, -1));

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 60, 310, 150));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 228, 1030, -1));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1030, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1065, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_CobrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_CobrarMouseClicked

    }//GEN-LAST:event_btn_CobrarMouseClicked

    private void btn_CobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CobrarActionPerformed

        try {
            if (validador == 1 && cb_TipoPago.getSelectedItem() != "Seleccionar Tipo de Pago") {
                /*
                     monto,  tipoPago,  rfc,  correo,  usuario,  Nombre,  TipoHabitacion,  FechaIngreso,  FechaSalida,  IdFacturacion
                 */
                cco.insertCobro(Double.valueOf(jt_Monto.getText()), String.valueOf(cb_TipoPago.getSelectedItem()), lb_rfc.getText(), jt_email.getText(), Principal.User, lb_NombreHabitacion.getText(), lb_FechaIngreso.getText(), lb_FechaSalida.getText(), "Con Factura");
                cr.deleteReservacion(Integer.valueOf(lb_FolioReservaciones.getText()));
                ch.updateHabitacion(lb_NombreHabitacion.getText(), "Limpieza");
            } else if (validador == 0 && cb_TipoPago.getSelectedItem() != "Seleccionar Tipo de Pago") {
                cco.insertCobro(Double.valueOf(jt_Monto.getText()), String.valueOf(cb_TipoPago.getSelectedItem()), "----", "----", Principal.User, lb_NombreHabitacion.getText(), lb_FechaIngreso.getText(), lb_FechaSalida.getText(), "Sin Factura");
                cr.deleteReservacion(Integer.valueOf(lb_FolioReservaciones.getText()));
                ch.updateHabitacion(lb_NombreHabitacion.getText(), "Limpieza");
            } else if (cb_TipoPago.getSelectedItem() == "Seleccionar Tipo de Pago") {
                DesktopNotify.showDesktopMessage("Error", "Seleccione un tipo de pago", DesktopNotify.ERROR);
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_btn_CobrarActionPerformed

    private void cb_TipoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_TipoPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_TipoPagoActionPerformed

    private void cb_TipoPagoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_TipoPagoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
         //   this.idPago = cb_TipoPago.getSelectedIndex();
        }      
    }//GEN-LAST:event_cb_TipoPagoItemStateChanged

    private void jt_MontoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_MontoFocusGained
        cft.formFocusGain(jt_Monto);
    }//GEN-LAST:event_jt_MontoFocusGained

    private void jt_MontoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_MontoFocusLost
        cft.formFocusLostJTextField(jt_Monto, "Ingresar Monto");
    }//GEN-LAST:event_jt_MontoFocusLost

    private void jt_MontoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_MontoMouseClicked
        cft.formFocusGain(jt_Monto);
    }//GEN-LAST:event_jt_MontoMouseClicked

    private void jt_MontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_MontoKeyTyped
        ce.typedCharsAndSpaceAndDigits(evt, jt_Monto);
    }//GEN-LAST:event_jt_MontoKeyTyped

    private void jch_facturacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jch_facturacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jch_facturacionActionPerformed

    private void jch_facturacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jch_facturacionMouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_jch_facturacionMouseClicked

    private void jch_facturacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jch_facturacionItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
            validador = 1;
            /*Regresa a su posicion original por debajo del formulario de datos para facturas 
            para dar paso a la aparicion de dicho formulario*/
            PosicionOriginalBotones();
            /*Muestra el pequeño formulario para poder seleccionar al cliente y modificar el 
            correo del mismo*/
            mostrarElementosFacturas();
            //FIN
            /*desbloquea el uso del boton para seleccionar clientes y la posibilidad de modificar el email
            de ese cliente en el apartado de facturas*/
            desbloquearComponentes();
            //FIN

        } else {//checkbox has been deselected
            validador = 0;
            /*AJUSTA LA POSICION CERCA DE LA PREGUNTA (requiere factura?) a los botones de 
            cobrar y salir*/
            ReposicionarBotones();
            /*Muestra el pequeño formulario para poder seleccionar al cliente y modificar el 
            correo del mismo*/
            ocultarElementosFacturas();
            //FIN 
            /*bloquea el uso del boton para seleccionar clientes y la posibilidad de modificar el email
            de ese cliente en el apartado de facturas*/
            bloquearComponentes();
            //FIN

        }
    }//GEN-LAST:event_jch_facturacionItemStateChanged

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

    private void btn_SalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SalirMouseClicked

    }//GEN-LAST:event_btn_SalirMouseClicked

    private void btn_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirActionPerformed
        dispose();

    }//GEN-LAST:event_btn_SalirActionPerformed

    private void btn_SeleccionarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SeleccionarClientesActionPerformed

        selectCC.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_SeleccionarClientesActionPerformed

    private void btn_SeleccionarClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SeleccionarClientesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_SeleccionarClientesMouseClicked

    private void jt_emailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_emailKeyTyped
        ce.typedCharsAndSpace(evt, jt_email);
    }//GEN-LAST:event_jt_emailKeyTyped

    private void jt_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_emailActionPerformed
        // TODO add your handling code here:
        jt_email.setText("");
    }//GEN-LAST:event_jt_emailActionPerformed

    private void jt_emailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_emailMouseClicked
        // TODO add your handling code here:
        // t_control.setText("");
        if (!jt_email.getText().equals("Ingresar Nombre")) {

        } else {
            jt_email.setText("");
        }
    }//GEN-LAST:event_jt_emailMouseClicked

    private void jt_emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_emailFocusLost
        // TODO add your handling code here:
        if (jt_email.getText().trim().equals("")) {
            jt_email.setText("Ingresar Nombre");
            jt_email.setForeground(new Color(153, 153, 153));

            // TODO add your handling code here:
        }
    }//GEN-LAST:event_jt_emailFocusLost

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
                new Pn_CobrarReservacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Celular2;
    private principal.MaterialButton btn_Cobrar;
    private principal.MaterialButton btn_Salir;
    private principal.MaterialButton btn_SeleccionarClientes;
    private javax.swing.JComboBox<String> cb_TipoPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JCheckBox jch_facturacion;
    private javax.swing.JSeparator jsep_Email;
    private javax.swing.JSeparator jsep_RFC;
    private javax.swing.JSeparator jsep_RazonSocial;
    private javax.swing.JTextField jt_Monto;
    public static javax.swing.JTextField jt_email;
    public static javax.swing.JLabel lb_CostoCalculado;
    public static javax.swing.JLabel lb_FechaIngreso;
    public static javax.swing.JLabel lb_FechaSalida;
    public static javax.swing.JLabel lb_FolioReservaciones;
    private javax.swing.JLabel lb_NombreEmail;
    public static javax.swing.JLabel lb_NombreHabitacion;
    private javax.swing.JLabel lb_NombreRFC;
    private javax.swing.JLabel lb_NombreRazonSocial;
    public static javax.swing.JLabel lb_PrecioHabitacion;
    public static javax.swing.JLabel lb_TipoHabitacion;
    private javax.swing.JLabel lb_errorCFDI;
    public static javax.swing.JLabel lb_nombreCliente;
    public static javax.swing.JLabel lb_razonSocial;
    public static javax.swing.JLabel lb_rfc;
    private javax.swing.JPanel pn_cerrar;
    private javax.swing.JPanel pn_showFacturas;
    // End of variables declaration//GEN-END:variables
}
