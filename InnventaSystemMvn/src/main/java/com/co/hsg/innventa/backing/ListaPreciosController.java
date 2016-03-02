package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.backing.util.Utils;
import com.co.hsg.innventa.beans.ListaPrecios;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "listaPreciosController")
@ViewScoped
public class ListaPreciosController extends AbstractController<ListaPrecios> {

    @Inject
    private MobilePageController mobilePageController;

    public ListaPreciosController() {
        // Inform the Abstract parent controller of the concrete ListaPrecios Entity
        super(ListaPrecios.class);
    }

    /**
     * Sets the "items" attribute with a collection of Cuentas entities that are
     * retrieved from ListaPrecios?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Cuentas page
     */
    public String navigateCuentasList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Cuentas_items", this.getSelected().getCuentasList());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/cuentas/index";
    }

    @Override
    public ListaPrecios prepareCreate(ActionEvent event) {
        ListaPrecios lp = super.prepareCreate(event);
        lp.setId(Utils.generateID());
        return lp;
    }
    
    

}
