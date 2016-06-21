package com.co.hsg.innventa.session;

/**
 *
 * @author Hector Sanchez Garcia
 */
public enum NamedQuerys {
    ORDER_PARAM("Parametros.orders"),
    PRODUCT_TYPES_PARAM("Parametros.productTypes"),
    STATUS("Estados.findByModulo"),
    CATEGORIES("Categorias.findByModulo"),
    ACCOUNT_CONTACTS("CuentasContactos.findByCuenta"),
    PARAMS("Parametros.findByParametro")
    ;
    
    private String query;
    

    
    NamedQuerys(String named){
        this.query = named;
    }

    public String getQuery() {
        return query;
    }
    
}
