/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;


import Utilerias.ComponenteMenuSuperior;
import Utilerias.ComponenteRecepcion;
import controladores.ControladorEstatusHabitaciones;
import controladores.ControladorHabitaciones;
import controladores.ControladorPisos;
import controladores.ControladorRecepciones;
import controladores.ControladorReservaciones;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;
import objetos.ObjetoEstadoHabitacion;
import objetos.ObjetoPiso;


/**
 *
 * @author FENIX
 */
public class Pn_Recepcion extends javax.swing.JPanel {

    //Se declaran las clases a utilizar
    private ControladorEstatusHabitaciones ceh = new ControladorEstatusHabitaciones();
    private ControladorPisos cp = new ControladorPisos();
    private ControladorReservaciones crv = new ControladorReservaciones();
    private ControladorRecepciones cr = new ControladorRecepciones();
    private ControladorHabitaciones ch= new ControladorHabitaciones();
    //Se declaran los componentes a utilizar
    private ComponenteRecepcion q;
    private ComponenteMenuSuperior ms;
    //Se declaran los colores a utilizar para los paneles
    private Color disponible = new Color(40, 180, 99);
    private Color reservado = new Color(255, 153, 0);
    private Color limpieza = new Color(211, 18, 18);
    private Color estilo = new Color(174, 182, 191);
    //Se declara la variable para identificar los pisos
    private int idPiso = 0;

    /**
     * Creates new form pnlHome
     */
    public Pn_Recepcion() {
        initComponents();
        //Se instancian los datos a utilizar
        crv.tablaReservaciones();
        cr.tablaReservaciones();
        cp.tablaPisos();
        ceh.tablaEstadoHabiaciones();
        ch.tablaHabitaciones();
        actualizarEstatus();
        actualizacionReservas();
        actualizaMenuPiso();
        cargarStatus();
        configScroll();
    }

    public void actualizarEstatus() {
        cr.actualizarEstatus();
    }

    public void configScroll() {
        //incrementa a velocidad de scroleo
        ScrollCentral.getVerticalScrollBar().setUnitIncrement(20);
        //elimina el scrollBar vertical 

        ScrollCentral.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
    }

    public void actualizacionReservas() {
        //Se ejecuta el método para generar los componentes pasando el panel que contendra los componentes, los coloresm el filtrado del piso, la categoria para filtrar, agrega los componentes automaticamente 
        cr.cargarRecepcion(pn_VistaReservaciones, disponible, reservado, limpieza, estilo, idPiso, cb_Categoria.getSelectedItem().toString());
    }

    public void actualizaMenuPiso() {

        for (ObjetoPiso piso : cp.selectPiso()) {
            ms = new ComponenteMenuSuperior();
            q = new ComponenteRecepcion();
            pn_MenuPisoDinamico.add(ms.ComponenteMenuSuperior(piso.getNombre(), 5, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    idPiso = piso.getIdPiso();
                    actualizacionReservas();
                }
            }));

        }
        pn_MenuPisoDinamico.updateUI();

    }

    public void cargarStatus() {
        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        cb.addElement("Todos los Estatus");
        for (ObjetoEstadoHabitacion campos : ceh.selectEstadoHabitacion()) {

            cb.addElement(campos.getNombre());
        }
        cb_Categoria.setModel(cb);
    }

    //EJEMPLO
    //carga todos los datos de una bd creando nuevos componentes agregandolos al panel principal
    /* public void CargaTodo() {
        for (ObjetoCliente col : mc.selectCliente()) {
            p = new ComponenteRecepcion();

            pn_VistaReservaciones.add(p.ComponenteRecepcionDatos(col.getNombre(), col.getNombre(), col.getNombre(), col.getNombre(), null, disponible));
        }
        pn_VistaReservaciones.updateUI();
    }
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        ScrollCentral = new javax.swing.JScrollPane();
        pn_VistaReservaciones = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        Pn_Pisos = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pn_MenuPisoDinamico = new javax.swing.JPanel();
        lbAccesos = new Utilerias.RSButtonMetro();
        cb_Categoria = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(153, 163, 164));
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(1086, 684));

        jPanel1.setBackground(new java.awt.Color(84, 110, 122));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(174, 182, 191));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ScrollCentral.setBorder(null);

        pn_VistaReservaciones.setBackground(new java.awt.Color(174, 182, 191));
        pn_VistaReservaciones.setLayout(new java.awt.GridLayout(0, 4, 10, 10));
        ScrollCentral.setViewportView(pn_VistaReservaciones);

        jPanel2.add(ScrollCentral, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 980, 390));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 1000, 420));

        jPanel3.setBackground(new java.awt.Color(84, 110, 122));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("VISTA GENERAL DE RECEPCIÓN");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Inicio > Recepción ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 509, Short.MAX_VALUE)
                .addComponent(jLabel29)
                .addGap(80, 80, 80))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel29))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 50));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Habitaciones disponibles");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1020, 10));

        Pn_Pisos.setBackground(new java.awt.Color(111, 122, 134));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Check - In ");

        pn_MenuPisoDinamico.setBackground(new java.awt.Color(111, 122, 134));
        pn_MenuPisoDinamico.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        lbAccesos.setBackground(new java.awt.Color(111, 122, 134));
        lbAccesos.setText("Todos los pisos ");
        lbAccesos.setToolTipText("");
        lbAccesos.setBorderPainted(false);
        lbAccesos.setColorBorde(null);
        lbAccesos.setColorHover(new java.awt.Color(111, 122, 134));
        lbAccesos.setColorNormal(new java.awt.Color(111, 122, 134));
        lbAccesos.setColorPressed(new java.awt.Color(111, 122, 134));
        lbAccesos.setFocusPainted(false);
        lbAccesos.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        lbAccesos.setHideActionText(true);
        lbAccesos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbAccesos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbAccesosActionPerformed(evt);
            }
        });

        cb_Categoria.setBackground(new java.awt.Color(111, 122, 134));
        cb_Categoria.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cb_Categoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos los Estatus" }));
        cb_Categoria.setBorder(null);
        cb_Categoria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cb_Categoria.setFocusable(false);
        cb_Categoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_CategoriaItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout Pn_PisosLayout = new javax.swing.GroupLayout(Pn_Pisos);
        Pn_Pisos.setLayout(Pn_PisosLayout);
        Pn_PisosLayout.setHorizontalGroup(
            Pn_PisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Pn_PisosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pn_MenuPisoDinamico, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbAccesos, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cb_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        Pn_PisosLayout.setVerticalGroup(
            Pn_PisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pn_PisosLayout.createSequentialGroup()
                .addGroup(Pn_PisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Pn_PisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(pn_MenuPisoDinamico, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                        .addGroup(Pn_PisosLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel2)
                            .addGap(8, 8, 8))
                        .addGroup(Pn_PisosLayout.createSequentialGroup()
                            .addComponent(lbAccesos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(2, 2, 2)))
                    .addGroup(Pn_PisosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cb_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(Pn_Pisos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 1000, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1069, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lbAccesosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbAccesosActionPerformed
        idPiso = 0;
        actualizacionReservas();
    }//GEN-LAST:event_lbAccesosActionPerformed

    private void cb_CategoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_CategoriaItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            actualizacionReservas();
        }
    }//GEN-LAST:event_cb_CategoriaItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Pn_Pisos;
    private javax.swing.JScrollPane ScrollCentral;
    private javax.swing.JComboBox<String> cb_Categoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private Utilerias.RSButtonMetro lbAccesos;
    private javax.swing.JPanel pn_MenuPisoDinamico;
    private static javax.swing.JPanel pn_VistaReservaciones;
    // End of variables declaration//GEN-END:variables

}
