package com.co.hsg.innventa.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "remisiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Remisiones.findAll", query = "SELECT r FROM Remisiones r WHERE r.eliminado=0"),
    @NamedQuery(name = "Remisiones.findById", query = "SELECT r FROM Remisiones r WHERE r.id = :id"),
    @NamedQuery(name = "Remisiones.findByFechaRemision", query = "SELECT r FROM Remisiones r WHERE r.fechaRemision = :fechaRemision"),
    @NamedQuery(name = "Remisiones.findByDetalles", query = "SELECT r FROM Remisiones r WHERE r.detalles = :detalles"),
    @NamedQuery(name = "Remisiones.findByTotalProductos", query = "SELECT r FROM Remisiones r WHERE r.totalProductos = :totalProductos"),
    @NamedQuery(name = "Remisiones.findByEstado", query = "SELECT r FROM Remisiones r WHERE r.estado = :estado"),
    @NamedQuery(name = "Remisiones.findByEntregadoA", query = "SELECT r FROM Remisiones r WHERE r.entregadoA = :entregadoA"),
    @NamedQuery(name = "Remisiones.findByCreadoPor", query = "SELECT r FROM Remisiones r WHERE r.creadoPor = :creadoPor"),
    @NamedQuery(name = "Remisiones.findByFechaCreacion", query = "SELECT r FROM Remisiones r WHERE r.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Remisiones.findByModificadoPor", query = "SELECT r FROM Remisiones r WHERE r.modificadoPor = :modificadoPor"),
    @NamedQuery(name = "Remisiones.findByFechaModificacion", query = "SELECT r FROM Remisiones r WHERE r.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "Remisiones.findByEliminado", query = "SELECT r FROM Remisiones r WHERE r.eliminado = :eliminado"),
     @NamedQuery(name = "Remisiones.delete", query = "UPDATE Remisiones e SET e.eliminado = 1 WHERE e.id =:id")})
public class Remisiones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_remision")
    @Temporal(TemporalType.DATE)
    private Date fechaRemision;
    @Size(max = 500)
    @Column(name = "detalles")
    private String detalles;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_productos")
    private int totalProductos;
    @JoinColumn(name = "estado", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Estados estado;
    @Size(max = 36)
    @Column(name = "entregado_a")
    private String entregadoA;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "creado_por")
    private String creadoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Size(max = 36)
    @Column(name = "modificado_por")
    private String modificadoPor;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eliminado")
    private short eliminado;
    @JoinColumn(name = "id_pedido", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pedidos idPedido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRemision")
    private List<RemisionesProducto> remisionesProductoList;

    public Remisiones() {
    }

    public Remisiones(String id) {
        this.id = id;
    }

    public Remisiones(String id, Date fechaRemision, int totalProductos, String creadoPor, Date fechaCreacion, short eliminado) {
        this.id = id;
        this.fechaRemision = fechaRemision;
        this.totalProductos = totalProductos;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.eliminado = eliminado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaRemision() {
        return fechaRemision;
    }

    public void setFechaRemision(Date fechaRemision) {
        this.fechaRemision = fechaRemision;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public int getTotalProductos() {
        return totalProductos;
    }

    public void setTotalProductos(int totalProductos) {
        this.totalProductos = totalProductos;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public String getEntregadoA() {
        return entregadoA;
    }

    public void setEntregadoA(String entregadoA) {
        this.entregadoA = entregadoA;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public short getEliminado() {
        return eliminado;
    }

    public void setEliminado(short eliminado) {
        this.eliminado = eliminado;
    }

    public Pedidos getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedidos idPedido) {
        this.idPedido = idPedido;
    }

    @XmlTransient
    public List<RemisionesProducto> getRemisionesProductoList() {
        if(remisionesProductoList == null){
            remisionesProductoList = new ArrayList<RemisionesProducto>();
        }
        return remisionesProductoList;
    }

    public void setRemisionesProductoList(List<RemisionesProducto> remisionesProductoList) {
        this.remisionesProductoList = remisionesProductoList;
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
        if (!(object instanceof Remisiones)) {
            return false;
        }
        Remisiones other = (Remisiones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.Remisiones[ id=" + id + " ]";
    }
    
}
