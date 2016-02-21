package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Clientes;
import com.co.hsg.innventa.session.ClientesFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "clientesController")
@ViewScoped
public class ClientesController extends AbstractController<Clientes> {

    @Inject
    private ClientesFacade ejbFacade;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete Clientes controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ClientesController() {
        // Inform the Abstract parent controller of the concrete Clientes Entity
        super(Clientes.class);
    }

    /**
     * Sets the "items" attribute with a collection of ClientesDireccion
     * entities that are retrieved from Clientes?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for ClientesDireccion page
     */
    public String navigateClientesDireccionList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ClientesDireccion_items", this.getSelected().getClientesDireccionList());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/clientesDireccion/index";
    }

    /**
     * Sets the "items" attribute with a collection of ClienteContactos entities
     * that are retrieved from Clientes?cap_first and returns the navigation
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

}
