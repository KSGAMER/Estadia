/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.Alertas;

import Utilerias.AWTUtilities;
import controladores.ControladorIncidencias;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author fenix
 */
public class Pn_Alert_TabladeIncidencias extends javax.swing.JDialog {
    
    private ControladorIncidencias cin = new ControladorIncidencias();
    //NECESARIO PARA EL USO DE LA NOTIFICACION DINAMICA DE BOTON ELIMINAR ()
    Frame Principal;
//FIN
//Variable publica que recibe el nombre de la habitacion desde el jdialog llamado Pn_Alert_DescripcionHabitacion
//y hacer fucionar el metodo interno de este componente llamado cTabla(NombreHabitacion); para cargar los datos 
//actuales de las incidencias que esten vinculadas a la habitacion enviada
    public static String NombreHabitacion="";
//fin
    private int seleccion;
    /**
     * Creates new form AlertSuccess
     */
    public Pn_Alert_TabladeIncidencias(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setPantalla();
        initComponents();
        AWTUtilities.setOpaque(this, false);
        centrarPantalla();
        //APARIENCIA DE LA TABLA
        RowHeaderApariencia();
        RowApariencia();
        //FIN
        //CARGA LOS VALORES EN LA TABLA
        cTabla(NombreHabitacion);
        //FIN
        //ASIGNA TAMAÑOS DE ANCHURA A LAS COLUMNAS
        tamañoTabla();
        //FIN
        jta_observaciones.setEnabled(false);
  //para ajustar el tecto al tamaño del jtextarea
        jta_observaciones.setLineWrap(true);
        //FIN
    }
   public void centrarPantalla() {
        this.setLocationRelativeTo(null);
    }
    public void setPantalla() {

        //para eliminar el tittle bar
        this.setUndecorated(true);
        //para que no le cambien el tamaño
        this.setResizable(false);

    }

    private void cTabla(String Habitacion) {
        jtabla_incidenciasActuales.setModel(cin.tablaIncidencias(Habitacion));
       
    }

    private void tamañoTabla() {
        TableColumnModel columnModel = jtabla_incidenciasActuales.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(250);
    }

    
    private void RowApariencia() {

        jtabla_incidenciasActuales.setFocusable(false);

        //espacio entre comulnas
        jtabla_incidenciasActuales.setIntercellSpacing(new Dimension(0, 1));
        //altura de columnas 
        jtabla_incidenciasActuales.setRowHeight(25);
        //margen entre filas
        jtabla_incidenciasActuales.setRowMargin(0);
//sin lineas verticles
        jtabla_incidenciasActuales.setShowVerticalLines(false);
        jtabla_incidenciasActuales.setSelectionBackground(new Color(97, 212, 195));

    }

    private void RowHeaderApariencia() {
        jtabla_incidenciasActuales.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 14));
        jtabla_incidenciasActuales.getTableHeader().setOpaque(false);
        jtabla_incidenciasActuales.getTableHeader().setBackground(new Color(32, 136, 203));
        jtabla_incidenciasActuales.getTableHeader().setForeground(new Color(255, 255, 255));

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
        cancelar = new principal.MaterialButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jta_observaciones = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtabla_incidenciasActuales = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jt_Nombre = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(36, 47, 65));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cancelar.setBackground(new java.awt.Color(211, 18, 18));
        cancelar.setForeground(new java.awt.Color(255, 255, 255));
        cancelar.setText("Cerrar");
        cancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelar.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        jPanel1.add(cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 410, 290, 46));

        jPanel3.setBackground(new java.awt.Color(233, 235, 238));

        jLabel2.setBackground(new java.awt.Color(36, 47, 65));
        jLabel2.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Vision General de Incidencias Actuales ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(85, 85, 85))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 50));

        jta_observaciones.setColumns(20);
        jta_observaciones.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jta_observaciones.setForeground(new java.awt.Color(153, 153, 153));
        jta_observaciones.setRows(5);
        jta_observaciones.setText("Ingresar Observaciones");
        jta_observaciones.setBorder(null);
        jta_observaciones.setFocusable(false);
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
        jPanel1.add(jta_observaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 430, 110));

        jtabla_incidenciasActuales.setModel(new javax.swing.table.DefaultTableModel(
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
        jtabla_incidenciasActuales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtabla_incidenciasActualesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtabla_incidenciasActuales);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 420, 100));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tabla de Incidencias por Habitación");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, 190, 10));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nombre :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 90, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Detalles de la Incidencia");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Observaciones :");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

        jt_Nombre.setBackground(new java.awt.Color(36, 47, 65));
        jt_Nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jt_Nombre.setForeground(new java.awt.Color(255, 255, 255));
        jt_Nombre.setText("Nombre de la Incidencia");
        jt_Nombre.setBorder(null);
        jt_Nombre.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jt_Nombre.setEnabled(false);
        jt_Nombre.setFocusable(false);
        jPanel1.add(jt_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 190, -1));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 450, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened


    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        setVisible(false);
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void jta_observacionesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jta_observacionesFocusGained
        
    }//GEN-LAST:event_jta_observacionesFocusGained

    private void jta_observacionesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jta_observacionesFocusLost
      
    }//GEN-LAST:event_jta_observacionesFocusLost

    private void jta_observacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jta_observacionesMouseClicked
      
    }//GEN-LAST:event_jta_observacionesMouseClicked

    private void jta_observacionesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jta_observacionesKeyPressed
     
    }//GEN-LAST:event_jta_observacionesKeyPressed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
     Cerrar();
    }//GEN-LAST:event_cancelarActionPerformed

    private void jtabla_incidenciasActualesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtabla_incidenciasActualesMouseClicked
        seleccion = jtabla_incidenciasActuales.rowAtPoint(evt.getPoint());
        jt_Nombre.setText(String.valueOf(jtabla_incidenciasActuales.getValueAt(seleccion, 0)));
        jta_observaciones.setText(String.valueOf(jtabla_incidenciasActuales.getValueAt(seleccion, 1)));

    }//GEN-LAST:event_jtabla_incidenciasActualesMouseClicked

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pn_Alert_TabladeIncidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pn_Alert_TabladeIncidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pn_Alert_TabladeIncidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pn_Alert_TabladeIncidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
           

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Pn_Alert_TabladeIncidencias dialog = new Pn_Alert_TabladeIncidencias(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private principal.MaterialButton cancelar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jt_Nombre;
    public static javax.swing.JTextArea jta_observaciones;
    private javax.swing.JTable jtabla_incidenciasActuales;
    // End of variables declaration//GEN-END:variables

    private void Cerrar() {
        this.dispose();
     
    }

 
}
