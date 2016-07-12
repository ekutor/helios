package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.Actions;
import com.co.hsg.innventa.session.AbstractFacade;
import com.co.hsg.innventa.backing.util.JsfUtil;
import com.co.hsg.innventa.backing.util.Permissions;
import com.co.hsg.innventa.session.NamedQuerys;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import java.util.ResourceBundle;
import javax.ejb.EJBException;
import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.primefaces.context.RequestContext;

/**
 * Represents an abstract shell of to be used as JSF Controller to be used in
 * AJAX-enabled applications. No outcomes will be generated from its methods
 * since handling is designed to be done inside one page.
 *
 * @param <T> the concrete Entity type of the Controller bean to be created
 */
public abstract class AbstractController<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    protected AbstractFacade<T> ejbFacade;
    
    @Inject
    protected AppController app;
    
    private Class<T> itemClass;
    protected T selected;
    protected Collection<T> items;
    protected boolean keepList;
    
    protected Modules actualModule;

    private enum PersistAction {
        CREATE,
        DELETE,
        UPDATE
    }

    public AbstractController() {
    }

    public AbstractController(Class<T> itemClass) {
        this.itemClass = itemClass;
    }

    /**
     * Retrieve the currently selected item.
     *
     * @return the currently selected Entity
     */
    public T getSelected() {
        return selected;
    }

    /**
     * Pass in the currently selected item.
     *
     * @param selected the Entity that should be set as selected
     */
    public void setSelected(T selected) {
        this.selected = selected;
    }
    
    public boolean isAllowed(String action, String id){
       Actions act  = Actions.getAction(action);
       Permissions permission = app.getAccessType(actualModule, act); 
       if(Permissions.PERMITTED.equals(permission)){
           return true;
       }else if(Permissions.OWNER.equals(permission)){
           if(Actions.CREATE.equals(act) || app.getActualUserId().equals(id)){
               return true;
           }
       }
       return false;
    }
    /**
     * Sets any embeddable key fields if an Entity uses composite keys. If the
     * entity does not have composite keys, this method performs no actions and
     * exists purely to be overridden inside a concrete controller class.
     */
    protected void setEmbeddableKeys() {
        // Nothing to do if entity does not have any embeddable key.
    }

    ;

    /**
     * Sets the concrete embedded key of an Entity that uses composite keys.
     * This method will be overriden inside concrete controller classes and does
     * nothing if the specific entity has no composite keys.
     */
    protected void initializeEmbeddableKey() {
        // Nothing to do if entity does not have any embeddable key.
    }
    
    public void keepLists(boolean value) {
        this.keepList = value;
    }
    
    /**
     * Returns all items as a Collection object.
     *
     * @return a collection of Entity items returned by the data layer
     */
    public Collection<T> getItems() {
        if (items == null || !keepList) {
            items = this.ejbFacade.findAll();
        }
        keepList = false;
        return items;
    }
    
    public T chargeItem(NamedQuerys namedQuery ,boolean recharge) {
        try{
         if (selected == null || recharge) {
            selected = this.ejbFacade.findByQuery(namedQuery.getQuery());
            if(selected == null){
                this.prepareCreate(null);
            }
         }
        }catch(Exception e){
            e.printStackTrace();
        }
           
        return selected;
    }
    
     public Collection<T> chargeItems(NamedQuerys namedQuery) {
        try{
         
            items = this.ejbFacade.findAllByQuery(namedQuery.getQuery());
        
        }catch(Exception e){
            e.printStackTrace();
        }
        return items;
    }
     
    public Collection<T> chargeItems(NamedQuerys namedQuery, String param, String value) {
        try{
         
            items = this.ejbFacade.findAllByQuery(namedQuery.getQuery(), param, value );
        
        }catch(Exception e){
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Pass in collection of items
     *
     * @param items a collection of Entity items
     */
    public void setItems(Collection<T> items) {
        this.items = items;
    }

    /**
     * Apply changes to an existing item to the data layer.
     *
     * @param event an event from the widget that wants to save an Entity to the
     * data layer
     */
    public void save(ActionEvent event) {
        String msg = "";
        if(event != null){
         msg = ResourceBundle.getBundle("/Innventa").getString(itemClass.getSimpleName() + "Updated");
        }   
        keepList = false;
        persist(PersistAction.UPDATE, msg, null);
    }
    
    public void save(T instance, String message) {
        if(instance != null){
            if(message!= null && !"".equals(message)){
                message = ResourceBundle.getBundle("/Innventa").getString(itemClass.getSimpleName() + "Updated"); 
            }
         selected = instance;  
         persist(PersistAction.UPDATE, message, null);
        }
    }

    /**
     * Store a new item in the data layer.
     *
     * @param event an event from the widget that wants to save a new Entity to
     * the data layer
     */
    public void saveNew(ActionEvent event) { 
        String msg = ResourceBundle.getBundle("/Innventa").getString(itemClass.getSimpleName() + "Created");
        persist(PersistAction.CREATE, msg, null);
         if(!isValidationFailed()){
            items = null; // Invalidate list of items to trigger re-query.
            keepList = false;
         }
    }

    /**
     * Remove an existing item from the data layer.
     *
     * @param id
     * @param event an event from the widget that wants to delete an Entity from
     * the data layer
     */
    /*public void delete(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/Innventa").getString(itemClass.getSimpleName() + "Deleted");
        persist(PersistAction.DELETE, msg,null);
        if (!isValidationFailed()) {
            selected = null; // Remove selection
            items = null; // Invalidate list of items to trigger re-query.
        }
    }*/
    

    public void delete( String id) {
        System.out.println("com.co.hsg.innventa.backing.AbstractController.delete() "+ id);
        String msg = ResourceBundle.getBundle("/Innventa").getString(itemClass.getSimpleName() + "Deleted");
        persist(PersistAction.DELETE, msg, id);
        if (!isValidationFailed()) {
            selected = null; // Remove selection
            items = null; // Invalidate list of items to trigger re-query.
            keepList = false;
        }
    }

    /**
     * Performs any data modification actions for an entity. The actions that
     * can be performed by this method are controlled by the
     * {@link PersistAction} enumeration and are either CREATE, EDIT or DELETE.
     *
     * @param persistAction a specific action that should be performed on the
     * current item
     * @param successMessage a message that should be displayed when persisting
     * the item succeeds
     */
    private void persist(PersistAction persistAction, String successMessage, String id) {
        if (selected != null) {
            this.setEmbeddableKeys();
            try {
                switch (persistAction) {
                    case CREATE:
                        this.ejbFacade.create(selected);
                        break;
                    case UPDATE:
                        this.ejbFacade.edit(selected);
                        break;
                    case DELETE:
                        this.ejbFacade.remove(id);                    
                        break;
                }
              
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                Throwable cause = JsfUtil.getRootCause(ex.getCause());
                if (cause != null) {
                    if (cause instanceof ConstraintViolationException) {
                        ConstraintViolationException excp = (ConstraintViolationException) cause;
                        for (ConstraintViolation s : excp.getConstraintViolations()) {
                            JsfUtil.addErrorMessage("Validacion Fallida " ,s.getMessage());
                        }
                    } else {
                        String msg = cause.getLocalizedMessage();
                        if (msg.length() > 0) {
                            JsfUtil.addErrorMessage("Validacion Fallida ",msg);
                        } else {
                            JsfUtil.addErrorMessage(ex, "Validacion Fallida ",ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, "Error Grave " ,ResourceBundle.getBundle("/Innventa").getString("PersistenceErrorOccured"));
            }
        }
    }

    /**
     * Creates a new instance of an underlying entity and assigns it to Selected
     * property.
     *
     * @param event an event from the widget that wants to create a new,
     * unmanaged Entity for the data layer
     * @return a new, unmanaged Entity
     */
    public T prepareCreate(ActionEvent event) {
        T newItem;
       
        try {
            newItem = itemClass.newInstance();
            this.selected = newItem;
            initializeEmbeddableKey();
            return newItem;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Inform the user interface whether any validation error exist on a page.
     *
     * @return a logical value whether form validation has passed or failed
     */
    public boolean isValidationFailed() {
        return JsfUtil.isValidationFailed();
    }

    /**
     * Retrieve all messages as a String to be displayed on the page.
     *
     * @param clientComponent the component for which the message applies
     * @param defaultMessage a default message to be shown
     * @return a concatenation of all messages
     */
    public String getComponentMessages(String clientComponent, String defaultMessage) {
        return JsfUtil.getComponentMessages(clientComponent, defaultMessage);
    }

    /**
     * Retrieve a collection of Entity items for a specific Controller from
     * another JSF page via Request parameters.
     */
    @PostConstruct
    public void initParams() {
        Object paramItems = FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(itemClass.getSimpleName() + "_items");
        if (paramItems != null) {
            this.items = (Collection<T>) paramItems;
        }
    }
    
    protected void updateUI(String panelToUpdate){
        RequestContext context = RequestContext.getCurrentInstance();
      //  context.addCallbackParam("saved", true);    //basic parameter
       // context.addCallbackParam("user", user);     //pojo as json
         
        //execute javascript oncomplete
    //    context.execute("PrimeFaces.info('Hello from the Backing Bean');");
         
        //update panel
        //context.update("form:panel");
        context.update(panelToUpdate);
         
        //scroll to panel
        context.scrollTo(panelToUpdate);
         
        //add facesmessage
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Success", panelToUpdate));
    }
    
    public void refresh() {
    FacesContext context = FacesContext.getCurrentInstance();
    Application application = context.getApplication();
    ViewHandler viewHandler = application.getViewHandler();
    UIViewRoot viewRoot = viewHandler.createView(context, context
     .getViewRoot().getViewId());
    context.setViewRoot(viewRoot);
    context.renderResponse(); 
 }
}
