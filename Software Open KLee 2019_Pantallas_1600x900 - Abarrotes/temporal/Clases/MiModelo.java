/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAUL
 */
public class MiModelo extends DefaultTableModel{

   /**
    * Este metodo indica que clase esta en que celda. Es importantisimo.
    */
   @Override
   public Class getColumnClass(int columnIndex)
   {
        if( columnIndex == 0)
        {
            return JButton.class;
        }
        else
        {
            // si no tienes el jar del JDateChooser solo regresa el JButton.class
            return JDateChooser.class;
        }
   }


   @Override
   public int getColumnCount()
   {
        // la tabla solo tiene 2 columnas
        return 7;
   }

   @Override
   public int getRowCount()
   {
        // la tabla solo tiene una fila
        return 1;
   }




   @Override
   public Object getValueAt(int row, int column)
   {
        if( column == 0 )
        {
            // en la primera columna el elemento es un boton
            return new JButton("hola");
        }
        // en la otra columna el elemento es un calendario (si no esta el jar solo hay que poner el return del boton sin ningun if).
        return new JDateChooser();
   }


}
