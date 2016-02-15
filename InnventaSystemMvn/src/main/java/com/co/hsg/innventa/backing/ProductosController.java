package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Productos;
import com.co.hsg.innventa.session.ProductosFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
@Named(value="productosController")
@ViewScoped
public class ProductosController extends AbstractController<Productos> {

    @Inject
    private ProductosFacade ejbFacade;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete Productos controller bean.
     * The AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ProductosController() {
        // Inform the Abstract parent controller of the concrete Productos Entity
        super(Productos.class);
    }



}
