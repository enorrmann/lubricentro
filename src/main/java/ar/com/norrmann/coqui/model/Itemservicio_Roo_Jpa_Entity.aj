// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.norrmann.coqui.model;

import ar.com.norrmann.coqui.model.Itemservicio;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

privileged aspect Itemservicio_Roo_Jpa_Entity {
    
    declare @type: Itemservicio: @Entity;
    
    declare @type: Itemservicio: @Table(name = "itemservicio");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Itemservicio.id;
    
    public Long Itemservicio.getId() {
        return this.id;
    }
    
    public void Itemservicio.setId(Long id) {
        this.id = id;
    }
    
}