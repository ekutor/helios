package com.co.hsg.innventa.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "acl_acciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AclAcciones.findAll", query = "SELECT a FROM AclAcciones a WHERE a.eliminado=0")
})
public class AclAcciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "id")
    private String id;
   
    @Basic(optional = false)
    @NotNull
    @Column(name = "eliminado")
    private short eliminado;
    @JoinColumn(name = "modulo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Parametros modulo;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "acceso")
    private short acceso;
     @Basic(optional = false)
    @NotNull
    @Column(name = "editar")
    private short editar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eliminar")
    private short eliminar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "exportar")
    private short exportar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "importar")
    private short importar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ver")
    private short ver;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ver_precios")
    private short verPrecios;
     
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accion")
    private List<AclRolesAccion> rolesList;
    
    public AclAcciones() {
    }

    public AclAcciones(String id) {
        this.id = id;
    }

    public AclAcciones(String id,  short eliminado) {
        this.id = id;
        this.eliminado = eliminado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public short getEliminado() {
        return eliminado;
    }

    public void setEliminado(short eliminado) {
        this.eliminado = eliminado;
    }

    public short getAcceso() {
        return acceso;
    }

    public void setAcceso(short acceso) {
        this.acceso = acceso;
    }

    public short getEditar() {
        return editar;
    }

    public void setEditar(short editar) {
        this.editar = editar;
    }

    public short getEliminar() {
        return eliminar;
    }

    public void setEliminar(short eliminar) {
        this.eliminar = eliminar;
    }

    public short getExportar() {
        return exportar;
    }

    public void setExportar(short exportar) {
        this.exportar = exportar;
    }

    public short getImportar() {
        return importar;
    }

    public void setImportar(short importar) {
        this.importar = importar;
    }

    public short getVer() {
        return ver;
    }

    public void setVer(short ver) {
        this.ver = ver;
    }

    public short getVerPrecios() {
        return verPrecios;
    }

    public void setVerPrecios(short verPrecios) {
        this.verPrecios = verPrecios;
    }
    
    public Parametros getModulo() {
        return modulo;
    }

    public void setModulo(Parametros modulo) {
        this.modulo = modulo;
    }
    
    @XmlTransient
    public List<AclRolesAccion> getRolesList() {
        if(rolesList == null){
           rolesList = new ArrayList<>(); 
        }
        return rolesList;
    }

    public void setRolesList(List<AclRolesAccion> rolesList) {
        this.rolesList = rolesList;
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
        if (!(object instanceof AclAcciones)) {
            return false;
        }
        AclAcciones other = (AclAcciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.AclAcciones[ id=" + id + " ]";
    }
    
}
