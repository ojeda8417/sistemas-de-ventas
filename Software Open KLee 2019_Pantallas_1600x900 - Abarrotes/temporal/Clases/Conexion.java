/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael García A
 */
public class Conexion {

    private static String JDBC_DRIVER;
    private static String JDBC_URL;
    private static String JDBC_USER;
    private static String JDBC_PASSWORD;
    private static final String JDBC_FILE_NAME = "jdbc.properties";
    private static Connection con = null;
//    public static Properties loadProperties(String file){
//
//        return propiedades;
//    }

    public static Connection conexion()  {

        Properties propiedades = new Properties();

        try {
            if (con == null) {
                propiedades.load(new FileInputStream(JDBC_FILE_NAME));
                JDBC_DRIVER = propiedades.getProperty("driver");
                JDBC_USER = propiedades.getProperty("user");
                JDBC_URL = propiedades.getProperty("url");
                JDBC_PASSWORD = propiedades.getProperty("password");
                Class.forName(JDBC_DRIVER);
                con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        return con;
    }

    public static void close(ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
    }

    public static void closeConexion(Connection cn) throws SQLException {
        if (cn != null) {
            cn.close();

        }
    }

}
