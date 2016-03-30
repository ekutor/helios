/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.hsg.innventa.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hectsaga
 */
@Entity
@Table(name = "categorias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categorias.findAll", query = "SELECT c FROM Categorias c WHERE c.eliminado=0"),
    @NamedQuery(name = "Categorias.findById", query = "SELECT c FROM Categorias c WHERE c.id = :id"),
    @NamedQuery(name = "Categorias.findByNombre", query = "SELECT c FROM Categorias c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Categorias.findByEliminado", query = "SELECT c FROM Categorias c WHERE c.eliminado = :eliminado"),
    @NamedQuery(name = "Categorias.findByModulo", query = "SELECT c FROM Categorias c WHERE c.eliminado =0 AND c.modulo = :modulo"),
    @NamedQuery(name = "Categorias.delete", query = "UPDATE Categorias c SET c.eliminado = 1 WHERE c.id =:id")})

public class Categorias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Lob
    @Column(name = "imagen")
    private byte[] imagen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "modulo")
    private String modulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eliminado")
    private short eliminado;
    @OneToMany(mappedBy = "idpadre")
    private List<Categorias> categoriasList;
    @JoinColumn(name = "idpadre", referencedColumnName = "id")
    @ManyToOne
    private Categorias idpadre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
    private Collection<Productos> productosCollection;

    public Categorias() {
    }

    public Categorias(String id) {
        this.id = id;
    }

    public Categorias(String id, String nombre, short eliminado) {
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
    
    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public short getEliminado() {
        return eliminado;
    }

    public void setEliminado(short eliminado) {
        this.eliminado = eliminado;
    }
    
    @XmlTransient
    public Collection<Productos> getProductosCollection() {
        return productosCollection;
    }

    public void setProductosCollection(Collection<Productos> productosCollection) {
        this.productosCollection = productosCollection;
    }
    
    @XmlTransient
    public List<Categorias> getCategoriasList() {
        return categoriasList;
    }

    public void setCategoriasList(List<Categorias> categoriasList) {
        this.categoriasList = categoriasList;
    }

    public Categorias getIdpadre() {
        return idpadre;
    }

    public void setIdpadre(Categorias idpadre) {
        this.idpadre = idpadre;
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
        if (!(object instanceof Categorias)) {
            return false;
        }
        Categorias other = (Categorias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.Categorias[ id=" + id + " ]";
    }
    
}
