package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.PersonaDirs;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "personaDirsController")
@ViewScoped
public class PersonaDirsController extends AbstractController<PersonaDirs> {

    @Inject
    private MobilePageController mobilePageController;

    public PersonaDirsController() {
        // Inform the Abstract parent controller of the concrete PersonaDirs Entity
        super(PersonaDirs.class);
    }

}
