// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.norrmann.coqui.jsf.converter;

import ar.com.norrmann.coqui.jsf.converter.VentaConverter;
import ar.com.norrmann.coqui.model.Venta;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

privileged aspect VentaConverter_Roo_Converter {
    
    declare parents: VentaConverter implements Converter;
    
    declare @type: VentaConverter: @FacesConverter("ar.com.norrmann.coqui.jsf.converter.VentaConverter");
    
    public Object VentaConverter.getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Long id = Long.parseLong(value);
        return Venta.findVenta(id);
    }
    
    public String VentaConverter.getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof Venta ? ((Venta) value).getId().toString() : "";
    }
    
}
