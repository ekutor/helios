package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.AclRolesUsuarios;
import com.co.hsg.innventa.session.AclRolesUsuariosFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
@Named(value="aclRolesUsuariosController")
@ViewScoped
public class AclRolesUsuariosController extends AbstractController<AclRolesUsuarios> {

    @Inject
    private AclRolesUsuariosFacade ejbFacade;
    @Inject
    private AclRolesController rolController;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete AclRolesUsuarios controller bean.
     * The AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public AclRolesUsuariosController() {
        // Inform the Abstract parent controller of the concrete AclRolesUsuarios Entity
        super(AclRolesUsuarios.class);
    }


    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        rolController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the AclRoles controller
     * in order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRol(ActionEvent event) {
        if (this.getSelected() != null && rolController.getSelected() == null) {
            rolController.setSelected(this.getSelected().getRol());
        }
    }
}
