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
 * @author RAUL
 */
public class CellRenderVentasCompras extends DefaultTableCellRenderer {
    

 public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //establecemos el fondo blanco o vacío
        setBackground(null);
        //COnstructor de la clase DefaultTableCellRenderer
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
 
        //Establecemos las filas que queremos cambiar el color. == 0 para pares y != 0 para impares
        boolean oddRow = (row % 2 == 0);
 
        //Creamos un color para las filas. El 200, 200, 200 en RGB es un color gris
        Color c = new Color(200, 200, 200);
 
        //Si las filas son pares, se cambia el color a gris
        if (oddRow) {
            setBackground(c);
        }
        return this;
    }
}
