/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIConsultas;

import Clases.ColorColumnVentas;
import Clases.Conexion;
import Clases.RenderStockGranel;
import Clases.conectar;
import GUInterfaces.GUIGranel;
import GUInterfaces.GUIMenuPrincipal;
import static GUInterfaces.GUIMenuPrincipal.lblimagenventas;
import static GUInterfaces.GUIMenuPrincipal.tabla_ventas;
import java.awt.Image;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author
 */
public class GUICProductosGranel extends javax.swing.JFrame {

    DefaultTableModel model;
    int contador;
//    conectar cc = new conectar();
//    Connection cn = cc.conexion();

    public GUICProductosGranel() {
        initComponents();
        setLocationRelativeTo(null);
        txtbuscargranel.requestFocus();
        tab_granel.setDefaultRenderer(Object.class, new RenderStockGranel());
    }

    void Productos(String valor) {
        String mostrar = "SELECT * FROM tabla_productos WHERE CONCAT(producto, unidad, codbarras) LIKE '%" + valor + "%'";
        String[] titulos = {"C.BARRAS", "PRODUCTO", "P.VENTA", "%", "UNIDAD", "STOCK", "IMG"};
        String[] Registros = new String[7];
        model = new DefaultTableModel(null, titulos);

        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(mostrar);
            while (rs.next()) {
                Registros[0] = rs.getString("codbarras");
                Registros[1] = rs.getString("producto");
                Registros[2] = rs.getString("pre_venta");
                Registros[3] = rs.getString("descuento");
                Registros[4] = rs.getString("unidad");
                Registros[5] = rs.getString("stock");
                Registros[6] = rs.getString("imagen");
                model.addRow(Registros);
                lblhayados.setText("Productos encontrados " + tab_granel.getRowCount());
            }
            tab_granel.setModel(model);
            lblhayados.setText("Productos encontrados " + tab_granel.getRowCount());
            tab_granel.setAutoResizeMode(tab_granel.AUTO_RESIZE_OFF);
            tab_granel.setRowHeight(20);
            tab_granel.getColumnModel().getColumn(0).setPreferredWidth(120);
            tab_granel.getColumnModel().getColumn(1).setPreferredWidth(300);
            tab_granel.getColumnModel().getColumn(2).setPreferredWidth(80);
            tab_granel.getColumnModel().getColumn(3).setPreferredWidth(60);
            tab_granel.getColumnModel().getColumn(4).setPreferredWidth(70);
            tab_granel.getColumnModel().getColumn(5).setPreferredWidth(60);
            tab_granel.getColumnModel().getColumn(6).setPreferredWidth(300);

        } catch (SQLException ex) {
            Logger.getLogger(GUICCProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void CalcularVenta() {

        String pre;
        String can;
        String desc = "";
        double desct = 0;
        double iva = 0;
        double total = 0;
        double subtotal = 0;
        double precio = 0.0;
        double cantidad;
        double impParcial = 0.0;
        double descuentos = 0.0;
        double totparcial = 0.0;

        for (int i = 0; i < GUIMenuPrincipal.tabla_ventas.getRowCount(); i++) {
            pre = GUIMenuPrincipal.tabla_ventas.getValueAt(i, 2).toString();
            can = GUIMenuPrincipal.tabla_ventas.getValueAt(i, 3).toString();

            precio = Double.parseDouble(pre);
            cantidad = Double.parseDouble(can);

            if (GUIMenuPrincipal.tabla_ventas.getValueAt(i, 5) == null) {
                totparcial = precio * cantidad;

            } else {

                desc = GUIMenuPrincipal.tabla_ventas.getValueAt(i, 5).toString();
                descuentos = Double.parseDouble(desc);
                impParcial = precio * cantidad;
                desct = (precio * descuentos) * cantidad;
                totparcial = impParcial - desct;
            }

            subtotal = subtotal + totparcial;
            iva = subtotal * 0.12;
            total = subtotal + iva;
            GUIMenuPrincipal.tabla_ventas.setValueAt(Math.rint(totparcial * 100) / 100, i, 6);
        }

        GUIMenuPrincipal.txtsubtotalvent.setText(Double.toString(subtotal));
        GUIMenuPrincipal.txtiva.setText("" + Math.rint(iva * 100) / 100);
        GUIMenuPrincipal.txtTotales.setText("" + Math.rint(total * 100) / 100);

    }

    String comparar(String cod) {
        String cant = "";
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tabla_productos WHERE codbarras='" + cod + "'");
            while (rs.next()) {
                cant = rs.getString(7);

            }

        } catch (SQLException ex) {
            Logger.getLogger(GUICCProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cant;
    }

    public void RedondearSubtotal() {
        double valor = Double.parseDouble(GUIMenuPrincipal.txtsubtotalvent.getText());
        String val = valor + "";
        BigDecimal big = new BigDecimal(val);
        big = big.setScale(2, RoundingMode.HALF_UP);
        GUIMenuPrincipal.txtsubtotalvent.setText("" + big);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tab_granel = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblhayados = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtbuscargranel = new rojeru_san.RSMTextFull();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PRODUCTOS A GRANEL");
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/Buscar.png")).getImage());
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        tab_granel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tab_granel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_granelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tab_granel);

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("BÚSQUEDA DE PRODUCTOS A GRANEL");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Regresar.png"))); // NOI18N
        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtbuscargranel.setOpaque(false);
        txtbuscargranel.setPlaceholder("Ingrese nombre del producto...");
        txtbuscargranel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscargranelKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblhayados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtbuscargranel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtbuscargranel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblhayados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tab_granelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_granelMouseClicked

        txtbuscargranel.setText("Ingrese nombre del producto...");
        if (evt.getClickCount() == 1) {

            try {

                DefaultTableModel tabladet = (DefaultTableModel) GUIMenuPrincipal.tabla_ventas.getModel();
                String[] dato = new String[7];

                int fila = tab_granel.getSelectedRow();

                String codins = tab_granel.getValueAt(fila, 0).toString();
                String desins = tab_granel.getValueAt(fila, 1).toString();
                String preins = tab_granel.getValueAt(fila, 2).toString();
                String descuento = tab_granel.getValueAt(fila, 3).toString();
                String unidad = tab_granel.getValueAt(fila, 4).toString();

                int c = 0;
                int j = 0;

                ResultSet rs = null;

                if ((unidad.equals("GRANEL")) || (unidad.equals("GRANEL"))) {
                    GUIGranel gran = new GUIGranel();
                    gran.setVisible(true);

                    int filas = tab_granel.getSelectedRow();
                    String codbarras = tab_granel.getValueAt(filas, 0).toString();
                    String nomproducto = tab_granel.getValueAt(filas, 1).toString();
                    String precioactual = tab_granel.getValueAt(filas, 2).toString();
                    String desct = tab_granel.getValueAt(filas, 3).toString();

                    GUIGranel.lblcodbarras.setText(codbarras);
                    GUIGranel.lblnomproductos.setText(nomproducto);
                    GUIGranel.lblprecioreal.setText(precioactual);
                    GUIGranel.lbldescuentopar.setText(desct);
                    GUIGranel.txtpesoKG.requestFocus();

                    String Imagen = tab_granel.getValueAt(fila, 6).toString();
                    ImageIcon img = new ImageIcon(Imagen);
                    GUIMenuPrincipal.lblimagenventas.setIcon(new ImageIcon(img.getImage().getScaledInstance(110, 110, Image.SCALE_DEFAULT)));
                    lblimagenventas.setVisible(true);

                } else {

                    String Imagen = tab_granel.getValueAt(fila, 6).toString();
                    ImageIcon img = new ImageIcon(Imagen);
                    GUIMenuPrincipal.lblimagenventas.setIcon(new ImageIcon(img.getImage().getScaledInstance(110, 110, Image.SCALE_DEFAULT)));
                    lblimagenventas.setVisible(true);

                    String cant = JOptionPane.showInputDialog(null, "Introduzca la cantidad solicitada", "Solicitud", JOptionPane.PLAIN_MESSAGE);
                    if ((cant.equals("")) || (cant.equals("0"))) {
                        JOptionPane.showMessageDialog(this, "Debe ingresar algun valor mayor que 0", "Open K´LEE", JOptionPane.PLAIN_MESSAGE);
                    } else {

                        double cantidad = Double.parseDouble(cant);
                        double compra = Double.parseDouble(comparar(codins));
                        if (cantidad > compra) {
                            JOptionPane.showMessageDialog(this, "El producto " + desins + ", se encuentra en stock");
                        } else {
                            for (int i = 0; i < GUIMenuPrincipal.tabla_ventas.getRowCount(); i++) {
                                Object com = GUIMenuPrincipal.tabla_ventas.getValueAt(i, 0);
                                if (codins.equals(com)) {
                                    j = i;
                                    GUIMenuPrincipal.tabla_ventas.setValueAt(cant, i, 3);
                                    c = c + 1;
                                }
                            }

                            if (c == 0) {

                                dato[0] = codins;
                                dato[1] = desins;
                                dato[2] = preins;
                                dato[3] = cant;
                                dato[4] = "0.0";
                                dato[5] = descuento;

                                tabladet.addRow(dato);
                                tabla_ventas.setRowHeight(30);
                                GUIMenuPrincipal.tabla_ventas.setModel(tabladet);
                                
                                ColorColumnVentas ft = new ColorColumnVentas();        
                                tabla_ventas.setDefaultRenderer (Object.class, ft );
                                
                                CalcularVenta();
                                RedondearSubtotal();
                                GUIMenuPrincipal.lbltotal.setText(" Productos en la venta actual =====" + tabla_ventas.getRowCount() + "=====");
                            }
                        }
                    }
                }

            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_tab_granelMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        txtbuscargranel.setText("Ingrese nombre del producto...");
    }//GEN-LAST:event_jPanel1MouseClicked

    private void txtbuscargranelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscargranelKeyReleased
        Productos(txtbuscargranel.getText());
    }//GEN-LAST:event_txtbuscargranelKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUICProductosGranel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUICProductosGranel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUICProductosGranel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUICProductosGranel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUICProductosGranel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblhayados;
    private javax.swing.JTable tab_granel;
    private rojeru_san.RSMTextFull txtbuscargranel;
    // End of variables declaration//GEN-END:variables
}
