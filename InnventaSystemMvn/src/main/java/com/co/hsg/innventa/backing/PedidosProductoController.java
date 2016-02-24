package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.PedidosProducto;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "pedidosProductoController")
@ViewScoped
public class PedidosProductoController extends AbstractController<PedidosProducto> {

    @Inject
    private ProductosController idProductoController;
    @Inject
    private PedidosController idPedidoController;
    @Inject
    private MobilePageController mobilePageController;

    public PedidosProductoController() {
        // Inform the Abstract parent controller of the concrete PedidosProducto Entity
        super(PedidosProducto.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idProductoController.setSelected(null);
        idPedidoController.setSelected(null);
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
}
