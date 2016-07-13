package com.co.hsg.innventa.converter;

import com.co.hsg.innventa.beans.Terceros;
import com.co.hsg.innventa.session.TercerosFacade;
import com.co.hsg.innventa.backing.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;


@Named(value = "tercerosConverter")
@RequestScoped
public class TercerosConverter implements Converter {

    @Inject
    private TercerosFacade ejbFacade;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(value);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null){
            return null;
        }else if (object instanceof String){
                return (String) object;
        }else if (object instanceof Terceros) {
            Terceros o = (Terceros) object;
            return o.getId();
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Terceros.class.getName()});
            return null;
        }
    }

}
