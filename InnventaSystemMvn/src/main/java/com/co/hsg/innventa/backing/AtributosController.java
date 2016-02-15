package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Atributos;
import com.co.hsg.innventa.session.AtributosFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
@Named(value="atributosController")
@ViewScoped
public class AtributosController extends AbstractController<Atributos> {

    @Inject
    private AtributosFacade ejbFacade;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete Atributos controller bean.
     * The AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public AtributosController() {
        // Inform the Abstract parent controller of the concrete Atributos Entity
        super(Atributos.class);
    }



}
