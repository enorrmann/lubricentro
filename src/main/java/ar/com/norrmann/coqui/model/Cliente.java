package ar.com.norrmann.coqui.model;

import java.util.List;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "cliente")
@RooDbManaged(automaticallyDelete = true)
public class Cliente {

    public static List<ar.com.norrmann.coqui.model.Cliente> findAllClientes() {
        return entityManager().createQuery("SELECT o FROM Cliente o order by o.apellido, o.nombres", Cliente.class).getResultList();
    }

    public String getNombreCompleto() {
        return getApellido() + ", " + getNombres();
    }
}
