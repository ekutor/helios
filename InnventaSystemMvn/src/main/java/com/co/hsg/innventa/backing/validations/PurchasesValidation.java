package com.co.hsg.innventa.backing.validations;

import com.co.hsg.innventa.backing.util.JsfUtil;
import com.co.hsg.innventa.beans.Pedidos;
import com.co.hsg.innventa.beans.PedidosProducto;
import com.co.hsg.innventa.beans.Remisiones;
import com.co.hsg.innventa.beans.RemisionesProducto;
import java.util.ArrayList;

/**
 *
 * @author hectsaga
 */
public class PurchasesValidation implements IValidation {

    private final Remisiones purchase;
    
    public PurchasesValidation(Remisiones purchase){
        this.purchase = purchase;
    }
    @Override
    public void doValidate() {
       errors.clear();
       validatehasProducts();

       if(!errors.isEmpty()){
           JsfUtil.addErrorMessages("Validacion al Guardar Orden",new ArrayList(errors));
       }
    }
    
    public void validatehasProducts(){
        if(purchase.getRemisionesProductoList().isEmpty()){
            errors.add("Debe seleccionar por lo menos un producto");
        }else {
            for(RemisionesProducto rp :purchase.getRemisionesProductoList()){
                if(rp.getCantidad() <= 0){
               //    errors.add("Debe definir una cantidad mínima para "+rp.getIdProducto().getNombre()); 
                }
            }
        }
    }
    
 
    
}
