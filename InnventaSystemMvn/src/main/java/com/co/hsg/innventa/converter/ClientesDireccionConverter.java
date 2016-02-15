package com.co.hsg.innventa.converter;

import com.co.hsg.innventa.beans.ClientesDireccion;
import com.co.hsg.innventa.session.ClientesDireccionFacade;
import com.co.hsg.innventa.backing.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


@FacesConverter(value = "clientesDireccionConverter")
public class ClientesDireccionConverter implements Converter {

    @Inject
    private ClientesDireccionFacade ejbFacade;


    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    java.lang.String getKey(String value) {
        java.lang.String key;
            key = value;
        return key;
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
        if (object instanceof ClientesDireccion) {
            ClientesDireccion o = (ClientesDireccion) object;
            return getStringKey(o.getId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ClientesDireccion.class.getName()});
            return null;
        }
    }

}
