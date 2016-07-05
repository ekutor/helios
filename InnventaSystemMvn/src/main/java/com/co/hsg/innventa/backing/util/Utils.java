package com.co.hsg.innventa.backing.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
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

    public static String getReference(String pref, String sequence) {
        int seq  = 0;
        String formatted = "0";
        try{
            seq = Integer.parseInt( sequence );
            formatted = String.format("%05d", ++seq );
            formatted = pref + formatted;
        }catch(Exception e){
            e.printStackTrace();
        }
       
        return  formatted;
    }
    
    public static String getClassName(Class entityClass ){
        return entityClass.getName().substring(entityClass.getName().lastIndexOf(".")+1);
    }

    public static String getFormattedDate(Date fechaRemision) {
       try{
           SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-YYYY", new Locale("es","ES"));
       
        return sdf.format(fechaRemision);
       }catch(Exception e ){
           return "";
       }
        
    }
}
