package com.co.hsg.innventa.backing.validations;

import com.co.hsg.innventa.backing.util.JsfUtil;
import com.co.hsg.innventa.beans.Pedidos;
import com.co.hsg.innventa.beans.PedidosProducto;
import java.util.ArrayList;

/**
 *
 * @author hectsaga
 */
public class OrdersValidation implements IValidation {

    private final Pedidos pedido;
    
    public OrdersValidation(Pedidos pedido){
        this.pedido = pedido;
    }
    @Override
    public void doValidate() {
       validateOrderhasProducts();
       validateOrderhasAccount();
       if(!errors.isEmpty()){
           JsfUtil.addErrorMessages("Validacion al Guardar Orden",new ArrayList(errors));
       }
    }
    
    public void validateOrderhasProducts(){
        if(pedido.getPedidosProductoList().isEmpty()){
            errors.add("Debe seleccionar por lo menos un producto");
        }else {
            for(PedidosProducto pp :pedido.getPedidosProductoList()){
                if(pp.getCantidad() <= 0){
                   errors.add("Debe definir una cantidad mínima para "+pp.getIdProducto().getNombre()); 
                }
            }
        }
    }
    
    public void validateOrderhasAccount(){
        if(pedido.getIdCliente() == null){
            errors.add("Debe seleccionar un cliente"); 
        }
    }
    
}
