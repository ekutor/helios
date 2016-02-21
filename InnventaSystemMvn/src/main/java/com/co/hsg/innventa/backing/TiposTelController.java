package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.TiposTel;
import com.co.hsg.innventa.session.TiposTelFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "tiposTelController")
@ViewScoped
public class TiposTelController extends AbstractController<TiposTel> {

    @Inject
    private TiposTelFacade ejbFacade;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete TiposTel controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TiposTelController() {
        // Inform the Abstract parent controller of the concrete TiposTel Entity
        super(TiposTel.class);
    }

}
