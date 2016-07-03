package com.co.hsg.innventa.session;

/**
 *
 * @author Hector Sanchez Garcia
 */
public enum NamedQuerys {
    ORDER_PARAM("Parametros.orders", "CONF_ORDERS"),
    REMISSION_PARAM("Parametros.remissions","CONF_REMISSIONS"),
    PRODUCT_TYPES_PARAM("Parametros.productTypes", "PRODUCT_TYPE"),
    STATUS("Estados.findByModulo",""),
    CATEGORIES("Categorias.findByModulo",""),
    ACCOUNT_CONTACTS("CuentasContactos.findByCuenta",""),
    PARAMS("Parametros.findByParametro","")
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
