package Utilerias;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
/*esta clase se utliza en la vista de reservaciones para establecer 
el color de la ultima culona en su tabla, dependiendo de si su estado de cobro esta pendiente o ua fue realizado 
*/
public class ColorearFilaxEstado extends DefaultTableCellRenderer {
//variable que guarda el numero de la columna sobre la que se desea aplicar el cambio y que recibe su valor del contructor de esta
//clase
    private final int columna_patron;
//constructor que guarda el numero de la columna a aplicar los cambios de color
    public ColorearFilaxEstado(int Colpatron) {
        this.columna_patron = Colpatron;
    }
//sobre escritura del componente de DefaultTableCellRenderer
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col) {
        Font font = new Font("Courier", Font.BOLD, 16);
//accion que itera entre el valor que tenga cada fila en funcion de la columna
        switch (table.getValueAt(row, columna_patron).toString()) {
            //casos posibles dependiente del estado de cobro
            case "Pendiente":              
                setBackground(Color.ORANGE);
                setForeground(Color.WHITE);
                setFont(font);
                break;
            case "Cobrado":
                setBackground(Color.RED);
                setForeground(Color.WHITE);
                setFont(font);
                break;
            default:
                break;
        }
        super.getTableCellRendererComponent(table, value, Selected, hasFocus, row, col);
        return this;
    }

}
