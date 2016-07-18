package com.co.hsg.innventa.backing.validations;

import com.co.hsg.innventa.backing.PedidosController;
import com.co.hsg.innventa.backing.util.JsfUtil;
import com.co.hsg.innventa.beans.Pedidos;
import com.co.hsg.innventa.beans.PedidosProducto;
import com.co.hsg.innventa.beans.Remisiones;
import com.co.hsg.innventa.beans.RemisionesProducto;
import com.co.hsg.innventa.beans.enums.ProcessStates;
import com.co.hsg.innventa.session.NamedQuerys;
import java.util.ArrayList;
import java.util.Calendar;
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
        int cantTotal = 0;
        int deliveredQty = 0;
        int qty = 0;
       
        if (purchase != null && !purchase.getRemisionesProductoList().isEmpty()) {
            for (RemisionesProducto remProd : purchase.getRemisionesProductoList()) {
                cantTotal += remProd.getCantidad();
                for (PedidosProducto pedProd : remProd.getIdPedido().getPedidosProductoList()) {
                    deliveredQty = 0;
                    if (remProd.getIdProducto().equals(pedProd.getIdProducto())) {
                        
                        qty = pedProd.getCantidad();
                         int allDelivered = validateRemissionsQty(pedProd.getIdPedido().getId(),remProd,deliveredQty, qty);
                        if( allDelivered == 0){
                            errors.add("Para la orden "+pedProd.getIdPedido().getReferencia()+" ya se entregaron "+allDelivered+" articulos " + 
                                    remProd.getIdProducto().getNombre());
                            break;
                        }
                        deliveredQty = remProd.getCantidad();
                       
                    } else if (remProd.getIdProducto().getProductoPadre() != null
                            && remProd.getIdProducto().getProductoPadre().equals(pedProd.getIdProducto())) {
                        if (remProd.getIdProducto().getCantEnPadre() <= 0) {
                            errors.add("La cantidad mínima de productos parte en un producto Principal esta mal definida, "
                                    + " articulos " + remProd.getIdProducto().getNombre());
                            break;
                        }
                        deliveredQty = pedProd.getCantidadEntregada() * remProd.getIdProducto().getCantEnPadre();
                        qty = pedProd.getCantidad() * remProd.getIdProducto().getCantEnPadre();
                        int maxElemnts = pedProd.getCantidad() - pedProd.getCantidadEntregada();
                        int elementsDelivered = 0;
                        int surplus = remProd.getCantidad() % remProd.getIdProducto().getCantEnPadre(); //sobrante
                        if (surplus == 0) {
                            elementsDelivered = remProd.getCantidad() / remProd.getIdProducto().getCantEnPadre();
                        } else {
                            elementsDelivered = (remProd.getCantidad() - surplus) / remProd.getIdProducto().getCantEnPadre();
                        }
                        int allDelivered = validateRemissionsQty(pedProd.getIdPedido().getId(),remProd,remProd.getCantidad(), qty);
                        if( allDelivered != 0){
                            errors.add("Para la orden "+pedProd.getIdPedido().getReferencia()+" ya se entregaron "+allDelivered+" articulos " + 
                                    remProd.getIdProducto().getNombre());
                            break;
                        }
                        deliveredQty = (elementsDelivered < deliveredQty || deliveredQty == 0) ? elementsDelivered : deliveredQty;

                    }

                    pedProd.setCantidadEntregada(pedProd.getCantidadEntregada()+ deliveredQty);
                }
                if ( deliveredQty > qty ) {
                    errors.add("se estan entregando mas articulos de los solicitados en la Orden " + remProd.getIdPedido().getReferencia());

                    break;
                }

            }
            purchase.setTotalProductos(cantTotal);
        }
    }
    
    public static boolean validateQyuantityProducts(RemisionesProducto productPurchase, int modifiedValue){
        int deliveredQty = 0;
        int qty = 0;
        boolean isValid = true;
        for(PedidosProducto ped : productPurchase.getIdPedido().getPedidosProductoList()){
               if( productPurchase.getIdProducto().equals(ped.getIdProducto()) ){
                   deliveredQty = ped.getCantidadEntregada();
                   qty = ped.getCantidad();
               }else if( productPurchase.getIdProducto().getProductoPadre()!= null &&
                       productPurchase.getIdProducto().getProductoPadre().equals(ped.getIdProducto())){
                   deliveredQty = ped.getCantidadEntregada() * productPurchase.getIdProducto().getCantEnPadre();
                   qty = ped.getCantidad() * productPurchase.getIdProducto().getCantEnPadre();
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

    private int validateRemissionsQty(String orderId, RemisionesProducto remProduct, int elementsDelivered, int requiredQty) {
        int totalDelivered = 0;
        //Recargar Pedido
        Pedidos order = pedidosController.chargeItem(NamedQuerys.ORDER_SINGLE, orderId);
        for(Remisiones r :order.getRemisionesList()){
            for( RemisionesProducto rp : r.getRemisionesProductoList()){
                if(rp.equals(remProduct)){
                    continue;
                }
                if(rp.getIdProducto().equals(remProduct.getIdProducto())){
                    totalDelivered += rp.getCantidad();
                }
            }
        }
        if (requiredQty >= (totalDelivered + elementsDelivered) ){
            return 0;
        }else{
            return totalDelivered;
        }
    }

}
