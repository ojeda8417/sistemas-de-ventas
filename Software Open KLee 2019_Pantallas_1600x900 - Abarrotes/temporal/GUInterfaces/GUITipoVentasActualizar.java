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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author 
 */
public class GUITipoVentasActualizar extends javax.swing.JFrame {

//    conectar cc = new conectar();
//    Connection cn = cc.conexion();
//    
    PreparedStatement pstm = null;
    ResultSet rs = null;
//    conectar conect = new conectar();
//    Connection cnt = conect.conexion();
    
    
    int contador;

    public GUITipoVentasActualizar() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("ESTADO DE CRÉDITO");
        Image ico = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Recursos/Cta.Cobrar.png"));
        this.setIconImage(ico);
        lblfechacancelar.setText(fechaact());
        BuscarVentasCredito();
    }
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Recursos/LogoOK2019.png"));
        return retValue;
    }
    public static String fechaact() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("YYYY-MM-dd");
        return formatofecha.format(fecha);
    }

    void limpiar() {

        txtnumfactvent.setText("");
        txtclientesvent.setText("");
        GUITipoVentasActualizar.txttotalcreditovent.setText("");
        txtabonovent.setText("");
        txtultabonovent.setText("");
        txtpendientevent.setText("");
        
        txtsaldovent.setText("");
        cbTipPagovent.setSelectedItem("---Seleccionar---");
        jdFechaPagocta.setDateFormatString("");
    }

    public void BuscarVentasCredito() {
        TextAutoCompleter textAutoC = new TextAutoCompleter(txtnumfactvent);
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = con.createStatement();
            ResultSet rs = sent.executeQuery("SELECT num_fact FROM tabla_ventas");
            rs.next();
            {
                textAutoC.addItem(rs.getString("num_fact"));
            }
        } catch (SQLException e) {
            System.out.println("No se han registrado datos");
        }
    }
    void SaldoCredito() {
        try {
            double ulttotal = Double.parseDouble(this.txtsaldovent.getText());
            double ultabono = Double.parseDouble(this.txtultabonovent.getText());
            double ultsaldo = ulttotal - ultabono;
            txtpendientevent.setText("" + ultsaldo);
        } catch (Exception ex) {
            System.out.println("Saldo de abono es " + ex);
        }
    }

    public static double Calculos() {
        double total = Double.parseDouble(GUITipoVentasActualizar.txtpendientevent.getText());
        return Redondeo.redondear(total);
    }

    /**
     * *********************************************************************************************************************************
     */
    void detallectacobrar() {

        String InsertarSQL = "INSERT INTO detallectacobrar (numfactura, cliente, credito, ultimoabono,"
                + "saldo,abonoactual, pendiente,fechapago) VALUES (?,?,?,?,?,?,?,?)";

        String fecha = lblfechacancelar.getText();
        String num_fact = txtnumfactvent.getText();
        String clientes = txtclientesvent.getText();
        String totcred = txttotalcreditovent.getText();
        String abono = txtabonovent.getText();
        String saldo = txtsaldovent.getText();
        String pend = txtpendientevent.getText();
        String tabono = txtultabonovent.getText();

        try {

            PreparedStatement pst = Conexion.conexion().prepareStatement(InsertarSQL);
            pst.setString(1, num_fact);
            pst.setString(2, clientes);
            pst.setString(3, totcred);
            pst.setString(4, abono);
            pst.setString(5, saldo);
            pst.setString(6, tabono);
            pst.setString(7, pend);
            pst.setDate(8, new java.sql.Date(jdFechaPagocta.getDate().getTime()));

            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(GUITipoVentasActualizar.class.getName()).log(Level.SEVERE, null, ex);
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
        jdFechaPagocta = new com.toedter.calendar.JDateChooser();
        txtnumfactvent = new javax.swing.JTextField();
        txtclientesvent = new javax.swing.JTextField();
        txttotalcreditovent = new javax.swing.JTextField();
        txtabonovent = new javax.swing.JTextField();
        txtsaldovent = new javax.swing.JTextField();
        lblfechacancelar = new javax.swing.JLabel();
        cbTipPagovent = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        txtultabonovent = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtpendientevent = new javax.swing.JTextField();
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

        jdFechaPagocta.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jdFechaPagocta.setOpaque(false);

        txtnumfactvent.setEditable(false);
        txtnumfactvent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtnumfactvent.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtclientesvent.setEditable(false);
        txtclientesvent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtclientesvent.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtclientesvent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtclientesventKeyPressed(evt);
            }
        });

        txttotalcreditovent.setEditable(false);
        txttotalcreditovent.setBackground(new java.awt.Color(255, 255, 0));
        txttotalcreditovent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txttotalcreditovent.setForeground(new java.awt.Color(153, 0, 0));
        txttotalcreditovent.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtabonovent.setEditable(false);
        txtabonovent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtabonovent.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtabonovent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtabonoventKeyReleased(evt);
            }
        });

        txtsaldovent.setEditable(false);
        txtsaldovent.setBackground(new java.awt.Color(204, 255, 204));
        txtsaldovent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtsaldovent.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblfechacancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblfechacancelar.setForeground(new java.awt.Color(153, 0, 0));
        lblfechacancelar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblfechacancelar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cbTipPagovent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbTipPagovent.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Seleccionar---", "Abono" }));
        cbTipPagovent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipPagoventActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Abono:");

        txtultabonovent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtultabonovent.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtultabonovent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtultabonoventActionPerformed(evt);
            }
        });
        txtultabonovent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtultabonoventKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel11.setText("Pendiente a cobrar:");

        txtpendientevent.setEditable(false);
        txtpendientevent.setBackground(new java.awt.Color(255, 204, 204));
        txtpendientevent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtpendientevent.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Print.png"))); // NOI18N
        jButton1.setText("Actualizar e imprimir");
        jButton1.setToolTipText("Actualizar cobro");
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
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtclientesvent)
                    .addComponent(txtnumfactvent)
                    .addComponent(lblfechacancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtabonovent, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdFechaPagocta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(txtpendientevent)
                            .addComponent(txttotalcreditovent, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtsaldovent)
                                    .addComponent(cbTipPagovent, 0, 149, Short.MAX_VALUE)
                                    .addComponent(txtultabonovent)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblfechacancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtnumfactvent, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtclientesvent, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttotalcreditovent, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTipPagovent, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtabonovent, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtsaldovent, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtultabonovent, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jdFechaPagocta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtpendientevent, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtabonoventKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtabonoventKeyReleased
        SaldoCredito();
        try {
            double Calculos = GUITipoVentasActualizar.Calculos();
            txtsaldovent.setText(String.valueOf(Calculos));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtabonoventKeyReleased

    private void txtclientesventKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtclientesventKeyPressed
        try {

            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                if (this.contador >= 0) {

                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
                    Statement st =  Conexion.conexion().createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM tabla_ventas WHERE num_fact= '" + this.txtnumfactvent.getText() + "'");
                    rs.next();
                    txtnumfactvent.setText(String.valueOf(rs.getString("num_fact")));
                    txtclientesvent.setText(String.valueOf(rs.getString("cliente")));
                    txttotalcreditovent.setText(String.valueOf(rs.getString("total")));
                    txtabonovent.requestFocus();
                }
            }
        } catch (SQLException ex) {
            System.out.println("Ciudado este codigo = " + ex);
        }
    }//GEN-LAST:event_txtclientesventKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            PreparedStatement pst =  Conexion.conexion().prepareStatement("UPDATE cuentas_cobrar SET cliente='" + txtclientesvent.getText()
                    + "',total_credito='" + txttotalcreditovent.getText()
                    + "',tipo_pago='" + cbTipPagovent.getSelectedItem()
                    + "',abonos='" + txtultabonovent.getText()
                    + "',saldos='" + txtpendientevent.getText()
                    + "',fecha_pago='" + new java.sql.Date(jdFechaPagocta.getDate().getTime())
                    + "',fecha_cancelar='" + lblfechacancelar.getText() + "' WHERE num_fact='" + txtnumfactvent.getText() + "'");

            JOptionPane.showMessageDialog(null, "Actualización de cuenta exitosa");
            pst.executeUpdate();
            detallectacobrar();
            
            String numf = txtultabonovent.getText();
            int print = JOptionPane.showConfirmDialog(null, "Desea imprimir comprobante?", "Información", JOptionPane.YES_NO_OPTION);
            if (print == 0) {

                try {

                    String urlreporte = "src/Reportes/R_ImpresionCtaCobrar.jasper";
                    Map parametros = new HashMap();
                    parametros.put("fecha", jdFechaPagocta.getDate());
                    JasperPrint reporte = JasperFillManager.fillReport(urlreporte, parametros,Conexion.conexion());
                    JasperViewer ventana = new JasperViewer(reporte, true);
                    ventana.setTitle("IMPRIMIR/GUARDAR COMPROBANTE");
                    ventana.setVisible(true);
                    ventana.setIconImage(getIconImage());

                } catch (Exception e) {
                    System.out.printf(e.getMessage());
                }
            }
                        
            limpiar();

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtultabonoventKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtultabonoventKeyReleased
        SaldoCredito();
        try {
            double Calculosv = GUITipoVentasActualizar.Calculos();
            txtpendientevent.setText(String.valueOf(Calculosv));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtultabonoventKeyReleased

    private void txtultabonoventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtultabonoventActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtultabonoventActionPerformed

    private void cbTipPagoventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipPagoventActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipPagoventActionPerformed

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
            java.util.logging.Logger.getLogger(GUITipoVentasActualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUITipoVentasActualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUITipoVentasActualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUITipoVentasActualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new GUITipoVentasActualizar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox cbTipPagovent;
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
    public static com.toedter.calendar.JDateChooser jdFechaPagocta;
    public static javax.swing.JLabel lblfechacancelar;
    public static javax.swing.JTextField txtabonovent;
    public static javax.swing.JTextField txtclientesvent;
    public static javax.swing.JTextField txtnumfactvent;
    public static javax.swing.JTextField txtpendientevent;
    public static javax.swing.JTextField txtsaldovent;
    public static javax.swing.JTextField txttotalcreditovent;
    public static javax.swing.JTextField txtultabonovent;
    // End of variables declaration//GEN-END:variables
}
