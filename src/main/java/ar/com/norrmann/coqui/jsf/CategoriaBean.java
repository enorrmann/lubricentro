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
import ar.com.norrmann.coqui.model.Categoria;
import ar.com.norrmann.coqui.model.Tipoitem;

import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.message.Message;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;

@RooJsfManagedBean(entity = Categoria.class, beanName = "categoriaBean")
public class CategoriaBean {
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
        tipoItemIdCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{categoriaBean.categoria.tipoItemId}", Tipoitem.class));
        tipoItemIdCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{categoriaBean.completeTipoItemId}", List.class, new Class[] { String.class }));
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
        
        HtmlOutputText categoriaCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        categoriaCreateOutput.setId("categoriaCreateOutput");
        categoriaCreateOutput.setValue("Categoria: * ");
        htmlPanelGrid.getChildren().add(categoriaCreateOutput);
        
        InputTextarea categoriaCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        categoriaCreateInput.setMaxHeight(100);
        categoriaCreateInput.setId("categoriaCreateInput");
        categoriaCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{categoriaBean.categoria.categoria}", String.class));
        LengthValidator categoriaCreateInputValidator = new LengthValidator();
        categoriaCreateInputValidator.setMaximum(255);
        categoriaCreateInput.addValidator(categoriaCreateInputValidator);
        categoriaCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(categoriaCreateInput);
        
        Message categoriaCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        categoriaCreateInputMessage.setId("categoriaCreateInputMessage");
        categoriaCreateInputMessage.setFor("categoriaCreateInput");
        categoriaCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(categoriaCreateInputMessage);
        
        return htmlPanelGrid;
    }

}
