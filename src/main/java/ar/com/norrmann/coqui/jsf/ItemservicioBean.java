package ar.com.norrmann.coqui.jsf;

import ar.com.norrmann.coqui.model.Itemservicio;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = Itemservicio.class, beanName = "itemservicioBean")
public class ItemservicioBean {
}
