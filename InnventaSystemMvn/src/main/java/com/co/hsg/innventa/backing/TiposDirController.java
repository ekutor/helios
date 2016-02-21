package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.TiposDir;
import com.co.hsg.innventa.session.TiposDirFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "tiposDirController")
@ViewScoped
public class TiposDirController extends AbstractController<TiposDir> {

    @Inject
    private TiposDirFacade ejbFacade;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete TiposDir controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TiposDirController() {
        // Inform the Abstract parent controller of the concrete TiposDir Entity
        super(TiposDir.class);
    }

}
