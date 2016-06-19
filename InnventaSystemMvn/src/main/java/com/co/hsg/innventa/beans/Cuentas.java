package com.co.hsg.innventa.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
@Table(name = "cuentas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuentas.findAll", query = "SELECT c FROM Cuentas c WHERE c.eliminado=0"),
    @NamedQuery(name = "Cuentas.findById", query = "SELECT c FROM Cuentas c WHERE c.id = :id"),
    @NamedQuery(name = "Cuentas.findByNombre", query = "SELECT c FROM Cuentas c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cuentas.findByTipoCliente", query = "SELECT c FROM Cuentas c WHERE c.tipoCliente = :tipoCliente"),
    @NamedQuery(name = "Cuentas.findByEstado", query = "SELECT c FROM Cuentas c WHERE c.estado = :estado"),
    @NamedQuery(name = "Cuentas.findByEliminado", query = "SELECT c FROM Cuentas c WHERE c.eliminado = :eliminado"),
    @NamedQuery(name = "Cuentas.findByCreadoPor", query = "SELECT c FROM Cuentas c WHERE c.creadoPor = :creadoPor"),
    @NamedQuery(name = "Cuentas.findByFechaCreacion", query = "SELECT c FROM Cuentas c WHERE c.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Cuentas.findByModificadoPor", query = "SELECT c FROM Cuentas c WHERE c.modificadoPor = :modificadoPor"),
    @NamedQuery(name = "Cuentas.findByFechaModificacion", query = "SELECT c FROM Cuentas c WHERE c.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "Cuentas.delete", query = "UPDATE Cuentas e SET e.eliminado = 1 WHERE e.id =:id")})
public class Cuentas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Size(min = 1, max = 36)
    @Column(name = "tipo_cliente")
    private String tipoCliente;
    @JoinColumn(name = "estado", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Estados estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eliminado")
    private short eliminado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "creado_por")
    private String creadoPor;
    @JoinColumn(name = "lista_precios", referencedColumnName = "id")
    @ManyToOne
    private ListaPrecios listaPrecios;
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente")
    private List<Pedidos> pedidosList;
    
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy="cuenta")
    private List<CuentasContactos> persona;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente")
    private List<CuentasDireccion> cuentasDireccionList;

    public Cuentas() {
    }

    public Cuentas(String id) {
        this.id = id;
    }

    public Cuentas(String id, String nombre, String tipoCliente,short eliminado, String creadoPor, Date fechaCreacion, String modificadoPor, Date fechaModificacion) {
        this.id = id;
        this.nombre = nombre;
        this.tipoCliente = tipoCliente;
        this.eliminado = eliminado;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.modificadoPor = modificadoPor;
        this.fechaModificacion = fechaModificacion;
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

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public short getEliminado() {
        return eliminado;
    }
    
    public ListaPrecios getListaPrecios() {
        return listaPrecios;
    }

    public void setListaPrecios(ListaPrecios listaPrecios) {
        this.listaPrecios = listaPrecios;
    }
    public void setEliminado(short eliminado) {
        this.eliminado = eliminado;
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

    @XmlTransient
    public List<Pedidos> getPedidosList() {
        return pedidosList;
    }

    public void setPedidosList(List<Pedidos> pedidosList) {
        this.pedidosList = pedidosList;
    }

    @XmlTransient
    public List<CuentasContactos> getPersona() {
        if(persona == null){
            persona = new ArrayList<CuentasContactos>();
        }
        return persona;
    }

    public void setPersona(List<CuentasContactos> persona) {
        this.persona = persona;
    }
    
     public void addContact( Personas persona, String cargo ) {
       CuentasContactos asociation = new CuentasContactos();
       asociation.setCargo(cargo);
       asociation.setPersona(persona);
       asociation.setEliminado((short)0);
       asociation.setCuenta(this);
       
       this.getPersona().add(asociation);
       persona.getCuenta().add(asociation);
    }

    @XmlTransient
    public List<CuentasDireccion> getCuentasDireccionList() {
        return cuentasDireccionList;
    }

    public void setCuentasDireccionList(List<CuentasDireccion> cuentasDireccionList) {
        this.cuentasDireccionList = cuentasDireccionList;
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
        if (!(object instanceof Cuentas)) {
            return false;
        }
        Cuentas other = (Cuentas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.hsg.innventa.beans.Cuentas[ id=" + id + " ]";
    }
    
}
