package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.PersonasTels;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "personasTelsController")
@ViewScoped
public class PersonasTelsController extends AbstractController<PersonasTels> {

    @Inject
    private MobilePageController mobilePageController;

    public PersonasTelsController() {
        // Inform the Abstract parent controller of the concrete PersonasTels Entity
        super(PersonasTels.class);
    }

}
