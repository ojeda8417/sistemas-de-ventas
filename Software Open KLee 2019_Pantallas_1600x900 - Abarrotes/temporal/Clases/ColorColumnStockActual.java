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
public class ColorColumnStockActual extends DefaultTableCellRenderer {

       

    //SIRVE PARA PINTAR TODA LA COLUMNA
    
    Color bajo = new Color(204,0,0);//rojo
    Color normal = new Color(138,224,132);//verde
    Color medio = new Color(255,255,51);//amarillo
    
    double cc=0;
    
    @Override
    public Component getTableCellRendererComponent ( 
            JTable table, Object value, boolean selected, 
            boolean focused, int row, int column )
    {        
        //color al seleccionar las filas
        table.setSelectionBackground(Color.BLACK);
        table.setSelectionForeground(Color.YELLOW);
        
        //se obtiene el valor de la columna 3
        String col3 = table.getValueAt(row, 3).toString();
        
        //se obtiene el valor de las columnas 3 y 3
        Double col4 =  Double.valueOf(table.getValueAt(row,3).toString() );
        Double col5 =  Double.valueOf(table.getValueAt(row,3).toString() );

        //se realiza la comparacion para la columna 3
        if(col3.equals("1.00") ||  col3.equals("2.00"))
        {setBackground(bajo);
        }        
                
        else if(col3.equals("3.00") ||  col3.equals("4.00"))
        {setBackground(bajo);
        }
        
        else if(col3.equals("5.00"))
        {setBackground(bajo);
        }  
        
        
        else if(col3.equals("6.00") ||  col3.equals("7.00"))
        {setBackground(medio);
        }
        else if(col3.equals("8.00") ||  col3.equals("9.00"))
        {setBackground(medio);
        }
        
        else if(col3.equals("10.00"))
        {setBackground(normal);
        }
        
        
        else{
            setBackground(Color.WHITE);
        }

        //se obtiene la celdas
        Component c = super.getTableCellRendererComponent(table, value, selected, focused, row, column);         
        
        //se realiza la comparacion para las columnas 3 
        if( col4!=col5 && column==3 )
        {   
            c.setBackground(normal);
        }  
        return this;
    }

}
