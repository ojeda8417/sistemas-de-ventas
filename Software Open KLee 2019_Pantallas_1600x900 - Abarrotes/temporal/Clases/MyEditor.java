/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import static GUInterfaces.GUIMenuPrincipal.lblimagenventas;
import static GUInterfaces.GUIMenuPrincipal.lbltotal;
import static GUInterfaces.GUIMenuPrincipal.tabla_ventas;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author 
 */
public class MyEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

    Boolean currentValue;
    JButton button;
    protected static final String EDIT = "edit";
    private JTable jTable1;

    public MyEditor(JTable jTable1) {
        button = new JButton();
        button.setActionCommand(EDIT);
        button.addActionListener(this);
        button.setBorderPainted(false);
        this.jTable1 = jTable1;
    }

    
    
    public void actionPerformed(ActionEvent e) {
        // mymodel t = (mymodel) jTable1.getModel();
        // t.addNewRecord();
//        fireEditingStopped();
        
        DefaultTableModel modelo = (DefaultTableModel) tabla_ventas.getModel();
        int fila = tabla_ventas.getSelectedRow();
        if (fila >= 0) {
            modelo.removeRow(fila); 
            lbltotal.setText(" Productos en la venta actual =====" + tabla_ventas.getRowCount() + "=====");
        } else {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila");
            lblimagenventas.setVisible(false);
        }
                 
    }

    //Implement the one CellEditor method that AbstractCellEditor doesn't.
    public Object getCellEditorValue() {
        return currentValue;
    }

    //Implement the one method defined by TableCellEditor.
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        // Va a mostrar el botón solo en la última fila.
        // de otra forma muestra un espacio en blanco.
        if (row == table.getModel().getRowCount() - 1) {
            currentValue = (Boolean) value;
            return button;
        }
        return new JLabel();
    }
}