// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.norrmann.coqui.jsf.converter;

import ar.com.norrmann.coqui.jsf.converter.PagoConverter;
import ar.com.norrmann.coqui.model.Pago;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

privileged aspect PagoConverter_Roo_Converter {
    
    declare parents: PagoConverter implements Converter;
    
    declare @type: PagoConverter: @FacesConverter("ar.com.norrmann.coqui.jsf.converter.PagoConverter");
    
    public Object PagoConverter.getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Long id = Long.parseLong(value);
        return Pago.findPago(id);
    }
    
    public String PagoConverter.getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof Pago ? ((Pago) value).getId().toString() : "";
    }
    
}
