// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.norrmann.coqui.model;

import ar.com.norrmann.coqui.model.Compra;
import ar.com.norrmann.coqui.model.DetalleCompra;
import ar.com.norrmann.coqui.model.Proveedor;
import java.util.Date;
import java.util.Set;

privileged aspect Compra_Roo_JavaBean {
    
    public Date Compra.getFechaCompra() {
        return this.fechaCompra;
    }
    
    public void Compra.setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    
    public Set<DetalleCompra> Compra.getDetalleList() {
        return this.detalleList;
    }
    
    public void Compra.setDetalleList(Set<DetalleCompra> detalleList) {
        this.detalleList = detalleList;
    }
    
    public Proveedor Compra.getProveedor() {
        return this.proveedor;
    }
    
    public void Compra.setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
}
