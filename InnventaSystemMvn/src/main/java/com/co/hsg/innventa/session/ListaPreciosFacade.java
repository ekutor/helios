package com.co.hsg.innventa.session;

import com.co.hsg.innventa.beans.ListaPrecios;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hector Sanchez Garcia
 */
@Stateless
public class ListaPreciosFacade extends AbstractFacade<ListaPrecios> {

    @PersistenceContext(unitName = "InnventaSystemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ListaPreciosFacade() {
        super(ListaPrecios.class);
    }

}
