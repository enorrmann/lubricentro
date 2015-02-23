package ar.com.norrmann.coqui.jsf.util;

import java.math.BigDecimal;

import ar.com.norrmann.coqui.model.Categoria;
import ar.com.norrmann.coqui.model.Marca;

public class FiltroItem {
	private Marca marca;
	private Categoria categoria;
	private BigDecimal porcentajeAAumentar;
	private BigDecimal importeAAumentar;

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public boolean esVacio(){
		return marca==null&&categoria==null;
	}
	public boolean esCompleto(){
		return marca!=null&&categoria!=null;
	}

	public BigDecimal getPorcentajeAAumentar() {
		return porcentajeAAumentar;
	}

	public void setPorcentajeAAumentar(BigDecimal porcentajeAAumentar) {
		this.porcentajeAAumentar = porcentajeAAumentar;
	}

	public BigDecimal getImporteAAumentar() {
		return importeAAumentar;
	}

	public void setImporteAAumentar(BigDecimal importeAAumentar) {
		this.importeAAumentar = importeAAumentar;
	}
	public boolean tieneAumento(){
		return porcentajeAAumentar!=null&&porcentajeAAumentar.intValue()>0;
	}
	public boolean tieneRebaja(){
		return porcentajeAAumentar!=null&&porcentajeAAumentar.intValue()<0;
	}
}
