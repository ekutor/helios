package com.co.hsg.innventa.beans;

import java.io.Serializable;

/**
 *
 * @author hectsaga
 */
public class CuentasContactosId implements Serializable {

    private String persona;
    private String cuenta;

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    
    @Override
    public int hashCode() {
        return (persona!= null && cuenta!= null)? persona.hashCode()
                + cuenta.hashCode() : 0;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CuentasContactosId)) {
            return false;
        }
        CuentasContactosId other = (CuentasContactosId) object;
        return (other.persona == this.persona) && (other.cuenta == this.cuenta);
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.CuentasContactosId[ id=" + cuenta +persona + " ]";
    }
    
}
