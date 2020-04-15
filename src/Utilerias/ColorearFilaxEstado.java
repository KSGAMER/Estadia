package Utilerias;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class ColorearFilaxEstado extends DefaultTableCellRenderer {

    private final int columna_patron;

    public ColorearFilaxEstado(int Colpatron) {
        this.columna_patron = Colpatron;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col) {
        Font font = new Font("Courier", Font.BOLD, 16);
        switch (table.getValueAt(row, columna_patron).toString()) {
            
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
