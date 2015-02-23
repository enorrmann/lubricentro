package ar.com.norrmann.coqui.jsf;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.validator.LengthValidator;

import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.behavior.ajax.AjaxBehavior;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.message.Message;
import org.primefaces.component.spinner.Spinner;
import org.primefaces.context.RequestContext;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;

import ar.com.norrmann.coqui.jsf.converter.CategoriaConverter;
import ar.com.norrmann.coqui.jsf.converter.ItemConverter;
import ar.com.norrmann.coqui.jsf.converter.TipomovimientoConverter;
import ar.com.norrmann.coqui.model.Categoria;
import ar.com.norrmann.coqui.model.Item;
import ar.com.norrmann.coqui.model.Movimientoitem;
import ar.com.norrmann.coqui.model.Tipoitem;
import ar.com.norrmann.coqui.model.Tipomovimiento;

@RooJsfManagedBean(entity = Movimientoitem.class, beanName = "movimientoitemBean")
public class MovimientoitemBean {

	private String name = "Movimientos";
	private Categoria categoriaId;
	private Movimientoitem movimientoitem;
	private boolean createDialogVisible;
    private List<String> columns;
    private String tituloMovimiento;
	
    @ManagedProperty("#{itemBean}")
	private ItemBean itemBean;

    @PostConstruct
    public void init() {
        columns = new ArrayList<String>();
        columns.add("cantidad");
        columns.add("observaciones");
    }

    
    public HtmlPanelGrid populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
//        
//        HtmlOutputText tipoMovimientoIdCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
//        tipoMovimientoIdCreateOutput.setId("tipoMovimientoIdCreateOutput");
//        tipoMovimientoIdCreateOutput.setValue("Tipo Movimiento :");
//        htmlPanelGrid.getChildren().add(tipoMovimientoIdCreateOutput);
//        
//        AutoComplete tipoMovimientoIdCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
//        tipoMovimientoIdCreateInput.setId("tipoMovimientoIdCreateInput");
//        tipoMovimientoIdCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{movimientoitemBean.movimientoitem.tipoMovimientoId}", Tipomovimiento.class));
//        tipoMovimientoIdCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{movimientoitemBean.completeTipoMovimientoId}", List.class, new Class[] { String.class }));
//        tipoMovimientoIdCreateInput.setDropdown(true);
//        tipoMovimientoIdCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "tipoMovimientoId", String.class));
//        tipoMovimientoIdCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{tipoMovimientoId.tipoMovimiento}", String.class));
//        tipoMovimientoIdCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{tipoMovimientoId}", Tipomovimiento.class));
//        tipoMovimientoIdCreateInput.setConverter(new TipomovimientoConverter());
//        tipoMovimientoIdCreateInput.setRequired(true);
//        htmlPanelGrid.getChildren().add(tipoMovimientoIdCreateInput);
//AjaxBehavior ajax = new AjaxBehavior();
//        ajax.setUpdate("categoriaIdCreateInput");
//        tipoMovimientoIdCreateInput.addClientBehavior("itemSelect", ajax);
//        
//        Message tipoMovimientoIdCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
//        tipoMovimientoIdCreateInputMessage.setId("tipoMovimientoIdCreateInputMessage");
//        tipoMovimientoIdCreateInputMessage.setFor("tipoMovimientoIdCreateInput");
//        tipoMovimientoIdCreateInputMessage.setDisplay("icon");
//        htmlPanelGrid.getChildren().add(tipoMovimientoIdCreateInputMessage);
//        
//        
//        HtmlOutputText categoriaIdCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
//        categoriaIdCreateOutput.setId("categoriaIdCreateOutput");
//        categoriaIdCreateOutput.setValue("Categoria:");
//        htmlPanelGrid.getChildren().add(categoriaIdCreateOutput);
//        
//        AutoComplete categoriaIdCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
//        categoriaIdCreateInput.setId("categoriaIdCreateInput");
//        categoriaIdCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{movimientoitemBean.categoriaId}", Categoria.class));
//        categoriaIdCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{movimientoitemBean.completeCategoriaId}", List.class, new Class[] { String.class }));
//        categoriaIdCreateInput.setDropdown(true);
//        categoriaIdCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "categoriaId", String.class));
//        categoriaIdCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{categoriaId.categoria}", String.class));
//        categoriaIdCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{categoriaId}", Categoria.class));
//        categoriaIdCreateInput.setConverter(new CategoriaConverter());
//        categoriaIdCreateInput.setRequired(true);
//        htmlPanelGrid.getChildren().add(categoriaIdCreateInput);
//        AjaxBehavior ajax2 = new AjaxBehavior();
//        ajax2.setUpdate("itemIdCreateInput");
//        categoriaIdCreateInput.addClientBehavior("itemSelect", ajax2);
//
//        
//        Message categoriaIdCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
//        categoriaIdCreateInputMessage.setId("categoriaIdCreateInputMessage");
//        categoriaIdCreateInputMessage.setFor("categoriaIdCreateInput");
//        categoriaIdCreateInputMessage.setDisplay("icon");
//        htmlPanelGrid.getChildren().add(categoriaIdCreateInputMessage);


        
        
//        HtmlOutputText itemIdCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
//        itemIdCreateOutput.setId("itemIdCreateOutput");
//        itemIdCreateOutput.setValue("Item :");
//        htmlPanelGrid.getChildren().add(itemIdCreateOutput);
//        
//        AutoComplete itemIdCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
//        itemIdCreateInput.setId("itemIdCreateInput");
//        itemIdCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{movimientoitemBean.movimientoitem.itemId}", Item.class));
//        itemIdCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{movimientoitemBean.completeItemId}", List.class, new Class[] { String.class }));
//        itemIdCreateInput.setDropdown(true);
//        itemIdCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "itemId", String.class));
//        itemIdCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{itemId.codigo} #{itemId.descripcion}", String.class));
//        itemIdCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{itemId}", Item.class));
//        itemIdCreateInput.setConverter(new ItemConverter());
//        itemIdCreateInput.setRequired(true);
//        htmlPanelGrid.getChildren().add(itemIdCreateInput);
//        
//        Message itemIdCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
//        itemIdCreateInputMessage.setId("itemIdCreateInputMessage");
//        itemIdCreateInputMessage.setFor("itemIdCreateInput");
//        itemIdCreateInputMessage.setDisplay("icon");
//        htmlPanelGrid.getChildren().add(itemIdCreateInputMessage);
        
        HtmlOutputText cantidadCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        cantidadCreateOutput.setId("cantidadCreateOutput");
        cantidadCreateOutput.setValue("Cantidad: * ");
        htmlPanelGrid.getChildren().add(cantidadCreateOutput);
        
        Spinner cantidadCreateInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        cantidadCreateInput.setId("cantidadCreateInput");
        cantidadCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{movimientoitemBean.movimientoitem.cantidad}", Integer.class));
        cantidadCreateInput.setRequired(true);
        
        htmlPanelGrid.getChildren().add(cantidadCreateInput);
        
        Message cantidadCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        cantidadCreateInputMessage.setId("cantidadCreateInputMessage");
        cantidadCreateInputMessage.setFor("cantidadCreateInput");
        cantidadCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(cantidadCreateInputMessage);
        
        HtmlOutputText fechaCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        fechaCreateOutput.setId("fechaCreateOutput");
        fechaCreateOutput.setValue("Fecha: *");
        htmlPanelGrid.getChildren().add(fechaCreateOutput);
        
        Calendar fechaCreateInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        fechaCreateInput.setId("fechaCreateInput");
        fechaCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{movimientoitemBean.movimientoitem.fecha}", Date.class));
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
        observacionesCreateOutput.setValue("Observaciones:");
        htmlPanelGrid.getChildren().add(observacionesCreateOutput);
        
        InputTextarea observacionesCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        observacionesCreateInput.setMaxHeight(100);
        observacionesCreateInput.setId("observacionesCreateInput");
        observacionesCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{movimientoitemBean.movimientoitem.observaciones}", String.class));
        LengthValidator observacionesCreateInputValidator = new LengthValidator();
        observacionesCreateInputValidator.setMaximum(255);
        observacionesCreateInput.addValidator(observacionesCreateInputValidator);
        observacionesCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(observacionesCreateInput);
        
        Message observacionesCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        observacionesCreateInputMessage.setId("observacionesCreateInputMessage");
        observacionesCreateInputMessage.setFor("observacionesCreateInput");
        observacionesCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(observacionesCreateInputMessage);
        
        return htmlPanelGrid;
    }
    
    public List<Item> completeItemId(String query) {
        List<Item> suggestions = new ArrayList<Item>();
      if (movimientoitem==null||movimientoitem.getTipoMovimientoId()==null||movimientoitem.getTipoMovimientoId().getTipoItemId()==null) return suggestions;
        List<Item> itemList =Item.findItemsByTipoItemId(movimientoitem.getTipoMovimientoId().getTipoItemId()).getResultList();
        for (Item item : itemList) {
            String itemStr = String.valueOf(item.getCodigo() +  " "  + item.getDescripcion() +  " "  + item.getPrecioCosto() +  " "  + item.getPrecioVenta());
            if (itemStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(item);
            }
        }
        return suggestions;
    }

    public List<Categoria> completeCategoriaId(String query) {
        List<Categoria> suggestions = new ArrayList<Categoria>();
        if (getMovimientoitem()==null||getMovimientoitem().getTipoMovimientoId()==null||getMovimientoitem().getTipoMovimientoId().getTipoItemId()==null){
        	return suggestions;
        }
    	Tipoitem tipoItem = getMovimientoitem().getTipoMovimientoId().getTipoItemId();
        for (Categoria categoria : Categoria.findCategoriasByTipoItemId(tipoItem).getResultList()) {
            String categoriaStr = String.valueOf(categoria.getCategoria());
            if (categoriaStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(categoria);
            }
        }
        return suggestions;
    }
	public Categoria getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(Categoria categoriaId) {
		this.categoriaId = categoriaId;
	}
	public TipomovimientoConverter gettipomovimientoConverter(){
		return new TipomovimientoConverter();
	}
    public String onEdit() {
    	if (getMovimientoitem()!=null&&movimientoitem.getItemId()!=null){
    		categoriaId = movimientoitem.getItemId().getCategoriaId();
    	}
        return null;
    }

    public HtmlPanelGrid populateEditPanel() {
    	return this.populateCreatePanel();
    }
    public HtmlPanelGrid populateViewPanel(){
    	return populateCreatePanel();
    }
    
    public String persist() {
        String message = "";
        if (movimientoitem.getId() != null) {
            movimientoitem.merge();
            message = "Actualizado correctamente";
        } else {
            movimientoitem.persist();
            message = "Creado correctamente";
        }
        movimientoitem.flush();
        Item item = movimientoitem.getItemId();
        item.calcularStockActual();
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialog.hide()");
        context.execute("editDialog.hide()");
        
        FacesMessage facesMessage = new FacesMessage(message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return displayList(item.getTipoItemId());
    }

    public String delete() {
    	Item item = movimientoitem.getItemId();
    	movimientoitem.remove();
        movimientoitem.flush();
        item.calcularStockActual();
        FacesMessage facesMessage = new FacesMessage("Borrado correctamente");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return displayList(item.getTipoItemId());
    }
    
    public String displayProductoList() {
        return displayList(Tipoitem.findTipoitem(Tipoitem.ID_PRODUCTO));
    }

    public String displayHerramientaList() {
        return displayList(Tipoitem.findTipoitem(Tipoitem.ID_HERRAMIENTA));
    }
    
    private String displayList(Tipoitem tipoItem){
        createDialogVisible = false;
        setAllMovimientoitems(Movimientoitem.findMovimientoitemsByTipoItem(tipoItem));
        setDataVisible(!getAllMovimientoitems().isEmpty());
        return "movimientoitem";
    }
    
    public String displayIngresoProductoDialog(){
        Tipomovimiento tipoMovimiento = Tipomovimiento.findTipomovimiento(Tipomovimiento.ID_MOVIMIENTO_INGRESO_STOCK);
        return nuevoMovimientoItem(tipoMovimiento);
    }

    public String displayEgresoProductoDialog(){
        Tipomovimiento tipoMovimiento = Tipomovimiento.findTipomovimiento(Tipomovimiento.ID_MOVIMIENTO_EGRESO_STOCK);
        return nuevoMovimientoItem(tipoMovimiento);
    }

    public String displayIngresoHerramientaDialog(){
        Tipomovimiento tipoMovimiento = Tipomovimiento.findTipomovimiento(Tipomovimiento.ID_MOVIMIENTO_INGRESO_INVENTARIO);
        return nuevoMovimientoItem(tipoMovimiento);
    }
    
    public String displayEgresoHerramientaDialog(){
        Tipomovimiento tipoMovimiento = Tipomovimiento.findTipomovimiento(Tipomovimiento.ID_MOVIMIENTO_EGRESO_INVENTARIO);
        return nuevoMovimientoItem(tipoMovimiento);
    }

    private String nuevoMovimientoItem(Tipomovimiento tipoMovimiento){
        movimientoitem = new Movimientoitem();
        movimientoitem.setTipoMovimientoId(tipoMovimiento);
        movimientoitem.setItemId(getItemBean().getItem());
        tituloMovimiento = tipoMovimiento.getTipoMovimiento();
        createDialogVisible = true;
        categoriaId=null;
        return "movimientoitem";
    }
    public String displayIngresoDialog(){
    	Item item = getItemBean().getItem();
    	if (item==null)return null;
    	if (item.getTipoItemId().getId().equals(Tipoitem.ID_HERRAMIENTA)){
    		return displayIngresoHerramientaDialog();
    	} else {
    		return displayIngresoProductoDialog();
    	}
    }   
    public String displayEgresoDialog(){
    	Item item = getItemBean().getItem();
    	if (item==null)return null;
    	if (item.getTipoItemId().getId().equals(Tipoitem.ID_HERRAMIENTA)){
    		return displayEgresoHerramientaDialog();
    	} else {
    		return displayEgresoProductoDialog();
    	}
    }

	public String getTituloMovimiento() {
		return tituloMovimiento;
	}


	public void setTituloMovimiento(String tituloMovimiento) {
		this.tituloMovimiento = tituloMovimiento;
	}


	public ItemBean getItemBean() {
		return itemBean;
	}


	public void setItemBean(ItemBean itemBean) {
		this.itemBean = itemBean;
	}
    
}
