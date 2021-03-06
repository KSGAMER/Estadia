/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import vistas.Alertas.Pn_Alert_Eliminar;
import controladores.ControladorCategorias;
import controladores.ControladorEscritura;
import controladores.ControladorEstatusHabitaciones;
import controladores.ControladorFormularioTab;
import controladores.ControladorHabitaciones;
import controladores.ControladorPisos;
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
import objetos.ObjetoCategoria;
import objetos.ObjetoPiso;
import objetos.ObjetoEstadoHabitacion;

/**
 *
 * @author fenix
 */
public class Pn_NuevaHabitacion extends javax.swing.JPanel {
//NECESARIO PARA FUNCIONES DE ESTE MODULO 

    ControladorHabitaciones ch = new ControladorHabitaciones();
    ControladorPisos cp = new ControladorPisos();
    ControladorCategorias ccat = new ControladorCategorias();
    ControladorEstatusHabitaciones ceh = new ControladorEstatusHabitaciones();
    ControladorEscritura ce = new ControladorEscritura();
    private ControladorFormularioTab cft = new ControladorFormularioTab();
    DefaultTableModel NewTable;
    //FIN
    /*
    private int idPiso, idCategoria, idStatus;
     */
    //FIN

    //NECESARIO PARA EL USO DE LA NOTIFICACION DINAMICA DE BOTON ELIMINAR ()
    Frame Principal;
//FIN

    /**
     * Creates new form Pn_NuevaHabitaion
     */
    public Pn_NuevaHabitacion() {

        initComponents();
        //APARIENCIA DE LA TABLA
        RowApariencia();
        RowHeaderApariencia();
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
        //NECESARIOS PARA CARGAR LOS DATOS DE LOS METODOS CARGARPISO, CATEGORIA, STATUS
        ccat.tablaCategorias();
        cp.tablaPisos();
        ceh.tablaEstadoHabiaciones();
        //FIN
        //CARGAN LOS DATOS DENTRO DE LOS COMBOBOX
        cargarPisos();
        cargarCategorias();
        cargarStatus();
        //FIN
        //para ajustar el tecto al tamaño del jtextarea
        jta_observaciones.setLineWrap(true);
        //FIN
    }

    private void cTabla() {
        jt_habitaciones.setModel(ch.tablaHabitaciones());
        jt_t_registros.setText(String.valueOf(jt_habitaciones.getRowCount()));
    }

    private void tamañoTabla() {
        TableColumnModel columnModel = jt_habitaciones.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(5);
        columnModel.getColumn(1).setPreferredWidth(50);
        columnModel.getColumn(2).setPreferredWidth(70);
        columnModel.getColumn(3).setPreferredWidth(70);
        columnModel.getColumn(4).setPreferredWidth(40);
        columnModel.getColumn(5).setPreferredWidth(40);
        columnModel.getColumn(6).setPreferredWidth(200);
        columnModel.getColumn(7).setPreferredWidth(30);
    }

    private void cargarPisos() {

        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        cb.addElement("Seleccionar Piso");
        for (ObjetoPiso campos : cp.selectPiso()) {
            cb.addElement(campos.getNombre());
        }
        cb_piso.setModel(cb);

    }

    private void cargarCategorias() {
        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        cb.addElement("Seleccionar Categoria");
        for (ObjetoCategoria campos : ccat.selectCategoria()) {

            cb.addElement(campos.getNombre());
        }
        cb_Categoria.setModel(cb);
    }

    private void cargarStatus() {
        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        cb.addElement("Seleccionar Estatus");
        for (ObjetoEstadoHabitacion campos : ceh.selectEstadoHabitacion()) {

            cb.addElement(campos.getNombre());
        }
        cb_status.setModel(cb);
    }

    /*public void cargarTabla() {
        DefaultTableModel NewTable = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        ArrayList<Object> columna = new ArrayList<Object>();
        columna.add("Clave");
        columna.add("Habitación");
        columna.add("Piso");
        columna.add("Categoria");
        columna.add("Precio Sugerido");
        columna.add("Caracteristicas");

        for (Object col : columna) {
            NewTable.addColumn(col);
        }
        this.jt_habitaciones.setModel(NewTable);

        Object[] fila = new Object[jt_habitaciones.getColumnCount()];
        for (int i = 0; i < ch.selectHabitacion().size(); i++) {
            fila[0] = ch.selectHabitacion().get(i).getIdHabitacion();
            fila[1] = ch.selectHabitacion().get(i).getNombre();
            for (int j = 0; j < cp.selectPiso().size(); j++) {
                if(cp.selectPiso().get(j).getIdPiso() == ch.selectHabitacion().get(i).getIdPiso()) {
                    fila[2] = cp.selectPiso().get(j).getNombre();
                }
            }
            for (int j = 0; j < ccat.selectCategoria().size(); j++) {
               // if(ccat.selectCategoria().get(j).getIdCategoria() == ch.selectHabitacion().get(i).getIdCategoria()) {
                    fila[3] = ccat.selectCategoria().get(j).getNombre();
              //  }
            }
            fila[4] = ch.selectHabitacion().get(i).getPrecioSugerido();
            fila[5] = ch.selectHabitacion().get(i).getCaracteristicas();
            NewTable.addRow(fila);
        }
        this.jt_habitaciones.setModel(NewTable);
    }*/
    private void RowApariencia() {
        jt_habitaciones.setFocusable(false);
        jt_habitaciones.setIntercellSpacing(new Dimension(0, 1));
        jt_habitaciones.setRowHeight(25);
        jt_habitaciones.setRowMargin(0);
        jt_habitaciones.setShowVerticalLines(false);
        jt_habitaciones.setSelectionBackground(new Color(97, 212, 195));
    }

    public void RowHeaderApariencia() {
        jt_habitaciones.getTableHeader().setFont(new Font("Centaury Gotich", Font.BOLD, 14));
        jt_habitaciones.getTableHeader().setOpaque(false);
        jt_habitaciones.getTableHeader().setBackground(new Color(32, 136, 203));
        jt_habitaciones.getTableHeader().setForeground(new Color(255, 255, 255));
    }

    private void datosIniciales() {
        lb_Id.setText("*");
        jt_nombre.setText("Ingresar Nombre");
        jt_precioxNoche.setText("Ingresar Precio");
        jt_precioxHora.setText("Ingresar Precio");
        jta_observaciones.setText("Ingresar Caracteristicas");
        cb_piso.setSelectedIndex(0);
        cb_status.setSelectedIndex(0);
        cb_Categoria.setSelectedIndex(0);
        btn_Modificar.setEnabled(false);
        btn_Eliminar.setEnabled(false);
        btn_Ingresar.setEnabled(true);

        
        
        lb_errorNombre.setForeground(new Color(84, 110, 122));
        lb_errorPrecioxNoche.setForeground(new Color(84, 110, 122));
        lb_errorPrecioxHora.setForeground(new Color(84, 110, 122));
        lb_errorCaracteristicas.setForeground(new Color(84, 110, 122));
        lb_errorPiso.setForeground(new Color(84, 110, 122));
        lb_errorCategoria.setForeground(new Color(84, 110, 122));
        lb_errorStatus.setForeground(new Color(84, 110, 122));
    

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
        if (!(jt_precioxNoche.getText().equals("Ingresar Precio")) && !(jt_precioxNoche.getText().equals(""))) {
            lb_errorPrecioxNoche.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorPrecioxNoche.setForeground(Color.RED);
            val = false;
        }
         //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(jt_precioxHora.getText().equals("Ingresar Precio")) && !(jt_precioxNoche.getText().equals(""))) {
            lb_errorPrecioxHora.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorPrecioxHora.setForeground(Color.RED);
            val = false;
        }
        //si el textfield tiene algo diferente a Vacío aparecerá de color negro
        if (!(jta_observaciones.getText().equals("Ingresar Caracteristicas")) && !(jta_observaciones.getText().equals(""))) {
            lb_errorCaracteristicas.setForeground(new Color(84, 110, 122));
        } else {
            lb_errorCaracteristicas.setForeground(Color.RED);
            val = false;
        }

        return val;
    }

    private Boolean validarSeleccion() {
        Boolean val = true;
        if (!(cb_piso.getSelectedIndex()==0)) {

            lb_errorPiso.setForeground(new Color(84, 110, 122));
        } else {

            lb_errorPiso.setForeground(Color.RED);

            val = false;
        }
        if (!(cb_Categoria.getSelectedIndex()==0)) {

            lb_errorCategoria.setForeground(new Color(84, 110, 122));
        } else {

            lb_errorCategoria.setForeground(Color.RED);

            val = false;
        }

        if (!(cb_status.getSelectedIndex()==0)) {

            lb_errorStatus.setForeground(new Color(84, 110, 122));
        } else {

            lb_errorStatus.setForeground(Color.RED);

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
        jt_precioxHora = new javax.swing.JTextField();
        lb_errorPrecioxHora = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_habitaciones = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jt_nombre = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jt_precioxNoche = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jta_observaciones = new javax.swing.JTextArea();
        cb_piso = new javax.swing.JComboBox<>();
        cb_status = new javax.swing.JComboBox<>();
        btn_Ingresar = new principal.MaterialButton();
        btn_Modificar = new principal.MaterialButton();
        btn_Eliminar = new principal.MaterialButton();
        jLabel11 = new javax.swing.JLabel();
        cb_Categoria = new javax.swing.JComboBox<>();
        lb_Id = new javax.swing.JLabel();
        lb_errorNombre = new javax.swing.JLabel();
        lb_errorPiso = new javax.swing.JLabel();
        lb_errorCategoria = new javax.swing.JLabel();
        lb_errorPrecioxNoche = new javax.swing.JLabel();
        lb_errorCaracteristicas = new javax.swing.JLabel();
        lb_errorStatus = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jt_t_registros = new javax.swing.JTextField();
        jt_Buscar = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jb_limpiarCampos2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();

        jPanel1.setBackground(new java.awt.Color(84, 110, 122));
        jPanel1.setFocusable(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jt_precioxHora.setBackground(new java.awt.Color(84, 110, 122));
        jt_precioxHora.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jt_precioxHora.setForeground(new java.awt.Color(204, 204, 204));
        jt_precioxHora.setText("Ingresar Precio");
        jt_precioxHora.setBorder(null);
        jt_precioxHora.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jt_precioxHoraFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_precioxHoraFocusLost(evt);
            }
        });
        jt_precioxHora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_precioxHoraMouseClicked(evt);
            }
        });
        jt_precioxHora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_precioxHoraKeyTyped(evt);
            }
        });
        jPanel1.add(jt_precioxHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 570, 170, -1));

        lb_errorPrecioxHora.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorPrecioxHora.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorPrecioxHora.setText("*");
        jPanel1.add(lb_errorPrecioxHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 10, -1));

        jPanel2.setBackground(new java.awt.Color(84, 110, 122));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("VISTA GENERAL DE HABITACIONES");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Inicio > Hotel > Nueva Habitación");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 367, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(48, 48, 48))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel14))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 50));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Detalle de Habitaciones");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Formulario de Cambios");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 320, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1030, 10));

        jt_habitaciones.setModel(new javax.swing.table.DefaultTableModel(
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
        jt_habitaciones.setEditingColumn(1);
        jt_habitaciones.setEditingRow(1);
        jt_habitaciones.getTableHeader().setResizingAllowed(false);
        jt_habitaciones.getTableHeader().setReorderingAllowed(false);
        jt_habitaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_habitacionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jt_habitaciones);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 960, 170));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre de la Habitación :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Piso de la Habitación :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Estatus:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 370, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Precio Por Hora :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 550, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Caracteristicas de la Habitación :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 430, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("$");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, -1, -1));

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
        jPanel1.add(jt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 190, -1));

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 190, 10));

        jt_precioxNoche.setBackground(new java.awt.Color(84, 110, 122));
        jt_precioxNoche.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jt_precioxNoche.setForeground(new java.awt.Color(204, 204, 204));
        jt_precioxNoche.setText("Ingresar Precio");
        jt_precioxNoche.setBorder(null);
        jt_precioxNoche.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jt_precioxNocheFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_precioxNocheFocusLost(evt);
            }
        });
        jt_precioxNoche.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_precioxNocheMouseClicked(evt);
            }
        });
        jt_precioxNoche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_precioxNocheKeyTyped(evt);
            }
        });
        jPanel1.add(jt_precioxNoche, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 520, 170, -1));

        jSeparator7.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 540, 170, 10));

        jta_observaciones.setColumns(20);
        jta_observaciones.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jta_observaciones.setForeground(new java.awt.Color(153, 153, 153));
        jta_observaciones.setRows(5);
        jta_observaciones.setText("Ingresar Caracteristicas");
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
        jPanel1.add(jta_observaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 460, 310, 140));

        cb_piso.setBackground(new java.awt.Color(84, 110, 122));
        cb_piso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Piso" }));
        cb_piso.setBorder(null);
        cb_piso.setFocusable(false);
        cb_piso.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_pisoItemStateChanged(evt);
            }
        });
        jPanel1.add(cb_piso, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 190, -1));

        cb_status.setBackground(new java.awt.Color(84, 110, 122));
        cb_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Estatus" }));
        cb_status.setBorder(null);
        cb_status.setFocusable(false);
        cb_status.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_statusItemStateChanged(evt);
            }
        });
        jPanel1.add(cb_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 400, 190, -1));

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
        jPanel1.add(btn_Ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 370, 170, 40));

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
        jPanel1.add(btn_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 440, 170, 40));

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
        jPanel1.add(btn_Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 510, 170, 40));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Categoria de la Habitación :");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, -1, -1));

        cb_Categoria.setBackground(new java.awt.Color(84, 110, 122));
        cb_Categoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Categoria" }));
        cb_Categoria.setBorder(null);
        cb_Categoria.setFocusable(false);
        cb_Categoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_CategoriaItemStateChanged(evt);
            }
        });
        jPanel1.add(cb_Categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 190, -1));

        lb_Id.setForeground(new java.awt.Color(84, 110, 122));
        jPanel1.add(lb_Id, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 330, 30, 20));

        lb_errorNombre.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorNombre.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorNombre.setText("*");
        jPanel1.add(lb_errorNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 10, -1));

        lb_errorPiso.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorPiso.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorPiso.setText("*");
        jPanel1.add(lb_errorPiso, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 10, -1));

        lb_errorCategoria.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorCategoria.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorCategoria.setText("*");
        jPanel1.add(lb_errorCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 10, -1));

        lb_errorPrecioxNoche.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorPrecioxNoche.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorPrecioxNoche.setText("*");
        jPanel1.add(lb_errorPrecioxNoche, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 10, -1));

        lb_errorCaracteristicas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorCaracteristicas.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorCaracteristicas.setText("*");
        jPanel1.add(lb_errorCaracteristicas, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 430, 10, -1));

        lb_errorStatus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorStatus.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorStatus.setText("*");
        jPanel1.add(lb_errorStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, 10, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Total de registros ");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 320, 100, 20));

        jt_t_registros.setEditable(false);
        jt_t_registros.setBackground(new java.awt.Color(84, 110, 122));
        jt_t_registros.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jt_t_registros.setForeground(new java.awt.Color(255, 255, 255));
        jt_t_registros.setBorder(null);
        jPanel1.add(jt_t_registros, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 320, 30, 20));

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
        jPanel1.add(jt_Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 100, 150, 20));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/campo-buscar.png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 90, -1, 40));

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
        jPanel1.add(jb_limpiarCampos2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 330, 40, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Precio Por Noche :");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("$");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 570, -1, -1));

        jSeparator8.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 590, 170, 10));

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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
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

    private void jt_precioxNocheFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_precioxNocheFocusLost
        cft.formFocusLostJTextField(jt_precioxNoche, "Ingresar Precio");
    }//GEN-LAST:event_jt_precioxNocheFocusLost

    private void jt_precioxNocheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_precioxNocheMouseClicked
      cft.formFocusGain(jt_precioxNoche);
    }//GEN-LAST:event_jt_precioxNocheMouseClicked

    private void jt_precioxNocheKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_precioxNocheKeyTyped

        ce.typedMoney(evt, jt_precioxNoche);
    }//GEN-LAST:event_jt_precioxNocheKeyTyped

    private void jta_observacionesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jta_observacionesFocusLost
        cft.formFocusLostJTextArea(jta_observaciones, "Ingresar Caracteristicas");
    }//GEN-LAST:event_jta_observacionesFocusLost

    private void jta_observacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jta_observacionesMouseClicked
        cft.formFocusGainJTextArea(jta_observaciones);
    }//GEN-LAST:event_jta_observacionesMouseClicked

    private void cb_pisoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_pisoItemStateChanged
        /*  if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.idPiso = cb_piso.getSelectedIndex();
        }*/
        //  System.out.println(cb_piso.getSelectedItem());   
    }//GEN-LAST:event_cb_pisoItemStateChanged

    private void btn_IngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_IngresarActionPerformed
        try {
            if (!validarEscritura() == true || !validarSeleccion() == true) {
                DesktopNotify.showDesktopMessage("Error", "REVISAR CAMPOS OBLIGATORIOS", DesktopNotify.ERROR);
            } else {
                ch.insertHabitacion(jt_nombre.getText(), String.valueOf(cb_piso.getSelectedItem()), String.valueOf(cb_Categoria.getSelectedItem()), Double.parseDouble(jt_precioxNoche.getText()),Double.parseDouble(jt_precioxHora.getText()) ,jta_observaciones.getText(), String.valueOf(cb_status.getSelectedItem()));
                NewTable = new DefaultTableModel();
                cTabla();
                tamañoTabla();
                datosIniciales();
            }
        } catch (Exception e) {
       }
    }//GEN-LAST:event_btn_IngresarActionPerformed

    private void jt_habitacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_habitacionesMouseClicked
        int seleccion = jt_habitaciones.rowAtPoint(evt.getPoint());
        btn_Modificar.setEnabled(true);
        btn_Eliminar.setEnabled(true);
        btn_Ingresar.setEnabled(false);
        lb_Id.setText(String.valueOf(jt_habitaciones.getValueAt(seleccion, 0)));
        jt_nombre.setText(String.valueOf(jt_habitaciones.getValueAt(seleccion, 1)));
        cb_piso.getModel().setSelectedItem(jt_habitaciones.getValueAt(seleccion, 2));
        cb_Categoria.getModel().setSelectedItem(jt_habitaciones.getValueAt(seleccion, 3));
        jt_precioxNoche.setText(String.valueOf(jt_habitaciones.getValueAt(seleccion, 4)));
        jt_precioxHora.setText(String.valueOf(jt_habitaciones.getValueAt(seleccion, 5)));
        jta_observaciones.setText(String.valueOf(jt_habitaciones.getValueAt(seleccion, 6)));
        cb_status.getModel().setSelectedItem(jt_habitaciones.getValueAt(seleccion, 7));
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_habitacionesMouseClicked

    private void cb_CategoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_CategoriaItemStateChanged
        /*        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.idCategoria = cb_Categoria.getSelectedIndex();
        }*/

    }//GEN-LAST:event_cb_CategoriaItemStateChanged

    private void cb_statusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_statusItemStateChanged

        /* if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.idStatus = cb_status.getSelectedIndex();
        }*/
    }//GEN-LAST:event_cb_statusItemStateChanged

    private void btn_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModificarActionPerformed
        try {
            if (!validarEscritura() == true || !validarSeleccion() == true) {
                DesktopNotify.showDesktopMessage("Error", "REVISAR CAMPOS OBLIGATORIOS", DesktopNotify.ERROR);
            } else {
                ch.updateHabitacion(jt_nombre.getText(), String.valueOf(cb_piso.getSelectedItem()), String.valueOf(cb_Categoria.getSelectedItem()), Double.parseDouble(jt_precioxNoche.getText()),Double.parseDouble(jt_precioxHora.getText()) , jta_observaciones.getText(), String.valueOf(cb_status.getSelectedItem()), Integer.valueOf(lb_Id.getText()));
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
                        ch.deleteHabitacion(Integer.valueOf(lb_Id.getText()));
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
        jt_habitaciones.setModel(ch.tablaHabitaciones(jt_Buscar));
        tamañoTabla();
    }//GEN-LAST:event_jt_BuscarKeyReleased

    private void jt_nombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_nombreFocusGained
        cft.formFocusGain(jt_nombre);
    }//GEN-LAST:event_jt_nombreFocusGained

    private void jt_precioxNocheFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_precioxNocheFocusGained
        cft.formFocusGain(jt_precioxNoche);
    }//GEN-LAST:event_jt_precioxNocheFocusGained

    private void jta_observacionesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jta_observacionesFocusGained
        cft.formFocusGainJTextArea(jta_observaciones);
    }//GEN-LAST:event_jta_observacionesFocusGained

    private void jt_BuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_BuscarFocusGained
        cft.formFocusGain(jt_Buscar);
    }//GEN-LAST:event_jt_BuscarFocusGained

    private void jta_observacionesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jta_observacionesKeyPressed
        cft.formTab(evt, jt_Buscar);
    }//GEN-LAST:event_jta_observacionesKeyPressed

    private void jb_limpiarCampos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_limpiarCampos2ActionPerformed
        datosIniciales();        // TODO add your handling code here:
    }//GEN-LAST:event_jb_limpiarCampos2ActionPerformed

    private void jt_precioxHoraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_precioxHoraFocusGained
      cft.formFocusGain(jt_precioxHora);   // TODO add your handling code here:
    }//GEN-LAST:event_jt_precioxHoraFocusGained

    private void jt_precioxHoraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_precioxHoraFocusLost
  cft.formFocusLostJTextField(jt_precioxHora, "Ingresar Precio");        // TODO add your handling code here:
    }//GEN-LAST:event_jt_precioxHoraFocusLost

    private void jt_precioxHoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_precioxHoraMouseClicked
    cft.formFocusGain(jt_precioxHora);
    }//GEN-LAST:event_jt_precioxHoraMouseClicked

    private void jt_precioxHoraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_precioxHoraKeyTyped
  ce.typedMoney(evt, jt_precioxHora);        // TODO add your handling code here:
    }//GEN-LAST:event_jt_precioxHoraKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static principal.MaterialButton btn_Eliminar;
    public static principal.MaterialButton btn_Ingresar;
    public static principal.MaterialButton btn_Modificar;
    private javax.swing.JComboBox<String> cb_Categoria;
    private javax.swing.JComboBox<String> cb_piso;
    private javax.swing.JComboBox<String> cb_status;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JButton jb_limpiarCampos2;
    public static javax.swing.JTextField jt_Buscar;
    private javax.swing.JTable jt_habitaciones;
    private javax.swing.JTextField jt_nombre;
    private javax.swing.JTextField jt_precioxHora;
    private javax.swing.JTextField jt_precioxNoche;
    private javax.swing.JTextField jt_t_registros;
    private javax.swing.JTextArea jta_observaciones;
    private javax.swing.JLabel lb_Id;
    private javax.swing.JLabel lb_errorCaracteristicas;
    private javax.swing.JLabel lb_errorCategoria;
    private javax.swing.JLabel lb_errorNombre;
    private javax.swing.JLabel lb_errorPiso;
    private javax.swing.JLabel lb_errorPrecioxHora;
    private javax.swing.JLabel lb_errorPrecioxNoche;
    private javax.swing.JLabel lb_errorStatus;
    // End of variables declaration//GEN-END:variables
}
