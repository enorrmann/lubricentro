package ar.com.norrmann.coqui.jsf;

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
import javax.faces.validator.LengthValidator;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.message.Message;
import org.primefaces.component.spinner.Spinner;
import org.primefaces.context.RequestContext;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;

import ar.com.norrmann.coqui.model.Automotor;
import ar.com.norrmann.coqui.model.Servicio;

@RooJsfManagedBean(entity = Servicio.class, beanName = "servicioBean")
public class ServicioBean {
	private Automotor automotor;
	private Servicio servicio;
    
	public HtmlPanelGrid populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText automotorIdCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        automotorIdCreateOutput.setId("automotorIdCreateOutput");
        automotorIdCreateOutput.setValue("Automotor :");
        htmlPanelGrid.getChildren().add(automotorIdCreateOutput);
        
        HtmlOutputText automotorIdCreateInput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        automotorIdCreateInput.setId("automotorIdCreateInput");
        automotorIdCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{servicioBean.servicio.automotorId.dominio}", String.class));
        htmlPanelGrid.getChildren().add(automotorIdCreateInput);
        
        Message automotorIdCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        automotorIdCreateInputMessage.setId("automotorIdCreateInputMessage");
        automotorIdCreateInputMessage.setFor("automotorIdCreateInput");
        automotorIdCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(automotorIdCreateInputMessage);
        
        HtmlOutputText descripcionCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        descripcionCreateOutput.setId("descripcionCreateOutput");
        descripcionCreateOutput.setValue("Descripcion: * ");
        htmlPanelGrid.getChildren().add(descripcionCreateOutput);
        
        InputTextarea descripcionCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        descripcionCreateInput.setMaxHeight(100);
        descripcionCreateInput.setId("descripcionCreateInput");
        descripcionCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{servicioBean.servicio.descripcion}", String.class));
        LengthValidator descripcionCreateInputValidator = new LengthValidator();
        descripcionCreateInputValidator.setMaximum(255);
        descripcionCreateInput.addValidator(descripcionCreateInputValidator);
        descripcionCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(descripcionCreateInput);
        
        Message descripcionCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        descripcionCreateInputMessage.setId("descripcionCreateInputMessage");
        descripcionCreateInputMessage.setFor("descripcionCreateInput");
        descripcionCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(descripcionCreateInputMessage);

        HtmlOutputText kilometrosCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        kilometrosCreateOutput.setId("kilometrosCreateOutput");
        kilometrosCreateOutput.setValue("Kilometros:   ");
        htmlPanelGrid.getChildren().add(kilometrosCreateOutput);
        
        Spinner kilometrosCreateInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        kilometrosCreateInput.setId("kilometrosCreateInput");
        kilometrosCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{servicioBean.servicio.kilometros}", Long.class));
        kilometrosCreateInput.setRequired(false);
        
        htmlPanelGrid.getChildren().add(kilometrosCreateInput);
        
        Message kilometrosCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        kilometrosCreateInputMessage.setId("kilometrosCreateInputMessage");
        kilometrosCreateInputMessage.setFor("kilometrosCreateInput");
        kilometrosCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(kilometrosCreateInputMessage);
        
        HtmlOutputText fechaCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        fechaCreateOutput.setId("fechaCreateOutput");
        fechaCreateOutput.setValue("Fecha: * ");
        htmlPanelGrid.getChildren().add(fechaCreateOutput);
        
        Calendar fechaCreateInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        fechaCreateInput.setId("fechaCreateInput");
        fechaCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{servicioBean.servicio.fecha}", Date.class));
        fechaCreateInput.setNavigator(true);
        fechaCreateInput.setEffect("slideDown");
        fechaCreateInput.setPattern("dd/MM/yyyy");
        fechaCreateInput.setLocale("es");
        fechaCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(fechaCreateInput);
        
        Message fechaCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        fechaCreateInputMessage.setId("fechaCreateInputMessage");
        fechaCreateInputMessage.setFor("fechaCreateInput");
        fechaCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(fechaCreateInputMessage);

        return htmlPanelGrid;
    }
    public HtmlPanelGrid populateEditPanel() {
    	return populateCreatePanel();
    }
    public HtmlPanelGrid populateViewPanel() {
    	return populateCreatePanel();
    }

    public List<Automotor> completeAutomotorId(String query) {
        List<Automotor> suggestions = new ArrayList<Automotor>();
        for (Automotor automotor : Automotor.findAllAutomotors()) {
            String automotorStr = String.valueOf(automotor.getDominio()+automotor.getPropietarioId().getApellido()+automotor.getPropietarioId().getNombres());
            if (automotorStr.toLowerCase().indexOf(query.toLowerCase())>=0) {
                suggestions.add(automotor);
            }
        }
        return suggestions;
    }
	public Automotor getAutomotor() {
		return automotor;
	}
	public void setAutomotor(Automotor automotor) {
		this.automotor = automotor;
	}
	
    public String displayCreateDialog() {
        Servicio servicio = new Servicio();
        if (automotor!=null)servicio.setAutomotorId(automotor);
        setServicio(servicio);
        setCreateDialogVisible(true);
        return "detalle_automotor";
    }

    public String persist() {
        String message = "";
        if (servicio.getId() != null) {
            servicio.merge();
            message = "Actualizado correctamente";
        } else {
            servicio.persist();
            message = "Guardado correctamente";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createServicioDialog.hide()");
        context.execute("editServicioDialog.hide()");
        
        FacesMessage facesMessage = new FacesMessage(message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        //return findAllServicios();
        return null;
    }
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

}
