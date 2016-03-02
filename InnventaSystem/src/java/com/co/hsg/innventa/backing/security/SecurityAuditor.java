package com.co.hsg.innventa.backing.security;

import com.co.hsg.innventa.beans.Usuarios;
import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import javax.faces.context.FacesContext;
import javax.inject.Qualifier;

/**
 *
 * @author hectsaga
 */

public interface SecurityAuditor {
    public void validateSession();
}
