
package com.co.hsg.innventa.session;

import com.co.hsg.innventa.beans.UnidadesMedida;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hectsaga
 */
@Stateless
public class UnidadesMedidaFacade extends AbstractFacade<UnidadesMedida> {

    @PersistenceContext(unitName = "InnventaSystemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UnidadesMedidaFacade() {
        super(UnidadesMedida.class);
    }
    
}
