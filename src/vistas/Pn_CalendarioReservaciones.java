/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import Utilerias.*;
import controladores.*;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
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
 
    //es el mes que que aparece en pantalla al cambiar con el boton siguiente / anterior
    //sirve para compracion 
    private String mesActual = "";
  

    //NECESARIO
//busqueda del numero de mes de la reservaciion para comparaciones
    String MesdeFechaIngreso, MesdeFechaSalida;
    int mesIn, mesOut;
//Busqueda del dia de la reservacion en ese mes para compracion 
    String diaFechaIngreso, diaFechaSalida;
    int DayIn, DayOut;
//FIN DE NECESARIO
    int days;

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
        

    }

    public void RowHeaderApariencia() {
        jt_Reservas.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 14));
        jt_Reservas.getTableHeader().setOpaque(false);
        jt_Reservas.getTableHeader().setBackground(new Color(32, 136, 203));
        jt_Reservas.getTableHeader().setForeground(new Color(255, 255, 255));

    }

    public void RowApariencia() {
        jt_Reservas.setFont(new Font("Tahoma", Font.BOLD, 13));
        jt_Reservas.setFocusable(false);
        //espacio entre comulnas
        jt_Reservas.setIntercellSpacing(new Dimension(0, 1));
        //altura de columnas 
        jt_Reservas.setRowHeight(25);
        //margen entre filas
        jt_Reservas.setRowMargin(5);
//sin lineas verticles
        jt_Reservas.setShowVerticalLines(true);
        jt_Reservas.setGridColor(Color.decode("#D3EFFC"));
        jt_Reservas.setShowHorizontalLines(false);

    }

    public void tamañoTabla() {
        TableColumnModel columnModel = jt_Reservas.getColumnModel();
        //evita la seleccion de las filas en la tabla, SOLO ES PARA VISUALIZAR, NO NECESITA ACCION ALGUNA
        jt_Reservas.setEnabled(false);
        columnModel.getColumn(0).setPreferredWidth(390);
   
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
              
                break;
            case 2:
                cal = new GregorianCalendar(ye, Calendar.FEBRUARY, 1);
                mesActual = "Febrero";
              
                break;
            case 3:
                cal = new GregorianCalendar(ye, Calendar.MARCH, 1);
                mesActual = "Marzo";
              
               break;
            case 4:
                cal = new GregorianCalendar(ye, Calendar.APRIL, 1);
                mesActual = "Abril";
                
                break;
            case 5:
                cal = new GregorianCalendar(ye, Calendar.MAY, 1);
                mesActual = "Mayo";
                
                break;
            case 6:
                cal = new GregorianCalendar(ye, Calendar.JUNE, 1);
                mesActual = "Junio";
                
                break;
            case 7:
                cal = new GregorianCalendar(ye, Calendar.JULY, 1);
                mesActual = "Julio";
                
                break;
            case 8:
                cal = new GregorianCalendar(ye, Calendar.AUGUST, 1);
                mesActual = "Agosto";
              
                break;
            case 9:
                cal = new GregorianCalendar(ye, Calendar.SEPTEMBER, 1);
                mesActual = "Septiembre";
                
                break;
            case 10:

                cal = new GregorianCalendar(ye, Calendar.OCTOBER, 1);
                mesActual = "Octubre";
                
                break;
            case 11:
                cal = new GregorianCalendar(ye, Calendar.NOVEMBER, 1);
                mesActual = "Noviembre";
               
                break;

            case 12:
                cal = new GregorianCalendar(ye, Calendar.DECEMBER, 1);
                mesActual = "Diciembre";
               
                break;

        }
//DATOS DEL MES Y AÑO ACTUALES
//        lb_año.setText(Integer.toString(ye));
        lb_mes.setText(mesActual);

        /*CODIGO QUE CARGA LOS TITULOS DE LA TABLA .. EJEMPLO
                                DIAS DEL MES
HABITACION  | 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 ETC

         */
        days = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // int numero de dias        
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
            //  Object[] columna = new Object[days + 1]; //objeto que sirve para guardar dias o como medida de comparacion
            // Object[] columna2 = new Object[days + 1];
            for (ObjetoHabitacion habitacion : ch.selectHabitacion()) {
                Object[] columna = new Object[days + 1];
                columna[0] = habitacion.getNombre();

                for (ObjetoReservacion objetoReservacion : cr.selectReservacion()) {

//busqueda del numero de mes de la reservaciion para comparaciones
                    MesdeFechaIngreso = new SimpleDateFormat("MM").format(dateFormat.parse(objetoReservacion.getFechaIngresoCompleta()));
                    MesdeFechaSalida = new SimpleDateFormat("MM").format(dateFormat.parse(objetoReservacion.getFechaSalidaCompleta()));
                    mesIn = Integer.parseInt(MesdeFechaIngreso);
                    mesOut = Integer.parseInt(MesdeFechaSalida);
//busqueda del numero de dia de la reservacion para comparaciones
                    diaFechaIngreso = new SimpleDateFormat("dd").format(dateFormat.parse(objetoReservacion.getFechaIngresoCompleta()));
                    diaFechaSalida = new SimpleDateFormat("dd").format(dateFormat.parse(objetoReservacion.getFechaSalidaCompleta()));
                    DayIn = Integer.parseInt(diaFechaIngreso);
                    DayOut = Integer.parseInt(diaFechaSalida);

                    for (int i = 1; i < columna.length; i++) {  //ARREGLO DE DIAS 
//comparacion si el mes de la reservaciones es igual al mes actual (mes inicial , mes final)
                        if (mesIn == mes && mesOut == mes) {
//comparamos si el id de la reservacion en el mes, es igual al id de alguna de las habitaciones
                            if (objetoReservacion.getIdHabitacion() == habitacion.getIdHabitacion()) {

/*comparamos el dia de ingreso de 1a reservacion(DayIn) con la posicion del arreglo que tiene como size()
al objeto columna y si son iguales , iniamos el siguiente arreglo a partir de esa posicion hasta 
la posicion final que seria DayOut*/
                                if (i >= DayIn && i <= DayOut) {
                                    columna[i] = " ";
                                }
//SIRVE PARA COMPROBAR QUE SE HACE EL LLENADO EN LAS POSICIONES Y HABITACIONES REQUERIDAS
//-----System.out.println(habitacion.getNombre() + ": Dia " + i + "-> " + columna[i]);------IMPORTANTE
                            }
                        } else if (mesIn == mes && !(mesOut == mes)) {

//comparamos si el id de la reservacion en el mes, es igual al id de alguna de las habitaciones
                            if (objetoReservacion.getIdHabitacion() == habitacion.getIdHabitacion()) {

//comparamos el dia de ingreso de 1a reservacion(DayIn) con la posicion del arreglo que tiene como size() al objeto columna
//y si son iguales , iniamos el siguiente arreglo a partir de esa posicion 
                                //hasta la posicion final que seria Days
                                if (DayIn == i) {

                                    /*arreglo iniciado con el primer dia de la reservacion y finalizado con el dia de
termino del mes actual (days  nos dato el total de dias del mes actual o en el que se encuentra la vista)*/
                                    for (int p = DayIn; p <= days; p++) {
//llenamos la tabla de x para denotar en la tabla donde hay fechas reservadas
                                        columna[p] = " ";
                                    }
                                }
//si no existe ninguna reservacion , rellenamos la tabla con cuialquier otro caracter
                            }

//(2 DE 2)excepcion en caso de que la reservacion este en 2 meses diferentes (ejemp. ehero 22- febrero 3)
//y carga desde el dia 1 del siguiente mes hasta el ultimo dia de la reservacion 3 febrero
                        } else if (!(mesIn == mes) && mesOut == mes) {

 //comparamos si el id de la reservacion en el mes, es igual al id de alguna de las habitaciones
                            if (objetoReservacion.getIdHabitacion() == habitacion.getIdHabitacion()) {

/*comparamos que la posicion del arreglo sea igual a 1 para comenzar el llnado de la reservacion 
con 2 fechas en 2 meses diferentes 
este apartado comienza a llenar desde el dia 1 del siguiente mes , hasta el dia (Dayout) donde finaliza 
la reservacion */
                                if (i == 1) {

/*arreglo iniciado con el primer dia de la reservacion y finalizado con el dia de
termino de la reservacion (DayOut) para pintar la tabla con x */
                                    for (int p = i; p <= DayOut; p++) {
//llenamos la tabla de x para denotar en la tabla donde hay fechas reservadas
                                        columna[p] = " ";
                                    
                                    }
                                }

                            }
                        }
                    }

                }
//ingresa cada fila en la tabla , en cada iteracion de habitacion
                modelo.addRow(columna);
               
/*borra los valores guardados en el objeto columna, para que no se pinten los mismos valores de filas anteriores 
en las siguientes finas*/
                columna = null;
                
            }

        } catch (ParseException ex) {
            Logger.getLogger(ControladorRecepciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        headerRenderer.setBackground(new Color(102, 205, 170));
        jt_Reservas.setModel(modelo);
        jt_Reservas.getColumnModel().getColumn(0).setPreferredWidth(200);
        
       

   
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
        lb_mes = new javax.swing.JLabel();
        btnBeforeMont = new principal.MaterialButton();
        btnNextMonth = new principal.MaterialButton();
        scroll_Tabla = new javax.swing.JScrollPane();
        jt_Reservas = new javax.swing.JTable()
        {
            @Override

            public Component prepareRenderer (TableCellRenderer renderer, int rowIndex, int columnIndex){
                Component componenet = super.prepareRenderer(renderer, rowIndex, columnIndex);
                Object value = getModel().getValueAt(rowIndex,columnIndex);

                if(columnIndex >=1 && columnIndex<days+1){
                    if(value ==" "){
                        componenet.setBackground(new Color(97, 212, 195));
                    }else{
                        componenet.setBackground(Color.WHITE);
                    }

                }else{
                    componenet.setBackground(Color.WHITE);
                }
                return componenet;
            }

        }

        ;

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

        lb_mes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb_mes.setText("mes");
        Pn_Pisos.add(lb_mes, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, -1, -1));

        btnBeforeMont.setBackground(new java.awt.Color(40, 180, 99));
        btnBeforeMont.setForeground(new java.awt.Color(255, 255, 255));
        btnBeforeMont.setText("< Anterior");
        btnBeforeMont.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBeforeMont.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btnBeforeMont.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBeforeMontMouseClicked(evt);
            }
        });
        btnBeforeMont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBeforeMontActionPerformed(evt);
            }
        });
        Pn_Pisos.add(btnBeforeMont, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 6, 100, 40));

        btnNextMonth.setBackground(new java.awt.Color(40, 180, 99));
        btnNextMonth.setForeground(new java.awt.Color(255, 255, 255));
        btnNextMonth.setText("Siguiente >");
        btnNextMonth.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNextMonth.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btnNextMonth.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNextMonthMouseClicked(evt);
            }
        });
        btnNextMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextMonthActionPerformed(evt);
            }
        });
        Pn_Pisos.add(btnNextMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 6, 100, 40));

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

    private void jt_ReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_ReservasMouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_jt_ReservasMouseClicked

    private void btnBeforeMontMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBeforeMontMouseClicked

    }//GEN-LAST:event_btnBeforeMontMouseClicked

    private void btnBeforeMontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBeforeMontActionPerformed

        m = m - 1;
        cargarReservas();
        tamañoTabla();
                // TODO add your handling code here:
    }//GEN-LAST:event_btnBeforeMontActionPerformed

    private void btnNextMonthMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMonthMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextMonthMouseClicked

    private void btnNextMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextMonthActionPerformed

        m = m + 1;
        cargarReservas();
        tamañoTabla();        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextMonthActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Pn_Pisos;
    public static principal.MaterialButton btnBeforeMont;
    public static principal.MaterialButton btnNextMonth;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JTable jt_Reservas;
    public static javax.swing.JLabel lb_mes;
    private javax.swing.JLabel lb_titulo;
    private javax.swing.JScrollPane scroll_Tabla;
    // End of variables declaration//GEN-END:variables
}
