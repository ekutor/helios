package com.co.hsg.innventa.session;

import com.co.hsg.innventa.backing.SystemManager;
import com.co.hsg.innventa.backing.util.Utils;
import com.co.hsg.innventa.beans.Remisiones;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hectsaga
 */
@Stateless
public class RemisionesFacade extends AbstractFacade<Remisiones> {

    @PersistenceContext(unitName = "InnventaSystemPU")
    private EntityManager em;
    
     @Inject
    SystemManager manager;
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RemisionesFacade() {
        super(Remisiones.class);
    }
    
    @Override
    public void edit(Remisiones entity) {
        String id = app.getActualUserId();
        entity.setModificadoPor(id);
        Date d = Calendar.getInstance().getTime();
        entity.setFechaModificacion(d);
        super.edit(entity);
    }

    @Override
    public void create(Remisiones entity) {
        String id = app.getActualUserId();
        entity.setId(Utils.generateID());
        entity.setCreadoPor(id);
        entity.setModificadoPor(id);
        Date d = Calendar.getInstance().getTime();
        entity.setFechaModificacion(d);
        entity.setFechaCreacion(d);
        manager.saveSequence(NamedQuerys.REMISSION_PARAM);
        super.create(entity);
    }
}
