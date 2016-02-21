package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.PersonaDirs;
import com.co.hsg.innventa.session.PersonaDirsFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "personaDirsController")
@ViewScoped
public class PersonaDirsController extends AbstractController<PersonaDirs> {

    @Inject
    private PersonaDirsFacade ejbFacade;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete PersonaDirs controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public PersonaDirsController() {
        // Inform the Abstract parent controller of the concrete PersonaDirs Entity
        super(PersonaDirs.class);
    }

}
