// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.norrmann.coqui.jsf;

import ar.com.norrmann.coqui.jsf.ApplicationBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.MenuModel;

privileged aspect ApplicationBean_Roo_ApplicationBean {
    
    declare @type: ApplicationBean: @ManagedBean;
    
    declare @type: ApplicationBean: @RequestScoped;
    
    public MenuModel ApplicationBean.getMenuModel() {
        return menuModel;
    }
    
}
