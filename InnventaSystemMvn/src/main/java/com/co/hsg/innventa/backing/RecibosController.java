package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Recibos;
import com.co.hsg.innventa.session.RecibosFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "recibosController")
@ViewScoped
public class RecibosController extends AbstractController<Recibos> {

    @Inject
    private RecibosFacade ejbFacade;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete Recibos controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public RecibosController() {
        // Inform the Abstract parent controller of the concrete Recibos Entity
        super(Recibos.class);
    }

}
