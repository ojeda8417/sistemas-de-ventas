/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author
 */
public class ColumnCellStockActual extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
        setEnabled(column <= 2.0 || table.isEnabled());

        setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        setHorizontalAlignment(2);

        if(isNumber(String.valueOf(table.getValueAt(row, column)))){
            setBackground(Color.GREEN); 
            setHorizontalAlignment(2);
        }      
        
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);         
        return this;
 }
    
    
    
 private boolean isNumber(String cadena){
         try {
             Double.parseDouble(cadena.replace(",", ""));
         } catch (NumberFormatException nfe){
             String newCadena = cadena.replace(".", "").replace(',', '.');
             try{
                 Double.parseDouble(newCadena);
             } catch (NumberFormatException nfe2){
                 return false;
             }
        }
         return true;
    }

}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   /* public Component getTableCellRendererComponent(JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {

        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (value instanceof Double) {
            double valor = (double) value;
            if (column == 3) {

                if (valor < 2.0) {
                    cell.setBackground(Color.RED);
                    cell.setForeground(Color.WHITE);
                }
            }
        }
        if (value instanceof Double) {
            Double valor = (Double) value;
            cell.setBackground(Color.BLUE);
            cell.setForeground(Color.BLACK);
            }
    return cell ;
}
}*/
