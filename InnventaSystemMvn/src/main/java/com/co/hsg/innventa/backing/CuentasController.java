package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Cuentas;
import com.co.hsg.innventa.beans.ListaPrecios;
import com.co.hsg.innventa.beans.Parametros;
import com.co.hsg.innventa.session.CuentasFacade;
import com.co.hsg.innventa.session.NamedQuerys;
import java.util.Collection;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import org.primefaces.event.FlowEvent;

@Named(value="cuentasController")
@ViewScoped
public class CuentasController extends AbstractController<Cuentas> {

    @Inject
    private CuentasFacade ejbFacade;
    @Inject
    private MobilePageController mobilePageController;
    @Inject
    private PersonasController personasController;
    @Inject 
    private ListaPreciosController lpController;
    
    @Inject
    private ParametrosController params;
    
    private Collection<Parametros> info;
    
    private boolean skip;
    
    private String selectedPosition;

    public CuentasController() {
        // Inform the Abstract parent controller of the concrete Cuentas Entity
        super(Cuentas.class);
    }

    @Override
    public Cuentas prepareCreate(ActionEvent event) {
        lpController.prepareCreate(event);
        personasController.prepareCreate(event);
        return super.prepareCreate(event);
    }

    @Override
    public void saveNew(ActionEvent event) {
        Cuentas c = this.getSelected();
        ListaPrecios lp = lpController.getSelected();
        if(lpController.getSelected() != null && lpController.getSelected().getNombre()!= null){
        //    c.setListaPrecios(lpController.getSelected());
        }
        if(personasController.getSelected() != null){
           c.addContact(personasController.getSelected(), selectedPosition);
        }
        super.saveNew(event);
    }

    public String getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(String selectedPosition) {
        this.selectedPosition = selectedPosition;
    }
    
    

    /**
    * Sets the "items" attribute with a collection of Pedidos entities that are retrieved from Cuentas?cap_first
     * and returns the navigation outcome.
     *
     * @return  navigation outcome for Pedidos page
     */
    public String navigatePedidosList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Pedidos_items", this.getSelected().getPedidosList());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/pedidos/index";
    }

    /**
    * Sets the "items" attribute with a collection of CuentasContactos entities that are retrieved from Cuentas?cap_first
     * and returns the navigation outcome.
     *
     * @return  navigation outcome for CuentasContactos page
     */
    public String navigateCuentasContactosList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CuentasContactos_items", this.getSelected().getPersona());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/cuentasContactos/index";
    }

    /**
    * Sets the "items" attribute with a collection of CuentasDireccion entities that are retrieved from Cuentas?cap_first
     * and returns the navigation outcome.
     *
     * @return  navigation outcome for CuentasDireccion page
     */
    public String navigateCuentasDireccionList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CuentasDireccion_items", this.getSelected().getCuentasDireccionList());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/cuentasDireccion/index";
    }
    
    public boolean isSkip() {
        return skip;
    }
 
    public void setSkip(boolean skip) {
        this.skip = skip;
    }
    
    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }
    
     public String transformPostions(String position){
        String finded = "";
        if(info == null){
            info = params.chargeItems(NamedQuerys.ACCOUNTS_POSITIONS_PARAM);
        }
        for(Parametros p : info){
            if(p.getId().equals(position)){
                finded = p.getClave1();
                break;
            }
        }
        return finded;
     }

}
