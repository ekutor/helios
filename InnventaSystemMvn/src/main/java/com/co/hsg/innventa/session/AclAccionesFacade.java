package com.co.hsg.innventa.session;

import com.co.hsg.innventa.beans.AclAcciones;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hectsaga
 */
@Stateless
public class AclAccionesFacade extends AbstractFacade<AclAcciones> {

    @PersistenceContext(unitName = "InnventaSystemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AclAccionesFacade() {
        super(AclAcciones.class);
    }
    
}
