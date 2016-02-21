package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.LineasImpuestos;
import com.co.hsg.innventa.session.LineasImpuestosFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "lineasImpuestosController")
@ViewScoped
public class LineasImpuestosController extends AbstractController<LineasImpuestos> {

    @Inject
    private LineasImpuestosFacade ejbFacade;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete LineasImpuestos controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public LineasImpuestosController() {
        // Inform the Abstract parent controller of the concrete LineasImpuestos Entity
        super(LineasImpuestos.class);
    }

}
