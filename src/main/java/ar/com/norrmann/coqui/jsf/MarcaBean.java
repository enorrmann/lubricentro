package ar.com.norrmann.coqui.jsf;

import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.validator.LengthValidator;

import ar.com.norrmann.coqui.jsf.converter.TipoitemConverter;
import ar.com.norrmann.coqui.model.Marca;
import ar.com.norrmann.coqui.model.Tipoitem;

import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.message.Message;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;

@RooJsfManagedBean(entity = Marca.class, beanName = "marcaBean")
public class MarcaBean {
    public HtmlPanelGrid populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText tipoItemIdCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        tipoItemIdCreateOutput.setId("tipoItemIdCreateOutput");
        tipoItemIdCreateOutput.setValue("Tipo Item :");
        htmlPanelGrid.getChildren().add(tipoItemIdCreateOutput);
        
        AutoComplete tipoItemIdCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        tipoItemIdCreateInput.setId("tipoItemIdCreateInput");
        tipoItemIdCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{marcaBean.marca.tipoItemId}", Tipoitem.class));
        tipoItemIdCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{marcaBean.completeTipoItemId}", List.class, new Class[] { String.class }));
        tipoItemIdCreateInput.setDropdown(true);
        tipoItemIdCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "tipoItemId", String.class));
        tipoItemIdCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{tipoItemId.tipoItem}", String.class));
        tipoItemIdCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{tipoItemId}", Tipoitem.class));
        tipoItemIdCreateInput.setConverter(new TipoitemConverter());
        tipoItemIdCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(tipoItemIdCreateInput);
        
        Message tipoItemIdCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        tipoItemIdCreateInputMessage.setId("tipoItemIdCreateInputMessage");
        tipoItemIdCreateInputMessage.setFor("tipoItemIdCreateInput");
        tipoItemIdCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(tipoItemIdCreateInputMessage);
        
        HtmlOutputText marcaCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        marcaCreateOutput.setId("marcaCreateOutput");
        marcaCreateOutput.setValue("Marca: * ");
        htmlPanelGrid.getChildren().add(marcaCreateOutput);
        
        InputText marcaCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        marcaCreateInput.setId("marcaCreateInput");
        marcaCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{marcaBean.marca.marca}", String.class));
        LengthValidator marcaCreateInputValidator = new LengthValidator();
        marcaCreateInputValidator.setMaximum(255);
        marcaCreateInput.addValidator(marcaCreateInputValidator);
        marcaCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(marcaCreateInput);
        
        Message marcaCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        marcaCreateInputMessage.setId("marcaCreateInputMessage");
        marcaCreateInputMessage.setFor("marcaCreateInput");
        marcaCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(marcaCreateInputMessage);
        
        return htmlPanelGrid;
    }
    
    public HtmlPanelGrid populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        

        HtmlOutputText tipoItemIdEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        tipoItemIdEditOutput.setId("tipoItemIdEditOutput");
        tipoItemIdEditOutput.setValue("Tipo Item :");
        htmlPanelGrid.getChildren().add(tipoItemIdEditOutput);
        
        AutoComplete tipoItemIdEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        tipoItemIdEditInput.setId("tipoItemIdEditInput");
        tipoItemIdEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{marcaBean.marca.tipoItemId}", Tipoitem.class));
        tipoItemIdEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{marcaBean.completeTipoItemId}", List.class, new Class[] { String.class }));
        tipoItemIdEditInput.setDropdown(true);
        tipoItemIdEditInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "tipoItemId", String.class));
        tipoItemIdEditInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{tipoItemId.tipoItem}", String.class));
        tipoItemIdEditInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{tipoItemId}", Tipoitem.class));
        tipoItemIdEditInput.setConverter(new TipoitemConverter());
        tipoItemIdEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(tipoItemIdEditInput);
        
        Message tipoItemIdEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        tipoItemIdEditInputMessage.setId("tipoItemIdEditInputMessage");
        tipoItemIdEditInputMessage.setFor("tipoItemIdEditInput");
        tipoItemIdEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(tipoItemIdEditInputMessage);
        
        HtmlOutputText marcaEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        marcaEditOutput.setId("marcaEditOutput");
        marcaEditOutput.setValue("Marca: * ");
        htmlPanelGrid.getChildren().add(marcaEditOutput);
        
        InputText marcaEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        marcaEditInput.setId("marcaEditInput");
        marcaEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{marcaBean.marca.marca}", String.class));
        LengthValidator marcaEditInputValidator = new LengthValidator();
        marcaEditInputValidator.setMaximum(255);
        marcaEditInput.addValidator(marcaEditInputValidator);
        marcaEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(marcaEditInput);
        
        Message marcaEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        marcaEditInputMessage.setId("marcaEditInputMessage");
        marcaEditInputMessage.setFor("marcaEditInput");
        marcaEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(marcaEditInputMessage);
        
        return htmlPanelGrid;
    }

}
