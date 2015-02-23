package ar.com.norrmann.coqui.jsf;

import java.math.BigDecimal;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponentBase;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;

import ar.com.norrmann.coqui.jsf.converter.ItemConverter;
import ar.com.norrmann.coqui.jsf.converter.MovimientoitemConverter;
import ar.com.norrmann.coqui.jsf.converter.VentaConverter;
import ar.com.norrmann.coqui.jsf.util.MensajeUtil;
import ar.com.norrmann.coqui.model.Compra;
import ar.com.norrmann.coqui.model.DetalleCompra;
import ar.com.norrmann.coqui.model.DetalleVenta;
import ar.com.norrmann.coqui.model.Item;
import ar.com.norrmann.coqui.model.Movimientoitem;
import ar.com.norrmann.coqui.model.Tipomovimiento;
import ar.com.norrmann.coqui.model.Venta;

import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.message.Message;
import org.primefaces.component.spinner.Spinner;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = DetalleVenta.class, beanName = "detalleVentaBean")
public class DetalleVentaBean {
	private String codigoProducto;
	
	@ManagedProperty("#{ventaBean}")
	private VentaBean ventaBean;

	private DetalleVenta detalleVenta;
	
    public HtmlPanelGrid populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText precioUnitarioCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        precioUnitarioCreateOutput.setId("precioUnitarioCreateOutput");
        precioUnitarioCreateOutput.setValue("Precio Unitario:   ");
        htmlPanelGrid.getChildren().add(precioUnitarioCreateOutput);
        
        InputText precioUnitarioCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        precioUnitarioCreateInput.setId("precioUnitarioCreateInput");
        precioUnitarioCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{detalleVentaBean.detalleVenta.precioUnitario}", BigDecimal.class));
        precioUnitarioCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(precioUnitarioCreateInput);
        
        Message precioUnitarioCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        precioUnitarioCreateInputMessage.setId("precioUnitarioCreateInputMessage");
        precioUnitarioCreateInputMessage.setFor("precioUnitarioCreateInput");
        precioUnitarioCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(precioUnitarioCreateInputMessage);
        
        HtmlOutputText cantidadCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        cantidadCreateOutput.setId("cantidadCreateOutput");
        cantidadCreateOutput.setValue("Cantidad: * ");
        htmlPanelGrid.getChildren().add(cantidadCreateOutput);
        
        Spinner cantidadCreateInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        cantidadCreateInput.setId("cantidadCreateInput");
        cantidadCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{detalleVentaBean.detalleVenta.cantidad}", Integer.class));
        cantidadCreateInput.setRequired(true);
        
        htmlPanelGrid.getChildren().add(cantidadCreateInput);
        
        Message cantidadCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        cantidadCreateInputMessage.setId("cantidadCreateInputMessage");
        cantidadCreateInputMessage.setFor("cantidadCreateInput");
        cantidadCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(cantidadCreateInputMessage);
        
        return htmlPanelGrid;
    }
    public HtmlPanelGrid populateEditPanel() {
    	return populateCreatePanel();
    }
    public HtmlPanelGrid populateViewPanel() {
    	return null;
    }
    
    public String persist() {
    	Tipomovimiento tipoMovimiento = Tipomovimiento.findTipomovimiento(Tipomovimiento.ID_MOVIMIENTO_EGRESO_STOCK);
        String message = "";
        if (detalleVenta.getId() != null) {
			Movimientoitem movimientoItem = detalleVenta.getMovimientoEgresoStock();
			Item itemAnterior = movimientoItem.getItemId();
			detalleVenta.updateMovimientoEgresoStock(tipoMovimiento);
            
			detalleVenta.merge();
            
            itemAnterior.calcularStockActual();
            message = MensajeUtil.ACTUALIZADO_CORRECTAMENTE;
        } else {
        	detalleVenta.updateMovimientoEgresoStock(tipoMovimiento);
            detalleVenta.persist();
            message = MensajeUtil.GUARDADO_CORRECTAMENTE;
        }
        detalleVenta.getItem().calcularStockActual();
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialog.hide()");
        context.execute("editDialog.hide()");
        
        FacesMessage facesMessage = new FacesMessage(message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        onView();
        return null;
    }
    
	public String onView() {
		codigoProducto = null;
		if (ventaBean.getVenta() == null)
			return null;
		setAllDetalleVentas(DetalleVenta.findDetalleVentasByVenta(
				ventaBean.getVenta()).getResultList());

		return "detalleVenta";
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public VentaBean getVentaBean() {
		return ventaBean;
	}

	public void setVentaBean(VentaBean ventaBean) {
		this.ventaBean = ventaBean;
	}
	public ItemConverter getItemConverter() {
		return new ItemConverter();

	}
    public String delete() {
    	Item item = detalleVenta.getItem();
    	detalleVenta.remove();
        item.calcularStockActual();
        FacesMessage facesMessage = new FacesMessage(MensajeUtil.BORRADO_CORRECTAMENTE);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllDetalleVentas();
    }
	public void addToDetalleList(){
		List<Item> itemList = Item.findItemsByCodigoEquals(codigoProducto).getResultList();
		if (itemList==null)return;
		Venta venta =ventaBean.getVenta(); 
		if ( venta == null)	return;
		Tipomovimiento tipoMovimiento = Tipomovimiento.findTipomovimiento(Tipomovimiento.ID_MOVIMIENTO_EGRESO_STOCK);
		for (Item unItem:itemList){
			DetalleVenta nuevoDetalleVenta = new DetalleVenta();
			nuevoDetalleVenta.setCantidad(1);
			nuevoDetalleVenta.setItem(unItem);
			nuevoDetalleVenta.setPrecioUnitario(unItem.getPrecioVenta());
			nuevoDetalleVenta.setVenta(venta);
			nuevoDetalleVenta.updateMovimientoEgresoStock(tipoMovimiento);
			nuevoDetalleVenta.persist();
			unItem.calcularStockActual();
		}
		onView();
	} 
	public void addToDetalleListFromReferencia(SelectEvent event) {
		Item unItem = (Item) event.getObject();
		if (unItem == null)
			return;
		Venta venta =ventaBean.getVenta(); 
		if ( venta == null)	return;
		Tipomovimiento tipoMovimiento = Tipomovimiento.findTipomovimiento(Tipomovimiento.ID_MOVIMIENTO_INGRESO_STOCK);
		DetalleVenta nuevoDetalleVenta = new DetalleVenta();
		nuevoDetalleVenta.setCantidad(1);
		nuevoDetalleVenta.setItem(unItem);
		nuevoDetalleVenta.setPrecioUnitario(unItem.getPrecioVenta());
		nuevoDetalleVenta.setVenta(venta);
		nuevoDetalleVenta.updateMovimientoEgresoStock(tipoMovimiento);
		nuevoDetalleVenta.persist();
		unItem.calcularStockActual();
		AutoComplete ac = (AutoComplete)event.getSource();
		ac.setValue(null);
		onView();
	}
}
