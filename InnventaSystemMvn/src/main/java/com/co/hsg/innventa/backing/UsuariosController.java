package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Usuarios;
import com.co.hsg.innventa.session.UsuariosFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
@Named(value="usuariosController")
@ViewScoped
public class UsuariosController extends AbstractController<Usuarios> {

    @Inject
    private UsuariosFacade ejbFacade;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete Usuarios controller bean.
     * The AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public UsuariosController() {
        // Inform the Abstract parent controller of the concrete Usuarios Entity
        super(Usuarios.class);
    }



}
