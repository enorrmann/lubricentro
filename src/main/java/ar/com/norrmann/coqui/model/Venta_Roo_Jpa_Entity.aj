// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.norrmann.coqui.model;

import ar.com.norrmann.coqui.model.Venta;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect Venta_Roo_Jpa_Entity {
    
    declare @type: Venta: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Venta.id;
    
    @Version
    @Column(name = "version")
    private Integer Venta.version;
    
    public Long Venta.getId() {
        return this.id;
    }
    
    public void Venta.setId(Long id) {
        this.id = id;
    }
    
    public Integer Venta.getVersion() {
        return this.version;
    }
    
    public void Venta.setVersion(Integer version) {
        this.version = version;
    }
    
}
