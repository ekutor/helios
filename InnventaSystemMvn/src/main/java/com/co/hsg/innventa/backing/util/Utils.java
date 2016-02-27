package com.co.hsg.innventa.backing.util;

import java.util.UUID;

/**
 *
 * @author hectsaga
 */
public class Utils {
    public static String capitalizes(String value){
       return value.substring(0, 1).toUpperCase()
                    + value.substring(1).toLowerCase();
    }

    public static String generateID() {
       UUID uuid = UUID.randomUUID();
       return ""+uuid; 
    }

    public static String getRefOC(String pref, String sequence) {
        int seq = Integer.parseInt( sequence );
        String formatted = String.format("%05d", ++seq );
        return  pref + formatted;
    }
    
    public static String getClassName(Class entityClass ){
        return entityClass.getName().substring(entityClass.getName().lastIndexOf(".")+1);
    }
}
