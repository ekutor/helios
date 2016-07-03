package com.co.hsg.innventa.converter;


import com.co.hsg.innventa.backing.util.JsfUtil;
import com.co.hsg.innventa.beans.Estados;
import com.co.hsg.innventa.session.EstadosFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;


@Named(value = "estadosConverter")
@RequestScoped
public class EstadosConverter implements Converter {

    @Inject
    private EstadosFacade ejbFacade;


    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 ) {
            return null;
        }
        Object obj = this.ejbFacade.find(value);
        return obj;
    }


    String getStringKey(java.lang.String value) {
        StringBuffer sb = new StringBuffer();
            sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null || 
            (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof Estados) {
            Estados o = (Estados) object;
            return getStringKey(o.getId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Estados.class.getName()});
            return null;
        }
    }

}
