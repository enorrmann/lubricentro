// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.norrmann.coqui.jsf;

import ar.com.norrmann.coqui.jsf.ItemservicioBean;
import ar.com.norrmann.coqui.jsf.converter.ItemConverter;
import ar.com.norrmann.coqui.jsf.converter.MovimientoitemConverter;
import ar.com.norrmann.coqui.jsf.converter.ServicioConverter;
import ar.com.norrmann.coqui.model.Item;
import ar.com.norrmann.coqui.model.Itemservicio;
import ar.com.norrmann.coqui.model.Movimientoitem;
import ar.com.norrmann.coqui.model.Servicio;
import java.util.ArrayList;
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
import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.message.Message;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;

privileged aspect ItemservicioBean_Roo_ManagedBean {
    
    declare @type: ItemservicioBean: @ManagedBean(name = "itemservicioBean");
    
    declare @type: ItemservicioBean: @SessionScoped;
    
    private String ItemservicioBean.name = "Itemservicios";
    
    private Itemservicio ItemservicioBean.itemservicio;
    
    private List<Itemservicio> ItemservicioBean.allItemservicios;
    
    private boolean ItemservicioBean.dataVisible = false;
    
    private List<String> ItemservicioBean.columns;
    
    private HtmlPanelGrid ItemservicioBean.createPanelGrid;
    
    private HtmlPanelGrid ItemservicioBean.editPanelGrid;
    
    private HtmlPanelGrid ItemservicioBean.viewPanelGrid;
    
    private boolean ItemservicioBean.createDialogVisible = false;
    
    @PostConstruct
    public void ItemservicioBean.init() {
        columns = new ArrayList<String>();
    }
    
    public String ItemservicioBean.getName() {
        return name;
    }
    
    public List<String> ItemservicioBean.getColumns() {
        return columns;
    }
    
    public List<Itemservicio> ItemservicioBean.getAllItemservicios() {
        return allItemservicios;
    }
    
    public void ItemservicioBean.setAllItemservicios(List<Itemservicio> allItemservicios) {
        this.allItemservicios = allItemservicios;
    }
    
    public String ItemservicioBean.findAllItemservicios() {
        allItemservicios = Itemservicio.findAllItemservicios();
        dataVisible = !allItemservicios.isEmpty();
        return null;
    }
    
    public boolean ItemservicioBean.isDataVisible() {
        return dataVisible;
    }
    
    public void ItemservicioBean.setDataVisible(boolean dataVisible) {
        this.dataVisible = dataVisible;
    }
    
    public HtmlPanelGrid ItemservicioBean.getCreatePanelGrid() {
        if (createPanelGrid == null) {
            createPanelGrid = populateCreatePanel();
        }
        return createPanelGrid;
    }
    
    public void ItemservicioBean.setCreatePanelGrid(HtmlPanelGrid createPanelGrid) {
        this.createPanelGrid = createPanelGrid;
    }
    
    public HtmlPanelGrid ItemservicioBean.getEditPanelGrid() {
        if (editPanelGrid == null) {
            editPanelGrid = populateEditPanel();
        }
        return editPanelGrid;
    }
    
    public void ItemservicioBean.setEditPanelGrid(HtmlPanelGrid editPanelGrid) {
        this.editPanelGrid = editPanelGrid;
    }
    
    public HtmlPanelGrid ItemservicioBean.getViewPanelGrid() {
        return populateViewPanel();
    }
    
    public void ItemservicioBean.setViewPanelGrid(HtmlPanelGrid viewPanelGrid) {
        this.viewPanelGrid = viewPanelGrid;
    }
    
    public HtmlPanelGrid ItemservicioBean.populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText egresoStockIdCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        egresoStockIdCreateOutput.setId("egresoStockIdCreateOutput");
        egresoStockIdCreateOutput.setValue("Egreso Stock Id:   ");
        htmlPanelGrid.getChildren().add(egresoStockIdCreateOutput);
        
        AutoComplete egresoStockIdCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        egresoStockIdCreateInput.setId("egresoStockIdCreateInput");
        egresoStockIdCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemservicioBean.itemservicio.egresoStockId}", Movimientoitem.class));
        egresoStockIdCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{itemservicioBean.completeEgresoStockId}", List.class, new Class[] { String.class }));
        egresoStockIdCreateInput.setDropdown(true);
        egresoStockIdCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "egresoStockId", String.class));
        egresoStockIdCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{egresoStockId.cantidad} #{egresoStockId.fecha} #{egresoStockId.observaciones}", String.class));
        egresoStockIdCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{egresoStockId}", Movimientoitem.class));
        egresoStockIdCreateInput.setConverter(new MovimientoitemConverter());
        egresoStockIdCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(egresoStockIdCreateInput);
        
        Message egresoStockIdCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        egresoStockIdCreateInputMessage.setId("egresoStockIdCreateInputMessage");
        egresoStockIdCreateInputMessage.setFor("egresoStockIdCreateInput");
        egresoStockIdCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(egresoStockIdCreateInputMessage);
        
        HtmlOutputText servicioIdCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        servicioIdCreateOutput.setId("servicioIdCreateOutput");
        servicioIdCreateOutput.setValue("Servicio Id:   ");
        htmlPanelGrid.getChildren().add(servicioIdCreateOutput);
        
        AutoComplete servicioIdCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        servicioIdCreateInput.setId("servicioIdCreateInput");
        servicioIdCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemservicioBean.itemservicio.servicioId}", Servicio.class));
        servicioIdCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{itemservicioBean.completeServicioId}", List.class, new Class[] { String.class }));
        servicioIdCreateInput.setDropdown(true);
        servicioIdCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "servicioId", String.class));
        servicioIdCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{servicioId.descripcion} #{servicioId.kilometros} #{servicioId.fecha}", String.class));
        servicioIdCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{servicioId}", Servicio.class));
        servicioIdCreateInput.setConverter(new ServicioConverter());
        servicioIdCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(servicioIdCreateInput);
        
        Message servicioIdCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        servicioIdCreateInputMessage.setId("servicioIdCreateInputMessage");
        servicioIdCreateInputMessage.setFor("servicioIdCreateInput");
        servicioIdCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(servicioIdCreateInputMessage);
        
        HtmlOutputText itemIdCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        itemIdCreateOutput.setId("itemIdCreateOutput");
        itemIdCreateOutput.setValue("Item Id:   ");
        htmlPanelGrid.getChildren().add(itemIdCreateOutput);
        
        AutoComplete itemIdCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        itemIdCreateInput.setId("itemIdCreateInput");
        itemIdCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemservicioBean.itemservicio.itemId}", Item.class));
        itemIdCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{itemservicioBean.completeItemId}", List.class, new Class[] { String.class }));
        itemIdCreateInput.setDropdown(true);
        itemIdCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "itemId", String.class));
        itemIdCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{itemId.precioCosto} #{itemId.precioVenta} #{itemId.puntoReposicion} #{itemId.stockActual}", String.class));
        itemIdCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{itemId}", Item.class));
        itemIdCreateInput.setConverter(new ItemConverter());
        itemIdCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(itemIdCreateInput);
        
        Message itemIdCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        itemIdCreateInputMessage.setId("itemIdCreateInputMessage");
        itemIdCreateInputMessage.setFor("itemIdCreateInput");
        itemIdCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(itemIdCreateInputMessage);
        
        return htmlPanelGrid;
    }
    
    public HtmlPanelGrid ItemservicioBean.populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText egresoStockIdEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        egresoStockIdEditOutput.setId("egresoStockIdEditOutput");
        egresoStockIdEditOutput.setValue("Egreso Stock Id:   ");
        htmlPanelGrid.getChildren().add(egresoStockIdEditOutput);
        
        AutoComplete egresoStockIdEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        egresoStockIdEditInput.setId("egresoStockIdEditInput");
        egresoStockIdEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemservicioBean.itemservicio.egresoStockId}", Movimientoitem.class));
        egresoStockIdEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{itemservicioBean.completeEgresoStockId}", List.class, new Class[] { String.class }));
        egresoStockIdEditInput.setDropdown(true);
        egresoStockIdEditInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "egresoStockId", String.class));
        egresoStockIdEditInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{egresoStockId.cantidad} #{egresoStockId.fecha} #{egresoStockId.observaciones}", String.class));
        egresoStockIdEditInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{egresoStockId}", Movimientoitem.class));
        egresoStockIdEditInput.setConverter(new MovimientoitemConverter());
        egresoStockIdEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(egresoStockIdEditInput);
        
        Message egresoStockIdEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        egresoStockIdEditInputMessage.setId("egresoStockIdEditInputMessage");
        egresoStockIdEditInputMessage.setFor("egresoStockIdEditInput");
        egresoStockIdEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(egresoStockIdEditInputMessage);
        
        HtmlOutputText servicioIdEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        servicioIdEditOutput.setId("servicioIdEditOutput");
        servicioIdEditOutput.setValue("Servicio Id:   ");
        htmlPanelGrid.getChildren().add(servicioIdEditOutput);
        
        AutoComplete servicioIdEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        servicioIdEditInput.setId("servicioIdEditInput");
        servicioIdEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemservicioBean.itemservicio.servicioId}", Servicio.class));
        servicioIdEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{itemservicioBean.completeServicioId}", List.class, new Class[] { String.class }));
        servicioIdEditInput.setDropdown(true);
        servicioIdEditInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "servicioId", String.class));
        servicioIdEditInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{servicioId.descripcion} #{servicioId.kilometros} #{servicioId.fecha}", String.class));
        servicioIdEditInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{servicioId}", Servicio.class));
        servicioIdEditInput.setConverter(new ServicioConverter());
        servicioIdEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(servicioIdEditInput);
        
        Message servicioIdEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        servicioIdEditInputMessage.setId("servicioIdEditInputMessage");
        servicioIdEditInputMessage.setFor("servicioIdEditInput");
        servicioIdEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(servicioIdEditInputMessage);
        
        HtmlOutputText itemIdEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        itemIdEditOutput.setId("itemIdEditOutput");
        itemIdEditOutput.setValue("Item Id:   ");
        htmlPanelGrid.getChildren().add(itemIdEditOutput);
        
        AutoComplete itemIdEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        itemIdEditInput.setId("itemIdEditInput");
        itemIdEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemservicioBean.itemservicio.itemId}", Item.class));
        itemIdEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{itemservicioBean.completeItemId}", List.class, new Class[] { String.class }));
        itemIdEditInput.setDropdown(true);
        itemIdEditInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "itemId", String.class));
        itemIdEditInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{itemId.precioCosto} #{itemId.precioVenta} #{itemId.puntoReposicion} #{itemId.stockActual}", String.class));
        itemIdEditInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{itemId}", Item.class));
        itemIdEditInput.setConverter(new ItemConverter());
        itemIdEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(itemIdEditInput);
        
        Message itemIdEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        itemIdEditInputMessage.setId("itemIdEditInputMessage");
        itemIdEditInputMessage.setFor("itemIdEditInput");
        itemIdEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(itemIdEditInputMessage);
        
        return htmlPanelGrid;
    }
    
    public HtmlPanelGrid ItemservicioBean.populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText egresoStockIdLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        egresoStockIdLabel.setId("egresoStockIdLabel");
        egresoStockIdLabel.setValue("Egreso Stock Id:   ");
        htmlPanelGrid.getChildren().add(egresoStockIdLabel);
        
        HtmlOutputText egresoStockIdValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        egresoStockIdValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemservicioBean.itemservicio.egresoStockId}", Movimientoitem.class));
        egresoStockIdValue.setConverter(new MovimientoitemConverter());
        htmlPanelGrid.getChildren().add(egresoStockIdValue);
        
        HtmlOutputText servicioIdLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        servicioIdLabel.setId("servicioIdLabel");
        servicioIdLabel.setValue("Servicio Id:   ");
        htmlPanelGrid.getChildren().add(servicioIdLabel);
        
        HtmlOutputText servicioIdValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        servicioIdValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemservicioBean.itemservicio.servicioId}", Servicio.class));
        servicioIdValue.setConverter(new ServicioConverter());
        htmlPanelGrid.getChildren().add(servicioIdValue);
        
        HtmlOutputText itemIdLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        itemIdLabel.setId("itemIdLabel");
        itemIdLabel.setValue("Item Id:   ");
        htmlPanelGrid.getChildren().add(itemIdLabel);
        
        HtmlOutputText itemIdValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        itemIdValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemservicioBean.itemservicio.itemId}", Item.class));
        itemIdValue.setConverter(new ItemConverter());
        htmlPanelGrid.getChildren().add(itemIdValue);
        
        return htmlPanelGrid;
    }
    
    public Itemservicio ItemservicioBean.getItemservicio() {
        if (itemservicio == null) {
            itemservicio = new Itemservicio();
        }
        return itemservicio;
    }
    
    public void ItemservicioBean.setItemservicio(Itemservicio itemservicio) {
        this.itemservicio = itemservicio;
    }
    
    public List<Movimientoitem> ItemservicioBean.completeEgresoStockId(String query) {
        List<Movimientoitem> suggestions = new ArrayList<Movimientoitem>();
        for (Movimientoitem movimientoitem : Movimientoitem.findAllMovimientoitems()) {
            String movimientoitemStr = String.valueOf(movimientoitem.getCantidad() +  " "  + movimientoitem.getFecha() +  " "  + movimientoitem.getObservaciones());
            if (movimientoitemStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(movimientoitem);
            }
        }
        return suggestions;
    }
    
    public List<Servicio> ItemservicioBean.completeServicioId(String query) {
        List<Servicio> suggestions = new ArrayList<Servicio>();
        for (Servicio servicio : Servicio.findAllServicios()) {
            String servicioStr = String.valueOf(servicio.getDescripcion() +  " "  + servicio.getKilometros() +  " "  + servicio.getFecha());
            if (servicioStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(servicio);
            }
        }
        return suggestions;
    }
    
    public List<Item> ItemservicioBean.completeItemId(String query) {
        List<Item> suggestions = new ArrayList<Item>();
        for (Item item : Item.findAllItems()) {
            String itemStr = String.valueOf(item.getPrecioCosto() +  " "  + item.getPrecioVenta() +  " "  + item.getPuntoReposicion() +  " "  + item.getStockActual());
            if (itemStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(item);
            }
        }
        return suggestions;
    }
    
    public String ItemservicioBean.onEdit() {
        return null;
    }
    
    public boolean ItemservicioBean.isCreateDialogVisible() {
        return createDialogVisible;
    }
    
    public void ItemservicioBean.setCreateDialogVisible(boolean createDialogVisible) {
        this.createDialogVisible = createDialogVisible;
    }
    
    public String ItemservicioBean.displayList() {
        createDialogVisible = false;
        findAllItemservicios();
        return "itemservicio";
    }
    
    public String ItemservicioBean.displayCreateDialog() {
        itemservicio = new Itemservicio();
        createDialogVisible = true;
        return "itemservicio";
    }
    
    public String ItemservicioBean.persist() {
        String message = "";
        if (itemservicio.getId() != null) {
            itemservicio.merge();
            message = "Successfully updated";
        } else {
            itemservicio.persist();
            message = "Successfully created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialog.hide()");
        context.execute("editDialog.hide()");
        
        FacesMessage facesMessage = new FacesMessage(message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllItemservicios();
    }
    
    public String ItemservicioBean.delete() {
        itemservicio.remove();
        FacesMessage facesMessage = new FacesMessage("Successfully deleted");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllItemservicios();
    }
    
    public void ItemservicioBean.reset() {
        itemservicio = null;
        createDialogVisible = false;
    }
    
    public void ItemservicioBean.handleDialogClose(CloseEvent event) {
        reset();
    }
    
}
