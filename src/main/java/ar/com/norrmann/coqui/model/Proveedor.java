package ar.com.norrmann.coqui.model;

import java.util.List;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "proveedor")
@RooDbManaged(automaticallyDelete = true)
public class Proveedor {

    public static List<ar.com.norrmann.coqui.model.Proveedor> findAllProveedors() {
        return entityManager().createQuery("SELECT o FROM Proveedor o order by o.nombre", Proveedor.class).getResultList();
    }
}
