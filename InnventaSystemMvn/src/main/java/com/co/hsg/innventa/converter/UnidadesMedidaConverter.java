package com.co.hsg.innventa.converter;

import com.co.hsg.innventa.backing.util.JsfUtil;
import com.co.hsg.innventa.beans.UnidadesMedida;
import com.co.hsg.innventa.session.UnidadesMedidaFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named(value = "unidadesMedidaConverter")
@RequestScoped
public class UnidadesMedidaConverter implements Converter {

    @Inject
    private UnidadesMedidaFacade ejbFacade;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        Object p = this.ejbFacade.find(getKey(value));
        return p;
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
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof UnidadesMedida) {
            UnidadesMedida o = (UnidadesMedida) object;
            return o.getId();
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UnidadesMedida.class.getName()});
            return null;
        }
    }

}
