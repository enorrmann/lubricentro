// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.norrmann.coqui.jsf;

import ar.com.norrmann.coqui.jsf.ClienteDebeBean;
import ar.com.norrmann.coqui.jsf.converter.ClienteConverter;
import ar.com.norrmann.coqui.jsf.converter.ServicioConverter;
import ar.com.norrmann.coqui.jsf.converter.VentaConverter;
import ar.com.norrmann.coqui.model.Cliente;
import ar.com.norrmann.coqui.model.ClienteDebe;
import ar.com.norrmann.coqui.model.Servicio;
import ar.com.norrmann.coqui.model.Venta;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.message.Message;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;

privileged aspect ClienteDebeBean_Roo_ManagedBean {
    
    declare @type: ClienteDebeBean: @ManagedBean(name = "clienteDebeBean");
    
    declare @type: ClienteDebeBean: @SessionScoped;
    
    private String ClienteDebeBean.name = "ClienteDebes";
    
    private ClienteDebe ClienteDebeBean.clienteDebe;
    
    private List<ClienteDebe> ClienteDebeBean.allClienteDebes;
    
    private boolean ClienteDebeBean.dataVisible = false;
    
    private List<String> ClienteDebeBean.columns;
    
    private HtmlPanelGrid ClienteDebeBean.createPanelGrid;
    
    private HtmlPanelGrid ClienteDebeBean.editPanelGrid;
    
    private HtmlPanelGrid ClienteDebeBean.viewPanelGrid;
    
    private boolean ClienteDebeBean.createDialogVisible = false;
    
    @PostConstruct
    public void ClienteDebeBean.init() {
        columns = new ArrayList<String>();
        columns.add("fecha");
        columns.add("debe");
        columns.add("observaciones");
    }
    
    public String ClienteDebeBean.getName() {
        return name;
    }
    
    public List<String> ClienteDebeBean.getColumns() {
        return columns;
    }
    
    public List<ClienteDebe> ClienteDebeBean.getAllClienteDebes() {
        return allClienteDebes;
    }
    
    public void ClienteDebeBean.setAllClienteDebes(List<ClienteDebe> allClienteDebes) {
        this.allClienteDebes = allClienteDebes;
    }
    
    public String ClienteDebeBean.findAllClienteDebes() {
        allClienteDebes = ClienteDebe.findAllClienteDebes();
        dataVisible = !allClienteDebes.isEmpty();
        return null;
    }
    
    public boolean ClienteDebeBean.isDataVisible() {
        return dataVisible;
    }
    
    public void ClienteDebeBean.setDataVisible(boolean dataVisible) {
        this.dataVisible = dataVisible;
    }
    
    public HtmlPanelGrid ClienteDebeBean.getCreatePanelGrid() {
        if (createPanelGrid == null) {
            createPanelGrid = populateCreatePanel();
        }
        return createPanelGrid;
    }
    
    public void ClienteDebeBean.setCreatePanelGrid(HtmlPanelGrid createPanelGrid) {
        this.createPanelGrid = createPanelGrid;
    }
    
    public HtmlPanelGrid ClienteDebeBean.getEditPanelGrid() {
        if (editPanelGrid == null) {
            editPanelGrid = populateEditPanel();
        }
        return editPanelGrid;
    }
    
    public void ClienteDebeBean.setEditPanelGrid(HtmlPanelGrid editPanelGrid) {
        this.editPanelGrid = editPanelGrid;
    }
    
    public HtmlPanelGrid ClienteDebeBean.getViewPanelGrid() {
        return populateViewPanel();
    }
    
    public void ClienteDebeBean.setViewPanelGrid(HtmlPanelGrid viewPanelGrid) {
        this.viewPanelGrid = viewPanelGrid;
    }
    
    public HtmlPanelGrid ClienteDebeBean.populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText fechaCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        fechaCreateOutput.setId("fechaCreateOutput");
        fechaCreateOutput.setValue("Fecha:   ");
        htmlPanelGrid.getChildren().add(fechaCreateOutput);
        
        Calendar fechaCreateInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        fechaCreateInput.setId("fechaCreateInput");
        fechaCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{clienteDebeBean.clienteDebe.fecha}", Date.class));
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
        
        HtmlOutputText clienteCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        clienteCreateOutput.setId("clienteCreateOutput");
        clienteCreateOutput.setValue("Cliente: * ");
        htmlPanelGrid.getChildren().add(clienteCreateOutput);
        
        AutoComplete clienteCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        clienteCreateInput.setId("clienteCreateInput");
        clienteCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{clienteDebeBean.clienteDebe.cliente}", Cliente.class));
        clienteCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{clienteDebeBean.completeCliente}", List.class, new Class[] { String.class }));
        clienteCreateInput.setDropdown(true);
        clienteCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "cliente", String.class));
        clienteCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{cliente.apellido} #{cliente.domicilio} #{cliente.email} #{cliente.nombres}", String.class));
        clienteCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{cliente}", Cliente.class));
        clienteCreateInput.setConverter(new ClienteConverter());
        clienteCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(clienteCreateInput);
        
        Message clienteCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        clienteCreateInputMessage.setId("clienteCreateInputMessage");
        clienteCreateInputMessage.setFor("clienteCreateInput");
        clienteCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(clienteCreateInputMessage);
        
        HtmlOutputText ventaCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        ventaCreateOutput.setId("ventaCreateOutput");
        ventaCreateOutput.setValue("Venta:   ");
        htmlPanelGrid.getChildren().add(ventaCreateOutput);
        
        AutoComplete ventaCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        ventaCreateInput.setId("ventaCreateInput");
        ventaCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{clienteDebeBean.clienteDebe.venta}", Venta.class));
        ventaCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{clienteDebeBean.completeVenta}", List.class, new Class[] { String.class }));
        ventaCreateInput.setDropdown(true);
        ventaCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "venta", String.class));
        ventaCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{venta.fecha}", String.class));
        ventaCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{venta}", Venta.class));
        ventaCreateInput.setConverter(new VentaConverter());
        ventaCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(ventaCreateInput);
        
        Message ventaCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        ventaCreateInputMessage.setId("ventaCreateInputMessage");
        ventaCreateInputMessage.setFor("ventaCreateInput");
        ventaCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(ventaCreateInputMessage);
        
        HtmlOutputText servicioCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        servicioCreateOutput.setId("servicioCreateOutput");
        servicioCreateOutput.setValue("Servicio:   ");
        htmlPanelGrid.getChildren().add(servicioCreateOutput);
        
        AutoComplete servicioCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        servicioCreateInput.setId("servicioCreateInput");
        servicioCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{clienteDebeBean.clienteDebe.servicio}", Servicio.class));
        servicioCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{clienteDebeBean.completeServicio}", List.class, new Class[] { String.class }));
        servicioCreateInput.setDropdown(true);
        servicioCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "servicio", String.class));
        servicioCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{servicio.descripcion} #{servicio.kilometros} #{servicio.fecha}", String.class));
        servicioCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{servicio}", Servicio.class));
        servicioCreateInput.setConverter(new ServicioConverter());
        servicioCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(servicioCreateInput);
        
        Message servicioCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        servicioCreateInputMessage.setId("servicioCreateInputMessage");
        servicioCreateInputMessage.setFor("servicioCreateInput");
        servicioCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(servicioCreateInputMessage);
        
        HtmlOutputText debeCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        debeCreateOutput.setId("debeCreateOutput");
        debeCreateOutput.setValue("Debe: * ");
        htmlPanelGrid.getChildren().add(debeCreateOutput);
        
        InputText debeCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        debeCreateInput.setId("debeCreateInput");
        debeCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{clienteDebeBean.clienteDebe.debe}", BigDecimal.class));
        debeCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(debeCreateInput);
        
        Message debeCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        debeCreateInputMessage.setId("debeCreateInputMessage");
        debeCreateInputMessage.setFor("debeCreateInput");
        debeCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(debeCreateInputMessage);
        
        HtmlOutputText observacionesCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        observacionesCreateOutput.setId("observacionesCreateOutput");
        observacionesCreateOutput.setValue("Observaciones:   ");
        htmlPanelGrid.getChildren().add(observacionesCreateOutput);
        
        InputText observacionesCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        observacionesCreateInput.setId("observacionesCreateInput");
        observacionesCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{clienteDebeBean.clienteDebe.observaciones}", String.class));
        htmlPanelGrid.getChildren().add(observacionesCreateInput);
        
        Message observacionesCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        observacionesCreateInputMessage.setId("observacionesCreateInputMessage");
        observacionesCreateInputMessage.setFor("observacionesCreateInput");
        observacionesCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(observacionesCreateInputMessage);
        
        return htmlPanelGrid;
    }
    
    public HtmlPanelGrid ClienteDebeBean.populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText fechaEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        fechaEditOutput.setId("fechaEditOutput");
        fechaEditOutput.setValue("Fecha:   ");
        htmlPanelGrid.getChildren().add(fechaEditOutput);
        
        Calendar fechaEditInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        fechaEditInput.setId("fechaEditInput");
        fechaEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{clienteDebeBean.clienteDebe.fecha}", Date.class));
        fechaEditInput.setNavigator(true);
        fechaEditInput.setEffect("slideDown");
        fechaEditInput.setPattern("dd/MM/yyyy");
        fechaEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(fechaEditInput);
        
        Message fechaEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        fechaEditInputMessage.setId("fechaEditInputMessage");
        fechaEditInputMessage.setFor("fechaEditInput");
        fechaEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(fechaEditInputMessage);
        
        HtmlOutputText clienteEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        clienteEditOutput.setId("clienteEditOutput");
        clienteEditOutput.setValue("Cliente: * ");
        htmlPanelGrid.getChildren().add(clienteEditOutput);
        
        AutoComplete clienteEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        clienteEditInput.setId("clienteEditInput");
        clienteEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{clienteDebeBean.clienteDebe.cliente}", Cliente.class));
        clienteEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{clienteDebeBean.completeCliente}", List.class, new Class[] { String.class }));
        clienteEditInput.setDropdown(true);
        clienteEditInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "cliente", String.class));
        clienteEditInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{cliente.apellido} #{cliente.domicilio} #{cliente.email} #{cliente.nombres}", String.class));
        clienteEditInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{cliente}", Cliente.class));
        clienteEditInput.setConverter(new ClienteConverter());
        clienteEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(clienteEditInput);
        
        Message clienteEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        clienteEditInputMessage.setId("clienteEditInputMessage");
        clienteEditInputMessage.setFor("clienteEditInput");
        clienteEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(clienteEditInputMessage);
        
        HtmlOutputText ventaEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        ventaEditOutput.setId("ventaEditOutput");
        ventaEditOutput.setValue("Venta:   ");
        htmlPanelGrid.getChildren().add(ventaEditOutput);
        
        AutoComplete ventaEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        ventaEditInput.setId("ventaEditInput");
        ventaEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{clienteDebeBean.clienteDebe.venta}", Venta.class));
        ventaEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{clienteDebeBean.completeVenta}", List.class, new Class[] { String.class }));
        ventaEditInput.setDropdown(true);
        ventaEditInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "venta", String.class));
        ventaEditInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{venta.fecha}", String.class));
        ventaEditInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{venta}", Venta.class));
        ventaEditInput.setConverter(new VentaConverter());
        ventaEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(ventaEditInput);
        
        Message ventaEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        ventaEditInputMessage.setId("ventaEditInputMessage");
        ventaEditInputMessage.setFor("ventaEditInput");
        ventaEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(ventaEditInputMessage);
        
        HtmlOutputText servicioEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        servicioEditOutput.setId("servicioEditOutput");
        servicioEditOutput.setValue("Servicio:   ");
        htmlPanelGrid.getChildren().add(servicioEditOutput);
        
        AutoComplete servicioEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        servicioEditInput.setId("servicioEditInput");
        servicioEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{clienteDebeBean.clienteDebe.servicio}", Servicio.class));
        servicioEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{clienteDebeBean.completeServicio}", List.class, new Class[] { String.class }));
        servicioEditInput.setDropdown(true);
        servicioEditInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "servicio", String.class));
        servicioEditInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{servicio.descripcion} #{servicio.kilometros} #{servicio.fecha}", String.class));
        servicioEditInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{servicio}", Servicio.class));
        servicioEditInput.setConverter(new ServicioConverter());
        servicioEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(servicioEditInput);
        
        Message servicioEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        servicioEditInputMessage.setId("servicioEditInputMessage");
        servicioEditInputMessage.setFor("servicioEditInput");
        servicioEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(servicioEditInputMessage);
        
        HtmlOutputText debeEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        debeEditOutput.setId("debeEditOutput");
        debeEditOutput.setValue("Debe: * ");
        htmlPanelGrid.getChildren().add(debeEditOutput);
        
        InputText debeEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        debeEditInput.setId("debeEditInput");
        debeEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{clienteDebeBean.clienteDebe.debe}", BigDecimal.class));
        debeEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(debeEditInput);
        
        Message debeEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        debeEditInputMessage.setId("debeEditInputMessage");
        debeEditInputMessage.setFor("debeEditInput");
        debeEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(debeEditInputMessage);
        
        HtmlOutputText observacionesEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        observacionesEditOutput.setId("observacionesEditOutput");
        observacionesEditOutput.setValue("Observaciones:   ");
        htmlPanelGrid.getChildren().add(observacionesEditOutput);
        
        InputText observacionesEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        observacionesEditInput.setId("observacionesEditInput");
        observacionesEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{clienteDebeBean.clienteDebe.observaciones}", String.class));
        htmlPanelGrid.getChildren().add(observacionesEditInput);
        
        Message observacionesEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        observacionesEditInputMessage.setId("observacionesEditInputMessage");
        observacionesEditInputMessage.setFor("observacionesEditInput");
        observacionesEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(observacionesEditInputMessage);
        
        return htmlPanelGrid;
    }
    
    public HtmlPanelGrid ClienteDebeBean.populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText fechaLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        fechaLabel.setId("fechaLabel");
        fechaLabel.setValue("Fecha:   ");
        htmlPanelGrid.getChildren().add(fechaLabel);
        
        HtmlOutputText fechaValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        fechaValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{clienteDebeBean.clienteDebe.fecha}", Date.class));
        DateTimeConverter fechaValueConverter = (DateTimeConverter) application.createConverter(DateTimeConverter.CONVERTER_ID);
        fechaValueConverter.setPattern("dd/MM/yyyy");
        fechaValue.setConverter(fechaValueConverter);
        htmlPanelGrid.getChildren().add(fechaValue);
        
        HtmlOutputText clienteLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        clienteLabel.setId("clienteLabel");
        clienteLabel.setValue("Cliente:   ");
        htmlPanelGrid.getChildren().add(clienteLabel);
        
        HtmlOutputText clienteValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        clienteValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{clienteDebeBean.clienteDebe.cliente}", Cliente.class));
        clienteValue.setConverter(new ClienteConverter());
        htmlPanelGrid.getChildren().add(clienteValue);
        
        HtmlOutputText ventaLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        ventaLabel.setId("ventaLabel");
        ventaLabel.setValue("Venta:   ");
        htmlPanelGrid.getChildren().add(ventaLabel);
        
        HtmlOutputText ventaValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        ventaValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{clienteDebeBean.clienteDebe.venta}", Venta.class));
        ventaValue.setConverter(new VentaConverter());
        htmlPanelGrid.getChildren().add(ventaValue);
        
        HtmlOutputText servicioLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        servicioLabel.setId("servicioLabel");
        servicioLabel.setValue("Servicio:   ");
        htmlPanelGrid.getChildren().add(servicioLabel);
        
        HtmlOutputText servicioValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        servicioValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{clienteDebeBean.clienteDebe.servicio}", Servicio.class));
        servicioValue.setConverter(new ServicioConverter());
        htmlPanelGrid.getChildren().add(servicioValue);
        
        HtmlOutputText debeLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        debeLabel.setId("debeLabel");
        debeLabel.setValue("Debe:   ");
        htmlPanelGrid.getChildren().add(debeLabel);
        
        HtmlOutputText debeValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        debeValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{clienteDebeBean.clienteDebe.debe}", String.class));
        htmlPanelGrid.getChildren().add(debeValue);
        
        HtmlOutputText observacionesLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        observacionesLabel.setId("observacionesLabel");
        observacionesLabel.setValue("Observaciones:   ");
        htmlPanelGrid.getChildren().add(observacionesLabel);
        
        HtmlOutputText observacionesValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        observacionesValue.setId("observacionesValue");
        observacionesValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{clienteDebeBean.clienteDebe.observaciones}", String.class));
        htmlPanelGrid.getChildren().add(observacionesValue);
        
        return htmlPanelGrid;
    }
    
    public ClienteDebe ClienteDebeBean.getClienteDebe() {
        if (clienteDebe == null) {
            clienteDebe = new ClienteDebe();
        }
        return clienteDebe;
    }
    
    public void ClienteDebeBean.setClienteDebe(ClienteDebe clienteDebe) {
        this.clienteDebe = clienteDebe;
    }
    
    public List<Cliente> ClienteDebeBean.completeCliente(String query) {
        List<Cliente> suggestions = new ArrayList<Cliente>();
        for (Cliente cliente : Cliente.findAllClientes()) {
            String clienteStr = String.valueOf(cliente.getApellido() +  " "  + cliente.getDomicilio() +  " "  + cliente.getEmail() +  " "  + cliente.getNombres());
            if (clienteStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(cliente);
            }
        }
        return suggestions;
    }
    
    public List<Venta> ClienteDebeBean.completeVenta(String query) {
        List<Venta> suggestions = new ArrayList<Venta>();
        for (Venta venta : Venta.findAllVentas()) {
            String ventaStr = String.valueOf(venta.getFecha());
            if (ventaStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(venta);
            }
        }
        return suggestions;
    }
    
    public List<Servicio> ClienteDebeBean.completeServicio(String query) {
        List<Servicio> suggestions = new ArrayList<Servicio>();
        for (Servicio servicio : Servicio.findAllServicios()) {
            String servicioStr = String.valueOf(servicio.getDescripcion() +  " "  + servicio.getKilometros() +  " "  + servicio.getFecha());
            if (servicioStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(servicio);
            }
        }
        return suggestions;
    }
    
    public String ClienteDebeBean.onEdit() {
        return null;
    }
    
    public boolean ClienteDebeBean.isCreateDialogVisible() {
        return createDialogVisible;
    }
    
    public void ClienteDebeBean.setCreateDialogVisible(boolean createDialogVisible) {
        this.createDialogVisible = createDialogVisible;
    }
    
    public String ClienteDebeBean.displayList() {
        createDialogVisible = false;
        findAllClienteDebes();
        return "clienteDebe";
    }
    
    public String ClienteDebeBean.displayCreateDialog() {
        clienteDebe = new ClienteDebe();
        createDialogVisible = true;
        return "clienteDebe";
    }
    
    public String ClienteDebeBean.persist() {
        String message = "";
        if (clienteDebe.getId() != null) {
            clienteDebe.merge();
            message = "Successfully updated";
        } else {
            clienteDebe.persist();
            message = "Successfully created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialog.hide()");
        context.execute("editDialog.hide()");
        
        FacesMessage facesMessage = new FacesMessage(message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllClienteDebes();
    }
    
    public String ClienteDebeBean.delete() {
        clienteDebe.remove();
        FacesMessage facesMessage = new FacesMessage("Successfully deleted");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllClienteDebes();
    }
    
    public void ClienteDebeBean.reset() {
        clienteDebe = null;
        createDialogVisible = false;
    }
    
    public void ClienteDebeBean.handleDialogClose(CloseEvent event) {
        reset();
    }
    
}
