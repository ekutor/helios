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
@Table(name = "personas_tels")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonasTels.findAll", query = "SELECT p FROM PersonasTels p"),
    @NamedQuery(name = "PersonasTels.findById", query = "SELECT p FROM PersonasTels p WHERE p.id = :id"),
    @NamedQuery(name = "PersonasTels.findByIdPersona", query = "SELECT p FROM PersonasTels p WHERE p.idPersona = :idPersona"),
    @NamedQuery(name = "PersonasTels.findByTelefono", query = "SELECT p FROM PersonasTels p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "PersonasTels.findByTipo", query = "SELECT p FROM PersonasTels p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "PersonasTels.findByExtension", query = "SELECT p FROM PersonasTels p WHERE p.extension = :extension"),
    @NamedQuery(name = "PersonasTels.findByPrincipal", query = "SELECT p FROM PersonasTels p WHERE p.principal = :principal"),
    @NamedQuery(name = "PersonasTels.findByEliminado", query = "SELECT p FROM PersonasTels p WHERE p.eliminado = :eliminado")})
public class PersonasTels implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "id_persona")
    private String idPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    private String tipo;
    @Size(max = 10)
    private String extension;
    private Boolean principal;
    @Basic(optional = false)
    @NotNull
    private short eliminado;

    public PersonasTels() {
    }

    public PersonasTels(String id) {
        this.id = id;
    }

    public PersonasTels(String id, String idPersona, String telefono, String tipo, short eliminado) {
        this.id = id;
        this.idPersona = idPersona;
        this.telefono = telefono;
        this.tipo = tipo;
        this.eliminado = eliminado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
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
        if (!(object instanceof PersonasTels)) {
            return false;
        }
        PersonasTels other = (PersonasTels) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.PersonasTels[ id=" + id + " ]";
    }
    
}
