package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Pagos;
import com.co.hsg.innventa.session.PagosFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
@Named(value="pagosController")
@ViewScoped
public class PagosController extends AbstractController<Pagos> {

    @Inject
    private PagosFacade ejbFacade;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete Pagos controller bean.
     * The AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public PagosController() {
        // Inform the Abstract parent controller of the concrete Pagos Entity
        super(Pagos.class);
    }



}
