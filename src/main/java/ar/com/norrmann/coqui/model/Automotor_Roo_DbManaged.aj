// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.norrmann.coqui.model;

import ar.com.norrmann.coqui.model.Automotor;
import ar.com.norrmann.coqui.model.Cliente;
import ar.com.norrmann.coqui.model.Modelo;
import ar.com.norrmann.coqui.model.Servicio;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

privileged aspect Automotor_Roo_DbManaged {
    
    @OneToMany(mappedBy = "automotorId")
    private Set<Servicio> Automotor.servicios;
    
    @ManyToOne
    @JoinColumn(name = "propietario_id", referencedColumnName = "id")
    private Cliente Automotor.propietarioId;
    
    @ManyToOne
    @JoinColumn(name = "modelo_id", referencedColumnName = "id")
    private Modelo Automotor.modeloId;
    
    @Column(name = "anio")
    @NotNull
    private Integer Automotor.anio;
    
    @Column(name = "dominio", length = 255)
    @NotNull
    private String Automotor.dominio;
    
    public Set<Servicio> Automotor.getServicios() {
        return servicios;
    }
    
    public void Automotor.setServicios(Set<Servicio> servicios) {
        this.servicios = servicios;
    }
    
    public Cliente Automotor.getPropietarioId() {
        return propietarioId;
    }
    
    public void Automotor.setPropietarioId(Cliente propietarioId) {
        this.propietarioId = propietarioId;
    }
    
    public Modelo Automotor.getModeloId() {
        return modeloId;
    }
    
    public void Automotor.setModeloId(Modelo modeloId) {
        this.modeloId = modeloId;
    }
    
    public Integer Automotor.getAnio() {
        return anio;
    }
    
    public void Automotor.setAnio(Integer anio) {
        this.anio = anio;
    }
    
    public String Automotor.getDominio() {
        return dominio;
    }
    
    public void Automotor.setDominio(String dominio) {
        this.dominio = dominio;
    }
    
}
