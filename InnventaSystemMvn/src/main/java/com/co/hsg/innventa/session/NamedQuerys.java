package com.co.hsg.innventa.session;

/**
 *
 * @author Hector Sanchez Garcia
 */
public enum NamedQuerys {
    ORDER_PARAM("Parametros.orders", "CONF_ORDERS"),
    REMISSION_PARAM("Parametros.remissions","CONF_REMISSIONS"),
    PRODUCT_TYPES_PARAM("Parametros.productTypes", "PRODUCT_TYPE"),
    PRODUCT_TYPES("Productos.findByType", ""),
    STATUS("Estados.findByModulo",""),
    CATEGORIES("Categorias.findByModulo",""),
    ACCOUNT_CONTACTS("CuentasContactos.findByCuenta",""),
    ACCOUNTS_POSITIONS_PARAM("Parametros.accountPositions", "ACCOUNTS_POSITION"),
    PARAMS("Parametros.findByParametro",""),
    PARAM_MODULES("Parametros.modules",""),
    ORDERS_PENDING("Pedidos.pendings",""),
    ;
    
    private String query;
    private String paramValue;
    

    
    NamedQuerys(String named, String paramValue){
        this.query = named;
        this.paramValue = paramValue;
    }

    public String getQuery() {
        return query;
    }

    public String getParamValue() {
        return paramValue;
    }
    
    
}
