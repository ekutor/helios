package com.co.hsg.innventa.session;

import com.co.hsg.innventa.backing.util.Utils;
import com.co.hsg.innventa.beans.Categorias;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hectsaga
 */
@Stateless
public class CategoriasFacade extends AbstractFacade<Categorias> {

    @PersistenceContext(unitName = "InnventaSystemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriasFacade() {
        super(Categorias.class);
    }
    
     @Override
    public void create(Categorias entity) {
        entity.setId(Utils.generateID());
        super.create(entity);
    }
    
}
