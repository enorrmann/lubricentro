// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.norrmann.coqui.model;

import ar.com.norrmann.coqui.model.Servicio;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

privileged aspect Servicio_Roo_Jpa_Entity {
    
    declare @type: Servicio: @Entity;
    
    declare @type: Servicio: @Table(name = "servicio");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Servicio.id;
    
    public Long Servicio.getId() {
        return this.id;
    }
    
    public void Servicio.setId(Long id) {
        this.id = id;
    }
    
}