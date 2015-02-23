package ar.com.norrmann.coqui.model;

import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findDetalleVentasByVenta" })
public class DetalleVenta {

    @NotNull
    @ManyToOne
    private Venta venta;

    private BigDecimal precioUnitario;
    
    @NotNull
    @ManyToOne
    private Item item;

    @NotNull
    private int cantidad;

    @ManyToOne(cascade = CascadeType.ALL)
    private Movimientoitem movimientoEgresoStock;

    public void updateMovimientoEgresoStock(Tipomovimiento tipoMovimiento) {
        if (movimientoEgresoStock == null) {
            movimientoEgresoStock = new Movimientoitem();
        }
        movimientoEgresoStock.updateFromDetalleVenta(this, tipoMovimiento);
    }
    
    public BigDecimal getPrecioTotal(){
    	if (precioUnitario==null) return null;
    	return precioUnitario.multiply(BigDecimal.valueOf(cantidad));
    	
    }
}
