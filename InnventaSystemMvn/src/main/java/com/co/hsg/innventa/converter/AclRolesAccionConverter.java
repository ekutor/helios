package com.co.hsg.innventa.converter;

import com.co.hsg.innventa.beans.AclRolesAccion;
import com.co.hsg.innventa.session.AclRolesAccionFacade;
import com.co.hsg.innventa.backing.util.Permissions;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named(value = "aclRolesAccionConverter")
@RequestScoped
public class AclRolesAccionConverter implements Converter {

    @Inject
    private AclRolesAccionFacade ejbFacade;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return 0;
        }
        Permissions p = Permissions.getValue(value);
        return p.value;
    }


    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null){
            return null;
        }
        if (object instanceof Short) {
            Permissions p = Permissions.getAction((Short) object);
            return p.visualName;
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AclRolesAccion.class.getName()});
            return null;
        }
    }

}
