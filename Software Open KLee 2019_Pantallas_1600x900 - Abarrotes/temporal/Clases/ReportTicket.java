/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JDialog;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author 
 */
public class ReportTicket {
  private conectar CON;

    public ReportTicket() {
    }


 public boolean verReporteExistente(String rutaJRXML){
        try {
            this.CON = new conectar();
            File reportFile = new File(rutaJRXML);
            JasperDesign design = JRXmlLoader.load(reportFile);
            JasperReport reporte = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, Conexion.conexion());
            JRViewer jrv = new JRViewer(jasperPrint);            
            this.verReporte(jrv);            
            return true;
        } catch (Exception e) {
            System.out.println("Error >> " + e.getMessage());
            return false;
        }
    }
    
    public boolean generarNuevoReporte(String rutaJRXML, HashMap<String, Object> parametros, ArrayList<ProductoTicket> data){
        try {
            File reportFile = new File(rutaJRXML);
            JasperDesign design = JRXmlLoader.load(reportFile);
            JasperReport reporte = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(data));
            JRViewer jrv = new JRViewer(jasperPrint);  
            this.verReporte(jrv);
            return true;
        } catch (Exception e) {
            System.out.println("Error >> " + e.getMessage());
            return false;
        }
    }

    private void verReporte(JRViewer jrv) {
        JDialog report = new JDialog();
        report.setTitle("Impresión de Ticket");
        report.setSize(900, 700);
        report.setLocationRelativeTo(null);
        report.getContentPane().add(jrv);
        report.setVisible(true);
    }
    
    public static void main(String[] args) {
        ReportFactura generar = new ReportFactura();
        if(generar.verReporteExistente("src\\Reportes\\Ticket.jrxml")){
            System.out.println(" ");//Reporte Generado Correctamente
        }else{
            System.out.println("Hubo un error al generar el reporte");
        }
    }
}