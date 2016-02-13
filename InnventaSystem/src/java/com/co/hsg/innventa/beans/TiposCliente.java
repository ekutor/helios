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
@Table(name = "tipos_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposCliente.findAll", query = "SELECT t FROM TiposCliente t"),
    @NamedQuery(name = "TiposCliente.findById", query = "SELECT t FROM TiposCliente t WHERE t.id = :id"),
    @NamedQuery(name = "TiposCliente.findByNombre", query = "SELECT t FROM TiposCliente t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TiposCliente.findByEliminado", query = "SELECT t FROM TiposCliente t WHERE t.eliminado = :eliminado")})
public class TiposCliente implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    private short eliminado;

    public TiposCliente() {
    }

    public TiposCliente(String id) {
        this.id = id;
    }

    public TiposCliente(String id, String nombre, short eliminado) {
        this.id = id;
        this.nombre = nombre;
        this.eliminado = eliminado;
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
        if (!(object instanceof TiposCliente)) {
            return false;
        }
        TiposCliente other = (TiposCliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.TiposCliente[ id=" + id + " ]";
    }
    
}
