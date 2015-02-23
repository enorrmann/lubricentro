package ar.com.norrmann.coqui.jsf;

import java.util.ArrayList;

import ar.com.norrmann.coqui.model.Automotor;
import ar.com.norrmann.coqui.model.Modelo;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;

@RooJsfManagedBean(entity = Modelo.class, beanName = "modeloBean")
public class ModeloBean {
    public String onEdit() {
        return null;
    }

}
