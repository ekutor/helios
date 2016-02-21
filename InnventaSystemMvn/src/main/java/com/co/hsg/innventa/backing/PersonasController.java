package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Personas;
import com.co.hsg.innventa.session.PersonasFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "personasController")
@ViewScoped
public class PersonasController extends AbstractController<Personas> {

    @Inject
    private PersonasFacade ejbFacade;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete Personas controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public PersonasController() {
        // Inform the Abstract parent controller of the concrete Personas Entity
        super(Personas.class);
    }

    /**
     * Sets the "items" attribute with a collection of ClienteContactos entities
     * that are retrieved from Personas?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for ClienteContactos page
     */
    public String navigateClienteContactosList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ClienteContactos_items", this.getSelected().getClienteContactosList());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/clienteContactos/index";
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

}
