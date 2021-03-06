package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Productos;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "productosController")
@ViewScoped
public class ProductosController extends AbstractController<Productos> {

    @Inject
    private MobilePageController mobilePageController;
    
    private Productos findProduct; 
 
    
    public ProductosController() {
        super(Productos.class);
    }

    public Productos getFindProduct() {
        return findProduct;
    }

    public void setFindProduct(Productos findProduct) {
        this.findProduct = findProduct;
    }
    
    
    /**
     * Sets the "items" attribute with a collection of PedidosProducto entities
     * that are retrieved from Productos?cap_first and returns the navigation
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
