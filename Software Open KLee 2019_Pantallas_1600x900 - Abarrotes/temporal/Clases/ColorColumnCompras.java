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
 * @author 
 */
public class ColorColumnCompras extends DefaultTableCellRenderer{

//    Color celda_error = new Color(185,63,51);
//    Color vigente = new Color(138,224,132);
//    Color vencido = new Color(145,175,238);
    
    Color bajo = new Color(0,153,255);//rojo 204,0,0
    Color normal = new Color(138,224,132);//verde
    Color medio = new Color(255,255,51);//amarillo
    Color stock = new Color(255,153,153);//
    
    
    double c=0;
    
    @Override
    public Component getTableCellRendererComponent ( 
            JTable table, Object value, boolean selected, 
            boolean focused, int row, int column )
    {        
        table.setSelectionBackground(Color.YELLOW);
        table.setSelectionForeground(Color.BLACK);
        table.setSelectionForeground(Color.BLACK);
        
        //se obtiene el valor de la columna 4
        String col4 = table.getValueAt(row, 4).toString();//cant de comp
        String col2 = table.getValueAt(row, 2).toString();//stock
        
        //se obtiene el valor de las columnas 5 y 6
        Double col5 =  Double.valueOf(table.getValueAt(row,5).toString());//cant s
        Double col6 =  Double.valueOf(table.getValueAt(row,6).toString());//cant e
        Double col7 =  Double.valueOf(table.getValueAt(row,7).toString());//parcial

        if(col4.equals("") )
        {            
            setBackground(normal);      
        }
        else if(col4.equals("") )
        {
            setBackground(normal); 
        }
               
        else{
            setBackground(Color.WHITE);
        }
        
        
        if(col2.equals("") )
        {            
            setBackground(normal);      
        }
        else if(col2.equals("") )
        {
            setBackground(normal); 
        }
        
        else{
            setBackground(Color.WHITE);
        }
                
        

        Component c = super.getTableCellRendererComponent(table, value, selected, focused, row, column);         
        
        if( col5!=col6 && column==4 )
        {   
            c.setBackground(normal);
        }  
        if( col5!=col6 && column==5 )
        {   
            c.setBackground(bajo);
        }  
         if( col5!=col6 && column==2 )
        {   
            c.setBackground(stock);
        }  

        return this;
    }

}