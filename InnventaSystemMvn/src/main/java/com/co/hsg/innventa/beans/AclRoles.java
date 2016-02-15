/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.hsg.innventa.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hectsaga
 */
@Entity
@Table(name = "acl_roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AclRoles.findAll", query = "SELECT a FROM AclRoles a"),
    @NamedQuery(name = "AclRoles.findById", query = "SELECT a FROM AclRoles a WHERE a.id = :id"),
    @NamedQuery(name = "AclRoles.findByNombre", query = "SELECT a FROM AclRoles a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "AclRoles.findByDescripcion", query = "SELECT a FROM AclRoles a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "AclRoles.findByFechaCreacion", query = "SELECT a FROM AclRoles a WHERE a.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "AclRoles.findByCreadoPor", query = "SELECT a FROM AclRoles a WHERE a.creadoPor = :creadoPor"),
    @NamedQuery(name = "AclRoles.findByFechaModificacion", query = "SELECT a FROM AclRoles a WHERE a.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "AclRoles.findByModificadoPor", query = "SELECT a FROM AclRoles a WHERE a.modificadoPor = :modificadoPor"),
    @NamedQuery(name = "AclRoles.findByEliminado", query = "SELECT a FROM AclRoles a WHERE a.eliminado = :eliminado")})
public class AclRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "creado_por")
    private String creadoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "modificado_por")
    private String modificadoPor;
    @Basic(optional = false)
    @NotNull
    private short eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rol")
    private Collection<AclRolesUsuarios> aclRolesUsuariosCollection;

    public AclRoles() {
    }

    public AclRoles(String id) {
        this.id = id;
    }

    public AclRoles(String id, String nombre, String descripcion, Date fechaCreacion, String creadoPor, Date fechaModificacion, String modificadoPor, short eliminado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.creadoPor = creadoPor;
        this.fechaModificacion = fechaModificacion;
        this.modificadoPor = modificadoPor;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public short getEliminado() {
        return eliminado;
    }

    public void setEliminado(short eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<AclRolesUsuarios> getAclRolesUsuariosCollection() {
        return aclRolesUsuariosCollection;
    }

    public void setAclRolesUsuariosCollection(Collection<AclRolesUsuarios> aclRolesUsuariosCollection) {
        this.aclRolesUsuariosCollection = aclRolesUsuariosCollection;
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
        if (!(object instanceof AclRoles)) {
            return false;
        }
        AclRoles other = (AclRoles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.AclRoles[ id=" + id + " ]";
    }
    
}
