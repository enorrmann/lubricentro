package ar.com.norrmann.coqui.model;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Query;
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

    public List<ar.com.norrmann.coqui.model.Venta> getVentas() {
        Query query = entityManager().createQuery("SELECT v FROM Venta v where v.cliente = :cliente", Venta.class);
        query.setParameter("cliente", this);
        return query.getResultList();
    }

    public BigDecimal getSaldo() {
        List<Venta> ventas = getVentas();
        if (ventas == null || ventas.isEmpty()) {
            return new BigDecimal(0);
        }
        BigDecimal saldo = new BigDecimal(0);
        for (Venta unaVenta : ventas) {
            saldo = saldo.add(unaVenta.getSaldo());
        }
        return saldo;
    }

    public String getNombreCompleto() {
        return getApellido() + ", " + getNombres();
    }
}
