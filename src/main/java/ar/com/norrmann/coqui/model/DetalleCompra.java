package ar.com.norrmann.coqui.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findDetalleComprasByCompra" })
public class DetalleCompra {

    @NotNull
    @ManyToOne
    private Compra compra;

    private BigDecimal precioUnitario;

    @NotNull
    @ManyToOne
    private Item item;

    @NotNull
    private int cantidad=1;

    @ManyToOne(cascade=CascadeType.ALL)
    private Movimientoitem movimientoIngresoStock;

	public void updateMovimientoIngresoStock(Tipomovimiento tipoMovimiento) {
		if (movimientoIngresoStock==null){
			movimientoIngresoStock = new Movimientoitem();
		}
		movimientoIngresoStock.updateFromDetalleCompra(this, tipoMovimiento);
	}
    public BigDecimal getPrecioTotal(){
    	if (precioUnitario==null) return null;
    	return precioUnitario.multiply(BigDecimal.valueOf(cantidad));
    	
    }
}
