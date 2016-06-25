package com.co.hsg.innventa.backing.reports;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author hectsaga
 */
public class ReportGenerator {
    
    public boolean generate( TypeReport type, ReportParams reportInfo){
        
     boolean resp = false;
     try {
       
       List data = new ArrayList();
       //AGREGAR PARAMETROS Y DS AL REPORTE
      
       JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(data);
       
       String pathTarget = "";//FileManager.getInst().leerPropiedad("RUTA_TEMP");
 
       switch (type) {
       case JASPER:
         compileJasper(pathTarget, reportInfo);
         break;
       case JRXML:
         compileJRXML(pathTarget, reportInfo);
         break;
       }
 
     
 
      
       resp = true;
     } catch (Exception e) {
  
     }
 
     return resp;
   }
 
 
   private void compileJRXML(String pathTarget, ReportParams reportInfo) throws Exception
   {
     JasperDesign jd = JRXmlLoader.load(pathTarget + reportInfo.getFileName());
 
     JasperReport report = JasperCompileManager.compileReport(jd);
 
     JasperPrint print = JasperFillManager.fillReport(report, reportInfo.getParams(), reportInfo.getDatasourcePpal());
 
     JRPdfExporter exporter = new JRPdfExporter();
     pathTarget = pathTarget + "/PruebaChart.pdf";
     ByteArrayOutputStream baos = new ByteArrayOutputStream();
     try
     {
       exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, 
         "UTF-8");
       exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
       exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
       exporter.exportReport();
 
       File f = new File(pathTarget);
       FileOutputStream fos = new FileOutputStream(f);
       fos.write(baos.toByteArray());
       fos.flush();
       fos.close();
       
     } catch (Exception e) {
     
     }
 
     
   }
 
   private void compileJasper(String pathTarget, ReportParams reportInfo) throws Exception
   {
     String filePath = pathTarget + reportInfo.getFileName();
 
     JasperPrint jp = JasperFillManager.fillReport(reportInfo.getJasperFile(), 
     reportInfo.getParams(), reportInfo.getDatasourcePpal());
    
 
     JRPdfExporter exporter = new JRPdfExporter();
 
     ByteArrayOutputStream baos = new ByteArrayOutputStream();
 
     exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, 
       "UTF-8");
     exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
     exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
     exporter.exportReport();
 
     File f = new File(pathTarget, reportInfo.getReportID());
 
     FileOutputStream fos = new FileOutputStream(f);
 
     fos.write(baos.toByteArray());
    
     fos.flush();
     fos.close();
 
   }

 
   public static enum TypeReport
   {
     JASPER, JRXML;
   }
}
