package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Terceros;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "tercerosController")
@ViewScoped
public class TercerosController extends AbstractController<Terceros> {

    @Inject
    private MobilePageController mobilePageController;

    public TercerosController() {
        // Inform the Abstract parent controller of the concrete Terceros Entity
        super(Terceros.class);
    }

}
