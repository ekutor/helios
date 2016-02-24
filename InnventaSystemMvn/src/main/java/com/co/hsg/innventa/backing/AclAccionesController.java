package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.AclAcciones;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "aclAccionesController")
@ViewScoped
public class AclAccionesController extends AbstractController<AclAcciones> {

    @Inject
    private MobilePageController mobilePageController;

    public AclAccionesController() {
        // Inform the Abstract parent controller of the concrete AclAcciones Entity
        super(AclAcciones.class);
    }

}
