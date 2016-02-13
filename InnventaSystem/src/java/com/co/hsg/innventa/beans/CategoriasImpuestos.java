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
@Table(name = "categorias_impuestos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriasImpuestos.findAll", query = "SELECT c FROM CategoriasImpuestos c"),
    @NamedQuery(name = "CategoriasImpuestos.findById", query = "SELECT c FROM CategoriasImpuestos c WHERE c.id = :id"),
    @NamedQuery(name = "CategoriasImpuestos.findByNombre", query = "SELECT c FROM CategoriasImpuestos c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CategoriasImpuestos.findByEliminado", query = "SELECT c FROM CategoriasImpuestos c WHERE c.eliminado = :eliminado")})
public class CategoriasImpuestos implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    private short eliminado;

    public CategoriasImpuestos() {
    }

    public CategoriasImpuestos(String id) {
        this.id = id;
    }

    public CategoriasImpuestos(String id, String nombre, short eliminado) {
        this.id = id;
        this.nombre = nombre;
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
        if (!(object instanceof CategoriasImpuestos)) {
            return false;
        }
        CategoriasImpuestos other = (CategoriasImpuestos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.CategoriasImpuestos[ id=" + id + " ]";
    }
    
}
