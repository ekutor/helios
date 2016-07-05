package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.Utils;
import com.co.hsg.innventa.beans.Personas;
import com.co.hsg.innventa.beans.Remisiones;
import com.co.hsg.innventa.beans.RemisionesProducto;
import com.co.hsg.innventa.beans.ReportInfo;
import java.io.BufferedOutputStream;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("/formats/RemissionFormat.jasper");
            Map<String, Object> params = new HashMap<>();
            String  pathLogo = Thread.currentThread().getContextClassLoader().getResource("/formats/logo.jpg").getFile();
     
            params.put("LOGO", pathLogo);
           
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(info);
            jasperPrint = JasperFillManager.fillReport(is, params, beanCollectionDataSource);
            
            
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
        List<ReportInfo> list = new ArrayList<>();
        if (selected != null) {
            for (RemisionesProducto rem : selected.getRemisionesProductoList()) {
                ReportInfo ri = new ReportInfo();
                
                ri.setCantidad(rem.getCantidad());
                ri.setNombre(rem.getIdProducto().getNombre());
                
                ri.setNombreCliente(selected.getIdPedido().getIdCliente().getNombre());
                ri.setDirCliente(selected.getIdPedido().getIdCliente().getDireccionPpal());
                ri.setTelCliente(selected.getIdPedido().getIdCliente().getTelefonoPpal());
                try{
                    Personas contact = selected.getIdPedido().getIdCliente().getPersona().get(0).getPersona();
                    String pre = ("F".equals(contact.getSexo()))?"Sra. ":"Sr.  ";
                    ri.setContacto(contact.getNombre1()+" "+contact.getApellido1());
                    ri.setDespachador(selected.getCreadoPor().getNombre1()+" "+selected.getCreadoPor().getApellido1());
                     ri.setCantTotal(String.valueOf(selected.getTotalProductos()));
                }catch(Exception e){
                    
                }
               
                ri.setNitCliente("Nit: "+selected.getIdPedido().getIdCliente().getId());
                ri.setNumOrden(selected.getIdPedido().getReferencia());
                ri.setFechaEntrega(Utils.getFormattedDate(selected.getFechaRemision()));
                ri.setReferencia(selected.getReferencia());
                ri.setObservaciones(selected.getObservaciones());
               
                
                list.add(ri);
            }
        }
        return list;
    }

}
