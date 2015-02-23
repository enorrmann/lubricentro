package ar.com.norrmann.coqui.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooDbManaged(automaticallyDelete = true)
@RooJpaActiveRecord(versionField = "", table = "servicio", finders = { "findServiciosByAutomotorId" })
public class Servicio {

    @Column(name = "kilometros")
    private Long kilometros;

    @Column(name = "fecha")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date fecha;

    public static TypedQuery<ar.com.norrmann.coqui.model.Servicio> findServiciosByAutomotorId(Automotor automotorId) {
        if (automotorId == null) throw new IllegalArgumentException("The automotorId argument is required");
        EntityManager em = Servicio.entityManager();
        TypedQuery<Servicio> q = em.createQuery("SELECT o FROM Servicio AS o WHERE o.automotorId = :automotorId order by o.fecha", Servicio.class);
        q.setParameter("automotorId", automotorId);
        return q;
    }
}
