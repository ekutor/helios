package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Parametros;
import com.co.hsg.innventa.session.NamedQuerys;
import java.util.Collection;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "parametrosController")
@ViewScoped
public class ParametrosController extends AbstractController<Parametros> {

    @Inject
    private MobilePageController mobilePageController;

    public ParametrosController() {
        // Inform the Abstract parent controller of the concrete Parametros Entity
        super(Parametros.class);
    }
    
    public void cargarObj(String param) {
       
        System.out.println("parametro : "+param);
        try{
            switch(param){
                case "purchaseOrder":
                    this.chargeItem(NamedQuerys.ORDER_PARAM);
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

    
    public void save(ActionEvent event, String param) {
        this.selected.setParametro(param);
        super.save(event);
    }
    
    

}
