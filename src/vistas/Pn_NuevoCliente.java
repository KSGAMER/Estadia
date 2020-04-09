package vistas;


import vistas.Alertas.Pn_Alert_Eliminar;
import controladores.ControladorCFDI;
import controladores.ControladorClientes;
import controladores.ControladorEscritura;
import controladores.ControladorFormularioTab;
import controladores.ValidadorDePrivilegios.ControladorPrivilegiosCliente;
import ds.desktop.notify.DesktopNotify;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import objetos.ObjetoCFDI;

/**
 *
 * @author RojeruSan
 */
public class Pn_NuevoCliente extends javax.swing.JPanel {
//NECESARIO PARA FUNCIONES DE ESTE MODULO 
    private ControladorClientes mc = new ControladorClientes();
    private ControladorCFDI cf = new ControladorCFDI();
    private ControladorEscritura ce = new ControladorEscritura();
    private ControladorFormularioTab cft = new ControladorFormularioTab();

    DefaultTableModel NewTable;
    private int seleccion;
    //FIN
  
   //NECESARIO PARA EXTRAER LOS PRIVILEGIOS DENTRO DE ESTE MODULO, EN FUNCION AL USUARIO ACTUAL 
    ControladorPrivilegiosCliente analisis = new ControladorPrivilegiosCliente();
    //FIN
    //NECESARIO PARA HACER LA COMPRACION Y EXTRACCION DE LOS PRIVILEGIOS DE ESTE MODULO
    private String NombreModulo = "Clientes";
    //FIN
    //NECESARIO PARA EL USO DE LA NOTIFICACION DINAMICA DE BOTON ELIMINAR ()
    Frame Principal;
//FIN
     private int limiteRFC = 13;
    /**
     * Creates new form pnlHome
     */
    public Pn_NuevoCliente() {

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
        //NECESARIO PARA CARGAR LOS VALORES EN EL COMBOBOX DEL CFDI
        cf.tablaCFDI();
        cargarCFDI();
        //FIN
        //RECORDATORIO 
        DesktopNotify.showDesktopMessage("Recordatorio", "La dirección fiscal debe contener los siguientes datos \n"
                + "Calle , Num Int y/o Ext, Colonia, Codigo Postal, Ciudad, Municipio, Estado  ", DesktopNotify.TIP);
    }

    private void cTabla() {
        jt_Clientes.setModel(mc.tablaClientes());
        jt_t_registros.setText(String.valueOf(jt_Clientes.getRowCount()));
    }

    private void cargarCFDI() {
        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        cb.addElement("Seleccionar CFDI");
        for (ObjetoCFDI campos : cf.selectCFDI()) {
            cb.addElement(campos.getNombre());

        }
        cb_cfdi.setModel(cb);

    }

    private void tamañoTabla() {
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
    private void RowApariencia() {

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

    private void RowHeaderApariencia() {
        jt_Clientes.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 14));
        jt_Clientes.getTableHeader().setOpaque(false);
        jt_Clientes.getTableHeader().setBackground(new Color(32, 136, 203));
        jt_Clientes.getTableHeader().setForeground(new Color(255, 255, 255));

    }

    private void datosIniciales() {
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
        btn_Modificar.setEnabled(false);
        btn_Eliminar.setEnabled(false);
        btn_Ingresar.setEnabled(true);
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
        if (!(jt_rfc.getText().equals("Ingresar RFC")) && !(jt_rfc.getText().equals(""))) {
            lb_errorRFC.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorRFC.setForeground(Color.RED);
            val = false;
        }
        //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(jt_direccion.getText().equals("Ingresar Direccion Fiscal")) && !(jt_direccion.getText().equals(""))) {
            lb_errorDireccion.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorDireccion.setForeground(Color.RED);
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
        if (!(jt_email.getText().equals("Ingresar Email")) && !(jt_email.getText().equals(""))) {
            lb_errorEmail.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorEmail.setForeground(Color.RED);
            val = false;
        }

        return val;
    }

    private Boolean validarSeleccion() {
        Boolean val = true;
        if (!(cb_cfdi.getSelectedIndex() == 0)) {

            lb_errorCFDI.setForeground(new Color(84, 110, 122));
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
        jt_t_registros = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btn_Ingresar = new principal.MaterialButton();
        btn_Eliminar = new principal.MaterialButton();
        btn_Modificar = new principal.MaterialButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lb_Id = new javax.swing.JLabel();
        lb_errorCFDI = new javax.swing.JLabel();
        lb_errorNombre = new javax.swing.JLabel();
        lb_errorRFC = new javax.swing.JLabel();
        lb_errorDireccion = new javax.swing.JLabel();
        lb_errorTelefono = new javax.swing.JLabel();
        lb_errorEmail = new javax.swing.JLabel();
        jt_Buscar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jb_limpiarCampos2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(84, 110, 122));
        setForeground(new java.awt.Color(255, 255, 255));
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(1086, 684));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, 720, 420));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Nombre y/o Razon Social ");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 190, -1));

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
        jPanel1.add(jt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 170, -1));

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Registro Federal de Controbuyentes (RFC)");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 250, -1));

        jt_rfc.setBackground(new java.awt.Color(84, 110, 122));
        jt_rfc.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jt_rfc.setForeground(new java.awt.Color(204, 204, 204));
        jt_rfc.setText("Ingresar RFC");
        jt_rfc.setBorder(null);
        jt_rfc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jt_rfcFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_rfcFocusLost(evt);
            }
        });
        jt_rfc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_rfcMouseClicked(evt);
            }
        });
        jt_rfc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_rfcKeyTyped(evt);
            }
        });
        jPanel1.add(jt_rfc, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 170, -1));

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Direccion Fiscal ");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 190, -1));

        jt_direccion.setBackground(new java.awt.Color(84, 110, 122));
        jt_direccion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jt_direccion.setForeground(new java.awt.Color(204, 204, 204));
        jt_direccion.setText("Ingresar Direccion Fiscal");
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
        jPanel1.add(jt_direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 170, -1));

        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("Telefono");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 190, -1));

        jt_telefono.setBackground(new java.awt.Color(84, 110, 122));
        jt_telefono.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
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
        jPanel1.add(jt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 170, -1));

        Celular1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        Celular1.setForeground(new java.awt.Color(255, 255, 255));
        Celular1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Celular1.setText("Correo Electronico (Email)");
        jPanel1.add(Celular1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 190, -1));

        jt_email.setBackground(new java.awt.Color(84, 110, 122));
        jt_email.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jt_email.setForeground(new java.awt.Color(204, 204, 204));
        jt_email.setText("Ingresar Email");
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
        jPanel1.add(jt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 190, -1));

        Celular2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        Celular2.setForeground(new java.awt.Color(255, 255, 255));
        Celular2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Celular2.setText("Uso de CFDI");
        jPanel1.add(Celular2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 140, -1));

        cb_cfdi.setBackground(new java.awt.Color(84, 110, 122));
        cb_cfdi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cb_cfdi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar CFDI" }));
        cb_cfdi.setBorder(null);
        cb_cfdi.setFocusable(false);
        cb_cfdi.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_cfdiItemStateChanged(evt);
            }
        });
        cb_cfdi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cb_cfdiFocusLost(evt);
            }
        });
        jPanel1.add(cb_cfdi, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 190, -1));

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 190, 10));

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 190, 10));

        jSeparator6.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 190, 10));

        jSeparator9.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 190, 10));

        jSeparator10.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 190, 10));

        jt_t_registros.setEditable(false);
        jt_t_registros.setBackground(new java.awt.Color(84, 110, 122));
        jt_t_registros.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jt_t_registros.setForeground(new java.awt.Color(255, 255, 255));
        jt_t_registros.setBorder(null);
        jPanel1.add(jt_t_registros, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 590, 30, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Total de registros ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 590, 100, 20));

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
        jPanel1.add(btn_Ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 100, 40));

        btn_Eliminar.setBackground(new java.awt.Color(211, 18, 18));
        btn_Eliminar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Eliminar.setText("eliminar");
        btn_Eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Eliminar.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 500, 100, 40));

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
        jPanel1.add(btn_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 500, 95, 40));

        jPanel2.setBackground(new java.awt.Color(84, 110, 122));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("VISTA GENERAL DE CLIENTES");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Inicio > Clientes > Nuevo Cliente");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 450, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(87, 87, 87))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 50));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1090, 10));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Formulario de Cambios");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Detalle de Clientes");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, -1, -1));

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

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/campo-buscar.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 110, -1, 40));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1065, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jt_rfcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_rfcKeyTyped
        //ce.typedCharsAndDigits(evt, jt_rfc);
            char tecla;
        tecla = evt.getKeyChar();
        //Convertir a letras mayusculas
        if (Character.isLetter(tecla)) {
            evt.setKeyChar(Character.toUpperCase(tecla));

        }
          if (jt_rfc.getText().length() == limiteRFC) {
            evt.consume();
        }
    }//GEN-LAST:event_jt_rfcKeyTyped

    private void jt_rfcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_rfcMouseClicked
        cft.formFocusGain(jt_rfc);
    }//GEN-LAST:event_jt_rfcMouseClicked

    private void jt_rfcFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_rfcFocusLost
        cft.formFocusLostJTextField(jt_rfc, "Ingresar RFC");
    }//GEN-LAST:event_jt_rfcFocusLost

    private void jt_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_nombreKeyTyped
        ce.typedCharsAndSpaceAndDigits(evt, jt_nombre);
    }//GEN-LAST:event_jt_nombreKeyTyped

    private void jt_nombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_nombreMouseClicked
        cft.formFocusGain(jt_nombre);
    }//GEN-LAST:event_jt_nombreMouseClicked

    private void jt_nombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_nombreFocusLost
        cft.formFocusLostJTextField(jt_nombre, "Ingresar Nombre");
    }//GEN-LAST:event_jt_nombreFocusLost

    private void jt_direccionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_direccionFocusLost
        cft.formFocusLostJTextField(jt_direccion, "Ingresar Dirección Fiscal");
    }//GEN-LAST:event_jt_direccionFocusLost

    private void jt_direccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_direccionMouseClicked
        cft.formFocusGain(jt_direccion);
    }//GEN-LAST:event_jt_direccionMouseClicked

    private void jt_direccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_direccionKeyTyped
        ce.typedAddress(evt, jt_direccion);
    }//GEN-LAST:event_jt_direccionKeyTyped

    private void jt_telefonoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_telefonoFocusLost
        cft.formFocusLostJTextField(jt_telefono, "Ingresar Telefono");
    }//GEN-LAST:event_jt_telefonoFocusLost

    private void jt_telefonoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_telefonoMouseClicked
        cft.formFocusGain(jt_telefono);
    }//GEN-LAST:event_jt_telefonoMouseClicked

    private void jt_telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_telefonoKeyTyped
        ce.typedDigits(evt, jt_telefono);
    }//GEN-LAST:event_jt_telefonoKeyTyped

    private void jt_emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_emailFocusLost
        cft.formFocusLostJTextField(jt_email, "Ingresar Email");
    }//GEN-LAST:event_jt_emailFocusLost

    private void jt_emailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_emailMouseClicked
        cft.formFocusGain(jt_email);
    }//GEN-LAST:event_jt_emailMouseClicked

    private void jt_emailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_emailKeyTyped
        ce.typedEmail(evt, jt_email);
    }//GEN-LAST:event_jt_emailKeyTyped

    private void cb_cfdiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_cfdiItemStateChanged
      /*  if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.idCfdi = cb_cfdi.getSelectedIndex();
        }*/
    }//GEN-LAST:event_cb_cfdiItemStateChanged

    private void jt_ClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_ClientesMouseClicked
        seleccion = jt_Clientes.rowAtPoint(evt.getPoint());
        btn_Modificar.setEnabled(true);
        btn_Eliminar.setEnabled(true);
        btn_Ingresar.setEnabled(false);
        lb_Id.setText(String.valueOf(jt_Clientes.getValueAt(seleccion, 0)));
        jt_nombre.setText(String.valueOf(jt_Clientes.getValueAt(seleccion, 1)));
        jt_rfc.setText(String.valueOf(jt_Clientes.getValueAt(seleccion, 2)));
        jt_direccion.setText(String.valueOf(jt_Clientes.getValueAt(seleccion, 3)));
        jt_telefono.setText(String.valueOf(jt_Clientes.getValueAt(seleccion, 4)));
        jt_email.setText(String.valueOf(jt_Clientes.getValueAt(seleccion, 5)));

        cb_cfdi.getModel().setSelectedItem(jt_Clientes.getValueAt(seleccion, 6));
    }//GEN-LAST:event_jt_ClientesMouseClicked

    private void btn_IngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_IngresarActionPerformed

        try {
            if (!validarEscritura() == true || !validarSeleccion() == true) {

                DesktopNotify.showDesktopMessage("Error", "REVISAR CAMPOS OBLIGATORIOS", DesktopNotify.ERROR);

            } else {

                mc.insertCliente(jt_nombre.getText(), jt_rfc.getText(), jt_direccion.getText(), jt_telefono.getText(), jt_email.getText(), String.valueOf(cb_cfdi.getSelectedItem()));
                //DesktopNotify.showDesktopMessage("Exito", "Datos del cliente " + jt_nombre.getText() + " agregados con éxito.", DesktopNotify.SUCCESS);
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
            if (!validarEscritura() == true || !validarSeleccion() == true) {

                DesktopNotify.showDesktopMessage("Error", "REVISAR CAMPOS OBLIGATORIOS", DesktopNotify.ERROR);

            } else {

                mc.updateCliente(jt_nombre.getText(), jt_rfc.getText(), jt_direccion.getText(), jt_telefono.getText(), jt_email.getText(), String.valueOf(cb_cfdi.getSelectedItem()), Integer.valueOf(lb_Id.getText()));
                //DesktopNotify.showDesktopMessage("Exito", "Datos del cliente " + jt_nombre.getText() + " actualizados con éxito.", DesktopNotify.SUCCESS);
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

            
                Pn_Alert_Eliminar ale = new Pn_Alert_Eliminar(Principal, true);
                ale.lb_titulo.setText("¿Esta seguro de eliminar este elemento?");
                ale.jb_aceptar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        mc.deleteCliente(Integer.valueOf(lb_Id.getText()));
                        //DesktopNotify.showDesktopMessage("Exito", "Datos del cliente " + jt_nombre.getText() + " eliminados con éxito.", DesktopNotify.SUCCESS);

                        tamañoTabla();
                        NewTable = new DefaultTableModel();
                        cTabla();
                        datosIniciales();

                    }
                });
                ale.setVisible(true);

            }

        } catch (Exception e) {

  
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btn_EliminarActionPerformed

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
    }//GEN-LAST:event_jt_BuscarKeyTyped

    private void jt_BuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_BuscarKeyReleased
        jt_Clientes.setModel(mc.tablaClientes(jt_Buscar));
        tamañoTabla();
    }//GEN-LAST:event_jt_BuscarKeyReleased

    private void jt_nombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_nombreFocusGained
        cft.formFocusGain(jt_nombre);
    }//GEN-LAST:event_jt_nombreFocusGained

    private void jt_rfcFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_rfcFocusGained
        cft.formFocusGain(jt_rfc);
    }//GEN-LAST:event_jt_rfcFocusGained

    private void jt_direccionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_direccionFocusGained
        cft.formFocusGain(jt_direccion);
    }//GEN-LAST:event_jt_direccionFocusGained

    private void jt_telefonoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_telefonoFocusGained
        cft.formFocusGain(jt_telefono);
    }//GEN-LAST:event_jt_telefonoFocusGained

    private void jt_emailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_emailFocusGained
        cft.formFocusGain(jt_email);
    }//GEN-LAST:event_jt_emailFocusGained

    private void jt_BuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_BuscarFocusGained
        cft.formFocusGain(jt_Buscar);
    }//GEN-LAST:event_jt_BuscarFocusGained

    private void cb_cfdiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_cfdiFocusLost
        cft.formFocusLostJTextField(jt_Buscar);
    }//GEN-LAST:event_cb_cfdiFocusLost

    private void jb_limpiarCampos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_limpiarCampos2ActionPerformed
        datosIniciales();        // TODO add your handling code here:
    }//GEN-LAST:event_jb_limpiarCampos2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Celular1;
    private javax.swing.JLabel Celular2;
    public static principal.MaterialButton btn_Eliminar;
    public static principal.MaterialButton btn_Ingresar;
    public static principal.MaterialButton btn_Modificar;
    private javax.swing.JComboBox<String> cb_cfdi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JButton jb_limpiarCampos2;
    public static javax.swing.JTextField jt_Buscar;
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
    // End of variables declaration//GEN-END:variables
}
