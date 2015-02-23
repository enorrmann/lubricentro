package ar.com.norrmann.coqui.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.validator.LengthValidator;

import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.behavior.ajax.AjaxBehavior;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.message.Message;
import org.primefaces.component.spinner.Spinner;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;

import ar.com.norrmann.coqui.jsf.converter.ClienteConverter;
import ar.com.norrmann.coqui.jsf.converter.MarcaConverter;
import ar.com.norrmann.coqui.jsf.converter.ModeloConverter;
import ar.com.norrmann.coqui.model.Automotor;
import ar.com.norrmann.coqui.model.Cliente;
import ar.com.norrmann.coqui.model.Marca;
import ar.com.norrmann.coqui.model.Modelo;
import ar.com.norrmann.coqui.model.Servicio;

@RooJsfManagedBean(entity = Automotor.class, beanName = "automotorBean")
public class AutomotorBean {
	private Marca marcaId;
    private String name = "Automotores";
    public HtmlPanelGrid populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);

        HtmlOutputText propietarioIdEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        propietarioIdEditOutput.setId("propietarioIdEditOutput");
        propietarioIdEditOutput.setValue("Propietario : *");
        htmlPanelGrid.getChildren().add(propietarioIdEditOutput);
        
        AutoComplete propietarioIdEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        propietarioIdEditInput.setId("propietarioIdEditInput");
        propietarioIdEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{automotorBean.automotor.propietarioId}", Cliente.class));
        propietarioIdEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{automotorBean.completePropietarioId}", List.class, new Class[] { String.class }));
        propietarioIdEditInput.setDropdown(true);
        propietarioIdEditInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "propietarioId", String.class));
        propietarioIdEditInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{propietarioId.apellido}, #{propietarioId.nombres}", String.class));
        propietarioIdEditInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{propietarioId}", Cliente.class));
        propietarioIdEditInput.setConverter(new ClienteConverter());
        propietarioIdEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(propietarioIdEditInput);
        
        Message propietarioIdEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        propietarioIdEditInputMessage.setId("propietarioIdEditInputMessage");
        propietarioIdEditInputMessage.setFor("propietarioIdEditInput");
        propietarioIdEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(propietarioIdEditInputMessage);

        
        HtmlOutputText marcaIdCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        marcaIdCreateOutput.setId("marcaIdCreateOutput");
        marcaIdCreateOutput.setValue("Marca:");
        htmlPanelGrid.getChildren().add(marcaIdCreateOutput);
        
        AutoComplete marcaIdCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        marcaIdCreateInput.setId("marcaIdCreateInput");
        marcaIdCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{automotorBean.marcaId}", Marca.class));
        marcaIdCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{automotorBean.completeMarcaId}", List.class, new Class[] { String.class }));
        marcaIdCreateInput.setDropdown(true);
        marcaIdCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "marcaId", String.class));
        marcaIdCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{marcaId.marca}", String.class));
        marcaIdCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{marcaId}", Marca.class));
        marcaIdCreateInput.setConverter(new MarcaConverter());
        marcaIdCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(marcaIdCreateInput);
        AjaxBehavior pajax;
        pajax = new AjaxBehavior();
        pajax.setUpdate( "modeloIdCreateInput" );
        marcaIdCreateInput.addClientBehavior( "itemSelect", pajax );

        Message marcaIdCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        marcaIdCreateInputMessage.setId("marcaIdCreateInputMessage");
        marcaIdCreateInputMessage.setFor("marcaIdCreateInput");
        marcaIdCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(marcaIdCreateInputMessage);
        
        
        HtmlOutputText modeloIdCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        modeloIdCreateOutput.setId("modeloIdCreateOutput");
        modeloIdCreateOutput.setValue("Modelo :");
        htmlPanelGrid.getChildren().add(modeloIdCreateOutput);
        
        AutoComplete modeloIdCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        modeloIdCreateInput.setId("modeloIdCreateInput");
        modeloIdCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{automotorBean.automotor.modeloId}", Modelo.class));
        modeloIdCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{automotorBean.completeModeloId}", List.class, new Class[] { String.class }));
        modeloIdCreateInput.setDropdown(true);
        modeloIdCreateInput.setMinQueryLength(4);
        modeloIdCreateInput.setMaxResults(10);
        modeloIdCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "modeloId", String.class));
        modeloIdCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{modeloId.modelo}", String.class));
        modeloIdCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{modeloId}", Modelo.class));
        modeloIdCreateInput.setConverter(new ModeloConverter());
        modeloIdCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(modeloIdCreateInput);
        
        Message modeloIdCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        modeloIdCreateInputMessage.setId("modeloIdCreateInputMessage");
        modeloIdCreateInputMessage.setFor("modeloIdCreateInput");
        modeloIdCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(modeloIdCreateInputMessage);
        
        HtmlOutputText anioCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        anioCreateOutput.setId("anioCreateOutput");
        anioCreateOutput.setValue("Anio: * ");
        htmlPanelGrid.getChildren().add(anioCreateOutput);
        
        Spinner anioCreateInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        anioCreateInput.setId("anioCreateInput");
        anioCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{automotorBean.automotor.anio}", Integer.class));
        anioCreateInput.setRequired(true);
        
        htmlPanelGrid.getChildren().add(anioCreateInput);
        
        Message anioCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        anioCreateInputMessage.setId("anioCreateInputMessage");
        anioCreateInputMessage.setFor("anioCreateInput");
        anioCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(anioCreateInputMessage);
        
        HtmlOutputText dominioCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        dominioCreateOutput.setId("dominioCreateOutput");
        dominioCreateOutput.setValue("Patente: * ");
        htmlPanelGrid.getChildren().add(dominioCreateOutput);
        
        InputText dominioCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        dominioCreateInput.setId("dominioCreateInput");
        dominioCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{automotorBean.automotor.dominio}", String.class));
        LengthValidator dominioCreateInputValidator = new LengthValidator();
        dominioCreateInputValidator.setMaximum(255);
        dominioCreateInput.addValidator(dominioCreateInputValidator);
        dominioCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(dominioCreateInput);
        
        Message dominioCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        dominioCreateInputMessage.setId("dominioCreateInputMessage");
        dominioCreateInputMessage.setFor("dominioCreateInput");
        dominioCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(dominioCreateInputMessage);
        
        return htmlPanelGrid;
    }
    
    public HtmlPanelGrid populateEditPanel() {
    	return populateCreatePanel();
    }
    public HtmlPanelGrid populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);

        HtmlOutputText dominioLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        dominioLabel.setId("dominioLabel");
        dominioLabel.setValue("Patente:   ");
        htmlPanelGrid.getChildren().add(dominioLabel);
        
        HtmlOutputText dominioValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        dominioValue.setId("dominioValue");
        dominioValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{automotorBean.automotor.dominio}", String.class));
        htmlPanelGrid.getChildren().add(dominioValue);
        
        HtmlOutputText marcaIdLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        marcaIdLabel.setId("marcaIdLabel");
        marcaIdLabel.setValue("Marca :");
        htmlPanelGrid.getChildren().add(marcaIdLabel);
        
        HtmlOutputText marcaIdValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        marcaIdValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{automotorBean.automotor.modeloId.marcaId.marca}", String.class));
        htmlPanelGrid.getChildren().add(marcaIdValue);
        
        HtmlOutputText modeloIdLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        modeloIdLabel.setId("modeloIdLabel");
        modeloIdLabel.setValue("Modelo :");
        htmlPanelGrid.getChildren().add(modeloIdLabel);
        
        HtmlOutputText modeloIdValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        modeloIdValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{automotorBean.automotor.modeloId.modelo}", String.class));
        htmlPanelGrid.getChildren().add(modeloIdValue);
        
        HtmlOutputText anioLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        anioLabel.setId("anioLabel");
        anioLabel.setValue("Año:   ");
        htmlPanelGrid.getChildren().add(anioLabel);
        
        HtmlOutputText anioValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        anioValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{automotorBean.automotor.anio}", String.class));
        htmlPanelGrid.getChildren().add(anioValue);
        
        return htmlPanelGrid;
    }

    public List<Marca> completeMarcaId(String query) {
        List<Marca> suggestions = new ArrayList<Marca>();
        for (Marca marca : Marca.findMarcasAutomotor().getResultList()) {
            String marcaStr = String.valueOf(marca.getMarca());
            if (marcaStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(marca);
            }
        }
        return suggestions;
    }
    public List<Modelo> completeModeloId(String query) {
        List<Modelo> suggestions = new ArrayList<Modelo>();
        if (marcaId==null)return suggestions;
        for (Modelo modelo : Modelo.findModeloesByMarcaId(marcaId).getResultList()) {
            String modeloStr = String.valueOf(modelo.getModelo());
            if (modeloStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(modelo);
            }
        }
        return suggestions;
    }
	public Marca getMarcaId() {
		return marcaId;
	}
	public void setMarcaId(Marca marcaId) {
		this.marcaId = marcaId;
	}
	   public String onEdit() {
		   if (getAutomotor()!=null&&getAutomotor().getModeloId()!=null){
			   marcaId=getAutomotor().getModeloId().getMarcaId();
		   }
	        return null;
	    }
	    public List<Servicio> getServicioList(){
	    	if (getAutomotor()!=null){
	    		return Servicio.findServiciosByAutomotorId(getAutomotor()).getResultList();
	    	} else {
	    		return null;
	    	}
	    	
	    }

}
