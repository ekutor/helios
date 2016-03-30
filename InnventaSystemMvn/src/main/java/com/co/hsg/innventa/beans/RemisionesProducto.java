/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.hsg.innventa.beans;

import java.io.Serializable;
import javax.persistence.Basic;
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
@Table(name = "remisiones_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RemisionesProducto.findAll", query = "SELECT r FROM RemisionesProducto r"),
    @NamedQuery(name = "RemisionesProducto.findById", query = "SELECT r FROM RemisionesProducto r WHERE r.id = :id"),
    @NamedQuery(name = "RemisionesProducto.findByIdProducto", query = "SELECT r FROM RemisionesProducto r WHERE r.idProducto = :idProducto"),
    @NamedQuery(name = "RemisionesProducto.findByCantidad", query = "SELECT r FROM RemisionesProducto r WHERE r.cantidad = :cantidad"),
    @NamedQuery(name = "RemisionesProducto.findByEliminado", query = "SELECT r FROM RemisionesProducto r WHERE r.eliminado = :eliminado")})
public class RemisionesProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "id_producto")
    private String idProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private short cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eliminado")
    private short eliminado;
    @JoinColumn(name = "id_remision", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Remisiones idRemision;

    public RemisionesProducto() {
    }

    public RemisionesProducto(String id) {
        this.id = id;
    }

    public RemisionesProducto(String id, String idProducto, short cantidad, short eliminado) {
        this.id = id;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.eliminado = eliminado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public short getCantidad() {
        return cantidad;
    }

    public void setCantidad(short cantidad) {
        this.cantidad = cantidad;
    }

    public short getEliminado() {
        return eliminado;
    }

    public void setEliminado(short eliminado) {
        this.eliminado = eliminado;
    }

    public Remisiones getIdRemision() {
        return idRemision;
    }

    public void setIdRemision(Remisiones idRemision) {
        this.idRemision = idRemision;
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
        if (!(object instanceof RemisionesProducto)) {
            return false;
        }
        RemisionesProducto other = (RemisionesProducto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.RemisionesProducto[ id=" + id + " ]";
    }
    
}
