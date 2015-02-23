package ar.com.norrmann.coqui.jsf;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;

import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.message.Message;
import org.primefaces.context.RequestContext;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;

import ar.com.norrmann.coqui.jsf.converter.ItemConverter;
import ar.com.norrmann.coqui.jsf.converter.MovimientoitemConverter;
import ar.com.norrmann.coqui.jsf.converter.ServicioConverter;
import ar.com.norrmann.coqui.model.Item;
import ar.com.norrmann.coqui.model.Itemservicio;
import ar.com.norrmann.coqui.model.Movimientoitem;
import ar.com.norrmann.coqui.model.Servicio;
import ar.com.norrmann.coqui.model.Tipoitem;
import ar.com.norrmann.coqui.model.Tipomovimiento;

@RooJsfManagedBean(entity = Itemservicio.class, beanName = "itemServicioBean")
public class ItemServicioBean {
	private List<Itemservicio> itemServicioList;
	private Servicio servicio;
	private Itemservicio itemservicio;
	private boolean createDialogVisible;


	public List<Itemservicio> getItemServicioList() {
		if (servicio!=null){
			itemServicioList = Itemservicio.findItemserviciosByServicioId(servicio).getResultList();
		} else {
			return null;
		}
		return itemServicioList;
	}
	public void setItemServicioList(List<Itemservicio> itemServicioList) {
		this.itemServicioList = itemServicioList;
	}
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
    public String displayCreateDialog() {
        itemservicio = new Itemservicio();
        itemservicio.setServicioId(servicio);
        createDialogVisible = true;
        return "detalle_automotor";
    }
	public Itemservicio getItemservicio() {
		return itemservicio;
	}
	public void setItemservicio(Itemservicio itemservicio) {
		this.itemservicio = itemservicio;
	}
	public boolean isCreateDialogVisible() {
		return createDialogVisible;
	}
	public void setCreateDialogVisible(boolean createDialogVisible) {
		this.createDialogVisible = createDialogVisible;
	}


    public String persist() {
        String message = "";
        if (itemservicio.getId() != null) {
            itemservicio.merge();
            message = "Correctamente actualizado";
        } else {
        	Movimientoitem mi = new Movimientoitem();
        	// egreso de stock
        	mi.setTipoMovimientoId(Tipomovimiento.findTipomovimiento(2L));
        	mi.setCantidad(1);
        	mi.setFecha(Calendar.getInstance().getTime());
        	mi.setItemId(itemservicio.getItemId());
        	mi.setObservaciones("service");
        	mi.persist();
        	itemservicio.setEgresoStockId(mi);
            itemservicio.persist();
            itemservicio.getItemId().calcularStockActual();
            message = "Correctamente guardado";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createItemServicioDialog.hide()");
        
        FacesMessage facesMessage = new FacesMessage(message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        //return findAllItemservicios();
        return null;
    }
    public HtmlPanelGrid populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText itemIdCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        itemIdCreateOutput.setId("itemIdCreateOutput");
        itemIdCreateOutput.setValue("Item :");
        htmlPanelGrid.getChildren().add(itemIdCreateOutput);
        
        AutoComplete itemIdCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        itemIdCreateInput.setId("itemIdCreateInput");
        itemIdCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{itemServicioBean.itemservicio.itemId}", Item.class));
        itemIdCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{itemServicioBean.completeItemId}", List.class, new Class[] { String.class }));
        itemIdCreateInput.setDropdown(true);
        itemIdCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "itemId", String.class));
        itemIdCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{itemId.codigo} #{itemId.descripcion}", String.class));
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
    public List<Item> completeItemId(String query) {
        List<Item> suggestions = new ArrayList<Item>();
        // solo bienes de cambio
        for (Item item : Item.findItemsByTipoItemId(Tipoitem.findTipoitem(2L)).getResultList()) {
            String itemStr = String.valueOf(item.getCodigo() +  " "  + item.getDescripcion());
            if (itemStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(item);
            }
        }
        return suggestions;
    }

}
