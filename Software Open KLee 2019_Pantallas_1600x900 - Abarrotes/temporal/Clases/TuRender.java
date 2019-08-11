/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author 
 */
public class TuRender implements TableCellRenderer {



    // Este metodo indica como debe de pintarse el elemento en la fila row, en la columna column
    // que esta en la tabla table y tiene el color dado por el objeto.
    @Override
    public Component getTableCellRendererComponent(JTable table, Object color, boolean isSelected, boolean hasFocus, int row, int column) {

    // como el boton y el calendario son componentes esto se vale.
    return (JComponent) table.getValueAt(row, column);
}
}