package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.PersonaMails;
import com.co.hsg.innventa.session.PersonaMailsFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
@Named(value="personaMailsController")
@ViewScoped
public class PersonaMailsController extends AbstractController<PersonaMails> {

    @Inject
    private PersonaMailsFacade ejbFacade;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete PersonaMails controller bean.
     * The AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public PersonaMailsController() {
        // Inform the Abstract parent controller of the concrete PersonaMails Entity
        super(PersonaMails.class);
    }



}
