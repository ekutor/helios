package com.co.hsg.innventa.backing.util;

import com.co.hsg.innventa.backing.Modules;
import com.co.hsg.innventa.beans.AclAcciones;
import com.co.hsg.innventa.beans.AclRolesAccion;
import com.co.hsg.innventa.beans.Usuarios;

/**
 *
 * @author hectsaga
 */
public enum Actions {
    PERMITTED("Permitido", 1), NOT_ALLOWED("No Permitido", 0), OWNER("Propietario", 2);
    public String visualName;
    public int value;

    Actions(String visualName, int value) {
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
}
