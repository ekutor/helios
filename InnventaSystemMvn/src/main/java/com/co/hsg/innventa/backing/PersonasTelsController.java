package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.PersonasTels;
import com.co.hsg.innventa.session.PersonasTelsFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "personasTelsController")
@ViewScoped
public class PersonasTelsController extends AbstractController<PersonasTels> {

    @Inject
    private PersonasTelsFacade ejbFacade;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete PersonasTels controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public PersonasTelsController() {
        // Inform the Abstract parent controller of the concrete PersonasTels Entity
        super(PersonasTels.class);
    }

}
