
package com.co.hsg.innventa.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hectsaga
 */
@Entity
@Table(name = "acl_roles_accion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AclRolesAccion.findAll", query = "SELECT a FROM AclRolesAccion a WHERE a.eliminado=0"),
    @NamedQuery(name = "AclRolesAccion.findById", query = "SELECT a FROM AclRolesAccion a WHERE a.id = :id"),
    @NamedQuery(name = "AclRolesAccion.findByRol", query = "SELECT a FROM AclRolesAccion a WHERE a.rol = :rol"),
    @NamedQuery(name = "AclRolesAccion.findByAccion", query = "SELECT a FROM AclRolesAccion a WHERE a.accion = :accion"),
    @NamedQuery(name = "AclRolesAccion.findByEliminado", query = "SELECT a FROM AclRolesAccion a WHERE a.eliminado = :eliminado")})
public class AclRolesAccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "rol", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AclRoles rol;
    @JoinColumn(name = "accion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AclAcciones accion;
    @JoinColumn(name = "modulo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Parametros modulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eliminado")
    private short eliminado;

    public AclRolesAccion() {
    }

    public AclRolesAccion(Integer id) {
        this.id = id;
    }

    public AclRolesAccion(Integer id,short eliminado) {
        this.id = id;
        this.eliminado = eliminado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AclRoles getRol() {
        return rol;
    }

    public void setRol(AclRoles rol) {
        this.rol = rol;
    }

    public AclAcciones getAccion() {
        return accion;
    }

    public void setAccion(AclAcciones accion) {
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
