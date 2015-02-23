package ar.com.norrmann.coqui.model;

import ar.com.norrmann.coqui.model.dto.ResumenItemDto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

@RooJavaBean
@RooToString
@RooDbManaged(automaticallyDelete = true)
@RooJpaActiveRecord(versionField = "", table = "item", finders = { "findItemsByTipoItemId", "findItemsByCategoriaId", "findItemsByCodigoLike", "findItemsByCodigoLikeOrDescripcionLikeAndTipoItemIdEquals", "findItemsByCodigoEquals", "findItemsByCodigoReferenciaLike", "findItemsByCategoriaIdOrMarcaId", "findItemsByCategoriaIdAndMarcaId", "findItemsByMarcaId", "findItemsByDescripcionLike" })
public class Item {

    private String codigoReferencia;

    private String codigo;

    private String descripcion;

    @Transactional
    public void calcularStockActual() {
        TypedQuery<Long> q = entityManager().createQuery("SELECT sum(o.cantidad*o.tipoMovimientoId.diferencia) FROM Movimientoitem AS o WHERE o.itemId = :itemId", Long.class);
        q.setParameter("itemId", this);
        List<Long> resultList = q.getResultList();
        Long stockActual = resultList != null && !resultList.isEmpty() && resultList.get(0) != null ? resultList.get(0) : 0L;
        this.setStockActual(stockActual.intValue());
        merge();
    }

    public static List<ar.com.norrmann.coqui.model.Item> findItemsByCodigoLikeOrDescripcionLikeAndTipoItemIdEquals(String descripcion, Tipoitem tipoItemId) {
        if (descripcion == null || descripcion.isEmpty()) descripcion = "%%";
        descripcion = descripcion.replace('*', '%');
        if (descripcion.charAt(0) != '%') {
            descripcion = "%" + descripcion;
        }
        if (descripcion.charAt(descripcion.length() - 1) != '%') {
            descripcion = descripcion + "%";
        }
        if (tipoItemId == null) throw new IllegalArgumentException("The tipoItemId argument is required");
        EntityManager em = Item.entityManager();
        TypedQuery<Item> q = em.createQuery("SELECT o FROM Item AS o WHERE (LOWER(o.codigo) LIKE LOWER(:codigo)  OR LOWER(o.descripcion) LIKE LOWER(:descripcion))  AND o.tipoItemId = :tipoItemId", Item.class);
        q.setParameter("codigo", descripcion);
        q.setParameter("descripcion", descripcion);
        q.setParameter("tipoItemId", tipoItemId);
        return q.getResultList();
    }

    public static List<ar.com.norrmann.coqui.model.Item> findProductosAReponer() {
        Long tipoItemId = Tipoitem.ID_PRODUCTO;
        EntityManager em = Item.entityManager();
        TypedQuery<Item> q = em.createQuery("SELECT o FROM Item AS o WHERE o.tipoItemId.id = :tipoItemId and (o.stockActual is null or o.stockActual<=o.puntoReposicion)", Item.class);
        q.setParameter("tipoItemId", tipoItemId);
        return q.getResultList();
    }

    public static List<ar.com.norrmann.coqui.model.dto.ResumenItemDto> getResumenItemList() {
        final String queryString = "SELECT t.id, t.tipoItem, c.id, c.categoria, m.id, m.marca, i.id, i.descripcion, sum(i.stockActual) as stock, sum(i.precioCosto*i.stockActual) as costo, sum(i.precioVenta*i.stockActual) as venta FROM item i, tipoitem t, categoria c, marca m WHERE 1=1 and i.tipoItem_id=t.id AND i.categoria_id=c.id AND i.marca_id=m.id and i.stockActual > 0 ";
        final String groupBy = " group by c.id ";
        final String orderBy = " order by t.id,c.categoria,m.marca ";
        EntityManager em = Item.entityManager();
        Query query = em.createNativeQuery(queryString + groupBy + orderBy);
        return getResumenItemDtoList(query.getResultList());
    }

    private static List<ar.com.norrmann.coqui.model.dto.ResumenItemDto> getResumenItemDtoList(List<java.lang.Object[]> objectList) {
        List<ResumenItemDto> resumenItemDtoList = new ArrayList<ResumenItemDto>();
        if (objectList == null || objectList.isEmpty()) return resumenItemDtoList;
        for (Object[] unObjectArray : objectList) {
            resumenItemDtoList.add(new ResumenItemDto(unObjectArray));
        }
        return resumenItemDtoList;
    }

    public String toString() {
        return getNombreLargo();
    }

    public String getNombreLargo() {
        String codigoReferencia = getCodigoReferencia();
        Categoria categoria = getCategoriaId();
        Marca marca = getMarcaId();
        StringBuilder sb = new StringBuilder();
        sb.append(codigoReferencia);
        if (categoria != null) {
            sb.append(" ");
            sb.append(categoria.getCategoria());
        }
        if (marca != null) {
            sb.append(" ");
            sb.append(marca.getMarca());
        }
        return sb.toString();
    }

    @Transactional
    public void aumentarPorcentaje(BigDecimal porcentaje) {
        if (porcentaje == null || porcentaje.intValue() <= 0) return;
        BigDecimal variacion = porcentaje.divide(new BigDecimal(100)).add(new BigDecimal(1));
        setPrecioVenta(getPrecioVenta().multiply(variacion).setScale(2, BigDecimal.ROUND_HALF_DOWN));
        merge();
    }

    @Transactional
    public void reducirPorcentaje(BigDecimal porcentaje) {
        if (porcentaje == null || porcentaje.intValue() >= 0) return;
        porcentaje = porcentaje.abs();
        BigDecimal variacion = porcentaje.divide(new BigDecimal(100)).add(new BigDecimal(1));
        setPrecioVenta(getPrecioVenta().divide(variacion, 2, BigDecimal.ROUND_HALF_DOWN));
        merge();
    }
}
