package ar.com.norrmann.coqui.jsf;

import ar.com.norrmann.coqui.model.ClienteDebe;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = ClienteDebe.class, beanName = "clienteDebeBean")
public class ClienteDebeBean {
}
