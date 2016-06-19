/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.hsg.innventa.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hectsaga
 */
@Entity
@Table(name = "cuentas_contactos")
@IdClass(CuentasContactosId.class)
@XmlRootElement 
@NamedQueries({
    @NamedQuery(name = "CuentasContactos.findAll", query = "SELECT c FROM CuentasContactos c"),
    @NamedQuery(name = "CuentasContactos.findByCargo", query = "SELECT c FROM CuentasContactos c WHERE c.cargo = :cargo"),
    @NamedQuery(name = "CuentasContactos.findByEliminado", query = "SELECT c FROM CuentasContactos c WHERE c.eliminado = :eliminado")})
public class CuentasContactos implements Serializable {

    private static final long serialVersionUID = 1L;

    
    @Basic(optional = false)
    @NotNull
    @Column(name = "cargo")
    private String cargo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eliminado")
    private short eliminado;
   // @JoinColumn(name = "id_persona", referencedColumnName = "id")
    @Id
    @ManyToOne(cascade = CascadeType.PERSIST,optional = false)
    @JoinColumn(name="id_persona")
    private Personas persona;
  //  @JoinColumn(name = "id_cliente", referencedColumnName = "id")cascade = CascadeType.ALL,
    @Id
    @ManyToOne(cascade = CascadeType.PERSIST,optional = false)
    @JoinColumn(name="id_cuenta")
    private Cuentas cuenta;

    public CuentasContactos() {
    }

    public CuentasContactos(String cargo, short eliminado) {
        this.cargo = cargo;
        this.eliminado = eliminado;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    public Cuentas getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuentas cuenta) {
        this.cuenta = cuenta;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public short getEliminado() {
        return eliminado;
    }

    public void setEliminado(short eliminado) {
        this.eliminado = eliminado;
    }

    
    @Override
    public int hashCode() {
        return (persona.getId()!= null )? persona.getId().hashCode() + cuenta.getId().hashCode() : 0;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentasContactos)) {
            return false;
        }
        CuentasContactos other = (CuentasContactos) object;
        return (other.persona.getId() == this.persona.getId()) && (other.cuenta.getId() == this.cuenta.getId());
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.CuentasContactos[ id=" + cuenta.getId() + " ]";
    }
    
}
