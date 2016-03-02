package com.co.hsg.innventa.backing;

/**
 *
 * @author Hector Sanchez Garcia
 */
public enum Modules {
    PEDIDOS,REMISIONES,CUENTAS,PRODUCTOS,TERCEROS,USUARIOS,MAESTROS,DEFAULT;
    
    public static Modules getModule(String module){
       for (Modules m : Modules.values()){
           if(m.name().toUpperCase().equals(module.toUpperCase())){
               return m;
           }
       }
       return DEFAULT;
    }
}
