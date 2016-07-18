package com.co.hsg.innventa.beans.enums;

/**
 *
 * @author hectsaga
 */
public enum ProcessStates {
    SALES_PRODUCT("PRODUCT_TYPE1") , ELEMENT("PRODUCT_TYPE2"),
    
    REMISSIONS_DELIVERED("REMISSIONS_DELIVERED") , REMISSIONS_INPROCESS("REMISSIONS_INPROCESS"),
    
    ORDERS_DELIVERED("ORDERS_DELIVERED") , ORDERS_CANCELED("ORDERS_CANCELED"),
    ORDERS_ENDED("ORDERS_ENDED") , ORDERS_PAID("ORDERS_PAID"),
    ORDERS_ACCEPTED("ORDERS_ACCEPTED") , ORDERS_APPLY("ORDERS_APPLY"),
    ORDERS_PARTIALDELIVERY("ORDERS_PARTIALDELIVERY"),ORDERS_PARTIALPAID("ORDERS_PARTIALPAID"),
    
    PROCESS_NULL("")
    ;

    public static ProcessStates getState(String id) {
        for(ProcessStates p :ProcessStates.values()){
            if(p.getPermanentID().equals(id)){
                return p;
            }
        }
        return PROCESS_NULL;
    }

    String permanentID;

    ProcessStates(String pID){
        permanentID = pID;
    }
    public String getPermanentID(){
        return permanentID;
    }
}
