/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUInterfaces;

import Clases.Conexion;
import Clases.GenerarNumero;
import Clases.ProductCompra;
import Clases.Redondeo;
import Clases.ReportCompra;
import Clases.conectar;
import static GUInterfaces.GUIMenuPrincipal.*;
import static GUInterfaces.GUITipoCompras.txtnomproveedor;
import static GUInterfaces.GUITipoCompras.txtnumcompr;
import static GUInterfaces.GUITipoCompras.txttotalcreditocomp;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAUL
 */
public class GUICobrodeCompras extends javax.swing.JFrame {

    String formpago;
    String tarjeta;
    String cred;
    String moneda;
//    conectar cc = new conectar();
//
//    Connection cn = cc.conexion();

    public GUICobrodeCompras() {
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        ImageIcon a = new ImageIcon(getClass().getResource("/Recursos/Efectivo.png"));
        ImageIcon tam1 = new ImageIcon(a.getImage().getScaledInstance(12, 12, 1));
        tbbPaneles.setIconAt(0, tam1);

        ImageIcon b = new ImageIcon(getClass().getResource("/Recursos/Tarjeta.png"));
        ImageIcon tam2 = new ImageIcon(b.getImage().getScaledInstance(12, 12, 1));
        tbbPaneles.setIconAt(1, tam2);

        ImageIcon c = new ImageIcon(getClass().getResource("/Recursos/Moneda.png"));
        ImageIcon tam3 = new ImageIcon(c.getImage().getScaledInstance(12, 12, 1));
        tbbPaneles.setIconAt(2, tam3);

        txtpagos.requestFocus();
    }

    void abrir() {
        panelTarjetas.setEnabled(rootPaneCheckingEnabled);
    }

    /**
     * *************************************************************************************************************
     */
    void nuevostock(String codb, String cant) {
        double desp = Double.parseDouble(cant);
        String capb = "";
        double desfinalv;
        String consul = "SELECT * FROM tabla_productos WHERE codbarras='" + codb + "'";

        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consul);
            while (rs.next()) {
                capb = rs.getString(7);
            }

        } catch (Exception e) {
        }

        desfinalv = Double.parseDouble(capb) + desp;

        String modib = "UPDATE tabla_productos SET stock='" + desfinalv + "' WHERE codbarras = '" + codb + "'";
        try {
            PreparedStatement pst = Conexion.conexion().prepareStatement(modib);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("Se realizó el descuento de stock satisfactoriamente" + e);
        }

    }

    /**
     * *******************************************************************************************************************
     */
    void NumeroCompra() {
        String c = "";
        String SQL = "select max(num_comp) from tabla_compras";

        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getString(1);
            }
            if (c == null) {
                lblnumcomp.setText("00000001");
            } else {
                int j = Integer.parseInt(c);
                GenerarNumero gen = new GenerarNumero();
                gen.generar(j);
                lblnumcomp.setText(gen.serie());
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUICobrodeCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * ***************************************************************************************************
     */
    public void CalcularComp() {

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

            //LUGAR DONDE ESTA EL DESCUENTO EN LA COMPRA
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

            //COLUMNA 6 DONDE ESTA EL TOTAL
            GUIMenuPrincipal.tabla_compras.setValueAt(Math.rint(imp * 100) / 100, i, 7);

        }
        GUIMenuPrincipal.lblsubcomp.setText(Double.toString(subtotal));
        GUIMenuPrincipal.lblivacomp.setText("" + Math.rint(iva * 100) / 100);
        GUIMenuPrincipal.txtTotalComp.setText("" + Math.rint(total * 100) / 100);

    }

    /**
     * *********************************************************************************************************
     */
    void tabla_compras() {

        String InsertarSQL = "INSERT INTO tabla_compras(num_comp, proveedor, documento, subtotal,iva,pre_tot,hora,fecha) VALUES (?,?,?,?,?,?,?,?)";

        String numcomp = lblnumcomp.getText();
        String hora = lblhoras.getText();
        String documento = cbDoccomp.getSelectedItem().toString();
        String proveedor = cbProveedor.getSelectedItem().toString();
        String subtotal = lblsubcomp.getText();
        String iva = lblivacomp.getText();
        String total = txtTotalComp.getText();
        String fecha = lblfechas.getText();

        try {
            PreparedStatement pst = Conexion.conexion().prepareStatement(InsertarSQL);
            pst.setString(1, numcomp);
            pst.setString(2, proveedor);
            pst.setString(3, documento);
            pst.setString(4, subtotal);
            pst.setString(5, iva);
            pst.setString(6, total);
            pst.setString(7, hora);
            pst.setString(8, fecha);

            int n = pst.executeUpdate();
            if (n > 0) {

                GUIMensajeCompras cop = new GUIMensajeCompras();
                cop.setVisible(true);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GUICobrodeCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * ***************************************************************************************************
     */
    void detallecomprov() {

        for (int i = 0; i < tabla_compras.getRowCount(); i++) {
            String InsertarSQL = "INSERT INTO detallecompras(num_comp,cod_pro,des_pro,cant_pro,pre_unit,desct,total,tipo_comp,proveedor,empleado,hora,fecha) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            String tipo_compra = cbDoccomp.getSelectedItem().toString();
            String empleado = cbUsuarioCompra.getSelectedItem().toString();

            String fecha_compra = lblfechas.getText();
            String numcomp = lblnumcomp.getText();
            String prov = cbProveedor.getSelectedItem().toString();
            String hora = lblhoras.getText();
//            String total = txtTotalComp.getText();
            
            String codpro = tabla_compras.getValueAt(i, 0).toString();
            String despro = tabla_compras.getValueAt(i, 1).toString();
            String cantpro = tabla_compras.getValueAt(i, 4).toString();
            String preunit = tabla_compras.getValueAt(i, 3).toString();
            String desct = tabla_compras.getValueAt(i, 6).toString();
            String total = tabla_compras.getValueAt(i, 7).toString();

            try {
                PreparedStatement pst = Conexion.conexion().prepareStatement(InsertarSQL);
                pst.setString(1, numcomp);
                pst.setString(2, codpro);
                pst.setString(3, despro);
                pst.setString(4, cantpro);
                pst.setString(5, preunit);
                pst.setString(6, desct);
                pst.setString(7, total);
                pst.setString(8, tipo_compra);
                pst.setString(9, prov);
                pst.setString(10, empleado);
                pst.setString(11, hora);
                pst.setString(12, fecha_compra);

                pst.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(GUICobrodeCompras.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * ************************************************************************************************
     */
    void detallekardex() {

        for (int i = 0; i < tabla_compras.getRowCount(); i++) {

            String InsertarSQL = "INSERT INTO tabla_kardex (num_fact, cod_prod, producto,motivo,documento,proveedor,pre_unit,entrada,salida,total,fecha) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

            String num_fact = lblnumcomp.getText();
            String proveedor = cbProveedor.getSelectedItem().toString();
            String documento = cbDoccomp.getSelectedItem().toString();
            String fecha = lblfechas.getText();
            String motivo = cbDoccomp.getSelectedItem().toString();

            String cod_prod = tabla_compras.getValueAt(i, 0).toString();
            String des_comp = tabla_compras.getValueAt(i, 1).toString();
            String pre_comp = tabla_compras.getValueAt(i, 3).toString();
            String cant_comp = tabla_compras.getValueAt(i, 4).toString();
            String sal_comp = tabla_compras.getValueAt(i, 5).toString();
            String pre_tot = tabla_compras.getValueAt(i, 7).toString();
//            String pre_tot = txtTotalComp.getText();
            try {

                PreparedStatement pst = Conexion.conexion().prepareStatement(InsertarSQL);
                pst.setString(1, num_fact);
                pst.setString(2, cod_prod);
                pst.setString(3, des_comp);
                pst.setString(4, motivo);
                pst.setString(5, documento);
                pst.setString(6, proveedor);
                pst.setString(7, pre_comp);
                pst.setString(8, cant_comp);
                pst.setString(9, sal_comp);
                pst.setString(10, pre_tot);
                pst.setString(11, fecha);

                pst.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(GUICobrodeCompras.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void RedondearSubtotal() {
        double valor = Double.parseDouble(GUIMenuPrincipal.lblsubcomp.getText());
        String val = valor + "";
        BigDecimal big = new BigDecimal(val);
        big = big.setScale(2, RoundingMode.HALF_UP);
        lblsubcomp.setText("" + big);
    }
    
    public static double Calculados() {
        double monto = Double.parseDouble(GUICobrodeCompras.lblcantidadcomp.getText());
        double ingreso = Double.parseDouble(GUICobrodeCompras.txtpagos.getText());
        double vuelto = ingreso - monto;
        return Redondeo.redondear(vuelto);
    }

    void contado() {
        if (lblcontado.getText().equals("Contado")) {
            formpago = "Contado";
        }
    }

    void credito() {
        if (lblcredito.getText().equals("Crédito")) {
            formpago = "Crédito";
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

        btgMonedas = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        lblcontado = new javax.swing.JLabel();
        lblcredito = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblcantidadcomp = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbltarjeta = new javax.swing.JLabel();
        tbbPaneles = new javax.swing.JTabbedPane();
        panelEfectivo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtpagos = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtcambios = new javax.swing.JTextField();
        panelTarjetas = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        cbTarjetas = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        txtnum = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        rdDolares = new javax.swing.JRadioButton();
        rdEuros = new javax.swing.JRadioButton();
        rdSoles = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("PAGAR COMPRA");
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/Efectivo.png")).getImage());
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204), 6));
        jPanel1.setOpaque(false);

        jPanel2.setBackground(new java.awt.Color(0, 153, 204));

        jPanel5.setOpaque(false);
        jPanel5.setLayout(new java.awt.GridLayout(2, 0));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Access.png"))); // NOI18N
        jButton1.setText("Pagar");
        jButton1.setToolTipText("Pagar compra");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/FacturaN.png"))); // NOI18N
        jButton2.setText("Factura");
        jButton2.setToolTipText("Ver factura proveedor");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Regresar.png"))); // NOI18N
        jButton3.setText("Salir");
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );

        lblcontado.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        lblcontado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblcontado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Efectivo.png"))); // NOI18N
        lblcontado.setText("Efectivo");
        lblcontado.setToolTipText("");
        lblcontado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblcontado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblcontado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblcontado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblcontadoMousePressed(evt);
            }
        });

        lblcredito.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        lblcredito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblcredito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Credito.png"))); // NOI18N
        lblcredito.setText("Crédito");
        lblcredito.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblcredito.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblcredito.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblcredito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblcreditoMousePressed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 102, 255));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Pago de compra");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jLabel6.setFont(new java.awt.Font("Courier New", 1, 38)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("$");

        lblcantidadcomp.setFont(new java.awt.Font("Courier New", 1, 40)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Total a pagar");

        lbltarjeta.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        lbltarjeta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbltarjeta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Tarjeta.png"))); // NOI18N
        lbltarjeta.setText("Tarjeta");
        lbltarjeta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbltarjeta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbltarjeta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lbltarjeta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbltarjetaMousePressed(evt);
            }
        });

        panelEfectivo.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Pago con");

        txtpagos.setBackground(new java.awt.Color(255, 255, 204));
        txtpagos.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        txtpagos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtpagos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpagosKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Cambio");

        txtcambios.setBackground(new java.awt.Color(255, 255, 204));
        txtcambios.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        txtcambios.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout panelEfectivoLayout = new javax.swing.GroupLayout(panelEfectivo);
        panelEfectivo.setLayout(panelEfectivoLayout);
        panelEfectivoLayout.setHorizontalGroup(
            panelEfectivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEfectivoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEfectivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtpagos, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtcambios))
                .addContainerGap())
        );
        panelEfectivoLayout.setVerticalGroup(
            panelEfectivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEfectivoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtpagos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtcambios, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbbPaneles.addTab("Efectivo", panelEfectivo);

        panelTarjetas.setOpaque(false);

        jLabel9.setText("Referecia de tarjeta");

        cbTarjetas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbTarjetas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Master Cards", "Visa", "Diners Club" }));

        jLabel10.setText("N° designado");

        txtnum.setBackground(new java.awt.Color(255, 255, 204));
        txtnum.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout panelTarjetasLayout = new javax.swing.GroupLayout(panelTarjetas);
        panelTarjetas.setLayout(panelTarjetasLayout);
        panelTarjetasLayout.setHorizontalGroup(
            panelTarjetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTarjetasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTarjetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbTarjetas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelTarjetasLayout.createSequentialGroup()
                        .addGroup(panelTarjetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(0, 228, Short.MAX_VALUE))
                    .addComponent(txtnum))
                .addContainerGap())
        );
        panelTarjetasLayout.setVerticalGroup(
            panelTarjetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTarjetasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTarjetas, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnum, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbbPaneles.addTab("Tarjetas de crédito", panelTarjetas);

        jPanel4.setOpaque(false);

        btgMonedas.add(rdDolares);
        rdDolares.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rdDolares.setText("Dólares");
        rdDolares.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rdDolares.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rdDolares.setOpaque(false);
        rdDolares.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        rdDolares.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        rdDolares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdDolaresActionPerformed(evt);
            }
        });

        btgMonedas.add(rdEuros);
        rdEuros.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rdEuros.setText("Euros");
        rdEuros.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rdEuros.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rdEuros.setOpaque(false);
        rdEuros.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        rdEuros.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        rdEuros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdEurosActionPerformed(evt);
            }
        });

        btgMonedas.add(rdSoles);
        rdSoles.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rdSoles.setText("Nuevos Soles");
        rdSoles.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rdSoles.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rdSoles.setOpaque(false);
        rdSoles.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        rdSoles.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        rdSoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdSolesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdDolares, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rdEuros, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rdSoles, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rdSoles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdDolares, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdEuros))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        tbbPaneles.addTab("Moneda", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblcontado, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblcredito, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbltarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblcantidadcomp, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tbbPaneles))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(lblcantidadcomp, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblcontado, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblcredito, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbltarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tbbPaneles, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblcontadoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblcontadoMousePressed
        tbbPaneles.setSelectedIndex(0);
        try {
            formpago = "Contado";
            String opc = GUIMenuPrincipal.cbFormadepago.getSelectedItem().toString();
            String pay;
            pay = formpago;
            GUIMenuPrincipal.cbFormadepago.setSelectedItem(formpago);
            GUIMenuPrincipal.cbFormadepago.setSelectedItem(pay);
        } catch (Exception ex) {
            System.out.println("No hay dinero para cobrar");
        }
    }//GEN-LAST:event_lblcontadoMousePressed

    private void lblcreditoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblcreditoMousePressed
        try {
            cred = "Crédito";
            String opc = GUIMenuPrincipal.cbFormadepago.getSelectedItem().toString();
            String credi;
            credi = cred;
            GUIMenuPrincipal.cbFormadepago.setSelectedItem(cred);
            GUIMenuPrincipal.cbFormadepago.setSelectedItem(credi);

            GUITipoCompras proc = new GUITipoCompras();
            proc.setVisible(true);

            String total;
            String factura;
            String pro;

            total = txtTotalComp.getText();
            txttotalcreditocomp.setText(total);

            factura = lblnumcomp.getText();
            txtnumcompr.setText(factura);

            pro = cbProveedor.getSelectedItem().toString();
            txtnomproveedor.setText(pro);
        } catch (Exception ex) {
            System.out.println("No hay dinero para cobrar");
        }
    }//GEN-LAST:event_lblcreditoMousePressed

    private void txtpagosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpagosKeyReleased
        try {
            double Calculos = GUICobrodeCompras.Calculados();
            txtcambios.setText(String.format("%.0f",Calculos));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtpagosKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String capcod = "", capcan = "";
        for (int i = 0; i < GUIMenuPrincipal.tabla_compras.getRowCount(); i++) {
            capcod = GUIMenuPrincipal.tabla_compras.getValueAt(i, 0).toString();
            capcan = GUIMenuPrincipal.tabla_compras.getValueAt(i, 4).toString();
            nuevostock(capcod, capcan);

        }
        txtcambios.setText("");
        txtpagos.setText("");
        lblcantidadcomp.setText("0.0");

        tabla_compras();
        detallecomprov();
        detallekardex();
        contado();
        credito();

        DefaultTableModel modelo = (DefaultTableModel) tabla_compras.getModel();
        int a = tabla_compras.getRowCount() - 1;
        int i;
        for (i = a; i >= 0; i--) {
            modelo.removeRow(i);
        }

        NumeroCompra();

        this.dispose();
        GUIMenuPrincipal.lblsubcomp.setText("0.0");
        GUIMenuPrincipal.lblivacomp.setText("0.0");
        GUIMenuPrincipal.txtTotalComp.setText("0.0");
        GUIMenuPrincipal.cbUsuarioCompra.setSelectedIndex(0);
        GUIMenuPrincipal.cbDoccomp.setSelectedIndex(0);
//        lbltotal.setText(" Productos en la venta actual =====" + tabla_ventas.getRowCount() + "=====");
        GUIMenuPrincipal.txtbarcodcomp.requestFocus();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void lbltarjetaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbltarjetaMousePressed
        tbbPaneles.setSelectedIndex(1);
        try {
            tarjeta = "Tarjeta";
            String op = GUIMenuPrincipal.cbFormadepago.getSelectedItem().toString();
            String pay;
            pay = tarjeta;
            GUIMenuPrincipal.cbFormadepago.setSelectedItem(tarjeta);
            GUIMenuPrincipal.cbFormadepago.setSelectedItem(pay);
        } catch (Exception ex) {
            System.out.println("No hay tarjeta para cobrar");
        }
    }//GEN-LAST:event_lbltarjetaMousePressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {

            ArrayList<ProductCompra> listaProd = new ArrayList<ProductCompra>();
            int filas = tabla_compras.getRowCount();
            for (int i = 0; i < filas; i++) {
                ProductCompra p = new ProductCompra(tabla_compras.getValueAt(i, 0).toString(),
                        tabla_compras.getValueAt(i, 1).toString(), tabla_compras.getValueAt(i, 3).toString(),
                        tabla_compras.getValueAt(i, 4).toString(), tabla_compras.getValueAt(i, 6).toString(),
                        tabla_compras.getValueAt(i, 7).toString());
                listaProd.add(p);
            }

            HashMap<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("nunfac", lblnumcomp.getText());
            parametros.put("vendedor", cbUsuarioCompra.getSelectedItem().toString());
            parametros.put("nomape", cbProveedor.getSelectedItem().toString());
            parametros.put("direccion", txtdireccionprov.getText());
            parametros.put("fecha", lblfechas.getText());
            parametros.put("subtotal", lblsubcomp.getText());
            parametros.put("iva", lblivacomp.getText());
            parametros.put("total", txtTotalComp.getText());
            parametros.put("docu", cbDoccomp.getSelectedItem().toString());
            parametros.put("tipo", cbDoccomp.getSelectedItem().toString());
            parametros.put("observ", txtObservaciones.getText());
            parametros.put("moneda", moneda);
            ReportCompra report = new ReportCompra();

            if (report.generarNuevoReporte("src//Reportes//Compras.jrxml", parametros, listaProd)) {
//         if(report.generarNuevoReporte("C:\\SystemFarmacys\\src\\REPORTES\\Factura.jrxml", parametros, listaProductos)){
                System.out.println(" ");
            } else {
                System.out.println("Error del Reporte");
            }
        } catch (Exception ex) {
            Logger.getLogger(GUICobrodeCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void rdDolaresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdDolaresActionPerformed
        if (rdDolares.isSelected() == true) {
            moneda = "Dólares";
        } else {
            moneda = "";
        }
    }//GEN-LAST:event_rdDolaresActionPerformed

    private void rdEurosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdEurosActionPerformed
        if (rdEuros.isSelected() == true) {
            moneda = "Euros";
        } else {
            moneda = "";
        }
    }//GEN-LAST:event_rdEurosActionPerformed

    private void rdSolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdSolesActionPerformed
        if (rdSoles.isSelected() == true) {
            moneda = "Nuevos Soles";
        } else {
            moneda = "";
        }
    }//GEN-LAST:event_rdSolesActionPerformed

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
            java.util.logging.Logger.getLogger(GUICobrodeCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUICobrodeCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUICobrodeCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUICobrodeCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUICobrodeCompras().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgMonedas;
    public static javax.swing.JComboBox cbTarjetas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    public static javax.swing.JLabel lblcantidadcomp;
    private javax.swing.JLabel lblcontado;
    private javax.swing.JLabel lblcredito;
    private javax.swing.JLabel lbltarjeta;
    private javax.swing.JPanel panelEfectivo;
    private javax.swing.JPanel panelTarjetas;
    private javax.swing.JRadioButton rdDolares;
    private javax.swing.JRadioButton rdEuros;
    private javax.swing.JRadioButton rdSoles;
    private javax.swing.JTabbedPane tbbPaneles;
    private javax.swing.JTextField txtcambios;
    public static javax.swing.JTextField txtnum;
    public static javax.swing.JTextField txtpagos;
    // End of variables declaration//GEN-END:variables
}
