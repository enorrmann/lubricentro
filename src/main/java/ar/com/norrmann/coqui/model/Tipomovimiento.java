package ar.com.norrmann.coqui.model;

import javax.persistence.Transient;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "tipomovimiento")
@RooDbManaged(automaticallyDelete = true)
public class Tipomovimiento {

    @Transient
    public static final Long ID_MOVIMIENTO_INGRESO_STOCK = 1L;

    public static final Long ID_MOVIMIENTO_EGRESO_STOCK = 2L;

    public static final Long ID_MOVIMIENTO_INGRESO_INVENTARIO = 3L;

    public static final Long ID_MOVIMIENTO_EGRESO_INVENTARIO = 4L;

    public boolean isPositivo() {
        return getDiferencia() > 0;
    }

    public boolean isNegativo() {
        return getDiferencia() < 0;
    }
}
