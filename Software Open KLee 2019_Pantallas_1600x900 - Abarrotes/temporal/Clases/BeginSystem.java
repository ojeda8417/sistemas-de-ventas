/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import GUInterfaces.GUILogin;
import GUInterfaces.GUISplash;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import org.jvnet.substance.SubstanceLookAndFeel;

/**
 *
 * @authorssss
 */
public class BeginSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     try {
            GUISplash acc = new GUISplash();
            acc.setLocationRelativeTo(null);
            acc.setVisible(true);

        } catch (Exception e) {
            StringBuilder sb = new StringBuilder(e.toString());
            for (StackTraceElement ste : e.getStackTrace()) {
                sb.append("\n\tat ");
                sb.append(ste);
            }
            String trace = sb.toString();
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "No se pudo cargar el menu principal: " + trace);

        }

    }
}



      
/*try {
 UIManager.setLookAndFeel(new SyntheticaWhiteVisionLookAndFeel());

 } catch (Exception e) {
 JOptionPane.showMessageDialog(null, "No se pudo cargar el skin " + e);
 }
        GUISplashDesvanecedor acc = new GUISplashDesvanecedor();
 acc.setLocationRelativeTo(null);
 acc.setVisible(true);
 }
 }*/



//LIBRERIA SYNTHETIC
       /* String SyntheticaBlackStarLookAndFeel = "";
 //         SyntheticaBlackStarLookAndFeel = "de.javasoft.plaf.synthetica.SyntheticaClassyLookAndFeel";
 //        SyntheticaBlackStarLookAndFeel = "de.javasoft.plaf.synthetica.SyntheticaOrangeMetallicLookAndFeel";
 // SyntheticaBlackStarLookAndFeel = "de.javasoft.plaf.synthetica.SyntheticaSimple2DLookAndFeel";
 // SyntheticaBlackStarLookAndFeel = "de.javasoft.plaf.synthetica.SyntheticaSkyMetallicLookAndFeel";
 // SyntheticaBlackStarLookAndFeel = "de.javasoft.plaf.synthetica.SyntheticaGreenDreamLookAndFeel";
 SyntheticaBlackStarLookAndFeel = "de.javasoft.plaf.synthetica.SyntheticaBlueIceLookAndFeel";
*/
 
       
       /*try {
 javax.swing.UIManager.setLookAndFeel(SyntheticaBlackStarLookAndFeel);
 GUISplash acc = new GUISplash();
 acc.setLocationRelativeTo(null);
 acc.setVisible(true);
 } catch (ClassNotFoundException ex) {
 Logger.getLogger(BeginSystem.class.getName()).log(Level.SEVERE, null, ex);
 } catch (InstantiationException ex) {
 Logger.getLogger(BeginSystem.class.getName()).log(Level.SEVERE, null, ex);
 } catch (IllegalAccessException ex) {
 Logger.getLogger(BeginSystem.class.getName()).log(Level.SEVERE, null, ex);
 } catch (UnsupportedLookAndFeelException ex) {
 }
 }
}*/
       
       
/*try {
 UIManager.setLookAndFeel("com.jgoodies.looks.windows.WindowsLookAndFeel");
 GUISplash lib = new GUISplash();
 lib.setLocationRelativeTo(null);
 lib.setVisible(true);
 } catch (ClassNotFoundException ex) {
 Logger.getLogger(BeginSystem.class.getName()).log(Level.SEVERE, null, ex);
 } catch (InstantiationException ex) {
 Logger.getLogger(BeginSystem.class.getName()).log(Level.SEVERE, null, ex);
 } catch (IllegalAccessException ex) {
 Logger.getLogger(BeginSystem.class.getName()).log(Level.SEVERE, null, ex);
 } catch (UnsupportedLookAndFeelException ex) {

 }
 System.currentTimeMillis();
 }      
       
 }*/



//LIBRERIA TATOO FORMA 1 
/*try {
 UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
 GUISplash lib = new GUISplash();
 lib.setLocationRelativeTo(null);
 lib.setVisible(true);
 } catch (ClassNotFoundException ex) {
 java.util.logging.Logger.getLogger(BeginSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

 } catch (InstantiationException iex) {
 java.util.logging.Logger.getLogger(BeginSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, iex);

 } catch (IllegalAccessException iae) {
 java.util.logging.Logger.getLogger(BeginSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, iae);

 } catch (javax.swing.UnsupportedLookAndFeelException usex) {
 java.util.logging.Logger.getLogger(BeginSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, usex);
 }
 }
 }*/
    
 //SEGUNDA FORMA
 /*try {
 // PlasticLookAndFeel.setPlasticTheme(new BrownSugar());//1
 Plastic3DLookAndFeel.setPlasticTheme(new BrownSugar());//2
 UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticLookAndFeel");
 GUISplash lib = new GUISplash();
 lib.setLocationRelativeTo(null);
 lib.setVisible(true);
 } catch (ClassNotFoundException ex) {
 Logger.getLogger(BeginSystem.class.getName()).log(Level.SEVERE, null, ex);
 } catch (InstantiationException ex) {
 Logger.getLogger(BeginSystem.class.getName()).log(Level.SEVERE, null, ex);
 } catch (IllegalAccessException ex) {
 Logger.getLogger(BeginSystem.class.getName()).log(Level.SEVERE, null, ex);
 } catch (UnsupportedLookAndFeelException ex) {
 }
 }
 }*/
//TERCERA FORMA

/*try {
            
 UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
 GUISplash lib = new GUISplash();
 lib.setLocationRelativeTo(null);
 lib.setVisible(true);
 } catch (ClassNotFoundException ex) {
 Logger.getLogger(BeginSystem.class.getName()).log(Level.SEVERE, null, ex);
 } catch (InstantiationException ex) {
 Logger.getLogger(BeginSystem.class.getName()).log(Level.SEVERE, null, ex);
 } catch (IllegalAccessException ex) {
 Logger.getLogger(BeginSystem.class.getName()).log(Level.SEVERE, null, ex);
 } catch (UnsupportedLookAndFeelException ex) {
 }
 }
 }*/

//Skins, Theme, Current Watermark
       /* try {
 UIManager.LookAndFeelInfo[] lista = UIManager.getInstalledLookAndFeels();
 for (int i = 0; i < lista.length; i++) {
 System.out.println(lista[i].getClassName());
 }
 GUISplash.setDefaultLookAndFeelDecorated(true);
 SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.OfficeBlue2007Skin");
 SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceLimeGreenTheme");
 SubstanceLookAndFeel.setCurrentWatermark("org.jvnet.substance.watermark.SubstanceLatchWatermark");
 SubstanceLookAndFeel.setCurrentWatermark(new SubstanceImageWatermark("src\\Imagenes\\Inventarios.png"));
 SubstanceLookAndFeel.setImageWatermarkOpacity(new Float(0.9));

 } catch (Exception e) {
 JOptionPane.showMessageDialog(null, "No se pudo cargar el skin " + e);
 }
 try {
 GUIMenuPrincipal acc = new GUIMenuPrincipal();
 acc.setLocationRelativeTo(null);
 acc.setVisible(true);
 } catch (HeadlessException e) {
 Mensajes.error("Error al cargar...Intentelo de nuevo " + e.toString());
 System.exit(0);
 }
 }
 }*/
       
       
//LIBRERIA BEAUTYEYE
   /* String s = "";

    s = "org.jb2011.lnf.beautyeye.BeautyEyeLookAndFeelCross";

 try {
    javax.swing.UIManager.setLookAndFeel(s);
    GUISplash acc = new GUISplash();
    acc.setLocationRelativeTo(null);
    acc.setVisible(true);
    } catch (ClassNotFoundException ex) {
    Logger.getLogger(BeginSystem.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
 Logger.getLogger(BeginSystem.class.getName()).log(Level.SEVERE, null, ex);
 } catch (IllegalAccessException ex) {
 Logger.getLogger(BeginSystem.class.getName()).log(Level.SEVERE, null, ex);
 } catch (UnsupportedLookAndFeelException ex) {
 Logger.getLogger(BeginSystem.class.getName()).log(Level.SEVERE, null, ex);
 }
 }
 }
*/