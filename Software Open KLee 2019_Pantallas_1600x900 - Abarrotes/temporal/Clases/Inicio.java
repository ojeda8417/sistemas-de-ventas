/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import GUInterfaces.GUISplashDesvanecedor;
import java.awt.HeadlessException;
import javax.swing.UIManager;

/**
 *
 * @author USUARIO
 */
public class Inicio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    /*    try {
                  //org.jvnet.substance.skin.MistAquaSkin
                  //org.jvnet.substance.skin.OfficeSilver2007Skin
                  //org.jvnet.substance.skin.CremeCoffeeSkin
                  //com.lipstikLF.LipstikLookAndFeel
                  GUISplash.setDefaultLookAndFeelDecorated(true);
            SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.OfficeSilver2007Skin");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar el skin " + e);
        }
        
        try {       
             
            GUISplash acc = new GUISplash();
            acc.setLocationRelativeTo(null);
            acc.setVisible(true);
        } catch (HeadlessException e) {
            Mensajes.error("Error al cargar...Intentelo de nuevo " + e.toString());
            System.exit(0);
        }
    }
} */
    
try{ 
            UIManager.setLookAndFeel(new com.lipstikLF.LipstikLookAndFeel()); 
            }
       catch (Exception e){
            e.printStackTrace();
        }
try {
//            SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.MagmaSkin");
//            SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceCharcoalTheme");
//            SubstanceLookAndFeel.setCurrentWatermark("org.jvnet.substance.watermark.SubstanceWoodWatermark");

            GUISplashDesvanecedor acc = new GUISplashDesvanecedor();
            acc.setLocationRelativeTo(null);
            acc.setVisible(true);
        } catch (HeadlessException e) {
            Mensajes.error("Error al cargar...Intentelo de nuevo " + e.toString());
            System.exit(0);
        }
    }
}
