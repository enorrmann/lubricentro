package ar.com.norrmann.coqui.model;

import java.util.List;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "tipoitem")
@RooDbManaged(automaticallyDelete = true)
public class Tipoitem {

    public static final Long ID_PRODUCTO = 2L;

    public static final Long ID_HERRAMIENTA = 1L;

    public static List<ar.com.norrmann.coqui.model.Tipoitem> findAllTipoitemsExcept(Long id) {
        return entityManager().createQuery("SELECT o FROM Tipoitem o WHERE o.id != :id", Tipoitem.class).setParameter("id", id).getResultList();
    }
}
