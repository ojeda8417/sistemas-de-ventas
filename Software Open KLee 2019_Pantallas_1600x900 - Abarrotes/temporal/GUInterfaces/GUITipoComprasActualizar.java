/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUInterfaces;

import Clases.Conexion;
import Clases.Redondeo;
import Clases.conectar;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author 
 */
public class GUITipoComprasActualizar extends javax.swing.JFrame {
//
//    conectar cc = new conectar();
//    Connection cn = cc.conexion();
    int contador;

    public GUITipoComprasActualizar() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("ESTADO DE CRÉDITO");
        Image ico = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Recursos/Cta.Pagar.png"));
        this.setIconImage(ico);
        lblfechacomp.setText(fechaact());
        BuscarVentasCredito();   
    }
    
public static String fechaact() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("YYYY-MM-dd");
        return formatofecha.format(fecha);
    }

    void limpiar() {

        txtnumfactcomp.setText("");
        txtprovcomp.setText("");
        GUITipoComprasActualizar.txttotalcreditocomp.setText("");
        txtabonocomp.setText("");
        txtsaldocomp.setText("");
        cbTipPagocomp.setSelectedItem("---Seleccionar---");
        jdFechadepago.setDateFormatString("");
    }

    public void BuscarVentasCredito() {
        TextAutoCompleter textAutoC = new TextAutoCompleter(txtnumfactcomp);
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = con.createStatement();
            ResultSet rs = sent.executeQuery("SELECT num_comp FROM tabla_compras");
            rs.next();
            {         
                textAutoC.addItem(rs.getString("num_comp"));
            }
        } catch (SQLException e) {
            System.out.println("No se han registrado datos");
        }
    }
    
    /*void SaldoCredito() {

        try {
            double total = Double.parseDouble(this.txtsaldocomp.getText());
            double abono = Double.parseDouble(this.txtabonocomp.getText());
            double saldo = total - abono;
            txtsaldocomp.setText("" + saldo);
        } catch (Exception ex) {
            System.out.println("Saldo de abono es " + ex);
        }
    }*/
    
    void SaldoDeudor() {
        try {
            double ulttotal = Double.parseDouble(this.txtsaldocomp.getText());
            double ultabono = Double.parseDouble(this.txtultabono.getText());
            double ultsaldo = ulttotal - ultabono;
            txtpendiente.setText("" + ultsaldo);
        } catch (Exception ex) {
            System.out.println("Saldo de abono es " + ex);
        }
    }

    public static double CalculosDeudor() {
        double totalp = Double.parseDouble(GUITipoComprasActualizar.txtpendiente.getText());
        return Redondeo.redondear(totalp);
    }
    public static double Calculos() {
        double total = Double.parseDouble(GUITipoComprasActualizar.txtsaldocomp.getText());
        return Redondeo.redondear(total);
    }
    
/**
     * *********************************************************************************************************************************
     */
    void detallectapagar() {


            String InsertarSQL = "INSERT INTO detallectapagar (numfactura, proveedor, credito, ultimoabono,"
                    + "saldo, abonoactual,pendiente, fechapago) VALUES (?,?,?,?,?,?,?,?)";
            
            
            String fecha = lblfechacomp.getText();            
            String num_fact = txtnumfactcomp.getText();
            String proveedor = txtprovcomp.getText();
            String totcred = txttotalcreditocomp.getText();
            String abono = txtabonocomp.getText();
            String saldo = txtsaldocomp.getText();            
            String pend = txtpendiente.getText();            
            String tabono = txtultabono.getText();

            try {

                PreparedStatement pst = Conexion.conexion().prepareStatement(InsertarSQL);
                pst.setString(1, num_fact);
                pst.setString(2, proveedor);
                pst.setString(3, totcred);                
                pst.setString(4, abono);
                pst.setString(5, saldo);                
                pst.setString(6, tabono);
                pst.setString(7, pend);
                pst.setDate(8, new java.sql.Date(jdFechadepago.getDate().getTime()));
                
                pst.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(GUITipoComprasActualizar.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jdFechadepago = new com.toedter.calendar.JDateChooser();
        txtnumfactcomp = new javax.swing.JTextField();
        txtprovcomp = new javax.swing.JTextField();
        txttotalcreditocomp = new javax.swing.JTextField();
        txtabonocomp = new javax.swing.JTextField();
        txtsaldocomp = new javax.swing.JTextField();
        lblfechacomp = new javax.swing.JLabel();
        cbTipPagocomp = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        txtultabono = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtpendiente = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("Fecha a cancelar:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("Nº Factura:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("Cliente:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setText("Total de crédito:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Paga con:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setText("Último abono:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Saldo:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setText("Fecha que cancela:");

        jdFechadepago.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jdFechadepago.setOpaque(false);

        txtnumfactcomp.setEditable(false);
        txtnumfactcomp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtnumfactcomp.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtprovcomp.setEditable(false);
        txtprovcomp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtprovcomp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtprovcomp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtprovcompKeyPressed(evt);
            }
        });

        txttotalcreditocomp.setEditable(false);
        txttotalcreditocomp.setBackground(new java.awt.Color(255, 255, 0));
        txttotalcreditocomp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txttotalcreditocomp.setForeground(new java.awt.Color(153, 0, 0));
        txttotalcreditocomp.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtabonocomp.setEditable(false);
        txtabonocomp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtabonocomp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtabonocomp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtabonocompKeyReleased(evt);
            }
        });

        txtsaldocomp.setEditable(false);
        txtsaldocomp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtsaldocomp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtsaldocomp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsaldocompActionPerformed(evt);
            }
        });

        lblfechacomp.setBackground(new java.awt.Color(255, 255, 204));
        lblfechacomp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblfechacomp.setForeground(new java.awt.Color(153, 0, 0));
        lblfechacomp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblfechacomp.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cbTipPagocomp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbTipPagocomp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Seleccionar---", "Efectivo", "Abono" }));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Abono:");

        txtultabono.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtultabono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtultabono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtultabonoKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel11.setText("Pendiente a pagar:");

        txtpendiente.setEditable(false);
        txtpendiente.setBackground(new java.awt.Color(255, 204, 204));
        txtpendiente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtpendiente.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Actualizar.png"))); // NOI18N
        jButton1.setToolTipText("Actualizar pago");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Regresar.png"))); // NOI18N
        jButton2.setToolTipText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jdFechadepago, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(txtabonocomp, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttotalcreditocomp, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtpendiente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtsaldocomp, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtultabono)
                                .addComponent(cbTipPagocomp, 0, 148, Short.MAX_VALUE))))
                    .addComponent(txtnumfactcomp, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtprovcomp, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblfechacomp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblfechacomp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnumfactcomp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtprovcomp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(txtsaldocomp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txttotalcreditocomp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtabonocomp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jdFechadepago, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(txtultabono)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtpendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbTipPagocomp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtabonocompKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtabonocompKeyReleased
        
    }//GEN-LAST:event_txtabonocompKeyReleased

    private void txtprovcompKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprovcompKeyPressed
        try {

            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                if (this.contador >= 0) {

                  //  Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
                    Statement st = Conexion.conexion().createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM tabla_compras WHERE num_comp= '" + this.txtnumfactcomp.getText() + "'");
                    rs.next();
                    txtnumfactcomp.setText(String.valueOf(rs.getString("num_comp")));
                    txtprovcomp.setText(String.valueOf(rs.getString("proveedor")));
                    txttotalcreditocomp.setText(String.valueOf(rs.getString("credito")));
                    txtabonocomp.requestFocus();
                }
            }
        } catch (SQLException ex) {
            System.out.println("Ciudado este codigo = " + ex);
        }
    }//GEN-LAST:event_txtprovcompKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            PreparedStatement pst = Conexion.conexion().prepareStatement("UPDATE cuentas_pagar SET proveedor='" + txtprovcomp.getText()
                    + "',total_credito='" + txttotalcreditocomp.getText()
                    + "',tipo_pago='" + cbTipPagocomp.getSelectedItem()
                    + "',abonos='" + txtultabono.getText()
                    + "',saldos='" + txtpendiente.getText()
                    + "',fecha_pago='" + new java.sql.Date(jdFechadepago.getDate().getTime())
                    + "',fecha_cancelar='" + lblfechacomp.getText() + "' WHERE num_fact='" + txtnumfactcomp.getText() + "'");

            JOptionPane.showMessageDialog(null, "Actualización de cuenta exitosa");
            pst.executeUpdate();
            detallectapagar();
            limpiar();

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtultabonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtultabonoKeyReleased
        SaldoDeudor();
        try {
            double Calculosp = GUITipoComprasActualizar.CalculosDeudor();
            txtpendiente.setText(String.valueOf(Calculosp));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtultabonoKeyReleased

    private void txtsaldocompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsaldocompActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsaldocompActionPerformed

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
            java.util.logging.Logger.getLogger(GUITipoComprasActualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUITipoComprasActualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUITipoComprasActualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUITipoComprasActualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUITipoComprasActualizar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox cbTipPagocomp;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    public static com.toedter.calendar.JDateChooser jdFechadepago;
    public static javax.swing.JLabel lblfechacomp;
    public static javax.swing.JTextField txtabonocomp;
    public static javax.swing.JTextField txtnumfactcomp;
    public static javax.swing.JTextField txtpendiente;
    public static javax.swing.JTextField txtprovcomp;
    public static javax.swing.JTextField txtsaldocomp;
    public static javax.swing.JTextField txttotalcreditocomp;
    public static javax.swing.JTextField txtultabono;
    // End of variables declaration//GEN-END:variables
}
