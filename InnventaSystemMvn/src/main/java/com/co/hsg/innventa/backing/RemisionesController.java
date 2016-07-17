package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.backing.util.Utils;
import com.co.hsg.innventa.backing.validations.IValidation;
import com.co.hsg.innventa.backing.validations.PurchasesValidation;
import com.co.hsg.innventa.beans.Estados;
import com.co.hsg.innventa.beans.Pedidos;
import com.co.hsg.innventa.beans.PedidosProducto;
import com.co.hsg.innventa.beans.Productos;
import com.co.hsg.innventa.beans.Remisiones;
import com.co.hsg.innventa.beans.RemisionesProducto;
import com.co.hsg.innventa.session.NamedQuerys;
import com.co.hsg.innventa.session.RemisionesProductoFacade;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "remisionesController")
@SessionScoped
public class RemisionesController extends AbstractController<Remisiones> {

    @Inject
    private PedidosController pedidoController;
    @Inject
    private MobilePageController mobilePageController;
    @Inject
    private SystemManager systemManager;
    @Inject
    private Navigation nav;
    @Inject
    private RemisionesProductoFacade rpfacade;
    @Inject
    private EstadosController estadosController;
    
    private PedidosProducto selectedProduct;
    private String orderRef;
    private List<PedidosProducto> incPendingOrders;
    private boolean allPendings;
    
    
    public RemisionesController() {
        // Inform the Abstract parent controller of the concrete Remisiones Entity
        super(Remisiones.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        pedidoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Pedidos controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdPedido(ActionEvent event) {
        if (this.getSelected() != null && pedidoController.getSelected() == null) {
            pedidoController.setSelected(this.getSelected().getIdPedido());
        }
    }

    /**
     * Sets the "items" attribute with a collection of RemisionesProducto
     * entities that are retrieved from Remisiones?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for RemisionesProducto page
     */
    public String navigateRemisionesProductoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RemisionesProducto_items", this.getSelected().getRemisionesProductoList());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/remisionesProducto/index";
    }
  
    @Override
    public Remisiones prepareCreate(ActionEvent event) {
        Remisiones obj = super.prepareCreate(event);
        obj.setReferencia(systemManager.getSequence(NamedQuerys.REMISSION_PARAM));
         if(pedidoController.getSelected() != null){
            obj.setIdPedido(pedidoController.getSelected() );
            incPendingOrders = new ArrayList<>(pedidoController.getSelected().getPedidosProductoList());
         }
        Estados defStates = estadosController.chargeItems("REMISIONES").iterator().next();
        obj.setEstado(defStates);
        nav.createPurchaseOrder();
        return obj;
    }
    @Override
    public void saveNew(ActionEvent event) {
        IValidation validator = new PurchasesValidation(selected);
        validator.doValidate();
        if(!isValidationFailed()){
            this.calculateTotals();
            super.saveNew(event);
            nav.remissions();
        }
    }

    @Override
    public void save(ActionEvent event) {
        this.calculateTotals();
        super.save(event); 
        
         nav.remissions();
    }
    
    public void save(RemisionesProducto rp) {
      /*  IValidation validator = new PurchasesValidation(selected);
        validator.doValidate();
        if(!isValidationFailed()){*/
            this.calculateTotals();
            
            super.save(null);
            rpfacade.edit(rp);
       // }
    }
    
    @Override
    public void delete( String id){
        systemManager.decreaseSequence(NamedQuerys.REMISSION_PARAM);
        super.delete(id);
    }
    
     private RemisionesProducto getProductFromList(Productos product){
        Remisiones r = this.getSelected();
        for(RemisionesProducto prod : r.getRemisionesProductoList()){
            if(prod.getIdProducto().equals(product)){
               return prod;
            }
        }
        return null;
    }

    public String getOrderRef() {
        if(incPendingOrders != null){
            String idAux = "";
            orderRef = "";
            for(PedidosProducto order : incPendingOrders){
                if(!idAux.equals(order.getIdPedido().getReferencia())){
                    orderRef += order.getIdPedido().getReferencia(); 
                    orderRef += "-";
                }
                idAux=order.getIdPedido().getReferencia();
                
            }
        }
        if(orderRef != null && orderRef.length()> 1);
            orderRef = orderRef.substring(0, orderRef.length() -1);
        return orderRef;
    }

    public void setOrderRef(String orderRef) {
        this.orderRef = orderRef;
    }
    
    public PedidosProducto getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(PedidosProducto selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public boolean isAllPendings() {
        return allPendings;
    }

    public void setAllPendings(boolean allPendings) {
        this.allPendings = allPendings;
    }
    
    public void chargeAllPendings(){
        if(allPendings){
           Collection<Pedidos> pendings = pedidoController.chargeItems(NamedQuerys.ORDERS_PENDING, "cliente", selected.getIdPedido().getIdCliente());
           for( Pedidos pend:pendings){
               for(PedidosProducto pendProd :pend.getPedidosProductoList()){
                  if(pendProd.getCantidad() != pendProd.getCantidadEntregada()){
                      if(!incPendingOrders.contains(pendProd)){
                         incPendingOrders.add(pendProd);
                      }
                  }
               }
           }
        }else{
           incPendingOrders = pedidoController.getSelected().getPedidosProductoList();
        }
    }

    public List<PedidosProducto> getIncPendingOrders() {
        if(incPendingOrders == null){
            incPendingOrders = new ArrayList<>();
        }
        return incPendingOrders;
    }

    public void setIncPendingOrders(List<PedidosProducto> incPendingOrders) {
        this.incPendingOrders = incPendingOrders;
    }

    public void addProduct(ActionEvent ae) {
        if(selectedProduct != null){
           if(selectedProduct.getIdProducto().getProductosHijos().isEmpty()){
              this.addProduct(selectedProduct); 
           }else{
               for(Productos p :selectedProduct.getIdProducto().getProductosHijos()){
                   PedidosProducto pp = new PedidosProducto();
                   pp.setCantidad(selectedProduct.getCantidad());
                   pp.setIdProducto(p);
                   this.addProduct(pp);
               }
               
           }
        }
       
    }
    
    private void addProduct(PedidosProducto pedProd){
       RemisionesProducto rp = this.getProductFromList(pedProd.getIdProducto());
       if(rp == null){
           rp = buildRemisionProducto(pedProd);
       }
       rp.setCantidad(pedProd.getCantidad());  
       selected.setTotalProductos(getCantTotal());
    }
    
    private RemisionesProducto buildRemisionProducto(PedidosProducto pedProd){
        RemisionesProducto rp = new RemisionesProducto();
        rp.setId(Utils.generateID());
        rp.setEliminado((short)0);
        rp.setIdProducto(pedProd.getIdProducto());
        rp.setIdRemision(selected);
        rp.setCantidad(pedProd.getCantidad());
        selected.getRemisionesProductoList().add(rp);
        return rp;
        
    }
     public void addAllProducts(ActionEvent ae) {
        selected.getRemisionesProductoList().clear();
        for (PedidosProducto prodPedido : selected.getIdPedido().getPedidosProductoList()){
            this.buildRemisionProducto(prodPedido);
        }
        selected.setTotalProductos(getCantTotal());
    }
     
    public void onRowEdit(RemisionesProducto prodSelec,String value){
       try{
        if(selectedProduct != null){
           selectedProduct.setCantidadEntregada(Integer.parseInt(value));
        }
        selected.setTotalProductos(this.getCantTotal());
       }catch(Exception e){
           e.printStackTrace();
       }
    }
    
    public void calculateTotals() {
         int cantTotal = 0;
        if(selected!= null && !selected.getRemisionesProductoList().isEmpty()){
            for( RemisionesProducto remProd : selected.getRemisionesProductoList()){
                cantTotal += remProd.getCantidad();
                remProd.getCantidad();
              
            }
            selected.setTotalProductos(cantTotal);
        }
    }
    public int getCantTotal() {
        
        int cantTotal = 0;
        if(selected!= null && !selected.getRemisionesProductoList().isEmpty()){
            for( RemisionesProducto remProd : selected.getRemisionesProductoList()){
                cantTotal += remProd.getCantidad();
            }
        }
        return cantTotal;
    }
    
    public void delProduct(ActionEvent ae) {
      
        if(selected.getRemisionesProductoList() != null){
            if(selected.getRemisionesProductoList().contains( selectedProduct )){
                selected.getRemisionesProductoList().remove(selectedProduct);
            }
            
        }
    }
    
   
}
