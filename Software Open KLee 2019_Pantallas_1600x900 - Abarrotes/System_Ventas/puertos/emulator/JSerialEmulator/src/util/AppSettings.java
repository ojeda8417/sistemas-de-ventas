/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

//import com.google.gson.Gson;
import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;
import java.io.IOException;
import java.io.Serializable;
import com.port.PortSettings;

/**
 *
 * @author Rene
 */
public class AppSettings  implements Serializable {
    private PortSettings portSettings;
    private String pathFiles;
    private String stringToBeginReading;

    public static String serializeToString(AppSettings pSettings) throws IOException{
        String json = JsonWriter.objectToJson(pSettings);
        return json;
    }
    
    public static AppSettings unserializeFromString(String json) throws IOException, ClassNotFoundException{

        AppSettings settings = (AppSettings)JsonReader.jsonToJava(json);
        
        return settings;
    }
    /**
     * @return the portSettings
     */
    public PortSettings getPortSettings() {
        return portSettings;
    }

    /**
     * @param portSettings the portSettings to set
     */
    public void setPortSettings(PortSettings portSettings) {
        this.portSettings = portSettings;
    }

    /**
     * @return the pathFiles
     */
    public String getPathFiles() {
        return pathFiles;
    }

    /**
     * @param pathFiles the pathFiles to set
     */
    public void setPathFiles(String pathFiles) {
        this.pathFiles = pathFiles;
    }

    /**
     * @return the stringToBeginReading
     */
    public String getStringToBeginReading() {
        return stringToBeginReading;
    }

    /**
     * @param stringToBeginReading the stringToBeginReading to set
     */
    public void setStringToBeginReading(String stringToBeginReading) {
        this.stringToBeginReading = stringToBeginReading;
    }
}
