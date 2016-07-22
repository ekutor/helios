package com.co.hsg.innventa.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hectsaga
 */
@Entity
@Table(name = "productos_componentes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductosComponentes.findAll", query = "SELECT p FROM ProductosComponentes p"),
    @NamedQuery(name = "ProductosComponentes.findById", query = "SELECT p FROM ProductosComponentes p WHERE p.id = :id"),
    @NamedQuery(name = "ProductosComponentes.delete", query = "DELETE FROM ProductosComponentes p WHERE p.id =:id"),
})
public class ProductosComponentes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    
    @JoinColumn(name = "id_padre", referencedColumnName = "id")
    @ManyToOne( cascade = CascadeType.ALL,optional = true)
    private Productos productoPadre;
    
    @JoinColumn(name = "id_componente", referencedColumnName = "id")
    @ManyToOne( cascade = CascadeType.ALL,optional = true)
    private Productos componente;
  

    public ProductosComponentes() {
    }

    public ProductosComponentes(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Productos getProductoPadre() {
        return productoPadre;
    }

    public void setProductoPadre(Productos productoPadre) {
        this.productoPadre = productoPadre;
    }

    public Productos getComponente() {
        return componente;
    }

    public void setComponente(Productos componente) {
        this.componente = componente;
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
        if (!(object instanceof ProductosComponentes)) {
            return false;
        }
        ProductosComponentes other = (ProductosComponentes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.ProductosComponentes[ id=" + id + " ]";
    }
    
}
