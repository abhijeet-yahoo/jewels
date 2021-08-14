package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRepairRetDt is a Querydsl query type for RepairRetDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRepairRetDt extends EntityPathBase<RepairRetDt> {

    private static final long serialVersionUID = 510658212L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRepairRetDt repairRetDt = new QRepairRetDt("repairRetDt");

    public final NumberPath<Double> adjQty = createNumber("adjQty", Double.class);

    public final StringPath barcode = createString("barcode");

    public final NumberPath<Double> compValue = createNumber("compValue", Double.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesign design;

    public final StringPath destination = createString("destination");

    public final NumberPath<Double> discAmount = createNumber("discAmount", Double.class);

    public final NumberPath<Double> discPerc = createNumber("discPerc", Double.class);

    public final NumberPath<Double> finalPrice = createNumber("finalPrice", Double.class);

    public final NumberPath<Double> fob = createNumber("fob", Double.class);

    public final NumberPath<Double> grossWt = createNumber("grossWt", Double.class);

    public final NumberPath<Double> handlingValue = createNumber("handlingValue", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> labValue = createNumber("labValue", Double.class);

    public final NumberPath<Double> lossValue = createNumber("lossValue", Double.class);

    public final NumberPath<Double> lossWt = createNumber("lossWt", Double.class);

    public final NumberPath<Double> metalValue = createNumber("metalValue", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final NumberPath<Double> netAmount = createNumber("netAmount", Double.class);

    public final NumberPath<Double> netWt = createNumber("netWt", Double.class);

    public final StringPath orderRef = createString("orderRef");

    public final NumberPath<Double> other = createNumber("other", Double.class);

    public final NumberPath<Double> pcs = createNumber("pcs", Double.class);

    public final StringPath remark = createString("remark");

    public final QRepairRetMt repairRetMt;

    public final BooleanPath rLock = createBoolean("rLock");

    public final NumberPath<Double> setValue = createNumber("setValue", Double.class);

    public final QStockMt stockMt;

    public final NumberPath<Double> stoneValue = createNumber("stoneValue", Double.class);

    public final NumberPath<Double> tagPrice = createNumber("tagPrice", Double.class);

    public QRepairRetDt(String variable) {
        this(RepairRetDt.class, forVariable(variable), INITS);
    }

    public QRepairRetDt(Path<? extends RepairRetDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRepairRetDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRepairRetDt(PathMetadata<?> metadata, PathInits inits) {
        this(RepairRetDt.class, metadata, inits);
    }

    public QRepairRetDt(Class<? extends RepairRetDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.design = inits.isInitialized("design") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesign(forProperty("design"), inits.get("design")) : null;
        this.repairRetMt = inits.isInitialized("repairRetMt") ? new QRepairRetMt(forProperty("repairRetMt"), inits.get("repairRetMt")) : null;
        this.stockMt = inits.isInitialized("stockMt") ? new QStockMt(forProperty("stockMt"), inits.get("stockMt")) : null;
    }

}

