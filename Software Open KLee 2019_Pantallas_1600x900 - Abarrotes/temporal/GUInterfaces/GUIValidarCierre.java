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
import java.awt.Image;
import java.awt.Toolkit;
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
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class GUIValidarCierre extends javax.swing.JFrame {

//    conectar cc = new conectar();
//    Connection cn = cc.conexion();
    
    public GUIValidarCierre() {
        initComponents();
        setLocationRelativeTo(null);
        BuscarUsers();
        setTitle("VALIDACIÓN DE USUARIO");
        Image ico = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Recursos/RecuperarClave.png"));
        this.setIconImage(ico);
        lblfecha.setVisible(false);
        lblhora.setVisible(false);
        relojLog hilo = new relojLog(lblhora);
        hilo.start();
        lblfecha.setText(fechaact());
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
                JOptionPane.showMessageDialog(null, "Has accedido como usuario: \n"
                        + usuario);

                GUICierreCaja caj = new GUICierreCaja();
                caj.setLocationRelativeTo(null);
                caj.setVisible(true);
                caj.pack();              
                
            }
            if (cap.equals("Empleado")) {
                this.setVisible(false);
                JOptionPane.showMessageDialog(null, "Has accedido como usuario: \n"
                        + usuario);

                GUICierreCaja caj = new GUICierreCaja();
                caj.setLocationRelativeTo(null);
                caj.setVisible(true);
                caj.pack();
                
            }
            if (cap.equals("Cajero")) {
                this.setVisible(false);
                JOptionPane.showMessageDialog(null, "Has accedido como usuario: \n"
                        + usuario);
                
                GUICierreCaja caj = new GUICierreCaja();
                caj.setLocationRelativeTo(null);
                caj.setVisible(true);
                caj.pack();
                
            }

            if ((!cap.equals("Administrador")) && (!cap.equals("Empleado"))&& (!cap.equals("Cajero"))) {

                System.out.println("Datos de acceso no encontrados\n"
                        + "Sin registros en la base de datos");

                JOptionPane.showMessageDialog(null, "El Usuario y/o Contraseña no estan autorizadas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUILogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void Sesiones() {
        String InsertarSQL = "INSERT INTO tabla_sesiones (usuario, tipo, hora, fecha) VALUES (?,?,?,?)";
        String usuario = txtusuario.getText();
        String tipo = cbTipo.getSelectedItem().toString();
        String hora = lblhora.getText();
        String fecha = lblfecha.getText();

        try {
            PreparedStatement pst = Conexion.conexion().prepareStatement(InsertarSQL);
            pst.setString(1, usuario);
            pst.setString(2, tipo);
            pst.setString(3, hora);
            pst.setString(4, fecha);

            int n = pst.executeUpdate();
            if (n > 0) {

            }
        } catch (SQLException ex) {
            System.out.println("Error en " + ex);
            Logger.getLogger(GUILogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void BuscarUsers() {
        TextAutoCompleter textAutoC = new TextAutoCompleter(txtusuario);
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = con.createStatement();
            ResultSet rs = sent.executeQuery("SELECT * FROM tabla_usuarios");
            while (rs.next()) {
                textAutoC.addItem(rs.getString("nick"));

            }
        } catch (SQLException e) {
            System.out.println("No se han registrado clientes");
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

        jPanel1 = new javax.swing.JPanel();
        txtusuario = new rojeru_san.RSMTextFull();
        txtcontraseña = new rojeru_san.RSMPassView();
        rSButton1 = new rojeru_san.RSButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblhora = new javax.swing.JLabel();
        lblfecha = new javax.swing.JLabel();
        cbTipo = new rojerusan.RSComboMetro();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtusuario.setOpaque(false);
        txtusuario.setPlaceholder("Ingrese usuario...");

        txtcontraseña.setOpaque(false);
        txtcontraseña.setPlaceholder("Contraseña...");

        rSButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Access.png"))); // NOI18N
        rSButton1.setText("Aceptar");
        rSButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("   VALIDACIÓN");
        jPanel2.add(jLabel1);

        lblhora.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblhora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(lblhora);

        lblfecha.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblfecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(lblfecha);

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Seleccionar---", "Administrador", "Empleado", "Cajero" }));
        cbTipo.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtcontraseña, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                    .addComponent(txtusuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rSButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtcontraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rSButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void rSButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButton1ActionPerformed
        if (this.txtusuario.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un usuario", "Aviso", JOptionPane.WARNING_MESSAGE);
            this.txtusuario.requestFocus();
            return;
        }
        if (this.txtcontraseña.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una contraseña", "Aviso", JOptionPane.WARNING_MESSAGE);
            this.txtcontraseña.requestFocus();
            return;
        }
        String usu = txtusuario.getText();
        String pas = new String(txtcontraseña.getPassword());
        String tip = cbTipo.getSelectedItem().toString();
        Acceso(usu, pas, tip);
        txtcontraseña.setText("");
        txtusuario.requestFocus();
        Sesiones();
    }//GEN-LAST:event_rSButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(GUIValidarCierre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIValidarCierre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIValidarCierre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIValidarCierre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIValidarCierre().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSComboMetro cbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblfecha;
    private javax.swing.JLabel lblhora;
    private rojeru_san.RSButton rSButton1;
    private rojeru_san.RSMPassView txtcontraseña;
    private rojeru_san.RSMTextFull txtusuario;
    // End of variables declaration//GEN-END:variables
}
