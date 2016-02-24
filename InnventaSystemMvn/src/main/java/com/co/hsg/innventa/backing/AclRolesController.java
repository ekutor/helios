package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.AclRoles;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "aclRolesController")
@ViewScoped
public class AclRolesController extends AbstractController<AclRoles> {

    @Inject
    private MobilePageController mobilePageController;

    public AclRolesController() {
        // Inform the Abstract parent controller of the concrete AclRoles Entity
        super(AclRoles.class);
    }

    /**
     * Sets the "items" attribute with a collection of AclRolesUsuarios entities
     * that are retrieved from AclRoles?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for AclRolesUsuarios page
     */
    public String navigateAclRolesUsuariosList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("AclRolesUsuarios_items", this.getSelected().getAclRolesUsuariosList());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/aclRolesUsuarios/index";
    }

}
