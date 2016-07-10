package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.AclAcciones;
import com.co.hsg.innventa.beans.AclRoles;
import com.co.hsg.innventa.beans.AclRolesAccion;
import com.co.hsg.innventa.beans.Usuarios;
import com.co.hsg.innventa.beans.Parametros;
import com.co.hsg.innventa.session.NamedQuerys;
import java.util.Collection;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "aclRolesController")
@ViewScoped
public class AclRolesController extends AbstractController<AclRoles> {

    @Inject
    private MobilePageController mobilePageController;
    
    @Inject
    private ParametrosController params;
    
    private AclRolesAccion selectedAction;
    private Usuarios selectedUser;
    
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

    @Override
    public void save(ActionEvent event) {
        super.save(event);
       // nav.orders();
    }

    @Override
    public AclRoles prepareCreate(ActionEvent event) {
        super.prepareCreate(event);
        Collection<Parametros> modules = params.chargeItems(NamedQuerys.PARAM_MODULES);
        for(Parametros mod : modules){
            AclRolesAccion acl = new AclRolesAccion();
            acl.setRol(selected);
            AclAcciones action = acl.getAccion();
            action.setModulo(mod);
            
            selected.getAccionesList().add(acl);
        }
        return selected;
    }

    public AclRolesAccion getSelectedAction() {
        return selectedAction;
    }

    public void setSelectedAction(AclRolesAccion selectedAction) {
        this.selectedAction = selectedAction;
    }

    public Usuarios getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(Usuarios selectedUser) {
        this.selectedUser = selectedUser;
    }
    
    
}
