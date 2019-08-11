/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUInterfaces;

import Clases.Conexion;
import Clases.Redondeo;
import Clases.conectar;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author
 */
public class GUIIngresosActualizar extends javax.swing.JFrame {

    DefaultTableModel model;
    int contador;

    public GUIIngresosActualizar() {
        initComponents();
        setLocationRelativeTo(null);
        txtcoding1.setVisible(false);
        Ingresos("");
        Fechas();
        TotalIng();
        RedondearTotal();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = con.createStatement();
            ResultSet rs = sent.executeQuery("select * from tabla_conceptos");

            while (rs.next()) {
                this.cbConceptos1.addItem(rs.getString("conceptos"));

            }
            contador++;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }

    void Generarnumeracion() {
        String SQL = "select max(codingresos) from tabla_ingresos";

        int c = 0;
        int b = 0;
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                c = rs.getInt(1);
            }

            if (c == 0) {
                txtcoding1.setText("1");
            } else {

                txtcoding1.setText("" + (c + 1));

            }
        } catch (SQLException ex) {
            Logger.getLogger(GUIIngresosActualizar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void Ingresos(String valor) {
        String mostrar = "SELECT * FROM tabla_ingresos WHERE CONCAT(conceptos) LIKE '%" + valor + "%'";
        String[] titulos = {"Nº", "DESCRIPCIÓN", "MÉTODO", "REFERENCIA", "MONTO",
            "DOCUMENTO", "N° DOCUMENTO", "DETALLES", "FECHA"};
        String[] Registros = new String[9];
        model = new DefaultTableModel(null, titulos);

        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(mostrar);
            while (rs.next()) {
                Registros[0] = rs.getString("codingresos");
                Registros[1] = rs.getString("conceptos");
                Registros[2] = rs.getString("mpagos");
                Registros[3] = rs.getString("referencia");
                Registros[4] = rs.getString("monto");
                Registros[5] = rs.getString("documento");
                Registros[6] = rs.getString("numdocumento");
                Registros[7] = rs.getString("descripcion");
                Registros[8] = rs.getString("fecha");
                model.addRow(Registros);
            }
            tbingresos.setModel(model);
//            lblcant.setText("Consta de " + tbproductos.getRowCount() + " productos registradas en su sistema");
            tbingresos.setAutoResizeMode(tbingresos.AUTO_RESIZE_OFF);
            tbingresos.setRowHeight(22);
            tbingresos.getColumnModel().getColumn(0).setPreferredWidth(60);
            tbingresos.getColumnModel().getColumn(1).setPreferredWidth(260);
            tbingresos.getColumnModel().getColumn(2).setPreferredWidth(90);
            tbingresos.getColumnModel().getColumn(3).setPreferredWidth(100);
            tbingresos.getColumnModel().getColumn(4).setPreferredWidth(90);
            tbingresos.getColumnModel().getColumn(5).setPreferredWidth(100);
            tbingresos.getColumnModel().getColumn(6).setPreferredWidth(110);
            tbingresos.getColumnModel().getColumn(7).setPreferredWidth(200);
            tbingresos.getColumnModel().getColumn(8).setPreferredWidth(90);
        } catch (SQLException ex) {
            Logger.getLogger(GUIGastos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static double Calculos() {
        double total = Double.parseDouble(GUIIngresosActualizar.txttoting.getText());
        return Redondeo.redondear(total);
    }

    public void RedondearTotal() {
        double valor = Double.parseDouble(GUIIngresosActualizar.txttoting.getText());
        String val = valor + "";
        BigDecimal big = new BigDecimal(val);
        big = big.setScale(2, RoundingMode.HALF_UP);
        txttoting.setText("" + big);
    }

    public void TotalIng() {

        double sumatoria;
        double sumatoria1 = 0;
        double totalRow = tbingresos.getRowCount();
        totalRow -= 1;

        for (int i = 0; i <= (totalRow); i++) {
            sumatoria = Double.parseDouble(String.valueOf(tbingresos.getValueAt(i, 4)));
            sumatoria1 += sumatoria;
        }
        txttoting.setText(String.valueOf(sumatoria1));
        Calculos();
        RedondearTotal();
    }

    public void Fechas() {
        Calendar cal = new GregorianCalendar();
        jdfechaing1.setCalendar(cal);

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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbMpag1 = new javax.swing.JComboBox();
        cbConceptos1 = new javax.swing.JComboBox();
        txtref1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtmontos1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jdfechaing1 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbDoc1 = new javax.swing.JComboBox();
        txtndoc1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtdesc1 = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtcoding1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbingresos = new javax.swing.JTable();
        txtbuscaring = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txttoting = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ACTUALIZAR INGRESOS");
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/IngresosDinero.png")).getImage());
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Concepto:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Método de pago:");

        cbMpag1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Efectivo", "Crédito", "Cheque" }));

        txtref1.setBackground(new java.awt.Color(255, 255, 204));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Monto:");

        txtmontos1.setBackground(new java.awt.Color(255, 255, 204));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Referencia:");

        jdfechaing1.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Fecha:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Documento:");

        cbDoc1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Factura", "Ticket", "Nota de débito", "Nota de crédito", "Orden de compra", "Nota de pedido", "Otros" }));

        txtndoc1.setBackground(new java.awt.Color(255, 255, 204));

        jLabel9.setForeground(new java.awt.Color(204, 0, 0));
        jLabel9.setText("* Importante");

        jLabel11.setForeground(new java.awt.Color(204, 0, 0));
        jLabel11.setText("* Importante");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Descripción:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("N° Documento:");

        txtdesc1.setBackground(new java.awt.Color(255, 255, 204));
        txtdesc1.setColumns(20);
        txtdesc1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txtdesc1.setRows(5);
        jScrollPane1.setViewportView(txtdesc1);

        jPanel5.setOpaque(false);
        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Actualizar.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Regresar.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtref1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbMpag1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jdfechaing1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtmontos1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcoding1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbConceptos1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtndoc1)
                                    .addComponent(cbDoc1, 0, 186, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbConceptos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbMpag1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtref1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtmontos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcoding1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jdfechaing1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbDoc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtndoc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ingresos", jPanel3);

        jPanel4.setOpaque(false);

        tbingresos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbingresos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbingresosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbingresos);

        txtbuscaring.setText("Buscar");
        txtbuscaring.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtbuscaringMouseClicked(evt);
            }
        });
        txtbuscaring.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscaringKeyReleased(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Totales:");

        txttoting.setEditable(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txttoting, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtbuscaring, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtbuscaring, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txttoting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Lista", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            PreparedStatement pst = Conexion.conexion().prepareStatement("UPDATE tabla_ingresos SET conceptos='" + cbConceptos1.getSelectedItem()
                    + "',mpagos='" + cbMpag1.getSelectedItem()
                    + "',referencia='" + txtref1.getText()
                    + "',monto='" + txtmontos1.getText()
                    + "',documento='" + cbDoc1.getSelectedItem()
                    + "',numdocumento='" + txtndoc1.getText()
                    + "',descripcion='" + txtdesc1.getText()
                    + "',fecha='" + new java.sql.Date(jdfechaing1.getDate().getTime()) + "' WHERE codingresos='" + txtcoding1.getText() + "'");
            JOptionPane.showMessageDialog(null, "Se actualizado el gasto seleccionado");
            pst.executeUpdate();
            txtcoding1.setText("");
            cbConceptos1.getSelectedIndex();
            cbMpag1.getSelectedIndex();
            txtref1.setText("");
            txtmontos1.setText("");
            cbDoc1.getSelectedIndex();
            txtndoc1.setText("");
            txtdesc1.setText("");
            txtref1.requestFocus();

        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tbingresosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbingresosMouseClicked
        int fila = tbingresos.getSelectedRow();
        String cod = tbingresos.getValueAt(fila, 0).toString();
        String conc = tbingresos.getValueAt(fila, 1).toString();
        String mpag = tbingresos.getValueAt(fila, 2).toString();
        String ref = tbingresos.getValueAt(fila, 3).toString();
        String mont = tbingresos.getValueAt(fila, 4).toString();
        String doc = tbingresos.getValueAt(fila, 5).toString();
        String ndoc = tbingresos.getValueAt(fila, 6).toString();
        String desc = tbingresos.getValueAt(fila, 7).toString();
        String fec = tbingresos.getValueAt(fila, 8).toString();

        txtcoding1.setText(cod);
        cbConceptos1.setSelectedItem(conc);
        cbMpag1.setSelectedItem(mpag);
        txtref1.setText(ref);
        jdfechaing1.setDate(Date.valueOf(tbingresos.getValueAt(fila, 8).toString()));
        txtmontos1.setText(mont);
        cbDoc1.setSelectedItem(doc);
        txtndoc1.setText(ndoc);
        txtdesc1.setText(desc);
    }//GEN-LAST:event_tbingresosMouseClicked

    private void txtbuscaringKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscaringKeyReleased
        Ingresos(txtbuscaring.getText());
        TotalIng();
        Calculos();
        RedondearTotal();
    }//GEN-LAST:event_txtbuscaringKeyReleased

    private void txtbuscaringMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtbuscaringMouseClicked
        txtbuscaring.setText("");
    }//GEN-LAST:event_txtbuscaringMouseClicked

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
            java.util.logging.Logger.getLogger(GUIIngresosActualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIIngresosActualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIIngresosActualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIIngresosActualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIIngresosActualizar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbConceptos1;
    private javax.swing.JComboBox cbDoc1;
    private javax.swing.JComboBox cbMpag1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser jdfechaing1;
    private javax.swing.JTable tbingresos;
    private javax.swing.JTextField txtbuscaring;
    private javax.swing.JTextField txtcoding1;
    private javax.swing.JTextArea txtdesc1;
    private javax.swing.JTextField txtmontos1;
    private javax.swing.JTextField txtndoc1;
    private javax.swing.JTextField txtref1;
    public static javax.swing.JTextField txttoting;
    // End of variables declaration//GEN-END:variables
//  conectar cc = new conectar();
//    Connection cn = cc.conexion();
}
