/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genericos;

import java.io.InputStream;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
//import ConexionTALLERCASAJC;

/**
 *
 * @author user
 */
public class GestorReportes {
    
    public GestorReportes(){
        ConexionTALLERCASAJC.getRutaConexion();
    }
    
public String verReporte(String nombreJasper) {
        try {
            String urlPdf = "C:\\TemporalPDF";
            InputStream reporte = GestorReportes.class.getResourceAsStream("/Reportes/"+nombreJasper+".jasper");
            JasperPrint print = JasperFillManager.fillReport(reporte, new HashMap<>(), ConexionTALLERCASAJC.getRutaConexion());
            JasperExportManager.exportReportToPdfFile(print, urlPdf + "/" + nombreJasper + ".pdf");
            return urlPdf + "/" + nombreJasper + ".pdf";
        } catch (JRException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public String verReporteParametros(String nombreJasper, HashMap <String, Object> parametros) {
        try {
            String urlPdf = "C:\\TemporalPDF";
            InputStream reporte = GestorReportes.class.getResourceAsStream("/Reportes/"+nombreJasper+".jasper");
            JasperPrint print = JasperFillManager.fillReport(reporte, parametros, ConexionTALLERCASAJC.getRutaConexion());
            JasperExportManager.exportReportToPdfFile(print, urlPdf + "/" + nombreJasper + ".pdf");
            return urlPdf + "/" + nombreJasper + ".pdf";
        } catch (JRException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    
}
