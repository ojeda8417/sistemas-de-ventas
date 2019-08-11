/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIConsultas;

import Clases.CellRendererCobros; 
import Clases.Conexion;
import Clases.Excel;
import Clases.Redondeo;
import Clases.conectar;
import GUInterfaces.GUITipoVentasActualizar;
import GUInterfaces.GUITipoVentasVer;
import GUInterfaces.GUIVerUltimoPagoCtaCli;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File; 
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
public class GUICCtaCobrar extends javax.swing.JFrame {
//    conectar cc = new conectar();
//    Connection cn = cc.conexion();
    int contador;
    DefaultTableModel model;

    public GUICCtaCobrar() {
        initComponents();
        setTitle("ESTADOS PENDIENTES DE CUENTAS POR COBRAR");
        Image ico = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Recursos/Cta.Cobrar.png"));
        this.setIconImage(ico);
        setLocationRelativeTo(null);
        tb_ctacobrar.setDefaultRenderer(Object.class, new CellRendererCobros());
        
        ClientesCob("");
        totalCobrar();
        RedondearCuentas1();
        RedondearCuentas2();
    }
    
    void ClientesCob(String valor) {
        String mostrar = "SELECT * FROM cuentas_cobrar WHERE CONCAT(cliente) LIKE '%" + valor + "%'";
        String[] titulos = {"Nº", "CLIENTE", "CRÉDITO", "T.PAGO", "ABONO", "SALDO", "F.REGISTRO", "F.CANCELAR"};
        String[] Registros = new String[8];
        model = new DefaultTableModel(null, titulos);

        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(mostrar);
            while (rs.next()) {
                Registros[0] = rs.getString("num_fact");
                Registros[1] = rs.getString("cliente");
                Registros[2] = rs.getString("total_credito");
                Registros[3] = rs.getString("tipo_pago");
                Registros[4] = rs.getString("abonos");
                Registros[5] = rs.getString("saldos");
                Registros[6] = rs.getString("fecha_pago");
                Registros[7] = rs.getString("fecha_cancelar");
                model.addRow(Registros);
            }

            tb_ctacobrar.setModel(model);
            tb_ctacobrar.setAutoResizeMode(tb_ctacobrar.AUTO_RESIZE_OFF);
            tb_ctacobrar.setRowHeight(22);
            tb_ctacobrar.getColumnModel().getColumn(0).setPreferredWidth(80);
            tb_ctacobrar.getColumnModel().getColumn(1).setPreferredWidth(370);
            tb_ctacobrar.getColumnModel().getColumn(2).setPreferredWidth(100);
            tb_ctacobrar.getColumnModel().getColumn(3).setPreferredWidth(130);
            tb_ctacobrar.getColumnModel().getColumn(4).setPreferredWidth(80);
            tb_ctacobrar.getColumnModel().getColumn(5).setPreferredWidth(80);
            tb_ctacobrar.getColumnModel().getColumn(6).setPreferredWidth(100);
            tb_ctacobrar.getColumnModel().getColumn(7).setPreferredWidth(100);
            TotalCob();
            CalculosCob();

        } catch (SQLException ex) {
            Logger.getLogger(GUICCtaCobrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TotalCob() {

        double sumatoria;
        double sumatoria1 = 0;
        double totalRow = tb_ctacobrar.getRowCount();
        totalRow -= 1;
        for (int i = 0; i <= (totalRow); i++) {
            sumatoria = Double.parseDouble(String.valueOf(tb_ctacobrar.getValueAt(i, 2)));
            sumatoria1 += sumatoria;
        }
        txttotalcobrar.setText(String.valueOf(sumatoria1));
        CalculosCob();
    }

    public static double CalculosCob() 
    {
        double total = Double.parseDouble(GUICCtaCobrar.txttotalcobrar.getText());
        return Redondeo.redondear(total);
    }

    public void totalCobrar() {

        double sumatoria;
        double sumatoria1 = 0;
        double totalRow = tb_ctacobrar.getRowCount();
        totalRow -= 1;
        for (int i = 0; i <= (totalRow); i++) {
            sumatoria = Double.parseDouble(String.valueOf(tb_ctacobrar.getValueAt(i, 5)));
            sumatoria1 += sumatoria;
        }
        txtcobrar.setText(String.valueOf(sumatoria1));
        CalculosCob(); 
    }

    public static double calculoTotalCobrar()//Metodo que realiza los calculos finales de la venta
    {
        double total = Double.parseDouble(GUICCtaCobrar.txtcobrar.getText());
        return Redondeo.redondear(total);
    }

    public void RedondearCuentas1(){
      double valor = Double.parseDouble(GUICCtaCobrar.txttotalcobrar.getText());
      String val = valor+"";
      BigDecimal big = new BigDecimal(val);
      big = big.setScale(2, RoundingMode.HALF_UP);
      System.out.println("Número : "+big);    
      txttotalcobrar.setText(""+big);
    }    
    
    public void RedondearCuentas2(){
      double valor2 = Double.parseDouble(GUICCtaCobrar.txtcobrar.getText());
      String val2 = valor2+"";
      BigDecimal big2 = new BigDecimal(val2);
      big2 = big2.setScale(2, RoundingMode.HALF_UP);
      System.out.println("Número : "+big2);    
      txtcobrar.setText(""+big2);
    }  
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popEstados = new javax.swing.JPopupMenu();
        mnver = new javax.swing.JMenuItem();
        mnverultimo = new javax.swing.JMenuItem();
        mnactualizar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtbuscli = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jdFechPagar = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_ctacobrar = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txttotalcobrar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtcobrar = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        mnver.setText("Ver cuenta del cliente");
        mnver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnverActionPerformed(evt);
            }
        });
        popEstados.add(mnver);

        mnverultimo.setText("Ver último cobro");
        mnverultimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnverultimoActionPerformed(evt);
            }
        });
        popEstados.add(mnverultimo);

        mnactualizar.setText("Actualizar cobros");
        mnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnactualizarActionPerformed(evt);
            }
        });
        popEstados.add(mnactualizar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CONSULTA DE ESTADOS PENDIENTES DE CUENTAS POR COBRAR");

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

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Opciones de busqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel3.setOpaque(false);

        txtbuscli.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtbuscli.setText("Ingrese nombre del cliente");
        txtbuscli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtbuscliMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtbuscliMousePressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Fecha:");

        jdFechPagar.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jdFechPagar.setOpaque(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Buscar.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Vencimientos.png"))); // NOI18N
        jButton2.setToolTipText("Vencimiento de cobros");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/EstadoCuenta.png"))); // NOI18N
        jButton4.setToolTipText("Ver estado de cuentas");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtbuscli)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jdFechPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jdFechPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtbuscli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tb_ctacobrar.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tb_ctacobrar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_ctacobrar.setComponentPopupMenu(popEstados);
        jScrollPane1.setViewportView(tb_ctacobrar);

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Total de cta. por cobrar:");
        jPanel4.add(jLabel3);

        txttotalcobrar.setEditable(false);
        txttotalcobrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txttotalcobrar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel4.add(txttotalcobrar);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Total de cobro:");
        jPanel4.add(jLabel4);

        txtcobrar.setEditable(false);
        txtcobrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtcobrar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel4.add(txtcobrar);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Excel.png"))); // NOI18N
        jButton3.setText("Exportar a Excel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void mnverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnverActionPerformed
        try {
            int filas = tb_ctacobrar.getSelectedRow();
            String ctas = tb_ctacobrar.getValueAt(filas, 1).toString();
            ctas = tb_ctacobrar.getValueAt(filas, 0).toString();
            String fact, cli, tot, tip, ab, sal, fec,fecan;

            fact = tb_ctacobrar.getValueAt(filas, 0).toString();
            cli = tb_ctacobrar.getValueAt(filas, 1).toString();
            tot = tb_ctacobrar.getValueAt(filas, 2).toString();
            tip = tb_ctacobrar.getValueAt(filas, 3).toString();
            ab = tb_ctacobrar.getValueAt(filas, 4).toString();
            sal = tb_ctacobrar.getValueAt(filas, 5).toString();
            fec = tb_ctacobrar.getValueAt(filas, 6).toString();
            fecan = tb_ctacobrar.getValueAt(filas, 7).toString();

            GUITipoVentasVer ge = new GUITipoVentasVer();
            ge.setVisible(true);

            GUITipoVentasVer.txtnumfact.setText(fact);
            GUITipoVentasVer.txtclientes.setText(cli);
            GUITipoVentasVer.txttotalcredito.setText(tot);
            GUITipoVentasVer.cbTipPago.setSelectedItem(tip);
            GUITipoVentasVer.txtabono.setText(ab);
            GUITipoVentasVer.txtsaldo.setText(sal);
            GUITipoVentasVer.lblfecha.setText(fec);
            GUITipoVentasVer.jdFechaCancelar.setDate(java.sql.Date.valueOf(tb_ctacobrar.getValueAt(filas, 7).toString()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila de la tabla");
            System.out.println("Error" + e);
        }
    }//GEN-LAST:event_mnverActionPerformed

    private void mnverultimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnverultimoActionPerformed
        try {
            int filas = tb_ctacobrar.getSelectedRow();
            String ctas = tb_ctacobrar.getValueAt(filas, 1).toString();
            ctas = tb_ctacobrar.getValueAt(filas, 0).toString();
            String fact, cli, tot, tip, ab, sal, fec,fecan;

            fact = tb_ctacobrar.getValueAt(filas, 0).toString();
            cli = tb_ctacobrar.getValueAt(filas, 1).toString();
            tot = tb_ctacobrar.getValueAt(filas, 2).toString();
            ab = tb_ctacobrar.getValueAt(filas, 4).toString();
            sal = tb_ctacobrar.getValueAt(filas, 5).toString();
            fec = tb_ctacobrar.getValueAt(filas, 6).toString();

            GUIVerUltimoPagoCtaCli ccul = new GUIVerUltimoPagoCtaCli();
            ccul.setVisible(true);

            GUIVerUltimoPagoCtaCli.txtnfact.setText(fact);
            GUIVerUltimoPagoCtaCli.txtclicta.setText(cli);
            GUIVerUltimoPagoCtaCli.txtcrecta.setText(tot);
            GUIVerUltimoPagoCtaCli.txtabocta.setText(ab);
            GUIVerUltimoPagoCtaCli.txsalcta.setText(sal);
            GUIVerUltimoPagoCtaCli.jdcrecta.setDate(java.sql.Date.valueOf(tb_ctacobrar.getValueAt(filas, 6).toString()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila de la tabla");
            System.out.println("Error" + e);
        }
    }//GEN-LAST:event_mnverultimoActionPerformed

    private void mnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnactualizarActionPerformed
        try {
            int filas = tb_ctacobrar.getSelectedRow();
            String ctas = tb_ctacobrar.getValueAt(filas, 1).toString();
            ctas = tb_ctacobrar.getValueAt(filas, 0).toString();
            String fact, cli, totc, tip, ab, sal, fec,fecan;

            fact = tb_ctacobrar.getValueAt(filas, 0).toString();
            cli = tb_ctacobrar.getValueAt(filas, 1).toString();
            totc = tb_ctacobrar.getValueAt(filas, 2).toString();
            tip = tb_ctacobrar.getValueAt(filas, 3).toString();
            ab = tb_ctacobrar.getValueAt(filas, 4).toString();
            sal = tb_ctacobrar.getValueAt(filas, 5).toString();
            fec = tb_ctacobrar.getValueAt(filas, 6).toString();
            fecan = tb_ctacobrar.getValueAt(filas, 7).toString();

            GUITipoVentasActualizar tva = new GUITipoVentasActualizar();
            tva.setVisible(true);

            GUITipoVentasActualizar.txtnumfactvent.setText(fact);
            GUITipoVentasActualizar.txtclientesvent.setText(cli);
            GUITipoVentasActualizar.txttotalcreditovent.setText(totc);
            GUITipoVentasActualizar.cbTipPagovent.setSelectedItem(tip);
            GUITipoVentasActualizar.txtabonovent.setText(ab);
            GUITipoVentasActualizar.txtsaldovent.setText(sal);
            GUITipoVentasActualizar.lblfechacancelar.setText(fecan);
//            GUITipoVentasActualizar.jdFechaCancelar.setDate(java.sql.Date.valueOf(tb_ctacobrar.getValueAt(filas, 7).toString()));
            GUITipoVentasActualizar.txtultabonovent.requestFocus();
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Seleccione una fila de la tabla");
            System.out.println("Error" + e);
        }
    }//GEN-LAST:event_mnactualizarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String consulta = "";
        Date fecha = jdFechPagar.getDate();
        SimpleDateFormat formatofecha = new SimpleDateFormat("YYYY/MM/dd");
        String fec = "" + formatofecha.format(fecha);
        consulta = "SELECT * FROM cuentas_cobrar WHERE fecha_cancelar='" + fec + "'";
        DefaultTableModel tabla = new DefaultTableModel();
        String[] titulos = {"Nº", "CLIENTE", "CRÉDITO", "T.PAGO", "ABONO", "SALDO", "F.REGISTRO", "F.CANCELAR"};
        tabla.setColumnIdentifiers(titulos);
        this.tb_ctacobrar.setModel(tabla);

        String[] Datos = new String[8];
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                Datos[0] = rs.getString("num_fact");
                Datos[1] = rs.getString("cliente");
                Datos[2] = rs.getString("total_credito");
                Datos[3] = rs.getString("tipo_pago");
                Datos[4] = rs.getString("abonos");
                Datos[5] = rs.getString("saldos");
                Datos[6] = rs.getString("fecha_pago");
                Datos[7] = rs.getString("fecha_cancelar");

                tabla.addRow(Datos);
                tb_ctacobrar.setAutoResizeMode(tb_ctacobrar.AUTO_RESIZE_OFF);
                tb_ctacobrar.setRowHeight(20);
                tb_ctacobrar.getColumnModel().getColumn(0).setPreferredWidth(80);
                tb_ctacobrar.getColumnModel().getColumn(1).setPreferredWidth(370);
                tb_ctacobrar.getColumnModel().getColumn(2).setPreferredWidth(100);
                tb_ctacobrar.getColumnModel().getColumn(3).setPreferredWidth(130);
                tb_ctacobrar.getColumnModel().getColumn(4).setPreferredWidth(100);
                tb_ctacobrar.getColumnModel().getColumn(5).setPreferredWidth(100);
                tb_ctacobrar.getColumnModel().getColumn(6).setPreferredWidth(100);
                tb_ctacobrar.getColumnModel().getColumn(7).setPreferredWidth(100);
                TotalCob();
                CalculosCob();

            }
        } catch (SQLException ex) {
            Logger.getLogger(GUICCtaCobrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String consult = "";
        consult = "SELECT `num_fact`, `cliente`, `total_credito`, `tipo_pago`, `abonos`, `saldos`, `fecha_pago`, `fecha_cancelar` FROM `cuentas_cobrar` WHERE DATE(fecha_cancelar) = DATE(NOW()) OR DATE(fecha_cancelar) < DATE(NOW())";
        DefaultTableModel tabla = new DefaultTableModel();
        String[] titulos = {"Nº", "CLIENTE", "CRÉDITO", "T.PAGO", "ABONO", "SALDO", "F.REGISTRO", "F.CANCELAR"};
        tabla.setColumnIdentifiers(titulos);
        this.tb_ctacobrar.setModel(tabla);

        String[] Datos = new String[8];
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consult);
            while (rs.next()) {
                Datos[0] = rs.getString("num_fact");
                Datos[1] = rs.getString("cliente");
                Datos[2] = rs.getString("total_credito");
                Datos[3] = rs.getString("tipo_pago");
                Datos[4] = rs.getString("abonos");
                Datos[5] = rs.getString("saldos");
                Datos[6] = rs.getString("fecha_pago");
                Datos[7] = rs.getString("fecha_cancelar");

                tabla.addRow(Datos);
                tb_ctacobrar.setAutoResizeMode(tb_ctacobrar.AUTO_RESIZE_OFF);
                tb_ctacobrar.setRowHeight(20);
                tb_ctacobrar.getColumnModel().getColumn(0).setPreferredWidth(80);
                tb_ctacobrar.getColumnModel().getColumn(1).setPreferredWidth(370);
                tb_ctacobrar.getColumnModel().getColumn(2).setPreferredWidth(100);
                tb_ctacobrar.getColumnModel().getColumn(3).setPreferredWidth(130);
                tb_ctacobrar.getColumnModel().getColumn(4).setPreferredWidth(100);
                tb_ctacobrar.getColumnModel().getColumn(5).setPreferredWidth(100);
                tb_ctacobrar.getColumnModel().getColumn(6).setPreferredWidth(100);
                tb_ctacobrar.getColumnModel().getColumn(7).setPreferredWidth(100);
                TotalCob();
                CalculosCob();
                RedondearCuentas1();
                RedondearCuentas2();             

            }
        } catch (SQLException ex) {
            Logger.getLogger(GUICCtaCobrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtbuscliMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtbuscliMousePressed
        
    }//GEN-LAST:event_txtbuscliMousePressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       JFileChooser dialog = new JFileChooser();
        int opcion = dialog.showSaveDialog(GUICCtaCobrar.this);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            File dir = dialog.getSelectedFile();
            try {
                List<JTable> tb = new ArrayList<JTable>();
                tb.add(tb_ctacobrar);

                Excel excelExporter = new Excel(tb, new File(dir.getAbsolutePath() + ".xls"));
                if (excelExporter.Export()) {
                    JOptionPane.showMessageDialog(null, "Cuentas por cobrar exportada con éxito");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } 
        
        /* try {
           
            List<JTable> tb = new ArrayList<JTable>();

            tb.add(tb_ctacobrar);

            Excel excellExporter = new Excel(tb, new File("CTA. POR COBRAR.xls"));
            if (excellExporter.Export()) {
                Mensajes.informacion("Datos exportados con èxito");
            }
            llamaExcel();
        } catch (Exception e) {
            System.out.println("No se pudo");
        }
    }

    public void llamaExcel() {
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "CTA. POR COBRAR.xls");
        } catch (IOException e) {// } catch (Exception e) {
            System.out.println("No se pudo");
        }*/
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtbuscliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtbuscliMouseClicked
        txtbuscli.setText("");
    }//GEN-LAST:event_txtbuscliMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            GUICuentasClientes cctc = new GUICuentasClientes();
            cctc.setVisible(true);
        } catch (Exception e) {
            System.out.println("No se pudo");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(GUICCtaCobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUICCtaCobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUICCtaCobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUICCtaCobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUICCtaCobrar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdFechPagar;
    private javax.swing.JMenuItem mnactualizar;
    private javax.swing.JMenuItem mnver;
    private javax.swing.JMenuItem mnverultimo;
    private javax.swing.JPopupMenu popEstados;
    private javax.swing.JTable tb_ctacobrar;
    private javax.swing.JTextField txtbuscli;
    public static javax.swing.JTextField txtcobrar;
    public static javax.swing.JTextField txttotalcobrar;
    // End of variables declaration//GEN-END:variables
}
