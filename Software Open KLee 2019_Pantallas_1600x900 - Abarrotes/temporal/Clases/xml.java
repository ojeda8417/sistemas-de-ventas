package Clases;
import java.io.*;
import java.util.*;
import java.sql.*;
import org.jdom.*;
import org.jdom.output.*;
/**
 * 
 * @author Net
 */
public class xml {

    //archivo destino
    private String file="I:\\Software Open KLee 2017\\System_Ventas\\src\\xml\\Fact.xml";
    /* DATOS PARA LA CONEXION */
    private String db = "system_ventas";
    private String user = "root";
    private String password = "";    
    private Connection conn = null;

    public xml(){
       conectar();
    }

    private void conectar()
   {
       String url = "jdbc:mysql://localhost/"+this.db;
       try{
         //obtenemos el driver de para mysql
         Class.forName("com.mysql.jdbc.Driver");
         //obtenemos la conexión
         conn = DriverManager.getConnection(url, this.user , this.password );
         if (conn!=null){
            System.out.println("OK base de datos "+this.db+" listo");
         }
      }catch(SQLException e){
         System.out.println(e);
      }catch(ClassNotFoundException e){
         System.out.println(e);
      }
   }

    public void getXML()
    {
        Document doc = new Document();
        //etiqueta principal
        Element root = new Element("DataBase");
        doc.setRootElement(root);
        try {
            //consulta
            String q = "SELECT * FROM tabla_ventas";//persona
            Statement stmt = conn.createStatement();
            ResultSet resultset = stmt.executeQuery(q);
            ResultSetMetaData resultmetadata = resultset.getMetaData();
            //obtiene la cantidad de columnas de la tabla
            int numCols = resultmetadata.getColumnCount();
            while (resultset.next()) {
                List elmts = new ArrayList();
                for (int i=1; i <= numCols; i++) {
                    //obtiene nombre de columna
                    String colName = resultmetadata.getColumnName(i);                    
                    //Obtiene el contendio de la celda
                    String colVal = resultset.getString(i);                    
                    //forma los elementos para el XML
                    Element elmt = new Element(colName);
                    elmt.setText(colVal);
                    elmts.add(elmt);
                }
                //para la etiqueta
                Element row = new Element("registro");
                //añade el registro en xml
                row.setContent(elmts);
                root.addContent(row);                
            }
            //cierra database
            resultset.close();
            stmt.close();
            conn.close();
            // Graba el archiv XML en disco
            XMLOutputter outputter = new XMLOutputter( Format.getPrettyFormat() );
            try {
               outputter.output(doc, new FileOutputStream ( file ));
               System.out.println("Arhivo XML creado en " + file);
            }
            catch (IOException e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
               System.err.println(e);
        }
    }
    
}
