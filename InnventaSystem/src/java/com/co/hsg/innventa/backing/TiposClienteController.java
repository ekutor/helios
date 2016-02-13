package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.TiposCliente;
import com.co.hsg.innventa.session.TiposClienteFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
@Named(value="tiposClienteController")
@ViewScoped
public class TiposClienteController extends AbstractController<TiposCliente> {

    @Inject
    private TiposClienteFacade ejbFacade;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete TiposCliente controller bean.
     * The AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TiposClienteController() {
        // Inform the Abstract parent controller of the concrete TiposCliente Entity
        super(TiposCliente.class);
    }



}
