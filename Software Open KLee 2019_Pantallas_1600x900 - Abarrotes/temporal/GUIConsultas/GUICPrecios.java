/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIConsultas;

import Clases.ColorColumnVentas;
import Clases.Conexion;
import Clases.conectar;
import GUInterfaces.GUIGranel;
import GUInterfaces.GUIMenuPrincipal;
import static GUInterfaces.GUIMenuPrincipal.lblimagenventas;
import static GUInterfaces.GUIMenuPrincipal.tabla_ventas;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
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
public class GUICPrecios extends javax.swing.JFrame {

    DefaultTableModel model;
    int contador;
//    conectar cc = new conectar();
//    Connection cn = cc.conexion();

    public GUICPrecios() {
        initComponents();
        setLocationRelativeTo(null);
        NomProd();
        txtbuscarp.requestFocus();
        Image ico = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Recursos/Precios.png"));
        this.setIconImage(ico);
        lblcantidad.setText("Productos en lista actual " + tabpreciosprod.getRowCount());
        
        checkDescripcion.setSelected(true);
        if (checkDescripcion.isSelected() == true) {
            lblcantidad.setText("Productos en lista actual " + tabpreciosprod.getRowCount());
            txtbuscarp.requestFocus();
            txtbuscarp.setText("");
            Productos(txtbuscarp.getText());
        }
    }

    void Productos(String valor) {
        String mostrar = "SELECT * FROM tabla_productos WHERE CONCAT(codbarras,producto) LIKE '%" + valor + "%'";
        String[] titulos = {"C.BARRAS", "PRODUCTO", "P.VENTA", "%", "UNIDAD","IMG"};
        String[] Registros = new String[6];
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
                Registros[5] = rs.getString("imagen");
                model.addRow(Registros); 
            }
            tabpreciosprod.setModel(model);
            lblcantidad.setText("Productos en lista actual " + tabpreciosprod.getRowCount());
            tabpreciosprod.setAutoResizeMode(tabpreciosprod.AUTO_RESIZE_OFF);
            tabpreciosprod.setRowHeight(20);
            tabpreciosprod.getColumnModel().getColumn(0).setPreferredWidth(110);
            tabpreciosprod.getColumnModel().getColumn(1).setPreferredWidth(280);
            tabpreciosprod.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabpreciosprod.getColumnModel().getColumn(3).setPreferredWidth(80);
            tabpreciosprod.getColumnModel().getColumn(4).setPreferredWidth(80);
            tabpreciosprod.getColumnModel().getColumn(5).setPreferredWidth(100);
        } catch (SQLException ex) {
            Logger.getLogger(GUICPrecios.class.getName()).log(Level.SEVERE, null, ex);
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
            iva = subtotal * 0.14;
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

    public void NomProd() {
        TextAutoCompleter textAutoC = new TextAutoCompleter(txtbuscarp);
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = con.createStatement();
            ResultSet rs = sent.executeQuery("SELECT producto FROM tabla_productos");
            while (rs.next()) {

                textAutoC.addItem(rs.getString("producto"));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        checkDescripcion = new javax.swing.JRadioButton();
        checkCodigo = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabpreciosprod = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        txtbuscarp = new javax.swing.JTextField();
        lblimag = new javax.swing.JLabel();
        buttonTask1 = new org.edisoncor.gui.button.ButtonTask();
        lblcantidad = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("LISTA DE PRECIOS");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CONSULTA DE PRECIOS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Buscar por", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        grupo.add(checkDescripcion);
        checkDescripcion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        checkDescripcion.setText("Descripción");
        checkDescripcion.setOpaque(false);
        checkDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkDescripcionActionPerformed(evt);
            }
        });
        jPanel3.add(checkDescripcion);

        grupo.add(checkCodigo);
        checkCodigo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        checkCodigo.setText("Código");
        checkCodigo.setOpaque(false);
        checkCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCodigoActionPerformed(evt);
            }
        });
        jPanel3.add(checkCodigo);

        tabpreciosprod.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabpreciosprod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabpreciosprodMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabpreciosprodMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tabpreciosprod);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Buscar por descripción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        txtbuscarp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarpKeyReleased(evt);
            }
        });
        jPanel4.add(txtbuscarp);

        lblimag.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblimag.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonTask1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Precios.png"))); // NOI18N
        buttonTask1.setText("Lista de precios");
        buttonTask1.setDescription("Control de precios");

        lblcantidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblcantidad.setForeground(new java.awt.Color(0, 51, 204));
        lblcantidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblcantidad.setText("jLabel2");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Regresar.png"))); // NOI18N
        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                    .addComponent(lblcantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblimag, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonTask1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblimag, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(buttonTask1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabpreciosprodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabpreciosprodMouseClicked

        int filas = tabpreciosprod.getSelectedRow();
        try {
            String inicio = tabpreciosprod.getValueAt(filas, 0).toString();
            String Ruta;
            Ruta = tabpreciosprod.getValueAt(filas, 5).toString();
            ImageIcon img = new ImageIcon(Ruta);
            //alto //ancho
            lblimag.setIcon(new ImageIcon(img.getImage().getScaledInstance(145, 150, Image.SCALE_DEFAULT)));
            

        } catch (java.lang.Exception e) {
            System.out.println("No hay imagen del producto");
        }

        if (evt.getClickCount() == 1) {

            try {

                DefaultTableModel tabladet = (DefaultTableModel) GUIMenuPrincipal.tabla_ventas.getModel();
                String[] dato = new String[7];

                int fila = tabpreciosprod.getSelectedRow();

                String codins = tabpreciosprod.getValueAt(fila, 0).toString();
                String desins = tabpreciosprod.getValueAt(fila, 1).toString();
                String preins = tabpreciosprod.getValueAt(fila, 2).toString();
                String descuento = tabpreciosprod.getValueAt(fila, 3).toString();
                String unidad = tabpreciosprod.getValueAt(fila, 4).toString();

                int c = 0;
                int j = 0;
                
                /***********/
                
                ResultSet rs = null;

                if ((unidad.equals("GRANEL")) || (unidad.equals("GRANEL"))) {
                    GUIGranel gran = new GUIGranel();
                    gran.setVisible(true);

                    int fil = tabpreciosprod.getSelectedRow();
                    String codbarras = tabpreciosprod.getValueAt(fil, 0).toString();
                    String nomproducto = tabpreciosprod.getValueAt(fil, 1).toString();
                    String precioactual = tabpreciosprod.getValueAt(fil, 2).toString();
                    String desct = tabpreciosprod.getValueAt(fil, 3).toString();

                    GUIGranel.lblcodbarras.setText(codbarras);
                    GUIGranel.lblnomproductos.setText(nomproducto);
                    GUIGranel.lblprecioreal.setText(precioactual);
                    GUIGranel.lbldescuentopar.setText(desct);
                    GUIGranel.txtpesoKG.requestFocus();

                    String Imagen = tabpreciosprod.getValueAt(fila, 5).toString();
                    ImageIcon img = new ImageIcon(Imagen);
                    GUIMenuPrincipal.lblimagenventas.setIcon(new ImageIcon(img.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT)));
                    lblimagenventas.setVisible(true);
                

                } else {
                    
                
                /**************************/
                
                String Imagen = tabpreciosprod.getValueAt(fila, 5).toString();
                ImageIcon img = new ImageIcon(Imagen);
                GUIMenuPrincipal.lblimagenventas.setIcon(new ImageIcon(img.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT)));
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
        
    }//GEN-LAST:event_tabpreciosprodMouseClicked

    private void txtbuscarpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarpKeyReleased
        Productos(txtbuscarp.getText());
    }//GEN-LAST:event_txtbuscarpKeyReleased

    private void checkCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCodigoActionPerformed
        if (checkCodigo.isSelected() == true) {
            lblcantidad.setText("Productos en lista actual " + tabpreciosprod.getRowCount());
            /*txtbuscarp.setBackground(Color.BLACK);
            txtbuscarp.setForeground(Color.WHITE);*/
            txtbuscarp.requestFocus();
            txtbuscarp.setText("");
            Productos(txtbuscarp.getText());
        }
    }//GEN-LAST:event_checkCodigoActionPerformed

    private void checkDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkDescripcionActionPerformed
        if (checkDescripcion.isSelected() == true) {
            lblcantidad.setText("Productos en lista actual " + tabpreciosprod.getRowCount());
           /*txtbuscarp.setBackground(Color.BLACK);
            txtbuscarp.setForeground(Color.WHITE);*/
            txtbuscarp.requestFocus();
            txtbuscarp.setText("");
            Productos(txtbuscarp.getText());
        }
    }//GEN-LAST:event_checkDescripcionActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tabpreciosprodMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabpreciosprodMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tabpreciosprodMouseEntered

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
            java.util.logging.Logger.getLogger(GUICPrecios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUICPrecios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUICPrecios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUICPrecios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUICPrecios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonTask buttonTask1;
    private javax.swing.JRadioButton checkCodigo;
    private javax.swing.JRadioButton checkDescripcion;
    private javax.swing.ButtonGroup grupo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblcantidad;
    private javax.swing.JLabel lblimag;
    private javax.swing.JTable tabpreciosprod;
    public static javax.swing.JTextField txtbuscarp;
    // End of variables declaration//GEN-END:variables
}
