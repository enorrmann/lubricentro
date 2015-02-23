package ar.com.norrmann.coqui.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooDbManaged(automaticallyDelete = true)
@RooJpaActiveRecord(versionField = "", table = "categoria", finders = { "findCategoriasByTipoItemId" })
public class Categoria {

    public static TypedQuery<ar.com.norrmann.coqui.model.Categoria> findCategoriasByTipoItemId(Tipoitem tipoItemId) {
        if (tipoItemId == null) throw new IllegalArgumentException("The tipoItemId argument is required");
        EntityManager em = Categoria.entityManager();
        TypedQuery<Categoria> q = em.createQuery("SELECT o FROM Categoria AS o WHERE o.tipoItemId = :tipoItemId order by o.categoria", Categoria.class);
        q.setParameter("tipoItemId", tipoItemId);
        return q;
    }

    public static List<ar.com.norrmann.coqui.model.Categoria> findAllCategorias() {
        return entityManager().createQuery("SELECT o FROM Categoria o order by o.categoria", Categoria.class).getResultList();
    }
}
