/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUInterfaces;

import Clases.CellRenderVentasCompras;
import Clases.CellRendererImagenes;
import Clases.ColorColumnCompras;
import Clases.ColorColumnVentas;
import Clases.Conexion;
import Clases.Controlador;
import Clases.Correo;
import Clases.GenerarNumero;
import Clases.HeaderCellRenderer;
import Clases.Mensajes;
import Clases.Redondeo;
import Clases.RenderStock;
import Clases.conectar;
import Clases.relojLog;
import GUIConsultas.GUICBarcodeTodos;
import GUIConsultas.GUICCProducts;
import GUIConsultas.GUICCajaGeneralFechas;
import GUIConsultas.GUICClientes;
import GUIConsultas.GUICClientesVentas;
import GUIConsultas.GUICCodBarras;
import GUIConsultas.GUICCompras;
import GUIConsultas.GUICComprasDetalladas;
import GUIConsultas.GUICComprasdelDia;
import GUIConsultas.GUICComprasxFechas;
import GUIConsultas.GUICCtaCobrar;
import GUIConsultas.GUICCtaPagar;
import GUIConsultas.GUICPrecios;
import GUIConsultas.GUICProductosCompras;
import GUIConsultas.GUICProductosGranel;
import GUIConsultas.GUICProveedor;
import GUIConsultas.GUICVentas;
import GUIConsultas.GUICVentasDelDia;
import GUIConsultas.GUICVentasDetalladas;
import GUIConsultas.GUICVentasxFechas;
import GUIConsultas.GUICVisorProductos;
import GUIConsultas.GUI_CAlertas;
import static GUInterfaces.GUICobrodeVentas.lblcantidad;
import static GUInterfaces.GUIGranel.txtprecioKG;
import static GUInterfaces.GUIGranel.lblnomproductos;
import static GUInterfaces.GUIGranel.txtprecioKG;
import static GUInterfaces.GUIProductosActualizar.cbCategoria1;
import static GUInterfaces.GUIProductosActualizar.cbMarca1;
import static GUInterfaces.GUIProductosActualizar.cbProveedor1;
import static GUInterfaces.GUIProductosActualizar.cbUnidad1;
import static GUInterfaces.GUIProductosActualizar.lblimagen1;
import static GUInterfaces.GUIProductosActualizar.txtco1;
import static GUInterfaces.GUIProductosActualizar.txtcodbarras1;
import static GUInterfaces.GUIProductosActualizar.txtdesc1;
import static GUInterfaces.GUIProductosActualizar.txtprecio1;
import static GUInterfaces.GUIProductosActualizar.txtprod1;
import static GUInterfaces.GUIProductosActualizar.txtpvp1;
import static GUInterfaces.GUIProductosActualizar.txtstock1;
import com.mxrck.autocompleter.TextAutoCompleter;
import ds.desktop.notify.DesktopNotify;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.*;
import static java.lang.Thread.sleep;
import java.math.*;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import org.jvnet.substance.SubstanceLookAndFeel;
import system_ventas.JMySQL;
import system_ventas.export_excel;
import static GUInterfaces.GUIProductosActualizar.txtiv1;
import static GUInterfaces.GUIProductosActualizar.txtrutaimgprodact;
import port.PortController;
import port.PortSettings;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.UnsupportedLookAndFeelException;
import net.sf.jasperreports.engine.util.JRStyledText;
import util.AppSettings;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author
 */
public class GUIMenuPrincipal extends javax.swing.JFrame {

    Correo c = new Correo();
    DefaultTableModel model;
//    conectar cc = new conectar();
//    Connection cn = cc.conexion();
    String tema = "";//com.sun.java.swing.plaf.windows.WindowsLookAndFeel
    private FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PDF ", "pdf");

    int contador;
    String pedido;

    private static DefaultTableModel modelo;
    private TableRowSorter trsFiltro;

    private JFileChooser FileChooser = new JFileChooser();
    private Vector columna = new Vector();
    private Vector filas = new Vector();
    private static int tabla_ancho = 1;
    private static int tabla_alto = 1;

    public static String usuario;

    private FileInputStream fis;
    private int longitudBytes;

    PreparedStatement pstm = null;
    ResultSet rs = null;
    /**
     * Variable donde almacenamos el peso de la balanza
     */
    double data = 0;

//    conectar conect = new conectar();
//    Connection cnt = conect.conexion();
    public GUIMenuPrincipal() {
        initComponents();
        performRead(); //METODO BASCULA
        tabla_ventas.getColumnModel().getColumn(2).setMaxWidth(150);
        tabla_ventas.getColumnModel().getColumn(2).setMinWidth(150);
        tabla_ventas.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(150);
        tabla_ventas.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(150);

        tabla_ventas.getColumnModel().getColumn(6).setMaxWidth(150);
        tabla_ventas.getColumnModel().getColumn(6).setMinWidth(150);
        tabla_ventas.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(150);
        tabla_ventas.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(150);

        setLocationRelativeTo(null);

        DesktopNotify.showDesktopMessage(
                "SISTEMA PUNTO DE VENTA",
                "Terminal Punto de Venta PYMES",
                DesktopNotify.SUCCESS, 5000L);

        //setCellRender(tabla_ventas);
//        setCellRender(tabla_compras);
        ColorColumnVentas ft = new ColorColumnVentas();
        tabla_ventas.setDefaultRenderer(Object.class, ft);
        tabla_stock.setDefaultRenderer(Object.class, new RenderStock());
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Recursos/LogoOK2019.png"));
        setIconImage(icon);
        setVisible(true);

        progLoginP.setVisible(false);
        lblcarga.setVisible(false);
        lbldirecc.setVisible(false);
        txtrutapdfs.setVisible(false);
        txtrutaimgtick.setVisible(false);
        //FUNCIONA BIENE ESTE COD
        /* ImageIcon icono = new ImageIcon(getClass().getResource("/Imagenes/Dollar.png"));
         ImageIcon tam0 = new ImageIcon(icono.getImage().getScaledInstance(15, 15, 1));
         tbbPanelesMenu.setIconAt(0, tam0);
         ImageIcon iconos = new ImageIcon(getClass().getResource("/Imagenes/Dollar.png"));
         ImageIcon tam1= new ImageIcon(iconos.getImage().getScaledInstance(15, 15, 1));
         tbbPanelesMenu.setIconAt(1, tam1);*/
        //ESTE ES DUDA
        /*ImageIcon b = new ImageIcon(getClass().getResource("/Imagenes/Bag.png"));
         ImageIcon tam1 = new ImageIcon(b.getImage().getScaledInstance(15, 15, 1));
         tbbPanelesMenu.setIconAt(1, tam1);
         ImageIcon c = new ImageIcon(getClass().getResource("/Imagenes/1479068974_Package-Download.png"));
         ImageIcon tam2 = new ImageIcon(c.getImage().getScaledInstance(15, 15, 1));
         tbbPanelesMenu.setIconAt(2, tam2);
         ImageIcon d = new ImageIcon(getClass().getResource("/Imagenes/Inventory.png"));
         ImageIcon tam3 = new ImageIcon(d.getImage().getScaledInstance(15, 15, 1));
         tbbPanelesMenu.setIconAt(3, tam3);
         ImageIcon e = new ImageIcon(getClass().getResource("/Imagenes/1454291654_application_form_edit.png"));
         ImageIcon tam4 = new ImageIcon(e.getImage().getScaledInstance(15, 15, 1));
         tbbPanelesMenu.setIconAt(4, tam4);
         ImageIcon f = new ImageIcon(getClass().getResource("/Imagenes/Movimientos.png"));
         ImageIcon tam5 = new ImageIcon(f.getImage().getScaledInstance(15, 15, 1));
         tbbPanelesMenu.setIconAt(5, tam5);
         ImageIcon g = new ImageIcon(getClass().getResource("/Imagenes/Informes.png"));
         ImageIcon tam6 = new ImageIcon(g.getImage().getScaledInstance(15, 15, 1));
         tbbPanelesMenu.setIconAt(6, tam6);
         ImageIcon h = new ImageIcon(getClass().getResource("/Imagenes/Catalogoss.png"));
         ImageIcon tam7 = new ImageIcon(h.getImage().getScaledInstance(15, 15, 1));
         tbbPanelesMenu.setIconAt(7, tam7);
         ImageIcon i = new ImageIcon(getClass().getResource("/Imagenes/Configurate.png"));
         ImageIcon tam8 = new ImageIcon(i.getImage().getScaledInstance(15, 15, 1));
         tbbPanelesMenu.setIconAt(8, tam8);

         ImageIcon k = new ImageIcon(getClass().getResource("/Imagenes/KleeIcono.png"));
         ImageIcon tam10 = new ImageIcon(k.getImage().getScaledInstance(18, 18, 1));
         tbbPanelesConfiguraciones.setIconAt(0, tam10);
         ImageIcon l = new ImageIcon(getClass().getResource("/Imagenes/Empresa.png"));
         ImageIcon tam11 = new ImageIcon(l.getImage().getScaledInstance(18, 18, 1));
         tbbPanelesConfiguraciones.setIconAt(1, tam11);
         ImageIcon m = new ImageIcon(getClass().getResource("/Imagenes/ConfiTick.png"));
         ImageIcon tam12 = new ImageIcon(m.getImage().getScaledInstance(18, 18, 1));
         tbbPanelesConfiguraciones.setIconAt(2, tam12);
         ImageIcon n = new ImageIcon(getClass().getResource("/Imagenes/Themes.png"));
         ImageIcon tam13 = new ImageIcon(n.getImage().getScaledInstance(18, 18, 1));
         tbbPanelesConfiguraciones.setIconAt(3, tam13);
         ImageIcon o = new ImageIcon(getClass().getResource("/Imagenes/Facturacion.png"));
         ImageIcon tam14 = new ImageIcon(o.getImage().getScaledInstance(18, 18, 1));
         tbbPanelesConfiguraciones.setIconAt(4, tam14);
         ImageIcon p = new ImageIcon(getClass().getResource("/Imagenes/MoreConfig.png"));
         ImageIcon tam15 = new ImageIcon(p.getImage().getScaledInstance(18, 18, 1));
         tbbPanelesConfiguraciones.setIconAt(5, tam15);
         ImageIcon q = new ImageIcon(getClass().getResource("/Imagenes/Account-msn.png"));
         ImageIcon tam16 = new ImageIcon(q.getImage().getScaledInstance(18, 18, 1));
         tbbPanelesConfiguraciones.setIconAt(6, tam16);*/

        /*jButton10.setContentAreaFilled(false);                  
         jButton10.setBorderPainted(false);
         jButton35.setContentAreaFilled(false);                  
         jButton35.setBorderPainted(false);   
         jButton36.setContentAreaFilled(false);                  
         jButton36.setBorderPainted(false);
         jButton37.setContentAreaFilled(false);                  
         jButton37.setBorderPainted(false);
         jButton38.setContentAreaFilled(false);                  
         jButton38.setBorderPainted(false);
         jButton39.setContentAreaFilled(false);                  
         jButton39.setBorderPainted(false);        
         jButton4.setContentAreaFilled(false);                  
         jButton4.setBorderPainted(false);
         jButton5.setContentAreaFilled(false); 
         jButton5.setBorderPainted(false);
         jButton6.setBorderPainted(false);
         jButton6.setContentAreaFilled(false);                  
         jButton8.setBorderPainted(false);
         jButton8.setContentAreaFilled(false);                  
         jButton11.setBorderPainted(false);
         jButton11.setContentAreaFilled(false);*/
        this.chInventarios.setSelected(true);
        this.chPDF.setSelected(true);
        this.chReportes.setSelected(true);

        btCobrar.setMnemonic(KeyEvent.VK_F2);
        btcompras.setMnemonic(KeyEvent.VK_F3);

        txtdireccionprov.setVisible(false);
        txtrutaimagen.setVisible(false);
        txtrutacompras.setVisible(false);
        txtcodticket.setVisible(false);
        txtcodemp.setVisible(false);

        Productos("");
        ProductosGen("");
        ProductosImg("");
        Kardex("");
        VentasHoy();
        ComprasHoy();
        NumeroVenta();
        NumeroCompra();
        Total();
        Calculos();
        Total2();
        Calculos2();
        Total3();
        Calculos3();
        RedondearKardexEnt();
        RedondearKardexSal();
        RedondearKardexTot();
        NomProd();
        NomEmp();
        NomTick();

        lblfechas.setText(fechas());
        lblfecha.setText(fecha());

        relojLog hilos = new relojLog(lblhoras);
        hilos.start();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = con.createStatement();
            ResultSet rs = sent.executeQuery("select * from tabla_usuarios");

            while (rs.next()) {
                this.cbUsuarioVenta.addItem(rs.getString("nick"));

            }
            contador++;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = con.createStatement();
            ResultSet rs = sent.executeQuery("select * from tabla_usuarios");

            while (rs.next()) {
                this.cbEmpledosInforme.addItem(rs.getString("nick"));

            }
            contador++;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = con.createStatement();
            ResultSet rs = sent.executeQuery("select * from tabla_productos");

            while (rs.next()) {
                this.cbInformeProductos.addItem(rs.getString("producto"));

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
                this.cbProveedor.addItem(rs.getString("nombres"));

            }
            contador++;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = con.createStatement();
            ResultSet rs = sent.executeQuery("select * from tabla_usuarios");

            while (rs.next()) {
                this.cbUsuarioCompra.addItem(rs.getString("nick"));

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
                this.cbMarcas.addItem(rs.getString("marca"));

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
                this.cbCategorias.addItem(rs.getString("categoria"));

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
                this.cbUnidades.addItem(rs.getString("unidad"));

            }
            contador++;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }

    }

    private static PortSettings createPortSettings() {

        PortSettings portSettings = new PortSettings();
        portSettings.setDeviceName("prueba");
        portSettings.setPortName(util.Puerto.darPuertoBalanza());
        portSettings.setBauds("9600");
        portSettings.setDataBits("8");
        portSettings.setStopBits("1");
        portSettings.setParity("0");
        portSettings.setFlowControl("0");
        portSettings.setReadingMode("0");
        portSettings.setIsContinuousReading("1");
        //  portSettings.setReadingCommand(txtComando.getText());
        portSettings.setInterval("0.5");

        return portSettings;
    }

    Timer tmrLecturas = null;
    PortController puerto = null;
    //  AppSettings aSettings = null;
    String weightPattern = null;

    private void performRead() {
        try {
            if (tmrLecturas == null) {

                //aSettings = util.Util.getAppSettings();
                //PortSettings pt = aSettings.getPortSettings();
                PortSettings pt = createPortSettings();
                int milliSeconds = (int) (Double.parseDouble(pt.getInterval()) * 1000);
                weightPattern = pt.getStringPattern();

                puerto = new PortController(pt);
                puerto.preparePort();
                puerto.openPort();

                tmrLecturas = new Timer(milliSeconds, new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        puerto.readString();
                        String weight = puerto.getData();

                        Matcher m = Pattern.compile("\\d+\\.\\d+").matcher(weight);
                        while (m.find()) {
                            data = Double.parseDouble(m.group()) * 1000;
                            //      System.out.println("matchar: " + m.group());
                        }
                        //System.out.println("double: " + data);

                        /* if (weightPattern != null && weightPattern.trim().compareTo("") != 0) {
                         // Pattern pattern = Pattern.compile(weightPattern);
                         //  Matcher match = pattern.matcher(weight);
                         Matcher match = Pattern.compile("\\d+\\.\\d+").matcher(weight);

                         if (match.find()) {
                         weight = match.group();
                         data = Double.parseDouble(match.group());
                         }
                         }*/
                        //  System.err.println(weight);
                        txtPesoBascula.setText("" + data);
//                        txtBascula2.setText("" + data);
                        //  txtBascula.setText(weight);
                    }
                });

                tmrLecturas.setRepeats(true);
                tmrLecturas.start();

                //txtResult.setText("");
                //btnPrueba.setText("Cerrar puerto");
                //btnEnviarComando.setEnabled(true);
                //txtComando.setEnabled(true);
            } else {

                tmrLecturas.stop();
                tmrLecturas = null;

                puerto.closePort();
                puerto = null;

            }
        } catch (Exception ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    /**
     * **ICONO REPORTES**
     */
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Recursos/LogoOK2019.png"));
        return retValue;
    }

    public static String fechaactual() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MM/YYYY");
        return formatofecha.format(fecha);
    }

    public void LookAndFeel() {

        System.setProperty(
                "Quaqua.tabLayoutPolicy", "wrap"
        );
        try {
            UIManager.setLookAndFeel(tema);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception erro) {
            Mensajes.informacion("Error al cargar el tema");
        }
    }

    public void LookAndFeel2() {
        System.setProperty(
                "Quaqua.tabLayoutPolicy", "wrap"
        );
        try {
            SubstanceLookAndFeel.setSkin(tema);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception erro) {
            Mensajes.informacion("Error al cargar el tema");
        }
    }

    private void configuroTabla() {
        tablakardex.getAutoResizeMode();
        tablakardex.tableChanged(null);
        tablakardex.setEnabled(true);
        tablakardex.setRowHeight(25);
        tablakardex.setRowMargin(4);
        tabla_ancho = modelo.getColumnCount() * 150;
        tabla_alto = modelo.getRowCount() * 25;
        tablakardex.setPreferredSize(new Dimension(tabla_ancho, tabla_alto));
    }

    private void AbrirManual() {

        String fileLocal = new String("src/Manual.docx");
        try {

            Runtime.getRuntime().exec("cmd /c start " + fileLocal);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void AbrirWeb() {
        String direccion = "http://www.sistemasnetfex.blogspot.com";
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + direccion);
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "Error: " + err);
        }
    }

    void AbrirFacebook() {
        String direccion = "https://www.facebook.com/jose8417";
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + direccion);
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "Error: " + err);
        }
    }

    public void ProdImgList() {
        try {

            int filas = tabla_productosimg.getSelectedRow();
            String prodimg = tabla_productosimg.getValueAt(filas, 1).toString();
            prodimg = tabla_productosimg.getValueAt(filas, 0).toString();

            String nomproimg, precio, rut;

            nomproimg = tabla_productosimg.getValueAt(filas, 1).toString();
            precio = tabla_productosimg.getValueAt(filas, 10).toString();
            rut = tabla_productosimg.getValueAt(filas, 13).toString();

            lblnompimg.setText(nomproimg);
            lblprepimg.setText("$ " + precio);

            try {
                int Fila = tabla_productosimg.getSelectedRow();
                String Ruta;
                Ruta = lblprodimg.getText();
                String Imagen = tabla_productosimg.getValueAt(Fila, 13).toString();
                ImageIcon img = new ImageIcon(Imagen);
                lblprodimg.setIcon(new ImageIcon(img.getImage().getScaledInstance(300, 320, Image.SCALE_SMOOTH)));

            } catch (Exception e) {
                System.out.println("No hay imagen" + e);
            }

        } catch (Exception e) {
            System.out.println("Error" + e);
        }

    }

    public void calcularvent() {

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
        double totparcialgeneral = 0.0;

        //Balanza
        double cuenta = 0.0;
        // double totalProducto = 0.0;
        double valorPeso = 0.0;

        String codigoProducto;

        int filasT = GUIMenuPrincipal.tabla_ventas.getRowCount();
        if (filasT == 0) {
            txtTotales.setText("0");
            txtsubtotalvent.setText("0");
            txtiva.setText("0");
        }
        for (int i = 0; i < GUIMenuPrincipal.tabla_ventas.getRowCount(); i++) {
            pre = GUIMenuPrincipal.tabla_ventas.getValueAt(i, 2).toString();
            can = GUIMenuPrincipal.tabla_ventas.getValueAt(i, 3).toString();

            //Capturamos el código del producto 
            codigoProducto = GUIMenuPrincipal.tabla_ventas.getValueAt(i, 0).toString();

            //Validamos si el producto va en balanza o no
            String unidadMedida = productoBalanza(codigoProducto);

            /*
             Si tenemos un verdadero en la balanza
             */
            if (!unidadMedida.isEmpty()) {

                //GUIMenuPrincipal.tabla_ventas.editingCanceled();
                // Si tipo es Lb
                if (unidadMedida.equals("LB")) {
                    precio = Double.parseDouble(pre);
                    valorPeso = 500;
                    System.err.println(data);
                    cuenta = data / valorPeso;
                    totparcial = cuenta * precio;

                    subtotal = subtotal + totparcial;
                    total = subtotal + iva;

                    GUIMenuPrincipal.tabla_ventas.setValueAt(String.format("%.0f", totparcial).replace(",", "."), i, 6);

                } else if (unidadMedida.equals("KG")) {
                    precio = Double.parseDouble(pre);
                    valorPeso = 1000;
                    System.err.println(data);
                    cuenta = data / valorPeso;
                    totparcial = cuenta * precio;

                    subtotal = subtotal + totparcial;
                    total = subtotal + iva;

                    GUIMenuPrincipal.tabla_ventas.setValueAt(String.format("%.0f", totparcial).replace(",", "."), i, 6);

                }

            } else {

                precio = Double.parseDouble(pre);
                cantidad = Double.parseDouble(can);

                if (GUIMenuPrincipal.tabla_ventas.getValueAt(i, 5) == null) {
                    totparcial = precio * cantidad;

                } else {

                    //DESCUENTO EN LA COLUMNA 5
                    desc = GUIMenuPrincipal.tabla_ventas.getValueAt(i, 5).toString();
                    descuentos = Double.parseDouble(desc);
                    impParcial = precio * cantidad;
                    desct = (precio * descuentos) * cantidad;
                    totparcial = impParcial - desct;

                }

                subtotal = subtotal + totparcial;
                // iva = subtotal * 0.12;
                total = subtotal + iva;

                //COLUMNA 6 DONDE ESTA EL PARCIAL
                GUIMenuPrincipal.tabla_ventas.setValueAt(String.format("%.0f", Math.rint(totparcial * 100) / 100).replace(",", "."), i, 6);

            }
            GUIMenuPrincipal.txtsubtotalvent.setText(String.format("%.0f", subtotal).replace(",", "."));
            //GUIMenuPrincipal.txtiva.setText(String.format("%.0f", Math.rint(iva * 100) / 100).replace(",", "."));
            GUIMenuPrincipal.txtTotales.setText(String.format("%.0f", Math.rint(total * 100) / 100).replace(",", "."));
        }
    }

    /**
     * *******************INVENTARIOS PRODUCTOS**********************
     */
    void Productos(String valor) {
        String mostrar = "SELECT * FROM tabla_productos WHERE CONCAT(producto) LIKE '%" + valor + "%'";
        String[] titulos = {"Nº", "C.BARRAS", "PRODUCTO", "MARCA", "UNIDAD", "CATEGORIA", "STOCK", "IVA",
            "DESCUENTO", "PROVEEDOR", "P.COMPRA", "P.VENTA", "F.CADC.", "DETALLES", "IMAGEN"};
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
            tabla_stock.setModel(model);
            tabla_stock.setAutoResizeMode(tabla_productos.AUTO_RESIZE_OFF);
            tabla_stock.setRowHeight(20);
            txtcant.setText("Consta de " + tabla_stock.getRowCount() + " productos registrados en su bodega");
            tabla_stock.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabla_stock.getColumnModel().getColumn(1).setPreferredWidth(105);
            tabla_stock.getColumnModel().getColumn(2).setPreferredWidth(320);
            tabla_stock.getColumnModel().getColumn(3).setPreferredWidth(100);
            tabla_stock.getColumnModel().getColumn(4).setPreferredWidth(80);
            tabla_stock.getColumnModel().getColumn(5).setPreferredWidth(200);
            tabla_stock.getColumnModel().getColumn(6).setPreferredWidth(80);
            tabla_stock.getColumnModel().getColumn(7).setPreferredWidth(45);
            tabla_stock.getColumnModel().getColumn(8).setPreferredWidth(100);
            tabla_stock.getColumnModel().getColumn(9).setPreferredWidth(240);
            tabla_stock.getColumnModel().getColumn(10).setPreferredWidth(80);
            tabla_stock.getColumnModel().getColumn(11).setPreferredWidth(80);
            tabla_stock.getColumnModel().getColumn(12).setPreferredWidth(90);
            tabla_stock.getColumnModel().getColumn(13).setPreferredWidth(360);
            tabla_stock.getColumnModel().getColumn(14).setPreferredWidth(360);
        } catch (SQLException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * *******************PRODUCTOS EN GENERAL******************************
     */
    void ProductosGen(String valor) {
        String mostrar = "SELECT * FROM tabla_productos WHERE CONCAT(producto) LIKE '%" + valor + "%'";
        String[] titulos = {"Nº", "C.BARRAS", "PRODUCTO", "MARCA", "UNIDAD", "CATEGORIA", "STOCK", "IVA",
            "DESCUENTO", "PROVEEDOR", "P.COMPRA", "P.VENTA", "F.CADC.", "DETALLES", "IMAGEN"};
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
            tabla_productos.setModel(model);
            tabla_productos.setAutoResizeMode(tabla_productos.AUTO_RESIZE_OFF);
            tabla_productos.setRowHeight(18);
            tabla_productos.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabla_productos.getColumnModel().getColumn(1).setPreferredWidth(105);
            tabla_productos.getColumnModel().getColumn(2).setPreferredWidth(320);
            tabla_productos.getColumnModel().getColumn(3).setPreferredWidth(100);
            tabla_productos.getColumnModel().getColumn(4).setPreferredWidth(80);
            tabla_productos.getColumnModel().getColumn(5).setPreferredWidth(200);
            tabla_productos.getColumnModel().getColumn(6).setPreferredWidth(80);
            tabla_productos.getColumnModel().getColumn(7).setPreferredWidth(35);
            tabla_productos.getColumnModel().getColumn(8).setPreferredWidth(100);
            tabla_productos.getColumnModel().getColumn(9).setPreferredWidth(240);
            tabla_productos.getColumnModel().getColumn(10).setPreferredWidth(80);
            tabla_productos.getColumnModel().getColumn(11).setPreferredWidth(80);
            tabla_productos.getColumnModel().getColumn(12).setPreferredWidth(90);
            tabla_productos.getColumnModel().getColumn(13).setPreferredWidth(360);
            tabla_productos.getColumnModel().getColumn(14).setPreferredWidth(360);
        } catch (SQLException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * *******************PRODUCTOS EN MARCA******************************
     */
    void ProductosMar(String valor) {
        String mostrar = "SELECT * FROM tabla_productos WHERE CONCAT(producto) LIKE '%" + valor + "%'";
        String[] titulos = {"Nº", "C.BARRAS", "PRODUCTO", "MARCA", "UNIDAD", "CATEGORIA", "STOCK", "IVA",
            "DESCUENTO", "PROVEEDOR", "P.COMPRA", "P.VENTA", "F.CADC.", "DETALLES", "IMAGEN"};
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
            tabla_productos.setModel(model);
            tabla_productos.setAutoResizeMode(tabla_productos.AUTO_RESIZE_OFF);
            tabla_productos.setRowHeight(18);
            tabla_productos.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabla_productos.getColumnModel().getColumn(1).setPreferredWidth(105);
            tabla_productos.getColumnModel().getColumn(2).setPreferredWidth(320);
            tabla_productos.getColumnModel().getColumn(3).setPreferredWidth(100);
            tabla_productos.getColumnModel().getColumn(4).setPreferredWidth(80);
            tabla_productos.getColumnModel().getColumn(5).setPreferredWidth(200);
            tabla_productos.getColumnModel().getColumn(6).setPreferredWidth(80);
            tabla_productos.getColumnModel().getColumn(7).setPreferredWidth(35);
            tabla_productos.getColumnModel().getColumn(8).setPreferredWidth(100);
            tabla_productos.getColumnModel().getColumn(9).setPreferredWidth(240);
            tabla_productos.getColumnModel().getColumn(10).setPreferredWidth(80);
            tabla_productos.getColumnModel().getColumn(11).setPreferredWidth(80);
            tabla_productos.getColumnModel().getColumn(12).setPreferredWidth(90);
            tabla_productos.getColumnModel().getColumn(13).setPreferredWidth(360);
            tabla_productos.getColumnModel().getColumn(14).setPreferredWidth(360);
        } catch (SQLException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void ProductosImg(String valor) {
        String mostrar = "SELECT * FROM tabla_productos WHERE CONCAT(producto,codbarras) LIKE '%" + valor + "%'";
        String[] titulos = {"C.BARRAS", "PRODUCTO", "MARCA", "UNIDAD", "CATEGORIA", "STOCK", "IVA",
            "DESC.", "PROVEEDOR", "P.COMPRA", "P.VENTA", "F.CADC.", "DETALLES", "IMAGEN"};
        String[] Registros = new String[14];
        model = new DefaultTableModel(null, titulos);

        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(mostrar);
            while (rs.next()) {
                Registros[0] = rs.getString("codbarras");
                Registros[1] = rs.getString("producto");
                Registros[2] = rs.getString("marca");
                Registros[3] = rs.getString("unidad");
                Registros[4] = rs.getString("categoria");
                Registros[5] = rs.getString("stock");
                Registros[6] = rs.getString("iva");
                Registros[7] = rs.getString("descuento");
                Registros[8] = rs.getString("proveedor");
                Registros[9] = rs.getString("pre_compra");
                Registros[10] = rs.getString("pre_venta");
                Registros[11] = rs.getString("fechacaducidad");
                Registros[12] = rs.getString("detalles");
                Registros[13] = rs.getString("imagen");

                model.addRow(Registros);
            }
            tabla_productosimg.setModel(model);
            tabla_productosimg.setAutoResizeMode(tabla_productosimg.AUTO_RESIZE_OFF);
            tabla_productosimg.setRowHeight(22);
            lblistaproductos.setText("Consta de " + tabla_productosimg.getRowCount() + " productos registrados en su bodega");
            tabla_productosimg.getColumnModel().getColumn(0).setPreferredWidth(150);
            tabla_productosimg.getColumnModel().getColumn(1).setPreferredWidth(320);
            tabla_productosimg.getColumnModel().getColumn(2).setPreferredWidth(110);
            tabla_productosimg.getColumnModel().getColumn(3).setPreferredWidth(110);
            tabla_productosimg.getColumnModel().getColumn(4).setPreferredWidth(200);
            tabla_productosimg.getColumnModel().getColumn(5).setPreferredWidth(80);
            tabla_productosimg.getColumnModel().getColumn(6).setPreferredWidth(80);
            tabla_productosimg.getColumnModel().getColumn(7).setPreferredWidth(80);
            tabla_productosimg.getColumnModel().getColumn(8).setPreferredWidth(230);
            tabla_productosimg.getColumnModel().getColumn(9).setPreferredWidth(80);
            tabla_productosimg.getColumnModel().getColumn(10).setPreferredWidth(80);
            tabla_productosimg.getColumnModel().getColumn(11).setPreferredWidth(90);
            tabla_productosimg.getColumnModel().getColumn(12).setPreferredWidth(280);
            tabla_productosimg.getColumnModel().getColumn(13).setPreferredWidth(360);
        } catch (SQLException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * *******************PRODUCTOS EN CATEGORIA******************************
     */
    void ProductosCat(String valor) {
        String mostrar = "SELECT * FROM tabla_productos WHERE CONCAT(producto) LIKE '%" + valor + "%'";
        String[] titulos = {"Nº", "C.BARRAS", "PRODUCTO", "MARCA", "UNIDAD", "CATEGORIA", "STOCK", "IVA",
            "DESCUENTO", "PROVEEDOR", "P.COMPRA", "P.VENTA", "F.CADC.", "DETALLES", "IMAGEN"};
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
            tabla_productos.setModel(model);
            tabla_productos.setAutoResizeMode(tabla_productos.AUTO_RESIZE_OFF);
            tabla_productos.setRowHeight(18);
            tabla_productos.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabla_productos.getColumnModel().getColumn(1).setPreferredWidth(105);
            tabla_productos.getColumnModel().getColumn(2).setPreferredWidth(320);
            tabla_productos.getColumnModel().getColumn(3).setPreferredWidth(100);
            tabla_productos.getColumnModel().getColumn(4).setPreferredWidth(80);
            tabla_productos.getColumnModel().getColumn(5).setPreferredWidth(200);
            tabla_productos.getColumnModel().getColumn(6).setPreferredWidth(80);
            tabla_productos.getColumnModel().getColumn(7).setPreferredWidth(35);
            tabla_productos.getColumnModel().getColumn(8).setPreferredWidth(100);
            tabla_productos.getColumnModel().getColumn(9).setPreferredWidth(240);
            tabla_productos.getColumnModel().getColumn(10).setPreferredWidth(80);
            tabla_productos.getColumnModel().getColumn(11).setPreferredWidth(80);
            tabla_productos.getColumnModel().getColumn(12).setPreferredWidth(90);
            tabla_productos.getColumnModel().getColumn(13).setPreferredWidth(360);
            tabla_productos.getColumnModel().getColumn(14).setPreferredWidth(360);
        } catch (SQLException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * *******************PRODUCTOS EN UNIDAD******************************
     */
    void ProductosUn(String valor) {
        String mostrar = "SELECT * FROM tabla_productos WHERE CONCAT(producto) LIKE '%" + valor + "%'";
        String[] titulos = {"Nº", "C.BARRAS", "PRODUCTO", "MARCA", "UNIDAD", "CATEGORIA", "STOCK", "IVA",
            "DESCUENTO", "PROVEEDOR", "P.COMPRA", "P.VENTA", "F.CADC.", "DETALLES", "IMAGEN"};
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
            tabla_productos.setModel(model);
            tabla_productos.setAutoResizeMode(tabla_productos.AUTO_RESIZE_OFF);
            tabla_productos.setRowHeight(18);
            tabla_productos.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabla_productos.getColumnModel().getColumn(1).setPreferredWidth(105);
            tabla_productos.getColumnModel().getColumn(2).setPreferredWidth(320);
            tabla_productos.getColumnModel().getColumn(3).setPreferredWidth(100);
            tabla_productos.getColumnModel().getColumn(4).setPreferredWidth(80);
            tabla_productos.getColumnModel().getColumn(5).setPreferredWidth(200);
            tabla_productos.getColumnModel().getColumn(6).setPreferredWidth(80);
            tabla_productos.getColumnModel().getColumn(7).setPreferredWidth(35);
            tabla_productos.getColumnModel().getColumn(8).setPreferredWidth(100);
            tabla_productos.getColumnModel().getColumn(9).setPreferredWidth(240);
            tabla_productos.getColumnModel().getColumn(10).setPreferredWidth(80);
            tabla_productos.getColumnModel().getColumn(11).setPreferredWidth(80);
            tabla_productos.getColumnModel().getColumn(12).setPreferredWidth(90);
            tabla_productos.getColumnModel().getColumn(13).setPreferredWidth(360);
            tabla_productos.getColumnModel().getColumn(14).setPreferredWidth(360);
        } catch (SQLException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * *************KARDEX*********************************
     */
    void Kardex(String valor) {
        String mostrar = "SELECT * FROM tabla_kardex WHERE CONCAT(producto) LIKE '%" + valor + "%'";

        DefaultTableModel tabla = new DefaultTableModel();
        String[] titulos = {"NUMERO", "COD", "PRODUCTO", "MOTIVO", "DOC", "PROVEEDOR/CLIENTE",
            "P.C.V", "ENTRADA", "SALIDA", "TOTAL", "FECHA"};
        tabla.setColumnIdentifiers(titulos);
        this.tablakardex.setModel(tabla);
        String consulta = "SELECT * FROM tabla_kardex";
        String[] Datos = new String[11];
        model = new DefaultTableModel(null, titulos);

        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(mostrar);
            while (rs.next()) {
                Datos[0] = rs.getString("num_fact");
                Datos[1] = rs.getString("cod_prod");
                Datos[2] = rs.getString("producto");
                Datos[3] = rs.getString("motivo");
                Datos[4] = rs.getString("documento");
                Datos[5] = rs.getString("proveedor");
                Datos[6] = rs.getString("pre_unit");
                Datos[7] = rs.getString("entrada");
                Datos[8] = rs.getString("salida");
                Datos[9] = rs.getString("total");
                Datos[10] = rs.getString("fecha");
                model.addRow(Datos);
            }
            tablakardex.setModel(model);
            tablakardex.setAutoResizeMode(tablakardex.AUTO_RESIZE_OFF);
            tablakardex.setRowHeight(35);
            tablakardex.getColumnModel().getColumn(0).setCellRenderer(new CellRendererImagenes("num"));
            tablakardex.getColumnModel().getColumn(1).setCellRenderer(new CellRendererImagenes("num"));
            tablakardex.getColumnModel().getColumn(2).setCellRenderer(new CellRendererImagenes("text"));
            tablakardex.getColumnModel().getColumn(3).setCellRenderer(new CellRendererImagenes("text"));
            tablakardex.getColumnModel().getColumn(4).setCellRenderer(new CellRendererImagenes("icon"));
            tablakardex.getColumnModel().getColumn(5).setCellRenderer(new CellRendererImagenes("text center"));
            tablakardex.getColumnModel().getColumn(6).setCellRenderer(new CellRendererImagenes("num"));
            tablakardex.getColumnModel().getColumn(7).setCellRenderer(new CellRendererImagenes("num"));
            tablakardex.getColumnModel().getColumn(8).setCellRenderer(new CellRendererImagenes("num"));
            tablakardex.getColumnModel().getColumn(9).setCellRenderer(new CellRendererImagenes("num"));
            tablakardex.getColumnModel().getColumn(10).setCellRenderer(new CellRendererImagenes("text"));

            JTableHeader jtableHeader = tablakardex.getTableHeader();
            jtableHeader.setDefaultRenderer(new HeaderCellRenderer());
            tablakardex.setTableHeader(jtableHeader);
            tablakardex.getColumnModel().getColumn(0).setPreferredWidth(80);
            tablakardex.getColumnModel().getColumn(1).setPreferredWidth(105);
            tablakardex.getColumnModel().getColumn(2).setPreferredWidth(480);
            tablakardex.getColumnModel().getColumn(3).setPreferredWidth(150);
            tablakardex.getColumnModel().getColumn(4).setPreferredWidth(50);
            tablakardex.getColumnModel().getColumn(5).setPreferredWidth(270);
            tablakardex.getColumnModel().getColumn(6).setPreferredWidth(80);
            tablakardex.getColumnModel().getColumn(7).setPreferredWidth(80);
            tablakardex.getColumnModel().getColumn(8).setPreferredWidth(80);
            tablakardex.getColumnModel().getColumn(9).setPreferredWidth(80);
            tablakardex.getColumnModel().getColumn(10).setPreferredWidth(80);

        } catch (SQLException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Total() {

        double sumatoria;
        double sumatoria1 = 0;
        double totalRow = tablakardex.getRowCount();
        totalRow -= 1;
        for (int i = 0; i <= (totalRow); i++) {
            sumatoria = Double.parseDouble(String.valueOf(tablakardex.getValueAt(i, 7)));
            sumatoria1 += sumatoria;
        }
        txtTotalEntradas.setText(String.valueOf(sumatoria1));
        Calculos();
    }

    public static double Calculos() {
        double total = Double.parseDouble(GUIMenuPrincipal.txtTotalEntradas.getText());
        return Redondeo.redondear(total);
    }

    public void Total2() {

        double sumatoria;
        double sumatoria1 = 0;
        double totalRow = tablakardex.getRowCount();
        totalRow -= 1;
        for (int i = 0; i <= (totalRow); i++) {
            sumatoria = Double.parseDouble(String.valueOf(tablakardex.getValueAt(i, 8)));
            sumatoria1 += sumatoria;
        }
        txtTotalSalidas.setText(String.valueOf(sumatoria1));
        Calculos();
    }

    public static double Calculos2() {
        double total = Double.parseDouble(GUIMenuPrincipal.txtTotalSalidas.getText());
        return Redondeo.redondear(total);
    }

    public void Total3() {

        double sumatoria;
        double sumatoria1 = 0;
        double totalRow = tablakardex.getRowCount();
        totalRow -= 1;
        for (int i = 0; i <= (totalRow); i++) {
            sumatoria = Double.parseDouble(String.valueOf(tablakardex.getValueAt(i, 9)));
            sumatoria1 += sumatoria;
        }
        txtTotalEntradasSalidas.setText(String.valueOf(sumatoria1));
        Calculos();
    }

    public static double Calculos3() {
        double total = Double.parseDouble(GUIMenuPrincipal.txtTotalEntradasSalidas.getText());
        return Redondeo.redondear(total);
    }

    public void RedondearKardexEnt() {
        double valKar = Double.parseDouble(GUIMenuPrincipal.txtTotalEntradas.getText());
        String val = valKar + "";
        BigDecimal big = new BigDecimal(val);
        big = big.setScale(2, RoundingMode.HALF_UP);
        System.out.println("Número : " + big);
        txtTotalEntradas.setText("" + big);
    }

    public void RedondearKardexSal() {
        double valKarsal = Double.parseDouble(GUIMenuPrincipal.txtTotalSalidas.getText());
        String valKars = valKarsal + "";
        BigDecimal big2 = new BigDecimal(valKarsal);
        big2 = big2.setScale(2, RoundingMode.HALF_UP);
        System.out.println("Número : " + big2);
        txtTotalSalidas.setText("" + big2);
    }

    public void RedondearKardexTot() {
        double valKartot = Double.parseDouble(GUIMenuPrincipal.txtTotalEntradasSalidas.getText());
        String valtot = valKartot + "";
        BigDecimal big3 = new BigDecimal(valKartot);
        big3 = big3.setScale(2, RoundingMode.HALF_UP);
        System.out.println("Número : " + big3);
        txtTotalEntradasSalidas.setText("" + big3);
    }

    /**
     * ****************NUMERO VENTA****************
     */
    void NumeroVenta() {
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
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * ************************* COMPRAS
     * ********************************************
     */
    void descontarstockcomp(String codb, String cant) {
        int desp = Integer.parseInt(cant);
        String capb = "";
        int desfinalv;
        String consul = "SELECT * FROM tabla_productos WHERE codbarras='" + codb + "'";

        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consul);
            while (rs.next()) {
                capb = rs.getString(7);
            }

        } catch (Exception e) {
        }

        desfinalv = Integer.parseInt(capb) + desp;

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
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
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

            GUIMenuPrincipal.tabla_compras.setValueAt(Math.rint(imp * 100) / 100, i, 7);
        }

        GUIMenuPrincipal.lblsubcomp.setText(String.format("%.0f", subtotal).replace(",", "."));
        GUIMenuPrincipal.lblivacomp.setText(String.format("%.0f", Math.rint(iva * 100) / 100).replace(",", "."));
        GUIMenuPrincipal.txtTotalComp.setText(String.format("%.0f", Math.rint(total * 100) / 100).replace(",", "."));

    }

    /**
     * *********************************************************************************************************
     */
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

    /**
     * ************************************************************************
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
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void RedondearSubtotalVent() {
        double valo = Double.parseDouble(GUIMenuPrincipal.txtsubtotalvent.getText());
        String val = valo + "";
        BigDecimal big = new BigDecimal(val);
        big = big.setScale(2, RoundingMode.HALF_UP);
        txtsubtotalvent.setText(String.format("%.0f", big).replace(",", "."));
    }

    public void RedondearSubtotalComp() {
        double valor = Double.parseDouble(GUIMenuPrincipal.lblsubcomp.getText());
        String vall = valor + "";
        BigDecimal big = new BigDecimal(vall);
        big = big.setScale(2, RoundingMode.HALF_UP);
        lblsubcomp.setText(String.format("%.0f", big).replace(",", "."));
    }

    public void VentasHoy() {
        Calendar c2 = new GregorianCalendar();
        jdFechaHoy.setCalendar(c2);
    }

    public void ComprasHoy() {
        Calendar c2 = new GregorianCalendar();
        jdComprasHoy.setCalendar(c2);
    }

    void nuevostock(String codb, String cant) {
        int n1 = Integer.parseInt(this.txtcantactual.getText());
        int n2 = Integer.parseInt(this.spAjuste.toString());
        int informe = n1 + n2;
    }

    void CalcularKG() {
        try {

            //1kg = 1000 gr            
            double precio1KG = 0.065;
            double pesoKG = Double.parseDouble(GUIGranel.txtpesoKG.getText());
            double totalenKG = (precio1KG * pesoKG);
            txtprecioKG.setText("" + totalenKG);
        } catch (Exception ex) {
            System.out.println("Error al calcular el precio del KG " + ex);
        }
    }

    public void NomEmp() {
        TextAutoCompleter textAutoC = new TextAutoCompleter(txtemp);
        try {
            // Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = Conexion.conexion().createStatement();
            ResultSet rs = sent.executeQuery("SELECT empresa FROM tabla_empresas");
            while (rs.next()) {

                textAutoC.addItem(rs.getString("empresa"));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void NomTick() {
        TextAutoCompleter textAutoC = new TextAutoCompleter(txtbticket);
        try {
            //Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = Conexion.conexion().createStatement();
            ResultSet rs = sent.executeQuery("SELECT nombre FROM tabla_tickets");
            while (rs.next()) {

                textAutoC.addItem(rs.getString("nombre"));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void enviarCorreos() {

        c.setContrasenia("nnxjqirikdcgfger");//rene= okuvyjgkevopppgo   // netfex= nnxjqirikdcgfger
        c.setUsuarioCorreo("netfexprogrammer@gmail.com");
        c.setAsunto(txtasuntos.getText());
        c.setMensaje(txtmensajes.getText());
        c.setDestino(txtcorreos.getText().trim());
        c.setNombreArchivo(txtadjuntos.getText());
        c.setRutaArchivo(txtrutapdfs.getText());

        Controlador co = new Controlador();
        if (co.enviarCorreo(c)) {
            JOptionPane.showMessageDialog(null, "Envio de mensaje, realizado con éxito", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al enviar su mensaje\n"
                    + "Por favor, si cuenta con un antivirus desactivelo");
        }
    }

    //COLORES ALTERNOS DE LA TABLA (VENTAS O COMPRAS)
//    public void setCellRender(JTable table) {
//        Enumeration<TableColumn> en = table.getColumnModel().getColumns();
//        while (en.hasMoreElements()) {
//            TableColumn tc = en.nextElement();
//            tc.setCellRenderer(new CellRenderVentasCompras());
//        }
//    }
    public String[] SacarDatos() {
        String data[] = new String[5];
        try {
            pstm = Conexion.conexion().prepareStatement("SELECT nombre,encabezado,direccion,pie FROM tabla_tickets");
            rs = pstm.executeQuery();

            while (rs.next()) {
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                data[3] = rs.getString(4);
            }

        } catch (Exception exx) {

        }
        return data;
    }

    public void VerTicket() {

        String data[] = new String[4];
        data = SacarDatos();
        HashMap<String, Object> parametross = new HashMap<String, Object>();
        parametross.put("nombre", data[0]);
        parametross.put("encabezado", data[1]);
        parametross.put("direccion", data[2]);
        parametross.put("pie", data[3]);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenu1 = new javax.swing.JMenu();
        fondo1 = new javax.swing.JMenuItem();
        fondo2 = new javax.swing.JMenuItem();
        fondo3 = new javax.swing.JMenuItem();
        fondo4 = new javax.swing.JMenuItem();
        fondo5 = new javax.swing.JMenuItem();
        fondo6 = new javax.swing.JMenuItem();
        pEliminarCli = new javax.swing.JPopupMenu();
        eliminarCli = new javax.swing.JMenuItem();
        tbbPanelesOpciones = new javax.swing.JTabbedPane();
        jtInicio = new javax.swing.JToolBar();
        jLabel88 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        lblabrircaja = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblregusuarios = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jtAdmin = new javax.swing.JToolBar();
        lblconceptos = new javax.swing.JLabel();
        lblingresos = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jtProductos = new javax.swing.JToolBar();
        lblmarcas = new javax.swing.JLabel();
        lblcategorias = new javax.swing.JLabel();
        lblunidades = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        lblproductos = new javax.swing.JLabel();
        lblprecios = new javax.swing.JLabel();
        jtTPV = new javax.swing.JToolBar();
        jLabel36 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jLabel37 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jtConsultas = new javax.swing.JToolBar();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jLabel40 = new javax.swing.JLabel();
        lblmasvendido = new javax.swing.JLabel();
        lblmascomprado = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        lblinventarios = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JToolBar.Separator();
        jLabel200 = new javax.swing.JLabel();
        jLabel201 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel154 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jtUtil = new javax.swing.JToolBar();
        lblcodbarrasproducto = new javax.swing.JLabel();
        lblcobarrastodos = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jtHerramientas = new javax.swing.JToolBar();
        lblcambioclave = new javax.swing.JLabel();
        lblrecuperarclave = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        lblregistro = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        jspMenus = new javax.swing.JSplitPane();
        tbbPanelesMenu = new javax.swing.JTabbedPane();
        panelVentas = new javax.swing.JPanel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        txtcodbarrasvent = new javax.swing.JTextField();
        jLabel105 = new javax.swing.JLabel();
        txtprovent = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        txtclienteventas = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        panelBotonesAcciones = new javax.swing.JPanel();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblnumventa = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbTipoPagosVenta = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        cbModo = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        cbTipoventa = new javax.swing.JComboBox();
        txtTotales = new javax.swing.JTextField();
        btCobrar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lbltotal = new javax.swing.JLabel();
        cbUsuarioVenta = new javax.swing.JComboBox();
        lblcargo = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblusuario = new javax.swing.JLabel();
        lblfecha = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        lblhoras = new javax.swing.JLabel();
        lblfechas = new javax.swing.JLabel();
        txtrutaimagen = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jLabel44 = new javax.swing.JLabel();
        lbldirecc = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtiva = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jXTaskPane2 = new org.jdesktop.swingx.JXTaskPane();
        lblimagenventas = new javax.swing.JLabel();
        txtsubtotalvent = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_ventas = new javax.swing.JTable();
        txtrucclifact = new javax.swing.JTextField();
        txtPesoBascula = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        panelCompras = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_compras = new javax.swing.JTable();
        txtbarcodcomp = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        cbProveedor = new javax.swing.JComboBox();
        jLabel79 = new javax.swing.JLabel();
        lblnumcomp = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        cbUsuarioCompra = new javax.swing.JComboBox();
        jLabel80 = new javax.swing.JLabel();
        cbDoccomp = new javax.swing.JComboBox();
        jLabel86 = new javax.swing.JLabel();
        cbFormadepago = new javax.swing.JComboBox();
        jSeparator9 = new javax.swing.JSeparator();
        jPanel31 = new javax.swing.JPanel();
        txtObservaciones = new javax.swing.JTextField();
        jLabel180 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel183 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        lblivacomp = new javax.swing.JLabel();
        txtTotalComp = new javax.swing.JTextField();
        btcompras = new javax.swing.JButton();
        jLabel181 = new javax.swing.JLabel();
        jLabel182 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lblsubcomp = new javax.swing.JLabel();
        jXTaskPane1 = new org.jdesktop.swingx.JXTaskPane();
        lblimgcompras = new javax.swing.JLabel();
        panelImage2 = new org.edisoncor.gui.panel.PanelImage();
        jLabel177 = new javax.swing.JLabel();
        txtrutacompras = new javax.swing.JTextField();
        txtdireccionprov = new javax.swing.JTextField();
        txtBascula = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        panelProductos = new javax.swing.JPanel();
        txtbuscarp = new javax.swing.JTextField();
        rdTodasClases = new javax.swing.JRadioButton();
        cbMarcas = new javax.swing.JComboBox();
        jLabel108 = new javax.swing.JLabel();
        cbCategorias = new javax.swing.JComboBox();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        cbUnidades = new javax.swing.JComboBox();
        btNuevoProducto = new javax.swing.JButton();
        btExpotProductos = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        tabla_productos = new rojerusan.RSTableMetro();
        panelListaInventarios = new javax.swing.JPanel();
        txtcant = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtbprod = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabla_stock = new rojerusan.RSTableMetro();
        panelInventariosManual = new javax.swing.JPanel();
        jLabel131 = new javax.swing.JLabel();
        txtcbarra = new javax.swing.JTextField();
        jLabel132 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        txtcantactual = new javax.swing.JTextField();
        jLabel134 = new javax.swing.JLabel();
        spAjuste = new javax.swing.JSpinner();
        btAjustar = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jLabel135 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        txtdescriproducto = new javax.swing.JTextField();
        lblnotacodigo = new javax.swing.JLabel();
        lblnomproductoInventario = new javax.swing.JLabel();
        panelImage3 = new org.edisoncor.gui.panel.PanelImage();
        jLabel130 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        panelListaProductos = new javax.swing.JPanel();
        rdBuscarprodimg = new javax.swing.JRadioButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        tabla_productosimg = new rojerusan.RSTableMetro();
        lblprodimg = new javax.swing.JLabel();
        lblprepimg = new javax.swing.JLabel();
        lblnompimg = new javax.swing.JLabel();
        txtbuscarpimg = new rojeru_san.RSMTextFull();
        lblistaproductos = new javax.swing.JLabel();
        panelMovimientos = new javax.swing.JPanel();
        txtbuspm = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jdFechaMov = new com.toedter.calendar.JDateChooser();
        txtTotalEntradas = new javax.swing.JTextField();
        txtTotalSalidas = new javax.swing.JTextField();
        txtTotalEntradasSalidas = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        btnExportarTabla = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        cbTipVent = new javax.swing.JComboBox();
        jLabel35 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        cbMotivos = new javax.swing.JComboBox();
        jButton34 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablakardex = new rojerusan.RSTableMetro();
        panelInformes = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        paneInformeVentas = new javax.swing.JPanel();
        jButton17 = new javax.swing.JButton();
        jdFecha1 = new com.toedter.calendar.JDateChooser();
        jdFecha2 = new com.toedter.calendar.JDateChooser();
        jLabel69 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jButton33 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        jdFechaC1 = new com.toedter.calendar.JDateChooser();
        jLabel93 = new javax.swing.JLabel();
        jdFechaC2 = new com.toedter.calendar.JDateChooser();
        jButton18 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        panelInformeCompras = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        cbEmpledosInforme = new javax.swing.JComboBox();
        jButton23 = new javax.swing.JButton();
        panelInformeEmpleados = new javax.swing.JPanel();
        jButton26 = new javax.swing.JButton();
        jLabel97 = new javax.swing.JLabel();
        jdateFecha = new com.toedter.calendar.JDateChooser();
        jPanel13 = new javax.swing.JPanel();
        jLabel96 = new javax.swing.JLabel();
        jButton24 = new javax.swing.JButton();
        panelInformeInventarios = new javax.swing.JPanel();
        cbInformeProductos = new javax.swing.JComboBox();
        jButton25 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        panelVentasHoy = new javax.swing.JPanel();
        jLabel111 = new javax.swing.JLabel();
        jdFechaHoy = new com.toedter.calendar.JDateChooser();
        jButton27 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel112 = new javax.swing.JLabel();
        jdComprasHoy = new com.toedter.calendar.JDateChooser();
        jButton28 = new javax.swing.JButton();
        panelComprasHoy = new javax.swing.JPanel();
        panelConfig = new javax.swing.JPanel();
        tbbPanelesConfiguraciones = new javax.swing.JTabbedPane();
        panelOpenKlee = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        panelEmpresa = new javax.swing.JPanel();
        jLabel139 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        jLabel141 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        jLabel147 = new javax.swing.JLabel();
        jLabel148 = new javax.swing.JLabel();
        txtemp = new javax.swing.JTextField();
        txtprop = new javax.swing.JTextField();
        txtdir = new javax.swing.JTextField();
        txtzon = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtref = new javax.swing.JTextArea();
        txt1 = new javax.swing.JTextField();
        txt2 = new javax.swing.JTextField();
        txtruc = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        txtweb = new javax.swing.JTextField();
        btGEmp = new javax.swing.JButton();
        btAEmp = new javax.swing.JButton();
        txtrutaimagenemp = new javax.swing.JTextField();
        panelImgEmpresa = new javax.swing.JPanel();
        btSFoto = new javax.swing.JButton();
        lblimagenemp = new javax.swing.JLabel();
        txtcodemp = new javax.swing.JTextField();
        jLabel107 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        txtfechareg = new javax.swing.JTextField();
        panelImage4 = new org.edisoncor.gui.panel.PanelImage();
        jLabel138 = new javax.swing.JLabel();
        btEEmp = new javax.swing.JButton();
        btNEmp = new javax.swing.JButton();
        panelTicket = new javax.swing.JPanel();
        panelImage9 = new org.edisoncor.gui.panel.PanelImage();
        jLabel187 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        btSFotoTick = new javax.swing.JButton();
        txtcodticket = new javax.swing.JTextField();
        lbllogoticket = new javax.swing.JLabel();
        btNTicket = new javax.swing.JButton();
        btGTicket = new javax.swing.JButton();
        btATicket = new javax.swing.JButton();
        btETicket = new javax.swing.JButton();
        txtrutaimgtick = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        jLabel155 = new javax.swing.JLabel();
        rSPanelShadow1 = new rojeru_san.RSPanelShadow();
        txtnomticket = new rojeru_san.RSMTextFull();
        txtenticket = new rojeru_san.RSMTextFull();
        txtdirectiticket = new rojeru_san.RSMTextFull();
        txtructick = new rojeru_san.RSMTextFull();
        txtteltick = new rojeru_san.RSMTextFull();
        jLabel31 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtpieticket = new javax.swing.JTextArea();
        jLabel128 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        txtbticket = new rojeru_san.RSMTextFull();
        panelTemas = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        panelImage5 = new org.edisoncor.gui.panel.PanelImage();
        jLabel42 = new javax.swing.JLabel();
        lblantum4 = new javax.swing.JLabel();
        lblantum = new javax.swing.JLabel();
        lblprofesional = new javax.swing.JLabel();
        lblprofesional1 = new javax.swing.JLabel();
        lblclassic = new javax.swing.JLabel();
        lblskin = new javax.swing.JLabel();
        lblblue = new javax.swing.JLabel();
        lblblack = new javax.swing.JLabel();
        lblantum2 = new javax.swing.JLabel();
        lblantum1 = new javax.swing.JLabel();
        lblwin8 = new javax.swing.JLabel();
        lblwin1 = new javax.swing.JLabel();
        lblwin4 = new javax.swing.JLabel();
        lblwin9 = new javax.swing.JLabel();
        lblblue1 = new javax.swing.JLabel();
        panelOtros = new javax.swing.JPanel();
        chInventarios = new javax.swing.JCheckBox();
        jLabel164 = new javax.swing.JLabel();
        jLabel165 = new javax.swing.JLabel();
        jLabel167 = new javax.swing.JLabel();
        chPDF = new javax.swing.JCheckBox();
        jLabel169 = new javax.swing.JLabel();
        jLabel170 = new javax.swing.JLabel();
        jLabel171 = new javax.swing.JLabel();
        chReportes = new javax.swing.JCheckBox();
        jLabel45 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel173 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel174 = new javax.swing.JLabel();
        jLabel175 = new javax.swing.JLabel();
        jLabel176 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel178 = new javax.swing.JLabel();
        chCargarPro = new javax.swing.JCheckBox();
        jLabel179 = new javax.swing.JLabel();
        progLoginP = new javax.swing.JProgressBar();
        panelImage7 = new org.edisoncor.gui.panel.PanelImage();
        jLabel1 = new javax.swing.JLabel();
        lblcarga = new rojerusan.componentes.RSProgressMaterial();
        panelContactos = new javax.swing.JPanel();
        jLabel188 = new javax.swing.JLabel();
        jLabel189 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        txtasuntos = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtmensajes = new javax.swing.JTextArea();
        txtcorreos = new javax.swing.JTextField();
        txtadjuntos = new javax.swing.JTextField();
        txtrutapdfs = new javax.swing.JTextField();
        jLabel191 = new javax.swing.JLabel();
        jLabel192 = new javax.swing.JLabel();
        jLabel193 = new javax.swing.JLabel();
        jLabel194 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel196 = new javax.swing.JLabel();
        jButton22 = new javax.swing.JButton();
        panelImage6 = new org.edisoncor.gui.panel.PanelImage();
        jLabel195 = new javax.swing.JLabel();
        jLabel197 = new javax.swing.JLabel();
        jLabel198 = new javax.swing.JLabel();
        panelReportes = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel157 = new javax.swing.JLabel();
        jLabel158 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/PersonalizarFondo1.png"))); // NOI18N
        jMenu1.setText("Personalizar pantalla");

        fondo1.setText("Fondo Open K´LEE One");
        fondo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fondo1ActionPerformed(evt);
            }
        });
        jMenu1.add(fondo1);

        fondo2.setText("Fondo Open K´LEE Two");
        fondo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fondo2ActionPerformed(evt);
            }
        });
        jMenu1.add(fondo2);

        fondo3.setText("Fondo Open K´LEE Three");
        fondo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fondo3ActionPerformed(evt);
            }
        });
        jMenu1.add(fondo3);

        fondo4.setText("Fondo Open K´LEE Four");
        fondo4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fondo4ActionPerformed(evt);
            }
        });
        jMenu1.add(fondo4);

        fondo5.setText("Fondo Open K´LEE Five");
        fondo5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fondo5ActionPerformed(evt);
            }
        });
        jMenu1.add(fondo5);

        fondo6.setText("Fondo Open K´LEE Six");
        fondo6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fondo6ActionPerformed(evt);
            }
        });
        jMenu1.add(fondo6);

        jPopupMenu1.add(jMenu1);

        eliminarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/EliminarT.png"))); // NOI18N
        eliminarCli.setText("Eliminar cliente");
        eliminarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarCliActionPerformed(evt);
            }
        });
        pEliminarCli.add(eliminarCli);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MAFAKAFER - Software Punto de Venta");

        tbbPanelesOpciones.setBackground(new java.awt.Color(255, 255, 255));

        jtInicio.setFloatable(false);
        jtInicio.setRollover(true);
        jtInicio.setOpaque(false);

        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel88.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CajaInicio.png"))); // NOI18N
        jLabel88.setToolTipText("Software ERP Punto de Venta");
        jtInicio.add(jLabel88);
        jtInicio.add(jSeparator5);

        lblabrircaja.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblabrircaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Caja.png"))); // NOI18N
        lblabrircaja.setText("  Abrir caja  ");
        lblabrircaja.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblabrircaja.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblabrircaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblabrircajaMouseClicked(evt);
            }
        });
        jtInicio.add(lblabrircaja);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CerrarCaja.png"))); // NOI18N
        jLabel25.setText("Cerrar caja  ");
        jLabel25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel25.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel25MouseClicked(evt);
            }
        });
        jtInicio.add(jLabel25);

        lblregusuarios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblregusuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Usuarios.png"))); // NOI18N
        lblregusuarios.setText("Usuarios  ");
        lblregusuarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblregusuarios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblregusuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblregusuariosMouseClicked(evt);
            }
        });
        jtInicio.add(lblregusuarios);

        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Disconnect.png"))); // NOI18N
        jLabel66.setText("Desconectar  ");
        jLabel66.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel66.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel66.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel66MouseClicked(evt);
            }
        });
        jtInicio.add(jLabel66);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/logoPower.png"))); // NOI18N
        jLabel6.setText("Reiniciar sistema  ");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jtInicio.add(jLabel6);

        tbbPanelesOpciones.addTab("Inicio", jtInicio);

        jtAdmin.setFloatable(false);
        jtAdmin.setRollover(true);
        jtAdmin.setOpaque(false);

        lblconceptos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblconceptos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Conceptos.png"))); // NOI18N
        lblconceptos.setText("  Conceptos  ");
        lblconceptos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblconceptos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblconceptos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblconceptosMouseClicked(evt);
            }
        });
        jtAdmin.add(lblconceptos);

        lblingresos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblingresos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IngresosDinero.png"))); // NOI18N
        lblingresos.setText("Ingreso a caja");
        lblingresos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblingresos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblingresos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblingresosMouseClicked(evt);
            }
        });
        jtAdmin.add(lblingresos);

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Email.png"))); // NOI18N
        jLabel28.setText("  E-Mail  ");
        jLabel28.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel28.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
        });
        jtAdmin.add(jLabel28);
        jtAdmin.add(jSeparator6);

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Clientes.png"))); // NOI18N
        jLabel29.setText(" Clientes  ");
        jLabel29.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel29.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel29MouseClicked(evt);
            }
        });
        jtAdmin.add(jLabel29);

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Proveedores.png"))); // NOI18N
        jLabel30.setText("Proveedores  ");
        jLabel30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel30.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel30MouseClicked(evt);
            }
        });
        jtAdmin.add(jLabel30);

        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Gastos.png"))); // NOI18N
        jLabel65.setText("Gastos empresariales");
        jLabel65.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel65.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel65.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel65MouseClicked(evt);
            }
        });
        jtAdmin.add(jLabel65);

        tbbPanelesOpciones.addTab("Administración", jtAdmin);

        jtProductos.setFloatable(false);
        jtProductos.setRollover(true);
        jtProductos.setOpaque(false);

        lblmarcas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblmarcas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/DeTodo.png"))); // NOI18N
        lblmarcas.setText("    Marcas  ");
        lblmarcas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblmarcas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblmarcas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblmarcasMouseClicked(evt);
            }
        });
        jtProductos.add(lblmarcas);

        lblcategorias.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblcategorias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/DeTodo.png"))); // NOI18N
        lblcategorias.setText("Categorías  ");
        lblcategorias.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblcategorias.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblcategorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblcategoriasMouseClicked(evt);
            }
        });
        jtProductos.add(lblcategorias);

        lblunidades.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblunidades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/DeTodo.png"))); // NOI18N
        lblunidades.setText("Unidades  ");
        lblunidades.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblunidades.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblunidades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblunidadesMouseClicked(evt);
            }
        });
        jtProductos.add(lblunidades);
        jtProductos.add(jSeparator7);

        lblproductos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblproductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Productos.png"))); // NOI18N
        lblproductos.setText("  Productos  ");
        lblproductos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblproductos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblproductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblproductosMouseClicked(evt);
            }
        });
        jtProductos.add(lblproductos);

        lblprecios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblprecios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Precios.png"))); // NOI18N
        lblprecios.setText("Actualizar precios");
        lblprecios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblprecios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblprecios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblpreciosMouseClicked(evt);
            }
        });
        jtProductos.add(lblprecios);

        tbbPanelesOpciones.addTab("Productos", jtProductos);

        jtTPV.setFloatable(false);
        jtTPV.setOpaque(false);

        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/ShoppingCar.png"))); // NOI18N
        jLabel36.setText("  Control ventas  ");
        jLabel36.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel36.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel36MouseClicked(evt);
            }
        });
        jtTPV.add(jLabel36);

        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Detalles.png"))); // NOI18N
        jLabel74.setText("Ventas detalladas  ");
        jLabel74.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel74.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel74.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel74MouseClicked(evt);
            }
        });
        jtTPV.add(jLabel74);

        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel90.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Revert.png"))); // NOI18N
        jLabel90.setText("Devoluciones");
        jLabel90.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel90.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel90.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel90MouseClicked(evt);
            }
        });
        jtTPV.add(jLabel90);
        jtTPV.add(jSeparator1);

        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/ShoppingComp.png"))); // NOI18N
        jLabel37.setText("  Control compras  ");
        jLabel37.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel37.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel37MouseClicked(evt);
            }
        });
        jtTPV.add(jLabel37);

        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Detalles.png"))); // NOI18N
        jLabel75.setText("Compras detalladas");
        jLabel75.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel75.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel75.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel75MouseClicked(evt);
            }
        });
        jtTPV.add(jLabel75);

        tbbPanelesOpciones.addTab("Punto de venta", jtTPV);

        jtConsultas.setFloatable(false);
        jtConsultas.setRollover(true);
        jtConsultas.setOpaque(false);

        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Clientes.png"))); // NOI18N
        jLabel38.setText("  Clientes  ");
        jLabel38.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel38.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel38MouseClicked(evt);
            }
        });
        jtConsultas.add(jLabel38);

        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Proveedores.png"))); // NOI18N
        jLabel39.setText("Proveedores  ");
        jLabel39.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel39.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel39MouseClicked(evt);
            }
        });
        jtConsultas.add(jLabel39);
        jtConsultas.add(jSeparator3);

        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Productos.png"))); // NOI18N
        jLabel40.setText(" Productos  ");
        jLabel40.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel40.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel40MouseClicked(evt);
            }
        });
        jtConsultas.add(jLabel40);

        lblmasvendido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblmasvendido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/MasVendido.png"))); // NOI18N
        lblmasvendido.setText("Más vendidos  ");
        lblmasvendido.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblmasvendido.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblmasvendido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblmasvendidoMouseClicked(evt);
            }
        });
        jtConsultas.add(lblmasvendido);

        lblmascomprado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblmascomprado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/MasComprado.png"))); // NOI18N
        lblmascomprado.setText("Más comprados  ");
        lblmascomprado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblmascomprado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblmascomprado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblmascompradoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblmascompradoMouseEntered(evt);
            }
        });
        jtConsultas.add(lblmascomprado);

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Catalogos.png"))); // NOI18N
        jLabel41.setText("Visor de catálogos  ");
        jLabel41.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel41.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel41MouseClicked(evt);
            }
        });
        jtConsultas.add(jLabel41);

        lblinventarios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblinventarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Inventory.png"))); // NOI18N
        lblinventarios.setText("Inventarios  ");
        lblinventarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblinventarios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblinventarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblinventariosMouseClicked(evt);
            }
        });
        jtConsultas.add(lblinventarios);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Movimientos.png"))); // NOI18N
        jLabel21.setText(" Movimientos  ");
        jLabel21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel21.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        jtConsultas.add(jLabel21);
        jtConsultas.add(jSeparator10);

        jLabel200.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel200.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Detalles.png"))); // NOI18N
        jLabel200.setText("  Ventas  ");
        jLabel200.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel200.setInheritsPopupMenu(false);
        jLabel200.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel200.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel200MouseClicked(evt);
            }
        });
        jtConsultas.add(jLabel200);

        jLabel201.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel201.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Detalles.png"))); // NOI18N
        jLabel201.setText("  Compras  ");
        jLabel201.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel201.setInheritsPopupMenu(false);
        jLabel201.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel201.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel201MouseClicked(evt);
            }
        });
        jtConsultas.add(jLabel201);

        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Cta.Pagar.png"))); // NOI18N
        jLabel55.setText(" Cuentas por pagar  ");
        jLabel55.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel55.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel55.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel55MouseClicked(evt);
            }
        });
        jtConsultas.add(jLabel55);

        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Cta.Cobrar.png"))); // NOI18N
        jLabel56.setText("Cuentas por cobrar");
        jLabel56.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel56.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel56.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel56MouseClicked(evt);
            }
        });
        jtConsultas.add(jLabel56);

        jLabel154.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel154.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Alerta.png"))); // NOI18N
        jLabel154.setText("  Caducidades");
        jLabel154.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel154.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel154.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel154MouseClicked(evt);
            }
        });
        jtConsultas.add(jLabel154);

        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/PrintCaja.png"))); // NOI18N
        jLabel70.setText("  Cierres de caja  ");
        jLabel70.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel70.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel70.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel70MouseClicked(evt);
            }
        });
        jtConsultas.add(jLabel70);

        tbbPanelesOpciones.addTab("Consultas", jtConsultas);

        jtUtil.setFloatable(false);
        jtUtil.setOpaque(false);

        lblcodbarrasproducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblcodbarrasproducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Barcode.png"))); // NOI18N
        lblcodbarrasproducto.setText("  Etiquetas por producto  ");
        lblcodbarrasproducto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblcodbarrasproducto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblcodbarrasproducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblcodbarrasproductoMouseClicked(evt);
            }
        });
        jtUtil.add(lblcodbarrasproducto);

        lblcobarrastodos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblcobarrastodos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Barcode2.png"))); // NOI18N
        lblcobarrastodos.setText("Etiquetar todos  ");
        lblcobarrastodos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblcobarrastodos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblcobarrastodos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblcobarrastodosMouseClicked(evt);
            }
        });
        jtUtil.add(lblcobarrastodos);

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Calculadora.png"))); // NOI18N
        jLabel32.setText("Calculadora");
        jLabel32.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel32.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel32MouseClicked(evt);
            }
        });
        jtUtil.add(jLabel32);

        tbbPanelesOpciones.addTab("Utilerías", jtUtil);

        jtHerramientas.setFloatable(false);
        jtHerramientas.setRollover(true);
        jtHerramientas.setOpaque(false);

        lblcambioclave.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblcambioclave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CambioClave.png"))); // NOI18N
        lblcambioclave.setText("  Cambio de clave  ");
        lblcambioclave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblcambioclave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblcambioclave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblcambioclaveMouseClicked(evt);
            }
        });
        jtHerramientas.add(lblcambioclave);

        lblrecuperarclave.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblrecuperarclave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/RecuperarClave.png"))); // NOI18N
        lblrecuperarclave.setText("Recuperar contraseña  ");
        lblrecuperarclave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblrecuperarclave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblrecuperarclave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblrecuperarclaveMouseClicked(evt);
            }
        });
        jtHerramientas.add(lblrecuperarclave);

        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/ExportarBD.png"))); // NOI18N
        jLabel68.setText("Respaldar Datos  ");
        jLabel68.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel68.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel68.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel68MouseClicked(evt);
            }
        });
        jtHerramientas.add(jLabel68);

        lblregistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblregistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Activar.png"))); // NOI18N
        lblregistro.setText("Registrar MAFAKAFER  ");
        lblregistro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblregistro.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblregistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblregistroMouseClicked(evt);
            }
        });
        jtHerramientas.add(lblregistro);
        jtHerramientas.add(jSeparator8);

        tbbPanelesOpciones.addTab("Herramientas", jtHerramientas);

        jspMenus.setDividerLocation(1);
        jspMenus.setDividerSize(22);
        jspMenus.setOneTouchExpandable(true);

        tbbPanelesMenu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbbPanelesMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbbPanelesMenuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbbPanelesMenuMouseEntered(evt);
            }
        });

        panelVentas.setOpaque(false);

        jLabel103.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CajaRegist.png"))); // NOI18N

        jLabel104.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Barcode.png"))); // NOI18N

        txtcodbarrasvent.setBackground(new java.awt.Color(255, 255, 204));
        txtcodbarrasvent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtcodbarrasvent.setToolTipText("Código de barras");
        txtcodbarrasvent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodbarrasventKeyTyped(evt);
            }
        });

        jLabel105.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel105.setText("Producto:");

        txtprovent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtprovent.setToolTipText("Ingrese nombre del producto");
        txtprovent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtproventKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtproventKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtproventKeyTyped(evt);
            }
        });

        jLabel106.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel106.setText("Cliente:");

        txtclienteventas.setEditable(false);
        txtclienteventas.setText("Consumidor final");
        txtclienteventas.setComponentPopupMenu(pEliminarCli);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Buscar.png"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        panelBotonesAcciones.setOpaque(false);
        panelBotonesAcciones.setLayout(new java.awt.GridLayout(1, 0));

        jButton35.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Historial.png"))); // NOI18N
        jButton35.setText("HISTORIAL");
        jButton35.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton35.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });
        panelBotonesAcciones.add(jButton35);

        jButton36.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Filtro.png"))); // NOI18N
        jButton36.setText("FILTRAR");
        jButton36.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton36.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });
        panelBotonesAcciones.add(jButton36);

        jButton37.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/1479069202_money-raise.png"))); // NOI18N
        jButton37.setText("VENTAS");
        jButton37.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton37.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });
        panelBotonesAcciones.add(jButton37);

        jButton38.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/PreciosV.png"))); // NOI18N
        jButton38.setText("PRECIOS");
        jButton38.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton38.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });
        panelBotonesAcciones.add(jButton38);

        jButton44.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/ProductSal.png"))); // NOI18N
        jButton44.setText("PRODUCT.");
        jButton44.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton44.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });
        panelBotonesAcciones.add(jButton44);

        jButton39.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/DecimalesG.png"))); // NOI18N
        jButton39.setText("GRANEL");
        jButton39.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton39.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });
        panelBotonesAcciones.add(jButton39);

        jButton45.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/DevolverG.png"))); // NOI18N
        jButton45.setText("DEVOL.");
        jButton45.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton45.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });
        panelBotonesAcciones.add(jButton45);

        jButton10.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/EliminarProd.png"))); // NOI18N
        jButton10.setText("ELIMINAR");
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        panelBotonesAcciones.add(jButton10);

        jButton42.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/EliminarVenta.png"))); // NOI18N
        jButton42.setText("ANULAR");
        jButton42.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton42.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });
        panelBotonesAcciones.add(jButton42);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("N° Venta:");

        lblnumventa.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblnumventa.setForeground(new java.awt.Color(153, 0, 0));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("F.Pago:");

        cbTipoPagosVenta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Contado", "Crédito", "Tarjeta de crédito" }));
        cbTipoPagosVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoPagosVentaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Modo:");

        cbModo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ventas", "Pedidos", "Compras" }));
        cbModo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbModoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Tipo:");

        cbTipoventa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ticket", "Factura", "Pedidos" }));
        cbTipoventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoventaActionPerformed(evt);
            }
        });

        txtTotales.setEditable(false);
        txtTotales.setBackground(new java.awt.Color(0, 0, 0));
        txtTotales.setFont(new java.awt.Font("Courier New", 1, 37)); // NOI18N
        txtTotales.setForeground(new java.awt.Color(0, 204, 0));
        txtTotales.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotales.setText("0");
        txtTotales.setToolTipText("");

        btCobrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btCobrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Access.png"))); // NOI18N
        btCobrar.setText("Cobrar venta");
        btCobrar.setToolTipText("Cobrar venta Alt+F2");
        btCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCobrarActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbltotal.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbltotal.setForeground(new java.awt.Color(153, 51, 0));
        lbltotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        cbUsuarioVenta.setEnabled(false);

        lblcargo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblcargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Tipos.png"))); // NOI18N
        lblcargo.setToolTipText("Tipo de usuario");
        lblcargo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Vendedor:");

        lblusuario.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblusuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Admin.png"))); // NOI18N
        lblusuario.setToolTipText("Usuario conectado");

        lblfecha.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblfecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Calendar.png"))); // NOI18N
        lblfecha.setToolTipText("Datos del sistema");

        lblhoras.setText("jLabel22");

        lblfechas.setText("jLabel22");

        txtrutaimagen.setText("jTextField3");

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/InfoIniP.png"))); // NOI18N
        jLabel50.setText("MAFAKAFER");

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel87.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Soporte.png"))); // NOI18N
        jLabel87.setText("Soporte Técnico");
        jLabel87.setToolTipText("Llamar: (+593)968967277");
        jLabel87.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel87MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbUsuarioVenta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblcargo, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblhoras, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblfechas, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtrutaimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel87)
                        .addGap(109, 109, 109)
                        .addComponent(lblfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(lbltotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbltotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblhoras)
                        .addComponent(lblfechas)
                        .addComponent(txtrutaimagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cbUsuarioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblusuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblcargo, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lblfecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1))
                    .addComponent(jLabel87, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/BannerProfAzul.png"))); // NOI18N

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("FACTURACIÓN - TICKETS - PEDIDOS");

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101)
                .addComponent(lbldirecc, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbldirecc, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Total a cobrar");

        txtiva.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtiva.setForeground(new java.awt.Color(0, 0, 204));
        txtiva.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("IVA Total");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 204));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("0%");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("IVA Ponderado");

        jXTaskPane2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/InfoIniP.png"))); // NOI18N
        jXTaskPane2.setTitle("MAFAKAFER");

        lblimagenventas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXTaskPane2.getContentPane().add(lblimagenventas);

        txtsubtotalvent.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtsubtotalvent.setForeground(new java.awt.Color(0, 0, 204));
        txtsubtotalvent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Subtotal");

        tabla_ventas.setFont(new java.awt.Font("Courier New", 0, 20)); // NOI18N
        tabla_ventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Producto en venta", "P.Unitario", "Cantidad", "Cant.Sal", "Descuento", "Parcial"
            }
        ));
        tabla_ventas.setGridColor(new java.awt.Color(255, 255, 255));
        tabla_ventas.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tabla_ventas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_ventasMouseClicked(evt);
            }
        });
        tabla_ventas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_ventasKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabla_ventasKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_ventas);
        if (tabla_ventas.getColumnModel().getColumnCount() > 0) {
            tabla_ventas.getColumnModel().getColumn(0).setMinWidth(180);
            tabla_ventas.getColumnModel().getColumn(0).setMaxWidth(180);
            tabla_ventas.getColumnModel().getColumn(2).setMinWidth(95);
            tabla_ventas.getColumnModel().getColumn(2).setMaxWidth(95);
            tabla_ventas.getColumnModel().getColumn(3).setMinWidth(80);
            tabla_ventas.getColumnModel().getColumn(3).setMaxWidth(80);
            tabla_ventas.getColumnModel().getColumn(4).setMinWidth(80);
            tabla_ventas.getColumnModel().getColumn(4).setMaxWidth(80);
            tabla_ventas.getColumnModel().getColumn(5).setMinWidth(80);
            tabla_ventas.getColumnModel().getColumn(5).setMaxWidth(80);
            tabla_ventas.getColumnModel().getColumn(6).setMinWidth(95);
            tabla_ventas.getColumnModel().getColumn(6).setMaxWidth(95);
        }

        txtrucclifact.setEditable(false);
        txtrucclifact.setText("-");

        txtPesoBascula.setBackground(new java.awt.Color(0, 204, 0));
        txtPesoBascula.setFont(new java.awt.Font("Courier New", 1, 37)); // NOI18N
        txtPesoBascula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPesoBascula.setText("00.00");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Bascula");

        javax.swing.GroupLayout panelVentasLayout = new javax.swing.GroupLayout(panelVentas);
        panelVentas.setLayout(panelVentasLayout);
        panelVentasLayout.setHorizontalGroup(
            panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelVentasLayout.createSequentialGroup()
                        .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelVentasLayout.createSequentialGroup()
                                .addComponent(jLabel103)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelVentasLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(6, 6, 6)
                                        .addComponent(lblnumventa, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelVentasLayout.createSequentialGroup()
                                        .addComponent(jLabel104)
                                        .addGap(4, 4, 4)
                                        .addComponent(txtcodbarrasvent, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10)
                                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel106)
                                    .addComponent(jLabel105))
                                .addGap(10, 10, 10)
                                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelVentasLayout.createSequentialGroup()
                                        .addComponent(txtclienteventas, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)
                                        .addComponent(txtrucclifact, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34)
                                        .addComponent(jButton9))
                                    .addComponent(txtprovent))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jXTaskPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelVentasLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPesoBascula, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtiva, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTotales, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtsubtotalvent, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelVentasLayout.createSequentialGroup()
                        .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator12, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelVentasLayout.createSequentialGroup()
                                .addComponent(panelBotonesAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, 925, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(cbTipoPagosVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4)
                                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbModo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelVentasLayout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelVentasLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbTipoventa, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        panelVentasLayout.setVerticalGroup(
            panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVentasLayout.createSequentialGroup()
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelBotonesAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelVentasLayout.createSequentialGroup()
                        .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4))
                        .addGap(6, 6, 6)
                        .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbModo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTipoventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTipoPagosVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVentasLayout.createSequentialGroup()
                        .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelVentasLayout.createSequentialGroup()
                                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblnumventa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtcodbarrasvent, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtclienteventas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtrucclifact, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelVentasLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jXTaskPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelVentasLayout.createSequentialGroup()
                                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtprovent, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelVentasLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel24)
                        .addGap(5, 5, 5)
                        .addComponent(txtPesoBascula, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtsubtotalvent, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(txtiva, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addGap(12, 12, 12)
                        .addComponent(txtTotales, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCobrar)
                        .addGap(0, 120, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tbbPanelesMenu.addTab("Punto de venta", panelVentas);

        panelCompras.setOpaque(false);

        jPanel6.setOpaque(false);
        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Historial.png"))); // NOI18N
        jButton5.setText("HISTORIAL");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton5);

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Filtro.png"))); // NOI18N
        jButton6.setText("FILTRAR");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton6);

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/1479069202_money-raise.png"))); // NOI18N
        jButton8.setText("COMPRAS");
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton8);

        jButton11.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/ProductSal.png"))); // NOI18N
        jButton11.setText("PRODUCT.");
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton11);

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/EliminarProd.png"))); // NOI18N
        jButton4.setText("ELIMINAR");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton4);

        jButton43.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/EliminarVenta.png"))); // NOI18N
        jButton43.setText("ANULAR");
        jButton43.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton43.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton43);

        tabla_compras.setFont(new java.awt.Font("Courier New", 0, 20)); // NOI18N
        tabla_compras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Detalles", "Stock", "P.Compra", "Cantidad", "Cantidad S.", "% Desc", "Parcial"
            }
        ));
        tabla_compras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_comprasKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabla_comprasKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tabla_compras);
        if (tabla_compras.getColumnModel().getColumnCount() > 0) {
            tabla_compras.getColumnModel().getColumn(0).setMinWidth(180);
            tabla_compras.getColumnModel().getColumn(0).setMaxWidth(180);
            tabla_compras.getColumnModel().getColumn(2).setMinWidth(80);
            tabla_compras.getColumnModel().getColumn(2).setMaxWidth(80);
            tabla_compras.getColumnModel().getColumn(3).setMinWidth(95);
            tabla_compras.getColumnModel().getColumn(3).setMaxWidth(95);
            tabla_compras.getColumnModel().getColumn(4).setMinWidth(80);
            tabla_compras.getColumnModel().getColumn(4).setMaxWidth(80);
            tabla_compras.getColumnModel().getColumn(5).setMinWidth(80);
            tabla_compras.getColumnModel().getColumn(5).setMaxWidth(80);
            tabla_compras.getColumnModel().getColumn(6).setMinWidth(80);
            tabla_compras.getColumnModel().getColumn(6).setMaxWidth(80);
            tabla_compras.getColumnModel().getColumn(7).setMinWidth(95);
            tabla_compras.getColumnModel().getColumn(7).setMaxWidth(95);
        }

        txtbarcodcomp.setBackground(new java.awt.Color(255, 255, 204));
        txtbarcodcomp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtbarcodcomp.setToolTipText("Código de barras");
        txtbarcodcomp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbarcodcompActionPerformed(evt);
            }
        });
        txtbarcodcomp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbarcodcompKeyTyped(evt);
            }
        });

        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel78.setText("Proveedor:");

        cbProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProveedorActionPerformed(evt);
            }
        });

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel79.setText("N° Compra:");

        lblnumcomp.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblnumcomp.setForeground(new java.awt.Color(153, 0, 0));
        lblnumcomp.setText("jLabel80");

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel84.setText("Encargado:");

        cbUsuarioCompra.setEnabled(false);

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel80.setText("Tipo:");

        cbDoccomp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Factura", "Orden de compra" }));

        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel86.setText("Forma de pago:");

        cbFormadepago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Contado", "Crédito", "Tarjeta" }));

        jPanel31.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel31.setOpaque(false);

        txtObservaciones.setText("Comentarios...");
        txtObservaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtObservacionesActionPerformed(evt);
            }
        });

        jLabel180.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/InfoIniP.png"))); // NOI18N
        jLabel180.setText("Mafakafer Punto de Venta");

        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel183.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel183.setText("Agregar comentario");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addComponent(jLabel180, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel183)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtObservaciones))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator11)
            .addComponent(jLabel180, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel183, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel82.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel82.setText("Subtotal");

        jLabel83.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setText("IVA Ponderado");

        lblivacomp.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lblivacomp.setForeground(new java.awt.Color(0, 0, 204));
        lblivacomp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        txtTotalComp.setEditable(false);
        txtTotalComp.setBackground(new java.awt.Color(0, 0, 0));
        txtTotalComp.setFont(new java.awt.Font("Courier New", 1, 37)); // NOI18N
        txtTotalComp.setForeground(new java.awt.Color(0, 204, 102));
        txtTotalComp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalComp.setText("0");

        btcompras.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btcompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Access.png"))); // NOI18N
        btcompras.setText("Pagar compra");
        btcompras.setToolTipText("Pagar compra Alt+F3");
        btcompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcomprasActionPerformed(evt);
            }
        });

        jLabel181.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel181.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel181.setText("Total a pagar");

        jLabel182.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel182.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Barcode.png"))); // NOI18N
        jLabel182.setText("Código de barras");

        jLabel23.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 204));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("0%");

        jLabel27.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("IVA Total");

        lblsubcomp.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lblsubcomp.setForeground(new java.awt.Color(0, 0, 204));
        lblsubcomp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jXTaskPane1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/InfoIniP.png"))); // NOI18N
        jXTaskPane1.setTitle("MAFAKAFER");

        lblimgcompras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXTaskPane1.getContentPane().add(lblimgcompras);

        panelImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/BannerProfAzul.png"))); // NOI18N

        jLabel177.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel177.setForeground(new java.awt.Color(255, 255, 255));
        jLabel177.setText("REGISTRO DE COMPRAS PROVEEDOR");

        txtrutacompras.setText("Ruta imagen");

        txtdireccionprov.setEditable(false);
        txtdireccionprov.setText("Direccion del proveedor");

        javax.swing.GroupLayout panelImage2Layout = new javax.swing.GroupLayout(panelImage2);
        panelImage2.setLayout(panelImage2Layout);
        panelImage2Layout.setHorizontalGroup(
            panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel177, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addComponent(txtrutacompras, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtdireccionprov, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelImage2Layout.setVerticalGroup(
            panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel177)
                    .addComponent(txtrutacompras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdireccionprov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtBascula.setBackground(new java.awt.Color(0, 204, 0));
        txtBascula.setFont(new java.awt.Font("Courier New", 1, 37)); // NOI18N
        txtBascula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBascula.setText("00.00");

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("Bascula");

        javax.swing.GroupLayout panelComprasLayout = new javax.swing.GroupLayout(panelCompras);
        panelCompras.setLayout(panelComprasLayout);
        panelComprasLayout.setHorizontalGroup(
            panelComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator9)
            .addComponent(panelImage2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelComprasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelComprasLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel79)
                        .addGap(5, 5, 5)
                        .addComponent(lblnumcomp, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(jLabel84)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbUsuarioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelComprasLayout.createSequentialGroup()
                        .addComponent(jLabel182)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtbarcodcomp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel78)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(183, 183, 183)
                        .addComponent(jLabel80)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbDoccomp, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel86)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbFormadepago, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelComprasLayout.createSequentialGroup()
                        .addGroup(panelComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtBascula)
                                .addComponent(jLabel82, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblsubcomp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel83, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblivacomp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel181, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTotalComp, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btcompras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelComprasLayout.createSequentialGroup()
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35))
                            .addComponent(jXTaskPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        panelComprasLayout.setVerticalGroup(
            panelComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelComprasLayout.createSequentialGroup()
                .addComponent(panelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelComprasLayout.createSequentialGroup()
                        .addGroup(panelComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbUsuarioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel84))
                            .addGroup(panelComprasLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel79))
                            .addComponent(lblnumcomp))
                        .addGap(18, 18, 18))
                    .addGroup(panelComprasLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel80)
                        .addComponent(cbDoccomp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel86)
                        .addComponent(cbFormadepago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel182)
                        .addComponent(txtbarcodcomp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel78)
                        .addComponent(cbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelComprasLayout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addGap(2, 2, 2)
                        .addComponent(txtBascula, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jXTaskPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel82)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblsubcomp, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel83)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblivacomp, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel181)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btcompras, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tbbPanelesMenu.addTab("Punto de compra", panelCompras);

        panelProductos.setOpaque(false);

        txtbuscarp.setText("Buscar productos");
        txtbuscarp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtbuscarpMouseClicked(evt);
            }
        });
        txtbuscarp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarpKeyReleased(evt);
            }
        });

        rdTodasClases.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rdTodasClases.setForeground(new java.awt.Color(153, 0, 0));
        rdTodasClases.setText("Todas las clases");
        rdTodasClases.setOpaque(false);
        rdTodasClases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdTodasClasesActionPerformed(evt);
            }
        });

        cbMarcas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Seleccionar---" }));
        cbMarcas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMarcasActionPerformed(evt);
            }
        });

        jLabel108.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel108.setText("Marcas:");

        cbCategorias.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Seleccionar---" }));
        cbCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCategoriasActionPerformed(evt);
            }
        });

        jLabel109.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel109.setText("Categorías:");

        jLabel110.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel110.setText("Unidades:");

        cbUnidades.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Seleccionar---" }));
        cbUnidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbUnidadesActionPerformed(evt);
            }
        });

        btNuevoProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Nuevos.png"))); // NOI18N
        btNuevoProducto.setToolTipText("Nuevo producto");
        btNuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevoProductoActionPerformed(evt);
            }
        });

        btExpotProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Excel.png"))); // NOI18N
        btExpotProductos.setText("Exportar a Excel");
        btExpotProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExpotProductosActionPerformed(evt);
            }
        });

        tabla_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla_productos.setColorSelBackgound(new java.awt.Color(204, 204, 0));
        tabla_productos.setColorSelForeground(new java.awt.Color(0, 102, 204));
        tabla_productos.setFuenteHead(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tabla_productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_productosMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tabla_productos);

        javax.swing.GroupLayout panelProductosLayout = new javax.swing.GroupLayout(panelProductos);
        panelProductos.setLayout(panelProductosLayout);
        panelProductosLayout.setHorizontalGroup(
            panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10)
                    .addGroup(panelProductosLayout.createSequentialGroup()
                        .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(panelProductosLayout.createSequentialGroup()
                                .addComponent(btExpotProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdTodasClases, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panelProductosLayout.createSequentialGroup()
                                .addComponent(btNuevoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtbuscarp, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel108)
                        .addGap(11, 11, 11)
                        .addComponent(cbMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel109)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel110)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 99, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelProductosLayout.setVerticalGroup(
            panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscarp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel108)
                    .addComponent(cbCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel109)
                    .addComponent(jLabel110)
                    .addComponent(cbUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNuevoProducto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btExpotProductos)
                    .addComponent(rdTodasClases))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbbPanelesMenu.addTab("Productos", panelProductos);

        panelListaInventarios.setOpaque(false);

        txtcant.setEditable(false);
        txtcant.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Producto:");

        txtbprod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbprodKeyReleased(evt);
            }
        });

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Notificaciones.png"))); // NOI18N
        jButton12.setText("Notificaciones");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Buscar.png"))); // NOI18N
        jButton19.setText("Buscar");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Excel.png"))); // NOI18N
        jButton20.setText("Exportar a Excel");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        tabla_stock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla_stock.setColorFilasBackgound1(new java.awt.Color(0, 153, 255));
        tabla_stock.setColorSelBackgound(new java.awt.Color(204, 204, 0));
        tabla_stock.setFuenteHead(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jScrollPane5.setViewportView(tabla_stock);

        javax.swing.GroupLayout panelListaInventariosLayout = new javax.swing.GroupLayout(panelListaInventarios);
        panelListaInventarios.setLayout(panelListaInventariosLayout);
        panelListaInventariosLayout.setHorizontalGroup(
            panelListaInventariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelListaInventariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelListaInventariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelListaInventariosLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtbprod)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(txtcant, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );
        panelListaInventariosLayout.setVerticalGroup(
            panelListaInventariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelListaInventariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelListaInventariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtbprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12)
                    .addComponent(jButton19)
                    .addComponent(jButton20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbbPanelesMenu.addTab("Inventarios", panelListaInventarios);

        panelInventariosManual.setOpaque(false);
        panelInventariosManual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelInventariosManualMouseClicked(evt);
            }
        });

        jLabel131.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel131.setText("Código de barra del producto:");

        txtcbarra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcbarra.setToolTipText("Ingrese código de barra");
        txtcbarra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcbarraKeyPressed(evt);
            }
        });

        jLabel132.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel132.setText("Descripción del producto:");

        jLabel133.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel133.setText("Cantidad actual:");

        txtcantactual.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel134.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel134.setText("Nueva cantidad:");

        spAjuste.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spAjusteStateChanged(evt);
            }
        });

        btAjustar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Ok.png"))); // NOI18N
        btAjustar.setText("Agregar nueva cantidad a su inventario");
        btAjustar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAjustarActionPerformed(evt);
            }
        });

        jPanel18.setBackground(new java.awt.Color(255, 255, 204));
        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 204), null));

        jLabel135.setText("Al realizar este ajuste se agregará la cantidad actual y se modificará la cantidad existente de su producto");

        jLabel136.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Info.png"))); // NOI18N

        jLabel137.setText("una vez realizado los cambios puede ver su nuevo inventario.");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel136)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel137, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel135, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel135)
                    .addComponent(jLabel136))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel137)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtdescriproducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblnotacodigo.setForeground(new java.awt.Color(204, 51, 0));
        lblnotacodigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/InfoIniP.png"))); // NOI18N
        lblnotacodigo.setText("Ingrese aquí código de barra del producto");

        lblnomproductoInventario.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblnomproductoInventario.setForeground(new java.awt.Color(0, 51, 153));
        lblnomproductoInventario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        panelImage3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/BannerProfAzul.png"))); // NOI18N

        jLabel130.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel130.setForeground(new java.awt.Color(255, 255, 255));
        jLabel130.setText("AGREGAR NUEVO INVENTARIO");

        javax.swing.GroupLayout panelImage3Layout = new javax.swing.GroupLayout(panelImage3);
        panelImage3.setLayout(panelImage3Layout);
        panelImage3Layout.setHorizontalGroup(
            panelImage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel130, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelImage3Layout.setVerticalGroup(
            panelImage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel130)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Ok.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInventariosManualLayout = new javax.swing.GroupLayout(panelInventariosManual);
        panelInventariosManual.setLayout(panelInventariosManualLayout);
        panelInventariosManualLayout.setHorizontalGroup(
            panelInventariosManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelInventariosManualLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInventariosManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInventariosManualLayout.createSequentialGroup()
                        .addGroup(panelInventariosManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel132, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInventariosManualLayout.createSequentialGroup()
                                .addComponent(jLabel131)
                                .addGap(10, 10, 10))
                            .addGroup(panelInventariosManualLayout.createSequentialGroup()
                                .addGroup(panelInventariosManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelInventariosManualLayout.createSequentialGroup()
                                        .addGap(85, 85, 85)
                                        .addComponent(jLabel134))
                                    .addGroup(panelInventariosManualLayout.createSequentialGroup()
                                        .addGap(83, 83, 83)
                                        .addComponent(jLabel133)))
                                .addGap(12, 12, 12)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelInventariosManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spAjuste, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btAjustar, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcantactual, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelInventariosManualLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelInventariosManualLayout.createSequentialGroup()
                                .addGroup(panelInventariosManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtcbarra, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                                    .addComponent(txtdescriproducto))
                                .addGap(23, 23, 23)
                                .addComponent(lblnotacodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblnomproductoInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelInventariosManualLayout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panelInventariosManualLayout.setVerticalGroup(
            panelInventariosManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInventariosManualLayout.createSequentialGroup()
                .addComponent(panelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelInventariosManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInventariosManualLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(lblnomproductoInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel131, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel132, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel133, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel134, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInventariosManualLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevo)
                        .addGap(71, 71, 71)
                        .addGroup(panelInventariosManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcbarra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblnotacodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(txtdescriproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtcantactual, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(spAjuste, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btAjustar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tbbPanelesMenu.addTab("Inventario manual", panelInventariosManual);

        panelListaProductos.setOpaque(false);

        rdBuscarprodimg.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rdBuscarprodimg.setForeground(new java.awt.Color(153, 0, 0));
        rdBuscarprodimg.setText("Todos los productos");
        rdBuscarprodimg.setOpaque(false);
        rdBuscarprodimg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdBuscarprodimgActionPerformed(evt);
            }
        });

        tabla_productosimg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla_productosimg.setColorSelBackgound(new java.awt.Color(204, 204, 0));
        tabla_productosimg.setColorSelForeground(new java.awt.Color(0, 102, 204));
        tabla_productosimg.setFuenteFilas(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        tabla_productosimg.setFuenteFilasSelect(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        tabla_productosimg.setFuenteHead(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tabla_productosimg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_productosimgMouseClicked(evt);
            }
        });
        tabla_productosimg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_productosimgKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tabla_productosimgKeyTyped(evt);
            }
        });
        jScrollPane11.setViewportView(tabla_productosimg);

        lblprodimg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblprodimg.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblprepimg.setFont(new java.awt.Font("Verdana", 0, 36)); // NOI18N
        lblprepimg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblnompimg.setFont(new java.awt.Font("Verdana", 0, 36)); // NOI18N
        lblnompimg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        txtbuscarpimg.setPlaceholder("Buscar producto...");
        txtbuscarpimg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtbuscarpimgMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtbuscarpimgMousePressed(evt);
            }
        });
        txtbuscarpimg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarpimgKeyReleased(evt);
            }
        });

        lblistaproductos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblistaproductos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelListaProductosLayout = new javax.swing.GroupLayout(panelListaProductos);
        panelListaProductos.setLayout(panelListaProductosLayout);
        panelListaProductosLayout.setHorizontalGroup(
            panelListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListaProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11)
                    .addGroup(panelListaProductosLayout.createSequentialGroup()
                        .addComponent(txtbuscarpimg, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdBuscarprodimg)
                        .addGap(6, 6, 6)
                        .addComponent(lblistaproductos, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(panelListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblprodimg, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                    .addComponent(lblprepimg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblnompimg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(46, 46, 46))
        );
        panelListaProductosLayout.setVerticalGroup(
            panelListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelListaProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtbuscarpimg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdBuscarprodimg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblistaproductos, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelListaProductosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblnompimg, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblprodimg, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblprepimg, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118))
                    .addGroup(panelListaProductosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane11)
                        .addContainerGap())))
        );

        tbbPanelesMenu.addTab("Lista de productos", panelListaProductos);

        panelMovimientos.setOpaque(false);

        txtbuspm.setText("Ingrese nombre del producto");
        txtbuspm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtbuspmMouseClicked(evt);
            }
        });
        txtbuspm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuspmKeyReleased(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel59.setText("Fecha:");

        txtTotalEntradas.setEditable(false);
        txtTotalEntradas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTotalEntradas.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtTotalSalidas.setEditable(false);
        txtTotalSalidas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTotalSalidas.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtTotalEntradasSalidas.setEditable(false);
        txtTotalEntradasSalidas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTotalEntradasSalidas.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Totales:");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Buscar.png"))); // NOI18N
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnExportarTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Excel.png"))); // NOI18N
        btnExportarTabla.setText("Exportar a Excel");
        btnExportarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarTablaActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setText("Tipo:");

        cbTipVent.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Seleccionar---", "Ticket", "Factura", "Pedidos", "Devolución" }));
        cbTipVent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipVentActionPerformed(evt);
            }
        });

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Buscar.png"))); // NOI18N

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel77.setText("Motivos:");

        cbMotivos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Seleccionar---", "Ventas", "Pedidos", "Orden de compra" }));
        cbMotivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMotivosActionPerformed(evt);
            }
        });

        jButton34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/PrintP.png"))); // NOI18N
        jButton34.setText("Imprimir Kardex Físico");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        tablakardex.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablakardex.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tablakardex.setFuenteHead(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tablakardex.setRowHeight(17);
        jScrollPane2.setViewportView(tablakardex);

        javax.swing.GroupLayout panelMovimientosLayout = new javax.swing.GroupLayout(panelMovimientos);
        panelMovimientos.setLayout(panelMovimientosLayout);
        panelMovimientosLayout.setHorizontalGroup(
            panelMovimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMovimientosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExportarTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTotalEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalEntradasSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(panelMovimientosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMovimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(panelMovimientosLayout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbuspm, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel77)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbMotivos, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbTipVent, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdFechaMov, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelMovimientosLayout.setVerticalGroup(
            panelMovimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMovimientosLayout.createSequentialGroup()
                .addGroup(panelMovimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMovimientosLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(panelMovimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtbuspm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34)
                            .addComponent(cbTipVent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel77)
                            .addComponent(cbMotivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMovimientosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelMovimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMovimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel59, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jdFechaMov, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelMovimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalEntradasSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(btnExportarTabla)
                    .addComponent(jButton34))
                .addContainerGap())
        );

        tbbPanelesMenu.addTab("Control de movimientos", panelMovimientos);

        jPanel10.setOpaque(false);

        javax.swing.GroupLayout paneInformeVentasLayout = new javax.swing.GroupLayout(paneInformeVentas);
        paneInformeVentas.setLayout(paneInformeVentasLayout);
        paneInformeVentasLayout.setHorizontalGroup(
            paneInformeVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        paneInformeVentasLayout.setVerticalGroup(
            paneInformeVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 573, Short.MAX_VALUE)
        );

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Filtrar.png"))); // NOI18N
        jButton17.setText("Filtrar");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel69.setText("Desde:");

        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel91.setText("Hasta:");

        jButton33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Ok.png"))); // NOI18N
        jButton33.setText("Ver gráfica");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel69)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel91)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(841, Short.MAX_VALUE))
                    .addComponent(paneInformeVentas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jdFecha2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel91, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdFecha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paneInformeVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Ventas", jPanel10);

        jPanel11.setOpaque(false);

        jLabel92.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel92.setText("Desde:");

        jLabel93.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel93.setText("Hasta:");

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Ok.png"))); // NOI18N
        jButton18.setText("Ver gráfica");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Filtrar.png"))); // NOI18N
        jButton41.setText("Filtrar");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInformeComprasLayout = new javax.swing.GroupLayout(panelInformeCompras);
        panelInformeCompras.setLayout(panelInformeComprasLayout);
        panelInformeComprasLayout.setHorizontalGroup(
            panelInformeComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelInformeComprasLayout.setVerticalGroup(
            panelInformeComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 575, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelInformeCompras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel92)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdFechaC1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel93)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdFechaC2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 829, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jdFechaC2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel93, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdFechaC1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel92, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInformeCompras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Compras", jPanel11);

        jPanel12.setOpaque(false);

        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel95.setText("Seleccione empleado:");

        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Filtrar.png"))); // NOI18N
        jButton23.setText("Filtrar");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInformeEmpleadosLayout = new javax.swing.GroupLayout(panelInformeEmpleados);
        panelInformeEmpleados.setLayout(panelInformeEmpleadosLayout);
        panelInformeEmpleadosLayout.setHorizontalGroup(
            panelInformeEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelInformeEmpleadosLayout.setVerticalGroup(
            panelInformeEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 573, Short.MAX_VALUE)
        );

        jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Buscar.png"))); // NOI18N
        jButton26.setText("Buscar");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jLabel97.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel97.setText("Fecha:");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel95)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbEmpledosInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel97)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panelInformeEmpleados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbEmpledosInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jdateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInformeEmpleados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Ventas por empleados", jPanel12);

        jPanel13.setOpaque(false);

        jLabel96.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel96.setText("Seleccionar producto:");

        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Filtrar.png"))); // NOI18N
        jButton24.setText("Filtrar");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInformeInventariosLayout = new javax.swing.GroupLayout(panelInformeInventarios);
        panelInformeInventarios.setLayout(panelInformeInventariosLayout);
        panelInformeInventariosLayout.setHorizontalGroup(
            panelInformeInventariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelInformeInventariosLayout.setVerticalGroup(
            panelInformeInventariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 572, Short.MAX_VALUE)
        );

        cbInformeProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbInformeProductosActionPerformed(evt);
            }
        });

        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Buscar.png"))); // NOI18N
        jButton25.setText("Buscar");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelInformeInventarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel96)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbInformeProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 758, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbInformeProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton24)
                    .addComponent(jButton25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInformeInventarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Informe de movimientos", jPanel13);

        jPanel14.setOpaque(false);

        javax.swing.GroupLayout panelVentasHoyLayout = new javax.swing.GroupLayout(panelVentasHoy);
        panelVentasHoy.setLayout(panelVentasHoyLayout);
        panelVentasHoyLayout.setHorizontalGroup(
            panelVentasHoyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelVentasHoyLayout.setVerticalGroup(
            panelVentasHoyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 573, Short.MAX_VALUE)
        );

        jLabel111.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel111.setText("Fecha a buscar:");

        jdFechaHoy.setOpaque(false);

        jButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Buscar.png"))); // NOI18N
        jButton27.setText("Buscar 100 últimas ventas");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelVentasHoy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel111)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdFechaHoy, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 946, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jdFechaHoy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel111, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelVentasHoy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Ventas de hoy", jPanel14);

        jPanel15.setOpaque(false);

        jLabel112.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel112.setText("Fecha a buscar:");

        jdComprasHoy.setOpaque(false);

        jButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Buscar.png"))); // NOI18N
        jButton28.setText("Buscar 100 últimas compras");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelComprasHoyLayout = new javax.swing.GroupLayout(panelComprasHoy);
        panelComprasHoy.setLayout(panelComprasHoyLayout);
        panelComprasHoyLayout.setHorizontalGroup(
            panelComprasHoyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelComprasHoyLayout.setVerticalGroup(
            panelComprasHoyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 573, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelComprasHoy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel112)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdComprasHoy, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 946, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jdComprasHoy, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel112, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelComprasHoy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Compras de hoy", jPanel15);

        javax.swing.GroupLayout panelInformesLayout = new javax.swing.GroupLayout(panelInformes);
        panelInformes.setLayout(panelInformesLayout);
        panelInformesLayout.setHorizontalGroup(
            panelInformesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panelInformesLayout.setVerticalGroup(
            panelInformesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        tbbPanelesMenu.addTab("Informes estadísticos", panelInformes);

        panelConfig.setOpaque(false);

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Fondo5.jpg"))); // NOI18N

        javax.swing.GroupLayout panelOpenKleeLayout = new javax.swing.GroupLayout(panelOpenKlee);
        panelOpenKlee.setLayout(panelOpenKleeLayout);
        panelOpenKleeLayout.setHorizontalGroup(
            panelOpenKleeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelOpenKleeLayout.setVerticalGroup(
            panelOpenKleeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 625, Short.MAX_VALUE)
        );

        tbbPanelesConfiguraciones.addTab("Mafakafer", panelOpenKlee);

        panelEmpresa.setOpaque(false);

        jLabel139.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel139.setText("Empresa:");

        jLabel140.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel140.setText("Propietarios:");

        jLabel141.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel141.setText("Dirección:");

        jLabel142.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel142.setText("Zonal:");

        jLabel143.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel143.setText("Referencias:");

        jLabel144.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel144.setText("Teléfono:");

        jLabel145.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel145.setText("Celular:");

        jLabel146.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel146.setText("Cedula:");

        jLabel147.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel147.setText("Email:");

        jLabel148.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel148.setText("Sitio web:");

        txtemp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtempKeyPressed(evt);
            }
        });

        txtref.setColumns(20);
        txtref.setRows(5);
        jScrollPane8.setViewportView(txtref);

        btGEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Guardar.png"))); // NOI18N
        btGEmp.setText("Guardar");
        btGEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGEmpActionPerformed(evt);
            }
        });

        btAEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Actualizar.png"))); // NOI18N
        btAEmp.setText("Actualizar");
        btAEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAEmpActionPerformed(evt);
            }
        });

        txtrutaimagenemp.setEditable(false);

        panelImgEmpresa.setOpaque(false);

        btSFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/SubirFoto.png"))); // NOI18N
        btSFoto.setText("Subir imágen");
        btSFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSFotoActionPerformed(evt);
            }
        });

        lblimagenemp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblimagenemp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblimagenemp.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panelImgEmpresaLayout = new javax.swing.GroupLayout(panelImgEmpresa);
        panelImgEmpresa.setLayout(panelImgEmpresaLayout);
        panelImgEmpresaLayout.setHorizontalGroup(
            panelImgEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImgEmpresaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelImgEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btSFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblimagenemp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtcodemp, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelImgEmpresaLayout.setVerticalGroup(
            panelImgEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImgEmpresaLayout.createSequentialGroup()
                .addComponent(lblimagenemp, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btSFoto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtcodemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 271, Short.MAX_VALUE))
        );

        jLabel107.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel107.setText("Ruta:");

        jLabel149.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel149.setText("Fecha de registro:");

        panelImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/BannerProfAzul.png"))); // NOI18N

        jLabel138.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel138.setForeground(new java.awt.Color(255, 255, 255));
        jLabel138.setText("REGISTRO DE EMPRESA");

        javax.swing.GroupLayout panelImage4Layout = new javax.swing.GroupLayout(panelImage4);
        panelImage4.setLayout(panelImage4Layout);
        panelImage4Layout.setHorizontalGroup(
            panelImage4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel138, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(919, Short.MAX_VALUE))
        );
        panelImage4Layout.setVerticalGroup(
            panelImage4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel138, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btEEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Eliminar.png"))); // NOI18N
        btEEmp.setText("Eliminar");
        btEEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEEmpActionPerformed(evt);
            }
        });

        btNEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Nuevos.png"))); // NOI18N
        btNEmp.setText("Nuevo");
        btNEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNEmpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelEmpresaLayout = new javax.swing.GroupLayout(panelEmpresa);
        panelEmpresa.setLayout(panelEmpresaLayout);
        panelEmpresaLayout.setHorizontalGroup(
            panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmpresaLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEmpresaLayout.createSequentialGroup()
                        .addGroup(panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel139)
                            .addComponent(jLabel140)
                            .addComponent(jLabel141)
                            .addComponent(jLabel142)
                            .addComponent(jLabel143))
                        .addGap(18, 18, 18)
                        .addGroup(panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtzon)
                            .addComponent(txtdir)
                            .addComponent(txtprop)
                            .addComponent(txtemp)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel144)
                            .addComponent(jLabel145)
                            .addComponent(jLabel146)
                            .addComponent(jLabel147)
                            .addComponent(jLabel148)
                            .addComponent(jLabel149)
                            .addComponent(jLabel107))
                        .addGap(18, 18, 18)
                        .addGroup(panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtrutaimagenemp)
                            .addComponent(txtweb)
                            .addComponent(txtemail)
                            .addComponent(txt1)
                            .addComponent(txt2)
                            .addComponent(txtruc)
                            .addComponent(txtfechareg, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelEmpresaLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(btNEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btGEmp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btAEmp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btEEmp)))
                .addGap(36, 36, 36)
                .addComponent(panelImgEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(166, 166, 166))
            .addComponent(panelImage4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelEmpresaLayout.setVerticalGroup(
            panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmpresaLayout.createSequentialGroup()
                .addComponent(panelImage4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelImgEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelEmpresaLayout.createSequentialGroup()
                        .addGroup(panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelEmpresaLayout.createSequentialGroup()
                                .addGroup(panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel139)
                                    .addComponent(txtemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel140)
                                    .addComponent(txtprop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel141)
                                    .addComponent(txtdir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel142)
                                    .addComponent(txtzon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel143)
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelEmpresaLayout.createSequentialGroup()
                                .addGroup(panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel144)
                                    .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel145)
                                    .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel146)
                                    .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel147)
                                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel148)
                                    .addComponent(txtweb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel149)
                                    .addComponent(txtfechareg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel107)
                                    .addComponent(txtrutaimagenemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(87, 87, 87)
                        .addGroup(panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btGEmp)
                            .addComponent(btAEmp)
                            .addComponent(btEEmp)
                            .addComponent(btNEmp)))))
        );

        tbbPanelesConfiguraciones.addTab("Empresa", panelEmpresa);

        panelTicket.setOpaque(false);

        panelImage9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/BannerProfAzul.png"))); // NOI18N

        jLabel187.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel187.setForeground(new java.awt.Color(255, 255, 255));
        jLabel187.setText("CONFIGURACIÓN DE TICKETS");

        javax.swing.GroupLayout panelImage9Layout = new javax.swing.GroupLayout(panelImage9);
        panelImage9.setLayout(panelImage9Layout);
        panelImage9Layout.setHorizontalGroup(
            panelImage9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel187, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelImage9Layout.setVerticalGroup(
            panelImage9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel187, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel25.setOpaque(false);

        btSFotoTick.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/SubirFoto.png"))); // NOI18N
        btSFotoTick.setText("Subir logo");
        btSFotoTick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSFotoTickActionPerformed(evt);
            }
        });

        lbllogoticket.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbllogoticket.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lbllogoticket.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btNTicket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/NuevosT.png"))); // NOI18N
        btNTicket.setText("Nuevo");
        btNTicket.setToolTipText("");
        btNTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNTicketActionPerformed(evt);
            }
        });

        btGTicket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/GuardarT.png"))); // NOI18N
        btGTicket.setText("Guardar");
        btGTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGTicketActionPerformed(evt);
            }
        });

        btATicket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/ActualizarT.png"))); // NOI18N
        btATicket.setText("Actualizar");
        btATicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btATicketActionPerformed(evt);
            }
        });

        btETicket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/EliminarT.png"))); // NOI18N
        btETicket.setText("Eliminar");
        btETicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btETicketActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtcodticket, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbllogoticket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btNTicket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btGTicket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btATicket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btETicket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(btSFotoTick, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtrutaimgtick))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbllogoticket, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btSFotoTick)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtcodticket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btNTicket)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btGTicket)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btATicket)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btETicket)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtrutaimgtick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel23.setBackground(new java.awt.Color(255, 255, 204));
        jPanel23.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 204), null));

        jLabel155.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Info.png"))); // NOI18N
        jLabel155.setText("Una vez configurado su ticket podra hacer uso del mismo en una impresión de prueba.");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel155, javax.swing.GroupLayout.DEFAULT_SIZE, 1394, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel155)
                .addContainerGap())
        );

        txtnomticket.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnomticket.setText("EMPRESA DE PRUEBA");
        txtnomticket.setToolTipText("Nombre de empresa");
        txtnomticket.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        txtnomticket.setOpaque(false);
        txtnomticket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnomticketActionPerformed(evt);
            }
        });

        txtenticket.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtenticket.setText("ENCABEZADO/PROPIETARIOS");
        txtenticket.setToolTipText("");
        txtenticket.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        txtenticket.setOpaque(false);
        txtenticket.setPlaceholder("Encabezado/Propietarios");

        txtdirectiticket.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtdirectiticket.setText("DIRECCIÓN");
        txtdirectiticket.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        txtdirectiticket.setOpaque(false);
        txtdirectiticket.setPlaceholder("Dirección");

        txtructick.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtructick.setText("CEDULA");
        txtructick.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        txtructick.setOpaque(false);
        txtructick.setPlaceholder("R.U.C/Pasaporte");

        txtteltick.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtteltick.setText("TELÉFONO");
        txtteltick.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        txtteltick.setOpaque(false);
        txtteltick.setPlaceholder("Teléfono");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setText("Cant.");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel46.setText("Producto");

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel63.setText("Imp");

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel72.setText("Tot.");

        jLabel89.setText("..................................................................................");

        jLabel94.setText("..................................................................................");

        jLabel117.setText("..................................................................................");

        txtpieticket.setColumns(20);
        txtpieticket.setRows(5);
        txtpieticket.setText("****Gracias por su compra****");
        txtpieticket.setToolTipText("Pie del ticket");
        jScrollPane9.setViewportView(txtpieticket);

        jLabel128.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel128.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel128.setText("Total Pesos");

        jLabel102.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel102.setText("25.50");

        jLabel129.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel129.setText("Subt.:");

        jLabel151.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel151.setText("I.V.A:");

        jLabel152.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel152.setText("0,00");

        jLabel153.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel153.setText("0");

        javax.swing.GroupLayout rSPanelShadow1Layout = new javax.swing.GroupLayout(rSPanelShadow1);
        rSPanelShadow1.setLayout(rSPanelShadow1Layout);
        rSPanelShadow1Layout.setHorizontalGroup(
            rSPanelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelShadow1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(rSPanelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelShadow1Layout.createSequentialGroup()
                        .addGroup(rSPanelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel151)
                            .addComponent(jLabel129))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(rSPanelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel152, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel153, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelShadow1Layout.createSequentialGroup()
                        .addGroup(rSPanelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtnomticket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtdirectiticket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtenticket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtructick, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtteltick, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(rSPanelShadow1Layout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel89, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel94, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel117, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane9)
                            .addComponent(jLabel128, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel102, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20))))
        );
        rSPanelShadow1Layout.setVerticalGroup(
            rSPanelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelShadow1Layout.createSequentialGroup()
                .addComponent(txtnomticket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtenticket, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtdirectiticket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtructick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtteltick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rSPanelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel46)
                    .addComponent(jLabel63)
                    .addComponent(jLabel72))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel89)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel94)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel117)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rSPanelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel129)
                    .addComponent(jLabel152, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rSPanelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel151)
                    .addComponent(jLabel153, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel128)
                .addGap(5, 5, 5)
                .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txtbticket.setOpaque(false);
        txtbticket.setPlaceholder("Buscar ticket registrado...");
        txtbticket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbticketKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout panelTicketLayout = new javax.swing.GroupLayout(panelTicket);
        panelTicket.setLayout(panelTicketLayout);
        panelTicketLayout.setHorizontalGroup(
            panelTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelTicketLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(rSPanelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtbticket, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(399, Short.MAX_VALUE))
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelTicketLayout.setVerticalGroup(
            panelTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTicketLayout.createSequentialGroup()
                .addComponent(panelImage9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTicketLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSPanelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelTicketLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(txtbticket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tbbPanelesConfiguraciones.addTab("Ticket", panelTicket);

        panelTemas.setOpaque(false);

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/InfoIniP.png"))); // NOI18N
        jLabel26.setText("Configure todos los diseños y gráficos del sistema mediante los temas presentados en la lista actual.");

        panelImage5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/BannerProfAzul.png"))); // NOI18N

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("CONFIGURAR APARIENCIA DEL SISTEMA");

        javax.swing.GroupLayout panelImage5Layout = new javax.swing.GroupLayout(panelImage5);
        panelImage5.setLayout(panelImage5Layout);
        panelImage5Layout.setHorizontalGroup(
            panelImage5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelImage5Layout.setVerticalGroup(
            panelImage5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel42)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblantum4.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lblantum4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblantum4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Temas.png"))); // NOI18N
        lblantum4.setText("Metal Office");
        lblantum4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblantum4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblantum4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblantum4MouseClicked(evt);
            }
        });

        lblantum.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lblantum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblantum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Temas.png"))); // NOI18N
        lblantum.setText("Antum Global");
        lblantum.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblantum.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblantum.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblantumMouseClicked(evt);
            }
        });

        lblprofesional.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lblprofesional.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblprofesional.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Temas.png"))); // NOI18N
        lblprofesional.setText("Professional");
        lblprofesional.setToolTipText("Para quitar este tema debe reiniciar sistema");
        lblprofesional.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblprofesional.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblprofesional.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblprofesionalMouseClicked(evt);
            }
        });

        lblprofesional1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lblprofesional1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblprofesional1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Temas.png"))); // NOI18N
        lblprofesional1.setText("Stetic Theme");
        lblprofesional1.setToolTipText("Para quitar este tema debe reiniciar sistema");
        lblprofesional1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblprofesional1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblprofesional1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblprofesional1MouseClicked(evt);
            }
        });

        lblclassic.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lblclassic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblclassic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Temas.png"))); // NOI18N
        lblclassic.setText("  Windows Classic  ");
        lblclassic.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblclassic.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblclassic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblclassicMouseClicked(evt);
            }
        });

        lblskin.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lblskin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblskin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Temas.png"))); // NOI18N
        lblskin.setText("  Mist Aqua Skin  ");
        lblskin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblskin.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblskin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblskinMouseClicked(evt);
            }
        });

        lblblue.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lblblue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblblue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Temas.png"))); // NOI18N
        lblblue.setText("  Windows Blue    ");
        lblblue.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblblue.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblblue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblblueMouseClicked(evt);
            }
        });

        lblblack.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lblblack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblblack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Temas.png"))); // NOI18N
        lblblack.setText(" Windows Black");
        lblblack.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblblack.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblblack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblblackMouseClicked(evt);
            }
        });

        lblantum2.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lblantum2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblantum2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Temas.png"))); // NOI18N
        lblantum2.setText("Raven Graphite");
        lblantum2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblantum2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblantum2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblantum2MouseClicked(evt);
            }
        });

        lblantum1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lblantum1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblantum1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Temas.png"))); // NOI18N
        lblantum1.setText("Creme Coffe");
        lblantum1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblantum1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblantum1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblantum1MouseClicked(evt);
            }
        });

        lblwin8.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lblwin8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblwin8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Temas.png"))); // NOI18N
        lblwin8.setText("Special Theme");
        lblwin8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblwin8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblwin8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblwin8MouseClicked(evt);
            }
        });

        lblwin1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lblwin1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblwin1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Temas.png"))); // NOI18N
        lblwin1.setText("Field Of Wheat");
        lblwin1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblwin1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblwin1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblwin1MouseClicked(evt);
            }
        });

        lblwin4.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lblwin4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblwin4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Temas.png"))); // NOI18N
        lblwin4.setText("Magma");
        lblwin4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblwin4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblwin4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblwin4MouseClicked(evt);
            }
        });

        lblwin9.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lblwin9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblwin9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Temas.png"))); // NOI18N
        lblwin9.setText("Beauty Cross");
        lblwin9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblwin9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblwin9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblwin9MouseClicked(evt);
            }
        });

        lblblue1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lblblue1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblblue1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Temas.png"))); // NOI18N
        lblblue1.setText("  Windows 7 Pro");
        lblblue1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblblue1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblblue1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblblue1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelTemasLayout = new javax.swing.GroupLayout(panelTemas);
        panelTemas.setLayout(panelTemasLayout);
        panelTemasLayout.setHorizontalGroup(
            panelTemasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelTemasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTemasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTemasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelTemasLayout.createSequentialGroup()
                            .addComponent(lblantum2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblprofesional1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblantum4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelTemasLayout.createSequentialGroup()
                            .addComponent(lblantum, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblantum1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblprofesional, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelTemasLayout.createSequentialGroup()
                            .addGroup(panelTemasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(panelTemasLayout.createSequentialGroup()
                                    .addComponent(lblblack, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblwin4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelTemasLayout.createSequentialGroup()
                                    .addComponent(lblclassic, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblwin1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelTemasLayout.createSequentialGroup()
                                    .addComponent(lblblue, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblwin8, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelTemasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblwin9, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblskin, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblblue1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 1407, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTemasLayout.setVerticalGroup(
            panelTemasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTemasLayout.createSequentialGroup()
                .addGroup(panelTemasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelTemasLayout.createSequentialGroup()
                        .addComponent(panelImage5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelTemasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblantum)
                            .addComponent(lblantum1)
                            .addComponent(lblprofesional))
                        .addGap(18, 18, 18)
                        .addGroup(panelTemasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblantum2)
                            .addComponent(lblprofesional1)
                            .addComponent(lblantum4))
                        .addGap(18, 18, 18)
                        .addGroup(panelTemasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblskin)
                            .addComponent(lblclassic)
                            .addComponent(lblwin1))
                        .addGap(18, 18, 18)
                        .addGroup(panelTemasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblwin4)
                            .addComponent(lblblack)
                            .addComponent(lblblue1))
                        .addGap(18, 18, 18)
                        .addGroup(panelTemasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblwin8, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblwin9)))
                    .addComponent(lblblue))
                .addGap(208, 208, 208)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tbbPanelesConfiguraciones.addTab("Apariencia", panelTemas);

        panelOtros.setOpaque(false);

        chInventarios.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chInventarios.setText("Ajustes de inventarios");
        chInventarios.setOpaque(false);
        chInventarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chInventariosActionPerformed(evt);
            }
        });

        jLabel164.setText("Permite que el usuario ingresado pueda realizar ajustes del");

        jLabel165.setText("inventario de sus productos");

        jLabel167.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Inventory.png"))); // NOI18N

        chPDF.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chPDF.setText("Exportar archivos a PDF");
        chPDF.setOpaque(false);

        jLabel169.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/PDF.png"))); // NOI18N

        jLabel170.setText("Le permitirá exportar directamente los archivos en PDF");

        jLabel171.setText("de todos sus reportes");

        chReportes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chReportes.setText("Estadísticas y gráficos");
        chReportes.setOpaque(false);

        jLabel45.setText("Crea gráficos estadísticos de ventas, compras, detalles, usuarios");

        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Graficas.png"))); // NOI18N

        jLabel173.setText("ingresos, egresos, etc");

        jPanel29.setBackground(new java.awt.Color(255, 255, 204));
        jPanel29.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 204), null));

        jLabel174.setText("Las opciones marcadas vienen por defecto. Ud se responsabiliza de los cambios que llegue");

        jLabel175.setText("a tener su sistema. Caso contrario reinicie el sistema Mafakafer");

        jLabel176.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Info.png"))); // NOI18N

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel176)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel174, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel175, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(777, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel176, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel174)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel175, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel85.setText("Cargar productos automáticamente, estos productos se");

        jLabel178.setText("agregarán al sistema");

        chCargarPro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chCargarPro.setText("Cargar productos internos");
        chCargarPro.setOpaque(false);
        chCargarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chCargarProActionPerformed(evt);
            }
        });

        jLabel179.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/ProductCarga.png"))); // NOI18N

        progLoginP.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                progLoginPStateChanged(evt);
            }
        });

        panelImage7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/BannerProfAzul.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CONFIGURACIÓN STANDARD");

        javax.swing.GroupLayout panelImage7Layout = new javax.swing.GroupLayout(panelImage7);
        panelImage7.setLayout(panelImage7Layout);
        panelImage7Layout.setHorizontalGroup(
            panelImage7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelImage7Layout.setVerticalGroup(
            panelImage7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblcarga.setAnchoProgress(4);

        javax.swing.GroupLayout panelOtrosLayout = new javax.swing.GroupLayout(panelOtros);
        panelOtros.setLayout(panelOtrosLayout);
        panelOtrosLayout.setHorizontalGroup(
            panelOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelOtrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOtrosLayout.createSequentialGroup()
                        .addGroup(panelOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(chReportes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelOtrosLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(panelOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelOtrosLayout.createSequentialGroup()
                                        .addComponent(jLabel47)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(panelOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel173, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(panelOtrosLayout.createSequentialGroup()
                                        .addComponent(jLabel169)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(panelOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel170, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel171, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 993, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelOtrosLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel167)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel165, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel164, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelOtrosLayout.createSequentialGroup()
                                .addGroup(panelOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chPDF)
                                    .addComponent(chInventarios, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 914, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addComponent(chCargarPro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelOtrosLayout.createSequentialGroup()
                        .addGroup(panelOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(progLoginP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelOtrosLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel179)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel178, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblcarga, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelOtrosLayout.setVerticalGroup(
            panelOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOtrosLayout.createSequentialGroup()
                .addComponent(panelImage7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(chInventarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel167)
                    .addGroup(panelOtrosLayout.createSequentialGroup()
                        .addComponent(jLabel164)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel165)))
                .addGap(24, 24, 24)
                .addComponent(chPDF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel169)
                    .addGroup(panelOtrosLayout.createSequentialGroup()
                        .addComponent(jLabel170)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel171)))
                .addGap(25, 25, 25)
                .addComponent(chReportes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelOtrosLayout.createSequentialGroup()
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel173))
                    .addComponent(jLabel47))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chCargarPro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelOtrosLayout.createSequentialGroup()
                        .addComponent(jLabel85)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel178))
                    .addComponent(jLabel179)
                    .addComponent(lblcarga, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(progLoginP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tbbPanelesConfiguraciones.addTab("Otras opciones", panelOtros);

        panelContactos.setOpaque(false);

        jLabel188.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel188.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Facebook.png"))); // NOI18N
        jLabel188.setText("Página en Facebook");
        jLabel188.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel188.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel189.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel189.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Email.png"))); // NOI18N
        jLabel189.setText("Correo electrónico");
        jLabel189.setToolTipText("");
        jLabel189.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel189.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jPanel32.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel32.setOpaque(false);

        txtmensajes.setColumns(20);
        txtmensajes.setRows(5);
        jScrollPane6.setViewportView(txtmensajes);

        txtcorreos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel191.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel191.setText("Destinatario:");

        jLabel192.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel192.setText("Asunto:");

        jLabel193.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel193.setText("Adjunto:");

        jLabel194.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel194.setText("Mensaje:");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Ok.png"))); // NOI18N
        jButton3.setToolTipText("Enviar mensaje...");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel196.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel196.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Contactos.png"))); // NOI18N

        jButton22.setText("...");
        jButton22.setToolTipText("Buscar archivos");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        panelImage6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/BannerProfAzul.png"))); // NOI18N

        jLabel195.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel195.setForeground(new java.awt.Color(255, 255, 255));
        jLabel195.setText("FORMULARIO DE CONTACTO");

        javax.swing.GroupLayout panelImage6Layout = new javax.swing.GroupLayout(panelImage6);
        panelImage6.setLayout(panelImage6Layout);
        panelImage6Layout.setHorizontalGroup(
            panelImage6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel195, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelImage6Layout.setVerticalGroup(
            panelImage6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel195)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel192)
                    .addComponent(jLabel191)
                    .addComponent(jLabel193)
                    .addComponent(jLabel194))
                .addGap(18, 18, 18)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel196, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel32Layout.createSequentialGroup()
                            .addComponent(txtrutapdfs, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel32Layout.createSequentialGroup()
                            .addComponent(txtadjuntos, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(txtcorreos, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtasuntos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(618, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addComponent(panelImage6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel196, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcorreos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel191))
                .addGap(7, 7, 7)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel192, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtasuntos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtadjuntos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel193)
                    .addComponent(jButton22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel194))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(txtrutapdfs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(108, Short.MAX_VALUE))
        );

        jLabel197.setForeground(new java.awt.Color(102, 102, 102));
        jLabel197.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel197.setText("/jose8417");

        jLabel198.setForeground(new java.awt.Color(102, 102, 102));
        jLabel198.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel198.setText("ojeda8417@gmail.com");

        javax.swing.GroupLayout panelContactosLayout = new javax.swing.GroupLayout(panelContactos);
        panelContactos.setLayout(panelContactosLayout);
        panelContactosLayout.setHorizontalGroup(
            panelContactosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContactosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelContactosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel197, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel198, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                    .addComponent(jLabel189, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel188, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelContactosLayout.setVerticalGroup(
            panelContactosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContactosLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel188)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel197)
                .addGap(18, 18, 18)
                .addComponent(jLabel189)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel198)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelContactosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbbPanelesConfiguraciones.addTab("Contactos", panelContactos);

        javax.swing.GroupLayout panelConfigLayout = new javax.swing.GroupLayout(panelConfig);
        panelConfig.setLayout(panelConfigLayout);
        panelConfigLayout.setHorizontalGroup(
            panelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbbPanelesConfiguraciones, javax.swing.GroupLayout.PREFERRED_SIZE, 1427, Short.MAX_VALUE)
        );
        panelConfigLayout.setVerticalGroup(
            panelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbbPanelesConfiguraciones, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        tbbPanelesMenu.addTab("Configuración", panelConfig);

        panelReportes.setOpaque(false);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/ReporteVentas.png"))); // NOI18N
        jLabel13.setText("Ventas");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/ReporteCompras.png"))); // NOI18N
        jLabel16.setText("Compras");
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel16.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Cta.Cobrar.png"))); // NOI18N
        jLabel19.setText("Cta. por cobrar");
        jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel19.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Cta.Pagar.png"))); // NOI18N
        jLabel22.setText("Cta. por pagar");
        jLabel22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel22.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/VentasGraficas.png"))); // NOI18N
        jLabel48.setText("Ventas");
        jLabel48.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel48.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel48MouseClicked(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IngresosDinero.png"))); // NOI18N
        jLabel57.setText("Ingresos");
        jLabel57.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel57.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel57.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel57MouseClicked(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Gastos.png"))); // NOI18N
        jLabel58.setText("Gastos");
        jLabel58.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel58.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel58.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel58MouseClicked(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Clientes.png"))); // NOI18N
        jLabel60.setText("Clientes");
        jLabel60.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel60.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel60.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel60MouseClicked(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Proveedores.png"))); // NOI18N
        jLabel61.setText("Proveedores");
        jLabel61.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel61.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel61.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel61MouseClicked(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Productos.png"))); // NOI18N
        jLabel62.setText("Productos");
        jLabel62.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel62.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel62.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel62MouseClicked(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CerrarCaja.png"))); // NOI18N
        jLabel64.setText("Cierre de caja");
        jLabel64.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel64.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel64.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel64MouseClicked(evt);
            }
        });

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Clientes.png"))); // NOI18N
        jLabel71.setText("Clientes");
        jLabel71.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel71.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel71.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel71MouseClicked(evt);
            }
        });

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Empleados.png"))); // NOI18N
        jLabel73.setText("Empleados");
        jLabel73.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel73.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel73.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel73MouseClicked(evt);
            }
        });

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel76.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/GraficaCompras.png"))); // NOI18N
        jLabel76.setText("Ventas por fechas");
        jLabel76.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel76.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel76.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel76MouseClicked(evt);
            }
        });

        jLabel99.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel99.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/ComprasFechas.png"))); // NOI18N
        jLabel99.setText("Compas por fechas");
        jLabel99.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel99.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel99.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel99MouseClicked(evt);
            }
        });

        jLabel100.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel100.setText("Consultas Generales");

        jLabel101.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel101.setText("Consultas e informes");

        jLabel113.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel113.setText("Cuentas");

        jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel114.setText("Lista de clientes");

        jLabel115.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel115.setText("Lista de proveedores");

        jLabel116.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel116.setText("Productos en general");

        jLabel118.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel118.setText("Ingreso a caja");

        jLabel119.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel119.setText("Egreso de caja");

        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel98.setText("Informe de ventas");

        jLabel120.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel120.setText("Gráfica de ventas");

        jLabel121.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel121.setText("Informe de compras");

        jLabel122.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel122.setText("Rango de fechas");

        jLabel123.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel123.setText("Rango de fechas");

        jLabel124.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel124.setText("Saldo a proveedores");

        jLabel125.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel125.setText("Saldo de clientes");

        jLabel126.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel126.setText("Reporte de ventas");

        jLabel127.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel127.setText("Arqueo de caja");

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Mejores clientes");

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Cancelaciones");

        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel81.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Revert.png"))); // NOI18N
        jLabel81.setText("Devoluciones");
        jLabel81.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel81.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel81.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel81MouseClicked(evt);
            }
        });

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/LogoOK.png"))); // NOI18N

        jLabel157.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel157.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel157.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Bitacoras.png"))); // NOI18N
        jLabel157.setText("Bitácoras");
        jLabel157.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel157.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel157.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel157MouseClicked(evt);
            }
        });

        jLabel158.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel158.setText("Accesos");

        javax.swing.GroupLayout panelReportesLayout = new javax.swing.GroupLayout(panelReportes);
        panelReportes.setLayout(panelReportesLayout);
        panelReportesLayout.setHorizontalGroup(
            panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReportesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel101, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel113, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelReportesLayout.createSequentialGroup()
                        .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelReportesLayout.createSequentialGroup()
                                .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel114, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel115, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel116, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(jLabel118, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(jLabel119, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel157, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(jLabel158, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))
                            .addGroup(panelReportesLayout.createSequentialGroup()
                                .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelReportesLayout.createSequentialGroup()
                                        .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelReportesLayout.createSequentialGroup()
                                                .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel120, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelReportesLayout.createSequentialGroup()
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                            .addComponent(jLabel121, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                            .addComponent(jLabel122, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))
                                    .addGroup(panelReportesLayout.createSequentialGroup()
                                        .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelReportesLayout.createSequentialGroup()
                                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panelReportesLayout.createSequentialGroup()
                                                .addComponent(jLabel124, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel125, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel126, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(10, 10, 10)
                                        .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel127, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelReportesLayout.createSequentialGroup()
                                        .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel99, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                            .addComponent(jLabel123, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(0, 66, Short.MAX_VALUE)))
                .addContainerGap(600, Short.MAX_VALUE))
            .addGroup(panelReportesLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelReportesLayout.setVerticalGroup(
            panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReportesLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel100)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelReportesLayout.createSequentialGroup()
                        .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel57, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel62, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel61, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel60, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel119, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel118, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel116, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel115, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel114, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelReportesLayout.createSequentialGroup()
                        .addComponent(jLabel157, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel158)))
                .addGap(30, 30, 30)
                .addComponent(jLabel101)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel76, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel99, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel98)
                    .addComponent(jLabel120)
                    .addComponent(jLabel121)
                    .addComponent(jLabel122)
                    .addComponent(jLabel123)
                    .addComponent(jLabel43))
                .addGap(30, 30, 30)
                .addComponent(jLabel113)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelReportesLayout.createSequentialGroup()
                        .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel73, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel124)
                            .addComponent(jLabel125)
                            .addComponent(jLabel126)
                            .addComponent(jLabel18)
                            .addComponent(jLabel127)))
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbbPanelesMenu.addTab("Informes generales", panelReportes);

        jspMenus.setRightComponent(tbbPanelesMenu);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Fondo1.jpg"))); // NOI18N
        jLabel3.setComponentPopupMenu(jPopupMenu1);
        jspMenus.setLeftComponent(jLabel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbbPanelesOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, 1456, Short.MAX_VALUE)
            .addComponent(jspMenus, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tbbPanelesOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspMenus, javax.swing.GroupLayout.PREFERRED_SIZE, 682, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblabrircajaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblabrircajaMouseClicked
        GUICaja caj = new GUICaja();
        caj.show();
    }//GEN-LAST:event_lblabrircajaMouseClicked

    private void jLabel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseClicked
//        GUICierreCaja ccaj = new GUICierreCaja();
//        ccaj.show();
        GUIValidarCierre ccaj = new GUIValidarCierre();
        ccaj.show();
    }//GEN-LAST:event_jLabel25MouseClicked

    private void lblregusuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblregusuariosMouseClicked
        GUINewUsuarios nusu = new GUINewUsuarios();
        nusu.show();
    }//GEN-LAST:event_lblregusuariosMouseClicked

    private void jLabel66MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel66MouseClicked

        if (JOptionPane.showConfirmDialog(rootPane, "Esta seguro de salir?", "Salir del sistema", 1) == 0) {
            System.exit(WIDTH);
        }
    }//GEN-LAST:event_jLabel66MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        //PETICION DE CERRAR SESION ACTIVA
        if (JOptionPane.showConfirmDialog(rootPane, "Esta seguro de cerrar la sesión actual?\n"
                + "Se perderán todos los datos sin respaldar", "Cerrar sesión", 1) == 0) {
            this.dispose();

            //VISIBILIDAD DEL CIERRE ANTERIOR
            GUILogin principal = new GUILogin();
            principal.setVisible(true);
            principal.pack();
            this.setVisible(false);
        }
    }//GEN-LAST:event_jLabel6MouseClicked

    private void lblconceptosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblconceptosMouseClicked
        GUIConceptos cnp = new GUIConceptos();
        cnp.show();
    }//GEN-LAST:event_lblconceptosMouseClicked

    private void lblingresosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblingresosMouseClicked
        GUIIngresos ing = new GUIIngresos();
        ing.show();
    }//GEN-LAST:event_lblingresosMouseClicked

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        GUICorreo cor = new GUICorreo();
        cor.show();
    }//GEN-LAST:event_jLabel28MouseClicked

    private void jLabel29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseClicked
        GUIClientes cccc = new GUIClientes();
        cccc.show();
    }//GEN-LAST:event_jLabel29MouseClicked

    private void jLabel30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseClicked
        GUIProveedor pv = new GUIProveedor();
        pv.show();
    }//GEN-LAST:event_jLabel30MouseClicked

    private void jLabel65MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel65MouseClicked
        GUIGastos gts = new GUIGastos();
        gts.show();
    }//GEN-LAST:event_jLabel65MouseClicked

    private void lblmarcasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblmarcasMouseClicked
        GUIMarcas mar = new GUIMarcas();
        mar.show();
    }//GEN-LAST:event_lblmarcasMouseClicked

    private void lblcategoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblcategoriasMouseClicked
        GUICategorias cat = new GUICategorias();
        cat.show();
    }//GEN-LAST:event_lblcategoriasMouseClicked

    private void lblunidadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblunidadesMouseClicked
        GUIUnidades uni = new GUIUnidades();
        uni.show();
    }//GEN-LAST:event_lblunidadesMouseClicked

    private void lblproductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblproductosMouseClicked
        GUIProductos pro = new GUIProductos();
        pro.show();
    }//GEN-LAST:event_lblproductosMouseClicked

    private void lblpreciosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblpreciosMouseClicked
        GUIActualizarPrecios ap = new GUIActualizarPrecios();
        ap.show();
    }//GEN-LAST:event_lblpreciosMouseClicked

    private void jLabel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseClicked
        try {
            tbbPanelesMenu.setSelectedIndex(0);
        } catch (Exception ex) {
            System.out.println("No hay dinero para cobrar");
        }
    }//GEN-LAST:event_jLabel36MouseClicked

    private void jLabel74MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel74MouseClicked
        try {
            GUICVentasDetalladas vd = new GUICVentasDetalladas();
            vd.setVisible(true);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jLabel74MouseClicked

    private void jLabel37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseClicked
        try {
            tbbPanelesMenu.setSelectedIndex(1);
            txtdireccionprov.setVisible(false);
        } catch (Exception ex) {
            System.out.println("No hay datos");
        }
    }//GEN-LAST:event_jLabel37MouseClicked

    private void jLabel75MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel75MouseClicked
        try {
            GUICComprasDetalladas cd = new GUICComprasDetalladas();
            cd.setVisible(true);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jLabel75MouseClicked

    private void jLabel38MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel38MouseClicked
        GUICClientes cnc = new GUICClientes();
        cnc.setVisible(true);
    }//GEN-LAST:event_jLabel38MouseClicked

    private void jLabel39MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel39MouseClicked
        GUICProveedor cnp = new GUICProveedor();
        cnp.setVisible(true);
    }//GEN-LAST:event_jLabel39MouseClicked

    private void jLabel40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseClicked
        try {
            tbbPanelesMenu.setSelectedIndex(2);
        } catch (Exception ex) {
            System.out.println("No hay datos");
        }
    }//GEN-LAST:event_jLabel40MouseClicked

    private void jLabel41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseClicked
        GUICVisorProductos vdp = new GUICVisorProductos();
        vdp.setVisible(true);
    }//GEN-LAST:event_jLabel41MouseClicked

    private void lblinventariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblinventariosMouseClicked
        try {
            tbbPanelesMenu.setSelectedIndex(3);
        } catch (Exception ex) {
            System.out.println("No hay dinero para cobrar");
        }
    }//GEN-LAST:event_lblinventariosMouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        try {
            tbbPanelesMenu.setSelectedIndex(6);
        } catch (Exception ex) {
            System.out.println("No hay datos");
        }
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel55MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel55MouseClicked
        GUICCtaPagar ccp = new GUICCtaPagar();
        ccp.setVisible(true);
    }//GEN-LAST:event_jLabel55MouseClicked

    private void jLabel56MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel56MouseClicked
        GUICCtaCobrar ccb = new GUICCtaCobrar();
        ccb.setVisible(true);
    }//GEN-LAST:event_jLabel56MouseClicked

    private void jLabel70MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel70MouseClicked
        GUICCajaGeneralFechas cgf = new GUICCajaGeneralFechas();
        cgf.show();
    }//GEN-LAST:event_jLabel70MouseClicked

    private void lblcodbarrasproductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblcodbarrasproductoMouseClicked
        try {
            GUICCodBarras bcb = new GUICCodBarras();
            bcb.show();
        } catch (Exception ex) {
            System.out.println("Ciudado en el codigo barra = " + ex);
        }
    }//GEN-LAST:event_lblcodbarrasproductoMouseClicked

    private void lblcobarrastodosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblcobarrastodosMouseClicked
        try {
            GUICBarcodeTodos bct = new GUICBarcodeTodos();
            bct.show();
        } catch (Exception ex) {
            System.out.println("Ciudado en el codigo barra = " + ex);
        }
    }//GEN-LAST:event_lblcobarrastodosMouseClicked

    private void lblcambioclaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblcambioclaveMouseClicked
        GUINewUsuarios rc = new GUINewUsuarios();
        rc.show();
    }//GEN-LAST:event_lblcambioclaveMouseClicked

    private void lblrecuperarclaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblrecuperarclaveMouseClicked
        GUIRecuperarClave rc = new GUIRecuperarClave();
        rc.show();
    }//GEN-LAST:event_lblrecuperarclaveMouseClicked

    private void jLabel68MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel68MouseClicked
        GUIExportarSQL exp = new GUIExportarSQL();
        exp.show();
    }//GEN-LAST:event_jLabel68MouseClicked

    private void lblregistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblregistroMouseClicked
        try {
            GUIActivador at = new GUIActivador();
            at.show();
        } catch (Exception ex) {
            System.out.println("No carga " + ex);
        }
    }//GEN-LAST:event_lblregistroMouseClicked

    private void lblmasvendidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblmasvendidoMouseClicked
        try {
            conectar conect = new conectar();
            JasperReport reporte = JasperCompileManager.compileReport("src\\Reportes\\R_MasVendido.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, Conexion.conexion());
            JasperViewer view = new JasperViewer(print, false);
//            JasperExportManager.exportReportToPdfFile(print, "src/pdf/R_MasVendido.pdf");
            view.setVisible(true);
            view.setIconImage(getIconImage());
            view.setTitle("Productos con mayor actividad");
        } catch (JRException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblmasvendidoMouseClicked

    private void lblmascompradoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblmascompradoMouseClicked
        try {
            conectar conect = new conectar();
            JasperReport reporte = JasperCompileManager.compileReport("src\\Reportes\\R_MasComprado.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, Conexion.conexion());
            JasperViewer view = new JasperViewer(print, false);
//            JasperExportManager.exportReportToPdfFile(print, "src/pdf/R_MasComprados.pdf");
            view.setVisible(true);
            view.setIconImage(getIconImage());
            view.setTitle("Productos con mayor actividad");
        } catch (JRException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblmascompradoMouseClicked

    private void jLabel200MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel200MouseClicked
        try {
            GUICVentas conv = new GUICVentas();
            conv.setVisible(true);
        } catch (Exception ex) {
            System.out.println("Error al cargar");
        }
    }//GEN-LAST:event_jLabel200MouseClicked

    private void jLabel201MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel201MouseClicked
        try {
            GUICCompras conc = new GUICCompras();
            conc.setVisible(true);
        } catch (Exception ex) {
            System.out.println("Error al cargar");
        }
    }//GEN-LAST:event_jLabel201MouseClicked

    private void fondo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fondo1ActionPerformed
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Fondo1.jpg")));
    }//GEN-LAST:event_fondo1ActionPerformed

    private void fondo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fondo2ActionPerformed
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Fondo2.jpg")));
    }//GEN-LAST:event_fondo2ActionPerformed

    private void jLabel90MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel90MouseClicked
        GUIDevoluciones devol = new GUIDevoluciones();
        devol.setVisible(true);
    }//GEN-LAST:event_jLabel90MouseClicked

    private void jLabel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseClicked
        try {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("calc");
            p.waitFor();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

    }//GEN-LAST:event_jLabel32MouseClicked

    private void lblmascompradoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblmascompradoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lblmascompradoMouseEntered

    private void jLabel157MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel157MouseClicked
        try {
            conectar conect = new conectar();
            JasperReport reporte = JasperCompileManager.compileReport("src\\Reportes\\Bitacoras.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, Conexion.conexion());
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
            view.setIconImage(getIconImage());
            view.setTitle("Bitácoras");
        } catch (JRException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel157MouseClicked

    private void jLabel81MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel81MouseClicked
        try {
            conectar conect = new conectar();
            JasperReport reporte = JasperCompileManager.compileReport("src\\Reportes\\R_Devoluciones.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, Conexion.conexion());
            JasperViewer view = new JasperViewer(print, false);
            //            JasperExportManager.exportReportToPdfFile(print, "src/pdf/Devoluciones.pdf");
            view.setVisible(true);
            view.setIconImage(getIconImage());
            view.setTitle("Devoluciones");
        } catch (JRException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel81MouseClicked

    private void jLabel99MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel99MouseClicked
        GUICComprasxFechas vxf = new GUICComprasxFechas();
        vxf.show();
    }//GEN-LAST:event_jLabel99MouseClicked

    private void jLabel76MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel76MouseClicked
        GUICVentasxFechas vxf = new GUICVentasxFechas();
        vxf.show();
    }//GEN-LAST:event_jLabel76MouseClicked

    private void jLabel73MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel73MouseClicked
        try {
            conectar conect = new conectar();
            JasperReport reporte = JasperCompileManager.compileReport("src\\Reportes\\R_EmpleadoMasProductivo.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, Conexion.conexion());
            JasperViewer view = new JasperViewer(print, false);
            //JasperExportManager.exportReportToPdfFile(print, "src/pdf/Cierres de caja.pdf");
            view.setVisible(true);
            view.setIconImage(getIconImage());
            view.setTitle("Ventas del empleado");
        } catch (JRException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel73MouseClicked

    private void jLabel71MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel71MouseClicked
        try {
            conectar conect = new conectar();
            JasperReport reporte = JasperCompileManager.compileReport("src\\Reportes\\R_MejorCliente.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, Conexion.conexion());
            JasperViewer view = new JasperViewer(print, false);
            //JasperExportManager.exportReportToPdfFile(print, "src/pdf/MejoresClientes.pdf");
            view.setVisible(true);
            view.setIconImage(getIconImage());
            view.setTitle("Lista de mejores clientes");
        } catch (JRException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel71MouseClicked

    private void jLabel64MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel64MouseClicked
        try {
            conectar conect = new conectar();
            JasperReport reporte = JasperCompileManager.compileReport("src\\Reportes\\R_CajaGeneral.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, Conexion.conexion());
            JasperViewer view = new JasperViewer(print, false);
            //JasperExportManager.exportReportToPdfFile(print, "src/pdf/Cierres de caja.pdf");
            view.setVisible(true);
            view.setIconImage(getIconImage());
            view.setTitle("Caja General");
        } catch (JRException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel64MouseClicked

    private void jLabel62MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel62MouseClicked
        try {
            conectar conect = new conectar();
            JasperReport reporte = JasperCompileManager.compileReport("src\\Reportes\\R_Productos.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, Conexion.conexion());
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Lista general de productos");
            view.setVisible(true);
            view.setIconImage(getIconImage());
        } catch (JRException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel62MouseClicked

    private void jLabel61MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel61MouseClicked
        try {
            conectar conect = new conectar();
            JasperReport reporte = JasperCompileManager.compileReport("src\\Reportes\\R_RankinProveedores.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, Conexion.conexion());
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
            view.setIconImage(getIconImage());
            view.setTitle("Lista de proveedores");
        } catch (JRException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel61MouseClicked

    private void jLabel60MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel60MouseClicked
        try {
            conectar conect = new conectar();
            JasperReport reporte = JasperCompileManager.compileReport("src\\Reportes\\R_Clientes.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, Conexion.conexion());
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
            view.setIconImage(getIconImage());
            view.setTitle("Lista de clientes");
        } catch (JRException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel60MouseClicked

    private void jLabel58MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel58MouseClicked
        try {
            conectar conect = new conectar();
            JasperReport reporte = JasperCompileManager.compileReport("src\\Reportes\\R_Gastos.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, Conexion.conexion());
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Informe de gastos generados");
            view.setVisible(true);
            view.setIconImage(getIconImage());
        } catch (JRException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel58MouseClicked

    private void jLabel57MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel57MouseClicked
        try {
            conectar conect = new conectar();
            JasperReport reporte = JasperCompileManager.compileReport("src\\Reportes\\R_Ingresos.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, Conexion.conexion());
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
            view.setIconImage(getIconImage());
            view.setTitle("Informe de ingresos generados");
        } catch (JRException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel57MouseClicked

    private void jLabel48MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel48MouseClicked
        try {
            conectar conect = new conectar();
            JasperReport reporte = JasperCompileManager.compileReport("src\\Reportes\\R_GraficaVentas.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, Conexion.conexion());
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
            view.setIconImage(getIconImage());
            view.setTitle("Informe gráfico de ventas");
        } catch (JRException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel48MouseClicked

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        try {
            conectar conect = new conectar();
            JasperReport reporte = JasperCompileManager.compileReport("src\\Reportes\\R_CtaxPagar.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, Conexion.conexion());
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
            view.setIconImage(getIconImage());
            view.setTitle("Informe de cuentas por pagar");
        } catch (JRException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel22MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        try {
            conectar conect = new conectar();
            JasperReport reporte = JasperCompileManager.compileReport("src\\Reportes\\R_CtaxCobrar.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, Conexion.conexion());
            JasperViewer view = new JasperViewer(print, false);
            //            JasperExportManager.exportReportToPdfFile(print, "src/pdf/Listado de Clientes.pdf");
            view.setVisible(true);
            view.setIconImage(getIconImage());
            view.setTitle("Informe de cuentas por cobrar");
        } catch (JRException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        try {
            conectar conect = new conectar();
            JasperReport reporte = JasperCompileManager.compileReport("src\\Reportes\\R_ComprasGenerales.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, Conexion.conexion());
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
            view.setIconImage(getIconImage());
            view.setTitle("Ventas Generadas");
        } catch (JRException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        try {
            conectar conect = new conectar();
            JasperReport reporte = JasperCompileManager.compileReport("src\\Reportes\\R_VentasGenerales.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, Conexion.conexion());
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
            view.setIconImage(getIconImage());
            view.setTitle("Ventas Generadas");
        } catch (JRException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        JFileChooser filechoose = new JFileChooser();

        filechoose.setAcceptAllFileFilterUsed(false);
        javax.swing.filechooser.FileFilter Filtro1 = new FileNameExtensionFilter("JPG file", "jpg");
        javax.swing.filechooser.FileFilter Filtro2 = new FileNameExtensionFilter("PNG file", "png");
        filechoose.setFileFilter(Filtro1);
        filechoose.addChoosableFileFilter(Filtro2);

        filechoose.setFileFilter(filter);
        int option = filechoose.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            String nombreArchivo = filechoose.getSelectedFile().getName();
            String rutaArchivo = filechoose.getSelectedFile().toString();
            txtadjuntos.setText(nombreArchivo);
            txtrutapdfs.setText(rutaArchivo);
        }
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        enviarCorreos();
        txtcorreos.setText("");
        txtadjuntos.setText("");
        txtasuntos.setText("");
        txtmensajes.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void progLoginPStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_progLoginPStateChanged
        if (progLoginP.getValue() == 30) {
            lblcarga.setVisible(false);
            GUIMensajeCarga cti = new GUIMensajeCarga();
            cti.setVisible(true);
        }
    }//GEN-LAST:event_progLoginPStateChanged

    private void chCargarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chCargarProActionPerformed
        try {
            if (chCargarPro.isSelected() == true) {
                progLoginP.setVisible(false);
                IniciarCarga();
                lblcarga.setVisible(true);
                System.out.println("Carga habilitada");
            } else if (chCargarPro.isSelected() == false) {
                progLoginP.setVisible(false);
                lblcarga.setVisible(false);
                System.out.println("Carga deshabilitada");
            }
        } catch (Exception ex) {
            System.out.println("Panel de temas errado");
        }
    }//GEN-LAST:event_chCargarProActionPerformed

    private void chInventariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chInventariosActionPerformed
        try {
            if (chInventarios.isSelected() == true) {
                jLabel130.setVisible(true);
                jLabel131.setVisible(true);
                jLabel132.setVisible(true);
                jLabel133.setVisible(true);
                jLabel134.setVisible(true);
                txtcbarra.setVisible(true);
                lblnotacodigo.setVisible(true);
                txtdescriproducto.setVisible(true);
                txtcantactual.setVisible(true);
                jPanel18.setVisible(true);
                btAjustar.setVisible(true);
                spAjuste.setVisible(true);
                System.out.println("Panel de inventarios habilitado");
            } else if (chInventarios.isSelected() == false) {
                jLabel130.setVisible(false);
                jLabel131.setVisible(false);
                jLabel132.setVisible(false);
                jLabel133.setVisible(false);
                jLabel134.setVisible(false);
                txtcbarra.setVisible(false);
                lblnotacodigo.setVisible(false);
                txtdescriproducto.setVisible(false);
                txtcantactual.setVisible(false);
                spAjuste.setVisible(false);
                jPanel18.setVisible(false);
                btAjustar.setVisible(false);
                System.out.println("Panel de inventarios inhabilitado");
            }
        } catch (Exception ex) {
            System.out.println("Panel de inventarios errado");
        }
    }//GEN-LAST:event_chInventariosActionPerformed

    private void lblantum4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblantum4MouseClicked
        tema = "javax.swing.plaf.metal.MetalLookAndFeel";
        LookAndFeel();
    }//GEN-LAST:event_lblantum4MouseClicked

    private void lblantum2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblantum2MouseClicked
        tema = "org.jvnet.substance.skin.RavenGraphiteSkin";
        LookAndFeel2();
    }//GEN-LAST:event_lblantum2MouseClicked

    private void lblantum1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblantum1MouseClicked
        tema = "org.jvnet.substance.skin.CremeCoffeeSkin";
        LookAndFeel2();
    }//GEN-LAST:event_lblantum1MouseClicked

    private void lblprofesional1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblprofesional1MouseClicked
        tema = "com.lipstikLF.LipstikLookAndFeel";
        LookAndFeel();
    }//GEN-LAST:event_lblprofesional1MouseClicked

    private void lblprofesionalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblprofesionalMouseClicked
        tema = "org.jvnet.substance.skin.CremeSkin";
        LookAndFeel2();
    }//GEN-LAST:event_lblprofesionalMouseClicked

    private void lblantumMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblantumMouseClicked
        tema = "org.jvnet.substance.skin.AutumnSkin";
        LookAndFeel2();
    }//GEN-LAST:event_lblantumMouseClicked

    private void lblskinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblskinMouseClicked
        try {
            UIManager.LookAndFeelInfo[] lista = UIManager.getInstalledLookAndFeels();
            for (int i = 0; i < lista.length; i++) {
                System.out.println(lista[i].getClassName());
            }
            GUISplash.setDefaultLookAndFeelDecorated(true);
            SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.MistAquaSkin");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar el skin " + e);
        }
    }//GEN-LAST:event_lblskinMouseClicked

    private void lblblueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblblueMouseClicked
        try {
            UIManager.LookAndFeelInfo[] lista = UIManager.getInstalledLookAndFeels();
            for (int i = 0; i < lista.length; i++) {
                System.out.println(lista[i].getClassName());
            }
            GUISplash.setDefaultLookAndFeelDecorated(true);
            SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.OfficeBlue2007Skin");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar el skin " + e);
        }
    }//GEN-LAST:event_lblblueMouseClicked

    private void lblblackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblblackMouseClicked
        tema = "org.jvnet.substance.skin.BusinessBlackSteelSkin";
        LookAndFeel2();
    }//GEN-LAST:event_lblblackMouseClicked

    private void lblclassicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblclassicMouseClicked
        tema = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
        LookAndFeel();
    }//GEN-LAST:event_lblclassicMouseClicked

    private void txtbticketKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbticketKeyPressed
        try {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                if (this.contador >= 0) {
                    //Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
                    Statement st = Conexion.conexion().createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM tabla_tickets WHERE nombre= '" + this.txtbticket.getText() + "'");
                    rs.next();
                    txtcodticket.setText(String.valueOf(rs.getString("cod")));
                    txtnomticket.setText(String.valueOf(rs.getString("nombre")));
                    txtenticket.setText(String.valueOf(rs.getString("encabezado")));
                    txtdirectiticket.setText(String.valueOf(rs.getString("direccion")));
                    txtteltick.setText(String.valueOf(rs.getString("telefono")));
                    txtructick.setText(String.valueOf(rs.getString("ruc")));
                    txtpieticket.setText(String.valueOf(rs.getString("pie")));
                    txtrutaimgtick.setText(String.valueOf(rs.getString("logo")));

                    String Ruta;
                    Ruta = txtrutaimgtick.getText();
                    ImageIcon img = new ImageIcon(Ruta);
                    lbllogoticket.setIcon(new ImageIcon(img.getImage().getScaledInstance(180, 166, Image.SCALE_DEFAULT)));
                }
            }
        } catch (SQLException ex) {
        }
    }//GEN-LAST:event_txtbticketKeyPressed

    private void txtnomticketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnomticketActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnomticketActionPerformed

    private void btETicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btETicketActionPerformed
        try {

            int eliminar = JOptionPane.showConfirmDialog(null, "Desea eliminar este ticket?\n "
                    + "Se eliminará los datos de Facturas, Cotizaciones y Pedidos!", "Información", JOptionPane.YES_NO_OPTION);
            if (eliminar == 0) {

                PreparedStatement pst = Conexion.conexion().prepareStatement("DELETE FROM tabla_tickets WHERE cod='" + txtcodticket.getText() + "'");

                GUICargandoTicket ctick = new GUICargandoTicket();
                ctick.show();
                System.out.println("Ticket eliminado correctamente...");
                pst.executeUpdate();
                txtbticket.setText("");
                txtnomticket.setText("");
                txtenticket.setText("");
                txtdirectiticket.setText("");
                txtructick.setText("");
                txtteltick.setText("");
                lbllogoticket.setText("");
                txtrutaimgtick.setText("");
                txtpieticket.setText("");
                txtbticket.requestFocus();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btETicketActionPerformed

    private void btATicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btATicketActionPerformed
        try {

            PreparedStatement pst = Conexion.conexion().prepareStatement("UPDATE tabla_tickets SET nombre='" + txtnomticket.getText()
                    + "',encabezado='" + txtenticket.getText()
                    + "',direccion='" + txtdirectiticket.getText()
                    + "',telefono='" + txtteltick.getText()
                    + "',ruc='" + txtructick.getText()
                    + "',pie='" + txtpieticket.getText()
                    + "',logo='" + txtrutaimgtick.getText() + "' WHERE cod='" + txtcodticket.getText() + "'");

            GUIMensajeTicket cticke = new GUIMensajeTicket();
            cticke.setVisible(true);
            System.out.println("Ticket actualizado correctamente");
            pst.executeUpdate();
            txtbticket.requestFocus();

        } catch (Exception e) {
            System.out.println("Ticket actualizado...");
        }
    }//GEN-LAST:event_btATicketActionPerformed

    private void btGTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGTicketActionPerformed
        String nom, enc, dir, ruc, telf, pie, fot, logo;
        String sql = "";

        nom = txtnomticket.getText();
        enc = txtenticket.getText();
        dir = txtdirectiticket.getText();
        ruc = txtructick.getText();
        telf = txtteltick.getText();
        pie = txtpieticket.getText();
        logo = txtrutaimgtick.getText();

        sql = "INSERT INTO tabla_tickets (nombre,encabezado,direccion,telefono,ruc,pie,logo) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = Conexion.conexion().prepareStatement(sql);

            pst.setString(1, nom);
            pst.setString(2, enc);
            pst.setString(3, dir);
            pst.setString(4, telf);
            pst.setString(5, ruc);
            pst.setString(6, pie);
            pst.setString(7, logo);
            //pst.setBinaryStream(5, fis);

            int n = pst.executeUpdate();
            if (n > 0) {

                GUICargandoTicket ctick = new GUICargandoTicket();
                ctick.show();
            }
            txtbticket.setText("Buscar ticket registrado");
            txtnomticket.setText("Nombre de la empresa o negocio");
            txtenticket.setText("Propietarios");
            txtdirectiticket.setText("Calles, Rutas, Avenidas");
            txtteltick.setText("Teléfono");
            txtructick.setText("RUC");
            txtpieticket.setText("****Gracias por su compra****");
            lbllogoticket.setText("No Imágen");
            txtnomticket.requestFocus();

        } catch (SQLException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btGTicketActionPerformed

    private void btNTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNTicketActionPerformed
        txtbticket.setText("");
        txtnomticket.setText("");
        txtenticket.setText("");
        txtdirectiticket.setText("");
        txtructick.setText("");
        txtteltick.setText("");
        lbllogoticket.setText("No imágen");
        txtpieticket.setText("****Gracias por su compra****");
        txtbticket.requestFocus();
    }//GEN-LAST:event_btNTicketActionPerformed

    private void btSFotoTickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSFotoTickActionPerformed

        try {
            JFileChooser Chooser = new JFileChooser();
            Chooser.setAcceptAllFileFilterUsed(false);
            javax.swing.filechooser.FileFilter Filtro1 = new FileNameExtensionFilter("JPG file", "jpg");
            javax.swing.filechooser.FileFilter Filtro2 = new FileNameExtensionFilter("PNG file", "png");
            Chooser.setFileFilter(Filtro1);
            Chooser.addChoosableFileFilter(Filtro2);
            Chooser.showOpenDialog(this);
            File Archivo = Chooser.getSelectedFile();
            String FileName = Archivo.getAbsolutePath();
            txtrutaimgtick.setText(FileName.replace("\\", "//"));
            String Ruta;
            Ruta = txtrutaimgtick.getText();
            ImageIcon img = new ImageIcon(Ruta);
            lbllogoticket.setIcon(new ImageIcon(img.getImage().getScaledInstance(180, 166, Image.SCALE_DEFAULT)));
        } catch (java.lang.Exception e) {
        }
    }//GEN-LAST:event_btSFotoTickActionPerformed

    private void btNEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNEmpActionPerformed
        txtemp.setText("");
        txtprop.setText("");
        txtdir.setText("");
        txtzon.setText("");
        txtref.setText("");
        txt1.setText("");
        txt2.setText("");
        txtruc.setText("");
        txtemail.setText("");
        txtfechareg.setText("");
        txtweb.setText("");
        txtrutaimagenemp.setText("");
        txtemp.requestFocus();
    }//GEN-LAST:event_btNEmpActionPerformed

    private void btEEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEEmpActionPerformed
        try {

            int eliminar = JOptionPane.showConfirmDialog(null, "Desea eliminar esta empresa?\n "
                    + "Se eliminará los datos de la empresa!", "Información", JOptionPane.YES_NO_OPTION);
            if (eliminar == 0) {

                PreparedStatement pst = Conexion.conexion().prepareStatement("DELETE FROM tabla_empresas WHERE codigo='" + txtcodemp.getText() + "'");

                JOptionPane.showMessageDialog(null, "Empresa eliminada con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);

                System.out.println("Empresa eliminada correctamente...");
                pst.executeUpdate();
                txtemp.setText("");
                txtprop.setText("");
                txtdir.setText("");
                txtzon.setText("");
                txtref.setText("");
                txt1.setText("");
                txt2.setText("");
                txtruc.setText("");
                txtemail.setText("");
                txtfechareg.setText("");
                txtweb.setText("");
                txtrutaimagenemp.setText("");
                txtemp.requestFocus();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btEEmpActionPerformed

    private void btSFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSFotoActionPerformed
        try {
            JFileChooser Chooser = new JFileChooser();
            Chooser.setAcceptAllFileFilterUsed(false);
            javax.swing.filechooser.FileFilter Filtro1 = new FileNameExtensionFilter("JPG file", "jpg");
            javax.swing.filechooser.FileFilter Filtro2 = new FileNameExtensionFilter("PNG file", "png");
            Chooser.setFileFilter(Filtro1);
            Chooser.addChoosableFileFilter(Filtro2);
            Chooser.showOpenDialog(this);
            File Archivo = Chooser.getSelectedFile();
            String FileName = Archivo.getAbsolutePath();
            txtrutaimagenemp.setText(FileName.replace("\\", "//"));
            String Ruta;
            Ruta = txtrutaimagenemp.getText();
            ImageIcon img = new ImageIcon(Ruta);                           //largo   ancho
            lblimagenemp.setIcon(new ImageIcon(img.getImage().getScaledInstance(258, 260, Image.SCALE_DEFAULT)));
        } catch (java.lang.Exception e) {
        }
    }//GEN-LAST:event_btSFotoActionPerformed

    private void btAEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAEmpActionPerformed
        try {
            PreparedStatement pst = Conexion.conexion().prepareStatement("UPDATE tabla_empresas SET empresa='" + txtemp.getText()
                    + "',propietarios='" + txtprop.getText()
                    + "',ruc='" + txtruc.getText()
                    + "',zonal='" + txtzon.getText()
                    + "',direccion='" + txtdir.getText()
                    + "',referencia='" + txtref.getText()
                    + "',telf1='" + txt1.getText()
                    + "',telf2='" + txt2.getText()
                    + "',email='" + txtemail.getText()
                    + "',web='" + txtweb.getText()
                    + "',fecha='" + txtfechareg.getText()
                    + "',foto='" + txtrutaimagenemp.getText() + "' WHERE codigo='" + txtcodemp.getText() + "'");

            GUIMensajeEmpresa msme = new GUIMensajeEmpresa();
            msme.setVisible(true);

            pst.executeUpdate();
            txtcodemp.setText("");
            txtemp.setText("");
            txtprop.setText("");
            txtruc.setText("");
            txtzon.setText("");
            txtdir.setText("");
            txtref.setText("");
            txt1.setText("");
            txt2.setText("");
            txtemail.setText("");
            txtweb.setText("");
            txtfechareg.setText("");
            txtrutaimagenemp.setText("");

        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }//GEN-LAST:event_btAEmpActionPerformed

    private void btGEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGEmpActionPerformed
        if (this.txtemp.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Faltan algunos campos", "Correjr", JOptionPane.WARNING_MESSAGE);
            this.txtemp.requestFocus();
            return;
        }
        if (this.txtprop.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Faltan algunos campos", "Correjr", JOptionPane.WARNING_MESSAGE);
            this.txtprop.requestFocus();
            return;
        }
        if (this.txtdir.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Faltan algunos campos", "Correjr", JOptionPane.WARNING_MESSAGE);
            this.txtdir.requestFocus();
            return;
        }
        if (this.txt1.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Faltan algunos campos", "Correjr", JOptionPane.WARNING_MESSAGE);
            this.txt1.requestFocus();
            return;
        }
        if (this.txtruc.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Faltan algunos campos", "Correjr", JOptionPane.WARNING_MESSAGE);
            this.txtruc.requestFocus();
            return;
        }

        String emp, prop, dir, zon, ref, tel1, tel2, email, web, ruc, fot, fecc;
        String sql = "";

        emp = txtemp.getText();
        prop = txtprop.getText();
        dir = txtdir.getText();
        zon = txtzon.getText();
        ref = txtref.getText();
        tel1 = txt1.getText();
        tel2 = txt2.getText();
        email = txtemail.getText();
        web = txtweb.getText();
        ruc = txtruc.getText();
        fecc = txtfechareg.getText();
        fot = txtrutaimagenemp.getText();

        sql = "INSERT INTO tabla_empresas (empresa,propietarios,ruc,zonal,direccion,"
                + "referencia,telf1, telf2, email,web,fecha,foto) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = Conexion.conexion().prepareStatement(sql);
            pst.setString(1, emp);
            pst.setString(2, prop);
            pst.setString(3, ruc);
            pst.setString(4, zon);
            pst.setString(5, dir);
            pst.setString(6, ref);
            pst.setString(7, tel1);
            pst.setString(8, tel2);
            pst.setString(9, email);
            pst.setString(10, web);
            pst.setString(11, fecc);
            pst.setString(12, fot);

            int n = pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Se ha registrado satisfactoriamente su empresa " + emp + ". \n"
                        + "Mantenga la empresa en desarrollo.");
                txtemp.setText("");
                txtprop.setText("");
                txtdir.setText("");
                txtzon.setText("");
                txtref.setText("");
                txt1.setText("");
                txt2.setText("");
                txtruc.setText("");
                txtemail.setText("");
                txtfechareg.setText("");
                txtweb.setText("");
                txtrutaimagenemp.setText("");
                txtemp.requestFocus();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btGEmpActionPerformed

    private void txtempKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtempKeyPressed
        try {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                if (this.contador >= 0) {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
                    Statement st = Conexion.conexion().createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM tabla_empresas WHERE empresa= '" + this.txtemp.getText() + "'");
                    rs.next();
                    txtcodemp.setText(String.valueOf(rs.getString("codigo")));
                    txtemp.setText(String.valueOf(rs.getString("empresa")));
                    txtprop.setText(String.valueOf(rs.getString("propietarios")));
                    txtruc.setText(String.valueOf(rs.getString("ruc")));
                    txtzon.setText(String.valueOf(rs.getString("zonal")));
                    txtdir.setText(String.valueOf(rs.getString("direccion")));
                    txtref.setText(String.valueOf(rs.getString("referencia")));
                    txt1.setText(String.valueOf(rs.getString("telf1")));
                    txt2.setText(String.valueOf(rs.getString("telf2")));
                    txtemail.setText(String.valueOf(rs.getString("email")));
                    txtweb.setText(String.valueOf(rs.getString("web")));
                    txtfechareg.setText(String.valueOf(rs.getString("fecha")));
                    txtrutaimagenemp.setText(String.valueOf(rs.getString("foto")));

                    String Ruta;
                    Ruta = txtrutaimagenemp.getText();
                    ImageIcon img = new ImageIcon(Ruta);
                    lblimagenemp.setIcon(new ImageIcon(img.getImage().getScaledInstance(258, 260, Image.SCALE_DEFAULT)));

                }
            }
        } catch (SQLException ex) {
        }
    }//GEN-LAST:event_txtempKeyPressed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        try {
            conectar conect = new conectar();
            URL jasperUrl = this.getClass().getResource("/Reportes/R.ComprasHoy.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObject(jasperUrl);
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, new JMySQL().getConnection());
            //            JasperExportManager.exportReportToPdfFile(jasperPrint, "src/pdf/Comprashoy.pdf");
            JRViewer jRViewer = new JRViewer(jasperPrint);
            panelComprasHoy.removeAll();
            panelComprasHoy.setLayout(new BorderLayout());
            panelComprasHoy.add(jRViewer, BorderLayout.CENTER);
            jRViewer.setVisible(true);
            panelComprasHoy.repaint();
            panelComprasHoy.revalidate();
        } catch (JRException ex) {
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        try {
            conectar conect = new conectar();
            URL jasperUrl = this.getClass().getResource("/Reportes/VentasHoy.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObject(jasperUrl);
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, new JMySQL().getConnection());
            JRViewer jRViewer = new JRViewer(jasperPrint);
            panelVentasHoy.removeAll();
            panelVentasHoy.setLayout(new BorderLayout());
            panelVentasHoy.add(jRViewer, BorderLayout.CENTER);
            jRViewer.setVisible(true);
            panelVentasHoy.repaint();
            panelVentasHoy.revalidate();
        } catch (JRException ex) {
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        try {
            URL jasperUrl = this.getClass().getResource("/Reportes/R_InformeInventarios1.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObject(jasperUrl);
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("producto", cbInformeProductos.getSelectedItem().toString());
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, new JMySQL().getConnection());
            //            JasperExportManager.exportReportToPdfFile(jasperPrint, "src/pdf/Inventarioindividual.pdf");
            JRViewer jRViewer = new JRViewer(jasperPrint);
            panelInformeInventarios.removeAll();
            panelInformeInventarios.setLayout(new BorderLayout());
            panelInformeInventarios.add(jRViewer, BorderLayout.CENTER);
            jRViewer.setVisible(true);
            panelInformeInventarios.repaint();
            panelInformeInventarios.revalidate();
        } catch (JRException ex) {
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_jButton25ActionPerformed

    private void cbInformeProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbInformeProductosActionPerformed

    }//GEN-LAST:event_cbInformeProductosActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        try {
            URL jasperUrl = this.getClass().getResource("/Reportes/R_InformeInventarios.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObject(jasperUrl);
            Map parametros = new HashMap();
            parametros.clear();
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, new JMySQL().getConnection());
            //            JasperExportManager.exportReportToPdfFile(jasperPrint, "src/pdf/Inventariogeneral.pdf");
            JRViewer jRViewer = new JRViewer(jasperPrint);
            panelInformeInventarios.removeAll();
            panelInformeInventarios.setLayout(new BorderLayout());
            panelInformeInventarios.add(jRViewer, BorderLayout.CENTER);
            jRViewer.setVisible(true);
            panelInformeInventarios.repaint();
            panelInformeInventarios.revalidate();
        } catch (JRException ex) {
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        try {
            //archivo jasper
            //            URL  jasperUrl = this.getClass().getResource("/system_ferreteria/R_Clientes.jasper");
            URL jasperUrl = this.getClass().getResource("/Reportes/R_VentasXEmpleados.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObject(jasperUrl);
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("nombres", cbEmpledosInforme.getSelectedItem().toString());
            parametros.put("fecha", jdateFecha.getDate());
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, new JMySQL().getConnection());
            //            JasperExportManager.exportReportToPdfFile(jasperPrint, "src/PDF/Ventas por fechas.pdf");
            JRViewer jRViewer = new JRViewer(jasperPrint);
            panelInformeEmpleados.removeAll();
            panelInformeEmpleados.setLayout(new BorderLayout());
            panelInformeEmpleados.add(jRViewer, BorderLayout.CENTER);
            jRViewer.setVisible(true);
            panelInformeEmpleados.repaint();
            panelInformeEmpleados.revalidate();
        } catch (JRException ex) {
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        try {

            URL jasperUrl = this.getClass().getResource("/Reportes/R_InformeVentasEmpleados.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObject(jasperUrl);
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("empleado", cbEmpledosInforme.getSelectedItem().toString());
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, new JMySQL().getConnection());
            //            JasperExportManager.exportReportToPdfFile(jasperPrint, "src/PDF/Ventas por fechas.pdf");
            JRViewer jRViewer = new JRViewer(jasperPrint);
            panelInformeEmpleados.removeAll();
            panelInformeEmpleados.setLayout(new BorderLayout());
            panelInformeEmpleados.add(jRViewer, BorderLayout.CENTER);
            jRViewer.setVisible(true);
            panelInformeEmpleados.repaint();
            panelInformeEmpleados.revalidate();
        } catch (JRException ex) {
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        try {
            URL jasperUrl = this.getClass().getResource("/Reportes/R_ComprasxFechas.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObject(jasperUrl);
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("fechac1", jdFechaC1.getDate());
            parametros.put("fechac2", jdFechaC2.getDate());
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, new JMySQL().getConnection());
            //            JasperExportManager.exportReportToPdfFile(jasperPrint, "src/PDF/Ventas por fechas.pdf");
            JRViewer jRViewer = new JRViewer(jasperPrint);
            panelInformeCompras.removeAll();
            panelInformeCompras.setLayout(new BorderLayout());
            panelInformeCompras.add(jRViewer, BorderLayout.CENTER);
            jRViewer.setVisible(true);
            panelInformeCompras.repaint();
            panelInformeCompras.revalidate();
        } catch (JRException ex) {
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        try {

            URL jasperUrl = this.getClass().getResource("/Reportes/R_ComprasxFechas1.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObject(jasperUrl);
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("fecha1", jdFechaC1.getDate());
            parametros.put("fecha2", jdFechaC2.getDate());
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, new JMySQL().getConnection());
            //            JasperExportManager.exportReportToPdfFile(jasperPrint, "src/PDF/Ventas por fechas.pdf");
            JRViewer jRViewer = new JRViewer(jasperPrint);
            panelInformeCompras.removeAll();
            panelInformeCompras.setLayout(new BorderLayout());
            panelInformeCompras.add(jRViewer, BorderLayout.CENTER);
            jRViewer.setVisible(true);
            panelInformeCompras.repaint();
            panelInformeCompras.revalidate();
        } catch (JRException ex) {
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        try {
            URL jasperUrl = this.getClass().getResource("/Reportes/R_RegistrosVentas.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObject(jasperUrl);
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("fech1", jdFecha1.getDate());
            parametros.put("fech2", jdFecha2.getDate());
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, new JMySQL().getConnection());
            //            JasperExportManager.exportReportToPdfFile(jasperPrint, "src/PDF/Ventasfechas.pdf");
            JRViewer jRViewer = new JRViewer(jasperPrint);
            paneInformeVentas.removeAll();
            paneInformeVentas.setLayout(new BorderLayout());
            paneInformeVentas.add(jRViewer, BorderLayout.CENTER);
            jRViewer.setVisible(true);
            paneInformeVentas.repaint();
            paneInformeVentas.revalidate();
        } catch (JRException ex) {
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        try {
            //archivo jasper
            //            URL  jasperUrl = this.getClass().getResource("/system_ferreteria/R_Clientes.jasper");
            URL jasperUrl = this.getClass().getResource("/Reportes/R_VentasxFechas_1.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObject(jasperUrl);
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("fecha1", jdFecha1.getDate());
            parametros.put("fecha2", jdFecha2.getDate());
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, new JMySQL().getConnection());
            //            JasperExportManager.exportReportToPdfFile(jasperPrint, "src/PDF/Ventas por fechas.pdf");
            JRViewer jRViewer = new JRViewer(jasperPrint);
            paneInformeVentas.removeAll();
            paneInformeVentas.setLayout(new BorderLayout());
            paneInformeVentas.add(jRViewer, BorderLayout.CENTER);
            jRViewer.setVisible(true);
            paneInformeVentas.repaint();
            paneInformeVentas.revalidate();
        } catch (JRException ex) {
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        try {
            conectar conect = new conectar();
            JasperReport reporte = JasperCompileManager.compileReport("src\\Reportes\\R_KardexFisico.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, Conexion.conexion());
            JasperViewer view = new JasperViewer(print, false);
            //            JasperExportManager.exportReportToPdfFile(print, "src/pdf/KardexFisico.pdf");
            view.setVisible(true);
            view.setTitle("Kardex Físico Global");
        } catch (JRException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton34ActionPerformed

    private void cbMotivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMotivosActionPerformed
        String motivo = cbMotivos.getSelectedItem().toString();

        {
            DefaultTableModel modelCat = new DefaultTableModel();
            String[] Titulos = {"NUMERO", "COD", "PRODUCTO", "MOTIVO", "DOC", "PROVEEDOR/CLIENTE",
                "P.C.V", "ENTRADA", "SALIDA", "TOTAL", "FECHA"};
            modelCat.setColumnIdentifiers(Titulos);
            this.tablakardex.setModel(modelCat);
            try {

                String ConsultaSQL = "SELECT * FROM tabla_kardex WHERE motivo='" + motivo + "'";

                String[] Datos = new String[11];

                Statement st = Conexion.conexion().createStatement();
                ResultSet rs = st.executeQuery(ConsultaSQL);
                while (rs.next()) {
                    Datos[0] = rs.getString("num_fact");
                    Datos[1] = rs.getString("cod_prod");
                    Datos[2] = rs.getString("producto");
                    Datos[3] = rs.getString("motivo");
                    Datos[4] = rs.getString("documento");
                    Datos[5] = rs.getString("proveedor");
                    Datos[6] = rs.getString("pre_unit");
                    Datos[7] = rs.getString("entrada");
                    Datos[8] = rs.getString("salida");
                    Datos[9] = rs.getString("total");
                    Datos[10] = rs.getString("fecha");
                    modelCat.addRow(Datos);
                }
                tablakardex.setModel(modelCat);
                tablakardex.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                tablakardex.setRowHeight(35);
                tablakardex.getColumnModel().getColumn(0).setCellRenderer(new CellRendererImagenes("num"));
                tablakardex.getColumnModel().getColumn(1).setCellRenderer(new CellRendererImagenes("num"));
                tablakardex.getColumnModel().getColumn(2).setCellRenderer(new CellRendererImagenes("text"));
                tablakardex.getColumnModel().getColumn(3).setCellRenderer(new CellRendererImagenes("text"));
                tablakardex.getColumnModel().getColumn(4).setCellRenderer(new CellRendererImagenes("icon"));
                tablakardex.getColumnModel().getColumn(5).setCellRenderer(new CellRendererImagenes("text center"));
                tablakardex.getColumnModel().getColumn(6).setCellRenderer(new CellRendererImagenes("num"));
                tablakardex.getColumnModel().getColumn(7).setCellRenderer(new CellRendererImagenes("num"));
                tablakardex.getColumnModel().getColumn(8).setCellRenderer(new CellRendererImagenes("num"));
                tablakardex.getColumnModel().getColumn(9).setCellRenderer(new CellRendererImagenes("num"));
                tablakardex.getColumnModel().getColumn(10).setCellRenderer(new CellRendererImagenes("text"));

                JTableHeader jtableHeader = tablakardex.getTableHeader();
                jtableHeader.setDefaultRenderer(new HeaderCellRenderer());
                tablakardex.setTableHeader(jtableHeader);
                tablakardex.getColumnModel().getColumn(0).setPreferredWidth(80);
                tablakardex.getColumnModel().getColumn(1).setPreferredWidth(105);
                tablakardex.getColumnModel().getColumn(2).setPreferredWidth(480);
                tablakardex.getColumnModel().getColumn(3).setPreferredWidth(150);
                tablakardex.getColumnModel().getColumn(4).setPreferredWidth(50);
                tablakardex.getColumnModel().getColumn(5).setPreferredWidth(270);
                tablakardex.getColumnModel().getColumn(6).setPreferredWidth(80);
                tablakardex.getColumnModel().getColumn(7).setPreferredWidth(80);
                tablakardex.getColumnModel().getColumn(8).setPreferredWidth(80);
                tablakardex.getColumnModel().getColumn(9).setPreferredWidth(80);
                tablakardex.getColumnModel().getColumn(10).setPreferredWidth(80);
                Total();
                Total2();
                Total3();
                RedondearKardexEnt();
                RedondearKardexSal();
                RedondearKardexTot();
            } catch (SQLException ex) {
                Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cbMotivosActionPerformed

    private void cbTipVentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipVentActionPerformed
        String docu = cbTipVent.getSelectedItem().toString();

        {
            DefaultTableModel modelCat = new DefaultTableModel();
            String[] Titulos = {"NUMERO", "COD", "PRODUCTO", "MOTIVO", "DOC", "PROVEEDOR/CLIENTE",
                "P.C.V", "ENTRADA", "SALIDA", "TOTAL", "FECHA"};
            modelCat.setColumnIdentifiers(Titulos);
            this.tablakardex.setModel(modelCat);
            try {

                String ConsultaSQL = "SELECT * FROM tabla_kardex WHERE documento='" + docu + "'";

                String[] Datos = new String[11];

                Statement st = Conexion.conexion().createStatement();
                ResultSet rs = st.executeQuery(ConsultaSQL);
                while (rs.next()) {
                    Datos[0] = rs.getString("num_fact");
                    Datos[1] = rs.getString("cod_prod");
                    Datos[2] = rs.getString("producto");
                    Datos[3] = rs.getString("motivo");
                    Datos[4] = rs.getString("documento");
                    Datos[5] = rs.getString("proveedor");
                    Datos[6] = rs.getString("pre_unit");
                    Datos[7] = rs.getString("entrada");
                    Datos[8] = rs.getString("salida");
                    Datos[9] = rs.getString("total");
                    Datos[10] = rs.getString("fecha");
                    modelCat.addRow(Datos);
                }
                tablakardex.setModel(modelCat);
                //                lblinforme.setText("Consta de " + tabla_conprod.getRowCount() + " productos registradas en bodega");
                tablakardex.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                tablakardex.setRowHeight(35);
                tablakardex.getColumnModel().getColumn(0).setCellRenderer(new CellRendererImagenes("num"));
                tablakardex.getColumnModel().getColumn(1).setCellRenderer(new CellRendererImagenes("num"));
                tablakardex.getColumnModel().getColumn(2).setCellRenderer(new CellRendererImagenes("text"));
                tablakardex.getColumnModel().getColumn(3).setCellRenderer(new CellRendererImagenes("text"));
                tablakardex.getColumnModel().getColumn(4).setCellRenderer(new CellRendererImagenes("icon"));
                tablakardex.getColumnModel().getColumn(5).setCellRenderer(new CellRendererImagenes("text center"));
                tablakardex.getColumnModel().getColumn(6).setCellRenderer(new CellRendererImagenes("num"));
                tablakardex.getColumnModel().getColumn(7).setCellRenderer(new CellRendererImagenes("num"));
                tablakardex.getColumnModel().getColumn(8).setCellRenderer(new CellRendererImagenes("num"));
                tablakardex.getColumnModel().getColumn(9).setCellRenderer(new CellRendererImagenes("num"));
                tablakardex.getColumnModel().getColumn(10).setCellRenderer(new CellRendererImagenes("text"));

                JTableHeader jtableHeader = tablakardex.getTableHeader();
                jtableHeader.setDefaultRenderer(new HeaderCellRenderer());
                tablakardex.setTableHeader(jtableHeader);

                tablakardex.getColumnModel().getColumn(0).setPreferredWidth(80);
                tablakardex.getColumnModel().getColumn(1).setPreferredWidth(105);
                tablakardex.getColumnModel().getColumn(2).setPreferredWidth(480);
                tablakardex.getColumnModel().getColumn(3).setPreferredWidth(150);
                tablakardex.getColumnModel().getColumn(4).setPreferredWidth(50);
                tablakardex.getColumnModel().getColumn(5).setPreferredWidth(270);
                tablakardex.getColumnModel().getColumn(6).setPreferredWidth(80);
                tablakardex.getColumnModel().getColumn(7).setPreferredWidth(80);
                tablakardex.getColumnModel().getColumn(8).setPreferredWidth(80);
                tablakardex.getColumnModel().getColumn(9).setPreferredWidth(80);
                tablakardex.getColumnModel().getColumn(10).setPreferredWidth(80);
                Total();
                Total2();
                Total3();
                RedondearKardexEnt();
                RedondearKardexSal();
                RedondearKardexTot();
            } catch (SQLException ex) {
                Logger.getLogger(GUICProductosCompras.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cbTipVentActionPerformed

    private void btnExportarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarTablaActionPerformed
        JFileChooser dialog = new JFileChooser();
        int opcion = dialog.showSaveDialog(GUIMenuPrincipal.this);

        if (opcion == JFileChooser.APPROVE_OPTION) {

            File dir = dialog.getSelectedFile();

            try {
                List<JTable> tb = new ArrayList<JTable>();
                tb.add(tablakardex);
                //-------------------
                export_excel excelExporter = new export_excel(tb, new File(dir.getAbsolutePath() + ".xls"));
                if (excelExporter.export()) {
                    JOptionPane.showMessageDialog(null, "Tabla de movimientos exportada con éxito");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        /*try {
         List<JTable> tb = new ArrayList<JTable>();
         tb.add(tablakardex);
         Excel excellExporter = new Excel(tb, new File("MOVIMIENTOS POR FECHAS.xls"));
         if (excellExporter.Export()) {
         Mensajes.informacion("Datos exportados con èxito");
         }
         llamaExcel();
         } catch (Exception e) {
         System.out.println("No se pudo");
         }
         }*/

        /*public void Excel() {
         try {
         Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "MOVIMIENTOS POR FECHAS.xls");
         } catch (IOException e) {// } catch (Exception e) {
         System.out.println("No se pudo");
         }*/
        /**
         * IMPORTAR*
         */
        /*FileChooser.showDialog(this, "Importar movimientos");
         File file = FileChooser.getSelectedFile();
         if (!file.getName().endsWith(".xls")) {
         JOptionPane.showMessageDialog(null, "Seleccione un archivo excel...", "Error", JOptionPane.ERROR_MESSAGE);
         } else {
         try {
         CrearTabla(file);
         } catch (IOException ex) {
         Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
         }
         }*/
    }//GEN-LAST:event_btnExportarTablaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String consultaFecha = "";

        Date fecha = jdFechaMov.getDate();
        SimpleDateFormat formatofecha = new SimpleDateFormat("YYYY-MM-dd");
        String fec = "" + formatofecha.format(fecha);
        consultaFecha = "SELECT * FROM tabla_kardex WHERE fecha='" + fec + "'";

        DefaultTableModel tabla = new DefaultTableModel();
        String[] titulos = {"NUMERO", "COD", "PRODUCTO", "MOTIVO", "DOC", "PROVEEDOR/CLIENTE", "P.C.V", "ENTRADA", "SALIDA", "TOTAL", "FECHA"};
        tabla.setColumnIdentifiers(titulos);
        this.tablakardex.setModel(tabla);

        String[] Datos = new String[11];
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consultaFecha);
            while (rs.next()) {
                Datos[0] = rs.getString("num_fact");
                Datos[1] = rs.getString("cod_prod");
                Datos[2] = rs.getString("producto");
                Datos[3] = rs.getString("motivo");
                Datos[4] = rs.getString("documento");
                Datos[5] = rs.getString("proveedor");
                Datos[6] = rs.getString("pre_unit");
                Datos[7] = rs.getString("entrada");
                Datos[8] = rs.getString("salida");
                Datos[9] = rs.getString("total");
                Datos[10] = rs.getString("fecha");

                tabla.addRow(Datos);
                tablakardex.setAutoResizeMode(tablakardex.AUTO_RESIZE_OFF);
                tablakardex.setRowHeight(35);
                tablakardex.getColumnModel().getColumn(0).setCellRenderer(new CellRendererImagenes("num"));
                tablakardex.getColumnModel().getColumn(1).setCellRenderer(new CellRendererImagenes("num"));
                tablakardex.getColumnModel().getColumn(2).setCellRenderer(new CellRendererImagenes("text"));
                tablakardex.getColumnModel().getColumn(3).setCellRenderer(new CellRendererImagenes("text"));
                tablakardex.getColumnModel().getColumn(4).setCellRenderer(new CellRendererImagenes("icon"));
                tablakardex.getColumnModel().getColumn(5).setCellRenderer(new CellRendererImagenes("text center"));
                tablakardex.getColumnModel().getColumn(6).setCellRenderer(new CellRendererImagenes("num"));
                tablakardex.getColumnModel().getColumn(7).setCellRenderer(new CellRendererImagenes("num"));
                tablakardex.getColumnModel().getColumn(8).setCellRenderer(new CellRendererImagenes("num"));
                tablakardex.getColumnModel().getColumn(9).setCellRenderer(new CellRendererImagenes("num"));
                tablakardex.getColumnModel().getColumn(10).setCellRenderer(new CellRendererImagenes("text"));

                JTableHeader jtableHeader = tablakardex.getTableHeader();
                jtableHeader.setDefaultRenderer(new HeaderCellRenderer());
                tablakardex.setTableHeader(jtableHeader);
                tablakardex.getColumnModel().getColumn(0).setPreferredWidth(80);
                tablakardex.getColumnModel().getColumn(1).setPreferredWidth(105);
                tablakardex.getColumnModel().getColumn(2).setPreferredWidth(480);
                tablakardex.getColumnModel().getColumn(3).setPreferredWidth(150);
                tablakardex.getColumnModel().getColumn(4).setPreferredWidth(50);
                tablakardex.getColumnModel().getColumn(5).setPreferredWidth(270);
                tablakardex.getColumnModel().getColumn(6).setPreferredWidth(80);
                tablakardex.getColumnModel().getColumn(7).setPreferredWidth(80);
                tablakardex.getColumnModel().getColumn(8).setPreferredWidth(80);
                tablakardex.getColumnModel().getColumn(9).setPreferredWidth(80);
                tablakardex.getColumnModel().getColumn(10).setPreferredWidth(80);
                Total();
                Total2();
                Total3();
                RedondearKardexEnt();
                RedondearKardexSal();
                RedondearKardexTot();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtbuspmKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuspmKeyReleased
        Kardex(txtbuspm.getText());
        Total();
        Total2();
        Total3();
        RedondearKardexEnt();
        RedondearKardexSal();
        RedondearKardexTot();
    }//GEN-LAST:event_txtbuspmKeyReleased

    private void txtbuspmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtbuspmMouseClicked
        txtbuspm.setText("");
    }//GEN-LAST:event_txtbuspmMouseClicked

    private void btAjustarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAjustarActionPerformed

        try {
            PreparedStatement pst = Conexion.conexion().prepareStatement("UPDATE tabla_productos SET stock='"
                    + spAjuste.getValue() + "' WHERE codbarras='" + txtcbarra.getText() + "'");
            DesktopNotify.showDesktopMessage(
                    "AJUSTES DE INVENTARIO",
                    "Inventario agregado con éxito!",
                    DesktopNotify.SUCCESS, 7000L);
            pst.executeUpdate();
            txtcbarra.setText("");
            txtdescriproducto.setText("");
            txtcantactual.setText("");
            spAjuste.setValue(0);
            txtcbarra.setText("");
            lblnomproductoInventario.setText("");
            txtcbarra.requestFocus();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }//GEN-LAST:event_btAjustarActionPerformed

    private void spAjusteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spAjusteStateChanged
        int stocknuevo = (Integer) spAjuste.getValue();
    }//GEN-LAST:event_spAjusteStateChanged

    private void txtcbarraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcbarraKeyPressed
        try {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                if (this.contador >= 0) {
                    //Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
                    Statement st = Conexion.conexion().createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM tabla_productos WHERE codbarras= '" + this.txtcbarra.getText() + "'");
                    rs.next();
                    txtcbarra.setText(String.valueOf(rs.getString("codbarras")));
                    txtdescriproducto.setText(String.valueOf(rs.getString("producto")));
                    txtcantactual.setText(String.valueOf(rs.getString("stock")));
                    lblnomproductoInventario.setText(String.valueOf(rs.getString("producto")));
                }
            }

        } catch (SQLException ex) {
        }
    }//GEN-LAST:event_txtcbarraKeyPressed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        JFileChooser dialog = new JFileChooser();
        int opcion = dialog.showSaveDialog(GUIMenuPrincipal.this);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            File dir = dialog.getSelectedFile();
            try {
                List<JTable> tb = new ArrayList<JTable>();
                tb.add(tabla_productos);

                export_excel excelExporter = new export_excel(tb, new File(dir.getAbsolutePath() + ".xls"));
                if (excelExporter.export()) {
                    JOptionPane.showMessageDialog(null, "Tabla de inventario exportada con éxito");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        /*try {
         List<JTable> tb = new ArrayList<JTable>();
         tb.add(tabla_stock);

         Excel excellExporter = new Excel(tb, new File("INVENTARIO DE PRODUCTOS.xls"));
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
         Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "INVENTARIO DE PRODUCTOS.xls");
         } catch (IOException e) {
         System.out.println("No se pudo");
         }*/
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        Productos("");
        tabla_stock.setDefaultRenderer(Object.class, new RenderStock());
        txtcant.setText("Consta de " + tabla_stock.getRowCount() + " productos registrados en su bodega");
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        String consultaStock = "";
        consultaStock = ("SELECT `codprod`, `codbarras`, `producto`,`marca`, `unidad`, `categoria`,`stock`, `iva`,"
                + "`descuento`, `proveedor`, `pre_compra`, `pre_venta`, `fechacaducidad` , `detalles` , `imagen` FROM `tabla_productos` WHERE stock <=10");

        String[] titulos = {"Nº", "C.BARRAS", "PRODUCTO", "MARCA", "UNIDAD", "CATEGORIA", "STOCK", "IVA",
            "DESCUENTO", "PROVEEDOR", "R.COMPRA", "P.VENTA", "F.CADC.", "DETALLES", "IMAGEN"};

        String[] Datos = new String[15];
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consultaStock);
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

                tabla_stock.setAutoResizeMode(tabla_stock.AUTO_RESIZE_OFF);
                tabla_stock.setRowHeight(20);
                tabla_stock.getColumnModel().getColumn(0).setPreferredWidth(50);
                tabla_stock.getColumnModel().getColumn(1).setPreferredWidth(105);
                tabla_stock.getColumnModel().getColumn(2).setPreferredWidth(320);
                tabla_stock.getColumnModel().getColumn(3).setPreferredWidth(100);
                tabla_stock.getColumnModel().getColumn(4).setPreferredWidth(80);
                tabla_stock.getColumnModel().getColumn(5).setPreferredWidth(200);
                tabla_stock.getColumnModel().getColumn(6).setPreferredWidth(80);
                tabla_stock.getColumnModel().getColumn(7).setPreferredWidth(45);
                tabla_stock.getColumnModel().getColumn(8).setPreferredWidth(100);
                tabla_stock.getColumnModel().getColumn(9).setPreferredWidth(240);
                tabla_stock.getColumnModel().getColumn(10).setPreferredWidth(80);
                tabla_stock.getColumnModel().getColumn(11).setPreferredWidth(80);
                tabla_stock.getColumnModel().getColumn(12).setPreferredWidth(90);
                tabla_stock.getColumnModel().getColumn(13).setPreferredWidth(360);
                tabla_stock.getColumnModel().getColumn(14).setPreferredWidth(360);
                tabla_stock.setDefaultRenderer(Object.class, new RenderStock());
                GUIAvisoStock si = new GUIAvisoStock();
                si.setVisible(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void txtbprodKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbprodKeyReleased
        Productos(txtbprod.getText());
    }//GEN-LAST:event_txtbprodKeyReleased

    private void tabla_productosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_productosMouseClicked
        try {

            int filas = tabla_productos.getSelectedRow();
            String gastos = tabla_productos.getValueAt(filas, 1).toString();
            gastos = tabla_productos.getValueAt(filas, 0).toString();

            String cod, codb, prod, c, m, u, ca, pc, iv, pv, des, fe, prov, descri, rut;

            cod = tabla_productos.getValueAt(filas, 0).toString();
            codb = tabla_productos.getValueAt(filas, 1).toString();
            prod = tabla_productos.getValueAt(filas, 2).toString();
            m = tabla_productos.getValueAt(filas, 3).toString();
            u = tabla_productos.getValueAt(filas, 4).toString();
            c = tabla_productos.getValueAt(filas, 5).toString();
            ca = tabla_productos.getValueAt(filas, 6).toString();
            iv = tabla_productos.getValueAt(filas, 7).toString();
            des = tabla_productos.getValueAt(filas, 8).toString();
            prov = tabla_productos.getValueAt(filas, 9).toString();
            pc = tabla_productos.getValueAt(filas, 10).toString();
            pv = tabla_productos.getValueAt(filas, 11).toString();
            fe = tabla_productos.getValueAt(filas, 12).toString();
            descri = tabla_productos.getValueAt(filas, 13).toString();
            rut = tabla_productos.getValueAt(filas, 14).toString();

            GUIProductosActualizar ge = new GUIProductosActualizar();
            ge.setVisible(true);
            txtco1.setText(cod);
            txtcodbarras1.setText(codb);
            txtprod1.setText(prod);
            txtprecio1.setText(pc);
            txtpvp1.setText(pv);
            txtstock1.setText(ca);
            txtiv1.setText(iv);
            txtdesc1.setText(des);
            cbCategoria1.setSelectedItem(c);
            cbMarca1.setSelectedItem(m);
            cbProveedor1.setSelectedItem(prov);
            cbUnidad1.setSelectedItem(u);
            GUIProductosActualizar.jdFecha1.setDate(java.sql.Date.valueOf(tabla_productos.getValueAt(filas, 12).toString()));
            txtrutaimgprodact.setText(rut);

            try {
                int Fila = tabla_productos.getSelectedRow();
                String Ruta;
                Ruta = txtrutaimgprodact.getText();
                String Imagen = tabla_productos.getValueAt(Fila, 14).toString();
                ImageIcon img = new ImageIcon(Imagen);
                lblimagen1.setIcon(new ImageIcon(img.getImage().getScaledInstance(180, 166, Image.SCALE_SMOOTH)));

            } catch (Exception e) {
                System.out.println("No hay imagen" + e);
            }

        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }//GEN-LAST:event_tabla_productosMouseClicked

    private void btExpotProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExpotProductosActionPerformed
        JFileChooser dialog = new JFileChooser();
        dialog.setCurrentDirectory(new File("D:\\SistemasJava2018\\Software Open KLee 2019_Pantallas_1600x900 - Abarrotes\\DocumentosExcel"));
        int opcion = dialog.showSaveDialog(GUIMenuPrincipal.this);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            File dir = dialog.getSelectedFile();
            try {
                List<JTable> tb = new ArrayList<JTable>();
                tb.add(tabla_productos);

                export_excel excelExporter = new export_excel(tb, new File(dir.getAbsolutePath() + ".xls"));
                if (excelExporter.export()) {
                    JOptionPane.showMessageDialog(null, "TABLAS EXPORTADOS CON EXITOS!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        //IMPORTAR HOJA
        /*FileChooser.showDialog(null, "Importar Hoja");
         File file = FileChooser.getSelectedFile();
         if (!file.getName().endsWith(".xls")) {

         JOptionPane.showMessageDialog(null, "Seleccione un archivo excel...", "Error", JOptionPane.ERROR_MESSAGE);
         } else {
         try {
         CrearTabla(file);
         } catch (IOException ex) {
         Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
         }
         }*/
    }//GEN-LAST:event_btExpotProductosActionPerformed

    private void btNuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevoProductoActionPerformed
        GUIProductos pro = new GUIProductos();
        pro.show();
    }//GEN-LAST:event_btNuevoProductoActionPerformed

    private void cbUnidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbUnidadesActionPerformed
        String unidades = cbUnidades.getSelectedItem().toString();

        {
            DefaultTableModel modelMar = new DefaultTableModel();
            String[] Titulos = {"Nº", "C.BARRAS", "PRODUCTO", "MARCA", "UNIDAD", "CATEGORIA", "STOCK", "IVA",
                "DESCUENTO", "PROVEEDOR", "P.COMPRA", "P.VENTA", "F.CADUC.", "DESCRIPCION", "RUTA"};
            modelMar.setColumnIdentifiers(Titulos);
            this.tabla_productos.setModel(modelMar);
            try {

                String ConsultaSQL = "SELECT * FROM tabla_productos WHERE unidad='" + unidades + "'";

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
                    modelMar.addRow(Datos);
                }
                tabla_productos.setModel(modelMar);
                tabla_productos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                tabla_productos.setRowHeight(20);
                tabla_productos.getColumnModel().getColumn(0).setPreferredWidth(50);
                tabla_productos.getColumnModel().getColumn(1).setPreferredWidth(105);
                tabla_productos.getColumnModel().getColumn(2).setPreferredWidth(320);
                tabla_productos.getColumnModel().getColumn(3).setPreferredWidth(100);
                tabla_productos.getColumnModel().getColumn(4).setPreferredWidth(80);
                tabla_productos.getColumnModel().getColumn(5).setPreferredWidth(200);
                tabla_productos.getColumnModel().getColumn(6).setPreferredWidth(80);
                tabla_productos.getColumnModel().getColumn(7).setPreferredWidth(35);
                tabla_productos.getColumnModel().getColumn(8).setPreferredWidth(100);
                tabla_productos.getColumnModel().getColumn(9).setPreferredWidth(240);
                tabla_productos.getColumnModel().getColumn(10).setPreferredWidth(80);
                tabla_productos.getColumnModel().getColumn(11).setPreferredWidth(80);
                tabla_productos.getColumnModel().getColumn(12).setPreferredWidth(90);
                tabla_productos.getColumnModel().getColumn(13).setPreferredWidth(360);
                tabla_productos.getColumnModel().getColumn(14).setPreferredWidth(360);

            } catch (SQLException ex) {
                Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cbUnidadesActionPerformed

    private void cbCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoriasActionPerformed
        String categorias = cbCategorias.getSelectedItem().toString();

        {
            DefaultTableModel modelMar = new DefaultTableModel();
            String[] Titulos = {"Nº", "C.BARRAS", "PRODUCTO", "MARCA", "UNIDAD", "CATEGORIA", "STOCK",
                "IVA", "DESCUENTO", "PROVEEDOR", "P.COMPRA", "P.VENTA", "F.CADUC.O", "DESCRIPCION", "RUTA"};
            modelMar.setColumnIdentifiers(Titulos);
            this.tabla_productos.setModel(modelMar);
            try {

                String ConsultaSQL = "SELECT * FROM tabla_productos WHERE categoria='" + categorias + "'";

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
                    modelMar.addRow(Datos);
                }
                tabla_productos.setModel(modelMar);
                tabla_productos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                tabla_productos.setRowHeight(20);
                tabla_productos.getColumnModel().getColumn(0).setPreferredWidth(50);
                tabla_productos.getColumnModel().getColumn(1).setPreferredWidth(105);
                tabla_productos.getColumnModel().getColumn(2).setPreferredWidth(320);
                tabla_productos.getColumnModel().getColumn(3).setPreferredWidth(100);
                tabla_productos.getColumnModel().getColumn(4).setPreferredWidth(80);
                tabla_productos.getColumnModel().getColumn(5).setPreferredWidth(200);
                tabla_productos.getColumnModel().getColumn(6).setPreferredWidth(80);
                tabla_productos.getColumnModel().getColumn(7).setPreferredWidth(35);
                tabla_productos.getColumnModel().getColumn(8).setPreferredWidth(100);
                tabla_productos.getColumnModel().getColumn(9).setPreferredWidth(240);
                tabla_productos.getColumnModel().getColumn(10).setPreferredWidth(80);
                tabla_productos.getColumnModel().getColumn(11).setPreferredWidth(80);
                tabla_productos.getColumnModel().getColumn(12).setPreferredWidth(90);
                tabla_productos.getColumnModel().getColumn(13).setPreferredWidth(360);
                tabla_productos.getColumnModel().getColumn(14).setPreferredWidth(360);
            } catch (SQLException ex) {
                Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cbCategoriasActionPerformed

    private void cbMarcasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMarcasActionPerformed
        String marcas = cbMarcas.getSelectedItem().toString();

        {
            DefaultTableModel modelMar = new DefaultTableModel();
            String[] Titulos = {"Nº", "C.BARRAS", "PRODUCTO", "MARCA", "UNIDAD", "CATEGORIA", "STOCK",
                "IVA", "DESCUENTO", "PROVEEDOR", "P.COMPRA", "P.VENTA", "F.CADUC.", "DESCRIPCION", "RUTA"};
            modelMar.setColumnIdentifiers(Titulos);
            this.tabla_productos.setModel(modelMar);
            try {

                String ConsultaSQL = "SELECT * FROM tabla_productos WHERE marca='" + marcas + "'";

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
                    modelMar.addRow(Datos);
                }
                tabla_productos.setModel(modelMar);
                tabla_productos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                tabla_productos.setRowHeight(20);
                tabla_productos.getColumnModel().getColumn(0).setPreferredWidth(50);
                tabla_productos.getColumnModel().getColumn(1).setPreferredWidth(105);
                tabla_productos.getColumnModel().getColumn(2).setPreferredWidth(320);
                tabla_productos.getColumnModel().getColumn(3).setPreferredWidth(100);
                tabla_productos.getColumnModel().getColumn(4).setPreferredWidth(80);
                tabla_productos.getColumnModel().getColumn(5).setPreferredWidth(200);
                tabla_productos.getColumnModel().getColumn(6).setPreferredWidth(80);
                tabla_productos.getColumnModel().getColumn(7).setPreferredWidth(35);
                tabla_productos.getColumnModel().getColumn(8).setPreferredWidth(100);
                tabla_productos.getColumnModel().getColumn(9).setPreferredWidth(240);
                tabla_productos.getColumnModel().getColumn(10).setPreferredWidth(80);
                tabla_productos.getColumnModel().getColumn(11).setPreferredWidth(80);
                tabla_productos.getColumnModel().getColumn(12).setPreferredWidth(90);
                tabla_productos.getColumnModel().getColumn(13).setPreferredWidth(360);
                tabla_productos.getColumnModel().getColumn(14).setPreferredWidth(360);

            } catch (SQLException ex) {
                Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cbMarcasActionPerformed

    private void rdTodasClasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdTodasClasesActionPerformed
        ProductosGen("");
    }//GEN-LAST:event_rdTodasClasesActionPerformed

    private void txtbuscarpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarpKeyReleased
        ProductosGen(txtbuscarp.getText());
    }//GEN-LAST:event_txtbuscarpKeyReleased

    private void txtbuscarpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtbuscarpMouseClicked
        txtbuscarp.setText("");
        rdTodasClases.setSelected(false);
    }//GEN-LAST:event_txtbuscarpMouseClicked

    private void btcomprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcomprasActionPerformed
        GUICobrodeCompras cpc = new GUICobrodeCompras();
        cpc.setVisible(true);
        String totalcomp;
        String facturacomp;
        totalcomp = txtTotalComp.getText();
        GUICobrodeCompras.lblcantidadcomp.setText(totalcomp);
    }//GEN-LAST:event_btcomprasActionPerformed

    private void txtObservacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtObservacionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtObservacionesActionPerformed

    private void cbProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProveedorActionPerformed
        try {
            if (this.contador >= 0) {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
                Statement st = Conexion.conexion().createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM tabla_proveedores WHERE nombres= '" + this.cbProveedor.getSelectedItem() + "'");
                rs.next();
                cbProveedor.setSelectedItem(String.valueOf(rs.getString("codprov")));
                txtdireccionprov.setText(String.valueOf(rs.getString("direccion")));
            }
        } catch (SQLException ex) {
            System.out.println("Ciudado en el codigo del proveedor = " + ex);
        }
    }//GEN-LAST:event_cbProveedorActionPerformed

    private void txtbarcodcompKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbarcodcompKeyTyped
        String codc = txtbarcodcomp.getText();
        llenar_compra(codc);
        CalcularComp();
        RedondearSubtotalComp();
        txtbarcodcomp.setText("");
        //        lbltotal.setText(" Productos en la venta actual =====" + tabla_ventas.getRowCount() + "=====");
    }//GEN-LAST:event_txtbarcodcompKeyTyped

    private void txtbarcodcompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbarcodcompActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbarcodcompActionPerformed

    private void tabla_comprasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_comprasKeyPressed
        CalcularComp();
        RedondearSubtotalComp();
    }//GEN-LAST:event_tabla_comprasKeyPressed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "Desea cancelar la presente compra?\n"
                + "Se perderán todos los datos ingresados en la compra", "Aviso del sistema", 1) == 0) {

            try {
                DefaultTableModel mod = (DefaultTableModel) tabla_compras.getModel();
                int fils = tabla_compras.getRowCount();
                for (int i = 0; fils > i; i++) {
                    mod.removeRow(0);
                    CalcularComp();
                    RedondearSubtotalComp();
                    lblimgcompras.setVisible(false);
                    lblimgcompras.setText("");
                    lblsubcomp.setText("0.0");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
            }
        }
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) tabla_compras.getModel();
        int fila = tabla_compras.getSelectedRow();
        if (fila >= 0) {
            modelo.removeRow(fila);
            CalcularComp();
            RedondearSubtotalComp();
        } else {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila");
            lblimgcompras.setVisible(false);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        GUICProductosCompras pc = new GUICProductosCompras();
        pc.setVisible(true);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try {
            conectar conect = new conectar();
            JasperReport reporte = JasperCompileManager.compileReport("src\\Reportes\\R.ComprasHoy.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, Conexion.conexion());
            JasperViewer view = new JasperViewer(print, false);
            view.setIconImage(getIconImage());
            view.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        GUICComprasdelDia comd = new GUICComprasdelDia();
        comd.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            tbbPanelesMenu.setSelectedIndex(5);
        } catch (Exception ex) {
            System.out.println("No hay datos");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tabla_ventasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_ventasKeyPressed
        calcularvent();
        RedondearSubtotalVent();
    }//GEN-LAST:event_tabla_ventasKeyPressed

    private void tabla_ventasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ventasMouseClicked

    }//GEN-LAST:event_tabla_ventasMouseClicked

    private void jLabel87MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel87MouseClicked
        try {
            tbbPanelesConfiguraciones.setSelectedIndex(5);
        } catch (Exception ex) {
            System.out.println("No hay datos");
        }
    }//GEN-LAST:event_jLabel87MouseClicked

    private void btCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCobrarActionPerformed
        GUICobrodeVentas cpv = new GUICobrodeVentas();
        cpv.setVisible(true);
        String total;
        String factura;
        String cli;
        total = txtTotales.getText();
        lblcantidad.setText(total);
    }//GEN-LAST:event_btCobrarActionPerformed

    private void cbTipoventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoventaActionPerformed

    }//GEN-LAST:event_cbTipoventaActionPerformed

    private void cbModoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbModoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbModoActionPerformed

    private void cbTipoPagosVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoPagosVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoPagosVentaActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "Desea cancelar la venta actual?\n"
                + "Se perderán todos los datos ingresados en la venta", "Aviso del sistema", 1) == 0) {

            try {
                DefaultTableModel modelo = (DefaultTableModel) tabla_ventas.getModel();
                int fil = tabla_ventas.getRowCount();
                for (int i = 0; fil > i; i++) {
                    modelo.removeRow(0);
                    calcularvent();
                    RedondearSubtotalVent();
                    lbltotal.setText(" Productos en la venta actual =====" + tabla_ventas.getRowCount() + "=====");
                    lblimagenventas.setVisible(false);
                    lblimagenventas.setText("");
                    txtcodbarrasvent.setText("");
                    txtprovent.setText("");
                    txtTotales.setText("0.0");
                    txtsubtotalvent.setText("0.0");
                    txtiva.setText("0.0");
                    txtcodbarrasvent.requestFocus();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No selecciono ninguna fila");
                lblimagenventas.setVisible(false);
                JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
            }
        }
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) tabla_ventas.getModel();
        int fila = tabla_ventas.getSelectedRow();
        if (fila >= 0) {
            modelo.removeRow(fila);
            calcularvent();
            RedondearSubtotalVent();
            lbltotal.setText(" Productos en la venta actual =====" + tabla_ventas.getRowCount() + "=====");
        } else {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila");
            lblimagenventas.setVisible(false);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        GUIDevoluciones del = new GUIDevoluciones();
        del.setVisible(true);
    }//GEN-LAST:event_jButton45ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        GUICProductosGranel prodg = new GUICProductosGranel();
        prodg.show();
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        GUICCProducts prod = new GUICCProducts();
        prod.show();
    }//GEN-LAST:event_jButton44ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        GUICPrecios pre = new GUICPrecios();
        pre.show();
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        try {
            conectar conect = new conectar();
            JasperReport reporte = JasperCompileManager.compileReport("src\\Reportes\\VentasHoy.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, Conexion.conexion());
            JasperViewer view = new JasperViewer(print, false);
            view.setIconImage(getIconImage());
            view.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        GUICVentasDelDia vend = new GUICVentasDelDia();
        vend.setVisible(true);
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        try {
            tbbPanelesMenu.setSelectedIndex(5);
        } catch (Exception ex) {
            System.out.println("No hay datos");
        }
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        GUICClientesVentas ccc = new GUICClientesVentas();
        ccc.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void txtproventKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtproventKeyTyped
        String nom = txtprovent.getText();
        llenar_tabla(nom);
        calcularvent();
//        RedondearSubtotalVent();
        lblimagenventas.setVisible(true);
        lbltotal.setText(" Productos en la venta actual =====" + tabla_ventas.getRowCount() + "=====");
    }//GEN-LAST:event_txtproventKeyTyped

    private void txtproventKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtproventKeyReleased

    }//GEN-LAST:event_txtproventKeyReleased

    private void txtproventKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtproventKeyPressed
        /*try {
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

         if (this.contador >= 0) {
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
         Statement st = cn.createStatement();
         ResultSet rs = st.executeQuery("SELECT * FROM tabla_productos WHERE producto= '" + this.txtprovent.getText() + "'");
         rs.next();
         txtprovent.setText(String.valueOf(rs.getString("producto")));
         txtprovent.setText("");
         txtrutaimagen.setText(String.valueOf(rs.getString("imagen")));
         txtprovent.setText("");

         try {
         GUIGranel granel = new GUIGranel();
         granel.setVisible(true);
         lblprecioreal.setText(String.valueOf(rs.getString("pre_venta")));
         lblnomproductos.setText(String.valueOf(rs.getString("producto")));
         lblcodbarras.setText(String.valueOf(rs.getString("codbarras")));
         lbldescuentopar.setText(String.valueOf(rs.getString("descuento")));
         } catch (Exception exx) {

         }

         String Ruta;
         Ruta = txtrutaimagen.getText();
         ImageIcon img = new ImageIcon(Ruta);
         lblimagenventas.setIcon(new ImageIcon(img.getImage().getScaledInstance(120, 130, Image.SCALE_DEFAULT)));
         txtrutaimagen.setVisible(false);
         lblimagenventas.setVisible(true);
         RedondearSubtotalVent();

         }
         }
         } catch (SQLException ex) {
         }*/

        try {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                if (this.contador >= 0) {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
                    Statement st = Conexion.conexion().createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM tabla_productos WHERE producto= '" + this.txtprovent.getText() + "'");
                    rs.next();
                    txtprovent.setText(String.valueOf(rs.getString("producto")));
                    txtprovent.setText("");
                    txtrutaimagen.setText(String.valueOf(rs.getString("imagen")));
                    txtprovent.setText("");
                    String Ruta;
                    Ruta = txtrutaimagen.getText();
                    ImageIcon img = new ImageIcon(Ruta);
                    lblimagenventas.setIcon(new ImageIcon(img.getImage().getScaledInstance(120, 130, Image.SCALE_DEFAULT)));
                    txtrutaimagen.setVisible(false);
                    lblimagenventas.setVisible(true);
                    RedondearSubtotalVent();
                }
            }
        } catch (SQLException ex) {
        }
    }//GEN-LAST:event_txtproventKeyPressed

    private void txtcodbarrasventKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodbarrasventKeyTyped
        String cod = txtcodbarrasvent.getText();
        if (cod.equals("GRANEL")) {
            GUIGranel gran = new GUIGranel();
            gran.setVisible(true);
        }

        llenar_venta(cod);
        calcularvent();
        RedondearSubtotalVent();
        txtcodbarrasvent.setText("");
        lblimagenventas.setVisible(true);
        lbltotal.setText(" Productos en la venta actual =====" + tabla_ventas.getRowCount() + "=====");
    }//GEN-LAST:event_txtcodbarrasventKeyTyped

    private void lblwin8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblwin8MouseClicked
        try {
            UIManager.LookAndFeelInfo[] lista = UIManager.getInstalledLookAndFeels();
            for (int i = 0; i < lista.length; i++) {
                System.out.println(lista[i].getClassName());
            }
            SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.FindingNemoSkin");//org.jvnet.substance.skin.OfficeBlue2007Skin
            SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceBrownTheme");
            SubstanceLookAndFeel.setCurrentWatermark("org.jvnet.substance.watermark.SubstanceMetalWallWatermark");//org.jvnet.substance.watermark.SubstanceLatchWatermark
            //            SubstanceLookAndFeel.setCurrentWatermark(new SubstanceImageWatermark("src\\Recursos\\FondoGris.jpg"));
            //            SubstanceLookAndFeel.setImageWatermarkOpacity(new Float(0.9));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar el skin " + e);
        }
    }//GEN-LAST:event_lblwin8MouseClicked

    private void lblwin1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblwin1MouseClicked
        try {
            UIManager.LookAndFeelInfo[] lista = UIManager.getInstalledLookAndFeels();
            for (int i = 0; i < lista.length; i++) {
                System.out.println(lista[i].getClassName());
            }
            GUISplash.setDefaultLookAndFeelDecorated(true);
            SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.FieldOfWheatSkin");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar el skin " + e);
        }
    }//GEN-LAST:event_lblwin1MouseClicked

    private void lblwin4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblwin4MouseClicked
        try {
            UIManager.LookAndFeelInfo[] lista = UIManager.getInstalledLookAndFeels();
            for (int i = 0; i < lista.length; i++) {
                System.out.println(lista[i].getClassName());
            }
            GUISplash.setDefaultLookAndFeelDecorated(true);
            SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.MagmaSkin");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar el skin " + e);
        }
    }//GEN-LAST:event_lblwin4MouseClicked

    private void lblwin9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblwin9MouseClicked
        String s = "";

        s = "org.jb2011.lnf.beautyeye.BeautyEyeLookAndFeelCross";

        try {
            javax.swing.UIManager.setLookAndFeel(s);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JRStyledText.Run.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(JRStyledText.Run.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(JRStyledText.Run.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JRStyledText.Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblwin9MouseClicked

    private void lblblue1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblblue1MouseClicked
        tema = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        LookAndFeel();
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception excep) {
        }
    }//GEN-LAST:event_lblblue1MouseClicked

    private void rdBuscarprodimgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdBuscarprodimgActionPerformed
        ProductosImg("");
        lblistaproductos.setText("Consta de " + tabla_stock.getRowCount() + " productos registrados en su bodega");
    }//GEN-LAST:event_rdBuscarprodimgActionPerformed

    private void tabla_productosimgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_productosimgMouseClicked
        try {

            int filas = tabla_productosimg.getSelectedRow();
            String prodimg = tabla_productosimg.getValueAt(filas, 1).toString();
            prodimg = tabla_productosimg.getValueAt(filas, 0).toString();

            String nomproimg, precio, rut;

            nomproimg = tabla_productosimg.getValueAt(filas, 1).toString();
            precio = tabla_productosimg.getValueAt(filas, 10).toString();
            rut = tabla_productosimg.getValueAt(filas, 13).toString();

            lblnompimg.setText(nomproimg);
            lblprepimg.setText("$ " + precio);

            try {
                int Fila = tabla_productosimg.getSelectedRow();
                String Ruta;
                Ruta = lblprodimg.getText();
                String Imagen = tabla_productosimg.getValueAt(Fila, 13).toString();
                ImageIcon img = new ImageIcon(Imagen);
                lblprodimg.setIcon(new ImageIcon(img.getImage().getScaledInstance(300, 320, Image.SCALE_SMOOTH)));

            } catch (Exception e) {
                System.out.println("No hay imagen" + e);
            }

        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }//GEN-LAST:event_tabla_productosimgMouseClicked

    private void tabla_productosimgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_productosimgKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_productosimgKeyTyped

    private void tabla_productosimgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_productosimgKeyPressed
        ProdImgList();
    }//GEN-LAST:event_tabla_productosimgKeyPressed

    private void txtbuscarpimgKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarpimgKeyReleased
        ProductosImg(txtbuscarpimg.getText());
        this.rdBuscarprodimg.setSelected(false);
        lblistaproductos.setText("Consta de " + tabla_productosimg.getRowCount() + " productos registrados en su bodega");
    }//GEN-LAST:event_txtbuscarpimgKeyReleased

    private void jLabel154MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel154MouseClicked
        GUI_CAlertas alert = new GUI_CAlertas();
        alert.setVisible(true);
    }//GEN-LAST:event_jLabel154MouseClicked

    private void eliminarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarCliActionPerformed
        txtclienteventas.setText("Consumidor final");
        txtrucclifact.setText("-");
    }//GEN-LAST:event_eliminarCliActionPerformed

    private void fondo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fondo3ActionPerformed
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Fondo3.jpg")));
    }//GEN-LAST:event_fondo3ActionPerformed

    private void fondo4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fondo4ActionPerformed
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Fondo4.jpg")));
    }//GEN-LAST:event_fondo4ActionPerformed

    private void fondo5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fondo5ActionPerformed
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Fondo5.jpg")));
    }//GEN-LAST:event_fondo5ActionPerformed

    private void fondo6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fondo6ActionPerformed
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Fondo6.jpg")));
    }//GEN-LAST:event_fondo6ActionPerformed

    private void txtbuscarpimgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtbuscarpimgMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarpimgMouseClicked

    private void txtbuscarpimgMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtbuscarpimgMousePressed
        this.rdBuscarprodimg.setSelected(false);
    }//GEN-LAST:event_txtbuscarpimgMousePressed

    private void tabla_ventasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_ventasKeyReleased
        calcularvent();
        RedondearSubtotalVent();
    }//GEN-LAST:event_tabla_ventasKeyReleased

    private void tabla_comprasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_comprasKeyReleased
        CalcularComp();
        RedondearSubtotalComp();
    }//GEN-LAST:event_tabla_comprasKeyReleased

    private void tbbPanelesMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbbPanelesMenuMouseClicked

    }//GEN-LAST:event_tbbPanelesMenuMouseClicked

    private void tbbPanelesMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbbPanelesMenuMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbbPanelesMenuMouseEntered

    private void panelInventariosManualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelInventariosManualMouseClicked

    }//GEN-LAST:event_panelInventariosManualMouseClicked

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        String cap = "";
        String sql = ("SELECT tipousuario FROM tabla_usuarios");

        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cap = rs.getString("tipousuario");
            }
            if (cap.equals("Administrador")) {
                GUIMenuPrincipal.panelInventariosManual.setVisible(true);
                GUIMenuPrincipal.txtcbarra.setEnabled(true);
                GUIMenuPrincipal.txtdescriproducto.setEnabled(true);
                GUIMenuPrincipal.txtcantactual.setEnabled(true);
                GUIMenuPrincipal.spAjuste.setEnabled(true);
                GUIMenuPrincipal.btAjustar.setEnabled(true);
                GUIMenuPrincipal.txtcbarra.requestFocus();
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    public void CrearTabla(File file) throws IOException {

        Workbook workbook = null;

        try {
            workbook = Workbook.getWorkbook(file);

            Sheet sheet = workbook.getSheet(0);
            columna.clear();

            for (int i = 0; i < sheet.getColumns(); i++) {
                Cell cell1 = sheet.getCell(i, 0);
                columna.add(cell1.getContents());
            }
            filas.clear();

            for (int j = 1; j < sheet.getRows(); j++) {

                Vector d = new Vector();

                for (int i = 0; i < sheet.getColumns(); i++) {

                    Cell cell = sheet.getCell(i, j);
                    d.add(cell.getContents());
                }
                d.add("\n");
//                filas.add(d);
                model.addRow(d);
            }

        } catch (BiffException e) {
            e.printStackTrace();
        }
    }

    public static String fecha() {
        Date fecha = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("'Hoy es' EEEEEEE dd 'de' MMMMM 'del' yyyy");//HH:mm:ss
        GregorianCalendar cal = new GregorianCalendar();
        return formateador.format(fecha);
    }

    /**
     * **********************PARA VENDER POR COD.
     * BARRAS***********************************
     */
    void llenar_venta(String cod) {
        try {
            String can;
            String desc = "";
            double desct = 0;
            double iva = 0;
            double total = 0;
            double subtotal = 0;
            double precio = 0.0;
            double cantidad = 1;
            double impParcial = 0.0;
            double descuentos = 0.0;

            String sql = " select * from tabla_productos where codbarras='" + cod + "'";
            java.sql.PreparedStatement ps = Conexion.conexion().prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery();

            DefaultTableModel model = (DefaultTableModel) tabla_ventas.getModel();

            double importe = 0;

            while (rs.next()) {

                if (!rs.getString(1).equalsIgnoreCase("")) {

//                    cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cantidad a vender por favor!"));
                    //cod         scripcion             precio      cant c.sal     desct
                    Object[] row = {rs.getString(2), rs.getString(3), rs.getDouble(12), 1, 0.0, rs.getString(9)};
                    model.addRow(row);
                    tabla_ventas.setRowHeight(30);

                    ColorColumnVentas ft = new ColorColumnVentas();
                    tabla_ventas.setDefaultRenderer(Object.class, ft);

                    txtrutaimagen.setText(String.valueOf(rs.getString("imagen")));
                    txtprovent.setText("");
                    String Ruta;
                    Ruta = txtrutaimagen.getText();
                    ImageIcon img = new ImageIcon(Ruta);

                    GUIMenuPrincipal.lblimagenventas.setIcon(new ImageIcon(img.getImage().getScaledInstance(110, 110, Image.SCALE_DEFAULT)));
                    lblimagenventas.setVisible(true);

                    lblimagenventas.setIcon(new ImageIcon(img.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT)));
                    txtrutaimagen.setVisible(false);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * ***************VENTA POR PRODUCTO****************
     */
    void llenar_tabla(String nombre) {
        try {
            String can;
            String desc = "";
            double desct = 0;
            double iva = 0;
            double total = 0;
            double subtotal = 0;
            double precio = 0.0;
            double cantidad = 0;
            double impParcial = 0.0;
            double descuentos = 0.0;

            String sql = " select * from tabla_productos where producto='" + nombre + "'";

            java.sql.PreparedStatement ps = Conexion.conexion().prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tabla_ventas.getModel();

            double importe = 0;

            while (rs.next()) {
                if (!rs.getString(1).equalsIgnoreCase("")) {
                    //cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cantidad a vender por favor!"));
                }

                //desct en la bd
                Object[] row = {rs.getString(2), rs.getString(3), rs.getDouble(12), 1, 0.0, rs.getString(9)};
                model.addRow(row);
                tabla_ventas.setRowHeight(30);

                ColorColumnVentas ft = new ColorColumnVentas();
                tabla_ventas.setDefaultRenderer(Object.class, ft);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * ***************BUSCANDO POR CODIGO DE BARRAS****************
     */
    void llenar_compra(String cod) {
        try {
            String can;
            String desc = "";
            double desct = 0;
            double iva = 0;
            double total = 0;
            double subtotal = 0;
            double precio = 0.0;
            double cantidad = 0;
            double impParcial = 0.0;
            double descuentos = 0.0;

            String sql = " select * from tabla_productos where codbarras='" + cod + "'";
            java.sql.PreparedStatement ps = Conexion.conexion().prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tabla_compras.getModel();

            double importe = 0;

            while (rs.next()) {
                if (!rs.getString(1).equalsIgnoreCase("")) {
                    cantidad = Double.parseDouble(JOptionPane.showInputDialog(null, "Introduzca la cantidad que desea comprar!", "Solicitud", JOptionPane.PLAIN_MESSAGE));
                }
                //cod          //descrip        //stock         //precio      //cantidad enviada por el descuento
                Object[] row = {rs.getString(2), rs.getString(3), rs.getInt(7), rs.getDouble(12), cantidad, 0.0, rs.getString(9)};
                model.addRow(row);
                tabla_compras.setRowHeight(30);
                txtbarcodcomp.setText("");

                ColorColumnCompras ft = new ColorColumnCompras();
                tabla_compras.setDefaultRenderer(Object.class, ft);

                txtrutacompras.setText(String.valueOf(rs.getString("imagen")));
                txtbarcodcomp.setText("");
                String Ruta;
                Ruta = txtrutacompras.getText();
                ImageIcon img = new ImageIcon(Ruta);

                GUIMenuPrincipal.lblimgcompras.setIcon(new ImageIcon(img.getImage().getScaledInstance(110, 110, Image.SCALE_DEFAULT)));
                lblimgcompras.setVisible(true);

                lblimgcompras.setIcon(new ImageIcon(img.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT)));
                txtrutacompras.setVisible(false);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void NomProd() {
        TextAutoCompleter textAutoC = new TextAutoCompleter(txtprovent);
        try {
            // Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = Conexion.conexion().createStatement();
            ResultSet rs = sent.executeQuery("SELECT producto FROM tabla_productos");
            while (rs.next()) {
                textAutoC.addItem(rs.getString("producto"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static String fechas() {
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
//            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
//            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.mac.MacLookAndFeel");
//            setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel"); 
//            setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel"); 
//            setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");          

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIMenuPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIMenuPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIMenuPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIMenuPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIMenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btAEmp;
    public static javax.swing.JButton btATicket;
    public static javax.swing.JButton btAjustar;
    private javax.swing.JButton btCobrar;
    public static javax.swing.JButton btEEmp;
    public static javax.swing.JButton btETicket;
    public static javax.swing.JButton btExpotProductos;
    public static javax.swing.JButton btGEmp;
    public static javax.swing.JButton btGTicket;
    public static javax.swing.JButton btNEmp;
    public static javax.swing.JButton btNTicket;
    public static javax.swing.JButton btNuevoProducto;
    public static javax.swing.JButton btSFoto;
    public static javax.swing.JButton btSFotoTick;
    private javax.swing.JButton btcompras;
    private javax.swing.JButton btnExportarTabla;
    public static javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox cbCategorias;
    public static javax.swing.JComboBox cbDoccomp;
    private javax.swing.JComboBox cbEmpledosInforme;
    public static javax.swing.JComboBox cbFormadepago;
    private javax.swing.JComboBox cbInformeProductos;
    private javax.swing.JComboBox cbMarcas;
    public static javax.swing.JComboBox cbModo;
    private javax.swing.JComboBox cbMotivos;
    public static javax.swing.JComboBox cbProveedor;
    private javax.swing.JComboBox cbTipVent;
    public static javax.swing.JComboBox cbTipoPagosVenta;
    public static javax.swing.JComboBox cbTipoventa;
    private javax.swing.JComboBox cbUnidades;
    public static javax.swing.JComboBox cbUsuarioCompra;
    public static javax.swing.JComboBox cbUsuarioVenta;
    public static javax.swing.JCheckBox chCargarPro;
    public static javax.swing.JCheckBox chInventarios;
    public static javax.swing.JCheckBox chPDF;
    public static javax.swing.JCheckBox chReportes;
    private javax.swing.JMenuItem eliminarCli;
    private javax.swing.JMenuItem fondo1;
    private javax.swing.JMenuItem fondo2;
    private javax.swing.JMenuItem fondo3;
    private javax.swing.JMenuItem fondo4;
    private javax.swing.JMenuItem fondo5;
    private javax.swing.JMenuItem fondo6;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel178;
    private javax.swing.JLabel jLabel179;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel180;
    private javax.swing.JLabel jLabel181;
    private javax.swing.JLabel jLabel182;
    private javax.swing.JLabel jLabel183;
    private javax.swing.JLabel jLabel187;
    private javax.swing.JLabel jLabel188;
    private javax.swing.JLabel jLabel189;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel191;
    private javax.swing.JLabel jLabel192;
    private javax.swing.JLabel jLabel193;
    private javax.swing.JLabel jLabel194;
    private javax.swing.JLabel jLabel195;
    private javax.swing.JLabel jLabel196;
    private javax.swing.JLabel jLabel197;
    private javax.swing.JLabel jLabel198;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel200;
    private javax.swing.JLabel jLabel201;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane2;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane1;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane2;
    private com.toedter.calendar.JDateChooser jdComprasHoy;
    private com.toedter.calendar.JDateChooser jdFecha1;
    private com.toedter.calendar.JDateChooser jdFecha2;
    private com.toedter.calendar.JDateChooser jdFechaC1;
    private com.toedter.calendar.JDateChooser jdFechaC2;
    private com.toedter.calendar.JDateChooser jdFechaHoy;
    private com.toedter.calendar.JDateChooser jdFechaMov;
    private com.toedter.calendar.JDateChooser jdateFecha;
    private javax.swing.JSplitPane jspMenus;
    private javax.swing.JToolBar jtAdmin;
    private javax.swing.JToolBar jtConsultas;
    private javax.swing.JToolBar jtHerramientas;
    private javax.swing.JToolBar jtInicio;
    private javax.swing.JToolBar jtProductos;
    private javax.swing.JToolBar jtTPV;
    private javax.swing.JToolBar jtUtil;
    public static javax.swing.JLabel lblabrircaja;
    private javax.swing.JLabel lblantum;
    private javax.swing.JLabel lblantum1;
    private javax.swing.JLabel lblantum2;
    private javax.swing.JLabel lblantum4;
    private javax.swing.JLabel lblblack;
    private javax.swing.JLabel lblblue;
    private javax.swing.JLabel lblblue1;
    public static javax.swing.JLabel lblcambioclave;
    private rojerusan.componentes.RSProgressMaterial lblcarga;
    public static javax.swing.JLabel lblcargo;
    public static javax.swing.JLabel lblcategorias;
    private javax.swing.JLabel lblclassic;
    public static javax.swing.JLabel lblcobarrastodos;
    public static javax.swing.JLabel lblcodbarrasproducto;
    public static javax.swing.JLabel lblconceptos;
    public static javax.swing.JLabel lbldirecc;
    private javax.swing.JLabel lblfecha;
    public static javax.swing.JLabel lblfechas;
    public static javax.swing.JLabel lblhoras;
    private javax.swing.JLabel lblimagenemp;
    public static javax.swing.JLabel lblimagenventas;
    public static javax.swing.JLabel lblimgcompras;
    public static javax.swing.JLabel lblingresos;
    public static javax.swing.JLabel lblinventarios;
    private javax.swing.JLabel lblistaproductos;
    public static javax.swing.JLabel lblivacomp;
    private javax.swing.JLabel lbllogoticket;
    public static javax.swing.JLabel lblmarcas;
    public static javax.swing.JLabel lblmascomprado;
    public static javax.swing.JLabel lblmasvendido;
    private javax.swing.JLabel lblnompimg;
    private javax.swing.JLabel lblnomproductoInventario;
    private javax.swing.JLabel lblnotacodigo;
    public static javax.swing.JLabel lblnumcomp;
    public static javax.swing.JLabel lblnumventa;
    public static javax.swing.JLabel lblprecios;
    private javax.swing.JLabel lblprepimg;
    private javax.swing.JLabel lblprodimg;
    public static javax.swing.JLabel lblproductos;
    private javax.swing.JLabel lblprofesional;
    private javax.swing.JLabel lblprofesional1;
    public static javax.swing.JLabel lblrecuperarclave;
    public static javax.swing.JLabel lblregistro;
    public static javax.swing.JLabel lblregusuarios;
    private javax.swing.JLabel lblskin;
    public static javax.swing.JLabel lblsubcomp;
    public static javax.swing.JLabel lbltotal;
    public static javax.swing.JLabel lblunidades;
    public static javax.swing.JLabel lblusuario;
    private javax.swing.JLabel lblwin1;
    private javax.swing.JLabel lblwin4;
    private javax.swing.JLabel lblwin8;
    private javax.swing.JLabel lblwin9;
    private javax.swing.JPopupMenu pEliminarCli;
    private javax.swing.JPanel paneInformeVentas;
    private javax.swing.JPanel panelBotonesAcciones;
    private javax.swing.JPanel panelCompras;
    private javax.swing.JPanel panelComprasHoy;
    private javax.swing.JPanel panelConfig;
    private javax.swing.JPanel panelContactos;
    private javax.swing.JPanel panelEmpresa;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private org.edisoncor.gui.panel.PanelImage panelImage2;
    private org.edisoncor.gui.panel.PanelImage panelImage3;
    private org.edisoncor.gui.panel.PanelImage panelImage4;
    private org.edisoncor.gui.panel.PanelImage panelImage5;
    private org.edisoncor.gui.panel.PanelImage panelImage6;
    private org.edisoncor.gui.panel.PanelImage panelImage7;
    private org.edisoncor.gui.panel.PanelImage panelImage9;
    public static javax.swing.JPanel panelImgEmpresa;
    private javax.swing.JPanel panelInformeCompras;
    private javax.swing.JPanel panelInformeEmpleados;
    private javax.swing.JPanel panelInformeInventarios;
    private javax.swing.JPanel panelInformes;
    public static javax.swing.JPanel panelInventariosManual;
    public static javax.swing.JPanel panelListaInventarios;
    private javax.swing.JPanel panelListaProductos;
    private javax.swing.JPanel panelMovimientos;
    private javax.swing.JPanel panelOpenKlee;
    private javax.swing.JPanel panelOtros;
    public static javax.swing.JPanel panelProductos;
    private javax.swing.JPanel panelReportes;
    private javax.swing.JPanel panelTemas;
    private javax.swing.JPanel panelTicket;
    private javax.swing.JPanel panelVentas;
    private javax.swing.JPanel panelVentasHoy;
    private javax.swing.JProgressBar progLoginP;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private javax.swing.JRadioButton rdBuscarprodimg;
    private javax.swing.JRadioButton rdTodasClases;
    public static javax.swing.JSpinner spAjuste;
    public static javax.swing.JTable tabla_compras;
    private rojerusan.RSTableMetro tabla_productos;
    private rojerusan.RSTableMetro tabla_productosimg;
    private rojerusan.RSTableMetro tabla_stock;
    public static javax.swing.JTable tabla_ventas;
    private rojerusan.RSTableMetro tablakardex;
    private javax.swing.JTabbedPane tbbPanelesConfiguraciones;
    private javax.swing.JTabbedPane tbbPanelesMenu;
    private javax.swing.JTabbedPane tbbPanelesOpciones;
    public static javax.swing.JTextField txt1;
    public static javax.swing.JTextField txt2;
    public static javax.swing.JTextField txtBascula;
    public static javax.swing.JTextField txtObservaciones;
    public static javax.swing.JTextField txtPesoBascula;
    public static javax.swing.JTextField txtTotalComp;
    public static javax.swing.JTextField txtTotalEntradas;
    public static javax.swing.JTextField txtTotalEntradasSalidas;
    public static javax.swing.JTextField txtTotalSalidas;
    public static javax.swing.JTextField txtTotales;
    private javax.swing.JTextField txtadjuntos;
    private javax.swing.JTextField txtasuntos;
    public static javax.swing.JTextField txtbarcodcomp;
    private javax.swing.JTextField txtbprod;
    public static rojeru_san.RSMTextFull txtbticket;
    private javax.swing.JTextField txtbuscarp;
    private rojeru_san.RSMTextFull txtbuscarpimg;
    private javax.swing.JTextField txtbuspm;
    private javax.swing.JTextField txtcant;
    public static javax.swing.JTextField txtcantactual;
    public static javax.swing.JTextField txtcbarra;
    public static javax.swing.JTextField txtclienteventas;
    public static javax.swing.JTextField txtcodbarrasvent;
    private javax.swing.JTextField txtcodemp;
    private javax.swing.JTextField txtcodticket;
    private javax.swing.JTextField txtcorreos;
    public static javax.swing.JTextField txtdescriproducto;
    public static javax.swing.JTextField txtdir;
    public static javax.swing.JTextField txtdireccionprov;
    public static rojeru_san.RSMTextFull txtdirectiticket;
    public static javax.swing.JTextField txtemail;
    public static javax.swing.JTextField txtemp;
    public static rojeru_san.RSMTextFull txtenticket;
    public static javax.swing.JTextField txtfechareg;
    public static javax.swing.JLabel txtiva;
    private javax.swing.JTextArea txtmensajes;
    public static rojeru_san.RSMTextFull txtnomticket;
    public static javax.swing.JTextArea txtpieticket;
    public static javax.swing.JTextField txtprop;
    public static javax.swing.JTextField txtprovent;
    public static javax.swing.JTextArea txtref;
    public static javax.swing.JTextField txtruc;
    public static javax.swing.JTextField txtrucclifact;
    public static rojeru_san.RSMTextFull txtructick;
    private javax.swing.JTextField txtrutacompras;
    public static javax.swing.JTextField txtrutaimagen;
    public static javax.swing.JTextField txtrutaimagenemp;
    private javax.swing.JTextField txtrutaimgtick;
    private javax.swing.JTextField txtrutapdfs;
    public static javax.swing.JLabel txtsubtotalvent;
    public static rojeru_san.RSMTextFull txtteltick;
    public static javax.swing.JTextField txtweb;
    public static javax.swing.JTextField txtzon;
    // End of variables declaration//GEN-END:variables

    public void IniciarCarga() {
        new Thread() {

            @Override
            public void run() {
                int i = 0;
                while (i <= 100) {
                    i++;
                    progLoginP.setValue(i);
                    try {
                        sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
    }

    private String productoBalanza(String codigoProducto) {
        String query = "SELECT * FROM tabla_productos WHERE codbarras=" + codigoProducto;
        Statement st;
        String unidad = "";
        try {
            st = Conexion.conexion().createStatement();
            rs = st.executeQuery(query);
            if (rs.next()) {
                System.err.println(rs.getInt("balanza"));
                if (rs.getInt("balanza") == 1) {
                    //   System.out.println("Producto con balanza");
                    unidad = rs.getString("unidad");;
                    //return 
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        return unidad;
    }
}
