package ar.com.norrmann.coqui.jsf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.message.Message;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

import ar.com.norrmann.coqui.jsf.converter.ClienteConverter;
import ar.com.norrmann.coqui.jsf.util.MensajeUtil;
import ar.com.norrmann.coqui.model.Cliente;
import ar.com.norrmann.coqui.model.DetalleVenta;
import ar.com.norrmann.coqui.model.FormaDePago;
import ar.com.norrmann.coqui.model.Item;
import ar.com.norrmann.coqui.model.Pago;
import ar.com.norrmann.coqui.model.Tipomovimiento;
import ar.com.norrmann.coqui.model.Venta;

@RooSerializable
@RooJsfManagedBean(entity = Venta.class, beanName = "ventaBean")
public class VentaBean {
	
    private Venta venta;
	private String codigoProducto;
    private List<DetalleVenta> detalleVentaList;
    
    @ManagedProperty("#{clienteBean}")
    private ClienteBean clienteBean;
    
    
    public HtmlPanelGrid populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText clienteCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        clienteCreateOutput.setId("clienteCreateOutput");
        clienteCreateOutput.setValue("Cliente:   ");
        htmlPanelGrid.getChildren().add(clienteCreateOutput);
        
        AutoComplete clienteCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        clienteCreateInput.setId("clienteCreateInput");
        clienteCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{ventaBean.venta.cliente}", Cliente.class));
        clienteCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{ventaBean.completeCliente}", List.class, new Class[] { String.class }));
        clienteCreateInput.setDropdown(true);
        clienteCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "cliente", String.class));
        clienteCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{cliente.apellido}, #{cliente.nombres}", String.class));
        clienteCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{cliente}", Cliente.class));
        clienteCreateInput.setConverter(new ClienteConverter());
        clienteCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(clienteCreateInput);
        
        Message clienteCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        clienteCreateInputMessage.setId("clienteCreateInputMessage");
        clienteCreateInputMessage.setFor("clienteCreateInput");
        clienteCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(clienteCreateInputMessage);
        
        HtmlOutputText fechaCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        fechaCreateOutput.setId("fechaCreateOutput");
        fechaCreateOutput.setValue("Fecha:   ");
        htmlPanelGrid.getChildren().add(fechaCreateOutput);
        
        Calendar fechaCreateInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        fechaCreateInput.setId("fechaCreateInput");
        fechaCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{ventaBean.venta.fecha}", Date.class));
        fechaCreateInput.setNavigator(true);
        fechaCreateInput.setEffect("slideDown");
        fechaCreateInput.setPattern("dd/MM/yyyy");
        fechaCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(fechaCreateInput);
        
        Message fechaCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        fechaCreateInputMessage.setId("fechaCreateInputMessage");
        fechaCreateInputMessage.setFor("fechaCreateInput");
        fechaCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(fechaCreateInputMessage);
        
        HtmlOutputText formaDePagoCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        formaDePagoCreateOutput.setId("formaDePagoCreateOutput");
        formaDePagoCreateOutput.setValue("Forma De Pago:");
        htmlPanelGrid.getChildren().add(formaDePagoCreateOutput);
        
        AutoComplete formaDePagoCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        formaDePagoCreateInput.setId("formaDePagoCreateInput");
        formaDePagoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{ventaBean.venta.formaDePago}", FormaDePago.class));
        formaDePagoCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{ventaBean.completeFormaDePago}", List.class, new Class[] { String.class }));
        formaDePagoCreateInput.setDropdown(true);
        formaDePagoCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(formaDePagoCreateInput);
        
        Message formaDePagoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        formaDePagoCreateInputMessage.setId("formaDePagoCreateInputMessage");
        formaDePagoCreateInputMessage.setFor("formaDePagoCreateInput");
        formaDePagoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(formaDePagoCreateInputMessage);

        return htmlPanelGrid;
    }
    
    public HtmlPanelGrid populateEditPanel() {
    	return populateCreatePanel();
    }
    public HtmlPanelGrid populateViewPanel() {
    	return null;
    }

	public String goNuevaVenta() {
        venta = new Venta();
        codigoProducto = "";
        detalleVentaList = new ArrayList<DetalleVenta>();
       return "nuevaVenta";
   }
	
	public String goNuevaVentaDeCliente() {
		goNuevaVenta();
        getVenta().setCliente(clienteBean.getCliente());
       return "nuevaVenta";
   }
	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public List<DetalleVenta> getDetalleVentaList() {
		return detalleVentaList;
	}

	public void setDetalleVentaList(List<DetalleVenta> detalleVentaList) {
		this.detalleVentaList = detalleVentaList;
	}
	public void addToDetalleList(){
		List<Item> itemList = Item.findItemsByCodigoEquals(codigoProducto).getResultList();
		if (itemList==null)return;
		for (Item unItem:itemList){
			DetalleVenta nuevoDetalleVenta = new DetalleVenta();
			nuevoDetalleVenta.setCantidad(1);
			nuevoDetalleVenta.setItem(unItem);
			nuevoDetalleVenta.setPrecioUnitario(unItem.getPrecioVenta());
			detalleVentaList.add(nuevoDetalleVenta);
		}
	}
    public String guardarNuevaVenta() {
    	Tipomovimiento tipoMovimiento = Tipomovimiento.findTipomovimiento(Tipomovimiento.ID_MOVIMIENTO_EGRESO_STOCK);

        venta.persist();
        String message = MensajeUtil.GUARDADO_CORRECTAMENTE;

        for (DetalleVenta unDetalleVenta : detalleVentaList){
        	unDetalleVenta.setVenta(venta);
        	unDetalleVenta.updateMovimientoEgresoStock(tipoMovimiento);
        	unDetalleVenta.persist();
        	unDetalleVenta.getItem().calcularStockActual();
        }
        if (venta.getFormaDePago().equals(FormaDePago.Contado)){
    		Pago pago = new Pago();
    		pago.setVenta(venta);
    		pago.setObservaciones("Pago contado");
    		pago.setFecha(venta.getFecha());
    		pago.setImporte(venta.getPrecioTotal());
    		pago.persist();
    	}
        FacesMessage facesMessage = new FacesMessage(message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        //findAllVentas();
        return "detalleVenta";
    }
    
    public String delete() {
    	if (venta==null) return null;
    	List<Item> itemList = new ArrayList<Item>();
    	List<DetalleVenta> detalleList = DetalleVenta.findDetalleVentasByVenta(venta).getResultList();
    	if (detalleList!=null){
    		for (DetalleVenta unDetalleVenta:detalleList){
    			itemList.add(unDetalleVenta.getItem());
    		}
    	}
    	venta.remove();
        for (Item unItem:itemList){
        	unItem.calcularStockActual();
        }
        FacesMessage facesMessage = new FacesMessage(MensajeUtil.BORRADO_CORRECTAMENTE);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllVentas();
    }
    
    public String persist() {
        String message = "";
        if (venta.getId() != null) {
            venta.merge();
            message = MensajeUtil.ACTUALIZADO_CORRECTAMENTE;
        } else {
            venta.persist();
            message = MensajeUtil.GUARDADO_CORRECTAMENTE;
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialog.hide()");
        context.execute("editDialog.hide()");
        
        FacesMessage facesMessage = new FacesMessage(message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllVentas();
    }    
	public void addToDetalleListFromReferencia(SelectEvent event) {
		Item unItem = (Item) event.getObject();
		if (unItem == null)
			return;
		DetalleVenta nuevoDetalleVenta = new DetalleVenta();
		nuevoDetalleVenta.setCantidad(1);
		nuevoDetalleVenta.setItem(unItem);
		nuevoDetalleVenta.setPrecioUnitario(unItem.getPrecioVenta());
		detalleVentaList.add(nuevoDetalleVenta);
		AutoComplete ac = (AutoComplete)event.getSource();
		ac.setValue(null);
	}

	
	public BigDecimal getTotalVenta(){
		 BigDecimal totalVenta = new BigDecimal(0);
		if (detalleVentaList==null||detalleVentaList.isEmpty()) return totalVenta;
		for (DetalleVenta unDetalleVenta:detalleVentaList){
			totalVenta = totalVenta.add(unDetalleVenta.getPrecioTotal());
		}
		return totalVenta;

	}
	public List<Item> completeDescripcionProducto(String query) {
		return Item.findItemsByDescripcionLike(query).getResultList();
	}
	
	public ClienteBean getClienteBean(){
		return clienteBean;
	}
	public void setClienteBean(ClienteBean clienteBean){
		this.clienteBean = clienteBean;
	}

}
