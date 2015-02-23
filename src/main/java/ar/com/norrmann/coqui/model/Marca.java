package ar.com.norrmann.coqui.model;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooDbManaged(automaticallyDelete = true)
@RooJpaActiveRecord(versionField = "", table = "marca", finders = { "findMarcasByTipoItemId" })
public class Marca {

    public static TypedQuery<ar.com.norrmann.coqui.model.Marca> findMarcasByTipoItemId(Tipoitem tipoItemId) {
        if (tipoItemId == null) throw new IllegalArgumentException("The tipoItemId argument is required");
        EntityManager em = Marca.entityManager();
        TypedQuery<Marca> q = em.createQuery("SELECT o FROM Marca AS o WHERE o.tipoItemId = :tipoItemId order by o.marca", Marca.class);
        q.setParameter("tipoItemId", tipoItemId);
        return q;
    }

    public static TypedQuery<ar.com.norrmann.coqui.model.Marca> findMarcasAutomotor() {
        EntityManager em = Marca.entityManager();
        TypedQuery<Marca> q = em.createQuery("SELECT o FROM Marca AS o WHERE o.tipoItemId.id = 3 order by o.marca", Marca.class);
        return q;
    }
}
