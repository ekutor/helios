package com.co.hsg.innventa.session;

import com.co.hsg.innventa.backing.AppController;
import com.co.hsg.innventa.backing.util.JsfUtil;
import com.co.hsg.innventa.backing.util.Utils;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;


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
        if (!constraintValidationsDetected(entity))
            getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        if (!constraintValidationsDetected(entity))
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
         T newItem = null;
        try{
        TypedQuery<T> q = getEntityManager().createNamedQuery(namedQuery,entityClass);
         newItem = q.getSingleResult();
        }catch(Exception e){
             //e.printStackTrace();
        }
        return newItem;
    }
    
    public List<T> findAllByQuery(String namedQuery, String param, Object value) {
        TypedQuery<T> q = getEntityManager().createNamedQuery(namedQuery,entityClass);
        q.setParameter(param, value);
        return q.getResultList();
    }
    
    public List<T> findAllByQuery(String namedQuery) {
        TypedQuery<T> q = getEntityManager().createNamedQuery(namedQuery,entityClass);
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
    
    private boolean constraintValidationsDetected(T entity) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
        if (constraintViolations.size() > 0) {
          Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
          while (iterator.hasNext()) {
            ConstraintViolation<T> cv = iterator.next();
            System.err.println(cv.getRootBeanClass().getName() + "." + cv.getPropertyPath() + " " + cv.getMessage());

            JsfUtil.addErrorMessage("",cv.getRootBeanClass().getSimpleName() + "." + cv.getPropertyPath() + " " + cv.getMessage());
          }
          return true;
    }
    else {
      return false;
    }
  }
}
