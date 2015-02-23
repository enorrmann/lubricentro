// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.norrmann.coqui.model;

import ar.com.norrmann.coqui.model.Itemservicio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Itemservicio_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Itemservicio.entityManager;
    
    public static final EntityManager Itemservicio.entityManager() {
        EntityManager em = new Itemservicio().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Itemservicio.countItemservicios() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Itemservicio o", Long.class).getSingleResult();
    }
    
    public static List<Itemservicio> Itemservicio.findAllItemservicios() {
        return entityManager().createQuery("SELECT o FROM Itemservicio o", Itemservicio.class).getResultList();
    }
    
    public static Itemservicio Itemservicio.findItemservicio(Long id) {
        if (id == null) return null;
        return entityManager().find(Itemservicio.class, id);
    }
    
    public static List<Itemservicio> Itemservicio.findItemservicioEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Itemservicio o", Itemservicio.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Itemservicio.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Itemservicio.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Itemservicio attached = Itemservicio.findItemservicio(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Itemservicio.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Itemservicio.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Itemservicio Itemservicio.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Itemservicio merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}