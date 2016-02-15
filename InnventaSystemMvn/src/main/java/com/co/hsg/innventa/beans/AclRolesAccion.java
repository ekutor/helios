/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.hsg.innventa.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "acl_roles_accion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AclRolesAccion.findAll", query = "SELECT a FROM AclRolesAccion a"),
    @NamedQuery(name = "AclRolesAccion.findById", query = "SELECT a FROM AclRolesAccion a WHERE a.id = :id"),
    @NamedQuery(name = "AclRolesAccion.findByRol", query = "SELECT a FROM AclRolesAccion a WHERE a.rol = :rol"),
    @NamedQuery(name = "AclRolesAccion.findByAccion", query = "SELECT a FROM AclRolesAccion a WHERE a.accion = :accion"),
    @NamedQuery(name = "AclRolesAccion.findByEliminado", query = "SELECT a FROM AclRolesAccion a WHERE a.eliminado = :eliminado")})
public class AclRolesAccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    private String rol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    private String accion;
    @Basic(optional = false)
    @NotNull
    private short eliminado;

    public AclRolesAccion() {
    }

    public AclRolesAccion(Integer id) {
        this.id = id;
    }

    public AclRolesAccion(Integer id, String rol, String accion, short eliminado) {
        this.id = id;
        this.rol = rol;
        this.accion = accion;
        this.eliminado = eliminado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
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
        if (!(object instanceof AclRolesAccion)) {
            return false;
        }
        AclRolesAccion other = (AclRolesAccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.AclRolesAccion[ id=" + id + " ]";
    }
    
}
