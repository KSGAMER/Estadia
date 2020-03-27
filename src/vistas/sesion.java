/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//extendedstate es para poner el frame en fullscreen
//this.setExtendedState(dashBoardPrincipal.MAXIMIZED_BOTH);
package vistas;

import controladores.ControladorEventosSesion;
import controladores.ControladorSesion;
import controladores.ControladorUsuarios;
import java.awt.Image;
import java.awt.event.ItemEvent;
//para cambiar los valores de un boton 

//para la fecha y la hora 
import javax.swing.ImageIcon;

/**
 *
 * @author sebastian
 */
public class sesion extends javax.swing.JFrame {

    
    private ControladorEventosSesion ce = new ControladorEventosSesion();
    private ControladorSesion cs = new ControladorSesion();
    private ControladorUsuarios cu = new ControladorUsuarios();
    public static String Username;

    /**
     * Creates new form sesion
     */
    public sesion() {

        setPantalla();//se debe mandar a llamar primero
        initComponents();
        setIconSystem();
        centrarPantalla();
        cu.tablaUsuarios();
//jp_imagen.setBackground(new Color(0,0,0,1));
        ajustarImagen();
    }
 private void setIconSystem() {
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logosmall.png")).getImage());
    }
    private void setPantalla() {

        //para eliminar el tittle bar
        this.setUndecorated(true);
        //para que no le cambien el tamaño
        this.setResizable(false);

    }

    private void centrarPantalla() {
        //para dejar el menu centrado y estatico
        this.setLocationRelativeTo(null);
    }




    private void ajustarImagen() {
        //se utiliza para obtener la ruta de la imagen 
        ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/ImagenFondo/img_Hotel.jpg"));

        //se utilizar para obtener el tamaño de jlaben que contendra la imagen y 
        //despues se reacomda la imagen automaticamente 
        Image image = icon.getImage().getScaledInstance(IconImage.getWidth(), IconImage.getHeight(), Image.SCALE_SMOOTH);
//AQUI AGREGAMOS LAS IMAGENES AL LABEL COMO ICONO
        IconImage.setIcon(new ImageIcon(image));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jp_imagen = new javax.swing.JPanel();
        lb_hora = new javax.swing.JLabel();
        lb_fecha = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        IconImage = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        usuario = new javax.swing.JTextField();
        contraseña = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Lb_notificacion = new javax.swing.JLabel();
        cerrar_sesion = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        iniciar_sesion = new principal.MaterialButton();
        lb_Configs = new javax.swing.JLabel();
        jch_mostrar = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocationByPlatform(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jp_imagen.setBackground(new java.awt.Color(211, 211, 211));
        jp_imagen.setForeground(new java.awt.Color(97, 212, 195));
        jp_imagen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_hora.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lb_hora.setForeground(new java.awt.Color(255, 255, 255));
        lb_hora.setText("00:00:00");
        jp_imagen.add(lb_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 430, -1, -1));

        lb_fecha.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lb_fecha.setForeground(new java.awt.Color(255, 255, 255));
        lb_fecha.setText("dd/mm/yy");
        jp_imagen.add(lb_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, -1, -1));

        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Fecha y hora actual");
        jp_imagen.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 400, -1, -1));
        jp_imagen.add(IconImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 400));

        getContentPane().add(jp_imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 400));

        jPanel2.setBackground(new java.awt.Color(28, 37, 47));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Contraseña");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, -1, -1));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Hotel Paraiso Inn");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, 20));

        usuario.setBackground(new java.awt.Color(28, 37, 47));
        usuario.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        usuario.setForeground(new java.awt.Color(153, 153, 153));
        usuario.setText("Ingresa tu usuario");
        usuario.setBorder(null);
        usuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usuarioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                usuarioFocusLost(evt);
            }
        });
        usuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usuarioMouseClicked(evt);
            }
        });
        usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usuarioKeyPressed(evt);
            }
        });
        jPanel2.add(usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 220, -1));

        contraseña.setBackground(new java.awt.Color(28, 37, 47));
        contraseña.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        contraseña.setForeground(new java.awt.Color(153, 153, 153));
        contraseña.setText("password");
        contraseña.setBorder(null);
        contraseña.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                contraseñaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                contraseñaFocusLost(evt);
            }
        });
        contraseña.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contraseñaMouseClicked(evt);
            }
        });
        contraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                contraseñaKeyPressed(evt);
            }
        });
        jPanel2.add(contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 220, -1));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 220, 10));

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 220, 10));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Usuario");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/hotel.png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 70, 90));

        Lb_notificacion.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        Lb_notificacion.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(Lb_notificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 210, 10));

        cerrar_sesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconfinder_close16_216470.png"))); // NOI18N
        cerrar_sesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cerrar_sesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cerrar_sesionMouseClicked(evt);
            }
        });
        jPanel2.add(cerrar_sesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconfinder_icon-114-lock_314481 (1).png"))); // NOI18N
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, 28));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user.png"))); // NOI18N
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        iniciar_sesion.setBackground(new java.awt.Color(40, 180, 99));
        iniciar_sesion.setForeground(new java.awt.Color(255, 255, 255));
        iniciar_sesion.setText("Ingresar");
        iniciar_sesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iniciar_sesion.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        iniciar_sesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iniciar_sesionMouseClicked(evt);
            }
        });
        iniciar_sesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciar_sesionActionPerformed(evt);
            }
        });
        jPanel2.add(iniciar_sesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 230, 40));

        lb_Configs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons/configuracion-24x24.png"))); // NOI18N
        lb_Configs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lb_Configs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_ConfigsMouseClicked(evt);
            }
        });
        jPanel2.add(lb_Configs, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jch_mostrar.setBackground(new java.awt.Color(84, 110, 122));
        jch_mostrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jch_mostrar.setForeground(new java.awt.Color(255, 255, 255));
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
        jPanel2.add(jch_mostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, 20, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 310, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void usuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuarioMouseClicked
        ce.camposMouseClick(evt, usuario, contraseña);
    }//GEN-LAST:event_usuarioMouseClicked

    private void contraseñaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contraseñaMouseClicked
        ce.camposMouseClick(evt, usuario, contraseña);
    }//GEN-LAST:event_contraseñaMouseClicked

    private void usuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usuarioFocusLost
//si el usuario dejar de dar click en el textfield o passworfield o esta vacio y 
//da clic en otro lugar se autollenara nuevamente 

        /*if (usuario.getText().trim().equals("") || usuario.getText().trim().toLowerCase().equals("Ingresa tu usuario")) {
            usuario.setText("Ingresa tu usuario");
            usuario.setForeground(new Color(153, 153, 153));
        }*/
        
        
    }//GEN-LAST:event_usuarioFocusLost

    private void contraseñaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contraseñaFocusLost
        /*String password = String.valueOf(contraseña.getPassword());
        if (password.trim().equals("") || password.trim().toLowerCase().equals("password")) {
            contraseña.setText("Ingresa tu usuario");
            contraseña.setForeground(new Color(153, 153, 153));
        }*/
        
    }//GEN-LAST:event_contraseñaFocusLost

    private void cerrar_sesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrar_sesionMouseClicked
        System.exit(0);   //cierra el sistema
    }//GEN-LAST:event_cerrar_sesionMouseClicked

    private void usuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioKeyPressed
        ce.logearWithEnter(evt, usuario, contraseña, Lb_notificacion, this);
    }//GEN-LAST:event_usuarioKeyPressed

    private void usuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usuarioFocusGained
        ce.campoGainFocus(evt, usuario, contraseña);
    }//GEN-LAST:event_usuarioFocusGained

    private void contraseñaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contraseñaFocusGained
        ce.campoGainFocus(evt, usuario, contraseña);
    }//GEN-LAST:event_contraseñaFocusGained

    private void contraseñaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contraseñaKeyPressed
        ce.logearWithEnter(evt, usuario, contraseña, Lb_notificacion, this);
    }//GEN-LAST:event_contraseñaKeyPressed

    private void iniciar_sesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iniciar_sesionMouseClicked
  cs.autentificarUsuario(usuario, contraseña, Lb_notificacion, this);
  // TODO add your handling code here:
    }//GEN-LAST:event_iniciar_sesionMouseClicked

    private void lb_ConfigsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_ConfigsMouseClicked
        Pn_IpServerJason ale = new Pn_IpServerJason(this, true);
        ale.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_ConfigsMouseClicked

    private void jch_mostrarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jch_mostrarItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
            contraseña.setEchoChar((char)0);

        } else {//checkbox has been deselected
            contraseña.setEchoChar('\u25cf');
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jch_mostrarItemStateChanged

    private void jch_mostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jch_mostrarActionPerformed

    }//GEN-LAST:event_jch_mostrarActionPerformed

    private void iniciar_sesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciar_sesionActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_iniciar_sesionActionPerformed

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
            java.util.logging.Logger.getLogger(sesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new sesion().setVisible(true);

            }
        });
    }

    /*public void transparenciaDeBotones(){
        iniciar_sesion.setOpaque(false);
        iniciar_sesion.setContentAreaFilled(true);
        iniciar_sesion.setBorderPainted(false);
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IconImage;
    private javax.swing.JLabel Lb_notificacion;
    private javax.swing.JLabel cerrar_sesion;
    private javax.swing.JPasswordField contraseña;
    public static principal.MaterialButton iniciar_sesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JCheckBox jch_mostrar;
    private javax.swing.JPanel jp_imagen;
    private javax.swing.JLabel lb_Configs;
    private javax.swing.JLabel lb_fecha;
    private javax.swing.JLabel lb_hora;
    private javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables
}
