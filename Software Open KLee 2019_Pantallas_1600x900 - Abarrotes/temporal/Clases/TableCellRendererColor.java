/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Netfex
 */
public class TableCellRendererColor extends DefaultTableCellRenderer {
    
    private Component archivos;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        archivos =  super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        

        //COLOR DE LA LETRA EN LA TABLA        
        archivos.setForeground(Color.BLACK); 
        
        //CONDICION DE LOS COLORES
        if (row % 2 == 0){
             setBackground(Color.WHITE);
             setForeground(Color.BLACK);
            
            
        }else{
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
        }
     
        //C0LOR CUANDO SE SELECCIONA UNA FILA
        if (isSelected){
            archivos.setBackground(Color.white);
            
        }
        
        return archivos;
        
    }
}
           
 
