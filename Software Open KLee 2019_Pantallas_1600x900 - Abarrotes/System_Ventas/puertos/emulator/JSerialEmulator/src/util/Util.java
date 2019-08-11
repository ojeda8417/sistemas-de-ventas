/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.port.PortSettings;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Rene Quintero
 */
public class Util {

    public static AppSettings getAppSettings() throws Exception {

        AppSettings pt = null;

        try {
            String path = System.getProperty("user.dir");
            String cad = Util.readFile(path + File.separatorChar + "portSettings.json");

            pt = AppSettings.unserializeFromString(cad);

            return pt;
        } catch (IOException ioEx) {
            pt = new AppSettings();

            PortSettings psets = new PortSettings();
            psets.setPortName("COM1");
            psets.setBauds("9600");
            psets.setDataBits("8");
            psets.setParity("0");
            psets.setStopBits("1");
            psets.setFlowControl("1");
            psets.setIsContinuousReading("false");
            psets.setReadingMode("0");

            pt.setPortSettings(psets);
            Util.saveAppSettings(pt);
        }

        return pt;
    }

    public static void saveAppSettings(AppSettings portSettings) throws Exception {
        String archivo = System.getProperty("user.dir");
        byte[] cad = AppSettings.serializeToString(portSettings).getBytes();
        saveFile(archivo + File.separatorChar+ "portSettings.json", cad);
    }

    public static void saveFile(String sFile, byte[] bytes) throws Exception {

        //BufferedWriter bw = new BufferedWriter(new FileWriter(sFile));
        FileOutputStream fileOut = new FileOutputStream(sFile);
        BufferedOutputStream buffer = new BufferedOutputStream(fileOut);
        try {
            buffer.write(bytes);
            buffer.flush();
            buffer.close();
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }
    }

    private static String readFile(String sFile) throws Exception {
        StringBuilder sb = new StringBuilder();
        String cadena;
        FileReader f = new FileReader(sFile);
        BufferedReader b = new BufferedReader(f);

        while ((cadena = b.readLine()) != null) {
            //System.out.println(cadena);
            sb.append(cadena);
        }
        b.close();

        return sb.toString();
    }
}
