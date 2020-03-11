package vistas;

import controladores.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.ImageIcon;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleInsets;

/**
 *
 * @author KSGAMER
 */
public class Pn_NuevoReportes extends javax.swing.JPanel {

    private ControladorReportes reportes = new ControladorReportes();
    private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    boolean flag = false;

    /**
     * Creates new form pnlHome
     */
    public Pn_NuevoReportes() {
        initComponents();
        mouseOnClickGananciasFechas();
        rangoFechas();
        estadisticas();
        configScroll();
    }

    public void configScroll() {
        ScrollEstadisticas.getVerticalScrollBar().setUnitIncrement(20);
        ScrollReportes.getVerticalScrollBar().setUnitIncrement(20);
    }

    public void rangoFechas() {
        Calendar fecha = Calendar.getInstance();
        cFinalGananciasFechas.setDate(fecha.getTime());
        fecha.add(Calendar.DATE, -14);
        cInicialGananciasFechas.setDate(fecha.getTime());
    }

    public void popularHabitacion() {
        DefaultPieDataset grafica = new DefaultPieDataset();
        int total = 0;
        for (int i = 0; i < reportes.habitacionPopular().getRowCount(); i++) {
            grafica.setValue(reportes.habitacionPopular().getValueAt(i, 0).toString(), Integer.parseInt(reportes.habitacionPopular().getValueAt(i, 1).toString()));
            total = total + Integer.parseInt(reportes.habitacionPopular().getValueAt(i, 1).toString());
        }
        lbTotalReservacionesGrafica.setText(String.valueOf(total));
        lbTotalReservaciones.setText(String.valueOf(total));
        JFreeChart chart = ChartFactory.createRingChart("", grafica, false, true, false);
        RingPlot pie = (RingPlot) chart.getPlot();
        pie.setOutlineVisible(false);
        pie.setShadowPaint(null);
        pie.setLabelShadowPaint(null);
        pie.setSectionDepth(0.30);
        pie.setSectionOutlinesVisible(false);
        pie.setSeparatorsVisible(false);
        chart.getPlot().setBackgroundPaint(null);
        chart.getPlot().setOutlinePaint(null);
        ChartPanel panel = new ChartPanel(chart);
        panel.setOpaque(false);
        popularidad.add(panel);
        panel.setBounds(0, 60, 320, 225);
    }

    public void montoTotal() {
        DefaultCategoryDataset grafica = new DefaultCategoryDataset();
        double total = 0;
        for (int i = 0; i < reportes.ganancias().getRowCount(); i++) {
            grafica.setValue(Integer.parseInt(reportes.ganancias().getValueAt(i, 1).toString()), reportes.ganancias().getValueAt(i, 0).toString(), reportes.ganancias().getValueAt(i, 0).toString());
            total = total + Double.parseDouble(reportes.ganancias().getValueAt(i, 1).toString());
        }
        lbGanancias.setText("$" + total + "0");
        JFreeChart chart = ChartFactory.createBarChart("", "Fechas", "Ganancias Brutas", grafica, PlotOrientation.VERTICAL, true, true, false);
        StandardChartTheme theme = (StandardChartTheme) org.jfree.chart.StandardChartTheme.createJFreeTheme();
        theme.setRangeGridlinePaint(Color.decode("#C0C0C0"));
        theme.setAxisOffset(new RectangleInsets(0, 0, 0, 0));
        theme.setBarPainter(new StandardBarPainter());
        theme.apply(chart);
        chart.getCategoryPlot().setOutlineVisible(false);
        chart.getCategoryPlot().getRangeAxis().setAxisLineVisible(false);
        chart.getCategoryPlot().getRangeAxis().setTickMarksVisible(false);
        chart.getCategoryPlot().setRangeGridlineStroke(new BasicStroke());
        chart.getCategoryPlot().getRangeAxis().setTickLabelPaint(Color.decode("#666666"));
        chart.getCategoryPlot().getDomainAxis().setTickLabelPaint(Color.decode("#666666"));
        chart.setTextAntiAlias(true);
        chart.setAntiAlias(true);
        chart.getCategoryPlot().getRenderer().setSeriesPaint(0, Color.decode("#4572a7"));
        BarRenderer rend = (BarRenderer) chart.getCategoryPlot().getRenderer();
        rend.setShadowVisible(true);
        rend.setShadowXOffset(2);
        rend.setShadowYOffset(0);
        rend.setShadowPaint(Color.decode("#C0C0C0"));
        rend.setMaximumBarWidth(0.1);
        chart.getPlot().setBackgroundPaint(null);
        ChartPanel panel = new ChartPanel(chart);
        panel.setOpaque(false);
        montoTotal.add(panel);
        panel.setBounds(0, 60, 500, 325);
    }

    public void indiceFacturacion() {
        DefaultPieDataset grafica = new DefaultPieDataset();
        int total = 0;
        for (int i = 0; i < reportes.facturacionIndice().getRowCount(); i++) {
            grafica.setValue(reportes.facturacionIndice().getValueAt(i, 0).toString(), Integer.parseInt(reportes.facturacionIndice().getValueAt(i, 1).toString()));
            total = total + Integer.parseInt(reportes.facturacionIndice().getValueAt(i, 1).toString());
        }
        lbTotalIndiceFacturacion.setText(String.valueOf(total));
        lbConFacturacion.setText(reportes.facturacionIndice().getValueAt(0, 1).toString());
        lbSinFacturacion.setText(reportes.facturacionIndice().getValueAt(1, 1).toString());
        JFreeChart chart = ChartFactory.createRingChart("", grafica, true, true, false);
        RingPlot pie = (RingPlot) chart.getPlot();
        pie.setOutlineVisible(false);
        pie.setShadowPaint(null);
        pie.setLabelShadowPaint(null);
        pie.setSectionDepth(0.30);
        pie.setSectionOutlinesVisible(false);
        pie.setSeparatorsVisible(false);
        chart.getPlot().setBackgroundPaint(null);
        chart.getPlot().setOutlinePaint(null);
        ChartPanel panel = new ChartPanel(chart);
        panel.setOpaque(false);
        indiceFacturacion.add(panel);
        panel.setBounds(20, 80, 300, 196);
    }

    public void indiceReservacion() {
        DefaultCategoryDataset grafica = new DefaultCategoryDataset();
        int total = 0;
        for (int i = 0; i < reportes.ReservacionesIndice().getRowCount(); i++) {
            grafica.setValue(Integer.parseInt(reportes.ReservacionesIndice().getValueAt(i, 1).toString()), reportes.ReservacionesIndice().getValueAt(i, 0).toString(), reportes.ReservacionesIndice().getValueAt(i, 0).toString());
            total = total + Integer.parseInt(reportes.ReservacionesIndice().getValueAt(i, 1).toString());
        }
        lbTotalReservacionesFechas.setText(String.valueOf(total));
        JFreeChart chart = ChartFactory.createBarChart("", "Fecha", "Total", grafica, PlotOrientation.VERTICAL, true, true, false);
        StandardChartTheme theme = (StandardChartTheme) org.jfree.chart.StandardChartTheme.createJFreeTheme();
        theme.setRangeGridlinePaint(Color.decode("#C0C0C0"));
        theme.setAxisOffset(new RectangleInsets(0, 0, 0, 0));
        theme.setBarPainter(new StandardBarPainter());
        theme.apply(chart);
        chart.getCategoryPlot().setOutlineVisible(false);
        chart.getCategoryPlot().getRangeAxis().setAxisLineVisible(false);
        chart.getCategoryPlot().getRangeAxis().setTickMarksVisible(false);
        chart.getCategoryPlot().setRangeGridlineStroke(new BasicStroke());
        chart.getCategoryPlot().getRangeAxis().setTickLabelPaint(Color.decode("#666666"));
        chart.getCategoryPlot().getDomainAxis().setTickLabelPaint(Color.decode("#666666"));
        chart.setTextAntiAlias(true);
        chart.setAntiAlias(true);
        chart.getCategoryPlot().getRenderer().setSeriesPaint(0, Color.decode("#4572a7"));
        BarRenderer rend = (BarRenderer) chart.getCategoryPlot().getRenderer();
        rend.setShadowVisible(true);
        rend.setShadowXOffset(2);
        rend.setShadowYOffset(0);
        rend.setShadowPaint(Color.decode("#C0C0C0"));
        rend.setMaximumBarWidth(0.1);
        chart.getPlot().setBackgroundPaint(Color.WHITE);
        ChartPanel panel = new ChartPanel(chart);
        panel.setOpaque(false);
        IndiceReservaciones.add(panel);
        panel.setBounds(0, 60, 460, 325);
    }

    public void gananciasFechas() {
        tbGananciasFechas.setModel(reportes.fechasGanancias(formato.format(cInicialGananciasFechas.getDate().getTime()), formato.format(cFinalGananciasFechas.getDate().getTime())));
        DefaultCategoryDataset grafica = new DefaultCategoryDataset();
        double total = 0;
        for (int i = 0; i < reportes.fechasGanancias(formato.format(cInicialGananciasFechas.getDate().getTime()), formato.format(cFinalGananciasFechas.getDate().getTime())).getRowCount(); i++) {
            grafica.setValue(Integer.parseInt(reportes.fechasGanancias(formato.format(cInicialGananciasFechas.getDate().getTime()), formato.format(cFinalGananciasFechas.getDate().getTime())).getValueAt(i, 2).toString()), reportes.fechasGanancias(formato.format(cInicialGananciasFechas.getDate().getTime()), formato.format(cFinalGananciasFechas.getDate().getTime())).getValueAt(i, 0).toString(), reportes.fechasGanancias(formato.format(cInicialGananciasFechas.getDate().getTime()), formato.format(cFinalGananciasFechas.getDate().getTime())).getValueAt(i, 1).toString());
            total = total + Integer.parseInt(reportes.fechasGanancias(formato.format(cInicialGananciasFechas.getDate().getTime()), formato.format(cFinalGananciasFechas.getDate().getTime())).getValueAt(i, 2).toString());
        }
        lbGananciasFechas.setText("$" + total + "0");
        JFreeChart chart = ChartFactory.createBarChart("", "Reservaciones", "Ganancias", grafica, PlotOrientation.VERTICAL, true, true, false);
        StandardChartTheme theme = (StandardChartTheme) org.jfree.chart.StandardChartTheme.createJFreeTheme();
        theme.setRangeGridlinePaint(Color.decode("#C0C0C0"));
        theme.setAxisOffset(new RectangleInsets(0, 0, 0, 0));
        theme.setBarPainter(new StandardBarPainter());
        theme.apply(chart);
        chart.getCategoryPlot().setOutlineVisible(false);
        chart.getCategoryPlot().getRangeAxis().setAxisLineVisible(false);
        chart.getCategoryPlot().getRangeAxis().setTickMarksVisible(false);
        chart.getCategoryPlot().setRangeGridlineStroke(new BasicStroke());
        chart.getCategoryPlot().getRangeAxis().setTickLabelPaint(Color.decode("#666666"));
        chart.getCategoryPlot().getDomainAxis().setTickLabelPaint(Color.decode("#666666"));
        chart.setTextAntiAlias(true);
        chart.setAntiAlias(true);
        chart.getCategoryPlot().getRenderer().setSeriesPaint(0, Color.decode("#4572a7"));
        BarRenderer rend = (BarRenderer) chart.getCategoryPlot().getRenderer();
        rend.setShadowVisible(true);
        rend.setShadowXOffset(2);
        rend.setShadowYOffset(0);
        rend.setShadowPaint(Color.decode("#C0C0C0"));
        rend.setMaximumBarWidth(0.1);
        chart.getPlot().setBackgroundPaint(Color.WHITE);
        ChartPanel panel = new ChartPanel(chart);
        panel.setOpaque(false);
        if(flag == true) {
            GananciasFechas.remove(7);
        }
        GananciasFechas.add(panel);
        GananciasFechas.validate();
        panel.setBounds(0, 60, 440, 250);
    }

    public void porcentajeGanancias() {
        lbGananciaHoy.setText("$" + Double.parseDouble(reportes.porcentajeGanancias().getValueAt(0, 1).toString()) + "0");
        double porcentaje = Double.parseDouble(reportes.porcentajeGanancias().getValueAt(0, 1).toString()) * 100 / Double.parseDouble(reportes.porcentajeGanancias().getValueAt(1, 1).toString());
        lbPorcentajeGanancias.setText(String.valueOf(porcentaje).substring(0, 5) + "%");
        if (porcentaje >= 100) {
            lbPorcentajeGanancias.setForeground(new Color(0, 191, 95));
            lbPorcentajeGanancias.setIcon(new ImageIcon(getClass().getResource("/Imagenes/186407-16.png")));
        } else {
            lbPorcentajeGanancias.setForeground(new Color(229, 16, 16));
            lbPorcentajeGanancias.setIcon(new ImageIcon(getClass().getResource("/Imagenes/186411-16.png")));
        }
    }

    public void porcentajeReservacion() {
        lbReservacionesHoy.setText(String.valueOf(Integer.parseInt(reportes.porcentajeReservacion().getValueAt(0, 1).toString())));
        double porcentaje = Double.parseDouble(reportes.porcentajeReservacion().getValueAt(0, 1).toString()) * 100 / Double.parseDouble(reportes.porcentajeReservacion().getValueAt(1, 1).toString());
        lbPorcentajeReservaciones.setText(String.valueOf(porcentaje).substring(0, 5) + "%");
        if (porcentaje >= 100) {
            lbPorcentajeReservaciones.setForeground(new Color(0, 191, 95));
            lbPorcentajeReservaciones.setIcon(new ImageIcon(getClass().getResource("/Imagenes/186407-16.png")));
        } else {
            lbPorcentajeReservaciones.setForeground(new Color(0, 191, 95));
            lbPorcentajeReservaciones.setIcon(new ImageIcon(getClass().getResource("/Imagenes/186407-16.png")));
        }
    }

    public void estadisticas() {
        gananciasFechas();
        porcentajeReservacion();
        porcentajeGanancias();
        indiceReservacion();
        indiceFacturacion();
        montoTotal();
        popularHabitacion();
    }

    public void mouseOnClickGananciasFechas() {
        cInicialGananciasFechas.getComponent(0).addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                flag = true;
            }
        });
        cInicialGananciasFechas.getComponent(1).addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                flag = true;
            }
        });
        cFinalGananciasFechas.getComponent(0).addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                flag = true;
            }
        });
        cFinalGananciasFechas.getComponent(1).addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
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

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        ScrollEstadisticas = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        popularidad = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbTotalReservaciones = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbTotalReservacionesGrafica = new javax.swing.JLabel();
        indiceFacturacion = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbSinFacturacion = new javax.swing.JLabel();
        lbConFacturacion = new javax.swing.JLabel();
        lbTotalIndiceFacturacion = new javax.swing.JLabel();
        montoTotal = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbGanancias = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        IndiceReservaciones = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lbTotalReservacionesFechas = new javax.swing.JLabel();
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
        GananciasFechas = new javax.swing.JPanel();
        cInicialGananciasFechas = new com.toedter.calendar.JDateChooser();
        jLabel21 = new javax.swing.JLabel();
        cFinalGananciasFechas = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lbGananciasFechas = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbGananciasFechas = new javax.swing.JTable();
        materialButton1 = new principal.MaterialButton();
        materialButton2 = new principal.MaterialButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lb_Id = new javax.swing.JLabel();

        setBackground(new java.awt.Color(84, 110, 122));
        setForeground(new java.awt.Color(255, 255, 255));
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(1086, 684));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1090, 710));

        ScrollEstadisticas.setBackground(new java.awt.Color(204, 204, 204));
        ScrollEstadisticas.setBorder(null);
        ScrollEstadisticas.setPreferredSize(new java.awt.Dimension(1090, 710));

        jPanel4.setBackground(new java.awt.Color(245, 245, 245));
        jPanel4.setMaximumSize(new java.awt.Dimension(1090, 800));
        jPanel4.setPreferredSize(new java.awt.Dimension(1090, 800));
        jPanel4.setLayout(null);

        popularidad.setBackground(new java.awt.Color(255, 255, 255));
        popularidad.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/4171938-24.png"))); // NOI18N
        jLabel3.setText("Habitaciones más Reservadas");
        popularidad.add(jLabel3);
        jLabel3.setBounds(10, 10, 300, 32);

        lbTotalReservaciones.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbTotalReservaciones.setForeground(new java.awt.Color(51, 153, 255));
        lbTotalReservaciones.setText("jLabel4");
        popularidad.add(lbTotalReservaciones);
        lbTotalReservaciones.setBounds(110, 50, 44, 15);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Reservaciones:");
        popularidad.add(jLabel5);
        jLabel5.setBounds(10, 50, 90, 15);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 153, 153));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Total");
        popularidad.add(jLabel13);
        jLabel13.setBounds(90, 140, 140, 20);

        lbTotalReservacionesGrafica.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        lbTotalReservacionesGrafica.setForeground(new java.awt.Color(102, 102, 102));
        lbTotalReservacionesGrafica.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTotalReservacionesGrafica.setText("jLabel11");
        popularidad.add(lbTotalReservacionesGrafica);
        lbTotalReservacionesGrafica.setBounds(90, 160, 140, 30);

        jPanel4.add(popularidad);
        popularidad.setBounds(10, 11, 320, 300);

        indiceFacturacion.setBackground(new java.awt.Color(255, 255, 255));
        indiceFacturacion.setPreferredSize(new java.awt.Dimension(300, 300));
        indiceFacturacion.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 153, 153));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Total");
        indiceFacturacion.add(jLabel10);
        jLabel10.setBounds(100, 140, 140, 20);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/4172166-32.png"))); // NOI18N
        jLabel7.setText(" Indice de Facturación");
        indiceFacturacion.add(jLabel7);
        jLabel7.setBounds(10, 11, 250, 32);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Sin Facturación:");
        indiceFacturacion.add(jLabel8);
        jLabel8.setBounds(10, 50, 97, 15);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Con Facturación:");
        indiceFacturacion.add(jLabel9);
        jLabel9.setBounds(10, 70, 102, 15);

        lbSinFacturacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbSinFacturacion.setForeground(new java.awt.Color(51, 153, 255));
        lbSinFacturacion.setText("jLabel10");
        indiceFacturacion.add(lbSinFacturacion);
        lbSinFacturacion.setBounds(130, 50, 52, 15);

        lbConFacturacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbConFacturacion.setForeground(new java.awt.Color(51, 153, 255));
        lbConFacturacion.setText("jLabel10");
        indiceFacturacion.add(lbConFacturacion);
        lbConFacturacion.setBounds(130, 70, 52, 15);

        lbTotalIndiceFacturacion.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbTotalIndiceFacturacion.setForeground(new java.awt.Color(102, 102, 102));
        lbTotalIndiceFacturacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTotalIndiceFacturacion.setText("jLabel11");
        indiceFacturacion.add(lbTotalIndiceFacturacion);
        lbTotalIndiceFacturacion.setBounds(100, 150, 140, 40);

        jPanel4.add(indiceFacturacion);
        indiceFacturacion.setBounds(340, 10, 340, 300);

        montoTotal.setBackground(new java.awt.Color(255, 255, 255));
        montoTotal.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/134157-32.png"))); // NOI18N
        jLabel2.setText(" Ganancias Totales");
        montoTotal.add(jLabel2);
        jLabel2.setBounds(10, 11, 210, 32);

        lbGanancias.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbGanancias.setForeground(new java.awt.Color(51, 153, 255));
        lbGanancias.setText("jLabel3");
        montoTotal.add(lbGanancias);
        lbGanancias.setBounds(50, 50, 130, 15);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Total:");
        montoTotal.add(jLabel4);
        jLabel4.setBounds(10, 50, 35, 15);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(204, 204, 204));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconfinder_star_1930255.png"))); // NOI18N
        jLabel18.setText("Ultimos 7 Días");
        jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        montoTotal.add(jLabel18);
        jLabel18.setBounds(420, 20, 100, 20);

        jPanel4.add(montoTotal);
        montoTotal.setBounds(10, 329, 540, 389);

        IndiceReservaciones.setBackground(new java.awt.Color(255, 255, 255));
        IndiceReservaciones.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/465050-32.png"))); // NOI18N
        jLabel6.setText(" Reservaciones por Fechas");
        IndiceReservaciones.add(jLabel6);
        jLabel6.setBounds(10, 11, 280, 32);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 204, 204));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconfinder_star_1930255.png"))); // NOI18N
        jLabel11.setText("Ultimos 15 Días");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        IndiceReservaciones.add(jLabel11);
        jLabel11.setBounds(330, 20, 110, 20);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Reservaciones:");
        IndiceReservaciones.add(jLabel20);
        jLabel20.setBounds(10, 50, 90, 15);

        lbTotalReservacionesFechas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbTotalReservacionesFechas.setForeground(new java.awt.Color(51, 153, 255));
        lbTotalReservacionesFechas.setText("jLabel21");
        IndiceReservaciones.add(lbTotalReservacionesFechas);
        lbTotalReservacionesFechas.setBounds(110, 50, 70, 15);

        jPanel4.add(IndiceReservaciones);
        IndiceReservaciones.setBounds(560, 330, 460, 389);

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

        jTabbedPane1.addTab("Estadísticas", ScrollEstadisticas);

        ScrollReportes.setBackground(new java.awt.Color(204, 204, 204));
        ScrollReportes.setBorder(null);
        ScrollReportes.setPreferredSize(new java.awt.Dimension(1090, 710));

        jPanel5.setBackground(new java.awt.Color(245, 245, 245));
        jPanel5.setMaximumSize(new java.awt.Dimension(1090, 800));
        jPanel5.setPreferredSize(new java.awt.Dimension(1090, 800));
        jPanel5.setLayout(null);

        GananciasFechas.setBackground(new java.awt.Color(255, 255, 255));
        GananciasFechas.setPreferredSize(new java.awt.Dimension(450, 300));
        GananciasFechas.setLayout(null);

        cInicialGananciasFechas.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cInicialGananciasFechasPropertyChange(evt);
            }
        });
        GananciasFechas.add(cInicialGananciasFechas);
        cInicialGananciasFechas.setBounds(100, 320, 120, 20);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Fecha Inicial:");
        GananciasFechas.add(jLabel21);
        jLabel21.setBounds(20, 320, 80, 20);

        cFinalGananciasFechas.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cFinalGananciasFechasPropertyChange(evt);
            }
        });
        GananciasFechas.add(cFinalGananciasFechas);
        cFinalGananciasFechas.setBounds(300, 320, 120, 20);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Fecha Final:");
        GananciasFechas.add(jLabel22);
        jLabel22.setBounds(230, 320, 70, 20);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/134157-32.png"))); // NOI18N
        jLabel23.setText(" Ganancias por Rango de Fechas");
        GananciasFechas.add(jLabel23);
        jLabel23.setBounds(10, 10, 420, 40);

        lbGananciasFechas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbGananciasFechas.setForeground(new java.awt.Color(51, 153, 255));
        lbGananciasFechas.setText("jLabel24");
        GananciasFechas.add(lbGananciasFechas);
        lbGananciasFechas.setBounds(60, 50, 70, 15);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Total:");
        GananciasFechas.add(jLabel25);
        jLabel25.setBounds(10, 50, 35, 15);

        jPanel5.add(GananciasFechas);
        GananciasFechas.setBounds(20, 10, 450, 350);

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

        ScrollReportes.setViewportView(jPanel5);

        jTabbedPane1.addTab("Reportes", ScrollReportes);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1070, 660));

        jPanel2.setBackground(new java.awt.Color(84, 110, 122));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("VISTA GENERAL DE REPORTES");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(710, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 50));

        lb_Id.setForeground(new java.awt.Color(84, 110, 122));
        jPanel1.add(lb_Id, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 30, 20));

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

    private void cInicialGananciasFechasPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cInicialGananciasFechasPropertyChange
        if (flag == true) {
            gananciasFechas();
        }
    }//GEN-LAST:event_cInicialGananciasFechasPropertyChange

    private void cFinalGananciasFechasPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cFinalGananciasFechasPropertyChange
        if (flag == true) {
            gananciasFechas();
        }
    }//GEN-LAST:event_cFinalGananciasFechasPropertyChange

    private void materialButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materialButton2ActionPerformed
        reportes.exportExcel(tbGananciasFechas);
    }//GEN-LAST:event_materialButton2ActionPerformed

    private void materialButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materialButton1ActionPerformed
        HashMap parametros = new HashMap();
        parametros.put("FechaInicial", formato.format(cInicialGananciasFechas.getDate().getTime()));
        parametros.put("FechaFinal", formato.format(cFinalGananciasFechas.getDate().getTime()));
        reportes.reporteGenerar("ReporteHabitacionFechaGanancia", parametros);
    }//GEN-LAST:event_materialButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel GananciasFechas;
    private javax.swing.JPanel IndiceReservaciones;
    private javax.swing.JPanel PorcentajeGanancias;
    private javax.swing.JPanel PorcentajeReservaciones;
    private javax.swing.JScrollPane ScrollEstadisticas;
    private javax.swing.JScrollPane ScrollReportes;
    private com.toedter.calendar.JDateChooser cFinalGananciasFechas;
    private com.toedter.calendar.JDateChooser cInicialGananciasFechas;
    private javax.swing.JPanel indiceFacturacion;
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
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbConFacturacion;
    private javax.swing.JLabel lbGananciaHoy;
    private javax.swing.JLabel lbGanancias;
    private javax.swing.JLabel lbGananciasFechas;
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
    private javax.swing.JPanel montoTotal;
    private javax.swing.JPanel popularidad;
    private javax.swing.JTable tbGananciasFechas;
    // End of variables declaration//GEN-END:variables
}
