package ar.com.norrmann.coqui.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Compra {

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date fechaCompra;

    @OneToMany(cascade = {CascadeType.ALL,CascadeType.REMOVE},mappedBy="compra")
    private Set<DetalleCompra> detalleList = new HashSet<DetalleCompra>();

    @ManyToOne
    private Proveedor proveedor;
    public BigDecimal getPrecioTotal(){
    	List<DetalleCompra> detalleList = DetalleCompra.findDetalleComprasByCompra(this).getResultList();
    	BigDecimal total = new BigDecimal(0);
    	if (detalleList==null||detalleList.isEmpty()){
    		return total;
    	}
    	for (DetalleCompra unDetalleCompra:detalleList){
    		total= total.add(unDetalleCompra.getPrecioTotal());
    	}
    	return total;
    	
    }
}
