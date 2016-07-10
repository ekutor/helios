package com.co.hsg.innventa.session;

import com.co.hsg.innventa.beans.AclRoles;
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
public class AclRolesFacade extends AbstractFacade<AclRoles> {

    @PersistenceContext(unitName = "InnventaSystemPU")
    private EntityManager em;
    
    private static final String PREID = "ROL_";
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AclRolesFacade() {
        super(AclRoles.class);
    }
     @Override
    public void edit(AclRoles entity) {
        String id = app.getActualUserId();
        entity.setModificadoPor(id);
        Date d = Calendar.getInstance().getTime();
        entity.setFechaModificacion(d);
    
        super.edit(entity);
    }

    @Override
    public void create(AclRoles entity) {
        String id = app.getActualUserId();
        String idEnt = PREID+entity.getNombre().substring(0, 3).toUpperCase(); 
        int index;
        if( (index  = entity.getNombre().indexOf(" ") ) != -1){
            try{
                idEnt += entity.getNombre().substring(index+1,index+4);
            }catch(Exception e){
                
            }
        }
        entity.setId(idEnt);
        entity.setCreadoPor(id);
        entity.setModificadoPor(id);
        Date d = Calendar.getInstance().getTime();
        entity.setFechaModificacion(d);
        entity.setFechaCreacion(d);
      
        super.create(entity);
    }
}
