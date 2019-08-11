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
import com.sun.awt.AWTUtilities;
import static java.awt.image.ImageObserver.WIDTH;
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
import rojeru_san.RSPanelsSlider;
import util.SetProperties;

/**
 *
 * @author ADMIN
 */
public class GUIProperties extends javax.swing.JFrame {

    private GUISplashDesvanecedor splash;

//    conectar cc = new conectar();
//    Connection cn = cc.conexion();

    public GUIProperties() {
        
        setUndecorated(true);
        
        initComponents();
        
        
        txtusuario.requestFocus();
        
        
        
       
//        Generarnumeracion();
    }
//    public GUILogin(GUISplashDesvanecedor splashGUI) {
//        
//        this.splash = splashGUI;
//        
//        setUndecorated(true);
//        
//        initComponents();
        
//        setProgressB(0, "Cargando componentes...");
//        setProgressB(10, "Conectando a la base de datos...");
//        setProgressB(10, "Conectando a la base de datos...");
//        setProgressB(20, "Cargando interfacez de usuario...");
//        setProgressB(20, "Cargando interfacez de usuario...");
//        setProgressB(20, "Cargando reportes...");
//        setProgressB(40, "Compilando ventas...");
//        setProgressB(70, "Cargando listas...");
//        setProgressB(80, "Cargando listas...");
//        setProgressB(85, "Espere unos segundos...");
//        setProgressB(95, "Espere unos segundos...");
//        setProgressB(100, "Inicio del sistema...");
//        setProgressB(100, "Inicio del sistema...");
//    }
//
//
//    private void setProgressB(int login, String mens) {
//        splash.getLabel().setText(mens);
//        splash.getProgressBar().setValue(login);
//        try {
//            Thread.sleep(1000);
//
//        } catch (InterruptedException e) {
//            System.out.println(e);
//        }
//    }
    void PrivilegiosAdministrador(){
     GUIMenuPrincipal.lblabrircaja.setVisible(true);
     GUIMenuPrincipal.lblingresos.setVisible(true);
     GUIMenuPrincipal.lblcategorias.setVisible(true);
     GUIMenuPrincipal.lblmarcas.setVisible(true);
     GUIMenuPrincipal.lblunidades.setVisible(true);
     //GUIMenuPrincipal.lblproductos.setVisible(true);
     GUIMenuPrincipal.lblprecios.setVisible(true);
     GUIMenuPrincipal.lblmasvendido.setVisible(true);
     GUIMenuPrincipal.lblmascomprado.setVisible(true);
     GUIMenuPrincipal.lblcodbarrasproducto.setVisible(true);
     GUIMenuPrincipal.lblcobarrastodos.setVisible(true);
     GUIMenuPrincipal.lblregistro.setVisible(true);
     GUIMenuPrincipal.lblinventarios.setVisible(true);
     
     GUIMenuPrincipal.lblinventarios.setVisible(true);
     GUIMenuPrincipal.lblregistro.setVisible(true);
     GUIMenuPrincipal.lblcambioclave.setVisible(true);
     GUIMenuPrincipal.lblrecuperarclave.setVisible(true);
     GUIMenuPrincipal.lblconceptos.setVisible(true);
     GUIMenuPrincipal.lblregusuarios.setVisible(true);
     GUIMenuPrincipal.btNuevoProducto.setEnabled(true);
     GUIMenuPrincipal.btExpotProductos.setEnabled(true);
     
     /*PANEL INVENTARIO*/   
     GUIMenuPrincipal.panelInventariosManual.setVisible(false);
     GUIMenuPrincipal.txtcbarra.setEnabled(false);
     GUIMenuPrincipal.txtdescriproducto.setEnabled(false);
     GUIMenuPrincipal.txtcantactual.setEnabled(false);
     GUIMenuPrincipal.spAjuste.setEnabled(false);
     GUIMenuPrincipal.btAjustar.setEnabled(false);
     
     /*PANEL CONFIGURACION EMPRESA*/   
     
     GUIMenuPrincipal.txtemp.setEnabled(true);
     GUIMenuPrincipal.txtprop.setEnabled(true);
     GUIMenuPrincipal.txtdir.setEnabled(true);
     GUIMenuPrincipal.txtzon.setEnabled(true);
     GUIMenuPrincipal.txtref.setEnabled(true);
     GUIMenuPrincipal.txt1.setEnabled(true);
     GUIMenuPrincipal.txt2.setEnabled(true);
     GUIMenuPrincipal.txtruc.setEnabled(true);
     GUIMenuPrincipal.txtemail.setEnabled(true);
     GUIMenuPrincipal.txtweb.setEnabled(true);
     GUIMenuPrincipal.txtfechareg.setEnabled(true);
     GUIMenuPrincipal.txtrutaimagenemp.setEnabled(true);
     GUIMenuPrincipal.panelImgEmpresa.setEnabled(true);     
     GUIMenuPrincipal.btNEmp.setEnabled(true);
     GUIMenuPrincipal.btGEmp.setEnabled(true);
     GUIMenuPrincipal.btAEmp.setEnabled(true);
     GUIMenuPrincipal.btEEmp.setEnabled(true);
     GUIMenuPrincipal.btSFoto.setEnabled(true);
     
     
     /*PANEL CONFIGURACION TICKET*/   
     GUIMenuPrincipal.txtnomticket.setEnabled(true);
     GUIMenuPrincipal.txtenticket.setEnabled(true);
     GUIMenuPrincipal.txtdirectiticket.setEnabled(true);
     GUIMenuPrincipal.txtructick.setEnabled(true);
     GUIMenuPrincipal.txtteltick.setEnabled(true);
     GUIMenuPrincipal.txtbticket.setEnabled(true);
     GUIMenuPrincipal.btSFotoTick.setEnabled(true);
     GUIMenuPrincipal.btNTicket.setEnabled(true);
     GUIMenuPrincipal.btGTicket.setEnabled(true);
     GUIMenuPrincipal.btATicket.setEnabled(true);
     GUIMenuPrincipal.btETicket.setEnabled(true);
     GUIMenuPrincipal.txtpieticket.setEnabled(true);
          
     GUIMenuPrincipal.chInventarios.setEnabled(true);
     GUIMenuPrincipal.chPDF.setEnabled(true);
     GUIMenuPrincipal.chReportes.setEnabled(true);
     GUIMenuPrincipal.chCargarPro.setEnabled(true);
    }
    
  void PrivilegiosEmpleados(){
     GUIMenuPrincipal.lblabrircaja.setVisible(false);
     GUIMenuPrincipal.lblingresos.setVisible(false);
     GUIMenuPrincipal.lblcategorias.setVisible(false);
     GUIMenuPrincipal.lblmarcas.setVisible(false);
     GUIMenuPrincipal.lblunidades.setVisible(false);
     GUIMenuPrincipal.lblproductos.setVisible(false);
     GUIMenuPrincipal.lblprecios.setVisible(false);
     GUIMenuPrincipal.lblmasvendido.setVisible(false);
     GUIMenuPrincipal.lblmascomprado.setVisible(false);
     GUIMenuPrincipal.lblcodbarrasproducto.setVisible(false);
     GUIMenuPrincipal.lblcobarrastodos.setVisible(false);
     GUIMenuPrincipal.lblregistro.setVisible(false);
     GUIMenuPrincipal.lblinventarios.setVisible(false);
     GUIMenuPrincipal.panelInventariosManual.setVisible(false);
     GUIMenuPrincipal.lblinventarios.setVisible(false);
     GUIMenuPrincipal.lblregistro.setVisible(false);
     GUIMenuPrincipal.lblcambioclave.setVisible(false);
     GUIMenuPrincipal.lblrecuperarclave.setVisible(false);
     GUIMenuPrincipal.lblconceptos.setVisible(false);
     GUIMenuPrincipal.lblregusuarios.setVisible(false);
     GUIMenuPrincipal.btNuevoProducto.setEnabled(false);
     GUIMenuPrincipal.btExpotProductos.setEnabled(false);
     
     /*PANEL INVENTARIO*/   
     
     GUIMenuPrincipal.txtcbarra.setEnabled(false);
     GUIMenuPrincipal.txtdescriproducto.setEnabled(false);
     GUIMenuPrincipal.txtcantactual.setEnabled(false);
     GUIMenuPrincipal.spAjuste.setEnabled(false);
     GUIMenuPrincipal.btAjustar.setEnabled(false);
     
     /*PANEL CONFIGURACION EMPRESA*/   
     
     GUIMenuPrincipal.txtemp.setEnabled(false);
     GUIMenuPrincipal.txtprop.setEnabled(false);
     GUIMenuPrincipal.txtdir.setEnabled(false);
     GUIMenuPrincipal.txtzon.setEnabled(false);
     GUIMenuPrincipal.txtref.setEnabled(false);
     GUIMenuPrincipal.txt1.setEnabled(false);
     GUIMenuPrincipal.txt2.setEnabled(false);
     GUIMenuPrincipal.txtruc.setEnabled(false);
     GUIMenuPrincipal.txtemail.setEnabled(false);
     GUIMenuPrincipal.txtweb.setEnabled(false);
     GUIMenuPrincipal.txtfechareg.setEnabled(false);
     GUIMenuPrincipal.txtrutaimagenemp.setEnabled(false);
     GUIMenuPrincipal.panelImgEmpresa.setEnabled(false);     
     GUIMenuPrincipal.btNEmp.setEnabled(false);
     GUIMenuPrincipal.btGEmp.setEnabled(false);
     GUIMenuPrincipal.btAEmp.setEnabled(false);
     GUIMenuPrincipal.btEEmp.setEnabled(false);
     GUIMenuPrincipal.btSFoto.setEnabled(false);
     
     
     /*PANEL CONFIGURACION TICKET*/   
     GUIMenuPrincipal.txtnomticket.setEnabled(false);
     GUIMenuPrincipal.txtenticket.setEnabled(false);
     GUIMenuPrincipal.txtdirectiticket.setEnabled(false);
     GUIMenuPrincipal.txtructick.setEnabled(false);
     GUIMenuPrincipal.txtteltick.setEnabled(false);
     GUIMenuPrincipal.txtbticket.setEnabled(false);
     GUIMenuPrincipal.btSFotoTick.setEnabled(false);
     GUIMenuPrincipal.btNTicket.setEnabled(false);
     GUIMenuPrincipal.btGTicket.setEnabled(false);
     GUIMenuPrincipal.btATicket.setEnabled(false);
     GUIMenuPrincipal.btETicket.setEnabled(false);
     GUIMenuPrincipal.txtpieticket.setEnabled(false);
          
     GUIMenuPrincipal.chInventarios.setEnabled(false);
     GUIMenuPrincipal.chPDF.setEnabled(false);
     GUIMenuPrincipal.chReportes.setEnabled(false);
     GUIMenuPrincipal.chCargarPro.setEnabled(false);
    }
  
void PrivilegiosCajeros(){
     GUIMenuPrincipal.lblabrircaja.setVisible(false);
     GUIMenuPrincipal.lblingresos.setVisible(false);
     GUIMenuPrincipal.lblcategorias.setVisible(false);
     GUIMenuPrincipal.lblmarcas.setVisible(false);
     GUIMenuPrincipal.lblunidades.setVisible(false);
     GUIMenuPrincipal.lblproductos.setVisible(false);
     GUIMenuPrincipal.lblprecios.setVisible(false);
     GUIMenuPrincipal.lblmasvendido.setVisible(false);
     GUIMenuPrincipal.lblmascomprado.setVisible(false);
     GUIMenuPrincipal.lblcodbarrasproducto.setVisible(false);
     GUIMenuPrincipal.lblcobarrastodos.setVisible(false);
     GUIMenuPrincipal.lblregistro.setVisible(false);
     GUIMenuPrincipal.lblinventarios.setVisible(false);
     GUIMenuPrincipal.panelInventariosManual.setVisible(false);
     GUIMenuPrincipal.lblinventarios.setVisible(false);
     GUIMenuPrincipal.lblregistro.setVisible(false);
     GUIMenuPrincipal.lblcambioclave.setVisible(false);
     GUIMenuPrincipal.lblrecuperarclave.setVisible(false);
     GUIMenuPrincipal.lblconceptos.setVisible(false);
     GUIMenuPrincipal.lblregusuarios.setVisible(false);
     GUIMenuPrincipal.btNuevoProducto.setEnabled(false);
     GUIMenuPrincipal.btExpotProductos.setEnabled(false);
     
     /*PANEL INVENTARIO*/   
     
     GUIMenuPrincipal.txtcbarra.setEnabled(false);
     GUIMenuPrincipal.txtdescriproducto.setEnabled(false);
     GUIMenuPrincipal.txtcantactual.setEnabled(false);
     GUIMenuPrincipal.spAjuste.setEnabled(false);
     GUIMenuPrincipal.btAjustar.setEnabled(false);
     
     /*PANEL CONFIGURACION EMPRESA*/   
     
     GUIMenuPrincipal.txtemp.setEnabled(false);
     GUIMenuPrincipal.txtprop.setEnabled(false);
     GUIMenuPrincipal.txtdir.setEnabled(false);
     GUIMenuPrincipal.txtzon.setEnabled(false);
     GUIMenuPrincipal.txtref.setEnabled(false);
     GUIMenuPrincipal.txt1.setEnabled(false);
     GUIMenuPrincipal.txt2.setEnabled(false);
     GUIMenuPrincipal.txtruc.setEnabled(false);
     GUIMenuPrincipal.txtemail.setEnabled(false);
     GUIMenuPrincipal.txtweb.setEnabled(false);
     GUIMenuPrincipal.txtfechareg.setEnabled(false);
     GUIMenuPrincipal.txtrutaimagenemp.setEnabled(false);
     GUIMenuPrincipal.panelImgEmpresa.setEnabled(false);     
     GUIMenuPrincipal.btNEmp.setEnabled(false);
     GUIMenuPrincipal.btGEmp.setEnabled(false);
     GUIMenuPrincipal.btAEmp.setEnabled(false);
     GUIMenuPrincipal.btEEmp.setEnabled(false);
     GUIMenuPrincipal.btSFoto.setEnabled(false);
     
     
     /*PANEL CONFIGURACION TICKET*/   
     GUIMenuPrincipal.txtnomticket.setEnabled(false);
     GUIMenuPrincipal.txtenticket.setEnabled(false);
     GUIMenuPrincipal.txtdirectiticket.setEnabled(false);
     GUIMenuPrincipal.txtructick.setEnabled(false);
     GUIMenuPrincipal.txtteltick.setEnabled(false);
     GUIMenuPrincipal.txtbticket.setEnabled(false);
     GUIMenuPrincipal.btSFotoTick.setEnabled(false);
     GUIMenuPrincipal.btNTicket.setEnabled(false);
     GUIMenuPrincipal.btGTicket.setEnabled(false);
     GUIMenuPrincipal.btATicket.setEnabled(false);
     GUIMenuPrincipal.btETicket.setEnabled(false);
     GUIMenuPrincipal.txtpieticket.setEnabled(false);
          
     GUIMenuPrincipal.chInventarios.setEnabled(false);
     GUIMenuPrincipal.chPDF.setEnabled(false);
     GUIMenuPrincipal.chReportes.setEnabled(false);
     GUIMenuPrincipal.chCargarPro.setEnabled(false);
    }

    void Acceso(String usuario, String pass, String tipo) {
        String cap = "";
        String sql = "SELECT * FROM tabla_usuarios WHERE nick='" + usuario + "' && password='" + pass + "' && tipousuario = '" + tipo + "'";

        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cap = rs.getString("tipousuario");
            }

            if (cap.equals("Administrador")) {
                this.setVisible(false);
                JOptionPane.showMessageDialog(null, "Bienvenido al Sistema "
                        +cap+ " " + usuario);
                

                GUIMenuPrincipal ingreso = new GUIMenuPrincipal();
                
                ingreso.setLocationRelativeTo(null);
                ingreso.setVisible(true);
                ingreso.pack();

                if (JOptionPane.showConfirmDialog(rootPane, "Para empezar a trabajar debe de abrir\n"
                        + "Caja el día de hoy. Abrir caja?", "Aviso del sistema", 1) == 0) {

                    GUICaja caja = new GUICaja();
                    caja.setVisible(true);
                    dispose();
                    
                }
                PrivilegiosAdministrador();
                GUIMenuPrincipal.lblusuario.setText(usuario);
                GUIMenuPrincipal.lblcargo.setText(tipo);
                GUIMenuPrincipal.cbUsuarioVenta.setSelectedItem(usuario);
                GUIMenuPrincipal.cbUsuarioCompra.setSelectedItem(usuario);
            }
            if (cap.equals("Empleado")) {
                this.setVisible(false);
                JOptionPane.showMessageDialog(null, "Bienvenido al Sistema "
                        +cap+ " " + usuario);

                GUIMenuPrincipal ingreso = new GUIMenuPrincipal();
                ingreso.setLocationRelativeTo(null);
                ingreso.setVisible(true);
                ingreso.pack();
                if (JOptionPane.showConfirmDialog(rootPane, "Para empezar a trabajar debe de abrir\n"
                        + "Caja el día de hoy. Abrir caja?", "Aviso del sistema", 1) == 0) {

                    GUICaja caja = new GUICaja();
                    caja.setVisible(true);
                    dispose();
                }
                PrivilegiosEmpleados();
                GUIMenuPrincipal.lblusuario.setText(usuario);
                GUIMenuPrincipal.lblcargo.setText(tipo);
                GUIMenuPrincipal.cbUsuarioVenta.setSelectedItem(usuario);
                GUIMenuPrincipal.cbUsuarioCompra.setSelectedItem(usuario);

            }
            if (cap.equals("Cajero")) {
                this.setVisible(false);
                JOptionPane.showMessageDialog(null, "Bienvenido al Sistema "
                        +cap+ " " + usuario);

                GUIMenuPrincipal ingreso = new GUIMenuPrincipal();
                ingreso.setLocationRelativeTo(null);
                ingreso.setVisible(true);
                ingreso.pack();
                if (JOptionPane.showConfirmDialog(rootPane, "Para empezar a trabajar debe de abrir\n"
                        + "Caja el día de hoy. Abrir caja?", "Aviso del sistema", 1) == 0) {

                    GUICaja caja = new GUICaja();
                    caja.setVisible(true);
                    dispose();
                }
                PrivilegiosCajeros();
                GUIMenuPrincipal.lblusuario.setText(usuario);
                GUIMenuPrincipal.lblcargo.setText(tipo);
                GUIMenuPrincipal.cbUsuarioVenta.setSelectedItem(usuario);
                GUIMenuPrincipal.cbUsuarioCompra.setSelectedItem(usuario);

            }

            if ((!cap.equals("Administrador")) && (!cap.equals("Empleado"))&& (!cap.equals("Cajero"))) {

                System.out.println("Datos no encontrados\n"
                        + "Sin registros en la base de datos");

                GUIProperties.lblnotas.setText("El Usuario y/o Contraseña no estan autorizadas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUIProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

   



    public static String fechaact() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy/MM/dd");
        return formatofecha.format(fecha);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        rSButtonMetro3 = new rojerusan.RSButtonMetro();
        pnlSlider = new rojeru_san.RSPanelsSlider();
        panelRegistrar = new javax.swing.JPanel();
        rSMaterialButtonRectangle5 = new rojerusan.RSMaterialButtonRectangle();
        jLabel2 = new javax.swing.JLabel();
        txtUserPro = new javax.swing.JTextField();
        txtPuertoPro = new javax.swing.JTextField();
        txtIpPro = new javax.swing.JTextField();
        txtPassPro = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        panelSesion = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtusuario = new rojeru_san.RSMTextFull();
        txtcontraseña = new rojeru_san.RSMPassView();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        cbTipo = new rojerusan.RSComboMetro();
        lblnotas = new javax.swing.JLabel();
        rSButtonMetro4 = new rojerusan.RSButtonMetro();
        rSMaterialButtonRectangle6 = new rojerusan.RSMaterialButtonRectangle();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/LogoOK2019.png")).getImage());
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSButtonMetro3.setText("X");
        rSButtonMetro3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro3ActionPerformed(evt);
            }
        });
        jPanel3.add(rSButtonMetro3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 40, 30));

        panelRegistrar.setBackground(new java.awt.Color(255, 255, 255));
        panelRegistrar.setName("panelRegistrar"); // NOI18N

        rSMaterialButtonRectangle5.setText("Probar conexión");
        rSMaterialButtonRectangle5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle5ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Propiedades base de datos");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 255));
        jLabel3.setText("Usuario:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 255));
        jLabel4.setText("Contraseña:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 255));
        jLabel5.setText("Ip o Dominio:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 255));
        jLabel6.setText("Puerto:");

        javax.swing.GroupLayout panelRegistrarLayout = new javax.swing.GroupLayout(panelRegistrar);
        panelRegistrar.setLayout(panelRegistrarLayout);
        panelRegistrarLayout.setHorizontalGroup(
            panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSMaterialButtonRectangle5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelRegistrarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtPuertoPro, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtIpPro, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtPassPro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                        .addComponent(txtUserPro, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRegistrarLayout.setVerticalGroup(
            panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistrarLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUserPro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassPro, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIpPro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPuertoPro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(40, 40, 40)
                .addComponent(rSMaterialButtonRectangle5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlSlider.add(panelRegistrar, "card3");

        panelSesion.setBackground(new java.awt.Color(255, 255, 255));
        panelSesion.setName("panelSesion"); // NOI18N
        panelSesion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Access.png"))); // NOI18N
        jLabel1.setText("Iniciar Sesión");
        panelSesion.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 16, 319, -1));

        txtusuario.setOpaque(false);
        txtusuario.setPlaceholder("Usuario");
        panelSesion.add(txtusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 64, 319, -1));

        txtcontraseña.setOpaque(false);
        txtcontraseña.setPlaceholder("Contraseña");
        panelSesion.add(txtcontraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 122, 319, -1));

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(153, 0, 0));
        rSMaterialButtonRectangle1.setText("SALIR");
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });
        panelSesion.add(rSMaterialButtonRectangle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 145, 40));

        rSMaterialButtonRectangle2.setText("ACEPTAR");
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });
        panelSesion.add(rSMaterialButtonRectangle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 145, 40));

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Seleccionar---", "Administrador", "Empleado", "Cajero" }));
        panelSesion.add(cbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 180, 319, -1));

        lblnotas.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblnotas.setForeground(new java.awt.Color(153, 0, 0));
        lblnotas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelSesion.add(lblnotas, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 221, 319, 16));

        pnlSlider.add(panelSesion, "card2");

        jPanel3.add(pnlSlider, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 340, 330));

        rSButtonMetro4.setText("__");
        rSButtonMetro4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro4ActionPerformed(evt);
            }
        });
        jPanel3.add(rSButtonMetro4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 40, 30));

        rSMaterialButtonRectangle6.setText("Volver");
        rSMaterialButtonRectangle6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle6ActionPerformed(evt);
            }
        });
        jPanel3.add(rSMaterialButtonRectangle6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 320, 47));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 400, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "Esta seguro de salir", "Salir del sistema", 1) == 0) {
            System.exit(WIDTH);
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void rSMaterialButtonRectangle5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle5ActionPerformed
     
        String usu = txtUserPro.getText();
        String pas = new String(txtPassPro.getPassword());
        String ip = txtIpPro.getText();
        String puerto = txtPuertoPro.getText();
        SetProperties prop = new SetProperties();
        int res = prop.darPropiedades(usu, pas, ip, puerto);
        if (res==1) {
            JOptionPane.showMessageDialog(null, "Conexion exitosa");
           // this.dispose();
        } else{
             JOptionPane.showMessageDialog(null, "No se pudo conectar");
        }       
        //Acceso(usu, pas, tip);
        txtcontraseña.setText("");
        txtusuario.requestFocus();
     
    }//GEN-LAST:event_rSMaterialButtonRectangle5ActionPerformed

    private void rSButtonMetro3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro3ActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "Esta seguro de salir", "Salir del sistema", 1) == 0) {
            System.exit(WIDTH);
        }
    }//GEN-LAST:event_rSButtonMetro3ActionPerformed

    private void rSButtonMetro4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro4ActionPerformed
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_rSButtonMetro4ActionPerformed

    private void rSMaterialButtonRectangle6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle6ActionPerformed
        // TODO add your handling code here:
        GUILogin form = new GUILogin();
        form.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_rSMaterialButtonRectangle6ActionPerformed

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
            java.util.logging.Logger.getLogger(GUIProperties.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIProperties.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIProperties.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIProperties.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIProperties().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSComboMetro cbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JLabel lblnotas;
    private javax.swing.JPanel panelRegistrar;
    private javax.swing.JPanel panelSesion;
    private rojeru_san.RSPanelsSlider pnlSlider;
    private rojerusan.RSButtonMetro rSButtonMetro3;
    private rojerusan.RSButtonMetro rSButtonMetro4;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle5;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle6;
    private javax.swing.JTextField txtIpPro;
    private javax.swing.JPasswordField txtPassPro;
    private javax.swing.JTextField txtPuertoPro;
    private javax.swing.JTextField txtUserPro;
    private rojeru_san.RSMPassView txtcontraseña;
    private rojeru_san.RSMTextFull txtusuario;
    // End of variables declaration//GEN-END:variables
}
