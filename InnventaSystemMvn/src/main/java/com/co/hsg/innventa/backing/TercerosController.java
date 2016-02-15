package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Terceros;
import com.co.hsg.innventa.session.TercerosFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
@Named(value="tercerosController")
@ViewScoped
public class TercerosController extends AbstractController<Terceros> {

    @Inject
    private TercerosFacade ejbFacade;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete Terceros controller bean.
     * The AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TercerosController() {
        // Inform the Abstract parent controller of the concrete Terceros Entity
        super(Terceros.class);
    }



}
