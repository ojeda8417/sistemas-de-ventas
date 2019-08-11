/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUInterfaces;

import Clases.Conexion;
import Clases.GenerarCodigos;
import Clases.conectar;
import GUIConsultas.GUICClientes;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author
 */
public class GUIClientes extends javax.swing.JFrame {

    DefaultTableModel model;
    int contador;
    int cont = 0;
    Icon mensaje;

    public GUIClientes() {
        initComponents();
        setLocationRelativeTo(null);
        GenerarCods();
        NomCli();
        Clientes("");
        mensaje = new ImageIcon("src/Recursos/InfoIniP.png");
    }

    void contar() {
        String sql2 = "SELECT count(*) as cont from tabla_clientes";
        try {
            int con = 0;
            Statement s = Conexion.conexion().createStatement();
            ResultSet rs = s.executeQuery(sql2);
            while (rs.next()) {
                con = rs.getInt("cont");
            }
            if (con == 0) {
            } else {
                cont = con - 1;
                cargar(cont);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUIClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void cargar(int limite) {
        String sql = "SELECT * FROM tabla_clientes limit " + limite + ",1";

        try {

            Statement s = Conexion.conexion().createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                txtcodigo.setText(rs.getString("codcliente"));
                txtident.setText(rs.getString("identidad"));
                txtnombres.setText(rs.getString("nombres"));
                txtdirec.setText(rs.getString("direccion"));
                txttelf.setText(rs.getString("telefono"));
                txtcel.setText(rs.getString("celular"));
                txtemail.setText(rs.getString("email"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(GUIClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void GenerarCods() {

        int j;
        int cont = 1;
        String num = "";
        String c = "";
        String SQL = "select max(codcliente) from tabla_clientes";

        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                c = rs.getString(1);
            }

            if (c == null) {
                txtcodigo.setText("CL0001");
            } else {
                char r1 = c.charAt(2);
                char r2 = c.charAt(3);
                char r3 = c.charAt(4);
                char r4 = c.charAt(5);
                String r = "";
                r = "" + r1 + r2 + r3 + r4;
                j = Integer.parseInt(r);
                GenerarCodigos gen = new GenerarCodigos();
                gen.generar(j);
                txtcodigo.setText("CL" + gen.serie());

            }
        } catch (SQLException ex) {
            Logger.getLogger(GUIClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void NomCli() {
        TextAutoCompleter textAutoC = new TextAutoCompleter(txtbuscarcli);
        try {
           // Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
            Statement sent = Conexion.conexion().createStatement();
            ResultSet rs = sent.executeQuery("SELECT nombres FROM tabla_clientes");
            while (rs.next()) {

                textAutoC.addItem(rs.getString("nombres"));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    void Clientes(String valor) {
        String mostrar = "SELECT * FROM tabla_clientes WHERE CONCAT(codcliente,nombres,identidad) LIKE '%" + valor + "%'";
        String[] titulos = {"Nº", "IDENT.", "NOMBRES", "DIRECCIÓN", "TELÉFONO", "CELULAR", "E-MAIL"};
        String[] Registros = new String[7];
        model = new DefaultTableModel(null, titulos);

        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(mostrar);
            while (rs.next()) {
                Registros[0] = rs.getString("codcliente");
                Registros[1] = rs.getString("identidad");
                Registros[2] = rs.getString("nombres");
                Registros[3] = rs.getString("direccion");
                Registros[4] = rs.getString("telefono");
                Registros[5] = rs.getString("celular");
                Registros[6] = rs.getString("email");

                model.addRow(Registros);
                tbclientes.setAutoResizeMode(tbclientes.AUTO_RESIZE_OFF);
                tbclientes.setRowHeight(18);
                tbclientes.setModel(model);
//                lblnota.setText("Existen aproximadamente " + tbclientes.getRowCount() + " clientes registrados en el sistema");
                tbclientes.getColumnModel().getColumn(0).setPreferredWidth(60);
                tbclientes.getColumnModel().getColumn(1).setPreferredWidth(100);
                tbclientes.getColumnModel().getColumn(2).setPreferredWidth(240);
                tbclientes.getColumnModel().getColumn(3).setPreferredWidth(240);
                tbclientes.getColumnModel().getColumn(4).setPreferredWidth(90);
                tbclientes.getColumnModel().getColumn(5).setPreferredWidth(90);
                tbclientes.getColumnModel().getColumn(6).setPreferredWidth(200);

            }

        } catch (SQLException ex) {
            Logger.getLogger(GUICClientes.class.getName()).log(Level.SEVERE, null, ex);
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

        panelDiseño = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txttelf = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtdirec = new javax.swing.JTextField();
        txtident = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtnombres = new javax.swing.JTextField();
        txtcodigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtcel = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtbuscarcli = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbclientes = new javax.swing.JTable();
        txtbcli = new javax.swing.JTextField();
        panelBotones = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CLIENTES");
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/Clientes.png")).getImage());
        setResizable(false);

        panelDiseño.setBackground(new java.awt.Color(255, 255, 255));
        panelDiseño.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setOpaque(false);

        txttelf.setBackground(new java.awt.Color(255, 255, 204));
        txttelf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txttelf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttelf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelfKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Teléfono:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Dirección:");

        txtdirec.setBackground(new java.awt.Color(255, 255, 204));
        txtdirec.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtdirec.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtdirec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdirecActionPerformed(evt);
            }
        });

        txtident.setBackground(new java.awt.Color(255, 255, 204));
        txtident.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtident.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtident.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtidentKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Identidad:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Nombres:");

        txtnombres.setBackground(new java.awt.Color(255, 255, 204));
        txtnombres.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtnombres.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtcodigo.setEditable(false);
        txtcodigo.setBackground(new java.awt.Color(255, 255, 204));
        txtcodigo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Código:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Celular:");

        txtcel.setBackground(new java.awt.Color(255, 255, 204));
        txtcel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtcel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcelKeyTyped(evt);
            }
        });

        txtemail.setBackground(new java.awt.Color(255, 255, 204));
        txtemail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtemail.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("E-Mail:");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Opciones de búsqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        txtbuscarcli.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        txtbuscarcli.setText("Ingrese nombre del cliente");
        txtbuscarcli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtbuscarcliMouseClicked(evt);
            }
        });
        txtbuscarcli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscarcliKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarcliKeyReleased(evt);
            }
        });
        jPanel2.add(txtbuscarcli);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtdirec)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(txttelf, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtemail, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
                                .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtnombres))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtident, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtcel, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtident, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdirec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttelf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Registro", jPanel1);

        jPanel5.setOpaque(false);

        tbclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tbclientes);

        txtbcli.setText("Ingrese nombre del cliente");
        txtbcli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbcliKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                    .addComponent(txtbcli))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtbcli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Lista", jPanel5);

        panelBotones.setOpaque(false);
        panelBotones.setLayout(new java.awt.GridLayout(1, 0));

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Primero.png"))); // NOI18N
        jButton9.setToolTipText("Primero");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        panelBotones.add(jButton9);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Anterior.png"))); // NOI18N
        jButton7.setToolTipText("Anterior");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        panelBotones.add(jButton7);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Siguiente.png"))); // NOI18N
        jButton8.setToolTipText("Siguiente");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        panelBotones.add(jButton8);

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Ultimo.png"))); // NOI18N
        jButton10.setToolTipText("Ultimo");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        panelBotones.add(jButton10);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Nuevos.png"))); // NOI18N
        jButton1.setToolTipText("Nuevo cliente");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panelBotones.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Guardar.png"))); // NOI18N
        jButton2.setToolTipText("Guardar cliente");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        panelBotones.add(jButton2);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Actualizar.png"))); // NOI18N
        jButton5.setToolTipText("Actualizar datos");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        panelBotones.add(jButton5);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Eliminar.png"))); // NOI18N
        jButton3.setToolTipText("Eliminar datos");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        panelBotones.add(jButton3);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/BuscarG.png"))); // NOI18N
        jButton6.setToolTipText("Buscar clientes");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        panelBotones.add(jButton6);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Regresar.png"))); // NOI18N
        jButton4.setToolTipText("Salir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        panelBotones.add(jButton4);

        javax.swing.GroupLayout panelDiseñoLayout = new javax.swing.GroupLayout(panelDiseño);
        panelDiseño.setLayout(panelDiseñoLayout);
        panelDiseñoLayout.setHorizontalGroup(
            panelDiseñoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDiseñoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDiseñoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelDiseñoLayout.setVerticalGroup(
            panelDiseñoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDiseñoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDiseño, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDiseño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            PreparedStatement pst = Conexion.conexion().prepareStatement("UPDATE tabla_clientes SET identidad='" + txtident.getText()
                    + "',nombres='" + txtnombres.getText()
                    + "',direccion='" + txtdirec.getText()
                    + "',telefono='" + txttelf.getText()
                    + "',celular='" + txtcel.getText()
                    + "',email='" + txtemail.getText() + "' WHERE codcliente='" + txtcodigo.getText() + "'");

            JOptionPane.showMessageDialog(null, "Datos actualizados con éxito", "Informe de actualización", JOptionPane.INFORMATION_MESSAGE);//ico);
            GenerarCods();
            txtnombres.setText("");
            txtcel.setText("");
            txtdirec.setText("");
            txtident.setText("");
            txttelf.setText("");
            txtemail.setText("");
            txtident.requestFocus();
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.print(e.getMessage() + e);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (this.txtident.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Hay campos sin llenar", "Correjr", JOptionPane.WARNING_MESSAGE);
            this.txtident.requestFocus();
            return;
        }

        if (txtemail.getText().equals("")) {
            {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un correo existente", "Aviso", JOptionPane.ERROR_MESSAGE);
                this.txtemail.requestFocus();
                return;
            }
        }

        String cod, ident, nom, dir, telf, cel, email;

        String sql = "";
        cod = txtcodigo.getText();
        ident = txtident.getText();
        nom = txtnombres.getText();
        dir = txtdirec.getText();
        telf = txttelf.getText();
        cel = txtcel.getText();
        email = txtemail.getText();

        sql = "INSERT INTO tabla_clientes () VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = Conexion.conexion().prepareStatement(sql);

            pst.setString(1, cod);
            pst.setString(2, ident);
            pst.setString(3, nom);
            pst.setString(4, dir);
            pst.setString(5, telf);
            pst.setString(6, cel);
            pst.setString(7, email);

            int n = pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Ha registrado un cliente con éxito");
            }
            GenerarCods();
            txtnombres.setText("");
            txtcel.setText("");
            txtdirec.setText("");
            txtident.setText("");
            txttelf.setText("");
            txtemail.setText("");
            txtident.requestFocus();

        } catch (SQLException ex) {
            Logger.getLogger(GUIClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        GenerarCods();
        txtnombres.setText("");
        txtcel.setText("");
        txtdirec.setText("");
        txtident.setText("");
        txttelf.setText("");
        txtemail.setText("");
        txtbuscarcli.setText("Ingrese nombre del cliente");
        txtnombres.requestFocus();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtdirecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdirecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdirecActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        GUICClientes ccc = new GUICClientes();
        ccc.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtbuscarcliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarcliKeyPressed
        try {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                if (this.contador >= 0) {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
                    Statement st = Conexion.conexion().createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM tabla_clientes WHERE nombres= '" + this.txtbuscarcli.getText() + "'");
                    rs.next();
                    txtcodigo.setText(String.valueOf(rs.getString("codcliente")));
                    txtident.setText(String.valueOf(rs.getString("identidad")));
                    txtnombres.setText(String.valueOf(rs.getString("nombres")));
                    txtdirec.setText(String.valueOf(rs.getString("direccion")));
                    txttelf.setText(String.valueOf(rs.getString("telefono")));
                    txtcel.setText(String.valueOf(rs.getString("celular")));
                    txtemail.setText(String.valueOf(rs.getString("email")));

                }
            }
        } catch (SQLException ex) {
        }
    }//GEN-LAST:event_txtbuscarcliKeyPressed

    private void txtbuscarcliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtbuscarcliMouseClicked
        txtbuscarcli.setText("");
    }//GEN-LAST:event_txtbuscarcliMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String nom = txtbuscarcli.getText();
        try {
            int eliminar = JOptionPane.showConfirmDialog(null, "Desea eliminar este registro?", "Información", JOptionPane.YES_NO_OPTION);
            if (eliminar == 0) {

                PreparedStatement pst = Conexion.conexion().prepareStatement("DELETE FROM tabla_clientes WHERE nombres='" + nom + "'");

                JOptionPane.showMessageDialog(null, "Registro eliminado del sistema", "Informe de eliminación", JOptionPane.INFORMATION_MESSAGE);
                pst.executeUpdate();
                GenerarCods();
                txtnombres.setText("");
                txtcel.setText("");
                txtdirec.setText("");
                txtident.setText("");
                txttelf.setText("");
                txtemail.setText("");
                txtident.requestFocus();
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtbuscarcliKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarcliKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarcliKeyReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        //CODIGO PARA RETROCEDER DE UNO EN UNO REGISTRO
        if (cont == 0) {
            cargar(cont);
        } else if (cont < 0) {
            cargar(cont = 0);
        } else {
            cont = cont - 1;
            cargar(cont);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        //CODIGO PARA VER DE UNO EN UNO REGISTRO
        cont = cont + 1;
        cargar(cont);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        Connection cn = null;
        Statement st;
        ResultSet rs = null;
        try {
            if (this.contador <= 0) {
                Class.forName("com.mysql.jdbc.Driver");
                cn = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
                st = cn.createStatement(rs.TYPE_SCROLL_SENSITIVE, rs.CONCUR_READ_ONLY);
                rs = st.executeQuery("SELECT * FROM tabla_clientes");
                rs.last();
                txtcodigo.setText(String.valueOf(rs.getString("codcliente")));
                txtident.setText(String.valueOf(rs.getString("identidad")));
                txtnombres.setText(String.valueOf(rs.getString("nombres")));
                txtdirec.setText(String.valueOf(rs.getString("direccion")));
                txttelf.setText(String.valueOf(rs.getString("telefono")));
                txtcel.setText(String.valueOf(rs.getString("celular")));
                txtemail.setText(String.valueOf(rs.getString("email")));
            } else {
                JOptionPane.showMessageDialog(null, "Este es el primer registro");
                System.out.println("Este es el primer registro");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Este es el primer registro" + e);
            System.out.println("Este es el primer registro");
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        Connection cn = null;
        Statement st;
        ResultSet rs = null;
        try {
            if (this.contador <= 0) {
                Class.forName("com.mysql.jdbc.Driver");
                cn = DriverManager.getConnection("jdbc:mysql://localhost/system_ventas", "root", "");
                st = cn.createStatement(rs.TYPE_SCROLL_SENSITIVE, rs.CONCUR_READ_ONLY);
                rs = st.executeQuery("SELECT * FROM tabla_clientes");
                rs.first();
                txtcodigo.setText(String.valueOf(rs.getString("codcliente")));
                txtident.setText(String.valueOf(rs.getString("identidad")));
                txtnombres.setText(String.valueOf(rs.getString("nombres")));
                txtdirec.setText(String.valueOf(rs.getString("direccion")));
                txttelf.setText(String.valueOf(rs.getString("telefono")));
                txtcel.setText(String.valueOf(rs.getString("celular")));
                txtemail.setText(String.valueOf(rs.getString("email")));
            } else {
                JOptionPane.showMessageDialog(null, "Este es el primer registro");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Este es el primer registro" + e);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void txtbcliKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbcliKeyReleased
        Clientes(txtbcli.getText());
    }//GEN-LAST:event_txtbcliKeyReleased

    private void txttelfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelfKeyTyped
        if (txttelf.getText().length() > 9) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese un número válido", "Aviso del sistema", JOptionPane.WARNING_MESSAGE,mensaje);
            txttelf.requestFocus();
        } 
    }//GEN-LAST:event_txttelfKeyTyped

    private void txtcelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcelKeyTyped
       if (txtcel.getText().length() > 9) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese un número válido", "Aviso del sistema", JOptionPane.WARNING_MESSAGE,mensaje);
           txtcel.requestFocus();
        } 
    }//GEN-LAST:event_txtcelKeyTyped

    private void txtidentKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidentKeyTyped
        if (txtident.getText().length() > 9) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese un número válido", "Aviso del sistema", JOptionPane.WARNING_MESSAGE,mensaje);
           txtident.requestFocus();
        } 
    }//GEN-LAST:event_txtidentKeyTyped
    

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
            java.util.logging.Logger.getLogger(GUIClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelDiseño;
    public javax.swing.JTable tbclientes;
    private javax.swing.JTextField txtbcli;
    private javax.swing.JTextField txtbuscarcli;
    public static javax.swing.JTextField txtcel;
    public static javax.swing.JTextField txtcodigo;
    public static javax.swing.JTextField txtdirec;
    public static javax.swing.JTextField txtemail;
    public static javax.swing.JTextField txtident;
    public static javax.swing.JTextField txtnombres;
    public static javax.swing.JTextField txttelf;
    // End of variables declaration//GEN-END:variables
//    conectar cc = new conectar();
//    Connection cn = cc.conexion();
}
