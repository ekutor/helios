package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.AclRolesAccion;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "aclRolesAccionController")
@ViewScoped
public class AclRolesAccionController extends AbstractController<AclRolesAccion> {

    @Inject
    private MobilePageController mobilePageController;

    public AclRolesAccionController() {
        // Inform the Abstract parent controller of the concrete AclRolesAccion Entity
        super(AclRolesAccion.class);
    }

}
