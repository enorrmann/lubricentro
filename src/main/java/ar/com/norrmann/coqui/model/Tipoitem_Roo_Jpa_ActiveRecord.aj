// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.norrmann.coqui.model;

import ar.com.norrmann.coqui.model.Tipoitem;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Tipoitem_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Tipoitem.entityManager;
    
    public static final EntityManager Tipoitem.entityManager() {
        EntityManager em = new Tipoitem().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Tipoitem.countTipoitems() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Tipoitem o", Long.class).getSingleResult();
    }
    
    public static List<Tipoitem> Tipoitem.findAllTipoitems() {
        return entityManager().createQuery("SELECT o FROM Tipoitem o", Tipoitem.class).getResultList();
    }
    
    public static Tipoitem Tipoitem.findTipoitem(Long id) {
        if (id == null) return null;
        return entityManager().find(Tipoitem.class, id);
    }
    
    public static List<Tipoitem> Tipoitem.findTipoitemEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Tipoitem o", Tipoitem.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Tipoitem.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Tipoitem.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Tipoitem attached = Tipoitem.findTipoitem(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Tipoitem.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Tipoitem.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Tipoitem Tipoitem.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Tipoitem merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}