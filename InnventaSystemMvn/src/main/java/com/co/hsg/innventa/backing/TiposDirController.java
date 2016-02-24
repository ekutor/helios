package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.TiposDir;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "tiposDirController")
@ViewScoped
public class TiposDirController extends AbstractController<TiposDir> {

    @Inject
    private MobilePageController mobilePageController;

    public TiposDirController() {
        // Inform the Abstract parent controller of the concrete TiposDir Entity
        super(TiposDir.class);
    }

}
