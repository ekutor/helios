package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Pedidos;
import com.co.hsg.innventa.session.PedidosFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
@Named(value="pedidosController")
@ViewScoped
public class PedidosController extends AbstractController<Pedidos> {

    @Inject
    private PedidosFacade ejbFacade;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete Pedidos controller bean.
     * The AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public PedidosController() {
        // Inform the Abstract parent controller of the concrete Pedidos Entity
        super(Pedidos.class);
    }



    /**
    * Sets the "items" attribute with a collection of Remisiones entities that are retrieved from Pedidos?cap_first
     * and returns the navigation outcome.
     *
     * @return  navigation outcome for Remisiones page
     */
    public String navigateRemisionesCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Remisiones_items", this.getSelected().getRemisionesCollection());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/remisiones/index";
    }

}
