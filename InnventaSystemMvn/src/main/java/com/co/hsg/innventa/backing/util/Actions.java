package com.co.hsg.innventa.backing.util;

/**
 *
 * @author hectsaga
 */
public enum Actions {
    ACCESS,CREATE,DELETE,EDIT,EXPORT,IMPORT,VIEW_COSTS, DEFAULT;

    public static Actions getAction(String action) {
       for(Actions a :Actions.values()){
          if(a.name().equalsIgnoreCase(action)){
             return a; 
          } 
       }
       return DEFAULT;
    }
}
