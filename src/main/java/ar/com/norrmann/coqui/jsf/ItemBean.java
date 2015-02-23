package ar.com.norrmann.coqui.jsf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.LengthValidator;

import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.message.Message;
import org.primefaces.component.spinner.Spinner;
import org.primefaces.context.RequestContext;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;

import ar.com.norrmann.coqui.jsf.converter.CategoriaConverter;
import ar.com.norrmann.coqui.jsf.converter.MarcaConverter;
import ar.com.norrmann.coqui.jsf.util.FiltroItem;
import ar.com.norrmann.coqui.jsf.util.MensajeUtil;
import ar.com.norrmann.coqui.model.Categoria;
import ar.com.norrmann.coqui.model.Item;
import ar.com.norrmann.coqui.model.Marca;
import ar.com.norrmann.coqui.model.Movimientoitem;
import ar.com.norrmann.coqui.model.Tipoitem;
import ar.com.norrmann.coqui.model.dto.ResumenItemDto;

@RooJsfManagedBean(entity = Item.class, beanName = "itemBean")

public class ItemBean {
	private List<String> columns;
	private Item item;
	private boolean createDialogVisible;
    private String name = "Items";
    private List<Item> allItems;
    private FiltroItem filtroItem;
    private TipoLista tipoLista;

	@PostConstruct
    public void init() {
        columns = new ArrayList<String>();
        columns.add("precioVenta");
        columns.add("puntoReposicion");
        filtroItem = new FiltroItem();
    }
    public HtmlPanelGrid populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
//        HtmlOutputText tipoItemIdCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
//        tipoItemIdCreateOutput.setId("tipoItemIdCreateOutput");
//        tipoItemIdCreateOutput.setValue("Producto o Herramienta ?");
//        htmlPanelGrid.getChildren().add(tipoItemIdCreateOutput);
//        
//        AutoComplete tipoItemIdCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
//        tipoItemIdCreateInput.setId("tipoItemIdCreateInput");
//        tipoItemIdCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemBean.item.tipoItemId}", Tipoitem.class));
//        tipoItemIdCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{itemBean.completeTipoItemId}", List.class, new Class[] { String.class }));
//        tipoItemIdCreateInput.setDropdown(true);
//        tipoItemIdCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "tipoItemId", String.class));
//        tipoItemIdCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{tipoItemId.tipoItem}", String.class));
//        tipoItemIdCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{tipoItemId}", Tipoitem.class));
//        tipoItemIdCreateInput.setConverter(new TipoitemConverter());
//        tipoItemIdCreateInput.setRequired(false);
//        AjaxBehavior pajax;
//        pajax = new AjaxBehavior();
//        pajax.setUpdate( "marcaIdCreateInput" );
//        tipoItemIdCreateInput.addClientBehavior( "itemSelect", pajax );
//
//        htmlPanelGrid.getChildren().add(tipoItemIdCreateInput);
//        
//        Message tipoItemIdCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
//        tipoItemIdCreateInputMessage.setId("tipoItemIdCreateInputMessage");
//        tipoItemIdCreateInputMessage.setFor("tipoItemIdCreateInput");
//        tipoItemIdCreateInputMessage.setDisplay("icon");
//        htmlPanelGrid.getChildren().add(tipoItemIdCreateInputMessage);

        HtmlOutputText marcaIdCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        marcaIdCreateOutput.setId("marcaIdCreateOutput");
        marcaIdCreateOutput.setValue("Marca:");
        htmlPanelGrid.getChildren().add(marcaIdCreateOutput);
        
        AutoComplete marcaIdCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        marcaIdCreateInput.setId("marcaIdCreateInput");
        marcaIdCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemBean.item.marcaId}", Marca.class));
        marcaIdCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{itemBean.completeMarcaId}", List.class, new Class[] { String.class }));
        marcaIdCreateInput.setDropdown(true);
        marcaIdCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "marcaId", String.class));
        marcaIdCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{marcaId.marca}", String.class));
        marcaIdCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{marcaId}", Marca.class));
        marcaIdCreateInput.setConverter(new MarcaConverter());
        marcaIdCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(marcaIdCreateInput);
        
        Message marcaIdCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        marcaIdCreateInputMessage.setId("marcaIdCreateInputMessage");
        marcaIdCreateInputMessage.setFor("marcaIdCreateInput");
        marcaIdCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(marcaIdCreateInputMessage);
        
        
        HtmlOutputText categoriaIdCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        categoriaIdCreateOutput.setId("categoriaIdCreateOutput");
        categoriaIdCreateOutput.setValue("Categoria:");
        htmlPanelGrid.getChildren().add(categoriaIdCreateOutput);
        
        AutoComplete categoriaIdCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        categoriaIdCreateInput.setId("categoriaIdCreateInput");
        categoriaIdCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemBean.item.categoriaId}", Categoria.class));
        categoriaIdCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{itemBean.completeCategoriaId}", List.class, new Class[] { String.class }));
        categoriaIdCreateInput.setDropdown(true);
        categoriaIdCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "categoriaId", String.class));
        categoriaIdCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{categoriaId.categoria}", String.class));
        categoriaIdCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{categoriaId}", Categoria.class));
        categoriaIdCreateInput.setConverter(new CategoriaConverter());
        categoriaIdCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(categoriaIdCreateInput);
        
        Message categoriaIdCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        categoriaIdCreateInputMessage.setId("categoriaIdCreateInputMessage");
        categoriaIdCreateInputMessage.setFor("categoriaIdCreateInput");
        categoriaIdCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(categoriaIdCreateInputMessage);
        
        HtmlOutputText codigoCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        codigoCreateOutput.setId("codigoCreateOutput");
        codigoCreateOutput.setValue("Codigo:");
        htmlPanelGrid.getChildren().add(codigoCreateOutput);
        
        InputText codigoCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        codigoCreateInput.setId("codigoCreateInput");
        codigoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemBean.item.codigo}", String.class));
        codigoCreateInput.setValueExpression("onkeypress", expressionFactory.createValueExpression(elContext, "return customfunction(event);", String.class));
        LengthValidator codigoCreateInputValidator = new LengthValidator();
        codigoCreateInputValidator.setMaximum(255);
        codigoCreateInput.addValidator(codigoCreateInputValidator);
        codigoCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(codigoCreateInput);
        
        Message codigoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        codigoCreateInputMessage.setId("codigoCreateInputMessage");
        codigoCreateInputMessage.setFor("codigoCreateInput");
        codigoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(codigoCreateInputMessage);
        
        HtmlOutputText codigoReferenciaCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        codigoReferenciaCreateOutput.setId("codigoReferenciaCreateOutput");
        codigoReferenciaCreateOutput.setValue("Codigo Referencia:   ");
        htmlPanelGrid.getChildren().add(codigoReferenciaCreateOutput);
        
        InputText codigoReferenciaCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        codigoReferenciaCreateInput.setId("codigoReferenciaCreateInput");
        codigoReferenciaCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemBean.item.codigoReferencia}", String.class));
        htmlPanelGrid.getChildren().add(codigoReferenciaCreateInput);
        
        Message codigoReferenciaCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        codigoReferenciaCreateInputMessage.setId("codigoReferenciaCreateInputMessage");
        codigoReferenciaCreateInputMessage.setFor("codigoReferenciaCreateInput");
        codigoReferenciaCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(codigoReferenciaCreateInputMessage);

        
        HtmlOutputText descripcionCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        descripcionCreateOutput.setId("descripcionCreateOutput");
        descripcionCreateOutput.setValue("Descripcion: * ");
        htmlPanelGrid.getChildren().add(descripcionCreateOutput);
        
        InputTextarea descripcionCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        descripcionCreateInput.setMaxHeight(100);
        descripcionCreateInput.setId("descripcionCreateInput");
        descripcionCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemBean.item.descripcion}", String.class));
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
        
        HtmlOutputText precioCostoCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        precioCostoCreateOutput.setId("precioCostoCreateOutput");
        precioCostoCreateOutput.setValue("Precio Costo: * ");
        htmlPanelGrid.getChildren().add(precioCostoCreateOutput);
        
        InputText precioCostoCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        precioCostoCreateInput.setId("precioCostoCreateInput");
        precioCostoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemBean.item.precioCosto}", BigDecimal.class));
        precioCostoCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(precioCostoCreateInput);
        
        Message precioCostoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        precioCostoCreateInputMessage.setId("precioCostoCreateInputMessage");
        precioCostoCreateInputMessage.setFor("precioCostoCreateInput");
        precioCostoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(precioCostoCreateInputMessage);
        
        HtmlOutputText precioVentaCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        precioVentaCreateOutput.setId("precioVentaCreateOutput");
        precioVentaCreateOutput.setValue("Precio Venta: * ");
        htmlPanelGrid.getChildren().add(precioVentaCreateOutput);
        
        InputText precioVentaCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        precioVentaCreateInput.setId("precioVentaCreateInput");
        precioVentaCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemBean.item.precioVenta}", BigDecimal.class));
        precioVentaCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(precioVentaCreateInput);
        
        Message precioVentaCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        precioVentaCreateInputMessage.setId("precioVentaCreateInputMessage");
        precioVentaCreateInputMessage.setFor("precioVentaCreateInput");
        precioVentaCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(precioVentaCreateInputMessage);
        
        HtmlOutputText puntoReposicionCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        puntoReposicionCreateOutput.setId("puntoReposicionCreateOutput");
        puntoReposicionCreateOutput.setValue("Punto Reposicion: * ");
        htmlPanelGrid.getChildren().add(puntoReposicionCreateOutput);
        
        Spinner puntoReposicionCreateInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        puntoReposicionCreateInput.setId("puntoReposicionCreateInput");
        puntoReposicionCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemBean.item.puntoReposicion}", Integer.class));
        puntoReposicionCreateInput.setRequired(true);
        
        htmlPanelGrid.getChildren().add(puntoReposicionCreateInput);
        
        Message puntoReposicionCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        puntoReposicionCreateInputMessage.setId("puntoReposicionCreateInputMessage");
        puntoReposicionCreateInputMessage.setFor("puntoReposicionCreateInput");
        puntoReposicionCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(puntoReposicionCreateInputMessage);
        
        return htmlPanelGrid;
    }
    
    public List<Marca> completeMarcaId(String query) {
        List<Marca> suggestions = new ArrayList<Marca>();
        if (getItem()==null||getItem().getTipoItemId()==null)return suggestions;
        Tipoitem tipoItem = getItem().getTipoItemId();
        for (Marca marca : Marca.findMarcasByTipoItemId(tipoItem).getResultList()) {
            String marcaStr = String.valueOf(marca.getMarca());
            if (marcaStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(marca);
            }
        }
        return suggestions;
    }
    public List<Categoria> completeCategoriaId(String query) {
        List<Categoria> suggestions = new ArrayList<Categoria>();
        if (getItem()==null||getItem().getTipoItemId()==null)return suggestions;
        Tipoitem tipoItem = getItem().getTipoItemId();
        for (Categoria categoria : Categoria.findCategoriasByTipoItemId(tipoItem).getResultList()) {
            String categoriaStr = String.valueOf(categoria.getCategoria());
            if (categoriaStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(categoria);
            }
        }
        return suggestions;
    }

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
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
        
        HtmlOutputText tipoItemIdLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        tipoItemIdLabel.setId("tipoItemIdLabel");
        tipoItemIdLabel.setValue("Tipo Item :");
        htmlPanelGrid.getChildren().add(tipoItemIdLabel);
        
        HtmlOutputText tipoItemIdValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        tipoItemIdValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemBean.item.tipoItemId.tipoItem}", String.class));
        //tipoItemIdValue.setConverter(new TipoitemConverter());
        htmlPanelGrid.getChildren().add(tipoItemIdValue);
        
        HtmlOutputText marcaIdLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        marcaIdLabel.setId("marcaIdLabel");
        marcaIdLabel.setValue("Marca :");
        htmlPanelGrid.getChildren().add(marcaIdLabel);
        
        HtmlOutputText marcaIdValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        marcaIdValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemBean.item.marcaId.marca}", String.class));
//        marcaIdValue.setConverter(new MarcaConverter());
        htmlPanelGrid.getChildren().add(marcaIdValue);
        
        HtmlOutputText categoriaIdLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        categoriaIdLabel.setId("categoriaIdLabel");
        categoriaIdLabel.setValue("Categoria :");
        htmlPanelGrid.getChildren().add(categoriaIdLabel);
        
        HtmlOutputText categoriaIdValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        categoriaIdValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemBean.item.categoriaId.categoria}", String.class));
        //categoriaIdValue.setConverter(new CategoriaConverter());
        htmlPanelGrid.getChildren().add(categoriaIdValue);
        
        HtmlOutputText codigoLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        codigoLabel.setId("codigoLabel");
        codigoLabel.setValue("Codigo:   ");
        htmlPanelGrid.getChildren().add(codigoLabel);
        
        InputTextarea codigoValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        codigoValue.setMaxHeight(100);
        codigoValue.setId("codigoValue");
        codigoValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemBean.item.codigo}", String.class));
        codigoValue.setReadonly(true);
        codigoValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(codigoValue);
        
        HtmlOutputText descripcionLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        descripcionLabel.setId("descripcionLabel");
        descripcionLabel.setValue("Descripcion:   ");
        htmlPanelGrid.getChildren().add(descripcionLabel);
        
        InputTextarea descripcionValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        descripcionValue.setMaxHeight(100);
        descripcionValue.setId("descripcionValue");
        descripcionValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemBean.item.descripcion}", String.class));
        descripcionValue.setReadonly(true);
        descripcionValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(descripcionValue);
        
        HtmlOutputText precioCostoLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        precioCostoLabel.setId("precioCostoLabel");
        precioCostoLabel.setValue("Precio Costo:   ");
        htmlPanelGrid.getChildren().add(precioCostoLabel);
        
        HtmlOutputText precioCostoValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        precioCostoValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemBean.item.precioCosto}", String.class));
        htmlPanelGrid.getChildren().add(precioCostoValue);
        
        HtmlOutputText precioVentaLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        precioVentaLabel.setId("precioVentaLabel");
        precioVentaLabel.setValue("Precio Venta:   ");
        htmlPanelGrid.getChildren().add(precioVentaLabel);
        
        HtmlOutputText precioVentaValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        precioVentaValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemBean.item.precioVenta}", String.class));
        htmlPanelGrid.getChildren().add(precioVentaValue);
        
        HtmlOutputText puntoReposicionLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        puntoReposicionLabel.setId("puntoReposicionLabel");
        puntoReposicionLabel.setValue("Punto Reposicion:   ");
        htmlPanelGrid.getChildren().add(puntoReposicionLabel);
        
        HtmlOutputText puntoReposicionValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        puntoReposicionValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemBean.item.puntoReposicion}", String.class));
        htmlPanelGrid.getChildren().add(puntoReposicionValue);
        
        return htmlPanelGrid;
    }
    public Item getItem() {
        if (item == null) {
            item = new Item();
        }
        return item;
    }
    public String onEdit() {
        return null;
    }
    private List<Movimientoitem> movimientoItemList= new ArrayList<Movimientoitem>();
	private List<ResumenItemDto> resumenItemList;
	private int stockTotal;
	private BigDecimal totalVenta;
	private BigDecimal totalCosto;
    
    public List<Movimientoitem> getMovimientoItemList(){
    	//List<Movimientoitem> movimientoItemList = new ArrayList<Movimientoitem>();
    	if (getItem()!=null){
    		return Movimientoitem.findMovimientoitemsByItemId(getItem()).getResultList();
    	} else {
    		return null;
    	}
    	
    }
	public void setMovimientoItemList(List<Movimientoitem> movimientoItemList) {
		this.movimientoItemList = movimientoItemList;
	}

	public List<Tipoitem> completeTipoItemId(String query) {
        List<Tipoitem> suggestions = new ArrayList<Tipoitem>();
        for (Tipoitem tipoitem : Tipoitem.findAllTipoitemsExcept(3L)) {
            String tipoitemStr = String.valueOf(tipoitem.getTipoItem());
            if (tipoitemStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(tipoitem);
            }
        }
        return suggestions;
    }
    
	public String displayHerramientaList() {
		tipoLista = TipoLista.HERRAMIENTAS;
		setName("Herramienta");
        setCreateDialogVisible(false);
        findAllHerramientas();
        return "item";
    }
	public String displayProductoList() {
		tipoLista = TipoLista.PRODUCTOS;
		setName("Producto");
        setCreateDialogVisible(false);
        findAllProductos();
        return "item";
    }
	public String displayProductoReposicionList() {
		tipoLista = TipoLista.PRODUCTOS_A_REPONER;
		setName("Productos a Reponer");
        setCreateDialogVisible(false);
        findProductosAReponer();
        return "item";
    }
    
    private void findProductosAReponer() {
        setAllItems(Item.findProductosAReponer());
        setDataVisible(!getAllItems().isEmpty());
		
	}
	public String findAllHerramientas() {
        setAllItems(Item.findItemsByTipoItemId(Tipoitem.findTipoitem(1L)).getResultList());
        setDataVisible(!getAllItems().isEmpty());
        return null;
    }
    public String findAllProductos() {
        setAllItems(Item.findItemsByTipoItemId(Tipoitem.findTipoitem(2L)).getResultList());
        setDataVisible(!getAllItems().isEmpty());
        return null;
    }
    public String goResumenItem(){
    	this.resumenItemList=Item.getResumenItemList();
    	calcularTotales();
    	return "resumenItem";
    }
	private void calcularTotales() {
		this.stockTotal=0;
		this.totalVenta=new BigDecimal(0);
		this.totalCosto=new BigDecimal(0);
		if (this.resumenItemList==null||this.resumenItemList.isEmpty()) return;
		for (ResumenItemDto unResumenItemDto:resumenItemList){
			stockTotal+=unResumenItemDto.getStockActual();
			totalVenta=totalVenta.add(unResumenItemDto.getSumaVenta());
			totalCosto=totalCosto.add(unResumenItemDto.getSumaCosto());
		}
		
	}
	public boolean isCreateDialogVisible() {
		return createDialogVisible;
	}
	public void setCreateDialogVisible(boolean createDialogVisible) {
		this.createDialogVisible = createDialogVisible;
	}
	public List<Item> getAllItems() {
		return allItems;
	}
	public void setAllItems(List<Item> allItems) {
		this.allItems = allItems;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
	public String persist() {
        String message = "";
        if (item.getId() != null) {
            item.merge();
            message = MensajeUtil.ACTUALIZADO_CORRECTAMENTE;
        } else {
            item.persist();
            message = MensajeUtil.GUARDADO_CORRECTAMENTE;
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialog.hide()");
        context.execute("editDialog.hide()");
        
        FacesMessage facesMessage = new FacesMessage(message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        Tipoitem ti = item.getTipoItemId();
        setAllItems(Item.findItemsByTipoItemId(ti).getResultList());
        setDataVisible(!getAllItems().isEmpty());
        setName(ti.getTipoItem());
        reset();
        return "item";
    }

    public String displayCreateProductoDialog() {
        item = new Item();
        item.setTipoItemId(Tipoitem.findTipoitem(Tipoitem.ID_PRODUCTO));
        createDialogVisible = true;
        setNameFromItem();
        findAllProductos();
        return "item";
    }
    public String displayCreateHerramientaDialog() {
        item = new Item();
        item.setTipoItemId(Tipoitem.findTipoitem(Tipoitem.ID_HERRAMIENTA));
        setNameFromItem();
        createDialogVisible = true;
        findAllHerramientas();
        return "item";
    }
    private void setNameFromItem() {
    	if (item==null)return ;
    	setName(item.getTipoItemId().getTipoItem());
		
	}
	public String delete() {
    	  Tipoitem ti = item.getTipoItemId();
    	  Movimientoitem.eliminarMovimientosItem(item);
        item.remove();
        FacesMessage facesMessage = new FacesMessage(MensajeUtil.BORRADO_CORRECTAMENTE);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        setAllItems(Item.findItemsByTipoItemId(ti).getResultList());
        setDataVisible(!getAllItems().isEmpty());
        setName(ti.getTipoItem());
        reset();
        return  "item";
    }
	public List<ResumenItemDto> getResumenItemList() {
		return resumenItemList;
	}
	public void setResumenItemList(List<ResumenItemDto> resumenItemList) {
		this.resumenItemList = resumenItemList;
	}
	public int getStockTotal() {
		return stockTotal;
	}
	public void setStockTotal(int stockTotal) {
		this.stockTotal = stockTotal;
	}
	public BigDecimal getTotalVenta() {
		return totalVenta;
	}
	public void setTotalVenta(BigDecimal totalVenta) {
		this.totalVenta = totalVenta;
	}
	public BigDecimal getTotalCosto() {
		return totalCosto;
	}
	public void setTotalCosto(BigDecimal totalCosto) {
		this.totalCosto = totalCosto;
	}
	public FiltroItem getFiltroItem() {
		return filtroItem;
	}
	public void setFiltroItem(FiltroItem filtroItem) {
		this.filtroItem = filtroItem;
	}
	public MarcaConverter getMarcaConverter(){
		return new MarcaConverter();
	}
	public CategoriaConverter getCategoriaConverter(){
		return new CategoriaConverter();
	}
	public List<Marca> getMarcaProductoAll(){
		return Marca.findMarcasByTipoItemId(Tipoitem.findTipoitem(Tipoitem.ID_PRODUCTO)).getResultList();		
	}
	public List<Categoria> getCategoriaProductoAll(){
		return Categoria.findCategoriasByTipoItemId(Tipoitem.findTipoitem(Tipoitem.ID_PRODUCTO)).getResultList();
	}
	
	public String filtrarLista(){
		if (filtroItem.esVacio()){
			setAllItems(Item.findItemsByTipoItemId(Tipoitem.findTipoitem(2L)).getResultList());
		} else  if (filtroItem.esCompleto()){
	        setAllItems(Item.findItemsByCategoriaIdAndMarcaId(filtroItem.getCategoria(), filtroItem.getMarca()).getResultList());
		} else if (filtroItem.getCategoria()!=null){
			setAllItems(Item.findItemsByCategoriaId(filtroItem.getCategoria()).getResultList());
		} else {
			setAllItems(Item.findItemsByMarcaId(filtroItem.getMarca()).getResultList());
		}
		
        setDataVisible(!getAllItems().isEmpty());
		return "item";
	}
	public String aumentarPorcentaje(){
		if (filtroItem.tieneAumento()){
			for (Item unItem:getAllItems()){
				unItem.aumentarPorcentaje(filtroItem.getPorcentajeAAumentar());
			}
		} else if (filtroItem.tieneRebaja()){
			for (Item unItem:getAllItems()){
				unItem.reducirPorcentaje(filtroItem.getPorcentajeAAumentar());
			}
		}
		filtroItem.setPorcentajeAAumentar(new BigDecimal(0));
		return "item";
	}
	public boolean isMostrarEspere(){
		return false;
		
	}
	public TipoLista getTipoLista() {
		return tipoLista;
	}
	public void setTipoLista(TipoLista tipoLista) {
		this.tipoLista = tipoLista;
	}
	  public void destroyWorld(ActionEvent actionEvent){  
		  aumentarPorcentaje();
	    } 
public int getCantidadItems(){
	if (getAllItems()==null)return 0;
return getAllItems().size();	
}
}

 enum TipoLista {
    PRODUCTOS,PRODUCTOS_A_REPONER,HERRAMIENTAS
}
