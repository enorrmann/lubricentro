package ar.com.norrmann.coqui.jsf;

import java.math.BigDecimal;
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
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.message.Message;
import org.primefaces.context.RequestContext;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

import ar.com.norrmann.coqui.jsf.converter.VentaConverter;
import ar.com.norrmann.coqui.model.Pago;
import ar.com.norrmann.coqui.model.Venta;

@RooSerializable
@RooJsfManagedBean(entity = Pago.class, beanName = "pagoBean")
public class PagoBean {
	
	@ManagedProperty("#{ventaBean}")
	private VentaBean ventaBean;
	
	 public HtmlPanelGrid populateCreatePanel() {
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        Application application = facesContext.getApplication();
	        ExpressionFactory expressionFactory = application.getExpressionFactory();
	        ELContext elContext = facesContext.getELContext();
	        
	        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
	        
	        HtmlOutputText importeCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
	        importeCreateOutput.setId("importeCreateOutput");
	        importeCreateOutput.setValue("Importe: * ");
	        htmlPanelGrid.getChildren().add(importeCreateOutput);
	        
	        InputText importeCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
	        importeCreateInput.setId("importeCreateInput");
	        importeCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.importe}", BigDecimal.class));
	        importeCreateInput.setRequired(true);
	        htmlPanelGrid.getChildren().add(importeCreateInput);
	        
	        Message importeCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
	        importeCreateInputMessage.setId("importeCreateInputMessage");
	        importeCreateInputMessage.setFor("importeCreateInput");
	        importeCreateInputMessage.setDisplay("icon");
	        htmlPanelGrid.getChildren().add(importeCreateInputMessage);
	        
	        HtmlOutputText fechaCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
	        fechaCreateOutput.setId("fechaCreateOutput");
	        fechaCreateOutput.setValue("Fecha: * ");
	        htmlPanelGrid.getChildren().add(fechaCreateOutput);
	        
	        Calendar fechaCreateInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
	        fechaCreateInput.setId("fechaCreateInput");
	        fechaCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.fecha}", Date.class));
	        fechaCreateInput.setNavigator(true);
	        fechaCreateInput.setEffect("slideDown");
	        fechaCreateInput.setPattern("dd/MM/yyyy");
	        fechaCreateInput.setRequired(true);
	        htmlPanelGrid.getChildren().add(fechaCreateInput);
	        
	        Message fechaCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
	        fechaCreateInputMessage.setId("fechaCreateInputMessage");
	        fechaCreateInputMessage.setFor("fechaCreateInput");
	        fechaCreateInputMessage.setDisplay("icon");
	        htmlPanelGrid.getChildren().add(fechaCreateInputMessage);
	        
	        HtmlOutputText observacionesCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
	        observacionesCreateOutput.setId("observacionesCreateOutput");
	        observacionesCreateOutput.setValue("Observaciones:   ");
	        htmlPanelGrid.getChildren().add(observacionesCreateOutput);
	        
	        InputText observacionesCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
	        observacionesCreateInput.setId("observacionesCreateInput");
	        observacionesCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.observaciones}", String.class));
	        htmlPanelGrid.getChildren().add(observacionesCreateInput);
	        
	        Message observacionesCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
	        observacionesCreateInputMessage.setId("observacionesCreateInputMessage");
	        observacionesCreateInputMessage.setFor("observacionesCreateInput");
	        observacionesCreateInputMessage.setDisplay("icon");
	        htmlPanelGrid.getChildren().add(observacionesCreateInputMessage);
	        
	        return htmlPanelGrid;
	    }
	    
	 public String displayCreateDialog() {
	        setPago(new Pago());
	        setCreateDialogVisible(true);
	        return "detalleVenta";
	    }
	   
	 public String persist() {
	        String message = "";
	        getPago().setVenta(ventaBean.getVenta());
	        if (getPago().getId() != null) {
	        	getPago().merge();
	            message = "Successfully updated";
	        } else {
	        	getPago().persist();
	            message = "Successfully created";
	        }
	        RequestContext context = RequestContext.getCurrentInstance();
	        context.execute("createDialog.hide()");
	        //context.execute("editDialog.hide()");
	        
	        FacesMessage facesMessage = new FacesMessage(message);
	        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	        reset();
	        return null;
	    }

	public VentaBean getVentaBean() {
		return ventaBean;
	}

	public void setVentaBean(VentaBean ventaBean) {
		this.ventaBean = ventaBean;
	}
}
