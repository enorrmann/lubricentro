// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.norrmann.coqui.model;

import ar.com.norrmann.coqui.model.Compra;
import ar.com.norrmann.coqui.model.DetalleCompra;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect DetalleCompra_Roo_Finder {
    
    public static TypedQuery<DetalleCompra> DetalleCompra.findDetalleComprasByCompra(Compra compra) {
        if (compra == null) throw new IllegalArgumentException("The compra argument is required");
        EntityManager em = DetalleCompra.entityManager();
        TypedQuery<DetalleCompra> q = em.createQuery("SELECT o FROM DetalleCompra AS o WHERE o.compra = :compra", DetalleCompra.class);
        q.setParameter("compra", compra);
        return q;
    }
    
}