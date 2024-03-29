/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIConsultas;

import Clases.Conexion;
import Clases.Excel;
import Clases.Mensajes;
import Clases.conectar;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author  
 */
public class GUICComprasDetalladas extends javax.swing.JFrame {

    DefaultTableModel model;
//    conectar cc = new conectar();
//    Connection cn = cc.conexion();

    public GUICComprasDetalladas() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("CONSULTAS DETALLADAS DE COMPRAS");
        Image ico = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Recursos/Buscar.png"));
        this.setIconImage(ico);
        Total();
        RedondearCompras();
        DetalleCompras("");
    }

    void DetalleCompras(String valor) {
        String mostrar = "SELECT * FROM detallecompras WHERE CONCAT(proveedor) LIKE '%" + valor + "%'";
        String[] titulos = {"NºCOMPRA", "C.PROD", "DESCRIPCION", "CANT", "P.U", "DESC%", "TOTAL", "COMPRA", "PROVEEDOR", "EMPLEADO", "FECHA"};
        String[] Registros = new String[12];
        model = new DefaultTableModel(null, titulos);

        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(mostrar);
            while (rs.next()) {
                Registros[0] = rs.getString("num_comp");
                Registros[1] = rs.getString("cod_pro");
                Registros[2] = rs.getString("des_pro");
                Registros[3] = rs.getString("cant_pro");
                Registros[4] = rs.getString("pre_unit");
                Registros[5] = rs.getString("desct");
                Registros[6] = rs.getString("total");
                Registros[7] = rs.getString("tipo_comp");
                Registros[8] = rs.getString("proveedor");
                Registros[9] = rs.getString("empleado");
                Registros[10] = rs.getString("fecha");
                model.addRow(Registros);

            }
            tabla_detallecomp.setModel(model);
            tabla_detallecomp.setAutoResizeMode(tabla_detallecomp.AUTO_RESIZE_OFF);
            tabla_detallecomp.setRowHeight(20);
            tabla_detallecomp.getColumnModel().getColumn(0).setPreferredWidth(80);
            tabla_detallecomp.getColumnModel().getColumn(1).setPreferredWidth(130);
            tabla_detallecomp.getColumnModel().getColumn(2).setPreferredWidth(250);
            tabla_detallecomp.getColumnModel().getColumn(3).setPreferredWidth(50);
            tabla_detallecomp.getColumnModel().getColumn(4).setPreferredWidth(65);
            tabla_detallecomp.getColumnModel().getColumn(5).setPreferredWidth(60);
            tabla_detallecomp.getColumnModel().getColumn(6).setPreferredWidth(80);
            tabla_detallecomp.getColumnModel().getColumn(7).setPreferredWidth(120);
            tabla_detallecomp.getColumnModel().getColumn(8).setPreferredWidth(250);
            tabla_detallecomp.getColumnModel().getColumn(9).setPreferredWidth(280);
            tabla_detallecomp.getColumnModel().getColumn(10).setPreferredWidth(80);

        } catch (SQLException ex) {
            Logger.getLogger(GUICComprasDetalladas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Total() {

        double sumatoria;
        double sumatoria1 = 0;
        double totalRow = tabla_detallecomp.getRowCount();
        totalRow -= 1;

        for (int i = 0; i <= (totalRow); i++) {
            sumatoria = Double.parseDouble(String.valueOf(tabla_detallecomp.getValueAt(i, 6)));
            sumatoria1 += sumatoria;
        }
        txttotcomp.setText(String.valueOf(sumatoria1));
        RedondearCompras();
    }

    public void RedondearCompras() {
        double comp = Double.parseDouble(GUICComprasDetalladas.txttotcomp.getText());
        String valor = comp + "";
        BigDecimal big = new BigDecimal(valor);
        big = big.setScale(2, RoundingMode.HALF_UP);
        System.out.println("Número : " + big);
        txttotcomp.setText("" + big);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtnomprov = new javax.swing.JTextField();
        rdfech = new javax.swing.JRadioButton();
        jdFechas = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_detallecomp = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txttotcomp = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setBackground(new java.awt.Color(0, 153, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CONSULTAS DETALLADAS DE COMPRAS, GENERADAS A PROVEEDORES ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Opciones de busqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel1.setOpaque(false);

        txtnomprov.setText("Ingrese nombre del proveedor");
        txtnomprov.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtnomprovMouseClicked(evt);
            }
        });
        txtnomprov.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnomprovKeyReleased(evt);
            }
        });

        rdfech.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rdfech.setText("Fecha:");
        rdfech.setOpaque(false);
        rdfech.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdfechActionPerformed(evt);
            }
        });

        jdFechas.setOpaque(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Buscar.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Regresar2.png"))); // NOI18N
        jButton3.setText("Salir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtnomprov, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdfech)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jdFechas, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jdFechas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnomprov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdfech)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton3))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabla_detallecomp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabla_detallecomp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla_detallecomp);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("Total:");

        txttotcomp.setEditable(false);
        txttotcomp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txttotcomp.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Excel.png"))); // NOI18N
        jButton2.setText("Exportar a Excel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(0, 51, 204));
        jRadioButton1.setText("Todas las compras");
        jRadioButton1.setOpaque(false);
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txttotcomp, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txttotcomp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRadioButton1)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (this.rdfech.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Seleccione la opcion fecha", "Correjr", JOptionPane.WARNING_MESSAGE);
            this.rdfech.requestFocus();
            return;
        }
        
        String consulta = "";

        if (rdfech.isSelected() == true) {
            Date fecha = jdFechas.getDate();
            SimpleDateFormat formatofecha = new SimpleDateFormat("YYYY-MM-dd");
            String fec = "" + formatofecha.format(fecha);
            consulta = "SELECT * FROM detallecompras WHERE fecha='" + fec + "'";
        } else if (this.rdfech.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Seleccione la opcion fecha", "Correjr", JOptionPane.WARNING_MESSAGE);
            this.rdfech.requestFocus();
            return;
        }

        DefaultTableModel tabla = new DefaultTableModel();
        String[] titulos = {"NºCOMPRA", "C.PROD", "DESCRIPCION", "CANT", "P.U", "DESC%", "TOTAL", "COMPRA", "PROVEEDOR", "EMPLEADO", "FECHA"};
        tabla.setColumnIdentifiers(titulos);
        this.tabla_detallecomp.setModel(tabla);

        String[] Datos = new String[11];
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                Datos[0] = rs.getString("num_comp");
                Datos[1] = rs.getString("cod_pro");
                Datos[2] = rs.getString("des_pro");
                Datos[3] = rs.getString("cant_pro");
                Datos[4] = rs.getString("pre_unit");
                Datos[5] = rs.getString("desct");
                Datos[6] = rs.getString("total");
                Datos[7] = rs.getString("tipo_comp");
                Datos[8] = rs.getString("proveedor");
                Datos[9] = rs.getString("empleado");
                Datos[10] = rs.getString("fecha");
                tabla.addRow(Datos);
                tabla_detallecomp.setAutoResizeMode(tabla_detallecomp.AUTO_RESIZE_OFF);
                tabla_detallecomp.setRowHeight(20);
                tabla_detallecomp.getColumnModel().getColumn(0).setPreferredWidth(80);
                tabla_detallecomp.getColumnModel().getColumn(1).setPreferredWidth(130);
                tabla_detallecomp.getColumnModel().getColumn(2).setPreferredWidth(250);
                tabla_detallecomp.getColumnModel().getColumn(3).setPreferredWidth(50);
                tabla_detallecomp.getColumnModel().getColumn(4).setPreferredWidth(65);
                tabla_detallecomp.getColumnModel().getColumn(5).setPreferredWidth(60);
                tabla_detallecomp.getColumnModel().getColumn(6).setPreferredWidth(80);
                tabla_detallecomp.getColumnModel().getColumn(7).setPreferredWidth(120);
                tabla_detallecomp.getColumnModel().getColumn(8).setPreferredWidth(250);
                tabla_detallecomp.getColumnModel().getColumn(9).setPreferredWidth(280);
                tabla_detallecomp.getColumnModel().getColumn(10).setPreferredWidth(80);
                Total();
                RedondearCompras();

            }
        } catch (SQLException ex) {
            Logger.getLogger(GUICComprasDetalladas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void rdfechActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdfechActionPerformed
        if (this.rdfech.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Seleccione la opcion fecha", "Correjr", JOptionPane.WARNING_MESSAGE);
            this.rdfech.requestFocus();
            return;
        }
    }//GEN-LAST:event_rdfechActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      
        JFileChooser dialog = new JFileChooser();
        int opcion = dialog.showSaveDialog(GUICComprasDetalladas.this);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            File dir = dialog.getSelectedFile();
            try {
                List<JTable> tb = new ArrayList<JTable>();
                tb.add(tabla_detallecomp);

                Excel excelExporter = new Excel(tb, new File(dir.getAbsolutePath() + ".xls"));
                if (excelExporter.Export()) {
                    JOptionPane.showMessageDialog(null, "Compras detalladas exportada con éxito");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        
        /* try {
           
            List<JTable> tb = new ArrayList<JTable>();
            tb.add(tabla_detallecomp);
            Excel excellExporter = new Excel(tb, new File("DETALLE DE COMPRAS.xls"));
            if (excellExporter.Export()) {
                Mensajes.informacion("Datos exportados con èxito");
            }
            llamaExcel();
        } catch (Exception e) {
            System.out.println("No se pudo");
        }
    }*/

    /*public void llamaExcel() {
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "DETALLE DE COMPRAS.xls");
        } catch (IOException e) {// } catch (Exception e) {
            System.out.println("No se pudo");
        }*/
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtnomprovKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnomprovKeyReleased
        DetalleCompras(txtnomprov.getText());
        Total();
        RedondearCompras();
    }//GEN-LAST:event_txtnomprovKeyReleased

    private void txtnomprovMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtnomprovMouseClicked
        txtnomprov.setText("");
    }//GEN-LAST:event_txtnomprovMouseClicked

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        DetalleCompras("");
        Total();
        RedondearCompras();
    }//GEN-LAST:event_jRadioButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(GUICComprasDetalladas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUICComprasDetalladas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUICComprasDetalladas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUICComprasDetalladas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUICComprasDetalladas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdFechas;
    private javax.swing.JRadioButton rdfech;
    private javax.swing.JTable tabla_detallecomp;
    private javax.swing.JTextField txtnomprov;
    public static javax.swing.JTextField txttotcomp;
    // End of variables declaration//GEN-END:variables
}
