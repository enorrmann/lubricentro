// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.norrmann.coqui.model;

import ar.com.norrmann.coqui.model.Compra;
import ar.com.norrmann.coqui.model.DetalleCompra;
import ar.com.norrmann.coqui.model.Item;
import ar.com.norrmann.coqui.model.Movimientoitem;
import java.math.BigDecimal;

privileged aspect DetalleCompra_Roo_JavaBean {
    
    public Compra DetalleCompra.getCompra() {
        return this.compra;
    }
    
    public void DetalleCompra.setCompra(Compra compra) {
        this.compra = compra;
    }
    
    public BigDecimal DetalleCompra.getPrecioUnitario() {
        return this.precioUnitario;
    }
    
    public void DetalleCompra.setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    
    public Item DetalleCompra.getItem() {
        return this.item;
    }
    
    public void DetalleCompra.setItem(Item item) {
        this.item = item;
    }
    
    public int DetalleCompra.getCantidad() {
        return this.cantidad;
    }
    
    public void DetalleCompra.setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public Movimientoitem DetalleCompra.getMovimientoIngresoStock() {
        return this.movimientoIngresoStock;
    }
    
    public void DetalleCompra.setMovimientoIngresoStock(Movimientoitem movimientoIngresoStock) {
        this.movimientoIngresoStock = movimientoIngresoStock;
    }
    
}