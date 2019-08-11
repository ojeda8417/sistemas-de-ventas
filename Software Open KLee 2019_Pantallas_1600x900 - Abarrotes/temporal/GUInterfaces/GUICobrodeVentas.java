/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUInterfaces;

import Clases.Conexion;
import Clases.GenerarNumero;
import Clases.ProductoFactura;
import Clases.Redondeo;
import Clases.ReportFactura;
import Clases.Ticket;
import Clases.TicketImp;
import Clases.conectar;
import static GUInterfaces.GUIMenuPrincipal.*;
import static GUInterfaces.GUITipoVentas.txtclientes;
import static GUInterfaces.GUITipoVentas.txtnumfact;
import static GUInterfaces.GUITipoVentas.txttotalcreditos;
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
import net.sf.jasperreports.engine.JasperPrint;


/**
 *
 * @author Netfex
 */
public class GUICobrodeVentas extends javax.swing.JFrame {

    String formpago;
    String tarjeta;
    String cred;
    String documento;
    int contador;

//    conectar cc = new conectar();
//    Connection cn = cc.conexion();

    PreparedStatement pstm = null;
    ResultSet rs = null;
//    conectar conect = new conectar();
//    Connection cnt = conect.conexion();

    /**FACTURA**/
    PreparedStatement pst = null;
    ResultSet rst = null;
//    conectar conec = new conectar();
//    Connection c = conec.conexion();
    
       
    private static JasperPrint reportFilled;

    public GUICobrodeVentas() {
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        ImageIcon a = new ImageIcon(getClass().getResource("/Recursos/Efectivo.png"));
        ImageIcon tam1 = new ImageIcon(a.getImage().getScaledInstance(12, 12, 1));
        tbbPaneles.setIconAt(0, tam1);

        ImageIcon b = new ImageIcon(getClass().getResource("/Recursos/Tarjeta.png"));
        ImageIcon tam2 = new ImageIcon(b.getImage().getScaledInstance(12, 12, 1));
        tbbPaneles.setIconAt(1, tam2);
        txtpagos.requestFocus();
    }

    void abrir() {
        panelTarjetas.setEnabled(rootPaneCheckingEnabled);
    }

    /**
     * **************************************************************************************************************
     */
    void descontarstock(String codb, String cant) {
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

        desfinalv = Double.parseDouble(capb) - desp;

        String modib = "UPDATE tabla_productos SET stock='" + desfinalv + "' WHERE codbarras = '" + codb + "'";
        try {
            PreparedStatement pst = Conexion.conexion().prepareStatement(modib);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("Se realizó el descuento de stock satisfactoriamente" + e);
        }

    }

    /**
     * ************************************************************************************************************
     */
    void numeros() {
        int j;
        int cont = 1;
        String num = "";
        String c = "";
        String SQL = "select max(num_fact) from tabla_ventas";

        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getString(1);
            }

            if (c == null) {
                lblnumventa.setText("00000001");
            } else {
                j = Integer.parseInt(c);
                GenerarNumero gen = new GenerarNumero();
                gen.generar(j);
                lblnumventa.setText(gen.serie());

            }
        } catch (SQLException ex) {
            Logger.getLogger(GUICobrodeVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * ************************************************************************************************
     */
    public void calcular() {

        String pre;
        String can;
        String desc = "";
        double desct = 0;
        double iva = 0;
        double total = 0;
        double subtotal = 0;
        double precio = 0;
        double cantidad;
        double impParcial = 0;
        double descuentos = 0;
        double totparcial = 0;

        for (int i = 0; i < GUIMenuPrincipal.tabla_ventas.getRowCount(); i++) {
            pre = GUIMenuPrincipal.tabla_ventas.getValueAt(i, 2).toString();
            can = GUIMenuPrincipal.tabla_ventas.getValueAt(i, 3).toString();

            precio = Double.parseDouble(pre);
            cantidad = Double.parseDouble(can);

            if (GUIMenuPrincipal.tabla_ventas.getValueAt(i, 5) == null) {
                totparcial = precio * cantidad;

            } else {

                desc = GUIMenuPrincipal.tabla_ventas.getValueAt(i, 5).toString();
                descuentos = Double.parseDouble(desc);
                impParcial = precio * cantidad;
                desct = (precio * descuentos) * cantidad;
                totparcial = impParcial - desct;
            }

            subtotal = subtotal + totparcial;
            iva = subtotal * 0.12;
            total = subtotal + iva;

            GUIMenuPrincipal.tabla_ventas.setValueAt(Math.rint(totparcial * 100) / 100, i, 6);
        }

        GUIMenuPrincipal.txtsubtotalvent.setText(Double.toString(subtotal));
        GUIMenuPrincipal.txtiva.setText("" + Math.rint(iva * 100) / 100);
        GUIMenuPrincipal.txtTotales.setText("" + Math.rint(total * 100) / 100);

    }

    public static double Calculos() {
        double monto = Double.parseDouble(GUICobrodeVentas.lblcantidad.getText());
        double ingreso = Double.parseDouble(GUICobrodeVentas.txtpagos.getText());
        double vuelto = ingreso - monto;
        return Redondeo.redondear(vuelto);
    }

    public void RedondearSubtotal() {
        double valor = Double.parseDouble(GUIMenuPrincipal.txtsubtotalvent.getText());
        String val = valor + "";
        BigDecimal big = new BigDecimal(val);
        big = big.setScale(2, RoundingMode.HALF_UP);
        txtsubtotalvent.setText("" + big);
    }

    void ventas() {

        String InsertarSQLV = "INSERT INTO tabla_ventas (num_fact, cliente, subtotal, iva, "
                + "total, formapago, hora, fecha) VALUES (?,?,?,?,?,?,?,?)";
        String hora = lblhoras.getText();
        String num_fac = lblnumventa.getText();
        String client = txtclienteventas.getText();
        String subtotal = txtsubtotalvent.getText();
        String iva = txtiva.getText();
        String total = txtTotales.getText();
        String formap = cbTipoPagosVenta.getSelectedItem().toString();
        String fecha = lblfechas.getText();
        try {
            PreparedStatement pst = Conexion.conexion().prepareStatement(InsertarSQLV);
            pst.setString(1, num_fac);
            pst.setString(2, client);
            pst.setString(3, subtotal);
            pst.setString(4, iva);
            pst.setString(5, total);
            pst.setString(6, formap);
            pst.setString(7, hora);
            pst.setString(8, fecha);

            int n = pst.executeUpdate();
            if (n > 0) {

                GUIMensajeVenta cpv = new GUIMensajeVenta();
                cpv.setVisible(true);
            }
        } catch (SQLException ex) {

            System.out.println("Error en " + ex);

            Logger.getLogger(GUICobrodeVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void detalleventas() {

        for (int i = 0; i < tabla_ventas.getRowCount(); i++) {

            String InsertarSQLDV = "INSERT INTO detalleventas (num_fact, cod_pro, "
                    + "des_comp, cant_comp, pre_comp, descuento,pre_tot, "
                    + "formapago,referencia,numreferencia,usuario, hora, fecha) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            String hor = lblhoras.getText();
            String num_fact = lblnumventa.getText();
            String usuario = cbUsuarioVenta.getSelectedItem().toString();
            String formapag = cbTipoPagosVenta.getSelectedItem().toString();
            String ref = cbTarjetas.getSelectedItem().toString();
            String nref = txtnum.getText();
            String fecha = lblfechas.getText();

            String cod_pro = tabla_ventas.getValueAt(i, 0).toString();
            String des_comp = tabla_ventas.getValueAt(i, 1).toString();
            String pre_comp = tabla_ventas.getValueAt(i, 2).toString();
            String cant_comp = tabla_ventas.getValueAt(i, 3).toString();
            String descuento = tabla_ventas.getValueAt(i, 5).toString();
            String pre_tor = tabla_ventas.getValueAt(i, 6).toString();

            try {

                PreparedStatement pst = Conexion.conexion().prepareStatement(InsertarSQLDV);
                pst.setString(1, num_fact);
                pst.setString(2, cod_pro);
                pst.setString(3, des_comp);
                pst.setString(4, cant_comp);
                pst.setString(5, pre_comp);
                pst.setString(6, descuento);
                pst.setString(7, pre_tor);
                pst.setString(8, formapag);
                pst.setString(9, ref);
                pst.setString(10, nref);
                pst.setString(11, usuario);
                pst.setString(12, hor);
                pst.setString(13, fecha);
                pst.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(GUICobrodeVentas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void detallekardex() {

        for (int i = 0; i < tabla_ventas.getRowCount(); i++) {

            String InsertarSQLK = "INSERT INTO tabla_kardex (num_fact, cod_prod, producto,motivo,documento,"
                    + "proveedor,pre_unit,entrada,salida,total,fecha) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

            String num_fact = lblnumventa.getText();
            String emp = cbUsuarioVenta.getSelectedItem().toString();
            String doc = cbTipoventa.getSelectedItem().toString();
            String fec = lblfechas.getText();
            String mot = cbModo.getSelectedItem().toString();

            String cod_prod = tabla_ventas.getValueAt(i, 0).toString();
            String des_prod = tabla_ventas.getValueAt(i, 1).toString();
            String pre_prod = tabla_ventas.getValueAt(i, 2).toString();
            String cant_sal = tabla_ventas.getValueAt(i, 3).toString();
            String entr_prod = tabla_ventas.getValueAt(i, 4).toString();
            String pre_tot = tabla_ventas.getValueAt(i, 6).toString();
//            String pre_tot = txtTotales.getText();
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

    public static double Calculados() {
        double monto = Double.parseDouble(GUICobrodeVentas.lblcantidad.getText());
        double ingreso = Double.parseDouble(GUICobrodeVentas.txtpagos.getText());
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
 public String[] SacarDatos() {
        String data[] = new String[8];
        try {
            pstm = Conexion.conexion().prepareStatement("SELECT nombre,encabezado,direccion,telefono,ruc, pie,logo FROM tabla_tickets");
            rs = pstm.executeQuery();

            while (rs.next()) {
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                data[3] = rs.getString(4);
                data[4] = rs.getString(5);
                data[5] = rs.getString(6);
                data[6] = rs.getString(7);
            }

        } catch (Exception ex) {

        }
        return data;
    }

    public void VerTicket() {

        String data[] = new String[7];
        data = SacarDatos();
        HashMap<String, Object> parametross = new HashMap<String, Object>();
        parametross.put("nombre", data[0]);
        parametross.put("encabezado", data[1]);
        parametross.put("direccion", data[2]);
        parametross.put("telefono", data[3]);
        parametross.put("ruc", data[4]);
        parametross.put("pie", data[5]);
        parametross.put("logo", data[6]);

    }
    /**
     * ***********DOCUMENTO FACTURA**********
     */
    public String[] SacarDatosF() {
        String dataf[] = new String[8];
        try {
            pst = Conexion.conexion().prepareStatement("SELECT nombre,encabezado,direccion,telefono,ruc, pie,logo FROM tabla_tickets");
            rst = pst.executeQuery();

            while (rst.next()) {
                dataf[0] = rst.getString(1);
                dataf[1] = rst.getString(2);
                dataf[2] = rst.getString(3);
                dataf[3] = rst.getString(4);
                dataf[4] = rst.getString(5);
                dataf[5] = rst.getString(6);
                dataf[6] = rst.getString(7);
            }

        } catch (Exception exc) {

        }
        return dataf;
    }

    public void VerFact() {

        String dataf[] = new String[7];
        dataf = SacarDatosF();
        HashMap<String, Object> parametrosf = new HashMap<String, Object>();
        parametrosf.put("nombre", dataf[0]);
        parametrosf.put("encabezado", dataf[1]);
        parametrosf.put("direccion", dataf[2]);
        parametrosf.put("telefono", dataf[3]);
        parametrosf.put("ruc", dataf[4]);
        parametrosf.put("pie", dataf[5]);
        parametrosf.put("logo", dataf[6]);

    }

    /**
     * ***********DOCUMENTO FACTURA NORMAL**********
     */
    public String[] SacarDatosFN() {
        String datafn[] = new String[8];
        try {
            pst = Conexion.conexion().prepareStatement("SELECT nombre,encabezado,direccion,telefono,ruc, pie,logo FROM tabla_tickets");
            rst = pst.executeQuery();

            while (rst.next()) {
                datafn[0] = rst.getString(1);
                datafn[1] = rst.getString(2);
                datafn[2] = rst.getString(3);
                datafn[3] = rst.getString(4);
                datafn[4] = rst.getString(5);
                datafn[5] = rst.getString(6);
                datafn[6] = rst.getString(7);
            }

        } catch (Exception excp) {

        }
        return datafn;
    }

    public void VerFactN() {

        String datafn[] = new String[7];
        datafn = SacarDatosFN();
        HashMap<String, Object> parametrosf = new HashMap<String, Object>();
        parametrosf.put("nombre", datafn[0]);
        parametrosf.put("encabezado", datafn[1]);
        parametrosf.put("direccion", datafn[2]);
        parametrosf.put("telefono", datafn[3]);
        parametrosf.put("ruc", datafn[4]);
        parametrosf.put("pie", datafn[5]);
        parametrosf.put("logo", datafn[6]);

    }

    /*public ImageIcon img (int cod) {

     String sql= ("select logo from tabla_tickets where cod = "+cod);
     ImageIcon ii =null;
     InputStream is = null;
        
     try{
     Statement st = cn.createStatement();
     ResultSet rs = st.executeQuery(sql);
     rs = pstm.executeQuery(sql);
            
     if(rs.next()){
                
     rs.getBinaryStream(4);
     BufferedImage bi = ImageIO.read(is);
     ii= new ImageIcon(bi);
                
     }
            
     }catch(SQLException ex){ ex.printStackTrace();}
     catch(IOException ex) { ex.printStackTrace();}
            
     return ii;    
        
     }*/
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
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        lblcontado = new javax.swing.JLabel();
        lblcredito = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblcantidad = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("COBRAR VENTA");
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/Efectivo.png")).getImage());
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204), 6));
        jPanel1.setOpaque(false);

        jPanel2.setBackground(new java.awt.Color(0, 153, 204));

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

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridLayout(5, 0));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Access.png"))); // NOI18N
        jButton1.setText("Cobrar");
        jButton1.setToolTipText("Cobrar venta");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Ticket.png"))); // NOI18N
        jButton4.setText("Ticket");
        jButton4.setToolTipText("Ver ticket e imprimir");
        jButton4.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4);

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Pedido.png"))); // NOI18N
        jButton6.setText("Pedido");
        jButton6.setToolTipText("Ver pedido e imprimir");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton6);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/FacturaE.png"))); // NOI18N
        jButton2.setText("Factura");
        jButton2.setToolTipText("Ver factura electrónica");
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2);

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/FacturaN.png"))); // NOI18N
        jButton5.setText("F.Normal");
        jButton5.setToolTipText("Ver factura A4");
        jButton5.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton5);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        jLabel7.setText("Cobro de venta");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(255, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jLabel6.setFont(new java.awt.Font("Courier New", 1, 38)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("$");

        lblcantidad.setFont(new java.awt.Font("Courier New", 1, 40)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Total a cobrar");

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
        jLabel2.setText("Paga con");

        txtpagos.setBackground(new java.awt.Color(255, 255, 204));
        txtpagos.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        txtpagos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtpagos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpagosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpagosKeyTyped(evt);
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
            .addGroup(panelEfectivoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEfectivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtpagos, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
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

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Referecia de tarjeta");

        cbTarjetas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbTarjetas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Master Cards", "Visa", "Diners Club" }));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("N° designado");

        txtnum.setBackground(new java.awt.Color(255, 255, 204));
        txtnum.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtnum.setText("0");
        txtnum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnumKeyTyped(evt);
            }
        });

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
                        .addGap(0, 217, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tbbPaneles, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblcontado, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblcredito, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbltarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(50, 50, 50)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
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
                    .addComponent(lblcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblcontadoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblcontadoMousePressed
        tbbPaneles.setSelectedIndex(0);
        try {
            formpago = "Contado";
            String opc = cbTipoPagosVenta.getSelectedItem().toString();
            String pay;
            pay = formpago;
            cbTipoPagosVenta.setSelectedItem(formpago);
            GUIMenuPrincipal.cbTipoPagosVenta.setSelectedItem(pay);
        } catch (Exception ex) {
            System.out.println("No hay dinero para cobrar");
        }
    }//GEN-LAST:event_lblcontadoMousePressed

    private void lblcreditoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblcreditoMousePressed
        try {
            cred = "Crédito";
            String opc = cbTipoPagosVenta.getSelectedItem().toString();
            String credi;
            credi = cred;
            cbTipoPagosVenta.setSelectedItem(cred);
            GUIMenuPrincipal.cbTipoPagosVenta.setSelectedItem(credi);

            GUITipoVentas pro = new GUITipoVentas();
            pro.setVisible(true);

            String total;
            String factura;
            String cli;

            total = txtTotales.getText();
            txttotalcreditos.setText(total);

            factura = lblnumventa.getText();
            txtnumfact.setText(factura);

            cli = txtclienteventas.getText();
            txtclientes.setText(cli);

        } catch (Exception ex) {
            System.out.println("No hay dinero para cobrar");
        }
    }//GEN-LAST:event_lblcreditoMousePressed

    private void txtpagosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpagosKeyReleased
        try {
            double Calculos = GUICobrodeVentas.Calculados();
            txtcambios.setText(String.format("%.0f",Calculos));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtpagosKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if ((txtsubtotalvent.getText().equals(""))) {
//            JOptionPane.showMessageDialog(this, "No ingreso cliente, productos o realice operación");
        } else {
            String capcod = "", capcan = "";
            for (int i = 0; i < GUIMenuPrincipal.tabla_ventas.getRowCount(); i++) {
                capcod = GUIMenuPrincipal.tabla_ventas.getValueAt(i, 0).toString();
                capcan = GUIMenuPrincipal.tabla_ventas.getValueAt(i, 3).toString();
                descontarstock(capcod, capcan);

            }
            txtcambios.setText("");
            txtpagos.setText("");
            lblcantidad.setText("0.0");
            ventas();
            detalleventas();
            detallekardex();
            contado();
            credito();

            DefaultTableModel modelo = (DefaultTableModel) tabla_ventas.getModel();
            int a = tabla_ventas.getRowCount() - 1;
            int i;
            for (i = a; i >= 0; i--) {
                modelo.removeRow(i);
            }

            numeros();
        }
        this.dispose();
        GUIMenuPrincipal.txtsubtotalvent.setText("0.0");
        GUIMenuPrincipal.txtiva.setText("0.0");
        GUIMenuPrincipal.txtTotales.setText("0.0");
        cbUsuarioVenta.setSelectedIndex(0);
        cbTipoventa.setSelectedIndex(0);
        lblimagenventas.setVisible(false);
        lbltotal.setText(" Productos en la venta actual =====" + tabla_ventas.getRowCount() + "=====");
        GUIMenuPrincipal.txtcodbarrasvent.requestFocus();
        GUIMenuPrincipal.lblimagenventas.setText("");
        GUIMenuPrincipal.txtclienteventas.setText("Consumidor final");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
       try {
            documento = "Ticket";
            String opc = cbTipoventa.getSelectedItem().toString();
            String docu;
            docu = documento;
            cbTipoventa.setSelectedItem(documento);
            GUIMenuPrincipal.cbTipoPagosVenta.setSelectedItem(docu);
        } catch (Exception ex) {
            System.out.println("No hay documento");
        }

        try {

            ArrayList<Ticket> data = new ArrayList<Ticket>();
            int filas = tabla_ventas.getRowCount();
            for (int i = 0; i < filas; i++) {
                Ticket p = new Ticket(tabla_ventas.getValueAt(i, 3).toString(),
                        tabla_ventas.getValueAt(i, 1).toString(), tabla_ventas.getValueAt(i, 2).toString(),
                        tabla_ventas.getValueAt(i, 6).toString());
                data.add(p);
            }

            String datas[] = new String[6];
            datas = SacarDatos();
            HashMap<String, Object> parametross = new HashMap<String, Object>();
            parametross.put("nombre", datas[0]);
            parametross.put("encabezados", datas[1]);
            parametross.put("direccion", datas[2]);
            parametross.put("telefono", datas[3]);
            parametross.put("ruc", datas[4]);
            parametross.put("pie", datas[5]);
            parametross.put("logo", datas[6]);

            parametross.put("logo", this.getClass().getResourceAsStream("/Recursos/OpenKleeInformes.png"));
            parametross.put("nunfac", lblnumventa.getText());
            parametross.put("vendedor", cbUsuarioVenta.getSelectedItem().toString());
            parametross.put("document", cbTipoventa.getSelectedItem().toString());
            parametross.put("nomape", txtclienteventas.getText());
            parametross.put("fecha", lblfechas.getText());
            parametross.put("subtotal", txtsubtotalvent.getText());
            parametross.put("iva", txtiva.getText());
            parametross.put("total", txtTotales.getText());

            TicketImp reportes = new TicketImp();

            if (reportes.generarNuevoReporte("src\\Reportes\\Ticket.jrxml", parametross, data)) {

            } else {
                System.out.println("Error del Reporte");
            }
        } catch (Exception ex) {
            Logger.getLogger(GUICobrodeVentas.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void lbltarjetaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbltarjetaMousePressed
        tbbPaneles.setSelectedIndex(1);
        try {
            tarjeta = "Tarjeta de crédito";
            String op = cbTipoPagosVenta.getSelectedItem().toString();
            String pay;
            pay = tarjeta;
            cbTipoPagosVenta.setSelectedItem(tarjeta);
            GUIMenuPrincipal.cbTipoPagosVenta.setSelectedItem(pay);
        } catch (Exception ex) {
            System.out.println("No hay tarjeta para cobrar");
        }
    }//GEN-LAST:event_lbltarjetaMousePressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            documento = "Factura";
            String opc = cbTipoventa.getSelectedItem().toString();
            String docu;
            docu = documento;
            cbTipoventa.setSelectedItem(documento);
            GUIMenuPrincipal.cbTipoPagosVenta.setSelectedItem(docu);
        } catch (Exception ex) {
            System.out.println("No hay dinero para cobrar");
        }

        try {

            String dataf[] = new String[7];
            dataf = SacarDatosF();
            HashMap<String, Object> parametrosf = new HashMap<String, Object>();
            parametrosf.put("nombre", dataf[0]);
            parametrosf.put("encabezado", dataf[1]);
            parametrosf.put("direccion", dataf[2]);
            parametrosf.put("telefono", dataf[3]);
            parametrosf.put("ruc", dataf[4]);
            parametrosf.put("pie", dataf[5]);
            parametrosf.put("logo", dataf[6]);
//                parametrosf.put("foto", this.getClass().getResourceAsStream("/Recursos/OpenKleeInformes.png"));

            ArrayList<ProductoFactura> listaProductos = new ArrayList<ProductoFactura>();
            int filas = tabla_ventas.getRowCount();
            for (int i = 0; i < filas; i++) {
                ProductoFactura p = new ProductoFactura(tabla_ventas.getValueAt(i, 0).toString(),
                        tabla_ventas.getValueAt(i, 1).toString(), tabla_ventas.getValueAt(i, 2).toString(),
                        tabla_ventas.getValueAt(i, 3).toString(), tabla_ventas.getValueAt(i, 6).toString(),
                        tabla_ventas.getValueAt(i, 5).toString());
                listaProductos.add(p);
            }
            HashMap<String, Object> parametros = new HashMap<String, Object>();
            parametrosf.put("nunfac", lblnumventa.getText());
            parametrosf.put("vendedor", cbUsuarioVenta.getSelectedItem().toString());
            parametrosf.put("tipovent", cbTipoPagosVenta.getSelectedItem().toString());
            parametrosf.put("nomape", txtclienteventas.getText());
            parametrosf.put("ruccli", txtrucclifact.getText());
            parametrosf.put("fecha", lblfechas.getText());
            parametrosf.put("subtotal", txtsubtotalvent.getText());
            parametrosf.put("iva", txtiva.getText());
            parametrosf.put("total", txtTotales.getText());

//            parametros.put("logofact", this.getClass().getResourceAsStream("/Recursos/OpenKleeInformes.png"));
            ReportFactura report = new ReportFactura();
            if (report.generarNuevoReporte("src//Reportes//Factura.jrxml", parametrosf, listaProductos)) {

                //crea el archivo xml del reporte
                /*xml x = new xml();
                 x.getXML();*/
                System.out.println(" ");
            } else {
                System.out.println("Error del Reporte");

            }
        } catch (Exception ex) {
            Logger.getLogger(GUICobrodeVentas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {

            ArrayList<ProductoFactura> listaProductos = new ArrayList<ProductoFactura>();
            int filas = tabla_ventas.getRowCount();
            for (int i = 0; i < filas; i++) {
                ProductoFactura p = new ProductoFactura(tabla_ventas.getValueAt(i, 0).toString(),
                        tabla_ventas.getValueAt(i, 1).toString(), tabla_ventas.getValueAt(i, 2).toString(),
                        tabla_ventas.getValueAt(i, 3).toString(), tabla_ventas.getValueAt(i, 6).toString(),
                        tabla_ventas.getValueAt(i, 5).toString());
                listaProductos.add(p);
            }
            String datafn[] = new String[7];
            datafn = SacarDatosFN();
            HashMap<String, Object> parametrosfn = new HashMap<String, Object>();
            parametrosfn.put("nombre", datafn[0]);
            parametrosfn.put("encabezado", datafn[1]);
            parametrosfn.put("direccion", datafn[2]);
            parametrosfn.put("telefono", datafn[3]);
            parametrosfn.put("ruc", datafn[4]);
            parametrosfn.put("pie", datafn[5]);
            parametrosfn.put("logo", datafn[6]);
                       
            HashMap<String, Object> parametros = new HashMap<String, Object>();
            parametrosfn.put("nunfac", lblnumventa.getText());
            parametrosfn.put("vendedor", cbUsuarioVenta.getSelectedItem().toString());
            parametrosfn.put("tipovent", cbTipoPagosVenta.getSelectedItem().toString());
            parametrosfn.put("nomape", txtclienteventas.getText());
            parametrosfn.put("ruccli", txtrucclifact.getText());
            parametrosfn.put("fecha", lblfechas.getText());
            parametrosfn.put("subtotal", txtsubtotalvent.getText());
            parametrosfn.put("iva", txtiva.getText());
            parametrosfn.put("total", txtTotales.getText());

            ReportFactura report = new ReportFactura();
            if (report.generarNuevoReporte("src//Reportes//FacturaNormal.jrxml", parametrosfn, listaProductos)) {
                System.out.println(" ");
            } else {
                System.out.println("Error del Reporte");

            }
        } catch (Exception ex) {
            Logger.getLogger(GUICobrodeVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtnumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnumKeyTyped
        char car = evt.getKeyChar();
        if (txtnum.getText().length() >= 13) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtnumKeyTyped

    private void txtpagosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpagosKeyTyped

    }//GEN-LAST:event_txtpagosKeyTyped

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            documento = "Pedidos";
            String opc = cbTipoventa.getSelectedItem().toString();
            String docu;
            docu = documento;
            cbTipoventa.setSelectedItem(documento);
            GUIMenuPrincipal.cbTipoPagosVenta.setSelectedItem(docu);
        } catch (Exception ex) {
            System.out.println("No hay dinero para cobrar");
        }

        try {
            if (cbTipoventa.getSelectedItem().equals("Pedidos")) {

                ArrayList<Ticket> data = new ArrayList<Ticket>();
                int filas = tabla_ventas.getRowCount();
                for (int i = 0; i < filas; i++) {
                    Ticket p = new Ticket(tabla_ventas.getValueAt(i, 3).toString(),
                            tabla_ventas.getValueAt(i, 1).toString(), tabla_ventas.getValueAt(i, 2).toString(),
                            tabla_ventas.getValueAt(i, 6).toString());
                    data.add(p);
                }

                String datas[] = new String[7];
                datas = SacarDatos();
                HashMap<String, Object> parametross = new HashMap<String, Object>();
                parametross.put("nombre", datas[0]);
                parametross.put("encabezados", datas[1]);
                parametross.put("direccion", datas[2]);
                parametross.put("telefono", datas[3]);
                parametross.put("ruc", datas[4]);
                parametross.put("pie", datas[5]);
//                parametross.put("logo", this.getClass().getResourceAsStream("/Recursos/OpenKleeInformes.png"));

                parametross.put("logo", datas[6]);
                parametross.put("nunfac", lblnumventa.getText());
                parametross.put("vendedor", cbUsuarioVenta.getSelectedItem().toString());
                parametross.put("document", cbTipoventa.getSelectedItem().toString());
                parametross.put("nomape", txtclienteventas.getText());
                parametross.put("dircli", lbldirecc.getText());
                parametross.put("subtotal", txtsubtotalvent.getText());
                parametross.put("iva", txtiva.getText());
                parametross.put("fecha", lblfechas.getText());
                parametross.put("total", txtTotales.getText());

                TicketImp reportes = new TicketImp();

                if (reportes.generarNuevoReporte("src\\Reportes\\R_TicketPedido.jrxml", parametross, data)) {
                    System.out.println(" ");
                } else {
                    System.out.println("Error del Reporte");
                }
            }
        } catch (Exception exc) {
            System.out.println("error " + exc);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(GUICobrodeVentas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUICobrodeVentas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUICobrodeVentas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUICobrodeVentas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUICobrodeVentas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox cbTarjetas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
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
    public static javax.swing.JLabel lblcantidad;
    private javax.swing.JLabel lblcontado;
    private javax.swing.JLabel lblcredito;
    private javax.swing.JLabel lbltarjeta;
    private javax.swing.JPanel panelEfectivo;
    private javax.swing.JPanel panelTarjetas;
    private javax.swing.JTabbedPane tbbPaneles;
    private javax.swing.JTextField txtcambios;
    public static javax.swing.JTextField txtnum;
    public static javax.swing.JTextField txtpagos;
    // End of variables declaration//GEN-END:variables
}
