package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.CategoriasImpuestos;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "categoriasImpuestosController")
@ViewScoped
public class CategoriasImpuestosController extends AbstractController<CategoriasImpuestos> {

    @Inject
    private MobilePageController mobilePageController;

    public CategoriasImpuestosController() {
        // Inform the Abstract parent controller of the concrete CategoriasImpuestos Entity
        super(CategoriasImpuestos.class);
    }

}
