package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.ClientesDireccion;
import com.co.hsg.innventa.session.ClientesDireccionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
@Named(value="clientesDireccionController")
@ViewScoped
public class ClientesDireccionController extends AbstractController<ClientesDireccion> {

    @Inject
    private ClientesDireccionFacade ejbFacade;
    @Inject
    private ClientesController idClienteController;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete ClientesDireccion controller bean.
     * The AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ClientesDireccionController() {
        // Inform the Abstract parent controller of the concrete ClientesDireccion Entity
        super(ClientesDireccion.class);
    }


    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idClienteController.setSelected(null);
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
