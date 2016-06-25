package com.co.hsg.innventa.backing.reports;

 import java.util.HashMap;
 import java.util.Locale;
 import java.util.Map;
 import java.util.UUID;
 import net.sf.jasperreports.engine.JRAbstractScriptlet;
 import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
 import net.sf.jasperreports.engine.util.JRClassLoader;

/**
 *
 * @author hectsaga
 */
public class ReportParams {
   private JRAbstractScriptlet scriptlet;
   private Map<String, Object> params;
   private String fileName;
 
   private JRBeanCollectionDataSource datasourcePpal;

   private String jasperFile;
   private UUID uid;
 
   public ReportParams()
   {
     this.params = new HashMap();
 
     //this.params.put("LOGO", Util.getAbsolutePath(Constants.LOGO.getValue()));
     Locale locale = new Locale("es", "ES");
     this.params.put("REPORT_LOCALE", locale);
   }
 
   private void chargeScriptlet()
   {
     try
     {
       Class scriptletClass = 
         JRClassLoader.loadClassForName("com.co.hsg.generator.scriptlet.ReportUtilsScriptlet");
       this.scriptlet = ((JRAbstractScriptlet)scriptletClass.newInstance());
       this.params.put("REPORT_SCRIPTLET", this.scriptlet);
     
     } catch (Exception ex) {
      
     }
   }
 
   public Map<String, Object> getParams()
   {
     return this.params;
   }
 
   public void addParam(String llave, Object param)
   {
     this.params.put(llave, param);
   }
 
   public void setFile(String absolutePath) {
     this.fileName = absolutePath;
   }
 
   public String getFileName()
   {
     if (this.fileName == null) {
      // this.fileName = (this.report.name() + ".pdf");
     }
     return this.fileName;
   }
 
 
 
   public JRBeanCollectionDataSource getDatasourcePpal() {
     return this.datasourcePpal;
   }
 
   public void setDatasourcePpal(JRBeanCollectionDataSource datasourcePpal)
   {
     this.datasourcePpal = datasourcePpal;
   }
 
   public void setFileName(String fileName)
   {
     this.fileName = fileName;
   }
 

   public String getJasperFile()
   {
     return this.jasperFile;
   }
 
   public void setJasperFile(String jasperFile)
   {
     this.jasperFile = jasperFile;
   }
 
   public void generateUID()
   {
     UUID uuidRel = UUID.randomUUID();
     this.uid = uuidRel;
   }
 
   public String getReportID() {
     return ""+this.uid;
   }
}
