/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import vistas.Alertas.Pn_SeleccionarClientes;
import vistas.Alertas.Pn_CobrarReservacion;
import vistas.Alertas.Pn_Alert_Eliminar;
import controladores.ControladorCategorias;
import controladores.ControladorClientes;
import controladores.ControladorEscritura;
import controladores.ControladorFormularioTab;
import controladores.ControladorHabitaciones;
import controladores.ControladorReservaciones;
import controladores.ValidadorDePrivilegios.*;
import ds.desktop.notify.DesktopNotify;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import objetos.ObjetoCategoria;
import objetos.ObjetoHabitacion;

/**
 *
 * @author fenix
 */
public class Pn_Reservaciones extends javax.swing.JPanel {
//NECESARIO PARA FUNCIONES DE ESTE MODULO 

    private ControladorEscritura ce = new ControladorEscritura();
    private ControladorHabitaciones ch = new ControladorHabitaciones();
    private ControladorCategorias ccat = new ControladorCategorias();
    private ControladorClientes mc = new ControladorClientes();
    private ControladorReservaciones cr = new ControladorReservaciones();
    private ControladorFormularioTab cft = new ControladorFormularioTab();

    //private Pn_CobrarReservacion1 CobrarReserv = new Pn_CobrarReservacion1();
    private DefaultTableModel NewTable;
//FIN
    //necesario para obtener el numero de fila de la jtable
    private int seleccion;
    //fin
    //necesario para dar formato al jdateChooser
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private int m = 0;
    //fin
    //NECESARIO PARA EXTRAER LOS PRIVILEGIOS DENTRO DE ESTE MODULO, EN FUNCION AL USUARIO ACTUAL 
    ControladorPrivilegiosReservaciones analisis = new ControladorPrivilegiosReservaciones();
    //FIN
    //NECESARIO PARA HACER LA COMPRACION Y EXTRACCION DE LOS PRIVILEGIOS DE ESTE MODULO
    private String NombreModulo = "Reservaciones";
    //FIN
    //NECESARIO PARA EL USO DE LA NOTIFICACION DINAMICA DE BOTON ELIMINAR ()
    Frame principal;
//FIN
    //NECESARIO
//busqueda del numero de mes de la reservaciion para comparaciones
    String MesdeFechaIngreso, MesdeFechaSalida;

    private boolean flag = false;

//FIN DE NECESARIO
    Date now = new Date(); // java.util.Date, NOT java.sql.Date or java.sql.Timestamp!
    String year = new SimpleDateFormat("yyyy").format(now);
    String month = new SimpleDateFormat("MM").format(now);
    String day = new SimpleDateFormat("dd").format(now);
    int año = Integer.parseInt(year);
    int mes = Integer.parseInt(month);
    int dia = Integer.parseInt(day);
    String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(now);

    /**
     * Creates new form Pn_Reservaciones1
     */
    public Pn_Reservaciones() {
        initComponents();
        //EXTRAE LOS PRIVILEGIOS DE ESTE MODULO
        analisis.validarPermisos(NombreModulo);
        //FIN
        //NECESARIO PARA CARGAR LOS DATOS DE LAS TABLAS O EN LOS CB REQUERIDOS
        cr.tablaReservaciones();
        mc.tablaClientes();
        ch.tablaHabitaciones();
        ccat.tablaCategorias();
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
        //CARGA EL CB CON LAS HABITACIONES
        cargarHabitaciones();
        //FIN
        DesktopNotify.showDesktopMessage("Recordatorio", "La fecha de ingreso no debe ser menor "
                + "a la fecha actual ", DesktopNotify.TIP);
    }

    private void cargarHabitaciones() {
        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        cb.addElement("Seleccionar Habitación");

        for (ObjetoHabitacion campos : ch.selectHabitacion()) {
            if (!(campos.getIdEstadoHabitacion() == 1)) {
                //break;
            } else {
                cb.addElement(campos.getNombre());

            }
        }
        cb_Habitacion.setModel(cb);
    }

    public void cTabla() {
        jt_Reservas.setModel(cr.tablaReservaciones());
        jt_t_registros.setText(String.valueOf(jt_Reservas.getRowCount()));

    }

    private void tamañoTabla() {
        TableColumnModel columnModel = jt_Reservas.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(1).setPreferredWidth(120);
        columnModel.getColumn(2).setPreferredWidth(120);
        columnModel.getColumn(3).setPreferredWidth(80);
        columnModel.getColumn(4).setPreferredWidth(80);
        columnModel.getColumn(5).setPreferredWidth(50);

    }

    private void datosIniciales() {
        btn_Cobrar.setEnabled(false);
        //btn_Eliminar.setEnabled(false);
        jt_nombre.setText("Ingresar Nombre");
        cb_Habitacion.setSelectedIndex(0);
        jd_Ingreso.setCalendar(null);
        jd_Salida.setCalendar(null);
        lb_Id.setText("");
        lb_estadoCobro.setText("");

        lb_errorNombre.setForeground(new Color(84, 110, 122));
        lb_errorHabitacion.setForeground(new Color(84, 110, 122));
        lb_errorFechaIngreso.setForeground(new Color(84, 110, 122));
        lb_errorFechaSalida.setForeground(new Color(84, 110, 122));

    }

    private void RowApariencia() {

        jt_Reservas.setFocusable(false);

        //espacio entre comulnas
        jt_Reservas.setIntercellSpacing(new Dimension(0, 1));
        //altura de columnas 
        jt_Reservas.setRowHeight(25);
        //margen entre filas
        jt_Reservas.setRowMargin(0);
//sin lineas verticles
        jt_Reservas.setShowVerticalLines(false);
        jt_Reservas.setSelectionBackground(new Color(97, 212, 195));

    }

    private void RowHeaderApariencia() {
        jt_Reservas.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 14));
        jt_Reservas.getTableHeader().setOpaque(false);
        jt_Reservas.getTableHeader().setBackground(new Color(32, 136, 203));
        jt_Reservas.getTableHeader().setForeground(new Color(255, 255, 255));

    }

    private Boolean validarEscritura() {
        Boolean val = true;
        //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(jt_nombre.getText().equals("Ingresar Nombre")) && !(jt_nombre.getText().equals(""))) {
            lb_errorNombre.setForeground(new Color(84, 110, 122));

        } else {
            lb_errorNombre.setForeground(Color.RED);
            val = false;
        }

        //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(jd_Ingreso.getDate() == null)) {
            lb_errorFechaIngreso.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorFechaIngreso.setForeground(Color.RED);
            val = false;
        }
        //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(jd_Salida.getDate() == null)) {
            lb_errorFechaSalida.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorFechaSalida.setForeground(Color.RED);
            val = false;
        }

        return val;
    }

    private Boolean validarSeleccion() {
        Boolean val = true;

        if (!(cb_Habitacion.getSelectedIndex() == 0)) {
            lb_errorHabitacion.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorHabitacion.setForeground(Color.RED);
            val = false;
        }
        return val;

    }

    private Boolean validacionFechas() {
        Boolean val = true;

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
        jt_Reservas = new javax.swing.JTable()

        {
            @Override

            public Component prepareRenderer (TableCellRenderer renderer, int rowIndex, int columnIndex){
                Component component = super.prepareRenderer(renderer, rowIndex, columnIndex);
                Object value = getModel().getValueAt(rowIndex,columnIndex);

                if(columnIndex == 4){
                    if(value.equals(fechaActual)){
                        component.setBackground(Color.RED);
                    }

                }else if(columnIndex == 5){
                    if(value.equals("Pendiente")){
                        component.setBackground(Color.ORANGE);
                    }else if(value.equals("Cobrado")){
                        component.setBackground(Color.RED);
                    }
                }else{
                    component.setBackground(Color.WHITE);
                    component.setForeground(Color.BLACK);

                }
                return component;
            }
        }

        ;
        jLabel15 = new javax.swing.JLabel();
        jt_nombre = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        btn_Ingresar = new principal.MaterialButton();
        btn_Cobrar = new principal.MaterialButton();
        btn_Modificar = new principal.MaterialButton();
        cb_Habitacion = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jd_Ingreso = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        jd_Salida = new com.toedter.calendar.JDateChooser();
        jt_t_registros = new javax.swing.JLabel();
        lb_errorFechaSalida = new javax.swing.JLabel();
        lb_errorNombre = new javax.swing.JLabel();
        lb_errorFechaIngreso = new javax.swing.JLabel();
        lb_errorHabitacion = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btn_clientes = new principal.MaterialButton();
        btn_Eliminar = new principal.MaterialButton();
        lb_Id = new javax.swing.JLabel();
        jt_Buscar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jb_limpiarCampos2 = new javax.swing.JButton();
        lb_estadoCobro = new javax.swing.JLabel();
        fSLabel1 = new LIB.FSLabel();
        fSLabel2 = new LIB.FSLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        fSLabel3 = new LIB.FSLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        fSLabel4 = new LIB.FSLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        fSLabel5 = new LIB.FSLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        fSLabel6 = new LIB.FSLabel();

        jPanel1.setBackground(new java.awt.Color(84, 110, 122));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jt_Reservas.setModel(new javax.swing.table.DefaultTableModel(
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
        jt_Reservas.getTableHeader().setResizingAllowed(false);
        jt_Reservas.getTableHeader().setReorderingAllowed(false);
        jt_Reservas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_ReservasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jt_Reservas);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 650, 200));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Nombre");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 190, -1));

        jt_nombre.setBackground(new java.awt.Color(84, 110, 122));
        jt_nombre.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
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
        jPanel1.add(jt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 170, -1));

        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("Fecha de ingreso");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 190, -1));

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 190, 10));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Total de registros ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 330, -1, 20));

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
        jPanel1.add(btn_Ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 100, 40));

        btn_Cobrar.setBackground(new java.awt.Color(51, 51, 51));
        btn_Cobrar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Cobrar.setText("Cobrar");
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
        jPanel1.add(btn_Cobrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 440, 100, 40));

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
        jPanel1.add(btn_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 95, 40));

        cb_Habitacion.setBackground(new java.awt.Color(84, 110, 122));
        cb_Habitacion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cb_Habitacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Habitacion" }));
        cb_Habitacion.setBorder(null);
        cb_Habitacion.setFocusable(false);
        cb_Habitacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_HabitacionItemStateChanged(evt);
            }
        });
        cb_Habitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_HabitacionActionPerformed(evt);
            }
        });
        jPanel1.add(cb_Habitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 190, -1));

        jPanel2.setBackground(new java.awt.Color(84, 110, 122));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("VISTA GENERAL DE LAS HABITACIONES RESERVADAS");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Inicio > Reservaciones > Nueva Reservación");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(jLabel29)
                .addGap(77, 77, 77))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel29))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 50));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Formulario de Cambios");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Detalle de Reservaciones");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1030, 10));

        jd_Ingreso.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jd_IngresoPropertyChange(evt);
            }
        });
        jPanel1.add(jd_Ingreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 190, -1));

        jLabel20.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Fecha de Salida");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 190, -1));

        jd_Salida.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jd_SalidaPropertyChange(evt);
            }
        });
        jPanel1.add(jd_Salida, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 190, -1));

        jt_t_registros.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jt_t_registros.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jt_t_registros, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 330, 30, 20));

        lb_errorFechaSalida.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorFechaSalida.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorFechaSalida.setText("*");
        jPanel1.add(lb_errorFechaSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 10, -1));

        lb_errorNombre.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorNombre.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorNombre.setText("*");
        jPanel1.add(lb_errorNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 10, -1));

        lb_errorFechaIngreso.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorFechaIngreso.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorFechaIngreso.setText("*");
        jPanel1.add(lb_errorFechaIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 10, -1));

        lb_errorHabitacion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorHabitacion.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorHabitacion.setText("*");
        jPanel1.add(lb_errorHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 10, -1));

        jLabel21.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("Habitación");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 190, -1));

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
        jPanel1.add(btn_clientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 30, 30));

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
        jPanel1.add(btn_Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 100, 40));

        lb_Id.setForeground(new java.awt.Color(84, 110, 122));
        jPanel1.add(lb_Id, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 30, 20));

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
        jPanel1.add(jt_Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 90, 150, 20));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/campo-buscar.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 80, -1, 40));

        jb_limpiarCampos2.setBackground(new java.awt.Color(84, 110, 122));
        jb_limpiarCampos2.setForeground(new java.awt.Color(84, 110, 122));
        jb_limpiarCampos2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/clean24x24.png"))); // NOI18N
        jb_limpiarCampos2.setBorder(null);
        jb_limpiarCampos2.setBorderPainted(false);
        jb_limpiarCampos2.setContentAreaFilled(false);
        jb_limpiarCampos2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_limpiarCampos2.setFocusPainted(false);
        jb_limpiarCampos2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/cleanSeleccionar24x24.png"))); // NOI18N
        jb_limpiarCampos2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_limpiarCampos2ActionPerformed(evt);
            }
        });
        jPanel1.add(jb_limpiarCampos2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 40, -1));

        lb_estadoCobro.setForeground(new java.awt.Color(84, 110, 122));
        lb_estadoCobro.setText("jLabel5");
        jPanel1.add(lb_estadoCobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 190, -1));

        fSLabel1.setBackground(java.awt.Color.white);
        fSLabel1.setText("");
        jPanel1.add(fSLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, 30, 30));

        fSLabel2.setBackground(java.awt.Color.orange);
        fSLabel2.setText("");
        jPanel1.add(fSLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, 30, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Estado");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 450, -1, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ESTADOS DE COLOR ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 330, -1, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Cobrar Reservación");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 380, -1, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("F. de Salida");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 410, -1, 30));

        fSLabel3.setBackground(java.awt.Color.red);
        fSLabel3.setText("");
        jPanel1.add(fSLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 410, 30, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("F. de Salida");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 410, -1, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Estado");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 450, -1, 30));

        fSLabel4.setBackground(java.awt.Color.orange);
        fSLabel4.setText("");
        jPanel1.add(fSLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 450, 30, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Cobrar Reservacion Urgente");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 380, -1, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("ELIMINAR RESERVACIÓN");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 380, -1, 30));

        fSLabel5.setBackground(java.awt.Color.red);
        fSLabel5.setText("");
        jPanel1.add(fSLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 410, 30, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("F. de Salida");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 410, -1, 30));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Estado");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 450, -1, 30));

        fSLabel6.setBackground(java.awt.Color.red);
        fSLabel6.setText("");
        jPanel1.add(fSLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 450, 30, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 979, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jt_ReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_ReservasMouseClicked

        seleccion = jt_Reservas.rowAtPoint(evt.getPoint());
        lb_Id.setText(String.valueOf(jt_Reservas.getValueAt(seleccion, 0)));
        jt_nombre.setText(String.valueOf(jt_Reservas.getValueAt(seleccion, 1)));
        cb_Habitacion.getModel().setSelectedItem(jt_Reservas.getValueAt(seleccion, 2));
        lb_estadoCobro.setText(String.valueOf(jt_Reservas.getValueAt(seleccion, 5)));
        try {
            jd_Ingreso.setDate(dateFormat.parse((String) jt_Reservas.getValueAt(seleccion, 3).toString()));
            jd_Salida.setDate(dateFormat.parse((String) jt_Reservas.getValueAt(seleccion, 4).toString()));
            if (((String) jt_Reservas.getValueAt(seleccion, 4).toString()).equals(fechaActual) && lb_estadoCobro.getText().equals("Cobrado")) {

                //  btn_Eliminar.setEnabled(true);
                btn_Cobrar.setEnabled(false);
                btn_Modificar.setEnabled(false);
            } else if (lb_estadoCobro.getText().equals("Pendiente")) {
                //  btn_Eliminar.setEnabled(false);
                btn_Cobrar.setEnabled(true);
                btn_Modificar.setEnabled(true);
            }

        } catch (ParseException ex) {
            Logger.getLogger(Pn_Reservaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jt_ReservasMouseClicked

    private void jt_nombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_nombreFocusLost
        cft.formFocusLostJTextField(jt_nombre, "Ingresar Nombre");
        // cft.formFocusLostJTextField(cb_Habitacion);
    }//GEN-LAST:event_jt_nombreFocusLost

    private void jt_nombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_nombreMouseClicked
        cft.formFocusGain(jt_nombre);
    }//GEN-LAST:event_jt_nombreMouseClicked

    private void jt_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_nombreKeyTyped
        ce.typedCharsAndSpace(evt, jt_nombre);
    }//GEN-LAST:event_jt_nombreKeyTyped

    private void btn_IngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_IngresarMouseClicked

    }//GEN-LAST:event_btn_IngresarMouseClicked

    private void btn_IngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_IngresarActionPerformed

        try {
            if (!(validarEscritura() == true) || !(validarSeleccion() == true) || !validacionFechas() == true) {

                DesktopNotify.showDesktopMessage("Error", "REVISAR CAMPOS OBLIGATORIOS", DesktopNotify.ERROR);

            } else {

                cr.insertReservacion(jt_nombre.getText(), String.valueOf(cb_Habitacion.getSelectedItem()), dateFormat.format(jd_Ingreso.getDate()), dateFormat.format(jd_Salida.getDate()), Principal.User, "Pendiente");
                ch.updateHabitacion(String.valueOf(cb_Habitacion.getSelectedItem()), "Reservado");
                NewTable = new DefaultTableModel();
                cTabla();
                datosIniciales();
            }

        } catch (Exception e) {

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_IngresarActionPerformed

    private void btn_CobrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_CobrarMouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_CobrarMouseClicked

    private void btn_CobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CobrarActionPerformed
        if (!validarEscritura() && !validarSeleccion()) {
            DesktopNotify.showDesktopMessage("Error", "REVISAR CAMPOS OBLIGATORIOS", DesktopNotify.ERROR);

        } else {
            Pn_CobrarReservacion CobrarReserv = new Pn_CobrarReservacion(principal, true);

            CobrarReserv.lb_FolioReservaciones.setText(lb_Id.getText());
            CobrarReserv.lb_nombreCliente.setText(jt_nombre.getText());
            CobrarReserv.lb_NombreHabitacion.setText(String.valueOf(cb_Habitacion.getSelectedItem()));
            CobrarReserv.lb_FechaIngreso.setText(dateFormat.format(jd_Ingreso.getDate()));
            CobrarReserv.lb_FechaSalida.setText(dateFormat.format(jd_Salida.getDate()));
            for (ObjetoHabitacion habitacion : ch.selectHabitacion()) {
                for (ObjetoCategoria categoria : ccat.selectCategoria()) {
                    if (habitacion.getNombre().equals(String.valueOf(cb_Habitacion.getSelectedItem()))) {
                        if (habitacion.getIdCategoria() == categoria.getIdCategoria()) {
                            CobrarReserv.lb_PrecioHabitacion.setText(String.valueOf(habitacion.getPrecioSugerido()));
                            CobrarReserv.lb_TipoHabitacion.setText(String.valueOf(categoria.getNombre()));
                            CobrarReserv.lb_precioxHora.setText(String.valueOf(habitacion.getPrecioxHora()));

                        }

                    }
                }

            }

            CobrarReserv.setVisible(true);
            datosIniciales();
        }

    }//GEN-LAST:event_btn_CobrarActionPerformed

    private void btn_ModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ModificarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ModificarMouseClicked

    private void cb_HabitacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_HabitacionItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_HabitacionItemStateChanged

    private void cb_HabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_HabitacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_HabitacionActionPerformed

    private void btn_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_clientesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_clientesMouseClicked

    private void btn_clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clientesActionPerformed
        Pn_SeleccionarClientes select = new Pn_SeleccionarClientes(principal, true);
        select.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_clientesActionPerformed

    private void btn_EliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_EliminarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_EliminarMouseClicked

    private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarActionPerformed
        try {
            if (!validarEscritura() == true && !validarSeleccion() == true) {

                DesktopNotify.showDesktopMessage("Error", "REVISAR CAMPOS OBLIGATORIOS", DesktopNotify.ERROR);

            } else {

                Pn_Alert_Eliminar ale = new Pn_Alert_Eliminar(principal, true);
                ale.lb_titulo.setText("¿Esta seguro de eliminar este elemento?");
                ale.jc_eliminarParaFinalizarReservacion.setVisible(true);
                ale.jc_seleccionarErrorHumano.setVisible(true);
                ale.jb_aceptar.setEnabled(false);
                //sirve para validar con estatus 
                // 0= desactiva el boton de aceptar
                //1= significa que fue error humano y solo se elimina el elemento de reservacion
                //2= significa que se esta terminando una reservacion 
                ale.jb_aceptar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        if (ale.validarError == 1) {
                            cr.deleteReservacion(Integer.valueOf(lb_Id.getText()));
                            ch.updateHabitacion(String.valueOf(cb_Habitacion.getSelectedItem()), "Disponible");

                            tamañoTabla();
                            NewTable = new DefaultTableModel();
                            cTabla();
                            datosIniciales();
                            ale.dispose();
                        } else if (ale.validarError == 2) {
                            cr.deleteReservacion(Integer.valueOf(lb_Id.getText()));
                            //DesktopNotify.showDesktopMessage("Exito", "Datos del piso " + jt_nombre.getText() + " eliminados con éxito.", DesktopNotify.SUCCESS);
                            ch.updateHabitacion(String.valueOf(cb_Habitacion.getSelectedItem()), "Limpieza");
                            tamañoTabla();
                            NewTable = new DefaultTableModel();
                            cTabla();
                            datosIniciales();
                            ale.dispose();
                        }

                    }
                });
                ale.setVisible(true);
            }

        } catch (Exception e) {

        }
    }//GEN-LAST:event_btn_EliminarActionPerformed

    private void btn_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModificarActionPerformed

        try {
            if (!validarEscritura() == true || !validarSeleccion() == true) {

                DesktopNotify.showDesktopMessage("Error", "REVISAR CAMPOS OBLIGATORIOS", DesktopNotify.ERROR);

            } else {

                cr.updateReservacion(jt_nombre.getText(), String.valueOf(cb_Habitacion.getSelectedItem()), dateFormat.format(jd_Ingreso.getDate()), dateFormat.format(jd_Salida.getDate()), "Pendiente", Integer.valueOf(lb_Id.getText()));
                NewTable = new DefaultTableModel();
                cTabla();
                datosIniciales();
            }

        } catch (Exception e) {

        }
    }//GEN-LAST:event_btn_ModificarActionPerformed

    private void jt_nombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_nombreFocusGained
        cft.formFocusGain(jt_nombre);
    }//GEN-LAST:event_jt_nombreFocusGained

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
        jt_Reservas.setModel(cr.tablaReservaciones(jt_Buscar));
        tamañoTabla();

    }//GEN-LAST:event_jt_BuscarKeyTyped

    private void jd_IngresoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jd_IngresoPropertyChange

    }//GEN-LAST:event_jd_IngresoPropertyChange

    private void jd_SalidaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jd_SalidaPropertyChange

    }//GEN-LAST:event_jd_SalidaPropertyChange

    private void jb_limpiarCampos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_limpiarCampos2ActionPerformed
        datosIniciales();      // TODO add your handling code here:
    }//GEN-LAST:event_jb_limpiarCampos2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private principal.MaterialButton btn_Cobrar;
    public static principal.MaterialButton btn_Eliminar;
    public static principal.MaterialButton btn_Ingresar;
    public static principal.MaterialButton btn_Modificar;
    private principal.MaterialButton btn_clientes;
    private javax.swing.JComboBox<String> cb_Habitacion;
    private LIB.FSLabel fSLabel1;
    private LIB.FSLabel fSLabel2;
    private LIB.FSLabel fSLabel3;
    private LIB.FSLabel fSLabel4;
    private LIB.FSLabel fSLabel5;
    private LIB.FSLabel fSLabel6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JButton jb_limpiarCampos2;
    private com.toedter.calendar.JDateChooser jd_Ingreso;
    private com.toedter.calendar.JDateChooser jd_Salida;
    public static javax.swing.JTextField jt_Buscar;
    public static javax.swing.JTable jt_Reservas;
    public static javax.swing.JTextField jt_nombre;
    private javax.swing.JLabel jt_t_registros;
    private javax.swing.JLabel lb_Id;
    private javax.swing.JLabel lb_errorFechaIngreso;
    private javax.swing.JLabel lb_errorFechaSalida;
    private javax.swing.JLabel lb_errorHabitacion;
    private javax.swing.JLabel lb_errorNombre;
    private javax.swing.JLabel lb_estadoCobro;
    // End of variables declaration//GEN-END:variables
}
