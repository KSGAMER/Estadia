/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import Utilerias.*;
import controladores.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import objetos.*;

/**
 *
 * @author RojeruSan
 */
public class Pn_CalendarioReservaciones extends javax.swing.JPanel {

    ControladorHabitaciones ch = new ControladorHabitaciones();
    ControladorReservaciones cr = new ControladorReservaciones();
    //para colorar celdas en una tabla con datos referidos 
    DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();

    //necesarios para cargar la tabla de meses y reervas
    private int m = 0;
    private int y = 0;

    DefaultTableModel modelo;

    Calendar cal = null;
    //necesario para dar formato al jdateChooser
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    //guarda el tamaño real de resrvaciones por mes cuando se compara
    private int sizeReservas;
    //IMPORTANTE
    private int mesBoton;
    //es el mes que que aparece en pantalla al cambiar con el boton siguiente / anterior
    //sirve para compracion 
    private String mesActual = "";
    private int mesIngreso;

    /*
    int mesIn;
    int mesOut;
    int DayIn;
    int DayOut;

    /**
     * Creates new form pnlHome
     */
    public Pn_CalendarioReservaciones() {
        initComponents();
        RowHeaderApariencia();
        RowApariencia();
        ch.tablaHabitaciones();
        cr.tablaReservaciones();
        cargarReservas();
        tamañoTabla();
        // prueba();

    }

    public void RowHeaderApariencia() {
        jt_Reservas.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 14));
        jt_Reservas.getTableHeader().setOpaque(false);
        jt_Reservas.getTableHeader().setBackground(new Color(32, 136, 203));
        jt_Reservas.getTableHeader().setForeground(new Color(255, 255, 255));

    }

    public void RowApariencia() {
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

    public void tamañoTabla() {
        TableColumnModel columnModel = jt_Reservas.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(370);
    }

    public void ajustesDeScroll() {
        //scroll_Menu es el nombre del jscrollpane que contiene el panel del menu
        scroll_Tabla.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

        //ELIMINA LA POSIBILIDAD DE REALIZAR SCROLL HORIZONTALMENTE
        scroll_Tabla.setHorizontalScrollBarPolicy(scroll_Tabla.HORIZONTAL_SCROLLBAR_NEVER);
    }
//metodo que carga las reservas en funcion de la fecha actual vs la fecha del label lb_mes

    public void cargarReservas() {

        modelo = new DefaultTableModel();
        Date now = new Date(); // java.util.Date, NOT java.sql.Date or java.sql.Timestamp!
        String year = new SimpleDateFormat("yyyy").format(now);
        String month = new SimpleDateFormat("MM").format(now);
        String day = new SimpleDateFormat("dd").format(now);
        int ye = Integer.parseInt(year);
        int mn = Integer.parseInt(month);
        int mes = mn + m;
        //bloquear y desbloquar el boton de siguiente o anterior mes
        if (mes > 11) {
            btnNextMonth.setEnabled(false);
        } else {
            btnNextMonth.setEnabled(true);
        }

        if (mes < 2) {
            btnBeforeMont.setEnabled(false);
        } else {
            btnBeforeMont.setEnabled(true);
        }
        //fin de bloqueos

        switch (mes) {
            case 1:
                cal = new GregorianCalendar(ye, Calendar.JANUARY, 1);
                mesActual = "Enero";
                mesBoton = 1;
                break;
            case 2:
                cal = new GregorianCalendar(ye, Calendar.FEBRUARY, 1);
                mesActual = "Febrero";
                mesBoton = 2;
                break;
            case 3:
                cal = new GregorianCalendar(ye, Calendar.MARCH, 1);
                mesActual = "Marzo";
                mesBoton = 3;
                break;
            case 4:
                cal = new GregorianCalendar(ye, Calendar.APRIL, 1);
                mesActual = "Abril";
                mesBoton = 4;
                break;
            case 5:
                cal = new GregorianCalendar(ye, Calendar.MAY, 1);
                mesActual = "Mayo";
                mesBoton = 5;
                break;
            case 6:
                cal = new GregorianCalendar(ye, Calendar.JUNE, 1);
                mesActual = "Junio";
                mesBoton = 6;
                break;
            case 7:
                cal = new GregorianCalendar(ye, Calendar.JULY, 1);
                mesActual = "Julio";
                mesBoton = 7;
                break;
            case 8:
                cal = new GregorianCalendar(ye, Calendar.AUGUST, 1);
                mesActual = "Agosto";
                mesBoton = 8;
                break;
            case 9:
                cal = new GregorianCalendar(ye, Calendar.SEPTEMBER, 1);
                mesActual = "Septiembre";
                mesBoton = 9;
                break;
            case 10:

                cal = new GregorianCalendar(ye, Calendar.OCTOBER, 1);
                mesActual = "Octubre";
                mesBoton = 10;
                break;
            case 11:
                cal = new GregorianCalendar(ye, Calendar.NOVEMBER, 1);
                mesActual = "Noviembre";
                mesBoton = 11;
                break;

            case 12:
                cal = new GregorianCalendar(ye, Calendar.DECEMBER, 1);
                mesActual = "Diciembre";
                // mesBoton=12;
                break;

        }
//DATOS DEL MES Y AÑO ACTUALES
        lb_año.setText(Integer.toString(ye));
        lb_mes.setText(mesActual);

        /*CODIGO QUE CARGA LOS TITULOS DE LA TABLA .. EJEMPLO
                                DIAS DEL MES
HABITACION  | 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 ETC

         */
        int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // int numero de dias        
        String titulos[] = new String[days + 1];//cabeceras de la tabla
        titulos[0] = "Habitaciones";

        for (int i = 1; i <= days; i++) {
            titulos[i] = String.valueOf(i);
        }

        modelo = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {//para evitar que las celdas sean editables
                return false;
            }
        };

//FIN DE LA CABECERA
        try {
            Object[] columna = new Object[days + 1]; //objeto que sirve para guardar dias o como medida de comparacion
            for (ObjetoHabitacion habitacion : ch.selectHabitacion()) {
                columna[0] = habitacion.getNombre();

                for (ObjetoReservacion objetoReservacion : cr.selectReservacion()) {

//busqueda del numero de mes de la reservaciion para comparaciones
                    String MesdeFechaIngreso = new SimpleDateFormat("MM").format(dateFormat.parse(objetoReservacion.getFechaIngresoCompleta()));
                    String MesdeFechaSalida = new SimpleDateFormat("MM").format(dateFormat.parse(objetoReservacion.getFechaSalidaCompleta()));
                    int mesIn = Integer.parseInt(MesdeFechaIngreso);
                    int mesOut = Integer.parseInt(MesdeFechaSalida);
                    //busqueda del numero de dia de la reservacion para comparaciones
                    String diaFechaIngreso = new SimpleDateFormat("dd").format(dateFormat.parse(objetoReservacion.getFechaIngresoCompleta()));
                    String diaFechaSalida = new SimpleDateFormat("dd").format(dateFormat.parse(objetoReservacion.getFechaSalidaCompleta()));
                    int DayIn = Integer.parseInt(diaFechaIngreso);
                    int DayOut = Integer.parseInt(diaFechaSalida);

                    for (int i = 1; i < columna.length; i++) {  //ARREGLO DE DIAS 
                        if (mesIn == mes && mesOut == mes) {
                            if (objetoReservacion.getIdHabitacion() == habitacion.getIdHabitacion()) {
                                if (i < DayIn) {
                                  //  columna[i] = "";
                                } else if (DayIn == i) {

//arreglo iniciado con el primer dia de la reservacion y finalizado con el dia de
//termino de la reservacion (DayOut) para pintar la tabla con x
                                    for (int p = DayIn; p <= DayOut; p++) {
//llenamos la tabla de x para denotar en la tabla donde hay fechas reservadas
                                        columna[p] = "x";
                                    }
                                } else if (i > DayOut) {
                                    columna[i] = "";
                                }
                           
                            } else {
                              

                            }
                        } else {
                            // columna[i]="";
                        }

                        if (mesIn == mes && (mesOut == mes + 1)) {

                            if (objetoReservacion.getIdHabitacion() == habitacion.getIdHabitacion()) {
                                if (i < DayIn) {
                                    columna[i] = "";
                                }
                                if (DayIn == i) {
//arreglo iniciado con el primer dia de la reservacion y finalizado con el dia de
//termino de la reservacion (DayOut) para pintar la tabla con x
                                    for (int p = DayIn; p <= days; p++) {
//llenamos la tabla de x para denotar en la tabla donde hay fechas reservadas
                                        columna[p] = "x";
                                    }
                                }

                            } else {
                                // columna[i]="";
                            }
                        } else {
                            // columna[i]="";
                        }
                        if ((mesIn == mes - 1) && mesOut == mes) {

                            if (objetoReservacion.getIdHabitacion() == habitacion.getIdHabitacion()) {

                                if (i == 1) {
//arreglo iniciado con el primer dia de la reservacion y finalizado con el dia de
//termino de la reservacion (DayOut) para pintar la tabla con x
                                    for (int p = i; p <= DayOut; p++) {
//llenamos la tabla de x para denotar en la tabla donde hay fechas reservadas
                                        columna[p] = "x";
                                    }
                                }
                                if (i > DayOut) {
                                    columna[i] = "";
                                }
                            } else {
                                // columna[i]="";
                            }
                        } else {
                            //columna[i]="";
                        }
                    }

                }
                modelo.addRow(columna);
            }

        } catch (ParseException ex) {
            Logger.getLogger(ControladorRecepciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        headerRenderer.setBackground(new Color(102, 205, 170));
        jt_Reservas.setModel(modelo);
        jt_Reservas.getColumnModel().getColumn(0).setPreferredWidth(200);
        //jt_Reservas.setDefaultRenderer(Object.class, null);

        //SI EL MES ACTUAL ES IGUAL AL MES DE LA APLICACION , 
        //SELECCIONAMOS EL LA POSICION DE LA COLUMNA EN LA TABLA CON EL MISMO NUMERO DE PISICION QUE EL DIA ACTUAL
        //Y LO RESALTAMOS
        if (mes == mn) {
            jt_Reservas.getColumnModel().getColumn(Integer.parseInt(day)).setHeaderRenderer(headerRenderer);
        }
        
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
        jPanel3 = new javax.swing.JPanel();
        lb_titulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        Pn_Pisos = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnBeforeMont = new javax.swing.JButton();
        btnNextMonth = new javax.swing.JButton();
        lb_año = new javax.swing.JLabel();
        lb_mes = new javax.swing.JLabel();
        scroll_Tabla = new javax.swing.JScrollPane();
        jt_Reservas = new javax.swing.JTable();

        setBackground(new java.awt.Color(153, 163, 164));
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(1086, 684));

        jPanel1.setBackground(new java.awt.Color(84, 110, 122));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(84, 110, 122));

        lb_titulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lb_titulo.setForeground(new java.awt.Color(255, 255, 255));
        lb_titulo.setText("CALEDARIO DE RESERVACIONES");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_titulo)
                .addContainerGap(685, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_titulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 50));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1020, 10));

        Pn_Pisos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Check - In ");
        Pn_Pisos.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        btnBeforeMont.setText("anterior");
        btnBeforeMont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBeforeMontActionPerformed(evt);
            }
        });
        Pn_Pisos.add(btnBeforeMont, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, -1, 30));

        btnNextMonth.setText("siguiente");
        btnNextMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextMonthActionPerformed(evt);
            }
        });
        Pn_Pisos.add(btnNextMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, -1, 30));

        lb_año.setText("año");
        Pn_Pisos.add(lb_año, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, -1, -1));

        lb_mes.setText("mes");
        Pn_Pisos.add(lb_mes, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 20, -1, -1));

        jPanel1.add(Pn_Pisos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 1000, 50));

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
        scroll_Tabla.setViewportView(jt_Reservas);

        jPanel1.add(scroll_Tabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 117, 1000, 490));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1069, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBeforeMontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBeforeMontActionPerformed
        m = m - 1;
        cargarReservas();
        tamañoTabla();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnBeforeMontActionPerformed

    private void btnNextMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextMonthActionPerformed
        m = m + 1;
        cargarReservas();
        tamañoTabla();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextMonthActionPerformed

    private void jt_ReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_ReservasMouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_jt_ReservasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Pn_Pisos;
    public static javax.swing.JButton btnBeforeMont;
    public static javax.swing.JButton btnNextMonth;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JTable jt_Reservas;
    public static javax.swing.JLabel lb_año;
    public static javax.swing.JLabel lb_mes;
    private javax.swing.JLabel lb_titulo;
    private javax.swing.JScrollPane scroll_Tabla;
    // End of variables declaration//GEN-END:variables
}
