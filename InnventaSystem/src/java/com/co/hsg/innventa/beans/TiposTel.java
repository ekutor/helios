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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hectsaga
 */
@Entity
@Table(name = "tipos_tel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposTel.findAll", query = "SELECT t FROM TiposTel t"),
    @NamedQuery(name = "TiposTel.findById", query = "SELECT t FROM TiposTel t WHERE t.id = :id"),
    @NamedQuery(name = "TiposTel.findByNombre", query = "SELECT t FROM TiposTel t WHERE t.nombre = :nombre")})
public class TiposTel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    private String nombre;

    public TiposTel() {
    }

    public TiposTel(String id) {
        this.id = id;
    }

    public TiposTel(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        if (!(object instanceof TiposTel)) {
            return false;
        }
        TiposTel other = (TiposTel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.TiposTel[ id=" + id + " ]";
    }
    
}
