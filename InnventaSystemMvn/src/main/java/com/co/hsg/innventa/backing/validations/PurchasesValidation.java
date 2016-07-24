package com.co.hsg.innventa.backing.validations;

import com.co.hsg.innventa.backing.PedidosController;
import com.co.hsg.innventa.backing.util.JsfUtil;
import com.co.hsg.innventa.beans.Pedidos;
import com.co.hsg.innventa.beans.PedidosProducto;
import com.co.hsg.innventa.beans.Productos;
import com.co.hsg.innventa.beans.ProductosComponentes;
import com.co.hsg.innventa.beans.Remisiones;
import com.co.hsg.innventa.beans.RemisionesProducto;
import com.co.hsg.innventa.beans.enums.ProcessStates;
import com.co.hsg.innventa.session.NamedQuerys;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author hectsaga
 */
@Named(value = "purchasesValidation")
@ViewScoped
public class PurchasesValidation extends AbstractValidation {

    private Remisiones purchase;
    @Inject
    PedidosController pedidosController;

    private ProductosComponentes actualParent;
    
    public PurchasesValidation() {
    }

    public Remisiones getPurchase() {
        return purchase;
    }

    public void setPurchase(Remisiones purchase) {
        this.purchase = purchase;
    }


    @Override
    public void doValidate() {
        validatehasProducts();
        validateTotals();
        validateStates();
        if (!errors.isEmpty()) {
            JsfUtil.addErrorMessages("Validacion al Guardar Remision", new ArrayList(errors));
        }
    }

    public void validatehasProducts() {
        if (purchase.getRemisionesProductoList().isEmpty()) {
            errors.add("Debe seleccionar por lo menos un producto");
        }
    }

    public void validateStates() {
        Calendar c = Calendar.getInstance();
        boolean before = purchase.getFechaRemision().before(c.getTime());
        if (before && purchase.getEstado().getId().equals(ProcessStates.REMISSIONS_DELIVERED)) {
            errors.add("Esta Remision no se puede editar, consulte con el supervisor");
        }

    }

    public void validateTotals() {
         int  qty = 0;
         Map<Productos , Integer> auxParents = new HashMap<>();
        if (purchase != null && !purchase.getRemisionesProductoList().isEmpty()) {
            int total = 0;
            for (RemisionesProducto remProd : purchase.getRemisionesProductoList()) {
                validateQuantityProducts(remProd, remProd.getCantidad(), purchase.getId());
                if(remProd.getIdProducto().getProductosParte().isEmpty()){
                    total += remProd.getCantidad();
                }else{
                    if(actualParent != null){
                        qty = remProd.getCantidad() / actualParent.getCantidad();
                        if(auxParents.containsKey(actualParent.getProductoPadre())){
                            int tempVal = auxParents.get(actualParent.getProductoPadre());
                            qty = (tempVal > qty )? qty : tempVal ;
                            total = total - tempVal;
                        }
                        auxParents.put(actualParent.getProductoPadre(), qty);
                    }else{
                       qty = remProd.getCantidad(); 
                    }
                    total += qty;
                }
                
            }
            if(purchase.getIdPedido().getCantidadTotal() < total){
                errors.add("Esta intentando entregar más productos que los solicitados en la Orden "+purchase.getIdPedido().getReferencia());
            }else{
                purchase.setTotalProductos(total);
            }
        }
    }
    
    public boolean validateQuantityProducts(RemisionesProducto productPurchase, int modifiedValue, String actualId){
        int deliveredQty = 0;
        int qty = 0;
        boolean isValid = true;
        for(PedidosProducto ped : productPurchase.getIdPedido().getPedidosProductoList()){
              deliveredQty = pedidosController.calculateDeliveryQty(ped);
              qty = ped.getCantidad();
              
              if(productPurchase.getIdProducto().getProductosParte().size() > 0){
                   ProductosComponentes parent = getComponentParent(productPurchase.getIdProducto(), ped.getIdProducto());
                   if(parent != null){
                    deliveredQty = deliveredQty * parent.getCantidad();
                    qty = qty * parent.getCantidad();
                   }
                   actualParent = parent;
               }
               if(actualId != null && productPurchase.getIdRemision().getId().equals(actualId)){
                    deliveredQty = deliveredQty - productPurchase.getCantidad();
               }
               
                if( qty == deliveredQty && qty != 0){
                        JsfUtil.addErrorMessage("Cantidad Completada ", "Ya se entregaron todos los articulos "+
                                ped.getIdProducto().getNombre()+" de la Orden "+ped.getIdPedido().getReferencia());
                        productPurchase.setCantidad(deliveredQty);
                        isValid = false;
                        break;
                }else if(qty < deliveredQty + modifiedValue && qty != 0){
                    deliveredQty  = qty - deliveredQty;
                    JsfUtil.addErrorMessage("Cantidad Superada ", "En la Orden "+ped.getIdPedido().getReferencia()+
                            " solo faltan  "+
                            (deliveredQty) +
                            " articulos de "+productPurchase.getIdProducto().getNombre() );
                    productPurchase.setCantidad(deliveredQty);
                    isValid = false;
                    break;
                }
        }
        return isValid;
    }


    private static ProductosComponentes getComponentParent(Productos product, Productos productParent) {
        for(ProductosComponentes pp : product.getProductosParte()){
            if(pp.getProductoPadre().equals(productParent)){
                return pp;
            }
        }
        return null;
    }



}
