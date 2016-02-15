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
@Table(name = "persona_dirs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaDirs.findAll", query = "SELECT p FROM PersonaDirs p"),
    @NamedQuery(name = "PersonaDirs.findById", query = "SELECT p FROM PersonaDirs p WHERE p.id = :id"),
    @NamedQuery(name = "PersonaDirs.findByIdPersona", query = "SELECT p FROM PersonaDirs p WHERE p.idPersona = :idPersona"),
    @NamedQuery(name = "PersonaDirs.findByDireccion", query = "SELECT p FROM PersonaDirs p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "PersonaDirs.findByTipo", query = "SELECT p FROM PersonaDirs p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "PersonaDirs.findByObservacion", query = "SELECT p FROM PersonaDirs p WHERE p.observacion = :observacion"),
    @NamedQuery(name = "PersonaDirs.findByPrincipal", query = "SELECT p FROM PersonaDirs p WHERE p.principal = :principal"),
    @NamedQuery(name = "PersonaDirs.findByEliminado", query = "SELECT p FROM PersonaDirs p WHERE p.eliminado = :eliminado")})
public class PersonaDirs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_persona")
    private int idPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    private String tipo;
    @Size(max = 255)
    private String observacion;
    @Basic(optional = false)
    @NotNull
    private boolean principal;
    @Basic(optional = false)
    @NotNull
    private short eliminado;

    public PersonaDirs() {
    }

    public PersonaDirs(String id) {
        this.id = id;
    }

    public PersonaDirs(String id, int idPersona, String direccion, String tipo, boolean principal, short eliminado) {
        this.id = id;
        this.idPersona = idPersona;
        this.direccion = direccion;
        this.tipo = tipo;
        this.principal = principal;
        this.eliminado = eliminado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
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
        if (!(object instanceof PersonaDirs)) {
            return false;
        }
        PersonaDirs other = (PersonaDirs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.PersonaDirs[ id=" + id + " ]";
    }
    
}
