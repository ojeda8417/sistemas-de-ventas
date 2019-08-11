/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUInterfaces;

import Clases.Conexion;
import Clases.Redondeo;
import Clases.relojLog;
import GUIConsultas.GUICCaja;
import GUIConsultas.GUICCajaCierre;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario-Pc
 */
public class GUICierreCaja extends javax.swing.JFrame {

    int contador;

    public GUICierreCaja() {
        initComponents();
        this.setLocationRelativeTo(null);
        lblfecha.setText(fechaactual());
        relojLog hilo = new relojLog(lblhora);
        hilo.start();
        Fechas();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = con.createStatement();
            ResultSet rs = sent.executeQuery("select * from tabla_usuarios");

            while (rs.next()) {
                this.cbEmpleados.addItem(rs.getString("nick"));

            }
            contador++;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        Total();
        TotalComp();
        TotalGas();
        TotalIng();
        TotalCobros();
        TotalCtaPagar();
        TotalDev();

    }

    public void Fechas() {
        Calendar c2 = new GregorianCalendar();
        jdFechasTodos.setCalendar(c2);

    }

    void limpiar() {
        txttotalesencaja.setText("0.00");
        txtnombrecaja.setText("0.00");
        txtcaja.setText("0.00");
        txtventas.setText("0.00");
        txtcompras.setText("0.00");
        txtgastos.setText("0.00");
        txtingresost.setText("0.00");
        txttotCtaCobrar.setText("0.00");
        txttotCtaPagar.setText("0.00");
        txttotaldevoluciones.setText("0.00");

    }

    public static double Calculos() {
        double caja = Double.parseDouble(GUICierreCaja.txtcaja.getText());
        double ventas = Double.parseDouble(GUICierreCaja.txtventas.getText());
        double compras = Double.parseDouble(GUICierreCaja.txtcompras.getText());
        double gastos = Double.parseDouble(GUICierreCaja.txtgastos.getText());
        double ingresos = Double.parseDouble(GUICierreCaja.txtingresost.getText());
        double ctacob = Double.parseDouble(GUICierreCaja.txttotCtaCobrar.getText());
        double ctapag = Double.parseDouble(GUICierreCaja.txttotCtaPagar.getText());
        double dev = Double.parseDouble(GUICierreCaja.txttotaldevoluciones.getText());

        double totencajas = (caja + ventas - compras - gastos + ingresos + ctacob - ctapag - dev);
        return Redondeo.redondear(totencajas);
    }

    public static String fechaactual() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
        return formatofecha.format(fecha);
    }

    public void RedondearTVentas() {
        double valor = Double.parseDouble(GUICierreCaja.txtventas.getText());
        String val = valor + "";
        BigDecimal big = new BigDecimal(val);
        big = big.setScale(2, RoundingMode.HALF_UP);
        txtventas.setText("" + big);
    }

    /**
     * *****************************************************************************
     */
    //  CAJA
    void CargarCaja() {
        DefaultTableModel tabla = new DefaultTableModel();
        String[] titulos = {"NUMERO", "TOTAL"};
        tabla.setColumnIdentifiers(titulos);
        this.tabla_facturas.setModel(tabla);
        String consulta = "SELECT * FROM tabla_aperturacaja";
        String[] Datos = new String[2];
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                Datos[0] = rs.getString("codcaja");
                Datos[1] = rs.getString("apertura");
                tabla.addRow(Datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUICierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //INFORMACION PARA CIERRE DE CAJA
    void CargartodasFacturas() {
        DefaultTableModel tabla = new DefaultTableModel();
        String[] titulos = {"NUMERO", "TOTAL"};
        tabla.setColumnIdentifiers(titulos);
        this.tabla_facturas.setModel(tabla);
        String consulta = "SELECT * FROM tabla_ventas";
        String[] Datos = new String[2];
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                Datos[0] = rs.getString("num_fact");
                Datos[1] = rs.getString("total");
                tabla.addRow(Datos);
                Total();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUICierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Total() {

        double sumatoria;
        double sumatoria1 = 0;
        double totalRow = tabla_facturas.getRowCount();
        totalRow -= 1;
        for (int i = 0; i <= (totalRow); i++) {
            sumatoria = Double.parseDouble(String.valueOf(tabla_facturas.getValueAt(i, 1)));
            sumatoria1 += sumatoria;
        }
        txtventas.setText(String.valueOf(sumatoria1));
    }

    //COMPRAS
    void CargartodasCompras() {
        DefaultTableModel tab = new DefaultTableModel();
        String[] titulos = {"NUMERO", "TOTAL"};
        tab.setColumnIdentifiers(titulos);
        this.tabla_compr.setModel(tab);
        String consulta = "SELECT * FROM tabla_compras";
        String[] Data = new String[2];
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                tab.addRow(Data);
                TotalComp();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUICierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TotalComp() {

        double sum;
        double sum1 = 0;
        double totalRow = tabla_compr.getRowCount();
        totalRow -= 1;
        for (int i = 0; i <= (totalRow); i++) {
            sum = Double.parseDouble(String.valueOf(tabla_compr.getValueAt(i, 1)));
            sum1 += sum;
        }
        txtcompras.setText(String.valueOf(sum1));

    }

    //GASTOS
    void Gastos() {
        DefaultTableModel t = new DefaultTableModel();
        String[] titulos = {"NUMERO", "TOTAL"};
        t.setColumnIdentifiers(titulos);
        this.tb_egresos.setModel(t);
        String consulta = "SELECT * FROM tabla_gastos";
        String[] Dat = new String[2];
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                Dat[0] = rs.getString("codgastos");
                Dat[1] = rs.getString("total");
                t.addRow(Dat);
                TotalGas();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUICierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TotalGas() {

        double s;
        double s1 = 0;
        double totalRow = tb_egresos.getRowCount();
        totalRow -= 1;

        for (int i = 0; i <= (totalRow); i++) {
            s = Double.parseDouble(String.valueOf(tb_egresos.getValueAt(i, 1)));
            s1 += s;
        }
        txtgastos.setText(String.valueOf(s1));
    }

    void Ingresos() {
        DefaultTableModel t = new DefaultTableModel();
        String[] titulos = {"NUMERO", "TOTAL"};
        t.setColumnIdentifiers(titulos);
        this.tb_ingresos.setModel(t);
        String consulta = "SELECT * FROM tabla_ingresos";
        String[] Dat = new String[2];
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                Dat[0] = rs.getString("codingresos");
                Dat[1] = rs.getString("monto");
                t.addRow(Dat);
                TotalIng();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUICierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TotalIng() {

        double s;
        double s1 = 0;
        double totalRow = tb_ingresos.getRowCount();
        totalRow -= 1;

        for (int i = 0; i <= (totalRow); i++) {
            s = Double.parseDouble(String.valueOf(tb_ingresos.getValueAt(i, 1)));
            s1 += s;
        }
        txtingresost.setText(String.valueOf(s1));
    }

    //COBROS
    void CtaCobrar() {
        DefaultTableModel cob = new DefaultTableModel();
        String[] titulos = {"NUMERO", "TOTAL"};
        cob.setColumnIdentifiers(titulos);
        this.tb_ctacobrar.setModel(cob);
        String consulta = "SELECT * FROM detallectacobrar";
        String[] Dat = new String[2];
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                Dat[0] = rs.getString("numfactura");
                Dat[1] = rs.getString("abonoactual");
                cob.addRow(Dat);
                TotalCobros();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUICierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TotalCobros() {

        double cob;
        double sc = 0;
        double totalRows = tb_ctacobrar.getRowCount();
        totalRows -= 1;

        for (int i = 0; i <= (totalRows); i++) {
            cob = Double.parseDouble(String.valueOf(tb_ctacobrar.getValueAt(i, 1)));
            sc += cob;
        }
        txttotCtaCobrar.setText(String.valueOf(sc));
    }

    void CtaPagar() {
        DefaultTableModel tctapagar = new DefaultTableModel();
        String[] titulos = {"NUMERO", "TOTAL"};
        tctapagar.setColumnIdentifiers(titulos);
        this.tb_ctapagar.setModel(tctapagar);
        String consulta = "SELECT * FROM detallectapagar";
        String[] Dat = new String[2];
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                Dat[0] = rs.getString("numfactura");
                Dat[1] = rs.getString("abonoactual");
                tctapagar.addRow(Dat);
                TotalCtaPagar();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUICierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TotalCtaPagar() {

        double cp;
        double tcp = 0;
        double totalRowss = tb_ctapagar.getRowCount();
        totalRowss -= 1;

        for (int i = 0; i <= (totalRowss); i++) {
            cp = Double.parseDouble(String.valueOf(tb_ctapagar.getValueAt(i, 1)));
            tcp += cp;
        }
        txttotCtaPagar.setText(String.valueOf(tcp));
    }

    void Devoluciones() {
        DefaultTableModel tdevoluciones = new DefaultTableModel();
        String[] titulosDev = {"NUMERO", "TOTAL"};
        tdevoluciones.setColumnIdentifiers(titulosDev);
        this.tb_devoluciones.setModel(tdevoluciones);
        String consultaDev = "SELECT * FROM tabla_devoluciones";
        String[] DatDev = new String[2];
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consultaDev);
            while (rs.next()) {
                DatDev[0] = rs.getString("cod_detallefact");
                DatDev[1] = rs.getString("total");
                tdevoluciones.addRow(DatDev);
                TotalDev();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUICierreCaja.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void TotalDev() {

        double dev;
        double tdev = 0;
        double totalRowsdev = tb_devoluciones.getRowCount();
        totalRowsdev -= 1;

        for (int i = 0; i <= (totalRowsdev); i++) {
            dev = Double.parseDouble(String.valueOf(tb_devoluciones.getValueAt(i, 1)));
            tdev += dev;
        }
        txttotaldevoluciones.setText(String.valueOf(tdev));
    }

    /**
     * ***************TOTALES DE CAJA******************************************
     */
    public void RVentas() {
        double valVent = Double.parseDouble(GUICierreCaja.txtventas.getText());
        String val1 = valVent + "";
        BigDecimal big = new BigDecimal(val1);
        big = big.setScale(2, RoundingMode.HALF_UP);
        txtventas.setText("" + big);
    }

    public void RCompras() {
        double valComp = Double.parseDouble(GUICierreCaja.txtcompras.getText());
        String val2 = valComp + "";
        BigDecimal big2 = new BigDecimal(val2);
        big2 = big2.setScale(2, RoundingMode.HALF_UP);
        txtcompras.setText("" + big2);
    }

    public void RIng() {
        double valPed = Double.parseDouble(GUICierreCaja.txtingresost.getText());
        String val3 = valPed + "";
        BigDecimal big3 = new BigDecimal(val3);
        big3 = big3.setScale(2, RoundingMode.HALF_UP);
        txtingresost.setText("" + big3);
    }

    public void RGastos() {
        double valGast = Double.parseDouble(GUICierreCaja.txtgastos.getText());
        String val4 = valGast + "";
        BigDecimal big4 = new BigDecimal(val4);
        big4 = big4.setScale(2, RoundingMode.HALF_UP);
        txtgastos.setText("" + big4);
    }

    public void RCtaCobrar() {
        double valCtaCobrar = Double.parseDouble(GUICierreCaja.txttotCtaCobrar.getText());
        String val5 = valCtaCobrar + "";
        BigDecimal big5 = new BigDecimal(val5);
        big5 = big5.setScale(2, RoundingMode.HALF_UP);
        txttotCtaCobrar.setText("" + big5);
    }

    public void RCtaPgar() {
        double valCtaPgar = Double.parseDouble(GUICierreCaja.txttotCtaPagar.getText());
        String val6 = valCtaPgar + "";
        BigDecimal big6 = new BigDecimal(val6);
        big6 = big6.setScale(2, RoundingMode.HALF_UP);
        txttotCtaPagar.setText("" + big6);
    }

    public void RTotales() {
        double valTot = Double.parseDouble(GUICierreCaja.txttotalesencaja.getText());
        String val7 = valTot + "";
        BigDecimal big7 = new BigDecimal(val7);
        big7 = big7.setScale(2, RoundingMode.HALF_UP);
        txttotalesencaja.setText("" + big7);
    }

    public void RDevoluciones() {
        double valTot8 = Double.parseDouble(GUICierreCaja.txttotaldevoluciones.getText());
        String valdev = valTot8 + "";
        BigDecimal big8 = new BigDecimal(valdev);
        big8 = big8.setScale(2, RoundingMode.HALF_UP);
        txttotaldevoluciones.setText("" + big8);
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
        jPanel9 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblhora = new javax.swing.JLabel();
        lblfecha = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btCalcular = new javax.swing.JButton();
        btGuardar = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        btSalir = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jdFechasTodos = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtnombrecaja = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cbEmpleados = new javax.swing.JComboBox();
        PANELSALDOS = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtcaja = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtventas = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtcompras = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtingresost = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtgastos = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txttotCtaCobrar = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txttotCtaPagar = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txttotaldevoluciones = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txttotalesencaja = new javax.swing.JTextField();
        PANELTOTALES = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_facturas = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_compr = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_egresos = new javax.swing.JTable();
        PANELTOTAL2 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_ctapagar = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tb_ctacobrar = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_ingresos = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tb_devoluciones = new javax.swing.JTable();
        labelTask1 = new org.edisoncor.gui.label.LabelTask();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("CIERRE DE CAJA");
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/CerrarCaja.png")).getImage());
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel9.setBackground(new java.awt.Color(0, 153, 255));
        jPanel9.setLayout(new java.awt.GridLayout(1, 0));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("  INFORME DE RESULTADOS GENERADOS EN EL DÍA DE EJERCICIOS DE PRODUCTIVIDAD");
        jPanel9.add(jLabel11);

        jPanel10.setBackground(new java.awt.Color(0, 153, 255));
        jPanel10.setLayout(new java.awt.GridLayout(1, 0));

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("  Revisión total de todos los fondos monetarios en sus registros diarios");
        jPanel10.add(jLabel13);

        jPanel11.setBackground(new java.awt.Color(204, 204, 0));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel11.setOpaque(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        lblhora.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblhora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblhora.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.add(lblhora);

        lblfecha.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblfecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblfecha.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.add(lblfecha);

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridLayout(2, 0));

        btCalcular.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        btCalcular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Calculadora.png"))); // NOI18N
        btCalcular.setText("Calcular");
        btCalcular.setToolTipText("Calcular");
        btCalcular.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCalcular.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCalcularActionPerformed(evt);
            }
        });
        jPanel3.add(btCalcular);

        btGuardar.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        btGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CerrarCaja.png"))); // NOI18N
        btGuardar.setText("Cerrar");
        btGuardar.setToolTipText("Cerrar caja");
        btGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGuardarActionPerformed(evt);
            }
        });
        jPanel3.add(btGuardar);

        jButton7.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Print.png"))); // NOI18N
        jButton7.setText("Ver");
        jButton7.setToolTipText("Ver informe");
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7);

        btSalir.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        btSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Regresar.png"))); // NOI18N
        btSalir.setText("Salir");
        btSalir.setToolTipText("Salir");
        btSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalirActionPerformed(evt);
            }
        });
        jPanel3.add(btSalir);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel12.setText("Seleccionar fecha de corte");

        jdFechasTodos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Buscar.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel14.setText("Seleccione caja");

        txtnombrecaja.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtnombrecaja.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnombrecaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtnombrecajaMousePressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel15.setText("Selec. empleado de cierre");

        cbEmpleados.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        cbEmpleados.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "**********" }));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jdFechasTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtnombrecaja, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cbEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdFechasTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(txtnombrecaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PANELSALDOS.setBackground(new java.awt.Color(102, 102, 255));
        PANELSALDOS.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PANELSALDOS.setOpaque(false);
        PANELSALDOS.setLayout(new java.awt.GridLayout(9, 0));

        jLabel1.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        jLabel1.setText(" Caja inicial:");
        PANELSALDOS.add(jLabel1);

        txtcaja.setEditable(false);
        txtcaja.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        txtcaja.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcaja.setText("0.00");
        PANELSALDOS.add(txtcaja);

        jLabel6.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        jLabel6.setText(" Ventas a clientes:");
        PANELSALDOS.add(jLabel6);

        txtventas.setEditable(false);
        txtventas.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        txtventas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtventas.setText("0.00");
        PANELSALDOS.add(txtventas);

        jLabel2.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        jLabel2.setText(" Compras:");
        PANELSALDOS.add(jLabel2);

        txtcompras.setEditable(false);
        txtcompras.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        txtcompras.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcompras.setText("0.00");
        txtcompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcomprasActionPerformed(evt);
            }
        });
        PANELSALDOS.add(txtcompras);

        jLabel9.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        jLabel9.setText(" Otros ingresos:");
        PANELSALDOS.add(jLabel9);

        txtingresost.setEditable(false);
        txtingresost.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        txtingresost.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtingresost.setText("0.00");
        PANELSALDOS.add(txtingresost);

        jLabel4.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        jLabel4.setText(" Otros egresos:");
        PANELSALDOS.add(jLabel4);

        txtgastos.setEditable(false);
        txtgastos.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        txtgastos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtgastos.setText("0.00");
        txtgastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtgastosActionPerformed(evt);
            }
        });
        PANELSALDOS.add(txtgastos);

        jLabel17.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        jLabel17.setText(" Ctas. cobradas:");
        PANELSALDOS.add(jLabel17);

        txttotCtaCobrar.setEditable(false);
        txttotCtaCobrar.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        txttotCtaCobrar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttotCtaCobrar.setText("0.00");
        PANELSALDOS.add(txttotCtaCobrar);

        jLabel16.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        jLabel16.setText(" Ctas. pagadas:");
        PANELSALDOS.add(jLabel16);

        txttotCtaPagar.setEditable(false);
        txttotCtaPagar.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        txttotCtaPagar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttotCtaPagar.setText("0.00");
        PANELSALDOS.add(txttotCtaPagar);

        jLabel21.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        jLabel21.setText(" Devoluciones:");
        PANELSALDOS.add(jLabel21);

        txttotaldevoluciones.setEditable(false);
        txttotaldevoluciones.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        txttotaldevoluciones.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttotaldevoluciones.setText("0.00");
        PANELSALDOS.add(txttotaldevoluciones);

        jLabel3.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        jLabel3.setText(" Total en caja:");
        PANELSALDOS.add(jLabel3);

        txttotalesencaja.setEditable(false);
        txttotalesencaja.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        txttotalesencaja.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttotalesencaja.setText("0.00");
        PANELSALDOS.add(txttotalesencaja);

        PANELTOTALES.setLayout(new java.awt.GridLayout(6, 5, 0, 5));

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setLayout(new java.awt.GridLayout(1, 0));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("VENTAS");
        jPanel7.add(jLabel7);

        PANELTOTALES.add(jPanel7);

        tabla_facturas.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tabla_facturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla_facturas);

        PANELTOTALES.add(jScrollPane1);

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("COMPRAS");
        jPanel6.add(jLabel8);

        PANELTOTALES.add(jPanel6);

        tabla_compr.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tabla_compr.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tabla_compr);

        PANELTOTALES.add(jScrollPane2);

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("OTROS EGRESOS");
        jPanel4.add(jLabel10);

        PANELTOTALES.add(jPanel4);

        tb_egresos.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tb_egresos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tb_egresos);

        PANELTOTALES.add(jScrollPane3);

        PANELTOTAL2.setLayout(new java.awt.GridLayout(6, 5, 0, 5));

        jPanel13.setBackground(new java.awt.Color(0, 0, 0));
        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel13.setLayout(new java.awt.GridLayout(1, 0));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("CTA. PAGAR");
        jPanel13.add(jLabel18);

        PANELTOTAL2.add(jPanel13);

        tb_ctapagar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tb_ctapagar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(tb_ctapagar);

        PANELTOTAL2.add(jScrollPane5);

        jPanel12.setBackground(new java.awt.Color(0, 0, 0));
        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel12.setLayout(new java.awt.GridLayout(1, 0));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("CTA. COBRAR");
        jPanel12.add(jLabel19);

        PANELTOTAL2.add(jPanel12);

        tb_ctacobrar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tb_ctacobrar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(tb_ctacobrar);

        PANELTOTAL2.add(jScrollPane6);

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText(" OTROS INGRESOS");
        jPanel5.add(jLabel5);

        PANELTOTAL2.add(jPanel5);

        tb_ingresos.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tb_ingresos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tb_ingresos);

        PANELTOTAL2.add(jScrollPane4);

        jPanel14.setLayout(new java.awt.GridLayout(2, 0));

        jPanel15.setBackground(new java.awt.Color(0, 0, 0));
        jPanel15.setLayout(new java.awt.GridLayout(1, 0));

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("DEVOLUCIONES");
        jPanel15.add(jLabel20);

        jPanel14.add(jPanel15);

        tb_devoluciones.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tb_devoluciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane8.setViewportView(tb_devoluciones);

        jPanel14.add(jScrollPane8);

        labelTask1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTask1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/ControlCaja.png"))); // NOI18N
        labelTask1.setText("Control de Caja");
        labelTask1.setDescription("Corte de Caja / Cierre");
        labelTask1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PANELTOTALES, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PANELTOTAL2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelTask1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PANELSALDOS, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PANELTOTALES, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(PANELTOTAL2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(PANELSALDOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(labelTask1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btSalirActionPerformed

    private void btCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCalcularActionPerformed
        try {
            double Calculos = GUICierreCaja.Calculos();
            txttotalesencaja.setText(String.valueOf(Calculos));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btCalcularActionPerformed

    private void btGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarActionPerformed

        String caja, acaja, aper, ting, totcomp, tgast, tventas, tcaja, hor, fec, nick, ctacob, ctapag, devol;
        String sql = "";

        caja = txtnombrecaja.getText();
        aper = txtcaja.getText();
        totcomp = txtcompras.getText();
        tventas = txtventas.getText();
        ting = txtingresost.getText();
        tgast = txtgastos.getText();
        ctacob = txttotCtaCobrar.getText();
        ctapag = txttotCtaPagar.getText();
        tcaja = txttotalesencaja.getText();
        devol = txttotaldevoluciones.getText();
        nick = cbEmpleados.getSelectedItem().toString();
        hor = lblhora.getText();
        fec = lblfecha.getText();

        sql = "INSERT INTO tabla_cierre (caja, aperturado, tot_compras,tot_ventas,tot_ingresos,tot_gastos,"
                + "tot_ctacobrar,tot_ctapagar,tot_devoluciones,tot_caja,empleado,hora,fecha) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = Conexion.conexion().prepareStatement(sql);

            pst.setString(1, caja);
            pst.setString(2, aper);
            pst.setString(3, totcomp);
            pst.setString(4, tventas);
            pst.setString(5, ting);
            pst.setString(6, tgast);
            pst.setString(7, ctacob);
            pst.setString(8, ctapag);
            pst.setString(9, devol);
            pst.setString(10, tcaja);
            pst.setString(11, nick);
            pst.setString(12, hor);
            pst.setString(13, fec);

            int n = pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Su cierre de caja se ha registrado con éxito\n"
                        + "Por favor, revisar sus ingresos respectivos. Gracias.");

            }
            limpiar();
            txtnombrecaja.setText("");
            txtnombrecaja.requestFocus();
            System.out.println("Caja cerrada con éxito");

            DefaultTableModel m1 = (DefaultTableModel) tabla_facturas.getModel();
            int a = tabla_facturas.getRowCount() - 1;
            int i;
            for (i = a; i >= 0; i--) {
                m1.removeRow(i);

                DefaultTableModel m2 = (DefaultTableModel) tabla_compr.getModel();
                int b = tabla_compr.getRowCount() - 1;
                int j;
                for (j = b; j >= 0; j--) {
                    m2.removeRow(j);
                }
                DefaultTableModel m3 = (DefaultTableModel) tb_egresos.getModel();
                int c = tb_egresos.getRowCount() - 1;
                int k;
                for (k = c; k >= 0; k--) {
                    m3.removeRow(k);
                }
                DefaultTableModel m4 = (DefaultTableModel) tb_ingresos.getModel();
                int d = tb_ingresos.getRowCount() - 1;
                int l;
                for (l = d; l >= 0; l--) {
                    m4.removeRow(l);
                }

                cbEmpleados.setSelectedIndex(0);

                DefaultTableModel m5 = (DefaultTableModel) tb_ctacobrar.getModel();
                int e = tb_ctacobrar.getRowCount() - 1;
                int m;
                for (m = e; m >= 0; m--) {
                    m5.removeRow(m);

                }
                cbEmpleados.setSelectedIndex(0);

                DefaultTableModel m6 = (DefaultTableModel) tb_ctapagar.getModel();
                int f = tb_ctapagar.getRowCount() - 1;
                int nn;
                for (nn = f; nn >= 0; nn--) {
                    m6.removeRow(nn);

                }

                cbEmpleados.setSelectedIndex(0);

                DefaultTableModel m7 = (DefaultTableModel) tb_devoluciones.getModel();
                int g = tb_devoluciones.getRowCount() - 1;
                int h;
                for (h = g; h >= 0; h--) {
                    m7.removeRow(h);

                }
                cbEmpleados.setSelectedIndex(0);

            }
            if (JOptionPane.showConfirmDialog(rootPane, "Desea imprimir este corte de caja?\n"
                    + "Se visualizará el informe.", "Aviso del sistema", 1) == 0) {
                GUICCajaCierre cfc = new GUICCajaCierre();
                cfc.setVisible(true);
                dispose();
            }

        } catch (SQLException ex) {
            Logger.getLogger(GUICierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btGuardarActionPerformed

    private void txtcomprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcomprasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcomprasActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String consulta = "";

        {
            Date f = jdFechasTodos.getDate();
            SimpleDateFormat formatofecha = new SimpleDateFormat("YYYY-MM-dd");
            String fec = "" + formatofecha.format(f);
            consulta = "SELECT * FROM tabla_aperturacaja WHERE apertura ='" + f + "'";
            txtcaja.getText();
        }

        {
            Date fecha = jdFechasTodos.getDate();
            SimpleDateFormat formatofecha = new SimpleDateFormat("YYYY-MM-dd");
            String fec = "" + formatofecha.format(fecha);
            consulta = "SELECT * FROM tabla_ventas WHERE fecha ='" + fec + "'";
        }
        DefaultTableModel modelV = new DefaultTableModel();
        String[] titulos = {"NUMERO", "TOTAL"};
        modelV.setColumnIdentifiers(titulos);
        this.tabla_facturas.setModel(modelV);

        String[] Registros = new String[2];
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                Registros[0] = rs.getString("num_fact");
                Registros[1] = rs.getString("total");
                modelV.addRow(Registros);
                Total();
                RTotales();
                RedondearTVentas();

            }
        } catch (SQLException ex) {
            Logger.getLogger(GUICierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }

        //CONSULTA DE COMPRAS
        String consulta1 = "";
        {
            Date fechaC = jdFechasTodos.getDate();
            SimpleDateFormat formatofecha = new SimpleDateFormat("YYYY-MM-dd");
            String fecC = "" + formatofecha.format(fechaC);
            consulta1 = "SELECT * FROM tabla_compras WHERE fecha ='" + fecC + "'";
        }

        DefaultTableModel modelC = new DefaultTableModel();
        String[] titC = {"NUMERO", "TOTAL"};
        modelC.setColumnIdentifiers(titC);
        this.tabla_compr.setModel(modelC);

        String[] Regist = new String[2];
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consulta1);
            while (rs.next()) {
                Regist[0] = rs.getString("num_comp");
                Regist[1] = rs.getString("pre_tot");
                modelC.addRow(Regist);
                TotalComp();
                RCompras();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUICierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }

//CONSULTA DE INGRESOS
        String consulta2 = "";
        {
            Date fechaP = jdFechasTodos.getDate();
            SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
            String fecP = "" + formatofecha.format(fechaP);
            consulta2 = "SELECT * FROM tabla_ingresos WHERE fecha ='" + fecP + "'";
        }

        DefaultTableModel modelP = new DefaultTableModel();
        String[] titP = {"NUMERO", "TOTAL"};
        modelP.setColumnIdentifiers(titP);
        this.tb_ingresos.setModel(modelP);

        String[] Reg = new String[2];
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consulta2);
            while (rs.next()) {
                Reg[0] = rs.getString("codingresos");
                Reg[1] = rs.getString("monto");
                modelP.addRow(Reg);
                TotalIng();
                RIng();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUICierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }

        //CONSULTA DE GASTOS
        String consulta3 = "";
        {
            Date fechaG = jdFechasTodos.getDate();
            SimpleDateFormat formatofecha = new SimpleDateFormat("YYYY-MM-dd");
            String fecG = "" + formatofecha.format(fechaG);
            consulta3 = "SELECT * FROM tabla_gastos WHERE fecha ='" + fecG + "'";
        }

        DefaultTableModel modelG = new DefaultTableModel();
        String[] titG = {"NUMERO", "TOTAL"};
        modelG.setColumnIdentifiers(titG);
        this.tb_egresos.setModel(modelG);

        String[] RegG = new String[2];
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consulta3);
            while (rs.next()) {
                RegG[0] = rs.getString("codgastos");
                RegG[1] = rs.getString("total");
                modelG.addRow(RegG);
                TotalGas();
                RGastos();

            }
        } catch (SQLException ex) {
            Logger.getLogger(GUICierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }

        //CONSULTA DE COBROS
        String consulta4 = "";
        {
            Date fechaCob = jdFechasTodos.getDate();
            SimpleDateFormat formatofecha = new SimpleDateFormat("YYYY-MM-dd");
            String fecCob = "" + formatofecha.format(fechaCob);
            consulta4 = "SELECT * FROM detallectacobrar WHERE fechapago ='" + fecCob + "'";
        }

        DefaultTableModel modelCob = new DefaultTableModel();
        String[] titCob = {"NUMERO", "TOTAL"};
        modelCob.setColumnIdentifiers(titCob);
        this.tb_ctacobrar.setModel(modelCob);

        String[] RegCob = new String[2];
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consulta4);
            while (rs.next()) {
                RegCob[0] = rs.getString("numfactura");
                RegCob[1] = rs.getString("abonoactual");
                modelCob.addRow(RegCob);
                TotalCobros();
                RCtaCobrar();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUICierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }

        //CONSULTA DE PAGOS
        String consulta5 = "";
        {
            Date fechaPag = jdFechasTodos.getDate();
            SimpleDateFormat formatofecha = new SimpleDateFormat("YYYY-MM-dd");
            String fecPag = "" + formatofecha.format(fechaPag);
            consulta5 = "SELECT * FROM detallectapagar WHERE fechapago ='" + fecPag + "'";
        }

        DefaultTableModel modelPag = new DefaultTableModel();
        String[] titPag = {"NUMERO", "TOTAL"};
        modelPag.setColumnIdentifiers(titPag);
        this.tb_ctapagar.setModel(modelPag);

        String[] RegPag = new String[2];
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consulta5);
            while (rs.next()) {
                RegPag[0] = rs.getString("numfactura");
                RegPag[1] = rs.getString("abonoactual");
                modelPag.addRow(RegPag);
                TotalCtaPagar();
                RCtaPgar();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUICierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            double Calculos = GUICierreCaja.Calculos();
            txttotalesencaja.setText(String.valueOf(Calculos));
        } catch (Exception e) {
        }

        //CONSULTA DE DEVOLUCIONES              
        String consulta6 = "";
        {
            Date fechaDev = jdFechasTodos.getDate();
            SimpleDateFormat formatofechadev = new SimpleDateFormat("YYYY-MM-dd");
            String fecDev = "" + formatofechadev.format(fechaDev);
            consulta6 = "SELECT * FROM tabla_devoluciones WHERE fecha ='" + fecDev + "'";
        }

        DefaultTableModel modelDev = new DefaultTableModel();
        String[] titDev = {"NUMERO", "TOTAL"};
        modelDev.setColumnIdentifiers(titDev);
        this.tb_devoluciones.setModel(modelDev);

        String[] RegDev = new String[2];
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consulta6);
            while (rs.next()) {
                RegDev[0] = rs.getString("cod_detallefact");
                RegDev[1] = rs.getString("total");
                modelDev.addRow(RegDev);
                TotalDev();
                RDevoluciones();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUICierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            double Calculos = GUICierreCaja.Calculos();
            txttotalesencaja.setText(String.valueOf(Calculos));
        } catch (Exception e) {
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            GUICCajaCierre cfc = new GUICCajaCierre();
            cfc.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(GUICierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtnombrecajaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtnombrecajaMousePressed
        GUICCaja ccc = new GUICCaja();
        ccc.setVisible(true);
    }//GEN-LAST:event_txtnombrecajaMousePressed

    private void txtgastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgastosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgastosActionPerformed

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
            java.util.logging.Logger.getLogger(GUICierreCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUICierreCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUICierreCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUICierreCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new GUICierreCaja().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PANELSALDOS;
    private javax.swing.JPanel PANELTOTAL2;
    private javax.swing.JPanel PANELTOTALES;
    private javax.swing.JButton btCalcular;
    private javax.swing.JButton btGuardar;
    private javax.swing.JButton btSalir;
    public static javax.swing.JComboBox cbEmpleados;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private com.toedter.calendar.JDateChooser jdFechasTodos;
    private org.edisoncor.gui.label.LabelTask labelTask1;
    private javax.swing.JLabel lblfecha;
    private javax.swing.JLabel lblhora;
    private javax.swing.JTable tabla_compr;
    private javax.swing.JTable tabla_facturas;
    private javax.swing.JTable tb_ctacobrar;
    private javax.swing.JTable tb_ctapagar;
    private javax.swing.JTable tb_devoluciones;
    private javax.swing.JTable tb_egresos;
    private javax.swing.JTable tb_ingresos;
    public static javax.swing.JTextField txtcaja;
    public static javax.swing.JTextField txtcompras;
    public static javax.swing.JTextField txtgastos;
    public static javax.swing.JTextField txtingresost;
    public static javax.swing.JTextField txtnombrecaja;
    public static javax.swing.JTextField txttotCtaCobrar;
    protected static javax.swing.JTextField txttotCtaPagar;
    public static javax.swing.JTextField txttotaldevoluciones;
    public static javax.swing.JTextField txttotalesencaja;
    public static javax.swing.JTextField txtventas;
    // End of variables declaration//GEN-END:variables
//conectar cc = new conectar();
//    Connection cn = cc.conexion();
}
