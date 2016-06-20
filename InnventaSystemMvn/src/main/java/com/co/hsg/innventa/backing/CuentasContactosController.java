package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.CuentasContactos;
import com.co.hsg.innventa.session.NamedQuerys;
import java.util.Collection;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

@Named(value = "cuentasContactosController")
@ViewScoped
public class CuentasContactosController extends AbstractController<CuentasContactos> {

    @Inject
    private PersonasController idPersonaController;
    @Inject
    private CuentasController idClienteController;
    @Inject
    private MobilePageController mobilePageController;
    
    private Collection<CuentasContactos> selectedItems;

    public CuentasContactosController() {
        // Inform the Abstract parent controller of the concrete CuentasContactos Entity
        super(CuentasContactos.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idPersonaController.setSelected(null);
        idClienteController.setSelected(null);
    }
    
    
     
      public void chargeItems(String accoutNumber) {
        selectedItems = this.chargeItems(NamedQuerys.ACCOUNT_CONTACTS, "cuenta", accoutNumber);
        String r = "dataDetailListForm:dataDetailList";
    /*     RequestContext context = RequestContext.getCurrentInstance();
      //  context.addCallbackParam("saved", true);    //basic parameter
        context.addCallbackParam("itemsDataList", items);     //pojo as json
         
        //execute javascript oncomplete
    //    context.execute("PrimeFaces.info('Hello from the Backing Bean');");
         
        //update panel
        //context.update("form:panel");
        context.update(r);
 
        //scroll to panel
        context.scrollTo(r);
         
        //add facesmessage
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Success", r));*/
    }

    public Collection<CuentasContactos> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(Collection<CuentasContactos> selectedItems) {
        this.selectedItems = selectedItems;
    }
     
    /**
     * Sets the "selected" attribute of the Personas controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    /*public void prepareIdPersona(ActionEvent event) {
        if (this.getSelected() != null && idPersonaController.getSelected() == null) {
            idPersonaController.setSelected(this.getSelected().get);
        }
    }*/

    /**
     * Sets the "selected" attribute of the Cuentas controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdCliente(ActionEvent event) {
      /*  if (this.getSelected() != null && idClienteController.getSelected() == null) {
            idClienteController.setSelected(this.getSelected().getIdCliente());
        }*/
    }
}
