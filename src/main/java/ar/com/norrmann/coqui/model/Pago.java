package ar.com.norrmann.coqui.model;

import ar.com.norrmann.coqui.jsf.TipoDePago;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findPagoesByFechaEquals" })
public class Pago {

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date fecha = new Date();

    @NotNull
    private BigDecimal importe;

    @ManyToOne
    private Venta venta;

    private String observaciones;

    @Enumerated
    @NotNull
    private TipoDePago tipodePago;
}
