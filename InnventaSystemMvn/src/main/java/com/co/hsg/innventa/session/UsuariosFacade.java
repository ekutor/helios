package com.co.hsg.innventa.session;

import com.co.hsg.innventa.backing.util.Utils;
import com.co.hsg.innventa.beans.Productos;
import com.co.hsg.innventa.beans.Usuarios;
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
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "InnventaSystemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
    
     @Override
    public void edit(Usuarios entity) {
        String id = app.getActualUserId();
        entity.setModificadoPor(id);
        Date d = Calendar.getInstance().getTime();
        entity.setFechaModificacion(d);
        super.edit(entity);
    }

    @Override
    public void create(Usuarios entity) {
        String id = app.getActualUserId();
        entity.setCreadoPor(id);
        entity.setModificadoPor(id);
        Date d = Calendar.getInstance().getTime();
        entity.setFechaModificacion(d);
        entity.setFechaCreacion(d);
        super.create(entity);
    }
}
