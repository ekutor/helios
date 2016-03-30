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
@Table(name = "pagos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagos.findAll", query = "SELECT p FROM Pagos p"),
    @NamedQuery(name = "Pagos.findById", query = "SELECT p FROM Pagos p WHERE p.id = :id"),
    @NamedQuery(name = "Pagos.findByRecibo", query = "SELECT p FROM Pagos p WHERE p.recibo = :recibo"),
    @NamedQuery(name = "Pagos.findByPago", query = "SELECT p FROM Pagos p WHERE p.pago = :pago"),
    @NamedQuery(name = "Pagos.findByTotal", query = "SELECT p FROM Pagos p WHERE p.total = :total"),
    @NamedQuery(name = "Pagos.findByIdTrans", query = "SELECT p FROM Pagos p WHERE p.idTrans = :idTrans"),
    @NamedQuery(name = "Pagos.findByEliminado", query = "SELECT p FROM Pagos p WHERE p.eliminado = :eliminado")})
public class Pagos implements Serializable {

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
    @Column(name = "recibo")
    private String recibo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "pago")
    private String pago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private double total;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "id_trans")
    private String idTrans;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eliminado")
    private short eliminado;

    public Pagos() {
    }

    public Pagos(String id) {
        this.id = id;
    }

    public Pagos(String id, String recibo, String pago, double total, String idTrans, short eliminado) {
        this.id = id;
        this.recibo = recibo;
        this.pago = pago;
        this.total = total;
        this.idTrans = idTrans;
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

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(String idTrans) {
        this.idTrans = idTrans;
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
        if (!(object instanceof Pagos)) {
            return false;
        }
        Pagos other = (Pagos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.Pagos[ id=" + id + " ]";
    }
    
}
