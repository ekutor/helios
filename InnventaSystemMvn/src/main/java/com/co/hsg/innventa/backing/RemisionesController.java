package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.JsfUtil;
import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.backing.util.Utils;
import com.co.hsg.innventa.backing.validations.IValidation;
import com.co.hsg.innventa.backing.validations.PurchasesValidation;
import com.co.hsg.innventa.beans.Estados;
import com.co.hsg.innventa.beans.Pedidos;
import com.co.hsg.innventa.beans.PedidosProducto;
import com.co.hsg.innventa.beans.Personas;
import com.co.hsg.innventa.beans.Productos;
import com.co.hsg.innventa.beans.Remisiones;
import com.co.hsg.innventa.beans.RemisionesProducto;
import com.co.hsg.innventa.beans.ReportInfo;
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
    private RemisionesProducto selectedRemisionProduct;
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
        Estados defStates = estadosController.chargeItems(Modules.REMISSIONS).iterator().next();
        obj.setEstado(defStates);
        nav.createPurchaseOrder();
        return obj;
    }
    @Override
    public void saveNew(ActionEvent event) {
        IValidation validator = new PurchasesValidation(selected);
        validator.doValidate();
        if(!isValidationFailed()){
            saveOrders();
            super.saveNew(event);
            nav.remissions();
        }
    }

    @Override
    public void save(ActionEvent event) {
        IValidation validator = new PurchasesValidation(selected);
        validator.doValidate();
        if(!isValidationFailed()){
            saveOrders();
            super.save(event); 
             nav.remissions();
        }
    }
    
    public void save(ActionEvent event, RemisionesProducto rp) {
        if(!PurchasesValidation.validateQyuantityProducts(rp, rp.getCantidad())){
            saveOrders();
            super.save(null);
            rpfacade.edit(rp);
       }
    }
    
    @Override
    public void delete( String id){
        systemManager.decreaseSequence(NamedQuerys.REMISSION_PARAM);
        super.delete(id);
    }
    
     private RemisionesProducto getProductFromList(PedidosProducto pedProduct){
        Remisiones r = this.getSelected();
        for(RemisionesProducto prod : r.getRemisionesProductoList()){
            if(prod.getIdProducto().equals(pedProduct.getIdProducto())){
                if(prod.getIdPedido().equals(pedProduct.getIdPedido())){
                  return prod;  
                }
            }
        }
        return null;
    }

    public String getOrderRef() {
        if(selected != null){
            String idAux = "";
            orderRef = "";
            for(RemisionesProducto rem : selected.getRemisionesProductoList()){
                if(!idAux.equals(rem.getIdPedido().getReferencia())){
                    orderRef += rem.getIdPedido().getReferencia(); 
                    orderRef += "-";
                }
                idAux=rem.getIdPedido().getReferencia();
                
            }
        }
        if(orderRef != null && orderRef.length()> 1){
            orderRef = orderRef.substring(0, orderRef.length() -1);
        }
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
          buildDetailRemissionProduct(selectedProduct);
        }
       
    }
    
    private void addProduct(PedidosProducto pedProd){
       RemisionesProducto rp = this.getProductFromList(pedProd);
       if(rp == null){
           rp = buildRemisionProducto(pedProd);
       }
       rp.setIdPedido(pedProd.getIdPedido());
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
        for (PedidosProducto prodPedido :  incPendingOrders){
            buildDetailRemissionProduct(prodPedido);
        }
    }
     
    public void onRowEdit(RemisionesProducto prodSelec,String value){
       try{
           int valueInt = Integer.parseInt(value);
           PurchasesValidation.validateQyuantityProducts(prodSelec, valueInt);
           selected.setTotalProductos(this.getCantTotal());
       }catch(Exception e){
           e.printStackTrace();
       }
    }
    
    public void saveOrders(){
        for( RemisionesProducto remProd : selected.getRemisionesProductoList()){
            pedidoController.save(remProd.getIdPedido(), null);
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
    
    public void delProduct(ActionEvent e) {
      
        if(selected.getRemisionesProductoList() != null){
            if(selected.getRemisionesProductoList().contains( selectedRemisionProduct )){
                selected.getRemisionesProductoList().remove(selectedRemisionProduct);
                selected.setTotalProductos(this.getCantTotal());
            }
            
        }
    }

    private void buildDetailRemissionProduct(PedidosProducto selectedProduct) {
         if(selectedProduct.getIdProducto().getProductosHijos().isEmpty()){
              this.addProduct(selectedProduct); 
           }else{
               for(Productos p :selectedProduct.getIdProducto().getProductosHijos()){
                   if(p.getCantEnPadre() == 0){
                       JsfUtil.addErrorMessage("Producto Mal Configurado", "La cantidad mínima de productos parte en un producto Principal esta mal definida, "+
                               " articulos "+p.getNombre());
                       break;
                   }
                   PedidosProducto pp = new PedidosProducto();
                   pp.setIdPedido(selectedProduct.getIdPedido());
                   pp.setCantidad(selectedProduct.getCantidad() * p.getCantEnPadre());
                   pp.setIdProducto(p);
                   this.addProduct(pp);
               }
               
           }
    }
    
    public  List<ReportInfo> generateInfo() {
        List<ReportInfo> list = new ArrayList<>();
        if (selected != null) {
            for (RemisionesProducto rem : selected.getRemisionesProductoList()) {
                ReportInfo ri = new ReportInfo();
                
                ri.setCantidad(rem.getCantidad());
                ri.setNombre(rem.getIdProducto().getNombre());
                
                ri.setNombreCliente(selected.getIdPedido().getIdCliente().getNombre());
                ri.setDirCliente(selected.getIdPedido().getIdCliente().getDireccionPpal());
                ri.setTelCliente(selected.getIdPedido().getIdCliente().getTelefonoPpal());
                try{
                    Personas contact = selected.getIdPedido().getIdCliente().getPersona().get(0).getPersona();
                    String pre = ("F".equals(contact.getSexo()))?"Sra. ":"Sr.  ";
                    ri.setContacto(contact.getNombre1()+" "+contact.getApellido1());
                    ri.setDespachador(selected.getCreadoPor().getNombre1()+" "+selected.getCreadoPor().getApellido1());
                    ri.setCantTotal(String.valueOf(selected.getTotalProductos()));
                    ri.setTercero(selected.getEntregadoA().getNombre());
                }catch(Exception e){
                    
                }
               
                ri.setNitCliente("Nit: "+selected.getIdPedido().getIdCliente().getId());
                ri.setNumOrden(this.getOrderRef());
                ri.setFechaEntrega(Utils.getFormattedDate(selected.getFechaRemision()));
                ri.setReferencia(selected.getReferencia());
                ri.setObservaciones(selected.getObservaciones());
                
                
                list.add(ri);
            }
            
        }
        return list;
    }

    public RemisionesProducto getSelectedRemisionProduct() {
        return selectedRemisionProduct;
    }

    public void setSelectedRemisionProduct(RemisionesProducto selectedRemisionProduct) {
        this.selectedRemisionProduct = selectedRemisionProduct;
    }
    
   
}
