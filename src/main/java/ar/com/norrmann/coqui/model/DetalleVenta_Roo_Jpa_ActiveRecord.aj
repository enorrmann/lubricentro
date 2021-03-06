// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.norrmann.coqui.model;

import ar.com.norrmann.coqui.model.DetalleVenta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect DetalleVenta_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager DetalleVenta.entityManager;
    
    public static final EntityManager DetalleVenta.entityManager() {
        EntityManager em = new DetalleVenta().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long DetalleVenta.countDetalleVentas() {
        return entityManager().createQuery("SELECT COUNT(o) FROM DetalleVenta o", Long.class).getSingleResult();
    }
    
    public static List<DetalleVenta> DetalleVenta.findAllDetalleVentas() {
        return entityManager().createQuery("SELECT o FROM DetalleVenta o", DetalleVenta.class).getResultList();
    }
    
    public static DetalleVenta DetalleVenta.findDetalleVenta(Long id) {
        if (id == null) return null;
        return entityManager().find(DetalleVenta.class, id);
    }
    
    public static List<DetalleVenta> DetalleVenta.findDetalleVentaEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM DetalleVenta o", DetalleVenta.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void DetalleVenta.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void DetalleVenta.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            DetalleVenta attached = DetalleVenta.findDetalleVenta(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void DetalleVenta.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void DetalleVenta.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public DetalleVenta DetalleVenta.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        DetalleVenta merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
