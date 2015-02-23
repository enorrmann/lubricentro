package ar.com.norrmann.coqui.jsf;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.validator.LengthValidator;

import ar.com.norrmann.coqui.model.Tipoitem;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.message.Message;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;

@RooJsfManagedBean(entity = Tipoitem.class, beanName = "tipoitemBean")
public class TipoitemBean {
	private String name = "Tipo de item";
    public HtmlPanelGrid populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText tipoItemCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        tipoItemCreateOutput.setId("tipoItemCreateOutput");
        tipoItemCreateOutput.setValue("Tipo de Item: * ");
        htmlPanelGrid.getChildren().add(tipoItemCreateOutput);
        
        InputText tipoItemCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        tipoItemCreateInput.setId("tipoItemCreateInput");
        tipoItemCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{tipoitemBean.tipoitem.tipoItem}", String.class));
        LengthValidator tipoItemCreateInputValidator = new LengthValidator();
        tipoItemCreateInputValidator.setMaximum(255);
        tipoItemCreateInput.addValidator(tipoItemCreateInputValidator);
        tipoItemCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(tipoItemCreateInput);
        
        Message tipoItemCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        tipoItemCreateInputMessage.setId("tipoItemCreateInputMessage");
        tipoItemCreateInputMessage.setFor("tipoItemCreateInput");
        tipoItemCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(tipoItemCreateInputMessage);
        
        return htmlPanelGrid;
    }
    
    public HtmlPanelGrid populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText tipoItemEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        tipoItemEditOutput.setId("tipoItemEditOutput");
        tipoItemEditOutput.setValue("Tipo Item: * ");
        htmlPanelGrid.getChildren().add(tipoItemEditOutput);
        
        InputText tipoItemEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        tipoItemEditInput.setId("tipoItemEditInput");
        tipoItemEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{tipoitemBean.tipoitem.tipoItem}", String.class));
        LengthValidator tipoItemEditInputValidator = new LengthValidator();
        tipoItemEditInputValidator.setMaximum(255);
        tipoItemEditInput.addValidator(tipoItemEditInputValidator);
        tipoItemEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(tipoItemEditInput);
        
        Message tipoItemEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        tipoItemEditInputMessage.setId("tipoItemEditInputMessage");
        tipoItemEditInputMessage.setFor("tipoItemEditInput");
        tipoItemEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(tipoItemEditInputMessage);
        
        return htmlPanelGrid;
    }

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
