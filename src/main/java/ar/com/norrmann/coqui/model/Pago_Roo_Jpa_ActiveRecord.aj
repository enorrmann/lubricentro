// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.norrmann.coqui.model;

import ar.com.norrmann.coqui.model.Pago;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Pago_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Pago.entityManager;
    
    public static final EntityManager Pago.entityManager() {
        EntityManager em = new Pago().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Pago.countPagoes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Pago o", Long.class).getSingleResult();
    }
    
    public static List<Pago> Pago.findAllPagoes() {
        return entityManager().createQuery("SELECT o FROM Pago o", Pago.class).getResultList();
    }
    
    public static Pago Pago.findPago(Long id) {
        if (id == null) return null;
        return entityManager().find(Pago.class, id);
    }
    
    public static List<Pago> Pago.findPagoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Pago o", Pago.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Pago.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Pago.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Pago attached = Pago.findPago(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Pago.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Pago.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Pago Pago.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Pago merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
