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
@Table(name = "lineas_impuestos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LineasImpuestos.findAll", query = "SELECT l FROM LineasImpuestos l"),
    @NamedQuery(name = "LineasImpuestos.findById", query = "SELECT l FROM LineasImpuestos l WHERE l.id = :id"),
    @NamedQuery(name = "LineasImpuestos.findByRecibo", query = "SELECT l FROM LineasImpuestos l WHERE l.recibo = :recibo"),
    @NamedQuery(name = "LineasImpuestos.findByImpuesto", query = "SELECT l FROM LineasImpuestos l WHERE l.impuesto = :impuesto"),
    @NamedQuery(name = "LineasImpuestos.findByBase", query = "SELECT l FROM LineasImpuestos l WHERE l.base = :base"),
    @NamedQuery(name = "LineasImpuestos.findByMonto", query = "SELECT l FROM LineasImpuestos l WHERE l.monto = :monto"),
    @NamedQuery(name = "LineasImpuestos.findByEliminado", query = "SELECT l FROM LineasImpuestos l WHERE l.eliminado = :eliminado")})
public class LineasImpuestos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    private String recibo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    private String impuesto;
    @Basic(optional = false)
    @NotNull
    private double base;
    @Basic(optional = false)
    @NotNull
    private double monto;
    @Basic(optional = false)
    @NotNull
    private short eliminado;

    public LineasImpuestos() {
    }

    public LineasImpuestos(String id) {
        this.id = id;
    }

    public LineasImpuestos(String id, String recibo, String impuesto, double base, double monto, short eliminado) {
        this.id = id;
        this.recibo = recibo;
        this.impuesto = impuesto;
        this.base = base;
        this.monto = monto;
        this.eliminado = eliminado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecibo() {
        return recibo;
    }

    public void setRecibo(String recibo) {
        this.recibo = recibo;
    }

    public String getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
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
        if (!(object instanceof LineasImpuestos)) {
            return false;
        }
        LineasImpuestos other = (LineasImpuestos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.LineasImpuestos[ id=" + id + " ]";
    }
    
}
