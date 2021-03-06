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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hector Sanchez Garcia
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedidos.findAll", query = "SELECT p FROM Pedidos p WHERE p.eliminado=0 ORDER BY p.fechaCreacion DESC"),
    @NamedQuery(name = "Pedidos.findById", query = "SELECT p FROM Pedidos p WHERE p.id = :id"),
    @NamedQuery(name = "Pedidos.findByReferencia", query = "SELECT p FROM Pedidos p WHERE p.referencia = :referencia"),
    @NamedQuery(name = "Pedidos.findByFechaPedido", query = "SELECT p FROM Pedidos p WHERE p.fechaPedido = :fechaPedido"),
    @NamedQuery(name = "Pedidos.findByFechaEntrega", query = "SELECT p FROM Pedidos p WHERE p.fechaEntrega = :fechaEntrega"),
    @NamedQuery(name = "Pedidos.findByCantidadTotal", query = "SELECT p FROM Pedidos p WHERE p.cantidadTotal = :cantidadTotal"),
    @NamedQuery(name = "Pedidos.findByDescripcion", query = "SELECT p FROM Pedidos p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Pedidos.findByObservaciones", query = "SELECT p FROM Pedidos p WHERE p.observaciones = :observaciones"),
    @NamedQuery(name = "Pedidos.findByCantidadPendientes", query = "SELECT p FROM Pedidos p WHERE p.cantidadPendientes = :cantidadPendientes"),
    @NamedQuery(name = "Pedidos.findByCreadoPor", query = "SELECT p FROM Pedidos p WHERE p.creadoPor = :creadoPor"),
    @NamedQuery(name = "Pedidos.findByFechaCreacion", query = "SELECT p FROM Pedidos p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Pedidos.findByModificadoPor", query = "SELECT p FROM Pedidos p WHERE p.modificadoPor = :modificadoPor"),
    @NamedQuery(name = "Pedidos.findByFechaModificacion", query = "SELECT p FROM Pedidos p WHERE p.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "Pedidos.findByEliminado", query = "SELECT p FROM Pedidos p WHERE p.eliminado = :eliminado"),
    @NamedQuery(name = "Pedidos.delete", query = "UPDATE Pedidos p SET p.eliminado =1 WHERE p.id =:id")})

public class Pedidos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    private String referencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_pedido")
    @Temporal(TemporalType.DATE)
    private Date fechaPedido;
    @Column(name = "fecha_entrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_total")
    private int cantidadTotal;
    @Size(max = 500)
    private String descripcion;
    @Size(max = 500)
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_pendientes")
    private int cantidadPendientes;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "modificado_por")
    private String modificadoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Basic(optional = false)
    @NotNull
    private short eliminado;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cuentas idCliente;
    @JoinColumn(name = "estado", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Estados estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPedido")
    private List<Remisiones> remisionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPedido")
    private List<PedidosProducto> pedidosProductoList;

    public Pedidos() {
    }

    public Pedidos(String id) {
        this.id = id;
    }

    public Pedidos(String id, String referencia, Date fechaPedido, int cantidadTotal, int cantidadPendientes, String creadoPor, Date fechaCreacion, String modificadoPor, Date fechaModificacion, short eliminado) {
        this.id = id;
        this.referencia = referencia;
        this.fechaPedido = fechaPedido;
        this.cantidadTotal = cantidadTotal;
        this.cantidadPendientes = cantidadPendientes;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.modificadoPor = modificadoPor;
        this.fechaModificacion = fechaModificacion;
        this.eliminado = eliminado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getCantidadPendientes() {
        return cantidadPendientes;
    }

    public void setCantidadPendientes(int cantidadPendientes) {
        this.cantidadPendientes = cantidadPendientes;
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

    public Cuentas getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cuentas idCliente) {
        this.idCliente = idCliente;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Remisiones> getRemisionesList() {
        return remisionesList;
    }

    public void setRemisionesList(List<Remisiones> remisionesList) {
        this.remisionesList = remisionesList;
    }

    @XmlTransient
    public List<PedidosProducto> getPedidosProductoList() {
        if(pedidosProductoList == null){
            pedidosProductoList = new ArrayList<PedidosProducto>();
        }
        return pedidosProductoList;
    }

    public void setPedidosProductoList(List<PedidosProducto> pedidosProductoList) {
        this.pedidosProductoList = pedidosProductoList;
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
        if (!(object instanceof Pedidos)) {
            return false;
        }
        Pedidos other = (Pedidos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.Pedidos[ id=" + id + " ]";
    }
    
}
