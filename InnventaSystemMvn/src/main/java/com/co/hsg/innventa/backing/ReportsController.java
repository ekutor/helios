package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.*;
import com.co.hsg.innventa.session.AbstractFacade;
import com.co.hsg.innventa.session.NamedQuerys;
import java.util.Collection;
import java.util.List;


import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "reportsController")
@ViewScoped
public class ReportsController extends AbstractController<Parametros> {

    @Inject
    private MobilePageController mobilePageController;
    @Inject 
    private Navigation nav;
    
    private NamedQuerys actualParam;
    
    private String confTitle;
    private String confMessage;
    
    @Inject
    protected AbstractFacade<Remisiones> remisionsFacade;
    @Inject
    protected AbstractFacade<Pedidos> pedidosFacade;
          
    
    public ReportsController() {
        // Inform the Abstract parent controller of the concrete Parametros Entity
        super(Parametros.class);
    }
     
    public void cargarObj(Modules param) {
       
        selected = null;
        actualModule = param;
        try{
            switch(param){
                case ORDERS:
                    confTitle = "Informes de Ordenes de Compra";
                    this.confMessage = "";             
                    break;
                 case REMISSIONS:
                     confTitle = "Informes de Remisiones";
                     this.confMessage = "Defina el Consecutivo actual de sus Remisiones, el formato es: RM0001";
                     this.actualParam = NamedQuerys.REMISSION_PARAM;
                    this.chargeItem(NamedQuerys.REMISSION_PARAM, true);
                    
                    break;
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
     public List<Pedidos> chargeInfoOrders() {
        return this.pedidosFacade.findAllByQuery("Pedidos.findAll");
     }
    
    public void save() {
        this.selected.setParametro(actualParam.getParamValue());
        super.save(null);
        nav.init();
    }
    
     public void save(NamedQuerys param) {
        this.selected.setParametro(param.getParamValue());
        super.save(null);
    }

    public String getConfTitle() {
        return confTitle;
    }

    public void setConfTitle(String confTitle) {
        this.confTitle = confTitle;
    }

    public String getConfMessage() {
        return confMessage;
    }

    public void setConfMessage(String confMessage) {
        this.confMessage = confMessage;
    }
    
    

}
