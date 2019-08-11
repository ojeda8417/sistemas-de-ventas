/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author
 */
public class DTable extends JTable {

     @Override
     public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {

     Component component = super.prepareRenderer(renderer, rowIndex, columnIndex);
     component.setBackground(Color.WHITE);
     component.setForeground(Color.BLACK);
         
     if ((Double.class.equals(this.getColumnClass(columnIndex))) && (getValueAt(rowIndex, columnIndex) !=null)){
          
     double val = Double.parseDouble(getValueAt(rowIndex, columnIndex).toString());
     if (val<=2.0) 
     {
     component.setBackground(Color.GREEN);
     component.setForeground(Color.RED);
                    
     } else if(val>=2.0) {
                
     component.setBackground(Color.GREEN);
     component.setForeground(Color.RED);
     }
            
     } 
        
        
     return component;

     }
     }
     