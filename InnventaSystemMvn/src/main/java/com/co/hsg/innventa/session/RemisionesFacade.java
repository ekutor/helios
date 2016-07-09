package com.co.hsg.innventa.session;

import com.co.hsg.innventa.backing.SystemManager;
import com.co.hsg.innventa.backing.util.Utils;
import com.co.hsg.innventa.beans.Personas;
import com.co.hsg.innventa.beans.Remisiones;
import com.co.hsg.innventa.beans.RemisionesProducto;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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
        sortRemission(entity);
        Date d = Calendar.getInstance().getTime();
        entity.setFechaModificacion(d);
        updateTotals(entity);
        super.edit(entity);
    }

    @Override
    public void create(Remisiones entity) {
        Personas creadoPor = app.getActualUser();
        sortRemission(entity);
        entity.setId(Utils.generateID());
        entity.setCreadoPor(creadoPor);
        entity.setModificadoPor(creadoPor.getId());
        Date d = Calendar.getInstance().getTime();
        entity.setFechaModificacion(d);
        entity.setFechaCreacion(d);
        updateTotals(entity);
        manager.saveSequence(NamedQuerys.REMISSION_PARAM);
        super.create(entity);
    }
    
    private void updateTotals(Remisiones rm){
        if(!rm.getRemisionesProductoList().isEmpty()){
            int sum = 0;
            for( RemisionesProducto prods : rm.getRemisionesProductoList()){
                sum += prods.getCantidad();
            }
            rm.setTotalProductos(sum);
        }
    }

    private void sortRemission(Remisiones entity) {
        try{
            Collections.sort(entity.getRemisionesProductoList(), new Comparator<RemisionesProducto>() {
                    @Override
                    public int compare(RemisionesProducto r1, RemisionesProducto r2)
                    {

                        return  r1.getIdProducto().getNombre().compareTo(r2.getIdProducto().getNombre());
                    }
                });
        }catch(Exception e){
            
        }
    }
}
