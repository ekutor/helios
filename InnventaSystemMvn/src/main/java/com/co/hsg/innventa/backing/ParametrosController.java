package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Parametros;
import com.co.hsg.innventa.session.NamedQuerys;
import java.util.Collection;

import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "parametrosController")
@ViewScoped
public class ParametrosController extends AbstractController<Parametros> {

    @Inject
    private MobilePageController mobilePageController;
    private NamedQuerys actualParam;
    
    private String confTitle;
    private String confMessage;
    
    public ParametrosController() {
        // Inform the Abstract parent controller of the concrete Parametros Entity
        super(Parametros.class);
    }
    
    public void cargarObj(String param) {
       
        System.out.println("parametro : "+param);
        selected = null;
        try{
            switch(param){
                case "purchaseOrder":
                    
                    confTitle = "Parametrizacion de Ordenes de Compra";
                    this.confMessage = "Defina el Consecutivo actual de sus Ordenes de Compra, el formato es: OC0001";
                    this.actualParam = NamedQuerys.ORDER_PARAM;
                    this.chargeItem(NamedQuerys.ORDER_PARAM, true);
                    
                    break;
                 case "remissions":
                     confTitle = "Parametrizacion de Remisiones";
                     this.confMessage = "Defina el Consecutivo actual de sus Remisiones, el formato es: RM0001";
                     this.actualParam = NamedQuerys.REMISSION_PARAM;
                    this.chargeItem(NamedQuerys.REMISSION_PARAM, true);
                    
                    break;
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public Collection<Parametros> chargeListObject(String paramId) {
        return chargeListObject(paramId, null);
    }
    public Collection<Parametros> chargeListObject(String paramId, String param) {
  
        try{
             switch(paramId){
                case "productTypes":
                    return this.chargeItems(NamedQuerys.PRODUCT_TYPES_PARAM);
                case "PARAMS":
                    return this.chargeItems(NamedQuerys.PARAMS, "parametro" , param );
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    
    public void save(ActionEvent event) {
        this.selected.setParametro(actualParam.getParamValue());
        super.save(event);
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
