/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIConsultas;

import Clases.Conexion;
import Clases.conectar;
import static GUIConsultas.GUICProductoImagen.lblimagenprod;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 
 */
public class GUICVisorProductos extends javax.swing.JFrame {

    DefaultTableModel model;
    int contador;

    public GUICVisorProductos() {
        initComponents();
//        Productos("");
        setLocationRelativeTo(null);
        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = con.createStatement();
            ResultSet rs = sent.executeQuery("select * from tabla_categorias");

            while (rs.next()) {
                this.cbCat.addItem(rs.getObject("categoria"));

            }
            contador++;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = con.createStatement();
            ResultSet rs = sent.executeQuery("select * from tabla_marcas");

            while (rs.next()) {
                this.cbMarca.addItem(rs.getObject("marca"));

            }
            contador++;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = con.createStatement();
            ResultSet rs = sent.executeQuery("select * from tabla_proveedores");

            while (rs.next()) {
                this.cbProveedores.addItem(rs.getObject("nombres"));

            }
            contador++;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }

    void Productos(String valor) {
        String mostrar = "SELECT * FROM tabla_productos WHERE CONCAT(producto) LIKE '%" + valor + "%'";
        String[] titulos = {"Nº", "C.BARRAS", "PRODUCTO", "MARCA", "UNIDAD", "CATEGORIA", "STOCK",
            "IVA","DESCUENTO", "PROVEEDOR", "R.COMPRA", "P.VENTA", "F.INGRESO", "DESCRIPCION", "RUTA"};
        String[] Registros = new String[15];
        model = new DefaultTableModel(null, titulos);

        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(mostrar);
            while (rs.next()) {
                Registros[0] = rs.getString("codprod");
                    Registros[1] = rs.getString("codbarras");
                    Registros[2] = rs.getString("producto");
                    Registros[3] = rs.getString("marca");
                    Registros[4] = rs.getString("unidad");
                    Registros[5] = rs.getString("categoria");
                    Registros[6] = rs.getString("stock");
                    Registros[7] = rs.getString("iva");
                    Registros[8] = rs.getString("descuento");
                    Registros[9] = rs.getString("proveedor");
                    Registros[10] = rs.getString("pre_compra");
                    Registros[11] = rs.getString("pre_venta");
                    Registros[12] = rs.getString("fechacaducidad");
                    Registros[13] = rs.getString("detalles");
                    Registros[14] = rs.getString("imagen");

                    model.addRow(Registros);
                }
                tbprod.setModel(model);
                lblcant.setText("Consta de " + tbprod.getRowCount() + " productos registrados en su bodega");
                tbprod.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                tbprod.setRowHeight(20);
                tbprod.getColumnModel().getColumn(0).setPreferredWidth(30);
                tbprod.getColumnModel().getColumn(1).setPreferredWidth(135);
                tbprod.getColumnModel().getColumn(2).setPreferredWidth(320);
                tbprod.getColumnModel().getColumn(3).setPreferredWidth(100);
                tbprod.getColumnModel().getColumn(4).setPreferredWidth(80);
                tbprod.getColumnModel().getColumn(5).setPreferredWidth(190);
                tbprod.getColumnModel().getColumn(6).setPreferredWidth(80);
                tbprod.getColumnModel().getColumn(7).setPreferredWidth(45);
                tbprod.getColumnModel().getColumn(8).setPreferredWidth(100);
                tbprod.getColumnModel().getColumn(9).setPreferredWidth(240);
                tbprod.getColumnModel().getColumn(10).setPreferredWidth(80);
                tbprod.getColumnModel().getColumn(11).setPreferredWidth(80);
                tbprod.getColumnModel().getColumn(12).setPreferredWidth(90);
                tbprod.getColumnModel().getColumn(13).setPreferredWidth(350);
                tbprod.getColumnModel().getColumn(14).setPreferredWidth(350);
        } catch (SQLException ex) {
            Logger.getLogger(GUICVisorProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* public void calcular() {

     String pre;
     String can;
     String desc = "";
     double desct = 0;
     double iva = 0;
     double total = 0;
     double subtotal = 0;
     double precio = 0.0;
     int cantidad;
     double impParcial = 0.0;
     double descuentos = 0.0;
     double totparcial = 0.0;

     for (int i = 0; i < GUIVentas.tbdetventas.getRowCount(); i++) {
     pre = GUIVentas.tbdetventas.getValueAt(i, 2).toString();
     can = GUIVentas.tbdetventas.getValueAt(i, 3).toString();

     precio = Double.parseDouble(pre);
     cantidad = Integer.parseInt(can);

     if (GUIVentas.tbdetventas.getValueAt(i, 4) == null) {
     totparcial = precio * cantidad;

     } else {
                
     desc = GUIVentas.tbdetventas.getValueAt(i, 4).toString();
     descuentos = Double.parseDouble(desc);
     impParcial = precio * cantidad;
     desct = (precio * descuentos) * cantidad;
     totparcial = impParcial - desct;
     }

     subtotal = subtotal + totparcial;
     iva = subtotal * 0.14;
     total = subtotal + iva;
     GUIVentas.tbdetventas.setValueAt(Math.rint(totparcial * 100) / 100, i, 5);
     }

     GUIVentas.txtsubtot.setText(Double.toString(subtotal));
     GUIVentas.txtiva.setText("" + Math.rint(iva * 100) / 100);
     GUIVentas.txttotal.setText("" + Math.rint(total * 100) / 100);

     }
     */
    /*public void calcular() {

     String pre;
     String can;
     String desc = "";
     double desct = 0;
     double iva = 0;
     double total = 0;
     double subtotal = 0;
     double precio = 0.0;
     int cantidad;
     double impParcial = 0.0;
     double descuentos = 0.0;
     double totparcial = 0.0;

     for (int i = 0; i < GUIVentas.tbdetventas.getRowCount(); i++) {
     pre = GUIVentas.tbdetventas.getValueAt(i, 2).toString();
     can = GUIVentas.tbdetventas.getValueAt(i, 3).toString();

     precio = Double.parseDouble(pre);
     cantidad = Integer.parseInt(can);

     if (GUIVentas.tbdetventas.getValueAt(i, 4) == null) {
     totparcial = precio * cantidad;

     } else {
                
     desc = GUIVentas.tbdetventas.getValueAt(i, 4).toString();
     descuentos = Double.parseDouble(desc);
     impParcial = precio * cantidad;
     desct = (precio * descuentos) * cantidad;
     totparcial = impParcial - desct;
     }

     subtotal = subtotal + totparcial;
     iva = subtotal * 0.14;
     total = subtotal + iva;
     GUIVentas.tbdetventas.setValueAt(Math.rint(totparcial * 100) / 100, i, 5);
     }

     GUIVentas.txtsubtot.setText(Double.toString(subtotal));
     GUIVentas.txtiva.setText("" + Math.rint(iva * 100) / 100);
     GUIVentas.txttotal.setText("" + Math.rint(total * 100) / 100);

     }
    
        
     String comparar(String cod) {
     String cant = "";
     try {
     Statement st = cn.createStatement();
     ResultSet rs = st.executeQuery("SELECT * FROM tabla_productos WHERE codbarras='" + cod + "'");
     while (rs.next()) {
     cant = rs.getString(7);
     }

     } catch (SQLException ex) {
     Logger.getLogger(GUICVisorProductos.class.getName()).log(Level.SEVERE, null, ex);
     }
     return cant;
     }

     public void RedondearSubtotal() {
     double valor = Double.parseDouble(GUIVentas.txtsubtot.getText());
     String val = valor + "";
     BigDecimal big = new BigDecimal(val);
     big = big.setScale(2, RoundingMode.HALF_UP);
     GUIVentas.txtsubtot.setText("" + big);
     }
     */
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
        jRadioButton1 = new javax.swing.JRadioButton();
        txtbuscar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbMarca = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        cbCat = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cbProveedores = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jDate = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblcant = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbprod = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("VISOR DE PRODUCTOS");
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/Buscar.png")).getImage());
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Opciones de busquedas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 1, 11))); // NOI18N
        jPanel2.setOpaque(false);

        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton1.setText("Descripción");
        jRadioButton1.setOpaque(false);
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        txtbuscar.setBackground(new java.awt.Color(255, 255, 153));
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Marca:");

        cbMarca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Seleccionar---" }));
        cbMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMarcaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Categoría:");

        cbCat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Seleccionar---" }));
        cbCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCatActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Proveedor:");

        cbProveedores.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Seleccionar---" }));
        cbProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProveedoresActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Fecha:");

        jDate.setOpaque(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Buscar.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbCat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cbProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cbMarca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jDate, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CONSULTA DE PRODUCTOS EN BODEGA");

        lblcant.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblcant.setForeground(new java.awt.Color(0, 51, 204));
        lblcant.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(654, 654, 654)
                .addComponent(lblcant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblcant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbprod.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbprod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbprodMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbprod);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
        Productos(txtbuscar.getText());
    }//GEN-LAST:event_txtbuscarKeyReleased

    private void cbMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMarcaActionPerformed
        String stock = cbMarca.getSelectedItem().toString();

        {
            DefaultTableModel model = new DefaultTableModel();
            String[] Titulos = {"Nº", "C.BARRAS", "PRODUCTO", "MARCA", "UNIDAD", "CATEGORIA", "STOCK",
                "IVA","DESCUENTO", "PROVEEDOR", "R.COMPRA", "P.VENTA", "F.INGRESO", "DESCRIPCION", "RUTA"};
            model.setColumnIdentifiers(Titulos);
            this.tbprod.setModel(model);
            try {

                String ConsultaSQL = "SELECT * FROM tabla_productos WHERE marca='" + stock + "'";

                String[] Registros = new String[15];

                Statement st = Conexion.conexion().createStatement();
                ResultSet rs = st.executeQuery(ConsultaSQL);
                while (rs.next()) {
                    Registros[0] = rs.getString("codprod");
                    Registros[1] = rs.getString("codbarras");
                    Registros[2] = rs.getString("producto");
                    Registros[3] = rs.getString("marca");
                    Registros[4] = rs.getString("unidad");
                    Registros[5] = rs.getString("categoria");
                    Registros[6] = rs.getString("stock");
                    Registros[7] = rs.getString("iva");
                    Registros[8] = rs.getString("descuento");
                    Registros[9] = rs.getString("proveedor");
                    Registros[10] = rs.getString("pre_compra");
                    Registros[11] = rs.getString("pre_venta");
                    Registros[12] = rs.getString("fechacaducidad");
                    Registros[13] = rs.getString("detalles");
                    Registros[14] = rs.getString("imagen");

                    model.addRow(Registros);
                }
                tbprod.setModel(model);
                lblcant.setText("Consta de " + tbprod.getRowCount() + " productos registrados en su bodega");
                tbprod.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                tbprod.setRowHeight(20);
                tbprod.getColumnModel().getColumn(0).setPreferredWidth(30);
                tbprod.getColumnModel().getColumn(1).setPreferredWidth(135);
                tbprod.getColumnModel().getColumn(2).setPreferredWidth(320);
                tbprod.getColumnModel().getColumn(3).setPreferredWidth(100);
                tbprod.getColumnModel().getColumn(4).setPreferredWidth(80);
                tbprod.getColumnModel().getColumn(5).setPreferredWidth(190);
                tbprod.getColumnModel().getColumn(6).setPreferredWidth(80);
                tbprod.getColumnModel().getColumn(7).setPreferredWidth(45);
                tbprod.getColumnModel().getColumn(8).setPreferredWidth(100);
                tbprod.getColumnModel().getColumn(9).setPreferredWidth(240);
                tbprod.getColumnModel().getColumn(10).setPreferredWidth(80);
                tbprod.getColumnModel().getColumn(11).setPreferredWidth(80);
                tbprod.getColumnModel().getColumn(12).setPreferredWidth(90);
                tbprod.getColumnModel().getColumn(13).setPreferredWidth(350);
                tbprod.getColumnModel().getColumn(14).setPreferredWidth(350);
            } catch (SQLException ex) {
                Logger.getLogger(GUICVisorProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cbMarcaActionPerformed

    private void cbCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCatActionPerformed
        String cat = cbCat.getSelectedItem().toString();

        {
            DefaultTableModel modelCat = new DefaultTableModel();
            String[] Titulos = {"Nº", "C.BARRAS", "PRODUCTO", "MARCA", "UNIDAD", "CATEGORIA", "STOCK",
                "IVA","DESCUENTO", "PROVEEDOR", "R.COMPRA", "P.VENTA", "F.INGRESO", "DESCRIPCION", "RUTA"};
            modelCat.setColumnIdentifiers(Titulos);
            this.tbprod.setModel(modelCat);
            try {

                String ConsultaSQL = "SELECT * FROM tabla_productos WHERE categoria='" + cat + "'";

                String[] Datos = new String[15];

                Statement st = Conexion.conexion().createStatement();
                ResultSet rs = st.executeQuery(ConsultaSQL);
                while (rs.next()) {
                    Datos[0] = rs.getString("codprod");
                    Datos[1] = rs.getString("codbarras");
                    Datos[2] = rs.getString("producto");
                    Datos[3] = rs.getString("marca");
                    Datos[4] = rs.getString("unidad");
                    Datos[5] = rs.getString("categoria");
                    Datos[6] = rs.getString("stock");
                    Datos[7] = rs.getString("iva");
                    Datos[8] = rs.getString("descuento");
                    Datos[9] = rs.getString("proveedor");
                    Datos[10] = rs.getString("pre_compra");
                    Datos[11] = rs.getString("pre_venta");
                    Datos[12] = rs.getString("fechacaducidad");
                    Datos[13] = rs.getString("detalles");
                    Datos[14] = rs.getString("imagen");

                    modelCat.addRow(Datos);
                }
                tbprod.setModel(modelCat);
                lblcant.setText("Consta de " + tbprod.getRowCount() + " productos registrados en su bodega");
                tbprod.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                tbprod.setRowHeight(20);
                tbprod.getColumnModel().getColumn(0).setPreferredWidth(30);
                tbprod.getColumnModel().getColumn(1).setPreferredWidth(135);
                tbprod.getColumnModel().getColumn(2).setPreferredWidth(320);
                tbprod.getColumnModel().getColumn(3).setPreferredWidth(100);
                tbprod.getColumnModel().getColumn(4).setPreferredWidth(80);
                tbprod.getColumnModel().getColumn(5).setPreferredWidth(190);
                tbprod.getColumnModel().getColumn(6).setPreferredWidth(80);
                tbprod.getColumnModel().getColumn(7).setPreferredWidth(45);
                tbprod.getColumnModel().getColumn(8).setPreferredWidth(100);
                tbprod.getColumnModel().getColumn(9).setPreferredWidth(240);
                tbprod.getColumnModel().getColumn(10).setPreferredWidth(80);
                tbprod.getColumnModel().getColumn(11).setPreferredWidth(80);
                tbprod.getColumnModel().getColumn(12).setPreferredWidth(90);
                tbprod.getColumnModel().getColumn(13).setPreferredWidth(350);
                tbprod.getColumnModel().getColumn(14).setPreferredWidth(350);
            } catch (SQLException ex) {
                Logger.getLogger(GUICVisorProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cbCatActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        Productos("");
        txtbuscar.requestFocus();
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void cbProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProveedoresActionPerformed
        String prov = cbProveedores.getSelectedItem().toString();

        {
            DefaultTableModel modelCat = new DefaultTableModel();
            String[] Titulos = {"Nº", "C.BARRAS", "PRODUCTO", "MARCA", "UNIDAD", "CATEGORIA", "STOCK",
                "IVA","DESCUENTO", "PROVEEDOR", "R.COMPRA", "P.VENTA", "F.INGRESO", "DESCRIPCION", "RUTA"};
            modelCat.setColumnIdentifiers(Titulos);
            this.tbprod.setModel(modelCat);
            try {

                String ConsultaSQL = "SELECT * FROM tabla_productos WHERE proveedor='" + prov + "'";

                String[] Datos = new String[15];

                Statement st = Conexion.conexion().createStatement();
                ResultSet rs = st.executeQuery(ConsultaSQL);
                while (rs.next()) {
                    Datos[0] = rs.getString("codprod");
                    Datos[1] = rs.getString("codbarras");
                    Datos[2] = rs.getString("producto");
                    Datos[3] = rs.getString("marca");
                    Datos[4] = rs.getString("unidad");
                    Datos[5] = rs.getString("categoria");
                    Datos[6] = rs.getString("stock");
                    Datos[7] = rs.getString("iva");
                    Datos[8] = rs.getString("descuento");
                    Datos[9] = rs.getString("proveedor");
                    Datos[10] = rs.getString("pre_compra");
                    Datos[11] = rs.getString("pre_venta");
                    Datos[12] = rs.getString("fechacaducidad");
                    Datos[13] = rs.getString("detalles");
                    Datos[14] = rs.getString("imagen");

                    modelCat.addRow(Datos);
                }
                tbprod.setModel(modelCat);
                lblcant.setText("Este proveedor consta de " + tbprod.getRowCount() + " productos registrados en su bodega");
                tbprod.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                tbprod.setRowHeight(20);
                tbprod.getColumnModel().getColumn(0).setPreferredWidth(30);
                tbprod.getColumnModel().getColumn(1).setPreferredWidth(135);
                tbprod.getColumnModel().getColumn(2).setPreferredWidth(320);
                tbprod.getColumnModel().getColumn(3).setPreferredWidth(100);
                tbprod.getColumnModel().getColumn(4).setPreferredWidth(80);
                tbprod.getColumnModel().getColumn(5).setPreferredWidth(190);
                tbprod.getColumnModel().getColumn(6).setPreferredWidth(80);
                tbprod.getColumnModel().getColumn(7).setPreferredWidth(45);
                tbprod.getColumnModel().getColumn(8).setPreferredWidth(100);
                tbprod.getColumnModel().getColumn(9).setPreferredWidth(240);
                tbprod.getColumnModel().getColumn(10).setPreferredWidth(80);
                tbprod.getColumnModel().getColumn(11).setPreferredWidth(80);
                tbprod.getColumnModel().getColumn(12).setPreferredWidth(90);
                tbprod.getColumnModel().getColumn(13).setPreferredWidth(350);
                tbprod.getColumnModel().getColumn(14).setPreferredWidth(350);
            } catch (SQLException ex) {
                Logger.getLogger(GUICVisorProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cbProveedoresActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String consulta = "";
        Date fecha = jDate.getDate();
        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
        String fec = "" + formatofecha.format(fecha);
        consulta = "SELECT * FROM tabla_productos WHERE fechaingreso='" + fec + "'";
        {
            DefaultTableModel modelCat = new DefaultTableModel();
            String[] Titulos = {"Nº", "C.BARRAS", "PRODUCTO", "MARCA", "UNIDAD", "CATEGORIA", "STOCK",
                "IVA","DESCUENTO", "PROVEEDOR", "R.COMPRA", "P.VENTA", "F.INGRESO", "DESCRIPCION", "RUTA"};
            modelCat.setColumnIdentifiers(Titulos);
            this.tbprod.setModel(modelCat);
            try {

                String ConsultaSQL = "SELECT * FROM tabla_productos WHERE fechaingreso='" + fec + "'";

                String[] Datos = new String[15];

                Statement st = Conexion.conexion().createStatement();
                ResultSet rs = st.executeQuery(ConsultaSQL);
                while (rs.next()) {
                    Datos[0] = rs.getString("codprod");
                    Datos[1] = rs.getString("codbarras");
                    Datos[2] = rs.getString("producto");
                    Datos[3] = rs.getString("marca");
                    Datos[4] = rs.getString("unidad");
                    Datos[5] = rs.getString("categoria");
                    Datos[6] = rs.getString("stock");
                    Datos[7] = rs.getString("iva");
                    Datos[8] = rs.getString("descuento");
                    Datos[9] = rs.getString("proveedor");
                    Datos[10] = rs.getString("pre_compra");
                    Datos[11] = rs.getString("pre_venta");
                    Datos[12] = rs.getString("fechacaducidad");
                    Datos[13] = rs.getString("detalles");
                    Datos[14] = rs.getString("imagen");

                    modelCat.addRow(Datos);
                }
                tbprod.setModel(modelCat);
                lblcant.setText("Se han registrado para "+fec+", la cantidad de " + tbprod.getRowCount() + " productos");
                tbprod.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                tbprod.setRowHeight(20);
                tbprod.getColumnModel().getColumn(0).setPreferredWidth(30);
                tbprod.getColumnModel().getColumn(1).setPreferredWidth(135);
                tbprod.getColumnModel().getColumn(2).setPreferredWidth(320);
                tbprod.getColumnModel().getColumn(3).setPreferredWidth(100);
                tbprod.getColumnModel().getColumn(4).setPreferredWidth(80);
                tbprod.getColumnModel().getColumn(5).setPreferredWidth(190);
                tbprod.getColumnModel().getColumn(6).setPreferredWidth(80);
                tbprod.getColumnModel().getColumn(7).setPreferredWidth(45);
                tbprod.getColumnModel().getColumn(8).setPreferredWidth(100);
                tbprod.getColumnModel().getColumn(9).setPreferredWidth(240);
                tbprod.getColumnModel().getColumn(10).setPreferredWidth(80);
                tbprod.getColumnModel().getColumn(11).setPreferredWidth(80);
                tbprod.getColumnModel().getColumn(12).setPreferredWidth(90);
                tbprod.getColumnModel().getColumn(13).setPreferredWidth(350);
                tbprod.getColumnModel().getColumn(14).setPreferredWidth(350);
            } catch (SQLException ex) {
                Logger.getLogger(GUICVisorProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbprodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbprodMouseClicked
        int filas = tbprod.getSelectedRow();
        try {
            String inicio = tbprod.getValueAt(filas, 0).toString();
            String Ruta;
            Ruta = tbprod.getValueAt(filas, 14).toString();
            GUICProductoImagen pac = new GUICProductoImagen();
            pac.setVisible(true);
            ImageIcon img = new ImageIcon(Ruta);
            lblimagenprod.setIcon(new ImageIcon(img.getImage().getScaledInstance(255, 255, Image.SCALE_DEFAULT)));

        } catch (java.lang.Exception e) {
            System.out.println("No hay imagen del producto");
        }
    }//GEN-LAST:event_tbprodMouseClicked

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
            java.util.logging.Logger.getLogger(GUICVisorProductos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUICVisorProductos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUICVisorProductos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUICVisorProductos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUICVisorProductos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbCat;
    private javax.swing.JComboBox cbMarca;
    private javax.swing.JComboBox cbProveedores;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblcant;
    public javax.swing.JTable tbprod;
    private javax.swing.JTextField txtbuscar;
    // End of variables declaration//GEN-END:variables
//    conectar cc = new conectar();
//    Connection cn = cc.conexion();
}
