package ar.com.norrmann.coqui.jsf;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.springframework.roo.addon.jsf.application.RooJsfApplicationBean;

import ar.com.norrmann.coqui.model.Tipomovimiento;

@RooJsfApplicationBean
public class ApplicationBean {

    private DefaultMenuModel menuModel;
	public String getColumnName(String column) {
        if (column == null || column.length() == 0) {
            return column;
        }
        final Pattern p = Pattern.compile("[A-Z][^A-Z]*");
        final Matcher m = p.matcher(Character.toUpperCase(column.charAt(0)) + column.substring(1));
        final StringBuilder builder = new StringBuilder();
        while (m.find()) {
            builder.append(m.group()).append(" ");
        }
        return builder.toString().trim();
    }
    
    public List<Tipomovimiento> getTipomovimientoAll(){
    	return Tipomovimiento.findAllTipomovimientoes();
    }
    
    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();

        menuModel = new DefaultMenuModel();
        Submenu submenu;
        Submenu submenuAdministrar;
        MenuItem item;
        
        submenu = new Submenu();
        submenu.setId("clienteSubmenu");
        submenu.setLabel("Clientes");
        item = new MenuItem();
        item.setId("createClienteMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_create} Cliente", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{clienteBean.displayCreateDialog}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-document");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate("data");
        submenu.getChildren().add(item);
        // listar todos clientes
        item = new MenuItem();
        item.setId("listClienteMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_list} Clientes", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{clienteBean.displayList}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-folder-open");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate("data");
        submenu.getChildren().add(item);
        // listar clientes MOROSOS
        item = new MenuItem();
        item.setId("listClienteMorosoMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_list} morosos", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{clienteBean.displayMorosoList}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-folder-open");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate("data");
        submenu.getChildren().add(item);

        menuModel.addSubmenu(submenu);

        item = new MenuItem();
        item.setId("createAutomotorMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_create} Automotor", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{automotorBean.displayCreateDialog}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-document");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate("data");
        submenu.getChildren().add(item);
        item = new MenuItem();
        item.setId("listAutomotorMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_list} Automotores", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{automotorBean.displayList}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-folder-open");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate("data");
        submenu.getChildren().add(item);
        menuModel.addSubmenu(submenu);

        submenu = new Submenu();
        submenu.setId("proveedorSubmenu");
        submenu.setLabel("Proveedores");
        item = new MenuItem();
        item.setId("createProveedorMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_create} Proveedor", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{proveedorBean.displayCreateDialog}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-document");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate(":dataForm:data");
        submenu.getChildren().add(item);
        item = new MenuItem();
        item.setId("listProveedorMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_list} Proveedores", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{proveedorBean.displayList}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-folder-open");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate(":dataForm:data");
        submenu.getChildren().add(item);
        menuModel.addSubmenu(submenu);
        
        submenu = new Submenu();
        submenu.setId("compraSubmenu");
        submenu.setLabel("Compras");
        item = new MenuItem();
        item.setId("createCompraMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "Registrar Compra", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{compraBean.goNuevaCompra}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-document");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate(":dataForm:data");
        submenu.getChildren().add(item);
        item = new MenuItem();
        item.setId("listCompraMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_list} Compras", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{compraBean.displayList}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-folder-open");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate(":dataForm:data");
        submenu.getChildren().add(item);
        menuModel.addSubmenu(submenu);

        submenu = new Submenu();
        submenu.setId("ventaSubmenu");
        submenu.setLabel("Ventas");
        item = new MenuItem();
        item.setId("createVentaMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "Registrar Venta", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{ventaBean.goNuevaVenta}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-document");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate(":dataForm:data");
        submenu.getChildren().add(item);
        item = new MenuItem();
        item.setId("listVentaMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "Listar Ventas", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{ventaBean.displayList}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-folder-open");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate(":dataForm:data");
        submenu.getChildren().add(item);
        menuModel.addSubmenu(submenu);

        submenuAdministrar= new Submenu();
        submenuAdministrar.setId("administrarSubMenu");
        submenuAdministrar.setLabel("Administrar");

        
        item = new MenuItem();
        item.setId("createCategoriaMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "Nueva categoria", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{categoriaBean.displayCreateDialog}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-document");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate("data");
        submenuAdministrar.getChildren().add(item);
        item = new MenuItem();
        item.setId("listCategoriaMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_list} categorias", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{categoriaBean.displayList}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-folder-open");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate("data");
        submenuAdministrar.getChildren().add(item);
        
        submenu = new Submenu();
        submenu.setId("itemSubmenu");
        submenu.setLabel("Productos");
        item = new MenuItem();
        item.setId("createItemMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_create} Producto", String.class));
        //item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{itemBean.displayCreateDialog}", String.class, new Class[0]));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{itemBean.displayCreateProductoDialog}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-document");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate("data");
        submenu.getChildren().add(item);

        // listar productos
        item = new MenuItem();
        item.setId("listProductosMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_list} Productos", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{itemBean.displayProductoList}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-folder-open");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate("data");
        submenu.getChildren().add(item);
        menuModel.addSubmenu(submenu);
        // listar productos a reponer
        item = new MenuItem();
        item.setId("listProductosReposicionMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_list} Productos a Reponer", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{itemBean.displayProductoReposicionList}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-folder-open");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate("data");
        submenu.getChildren().add(item);
        menuModel.addSubmenu(submenu);
        
        
        // registrar ingreso de productos
//        item = new MenuItem();
//        item.setId("createIngresoProductoMenuItem");
//        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "Registrar ingreso", String.class));
//        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{movimientoitemBean.displayIngresoProductoDialog}", String.class, new Class[0]));
//        item.setIcon("ui-icon ui-icon-arrowthick-1-n");
//        item.setAjax(false);
//        item.setAsync(false);
//        item.setUpdate("data");
//        submenu.getChildren().add(item);
//
//        // registrar egreso de productos
//        item = new MenuItem();
//        item.setId("createEgresoProductoMenuItem");
//        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "Registrar egreso", String.class));
//        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{movimientoitemBean.displayEgresoProductoDialog}", String.class, new Class[0]));
//        item.setIcon("ui-icon ui-icon-arrowthick-1-s");
//        item.setAjax(false);
//        item.setAsync(false);
//        item.setUpdate("data");
//        submenu.getChildren().add(item);

        // listar movimientos de productos
        item = new MenuItem();
        item.setId("listMovimientoProductoMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_list} Movimientos", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{movimientoitemBean.displayProductoList}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-folder-open");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate("data");
        submenu.getChildren().add(item);
        // listar RESUMEN
        item = new MenuItem();
        item.setId("listResumenMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_list} Resumen", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{itemBean.goResumenItem}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-folder-open");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate("data");
        submenu.getChildren().add(item);
        menuModel.addSubmenu(submenu);

        submenu = new Submenu();
        submenu.setId("herramientasSubmenu");
        submenu.setLabel("Herramientas");
        item = new MenuItem();
        item.setId("createHerramientaMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "Nueva Herramienta", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{itemBean.displayCreateHerramientaDialog}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-document");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate("data");
        submenu.getChildren().add(item);

        // listar herramientas
        item = new MenuItem();
        item.setId("listHerramientasMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_list} Herramientas", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{itemBean.displayHerramientaList}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-folder-open");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate("data");
        submenu.getChildren().add(item);
        menuModel.addSubmenu(submenu);
        
//        // registrar ingreso de herramientas
//        item = new MenuItem();
//        item.setId("createIngresoHerramientaMenuItem");
//        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "Registrar ingreso", String.class));
//        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{movimientoitemBean.displayIngresoHerramientaDialog}", String.class, new Class[0]));
//        item.setIcon("ui-icon ui-icon-arrowthick-1-n");
//        item.setAjax(false);
//        item.setAsync(false);
//        item.setUpdate("data");
//        submenu.getChildren().add(item);
//
//        // registrar egreso de herramientas
//        item = new MenuItem();
//        item.setId("createEgresoHerramientaMenuItem");
//        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "Registrar egreso", String.class));
//        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{movimientoitemBean.displayEgresoHerramientaDialog}", String.class, new Class[0]));
//        item.setIcon("ui-icon ui-icon-arrowthick-1-s");
//        item.setAjax(false);
//        item.setAsync(false);
//        item.setUpdate("data");
//        submenu.getChildren().add(item);

        // listar movimientos de herramientas
        item = new MenuItem();
        item.setId("listMovimientoHerramientasMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_list} Movimientos", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{movimientoitemBean.displayHerramientaList}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-folder-open");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate("data");
        submenu.getChildren().add(item);
        
        
        item = new MenuItem();
        item.setId("createMarcaMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "Nueva marca", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{marcaBean.displayCreateDialog}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-document");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate("data");
        submenuAdministrar.getChildren().add(item);
        item = new MenuItem();
        item.setId("listMarcaMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_list} marcas", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{marcaBean.displayList}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-folder-open");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate("data");
        submenuAdministrar.getChildren().add(item);

        item = new MenuItem();
        item.setId("createModeloMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_create} modelo", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{modeloBean.displayCreateDialog}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-document");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate("data");
        submenuAdministrar.getChildren().add(item);
        item = new MenuItem();
        item.setId("listModeloMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_list} modelos", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{modeloBean.displayList}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-folder-open");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate("data");
        submenuAdministrar.getChildren().add(item);
        

//        // SUBMENU QUE NO SE USA MAS, MOVIMIENTOS EN GRAL
//        submenu = new Submenu();
//        submenu.setId("movimientoitemSubmenu");
//        submenu.setLabel("Movimientos");
//        item = new MenuItem();
//        item.setId("createMovimientoitemMenuItem");
//        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_create}", String.class));
//        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{movimientoitemBean.displayCreateDialog}", String.class, new Class[0]));
//        item.setIcon("ui-icon ui-icon-document");
//        item.setAjax(false);
//        item.setAsync(false);
//        item.setUpdate("data");
//        submenu.getChildren().add(item);
//        item = new MenuItem();
//        item.setId("listMovimientoitemMenuItem");
//        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_list}", String.class));
//        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{movimientoitemBean.displayList}", String.class, new Class[0]));
//        item.setIcon("ui-icon ui-icon-folder-open");
//        item.setAjax(false);
//        item.setAsync(false);
//        item.setUpdate("data");
//        submenu.getChildren().add(item);
//        menuModel.addSubmenu(submenu);
//        
        
        menuModel.addSubmenu(submenuAdministrar);

    }
 
   public String getAppName() {
        return "Lubricentro Coqui";
    }
}