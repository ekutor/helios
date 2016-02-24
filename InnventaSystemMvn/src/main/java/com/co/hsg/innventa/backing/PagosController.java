package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Pagos;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "pagosController")
@ViewScoped
public class PagosController extends AbstractController<Pagos> {

    @Inject
    private MobilePageController mobilePageController;

    public PagosController() {
        // Inform the Abstract parent controller of the concrete Pagos Entity
        super(Pagos.class);
    }

}
