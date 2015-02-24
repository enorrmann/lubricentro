package ar.com.norrmann.coqui.jsf;

import ar.com.norrmann.coqui.model.Pago;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = Pago.class, beanName = "pagoBean")
public class PagoBean {
}
