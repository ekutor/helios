package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.beans.Remisiones;
import com.co.hsg.innventa.beans.RemisionesProducto;
import com.co.hsg.innventa.beans.ReportInfo;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author hectsaga
 */
@Named(value = "reportController")
@ViewScoped
public class ReportController implements Serializable{

    public void print(Remisiones selected) throws JRException {
        try {
            JasperPrint jasperPrint;
            List<ReportInfo> info = this.generateInfo(selected);
            
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            
            String relativeWebPath = "/resources/formats/RemissionFormat.jasper" ;
                ServletContext servletContext = (ServletContext) ec.getContext();
                String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
                File file = new File(".");
                String f = file.getAbsolutePath();
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(info);
            jasperPrint = JasperFillManager.fillReport("C:\\GitRepos\\helios\\InnventaSystemMvn\\src\\main\\resources\\formats\\RemissionFormat.jasper", new HashMap(), beanCollectionDataSource);
            
            
            HttpServletResponse response = (HttpServletResponse) ec.getResponse();
            response.reset();
            response.setHeader("Content-Type", "application/pdf");
            // response.setHeader("Content-Length", String.valueOf(file.length()));
            response.setHeader("Content-Disposition", "inline; filename=\"report.pdf\"");
            BufferedOutputStream  output = new BufferedOutputStream(response.getOutputStream(), 10240);
            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            fc.responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private List<ReportInfo> generateInfo(Remisiones selected) {
        List<ReportInfo> list = new ArrayList<ReportInfo>();
        if (selected != null) {
            for (RemisionesProducto rem : selected.getRemisionesProductoList()) {
                ReportInfo ri = new ReportInfo();
                ri.setCantidad(rem.getCantidad());
                ri.setNombre(rem.getIdProducto().getNombre());
                ri.setObservaciones(selected.getDetalles());
                list.add(ri);
            }
        }
        return list;
    }

}
