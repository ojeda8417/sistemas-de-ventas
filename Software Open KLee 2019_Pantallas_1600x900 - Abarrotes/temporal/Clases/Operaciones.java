/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases;


import java.sql.ResultSet;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author NETFEX
 */
public class Operaciones {
    
    Conexion conexion =new Conexion();
    conectar c = new conectar();
    Statement st;
    ResultSet res;

    public DefaultComboBoxModel getLista(String cadenaEscrita) throws IOException{

        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {

           String query = "SELECT producto FROM tabla_productos WHERE producto LIKE '" + cadenaEscrita + "%';";
            st = Conexion.conexion().createStatement();
            
            res = (ResultSet) st.executeQuery(query);
            while (res.next()) {
                modelo.addElement(res.getString("producto"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

     return modelo;

    }

    public String[] buscar(String nombre) throws IOException{

        String[] datos = new String[4];
        try {

            String query = "SELECT producto FROM tabla_productos WHERE producto='" + nombre + "'";
            st = Conexion.conexion().createStatement();
            res = (ResultSet) st.executeQuery(query);
            while (res.next()) {
                for (int i = 0; i < datos.length; i++) {
                    datos[i] = res.getString(i + 1);
                }
            }
        } catch (SQLException ex) {
            return null;
        }
        return datos;
    }

}
