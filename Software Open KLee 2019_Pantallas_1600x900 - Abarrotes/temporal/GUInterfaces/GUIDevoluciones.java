/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUInterfaces;

import Clases.Conexion;
import Clases.conectar;
import Clases.relojLog;
import com.mxrck.autocompleter.TextAutoCompleter;
import ds.desktop.notify.DesktopNotify;
import java.awt.Image;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class GUIDevoluciones extends javax.swing.JFrame {

    DefaultTableModel modelFACT;
    int contador;
//    conectar cc = new conectar();
//    Connection cn = cc.conexion();

    int cantidadVocales = 0;
    String vocales = "";

    public GUIDevoluciones() {
        initComponents();
        this.setLocationRelativeTo(null);
        txtfac.setEnabled(false);
        lblfecha.setText(fechaactual());
        txtnumfact.requestFocus();
        relojLog hilo = new relojLog(lblhora);
        hilo.start();
        Nfact();
        DesktopNotify.showDesktopMessage(
                "SISTEMA PUNTO DE VENTA MAFAKAFER",
                "Presione el campo C.E para cambiar cantidad de dovolución",
                DesktopNotify.INFORMATION, 5500L);

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = con.createStatement();
            ResultSet rs = sent.executeQuery("select * from tabla_usuarios");

            while (rs.next()) {
                this.cbUsu.addItem(rs.getString("nick"));

            }
            contador++;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Recursos/LogoOK2019.png"));
        return retValue;
    }
    void descontarstockdetalle(String codb, String cant) { 
        double desp = Double.parseDouble(cant);
        String capb = "";
        double desfinalv;
        String consul = "SELECT * FROM detalleventas WHERE cod_pro='" + codb + "'";

        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consul);
            while (rs.next()) {
                capb = rs.getString(4);
            }

        } catch (Exception e) {
        }

        desfinalv = Double.parseDouble(capb) - desp;

        String modib = "UPDATE detalleventas SET cant_comp='" + desfinalv + "' WHERE cod_pro = '" + codb + "'";
        try {
            PreparedStatement pst = Conexion.conexion().prepareStatement(modib);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("Se realizó el descuento de stock satisfactoriamente" + e.getMessage());
        }

    }

    void nuevostock(String cod, String cantt) {
        double desp = Double.parseDouble(cantt);
        String capb = "";
        double desfinalv;
        String consul = "SELECT * FROM tabla_productos WHERE codbarras='" + cod + "'";

        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consul);
            while (rs.next()) {
                capb = rs.getString(7); 
            }

        } catch (Exception e) {
        }

        desfinalv = Double.parseDouble(capb) + desp;

        String modib = "UPDATE tabla_productos SET stock='" + desfinalv + "' WHERE codbarras = '" + cod + "'";
        try {
            PreparedStatement pst = Conexion.conexion().prepareStatement(modib);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("Incremento de stock satisfactorio" + e);
        }

    }

    /**
     * ************************************************************************************************************
     */
    /*void numeros() {
     int j;
     int cont = 1;
     String num = "";
     String c = "";
     String SQL = "select max(num_fact) from tabla_ventas";

     try {
     Statement st = cn.createStatement();
     ResultSet rs = st.executeQuery(SQL);
     if (rs.next()) {
     c = rs.getString(1);
     }

     if (c == null) {
     txtfacturaventas.setText("00000001");
     } else {
     j = Integer.parseInt(c);
     GenerarNumero gen = new GenerarNumero();
     gen.generar(j);
     txtfacturaventas.setText(gen.serie());

     }
     } catch (SQLException ex) {
     Logger.getLogger(GUIVentas.class.getName()).log(Level.SEVERE, null, ex);
     }
     }*/
    void CargartodasFacturas(String valor) {
        String mostrar = "SELECT * FROM detalleventas WHERE CONCAT(num_fact,des_comp) LIKE '%" + valor + "%'";
        String[] titulos = {"NUMERO", "COD", "PRODUCTO", "CANT", "P.UNIT", "%", "F.PAGO", "TOTAL"};
        String[] Registros = new String[8];
        modelFACT = new DefaultTableModel(null, titulos);
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(mostrar);
            while (rs.next()) {
                Registros[0] = rs.getString("num_fact");
                Registros[1] = rs.getString("cod_pro");
                Registros[2] = rs.getString("des_comp");
                Registros[3] = rs.getString("cant_comp");
                Registros[4] = rs.getString("pre_comp");
                Registros[5] = rs.getString("descuento");
                Registros[6] = rs.getString("formapago");
                Registros[7] = rs.getString("pre_tot");

                modelFACT.addRow(Registros);
                tbdetvent.setModel(modelFACT);
                tbdetvent.setAutoResizeMode(tbdetvent.AUTO_RESIZE_OFF);
                tbdetvent.setRowHeight(24);
                tbdetvent.getColumnModel().getColumn(0).setPreferredWidth(70);
                tbdetvent.getColumnModel().getColumn(1).setPreferredWidth(90);
                tbdetvent.getColumnModel().getColumn(2).setPreferredWidth(140);
                tbdetvent.getColumnModel().getColumn(3).setPreferredWidth(50);
                tbdetvent.getColumnModel().getColumn(4).setPreferredWidth(50);
                tbdetvent.getColumnModel().getColumn(5).setPreferredWidth(60);
                tbdetvent.getColumnModel().getColumn(6).setPreferredWidth(60);
                tbdetvent.getColumnModel().getColumn(7).setPreferredWidth(60);

            }
        } catch (SQLException ex) {
            Logger.getLogger(GUIDevoluciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     void calculardev2() {
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
        double totparcial = 0.0;

        for (int i = 0; i < GUIDevoluciones.tabdevolucion.getRowCount(); i++) {
            pre = GUIDevoluciones.tabdevolucion.getValueAt(i, 2).toString();
            can = GUIDevoluciones.tabdevolucion.getValueAt(i, 3).toString();

            precio = Double.parseDouble(pre);
            cantidad = Double.parseDouble(can);

            if (GUIDevoluciones.tabdevolucion.getValueAt(i, 5) == null) {
                totparcial = precio * cantidad;

            } else {
 
                desc = GUIDevoluciones.tabdevolucion.getValueAt(i, 5).toString();
                descuentos = Double.parseDouble(desc);
                impParcial = precio * cantidad;
                desct = (precio * descuentos) * cantidad;
                totparcial = impParcial - desct;
            }

            subtotal = subtotal + totparcial;
            iva = subtotal * 0.00;
            total = subtotal + iva; 
            GUIDevoluciones.tabdevolucion.setValueAt(Math.rint(totparcial * 100) / 100, i, 6);
        }

        GUIDevoluciones.txtsubtot.setText(Double.toString(subtotal));
        GUIDevoluciones.txtiva.setText("" + Math.rint(iva * 100) / 100);
        GUIDevoluciones.txttotal.setText("" + Math.rint(total * 100) / 100);

    } 
     
    String comparar(String cod) {
        String cant = "";
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM detalleventas WHERE num_fact='" + cod + "'");
            while (rs.next()) {
                cant = rs.getString(2);

            }

        } catch (SQLException ex) {
            Logger.getLogger(GUIDevoluciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cant;
    }

    void detalledevoluciones() {

        for (int i = 0; i < tabdevolucion.getRowCount(); i++) {

            String InsertarSQLDV = "INSERT INTO tabla_devoluciones (cod_detallefact, cod_prod, "
                    + "producto, motivo, tipdevolucion, cantidad, precio, total, usuario, fecha) VALUES (?,?,?,?,?,?,?,?,?,?)";
            String hor = lblhora.getText();
            String num_fact = txtnumfact.getText();
            String fecha = lblfecha.getText();
            String devol = lbldevol.getText();
            String motivo = txtMotivos.getText(); 
            String usu = cbUsu.getSelectedItem().toString();
             
            String codi = tabdevolucion.getValueAt(i, 0).toString();
            String des_comp = tabdevolucion.getValueAt(i,1).toString();
            String pre_comp = tabdevolucion.getValueAt(i, 2).toString();
            String cant_comp = tabdevolucion.getValueAt(i, 3).toString();
            String total = tabdevolucion.getValueAt(i, 6).toString();

            try {

                PreparedStatement pst = Conexion.conexion().prepareStatement(InsertarSQLDV);
                pst.setString(1, num_fact);
                pst.setString(2, codi);
                pst.setString(3, des_comp);
                pst.setString(4, motivo);
                pst.setString(5, devol);
                pst.setString(6, cant_comp);
                pst.setString(7, pre_comp);
                pst.setString(8, total);
                pst.setString(9, usu);
                pst.setString(10, fecha);
                
                pst.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(GUIDevoluciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void detallekardex() {

        for (int i = 0; i < tabdevolucion.getRowCount(); i++) {

            String InsertarSQLK = "INSERT INTO tabla_kardex (num_fact, cod_prod, producto,motivo,documento,"
                    + "proveedor,pre_unit,entrada,salida,total,fecha) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

            String num_fact = txtfac.getText();
            String emp = cbUsu.getSelectedItem().toString();
            String doc = cboDocu.getSelectedItem().toString();
            String fec = lblfecha.getText();
            String mot = lbldevol.getText(); 

            String cod_prod = tabdevolucion.getValueAt(i, 0).toString();
            String des_prod = tabdevolucion.getValueAt(i, 1).toString();
            String pre_prod = tabdevolucion.getValueAt(i, 2).toString();
            String entr_prod = tabdevolucion.getValueAt(i, 3).toString();
            String cant_sal = tabdevolucion.getValueAt(i, 4).toString();
            String pre_tot = tabdevolucion.getValueAt(i, 6).toString();

            try {

                PreparedStatement pst = Conexion.conexion().prepareStatement(InsertarSQLK);
                pst.setString(1, num_fact);
                pst.setString(2, cod_prod);
                pst.setString(3, des_prod);
                pst.setString(4, mot);
                pst.setString(5, doc);
                pst.setString(6, emp);
                pst.setString(7, pre_prod);
                pst.setString(8, entr_prod);
                pst.setString(9, cant_sal);
                pst.setString(10, pre_tot);
                pst.setString(11, fec);

                pst.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(GUICobrodeVentas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void Nfact() {
        TextAutoCompleter textAutoC = new TextAutoCompleter(txtnumfact);
        try {
           // Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = Conexion.conexion().createStatement();
            ResultSet rs = sent.executeQuery("SELECT num_fact FROM tabla_ventas");
            while (rs.next()) {
                textAutoC.addItem(rs.getString("num_fact"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void RedondearSubtotal() {
        double valor = Double.parseDouble(txtsubtot.getText());
        String val = valor + "";
        BigDecimal big = new BigDecimal(val);
        big = big.setScale(2, RoundingMode.HALF_UP);
        txtsubtot.setText("" + big);
    }

    /**
     * *****************************************************************
     * /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoMedicamentos = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtfac = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtsubtot = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtiva = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMotivos = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblhora = new javax.swing.JLabel();
        lblfecha = new javax.swing.JLabel();
        lbldevol = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbUsu = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbdetvent = new javax.swing.JTable();
        btnEnvia1x1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabdevolucion = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        txtnumfact = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cboDocu = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DEVOLUCIONES EN VENTAS");
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/Revert.png")).getImage());
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 153));
        jLabel3.setText("Buscar venta:");

        txtfac.setEditable(false);
        txtfac.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        txtfac.setForeground(new java.awt.Color(153, 0, 0));
        txtfac.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setOpaque(false);

        jPanel3.setBackground(new java.awt.Color(0, 153, 204));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("  Seleccione una venta para proceder con su respectiva devolución");
        jPanel3.add(jLabel1);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Totales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridLayout(3, 0));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Subtotal:");
        jPanel4.add(jLabel18);

        txtsubtot.setFont(new java.awt.Font("Bernard MT Condensed", 1, 21)); // NOI18N
        txtsubtot.setForeground(new java.awt.Color(0, 0, 153));
        txtsubtot.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        txtsubtot.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(txtsubtot);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("I.V.A:");
        jPanel4.add(jLabel19);

        txtiva.setFont(new java.awt.Font("Bernard MT Condensed", 1, 21)); // NOI18N
        txtiva.setForeground(new java.awt.Color(0, 0, 153));
        txtiva.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        txtiva.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(txtiva);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 102));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Moneda.png"))); // NOI18N
        jPanel4.add(jLabel4);

        txttotal.setEditable(false);
        txttotal.setBackground(new java.awt.Color(51, 51, 51));
        txttotal.setFont(new java.awt.Font("Bernard MT Condensed", 1, 21)); // NOI18N
        txttotal.setForeground(new java.awt.Color(102, 255, 102));
        txttotal.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txttotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttotalActionPerformed(evt);
            }
        });
        txttotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttotalKeyPressed(evt);
            }
        });
        jPanel4.add(txttotal);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Motivo:");

        txtMotivos.setColumns(20);
        txtMotivos.setRows(5);
        txtMotivos.setText("Ingrese un motivo...\n");
        txtMotivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMotivosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(txtMotivos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(0, 153, 204));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Proceso de Devoluciones - Ticket - Facturas -");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Fecha remisión:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Hora remisión:");

        lblhora.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblhora.setForeground(new java.awt.Color(255, 255, 0));
        lblhora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblfecha.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblfecha.setForeground(new java.awt.Color(255, 255, 0));
        lblfecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbldevol.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lbldevol.setForeground(new java.awt.Color(255, 255, 255));
        lbldevol.setText("Devolución");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbldevol)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblhora, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbldevol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblhora, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 153));
        jLabel5.setText("Número de venta actual:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 153));
        jLabel7.setText("Usuario:");

        jPanel6.setBackground(new java.awt.Color(0, 102, 102));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tbdetvent.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tbdetvent.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        tbdetvent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbdetvent.setGridColor(new java.awt.Color(0, 102, 255));
        tbdetvent.setSelectionBackground(new java.awt.Color(255, 204, 153));
        tbdetvent.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tbdetvent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbdetventMouseClicked(evt);
            }
        });
        tbdetvent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbdetventKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbdetvent);

        btnEnvia1x1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Next.png"))); // NOI18N
        btnEnvia1x1.setToolTipText("Devolver este producto");
        btnEnvia1x1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnvia1x1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/EliminarD.png"))); // NOI18N
        jButton2.setToolTipText("Eliminar de la lista");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Todos.png"))); // NOI18N
        jButton3.setToolTipText("Devolver todos");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CancelarTodo.png"))); // NOI18N
        jButton4.setToolTipText("Cancelar todos");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/GuardarD.png"))); // NOI18N
        jButton5.setToolTipText("Guardar devolución");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/GuardarSalir.png"))); // NOI18N
        jButton6.setToolTipText("Guardar y cerrar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        tabdevolucion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabdevolucion.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        tabdevolucion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod", "Prod", "Pre", "C.E", "C.S", "%", "Parcial"
            }
        ));
        tabdevolucion.setGridColor(new java.awt.Color(0, 102, 255));
        tabdevolucion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabdevolucionKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tabdevolucion);
        if (tabdevolucion.getColumnModel().getColumnCount() > 0) {
            tabdevolucion.getColumnModel().getColumn(0).setMinWidth(105);
            tabdevolucion.getColumnModel().getColumn(0).setMaxWidth(105);
            tabdevolucion.getColumnModel().getColumn(1).setMinWidth(160);
            tabdevolucion.getColumnModel().getColumn(1).setMaxWidth(160);
            tabdevolucion.getColumnModel().getColumn(2).setMinWidth(50);
            tabdevolucion.getColumnModel().getColumn(2).setMaxWidth(50);
            tabdevolucion.getColumnModel().getColumn(3).setMinWidth(50);
            tabdevolucion.getColumnModel().getColumn(3).setMaxWidth(50);
            tabdevolucion.getColumnModel().getColumn(4).setMinWidth(40);
            tabdevolucion.getColumnModel().getColumn(4).setMaxWidth(40);
            tabdevolucion.getColumnModel().getColumn(5).setMinWidth(50);
            tabdevolucion.getColumnModel().getColumn(5).setMaxWidth(50);
            tabdevolucion.getColumnModel().getColumn(6).setMinWidth(50);
            tabdevolucion.getColumnModel().getColumn(6).setMaxWidth(50);
        }

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Revert.png"))); // NOI18N
        jButton1.setToolTipText("Reporte devoluciones");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnEnvia1x1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnEnvia1x1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 24, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        txtnumfact.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtnumfact.setText("Ingrese número de venta...");
        txtnumfact.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtnumfactMouseClicked(evt);
            }
        });
        txtnumfact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnumfactActionPerformed(evt);
            }
        });
        txtnumfact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnumfactKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnumfactKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 153));
        jLabel8.setText("Documento:");

        cboDocu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Seleccionar---", "Ventas", "Factura", "Ticket", "Orden de compra", "Pedidos", "Devolución", "Otros" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbUsu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboDocu, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtfac, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtnumfact))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfac, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addComponent(cboDocu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(txtnumfact))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    private void tbdetventKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbdetventKeyPressed
            calculardev2();
    }//GEN-LAST:event_tbdetventKeyPressed

    private void txttotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttotalKeyPressed

    }//GEN-LAST:event_txttotalKeyPressed

    private void txttotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttotalActionPerformed

    private void btnEnvia1x1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnvia1x1ActionPerformed

        int filasele = tbdetvent.getSelectedRow();
        try {

            if (filasele == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            } else {
//                DefaultTableModel mF;
////                mF = (DefaultTableModel) tbdetvent.getModel();
                
                String codins = tbdetvent.getValueAt(filasele, 1).toString();
                String prod = tbdetvent.getValueAt(filasele, 2).toString();
                String cant = tbdetvent.getValueAt(filasele, 3).toString();
                String cants = "0.0";
                String precio = tbdetvent.getValueAt(filasele, 4).toString();
                String desct = tbdetvent.getValueAt(filasele, 5).toString();
                
                DefaultTableModel mD;
                mD = (DefaultTableModel) tabdevolucion.getModel();
                String element[] = {codins, prod, precio, cant, cants, desct, precio};
                mD.addRow(element);

                tabdevolucion.setAutoResizeMode(tabdevolucion.AUTO_RESIZE_OFF);
                tabdevolucion.setRowHeight(24);
                tabdevolucion.getColumnModel().getColumn(0).setPreferredWidth(150);
                tabdevolucion.getColumnModel().getColumn(1).setPreferredWidth(60);
                tabdevolucion.getColumnModel().getColumn(2).setPreferredWidth(250);
                tabdevolucion.getColumnModel().getColumn(3).setPreferredWidth(55);
                tabdevolucion.getColumnModel().getColumn(4).setPreferredWidth(55);
                tabdevolucion.getColumnModel().getColumn(5).setPreferredWidth(55);
                calculardev2();
                RedondearSubtotal();

            }
        } catch (Exception ex) {

        }

    }//GEN-LAST:event_btnEnvia1x1ActionPerformed

    private void tbdetventMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdetventMouseClicked
        /* if (evt.getClickCount() == 1) {

            try {

                DefaultTableModel tabladet = (DefaultTableModel) GUIVentas.tabdevolucion.getModel();
                String[] dato = new String[5];

                int fila = tbdetvent.getSelectedRow();

                String codins = tbdetvent.getValueAt(fila, 0).toString();
                String fact = tbdetvent.getValueAt(fila, 1).toString();
                String desins = tbdetvent.getValueAt(fila, 2).toString();
                String preins = tbdetvent.getValueAt(fila, 4).toString();

                int c = 0;
                int j = 0;

                String cantidades = JOptionPane.showInputDialog(null, "Introduzca la cantidad solicitada", "Solicitud", JOptionPane.PLAIN_MESSAGE);
                if ((cantidades.equals("")) || (cantidades.equals("0"))) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar algun valor mayor que 0", "Open K´LEE", JOptionPane.PLAIN_MESSAGE);
                } else {

                    double cantp = Double.parseDouble(cantidades);
                    double compra = Double.parseDouble(comparar(codins));

                    if (cantp > compra) {
                        JOptionPane.showMessageDialog(this, "El producto " + desins + ", se encuentra en stock");
                    } else {
                        for (int i = 0; i < GUIVentas.tabdevolucion.getRowCount(); i++) {
                            Object com = GUIVentas.tabdevolucion.getValueAt(i, 0);
                            if (codins.equals(com)) {
                                j = i;
                                GUIVentas.tabdevolucion.setValueAt(cantp, i, 3);
                                c = c + 1;
                            }
                        }

                        if (c == 0) {

                            dato[0] = codins;
                            dato[1] = fact;
                            dato[2] = desins;
                            dato[4] = preins;
                            dato[3] = cantidades;
                            

                            tabladet.addRow(dato);
                            tabdevolucion.setModel(tabladet);
                            tabdevolucion.setAutoResizeMode(tabdevolucion.AUTO_RESIZE_OFF);
                            tabdevolucion.setRowHeight(24);
                            tabdevolucion.getColumnModel().getColumn(0).setPreferredWidth(80);
                            tabdevolucion.getColumnModel().getColumn(1).setPreferredWidth(80);
                            tabdevolucion.getColumnModel().getColumn(2).setPreferredWidth(250);
                            tabdevolucion.getColumnModel().getColumn(3).setPreferredWidth(55);
                            tabdevolucion.getColumnModel().getColumn(4).setPreferredWidth(55);

                            calcular();
                        }
                    }
                }

            } catch (Exception e) {
            }

        }*/

    }//GEN-LAST:event_tbdetventMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) tabdevolucion.getModel();
        int fila = tabdevolucion.getSelectedRow();
        if (fila >= 0) {
            modelo.removeRow(fila);
            calculardev2();
            RedondearSubtotal();
        } else {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "Desea cancelar los datos actuales?\n"
                + "Se perderán todos los datos ingresados en la devolución", "Aviso del sistema", 1) == 0) {

            try {
                DefaultTableModel modelo = (DefaultTableModel) tabdevolucion.getModel();
                int fil = tabdevolucion.getRowCount();
                for (int i = 0; fil > i; i++) {
                    modelo.removeRow(0);
                    calculardev2();
                    RedondearSubtotal();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No selecciono ninguna fila");
                JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int filasele = tbdetvent.getSelectedRow();
        if (tbdetvent.getSelectedRowCount() > 0) {

            String cod = tbdetvent.getValueAt(filasele, 0).toString();
            String prod = tbdetvent.getValueAt(filasele, 2).toString();
            String precio = tbdetvent.getValueAt(filasele, 3).toString();
            String cante = tbdetvent.getValueAt(filasele, 4).toString(); 
            String descuento = tbdetvent.getValueAt(filasele, 6).toString();

            DefaultTableModel model = (DefaultTableModel) tabdevolucion.getModel();
            String ver = "SELECT * FROM detalleventas WHERE num_fact='" + cod + "'";
            String[] datos = new String[7];
            try {
                Statement st = Conexion.conexion().createStatement();
                ResultSet rs = st.executeQuery(ver);
                while (rs.next()) {

                    datos[0] = rs.getString("cod_pro");
                    datos[1] = rs.getString("des_comp");
                    datos[2] = rs.getString("pre_comp");
                    datos[3] = rs.getString("cant_comp");
                    datos[4] = "0.0"; 
                    datos[5] = rs.getString("descuento");
                    datos[6] = rs.getString("pre_tot");

                    model.addRow(datos);
                    tabdevolucion.setAutoResizeMode(tabdevolucion.AUTO_RESIZE_OFF);
                    tabdevolucion.setRowHeight(24);
                    tabdevolucion.getColumnModel().getColumn(0).setPreferredWidth(80);
                    tabdevolucion.getColumnModel().getColumn(1).setPreferredWidth(80);
                    tabdevolucion.getColumnModel().getColumn(2).setPreferredWidth(250);
                    tabdevolucion.getColumnModel().getColumn(3).setPreferredWidth(55);
                    tabdevolucion.getColumnModel().getColumn(4).setPreferredWidth(55);
                    tabdevolucion.getColumnModel().getColumn(5).setPreferredWidth(55);
                    calculardev2();
                    RedondearSubtotal();
                }
            } catch (SQLException ex) {
                Logger.getLogger(GUIDevoluciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if ((txtsubtot.getText().equals(""))) {
//            JOptionPane.showMessageDialog(this, "No DATOS");
        } else {
            String codprod = "", cantprod = "";
            for (int i = 0; i < GUIDevoluciones.tabdevolucion.getRowCount(); i++) {
                codprod = GUIDevoluciones.tabdevolucion.getValueAt(i, 0).toString();
                cantprod = GUIDevoluciones.tabdevolucion.getValueAt(i, 3).toString();
                descontarstockdetalle(codprod, cantprod);
                nuevostock(codprod, cantprod);
                
                JOptionPane.showMessageDialog(null, "Datos almacenados correctamente");

            }
            detalledevoluciones();
            detallekardex();
            
            JOptionPane.showMessageDialog(null, "Nuevo inventario");
            DefaultTableModel modelo = (DefaultTableModel) tabdevolucion.getModel();
            int a = tabdevolucion.getRowCount() - 1;
            int i;
            for (i = a; i >= 0; i--) {
                modelo.removeRow(i);
            }

        }
        GUIDevoluciones.txtsubtot.setText("0.00");
        GUIDevoluciones.txtiva.setText("0.00");
        GUIDevoluciones.txttotal.setText("0.00");
        txtMotivos.setText("Ingrese un motivo...");
        GUIDevoluciones.txtnumfact.requestFocus();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tabdevolucionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabdevolucionKeyPressed
        calculardev2();
        RedondearSubtotal();        
    }//GEN-LAST:event_tabdevolucionKeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if ((txtsubtot.getText().equals(""))) {
//            JOptionPane.showMessageDialog(this, "No DATOS");
        } else {
            String codprod = "", cantprod = "";
            for (int i = 0; i < GUIDevoluciones.tabdevolucion.getRowCount(); i++) {
                codprod = GUIDevoluciones.tabdevolucion.getValueAt(i, 0).toString();
                cantprod = GUIDevoluciones.tabdevolucion.getValueAt(i, 3).toString();
                descontarstockdetalle(codprod, cantprod);
                nuevostock(codprod, cantprod);
                
                JOptionPane.showMessageDialog(null, "Datos almacenados correctamente");

            }
            detalledevoluciones();
            detallekardex();
            
            JOptionPane.showMessageDialog(null, "Nuevo inventario");
            DefaultTableModel modelo = (DefaultTableModel) tabdevolucion.getModel();
            int a = tabdevolucion.getRowCount() - 1;
            int i;
            for (i = a; i >= 0; i--) {
                modelo.removeRow(i);
            }

        }
        GUIDevoluciones.txtsubtot.setText("0.00");
        GUIDevoluciones.txtiva.setText("0.00");
        GUIDevoluciones.txttotal.setText("0.00");
        txtMotivos.setText("Ingrese un motivo...");
        GUIDevoluciones.txtnumfact.requestFocus();
        dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtMotivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMotivosMouseClicked
        txtMotivos.setText("");
    }//GEN-LAST:event_txtMotivosMouseClicked

    private void txtnumfactKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnumfactKeyPressed

        CargartodasFacturas(txtnumfact.getText());
        String num = txtnumfact.getText();

        String consulta = "";
        if (txtnumfact.getText().equals(num)) {
            consulta = "SELECT * FROM detalleventas WHERE num_fact='" + num + "'";

        }

        DefaultTableModel tabla = new DefaultTableModel();
        String[] titulos = {"NUMERO", "CLIENTE", "SUBTOTAL", "IVA %", "TOTAL", "F.PAGO", "HORA", "F.EMISIÓN"};
        tabla.setColumnIdentifiers(titulos);
        this.tbdetvent.setModel(tabla);

        String[] Datos = new String[8];
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                Datos[0] = rs.getString("num_fact");
                Datos[1] = rs.getString("cod_pro");
                Datos[2] = rs.getString("des_comp");
                Datos[3] = rs.getString("cant_comp");
                Datos[4] = rs.getString("pre_comp");
                Datos[5] = rs.getString("descuento");
                Datos[6] = rs.getString("formapago");
                Datos[7] = rs.getString("pre_tot");

                tabla.addRow(Datos);
                tbdetvent.setModel(tabla);
                tbdetvent.setAutoResizeMode(tbdetvent.AUTO_RESIZE_OFF);
                tbdetvent.setRowHeight(22);
                tbdetvent.getColumnModel().getColumn(0).setPreferredWidth(70);
                tbdetvent.getColumnModel().getColumn(1).setPreferredWidth(90);
                tbdetvent.getColumnModel().getColumn(2).setPreferredWidth(140);
                tbdetvent.getColumnModel().getColumn(3).setPreferredWidth(50);
                tbdetvent.getColumnModel().getColumn(4).setPreferredWidth(50);
                tbdetvent.getColumnModel().getColumn(5).setPreferredWidth(60);
                tbdetvent.getColumnModel().getColumn(6).setPreferredWidth(60);
                tbdetvent.getColumnModel().getColumn(7).setPreferredWidth(60);

                txtfac.setText(num);

            }
        } catch (SQLException ex) {
            Logger.getLogger(GUIDevoluciones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_txtnumfactKeyPressed

    private void txtnumfactKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnumfactKeyReleased
        CargartodasFacturas(txtnumfact.getText());
    }//GEN-LAST:event_txtnumfactKeyReleased

    private void txtnumfactMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtnumfactMouseClicked
        txtnumfact.setText("");
    }//GEN-LAST:event_txtnumfactMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            conectar conect = new conectar();
            JasperReport reporte = JasperCompileManager.compileReport("src\\Reportes\\R_GraficaDevoluciones.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null,Conexion.conexion());
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
            view.setIconImage(getIconImage());
            view.setTitle("Reporte Gráfico");
        } catch (JRException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtnumfactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnumfactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnumfactActionPerformed

    public static String fechaactual() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
        return formatofecha.format(fecha);

    }

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
            java.util.logging.Logger.getLogger(GUIDevoluciones.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIDevoluciones.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIDevoluciones.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIDevoluciones.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new GUIDevoluciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnvia1x1;
    private javax.swing.JComboBox cbUsu;
    private javax.swing.JComboBox<String> cboDocu;
    private javax.swing.ButtonGroup grupoMedicamentos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbldevol;
    public static javax.swing.JLabel lblfecha;
    public static javax.swing.JLabel lblhora;
    public static javax.swing.JTable tabdevolucion;
    public static javax.swing.JTable tbdetvent;
    private javax.swing.JTextArea txtMotivos;
    public static javax.swing.JTextField txtfac;
    public static javax.swing.JLabel txtiva;
    public static javax.swing.JTextField txtnumfact;
    public static javax.swing.JLabel txtsubtot;
    public static javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables

}
