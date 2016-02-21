package com.co.hsg.innventa.backing.security;

import com.co.hsg.innventa.backing.AppController;
import com.co.hsg.innventa.beans.Usuarios;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.inject.Inject;

/**
 *
 * @author hectsaga
 */

public class SecurityAuditorSession implements SecurityAuditor{
    
    private static SecurityAuditorSession sa;
    
    @Inject
    private AppController controler;
    public SecurityAuditorSession(){
        
    }
    
 
    public void validateSession(){
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        Usuarios user = (Usuarios) ctx.getSessionMap().get("user");
        if(user == null){
            controler.logout();
        }
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        String currentPage = facesContext.getViewRoot().getViewId();
        boolean isLoginPage = (currentPage.lastIndexOf("login.xhtml") > -1) ? true: false;
        Object usuario = facesContext.getExternalContext().getApplicationMap().get("user");
        
        if(!isLoginPage && usuario == null){
            NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
            nh.handleNavigation(facesContext, null, "/");
        }
    }

    @Override
    public void beforePhase(PhaseEvent pe) {
        
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
