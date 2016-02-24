package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Atributos;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "atributosController")
@ViewScoped
public class AtributosController extends AbstractController<Atributos> {

    @Inject
    private MobilePageController mobilePageController;

    public AtributosController() {
        // Inform the Abstract parent controller of the concrete Atributos Entity
        super(Atributos.class);
    }

}
