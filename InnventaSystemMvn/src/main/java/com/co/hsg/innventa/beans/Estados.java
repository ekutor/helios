package com.co.hsg.innventa.beans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hectsaga
 */
@Entity
@Table(name = "estados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estados.findAll", query = "SELECT e FROM Estados e WHERE e.eliminado=0"),
    @NamedQuery(name = "Estados.findById", query = "SELECT e FROM Estados e WHERE e.id = :id"),
    @NamedQuery(name = "Estados.findByEstado", query = "SELECT e FROM Estados e WHERE e.estado = :estado"),
    @NamedQuery(name = "Estados.findByModulo", query = "SELECT e FROM Estados e WHERE e.eliminado =0 AND e.modulo = :modulo"),
    @NamedQuery(name = "Estados.findByEliminado", query = "SELECT e FROM Estados e WHERE e.eliminado = :eliminado"),
    @NamedQuery(name = "Estados.delete", query = "UPDATE Estados e SET e.eliminado = 1 WHERE e.id =:id")})
public class Estados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "modulo")
    private String modulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eliminado")
    private short eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado")
    private Collection<Pedidos> pedidosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado")
    private Collection<Cuentas> cuentasCollection;

    public Estados() {
    }

    public Estados(String id) {
        this.id = id;
    }

    public Estados(String id, String estado, String modulo, short eliminado) {
        this.id = id;
        this.estado = estado;
        this.modulo = modulo;
        this.eliminado = eliminado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado.toUpperCase();
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public short getEliminado() {
        return eliminado;
    }

    public void setEliminado(short eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<Pedidos> getPedidosCollection() {
        return pedidosCollection;
    }

    public void setPedidosCollection(Collection<Pedidos> pedidosCollection) {
        this.pedidosCollection = pedidosCollection;
    }
    
    @XmlTransient
    public Collection<Cuentas> getCuentasCollection() {
        return cuentasCollection;
    }

    public void setCuentasCollection(Collection<Cuentas> cuentasCollection) {
        this.cuentasCollection = cuentasCollection;
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
        if (!(object instanceof Estados)) {
            return false;
        }
        Estados other = (Estados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.Estados[ id=" + id + " ]";
    }
    
}
