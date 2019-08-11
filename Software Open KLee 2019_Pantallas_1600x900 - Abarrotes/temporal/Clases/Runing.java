/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import GUInterfaces.GUISplash;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import org.jvnet.substance.SubstanceLookAndFeel;

/**
 *
 * @author RAUL
 */
public class Runing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

           GUISplash.setDefaultLookAndFeelDecorated(false);
           SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.OfficeSilver2007Skin"); 
           SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceSunsetTheme");
           SubstanceLookAndFeel.setCurrentWatermark("org.jvnet.substance.watermark.SubstanceBubblesWatermark");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar el skin " + e);
        }
        try {

            GUISplash acceso = new GUISplash();
            acceso.setLocationRelativeTo(null);
            acceso.setVisible(true);
        } catch (HeadlessException e) {
            Mensajes.error("Error al cargar...Intentelo de nuevo " + e.toString());
            System.exit(0);
        }
    }

}     
