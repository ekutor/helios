package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Pedidos;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "pedidosController")
@ViewScoped
public class PedidosController extends AbstractController<Pedidos> {

    @Inject
    private PersonasController idClienteController;
    @Inject
    private MobilePageController mobilePageController;

    public PedidosController() {
        // Inform the Abstract parent controller of the concrete Pedidos Entity
        super(Pedidos.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idClienteController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Personas controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdCliente(ActionEvent event) {
        if (this.getSelected() != null && idClienteController.getSelected() == null) {
            idClienteController.setSelected(this.getSelected().getIdCliente());
        }
    }

    /**
     * Sets the "items" attribute with a collection of Remisiones entities that
     * are retrieved from Pedidos?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Remisiones page
     */
    public String navigateRemisionesList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Remisiones_items", this.getSelected().getRemisionesList());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/remisiones/index";
    }

    /**
     * Sets the "items" attribute with a collection of PedidosProducto entities
     * that are retrieved from Pedidos?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for PedidosProducto page
     */
    public String navigatePedidosProductoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("PedidosProducto_items", this.getSelected().getPedidosProductoList());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/pedidosProducto/index";
    }
    
    

}
