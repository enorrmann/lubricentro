// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.norrmann.coqui.model;

import ar.com.norrmann.coqui.model.DetalleVenta;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect DetalleVenta_Roo_Jpa_Entity {
    
    declare @type: DetalleVenta: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long DetalleVenta.id;
    
    @Version
    @Column(name = "version")
    private Integer DetalleVenta.version;
    
    public Long DetalleVenta.getId() {
        return this.id;
    }
    
    public void DetalleVenta.setId(Long id) {
        this.id = id;
    }
    
    public Integer DetalleVenta.getVersion() {
        return this.version;
    }
    
    public void DetalleVenta.setVersion(Integer version) {
        this.version = version;
    }
    
}
