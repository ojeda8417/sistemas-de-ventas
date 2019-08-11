/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIConsultas;

import Clases.CellRendererCobros;
import Clases.Conexion;
import Clases.Excel;
import Clases.Mensajes;
import Clases.Redondeo;
import Clases.conectar;
import GUInterfaces.GUITipoComprasActualizar;
import GUInterfaces.GUITiposComprasVer;
import GUInterfaces.GUIVerUltimoPagoProveedor;
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
 * @author RAUL
 */
public class GUICCtaPagar extends javax.swing.JFrame {

 
    
    int contador;
    DefaultTableModel model;

    public GUICCtaPagar() {
        initComponents();
        setTitle("ESTADOS PENDIENTES DE CUENTAS POR PAGAR");
        Image ico = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Recursos/Cta.Pagar.png"));
        this.setIconImage(ico);
        setLocationRelativeTo(null);
        tb_ctapagar.setDefaultRenderer(Object.class, new CellRendererCobros());
        Proveedor("");
        totalPagar();
        RedondearCuentas1();
        RedondearCuentas2();
        calculoTotalPagar();
    }

    void Proveedor(String valor) {
        String mostrar = "SELECT * FROM cuentas_pagar WHERE CONCAT(proveedor) LIKE '%" + valor + "%'";
        String[] titulos = {"Nº", "PROVEEDOR", "CRÉDITO", "T.PAGO", "ABONO", "SALDO", "F.REGISTRO", "F.CANCELAR"};
        String[] Registros = new String[8];
        model = new DefaultTableModel(null, titulos);

        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(mostrar);
            while (rs.next()) {
                Registros[0] = rs.getString("num_fact");
                Registros[1] = rs.getString("proveedor");
                Registros[2] = rs.getString("total_credito");
                Registros[3] = rs.getString("tipo_pago");
                Registros[4] = rs.getString("abonos");
                Registros[5] = rs.getString("saldos");
                Registros[6] = rs.getString("fecha_pago");
                Registros[7] = rs.getString("fecha_cancelar");
                model.addRow(Registros);
            }

            tb_ctapagar.setModel(model);
            tb_ctapagar.setAutoResizeMode(tb_ctapagar.AUTO_RESIZE_OFF);
            tb_ctapagar.setRowHeight(22);
            tb_ctapagar.getColumnModel().getColumn(0).setPreferredWidth(80);
            tb_ctapagar.getColumnModel().getColumn(1).setPreferredWidth(350);
            tb_ctapagar.getColumnModel().getColumn(2).setPreferredWidth(100);
            tb_ctapagar.getColumnModel().getColumn(3).setPreferredWidth(80);
            tb_ctapagar.getColumnModel().getColumn(4).setPreferredWidth(80);
            tb_ctapagar.getColumnModel().getColumn(5).setPreferredWidth(110);
            tb_ctapagar.getColumnModel().getColumn(6).setPreferredWidth(110);
            tb_ctapagar.getColumnModel().getColumn(7).setPreferredWidth(110);
            Total();
            Calculos();

        } catch (SQLException ex) {
            Logger.getLogger(GUICCtaPagar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Total() {

        double sumatoria;
        double sumatoria1 = 0;
        double totalRow = tb_ctapagar.getRowCount();
        totalRow -= 1;
        for (int i = 0; i <= (totalRow); i++) {
            sumatoria = Double.parseDouble(String.valueOf(tb_ctapagar.getValueAt(i, 2)));
            sumatoria1 += sumatoria;
        }
        txttotalpagar.setText(String.valueOf(sumatoria1));
        Calculos();
    }

    public static double Calculos() 
    {
        double total = Double.parseDouble(GUICCtaPagar.txttotalpagar.getText());
        return Redondeo.redondear(total);
    }

    
    
    public void totalPagar() {

        double sumatoria;
        double sumatoria1 = 0;
        double totalRow = tb_ctapagar.getRowCount();
        totalRow -= 1;
        for (int i = 0; i <= (totalRow); i++) {
            sumatoria = Double.parseDouble(String.valueOf(tb_ctapagar.getValueAt(i, 5)));
            sumatoria1 += sumatoria;
        }
        txtpagar.setText(String.valueOf(sumatoria1));
        calculoTotalPagar();
    }

    public static double calculoTotalPagar()//Metodo que realiza los calculos finales de la venta
    {
        double total = Double.parseDouble(GUICCtaPagar.txtpagar.getText());
        return Redondeo.redondear(total);
    }
    
  public void RedondearCuentas1(){
      double valor = Double.parseDouble(GUICCtaPagar.txttotalpagar.getText());
      String val = valor+"";
      BigDecimal big = new BigDecimal(val);
      big = big.setScale(2, RoundingMode.HALF_UP);
      System.out.println("Número : "+big);    
      txttotalpagar.setText(""+big);
    }    
    
    public void RedondearCuentas2(){
      double valor2 = Double.parseDouble(GUICCtaPagar.txtpagar.getText());
      String val2 = valor2+"";
      BigDecimal big2 = new BigDecimal(val2);
      big2 = big2.setScale(2, RoundingMode.HALF_UP);
      System.out.println("Número : "+big2);    
      txtpagar.setText(""+big2);
    }     
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popEstadosProv = new javax.swing.JPopupMenu();
        mnver = new javax.swing.JMenuItem();
        mnverultimo = new javax.swing.JMenuItem();
        mnactualizar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtbuspro = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jdFechPagar = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_ctapagar = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txttotalpagar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtpagar = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        mnver.setText("Ver cuenta a proveedor");
        mnver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnverActionPerformed(evt);
            }
        });
        popEstadosProv.add(mnver);

        mnverultimo.setText("Ver último pago");
        mnverultimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnverultimoActionPerformed(evt);
            }
        });
        popEstadosProv.add(mnverultimo);

        mnactualizar.setText("Actualizar pagos");
        mnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnactualizarActionPerformed(evt);
            }
        });
        popEstadosProv.add(mnactualizar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Opciones de busqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel2.setOpaque(false);

        txtbuspro.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtbuspro.setText("Ingrese nombre del proveedor");
        txtbuspro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtbusproMouseClicked(evt);
            }
        });
        txtbuspro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbusproKeyReleased(evt);
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
        jButton2.setToolTipText("Vencimientos de pagos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/EstadoCuenta.png"))); // NOI18N
        jButton4.setToolTipText("Ver estado de cuenta");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtbuspro)
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
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jdFechPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtbuspro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tb_ctapagar.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tb_ctapagar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_ctapagar.setComponentPopupMenu(popEstadosProv);
        jScrollPane1.setViewportView(tb_ctapagar);

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Total de cta. por pagar:");
        jPanel3.add(jLabel3);

        txttotalpagar.setEditable(false);
        txttotalpagar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txttotalpagar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(txttotalpagar);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Total de pago:");
        jPanel3.add(jLabel4);

        txtpagar.setEditable(false);
        txtpagar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtpagar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(txtpagar);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Excel.png"))); // NOI18N
        jButton3.setText("Exportar a Excel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3);

        jPanel4.setBackground(new java.awt.Color(0, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CONSULTA DE ESTADOS PENDIENTES DE CUENTAS POR PAGAR");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       JFileChooser dialog = new JFileChooser();
        int opcion = dialog.showSaveDialog(GUICCtaPagar.this);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            File dir = dialog.getSelectedFile();
            try {
                List<JTable> tb = new ArrayList<JTable>();
                tb.add(tb_ctapagar);

                Excel excelExporter = new Excel(tb, new File(dir.getAbsolutePath() + ".xls"));
                if (excelExporter.Export()) {
                    JOptionPane.showMessageDialog(null, "Cuentas por pagar exportada con éxito");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } 
        
        /*try {
            List<JTable> tb = new ArrayList<JTable>();

            tb.add(tb_ctapagar);

            Excel excellExporter = new Excel(tb, new File("CTA. POR PAGAR.xls"));
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
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "CTA. POR PAGAR.xls");
        } catch (IOException e) {// } catch (Exception e) {
            System.out.println("No se pudo");
        }*/
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String consulta = "";
        Date fecha = jdFechPagar.getDate();
        SimpleDateFormat formatofecha = new SimpleDateFormat("YYYY-MM-dd");
        String fec = "" + formatofecha.format(fecha);
        consulta = "SELECT * FROM cuentas_pagar WHERE fecha_cancelar='" + fec + "'";
        DefaultTableModel tabla = new DefaultTableModel();
        String[] titulos = {"Nº", "PROVEEDOR", "CRÉDITO", "T.PAGO","ABONO", "SALDO", "F.REGISTRO", "F.CANCELAR"};
        tabla.setColumnIdentifiers(titulos);
        this.tb_ctapagar.setModel(tabla);

        String[] Datos = new String[8];
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                Datos[0] = rs.getString("num_fact");
                Datos[1] = rs.getString("proveedor");
                Datos[2] = rs.getString("total_credito");
                Datos[3] = rs.getString("tipo_pago");
                Datos[4] = rs.getString("abonos");
                Datos[5] = rs.getString("saldos");
                Datos[6] = rs.getString("fecha_pago");
                Datos[7] = rs.getString("fecha_cancelar");

                tabla.addRow(Datos);
                tb_ctapagar.setAutoResizeMode(tb_ctapagar.AUTO_RESIZE_OFF);
                tb_ctapagar.setRowHeight(20);
                tb_ctapagar.getColumnModel().getColumn(0).setPreferredWidth(80);
                tb_ctapagar.getColumnModel().getColumn(1).setPreferredWidth(350);
                tb_ctapagar.getColumnModel().getColumn(2).setPreferredWidth(100);
                tb_ctapagar.getColumnModel().getColumn(3).setPreferredWidth(80);
                tb_ctapagar.getColumnModel().getColumn(4).setPreferredWidth(80);
                tb_ctapagar.getColumnModel().getColumn(5).setPreferredWidth(110);
                tb_ctapagar.getColumnModel().getColumn(6).setPreferredWidth(110);
                tb_ctapagar.getColumnModel().getColumn(7).setPreferredWidth(100);
                Total();
                Calculos();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUICCtaPagar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String consult = "";
        consult = "SELECT `num_fact`, `proveedor`, `total_credito`, `tipo_pago`, `abonos`, `saldos`, `fecha_pago`, `fecha_cancelar` FROM `cuentas_pagar` WHERE DATE(fecha_cancelar) = DATE(NOW()) OR DATE(fecha_cancelar) < DATE(NOW())";
        DefaultTableModel tabla = new DefaultTableModel();
        String[] titulos = {"Nº", "PROVEEDOR", "CRÉDITO","T.PAGO", "ABONO", "SALDO", "F.REGISTRO", "F.CANCELAR"};
        tabla.setColumnIdentifiers(titulos);
        this.tb_ctapagar.setModel(tabla);

        String[] Datos = new String[8];
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consult);
            while (rs.next()) {
                Datos[0] = rs.getString("num_fact");
                Datos[1] = rs.getString("proveedor");
                Datos[2] = rs.getString("total_credito");
                Datos[3] = rs.getString("tipo_pago");
                Datos[4] = rs.getString("abonos");
                Datos[5] = rs.getString("saldos");
                Datos[6] = rs.getString("fecha_pago");
                Datos[7] = rs.getString("fecha_cancelar");

                tabla.addRow(Datos);
                tb_ctapagar.setAutoResizeMode(tb_ctapagar.AUTO_RESIZE_OFF);
                tb_ctapagar.setRowHeight(20);
                tb_ctapagar.getColumnModel().getColumn(0).setPreferredWidth(80);
                tb_ctapagar.getColumnModel().getColumn(1).setPreferredWidth(370);
                tb_ctapagar.getColumnModel().getColumn(2).setPreferredWidth(100);
                tb_ctapagar.getColumnModel().getColumn(3).setPreferredWidth(130);
                tb_ctapagar.getColumnModel().getColumn(4).setPreferredWidth(100);
                tb_ctapagar.getColumnModel().getColumn(5).setPreferredWidth(100);
                tb_ctapagar.getColumnModel().getColumn(6).setPreferredWidth(100);
                tb_ctapagar.getColumnModel().getColumn(7).setPreferredWidth(100);
                Total();
                Calculos();
                RedondearCuentas1();
                RedondearCuentas2();             
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUICCtaCobrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void mnverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnverActionPerformed
        try {
            int filas = tb_ctapagar.getSelectedRow();
            String ctas = tb_ctapagar.getValueAt(filas, 1).toString();
            ctas = tb_ctapagar.getValueAt(filas, 0).toString();
            String fact, prov, tot, tip, ab, sal, fec,fecan;

            fact = tb_ctapagar.getValueAt(filas, 0).toString();
            prov = tb_ctapagar.getValueAt(filas, 1).toString();
            tot = tb_ctapagar.getValueAt(filas, 2).toString();
            tip = tb_ctapagar.getValueAt(filas, 3).toString();
            ab = tb_ctapagar.getValueAt(filas, 4).toString();
            sal = tb_ctapagar.getValueAt(filas, 5).toString();
            fec = tb_ctapagar.getValueAt(filas, 6).toString();
            fecan = tb_ctapagar.getValueAt(filas, 7).toString();
            

            GUITiposComprasVer gc = new GUITiposComprasVer();
            gc.setVisible(true);

            GUITiposComprasVer.txtnumcomp.setText(fact);
            GUITiposComprasVer.txtproveedor.setText(prov);
            GUITiposComprasVer.txttotalcred.setText(tot);
            GUITiposComprasVer.cbPago.setSelectedItem(tip);
            GUITiposComprasVer.txtabonos.setText(ab);
            GUITiposComprasVer.txtsaldos.setText(sal);
            GUITiposComprasVer.lblfecomp.setText(fec);
            GUITiposComprasVer.jDateCancelar.setDate(java.sql.Date.valueOf(tb_ctapagar.getValueAt(filas, 7).toString()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila de la tabla");
            System.out.println("Error" + e);
        }
    }//GEN-LAST:event_mnverActionPerformed

    private void mnverultimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnverultimoActionPerformed
        
        try {
            int filas = tb_ctapagar.getSelectedRow();
            String ctas = tb_ctapagar.getValueAt(filas, 1).toString();
            ctas = tb_ctapagar.getValueAt(filas, 0).toString();
            String fact, prov, tot, tip, ab, sal, fec,fecan;

            fact = tb_ctapagar.getValueAt(filas, 0).toString();
            prov = tb_ctapagar.getValueAt(filas, 1).toString();
            tot = tb_ctapagar.getValueAt(filas, 2).toString();
            ab = tb_ctapagar.getValueAt(filas, 4).toString();
            sal = tb_ctapagar.getValueAt(filas, 5).toString();
            fec = tb_ctapagar.getValueAt(filas, 6).toString();

            GUIVerUltimoPagoProveedor ccul = new GUIVerUltimoPagoProveedor();
            ccul.setVisible(true);

            GUIVerUltimoPagoProveedor.txtndep.setText(fact);
            GUIVerUltimoPagoProveedor.txtprovee.setText(prov);
            GUIVerUltimoPagoProveedor.txtcred.setText(tot);
            GUIVerUltimoPagoProveedor.txtabonos.setText(ab);
            GUIVerUltimoPagoProveedor.txtsaldos.setText(sal);
            GUIVerUltimoPagoProveedor.jDateFecha.setDate(java.sql.Date.valueOf(tb_ctapagar.getValueAt(filas, 6).toString()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila de la tabla");
            System.out.println("Error" + e);
        }
    }//GEN-LAST:event_mnverultimoActionPerformed

    private void mnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnactualizarActionPerformed
        try {
            int filas = tb_ctapagar.getSelectedRow();
            String ctas = tb_ctapagar.getValueAt(filas, 1).toString();
            ctas = tb_ctapagar.getValueAt(filas, 0).toString();
            String fact, prov, tot, tip, ab, sal, fec,fecan;

            fact = tb_ctapagar.getValueAt(filas, 0).toString();
            prov = tb_ctapagar.getValueAt(filas, 1).toString();
            tot = tb_ctapagar.getValueAt(filas, 2).toString();            
            tip = tb_ctapagar.getValueAt(filas, 3).toString();
            ab = tb_ctapagar.getValueAt(filas, 4).toString();
            sal = tb_ctapagar.getValueAt(filas, 5).toString();
            fec = tb_ctapagar.getValueAt(filas, 6).toString();
            fecan = tb_ctapagar.getValueAt(filas, 7).toString();
         
            GUITipoComprasActualizar tva = new GUITipoComprasActualizar();
            tva.setVisible(true);

            GUITipoComprasActualizar.txtnumfactcomp.setText(fact);
            GUITipoComprasActualizar.txtprovcomp.setText(prov);
            GUITipoComprasActualizar.txttotalcreditocomp.setText(tot);
            GUITipoComprasActualizar.cbTipPagocomp.setSelectedItem(tip);
            GUITipoComprasActualizar.txtabonocomp.setText(ab);
            GUITipoComprasActualizar.txtsaldocomp.setText(sal);
            GUITipoComprasActualizar.lblfechacomp.setText(fecan);
//            GUITipoComprasActualizar.jdFechadepago.setDate(java.sql.Date.valueOf(tb_ctapagar.getValueAt(filas, 7).toString()));
            GUITipoComprasActualizar.txtultabono.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila de la tabla");
            System.out.println("Error" + e);
        }
    }//GEN-LAST:event_mnactualizarActionPerformed

    private void txtbusproKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusproKeyReleased
        Proveedor(txtbuspro.getText());
    }//GEN-LAST:event_txtbusproKeyReleased

    private void txtbusproMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtbusproMouseClicked
        txtbuspro.setText("");
    }//GEN-LAST:event_txtbusproMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            GUICuentasProveedores cctp = new GUICuentasProveedores();
            cctp.show();
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
            java.util.logging.Logger.getLogger(GUICCtaPagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUICCtaPagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUICCtaPagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUICCtaPagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUICCtaPagar().setVisible(true);
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
    private javax.swing.JPopupMenu popEstadosProv;
    private javax.swing.JTable tb_ctapagar;
    private javax.swing.JTextField txtbuspro;
    public static javax.swing.JTextField txtpagar;
    public static javax.swing.JTextField txttotalpagar;
    // End of variables declaration//GEN-END:variables
}
