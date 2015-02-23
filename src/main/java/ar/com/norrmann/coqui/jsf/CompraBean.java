package ar.com.norrmann.coqui.jsf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;

import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.message.Message;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

import ar.com.norrmann.coqui.jsf.converter.ItemConverter;
import ar.com.norrmann.coqui.jsf.converter.ProveedorConverter;
import ar.com.norrmann.coqui.jsf.util.MensajeUtil;
import ar.com.norrmann.coqui.model.Compra;
import ar.com.norrmann.coqui.model.DetalleCompra;
import ar.com.norrmann.coqui.model.DetalleVenta;
import ar.com.norrmann.coqui.model.Item;
import ar.com.norrmann.coqui.model.Proveedor;
import ar.com.norrmann.coqui.model.Tipomovimiento;

@RooSerializable
@RooJsfManagedBean(entity = Compra.class, beanName = "compraBean")
public class CompraBean {

	private Compra compra;
	private String codigoProducto;
	private List<DetalleCompra> detalleCompraList;

	public HtmlPanelGrid populateCreatePanel() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Application application = facesContext.getApplication();
		ExpressionFactory expressionFactory = application
				.getExpressionFactory();
		ELContext elContext = facesContext.getELContext();

		HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application
				.createComponent(HtmlPanelGrid.COMPONENT_TYPE);

		HtmlOutputText proveedorCreateOutput = (HtmlOutputText) application
				.createComponent(HtmlOutputText.COMPONENT_TYPE);
		proveedorCreateOutput.setId("proveedorCreateOutput");
		proveedorCreateOutput.setValue("Proveedor:   ");
		htmlPanelGrid.getChildren().add(proveedorCreateOutput);

		AutoComplete proveedorCreateInput = (AutoComplete) application
				.createComponent(AutoComplete.COMPONENT_TYPE);
		proveedorCreateInput.setId("proveedorCreateInput");
		proveedorCreateInput.setValueExpression("value", expressionFactory
				.createValueExpression(elContext,
						"#{compraBean.compra.proveedor}", Proveedor.class));
		proveedorCreateInput.setCompleteMethod(expressionFactory
				.createMethodExpression(elContext,
						"#{compraBean.completeProveedor}", List.class,
						new Class[] { String.class }));
		proveedorCreateInput.setDropdown(true);
		proveedorCreateInput.setValueExpression("var", expressionFactory
				.createValueExpression(elContext, "proveedor", String.class));
		proveedorCreateInput.setValueExpression("itemLabel", expressionFactory
				.createValueExpression(elContext, "#{proveedor.nombre}",
						String.class));
		proveedorCreateInput.setValueExpression("itemValue", expressionFactory
				.createValueExpression(elContext, "#{proveedor}",
						Proveedor.class));
		proveedorCreateInput.setConverter(new ProveedorConverter());
		proveedorCreateInput.setRequired(true);
		proveedorCreateInput.setLabel("Proveedor");
		htmlPanelGrid.getChildren().add(proveedorCreateInput);

		Message proveedorCreateInputMessage = (Message) application
				.createComponent(Message.COMPONENT_TYPE);
		proveedorCreateInputMessage.setId("proveedorCreateInputMessage");
		proveedorCreateInputMessage.setFor("proveedorCreateInput");
		proveedorCreateInputMessage.setDisplay("icon");
		htmlPanelGrid.getChildren().add(proveedorCreateInputMessage);

		HtmlOutputText fechaCompraCreateOutput = (HtmlOutputText) application
				.createComponent(HtmlOutputText.COMPONENT_TYPE);
		fechaCompraCreateOutput.setId("fechaCompraCreateOutput");
		fechaCompraCreateOutput.setValue("Fecha Compra:   ");
		htmlPanelGrid.getChildren().add(fechaCompraCreateOutput);

		Calendar fechaCompraCreateInput = (Calendar) application
				.createComponent(Calendar.COMPONENT_TYPE);
		fechaCompraCreateInput.setId("fechaCompraCreateInput");
		fechaCompraCreateInput.setValueExpression("value", expressionFactory
				.createValueExpression(elContext,
						"#{compraBean.compra.fechaCompra}", Date.class));
		fechaCompraCreateInput.setNavigator(true);
		fechaCompraCreateInput.setEffect("slideDown");
		fechaCompraCreateInput.setPattern("dd/MM/yyyy");
		fechaCompraCreateInput.setLabel("Fecha de compra");
		fechaCompraCreateInput.setRequired(true);
		htmlPanelGrid.getChildren().add(fechaCompraCreateInput);

		Message fechaCompraCreateInputMessage = (Message) application
				.createComponent(Message.COMPONENT_TYPE);
		fechaCompraCreateInputMessage.setId("fechaCompraCreateInputMessage");
		fechaCompraCreateInputMessage.setFor("fechaCompraCreateInput");
		fechaCompraCreateInputMessage.setDisplay("icon");
		htmlPanelGrid.getChildren().add(fechaCompraCreateInputMessage);

		return htmlPanelGrid;
	}

	public HtmlPanelGrid populateEditPanel() {
		return populateCreatePanel();
	}

	public HtmlPanelGrid populateViewPanel() {
		return populateCreatePanel();
	}

	public String onEdit() {
		return null;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public String goNuevaCompra() {
		compra = new Compra();
		codigoProducto = "";
		detalleCompraList = new ArrayList<DetalleCompra>();
		return "nuevaCompra";
	}

	public String guardarNuevaCompra() {
		Tipomovimiento tipoMovimiento = Tipomovimiento
				.findTipomovimiento(Tipomovimiento.ID_MOVIMIENTO_INGRESO_STOCK);
		String message = "";
		if (compra.getId() != null) {
			compra.merge();
			message = MensajeUtil.ACTUALIZADO_CORRECTAMENTE;
		} else {
			compra.persist();
			message = MensajeUtil.GUARDADO_CORRECTAMENTE;
		}
		for (DetalleCompra unDetalleCompra : detalleCompraList) {
			unDetalleCompra.setCompra(compra);
			unDetalleCompra.updateMovimientoIngresoStock(tipoMovimiento);
			unDetalleCompra.persist();
			unDetalleCompra.getItem().calcularStockActual();
		}

		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		findAllCompras();
		return "compra";
	}

	public String persist() {
		String message = "";
		if (compra.getId() != null) {
			compra.merge();
			message = MensajeUtil.ACTUALIZADO_CORRECTAMENTE;
		} else {
			compra.persist();
			message = MensajeUtil.GUARDADO_CORRECTAMENTE;
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("createDialog.hide()");
		context.execute("editDialog.hide()");

		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		reset();
		return findAllCompras();
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public void addToDetalleList() {
		List<Item> itemList = Item.findItemsByCodigoEquals(codigoProducto)
				.getResultList();
		if (itemList == null)
			return;
		for (Item unItem : itemList) {
			DetalleCompra nuevoDetalleCompra = new DetalleCompra();
			nuevoDetalleCompra.setCantidad(1);
			nuevoDetalleCompra.setItem(unItem);
			nuevoDetalleCompra.setPrecioUnitario(unItem.getPrecioCosto());
			detalleCompraList.add(nuevoDetalleCompra);
		}
	}

	public List<DetalleCompra> getDetalleCompraList() {
		return detalleCompraList;
	}

	public void setDetalleCompraList(List<DetalleCompra> detalleCompraList) {
		this.detalleCompraList = detalleCompraList;
	}

	public String delete() {
		if (compra == null)
			return null;
		List<Item> itemList = new ArrayList<Item>();
		List<DetalleCompra> detalleList = DetalleCompra
				.findDetalleComprasByCompra(compra).getResultList();
		if (detalleList != null) {
			for (DetalleCompra unDetalleCompra : detalleList) {
				itemList.add(unDetalleCompra.getItem());
			}
		}
		compra.remove();
		for (Item unItem : itemList) {
			unItem.calcularStockActual();
		}
		FacesMessage facesMessage = new FacesMessage(
				MensajeUtil.BORRADO_CORRECTAMENTE);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		reset();
		return findAllCompras();
	}

	public List<Item> completeCodigoReferenciaProducto(String query) {
		return Item.findItemsByCodigoReferenciaLike(query).getResultList();
	}

	public void addToDetalleListFromReferencia(SelectEvent event) {
		Item unItem = (Item) event.getObject();
		if (unItem == null)
			return;
		DetalleCompra nuevoDetalleCompra = new DetalleCompra();
		nuevoDetalleCompra.setCantidad(1);
		nuevoDetalleCompra.setItem(unItem);
		nuevoDetalleCompra.setPrecioUnitario(unItem.getPrecioCosto());
		detalleCompraList.add(nuevoDetalleCompra);
		AutoComplete ac = (AutoComplete)event.getSource();
		ac.setValue(null);
	}
	public ItemConverter getItemConverter(){
		return new ItemConverter();
		
	}
	public BigDecimal getTotalCompra(){
		 BigDecimal totalCompra = new BigDecimal(0);
		if (detalleCompraList==null||detalleCompraList.isEmpty()) return totalCompra;
		for (DetalleCompra unDetalleCompra:detalleCompraList){
			totalCompra = totalCompra.add(unDetalleCompra.getPrecioTotal());
		}
		return totalCompra;

	}
}
