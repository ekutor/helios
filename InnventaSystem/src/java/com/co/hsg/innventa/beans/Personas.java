/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.hsg.innventa.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    @NamedQuery(name = "Personas.findAll", query = "SELECT p FROM Personas p"),
    @NamedQuery(name = "Personas.findById", query = "SELECT p FROM Personas p WHERE p.id = :id"),
    @NamedQuery(name = "Personas.findByNombre1", query = "SELECT p FROM Personas p WHERE p.nombre1 = :nombre1"),
    @NamedQuery(name = "Personas.findByNombre2", query = "SELECT p FROM Personas p WHERE p.nombre2 = :nombre2"),
    @NamedQuery(name = "Personas.findByApellido1", query = "SELECT p FROM Personas p WHERE p.apellido1 = :apellido1"),
    @NamedQuery(name = "Personas.findByApellido2", query = "SELECT p FROM Personas p WHERE p.apellido2 = :apellido2"),
    @NamedQuery(name = "Personas.findBySexo", query = "SELECT p FROM Personas p WHERE p.sexo = :sexo"),
    @NamedQuery(name = "Personas.findByFechaNacim", query = "SELECT p FROM Personas p WHERE p.fechaNacim = :fechaNacim"),
    @NamedQuery(name = "Personas.findByTipoDocumento", query = "SELECT p FROM Personas p WHERE p.tipoDocumento = :tipoDocumento")})
public class Personas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    private String nombre1;
    @Size(max = 80)
    private String nombre2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    private String apellido1;
    @Size(max = 80)
    private String apellido2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    private String sexo;
    @Column(name = "fecha_nacim")
    @Temporal(TemporalType.DATE)
    private Date fechaNacim;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "tipo_documento")
    private String tipoDocumento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona")
    private Collection<ClienteContactos> clienteContactosCollection;

    public Personas() {
    }

    public Personas(String id) {
        this.id = id;
    }

    public Personas(String id, String nombre1, String apellido1, String sexo, String tipoDocumento) {
        this.id = id;
        this.nombre1 = nombre1;
        this.apellido1 = apellido1;
        this.sexo = sexo;
        this.tipoDocumento = tipoDocumento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacim() {
        return fechaNacim;
    }

    public void setFechaNacim(Date fechaNacim) {
        this.fechaNacim = fechaNacim;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    @XmlTransient
    public Collection<ClienteContactos> getClienteContactosCollection() {
        return clienteContactosCollection;
    }

    public void setClienteContactosCollection(Collection<ClienteContactos> clienteContactosCollection) {
        this.clienteContactosCollection = clienteContactosCollection;
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
        if (!(object instanceof Personas)) {
            return false;
        }
        Personas other = (Personas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.Personas[ id=" + id + " ]";
    }
    
}
