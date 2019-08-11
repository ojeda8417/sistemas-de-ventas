/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author RAUL
 */
public class CellRendererCobros extends DefaultTableCellRenderer{
    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {
        
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        double saldo = Double.parseDouble(table.getValueAt(row, 5).toString());
        
        if (saldo >= 0.0) {
            this.setOpaque(true);
            
            this.setBackground(Color.WHITE);
            this.setForeground(Color.RED);
            
            
        if (saldo <= 0.0) {
            this.setOpaque(true);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLUE);
        }
        }
        else {
            this.setOpaque(false);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLUE);
        }

        return this;
    }}