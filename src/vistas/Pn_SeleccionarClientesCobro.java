package vistas;


import controladores.ControladorCFDI;
import controladores.ControladorClientes;
import controladores.ControladorEscritura;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fenix
 */
public class Pn_SeleccionarClientesCobro extends javax.swing.JFrame {

    private ControladorClientes mc = new ControladorClientes();
    private ControladorCFDI cf = new ControladorCFDI();
    private ControladorEscritura ce = new ControladorEscritura();
    Pn_CobrarReservacion Cobrarreserv;
    DefaultTableModel NewTable;
    private int seleccion;
   // private int idCfdi = 0;
    /**
     * Creates new form Pn_SeleccionClientes
     */
    public Pn_SeleccionarClientesCobro() {
        setPantalla();
        initComponents();
        setIconSystem();
        centrarPantalla();
        datosIniciales();
        bloquearUsar();
        RowHeaderApariencia();
        RowApariencia();
        cTabla();
        tamañoTabla();
        


    }
       private void bloquearUsar(){
        btn_Usar.setEnabled(false);
    }
    private void desbloquearUsar(){
           btn_Usar.setEnabled(true);
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
        lb_errorEmail.setText("*");
        lb_errorNombre.setText("*");
        lb_errorRFC.setText("*");
        //colores
        lb_errorEmail.setForeground(new Color(84, 110, 122));
        lb_errorNombre.setForeground(new Color(84, 110, 122));
        lb_errorRFC.setForeground(new Color(84, 110, 122));
        jt_nombre.setText("Ingresar Nombre");
        jt_rfc.setText("Ingresar RFC");
        jt_email.setText("Ingresar Email");

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
        Celular1 = new javax.swing.JLabel();
        jt_email = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        btn_Cancelar = new principal.MaterialButton();
        lb_Id = new javax.swing.JLabel();
        lb_errorNombre = new javax.swing.JLabel();
        lb_errorRFC = new javax.swing.JLabel();
        lb_errorEmail = new javax.swing.JLabel();
        jt_Buscar = new javax.swing.JTextField();
        btn_Usar = new principal.MaterialButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(84, 110, 122));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 710, 210));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Nombre y/o Razon Social ");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 190, -1));

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
        jPanel1.add(jt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 170, -1));

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Registro Federal de Controbuyentes (RFC)");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 250, -1));

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
        jPanel1.add(jt_rfc, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 170, -1));

        Celular1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        Celular1.setForeground(new java.awt.Color(255, 255, 255));
        Celular1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Celular1.setText("Correo Electronico (Email)");
        jPanel1.add(Celular1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 190, -1));

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
        jPanel1.add(jt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 190, -1));

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 190, 10));

        jSeparator9.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 190, 10));

        jSeparator10.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 190, 10));

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
        jPanel1.add(btn_Cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 110, 40));

        lb_Id.setForeground(new java.awt.Color(84, 110, 122));
        jPanel1.add(lb_Id, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 30, 20));

        lb_errorNombre.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorNombre.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorNombre.setText("*");
        jPanel1.add(lb_errorNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 10, -1));

        lb_errorRFC.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorRFC.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorRFC.setText("*");
        jPanel1.add(lb_errorRFC, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 10, -1));

        lb_errorEmail.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_errorEmail.setForeground(new java.awt.Color(84, 110, 122));
        lb_errorEmail.setText("*");
        jPanel1.add(lb_errorEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 10, -1));

        jt_Buscar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jt_Buscar.setForeground(new java.awt.Color(122, 122, 122));
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
        jPanel1.add(jt_Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 60, 150, 20));

        btn_Usar.setBackground(new java.awt.Color(40, 180, 99));
        btn_Usar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Usar.setText("Usar");
        btn_Usar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Usar.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_Usar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_UsarMouseClicked(evt);
            }
        });
        btn_Usar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UsarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Usar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 110, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/campo-buscar.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 50, -1, 40));

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Selecciona el cliente ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 707, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 40));

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("BREVE DESCRIPCIÓN ");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 190, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jt_ClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_ClientesMouseClicked
        seleccion = jt_Clientes.rowAtPoint(evt.getPoint());
        desbloquearUsar();
        lb_Id.setText(String.valueOf(jt_Clientes.getValueAt(seleccion, 0)));
        jt_nombre.setText(String.valueOf(jt_Clientes.getValueAt(seleccion, 1)));
        jt_rfc.setText(String.valueOf(jt_Clientes.getValueAt(seleccion, 2)));
        jt_email.setText(String.valueOf(jt_Clientes.getValueAt(seleccion, 5)));

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

    private void btn_UsarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_UsarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_UsarMouseClicked

    private void btn_UsarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UsarActionPerformed

Cobrarreserv.lb_razonSocial.setText(jt_nombre.getText());
Cobrarreserv.lb_rfc.setText(jt_rfc.getText());
Cobrarreserv.jt_email.setText(jt_email.getText());
dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_UsarActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseEntered

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
            java.util.logging.Logger.getLogger(Pn_SeleccionarClientesCobro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pn_SeleccionarClientesCobro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pn_SeleccionarClientesCobro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pn_SeleccionarClientesCobro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pn_SeleccionarClientesCobro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Celular1;
    private principal.MaterialButton btn_Cancelar;
    private principal.MaterialButton btn_Usar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jt_Buscar;
    private javax.swing.JTable jt_Clientes;
    private javax.swing.JTextField jt_email;
    private javax.swing.JTextField jt_nombre;
    private javax.swing.JTextField jt_rfc;
    private javax.swing.JLabel lb_Id;
    private javax.swing.JLabel lb_errorEmail;
    private javax.swing.JLabel lb_errorNombre;
    private javax.swing.JLabel lb_errorRFC;
    // End of variables declaration//GEN-END:variables
}
