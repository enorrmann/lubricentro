package ar.com.norrmann.coqui.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooDbManaged(automaticallyDelete = true)
@RooJpaActiveRecord(versionField = "", table = "movimientoitem", finders = { "findMovimientoitemsByItemId" })
public class Movimientoitem {

    public void updateFromDetalleCompra(DetalleCompra unDetalleCompra, Tipomovimiento tipoMovimiento) {
        setCantidad(unDetalleCompra.getCantidad());
        setFecha(unDetalleCompra.getCompra().getFechaCompra());
        setItemId(unDetalleCompra.getItem());
        setObservaciones("Compra");
        setTipoMovimientoId(tipoMovimiento);
    }

    public void updateFromDetalleVenta(DetalleVenta unDetalleVenta, Tipomovimiento tipoMovimiento) {
        setCantidad(unDetalleVenta.getCantidad());
        setFecha(unDetalleVenta.getVenta().getFecha());
        setItemId(unDetalleVenta.getItem());
        setObservaciones("Venta");
        setTipoMovimientoId(tipoMovimiento);
    }

    public static Long getStockActual(Item itemId) {
        if (itemId == null) throw new IllegalArgumentException("The itemId argument is required");
        EntityManager em = Movimientoitem.entityManager();
        TypedQuery<Long> q = em.createQuery("SELECT sum(o.cantidad*o.tipoMovimientoId.diferencia) FROM Movimientoitem AS o WHERE o.itemId = :itemId", Long.class);
        q.setParameter("itemId", itemId);
        List<Long> resultList = q.getResultList();
        return resultList != null && !resultList.isEmpty() && resultList.get(0) != null ? resultList.get(0) : 0L;
    }

    public static TypedQuery<ar.com.norrmann.coqui.model.Movimientoitem> findMovimientoitemsByItemId(Item itemId) {
        if (itemId == null) throw new IllegalArgumentException("The itemId argument is required");
        EntityManager em = Movimientoitem.entityManager();
        TypedQuery<Movimientoitem> q = em.createQuery("SELECT o FROM Movimientoitem AS o WHERE o.itemId = :itemId order by o.fecha desc", Movimientoitem.class);
        q.setParameter("itemId", itemId);
        return q;
    }

    public static List<ar.com.norrmann.coqui.model.Movimientoitem> findMovimientoitemsByTipoItem(Tipoitem tipoItem) {
        if (tipoItem == null) throw new IllegalArgumentException("The tipoItem argument is required");
        EntityManager em = Movimientoitem.entityManager();
        TypedQuery<Movimientoitem> q = em.createQuery("SELECT o FROM Movimientoitem AS o WHERE o.itemId.tipoItemId = :tipoItem order by o.fecha desc", Movimientoitem.class);
        q.setParameter("tipoItem", tipoItem);
        return q.getResultList();
    }

    public static void eliminarMovimientosItem(Item item) {
        List<Movimientoitem> movimientos = findMovimientoitemsByItemId(item).getResultList();
        if (movimientos != null && !movimientos.isEmpty()) {
            for (Movimientoitem unMovimientoitem : movimientos) {
                unMovimientoitem.remove();
            }
        }
    }
}
