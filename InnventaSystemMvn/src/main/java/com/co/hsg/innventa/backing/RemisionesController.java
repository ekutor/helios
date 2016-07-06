package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.backing.util.Utils;
import com.co.hsg.innventa.backing.validations.IValidation;
import com.co.hsg.innventa.backing.validations.PurchasesValidation;
import com.co.hsg.innventa.beans.PedidosProducto;
import com.co.hsg.innventa.beans.Productos;
import com.co.hsg.innventa.beans.Remisiones;
import com.co.hsg.innventa.beans.RemisionesProducto;
import com.co.hsg.innventa.session.NamedQuerys;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "remisionesController")
@ViewScoped
public class RemisionesController extends AbstractController<Remisiones> {

    @Inject
    private PedidosController pedidoController;
    @Inject
    private MobilePageController mobilePageController;
    @Inject
    private SystemManager systemManager;
    @Inject
    private Navigation nav;
    private PedidosProducto selectedProduct;
    
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
         }
        nav.createPurchaseOrder();
        return obj;
    }
     @Override
    public void saveNew(ActionEvent event) {
        IValidation validator = new PurchasesValidation(selected);
        validator.doValidate();
        if(!isValidationFailed()){
            super.saveNew(event);
            nav.purchaseOrders();
        }
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
     
      public PedidosProducto getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(PedidosProducto selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
    public void addProduct(ActionEvent ae) {
        
        RemisionesProducto rp = this.getProductFromList(selectedProduct.getIdProducto());
        if(rp == null){
            rp = new RemisionesProducto();
            rp.setId(Utils.generateID());
            rp.setCantidad(selectedProduct.getCantidad());
            rp.setEliminado((short)0);
            rp.setIdProducto(selectedProduct.getIdProducto());
            rp.setIdRemision(this.selected);
            
            selectedProduct.setCantidadEntregada(selectedProduct.getCantidad());
            selected.getRemisionesProductoList().add(rp);
            selected.setTotalProductos(getCantTotal());
        }        
    }
    public void onRowEdit(String value){
       try{
        if(selectedProduct != null){
            selectedProduct.setCantidadEntregada(Integer.parseInt(value));
            selected.setTotalProductos(this.getCantTotal());
           
        }
       }catch(Exception e){
           e.printStackTrace();
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
