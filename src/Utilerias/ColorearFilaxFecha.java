package Utilerias;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
/*esta clase se utliza en la vista de reservaciones para establecer 
el color de la ultima culona en su tabla, dependiendo de si su fecha de salida es  igual a la actual 
*/
public class ColorearFilaxFecha extends DefaultTableCellRenderer {
//variable que guarda el numero de la columna sobre la que se desea aplicar el cambio y que recibe su valor del contructor de esta
//clase
    private final int columna_patron;
/* variable necesaria para hacer la compracion entre la fecha actual del sistema y la fecha final de reservacion   
*/ 
public String fechaActual;
//constructor que guarda el numero de la columna a aplicar los cambios de color y la fecha a comprara
    public ColorearFilaxFecha(int Colpatron, String fecha) {
        this.columna_patron = Colpatron;
        this.fechaActual = fecha;
    }
//sobre escritura del componente de DefaultTableCellRenderer
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col) {
        Font font = new Font("Courier", Font.BOLD, 16);
        //este if comprara que el dato de fecha de la columna indicada y la fecha actual del sistema, sean iguales 
        //y cambia el color 
        if (table.getValueAt(row, columna_patron).toString().equals(fechaActual)) {
            setBackground(Color.ORANGE);
            setForeground(Color.WHITE);
            setFont(font);
        }

        super.getTableCellRendererComponent(table, value, Selected, hasFocus, row, col);
        return this;
    }

}
