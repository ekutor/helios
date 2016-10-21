package com.co.hsg.innventa.backing.validations;

import com.co.hsg.innventa.backing.PedidosProductoController;
import com.co.hsg.innventa.backing.RemisionesProductoController;
import com.co.hsg.innventa.backing.util.JsfUtil;
import com.co.hsg.innventa.beans.PedidosProducto;
import com.co.hsg.innventa.beans.Productos;
import com.co.hsg.innventa.beans.ProductosComponentes;
import com.co.hsg.innventa.beans.Remisiones;
import com.co.hsg.innventa.beans.RemisionesProducto;
import com.co.hsg.innventa.beans.enums.ProcessStates;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
    PedidosProductoController pedidosProdController;
    @Inject
    RemisionesProductoController remisionesProdController;

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
        if (purchase.getIdPedido().getCantidadTotal() < purchase.getTotalProductos()) {
           // errors.add("Esta intentando entregar más productos que los solicitados en la Orden " + purchase.getIdPedido().getReferencia());
        }
    }

    public boolean validateQuantityProductsDelivered(RemisionesProducto productPurchase, int modifiedValue, boolean showAdvice) {
        return true;/*
        int deliveredQty = 0;
        int expectedAmount;
        boolean isValid = true;
        List<PedidosProducto> list = pedidosProdController.chargeOrderProducts(productPurchase.getIdPedido());
        for (PedidosProducto lp : list) {
            deliveredQty = remisionesProdController.getQtyDelivered(lp.getIdPedido(), lp.getIdProducto());
            expectedAmount = lp.getCantidad() - lp.getCantidadEntregada();
            ProductosComponentes parent = getComponentParent(productPurchase.getIdProducto(), lp.getIdProducto());
            if (parent != null) {
                expectedAmount *= parent.getCantidad();
            }
            
            if ( expectedAmount == 0 ) {
                if(showAdvice){
                    JsfUtil.addErrorMessage("Cantidad Completada ", "Ya se entregaron todos los articulos "
                        + lp.getIdProducto().getNombre() + " de la Orden " + lp.getIdPedido().getReferencia());
                }
                productPurchase.setCantidad(0);
                isValid = false;
                break;
            } else if (expectedAmount < deliveredQty +  modifiedValue ) {
                expectedAmount = expectedAmount - deliveredQty;
                if(showAdvice){
                    JsfUtil.addErrorMessage("Cantidad Superada ", "En la Orden " + lp.getIdPedido().getReferencia()
                            + " solo faltan  "
                            + expectedAmount
                            + " articulos de " + productPurchase.getIdProducto().getNombre());
                }
                productPurchase.setCantidad(expectedAmount);
                isValid = false;
                break;
            }
        }

        return isValid;*/
    }

    private static ProductosComponentes getComponentParent(Productos product, Productos productParent) {
        for (ProductosComponentes pp : product.getProductosParte()) {
            if (pp.getProductoPadre().equals(productParent)) {
                return pp;
            }
        }
        return null;
    }

}
