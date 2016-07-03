package com.co.hsg.innventa.beans.enums;

/**
 *
 * @author hectsaga
 */
public enum ProcessStates {
    SALES_PRODUCT("PRODUCT_TYPE1") , ELEMENT("PRODUCT_TYPE2");

    String permanentID;

    ProcessStates(String pID){
        permanentID = pID;
    }
    public String getPermanentID(){
        return permanentID;
    }
}
