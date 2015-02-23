// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.norrmann.coqui.model;

import ar.com.norrmann.coqui.model.Proveedor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Proveedor_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Proveedor.entityManager;
    
    public static final EntityManager Proveedor.entityManager() {
        EntityManager em = new Proveedor().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Proveedor.countProveedors() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Proveedor o", Long.class).getSingleResult();
    }
    
    public static Proveedor Proveedor.findProveedor(Long id) {
        if (id == null) return null;
        return entityManager().find(Proveedor.class, id);
    }
    
    public static List<Proveedor> Proveedor.findProveedorEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Proveedor o", Proveedor.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Proveedor.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Proveedor.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Proveedor attached = Proveedor.findProveedor(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Proveedor.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Proveedor.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Proveedor Proveedor.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Proveedor merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
