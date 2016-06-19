package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Personas;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "personasController")
@ViewScoped
public class PersonasController extends AbstractController<Personas> {

    @Inject
    private MobilePageController mobilePageController;
    private String attrib;
    
    public PersonasController() {
        // Inform the Abstract parent controller of the concrete Personas Entity
        super(Personas.class);
    }

    /**
     * Sets the "items" attribute with a collection of CuentasContactos entities
     * that are retrieved from Personas?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for CuentasContactos page
     */
    public String navigateCuentasContactosList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CuentasContactos_items", this.getSelected().getCuenta());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/cuentasContactos/index";
    }

    /**
     * Sets the "items" attribute with a collection of Usuarios entities that
     * are retrieved from Personas?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Usuarios page
     */
    public String navigateUsuariosList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Usuarios_items", this.getSelected().getUsuariosList());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/usuarios/index";
    }

    public String getAttrib() {
        return attrib;
    }

    public void setAttrib(String attrib) {
        this.attrib = attrib;
    }
    
    
}
