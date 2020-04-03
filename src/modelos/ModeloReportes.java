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

//Se aplica Herencia desde la clase padre BD
public class ModeloReportes extends BD {
    //Se declaran las variables de resultado y consultas preparadas
    private ResultSet rs;
    private PreparedStatement st;

    //Método que extrae la información directamente de base de datos
    protected DefaultTableModel popularHabitacion() {
        //Se declaran las columnas de la tabla
        String[] titulos = {"Habitación", "Reservaciones"};
        //Se declara la tabla y se pasan los titulos
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Se declara un objeto que actua como la fila de la tabla con un tamaño de 2
        Object[] fila = new Object[2];
        try {
            //Se instancia la conexión a la base de datos y se pasa la consulta preparada
            this.st = conectar().prepareStatement("SELECT h.Nombre, COUNT(h.Nombre) as Reservado FROM Habitacion h INNER JOIN Reservacion r on r.IdHabitacion = h.IdHabitacion group by h.Nombre");
            //Se ejecuta el Query
            this.rs = st.executeQuery();
            //Se iteran los resultados
            while (rs.next()) {
                //Se agregan los resultados al objeto
                fila[0] = rs.getString(1);
                fila[1] = rs.getInt(2);
                //Se agrega el objeto a la tabla
                tb.addRow(fila);
            }
            //Se cierra la conexión
        } catch (SQLException ex) {
            Logger.getLogger(ModeloReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retorna la tabla
        return tb;
    }

    //Método que extrae la información directamente de base de datos
    protected DefaultTableModel gananciasPorFechas() {
        //Se declaran las columnas de la tabla
        String[] titulos = {"Total", "Habitacion", "Fechas"};
        //Se declara la tabla pasando las columnas de la tabla
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Se declara un objeto que actuara como la fila de la tabla
        Object[] fila = new Object[3];
        try {
            //Se instancia la conexión a base de datos y se pasa la consulta preparada
            this.st = conectar().prepareStatement("SELECT top 7 SUM(c.Monto) as Total, h.Nombre as Habitación, CONVERT(DATE, c.FechaCobro, 103) as FechaCobro FROM Cobro c INNER JOIN Habitacion h on h.IdHabitacion = c.IdHabitacion GROUP BY CONVERT(DATE, FechaCobro, 103), h.Nombre ORDER BY CONVERT(DATE, FechaCobro, 103) DESC");
            //Se ejecuta el Query
            this.rs = st.executeQuery();
            //Se iteran los resultados
            while (rs.next()) {
                //Se agregan los resultados al objeto
                fila[0] = rs.getDouble(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                //Se agrega el objeto a la tabla
                tb.addRow(fila);
            }
            //Se cierra la conexión
        } catch (SQLException ex) {
            Logger.getLogger(ModeloReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retorna la tabla
        return tb;
    }

    //Método que retorna la información directamente de base de datos
    protected DefaultTableModel indiceFacturacion() {
        //Se declaran las columnas de la tabla
        String[] titulos = {"Tipo", "Cantidad"};
        //Se declara la tabla pasando las columnas
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Se declara el objeto que actuara como la fila de la tabla
        Object[] fila = new Object[2];
        try {
            //Se instancia la conexión a base de datos pasando la consulta preparada
            this.st = conectar().prepareStatement("SELECT  f.Nombre, COUNT(f.Nombre) as Cantidad FROM Cobro c INNER JOIN Facturacion f on f.IdFacturacion = c.IdFacturacion GROUP BY f.Nombre");
            //Se ejecuta el Query
            this.rs = st.executeQuery();
            //Se iteran los resultados
            while (rs.next()) {
                //Se agregan los resultados al objeto
                fila[0] = rs.getString(1);
                fila[1] = rs.getInt(2);
                //Se agrega el objeto a la tabla
                tb.addRow(fila);
            }
            //Se cierra la conexión
        } catch (SQLException ex) {
            Logger.getLogger(ModeloReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retorna la tabla
        return tb;
    }

    //Método que extrae la información directamente de base de datos
    protected DefaultTableModel indiceReservaciones() {
        //Se declaran las columnas de la tabla
        String[] titulos = {"Cantidad", "Habitación", "Fecha"};
        //Se declara la tabla pasando las columnas de la tabla
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Se declara el objeto que actuara como la fila de la tabla
        Object[] fila = new Object[3];
        try {
            //Se instancia la conexión a la base de datos pasando la consulta preparada
            this.st = conectar().prepareStatement("SELECT TOP 15 COUNT(r.FechaIngreso) as Reservaciones, h.Nombre as Habitacion, CONVERT(DATE, r.FechaIngreso, 103) as Fecha FROM Reservacion r INNER JOIN Habitacion h on h.IdHabitacion = r.IdHabitacion GROUP BY CONVERT(DATE, FechaIngreso, 103), h.Nombre ORDER BY CONVERT(DATE, FechaIngreso, 103) DESC");
            //Se ejecuta el Query
            this.rs = st.executeQuery();
            //Se iteran los resultados
            while (rs.next()) {
                //Se agregan los resultados al objeto
                fila[0] = rs.getInt(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                //Se agrega el objeto a la tabla
                tb.addRow(fila);
            }
            //Se cierra la conexión
        } catch (SQLException ex) {
            Logger.getLogger(ModeloReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retorna la tabla
        return tb;
    }

    //Método que extrae la información directamente de base datos
    protected DefaultTableModel gananciasHoy() {
        //Se declaran las columnas de la tabla
        String[] titulos = {"Fecha", "Total"};
        //Se declara la tabla pasando las columnas
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Se declara un objeto que actuara como la fila de la tabla
        Object[] fila = new Object[2];
        try {
            //Se instancia la conexión a base de datos pasando la consulta preparada
            this.st = conectar().prepareStatement("SELECT top 2 CONVERT(DATE, c.FechaCobro, 103) as FechaCobro, SUM(c.Monto) as Total FROM Cobro c GROUP BY CONVERT(DATE, c.FechaCobro, 103) ORDER BY CONVERT(DATE, c.FechaCobro, 103) DESC");
            //Se ejecuta el Query
            this.rs = st.executeQuery();
            //Se iteran los resultados
            while (rs.next()) {
                //Se agregan los resultados al objeto
                fila[0] = rs.getString(1);
                fila[1] = rs.getInt(2);
                //Se agrega el objeto a la tabla
                tb.addRow(fila);
            }
            //Se cierra la conexión
        } catch (SQLException ex) {
            Logger.getLogger(ModeloReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retorna la tabla
        return tb;
    }

    //Método que extrae la información directamente de base de datos
    protected DefaultTableModel porcentajeReservaciones() {
        //Se declaran las columnas de la tabla
        String[] titulos = {"Fecha", "Total"};
        //Se declara la tabla pasando las columnas
        DefaultTableModel tb = new DefaultTableModel(null, titulos);
        //Se declara un objeto que actuara la fila de la tabla
        Object[] fila = new Object[2];
        try {
            //Se instancia la conexión a base de datos pasando la consulta preparada
            this.st = conectar().prepareStatement("SELECT top 2 CONVERT(DATE, r.FechaIngreso, 103) as Fecha, COUNT(r.FechaIngreso) as Reservacion FROM Reservacion r GROUP BY CONVERT(DATE, r.FechaIngreso, 103) ORDER BY CONVERT(DATE, r.FechaIngreso, 103) DESC");
            //Se ejecuta el Query
            this.rs = st.executeQuery();
            //Se iteran los resultados
            while (rs.next()) {
                //Se agregan los resultados al objeto
                fila[0] = rs.getString(1);
                fila[1] = rs.getInt(2);
                //Se agrega el objeto a la tabla
                tb.addRow(fila);
            }
            //Se cierra la conexión
        } catch (SQLException ex) {
            Logger.getLogger(ModeloReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retorna la tabla
        return tb;
    }

    //Método que extrae la información directamente de base de datos
    protected DefaultTableModel gananciasFechas(String fechaInicial, String fechaFinal, String modo) {
        //Si el modo es igual a Tabla prosigue (Modo solo 2 Valores {Tabla | Grafica})
        if (modo.equals("Tabla")) {
            //Se declaran las columnas de la tabla
            String[] titulos = {"Habitación", "Reservaciones", "Ganancias"};
            //Se declara la tabla pasando las columnas de la tabla
            DefaultTableModel tb = new DefaultTableModel(null, titulos);
            //Se declara un objeto que actuara como la fila de la tabla
            Object[] fila = new Object[3];
            try {
                //Se instancia la conexión a la base de datos y se pasa la consulta preparada
                this.st = conectar().prepareStatement("SELECT h.Nombre, COUNT(h.Nombre) as Reservaciones, SUM(c.Monto) as Ganancias  FROM Cobro c INNER JOIN Habitacion h on h.IdHabitacion = c.IdHabitacion WHERE CONVERT(DATE, c.FechaCobro, 103) >= CONVERT(DATE, ?, 103) and CONVERT(DATE, c.FechaCobro, 103) <= CONVERT(DATE, ?, 103) GROUP BY h.Nombre ");
                //Se pasan los parametros a la consulta
                this.st.setString(1, fechaInicial);
                this.st.setString(2, fechaFinal);
                //Se ejecuta el Query
                this.rs = st.executeQuery();
                //Se iteran los resultados
                while (rs.next()) {
                    //Se agregan los resultados al objeto
                    fila[0] = rs.getString(1);
                    fila[1] = rs.getInt(2);
                    fila[2] = "$ " + rs.getDouble(3) + "0";
                    //Se agrega el objeto a la tabla
                    tb.addRow(fila);
                }
                //Se cierra la conexión
            } catch (SQLException ex) {
                Logger.getLogger(ModeloReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Se retorna la tabla
            return tb;
        } else {
            //Se declaran las columnas de la tabla
            String[] titulos = {"Ganancias", "Reservaciones", "Habitacion"};
            //Se declara la tabla pasando las columnas
            DefaultTableModel tb = new DefaultTableModel(null, titulos);
            //Se declara un objeto que actuara como la fila de la tabla
            Object[] fila = new Object[3];
            try {
                //Se instancia la conexión a base de datos pasando la consulta preparada
                this.st = conectar().prepareStatement("SELECT SUM(c.Monto) as Ganancias, h.Nombre, COUNT(h.Nombre) as Reservaciones  FROM Cobro c INNER JOIN Habitacion h on h.IdHabitacion = c.IdHabitacion WHERE CONVERT(DATE, c.FechaCobro, 103) >= CONVERT(DATE, ?, 103) and CONVERT(DATE, c.FechaCobro, 103) <= CONVERT(DATE, ?, 103) GROUP BY h.Nombre ");
                //Se pasan los parametros
                this.st.setString(1, fechaInicial);
                this.st.setString(2, fechaFinal);
                //Se ejecuta el Query
                this.rs = st.executeQuery();
                //Se iteran los resultados
                while (rs.next()) {
                    //Se agregan los resultados al objeto
                    fila[0] = rs.getInt(1);
                    fila[1] = rs.getString(2);
                    fila[2] = rs.getString(3);
                    //Se agrega el objeto a la tabla
                    tb.addRow(fila);
                }
                //Se cierra la conexión
            } catch (SQLException ex) {
                Logger.getLogger(ModeloReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Se retorna la tabla
            return tb;
        }
    }

    //Método que extrae la información directamente de base de datos con pase de parametros
    protected DefaultTableModel gananciasHabitaciones(String habitacion, String modo) {
        //Si el modo es igual a Tabla prosigue (Modo solo 2 Valores {Tabla | Grafica})
        if (modo.equals("Tabla")) {
            //Se declara la tabla pasando las columnas
            String[] titulos = {"Habitacion", "Fecha", "Ganancias"};
            //Se declara un objeto que actuara como la fila de la tabla
            DefaultTableModel tb = new DefaultTableModel(null, titulos);
            //Se declara un objeto que actuara como la fila de la tabla
            Object[] fila = new Object[3];
            try {
                //Se instancia la conexión a base de datos pasando la consulta preparada
                this.st = conectar().prepareStatement("SELECT h.Nombre as Habitacion, CONVERT(DATE, c.FechaCobro, 103) as Fecha, SUM(Monto) as Ganancia FROM Cobro c INNER JOIN Habitacion h on h.IdHabitacion = c.IdHabitacion WHERE h.Nombre like CONCAT('%', ? ,'%') GROUP BY CONVERT(DATE, c.FechaCobro, 103), h.Nombre ORDER BY CONVERT(DATE, c.FechaCobro, 103) DESC");
                //Se pasan los parametros a la consulta
                this.st.setString(1, habitacion);
                //Se ejecuta el Query
                this.rs = st.executeQuery();
                //Se iteran los resultados
                while (rs.next()) {
                    //Se agregan los resultados al objeto
                    fila[0] = rs.getString(1);
                    fila[1] = rs.getString(2);
                    fila[2] = "$ "+rs.getDouble(3);
                    //Se agrega el objeto a la tabla
                    tb.addRow(fila);
                }
                //Se cierra la conexión
            } catch (SQLException ex) {
                Logger.getLogger(ModeloReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Se retorna la tabla
            return tb;
        } else {
            //Se declara la tabla pasando las columnas
            String[] titulos = {"Ganancias", "Habitacion", "Fecha"};
            //Se declara un objeto que actuara como la fila de la tabla
            DefaultTableModel tb = new DefaultTableModel(null, titulos);
            //Se declara un objeto que actuara como la fila de la tabla
            Object[] fila = new Object[3];
            try {
                //Se instancia la conexión a base de datos pasando la consulta preparada
                this.st = conectar().prepareStatement("SELECT SUM(Monto) as Ganancia, h.Nombre as Habitacion, CONVERT(DATE, c.FechaCobro, 103) as Fecha FROM Cobro c INNER JOIN Habitacion h on h.IdHabitacion = c.IdHabitacion WHERE h.Nombre like CONCAT('%', ? ,'%') GROUP BY CONVERT(DATE, c.FechaCobro, 103), h.Nombre ORDER BY CONVERT(DATE, c.FechaCobro, 103) DESC");
                //Se pasan los parametros a la consulta
                this.st.setString(1, habitacion);
                //Se iteran los resultados
                this.rs = st.executeQuery();
                //Se iteran los resultados
                while (rs.next()) {
                    //Se agregan los resultados al objeto
                    fila[0] = rs.getDouble(1);
                    fila[1] = rs.getString(2);
                    fila[2] = rs.getString(3);
                    //Se agrega el objeto a la tabla
                    tb.addRow(fila);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModeloReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Se retorna la tabla
            return tb;
        }
    }
    
    protected DefaultTableModel cobroUsuario(String usuario) {
        //Se declara la tabla pasando las columnas
            String[] titulos = {"Usuario", "Fecha Cobro", "Cobro", "Habitación", "Fecha Ingreso", "Fecha Salida", "Cliente"};
            //Se declara un objeto que actuara como la fila de la tabla
            DefaultTableModel tb = new DefaultTableModel(null, titulos);
            //Se declara un objeto que actuara como la fila de la tabla
            Object[] fila = new Object[7];
            try {
                //Se instancia la conexión a base de datos pasando la consulta preparada
                this.st = conectar().prepareStatement("SELECT c.Username, CONVERT(DATE, c.FechaCobro, 103) as FechaCobro, SUM(c.Monto) as Cobro, h.Nombre as Habitacion, CONVERT(DATE, c.FechaIngreso, 103) as FechaIngreso, CONVERT(DATE, c.FechaSalida, 103) as FechaSalida, c.Nombre as Cliente FROM Cobro c INNER JOIN Habitacion h on h.IdHabitacion = c.IdHabitacion WHERE c.Username like CONCAT('%', ? ,'%') GROUP BY c.Username, CONVERT(DATE, c.FechaCobro, 103), h.Nombre, CONVERT(DATE, c.FechaIngreso, 103), CONVERT(DATE, c.FechaSalida, 103), c.Nombre ORDER BY CONVERT(DATE, c.FechaCobro, 103) DESC");
                //Se pasan los parametros a la consulta
                this.st.setString(1, usuario);
                //Se iteran los resultados
                this.rs = st.executeQuery();
                //Se iteran los resultados
                while (rs.next()) {
                    //Se agregan los resultados al objeto
                    fila[0] = rs.getString(1);
                    fila[1] = rs.getString(2);
                    fila[2] = rs.getDouble(3);
                    fila[3] = rs.getString(4);
                    fila[4] = rs.getString(5);
                    fila[5] = rs.getString(6);
                    fila[6] = rs.getString(7);
                    //Se agrega el objeto a la tabla
                    tb.addRow(fila);
                }
                //Se cierra la conexión
            } catch (SQLException ex) {
                Logger.getLogger(ModeloReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Se retorna la tabla
            return tb;
    }

    //Método para generar reportes con JasperReport
    protected void generarReporte(String report) {
        try {
            //Se declara la variable con la ubicación del reporte y se castea a tipo JasperReport
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("src\\reportes\\" + report + ".jasper");
            //Se declara la variable para poder extraer la información de base de datos pasando el reporte, los parametros y la conexión a base de datos
            JasperPrint imprimir = JasperFillManager.fillReport(reporte, null, conectar());
            //Se declara la variable para previsualizar el informe pasando la variable imprimir ya cargada con los datos
            JasperViewer vista = new JasperViewer(imprimir, false);
            //Se declara un metodo para cerrar la ventana de previsualización
            vista.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            //Se llama la ventana y se muestra en pantalla
            vista.setVisible(true);
        } catch (JRException ex) {
            DesktopNotify.showDesktopMessage("ERROR", "No se ha podido generar el Reporte, intentelo nuevamente", DesktopNotify.ERROR);
        } catch (Exception e) {
            DesktopNotify.showDesktopMessage("ERROR", "No se ha podido generar el Reporte, intente con otra opción", DesktopNotify.ERROR);
        }
    }

    //Método para generar reportes con JasperReport con pase de parametros y sobrecarga de operadores
    protected void generarReporte(String report, HashMap parametros) {
        try {
            //Se declara la variable con la ubicación del reporte y se castea a tipo JasperReport
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("reportes\\" + report + ".jasper");
            //Se declara la variable para poder extraer la información de base de datos pasando el reporte, los parametros y la conexión a base de datos
            JasperPrint imprimir = JasperFillManager.fillReport(reporte, parametros, conectar());
            //Se declara la variable para previsualizar el informe pasando la variable imprimir ya cargada con los datos
            JasperViewer vista = new JasperViewer(imprimir, false);
            //Se declara un metodo para cerrar la ventana de previsualización
            vista.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            //Se llama la ventana y se muestra en pantalla
            vista.setVisible(true);
        } catch (JRException ex) {
            DesktopNotify.showDesktopMessage("ERROR", "No se ha podido generar el Reporte, intentelo nuevamente", DesktopNotify.ERROR);
        } catch (Exception e) {
            DesktopNotify.showDesktopMessage("ERROR", "No se ha podido generar el Reporte, intente con otra opción", DesktopNotify.ERROR);
        }
    }

    //Método para generar excel
    protected void excel(JTable table) {
        //Se declara una variable para abrir el explorador de archivos
        JFileChooser file = new JFileChooser();
        //Se anbre la ventana de dialogo para extraer la ruta del archivo
        file.showSaveDialog(file);
        //Se crea el archivo de Excel pasando el archivo
        File excel = file.getSelectedFile();
        //Si la variable excel es diferente de nula prosigue
        if (excel != null) {
            try {
                //Se extrae la ruta del archivo y se agrega la terminación .xls
                excel = new File(excel.toString() + ".xls");
                //Se declara la variable para crear un nuevo libro de excel pasando la variable
                WritableWorkbook book = Workbook.createWorkbook(excel);
                //Se crea la hoja de Excel y se nombra
                WritableSheet sheet = book.createSheet("Hoja 1", 0);
                //Se recorre la tabla hasta el total de columnas
                for (int i = 0; i < table.getColumnCount(); i++) {
                    //Se crean las celdas con la posición en la que se encuentra cada dato
                    Label columna = new Label(i, 0, table.getColumnName(i));
                    //Se agregan las celdas a la hoja
                    sheet.addCell(columna);
                }
                //Se recorre la tabla hasta el total de filas
                for (int i = 0; i < table.getRowCount(); i++) {
                    //Se recoore la tabla hasta el total de columnas
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        //Se crean las celdas con la posición en la que se encuentra cada dato
                        Label row = new Label(j, i + 1, table.getValueAt(i, j).toString());
                        //Se agregan las celdas a la hoja
                        sheet.addCell(row);
                    }
                }
                //Se crea el archivo de Excel
                book.write();
                //Se cierra la trama de datos
                book.close();
                DesktopNotify.showDesktopMessage("Exito", "Se ha creado el archivo correctamente", DesktopNotify.SUCCESS);
                //Método que abre el archivo despues de crearlo
                abrirArchivo(excel.toString());
            } catch (IOException | WriteException ex) {
                Logger.getLogger(ControladorReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Método para abrir un archivo
    public void abrirArchivo(String file) {
        try {
            //Se extrae la ubicación del archivo
            File path = new File(file);
            //Se abre el archivo
            Desktop.getDesktop().open(path);
        } catch (IOException ex) {
            Logger.getLogger(ControladorReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
