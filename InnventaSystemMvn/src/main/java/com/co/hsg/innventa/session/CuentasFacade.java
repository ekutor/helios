package com.co.hsg.innventa.session;

import com.co.hsg.innventa.backing.util.Utils;
import com.co.hsg.innventa.beans.Cuentas;
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
public class CuentasFacade extends AbstractFacade<Cuentas> {

    @PersistenceContext(unitName = "InnventaSystemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentasFacade() {
        super(Cuentas.class);
    }
    
     @Override
    public void edit(Cuentas entity) {
        String id = app.getActualUserId();
        entity.setModificadoPor(id);
        Date d = Calendar.getInstance().getTime();
        entity.setFechaModificacion(d);
        super.edit(entity);
    }

    @Override
    public void create(Cuentas entity) {
        String id = app.getActualUserId();
        entity.setCreadoPor(id);
        entity.setModificadoPor(id);
        Date d = Calendar.getInstance().getTime();
        entity.setFechaModificacion(d);
        entity.setFechaCreacion(d);
        super.create(entity);
    }
}
