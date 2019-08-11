package GUInterfaces;

import com.sun.awt.AWTUtilities;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class GUISplash extends javax.swing.JFrame {

    private GUISplash splashGUI = this;

    public GUISplash() {

        /*  try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
         */
         
        
        try{ 
            UIManager.setLookAndFeel(new com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel()); //com.lipstikLF.LipstikLookAndFeel, com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel, org.jb2011.lnf.beautyeye.BeautyEyeLookAndFeelCross
            }
       catch (Exception e){
            e.printStackTrace();            
       }
        
    
        this.setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(null);
        iniciarCarga();
        this.setSize(900, 700);
        this.pack();

    }

    public void iniciarCarga() {
        new Thread() {

            @Override
            public void run() {
                int i = 0;
                while (i <= 100) {
                    i++;
                    ProgressB.setValue(i);
                    try {
                        sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ProgressB = new javax.swing.JProgressBar();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        progLogin = new javax.swing.JProgressBar();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        rSProgressMaterial1 = new rojerusan.componentes.RSProgressMaterial();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Iniciando Sistema Punto de Venta Open K´LEE");
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/LogoOK2019.png")).getImage());
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ProgressB.setBackground(new java.awt.Color(255, 102, 51));
        ProgressB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ProgressB.setForeground(new java.awt.Color(0, 204, 51));
        ProgressB.setStringPainted(true);
        ProgressB.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ProgressBStateChanged(evt);
            }
        });
        getContentPane().add(ProgressB, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 600, 20));

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Fondo5.jpg"))); // NOI18N

        progLogin.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                progLoginStateChanged(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("© Software realizado por Jose Ojeda. Todos los derechos reservados");
        jPanel1.add(jLabel4);

        rSProgressMaterial1.setAnchoProgress(5);

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImage1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImage1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSProgressMaterial1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(progLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rSProgressMaterial1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113)
                .addComponent(progLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(panelImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 340));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated


    }//GEN-LAST:event_formWindowActivated

    private void ProgressBStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ProgressBStateChanged
        try {
            if (ProgressB.getValue() == 20) {
                GUILogin logearse = new GUILogin();
                logearse.setVisible(true);
                this.dispose();
            } else {
//                JOptionPane.showMessageDialog(this, "No se puedo cargar ");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se puedo cargar " + ex);
            System.exit(WIDTH);
        }

    }//GEN-LAST:event_ProgressBStateChanged

    private void progLoginStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_progLoginStateChanged
        int i = 0;

        if (progLogin.getValue() == i) {
            int j = 0;
            if (j != 20) {
                AWTUtilities.setWindowOpacity(this, Float.valueOf((100 - j) / 150 + "f"));
                i++;
                j += 1;
            }
        }
        if (progLogin.getValue() == 20) {
            GUILogin v = new GUILogin();
            v.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_progLoginStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUISplash().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar ProgressB;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private javax.swing.JProgressBar progLogin;
    private rojerusan.componentes.RSProgressMaterial rSProgressMaterial1;
    // End of variables declaration//GEN-END:variables
 public javax.swing.JProgressBar getjProgressBar() {
        return ProgressB;
    }
}
