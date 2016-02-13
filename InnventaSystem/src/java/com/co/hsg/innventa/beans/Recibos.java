/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.hsg.innventa.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hectsaga
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recibos.findAll", query = "SELECT r FROM Recibos r"),
    @NamedQuery(name = "Recibos.findById", query = "SELECT r FROM Recibos r WHERE r.id = :id"),
    @NamedQuery(name = "Recibos.findByFechaInicio", query = "SELECT r FROM Recibos r WHERE r.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Recibos.findByAtributos", query = "SELECT r FROM Recibos r WHERE r.atributos = :atributos"),
    @NamedQuery(name = "Recibos.findByEliminado", query = "SELECT r FROM Recibos r WHERE r.eliminado = :eliminado"),
    @NamedQuery(name = "Recibos.findByFechaFin", query = "SELECT r FROM Recibos r WHERE r.fechaFin = :fechaFin")})
public class Recibos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    private int atributos;
    @Basic(optional = false)
    @NotNull
    private short eliminado;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;

    public Recibos() {
    }

    public Recibos(String id) {
        this.id = id;
    }

    public Recibos(String id, Date fechaInicio, int atributos, short eliminado) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.atributos = atributos;
        this.eliminado = eliminado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getAtributos() {
        return atributos;
    }

    public void setAtributos(int atributos) {
        this.atributos = atributos;
    }

    public short getEliminado() {
        return eliminado;
    }

    public void setEliminado(short eliminado) {
        this.eliminado = eliminado;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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
        if (!(object instanceof Recibos)) {
            return false;
        }
        Recibos other = (Recibos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.Recibos[ id=" + id + " ]";
    }
    
}
