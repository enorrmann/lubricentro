package ar.com.norrmann.coqui.model.dto;

import java.math.BigDecimal;

public class ResumenItemDto {
	private Long idTipoItem;
	private String tipoItem;
	private Long idCategoria;
	private String categoria;
	private Long idMarca;
	private String marca;
	private Long idItem;
	private String item;
	private int stockActual;
	private BigDecimal sumaCosto;
	private BigDecimal sumaVenta;

	public ResumenItemDto(Object[] unObjectArray) {
		idTipoItem = getLong(unObjectArray[0]);
		tipoItem = (String) unObjectArray[1];
		idCategoria = getLong(unObjectArray[2]);
		categoria = (String) unObjectArray[3];
		idMarca = getLong(unObjectArray[4]);
		marca = (String) unObjectArray[5];
		idItem = getLong(unObjectArray[6]);
		item = (String) unObjectArray[7];
		stockActual = getInteger(unObjectArray[8]);
		sumaCosto = (BigDecimal) unObjectArray[9];
		sumaVenta = (BigDecimal) unObjectArray[10];
	}

	private Long getLong(Object o) {
		if (o == null)
			return 0L;
		try {
			return ((BigDecimal) o).longValue();
		} catch (Exception e) {
			return 0L;
		}
	}
	private Integer getInteger(Object o) {
		if (o == null)
			return 0;
		try {
			return ((BigDecimal) o).intValue();
		} catch (Exception e) {
			return 0;
		}
	}

	public Long getIdTipoItem() {
		return idTipoItem;
	}

	public void setIdTipoItem(Long idTipoItem) {
		this.idTipoItem = idTipoItem;
	}

	public String getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(String tipoItem) {
		this.tipoItem = tipoItem;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Long getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Long getIdItem() {
		return idItem;
	}

	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getStockActual() {
		return stockActual;
	}

	public void setStockActual(int stockActual) {
		this.stockActual = stockActual;
	}

	public BigDecimal getSumaCosto() {
		return sumaCosto;
	}

	public void setSumaCosto(BigDecimal sumaCosto) {
		this.sumaCosto = sumaCosto;
	}

	public BigDecimal getSumaVenta() {
		return sumaVenta;
	}

	public void setSumaVenta(BigDecimal sumaVenta) {
		this.sumaVenta = sumaVenta;
	}
}
