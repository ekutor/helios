package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Pedidos;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "pedidosController")
@ViewScoped
public class PedidosController extends AbstractController<Pedidos> {

    @Inject
    private CuentasController idClienteController;
    @Inject
    private EstadosController estadoController;
    @Inject
    private SystemManager systemManager;
    @Inject
    private RemisionesController remisionController;
    @Inject
    private Navigation nav;
     
    @Inject
    private MobilePageController mobilePageController;

    public PedidosController() {
        // Inform the Abstract parent controller of the concrete Pedidos Entity
        super(Pedidos.class);
    }

    @Override
    public Pedidos prepareCreate(ActionEvent event) {
        Pedidos obj = super.prepareCreate(event); 
        
        obj.setReferencia(systemManager.getOCSequence());
        nav.createPedidos();
        
        return obj;
    }
    
    
    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idClienteController.setSelected(null);
        estadoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Personas controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdCliente(ActionEvent event) {
        if (this.getSelected() != null && idClienteController.getSelected() == null) {
            idClienteController.setSelected(this.getSelected().getIdCliente());
        }
    }
    
    public void prepareEstado(ActionEvent event) {
        if (this.getSelected() != null && estadoController.getSelected() == null) {
            estadoController.setSelected(this.getSelected().getEstado());
        }
    }
    
    public boolean hasRemisiones(){
        if(this.getSelected() != null &&this.getSelected().getRemisionesList() != null){
            return this.getSelected().getRemisionesList().isEmpty();
        }
        return true;
        
    }

    /**
     * Sets the "items" attribute with a collection of Remisiones entities that
     * are retrieved from Pedidos?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Remisiones page
     */
    public String navigateRemisionesList() {
        System.out.println("com.co.hsg.innventa.backing.PedidosController.navigateRemisionesList() Ingreso");
        if (this.getSelected() != null) {
            System.out.println("com.co.hsg.innventa.backing.PedidosController.navigateRemisionesList()"+this.getSelected().getRemisionesList().size());
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Remisiones_items", this.getSelected().getRemisionesList());
            remisionController.setItems(this.getSelected().getRemisionesList());
            remisionController.keepLists(true);
            nav.remisiones();
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/remisiones/index";
    }

    /**
     * Sets the "items" attribute with a collection of PedidosProducto entities
     * that are retrieved from Pedidos?cap_first and returns the navigation
     * outcome.
     * 
     *
     * @return navigation outcome for PedidosProducto page
     */
    public String navigatePedidosProductoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("PedidosProducto_items", this.getSelected().getPedidosProductoList());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/pedidosProducto/index";
    }
    
    

}
