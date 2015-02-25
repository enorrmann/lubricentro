package ar.com.norrmann.coqui.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Venta {

    @ManyToOne
    private Cliente cliente;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date fecha;

    @OneToMany(cascade = { javax.persistence.CascadeType.ALL, javax.persistence.CascadeType.REMOVE }, mappedBy = "venta")
    private Set<DetalleVenta> detalleList = new HashSet<DetalleVenta>();

    @Enumerated
    @NotNull
    private FormaDePago formaDePago;

    public List<Pago> getPagos() {
    	Query  query = entityManager().createQuery("SELECT p FROM Pago p where p.venta = :venta", Pago.class);
    	query.setParameter("venta", this);
        return query.getResultList();
    }   

    public BigDecimal getPrecioTotal() {
        List<DetalleVenta> detalleList = DetalleVenta.findDetalleVentasByVenta(this).getResultList();
        BigDecimal total = new BigDecimal(0);
        if (detalleList == null || detalleList.isEmpty()) {
            return total;
        }
        for (DetalleVenta unDetalleVenta : detalleList) {
            total = total.add(unDetalleVenta.getPrecioTotal());
        }
        return total;
    }
    
    public BigDecimal getTotalPagado() {
    	List<Pago> pagos = getPagos();
        BigDecimal totalPagado = new BigDecimal(0);
        if (pagos == null || pagos.isEmpty()) {
            return totalPagado;
        }
        for (Pago unPago : pagos) {
        	totalPagado = totalPagado.add(unPago.getImporte());
        }
        return totalPagado;
    }
    
    public BigDecimal getSaldo() {
    	if (formaDePago.equals(FormaDePago.Contado)){
    		return new BigDecimal(0);
    	}
        return getPrecioTotal().subtract(getTotalPagado());
    }
}
