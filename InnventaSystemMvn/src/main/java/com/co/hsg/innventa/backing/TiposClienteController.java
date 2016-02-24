package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.TiposCliente;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "tiposClienteController")
@ViewScoped
public class TiposClienteController extends AbstractController<TiposCliente> {

    @Inject
    private MobilePageController mobilePageController;

    public TiposClienteController() {
        // Inform the Abstract parent controller of the concrete TiposCliente Entity
        super(TiposCliente.class);
    }

}
