package com.co.hsg.innventa.session;

import com.co.hsg.innventa.backing.util.Utils;
import com.co.hsg.innventa.beans.Pedidos;
import com.co.hsg.innventa.beans.Productos;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hectsaga
 */
@Stateless
public class ProductosFacade extends AbstractFacade<Productos> {

    @PersistenceContext(unitName = "InnventaSystemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductosFacade() {
        super(Productos.class);
    }
    
    @Override
    public void edit(Productos entity) {
        String id = app.getActualUserId();
        entity.setModificadoPor(id);
        Date d = Calendar.getInstance().getTime();
        entity.setFechaModificacion(d);
        super.edit(entity);
    }

    @Override
    public void create(Productos entity) {
        String id = app.getActualUserId();
        entity.setId(Utils.generateID());
        entity.setCreadoPor(id);
        entity.setModificadoPor(id);
        Date d = Calendar.getInstance().getTime();
        entity.setFechaModificacion(d);
        entity.setFechaCreacion(d);
        super.create(entity);
    }
    
}
