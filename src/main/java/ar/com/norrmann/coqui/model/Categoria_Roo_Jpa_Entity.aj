// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.norrmann.coqui.model;

import ar.com.norrmann.coqui.model.Categoria;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

privileged aspect Categoria_Roo_Jpa_Entity {
    
    declare @type: Categoria: @Entity;
    
    declare @type: Categoria: @Table(name = "categoria");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Categoria.id;
    
    public Long Categoria.getId() {
        return this.id;
    }
    
    public void Categoria.setId(Long id) {
        this.id = id;
    }
    
}
