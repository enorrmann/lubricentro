package ar.com.norrmann.coqui.jsf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.validator.LengthValidator;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.message.Message;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;

import ar.com.norrmann.coqui.model.Cliente;

@RooJsfManagedBean(entity = Cliente.class, beanName = "clienteBean")
public class ClienteBean {
	
    private ArrayList<String> columns;
    private BigDecimal saldoTotal; 

	@PostConstruct
    public void init() {
        columns = new ArrayList<String>();
        columns.add("apellido");
        columns.add("nombres");
        columns.add("cuit");
        columns.add("domicilio");
        columns.add("email");
        columns.add("telefono");
    }
    

    public HtmlPanelGrid populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText apellidoCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        apellidoCreateOutput.setId("apellidoCreateOutput");
        apellidoCreateOutput.setValue("Apellido: * ");
        htmlPanelGrid.getChildren().add(apellidoCreateOutput);
        
        InputText apellidoCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        apellidoCreateInput.setId("apellidoCreateInput");
        apellidoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{clienteBean.cliente.apellido}", String.class));
        LengthValidator apellidoCreateInputValidator = new LengthValidator();
        apellidoCreateInputValidator.setMaximum(255);
        apellidoCreateInput.addValidator(apellidoCreateInputValidator);
        apellidoCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(apellidoCreateInput);
        
        Message apellidoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        apellidoCreateInputMessage.setId("apellidoCreateInputMessage");
        apellidoCreateInputMessage.setFor("apellidoCreateInput");
        apellidoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(apellidoCreateInputMessage);
        
        HtmlOutputText nombresCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        nombresCreateOutput.setId("nombresCreateOutput");
        nombresCreateOutput.setValue("Nombres: * ");
        htmlPanelGrid.getChildren().add(nombresCreateOutput);
        
        InputText nombresCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        nombresCreateInput.setId("nombresCreateInput");
        nombresCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{clienteBean.cliente.nombres}", String.class));
        LengthValidator nombresCreateInputValidator = new LengthValidator();
        nombresCreateInputValidator.setMaximum(255);
        nombresCreateInput.addValidator(nombresCreateInputValidator);
        nombresCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(nombresCreateInput);
        
        Message nombresCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        nombresCreateInputMessage.setId("nombresCreateInputMessage");
        nombresCreateInputMessage.setFor("nombresCreateInput");
        nombresCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(nombresCreateInputMessage);
        
        HtmlOutputText domicilioCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        domicilioCreateOutput.setId("domicilioCreateOutput");
        domicilioCreateOutput.setValue("Domicilio: ");
        htmlPanelGrid.getChildren().add(domicilioCreateOutput);
        
        InputText domicilioCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        domicilioCreateInput.setId("domicilioCreateInput");
        domicilioCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{clienteBean.cliente.domicilio}", String.class));
        LengthValidator domicilioCreateInputValidator = new LengthValidator();
        domicilioCreateInputValidator.setMaximum(255);
        domicilioCreateInput.addValidator(domicilioCreateInputValidator);
        domicilioCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(domicilioCreateInput);
        
        Message domicilioCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        domicilioCreateInputMessage.setId("domicilioCreateInputMessage");
        domicilioCreateInputMessage.setFor("domicilioCreateInput");
        domicilioCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(domicilioCreateInputMessage);
        
        HtmlOutputText emailCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        emailCreateOutput.setId("emailCreateOutput");
        emailCreateOutput.setValue("Email:");
        htmlPanelGrid.getChildren().add(emailCreateOutput);
        
        InputText emailCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        emailCreateInput.setId("emailCreateInput");
        emailCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{clienteBean.cliente.email}", String.class));
        LengthValidator emailCreateInputValidator = new LengthValidator();
        emailCreateInputValidator.setMaximum(255);
        emailCreateInput.addValidator(emailCreateInputValidator);
        emailCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(emailCreateInput);
        
        Message emailCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        emailCreateInputMessage.setId("emailCreateInputMessage");
        emailCreateInputMessage.setFor("emailCreateInput");
        emailCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(emailCreateInputMessage);
        
        HtmlOutputText cuitCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        cuitCreateOutput.setId("cuitCreateOutput");
        cuitCreateOutput.setValue("C.U.I.T: ");
        htmlPanelGrid.getChildren().add(cuitCreateOutput);
        
        InputText cuitCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        cuitCreateInput.setId("cuitCreateInput");
        cuitCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{clienteBean.cliente.cuit}", String.class));
        LengthValidator cuitCreateInputValidator = new LengthValidator();
        cuitCreateInputValidator.setMaximum(255);
        cuitCreateInput.addValidator(cuitCreateInputValidator);
        cuitCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(cuitCreateInput);
        
        Message cuitCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        cuitCreateInputMessage.setId("cuitCreateInputMessage");
        cuitCreateInputMessage.setFor("cuitCreateInput");
        cuitCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(cuitCreateInputMessage);

        
        HtmlOutputText telefonoCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        telefonoCreateOutput.setId("telefonoCreateOutput");
        telefonoCreateOutput.setValue("Telefono: ");
        htmlPanelGrid.getChildren().add(telefonoCreateOutput);
        
        InputText telefonoCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        telefonoCreateInput.setId("telefonoCreateInput");
        telefonoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{clienteBean.cliente.telefono}", String.class));
        LengthValidator telefonoCreateInputValidator = new LengthValidator();
        telefonoCreateInputValidator.setMaximum(255);
        telefonoCreateInput.addValidator(telefonoCreateInputValidator);
        telefonoCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(telefonoCreateInput);
        
        Message telefonoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        telefonoCreateInputMessage.setId("telefonoCreateInputMessage");
        telefonoCreateInputMessage.setFor("telefonoCreateInput");
        telefonoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(telefonoCreateInputMessage);
        
        return htmlPanelGrid;
    }
    public HtmlPanelGrid populateEditPanel() {
    	return populateCreatePanel();
    	
    }
    
    public String displayMorosoList() {
        setCreateDialogVisible(false);
        saldoTotal = new BigDecimal(0);
        List<Cliente> clientesAll = Cliente.findAllClientes();
        setAllClientes(new ArrayList<Cliente>());
        for (Cliente unCliente : clientesAll){
        	BigDecimal saldo = unCliente.getSaldo(); 
        	if (saldo.compareTo(new BigDecimal(0))>0){
        		getAllClientes().add(unCliente);
        		saldoTotal = saldoTotal.add(saldo);
        	}
        }
        setDataVisible(!getAllClientes().isEmpty());
        return "cliente";
    }

    public String onEdit() {
        return null;
    }


	public BigDecimal getSaldoTotal() {
		return saldoTotal;
	}


	public void setSaldoTotal(BigDecimal saldoTotal) {
		this.saldoTotal = saldoTotal;
	}

}
