// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.norrmann.coqui.jsf.converter;

import ar.com.norrmann.coqui.jsf.converter.ClienteDebeConverter;
import ar.com.norrmann.coqui.model.ClienteDebe;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

privileged aspect ClienteDebeConverter_Roo_Converter {
    
    declare parents: ClienteDebeConverter implements Converter;
    
    declare @type: ClienteDebeConverter: @FacesConverter("ar.com.norrmann.coqui.jsf.converter.ClienteDebeConverter");
    
    public Object ClienteDebeConverter.getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Long id = Long.parseLong(value);
        return ClienteDebe.findClienteDebe(id);
    }
    
    public String ClienteDebeConverter.getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof ClienteDebe ? ((ClienteDebe) value).getId().toString() : "";
    }
    
}