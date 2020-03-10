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

    ControladorClientes mc = new ControladorClientes();
    ControladorCFDI cf = new ControladorCFDI();
    ControladorEscritura ce = new ControladorEscritura();
    private ControladorFormularioTab cft = new ControladorFormularioTab();
    Pn_SeleccionarClientesCobro selectCC = new Pn_SeleccionarClientesCobro();
    ControladorTipoPagos ct = new ControladorTipoPagos();
    ControladorCobros cco = new ControladorCobros();
    DefaultTableModel NewTable;
    private int seleccion,validador,idPago;
    
        /**
     * Creates new form Pn_SeleccionClientes
     */
    public Pn_CobrarReservacion() {
        setPantalla();
        initComponents();
        datosIniciales();
        setIconSystem();
        centrarPantalla();
        RowHeaderApariencia();
        RowApariencia();
        cf.tablaCFDI();
        ct.tablaTipoPagos();
        cTabla();
        tamañoTabla();
        cargarTiposdePago();
        bloquearComponentes();
          
       
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
        try {
            jt_Clientes.setModel(cco.tablaCobros());
            jt_t_registros.setText(String.valueOf(cco.selectCobro().size()));
        } catch (Exception e) {
        }
    
    }

    public void cargarTiposdePago() {
        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        cb.addElement("Seleccionar Tipo de Pago");
        for (ObjetoTipoPago campos : ct.selectTipoPago()) {
            cb.addElement(campos.getNombre());

        }
        cb_TipoPago.setModel(cb);
    
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
     
        lb_errorCFDI.setText("*");
     
        //colores
        lb_errorCFDI.setForeground(new Color(84, 110, 122));
    
        cb_TipoPago.setSelectedIndex(0);
    }

    public void bloquearComponentes() {
btn_Clientes.setEnabled(false);
jt_email.setEnabled(false);
    }

    public void desbloquearComponentes() {
      btn_Clientes.setEnabled(true);
jt_email.setEnabled(true);
    }

    public Boolean validarEscritura() {
        Boolean val = true;
        return val;
    }

   public Boolean validarSeleccion() {
        Boolean val = true;
        if (!(cb_TipoPago.getSelectedIndex() == 0)) {

            lb_errorCFDI.setForeground(new Color(84,110,122));
        } else {

            lb_errorCFDI.setForeground(Color.RED);

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
        lb_FolioReservaciones = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        Celular2 = new javax.swing.JLabel();
        cb_TipoPago = new javax.swing.JComboBox<>();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jt_t_registros = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_Cobror = new principal.MaterialButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pn_cerrar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        lb_errorCFDI = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lb_nombreCliente = new javax.swing.JLabel();
        lb_NombreHabitacion = new javax.swing.JLabel();
        lb_FechaIngreso = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        lb_FechaSalida = new javax.swing.JLabel();
        jt_Monto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_Clientes = new javax.swing.JTable();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jch_facturacion = new javax.swing.JCheckBox();
        lb_razonSocial = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        lb_rfc = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jt_email = new javax.swing.JTextField();
        btn_Clientes = new principal.MaterialButton();
        jSeparator13 = new javax.swing.JSeparator();
        btn_Cancelar = new principal.MaterialButton();
        jLabel22 = new javax.swing.JLabel();
        lb_PrecioHabitacion = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel30 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(84, 110, 122));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_FolioReservaciones.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lb_FolioReservaciones.setForeground(new java.awt.Color(255, 255, 255));
        lb_FolioReservaciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(lb_FolioReservaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 30, 20));

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(204, 204, 204));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 190, -1));

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("F. Salida");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 70, -1));

        Celular2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        Celular2.setForeground(new java.awt.Color(255, 255, 255));
        Celular2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Celular2.setText("Tipo de pago ");
        jPanel1.add(Celular2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 90, -1));

        cb_TipoPago.setBackground(new java.awt.Color(84, 110, 122));
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
        jPanel1.add(cb_TipoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, 150, -1));

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 190, 10));

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 30, 10));

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
        jPanel1.add(jt_t_registros, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 580, 30, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Total de registros ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 580, 100, 20));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/limpiarCampos 24x24.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, -1, -1));

        btn_Cobror.setBackground(new java.awt.Color(40, 180, 99));
        btn_Cobror.setForeground(new java.awt.Color(255, 255, 255));
        btn_Cobror.setText("Cobrar");
        btn_Cobror.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Cobror.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_Cobror.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_CobrorMouseClicked(evt);
            }
        });
        btn_Cobror.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CobrorActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Cobror, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 100, 40));

        jPanel2.setBackground(new java.awt.Color(84, 110, 122));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Sección de Cobro ");

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

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 50));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1090, 10));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Formulario de Cambios");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        lb_errorCFDI.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorCFDI.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorCFDI.setText("*");
        jPanel1.add(lb_errorCFDI, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 10, -1));

        jLabel19.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Nombre;");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 70, -1));

        jLabel20.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Requiere Factura? ");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 120, 20));

        jLabel21.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("Habitacion;");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 70, -1));

        lb_nombreCliente.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        lb_nombreCliente.setForeground(new java.awt.Color(204, 204, 204));
        lb_nombreCliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_nombreCliente.setText("Nombre");
        jPanel1.add(lb_nombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 190, -1));

        lb_NombreHabitacion.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        lb_NombreHabitacion.setForeground(new java.awt.Color(204, 204, 204));
        lb_NombreHabitacion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_NombreHabitacion.setText("Detalles");
        jPanel1.add(lb_NombreHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 190, -1));

        lb_FechaIngreso.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        lb_FechaIngreso.setForeground(new java.awt.Color(204, 204, 204));
        lb_FechaIngreso.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_FechaIngreso.setText("Fecha ingreso");
        jPanel1.add(lb_FechaIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 190, -1));

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 190, 10));

        jSeparator7.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 190, 10));

        jLabel28.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("Monto a cobrar ");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 100, -1));

        jLabel29.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("$");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 10, -1));

        jSeparator8.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, 140, 10));

        lb_FechaSalida.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        lb_FechaSalida.setForeground(new java.awt.Color(204, 204, 204));
        lb_FechaSalida.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_FechaSalida.setText("Fecha Salida");
        jPanel1.add(lb_FechaSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 190, -1));

        jt_Monto.setBackground(new java.awt.Color(84, 110, 122));
        jt_Monto.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jt_Monto.setForeground(new java.awt.Color(204, 204, 204));
        jt_Monto.setText("0.0");
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
        jPanel1.add(jt_Monto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, 140, -1));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 720, 430));

        jSeparator9.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 190, 10));

        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("F. Ingreso");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 70, -1));

        jLabel24.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("Folio de Reservación");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 130, 20));

        jLabel26.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 330, 30, 20));

        jLabel27.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("Rzon Social");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 70, -1));

        jLabel31.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("RFC");
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 70, -1));

        jLabel32.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("Email");
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 70, -1));

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
        jPanel1.add(jch_facturacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, -1, -1));

        lb_razonSocial.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lb_razonSocial.setForeground(new java.awt.Color(204, 204, 204));
        lb_razonSocial.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_razonSocial.setText("Razon Social");
        jPanel1.add(lb_razonSocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 390, 170, -1));

        jSeparator10.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 300, 10));

        lb_rfc.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        lb_rfc.setForeground(new java.awt.Color(204, 204, 204));
        lb_rfc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_rfc.setText("RFC");
        jPanel1.add(lb_rfc, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 420, 190, -1));

        jSeparator11.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 470, 190, 10));

        jSeparator12.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 440, 190, 10));

        jt_email.setBackground(new java.awt.Color(84, 110, 122));
        jt_email.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
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
        jPanel1.add(jt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 450, 190, -1));

        btn_Clientes.setBackground(new java.awt.Color(233, 235, 238));
        btn_Clientes.setText("...");
        btn_Clientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Clientes.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_Clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ClientesMouseClicked(evt);
            }
        });
        btn_Clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ClientesActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Clientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 390, 30, 30));

        jSeparator13.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 410, 170, 10));

        btn_Cancelar.setBackground(new java.awt.Color(211, 18, 18));
        btn_Cancelar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Cancelar.setText("SALIR");
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
        jPanel1.add(btn_Cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 530, 110, 40));

        jLabel22.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("Precio Habitación");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 110, -1));

        lb_PrecioHabitacion.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        lb_PrecioHabitacion.setForeground(new java.awt.Color(204, 204, 204));
        lb_PrecioHabitacion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_PrecioHabitacion.setText("0.0");
        jPanel1.add(lb_PrecioHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 130, -1));

        jSeparator6.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 130, 10));

        jLabel30.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("$");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 10, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1065, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jt_t_registrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_t_registrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_t_registrosActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        datosIniciales();
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void btn_CobrorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_CobrorMouseClicked

    }//GEN-LAST:event_btn_CobrorMouseClicked

    private void btn_CobrorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CobrorActionPerformed
        try {
            if (validador == 1 && cb_TipoPago.getSelectedItem() != "Seleccionar Tipo de Pago") {

                cco.insertCobro(Integer.parseInt(lb_FolioReservaciones.getText()), Integer.parseInt(jt_Monto.getText()), String.valueOf(cb_TipoPago.getSelectedItem()), lb_rfc.getText(), jt_email.getText(), "Con Factura");

            } else if (validador == 0 && cb_TipoPago.getSelectedItem() != "Seleccionar Tipo de Pago") {
                cco.insertCobro(Integer.parseInt(lb_FolioReservaciones.getText()), Integer.parseInt(jt_Monto.getText()), String.valueOf(cb_TipoPago.getSelectedItem()),"----", "----", "Sin Factura");

            } else if (cb_TipoPago.getSelectedItem() == "Seleccionar Tipo de Pago"){
                DesktopNotify.showDesktopMessage("Error", "Seleccione un tipo de pago", DesktopNotify.ERROR);
            }
            cTabla();
            tamañoTabla();
        } catch (Exception e) {
        }

    }//GEN-LAST:event_btn_CobrorActionPerformed

    private void cb_TipoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_TipoPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_TipoPagoActionPerformed

    private void cb_TipoPagoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_TipoPagoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.idPago=cb_TipoPago.getSelectedIndex();
        }        // TODO add your handling code here:
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

    private void jt_ClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_ClientesMouseClicked
        seleccion = jt_Clientes.rowAtPoint(evt.getPoint());
     
    }//GEN-LAST:event_jt_ClientesMouseClicked

    private void jch_facturacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jch_facturacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jch_facturacionActionPerformed

    private void jt_emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_emailFocusLost
        // TODO add your handling code here:
        if (jt_email.getText().trim().equals("")) {
            jt_email.setText("Ingresar Nombre");
            jt_email.setForeground(new Color(153, 153, 153));

            // TODO add your handling code here:
        }
    }//GEN-LAST:event_jt_emailFocusLost

    private void jt_emailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_emailMouseClicked
        // TODO add your handling code here:
        // t_control.setText("");
        if (!jt_email.getText().equals("Ingresar Nombre")) {

        } else {
            jt_email.setText("");
        }
    }//GEN-LAST:event_jt_emailMouseClicked

    private void jt_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_emailActionPerformed
        // TODO add your handling code here:
        jt_email.setText("");
    }//GEN-LAST:event_jt_emailActionPerformed

    private void jt_emailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_emailKeyTyped
        ce.typedCharsAndSpace(evt, jt_email);
    }//GEN-LAST:event_jt_emailKeyTyped

    private void btn_ClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ClientesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ClientesMouseClicked

    private void btn_ClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ClientesActionPerformed

        selectCC.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ClientesActionPerformed

    private void jch_facturacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jch_facturacionMouseClicked


        // TODO add your handling code here:
    }//GEN-LAST:event_jch_facturacionMouseClicked

    private void jch_facturacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jch_facturacionItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
            validador = 1;
           desbloquearComponentes();
        } else {//checkbox has been deselected
            validador = 0;
            bloquearComponentes();
        }       // TODO add your handling code here:
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

    private void btn_CancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_CancelarMouseClicked

    }//GEN-LAST:event_btn_CancelarMouseClicked

    private void btn_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CancelarActionPerformed
        dispose();

    }//GEN-LAST:event_btn_CancelarActionPerformed

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
    private principal.MaterialButton btn_Cancelar;
    private principal.MaterialButton btn_Clientes;
    private principal.MaterialButton btn_Cobror;
    private javax.swing.JComboBox<String> cb_TipoPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JCheckBox jch_facturacion;
    private javax.swing.JTable jt_Clientes;
    private javax.swing.JTextField jt_Monto;
    public static javax.swing.JTextField jt_email;
    private javax.swing.JTextField jt_t_registros;
    public static javax.swing.JLabel lb_FechaIngreso;
    public static javax.swing.JLabel lb_FechaSalida;
    public static javax.swing.JLabel lb_FolioReservaciones;
    public static javax.swing.JLabel lb_NombreHabitacion;
    public static javax.swing.JLabel lb_PrecioHabitacion;
    private javax.swing.JLabel lb_errorCFDI;
    public static javax.swing.JLabel lb_nombreCliente;
    public static javax.swing.JLabel lb_razonSocial;
    public static javax.swing.JLabel lb_rfc;
    private javax.swing.JPanel pn_cerrar;
    // End of variables declaration//GEN-END:variables
}
