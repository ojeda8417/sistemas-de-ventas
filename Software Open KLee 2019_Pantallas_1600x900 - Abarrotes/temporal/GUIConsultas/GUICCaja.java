/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIConsultas;

import Clases.Conexion;
import Clases.conectar;
import GUInterfaces.GUICierreCaja;
import java.sql.Connection;
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
public class GUICCaja extends javax.swing.JFrame {

    DefaultTableModel model;

    public GUICCaja() {

        initComponents();
        setLocationRelativeTo(null);
        Caja("");
        Fechas();
    }
public void Fechas() {
        Calendar c2 = new GregorianCalendar();
        jdBuscarIngresos.setCalendar(c2);

    }
    void Caja(String valor) {
        String mostrar = "SELECT * FROM tabla_aperturacaja WHERE CONCAT(caja) LIKE '%" + valor + "%'";
        String[] titulos = {"Nº", "CAJA", "MONTO", "EMPLEADO", "FECHA"};
        String[] Registros = new String[5];
        model = new DefaultTableModel(null, titulos);

        try {
            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(mostrar);
            while (rs.next()) {
                Registros[0] = rs.getString("codcaja");
                Registros[1] = rs.getString("caja");
                Registros[2] = rs.getString("apertura");
                Registros[3] = rs.getString("empleado");
                Registros[4] = rs.getString("fecha");
                model.addRow(Registros);
            }
            tabla_caja.setModel(model);
            tabla_caja.setAutoResizeMode(tabla_caja.AUTO_RESIZE_OFF);
            tabla_caja.setRowHeight(20);
            tabla_caja.getColumnModel().getColumn(0).setPreferredWidth(30);
            tabla_caja.getColumnModel().getColumn(1).setPreferredWidth(140);
            tabla_caja.getColumnModel().getColumn(2).setPreferredWidth(70);
            tabla_caja.getColumnModel().getColumn(3).setPreferredWidth(250);
            tabla_caja.getColumnModel().getColumn(4).setPreferredWidth(80);

        } catch (SQLException ex) {
            Logger.getLogger(GUICCaja.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jdBuscarIngresos = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        rdFecha = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_caja = new rojerusan.RSTableMetro();
        jCheckBox1 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("APERTURAS DE CAJA");
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/Buscar.png")).getImage());
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel4.setBackground(new java.awt.Color(0, 153, 255));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("  CONSULTA DE INFORMACIÓN DE CAJA");
        jPanel4.add(jLabel1);

        jdBuscarIngresos.setOpaque(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Buscar.png"))); // NOI18N
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Seleccione la fecha que inició caja");

        rdFecha.setOpaque(false);
        rdFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdFechaActionPerformed(evt);
            }
        });

        tabla_caja.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_caja.setColorFilasBackgound1(new java.awt.Color(204, 255, 255));
        tabla_caja.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tabla_caja.setFuenteHead(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tabla_caja.setGrosorBordeFilas(0);
        tabla_caja.setSelectionBackground(new java.awt.Color(255, 255, 204));
        tabla_caja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_cajaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_caja);

        jCheckBox1.setText("Todos");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox1)
                        .addGap(14, 14, 14)
                        .addComponent(rdFecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdBuscarIngresos, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox1))
                    .addComponent(rdFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdBuscarIngresos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (this.rdFecha.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Seleccione la opción fecha", "Correjr", JOptionPane.WARNING_MESSAGE);
            this.rdFecha.requestFocus();
            return;
        }

        String consulta = "";
        if (rdFecha.isSelected() == true) {
            Date fecha = jdBuscarIngresos.getDate();
            SimpleDateFormat formatofecha = new SimpleDateFormat("YYYY-MM-dd");
            String fec = "" + formatofecha.format(fecha);
            consulta = "SELECT * FROM tabla_aperturacaja WHERE fecha='" + fec + "'";

        }

        DefaultTableModel tabla = new DefaultTableModel();
        String[] titulos = {"Nº", "CAJA", "MONTO", "EMPLEADO", "FECHA"};
        tabla.setColumnIdentifiers(titulos);
        this.tabla_caja.setModel(tabla);

        String[] Registros = new String[5];

        try {

            Statement st = Conexion.conexion().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                Registros[0] = rs.getString("codcaja");
                Registros[1] = rs.getString("caja");
                Registros[2] = rs.getString("apertura");
                Registros[3] = rs.getString("empleado");
                Registros[4] = rs.getString("fecha");

                tabla.addRow(Registros);
                tabla_caja.setRowHeight(20);
                tabla_caja.getColumnModel().getColumn(0).setPreferredWidth(30);
                tabla_caja.getColumnModel().getColumn(1).setPreferredWidth(140);
                tabla_caja.getColumnModel().getColumn(2).setPreferredWidth(70);
                tabla_caja.getColumnModel().getColumn(3).setPreferredWidth(250);
                tabla_caja.getColumnModel().getColumn(4).setPreferredWidth(80);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUICCaja.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void tabla_cajaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_cajaMouseClicked
        String monto = "", caja = "", emp = "";

        int fila = tabla_caja.getSelectedRow();
        try {
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "No ha seleccionado ningun dato");

            } else {
                caja = (String) tabla_caja.getValueAt(fila, 1);
                monto = (String) tabla_caja.getValueAt(fila, 2);
                emp = (String) tabla_caja.getValueAt(fila, 3);
                GUICierreCaja.txtnombrecaja.setText(caja);
                GUICierreCaja.txtcaja.setText(monto);
                GUICierreCaja.cbEmpleados.setSelectedItem(emp);
                this.dispose();

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tabla_cajaMouseClicked

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        Caja("");        
        if(jCheckBox1.isSelected()){
           rdFecha.setSelected(false); 
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void rdFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdFechaActionPerformed
        Caja("");        
        if(rdFecha.isSelected()){
           jCheckBox1.setSelected(false); 
        }
    }//GEN-LAST:event_rdFechaActionPerformed

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
            java.util.logging.Logger.getLogger(GUICCaja.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUICCaja.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUICCaja.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUICCaja.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUICCaja().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdBuscarIngresos;
    private javax.swing.JRadioButton rdFecha;
    private rojerusan.RSTableMetro tabla_caja;
    // End of variables declaration//GEN-END:variables
//conectar cc = new conectar();
//    Connection cn = cc.conexion();
}
