package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOrderDt is a Querydsl query type for OrderDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrderDt extends EntityPathBase<OrderDt> {

    private static final long serialVersionUID = 912567624L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderDt orderDt = new QOrderDt("orderDt");

    public final StringPath barcode = createString("barcode");

    public final NumberPath<Double> cancelQty = createNumber("cancelQty", Double.class);

    public final QColor color;

    public final NumberPath<Double> compValue = createNumber("compValue", Double.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final DateTimePath<java.util.Date> deliveryDate = createDateTime("deliveryDate", java.util.Date.class);

    public final QDesign design;

    public final StringPath designRemark = createString("designRemark");

    public final StringPath destination = createString("destination");

    public final NumberPath<Double> discAmount = createNumber("discAmount", Double.class);

    public final NumberPath<Double> discPercent = createNumber("discPercent", Double.class);

    public final DateTimePath<java.util.Date> dueDate = createDateTime("dueDate", java.util.Date.class);

    public final NumberPath<Double> finalPrice = createNumber("finalPrice", Double.class);

    public final NumberPath<Double> fob = createNumber("fob", Double.class);

    public final NumberPath<Double> grossWt = createNumber("grossWt", Double.class);

    public final NumberPath<Double> handlingValue = createNumber("handlingValue", Double.class);

    public final NumberPath<Double> hdlgValue = createNumber("hdlgValue", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath item = createString("item");

    public final StringPath ktDesc = createString("ktDesc");

    public final NumberPath<Double> labValue = createNumber("labValue", Double.class);

    public final NumberPath<Double> lossPercDt = createNumber("lossPercDt", Double.class);

    public final NumberPath<Double> lossValue = createNumber("lossValue", Double.class);

    public final NumberPath<Double> lossWt = createNumber("lossWt", Double.class);

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final NumberPath<Double> metalValue = createNumber("metalValue", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final NumberPath<Double> netAmount = createNumber("netAmount", Double.class);

    public final NumberPath<Double> netWt = createNumber("netWt", Double.class);

    public final QOrderMt orderMt;

    public final StringPath ordRef = createString("ordRef");

    public final NumberPath<Double> other = createNumber("other", Double.class);

    public final NumberPath<Double> pcs = createNumber("pcs", Double.class);

    public final NumberPath<Double> perGmWt = createNumber("perGmWt", Double.class);

    public final NumberPath<Double> perPcFinalPrice = createNumber("perPcFinalPrice", Double.class);

    public final QProductSize productSize;

    public final QPurity purity;

    public final NumberPath<Double> purityConv = createNumber("purityConv", Double.class);

    public final StringPath qltyDesc = createString("qltyDesc");

    public final StringPath refNo = createString("refNo");

    public final StringPath remark = createString("remark");

    public final NumberPath<Double> reqCarat = createNumber("reqCarat", Double.class);

    public final BooleanPath rLock = createBoolean("rLock");

    public final NumberPath<Double> setValue = createNumber("setValue", Double.class);

    public final NumberPath<Integer> srNo = createNumber("srNo", Integer.class);

    public final StringPath stampInst = createString("stampInst");

    public final NumberPath<Double> stnInwardQty = createNumber("stnInwardQty", Double.class);

    public final NumberPath<Double> stnOutwardQty = createNumber("stnOutwardQty", Double.class);

    public final NumberPath<Double> stoneValue = createNumber("stoneValue", Double.class);

    public final NumberPath<Double> tagPrice = createNumber("tagPrice", Double.class);

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public QOrderDt(String variable) {
        this(OrderDt.class, forVariable(variable), INITS);
    }

    public QOrderDt(Path<? extends OrderDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderDt(PathMetadata<?> metadata, PathInits inits) {
        this(OrderDt.class, metadata, inits);
    }

    public QOrderDt(Class<? extends OrderDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new QColor(forProperty("color")) : null;
        this.design = inits.isInitialized("design") ? new QDesign(forProperty("design"), inits.get("design")) : null;
        this.orderMt = inits.isInitialized("orderMt") ? new QOrderMt(forProperty("orderMt"), inits.get("orderMt")) : null;
        this.productSize = inits.isInitialized("productSize") ? new QProductSize(forProperty("productSize"), inits.get("productSize")) : null;
        this.purity = inits.isInitialized("purity") ? new QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

