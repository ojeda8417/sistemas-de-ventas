package Clases;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MiRender extends DefaultTableCellRenderer {

    @Override

    public Component getTableCellRendererComponent(JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        double cant = Double.parseDouble(table.getValueAt(row, 6).toString());

        if (cant <= 20) {
            this.setOpaque(true);
            this.setBackground(Color.YELLOW);
            this.setForeground(Color.RED);

            if (cant >= 10) {
                this.setOpaque(true);
                this.setBackground(Color.WHITE);
                this.setForeground(Color.BLACK);
            }
        } else {
            this.setOpaque(false);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
        }

        return this;
    }

}
