/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.hsg.innventa.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author hectsaga
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Impuestos.findAll", query = "SELECT i FROM Impuestos i"),
    @NamedQuery(name = "Impuestos.findById", query = "SELECT i FROM Impuestos i WHERE i.id = :id"),
    @NamedQuery(name = "Impuestos.findByNombre", query = "SELECT i FROM Impuestos i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Impuestos.findByCategoria", query = "SELECT i FROM Impuestos i WHERE i.categoria = :categoria"),
    @NamedQuery(name = "Impuestos.findByValidoDesde", query = "SELECT i FROM Impuestos i WHERE i.validoDesde = :validoDesde"),
    @NamedQuery(name = "Impuestos.findByTarifa", query = "SELECT i FROM Impuestos i WHERE i.tarifa = :tarifa"),
    @NamedQuery(name = "Impuestos.findByCreadoPor", query = "SELECT i FROM Impuestos i WHERE i.creadoPor = :creadoPor"),
    @NamedQuery(name = "Impuestos.findByModificadoPor", query = "SELECT i FROM Impuestos i WHERE i.modificadoPor = :modificadoPor"),
    @NamedQuery(name = "Impuestos.findByEliminado", query = "SELECT i FROM Impuestos i WHERE i.eliminado = :eliminado")})
public class Impuestos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String nombre;
    @Size(max = 36)
    private String categoria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valido_desde")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validoDesde;
    @Basic(optional = false)
    @NotNull
    private double tarifa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "creado_por")
    private String creadoPor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "modificado_por")
    private String modificadoPor;
    @Basic(optional = false)
    @NotNull
    private short eliminado;
    @OneToMany(mappedBy = "impuestoPadre")
    private List<Impuestos> impuestosList;
    @JoinColumn(name = "impuesto_padre", referencedColumnName = "id")
    @ManyToOne
    private Impuestos impuestoPadre;

    public Impuestos() {
    }

    public Impuestos(String id) {
        this.id = id;
    }

    public Impuestos(String id, String nombre, Date validoDesde, double tarifa, String creadoPor, String modificadoPor, short eliminado) {
        this.id = id;
        this.nombre = nombre;
        this.validoDesde = validoDesde;
        this.tarifa = tarifa;
        this.creadoPor = creadoPor;
        this.modificadoPor = modificadoPor;
        this.eliminado = eliminado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Date getValidoDesde() {
        return validoDesde;
    }

    public void setValidoDesde(Date validoDesde) {
        this.validoDesde = validoDesde;
    }

    public double getTarifa() {
        return tarifa;
    }

    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public short getEliminado() {
        return eliminado;
    }

    public void setEliminado(short eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public List<Impuestos> getImpuestosList() {
        return impuestosList;
    }

    public void setImpuestosList(List<Impuestos> impuestosList) {
        this.impuestosList = impuestosList;
    }

    public Impuestos getImpuestoPadre() {
        return impuestoPadre;
    }

    public void setImpuestoPadre(Impuestos impuestoPadre) {
        this.impuestoPadre = impuestoPadre;
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
        if (!(object instanceof Impuestos)) {
            return false;
        }
        Impuestos other = (Impuestos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.Impuestos[ id=" + id + " ]";
    }
    
}
