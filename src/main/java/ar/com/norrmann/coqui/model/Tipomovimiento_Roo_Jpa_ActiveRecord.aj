// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.norrmann.coqui.model;

import ar.com.norrmann.coqui.model.Tipomovimiento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Tipomovimiento_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Tipomovimiento.entityManager;
    
    public static final EntityManager Tipomovimiento.entityManager() {
        EntityManager em = new Tipomovimiento().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Tipomovimiento.countTipomovimientoes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Tipomovimiento o", Long.class).getSingleResult();
    }
    
    public static List<Tipomovimiento> Tipomovimiento.findAllTipomovimientoes() {
        return entityManager().createQuery("SELECT o FROM Tipomovimiento o", Tipomovimiento.class).getResultList();
    }
    
    public static Tipomovimiento Tipomovimiento.findTipomovimiento(Long id) {
        if (id == null) return null;
        return entityManager().find(Tipomovimiento.class, id);
    }
    
    public static List<Tipomovimiento> Tipomovimiento.findTipomovimientoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Tipomovimiento o", Tipomovimiento.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Tipomovimiento.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Tipomovimiento.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Tipomovimiento attached = Tipomovimiento.findTipomovimiento(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Tipomovimiento.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Tipomovimiento.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Tipomovimiento Tipomovimiento.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Tipomovimiento merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
