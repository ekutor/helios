package com.co.hsg.innventa.backing.security;

import com.co.hsg.innventa.beans.Usuarios;
import java.io.IOException;
import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Qualifier;

/**
 *
 * @author hectsaga
 */

public class SecurityAuditorSession implements SecurityAuditor{
    
    private static SecurityAuditorSession sa;
    private SecurityAuditorSession(){
        
    }
    
    public static SecurityAuditorSession getInstance(){
        if(sa == null){
            sa = new SecurityAuditorSession();
        }
        return sa;
    }
    public void validateSession(){
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        Usuarios user = (Usuarios) ctx.getSessionMap().get("user");
        if(user == null){
            ctx.invalidateSession();
            try {
                ctx.dispatch("/login?faces-redirect=true");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
