/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIConsultas;

import Clases.ColorColumnCompras;
import Clases.Conexion;
import Clases.conectar;
import GUInterfaces.GUIMenuPrincipal;
import static GUInterfaces.GUIMenuPrincipal.lblimgcompras;
import static GUInterfaces.GUIMenuPrincipal.lblsubcomp;
import static GUInterfaces.GUIMenuPrincipal.tabla_compras;
import java.awt.Image;
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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario-Pc
 */
public class GUICProductosCompras extends javax.swing.JFrame {

//    conectar cc = new conectar();
//    Connection cn = cc.conexion();
    DefaultTableModel model;
    int contador;

    public GUICProductosCompras() {
        initComponents();
        Productos("");
        setLocationRelativeTo(null);

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = con.createStatement();
            ResultSet rs = sent.executeQuery("select * from tabla_proveedores");

            while (rs.next()) {
                this.cbProveedores.addItem((String) rs.getObject("nombres"));

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
                this.cbMarca.addItem((String) rs.getObject("marca"));

            }
            contador++;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = con.createStatement();
            ResultSet rs = sent.executeQuery("select * from tabla_categorias");

            while (rs.next()) {
                this.cbCategoria.addItem((String) rs.getObject("categoria"));

            }
            contador++;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }

    void Productos(String valor) {
        String mostrar = "SELECT * FROM tabla_productos WHERE CONCAT(producto) LIKE '%" + valor + "%'";
        String[] titulos = {"Nº", "C.BARRAS", "PRODUCTO", "MARCA", "UNIDAD", "CATEGORIA", "STOCK", "IVA",
            "DESCUENTO", "PROVEEDOR", "R.COMPRA", "P.VENTA", "F.CADUC.", "DESCRIPCION", "RUTA"};
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
                lblinforme.setText("Lista general de productos para la compra " + tabla_conprod.getRowCount() + " registrados en el sistema");

            }
            tabla_conprod.setModel(model);
            lblinforme.setText("Lista general de productos para la compra " + tabla_conprod.getRowCount() + " registrados en el sistema");
            tabla_conprod.setAutoResizeMode(tabla_conprod.AUTO_RESIZE_OFF);
            tabla_conprod.setRowHeight(20);
            tabla_conprod.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabla_conprod.getColumnModel().getColumn(1).setPreferredWidth(110);
            tabla_conprod.getColumnModel().getColumn(2).setPreferredWidth(320);
            tabla_conprod.getColumnModel().getColumn(3).setPreferredWidth(100);
            tabla_conprod.getColumnModel().getColumn(4).setPreferredWidth(80);
            tabla_conprod.getColumnModel().getColumn(5).setPreferredWidth(140);
            tabla_conprod.getColumnModel().getColumn(6).setPreferredWidth(80);
            tabla_conprod.getColumnModel().getColumn(7).setPreferredWidth(50);
            tabla_conprod.getColumnModel().getColumn(8).setPreferredWidth(100);
            tabla_conprod.getColumnModel().getColumn(9).setPreferredWidth(240);
            tabla_conprod.getColumnModel().getColumn(10).setPreferredWidth(80);
            tabla_conprod.getColumnModel().getColumn(11).setPreferredWidth(80);
            tabla_conprod.getColumnModel().getColumn(12).setPreferredWidth(90);
            tabla_conprod.getColumnModel().getColumn(13).setPreferredWidth(320);
            tabla_conprod.getColumnModel().getColumn(14).setPreferredWidth(320);
        } catch (SQLException ex) {
            Logger.getLogger(GUICProductosCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void calcularcomp() {
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
        double imp = 0.0;

        for (int i = 0; i < GUIMenuPrincipal.tabla_compras.getRowCount(); i++) {
            pre = GUIMenuPrincipal.tabla_compras.getValueAt(i, 3).toString();
            can = GUIMenuPrincipal.tabla_compras.getValueAt(i, 4).toString();
            precio = Double.parseDouble(pre);
            cantidad = Double.parseDouble(can);

            if (GUIMenuPrincipal.tabla_compras.getValueAt(i, 6) == null) {
                imp = precio * cantidad;
            } else {
                desc = GUIMenuPrincipal.tabla_compras.getValueAt(i, 6).toString();
                descuentos = Double.parseDouble(desc);
                impParcial = precio * cantidad;
                desct = (precio * descuentos) * cantidad;
                imp = impParcial - desct;
            }

            subtotal = subtotal + imp;
            iva = subtotal * 0.12;
            total = subtotal + iva;
            //COLUMAN 5 DONDE ESTA EL TOTAL
            GUIMenuPrincipal.tabla_compras.setValueAt(String.format("%.0f",Math.rint(imp * 100) / 100), i, 7);

        }
        GUIMenuPrincipal.lblsubcomp.setText(String.format("%.0f", Math.rint(subtotal * 100) / 100));
        GUIMenuPrincipal.lblivacomp.setText( String.format("%.0f", Math.rint(total * 100) / 100));
        GUIMenuPrincipal.txtTotalComp.setText( String.format("%.0f", Math.rint(total * 100) / 100));

    }

    String comparar(String cod) {
        String cant = "";
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tabla_productos WHERE codbarras='" + cod + "'");
            while (rs.next()) {
                cant = rs.getString(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GUICProductosCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cant;
    }

    public void RedondearSubtotalComp() {
        double valor = Double.parseDouble(GUIMenuPrincipal.lblsubcomp.getText());
        String val = valor + "";
        BigDecimal big = new BigDecimal(val);
        big = big.setScale(2, RoundingMode.HALF_UP);
        lblsubcomp.setText(String.format("%.0f", big));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtbuscar = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        cbMarca = new javax.swing.JComboBox<>();
        cbCategoria = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        cbProveedores = new javax.swing.JComboBox();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_conprod = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblinforme = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BUSQUEDA DE PRODUCTOS EN GENERAL");
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/Buscar.png")).getImage());
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Opciones de busqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel1.setOpaque(false);

        txtbuscar.setText("Ingrese nombre del producto");
        txtbuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtbuscarMouseClicked(evt);
            }
        });
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton1.setText("Producto");
        jRadioButton1.setOpaque(false);
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton2.setText("Marca");
        jRadioButton2.setOpaque(false);
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton3.setText("Categoría");
        jRadioButton3.setOpaque(false);
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        cbMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Seleccionar---" }));
        cbMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMarcaActionPerformed(evt);
            }
        });

        cbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Seleccionar---" }));
        cbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCategoriaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Proveedor:");

        cbProveedores.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Seleccionar---" }));
        cbProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProveedoresActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Stock.png"))); // NOI18N
        jButton4.setText("Productos en stock");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Buscar.png"))); // NOI18N
        jButton1.setText("Consultar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Regresar2.png"))); // NOI18N
        jButton2.setText("Salir");
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jRadioButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(cbMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbCategoria, 0, 206, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton2)
                    .addComponent(cbMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton3)
                    .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabla_conprod.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_conprod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_conprodMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabla_conprodMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_conprod);

        jPanel4.setBackground(new java.awt.Color(0, 153, 255));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText(" LOCALIZACIÓN GENERAL DE PRODUCTOS A COMPRAR DE BAJO STOCK");
        jPanel4.add(jLabel4);

        lblinforme.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblinforme.setForeground(new java.awt.Color(0, 51, 204));
        lblinforme.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblinforme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblinforme, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Productos("");
        lblinforme.setText("Lista general de productos para la compra " + tabla_conprod.getRowCount() + " registrados en el sistema");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tabla_conprodMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_conprodMousePressed

    }//GEN-LAST:event_tabla_conprodMousePressed

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
        Productos(txtbuscar.getText());
    }//GEN-LAST:event_txtbuscarKeyReleased

    private void txtbuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtbuscarMouseClicked
        txtbuscar.setText("");
    }//GEN-LAST:event_txtbuscarMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String consults = "";
        consults = ("SELECT `codprod`, `codbarras`, `producto`, `marca`, `unidad`, `categoria`, `stock`, `iva`,"
                + "`descuento`,`proveedor`,`pre_compra`,`pre_venta`,`fechacaducidad`,`detalles`,`imagen` FROM `tabla_productos` WHERE stock <=10");
        DefaultTableModel tabla = new DefaultTableModel();
        String[] titulos = {"Nº", "C.BARRAS", "PRODUCTO", "MARCA", "UNIDAD", "CATEGORIA", "STOCK", "IVA",
            "DESCUENTO", "PROVEEDOR", "R.COMPRA", "P.VENTA", "F.INGRESO", "DESCRIPCION", "RUTA"};
        tabla.setColumnIdentifiers(titulos);
        this.tabla_conprod.setModel(tabla);

        String[] Datos = new String[15];
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consults);
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

                tabla.addRow(Datos);
                lblinforme.setText("Consta de " + tabla_conprod.getRowCount() + " productos de bajo stock en su bodega");
                tabla_conprod.setAutoResizeMode(tabla_conprod.AUTO_RESIZE_OFF);
                tabla_conprod.setRowHeight(20);
                tabla_conprod.getColumnModel().getColumn(0).setPreferredWidth(50);
                tabla_conprod.getColumnModel().getColumn(1).setPreferredWidth(110);
                tabla_conprod.getColumnModel().getColumn(2).setPreferredWidth(320);
                tabla_conprod.getColumnModel().getColumn(3).setPreferredWidth(100);
                tabla_conprod.getColumnModel().getColumn(4).setPreferredWidth(80);
                tabla_conprod.getColumnModel().getColumn(5).setPreferredWidth(140);
                tabla_conprod.getColumnModel().getColumn(6).setPreferredWidth(80);
                tabla_conprod.getColumnModel().getColumn(7).setPreferredWidth(50);
                tabla_conprod.getColumnModel().getColumn(8).setPreferredWidth(100);
                tabla_conprod.getColumnModel().getColumn(9).setPreferredWidth(240);
                tabla_conprod.getColumnModel().getColumn(10).setPreferredWidth(80);
                tabla_conprod.getColumnModel().getColumn(11).setPreferredWidth(80);
                tabla_conprod.getColumnModel().getColumn(12).setPreferredWidth(90);
                tabla_conprod.getColumnModel().getColumn(13).setPreferredWidth(320);
                tabla_conprod.getColumnModel().getColumn(14).setPreferredWidth(320);

            }
        } catch (SQLException ex) {
            Logger.getLogger(GUICProductosCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoriaActionPerformed
        String cat = cbCategoria.getSelectedItem().toString();

        {
            DefaultTableModel modelCat = new DefaultTableModel();
            String[] Titulos = {"Nº", "C.BARRAS", "PRODUCTO", "MARCA", "UNIDAD", "CATEGORIA", "STOCK", "IVA",
                "DESCUENTO", "PROVEEDOR", "R.COMPRA", "P.VENTA", "F.CADUC.", "DESCRIPCION", "RUTA"};
            modelCat.setColumnIdentifiers(Titulos);
            this.tabla_conprod.setModel(modelCat);
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
                tabla_conprod.setModel(modelCat);
                lblinforme.setText("Consta de " + tabla_conprod.getRowCount() + " productos registrados en bodega con la categoría " + cat);
                tabla_conprod.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                tabla_conprod.setRowHeight(20);
                tabla_conprod.getColumnModel().getColumn(0).setPreferredWidth(50);
                tabla_conprod.getColumnModel().getColumn(1).setPreferredWidth(110);
                tabla_conprod.getColumnModel().getColumn(2).setPreferredWidth(320);
                tabla_conprod.getColumnModel().getColumn(3).setPreferredWidth(100);
                tabla_conprod.getColumnModel().getColumn(4).setPreferredWidth(80);
                tabla_conprod.getColumnModel().getColumn(5).setPreferredWidth(140);
                tabla_conprod.getColumnModel().getColumn(6).setPreferredWidth(80);
                tabla_conprod.getColumnModel().getColumn(7).setPreferredWidth(50);
                tabla_conprod.getColumnModel().getColumn(8).setPreferredWidth(100);
                tabla_conprod.getColumnModel().getColumn(9).setPreferredWidth(240);
                tabla_conprod.getColumnModel().getColumn(10).setPreferredWidth(80);
                tabla_conprod.getColumnModel().getColumn(11).setPreferredWidth(80);
                tabla_conprod.getColumnModel().getColumn(12).setPreferredWidth(90);
                tabla_conprod.getColumnModel().getColumn(13).setPreferredWidth(320);
                tabla_conprod.getColumnModel().getColumn(14).setPreferredWidth(320);

            } catch (SQLException ex) {
                Logger.getLogger(GUICProductosCompras.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cbCategoriaActionPerformed

    private void cbMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMarcaActionPerformed
        String mar = cbMarca.getSelectedItem().toString();

        {
            DefaultTableModel modelCat = new DefaultTableModel();
            String[] Titulos = {"Nº", "C.BARRAS", "PRODUCTO", "MARCA", "UNIDAD", "CATEGORIA", "STOCK", "IVA",
                "DESCUENTO", "PROVEEDOR", "R.COMPRA", "P.VENTA", "F.CADUC.", "DESCRIPCION", "RUTA"};
            modelCat.setColumnIdentifiers(Titulos);
            this.tabla_conprod.setModel(modelCat);
            try {

                String ConsultaSQL = "SELECT * FROM tabla_productos WHERE marca='" + mar + "'";

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
                tabla_conprod.setModel(modelCat);
                lblinforme.setText("Consta de " + tabla_conprod.getRowCount() + " productos registrados en bodega con la marca " + mar);
                tabla_conprod.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                tabla_conprod.setRowHeight(20);
                tabla_conprod.getColumnModel().getColumn(0).setPreferredWidth(50);
                tabla_conprod.getColumnModel().getColumn(1).setPreferredWidth(110);
                tabla_conprod.getColumnModel().getColumn(2).setPreferredWidth(320);
                tabla_conprod.getColumnModel().getColumn(3).setPreferredWidth(100);
                tabla_conprod.getColumnModel().getColumn(4).setPreferredWidth(80);
                tabla_conprod.getColumnModel().getColumn(5).setPreferredWidth(140);
                tabla_conprod.getColumnModel().getColumn(6).setPreferredWidth(80);
                tabla_conprod.getColumnModel().getColumn(7).setPreferredWidth(50);
                tabla_conprod.getColumnModel().getColumn(8).setPreferredWidth(100);
                tabla_conprod.getColumnModel().getColumn(9).setPreferredWidth(240);
                tabla_conprod.getColumnModel().getColumn(10).setPreferredWidth(80);
                tabla_conprod.getColumnModel().getColumn(11).setPreferredWidth(80);
                tabla_conprod.getColumnModel().getColumn(12).setPreferredWidth(90);
                tabla_conprod.getColumnModel().getColumn(13).setPreferredWidth(320);
                tabla_conprod.getColumnModel().getColumn(14).setPreferredWidth(320);
            } catch (SQLException ex) {
                Logger.getLogger(GUICProductosCompras.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cbMarcaActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        Productos("");
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void tabla_conprodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_conprodMouseClicked
        if (evt.getClickCount() == 1) {
            try {

                DefaultTableModel tabladet = (DefaultTableModel) GUIMenuPrincipal.tabla_compras.getModel();
                String[] dato = new String[7];

                int fila = tabla_conprod.getSelectedRow();

                String codins = tabla_conprod.getValueAt(fila, 1).toString();
                String desins = tabla_conprod.getValueAt(fila, 2).toString();
                String preins = tabla_conprod.getValueAt(fila, 10).toString();
                String stock = tabla_conprod.getValueAt(fila, 6).toString();
                String desct = tabla_conprod.getValueAt(fila, 8).toString();

                int c = 0;
                int j = 0;

                String Imagen = tabla_conprod.getValueAt(fila, 14).toString();
                ImageIcon img = new ImageIcon(Imagen);
                GUIMenuPrincipal.lblimgcompras.setIcon(new ImageIcon(img.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT)));
                lblimgcompras.setVisible(true);
                
                int comp = Integer.parseInt(comparar(codins));
                
                String cantidadBalanza= GUIMenuPrincipal.txtBascula.getText();

                String cant="";
                if(!cantidadBalanza.equals("") && !cantidadBalanza.equals("0") && !cantidadBalanza.equals("00.00") && !cantidadBalanza.equals("0.00") && !cantidadBalanza.equals("0.0")){ 
                         cant = JOptionPane.showInputDialog(null, "Introduzca la cantidad solicitada", "Solicitud", JOptionPane.QUESTION_MESSAGE,null,null, cantidadBalanza).toString();
                }else{
                        cant = JOptionPane.showInputDialog(null, "Introduzca la cantidad solicitada", "Solicitud", JOptionPane.PLAIN_MESSAGE);
                }if ((cant.equals("")) || (cant.equals("0"))) {
                  JOptionPane.showMessageDialog(this, "Debe ingresar algun valor mayor que 0", "MAFAKAFER", JOptionPane.PLAIN_MESSAGE);
                } else {

                    {
                        for (int i = 0; i < GUIMenuPrincipal.tabla_compras.getRowCount(); i++) {
                            Object com = GUIMenuPrincipal.tabla_compras.getValueAt(i, 0);
                            if (codins.equals(com)) {
                                j = i;
                                c = c + 1;
                            }
                        }
                        if (c == 0) {
                            dato[0] = codins;
                            dato[1] = desins;
                            dato[3] = String.format("%.0f",Double.parseDouble(preins));
                            dato[2] = stock;
                            dato[4] = cant;
                            dato[5] = "0.0";
                            dato[6] = desct;

                            tabladet.addRow(dato);
                            GUIMenuPrincipal.tabla_compras.setRowHeight(30);
                            GUIMenuPrincipal.tabla_compras.setModel(tabladet);

                            ColorColumnCompras ft = new ColorColumnCompras();
                            tabla_compras.setDefaultRenderer(Object.class, ft);

                            calcularcomp();
                            RedondearSubtotalComp();
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_tabla_conprodMouseClicked

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void cbProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProveedoresActionPerformed

        String proveedor = cbProveedores.getSelectedItem().toString();
        String opc = cbProveedores.getSelectedItem().toString();
        String prov;
        prov = proveedor;
        cbProveedores.setSelectedItem(proveedor);
        GUIMenuPrincipal.cbProveedor.setSelectedItem(prov);
        {
            DefaultTableModel modelCat = new DefaultTableModel();
            String[] Titulos = {"Nº", "C.BARRAS", "PRODUCTO", "MARCA", "UNIDAD", "CATEGORIA", "STOCK", "IVA",
                "DESCUENTO", "PROVEEDOR", "R.COMPRA", "P.VENTA", "F.CADUC.", "DESCRIPCION", "RUTA"};
            modelCat.setColumnIdentifiers(Titulos);
            this.tabla_conprod.setModel(modelCat);
            try {

                String ConsultaSQL = "SELECT * FROM tabla_productos WHERE proveedor='" + proveedor + "'";

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
                tabla_conprod.setModel(modelCat);
                lblinforme.setText("Consta de " + tabla_conprod.getRowCount() + " productos registrados en bodega por el proveedor " + proveedor);
                tabla_conprod.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                tabla_conprod.setRowHeight(20);
                tabla_conprod.getColumnModel().getColumn(0).setPreferredWidth(50);
                tabla_conprod.getColumnModel().getColumn(1).setPreferredWidth(110);
                tabla_conprod.getColumnModel().getColumn(2).setPreferredWidth(320);
                tabla_conprod.getColumnModel().getColumn(3).setPreferredWidth(100);
                tabla_conprod.getColumnModel().getColumn(4).setPreferredWidth(80);
                tabla_conprod.getColumnModel().getColumn(5).setPreferredWidth(140);
                tabla_conprod.getColumnModel().getColumn(6).setPreferredWidth(80);
                tabla_conprod.getColumnModel().getColumn(7).setPreferredWidth(50);
                tabla_conprod.getColumnModel().getColumn(8).setPreferredWidth(100);
                tabla_conprod.getColumnModel().getColumn(9).setPreferredWidth(240);
                tabla_conprod.getColumnModel().getColumn(10).setPreferredWidth(80);
                tabla_conprod.getColumnModel().getColumn(11).setPreferredWidth(80);
                tabla_conprod.getColumnModel().getColumn(12).setPreferredWidth(90);
                tabla_conprod.getColumnModel().getColumn(13).setPreferredWidth(320);
                tabla_conprod.getColumnModel().getColumn(14).setPreferredWidth(320);
            } catch (SQLException ex) {
                Logger.getLogger(GUICProductosCompras.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cbProveedoresActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(GUICProductosCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUICProductosCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUICProductosCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUICProductosCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUICProductosCompras().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbCategoria;
    private javax.swing.JComboBox<String> cbMarca;
    private javax.swing.JComboBox cbProveedores;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblinforme;
    public static javax.swing.JTable tabla_conprod;
    private javax.swing.JTextField txtbuscar;
    // End of variables declaration//GEN-END:variables

}
