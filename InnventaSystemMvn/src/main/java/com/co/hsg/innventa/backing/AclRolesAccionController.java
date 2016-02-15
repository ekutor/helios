package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.AclRolesAccion;
import com.co.hsg.innventa.session.AclRolesAccionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
@Named(value="aclRolesAccionController")
@ViewScoped
public class AclRolesAccionController extends AbstractController<AclRolesAccion> {

    @Inject
    private AclRolesAccionFacade ejbFacade;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete AclRolesAccion controller bean.
     * The AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public AclRolesAccionController() {
        // Inform the Abstract parent controller of the concrete AclRolesAccion Entity
        super(AclRolesAccion.class);
    }



}
