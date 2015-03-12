package ar.com.norrmann.coqui.jsf.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class GenericFilter {

	private Date fechaDesde = new Date();
	private Date fechaHasta = new Date();

	public Date getFechaDesde() {
		if (fechaDesde==null)return null;
		return DateUtils.truncate(fechaDesde,Calendar.DAY_OF_MONTH);
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFechaHasta() {
		if (fechaHasta==null)return null;
		return DateUtils.truncate(fechaHasta,Calendar.DAY_OF_MONTH);
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

}
