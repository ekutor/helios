package com.co.hsg.innventa.session;

import com.co.hsg.innventa.backing.AppController;
import com.co.hsg.innventa.backing.util.Utils;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author hectsaga
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;
    
    @Inject
    AppController app;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

     public void remove(String id) {
        //getEntityManager().remove(getEntityManager().merge(entity));
        System.out.println("com.co.hsg.innventa.session.AbstractFacade.remove()"+ id);
        String className = Utils.getClassName(entityClass);
         System.out.println("com.co.hsg.innventa.session.AbstractFacade.remove()"+ id+" "+className);
        TypedQuery<T> q = getEntityManager().createNamedQuery(className+".delete",entityClass);
        q.setParameter("id", id);
        q.executeUpdate();
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }
    
    public T findByQuery(String namedQuery) {
        TypedQuery<T> q = getEntityManager().createNamedQuery(namedQuery,entityClass);
        return q.getSingleResult();
    }
    
    public List<T> findAllByQuery(String namedQuery, String param, String value) {
        TypedQuery<T> q = getEntityManager().createNamedQuery(namedQuery,entityClass);
        q.setParameter(param, value);
        return q.getResultList();
    }

    public List<T> findAll() {
        /*javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();*/
        String className = Utils.getClassName(entityClass);
        TypedQuery<T> q = getEntityManager().createNamedQuery(className+".findAll",entityClass);
        return q.getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
