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
import javax.persistence.Table;
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
@Table(name = "persona_mails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaMails.findAll", query = "SELECT p FROM PersonaMails p"),
    @NamedQuery(name = "PersonaMails.findById", query = "SELECT p FROM PersonaMails p WHERE p.id = :id"),
    @NamedQuery(name = "PersonaMails.findByIdPersona", query = "SELECT p FROM PersonaMails p WHERE p.idPersona = :idPersona"),
    @NamedQuery(name = "PersonaMails.findByMail", query = "SELECT p FROM PersonaMails p WHERE p.mail = :mail"),
    @NamedQuery(name = "PersonaMails.findByPrincipal", query = "SELECT p FROM PersonaMails p WHERE p.principal = :principal"),
    @NamedQuery(name = "PersonaMails.findByEliminado", query = "SELECT p FROM PersonaMails p WHERE p.eliminado = :eliminado")})
public class PersonaMails implements Serializable {

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
    @Size(min = 1, max = 150)
    private String mail;
    private Boolean principal;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date eliminado;

    public PersonaMails() {
    }

    public PersonaMails(String id) {
        this.id = id;
    }

    public PersonaMails(String id, String idPersona, String mail, Date eliminado) {
        this.id = id;
        this.idPersona = idPersona;
        this.mail = mail;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public Date getEliminado() {
        return eliminado;
    }

    public void setEliminado(Date eliminado) {
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
        if (!(object instanceof PersonaMails)) {
            return false;
        }
        PersonaMails other = (PersonaMails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.PersonaMails[ id=" + id + " ]";
    }
    
}
