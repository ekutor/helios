package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.ClienteContactos;
import com.co.hsg.innventa.session.ClienteContactosFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
@Named(value="clienteContactosController")
@ViewScoped
public class ClienteContactosController extends AbstractController<ClienteContactos> {

    @Inject
    private ClienteContactosFacade ejbFacade;
    @Inject
    private PersonasController idPersonaController;
    @Inject
    private ClientesController idClienteController;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete ClienteContactos controller bean.
     * The AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ClienteContactosController() {
        // Inform the Abstract parent controller of the concrete ClienteContactos Entity
        super(ClienteContactos.class);
    }


    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idPersonaController.setSelected(null);
        idClienteController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Personas controller
     * in order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdPersona(ActionEvent event) {
        if (this.getSelected() != null && idPersonaController.getSelected() == null) {
            idPersonaController.setSelected(this.getSelected().getIdPersona());
        }
    }
    /**
     * Sets the "selected" attribute of the Clientes controller
     * in order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdCliente(ActionEvent event) {
        if (this.getSelected() != null && idClienteController.getSelected() == null) {
            idClienteController.setSelected(this.getSelected().getIdCliente());
        }
    }
}
