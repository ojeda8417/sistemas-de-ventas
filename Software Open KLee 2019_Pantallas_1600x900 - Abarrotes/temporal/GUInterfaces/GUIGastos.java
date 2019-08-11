/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUInterfaces;

import Clases.Conexion;
import Clases.GenerarNumero;
import Clases.Redondeo;
import Clases.conectar;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Netfex
 */
public class GUIGastos extends javax.swing.JFrame {

//    conectar cc = new conectar();
//    Connection cn = cc.conexion();
    DefaultTableModel model;
    int cont = 0;

    public GUIGastos() {
        initComponents();
        setLocationRelativeTo(null);
        txtcod.setVisible(false);
        setTitle("EGRESOS");
        Image ico = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Recursos/Gastos.png"));
        this.setIconImage(ico);
    }

    public void Fechas() {
        Calendar c2 = new GregorianCalendar();
        jdcFecha.setCalendar(c2);

    }

    void NumeroGasto() {
        String c = "";
        String SQL = "select max(codgastos) from tabla_gastos";

        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getString(1);
            }
            if (c == null) {
                txtcod.setText("00000001");
            } else {
                int j = Integer.parseInt(c);
                GenerarNumero gen = new GenerarNumero();
                gen.generar(j);
                txtcod.setText(gen.serie());
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUIGastos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void Gastos(String valor) {
        String mostrar = "SELECT * FROM tabla_gastos WHERE CONCAT(descripcion) LIKE '%" + valor + "%'";
        String[] titulos = {"Nº", "DESCRIPCIÓN", "MES", "PERIODO", "TOTAL", "FECHA"};
        String[] Registros = new String[6];
        model = new DefaultTableModel(null, titulos);

        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(mostrar);
            while (rs.next()) {
                Registros[0] = rs.getString("codgastos");
                Registros[1] = rs.getString("descripcion");
                Registros[2] = rs.getString("mes");
                Registros[3] = rs.getString("periodo");
                Registros[4] = rs.getString("total");
                Registros[5] = rs.getString("fecha");
                model.addRow(Registros);
            }
            tb_gast.setModel(model);
//            lblcant.setText("Consta de " + tbproductos.getRowCount() + " productos registradas en su sistema");
            tb_gast.setAutoResizeMode(tb_gast.AUTO_RESIZE_OFF);
            tb_gast.setRowHeight(22);
            tb_gast.getColumnModel().getColumn(0).setPreferredWidth(85);
            tb_gast.getColumnModel().getColumn(1).setPreferredWidth(290);
            tb_gast.getColumnModel().getColumn(2).setPreferredWidth(90);
            tb_gast.getColumnModel().getColumn(3).setPreferredWidth(80);
            tb_gast.getColumnModel().getColumn(4).setPreferredWidth(90);
            tb_gast.getColumnModel().getColumn(5).setPreferredWidth(90);
        } catch (SQLException ex) {
            Logger.getLogger(GUIGastos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Total() {

        double sumatoria;
        double sumatoria1 = 0;
        double totalRow = tb_gast.getRowCount();
        totalRow -= 1;

        for (int i = 0; i <= (totalRow); i++) {
            sumatoria = Double.parseDouble(String.valueOf(tb_gast.getValueAt(i, 4)));
            sumatoria1 += sumatoria;
        }
        txtotgast.setText(String.valueOf(sumatoria1));
        Calculos();
        RedondearTotal();
    }

    public static double Calculos() {
        double total = Double.parseDouble(GUIGastos.txtotgast.getText());
        return Redondeo.redondear(total);
    }

    void contar() {
        String sql2 = "SELECT count(*) as cont from tabla_gastos";
        try {
            int con = 0;
            Statement s = Conexion.conexion().createStatement();
            ResultSet rs = s.executeQuery(sql2);
            while (rs.next()) {
                con = rs.getInt("cont");

            }
            if (con == 0) {
            } else {
                cont = con - 1;
                cargar(cont);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUIProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * ******************************************************
     */
    void cargar(int limite) {
        String sql = "SELECT * FROM tabla_gastos limit " + limite + ",1";
        String imagen_string = null;
        BufferedImage img = null;

        try {
            Statement s = Conexion.conexion().createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                txtcod.setText(String.valueOf(rs.getString("codgastos")));
                txtdetalle.setText(String.valueOf(rs.getString("descripcion")));
                cbMes.setSelectedItem(String.valueOf(rs.getString("mes")));
                cbAnio.setSelectedItem(String.valueOf(rs.getString("periodo")));
                txtmonto.setText(String.valueOf(rs.getString("total")));
                jdcFecha.setDateFormatString(String.valueOf(rs.getString("fecha")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUIProductos.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    public void RedondearTotal() {
        double valor = Double.parseDouble(GUIGastos.txtotgast.getText());
        String val = valor + "";
        BigDecimal big = new BigDecimal(val);
        big = big.setScale(2, RoundingMode.HALF_UP);
        txtotgast.setText("" + big);
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
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jdcFecha = new com.toedter.calendar.JDateChooser();
        cbAnio = new javax.swing.JComboBox();
        txtmonto = new javax.swing.JTextField();
        txtdetalle = new javax.swing.JTextField();
        cbMes = new javax.swing.JComboBox();
        txtcod = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_gast = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtotgast = new javax.swing.JTextField();
        txtbuscargast = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbPer = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cbMeses = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Detalles:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Mes:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Monto:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Año:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Fecha:");

        jdcFecha.setOpaque(false);

        cbAnio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[Seleccionar]", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022" }));

        txtmonto.setBackground(new java.awt.Color(255, 255, 204));

        txtdetalle.setBackground(new java.awt.Color(255, 255, 204));

        cbMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[Seleccionar]", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtdetalle)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbMes, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmonto, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtdetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtmonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdcFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Registro", jPanel4);

        jPanel6.setOpaque(false);

        tb_gast.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_gast.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_gastMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_gast);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Total de gastos:");

        txtotgast.setEditable(false);
        txtotgast.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtotgast.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtbuscargast.setText("Buscar");
        txtbuscargast.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtbuscargastMouseClicked(evt);
            }
        });
        txtbuscargast.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscargastKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Año:");

        cbPer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[Seleccionar]", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022" }));
        cbPer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPerActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Mes:");

        cbMeses.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[Seleccionar]", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        cbMeses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMesesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtotgast, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbPer, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbMeses, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtbuscargast, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtbuscargast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbPer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cbMeses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtotgast, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Lista", jPanel6);

        jPanel5.setOpaque(false);
        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Primero.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton6);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Anterior.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton7);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Siguiente.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton8);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Ultimo.png"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton9);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Nuevos.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Guardar.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Actualizar.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Eliminar.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Regresar.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        NumeroGasto();
        txtdetalle.setText("");
        txtmonto.setText("");
        txtotgast.setText("");
        txtdetalle.requestFocus();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (cbAnio.getSelectedItem().equals("[Seleccionar]")) {
            JOptionPane.showMessageDialog(null, "Necesita seleccionar un año para registrar", "Corregir", JOptionPane.WARNING_MESSAGE);
            this.cbAnio.requestFocus();
            return;
        }
        if (cbMes.getSelectedItem().equals("[Seleccionar]")) {
            JOptionPane.showMessageDialog(null, "Necesita seleccionar un mes para registrar", "Corregir", JOptionPane.WARNING_MESSAGE);
            this.cbMes.requestFocus();
            return;
        }
        if (txtdetalle.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Necesita describir el gasto antes de guardar", "Corregir", JOptionPane.WARNING_MESSAGE);
            this.txtdetalle.requestFocus();
            txtdetalle.setBackground(Color.YELLOW);
            return;
        }
        if (txtmonto.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Necesita detallar el monto del gasto para guardar", "Corregir", JOptionPane.WARNING_MESSAGE);
            this.txtmonto.requestFocus();
            txtmonto.setBackground(Color.YELLOW);
            return;
        }
        String cod, des, mes, tot, anio, fec;
        String sql = "";

        cod = txtcod.getText();
        des = txtdetalle.getText();
        mes = cbMes.getSelectedItem().toString();
        tot = txtmonto.getText();
        anio = cbAnio.getSelectedItem().toString();
//        fec = jdcFecha.getCalendar().getCalendarType();

        sql = "INSERT INTO tabla_gastos (codgastos, descripcion, mes, periodo, total,fecha) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pst = Conexion.conexion().prepareStatement(sql);

            pst.setString(1, cod);
            pst.setString(2, des);
            pst.setString(3, mes);
            pst.setString(4, anio);
            pst.setString(5, tot);
            pst.setDate(6, new java.sql.Date(jdcFecha.getDate().getTime()));

            int n = pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Gasto " + des + " registrado con éxito");

            }
            Gastos("");
            NumeroGasto();
            txtdetalle.setText("");
            txtmonto.setText("");
            txtdetalle.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(GUIGastos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            PreparedStatement pst = Conexion.conexion().prepareStatement("UPDATE tabla_gastos SET descripcion='" + txtdetalle.getText()
                    + "',mes='" + cbMes.getSelectedItem()
                    + "',periodo='" + cbAnio.getSelectedItem()
                    + "',total='" + txtmonto.getText()
                    + "',fecha='" + new java.sql.Date(jdcFecha.getDate().getTime()) + "' WHERE codgastos='" + txtcod.getText() + "'");
            JOptionPane.showMessageDialog(null, "Se actualizado el gasto seleccionado");
            pst.executeUpdate();
            Gastos("");

        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String nom = txtdetalle.getText();
        try {
            int eliminar = JOptionPane.showConfirmDialog(null, "Desea eliminar este registro?", "Información", JOptionPane.YES_NO_OPTION);
            if (eliminar == 0) {

                PreparedStatement pst = Conexion.conexion().prepareStatement("DELETE FROM tabla_gastos WHERE descripcion='" + nom + "'");
                JOptionPane.showMessageDialog(null, "Se ha eliminado un registro del sistema");
                pst.executeUpdate();
                Gastos("");
                txtdetalle.setText("");
                txtmonto.setText("");
                cbAnio.setSelectedItem("[Seleccionar]");
                cbMes.setSelectedItem("[Seleccionar]");
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void cbPerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPerActionPerformed
        String nom = cbPer.getSelectedItem().toString();

        {
            DefaultTableModel modelo = new DefaultTableModel();
            String[] Titulos = {"Nº", "DESCRIPCIÓN", "MES", "PERIODO", "TOTAL", "FECHA"};

            modelo.setColumnIdentifiers(Titulos);
            this.tb_gast.setModel(modelo);
            try {

                String ConsultaSQL = "SELECT * FROM tabla_gastos WHERE periodo='" + nom + "'";

                String[] Registros = new String[6];

                Statement st = Conexion.conexion().createStatement();
                ResultSet rs = st.executeQuery(ConsultaSQL);
                while (rs.next()) {
                    Registros[0] = rs.getString("codgastos");
                    Registros[1] = rs.getString("descripcion");
                    Registros[2] = rs.getString("mes");
                    Registros[3] = rs.getString("periodo");
                    Registros[4] = rs.getString("total");
                    Registros[5] = rs.getString("fecha");
                    modelo.addRow(Registros);
                    Total();
                    Calculos();
                    RedondearTotal();
                }
                tb_gast.setModel(modelo);
                tb_gast.setAutoResizeMode(tb_gast.AUTO_RESIZE_OFF);
                tb_gast.setRowHeight(22);
                tb_gast.getColumnModel().getColumn(0).setPreferredWidth(85);
                tb_gast.getColumnModel().getColumn(1).setPreferredWidth(290);
                tb_gast.getColumnModel().getColumn(2).setPreferredWidth(90);
                tb_gast.getColumnModel().getColumn(3).setPreferredWidth(80);
                tb_gast.getColumnModel().getColumn(4).setPreferredWidth(90);
                tb_gast.getColumnModel().getColumn(5).setPreferredWidth(90);
            } catch (SQLException ex) {
                Logger.getLogger(GUIGastos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cbPerActionPerformed

    private void cbMesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMesesActionPerformed
        String nom = cbMeses.getSelectedItem().toString();

        {
            DefaultTableModel modelo = new DefaultTableModel();
            String[] Titulos = {"Nº", "DESCRIPCIÓN", "MES", "PERIODO", "TOTAL", "FECHA"};

            modelo.setColumnIdentifiers(Titulos);
            this.tb_gast.setModel(modelo);
            try {

                String ConsultaSQL = "SELECT * FROM tabla_gastos WHERE mes='" + nom + "'";

                String[] Registros = new String[6];

                Statement st = Conexion.conexion().createStatement();
                ResultSet rs = st.executeQuery(ConsultaSQL);
                while (rs.next()) {
                    Registros[0] = rs.getString("codgastos");
                    Registros[1] = rs.getString("descripcion");
                    Registros[2] = rs.getString("mes");
                    Registros[3] = rs.getString("periodo");
                    Registros[4] = rs.getString("total");
                    Registros[5] = rs.getString("fecha");
                    modelo.addRow(Registros);
                    Total();
                    Calculos();
                    RedondearTotal();
                }
                tb_gast.setModel(modelo);
                tb_gast.setAutoResizeMode(tb_gast.AUTO_RESIZE_OFF);
                tb_gast.setRowHeight(22);
                tb_gast.getColumnModel().getColumn(0).setPreferredWidth(85);
                tb_gast.getColumnModel().getColumn(1).setPreferredWidth(290);
                tb_gast.getColumnModel().getColumn(2).setPreferredWidth(90);
                tb_gast.getColumnModel().getColumn(3).setPreferredWidth(80);
                tb_gast.getColumnModel().getColumn(4).setPreferredWidth(90);
                tb_gast.getColumnModel().getColumn(5).setPreferredWidth(90);
            } catch (SQLException ex) {
                Logger.getLogger(GUIGastos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cbMesesActionPerformed

    private void txtbuscargastKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscargastKeyReleased
        Gastos(txtbuscargast.getText());
        Total();
        Calculos();
        RedondearTotal();
    }//GEN-LAST:event_txtbuscargastKeyReleased

    private void tb_gastMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_gastMouseClicked
        int fila = tb_gast.getSelectedRow();
        String cod = tb_gast.getValueAt(fila, 0).toString();
        String des = tb_gast.getValueAt(fila, 1).toString();
        String mes = tb_gast.getValueAt(fila, 2).toString();
        String an = tb_gast.getValueAt(fila, 3).toString();
        String mon = tb_gast.getValueAt(fila, 4).toString();
        String fec = tb_gast.getValueAt(fila, 5).toString();
        txtcod.setText(cod);
        txtdetalle.setText(des);
        cbMes.setSelectedItem(mes);
        cbAnio.setSelectedItem(an);
        txtmonto.setText(mon);
        jdcFecha.setDate(Date.valueOf(tb_gast.getValueAt(fila, 5).toString()));
    }//GEN-LAST:event_tb_gastMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = con.createStatement();
            ResultSet rs = sent.executeQuery("select * from tabla_gastos");
            rs.first();
            txtcod.setText(String.valueOf(rs.getString("codgastos")));
            txtdetalle.setText(String.valueOf(rs.getString("descripcion")));
            cbMes.setSelectedItem(String.valueOf(rs.getString("mes")));
            cbAnio.setSelectedItem(String.valueOf(rs.getString("periodo")));
            txtmonto.setText(String.valueOf(rs.getString("total")));
            jdcFecha.setDateFormatString(String.valueOf(rs.getString("fecha")));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString());
            JOptionPane.showMessageDialog(this, "Este es el ultimo registro" + e);
            System.out.println("Este es el ultimo registro");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (cont == 0) {
            cargar(cont);
        } else if (cont < 0) {
            cargar(cont = 0);
        } else {
            cont = cont - 1;
            cargar(cont);
            Total();
            Calculos();
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        cont = cont + 1;
        cargar(cont);
        Total();
        Calculos();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = con.createStatement();
            ResultSet rs = sent.executeQuery("select * from tabla_gastos");
            rs.last();
            txtcod.setText(String.valueOf(rs.getString("codgastos")));
            txtdetalle.setText(String.valueOf(rs.getString("descripcion")));
            cbMes.setSelectedItem(String.valueOf(rs.getString("mes")));
            cbAnio.setSelectedItem(String.valueOf(rs.getString("periodo")));
            txtmonto.setText(String.valueOf(rs.getString("total")));
            jdcFecha.setDateFormatString(String.valueOf(rs.getString("fecha")));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString());
            JOptionPane.showMessageDialog(this, "Este es el ultimo registro" + e);
            System.out.println("Este es el ultimo registro");
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void txtbuscargastMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtbuscargastMouseClicked
        txtbuscargast.setText("");
    }//GEN-LAST:event_txtbuscargastMouseClicked

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
            java.util.logging.Logger.getLogger(GUIGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIGastos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbAnio;
    private javax.swing.JComboBox cbMes;
    private javax.swing.JComboBox cbMeses;
    private javax.swing.JComboBox cbPer;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private javax.swing.JTable tb_gast;
    private javax.swing.JTextField txtbuscargast;
    private javax.swing.JTextField txtcod;
    private javax.swing.JTextField txtdetalle;
    private javax.swing.JTextField txtmonto;
    public static javax.swing.JTextField txtotgast;
    // End of variables declaration//GEN-END:variables
}
