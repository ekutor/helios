package com.co.hsg.innventa.session;

/**
 *
 * @author Hector Sanchez Garcia
 */
public enum NamedQuerys {
    ORDER_PARAM("Parametros.orders"),
    STATUS("Estados.findByModulo")
    ;
    
    private String query;
    

    
    NamedQuerys(String named){
        this.query = named;
    }

    public String getQuery() {
        return query;
    }
    
}
