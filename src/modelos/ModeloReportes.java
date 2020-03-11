/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import controladores.ControladorReportes;
import ds.desktop.notify.DesktopNotify;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author KSGAMER
 */
public class ModeloReportes extends BD{
    private ResultSet rs;
    private PreparedStatement st;
    
    protected DefaultTableModel popularHabitacion() {
        String [] titulos = {"Habitación", "Reservaciones"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[2];
        try {
            this.st = conectar().prepareStatement("SELECT h.Nombre, COUNT(h.Nombre) as Reservado FROM Habitacion h INNER JOIN Reservacion r on r.IdHabitacion = h.IdHabitacion group by h.Nombre");
            this.rs = st.executeQuery();
            while(rs.next()) {
                fila[0] = rs.getString(1);
                fila[1] = rs.getInt(2);
                tb.addRow(fila);
            }
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
    
    protected DefaultTableModel gananciasPorFechas() {
        String [] titulos = {"Fecha", "Total"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[2];
        try {
            this.st = conectar().prepareStatement("SELECT top 7 CAST(CONCAT(SUBSTRING(FechaCobro,4,2),'/',SUBSTRING(FechaCobro,0,3),'/',SUBSTRING(FechaCobro,7,4)) as date) as FechaCobro, SUM(c.Monto) as Total FROM Cobro c group by CAST(CONCAT(SUBSTRING(FechaCobro,4,2),'/',SUBSTRING(FechaCobro,0,3),'/',SUBSTRING(FechaCobro,7,4)) as date) order by CAST(CONCAT(SUBSTRING(FechaCobro,4,2),'/',SUBSTRING(FechaCobro,0,3),'/',SUBSTRING(FechaCobro,7,4)) as date) desc");
            this.rs = st.executeQuery();
            while(rs.next()) {
                fila[0] = rs.getString(1);
                fila[1] = rs.getInt(2);
                tb.addRow(fila);
            }
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
    
    protected DefaultTableModel indiceFacturacion() {
        String [] titulos = {"Tipo", "Cantidad"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[2];
        try {
            this.st = conectar().prepareStatement("SELECT  f.Nombre, COUNT(f.Nombre) as Cantidad FROM Cobro c INNER JOIN Facturacion f on f.IdFacturacion = c.IdFacturacion GROUP BY f.Nombre");
            this.rs = st.executeQuery();
            while(rs.next()) {
                fila[0] = rs.getString(1);
                fila[1] = rs.getInt(2);
                tb.addRow(fila);
            }
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
    
    protected DefaultTableModel indiceReservaciones() {
        String [] titulos = {"Fecha", "Cantidad"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[2];
        try {
            this.st = conectar().prepareStatement("SELECT TOP 5 CAST(CONCAT(SUBSTRING(r.FechaIngreso, 4, 2) ,'/',SUBSTRING(r.FechaIngreso, 0, 3),'/',SUBSTRING(r.FechaIngreso, 7, 4)) AS date) as Fecha, COUNT(r.FechaIngreso) as Reservaciones FROM Reservacion r GROUP BY CAST(CONCAT(SUBSTRING(r.FechaIngreso, 4, 2) ,'/',SUBSTRING(r.FechaIngreso, 0, 3),'/',SUBSTRING(r.FechaIngreso, 7, 4)) AS date) ORDER BY CAST(CONCAT(SUBSTRING(r.FechaIngreso, 4, 2) ,'/',SUBSTRING(r.FechaIngreso, 0, 3),'/',SUBSTRING(r.FechaIngreso, 7, 4)) AS date) DESC");
            this.rs = st.executeQuery();
            while(rs.next()) {
                fila[0] = rs.getString(1);
                fila[1] = rs.getInt(2);
                tb.addRow(fila);
            }
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
    
    protected DefaultTableModel gananciasHoy() {
        String [] titulos = {"Fecha", "Total"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[2];
        try {
            this.st = conectar().prepareStatement("SELECT top 2 CAST(CONCAT(SUBSTRING(FechaCobro,4,2),'/',SUBSTRING(FechaCobro,0,3),'/',SUBSTRING(FechaCobro,7,4)) as date) as FechaCobro, SUM(c.Monto) as Total FROM Cobro c group by CAST(CONCAT(SUBSTRING(FechaCobro,4,2),'/',SUBSTRING(FechaCobro,0,3),'/',SUBSTRING(FechaCobro,7,4)) as date) order by CAST(CONCAT(SUBSTRING(FechaCobro,4,2),'/',SUBSTRING(FechaCobro,0,3),'/',SUBSTRING(FechaCobro,7,4)) as date) desc");
            this.rs = st.executeQuery();
            while(rs.next()) {
                fila[0] = rs.getString(1);
                fila[1] = rs.getInt(2);
                tb.addRow(fila);
            }
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
    }
    
   protected DefaultTableModel porcentajeReservaciones() {
       String [] titulos = {"Fecha", "Total"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[2];
        try {
            this.st = conectar().prepareStatement("SELECT TOP 2 CAST(CONCAT(SUBSTRING(r.FechaIngreso, 4, 2) ,'/',SUBSTRING(r.FechaIngreso, 0, 3),'/',SUBSTRING(r.FechaIngreso, 7, 4)) AS date) as Fecha, COUNT(r.FechaIngreso) as Reservaciones FROM Reservacion r GROUP BY CAST(CONCAT(SUBSTRING(r.FechaIngreso, 4, 2) ,'/',SUBSTRING(r.FechaIngreso, 0, 3),'/',SUBSTRING(r.FechaIngreso, 7, 4)) AS date) ORDER BY CAST(CONCAT(SUBSTRING(r.FechaIngreso, 4, 2) ,'/',SUBSTRING(r.FechaIngreso, 0, 3),'/',SUBSTRING(r.FechaIngreso, 7, 4)) AS date) DESC");
            this.rs = st.executeQuery();
            while(rs.next()) {
                fila[0] = rs.getString(1);
                fila[1] = rs.getInt(2);
                tb.addRow(fila);
            }
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
   }
   
   protected DefaultTableModel gananciasFechas(String fechaInicial, String fechaFinal) {
       String [] titulos = {"Habitación", "Reservaciones", "Ganancias"};
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        Object[] fila = new Object[3];
        try {
            this.st = conectar().prepareStatement("SELECT h.Nombre, COUNT(h.Nombre) as Reservaciones, SUM(c.Monto) as Ganancias FROM Cobro c INNER JOIN Reservacion r on r.IdReservacion = c.IdReservacion INNER JOIN Habitacion h on h.IdHabitacion = r.IdHabitacion WHERE CONVERT(DATE, c.FechaCobro, 103) >= CONVERT(DATE, ?, 103) and CONVERT(DATE, c.FechaCobro, 103) <= CONVERT(DATE, ?, 103) GROUP BY h.Nombre ");
            this.st.setString(1, fechaInicial);
            this.st.setString(2, fechaFinal);
            this.rs = st.executeQuery();
            while(rs.next()) {
                fila[0] = rs.getString(1);
                fila[1] = rs.getInt(2);
                fila[2] = rs.getInt(3);
                tb.addRow(fila);
            }
            conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tb;
   }
    
    protected void generarReporte(String report) {
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("src\\reportes\\"+report+".jasper");
            JasperPrint imprimir = JasperFillManager.fillReport(reporte, null, conectar());
            JasperViewer vista = new JasperViewer(imprimir, false);
            vista.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            vista.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ModeloReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void generarReporte(String report, HashMap parametros) {
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("src\\reportes\\"+report+".jasper");
            JasperPrint imprimir = JasperFillManager.fillReport(reporte, parametros, conectar());
            JasperViewer vista = new JasperViewer(imprimir, false);
            vista.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            vista.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ModeloReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void excel(JTable table) {
        JFileChooser file = new JFileChooser();
        file.showSaveDialog(file);
        File excel = file.getSelectedFile();
        if(excel != null) {
            try {
                excel = new File(excel.toString()+".xls");
                WritableWorkbook book = Workbook.createWorkbook(excel);
                WritableSheet sheet = book.createSheet("Hoja 1", 0);
                for (int i = 0; i < table.getColumnCount(); i++) {
                    Label columna = new Label(i, 0, table.getColumnName(i));
                    sheet.addCell(columna);
                }
                for (int i = 0; i < table.getRowCount(); i++) {
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        Label row = new Label(j, i+1, table.getValueAt(i, j).toString());
                        sheet.addCell(row);
                    }
                }
                book.write();
                book.close();
                DesktopNotify.showDesktopMessage("Exito", "Se ha creado el archivo correctamente", DesktopNotify.SUCCESS);
                abrirArchivo(excel.toString());
            } catch (IOException ex) {
                Logger.getLogger(ControladorReportes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WriteException ex) {
                Logger.getLogger(ControladorReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void abrirArchivo(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException ex) {
            Logger.getLogger(ControladorReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
