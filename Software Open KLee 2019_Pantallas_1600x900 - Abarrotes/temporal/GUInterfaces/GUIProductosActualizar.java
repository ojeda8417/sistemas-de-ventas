/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUInterfaces;

import Clases.Conexion;
import Clases.conectar;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;

/**
 *
 * @author RAUL
 */
public class GUIProductosActualizar extends javax.swing.JFrame {

    DefaultTableModel model;
    int contador;
    int cont = 0;
//    conectar cc = new conectar();
//    Connection cn = cc.conexion();

    public GUIProductosActualizar() {
        initComponents();
        Generarnumeracion();
        Productos("");
        setLocationRelativeTo(null);
              

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = con.createStatement();
            ResultSet rs = sent.executeQuery("select * from tabla_proveedores");
            while (rs.next()) {
                this.cbProveedor1.addItem((String) rs.getObject("nombres"));
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
                this.cbMarca1.addItem(rs.getObject("marca"));
            }
            contador++;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = con.createStatement();
            ResultSet rs = sent.executeQuery("select * from tabla_unidades");
            while (rs.next()) {
                this.cbUnidad1.addItem(rs.getObject("unidad"));
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
                this.cbCategoria1.addItem(rs.getObject("categoria"));
            }
            contador++;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    void Barcode() {
        Barcode barcode = null;
        try {
            barcode = BarcodeFactory.createCode39(txtcodbarras1.getText(), true);
        } catch (Exception e) {
        }
        barcode.setDrawingText(false);//false

        barcode.setBarWidth(1);
        barcode.setBarHeight(50);
        BufferedImage image = new BufferedImage(150, 100, BufferedImage.TYPE_INT_ARGB);//300,100
        Graphics2D g = (Graphics2D) image.getGraphics();

        try {
            barcode.draw(g, 5, 20);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de etiqueta");
        }
        ImageIcon icon = new ImageIcon(image);
        lblcode1.setIcon(icon);
    }

    void Generarnumeracion() {
        String SQL = "select max(codprod) from tabla_productos";
        int c = 0;
        int b = 0;
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                c = rs.getInt(1);
            }

            if (c == 0) {
                txtco1.setText("1");
            } else {
                txtco1.setText("" + (c + 1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUIProductosActualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void Productos(String valor) {
        String mostrar = "SELECT * FROM tabla_productos WHERE CONCAT(producto) LIKE '%" + valor + "%'";
        String[] titulos = {"Nº", "C.BARRAS", "PRODUCTO", "MARCA", "UNIDAD", "CATEGORIA", "STOCK", "IVA",
            "DESCUENTO", "PROVEEDOR", "R.COMPRA", "P.VENTA", "F.CADC.", "DETALLES", "IMAGEN"};
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
            tbproducts.setModel(model);
            tbproducts.setAutoResizeMode(tbproducts.AUTO_RESIZE_OFF);
            tbproducts.setRowHeight(18);
            tbproducts.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbproducts.getColumnModel().getColumn(1).setPreferredWidth(105);
            tbproducts.getColumnModel().getColumn(2).setPreferredWidth(320);
            tbproducts.getColumnModel().getColumn(3).setPreferredWidth(100);
            tbproducts.getColumnModel().getColumn(4).setPreferredWidth(80);
            tbproducts.getColumnModel().getColumn(5).setPreferredWidth(200);
            tbproducts.getColumnModel().getColumn(6).setPreferredWidth(80);
            tbproducts.getColumnModel().getColumn(7).setPreferredWidth(35);
            tbproducts.getColumnModel().getColumn(8).setPreferredWidth(100);
            tbproducts.getColumnModel().getColumn(9).setPreferredWidth(240);
            tbproducts.getColumnModel().getColumn(10).setPreferredWidth(80);
            tbproducts.getColumnModel().getColumn(11).setPreferredWidth(80);
            tbproducts.getColumnModel().getColumn(12).setPreferredWidth(90);
            tbproducts.getColumnModel().getColumn(13).setPreferredWidth(360);
            tbproducts.getColumnModel().getColumn(14).setPreferredWidth(360);
        } catch (SQLException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void Marca(String valor) {
        String mostrar = "SELECT * FROM tabla_productos WHERE CONCAT(marca) LIKE '%" + valor + "%'";
        String[] titulos = {"Nº", "C.BARRAS", "PRODUCTO", "MARCA", "UNIDAD", "CATEGORIA", "STOCK", "IVA",
            "DESCUENTO", "PROVEEDOR", "R.COMPRA", "P.VENTA", "F.CADC.", "DETALLES", "IMAGEN"};
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
            tbproducts.setModel(model);
            tbproducts.setAutoResizeMode(tbproducts.AUTO_RESIZE_OFF);
            tbproducts.setRowHeight(18);
            tbproducts.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbproducts.getColumnModel().getColumn(1).setPreferredWidth(105);
            tbproducts.getColumnModel().getColumn(2).setPreferredWidth(320);
            tbproducts.getColumnModel().getColumn(3).setPreferredWidth(100);
            tbproducts.getColumnModel().getColumn(4).setPreferredWidth(80);
            tbproducts.getColumnModel().getColumn(5).setPreferredWidth(200);
            tbproducts.getColumnModel().getColumn(6).setPreferredWidth(80);
            tbproducts.getColumnModel().getColumn(7).setPreferredWidth(35);
            tbproducts.getColumnModel().getColumn(8).setPreferredWidth(100);
            tbproducts.getColumnModel().getColumn(9).setPreferredWidth(240);
            tbproducts.getColumnModel().getColumn(10).setPreferredWidth(80);
            tbproducts.getColumnModel().getColumn(11).setPreferredWidth(80);
            tbproducts.getColumnModel().getColumn(12).setPreferredWidth(90);
            tbproducts.getColumnModel().getColumn(13).setPreferredWidth(360);
            tbproducts.getColumnModel().getColumn(14).setPreferredWidth(360);
        } catch (SQLException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void Categoria(String valor) {
        String mostrar = "SELECT * FROM tabla_productos WHERE CONCAT(categoria) LIKE '%" + valor + "%'";
        String[] titulos = {"Nº", "C.BARRAS", "PRODUCTO", "MARCA", "UNIDAD", "CATEGORIA", "STOCK", "IVA",
            "DESCUENTO", "PROVEEDOR", "R.COMPRA", "P.VENTA", "F.CADC.", "DETALLES", "IMAGEN"};
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
            tbproducts.setModel(model);
            tbproducts.setAutoResizeMode(tbproducts.AUTO_RESIZE_OFF);
            tbproducts.setRowHeight(18);
            tbproducts.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbproducts.getColumnModel().getColumn(1).setPreferredWidth(105);
            tbproducts.getColumnModel().getColumn(2).setPreferredWidth(320);
            tbproducts.getColumnModel().getColumn(3).setPreferredWidth(100);
            tbproducts.getColumnModel().getColumn(4).setPreferredWidth(80);
            tbproducts.getColumnModel().getColumn(5).setPreferredWidth(200);
            tbproducts.getColumnModel().getColumn(6).setPreferredWidth(80);
            tbproducts.getColumnModel().getColumn(7).setPreferredWidth(35);
            tbproducts.getColumnModel().getColumn(8).setPreferredWidth(100);
            tbproducts.getColumnModel().getColumn(9).setPreferredWidth(240);
            tbproducts.getColumnModel().getColumn(10).setPreferredWidth(80);
            tbproducts.getColumnModel().getColumn(11).setPreferredWidth(80);
            tbproducts.getColumnModel().getColumn(12).setPreferredWidth(90);
            tbproducts.getColumnModel().getColumn(13).setPreferredWidth(360);
            tbproducts.getColumnModel().getColumn(14).setPreferredWidth(360);
        } catch (SQLException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void contar() {
        String sql2 = "SELECT count(*) as cont from tabla_productos";
        try {
            int con = 0;
            Statement s = Conexion.conexion().createStatement();
            ResultSet rs = s.executeQuery(sql2);
            while (rs.next()) {
                con = rs.getInt("cont");

                txtrutaimgprodact.setText(String.valueOf(rs.getString("imagen")));
                lblimagen1.setIcon(new ImageIcon(getClass().getResource(rs.getString(13))));
                try {
                    int Fila = tbproducts.getSelectedRow();
                    String Imagen = tbproducts.getValueAt(Fila, 13).toString();
                    ImageIcon img = new ImageIcon(Imagen);
                    lblimagen1.setIcon(new ImageIcon(img.getImage().getScaledInstance(180, 166, Image.SCALE_SMOOTH)));

                } catch (Exception e) {
                    System.out.println("" + e);
                }

            }
            if (con == 0) {
            } else {
                cont = con - 1;
                cargar(cont);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUIProductosActualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * ******************************************************
     */
    void cargar(int limite) {
        String sql = "SELECT * FROM tabla_productos limit " + limite + ",1";
        String imagen_string = null;
        BufferedImage img = null;

        try {
            Statement s = Conexion.conexion().createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                txtco1.setText(String.valueOf(rs.getString("codprod")));
                txtcodbarras1.setText(String.valueOf(rs.getString("codbarras")));
                txtprod1.setText(String.valueOf(rs.getString("producto")));
                cbMarca1.setSelectedItem(String.valueOf(rs.getString("marca")));
                cbUnidad1.setSelectedItem(String.valueOf(rs.getString("unidad")));
                cbCategoria1.setSelectedItem(String.valueOf(rs.getString("categoria")));
                txtstock1.setText(String.valueOf(rs.getString("stock")));
                txtiv1.setText(String.valueOf(rs.getString("iva")));
                txtdesc1.setText(String.valueOf(rs.getString("descuento")));
                cbProveedor1.setSelectedItem(String.valueOf(rs.getString("proveedor")));
                txtprecio1.setText(String.valueOf(rs.getString("pre_compra")));
                txtpvp1.setText(String.valueOf(rs.getString("pre_venta")));
                txtdetalles1.setText(String.valueOf(rs.getString("detalles")));
                txtrutaimgprodact.setText(String.valueOf(rs.getString("imagen")));
                jdFecha1.setDate(Date.valueOf(rs.getString("fechacaducidad")));

                lblimagen1.setText("");
                String Rutas;
                Rutas = txtrutaimgprodact.getText();
                ImageIcon imgs = new ImageIcon(Rutas);
                lblimagen1.setIcon(new ImageIcon(imgs.getImage().getScaledInstance(180, 166, Image.SCALE_DEFAULT)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(GUIProductosActualizar.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void LlenarTxt() {

        Connection cn = null;
        Statement st;
        ResultSet rs = null;

        try {
            if (this.contador <= 0) {
                Class.forName("com.mysql.jdbc.Driver");
                cn = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
                st = cn.createStatement(rs.TYPE_SCROLL_SENSITIVE, rs.CONCUR_READ_ONLY);
                rs = st.executeQuery("SELECT * FROM tabla_productos");
                while (rs.next()) {

                    txtco1.setText(String.valueOf(rs.getString("codprod")));
                    txtcodbarras1.setText(String.valueOf(rs.getString("codbarras")));
                    txtprod1.setText(String.valueOf(rs.getString("producto")));
                    cbMarca1.setSelectedItem(String.valueOf(rs.getString("marca")));
                    cbUnidad1.setSelectedItem(String.valueOf(rs.getString("unidad")));
                    cbCategoria1.setSelectedItem(String.valueOf(rs.getString("categoria")));
                    txtstock1.setText(String.valueOf(rs.getString("stock")));
                    txtiv1.setText(String.valueOf(rs.getString("iva")));
                    txtdesc1.setText(String.valueOf(rs.getString("descuento")));
                    cbProveedor1.setSelectedItem(String.valueOf(rs.getString("proveedor")));
                    txtprecio1.setText(String.valueOf(rs.getString("pre_compra")));
                    txtpvp1.setText(String.valueOf(rs.getString("pre_venta")));
                    txtdetalles1.setText(String.valueOf(rs.getString("detalles")));
                    txtrutaimgprodact.setText(String.valueOf(rs.getString("imagen")));
                    jdFecha1.setDate(Date.valueOf(rs.getString("fechacaducidad")));

                    lblimagen1.setText("");
                    String Ruta;
                    Ruta = txtrutaimgprodact.getText();
                    ImageIcon img = new ImageIcon(Ruta);
                    lblimagen1.setIcon(new ImageIcon(img.getImage().getScaledInstance(180, 166, Image.SCALE_DEFAULT)));

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString());
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtbuscar = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbproducts = new javax.swing.JTable();
        lblcode1 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        lblimagen1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtco1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtcodbarras1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtprod1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbCategoria1 = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        cbMarca1 = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        cbUnidad1 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        txtstock1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtprecio1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtpvp1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtdesc1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jdFecha1 = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        cbProveedor1 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtdetalles1 = new javax.swing.JTextArea();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        txtrutaimgprodact = new javax.swing.JTextField();
        txtiv1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ACTUALIZAR PRODUCTOS");
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/Productos.png")).getImage());
        setResizable(false);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Opciones de busqeda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel1.setOpaque(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Primero.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Anterior.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Siguiente.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Ultimo.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Buscar.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addGap(75, 75, 75)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtbuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)
                        .addComponent(jButton3)
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setOpaque(false);

        tbproducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbproducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbproductsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbproducts);

        lblcode1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/SubirFoto.png"))); // NOI18N
        jButton6.setText("Subir imágen");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Vista.png"))); // NOI18N
        jButton7.setText("Ver código");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        lblimagen1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblimagen1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblimagen1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblimagen1, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(lblcode1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lblimagen1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblcode1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Código:");

        txtco1.setBackground(new java.awt.Color(255, 255, 204));
        txtco1.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Código de barras:");

        txtcodbarras1.setBackground(new java.awt.Color(255, 255, 204));
        txtcodbarras1.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Producto:");

        txtprod1.setBackground(new java.awt.Color(255, 255, 204));
        txtprod1.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Categoría:");

        cbCategoria1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Seleccionar---" }));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Marca:");

        cbMarca1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Seleccionar---" }));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Unidad:");

        cbUnidad1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Seleccionar---" }));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Cantidad:");

        txtstock1.setBackground(new java.awt.Color(255, 255, 204));
        txtstock1.setToolTipText("");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("P. Compra:");

        txtprecio1.setBackground(new java.awt.Color(255, 255, 204));
        txtprecio1.setToolTipText("");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("P.Venta:");

        txtpvp1.setBackground(new java.awt.Color(255, 255, 204));
        txtpvp1.setToolTipText("");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Descuento:");

        txtdesc1.setBackground(new java.awt.Color(255, 255, 204));
        txtdesc1.setToolTipText("");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("F.Cadc:");

        jdFecha1.setToolTipText("");
        jdFecha1.setOpaque(false);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Proveedor:");

        txtdetalles1.setBackground(new java.awt.Color(255, 255, 204));
        txtdetalles1.setColumns(20);
        txtdetalles1.setRows(5);
        txtdetalles1.setToolTipText("");
        txtdetalles1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtdetalles1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(txtdetalles1);

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Actualizar.png"))); // NOI18N
        jButton10.setToolTipText("Actualizar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Regresar.png"))); // NOI18N
        jButton11.setToolTipText("Salir");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        txtrutaimgprodact.setEditable(false);

        txtiv1.setBackground(new java.awt.Color(255, 255, 204));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("IVA:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtco1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcodbarras1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtprod1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtstock1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel14)
                                        .addGap(8, 8, 8)
                                        .addComponent(txtiv1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtprecio1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtpvp1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtdesc1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(cbCategoria1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbMarca1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbUnidad1, 0, 117, Short.MAX_VALUE)
                                    .addComponent(jdFecha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbProveedor1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtrutaimgprodact)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtco1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtcodbarras1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtprod1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(cbCategoria1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(cbUnidad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbMarca1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtstock1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(txtprecio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(txtpvp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(txtdesc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(txtiv1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14))
                    .addComponent(jdFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cbProveedor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtrutaimgprodact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = con.createStatement();
            ResultSet rs = sent.executeQuery("select * from tabla_productos");
            rs.last();
            txtco1.setText(String.valueOf(rs.getString("codprod")));
            txtcodbarras1.setText(String.valueOf(rs.getString("codbarras")));
            txtprod1.setText(String.valueOf(rs.getString("producto")));
            cbMarca1.setSelectedItem(String.valueOf(rs.getString("marca")));
            cbUnidad1.setSelectedItem(String.valueOf(rs.getString("unidad")));
            cbCategoria1.setSelectedItem(String.valueOf(rs.getString("categoria")));
            txtstock1.setText(String.valueOf(rs.getString("stock")));
            txtiv1.setText(String.valueOf(rs.getString("iva")));
            txtdesc1.setText(String.valueOf(rs.getString("descuento")));
            cbProveedor1.setSelectedItem(String.valueOf(rs.getString("proveedor")));
            txtprecio1.setText(String.valueOf(rs.getString("pre_compra")));
            txtpvp1.setText(String.valueOf(rs.getString("pre_venta")));
            txtdetalles1.setText(String.valueOf(rs.getString("detalles")));
            txtrutaimgprodact.setText(String.valueOf(rs.getString("imagen")));
            jdFecha1.setDate(Date.valueOf(rs.getString("fechacaducidad")));
            String Ruta;
            Ruta = txtrutaimgprodact.getText();
            ImageIcon img = new ImageIcon(Ruta);
            lblimagen1.setIcon(new ImageIcon(img.getImage().getScaledInstance(180, 166, Image.SCALE_DEFAULT)));
            Barcode();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString());
            JOptionPane.showMessageDialog(this, "Este es el ultimo registro" + e);
            System.out.println("Este es el ultimo registro");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        cont = cont + 1;
        cargar(cont);
        LlenarTxt();
        Barcode();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (cont == 0) {
            cargar(cont);
        } else if (cont < 0) {
            cargar(cont = 0);
        } else {
            cont = cont - 1;
            cargar(cont);
        }
        Barcode();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = con.createStatement();
            ResultSet rs = sent.executeQuery("select * from tabla_productos");
            rs.first();
            txtco1.setText(String.valueOf(rs.getString("codprod")));
            txtcodbarras1.setText(String.valueOf(rs.getString("codbarras")));
            txtprod1.setText(String.valueOf(rs.getString("producto")));
            cbMarca1.setSelectedItem(String.valueOf(rs.getString("marca")));
            cbUnidad1.setSelectedItem(String.valueOf(rs.getString("unidad")));
            cbCategoria1.setSelectedItem(String.valueOf(rs.getString("categoria")));
            txtstock1.setText(String.valueOf(rs.getString("stock")));
            txtiv1.setText(String.valueOf(rs.getString("iva")));
            txtdesc1.setText(String.valueOf(rs.getString("descuento")));
            cbProveedor1.setSelectedItem(String.valueOf(rs.getString("proveedor")));
            txtprecio1.setText(String.valueOf(rs.getString("pre_compra")));
            txtpvp1.setText(String.valueOf(rs.getString("pre_venta")));
            txtdetalles1.setText(String.valueOf(rs.getString("detalles")));
            txtrutaimgprodact.setText(String.valueOf(rs.getString("imagen")));
            jdFecha1.setDate(Date.valueOf(rs.getString("fechacaducidad")));
            String Ruta;
            Ruta = txtrutaimgprodact.getText();
            ImageIcon img = new ImageIcon(Ruta);
            lblimagen1.setIcon(new ImageIcon(img.getImage().getScaledInstance(180, 166, Image.SCALE_DEFAULT)));
            Barcode();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString());
            JOptionPane.showMessageDialog(this, "Este es el ultimo registro" + e);
            System.out.println("Este es el ultimo registro");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            PreparedStatement pst = Conexion.conexion().prepareStatement("UPDATE tabla_productos SET codbarras='" + txtcodbarras1.getText()
                    + "',producto='" + txtprod1.getText()
                    + "',marca='" + cbMarca1.getSelectedItem()
                    + "',unidad='" + cbUnidad1.getSelectedItem()
                    + "',categoria='" + cbCategoria1.getSelectedItem()
                    + "',stock='" + txtstock1.getText()
                    + "',iva='" + txtiv1.getText()
                    + "',descuento='" + txtdesc1.getText()
                    + "',proveedor='" + cbProveedor1.getSelectedItem()
                    + "',pre_compra='" + txtprecio1.getText()
                    + "',pre_venta='" + txtpvp1.getText()
                    + "',fechacaducidad='" + new java.sql.Date(jdFecha1.getDate().getTime())
                    + "',detalles='" + txtdetalles1.getText()
                    + "',imagen='" + txtrutaimgprodact.getText() + "' WHERE codprod='" + txtco1.getText() + "'");
            JOptionPane.showMessageDialog(null, "Este producto se ha actualizado con éxito!");
            pst.executeUpdate();
            Productos("");

            txtdesc1.setText("0.0");
            txtdetalles1.setText("Ingrese aquí una descripción");
            txtcodbarras1.setText("");
            txtprod1.setText("");
            txtprecio1.setText("0.0");
            txtpvp1.setText("0.0");
            txtrutaimgprodact.setText("");
            txtstock1.setText("0.0");
            txtiv1.setText("0.0");
            cbCategoria1.setSelectedItem("---Seleccionar---");
            cbMarca1.setSelectedItem("---Seleccionar---");
            cbProveedor1.setSelectedItem("---Seleccionar---");
            cbUnidad1.setSelectedItem("---Seleccionar---");
            lblcode1.setText("");
            txtcodbarras1.requestFocus();
            lblimagen1.setText("");
            dispose();

        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Barcode();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void tbproductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbproductsMouseClicked
        int fila = tbproducts.getSelectedRow();
        String cod = tbproducts.getValueAt(fila, 0).toString();
        String cb = tbproducts.getValueAt(fila, 1).toString();
        String pro = tbproducts.getValueAt(fila, 2).toString();
        String mar = tbproducts.getValueAt(fila, 3).toString();
        String un = tbproducts.getValueAt(fila, 4).toString();
        String cat = tbproducts.getValueAt(fila, 5).toString();
        String st = tbproducts.getValueAt(fila, 6).toString();
        String iv = tbproducts.getValueAt(fila, 7).toString();
        String des = tbproducts.getValueAt(fila, 8).toString();
        String prov = tbproducts.getValueAt(fila, 9).toString();
        String pc = tbproducts.getValueAt(fila, 10).toString();
        String pv = tbproducts.getValueAt(fila, 11).toString();
        String fec = tbproducts.getValueAt(fila, 12).toString();
        String det = tbproducts.getValueAt(fila, 13).toString();
        String ruta = tbproducts.getValueAt(fila, 14).toString();

        try {
            lblimagen1.setText("");
            lblimagen1.setText("");
            int Fila = tbproducts.getSelectedRow();
            String Imagen = tbproducts.getValueAt(Fila, 14).toString();
            ImageIcon img = new ImageIcon(Imagen);
            lblimagen1.setIcon(new ImageIcon(img.getImage().getScaledInstance(180, 166, Image.SCALE_SMOOTH)));

        } catch (Exception e) {
            System.out.println("" + e);
        }
        txtco1.setText(cod);
        txtcodbarras1.setText(cb);
        txtprod1.setText(pro);
        cbMarca1.setSelectedItem(mar);
        cbUnidad1.setSelectedItem(un);
        cbCategoria1.setSelectedItem(cat);
        txtstock1.setText(st);
        txtiv1.setText(iv);
        txtdesc1.setText(des);
        cbProveedor1.setSelectedItem(prov);
        jdFecha1.setDate(Date.valueOf(tbproducts.getValueAt(fila, 12).toString()));
        txtprecio1.setText(pc);
        txtpvp1.setText(pv);
        txtdetalles1.setText(det);
        txtrutaimgprodact.setText(ruta);
    }//GEN-LAST:event_tbproductsMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Productos("");
        Marca("");
        Categoria("");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void txtdetalles1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtdetalles1MouseClicked
        txtdetalles1.setText("");
    }//GEN-LAST:event_txtdetalles1MouseClicked

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        if (jRadioButton1.isSelected() == true) {
            txtbuscar.setBackground(Color.yellow);
            txtbuscar.requestFocus();
            Productos(txtbuscar.getText());
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        if (jRadioButton2.isSelected() == true) {
            txtbuscar.setBackground(Color.cyan);
            txtbuscar.requestFocus();
            Marca(txtbuscar.getText());
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        if (jRadioButton3.isSelected() == true) {
            txtbuscar.setBackground(Color.ORANGE);
            txtbuscar.requestFocus();
            Categoria(txtbuscar.getText());
        }
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {

            JFileChooser Chooser = new JFileChooser();
            Chooser.setCurrentDirectory(new File("D://FotosVentas"));
            Chooser.setAcceptAllFileFilterUsed(false);
            FileFilter Filtro1 = new FileNameExtensionFilter("JPG file", "jpg");
            FileFilter Filtro2 = new FileNameExtensionFilter("PNG file", "png");
            Chooser.setFileFilter(Filtro1);
            Chooser.addChoosableFileFilter(Filtro2);
            Chooser.showOpenDialog(this);
            File Archivo = Chooser.getSelectedFile();
            String FileName = Archivo.getAbsolutePath();
            txtrutaimgprodact.setText(FileName.replace("\\", "//"));
            String Ruta;
            Ruta = txtrutaimgprodact.getText();
            ImageIcon img = new ImageIcon(Ruta);
            lblimagen1.setIcon(new ImageIcon(img.getImage().getScaledInstance(180, 166, Image.SCALE_DEFAULT)));
        } catch (java.lang.Exception e) {
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
        Productos(txtbuscar.getText());
        Marca(txtbuscar.getText());
        Categoria(txtbuscar.getText());
    }//GEN-LAST:event_txtbuscarKeyReleased

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
            java.util.logging.Logger.getLogger(GUIProductosActualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIProductosActualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIProductosActualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIProductosActualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIProductosActualizar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    public static javax.swing.JComboBox cbCategoria1;
    public static javax.swing.JComboBox cbMarca1;
    public static javax.swing.JComboBox cbProveedor1;
    public static javax.swing.JComboBox cbUnidad1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static com.toedter.calendar.JDateChooser jdFecha1;
    private javax.swing.JLabel lblcode1;
    public static javax.swing.JLabel lblimagen1;
    private javax.swing.JTable tbproducts;
    private javax.swing.JTextField txtbuscar;
    public static javax.swing.JTextField txtco1;
    public static javax.swing.JTextField txtcodbarras1;
    public static javax.swing.JTextField txtdesc1;
    public static javax.swing.JTextArea txtdetalles1;
    public static javax.swing.JTextField txtiv1;
    public static javax.swing.JTextField txtprecio1;
    public static javax.swing.JTextField txtprod1;
    public static javax.swing.JTextField txtpvp1;
    public static javax.swing.JTextField txtrutaimgprodact;
    public static javax.swing.JTextField txtstock1;
    // End of variables declaration//GEN-END:variables
}
