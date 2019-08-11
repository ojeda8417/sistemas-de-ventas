/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIConsultas;

import Clases.ColorColumnVentas;
import Clases.Conexion;
import Clases.MiRender;
import Clases.conectar;
import GUInterfaces.GUIGranel;
import GUInterfaces.GUIMenuPrincipal;
import static GUInterfaces.GUIMenuPrincipal.lblimagenventas;
import static GUInterfaces.GUIMenuPrincipal.tabla_ventas;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Image;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author
 */
public class GUICCProducts extends javax.swing.JFrame {

    DefaultTableModel model;
    int contador;
  //  conectar cc = new conectar();
//    Connection cn = cc.conexion();

    public GUICCProducts() {
        initComponents();
        setLocationRelativeTo(null);
        NomProd();
        checkTodos.setSelected(true);
        Productos("");
        txtbuscar.requestFocus();
        tab_prod.setDefaultRenderer(Object.class, new MiRender());
     }

    void Productos(String valor) {
        String mostrar = "SELECT * FROM tabla_productos WHERE CONCAT(producto,unidad) LIKE '%" + valor + "%'";
        String[] titulos = {"C.BARRAS", "PRODUCTO", "P.VENTA", "IVA", "%", "UNIDAD", "STOCK", "IMG"};
        String[] Registros = new String[8];
        model = new DefaultTableModel(null, titulos);

        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(mostrar);
            while (rs.next()) {
                Registros[0] = rs.getString("codbarras");
                Registros[1] = rs.getString("producto");
                Registros[2] = rs.getString("pre_venta");
                Registros[3] = rs.getString("iva");
                Registros[4] = rs.getString("descuento");
                Registros[5] = rs.getString("unidad");
                Registros[6] = rs.getString("stock");
                Registros[7] = rs.getString("imagen");
                model.addRow(Registros);
                lblencontrados.setText("Productos encontrados " + tab_prod.getRowCount());
            }
            tab_prod.setModel(model);
            lblencontrados.setText("Productos encontrados " + tab_prod.getRowCount());
            tab_prod.setAutoResizeMode(tab_prod.AUTO_RESIZE_OFF);
            tab_prod.setRowHeight(20);
            tab_prod.getColumnModel().getColumn(0).setPreferredWidth(130);
            tab_prod.getColumnModel().getColumn(1).setPreferredWidth(255);
            tab_prod.getColumnModel().getColumn(2).setPreferredWidth(90);
            tab_prod.getColumnModel().getColumn(3).setPreferredWidth(60);
            tab_prod.getColumnModel().getColumn(4).setPreferredWidth(70);
            tab_prod.getColumnModel().getColumn(5).setPreferredWidth(70);
            tab_prod.getColumnModel().getColumn(6).setPreferredWidth(60);
            tab_prod.getColumnModel().getColumn(7).setPreferredWidth(300);

        } catch (SQLException ex) {
            Logger.getLogger(GUICCProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void CalcularVenta() {

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

                //DESCUENTO EN LA COLUMNA 5
                desc = GUIMenuPrincipal.tabla_ventas.getValueAt(i, 5).toString();
                descuentos = Double.parseDouble(desc);
                impParcial = precio * cantidad;
                desct = (precio * descuentos) * cantidad;
                totparcial = impParcial - desct;
            }

            subtotal = subtotal + totparcial;
            iva = subtotal * 0.12;
            total = subtotal + iva;
            //COLUMNA 6 DONDE ESTA EL TOTAL
            GUIMenuPrincipal.tabla_ventas.setValueAt(( String.format("%.0f", Math.rint(totparcial * 100) / 100)), i, 6);
        }
  
        GUIMenuPrincipal.txtsubtotalvent.setText(String.format("%.0f", Math.rint(subtotal * 100) / 100));
        GUIMenuPrincipal.txtiva.setText( String.format("%.0f", Math.rint(total * 100) / 100));
        GUIMenuPrincipal.txtTotales.setText( String.format("%.0f", Math.rint(total * 100) / 100));

    }     
                  
        

    String comparar(String cod) {
        String cant = "";
        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tabla_productos WHERE codbarras='" + cod + "'");
            while (rs.next()) {
                cant = rs.getString(7);

            }

        } catch (SQLException ex) {
            Logger.getLogger(GUICCProducts.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return cant;
    }

    public void RedondearSubtotal() {
        double valor = Double.parseDouble(GUIMenuPrincipal.txtsubtotalvent.getText());
        String val = valor + "";
        BigDecimal big = new BigDecimal(val);
        big = big.setScale(2, RoundingMode.HALF_UP);
        
        /*DecimalFormat formatea = new DecimalFormat("###.###,##");
        int valor1 = 1000;
        GUIMenuPrincipal.txtsubtotalvent.setText(formatea.format( valor1));*/
        
        //DecimalFormat formateador = new DecimalFormat("###,###.##");
        //Este daria a la salida 1,000
        //System.out.println (formateador.format (Integer.parseInt(String.format("%.0f", big))));
        
        //GUIMenuPrincipal.txtsubtotalvent.setText(formateador.format (Integer.parseInt(String.format("%.0f", big))));
        GUIMenuPrincipal.txtsubtotalvent.setText(String.format("%.0f", big));
        
    }

    public void NomProd() {
        TextAutoCompleter textAutoC = new TextAutoCompleter(txtbuscar);
        try {
            //Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = Conexion.conexion().createStatement(); //con.createStatement();
            ResultSet rs = sent.executeQuery("SELECT producto FROM tabla_productos");
            while (rs.next()) {

                textAutoC.addItem(rs.getString("producto"));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
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
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tab_prod = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        lblencontrados = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        checkTodos = new javax.swing.JRadioButton();
        txtbuscar = new rojeru_san.RSMTextFull();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BÚSQUEDA RÁDIPA");
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/Buscar.png")).getImage());
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Buscar.png"))); // NOI18N
        jLabel1.setText("BÚSQUEDA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab_prod.setFont(new java.awt.Font("Andalus", 0, 14)); // NOI18N
        tab_prod.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tab_prod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_prodMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tab_prodMousePressed(evt);
            }
        });
        tab_prod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tab_prodKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tab_prodKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tab_prod);

        jLabel3.setText("Ingrese las primeras letras del producto solicitado...");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Regresar.png"))); // NOI18N
        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("A granel");
        jRadioButton1.setOpaque(false);
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(checkTodos);
        checkTodos.setText("Todos");
        checkTodos.setOpaque(false);
        checkTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkTodosActionPerformed(evt);
            }
        });

        txtbuscar.setPlaceholder("Ingrese nombre del producto...");
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lblencontrados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(checkTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtbuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jRadioButton1)
                    .addComponent(checkTodos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1)
                    .addComponent(lblencontrados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void tab_prodMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_prodMousePressed
        /*    if (evt.getClickCount() == 1) {

         try {
         DefaultTableModel tabladet = (DefaultTableModel) GUIVentas.tbdetventas.getModel();
         String[] dato = new String[6];

         int fila = tab_prod.getSelectedRow();

         String codins = tab_prod.getValueAt(fila, 0).toString();
         String desins = tab_prod.getValueAt(fila, 1).toString();
         String preins = tab_prod.getValueAt(fila, 2).toString();
         String descuento = tab_prod.getValueAt(fila, 3).toString();

         int c = 0;
         int j = 0;

         //MANDA A PREGUNTAR CUANTOS PRODUCTOS SE VA A VENDER
         String cant = JOptionPane.showInputDialog("Ingrese cantidad a vender por favor!");

         //CAPTURA LA IMAGEN DE LA BD
         String Imagen = tab_prod.getValueAt(fila, 5).toString();
         ImageIcon img = new ImageIcon(Imagen);
         lblfotoventa.setIcon(new ImageIcon(img.getImage().getScaledInstance(120, 110, Image.SCALE_DEFAULT)));

         if ((cant.equals("")) || (cant.equals("0"))) {
         JOptionPane.showMessageDialog(this, "Debe ingresar algun valor mayor que 0");
         } else {

         int canting = Integer.parseInt(cant);
         int comp = Integer.parseInt(comparar(codins));
         if (canting > comp) {
         JOptionPane.showMessageDialog(this, "Ud. no cuenta con el stock apropiado para realizar la transacción");
         } else {
         for (int i = 0; i < GUIVentas.tbdetventas.getRowCount(); i++) {
         Object com = GUIVentas.tbdetventas.getValueAt(i, 0);
         if (codins.equals(com)) {
         j = i;
         GUIVentas.tbdetventas.setValueAt(cant, i, 3);//POSICION DE LA CANTIDAD EN LA TABLA
         c = c + 1;
         }

         }
         if (c == 0) {
         dato[0] = codins;
         dato[1] = desins;
         dato[2] = preins;
         dato[3] = cant;
         dato[4] = descuento;

         tabladet.addRow(dato);
         tbdetventas.setRowHeight(24);
         GUIVentas.tbdetventas.setModel(tabladet);
         CalcularVenta();
         RedondearSubtotal();
         GUIVentas.lblfotoventa.setVisible(true);
         GUIVentas.lblnotaventa.setText(" Artículos en venta " + tbdetventas.getRowCount());

         }
         }
         }

         } catch (Exception e) {
         }

         }*/

        /*if (evt.getClickCount() == 1) {
         try {

         DefaultTableModel tabladet = (DefaultTableModel) GUIVentas.tbdetventas.getModel();
         String[] dato = new String[7];//6

         int fila = tab_prod.getSelectedRow();

         String codins = tab_prod.getValueAt(fila, 0).toString();
         String desins = tab_prod.getValueAt(fila, 1).toString();
         String preins = tab_prod.getValueAt(fila, 2).toString();
         String descuento = tab_prod.getValueAt(fila, 3).toString();

         int c = 0;
         int j = 0;

         String Imagen = tab_prod.getValueAt(fila, 5).toString();
         ImageIcon img = new ImageIcon(Imagen);
         lblfotoventa.setIcon(new ImageIcon(img.getImage().getScaledInstance(65, 59, Image.SCALE_DEFAULT)));

         int comp = Integer.parseInt(comparar(codins));

         {
         for (int i = 0; i < GUIVentas.tbdetventas.getRowCount(); i++) {
         Object com = GUIVentas.tbdetventas.getValueAt(i, 0);
         if (codins.equals(com)) {
         j = i;
         c = c + 1;
         }
         }
         if (c == 0) {

         dato[0] = codins;
         dato[1] = desins;
         dato[2] = preins;
         dato[3] = "1";
         dato[4] = "0.0";
         dato[5] = descuento;

         tabladet.addRow(dato);
         tbdetventas.setRowHeight(22);
         GUIVentas.tbdetventas.setModel(tabladet);
         CalcularVenta();
         RedondearSubtotal();
         GUIVentas.lblfotoventa.setVisible(true);
         GUIVentas.lblnotaventa.setText(" Productos en la venta actual *****"+tbdetventas.getRowCount()+"****");
         }
         }
         } catch (Exception e) {
         }
         }*/

    }//GEN-LAST:event_tab_prodMousePressed

    private void tab_prodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tab_prodKeyPressed

    }//GEN-LAST:event_tab_prodKeyPressed

    private void tab_prodKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tab_prodKeyReleased

    }//GEN-LAST:event_tab_prodKeyReleased

    private void tab_prodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_prodMouseClicked

        //ENVO DIRECTO DE LA CANTIDAD POR DEFECTO !
        /*  if (evt.getClickCount() == 1) {

         try {

         DefaultTableModel tabladet = (DefaultTableModel) GUIMenu.tabla_ventas.getModel();
         String[] dato = new String[7];//6

         int fila = tab_prod.getSelectedRow();

         String codins = tab_prod.getValueAt(fila, 0).toString();
         String desins = tab_prod.getValueAt(fila, 1).toString();
         String preins = tab_prod.getValueAt(fila, 2).toString();
         String descuento = tab_prod.getValueAt(fila, 3).toString();

         int c = 0;
         int j = 0;

         /*String Imagen = tab_prod.getValueAt(fila, 5).toString();
         ImageIcon img = new ImageIcon(Imagen);
         lblfotoventa.setIcon(new ImageIcon(img.getImage().getScaledInstance(65, 59, Image.SCALE_DEFAULT)));*/
        /*  int comp = Integer.parseInt(comparar(codins));

         {
         for (int i = 0; i < GUIMenu.tabla_ventas.getRowCount(); i++) {
         Object com = GUIMenu.tabla_ventas.getValueAt(i, 0);
         if (codins.equals(com)) {
         j = i;
         c = c + 1;
         }
         }
         if (c == 0) {

         dato[0] = codins;
         dato[1] = desins;
         dato[2] = preins;
         dato[3] = "1";
         dato[4] = "0.0";
         dato[5] = descuento;

         tabladet.addRow(dato);
         tabla_ventas.setRowHeight(22);
         GUIMenu.tabla_ventas.setModel(tabladet);
         CalcularVenta();
         RedondearSubtotal();
         //GUIVentas.lblfotoventa.setVisible(true);
         GUIMenu.lbltotal.setText(" Productos en la venta actual =====" + tabla_ventas.getRowCount() + "=====");
         }
         }
         } catch (Exception e) {
         }
         }*/
        
        //SOLICTA LA CANTIDAD A ENVIAR
        if (evt.getClickCount() == 1) {

            try {

                DefaultTableModel tabladet = (DefaultTableModel) GUIMenuPrincipal.tabla_ventas.getModel();
                String[] dato = new String[7];

                int fila = tab_prod.getSelectedRow();

                String codins = tab_prod.getValueAt(fila, 0).toString();
                String desins = tab_prod.getValueAt(fila, 1).toString();
                String preins = tab_prod.getValueAt(fila, 2).toString();
                String descuento = tab_prod.getValueAt(fila, 4).toString();
                String unidad = tab_prod.getValueAt(fila, 5).toString();

                int c = 0;
                int j = 0;

                //GRANEL
                ResultSet rs = null;

                if ((unidad.equals("GRANEL")) || (unidad.equals("GRANEL"))) {
                    GUIGranel gran = new GUIGranel();
                    gran.setVisible(true);
                    int filas = tab_prod.getSelectedRow();
                    String codbarras = tab_prod.getValueAt(filas, 0).toString();
                    String nomproducto = tab_prod.getValueAt(filas, 1).toString();
                    String precioactual = tab_prod.getValueAt(filas, 2).toString();
                    String desct = tab_prod.getValueAt(filas, 4).toString();
                    GUIGranel.lblcodbarras.setText(codbarras);
                    GUIGranel.lblnomproductos.setText(nomproducto);
                    GUIGranel.lblprecioreal.setText(precioactual);
                    GUIGranel.lbldescuentopar.setText(desct);
                    GUIGranel.txtpesoKG.requestFocus();

                } else {// TERMINA A GRANEL

                    String Imagen = tab_prod.getValueAt(fila, 7).toString();
                    ImageIcon img = new ImageIcon(Imagen);
                    GUIMenuPrincipal.lblimagenventas.setIcon(new ImageIcon(img.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT)));
                    lblimagenventas.setVisible(true);

                   
                    String cantidadBalanza= GUIMenuPrincipal.txtPesoBascula.getText();

                    String cant="";
                    if(!cantidadBalanza.equals("") && !cantidadBalanza.equals("0") && !cantidadBalanza.equals("00.00") && !cantidadBalanza.equals("0.00") && !cantidadBalanza.equals("0.0")){ 
                         cant = JOptionPane.showInputDialog(null, "Introduzca la cantidad solicitada", "Solicitud", JOptionPane.QUESTION_MESSAGE,null,null, cantidadBalanza).toString();
                    }else{
                        cant = JOptionPane.showInputDialog(null, "Introduzca la cantidad solicitada", "Solicitud", JOptionPane.PLAIN_MESSAGE);
                    }
                    
                    if ((cant.equals("")) || (cant.equals("0"))) {
                        JOptionPane.showMessageDialog(this, "Debe ingresar algun valor mayor que 0", "MAFAKAFER", JOptionPane.PLAIN_MESSAGE);
                    } else {

                        double cantidad = Double.parseDouble(cant);
                        double compra = Double.parseDouble(comparar(codins));
                        if (cantidad > compra) {
                            JOptionPane.showMessageDialog(this, "El producto " + desins + ", no se encuentra en stock");
                        } else {
                            for (int i = 0; i < GUIMenuPrincipal.tabla_ventas.getRowCount(); i++) {
                                Object com = GUIMenuPrincipal.tabla_ventas.getValueAt(i, 0);
                                if (codins.equals(com)) {
                                    j = i;
                                    GUIMenuPrincipal.tabla_ventas.setValueAt(cant, i, 3);
                                    c = c + 1;
                                }
                            }

                            if (c == 0) {

                                dato[0] = codins;
                                dato[1] = desins;
                                dato[2] = preins;
                                dato[3] = cant;
                                dato[4] = "0.0";
                                dato[5] = descuento;

                                tabladet.addRow(dato);
                                tabla_ventas.setRowHeight(30);
                                GUIMenuPrincipal.tabla_ventas.setModel(tabladet);

                                ColorColumnVentas ft = new ColorColumnVentas();
                                tabla_ventas.setDefaultRenderer(Object.class, ft);

                                CalcularVenta();
                                RedondearSubtotal();
                                GUIMenuPrincipal.lbltotal.setText(" Productos en la venta actual =====" + tabla_ventas.getRowCount() + "=====");
                            }
                        }
                    }
                }

            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_tab_prodMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        Productos("GRANEL");
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void checkTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkTodosActionPerformed
        Productos("");
    }//GEN-LAST:event_checkTodosActionPerformed

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
       Productos(txtbuscar.getText());
    }//GEN-LAST:event_txtbuscarKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]){
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
            java.util.logging.Logger.getLogger(GUICCProducts.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUICCProducts.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUICCProducts.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUICCProducts.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUICCProducts().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton checkTodos;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblencontrados;
    public static javax.swing.JTable tab_prod;
    private rojeru_san.RSMTextFull txtbuscar;
    // End of variables declaration//GEN-END:variables
}
