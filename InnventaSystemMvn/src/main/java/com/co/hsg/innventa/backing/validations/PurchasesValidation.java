package com.co.hsg.innventa.backing.validations;

import com.co.hsg.innventa.backing.util.JsfUtil;
import com.co.hsg.innventa.beans.Pedidos;
import com.co.hsg.innventa.beans.PedidosProducto;
import com.co.hsg.innventa.beans.Productos;
import com.co.hsg.innventa.beans.Remisiones;
import com.co.hsg.innventa.beans.RemisionesProducto;
import com.co.hsg.innventa.beans.enums.ProcessStates;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author hectsaga
 */
public class PurchasesValidation extends AbstractValidation {

    private final Remisiones purchase;

    public PurchasesValidation(Remisiones purchase) {
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
        int cantPedProd = 0;
        if (purchase != null && !purchase.getRemisionesProductoList().isEmpty()) {
            for (RemisionesProducto remProd : purchase.getRemisionesProductoList()) {
                cantTotal += remProd.getCantidad();
                cantPedProd += remProd.getCantidad();
                for (PedidosProducto pedProd : remProd.getIdPedido().getPedidosProductoList()) {
                    if (remProd.getIdProducto().equals(pedProd.getIdProducto())) {
                        deliveredQty = pedProd.getCantidadEntregada();
                        qty = pedProd.getCantidad();
                         int allDelivered = validateRemissionsQty(pedProd.getIdPedido(),remProd,deliveredQty, qty);
                        if( allDelivered == 0){
                            errors.add("Para la orden "+pedProd.getIdPedido().getReferencia()+" ya se entregaron "+allDelivered+" articulos " + 
                                    remProd.getIdProducto().getNombre());
                            break;
                        }
                        pedProd.setCantidadEntregada(pedProd.getCantidadEntregada() + remProd.getCantidad());

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
                        int allDelivered = validateRemissionsQty(pedProd.getIdPedido(),remProd,remProd.getCantidad(), qty);
                        if( allDelivered != 0){
                            errors.add("Para la orden "+pedProd.getIdPedido().getReferencia()+" ya se entregaron "+allDelivered+" articulos " + 
                                    remProd.getIdProducto().getNombre());
                            break;
                        }
                        cantPedProd = (elementsDelivered < cantPedProd) ? elementsDelivered : cantPedProd;

                    }

                    pedProd.setCantidadEntregada(cantPedProd);
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

    private int validateRemissionsQty(Pedidos order, RemisionesProducto remProduct, int elementsDelivered, int requiredQty) {
        int totalDelivered = 0;
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
