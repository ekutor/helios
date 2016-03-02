package com.co.hsg.innventa.beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author Hector Sanchez Garcia
 */
@Entity
@Table(name = "lista_precios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListaPrecios.findAll", query = "SELECT l FROM ListaPrecios l"),
    @NamedQuery(name = "ListaPrecios.findById", query = "SELECT l FROM ListaPrecios l WHERE l.id = :id"),
    @NamedQuery(name = "ListaPrecios.findByNombre", query = "SELECT l FROM ListaPrecios l WHERE l.nombre = :nombre"),
    @NamedQuery(name = "ListaPrecios.findByPorcentaje", query = "SELECT l FROM ListaPrecios l WHERE l.porcentaje = :porcentaje"),
    @NamedQuery(name = "ListaPrecios.findByEliminado", query = "SELECT l FROM ListaPrecios l WHERE l.eliminado = :eliminado")})
public class ListaPrecios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    private boolean porcentaje;
    @Basic(optional = false)
    @NotNull
    private short valor_porcentaje;
    @Basic(optional = false)
    @NotNull
    private short eliminado;
    @OneToMany(mappedBy = "listaPrecios")
    private List<Cuentas> cuentasList;

    public ListaPrecios() {
    }

    public ListaPrecios(String id) {
        this.id = id;
    }

    public ListaPrecios(String id, String nombre, boolean porcentaje, short eliminado) {
        this.id = id;
        this.nombre = nombre;
        this.porcentaje = porcentaje;
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

    public boolean getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(boolean porcentaje) {
        this.porcentaje = porcentaje;
    }

    public short getEliminado() {
        return eliminado;
    }

    public short getValor_porcentaje() {
        return valor_porcentaje;
    }

    public void setValor_porcentaje(short valor_porcentaje) {
        this.valor_porcentaje = valor_porcentaje;
    }

    public void setEliminado(short eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public List<Cuentas> getCuentasList() {
        return cuentasList;
    }

    public void setCuentasList(List<Cuentas> cuentasList) {
        this.cuentasList = cuentasList;
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
        if (!(object instanceof ListaPrecios)) {
            return false;
        }
        ListaPrecios other = (ListaPrecios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.ListaPrecios[ id=" + id + " ]";
    }

}
