package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.PersonaMails;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "personaMailsController")
@ViewScoped
public class PersonaMailsController extends AbstractController<PersonaMails> {

    @Inject
    private MobilePageController mobilePageController;

    public PersonaMailsController() {
        // Inform the Abstract parent controller of the concrete PersonaMails Entity
        super(PersonaMails.class);
    }

}
