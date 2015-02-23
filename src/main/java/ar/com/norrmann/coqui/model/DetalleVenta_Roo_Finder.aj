// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.norrmann.coqui.model;

import ar.com.norrmann.coqui.model.DetalleVenta;
import ar.com.norrmann.coqui.model.Venta;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect DetalleVenta_Roo_Finder {
    
    public static TypedQuery<DetalleVenta> DetalleVenta.findDetalleVentasByVenta(Venta venta) {
        if (venta == null) throw new IllegalArgumentException("The venta argument is required");
        EntityManager em = DetalleVenta.entityManager();
        TypedQuery<DetalleVenta> q = em.createQuery("SELECT o FROM DetalleVenta AS o WHERE o.venta = :venta", DetalleVenta.class);
        q.setParameter("venta", venta);
        return q;
    }
    
}
