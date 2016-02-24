package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.TiposTel;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "tiposTelController")
@ViewScoped
public class TiposTelController extends AbstractController<TiposTel> {

    @Inject
    private MobilePageController mobilePageController;

    public TiposTelController() {
        // Inform the Abstract parent controller of the concrete TiposTel Entity
        super(TiposTel.class);
    }

}
