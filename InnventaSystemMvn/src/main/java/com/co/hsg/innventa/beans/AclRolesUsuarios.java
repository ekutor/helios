
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hectsaga
 */
@Entity
@Table(name = "acl_roles_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AclRolesUsuarios.findAll", query = "SELECT a FROM AclRolesUsuarios a WHERE a.eliminado=0"),
    @NamedQuery(name = "AclRolesUsuarios.findById", query = "SELECT a FROM AclRolesUsuarios a WHERE a.id = :id"),
    @NamedQuery(name = "AclRolesUsuarios.findByUsuario", query = "SELECT a FROM AclRolesUsuarios a WHERE a.usuario = :usuario"),
    @NamedQuery(name = "AclRolesUsuarios.findByEliminado", query = "SELECT a FROM AclRolesUsuarios a WHERE a.eliminado = :eliminado")})
public class AclRolesUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuarios usuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eliminado")
    private short eliminado;
    @JoinColumn(name = "rol", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AclRoles rol;

    public AclRolesUsuarios() {
    }

    public AclRolesUsuarios(Integer id) {
        this.id = id;
    }

    public AclRolesUsuarios(Integer id, short eliminado) {
        this.id = id;
        this.eliminado = eliminado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public short getEliminado() {
        return eliminado;
    }

    public void setEliminado(short eliminado) {
        this.eliminado = eliminado;
    }

    public AclRoles getRol() {
        return rol;
    }

    public void setRol(AclRoles rol) {
        this.rol = rol;
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
        if (!(object instanceof AclRolesUsuarios)) {
            return false;
        }
        AclRolesUsuarios other = (AclRolesUsuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.AclRolesUsuarios[ id=" + id + " ]";
    }
    
}
