/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.hsg.innventa.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hectsaga
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parametros.findAll", query = "SELECT p FROM Parametros p WHERE p.eliminado = 0"),
    @NamedQuery(name = "Parametros.findById", query = "SELECT p FROM Parametros p WHERE p.id = :id"),
    @NamedQuery(name = "Parametros.findByClave1", query = "SELECT p FROM Parametros p WHERE p.clave1 = :clave1"),
    @NamedQuery(name = "Parametros.findByClave2", query = "SELECT p FROM Parametros p WHERE p.clave2 = :clave2"),
    @NamedQuery(name = "Parametros.findByParametro", query = "SELECT p FROM Parametros p WHERE p.parametro = :parametro"),
    @NamedQuery(name = "Parametros.orders", query = "SELECT p FROM Parametros p WHERE p.parametro = 'CONF_ORDERS' AND p.eliminado=0")})
public class Parametros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    private String id;
    @Size(max = 20)
    private String clave1;
    @Size(max = 20)
    private String clave2;
    @Size(max = 20)
    private String parametro;
    @Basic(optional = false)
    @NotNull
    private short eliminado;

    public Parametros() {
    }

    public Parametros(String id) {
        this.id = id;
    }

    public Parametros(String id, short eliminado) {
        this.id = id;
        this.eliminado = eliminado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClave1() {
        return clave1;
    }

    public void setClave1(String clave1) {
        this.clave1 = clave1;
    }

    public String getClave2() {
        return clave2;
    }

    public void setClave2(String clave2) {
        this.clave2 = clave2;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public short getEliminado() {
        return eliminado;
    }

    public void setEliminado(short eliminado) {
        this.eliminado = eliminado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametros)) {
            return false;
        }
        Parametros other = (Parametros) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.Parametros[ id=" + id + " ]";
    }
    
}
