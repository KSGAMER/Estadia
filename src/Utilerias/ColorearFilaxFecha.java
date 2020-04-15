package Utilerias;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class ColorearFilaxFecha extends DefaultTableCellRenderer {

    private final int columna_patron;
    public String fechaActual;

    public ColorearFilaxFecha(int Colpatron, String fecha) {
        this.columna_patron = Colpatron;
        this.fechaActual = fecha;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col) {
        Font font = new Font("Courier", Font.BOLD, 16);
        if (table.getValueAt(row, columna_patron).toString().equals(fechaActual)) {
            setBackground(Color.ORANGE);
            setForeground(Color.WHITE);
            setFont(font);
        }

        super.getTableCellRendererComponent(table, value, Selected, hasFocus, row, col);
        return this;
    }

}
