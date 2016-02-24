package com.co.hsg.innventa.session;

import com.co.hsg.innventa.backing.AppController;
import com.co.hsg.innventa.backing.util.Utils;
import com.co.hsg.innventa.beans.Pedidos;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hectsaga
 */
@Stateless
public class PedidosFacade extends AbstractFacade<Pedidos> {

    @PersistenceContext(unitName = "InnventaSystemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidosFacade() {
        super(Pedidos.class);
    }

    @Override
    public List<Pedidos> findAll() {
        return super.findAll();
    }

    @Override
    public Pedidos find(Object id) {
        return super.find(id);
    }

    @Override
    public void remove(Pedidos entity) {
        super.remove(entity);
    }

    @Override
    public void edit(Pedidos entity) {
        String id = getActualUserId();
        entity.setModificadoPor(id);
        Date d = Calendar.getInstance().getTime();
        entity.setFechaModificacion(d);
        super.edit(entity);
    }

    @Override
    public void create(Pedidos entity) {
        String id = getActualUserId();
        entity.setId(Utils.generateID());
        entity.setCreadoPor(id);
        entity.setModificadoPor(id);
        Date d = Calendar.getInstance().getTime();
        entity.setFechaModificacion(d);
        entity.setFechaCreacion(d);
        super.create(entity);
    }

    private String getActualUserId() {
        if (app.getUser() != null && app.getUser().getPersona().getId() != null) {
            return  app.getUser().getPersona().getId();
        }
        return null;
    }

}
