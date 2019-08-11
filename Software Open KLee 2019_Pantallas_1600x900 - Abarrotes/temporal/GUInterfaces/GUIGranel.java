/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUInterfaces;
 
import Clases.Conexion;
import Clases.conectar;
import static GUInterfaces.GUIMenuPrincipal.tabla_ventas;
import static GUInterfaces.GUIMenuPrincipal.txtsubtotalvent;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author
 */
public class GUIGranel extends javax.swing.JFrame {

    DefaultTableModel model;
    int contador;
//    conectar cc = new conectar();
//    Connection cn = cc.conexion();
     
    Icon mensaje;
    
    public GUIGranel() {
        initComponents();
        
        mensaje= new ImageIcon("src/Recursos/DecimalesG.png");
        
        setLocationRelativeTo(null);
        jLabel3.setVisible(false);
        lbldescuentopar.setVisible(false);
        lblcodbarras.setVisible(false);
    }

    String comparar(String unidad) {
        String cant = "";
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tabla_productos WHERE unidad='" + unidad + "'");
            while (rs.next()) {
                cant = rs.getString(4);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GUIGranel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cant;
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

    public void RedondearSubtotal() {
        double valor = Double.parseDouble(GUIMenuPrincipal.txtsubtotalvent.getText());
        String val = valor + "";
        BigDecimal big = new BigDecimal(val);
        big = big.setScale(2, RoundingMode.HALF_UP);
        txtsubtotalvent.setText("" + big);
    }

    void CalcularKG() {
        try {
            double peso1LIB = 2.2046;

            double peso = Double.parseDouble(this.txtpesoKG.getText());
            double precio = Double.parseDouble(this.lblprecioreal.getText());

            double total = (precio * peso);

            txtprecioKG.setText("" + total);

        } catch (Exception ex) {
            System.out.println("Error al calcular el precio del KG " + ex);
        }
    }

    public void RedondearValorKG() {
        double valKar = Double.parseDouble(GUIGranel.txtprecioKG.getText());
        String val = valKar + "";
        BigDecimal big = new BigDecimal(val);
        big = big.setScale(2, RoundingMode.HALF_UP);
        System.out.println("Número : " + big);
        txtprecioKG.setText("" + big);
    }

    void Productos(String valor) {
        String mostrar = "SELECT * FROM tabla_productos WHERE CONCAT(codbarras,producto) LIKE '%" + valor + "%'";
        String[] titulos = {"C.BARRAS", "PRODUCTO", "P.VENTA", "UNIDAD", "%", "IMG"};
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
                Registros[4] = rs.getString("imagen");
                Registros[5] = rs.getString("unidad");
                model.addRow(Registros);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUIGranel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 void Enviar(){
     try {
            DefaultTableModel model = (DefaultTableModel) tabla_ventas.getModel();
            JTable tabla = new JTable(model);

            Object[] fila = new Object[7];

            fila[0] = lblcodbarras.getText();
            fila[1] = lblnomproductos.getText();
            fila[2] = lblprecioreal.getText();
            fila[3] = txtpesoKG.getText();
            fila[4] = 0.0;
            fila[5] = lbldescuentopar.getText();
            fila[6] = txtprecioKG.getText();

            model.addRow(fila);
            tabla_ventas.setRowHeight(30);
            CalcularKG();
            CalcularVenta();
            RedondearSubtotal();
            
            GUIMenuPrincipal.lbltotal.setText(" Productos en la venta actual =====" + tabla_ventas.getRowCount() + "=====");
            dispose();

        } catch (Exception ex) {
            System.out.println("Error el enviar (VENTA A GRANEL FALLIDA)");
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblnomproductos = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbldescuentopar = new javax.swing.JLabel();
        lblcodbarras = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblpreunit = new javax.swing.JLabel();
        txtpesoKG = new javax.swing.JTextField();
        txtprecioKG = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblprecioreal = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("PRODUCTO A GRANEL");
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/DecimalesG.png")).getImage());
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Play.png"))); // NOI18N
        jPanel2.add(jLabel5, new java.awt.GridBagConstraints());

        lblnomproductos.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        lblnomproductos.setForeground(new java.awt.Color(255, 255, 255));
        lblnomproductos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblnomproductos.setText("nombre del producto");
        jPanel2.add(lblnomproductos, new java.awt.GridBagConstraints());

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel3.setText("Descuento parcial:  %");
        jPanel2.add(jLabel3, new java.awt.GridBagConstraints());

        lbldescuentopar.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        lbldescuentopar.setText("desc");
        jPanel2.add(lbldescuentopar, new java.awt.GridBagConstraints());

        lblcodbarras.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        lblcodbarras.setForeground(new java.awt.Color(255, 255, 255));
        lblcodbarras.setText("Código de barras");
        jPanel2.add(lblcodbarras, new java.awt.GridBagConstraints());

        jPanel4.setBackground(new java.awt.Color(255, 255, 204));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/InfoIniP.png"))); // NOI18N
        jLabel4.setText("Agregue el peso indicado para venta del producto");
        jPanel4.add(jLabel4);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setOpaque(false);
        jPanel3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel3KeyPressed(evt);
            }
        });

        lblpreunit.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        lblpreunit.setText("Cantidad a enviar");

        txtpesoKG.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        txtpesoKG.setForeground(new java.awt.Color(51, 0, 153));
        txtpesoKG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpesoKGActionPerformed(evt);
            }
        });
        txtpesoKG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpesoKGKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpesoKGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpesoKGKeyTyped(evt);
            }
        });

        txtprecioKG.setEditable(false);
        txtprecioKG.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        txtprecioKG.setForeground(new java.awt.Color(51, 0, 153));
        txtprecioKG.setToolTipText("Valor por unidad");
        txtprecioKG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtprecioKGKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Precio parcial");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 51, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Báscula no instalada");

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 0, 204));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Info.png"))); // NOI18N
        jLabel2.setText(" Precio actual:  $");

        lblprecioreal.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        lblprecioreal.setForeground(new java.awt.Color(51, 0, 204));
        lblprecioreal.setText("25.00");

        btnAceptar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Access.png"))); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Regresar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtpesoKG)
                            .addComponent(lblpreunit, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtprecioKG)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblprecioreal, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblpreunit)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtpesoKG, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtprecioKG, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblprecioreal))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        try {
            DefaultTableModel model = (DefaultTableModel) tabla_ventas.getModel();
            JTable tabla = new JTable(model);

            Object[] fila = new Object[7];

            fila[0] = lblcodbarras.getText();
            fila[1] = lblnomproductos.getText();
            fila[2] = lblprecioreal.getText();
            fila[3] = txtpesoKG.getText();
            fila[4] = 0.0;
            fila[5] = lbldescuentopar.getText();
            fila[6] = txtprecioKG.getText();

            model.addRow(fila);
            tabla_ventas.setRowHeight(30);
            CalcularKG();
            CalcularVenta();
            RedondearSubtotal();
            
            GUIMenuPrincipal.lbltotal.setText(" Productos en la venta actual =====" + tabla_ventas.getRowCount() + "=====");
            dispose();

        } catch (Exception ex) {
            System.out.println("Error el enviar (VENTA A GRANEL FALLIDA)");
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtprecioKGKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioKGKeyReleased

    }//GEN-LAST:event_txtprecioKGKeyReleased

    private void txtpesoKGKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpesoKGKeyReleased
        CalcularKG();
        RedondearValorKG();
    }//GEN-LAST:event_txtpesoKGKeyReleased

    private void txtpesoKGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpesoKGActionPerformed

    }//GEN-LAST:event_txtpesoKGActionPerformed

    private void txtpesoKGKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpesoKGKeyTyped
        keyTyped(evt); 
    }//GEN-LAST:event_txtpesoKGKeyTyped

    private void txtpesoKGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpesoKGKeyPressed
                 
    }//GEN-LAST:event_txtpesoKGKeyPressed

    private void jPanel3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel3KeyPressed
         
    }//GEN-LAST:event_jPanel3KeyPressed

    public void keyTyped(KeyEvent ke) {
        char c = ke.getKeyChar();
        if (Character.isLetter(c)) {
            getToolkit().beep();
            ke.consume();
            JOptionPane.showMessageDialog(null, "Solo se permiten números decimales o enteros", "Aviso del sistema", JOptionPane.WARNING_MESSAGE,mensaje);
//JOptionPane.showMessageDialog(null, "Solo se permiten número", "Aviso del sistema", JOptionPane.WARNING_MESSAGE);
        }
    }

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
            java.util.logging.Logger.getLogger(GUIGranel.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIGranel.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIGranel.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIGranel.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIGranel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    public static javax.swing.JLabel lblcodbarras;
    public static javax.swing.JLabel lbldescuentopar;
    public static javax.swing.JLabel lblnomproductos;
    public static javax.swing.JLabel lblprecioreal;
    private javax.swing.JLabel lblpreunit;
    public static javax.swing.JTextField txtpesoKG;
    public static javax.swing.JTextField txtprecioKG;
    // End of variables declaration//GEN-END:variables
}
