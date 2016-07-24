package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.backing.util.Utils;
import com.co.hsg.innventa.backing.validations.IValidation;
import com.co.hsg.innventa.backing.validations.OrdersValidation;
import com.co.hsg.innventa.beans.Estados;
import com.co.hsg.innventa.beans.Pedidos;
import com.co.hsg.innventa.beans.PedidosProducto;
import com.co.hsg.innventa.beans.Productos;
import com.co.hsg.innventa.beans.ProductosComponentes;
import com.co.hsg.innventa.beans.Remisiones;
import com.co.hsg.innventa.beans.RemisionesProducto;
import com.co.hsg.innventa.beans.enums.ProcessStates;
import com.co.hsg.innventa.session.NamedQuerys;
import com.co.hsg.innventa.session.RemisionesFacade;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "pedidosController")
@SessionScoped
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
    private EstadosController estadosController;
    
    @Inject
    private RemisionesFacade remisionFacade;
    @Inject
    private Navigation nav;
    
    private double totalOC;
    private int cantTotal;

    private PedidosProducto selectedProduct;
     
    @Inject
    private MobilePageController mobilePageController;

    public PedidosController() {
        // Inform the Abstract parent controller of the concrete Pedidos Entity
        super(Pedidos.class);
    }

    @Override
    public Pedidos prepareCreate(ActionEvent event) {
        Pedidos obj = super.prepareCreate(event); 
        
        obj.setReferencia(systemManager.getSequence(NamedQuerys.ORDER_PARAM));
        
        Estados defStates = null;
        for( Estados it :estadosController.chargeItems(Modules.ORDERS)){
            if(it.getId().equals(ProcessStates.ORDERS_ACCEPTED.getPermanentID())){
                defStates = it;
                break;
            }
        }
        
        obj.setEstado(defStates);
        
        nav.createOrder();
        return obj;
    }
    
    @Override
     public Collection<Pedidos> getItems() {
        items = this.ejbFacade.findAll();
        return items;
    }
    

    public Pedidos editOrder(ActionEvent event) {
        nav.editOrder();
        return selected;
    }
    
    @Override
    public void delete( String id){
        systemManager.decreaseSequence(NamedQuerys.ORDER_PARAM);
        super.delete(id);
    }
    
    public void addProduct(ActionEvent ae, Productos product) {
        Pedidos p = this.getSelected();
        PedidosProducto pp = this.getProductFromList(product);
        if(pp == null){
            pp = new PedidosProducto();
            pp.setId(Utils.generateID());
            pp.setIdPedido(p);
            pp.setIdProducto(product);
            pp.setValorUnitario(product.getPrecioVenta());
            p.getPedidosProductoList().add(pp);
            selectedProduct = pp;
        }        
    }
    
    public void onRowEdit(String value){
       try{
        if(selectedProduct != null){
            double val = selectedProduct.getValorUnitario() * selectedProduct.getCantidad();
            selectedProduct.setValorTotal(val);
        }
       }catch(Exception e){
           e.printStackTrace();
       }
    }
    
    public void onRowCancel(ActionEvent ae){
        
    }

    public double getTotalOC() {
        Pedidos p = this.getSelected();
        totalOC = 0;
        if(p!= null && !p.getPedidosProductoList().isEmpty()){
            for( PedidosProducto pedido : p.getPedidosProductoList()){
                totalOC += pedido.getValorTotal();
            }
        }
        return totalOC;
    }
    
    public double getCantTotal() {
        Pedidos p = this.getSelected();
        cantTotal = 0;
        if(p!= null && !p.getPedidosProductoList().isEmpty()){
            for( PedidosProducto pedido : p.getPedidosProductoList()){
                cantTotal += pedido.getCantidad();
            }
            p.setCantidadTotal(cantTotal);
        }
        return cantTotal;
    }

    public void setTotalOC(double totalOC) {
        this.totalOC = totalOC;
    }
    
    public void delProduct(ActionEvent ae) {
        Pedidos p = this.getSelected();
        if(p.getPedidosProductoList() != null){
            if(p.getPedidosProductoList().contains( selectedProduct )){
                p.getPedidosProductoList().remove(selectedProduct);
            }
            
        }
    }
    
    private PedidosProducto getProductFromList(Productos product){
        Pedidos p = this.getSelected();
        for(PedidosProducto prodPed : p.getPedidosProductoList()){
            if(prodPed.getIdProducto().equals(product)){
               return prodPed;
            }
        }
        return null;
    }

    @Override
    public void saveNew(ActionEvent event) {
        IValidation validator = new OrdersValidation(selected);
        validator.doValidate();
        if(!isValidationFailed()){
            super.saveNew(event);
            nav.orders();
        }
    }

    @Override
    public void save(ActionEvent event) {
        super.save(event);
        nav.orders();
    }
    
     public void createRemission(ActionEvent event) {
        IValidation validator = new OrdersValidation(selected);
        validator.doValidate();
        if(!isValidationFailed()){
            Remisiones rem = new Remisiones();
            chargeInfotoRemission(rem);  
            super.saveNew(event);
            remisionFacade.create(rem);
            nav.remissions();
        }
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
            nav.remissions();
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

    public PedidosProducto getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(PedidosProducto selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    private void chargeInfotoRemission(Remisiones rem) {
        rem.setIdPedido(selected);
        rem.setReferencia(systemManager.getSequence(NamedQuerys.REMISSION_PARAM));
        rem.setObservaciones(selected.getObservaciones());
        rem.setFechaRemision(selected.getFechaPedido());
        rem.setEstado(selected.getEstado());
        
        for(PedidosProducto pprod:selected.getPedidosProductoList()){
            RemisionesProducto rp = new RemisionesProducto();
            rp.setIdProducto(pprod.getIdProducto());
            rp.setIdRemision(rem);
            rp.setCantidad(pprod.getCantidad());
            rp.setEliminado((short)0);
            rp.setId(Utils.generateID());
            rem.getRemisionesProductoList().add(rp);
            
        }
        //selected.getRemisionesList().add(rem);
    }

    public boolean isDelivered() {
        if(selected == null || selected.getId() == null){
            return true;
        }else {
            return validateProcessDeliveredFinished();
        }
    }

    private boolean validateProcessDeliveredFinished() {
        ProcessStates process = ProcessStates.getState(selected.getEstado().getId());
        switch(process){
            case ORDERS_CANCELED:
                return true;
            case ORDERS_PAID:
                return true;
            case ORDERS_DELIVERED:
                return true;
        }
        int total = 0;
        for(PedidosProducto pp:selected.getPedidosProductoList()){
            total += this.calculateDeliveryQty(pp);
        }
        return (selected.getCantidadTotal() <= total);
    }
   
    public int calculateDeliveryQty(PedidosProducto selectedPedidoProducto){
        int qty = 0;
       Map<Remisiones , Integer> auxParents = new HashMap<>();
       Pedidos pedidos = this.chargeSingleItem(NamedQuerys.ORDER_SINGLE, selected.getId());
       //Pedidos pedidos = selected;
       if(pedidos == null){
         pedidos = selectedPedidoProducto.getIdPedido();
       }
       Productos selectedProd = selectedPedidoProducto.getIdProducto();
       
        for( RemisionesProducto rp : pedidos.getRemisionesProductoList()){
            if(rp.getIdProducto().equals(selectedProd)){
                qty += rp.getCantidad();
            }else if(!rp.getIdProducto().getProductosParte().isEmpty()){
                int purchaseqty = 0;
                int cont = 0;
                for(ProductosComponentes ph :selectedProd.getProductosHijos()){
                    if(ph.getComponente().equals(rp.getIdProducto()) &&
                            ph.getProductoPadre().equals(selectedProd)){
                        purchaseqty = rp.getCantidad() / ph.getCantidad();
                        break;
                    }
                }

                if(auxParents.containsKey(rp.getIdRemision())){
                    int tempVal = auxParents.get(rp.getIdRemision());
                    purchaseqty = (tempVal > purchaseqty )? purchaseqty : tempVal ;
                    qty = qty - tempVal;
                }
                    auxParents.put(rp.getIdRemision(), purchaseqty);
                    
                    qty += purchaseqty;
            }
        }
      
        return qty;
    }
   
}
