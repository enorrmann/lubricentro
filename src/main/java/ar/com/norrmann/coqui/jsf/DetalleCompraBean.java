package ar.com.norrmann.coqui.jsf;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;

import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.message.Message;
import org.primefaces.component.spinner.Spinner;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

import ar.com.norrmann.coqui.jsf.converter.ItemConverter;
import ar.com.norrmann.coqui.jsf.util.MensajeUtil;
import ar.com.norrmann.coqui.model.Compra;
import ar.com.norrmann.coqui.model.DetalleCompra;
import ar.com.norrmann.coqui.model.Item;
import ar.com.norrmann.coqui.model.Movimientoitem;
import ar.com.norrmann.coqui.model.Tipoitem;
import ar.com.norrmann.coqui.model.Tipomovimiento;

@RooSerializable
@RooJsfManagedBean(entity = DetalleCompra.class, beanName = "detalleCompraBean")
public class DetalleCompraBean {
	private DetalleCompra detalleCompra;
	private boolean createNuevaCompraDialogVisible;
    private String codigoProducto;


	@ManagedProperty("#{compraBean}")
	private CompraBean compraBean;

	public String onView() {
		codigoProducto=null;
		if (compraBean.getCompra() == null)
			return null;
		setAllDetalleCompras(DetalleCompra.findDetalleComprasByCompra(
				compraBean.getCompra()).getResultList());

		return "detalleCompra";
	}

	public HtmlPanelGrid populateCreatePanel() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Application application = facesContext.getApplication();
		ExpressionFactory expressionFactory = application
				.getExpressionFactory();
		ELContext elContext = facesContext.getELContext();

		HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application
				.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
		//
		// HtmlOutputText itemCreateOutput = (HtmlOutputText)
		// application.createComponent(HtmlOutputText.COMPONENT_TYPE);
		// itemCreateOutput.setId("itemCreateOutput");
		// itemCreateOutput.setValue("Item: * ");
		// htmlPanelGrid.getChildren().add(itemCreateOutput);
		//
		// AutoComplete itemCreateInput = (AutoComplete)
		// application.createComponent(AutoComplete.COMPONENT_TYPE);
		// itemCreateInput.setId("itemCreateInput");
		// itemCreateInput.setValueExpression("value",
		// expressionFactory.createValueExpression(elContext,
		// "#{detalleCompraBean.detalleCompra.item}", Item.class));
		// itemCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext,
		// "#{detalleCompraBean.completeItem}", List.class, new Class[] {
		// String.class }));
		// itemCreateInput.setDropdown(true);
		// itemCreateInput.setValueExpression("var",
		// expressionFactory.createValueExpression(elContext, "item",
		// String.class));
		// itemCreateInput.setValueExpression("itemLabel",
		// expressionFactory.createValueExpression(elContext,
		// "#{item.codigo} #{item.descripcion}", String.class));
		// itemCreateInput.setValueExpression("itemValue",
		// expressionFactory.createValueExpression(elContext, "#{item}",
		// Item.class));
		// itemCreateInput.setConverter(new ItemConverter());
		// itemCreateInput.setRequired(true);
		// htmlPanelGrid.getChildren().add(itemCreateInput);
		//
		// Message itemCreateInputMessage = (Message)
		// application.createComponent(Message.COMPONENT_TYPE);
		// itemCreateInputMessage.setId("itemCreateInputMessage");
		// itemCreateInputMessage.setFor("itemCreateInput");
		// itemCreateInputMessage.setDisplay("icon");
		// htmlPanelGrid.getChildren().add(itemCreateInputMessage);

		HtmlOutputText precioUnitarioCreateOutput = (HtmlOutputText) application
				.createComponent(HtmlOutputText.COMPONENT_TYPE);
		precioUnitarioCreateOutput.setId("precioUnitarioCreateOutput");
		precioUnitarioCreateOutput.setValue("Precio Unitario:   ");
		htmlPanelGrid.getChildren().add(precioUnitarioCreateOutput);

		InputText precioUnitarioCreateInput = (InputText) application
				.createComponent(InputText.COMPONENT_TYPE);
		precioUnitarioCreateInput.setId("precioUnitarioCreateInput");
		precioUnitarioCreateInput.setValueExpression("value", expressionFactory
				.createValueExpression(elContext,
						"#{detalleCompraBean.detalleCompra.precioUnitario}",
						BigDecimal.class));
		precioUnitarioCreateInput.setRequired(false);
		htmlPanelGrid.getChildren().add(precioUnitarioCreateInput);

		Message precioUnitarioCreateInputMessage = (Message) application
				.createComponent(Message.COMPONENT_TYPE);
		precioUnitarioCreateInputMessage
				.setId("precioUnitarioCreateInputMessage");
		precioUnitarioCreateInputMessage.setFor("precioUnitarioCreateInput");
		precioUnitarioCreateInputMessage.setDisplay("icon");
		htmlPanelGrid.getChildren().add(precioUnitarioCreateInputMessage);

		HtmlOutputText cantidadCreateOutput = (HtmlOutputText) application
				.createComponent(HtmlOutputText.COMPONENT_TYPE);
		cantidadCreateOutput.setId("cantidadCreateOutput");
		cantidadCreateOutput.setValue("Cantidad: * ");
		htmlPanelGrid.getChildren().add(cantidadCreateOutput);

		Spinner cantidadCreateInput = (Spinner) application
				.createComponent(Spinner.COMPONENT_TYPE);
		cantidadCreateInput.setId("cantidadCreateInput");
		cantidadCreateInput.setValueExpression("value", expressionFactory
				.createValueExpression(elContext,
						"#{detalleCompraBean.detalleCompra.cantidad}",
						Integer.class));
		cantidadCreateInput.setRequired(true);

		htmlPanelGrid.getChildren().add(cantidadCreateInput);

		Message cantidadCreateInputMessage = (Message) application
				.createComponent(Message.COMPONENT_TYPE);
		cantidadCreateInputMessage.setId("cantidadCreateInputMessage");
		cantidadCreateInputMessage.setFor("cantidadCreateInput");
		cantidadCreateInputMessage.setDisplay("icon");
		htmlPanelGrid.getChildren().add(cantidadCreateInputMessage);

		return htmlPanelGrid;
	}

	public HtmlPanelGrid populateEditPanel() {
		return populateCreatePanel();

	}

	public String persist() {
		Tipomovimiento tipoMovimiento = Tipomovimiento.findTipomovimiento(Tipomovimiento.ID_MOVIMIENTO_INGRESO_STOCK);
		String message = "";
		detalleCompra.setCompra(compraBean.getCompra());

		if (detalleCompra.getId() != null) {
			Movimientoitem movimientoIngresoStock = detalleCompra.getMovimientoIngresoStock();
			Item itemAnterior = movimientoIngresoStock.getItemId();
			detalleCompra.updateMovimientoIngresoStock(tipoMovimiento);

			detalleCompra.merge();

			itemAnterior.calcularStockActual();

			message = MensajeUtil.ACTUALIZADO_CORRECTAMENTE;
		} else {

			detalleCompra.updateMovimientoIngresoStock(tipoMovimiento);
			detalleCompra.persist();
			message = MensajeUtil.GUARDADO_CORRECTAMENTE;
		}
		detalleCompra.getItem().calcularStockActual();

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("createDialog.hide()");
		context.execute("editDialog.hide()");

		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		reset();
		// return findAllDetalleCompras();
		onView();
		return null;
	}

	public DetalleCompra getDetalleCompra() {
		return detalleCompra;
	}

	public void setDetalleCompra(DetalleCompra detalleCompra) {
		this.detalleCompra = detalleCompra;
	}

	public boolean isCreateNuevaCompraDialogVisible() {
		return createNuevaCompraDialogVisible;
	}

	public void setCreateNuevaCompraDialogVisible(
			boolean createNuevaCompraDialogVisible) {
		this.createNuevaCompraDialogVisible = createNuevaCompraDialogVisible;
	}

	public CompraBean getCompraBean() {
		return compraBean;
	}

	public void setCompraBean(CompraBean compraBean) {
		this.compraBean = compraBean;
	}

	public void handleDialogClose(CloseEvent event) {
		reset();
	}

	public ItemConverter getItemConverter() {
		return new ItemConverter();

	}
    
    
	public void itemSelected(SelectEvent event) {
		Item itemSeleccionado = (Item)event.getObject();  
		//System.out.print("foooo "+message);
		detalleCompra.setPrecioUnitario(itemSeleccionado.getPrecioCosto());
	}

	public List<Item> completeItem(String query) {

		return Item.findItemsByCodigoLikeOrDescripcionLikeAndTipoItemIdEquals(query, Tipoitem.findTipoitem(Tipoitem.ID_PRODUCTO));
	}
	
	  public HtmlPanelGrid populateViewPanel() {
		  return null;
	  }

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	
	public void addToDetalleList(){
		List<Item> itemList = Item.findItemsByCodigoEquals(codigoProducto).getResultList();
		if (itemList==null)return;
		Compra compra =compraBean.getCompra(); 
		if ( compra == null)	return;
		Tipomovimiento tipoMovimiento = Tipomovimiento.findTipomovimiento(Tipomovimiento.ID_MOVIMIENTO_INGRESO_STOCK);
		for (Item unItem:itemList){
			DetalleCompra nuevoDetalleCompra = new DetalleCompra();
			nuevoDetalleCompra.setCantidad(1);
			nuevoDetalleCompra.setItem(unItem);
			nuevoDetalleCompra.setPrecioUnitario(unItem.getPrecioCosto());
			nuevoDetalleCompra.setCompra(compra);
			nuevoDetalleCompra.updateMovimientoIngresoStock(tipoMovimiento);
			nuevoDetalleCompra.persist();
			unItem.calcularStockActual();
		}
		onView();
	}
	public void addToDetalleListFromReferencia(SelectEvent event) {
		Item unItem = (Item) event.getObject();
		if (unItem == null)
			return;
		Compra compra =compraBean.getCompra(); 
		if ( compra == null)	return;
		Tipomovimiento tipoMovimiento = Tipomovimiento.findTipomovimiento(Tipomovimiento.ID_MOVIMIENTO_INGRESO_STOCK);
		DetalleCompra nuevoDetalleCompra = new DetalleCompra();
		nuevoDetalleCompra.setCantidad(1);
		nuevoDetalleCompra.setItem(unItem);
		nuevoDetalleCompra.setPrecioUnitario(unItem.getPrecioCosto());
		nuevoDetalleCompra.setCompra(compra);
		nuevoDetalleCompra.updateMovimientoIngresoStock(tipoMovimiento);
		nuevoDetalleCompra.persist();
		unItem.calcularStockActual();
		AutoComplete ac = (AutoComplete)event.getSource();
		ac.setValue(null);
		onView();
	}
    public String delete() {
    	Item item = detalleCompra.getItem();
        detalleCompra.remove();
        item.calcularStockActual();
        FacesMessage facesMessage = new FacesMessage(MensajeUtil.BORRADO_CORRECTAMENTE);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllDetalleCompras();
    }
}
