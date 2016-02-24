package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Recibos;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "recibosController")
@ViewScoped
public class RecibosController extends AbstractController<Recibos> {

    @Inject
    private MobilePageController mobilePageController;

    public RecibosController() {
        // Inform the Abstract parent controller of the concrete Recibos Entity
        super(Recibos.class);
    }

}
