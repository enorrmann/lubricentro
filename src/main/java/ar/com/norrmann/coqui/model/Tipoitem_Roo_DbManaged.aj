// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.norrmann.coqui.model;

import ar.com.norrmann.coqui.model.Categoria;
import ar.com.norrmann.coqui.model.Item;
import ar.com.norrmann.coqui.model.Marca;
import ar.com.norrmann.coqui.model.Tipoitem;
import ar.com.norrmann.coqui.model.Tipomovimiento;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

privileged aspect Tipoitem_Roo_DbManaged {
    
    @OneToMany(mappedBy = "tipoItemId")
    private Set<Categoria> Tipoitem.categorias;
    
    @OneToMany(mappedBy = "tipoItemId")
    private Set<Item> Tipoitem.items;
    
    @OneToMany(mappedBy = "tipoItemId")
    private Set<Marca> Tipoitem.marcas;
    
    @OneToMany(mappedBy = "tipoItemId")
    private Set<Tipomovimiento> Tipoitem.tipomovimientoes;
    
    @Column(name = "tipoItem", length = 255)
    @NotNull
    private String Tipoitem.tipoItem;
    
    public Set<Categoria> Tipoitem.getCategorias() {
        return categorias;
    }
    
    public void Tipoitem.setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }
    
    public Set<Item> Tipoitem.getItems() {
        return items;
    }
    
    public void Tipoitem.setItems(Set<Item> items) {
        this.items = items;
    }
    
    public Set<Marca> Tipoitem.getMarcas() {
        return marcas;
    }
    
    public void Tipoitem.setMarcas(Set<Marca> marcas) {
        this.marcas = marcas;
    }
    
    public Set<Tipomovimiento> Tipoitem.getTipomovimientoes() {
        return tipomovimientoes;
    }
    
    public void Tipoitem.setTipomovimientoes(Set<Tipomovimiento> tipomovimientoes) {
        this.tipomovimientoes = tipomovimientoes;
    }
    
    public String Tipoitem.getTipoItem() {
        return tipoItem;
    }
    
    public void Tipoitem.setTipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }
    
}
