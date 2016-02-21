package com.co.hsg.innventa.backing.security;

import javax.faces.event.PhaseListener;

/**
 *
 * @author hectsaga
 */

public interface SecurityAuditor  extends PhaseListener{
    public void validateSession();
}
