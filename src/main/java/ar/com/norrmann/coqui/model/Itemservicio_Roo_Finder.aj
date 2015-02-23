// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.norrmann.coqui.model;

import ar.com.norrmann.coqui.model.Itemservicio;
import ar.com.norrmann.coqui.model.Servicio;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Itemservicio_Roo_Finder {
    
    public static TypedQuery<Itemservicio> Itemservicio.findItemserviciosByServicioId(Servicio servicioId) {
        if (servicioId == null) throw new IllegalArgumentException("The servicioId argument is required");
        EntityManager em = Itemservicio.entityManager();
        TypedQuery<Itemservicio> q = em.createQuery("SELECT o FROM Itemservicio AS o WHERE o.servicioId = :servicioId", Itemservicio.class);
        q.setParameter("servicioId", servicioId);
        return q;
    }
    
}