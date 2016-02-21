package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.AclAcciones;
import com.co.hsg.innventa.session.AclAccionesFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "aclAccionesController")
@ViewScoped
public class AclAccionesController extends AbstractController<AclAcciones> {

    @Inject
    private AclAccionesFacade ejbFacade;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete AclAcciones controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public AclAccionesController() {
        // Inform the Abstract parent controller of the concrete AclAcciones Entity
        super(AclAcciones.class);
    }

}
