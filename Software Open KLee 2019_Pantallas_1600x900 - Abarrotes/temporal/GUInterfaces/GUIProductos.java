/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUInterfaces;

import Clases.Conexion;
import Clases.Redondeo;
import Clases.conectar;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;

/**
 *
 * @author Netfex
 */
public class GUIProductos extends javax.swing.JFrame {

    DefaultTableModel model;
    int contador;
    int cont = 0;
//    conectar cc = new conectar();
//    Connection cn = cc.conexion();

    public GUIProductos() {
        initComponents();
        Generarnumeracion();
        Productos("");
        txtrutaimgprod.setVisible(false);
        setLocationRelativeTo(null);

        barc();
        Barcode();
        txtdesc.setText("0.0");
        txtdetalles.setText("Ingrese aquí una descripción");
        txtprod.setText("");
        txtpreciocompra.setText("0.0");
        txtpvp.setText("0.0");
        txtrutaimgprod.setText("");
        txtstock.setText("0");
        txtiva.setText("0.0");
        cbCategoria.setSelectedItem("---Seleccionar---");
        cbMarca.setSelectedItem("---Seleccionar---");
        cbProveedor.setSelectedItem("---Seleccionar---");
        cbUnidad.setSelectedItem("---Seleccionar---");
        lblcode.setText("");
        txtprod.requestFocus();
        lblimagen.setText("");
        this.txtiva.setEnabled(false);

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = con.createStatement();
            ResultSet rs = sent.executeQuery("select * from tabla_proveedores");
            while (rs.next()) {
                this.cbProveedor.addItem((String) rs.getObject("nombres"));
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
            ResultSet rs = sent.executeQuery("select * from tabla_unidades");
            while (rs.next()) {
                this.cbUnidad.addItem(rs.getObject("unidad"));
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
                this.cbCategoria.addItem(rs.getObject("categoria"));
            }
            contador++;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        setearFecha();
    }

    public void setearFecha() {
        //SETEO LA FECHA ACTUAL

        try {
            Calendar c2 = new GregorianCalendar();
            String dateValue = "1111-11-11";
            java.util.Date dates = new SimpleDateFormat("yyyy-MM-dd").parse(dateValue);
            jdFechaing.setDate(dates);
        } catch (Exception e) {

        }
    }

    void Barcode() {
        Barcode barcode = null;
        try {
            barcode = BarcodeFactory.createCode39(txtcodbarras.getText(), true);
        } catch (Exception e) {
        }
        barcode.setDrawingText(true);

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
        lblcode.setIcon(icon);
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
                txtcodproduct.setText("1");
            } else {
                txtcodproduct.setText("" + (c + 1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUIProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void Productos(String valor) {
        String mostrar = "SELECT * FROM tabla_productos WHERE CONCAT(producto) LIKE '%" + valor + "%'";
        String[] titulos = {"Nº", "C.BARRAS", "PRODUCTO", "MARCA", "UNIDAD", "CATEGORIA", "STOCK",
            "IVA", "DESCUENTO", "PROVEEDOR", "R.COMPRA", "P.VENTA", "F.CADC.", "DESCRIPCION", "RUTA"};
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
//            lblcant.setText("Consta de " + tbproductos.getRowCount() + " productos registradas en su sistema");
            tbproducts.setAutoResizeMode(tbproducts.AUTO_RESIZE_OFF);
            tbproducts.setRowHeight(20);
            tbproducts.getColumnModel().getColumn(0).setPreferredWidth(30);
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
            tbproducts.getColumnModel().getColumn(13).setPreferredWidth(320);
            tbproducts.getColumnModel().getColumn(14).setPreferredWidth(320);
        } catch (SQLException ex) {
            Logger.getLogger(GUIProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void Marca(String valor) {
        String mostrar = "SELECT * FROM tabla_productos WHERE CONCAT(marca) LIKE '%" + valor + "%'";
        String[] titulos = {"Nº", "C.BARRAS", "PRODUCTO", "MARCA", "UNIDAD", "CATEGORIA", "STOCK",
            "IVA", "DESCUENTO", "PROVEEDOR", "R.COMPRA", "P.VENTA", "F.CADC.", "DESCRIPCION", "RUTA"};
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
//            lblcant.setText("Consta de " + tbproductos.getRowCount() + " productos registradas en su sistema");
            tbproducts.setAutoResizeMode(tbproducts.AUTO_RESIZE_OFF);
            tbproducts.setRowHeight(20);
            tbproducts.getColumnModel().getColumn(0).setPreferredWidth(30);
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
            tbproducts.getColumnModel().getColumn(13).setPreferredWidth(320);
            tbproducts.getColumnModel().getColumn(14).setPreferredWidth(320);
        } catch (SQLException ex) {
            Logger.getLogger(GUIProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void Categoria(String valor) {
        String mostrar = "SELECT * FROM tabla_productos WHERE CONCAT(categoria) LIKE '%" + valor + "%'";
        String[] titulos = {"Nº", "C.BARRAS", "PRODUCTO", "MARCA", "UNIDAD", "CATEGORIA", "STOCK",
            "IVA", "DESCUENTO", "PROVEEDOR", "R.COMPRA", "P.VENTA", "F.CADC.", "DESCRIPCION", "RUTA"};
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
//            lblcant.setText("Consta de " + tbproductos.getRowCount() + " productos registradas en su sistema");
            tbproducts.setAutoResizeMode(tbproducts.AUTO_RESIZE_OFF);
            tbproducts.setRowHeight(20);
            tbproducts.getColumnModel().getColumn(0).setPreferredWidth(30);
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
            tbproducts.getColumnModel().getColumn(13).setPreferredWidth(320);
            tbproducts.getColumnModel().getColumn(14).setPreferredWidth(320);
        } catch (SQLException ex) {
            Logger.getLogger(GUIProductos.class.getName()).log(Level.SEVERE, null, ex);
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

                txtrutaimgprod.setText(String.valueOf(rs.getString("imagen")));
                lblimagen.setIcon(new ImageIcon(getClass().getResource(rs.getString(14))));
                try {
                    int Fila = tbproducts.getSelectedRow();
                    String Imagen = tbproducts.getValueAt(Fila, 14).toString();
                    ImageIcon img = new ImageIcon(Imagen);
                    lblimagen.setIcon(new ImageIcon(img.getImage().getScaledInstance(180, 166, Image.SCALE_SMOOTH)));

                } catch (Exception e) {
                    System.out.println("No hay imagen" + e);
                }

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
        String sql = "SELECT * FROM tabla_productos limit " + limite + ",1";
        String imagen_string = null;
        BufferedImage img = null;

        try {
            Statement s = Conexion.conexion().createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                txtcodproduct.setText(String.valueOf(rs.getString("codprod")));
                txtcodbarras.setText(String.valueOf(rs.getString("codbarras")));
                txtprod.setText(String.valueOf(rs.getString("producto")));
                cbMarca.setSelectedItem(String.valueOf(rs.getString("marca")));
                cbUnidad.setSelectedItem(String.valueOf(rs.getString("unidad")));
                cbCategoria.setSelectedItem(String.valueOf(rs.getString("categoria")));
                txtstock.setText(String.valueOf(rs.getString("stock")));
                txtiva.setText(String.valueOf(rs.getString("iva")));
                txtdesc.setText(String.valueOf(rs.getString("descuento")));
                cbProveedor.setSelectedItem(String.valueOf(rs.getString("proveedor")));
                txtpreciocompra.setText(String.valueOf(rs.getString("pre_compra")));
                txtpvp.setText(String.valueOf(rs.getString("pre_venta")));
                txtdetalles.setText(String.valueOf(rs.getString("detalles")));
                txtrutaimgprod.setText(String.valueOf(rs.getString("imagen")));
                jdFechaing.setDate(Date.valueOf(rs.getString("fechacaducidad")));

                lblimagen.setText("No Imagen");
                String Rutas;
                Rutas = txtrutaimgprod.getText();
                ImageIcon imgs = new ImageIcon(Rutas);
                lblimagen.setIcon(new ImageIcon(imgs.getImage().getScaledInstance(180, 166, Image.SCALE_DEFAULT)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(GUIProductos.class.getName()).log(Level.SEVERE, null, ex);

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

                    txtcodproduct.setText(String.valueOf(rs.getString("codprod")));
                    txtcodbarras.setText(String.valueOf(rs.getString("codbarras")));
                    txtprod.setText(String.valueOf(rs.getString("producto")));
                    cbMarca.setSelectedItem(String.valueOf(rs.getString("marca")));
                    cbUnidad.setSelectedItem(String.valueOf(rs.getString("unidad")));
                    cbCategoria.setSelectedItem(String.valueOf(rs.getString("categoria")));
                    txtstock.setText(String.valueOf(rs.getString("stock")));
                    txtiva.setText(String.valueOf(rs.getString("iva")));
                    txtdesc.setText(String.valueOf(rs.getString("descuento")));
                    cbProveedor.setSelectedItem(String.valueOf(rs.getString("proveedor")));
                    txtpreciocompra.setText(String.valueOf(rs.getString("pre_compra")));
                    txtpvp.setText(String.valueOf(rs.getString("pre_venta")));
                    txtdetalles.setText(String.valueOf(rs.getString("detalles")));
                    jdFechaing.setDate(Date.valueOf(rs.getString("fechacaducidad")));
                    txtrutaimgprod.setText(String.valueOf(rs.getString("imagen")));

                    lblimagen.setText("No Imagen");
                    String Ruta;
                    Ruta = txtrutaimgprod.getText();
                    ImageIcon img = new ImageIcon(Ruta);
                    lblimagen.setIcon(new ImageIcon(img.getImage().getScaledInstance(180, 166, Image.SCALE_DEFAULT)));

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString());
        }
    }

    public static BigInteger prueba(BigInteger max) {
        Random rnd = new Random();
        do {
            BigInteger i = new BigInteger(max.bitLength(), rnd);
            if (i.compareTo(max) <= 0) {
                return i;
            }
        } while (true);
    }

    void barc() {
        txtcodbarras.setText("" + prueba(new BigInteger("9999999999999")));
    }

    public static double PVPRedondeado() {
        double total = Double.parseDouble(GUIProductos.txtpvp.getText());
        return Redondeo.redondear(total);
    }

    void CalculosIva() {
        try {
            double IVA = Double.parseDouble(this.txtiva.getText());
            double preCompra = Double.parseDouble(this.txtpreciocompra.getText());
            double totaliva = IVA * preCompra;
            double totalpagar = preCompra + totaliva;
            txtpvp.setText("" + totalpagar);
        } catch (Exception ex) {
            System.out.println("Saldo de abono es " + ex);
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtcodproduct = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtcodbarras = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtprod = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbCategoria = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        cbMarca = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        cbUnidad = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        txtstock = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtpreciocompra = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtpvp = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtdesc = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jdFechaing = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        cbProveedor = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtdetalles = new javax.swing.JTextArea();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        lblimagen = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        lblcode = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        txtrutaimgprod = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        txtiva = new javax.swing.JTextField();
        chIva = new javax.swing.JCheckBox();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jRadioBalanza = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbproducts = new javax.swing.JTable();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        txtbuscar = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("REGISTRO DE PRODUCTOS - INVENTARIO INICIAL");
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/Productos.png")).getImage());
        setResizable(false);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Código:");

        txtcodproduct.setBackground(new java.awt.Color(255, 255, 204));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Código de barras:");

        txtcodbarras.setBackground(new java.awt.Color(255, 255, 204));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Producto:");

        txtprod.setBackground(new java.awt.Color(255, 255, 204));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Categoría:");

        cbCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Seleccionar---" }));
        cbCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbCategoriaMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Marca:");

        cbMarca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Seleccionar---" }));
        cbMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMarcaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Unidad:");

        cbUnidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Seleccionar---" }));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Cantidad:");

        txtstock.setBackground(new java.awt.Color(255, 255, 204));
        txtstock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtstockMouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("P. Compra:");

        txtpreciocompra.setBackground(new java.awt.Color(255, 255, 204));
        txtpreciocompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtpreciocompraMouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("P.Venta:");

        txtpvp.setBackground(new java.awt.Color(255, 255, 204));
        txtpvp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtpvpMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Descuento:");

        txtdesc.setBackground(new java.awt.Color(255, 255, 204));
        txtdesc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtdescMouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Fecha Cad:");

        jdFechaing.setOpaque(false);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Proveedor:");

        cbProveedor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Seleccionar---" }));

        txtdetalles.setBackground(new java.awt.Color(255, 255, 204));
        txtdetalles.setColumns(20);
        txtdetalles.setRows(5);
        txtdetalles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtdetallesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(txtdetalles);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Nuevos.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Guardar.png"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Actualizar.png"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Regresar.png"))); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        lblimagen.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblimagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblimagen.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblimagen.setPreferredSize(new java.awt.Dimension(2, 2));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/SubirFoto.png"))); // NOI18N
        jButton6.setText("Subir imágen");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        lblcode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Vista.png"))); // NOI18N
        jButton7.setText("Ver código");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Ultimo.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Siguiente.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Anterior.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Primero.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Eliminar.png"))); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        txtiva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtivaKeyReleased(evt);
            }
        });

        chIva.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chIva.setText("IVA");
        chIva.setOpaque(false);
        chIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chIvaActionPerformed(evt);
            }
        });

        jButton13.setText("...");
        jButton13.setToolTipText("Registrar categoría...");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("...");
        jButton14.setToolTipText("Registrar marca...");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jRadioBalanza.setText("Balanza");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtcodproduct, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtcodbarras, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(cbMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioBalanza)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(chIva, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(cbUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel9)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtpreciocompra, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(90, 90, 90)
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtpvp, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel11)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtdesc, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtprod, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(14, 14, 14))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jButton1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtrutaimgprod, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtiva, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(cbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jLabel12)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jdFechaing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                        .addGap(10, 10, 10)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblcode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(13, 13, 13)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(txtrutaimgprod, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtpvp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(txtdesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtiva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chIva))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtcodproduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtcodbarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton13)
                                    .addComponent(jRadioBalanza))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jButton14)))
                            .addComponent(lblimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6)
                            .addComponent(jLabel9)
                            .addComponent(txtpreciocompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lblcode, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jdFechaing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(cbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Registro de productos", jPanel2);

        jPanel6.setOpaque(false);

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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtbuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRadioButton1)
                        .addComponent(jRadioButton2)
                        .addComponent(jRadioButton3))
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Lista", jPanel6);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            txtcodproduct.setText(String.valueOf(rs.getString("codprod")));
            txtcodbarras.setText(String.valueOf(rs.getString("codbarras")));
            txtprod.setText(String.valueOf(rs.getString("producto")));
            cbMarca.setSelectedItem(String.valueOf(rs.getString("marca")));
            cbUnidad.setSelectedItem(String.valueOf(rs.getString("unidad")));
            cbCategoria.setSelectedItem(String.valueOf(rs.getString("categoria")));
            txtstock.setText(String.valueOf(rs.getString("stock")));
            txtiva.setText(String.valueOf(rs.getString("iva")));
            txtdesc.setText(String.valueOf(rs.getString("descuento")));
            cbProveedor.setSelectedItem(String.valueOf(rs.getString("proveedor")));
            txtpreciocompra.setText(String.valueOf(rs.getString("pre_compra")));
            txtpvp.setText(String.valueOf(rs.getString("pre_venta")));
            txtdetalles.setText(String.valueOf(rs.getString("detalles")));
            txtrutaimgprod.setText(String.valueOf(rs.getString("imagen")));
            jdFechaing.setDate(Date.valueOf(rs.getString("fechacaducidad")));
            String Ruta;
            Ruta = txtrutaimgprod.getText();
            ImageIcon img = new ImageIcon(Ruta);
            lblimagen.setIcon(new ImageIcon(img.getImage().getScaledInstance(180, 166, Image.SCALE_DEFAULT)));
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
            txtcodproduct.setText(String.valueOf(rs.getString("codprod")));
            txtcodbarras.setText(String.valueOf(rs.getString("codbarras")));
            txtprod.setText(String.valueOf(rs.getString("producto")));
            cbMarca.setSelectedItem(String.valueOf(rs.getString("marca")));
            cbUnidad.setSelectedItem(String.valueOf(rs.getString("unidad")));
            cbCategoria.setSelectedItem(String.valueOf(rs.getString("categoria")));
            txtstock.setText(String.valueOf(rs.getString("stock")));
            txtiva.setText(String.valueOf(rs.getString("iva")));
            txtdesc.setText(String.valueOf(rs.getString("descuento")));
            cbProveedor.setSelectedItem(String.valueOf(rs.getString("proveedor")));
            txtpreciocompra.setText(String.valueOf(rs.getString("pre_compra")));
            txtpvp.setText(String.valueOf(rs.getString("pre_venta")));
            txtdetalles.setText(String.valueOf(rs.getString("detalles")));
            txtrutaimgprod.setText(String.valueOf(rs.getString("imagen")));
            jdFechaing.setDate(Date.valueOf(rs.getString("fechacaducidad")));
            String Ruta;
            Ruta = txtrutaimgprod.getText();
            ImageIcon img = new ImageIcon(Ruta);
            lblimagen.setIcon(new ImageIcon(img.getImage().getScaledInstance(180, 166, Image.SCALE_DEFAULT)));
            Barcode();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString());
            JOptionPane.showMessageDialog(this, "Este es el ultimo registro" + e);
            System.out.println("Este es el ultimo registro");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        String prod, codb, pre, stc, iva, marc, cat, uni, desc, feci, rut, prov, pvp, descu, det;
        String sql = "";

        prod = txtprod.getText();
        codb = txtcodbarras.getText();
        pre = txtpreciocompra.getText();
        double precioCompra = Double.parseDouble(pre);
        pre = String.valueOf(precioCompra);

        stc = txtstock.getText();
        iva = txtiva.getText();
        marc = cbMarca.getSelectedItem().toString();
        if (marc.equals("---Seleccionar---")) {
            marc = "";
        }
        uni = cbUnidad.getSelectedItem().toString();
        prov = cbProveedor.getSelectedItem().toString();
        if (prov.equals("---Seleccionar---")) {
            prov = "";
        }
        cat = cbCategoria.getSelectedItem().toString();
        if (cat.equals("---Seleccionar---")) {
            cat = "";
        }
        /*
            Validamos que sea tocado el botón de la balanza
        */
        boolean isSelectBalanza = jRadioBalanza.isSelected();
        int restBalanza;
        if (isSelectBalanza) {
            restBalanza = 1;
        } else {
            restBalanza = 0;
        }

        pvp = txtpvp.getText();
        double precioVenta = Double.parseDouble(pvp);
        pvp = String.valueOf(precioVenta);

        desc = txtdetalles.getText();

        descu = txtdesc.getText();
        double descuento = Double.parseDouble(descu);
        descu = String.valueOf(descuento);
        //feci = jdFecha.getCalendar().getCalendarType();
        det = txtdetalles.getText();
        rut = txtrutaimgprod.getText();

        sql = "INSERT INTO tabla_productos (codbarras,producto,marca,unidad,categoria,stock,iva,"
                + "descuento,proveedor,pre_compra,pre_venta,fechacaducidad,detalles,imagen, balanza) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = Conexion.conexion().prepareStatement(sql);
            pst.setString(1, codb);
            pst.setString(2, prod);
            pst.setString(3, marc);
            pst.setString(4, uni);
            pst.setString(5, cat);
            pst.setString(6, stc);
            pst.setString(7, iva);
            pst.setString(8, descu);
            pst.setString(9, prov);
            pst.setString(10, pre);
            pst.setString(11, pvp);
            pst.setDate(12, new java.sql.Date(jdFechaing.getDate().getTime()));
            pst.setString(13, det);
            pst.setString(14, rut);
            pst.setInt(15, restBalanza);

            int n = pst.executeUpdate();
            if (n > 0) {

                JOptionPane.showMessageDialog(null, "Se ha registrado satisfactoriamente el producto " + prod + " y se agregrará\n"
                        + "a la lista de productos en su bodega");

                try {
                    int consultar = JOptionPane.showConfirmDialog(null, "Desea imprimir las etiquetas del producto agregado?", "Información", JOptionPane.YES_NO_OPTION);
                    if (consultar == 0) {
                        String urlreporte = "src\\Reportes\\R_CodBarras.jasper";
                        Map parametros = new HashMap();
                        parametros.put("codbarras", txtcodbarras.getText());
                        JasperPrint reporte = JasperFillManager.fillReport(urlreporte, parametros, Conexion.conexion());
                        JasperViewer ventana = new JasperViewer(reporte, false);
                        ventana.setTitle("ETIQUETAS DE CÓDIGO DE BARRAS");
                        ventana.setVisible(true);
                    }
                } catch (Exception e) {
                    System.out.printf(e.getMessage());
                }
                Productos("");
                txtdetalles.setText("Ingrese aquí una descripción");

            }

        } catch (SQLException ex) {
            Logger.getLogger(GUIProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            PreparedStatement pst = Conexion.conexion().prepareStatement("UPDATE tabla_productos SET codbarras='" + txtcodbarras.getText()
                    + "',producto='" + txtprod.getText()
                    + "',marca='" + cbMarca.getSelectedItem()
                    + "',unidad='" + cbUnidad.getSelectedItem()
                    + "',categoria='" + cbCategoria.getSelectedItem()
                    + "',stock='" + txtstock.getText()
                    + "',iva='" + txtiva.getText()
                    + "',descuento='" + txtdesc.getText()
                    + "',proveedor='" + cbProveedor.getSelectedItem()
                    + "',pre_compra='" + txtpreciocompra.getText()
                    + "',pre_venta='" + txtpvp.getText()
                    + "',fechacaducidad='" + new java.sql.Date(jdFechaing.getDate().getTime())
                    + "',detalles='" + txtdetalles.getText()
                    + "',imagen='" + txtrutaimgprod.getText() + "' WHERE codprod='" + txtcodproduct.getText() + "'");
            JOptionPane.showMessageDialog(null, "Este producto se ha actualizado con éxito!");
            pst.executeUpdate();
            Productos("");

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
        String iva = tbproducts.getValueAt(fila, 7).toString();
        String des = tbproducts.getValueAt(fila, 8).toString();
        String prov = tbproducts.getValueAt(fila, 9).toString();
        String pc = tbproducts.getValueAt(fila, 10).toString();
        String pv = tbproducts.getValueAt(fila, 11).toString();
        String fec = tbproducts.getValueAt(fila, 12).toString();
        String det = tbproducts.getValueAt(fila, 13).toString();

        try {
            lblimagen.setText("");
            lblimagen.setText("No Imagen");
            int Fila = tbproducts.getSelectedRow();
            String Imagen = tbproducts.getValueAt(Fila, 14).toString();
            ImageIcon img = new ImageIcon(Imagen);
            lblimagen.setIcon(new ImageIcon(img.getImage().getScaledInstance(180, 166, Image.SCALE_DEFAULT)));

        } catch (Exception e) {
            System.out.println("No hay imagen" + e);
        }
        txtcodproduct.setText(cod);
        txtcodbarras.setText(cb);
        txtprod.setText(pro);
        cbMarca.setSelectedItem(mar);
        cbUnidad.setSelectedItem(un);
        cbCategoria.setSelectedItem(cat);
        txtstock.setText(st);
        txtiva.setText(iva);
        txtdesc.setText(des);
        cbProveedor.setSelectedItem(prov);
        jdFechaing.setDate(Date.valueOf(tbproducts.getValueAt(fila, 12).toString()));
        txtpreciocompra.setText(pc);
        txtpvp.setText(pv);
        txtdetalles.setText(det);
    }//GEN-LAST:event_tbproductsMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Productos("");
        Marca("");
        Categoria("");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Generarnumeracion();
        barc();
        Barcode();
        txtdesc.setText("0.0");
        txtdetalles.setText("Ingrese aquí una descripción");
        txtprod.setText("");
        txtpreciocompra.setText("0.0");
        txtpvp.setText("0.0");
        txtrutaimgprod.setText("");
        txtstock.setText("0");
        txtiva.setText("0.0");
        cbCategoria.setSelectedItem("---Seleccionar---");
        cbMarca.setSelectedItem("---Seleccionar---");
        cbProveedor.setSelectedItem("---Seleccionar---");
        cbUnidad.setSelectedItem("---Seleccionar---");
        lblcode.setText("");
        txtprod.requestFocus();
        lblimagen.setText("");
        lblimagen.setIcon(null);
        this.txtiva.setEnabled(false);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void txtdetallesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtdetallesMouseClicked
        txtdetalles.setText("");
    }//GEN-LAST:event_txtdetallesMouseClicked

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
            txtrutaimgprod.setText(FileName.replace("\\", "//"));
            String Ruta;
            Ruta = txtrutaimgprod.getText();
            ImageIcon img = new ImageIcon(Ruta);
            lblimagen.setIcon(new ImageIcon(img.getImage().getScaledInstance(180, 166, Image.SCALE_DEFAULT)));
        } catch (java.lang.Exception e) {
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
        Productos(txtbuscar.getText());
        Marca(txtbuscar.getText());
        Categoria(txtbuscar.getText());
    }//GEN-LAST:event_txtbuscarKeyReleased

    private void txtpreciocompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtpreciocompraMouseClicked
        txtpreciocompra.setText("");
    }//GEN-LAST:event_txtpreciocompraMouseClicked

    private void txtpvpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtpvpMouseClicked
        txtpvp.setText("");
    }//GEN-LAST:event_txtpvpMouseClicked

    private void txtdescMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtdescMouseClicked
        txtdesc.setText("");
    }//GEN-LAST:event_txtdescMouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        String codBar = txtcodbarras.getText();
        try {
            int eliminar = JOptionPane.showConfirmDialog(null, "Desea eliminar este producto?", "Información", JOptionPane.YES_NO_OPTION);
            if (eliminar == 0) {

                PreparedStatement pst = Conexion.conexion().prepareStatement("DELETE FROM tabla_productos WHERE codbarras='" + codBar + "'");

                JOptionPane.showMessageDialog(null, "Producto eliminado del sistema", "Informe de eliminación", JOptionPane.INFORMATION_MESSAGE);
                pst.executeUpdate();
                Generarnumeracion();
                barc();
                txtdesc.setText("0.0");
                txtdetalles.setText("Ingrese aquí una descripción");
                txtprod.setText("");
                txtpreciocompra.setText("0.0");
                txtpvp.setText("0.0");
                txtrutaimgprod.setText("");
                txtstock.setText("0");
                txtiva.setText("0.0");
                cbCategoria.setSelectedItem("---Seleccionar---");
                cbMarca.setSelectedItem("---Seleccionar---");
                cbProveedor.setSelectedItem("---Seleccionar---");
                cbUnidad.setSelectedItem("---Seleccionar---");
                lblcode.setText("");
                txtprod.requestFocus();
                lblimagen.setText("");
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void cbMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbMarcaActionPerformed

    private void txtivaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtivaKeyReleased
        try {
            double Calculos = GUIProductos.PVPRedondeado();
            txtpvp.setText(String.valueOf(Calculos));
            CalculosIva();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtivaKeyReleased

    private void chIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chIvaActionPerformed
        try {

            if (chIva.isSelected() == true) {
                txtiva.setEnabled(true);
            } else {
                txtiva.setEnabled(false);
            }
            txtiva.setText("0.0");

        } catch (Exception e) {
        }
    }//GEN-LAST:event_chIvaActionPerformed

    private void txtstockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtstockMouseClicked
        txtstock.setText("");
    }//GEN-LAST:event_txtstockMouseClicked

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        GUICategorias ct = new GUICategorias();
        ct.setVisible(true);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void cbCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbCategoriaMouseClicked

    }//GEN-LAST:event_cbCategoriaMouseClicked

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        GUIMarcas mr = new GUIMarcas();
        mr.setVisible(true);
    }//GEN-LAST:event_jButton14ActionPerformed

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
            java.util.logging.Logger.getLogger(GUIProductos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIProductos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIProductos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIProductos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIProductos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    public static javax.swing.JComboBox cbCategoria;
    public static javax.swing.JComboBox cbMarca;
    private javax.swing.JComboBox cbProveedor;
    private javax.swing.JComboBox cbUnidad;
    private javax.swing.JCheckBox chIva;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioBalanza;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser jdFechaing;
    private javax.swing.JLabel lblcode;
    private javax.swing.JLabel lblimagen;
    private javax.swing.JTable tbproducts;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtcodbarras;
    public static javax.swing.JTextField txtcodproduct;
    private javax.swing.JTextField txtdesc;
    private javax.swing.JTextArea txtdetalles;
    public static javax.swing.JTextField txtiva;
    private javax.swing.JTextField txtpreciocompra;
    private javax.swing.JTextField txtprod;
    public static javax.swing.JTextField txtpvp;
    private javax.swing.JTextField txtrutaimgprod;
    private javax.swing.JTextField txtstock;
    // End of variables declaration//GEN-END:variables
}
