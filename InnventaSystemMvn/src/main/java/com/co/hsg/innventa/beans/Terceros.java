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
    @NamedQuery(name = "Terceros.findAll", query = "SELECT t FROM Terceros t"),
    @NamedQuery(name = "Terceros.findById", query = "SELECT t FROM Terceros t WHERE t.id = :id"),
    @NamedQuery(name = "Terceros.findByNombre", query = "SELECT t FROM Terceros t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Terceros.findByObservaciones", query = "SELECT t FROM Terceros t WHERE t.observaciones = :observaciones"),
    @NamedQuery(name = "Terceros.findByDireccion", query = "SELECT t FROM Terceros t WHERE t.direccion = :direccion"),
    @NamedQuery(name = "Terceros.findByContacto", query = "SELECT t FROM Terceros t WHERE t.contacto = :contacto"),
    @NamedQuery(name = "Terceros.findByTelefono", query = "SELECT t FROM Terceros t WHERE t.telefono = :telefono"),
    @NamedQuery(name = "Terceros.findByCreadoPor", query = "SELECT t FROM Terceros t WHERE t.creadoPor = :creadoPor"),
    @NamedQuery(name = "Terceros.findByFechaCreacion", query = "SELECT t FROM Terceros t WHERE t.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Terceros.findByModificadoPor", query = "SELECT t FROM Terceros t WHERE t.modificadoPor = :modificadoPor"),
    @NamedQuery(name = "Terceros.findByFechaModificacion", query = "SELECT t FROM Terceros t WHERE t.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "Terceros.findByEliminado", query = "SELECT t FROM Terceros t WHERE t.eliminado = :eliminado")})
public class Terceros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    private String nombre;
    @Size(max = 500)
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    private String contacto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "creado_por")
    private String creadoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "modificado_por")
    private String modificadoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Basic(optional = false)
    @NotNull
    private short eliminado;

    public Terceros() {
    }

    public Terceros(String id) {
        this.id = id;
    }

    public Terceros(String id, String nombre, String direccion, String contacto, String telefono, String creadoPor, Date fechaCreacion, String modificadoPor, Date fechaModificacion, short eliminado) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.contacto = contacto;
        this.telefono = telefono;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.modificadoPor = modificadoPor;
        this.fechaModificacion = fechaModificacion;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
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
        if (!(object instanceof Terceros)) {
            return false;
        }
        Terceros other = (Terceros) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.Terceros[ id=" + id + " ]";
    }
    
}
