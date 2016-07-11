package com.co.hsg.innventa.backing.util;

import com.co.hsg.innventa.backing.Modules;
import com.co.hsg.innventa.beans.AclAcciones;
import com.co.hsg.innventa.beans.AclRolesAccion;
import com.co.hsg.innventa.beans.Usuarios;

/**
 *
 * @author hectsaga
 */
public enum Permissions {
    PERMITTED("Permitido", (short)1), NOT_ALLOWED("No Permitido", (short)0), OWNER("Propietario", (short)2);

    public String visualName;
    public short value;

    Permissions(String visualName, short value) {
        this.visualName = visualName;
        this.value = value;
    }

    public static boolean isPermitted(int value, Usuarios actualUser, String evalId, Modules module) {
        for (AclRolesAccion actions : actualUser.getRol().getAccionesList()) {
            AclAcciones actionModule = actions.getAccion();
            if (actionModule.getModulo().getId().equals(module.name())) {
                if (value == PERMITTED.value) {
                    return true;
                }
                else return value == OWNER.value && actualUser.getPersona().getId().equals(evalId);
            }
        }
        return false;
    }
    
    public static Permissions getAction(short action) {
        for(Permissions p : Permissions.values()){
            if(p.value == action){
                return p;
            }
        }
        return Permissions.NOT_ALLOWED;
    }
    
     public static Permissions getValue(String action) {
        for(Permissions p : Permissions.values()){
            if(p.visualName.equals(action)){
                return p;
            }
        }
        return Permissions.NOT_ALLOWED;
    }
     
     public Short getValue(){
         return value;
     }
     public String getVisualName(){
         return visualName;
     }
}
