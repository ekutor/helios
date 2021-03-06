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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "cuentas_direccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuentasDireccion.findAll", query = "SELECT c FROM CuentasDireccion c"),
    @NamedQuery(name = "CuentasDireccion.findById", query = "SELECT c FROM CuentasDireccion c WHERE c.id = :id"),
    @NamedQuery(name = "CuentasDireccion.findByDireccion", query = "SELECT c FROM CuentasDireccion c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "CuentasDireccion.findByEliminado", query = "SELECT c FROM CuentasDireccion c WHERE c.eliminado = :eliminado")})
public class CuentasDireccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    private String direccion;
    @Basic(optional = false)
    @NotNull
    private short eliminado;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cuentas idCliente;

    public CuentasDireccion() {
    }

    public CuentasDireccion(String id) {
        this.id = id;
    }

    public CuentasDireccion(String id, String direccion, short eliminado) {
        this.id = id;
        this.direccion = direccion;
        this.eliminado = eliminado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public short getEliminado() {
        return eliminado;
    }

    public void setEliminado(short eliminado) {
        this.eliminado = eliminado;
    }

    public Cuentas getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cuentas idCliente) {
        this.idCliente = idCliente;
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
        if (!(object instanceof CuentasDireccion)) {
            return false;
        }
        CuentasDireccion other = (CuentasDireccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.CuentasDireccion[ id=" + id + " ]";
    }
    
}
