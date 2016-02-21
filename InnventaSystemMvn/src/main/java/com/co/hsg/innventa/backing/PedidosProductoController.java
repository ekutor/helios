package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.PedidosProducto;
import com.co.hsg.innventa.session.PedidosProductoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "pedidosProductoController")
@ViewScoped
public class PedidosProductoController extends AbstractController<PedidosProducto> {

    @Inject
    private PedidosProductoFacade ejbFacade;
    @Inject
    private PedidosController idPedidoController;
    @Inject
    private ProductosController idProductoController;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete PedidosProducto controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public PedidosProductoController() {
        // Inform the Abstract parent controller of the concrete PedidosProducto Entity
        super(PedidosProducto.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idPedidoController.setSelected(null);
        idProductoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Pedidos controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdPedido(ActionEvent event) {
        if (this.getSelected() != null && idPedidoController.getSelected() == null) {
            idPedidoController.setSelected(this.getSelected().getIdPedido());
        }
    }

    /**
     * Sets the "selected" attribute of the Productos controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdProducto(ActionEvent event) {
        if (this.getSelected() != null && idProductoController.getSelected() == null) {
            idProductoController.setSelected(this.getSelected().getIdProducto());
        }
    }
}
