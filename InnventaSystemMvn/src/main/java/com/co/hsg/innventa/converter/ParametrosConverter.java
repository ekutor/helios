package com.co.hsg.innventa.converter;

import com.co.hsg.innventa.backing.ParametrosController;
import com.co.hsg.innventa.beans.Parametros;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named(value = "parametrosConverter")
@RequestScoped
public class ParametrosConverter implements Converter {

    @Inject
    private ParametrosController ejbController;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 ) {
            return null;
        }
        String finded = "";
        for(Parametros p : this.ejbController.getItems()){
            if(p.getClave1().equals(value)){
                finded = p.getId();
                break;
            }
        }
        return finded;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof Parametros) {
            Parametros o = (Parametros) object;
            return o.getId();
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Parametros.class.getName()});
            return null;
        }
    }
    
    public String getParamName(String id) {
        String finded = "";
        for(Parametros p : this.ejbController.getItems()){
            if(p.getId().equals("")){
                finded = p.getClave1();
                break;
            }
        }
        return finded;
    }

}
