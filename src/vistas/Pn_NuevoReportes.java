package vistas;

import Utilerias.ComponenteGrafica;
import controladores.*;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.ImageIcon;
import objetos.ObjetoHabitacion;

/**
 *
 * @author KSGAMER
 */
public class Pn_NuevoReportes extends javax.swing.JPanel {

    //Se declaran las clases a utilizar
    private ControladorReportes reportes = new ControladorReportes();
    private ControladorHabitaciones habitaciones = new ControladorHabitaciones();
    //Se declara una variable que actuara como un formato estandar de fecha
    private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    //Se declara una bandera instanciandola en falso
    private boolean flag = false;

    /**
     * Creates new form pnlHome
     */
    public Pn_NuevoReportes() {
        initComponents();
       
        //Se cargan los datos a utilizar
        habitaciones.tablaHabitaciones();
        //Se instancia el método que actua al dar click en los JCalendar
        mouseOnClickGananciasFechas();
        //Se instancia el combobox con los datos extraidos de base de datos
        comboHabitacion();
        //Se instancian las fechas de inicio y final de los JCalendar
        rangoFechas();
        //Se instacia el método de cargado de gráficas y tablas
        estadisticas();
        //Se instancia el método encargado de la velocidad del Scrolleo
        configScroll();
       
    }
    


    //Método que carga un combo box con las habitaciones
    public void comboHabitacion() {
        //Se remueven los elementos del combo
        cbHabitacion.removeAllItems();
        //Se agrega un nuevo elemento
        cbHabitacion.addItem("Todas las habitaciones");
        //Se recooren las habitaciones
        for (ObjetoHabitacion objetoHabitacion : habitaciones.selectHabitacion()) {
            //Se agregan las habitaciones al combo
            cbHabitacion.addItem(objetoHabitacion.getNombre());
        }
    }

    //Método que ajusta la velocidad del Scrolleo
    public void configScroll() {
        //Se agrega velocidad al Scroll del panel de Estadisticas
        ScrollEstadisticas.getVerticalScrollBar().setUnitIncrement(20);
        //Se agrega velocidad al Scroll del panel de Reportes
        ScrollReportes.getVerticalScrollBar().setUnitIncrement(20);
    }

    //Método que instancia los JCalendar con una fecha inicial y una fecha final
    public void rangoFechas() {
        //Se obtiene la fecha actual del equipo
        Calendar fecha = Calendar.getInstance();
        //Se asigna la fecha al JCalendar de fecha final
        cFinalGananciasFechas.setDate(fecha.getTime());
        //Se restan 14 dias a la fecha final
        fecha.add(Calendar.DATE, -14);
        //Se asigna la fecha al JCalendar de fecha inicial
        cInicialGananciasFechas.setDate(fecha.getTime());
    }

    //Método que genera una grafica con la habitación mas popular reservada
    public void popularHabitacion() {
        //Se declara un acumulador iniciandolo en 0
        int total = 0;
        //Se recorre la tabla hasta la ultima fila
        for (int i = 0; i < reportes.habitacionPopular().getRowCount(); i++) {
            //Se asigna el valor extraido de la tabla al acumulador
            total = total + Integer.parseInt(reportes.habitacionPopular().getValueAt(i, 1).toString());
        }
        //Se asigna el total acumulado al label
        lbTotalReservacionesGrafica.setText(String.valueOf(total));
        //Se asigna el toal acumulado al label
        lbTotalReservaciones.setText(String.valueOf(total));
        //Se remueven todos los componentes del panel contenedorPopularidad
        contenedorPopularidad.removeAll();
        //Se agrega un nuevo componente pasando el tipo de grafica a generar, la tabla donde se extraeran los datos, si requiere titulos en los ejes X y Y y las dimensiones del componente
        contenedorPopularidad.add(new ComponenteGrafica().componenteGrafica("Anillo", reportes.habitacionPopular(), "", "", 320, 225));
        //Se declara que el componente sera opaco para generar transparencia
        contenedorPopularidad.setOpaque(false);
    }

    //Método que genera una grafica con el monto total de las reservaciones cobradas
    public void montoTotal() {
        //Se declara un acumulador de tipo double (Decimal) iniciandolo en 0
        double total = 0;
        //Se recorre la tabla hasta el numero total de filas que contiene
        for (int i = 0; i < reportes.ganancias().getRowCount(); i++) {
            //Se asigna el valor extradido de la tabla al acumulador
            total = total + Double.parseDouble(reportes.ganancias().getValueAt(i, 0).toString());
        }
        //Se asigna el acumulador al label
        lbGanancias.setText("$" + total + "0");
        //Se remueven los elementos del panel contendorGananciasTotales
        contenedorGananciasTotales.removeAll();
        //Se agrega un nuevo componente pasando el tipo de grafica a generar, la tabla para la extracción de datos, los titulos de los ejes X y Y y las dimensiones de altura y ancho
        contenedorGananciasTotales.add(new ComponenteGrafica().componenteGrafica("Barras", reportes.ganancias(), "Fechas", "Ganancias Brutas", 500, 325));
        //Se declara que el componente tendra transparencia
        contenedorGananciasTotales.setOpaque(false);
    }

    //Método que genera los indices de facturación
    public void indiceFacturacion() {
        //Si el total de filas de la tabla es igual a 0 entra
        if (reportes.facturacionIndice().getRowCount() == 0) {
            //Se asigna 0 al label ya que no contiene valores a recorrer
            lbTotalIndiceFacturacion.setText("0");
            lbConFacturacion.setText("0");
            lbSinFacturacion.setText("0");
        } else {//De lo contrario 
            //Se declara un acumulador de tipo int
            int total = 0;
            //Se recorre la tabla hasta el total de filas que contiene
            for (int i = 0; i < reportes.facturacionIndice().getRowCount(); i++) {
                //Se asigna el valor extraido de la tabla al acumulador
                total = total + Integer.parseInt(reportes.facturacionIndice().getValueAt(i, 1).toString());
            }
            //Se asigna el acumulador al label
            lbTotalIndiceFacturacion.setText(String.valueOf(total));
            //Se extrae la información de la tabla y se asigna al label
            lbConFacturacion.setText(reportes.facturacionIndice().getValueAt(0, 1).toString());
            if(reportes.facturacionIndice().getRowCount() == 1) {
                lbSinFacturacion.setText("0");
            } else {
                lbSinFacturacion.setText(reportes.facturacionIndice().getValueAt(1, 1).toString());
            }
        }
        //Se remueven todos los elementos del panel contenedorIndiceFacturacion
        contenedorIndiceFacturacion.removeAll();
        //Se agrega un nuevo componente pasando el tipo de grafica a generar, la tabla para la extracción de datos, los titulos de los ejes X y Y y las dimesiones del componente
        contenedorIndiceFacturacion.add(new ComponenteGrafica().componenteGrafica("Anillo", reportes.facturacionIndice(), "", "", 300, 196));
        //Se declara que el componente tendra trasnparencia
        contenedorIndiceFacturacion.setOpaque(false);
    }

    //Método que genera el indice de Reservaciones
    public void indiceReservacion() {
        //Se declara un acumulador de tipo int
        int total = 0;
        //Se recorre la tabla hasta el numero total de filas que contiene
        for (int i = 0; i < reportes.ReservacionesIndice().getRowCount(); i++) {
            //Se asigna el valor extraido de la tabla al acumulador
            total = total + Integer.parseInt(reportes.ReservacionesIndice().getValueAt(i, 0).toString());
        }
        //Se asigna el acumulador al label
        lbTotalReservacionesFechas.setText(String.valueOf(total));
        //Se remueven los elementos del panel contenedorIndiceReservaciones
        contenedorIndiceReservaciones.removeAll();
        //Se agrega un nuevo componente pasando el tipo de grafica a generar, la tabla para la extracción de datos, los titulos de los ejes X y Y, además de las dimesiones de alto y ancho de la grafica
        contenedorIndiceReservaciones.add(new ComponenteGrafica().componenteGrafica("Barras", reportes.ReservacionesIndice(), "Fechas", "Total", 460, 325));
        //Se declara que el panel tendra trasnparencia
        contenedorIndiceReservaciones.setOpaque(false);
    }

    //Método que genera una grafica con las ganancias obtenidas en determinadas fechas
    public void gananciasFechas() {
        //Se asigna el modelo del Controlador reportes a la tabla tbGananciasFechas
        tbGananciasFechas.setModel(reportes.fechasGanancias(formato.format(cInicialGananciasFechas.getDate().getTime()), formato.format(cFinalGananciasFechas.getDate().getTime()), "Tabla"));
        //Se declara un acumulador de tipo double (Decimal)
        double total = 0;
        //Se recorre la tabla hasta el numero total de filas que contiene
        for (int i = 0; i < reportes.fechasGanancias(formato.format(cInicialGananciasFechas.getDate().getTime()), formato.format(cFinalGananciasFechas.getDate().getTime()), "Grafica").getRowCount(); i++) {
            //Se asigna el valor extraido de la tabla al acumulador
            total = total + Integer.parseInt(reportes.fechasGanancias(formato.format(cInicialGananciasFechas.getDate().getTime()), formato.format(cFinalGananciasFechas.getDate().getTime()), "Grafica").getValueAt(i, 0).toString());
        }
        //Se asigna el acumulador al label
        lbGananciasFechas.setText("$" + total + "0");
        //Se remueven los elementos del panel contenedorGananciasFechaHabitacion
        contenedorGananciasFechasHabitacion.removeAll();
        //Se genera un nuevo componente pasando el tipo de grafica a generar, la tabla donde se extraera la informacion, pasando las fechas a filtrar de inicio y final, y la bandera (Tabla o Grafica), los titulos de los ejes X y Y y las dimesiones de la grafica
        contenedorGananciasFechasHabitacion.add(new ComponenteGrafica().componenteGrafica("Barras", reportes.fechasGanancias(formato.format(cInicialGananciasFechas.getDate().getTime()), formato.format(cFinalGananciasFechas.getDate().getTime()), "Grafica"), "Reservaciones", "Ganancias", 440, 250));
        //Se declara que el componente tendra transparencia
        contenedorGananciasFechasHabitacion.setOpaque(false);
    }

    //Método que genera una grafica de las ganancias por cada habitacion
    public void gananciasHabitacion() {
        //Se asigna el el modelo extraido del controlador al tbGananciasHabitacion pasando el parametro de tabla
        tbGananciasHabitacion.setModel(reportes.gananciasHabitacion(cbHabitacion.getSelectedItem().toString(), "Tabla"));
        double total = 0;
        //Se recorre la tabla hasta el total de filas que contiene pasando el modo grafica
        for (int i = 0; i < reportes.gananciasHabitacion(cbHabitacion.getSelectedItem().toString(), "Grafica").getRowCount(); i++) {
            //Se asigna el valor obtenido de la tabla al acumulador
            total = total + Double.parseDouble(reportes.gananciasHabitacion(cbHabitacion.getSelectedItem().toString(), "Grafica").getValueAt(i, 0).toString());
        }
        //Se asigna el acumulador al label
        lbGananciasHabitacion.setText("$" + total + "0");
        //Se remueven los elementos del panel contenedorGananciasHabitacion
        contenedorGananciasHabitacion.removeAll();
        //Se ingresa un nuevo componente pasando el tipo de grafica a generar, la tabla de donde se extraera la información, se pasan los titulos de los ejes X y Y y además las dimesiones del componente
        contenedorGananciasHabitacion.add(new ComponenteGrafica().componenteGrafica("Barras", reportes.gananciasHabitacion(cbHabitacion.getSelectedItem().toString(), "Grafica"), "Fecha", "Total", 440, 270));
        //Se declara que el componente tendra trasnparencia
        contenedorGananciasHabitacion.setOpaque(false);
    }

    //Método que genera el porcentaje de ganancias
    public void porcentajeGanancias() {
        //Si las filas totales de la tabla son igual a 0 entra en el if
        if (reportes.porcentajeGanancias().getRowCount() == 0) {
            //Se asigna el valor 0.00 al label
            lbGananciaHoy.setText("$" + 0.00);
            //Se asigna el valor 0 al label
            lbPorcentajeGanancias.setText("0%");
            //Se asigna el color de la letra por el siguente
            lbPorcentajeGanancias.setForeground(new Color(153, 153, 153));
            //Se elimina el icono del label
            lbPorcentajeGanancias.setIcon(new ImageIcon());
        } else {
            //Se extrae el valor de la tabla y se asigna al label
            lbGananciaHoy.setText("$" + Double.parseDouble(reportes.porcentajeGanancias().getValueAt(0, 1).toString()) + "0");
            //Se declara una variable que actuara como el porcentaje extrayendo la información de la tabla
            double porcentaje = 0;
            if (reportes.porcentajeGanancias().getRowCount() == 1) {
                porcentaje = Double.parseDouble(reportes.porcentajeGanancias().getValueAt(0, 1).toString()) * 100 / Double.parseDouble(reportes.porcentajeGanancias().getValueAt(0, 1).toString());
            } else {
                 porcentaje = Double.parseDouble(reportes.porcentajeGanancias().getValueAt(0, 1).toString()) * 100 / Double.parseDouble(reportes.porcentajeGanancias().getValueAt(1, 1).toString());
            }
            //Se asigna el valor del porcentaje al label
            lbPorcentajeGanancias.setText(String.valueOf(porcentaje).substring(0, 5) + "%");
            //Si el porcentaje es mayor a 100 entra en el if
            if (porcentaje >= 100) {
                //Se cambia el color de la letra del label por el siguiente
                lbPorcentajeGanancias.setForeground(new Color(0, 191, 95));
                //Se cambia el icono del label por el siguiente
                lbPorcentajeGanancias.setIcon(new ImageIcon(getClass().getResource("/Imagenes/186407-16.png")));
            } else {//En caso contrario
                //Se cambia el color de la letra del label por el siguiente
                lbPorcentajeGanancias.setForeground(new Color(229, 16, 16));
                //Se cambia el icono del label por el siguiente
                lbPorcentajeGanancias.setIcon(new ImageIcon(getClass().getResource("/Imagenes/186411-16.png")));
            }
        }
    }

    //Método que genera los porcentajes de Reservacion
    public void porcentajeReservacion() {
        //Si el total de las filas de la tabla es igual a 0 entra en el if
        if (reportes.porcentajeReservacion().getRowCount() == 0) {
            //Se asigna el siguiente valor al label
            lbReservacionesHoy.setText("0");
            //Se asigna el siguiente valor al label
            lbPorcentajeReservaciones.setText("0%");
            //Se cambia el color de la letra del label por el siguiente
            lbPorcentajeGanancias.setForeground(new Color(153, 153, 153));
            //Se elimina el icono del label
            lbPorcentajeReservaciones.setIcon(new ImageIcon());
        } else if (reportes.porcentajeGanancias().getRowCount() >= 2) {//En caso contrario
            //Se asigna el valor extraido de la tabla al label
            lbReservacionesHoy.setText(String.valueOf(Integer.parseInt(reportes.porcentajeReservacion().getValueAt(0, 1).toString())));
            //Se declara una variable que actuara como el porcentaje
            double porcentaje = Double.parseDouble(reportes.porcentajeReservacion().getValueAt(0, 1).toString()) * 100 / Double.parseDouble(reportes.porcentajeReservacion().getValueAt(1, 1).toString());
            //Se asigna el porcentaje al label
            lbPorcentajeReservaciones.setText(String.valueOf(porcentaje) + "%");
            //Si el porcentaje es mayor a 100 entra en el if
            if (porcentaje >= 100) {
                //Se cambia el color de la letra por el siguiente
                lbPorcentajeReservaciones.setForeground(new Color(0, 191, 95));
                //Se cambia el icono del label por el siguiente
                lbPorcentajeReservaciones.setIcon(new ImageIcon(getClass().getResource("/Imagenes/186407-16.png")));
            } else { //En caso contrario
                //Se cambia el color de la letra por el siguiente
                lbPorcentajeReservaciones.setForeground(new Color(0, 191, 95));
                //Se cambia el icono por el siguiente
                lbPorcentajeReservaciones.setIcon(new ImageIcon(getClass().getResource("/Imagenes/186407-16.png")));
            }
        } else {
            //Se asigna el siguiente valor al label
            lbReservacionesHoy.setText("0");
            //Se asigna el siguiente valor al label
            lbPorcentajeReservaciones.setText("0%");
            //Se cambia el color de la letra del label por el siguiente
            lbPorcentajeGanancias.setForeground(new Color(153, 153, 153));
            //Se elimina el icono del label
            lbPorcentajeReservaciones.setIcon(new ImageIcon());
        }
    }

    //Método que carga una tabla
    public void cobrosUsuarios() {
        //Se asigna el modelo de la tabla extraido del controlador a la tabla tbCobrosUsuarios pasando el parametro a filtrar
        tbCobrosUsuarios.setModel(reportes.cobroPorUsuarios(txtBuscarUsuario.getText()));
    }

    //Método que inicializa las graficas, porcentajes y tablas
    public void estadisticas() {
        cobrosUsuarios();
        gananciasHabitacion();
        gananciasFechas();
        porcentajeReservacion();
        porcentajeGanancias();
        indiceReservacion();
        indiceFacturacion();
        montoTotal();
        popularHabitacion();
    }

    //Método que detecta los eventos click en los JCalendar
    public void mouseOnClickGananciasFechas() {
        //Se obtiene el componente 0 del Calendario Incial, se agrega un evento de escucha al componente, se pasa el evento de adaptación del mouse
        cInicialGananciasFechas.getComponent(0).addMouseListener(new java.awt.event.MouseAdapter() {
            //Método que obtiene el evento click del componente 0
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //Si hay un evento cambia el valor de la bandera a true
                flag = true;
            }
        });
        //Se obtiene el componente 1 del Calendario Incial, se agrega un evento de escucha al componente, se pasa el evento de adaptación del mouse
        cInicialGananciasFechas.getComponent(1).addMouseListener(new java.awt.event.MouseAdapter() {
            //Método que obtiene el evento click del componente 0
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //Si hay un evento cambia el valor de la bandera a true
                flag = true;
            }
        });
        //Se obtiene el componente 0 del Calendario Final, se agrega un evento de escucha al componente, se pasa el evento de adaptación del mouse
        cFinalGananciasFechas.getComponent(0).addMouseListener(new java.awt.event.MouseAdapter() {
            //Método que obtiene el evento click del componente 0
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //Si hay un evento cambia el valor de la bandera a true
                flag = true;
            }
        });
        //Se obtiene el componente 1 del Calendario Final, se agrega un evento de escucha al componente, se pasa el evento de adaptación del mouse
        cFinalGananciasFechas.getComponent(1).addMouseListener(new java.awt.event.MouseAdapter() {
            //Método que obtiene el evento click del componente 0
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //Si hay un evento cambia el valor de la bandera a true
                flag = true;
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jp_central = new javax.swing.JPanel();
        jTab_Reportes = new javax.swing.JTabbedPane();
        ScrollEstadisticas = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        JPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbTotalReservaciones = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbTotalReservacionesGrafica = new javax.swing.JLabel();
        contenedorPopularidad = new javax.swing.JPanel();
        JPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbSinFacturacion = new javax.swing.JLabel();
        lbConFacturacion = new javax.swing.JLabel();
        lbTotalIndiceFacturacion = new javax.swing.JLabel();
        contenedorIndiceFacturacion = new javax.swing.JPanel();
        JPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbGanancias = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        contenedorGananciasTotales = new javax.swing.JPanel();
        JPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lbTotalReservacionesFechas = new javax.swing.JLabel();
        contenedorIndiceReservaciones = new javax.swing.JPanel();
        indiceFacturacion1 = new javax.swing.JPanel();
        PorcentajeGanancias = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        lbGananciaHoy = new javax.swing.JLabel();
        lbPorcentajeGanancias = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        PorcentajeReservaciones = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbReservacionesHoy = new javax.swing.JLabel();
        lbPorcentajeReservaciones = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        ScrollReportes = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        JPanel6 = new javax.swing.JPanel();
        cInicialGananciasFechas = new com.toedter.calendar.JDateChooser();
        jLabel21 = new javax.swing.JLabel();
        cFinalGananciasFechas = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lbGananciasFechas = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        contenedorGananciasFechasHabitacion = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbGananciasFechas = new javax.swing.JTable();
        materialButton1 = new principal.MaterialButton();
        materialButton2 = new principal.MaterialButton();
        JPanel7 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        cbHabitacion = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lbGananciasHabitacion = new javax.swing.JLabel();
        contenedorGananciasHabitacion = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbGananciasHabitacion = new javax.swing.JTable();
        materialButton3 = new principal.MaterialButton();
        materialButton4 = new principal.MaterialButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbCobrosUsuarios = new javax.swing.JTable();
        txtBuscarUsuario = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        btnCobroUsuarioExcel = new principal.MaterialButton();
        btnCobroUsuarioPDF = new principal.MaterialButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lb_Id = new javax.swing.JLabel();

        setBackground(new java.awt.Color(84, 110, 122));
        setForeground(new java.awt.Color(255, 255, 255));
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(1086, 684));

        jp_central.setBackground(new java.awt.Color(255, 255, 255));
        jp_central.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTab_Reportes.setBackground(new java.awt.Color(255, 255, 255));
        jTab_Reportes.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jTab_Reportes.setPreferredSize(new java.awt.Dimension(1090, 710));

        ScrollEstadisticas.setBackground(new java.awt.Color(204, 204, 204));
        ScrollEstadisticas.setBorder(null);
        ScrollEstadisticas.setPreferredSize(new java.awt.Dimension(1090, 710));

        jPanel4.setBackground(new java.awt.Color(245, 245, 245));
        jPanel4.setMaximumSize(new java.awt.Dimension(1090, 800));
        jPanel4.setPreferredSize(new java.awt.Dimension(1090, 800));
        jPanel4.setLayout(null);

        JPanel1.setBackground(new java.awt.Color(255, 255, 255));
        JPanel1.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/4171938-24.png"))); // NOI18N
        jLabel3.setText("Habitaciones más Reservadas");
        JPanel1.add(jLabel3);
        jLabel3.setBounds(10, 10, 300, 32);

        lbTotalReservaciones.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbTotalReservaciones.setForeground(new java.awt.Color(51, 153, 255));
        lbTotalReservaciones.setText("jLabel4");
        JPanel1.add(lbTotalReservaciones);
        lbTotalReservaciones.setBounds(110, 50, 44, 15);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Reservaciones:");
        JPanel1.add(jLabel5);
        jLabel5.setBounds(10, 50, 90, 15);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 153, 153));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Total");
        JPanel1.add(jLabel13);
        jLabel13.setBounds(90, 140, 140, 20);

        lbTotalReservacionesGrafica.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        lbTotalReservacionesGrafica.setForeground(new java.awt.Color(102, 102, 102));
        lbTotalReservacionesGrafica.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTotalReservacionesGrafica.setText("jLabel11");
        JPanel1.add(lbTotalReservacionesGrafica);
        lbTotalReservacionesGrafica.setBounds(90, 160, 140, 30);

        contenedorPopularidad.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout contenedorPopularidadLayout = new javax.swing.GroupLayout(contenedorPopularidad);
        contenedorPopularidad.setLayout(contenedorPopularidadLayout);
        contenedorPopularidadLayout.setHorizontalGroup(
            contenedorPopularidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );
        contenedorPopularidadLayout.setVerticalGroup(
            contenedorPopularidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );

        JPanel1.add(contenedorPopularidad);
        contenedorPopularidad.setBounds(0, 60, 320, 240);

        jPanel4.add(JPanel1);
        JPanel1.setBounds(10, 11, 320, 300);

        JPanel4.setBackground(new java.awt.Color(255, 255, 255));
        JPanel4.setPreferredSize(new java.awt.Dimension(300, 300));
        JPanel4.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 153, 153));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Total");
        JPanel4.add(jLabel10);
        jLabel10.setBounds(100, 140, 140, 20);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/4172166-32.png"))); // NOI18N
        jLabel7.setText(" Indice de Facturación");
        JPanel4.add(jLabel7);
        jLabel7.setBounds(10, 11, 250, 32);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Sin Facturación:");
        JPanel4.add(jLabel8);
        jLabel8.setBounds(10, 50, 97, 15);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Con Facturación:");
        JPanel4.add(jLabel9);
        jLabel9.setBounds(10, 70, 102, 15);

        lbSinFacturacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbSinFacturacion.setForeground(new java.awt.Color(51, 153, 255));
        lbSinFacturacion.setText("jLabel10");
        JPanel4.add(lbSinFacturacion);
        lbSinFacturacion.setBounds(130, 50, 52, 15);

        lbConFacturacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbConFacturacion.setForeground(new java.awt.Color(51, 153, 255));
        lbConFacturacion.setText("jLabel10");
        JPanel4.add(lbConFacturacion);
        lbConFacturacion.setBounds(130, 70, 52, 15);

        lbTotalIndiceFacturacion.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbTotalIndiceFacturacion.setForeground(new java.awt.Color(102, 102, 102));
        lbTotalIndiceFacturacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTotalIndiceFacturacion.setText("jLabel11");
        JPanel4.add(lbTotalIndiceFacturacion);
        lbTotalIndiceFacturacion.setBounds(100, 150, 140, 40);

        contenedorIndiceFacturacion.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout contenedorIndiceFacturacionLayout = new javax.swing.GroupLayout(contenedorIndiceFacturacion);
        contenedorIndiceFacturacion.setLayout(contenedorIndiceFacturacionLayout);
        contenedorIndiceFacturacionLayout.setHorizontalGroup(
            contenedorIndiceFacturacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );
        contenedorIndiceFacturacionLayout.setVerticalGroup(
            contenedorIndiceFacturacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );

        JPanel4.add(contenedorIndiceFacturacion);
        contenedorIndiceFacturacion.setBounds(20, 70, 320, 220);

        jPanel4.add(JPanel4);
        JPanel4.setBounds(340, 10, 340, 300);

        JPanel3.setBackground(new java.awt.Color(255, 255, 255));
        JPanel3.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/134157-32.png"))); // NOI18N
        jLabel2.setText(" Ganancias Totales");
        JPanel3.add(jLabel2);
        jLabel2.setBounds(10, 11, 210, 32);

        lbGanancias.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbGanancias.setForeground(new java.awt.Color(51, 153, 255));
        lbGanancias.setText("jLabel3");
        JPanel3.add(lbGanancias);
        lbGanancias.setBounds(50, 50, 130, 15);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Total:");
        JPanel3.add(jLabel4);
        jLabel4.setBounds(10, 50, 35, 15);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(204, 204, 204));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconfinder_star_1930255.png"))); // NOI18N
        jLabel18.setText("Ultimos 7 Días");
        jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        JPanel3.add(jLabel18);
        jLabel18.setBounds(420, 20, 100, 20);

        contenedorGananciasTotales.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout contenedorGananciasTotalesLayout = new javax.swing.GroupLayout(contenedorGananciasTotales);
        contenedorGananciasTotales.setLayout(contenedorGananciasTotalesLayout);
        contenedorGananciasTotalesLayout.setHorizontalGroup(
            contenedorGananciasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );
        contenedorGananciasTotalesLayout.setVerticalGroup(
            contenedorGananciasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );

        JPanel3.add(contenedorGananciasTotales);
        contenedorGananciasTotales.setBounds(0, 60, 540, 330);

        jPanel4.add(JPanel3);
        JPanel3.setBounds(10, 329, 540, 389);

        JPanel5.setBackground(new java.awt.Color(255, 255, 255));
        JPanel5.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/465050-32.png"))); // NOI18N
        jLabel6.setText(" Reservaciones por Fechas");
        JPanel5.add(jLabel6);
        jLabel6.setBounds(10, 11, 280, 32);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 204, 204));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconfinder_star_1930255.png"))); // NOI18N
        jLabel11.setText("Ultimos 15 Días");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        JPanel5.add(jLabel11);
        jLabel11.setBounds(330, 20, 110, 20);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Reservaciones:");
        JPanel5.add(jLabel20);
        jLabel20.setBounds(10, 50, 90, 15);

        lbTotalReservacionesFechas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbTotalReservacionesFechas.setForeground(new java.awt.Color(51, 153, 255));
        lbTotalReservacionesFechas.setText("jLabel21");
        JPanel5.add(lbTotalReservacionesFechas);
        lbTotalReservacionesFechas.setBounds(110, 50, 70, 15);

        contenedorIndiceReservaciones.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout contenedorIndiceReservacionesLayout = new javax.swing.GroupLayout(contenedorIndiceReservaciones);
        contenedorIndiceReservaciones.setLayout(contenedorIndiceReservacionesLayout);
        contenedorIndiceReservacionesLayout.setHorizontalGroup(
            contenedorIndiceReservacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );
        contenedorIndiceReservacionesLayout.setVerticalGroup(
            contenedorIndiceReservacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );

        JPanel5.add(contenedorIndiceReservaciones);
        contenedorIndiceReservaciones.setBounds(0, 60, 460, 330);

        jPanel4.add(JPanel5);
        JPanel5.setBounds(560, 330, 460, 389);

        indiceFacturacion1.setBackground(new java.awt.Color(245, 245, 245));
        indiceFacturacion1.setPreferredSize(new java.awt.Dimension(300, 300));
        indiceFacturacion1.setLayout(null);

        PorcentajeGanancias.setBackground(new java.awt.Color(255, 255, 255));
        PorcentajeGanancias.setLayout(null);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/322465-32.png"))); // NOI18N
        jLabel14.setText(" Porcentaje de Ganancias");
        PorcentajeGanancias.add(jLabel14);
        jLabel14.setBounds(10, 11, 280, 32);

        lbGananciaHoy.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lbGananciaHoy.setForeground(new java.awt.Color(51, 153, 255));
        lbGananciaHoy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbGananciaHoy.setText("jLabel16");
        PorcentajeGanancias.add(lbGananciaHoy);
        lbGananciaHoy.setBounds(60, 70, 190, 29);

        lbPorcentajeGanancias.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbPorcentajeGanancias.setForeground(new java.awt.Color(0, 191, 95));
        lbPorcentajeGanancias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/186407-16.png"))); // NOI18N
        lbPorcentajeGanancias.setText("jLabel16");
        PorcentajeGanancias.add(lbPorcentajeGanancias);
        lbPorcentajeGanancias.setBounds(70, 100, 100, 30);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(204, 204, 204));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconfinder_star_1930255.png"))); // NOI18N
        jLabel16.setText("Hace un día");
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        PorcentajeGanancias.add(jLabel16);
        jLabel16.setBounds(10, 40, 80, 16);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(153, 153, 153));
        jLabel19.setText("/  día");
        PorcentajeGanancias.add(jLabel19);
        jLabel19.setBounds(170, 100, 50, 30);

        indiceFacturacion1.add(PorcentajeGanancias);
        PorcentajeGanancias.setBounds(10, 0, 330, 140);

        PorcentajeReservaciones.setBackground(new java.awt.Color(255, 255, 255));
        PorcentajeReservaciones.setLayout(null);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/4172165-24.png"))); // NOI18N
        jLabel12.setText(" Porcentaje de Reservaciones");
        PorcentajeReservaciones.add(jLabel12);
        jLabel12.setBounds(10, 11, 296, 24);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 204, 204));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconfinder_star_1930255.png"))); // NOI18N
        jLabel17.setText("Hace un día");
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        PorcentajeReservaciones.add(jLabel17);
        jLabel17.setBounds(10, 40, 110, 16);

        lbReservacionesHoy.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lbReservacionesHoy.setForeground(new java.awt.Color(51, 153, 255));
        lbReservacionesHoy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbReservacionesHoy.setText("jLabel18");
        PorcentajeReservaciones.add(lbReservacionesHoy);
        lbReservacionesHoy.setBounds(80, 60, 160, 44);

        lbPorcentajeReservaciones.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbPorcentajeReservaciones.setForeground(new java.awt.Color(0, 191, 95));
        lbPorcentajeReservaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/186407-16.png"))); // NOI18N
        lbPorcentajeReservaciones.setText("jLabel18");
        PorcentajeReservaciones.add(lbPorcentajeReservaciones);
        lbPorcentajeReservaciones.setBounds(70, 100, 100, 22);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(153, 153, 153));
        jLabel15.setText("/  día");
        PorcentajeReservaciones.add(jLabel15);
        jLabel15.setBounds(170, 100, 50, 22);

        indiceFacturacion1.add(PorcentajeReservaciones);
        PorcentajeReservaciones.setBounds(10, 150, 330, 150);

        jPanel4.add(indiceFacturacion1);
        indiceFacturacion1.setBounds(680, 10, 340, 300);

        ScrollEstadisticas.setViewportView(jPanel4);

        jTab_Reportes.addTab("Estadísticas", ScrollEstadisticas);

        ScrollReportes.setBackground(new java.awt.Color(204, 204, 204));
        ScrollReportes.setBorder(null);
        ScrollReportes.setPreferredSize(new java.awt.Dimension(1090, 2500));

        jPanel5.setBackground(new java.awt.Color(245, 245, 245));
        jPanel5.setMaximumSize(new java.awt.Dimension(1090, 1300));
        jPanel5.setPreferredSize(new java.awt.Dimension(1090, 1300));
        jPanel5.setLayout(null);

        JPanel6.setBackground(new java.awt.Color(255, 255, 255));
        JPanel6.setPreferredSize(new java.awt.Dimension(450, 300));
        JPanel6.setLayout(null);

        cInicialGananciasFechas.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cInicialGananciasFechasPropertyChange(evt);
            }
        });
        JPanel6.add(cInicialGananciasFechas);
        cInicialGananciasFechas.setBounds(100, 320, 120, 20);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Fecha Inicial:");
        JPanel6.add(jLabel21);
        jLabel21.setBounds(20, 320, 80, 20);

        cFinalGananciasFechas.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cFinalGananciasFechasPropertyChange(evt);
            }
        });
        JPanel6.add(cFinalGananciasFechas);
        cFinalGananciasFechas.setBounds(300, 320, 120, 20);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Fecha Final:");
        JPanel6.add(jLabel22);
        jLabel22.setBounds(230, 320, 70, 20);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/134157-32.png"))); // NOI18N
        jLabel23.setText(" Ganancias por Rango de Fechas");
        JPanel6.add(jLabel23);
        jLabel23.setBounds(10, 10, 420, 40);

        lbGananciasFechas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbGananciasFechas.setForeground(new java.awt.Color(51, 153, 255));
        lbGananciasFechas.setText("jLabel24");
        JPanel6.add(lbGananciasFechas);
        lbGananciasFechas.setBounds(60, 50, 70, 15);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Total:");
        JPanel6.add(jLabel25);
        jLabel25.setBounds(10, 50, 35, 15);

        contenedorGananciasFechasHabitacion.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout contenedorGananciasFechasHabitacionLayout = new javax.swing.GroupLayout(contenedorGananciasFechasHabitacion);
        contenedorGananciasFechasHabitacion.setLayout(contenedorGananciasFechasHabitacionLayout);
        contenedorGananciasFechasHabitacionLayout.setHorizontalGroup(
            contenedorGananciasFechasHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );
        contenedorGananciasFechasHabitacionLayout.setVerticalGroup(
            contenedorGananciasFechasHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        JPanel6.add(contenedorGananciasFechasHabitacion);
        contenedorGananciasFechasHabitacion.setBounds(0, 70, 450, 250);

        jPanel5.add(JPanel6);
        JPanel6.setBounds(10, 10, 450, 350);

        tbGananciasFechas = new javax.swing.JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbGananciasFechas.setModel(new javax.swing.table.DefaultTableModel(
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
        tbGananciasFechas.setMaximumSize(new java.awt.Dimension(450, 300));
        tbGananciasFechas.setMinimumSize(new java.awt.Dimension(450, 300));
        tbGananciasFechas.setPreferredSize(new java.awt.Dimension(480, 272));
        tbGananciasFechas.getTableHeader().setResizingAllowed(false);
        tbGananciasFechas.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbGananciasFechas);

        jPanel5.add(jScrollPane1);
        jScrollPane1.setBounds(490, 10, 500, 300);

        materialButton1.setBackground(new java.awt.Color(204, 0, 0));
        materialButton1.setForeground(new java.awt.Color(255, 255, 255));
        materialButton1.setText("Exportar A PDF");
        materialButton1.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        materialButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materialButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(materialButton1);
        materialButton1.setBounds(660, 320, 160, 40);

        materialButton2.setBackground(new java.awt.Color(0, 153, 51));
        materialButton2.setForeground(new java.awt.Color(255, 255, 255));
        materialButton2.setText("Exportar A Excel");
        materialButton2.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        materialButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materialButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(materialButton2);
        materialButton2.setBounds(490, 320, 160, 40);

        JPanel7.setBackground(new java.awt.Color(255, 255, 255));
        JPanel7.setLayout(null);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Habitación:");
        JPanel7.add(jLabel24);
        jLabel24.setBounds(40, 350, 70, 20);

        cbHabitacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbHabitacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbHabitacionItemStateChanged(evt);
            }
        });
        JPanel7.add(cbHabitacion);
        cbHabitacion.setBounds(120, 350, 280, 20);

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/134157-32.png"))); // NOI18N
        jLabel26.setText(" Ganancias por Habitación");
        JPanel7.add(jLabel26);
        jLabel26.setBounds(10, 10, 420, 40);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Total:");
        JPanel7.add(jLabel27);
        jLabel27.setBounds(10, 50, 35, 15);

        lbGananciasHabitacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbGananciasHabitacion.setForeground(new java.awt.Color(51, 153, 255));
        lbGananciasHabitacion.setText("jLabel24");
        JPanel7.add(lbGananciasHabitacion);
        lbGananciasHabitacion.setBounds(60, 50, 70, 15);

        contenedorGananciasHabitacion.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout contenedorGananciasHabitacionLayout = new javax.swing.GroupLayout(contenedorGananciasHabitacion);
        contenedorGananciasHabitacion.setLayout(contenedorGananciasHabitacionLayout);
        contenedorGananciasHabitacionLayout.setHorizontalGroup(
            contenedorGananciasHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );
        contenedorGananciasHabitacionLayout.setVerticalGroup(
            contenedorGananciasHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );

        JPanel7.add(contenedorGananciasHabitacion);
        contenedorGananciasHabitacion.setBounds(0, 70, 450, 280);

        jPanel5.add(JPanel7);
        JPanel7.setBounds(10, 380, 450, 380);

        tbGananciasHabitacion = new javax.swing.JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbGananciasHabitacion.setModel(new javax.swing.table.DefaultTableModel(
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
        tbGananciasHabitacion.setPreferredSize(new java.awt.Dimension(300, 300));
        tbGananciasHabitacion.getTableHeader().setResizingAllowed(false);
        tbGananciasHabitacion.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tbGananciasHabitacion);

        jPanel5.add(jScrollPane2);
        jScrollPane2.setBounds(490, 380, 500, 330);

        materialButton3.setBackground(new java.awt.Color(0, 153, 51));
        materialButton3.setForeground(new java.awt.Color(255, 255, 255));
        materialButton3.setText("Exportar A Excel");
        materialButton3.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        materialButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materialButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(materialButton3);
        materialButton3.setBounds(490, 720, 160, 40);

        materialButton4.setBackground(new java.awt.Color(204, 0, 0));
        materialButton4.setForeground(new java.awt.Color(255, 255, 255));
        materialButton4.setText("Exportar A PDF");
        materialButton4.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        materialButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materialButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(materialButton4);
        materialButton4.setBounds(660, 720, 160, 40);

        tbCobrosUsuarios = new javax.swing.JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbCobrosUsuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        tbCobrosUsuarios.getTableHeader().setResizingAllowed(false);
        tbCobrosUsuarios.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tbCobrosUsuarios);

        jPanel5.add(jScrollPane3);
        jScrollPane3.setBounds(10, 820, 980, 350);

        txtBuscarUsuario.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtBuscarUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarUsuarioKeyReleased(evt);
            }
        });
        jPanel5.add(txtBuscarUsuario);
        txtBuscarUsuario.setBounds(90, 790, 270, 20);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("Buscar:");
        jPanel5.add(jLabel28);
        jLabel28.setBounds(20, 790, 50, 20);

        btnCobroUsuarioExcel.setBackground(new java.awt.Color(0, 153, 51));
        btnCobroUsuarioExcel.setForeground(new java.awt.Color(255, 255, 255));
        btnCobroUsuarioExcel.setText("Exportar A Excel");
        btnCobroUsuarioExcel.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btnCobroUsuarioExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobroUsuarioExcelActionPerformed(evt);
            }
        });
        jPanel5.add(btnCobroUsuarioExcel);
        btnCobroUsuarioExcel.setBounds(10, 1180, 160, 40);

        btnCobroUsuarioPDF.setBackground(new java.awt.Color(204, 0, 0));
        btnCobroUsuarioPDF.setForeground(new java.awt.Color(255, 255, 255));
        btnCobroUsuarioPDF.setText("Exportar A PDF");
        btnCobroUsuarioPDF.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btnCobroUsuarioPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobroUsuarioPDFActionPerformed(evt);
            }
        });
        jPanel5.add(btnCobroUsuarioPDF);
        btnCobroUsuarioPDF.setBounds(180, 1180, 160, 40);

        ScrollReportes.setViewportView(jPanel5);

        jTab_Reportes.addTab("Reportes", ScrollReportes);

        jp_central.add(jTab_Reportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1070, 660));

        jPanel2.setBackground(new java.awt.Color(84, 110, 122));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("VISTA GENERAL DE REPORTES");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Inicio > Reportes > Reportes/Estadisticas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 368, Short.MAX_VALUE)
                .addComponent(jLabel29)
                .addGap(116, 116, 116))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jp_central.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 50));

        lb_Id.setForeground(new java.awt.Color(84, 110, 122));
        jp_central.add(lb_Id, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 30, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp_central, javax.swing.GroupLayout.PREFERRED_SIZE, 1065, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp_central, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    //Evento que obtiene los cambios de fecha dentro del JCalendar
    private void cInicialGananciasFechasPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cInicialGananciasFechasPropertyChange
        //Si la bandera es true entra y ejecuta el método (Si no se pone la condición ejecuta el método 3 veces sin tiempo a obtener respuesta)
        if (flag == true) {
            gananciasFechas();
        }
    }//GEN-LAST:event_cInicialGananciasFechasPropertyChange
    //Evento que obtiene los cambios de fecha dentro del JCalendar
    private void cFinalGananciasFechasPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cFinalGananciasFechasPropertyChange
        //Si la bandera es true entra y ejecuta el método (Si no se pone la condición ejecuta el método 3 veces sin tiempo a obtener respuesta)
        if (flag == true) {
            gananciasFechas();
        }
    }//GEN-LAST:event_cFinalGananciasFechasPropertyChange

    private void materialButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materialButton2ActionPerformed
        //Se ejecuta el método de exportar a Excel pasando la tabla
        reportes.exportExcel(tbGananciasFechas);
    }//GEN-LAST:event_materialButton2ActionPerformed

    private void materialButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materialButton1ActionPerformed
        //Se genera una variable de mapeo
        HashMap parametros = new HashMap();
        //Se agregan los parametros a mapea con las keys que estan declaradas en los JasperReport
        parametros.put("FechaInicial", formato.format(cInicialGananciasFechas.getDate().getTime()));
        parametros.put("FechaFinal", formato.format(cFinalGananciasFechas.getDate().getTime()));
        //Se ejecuta el método que el reporte en JasperReport pasando los parametros
        reportes.reporteGenerar("ReporteHabitacionFechaGanancia", parametros);
    }//GEN-LAST:event_materialButton1ActionPerformed

    private void cbHabitacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbHabitacionItemStateChanged
        //Se compara el evento de seleccion con el evento de ser seleccionado si son iguales entra en el if, se ejecuta el siguiente método
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            gananciasHabitacion();
        }
    }//GEN-LAST:event_cbHabitacionItemStateChanged

    private void materialButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materialButton3ActionPerformed
        //Se ejecuta el método de exportar a Excel pasando la tabla
        reportes.exportExcel(tbGananciasHabitacion);
    }//GEN-LAST:event_materialButton3ActionPerformed

    private void materialButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materialButton4ActionPerformed
        //Se declara una variable para mapear
        HashMap parametros = new HashMap();
        //Si el elemento seleccionado es igual a la cadena "Todas las Habitaciones" se prosigue
        if (cbHabitacion.getSelectedItem().toString().equals("Todas las habitaciones")) {
            //Se pasa el parametro con nada y la llave concerniente declarada previamente en el JasperReport
            parametros.put("habitacion", "");
        } else {//En caso contrario
            //Se pasa el parametro y la llave concerniente declarada previamente en el JasperReport
            parametros.put("habitacion", cbHabitacion.getSelectedItem().toString());
        }
        //Se ejecita el método para generar el reporte en JasperReport pasando el nombre del ArchivoJasper y los parametros a buscar
        reportes.reporteGenerar("ReporteGananciasTotalesHabitaciones", parametros);
    }//GEN-LAST:event_materialButton4ActionPerformed

    private void txtBuscarUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarUsuarioKeyReleased
        //Cuando una tecla es precionada en el JTextfield este captura el evento y ejecuta el siguiente método
        cobrosUsuarios();
    }//GEN-LAST:event_txtBuscarUsuarioKeyReleased

    private void btnCobroUsuarioExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobroUsuarioExcelActionPerformed
        //Se ejecuta el método de exportar a Excel pasando la tabla
        reportes.exportExcel(tbCobrosUsuarios);
    }//GEN-LAST:event_btnCobroUsuarioExcelActionPerformed

    private void btnCobroUsuarioPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobroUsuarioPDFActionPerformed
        //Se declara una variable de mapeo
        HashMap parametros = new HashMap();
        //Se pasan los parametros a buscar con las keys concernientes declaradas en el JasperReport
        parametros.put("usuario", txtBuscarUsuario.getText());
        //Se ejecuta el método pasando el nombre del JasperReport y los parametros
        reportes.reporteGenerar("ReporteCobrosUsuario", parametros);
    }//GEN-LAST:event_btnCobroUsuarioPDFActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanel1;
    private javax.swing.JPanel JPanel3;
    private javax.swing.JPanel JPanel4;
    private javax.swing.JPanel JPanel5;
    private javax.swing.JPanel JPanel6;
    private javax.swing.JPanel JPanel7;
    private javax.swing.JPanel PorcentajeGanancias;
    private javax.swing.JPanel PorcentajeReservaciones;
    private javax.swing.JScrollPane ScrollEstadisticas;
    private javax.swing.JScrollPane ScrollReportes;
    private principal.MaterialButton btnCobroUsuarioExcel;
    private principal.MaterialButton btnCobroUsuarioPDF;
    private com.toedter.calendar.JDateChooser cFinalGananciasFechas;
    private com.toedter.calendar.JDateChooser cInicialGananciasFechas;
    private javax.swing.JComboBox<String> cbHabitacion;
    private javax.swing.JPanel contenedorGananciasFechasHabitacion;
    private javax.swing.JPanel contenedorGananciasHabitacion;
    private javax.swing.JPanel contenedorGananciasTotales;
    private javax.swing.JPanel contenedorIndiceFacturacion;
    private javax.swing.JPanel contenedorIndiceReservaciones;
    private javax.swing.JPanel contenedorPopularidad;
    private javax.swing.JPanel indiceFacturacion1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTab_Reportes;
    private javax.swing.JPanel jp_central;
    private javax.swing.JLabel lbConFacturacion;
    private javax.swing.JLabel lbGananciaHoy;
    private javax.swing.JLabel lbGanancias;
    private javax.swing.JLabel lbGananciasFechas;
    private javax.swing.JLabel lbGananciasHabitacion;
    private javax.swing.JLabel lbPorcentajeGanancias;
    private javax.swing.JLabel lbPorcentajeReservaciones;
    private javax.swing.JLabel lbReservacionesHoy;
    private javax.swing.JLabel lbSinFacturacion;
    private javax.swing.JLabel lbTotalIndiceFacturacion;
    private javax.swing.JLabel lbTotalReservaciones;
    private javax.swing.JLabel lbTotalReservacionesFechas;
    private javax.swing.JLabel lbTotalReservacionesGrafica;
    private javax.swing.JLabel lb_Id;
    private principal.MaterialButton materialButton1;
    private principal.MaterialButton materialButton2;
    private principal.MaterialButton materialButton3;
    private principal.MaterialButton materialButton4;
    private javax.swing.JTable tbCobrosUsuarios;
    private javax.swing.JTable tbGananciasFechas;
    private javax.swing.JTable tbGananciasHabitacion;
    private javax.swing.JTextField txtBuscarUsuario;
    // End of variables declaration//GEN-END:variables
}
