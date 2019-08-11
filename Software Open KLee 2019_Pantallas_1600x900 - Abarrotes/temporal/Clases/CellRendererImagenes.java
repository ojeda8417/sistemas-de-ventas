/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author
 */
public class CellRendererImagenes extends DefaultTableCellRenderer {

    private String tipo="text";
    private Font courier = new Font( "Courier New",Font.PLAIN ,12 );
    private Font normal = new Font( "Arial",Font.PLAIN ,12 );
    private Font bold = new Font( "Arial",Font.BOLD ,12 );
    private JLabel label = new JLabel();
    
    private ImageIcon ventas = new ImageIcon(getClass().getResource("/Recursos/ShoppingCar.png"));
    private ImageIcon ticket = new ImageIcon(getClass().getResource("/Recursos/Ticket.png"));
    private ImageIcon compra = new ImageIcon(getClass().getResource("/Recursos/ShoppingComp.png"));
    private ImageIcon factura = new ImageIcon(getClass().getResource("/Recursos/FacturaE.png"));
    private ImageIcon pedidos = new ImageIcon(getClass().getResource("/Recursos/Pedido.png"));
    private ImageIcon devolucion = new ImageIcon(getClass().getResource("/Recursos/Revert.png"));
   
    
    public CellRendererImagenes( String tipo)
    {
        this.tipo = tipo;
    }
    
    
    @Override
    public Component getTableCellRendererComponent ( JTable table, Object value, boolean selected, boolean focused, int row, int column )
    {   
        if (selected) {                
            this.setBackground( new Color( 50, 153 , 254) );                        
        }
        else
        {
            this.setBackground(Color.white);
        }
        
        if( tipo.equals("num"))
        {
            this.setHorizontalAlignment( JLabel.LEFT );
            this.setText( (String) value );
            this.setForeground( (selected)?new Color(255,255,255):new Color(0,0,0) );            
            this.setFont(courier);            
            return this;
        }
                
        if( tipo.equals("text"))
        {
            this.setHorizontalAlignment( JLabel.LEFT );
            this.setText( (String) value );
            this.setForeground( (selected)?new Color(255,255,255):new Color(0,0,0) );            
            this.setFont(normal);            
            return this;
        }
        
        if( tipo.equals("text center"))
        {
            this.setHorizontalAlignment( JLabel.LEFT );
            this.setText( (String) value );
            this.setForeground( (selected)?new Color(255,255,255):new Color(0,0,0) );            
            this.setFont(normal);            
            return this;
        }
                
        if( tipo.equals("num"))
        {           
            this.setHorizontalAlignment( JLabel.LEFT );
            this.setText( (String) value );            
            this.setForeground( (selected)?new Color(255,255,255):new Color(32,117,32) );            
            this.setFont(bold);            
            return this;   
        }        
        
        
        this.setHorizontalAlignment(JLabel.CENTER);
        
        if( tipo.equals("icon"))
        {            
            if( String.valueOf(value).equals("Venta") )
            {
                label.setIcon(ventas);
            }
            if( String.valueOf(value).equals("Ventas") )
            {
                label.setIcon(ventas);
            }
            if( String.valueOf(value).equals("Factura") )
            {
                label.setIcon(factura);
            }
            if( String.valueOf(value).equals("Ticket") )
            {
                label.setIcon(ticket);
            }
            if( String.valueOf(value).equals("Pedidos") )
            {
                label.setIcon(pedidos);
            }
            if( String.valueOf(value).equals("Pedido") )
            {
                label.setIcon(pedidos);
            }
            if( String.valueOf(value).equals("Orden de compra") )
            {
                label.setIcon(compra);
            }
            
            if( String.valueOf(value).equals("Devolución") )
            {
                label.setIcon(devolucion);
            }
            return label;
        }
        
        return this;
    }
}
