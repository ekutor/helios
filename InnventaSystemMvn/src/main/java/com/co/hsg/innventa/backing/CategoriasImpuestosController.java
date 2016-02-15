package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.CategoriasImpuestos;
import com.co.hsg.innventa.session.CategoriasImpuestosFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
@Named(value="categoriasImpuestosController")
@ViewScoped
public class CategoriasImpuestosController extends AbstractController<CategoriasImpuestos> {

    @Inject
    private CategoriasImpuestosFacade ejbFacade;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete CategoriasImpuestos controller bean.
     * The AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public CategoriasImpuestosController() {
        // Inform the Abstract parent controller of the concrete CategoriasImpuestos Entity
        super(CategoriasImpuestos.class);
    }



}
