/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUInterfaces;

import ds.desktop.notify.DesktopNotify;
import java.awt.Image;
import java.awt.Toolkit;
import static java.lang.Thread.sleep;
import javax.swing.JOptionPane;

/**
 *
 * @author RAUL
 */
public class GUIProcesos extends javax.swing.JFrame {

    /**
     * Creates new form GUIProcesos
     */
    public GUIProcesos() {
        setUndecorated(true);
        initComponents();
        iniciarCarga();
        Image ico = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Recursos/KLeeIcono.png"));
        this.setIconImage(ico);
        setLocationRelativeTo(null);
    }
    public void iniciarCarga() { 
        new Thread() {

            @Override
            public void run() {
                int i = 0;
                while (i <= 100) {
                    i++;
                    progreso.setValue(i);
                    try {
                        sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        progreso = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Activando...");
        setResizable(false);

        progreso.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        progreso.setForeground(new java.awt.Color(0, 0, 0));
        progreso.setToolTipText("");
        progreso.setIndeterminate(true);
        progreso.setString("Espere...");
        progreso.setStringPainted(true);
        progreso.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                progresoStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(progreso, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(progreso, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void progresoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_progresoStateChanged
        if (progreso.getValue()== 50) {
           DesktopNotify.showDesktopMessage(
                "Sistema Open K´LEE PDV",
                "Se ha activado con éxito!",
                DesktopNotify.SUCCESS, 5000L);           
            this.dispose(); 
        }
    }//GEN-LAST:event_progresoStateChanged

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
            java.util.logging.Logger.getLogger(GUIProcesos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIProcesos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIProcesos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIProcesos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIProcesos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar progreso;
    // End of variables declaration//GEN-END:variables
public javax.swing.JProgressBar getjProgressBar() {
        return progreso;
    }
}
