package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.LineasImpuestos;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "lineasImpuestosController")
@ViewScoped
public class LineasImpuestosController extends AbstractController<LineasImpuestos> {

    @Inject
    private MobilePageController mobilePageController;

    public LineasImpuestosController() {
        // Inform the Abstract parent controller of the concrete LineasImpuestos Entity
        super(LineasImpuestos.class);
    }

}
