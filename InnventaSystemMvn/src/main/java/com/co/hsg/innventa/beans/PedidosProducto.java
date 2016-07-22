package com.co.hsg.innventa.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name = "pedidos_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PedidosProducto.findAll", query = "SELECT p FROM PedidosProducto p"),
    @NamedQuery(name = "PedidosProducto.findById", query = "SELECT p FROM PedidosProducto p WHERE p.id = :id"),
    @NamedQuery(name = "PedidosProducto.findByCantidad", query = "SELECT p FROM PedidosProducto p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "PedidosProducto.findByEliminado", query = "SELECT p FROM PedidosProducto p WHERE p.eliminado = :eliminado")})
public class PedidosProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;

    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_unitario")
    private double valorUnitario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_total")
    private double valorTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eliminado")
    private short eliminado;
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Productos idProducto;
    @JoinColumn(name = "id_pedido", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pedidos idPedido;

    public PedidosProducto() {
    }

    public PedidosProducto(String id) {
        this.id = id;
    }

    public PedidosProducto(String id, int cantidad, short eliminado) {
        this.id = id;
        this.cantidad = cantidad;
        this.eliminado = eliminado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public short getEliminado() {
        return eliminado;
    }

    public void setEliminado(short eliminado) {
        this.eliminado = eliminado;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    public Pedidos getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedidos idPedido) {
        this.idPedido = idPedido;
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
        if (!(object instanceof PedidosProducto)) {
            return false;
        }
        PedidosProducto other = (PedidosProducto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.PedidosProducto[ id=" + id + " ]";
    }
    
}
