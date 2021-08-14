package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOrderLabDt is a Querydsl query type for OrderLabDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrderLabDt extends EntityPathBase<OrderLabDt> {

    private static final long serialVersionUID = -833978555L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderLabDt orderLabDt = new QOrderLabDt("orderLabDt");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final BooleanPath gramWise = createBoolean("gramWise");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> labourRate = createNumber("labourRate", Double.class);

    public final QLabourType labourType;

    public final NumberPath<Double> labourValue = createNumber("labourValue", Double.class);

    public final QMetal metal;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final QOrderDt orderDt;

    public final QOrderMt orderMt;

    public final BooleanPath pcsWise = createBoolean("pcsWise");

    public final BooleanPath perCaratRate = createBoolean("perCaratRate");

    public final BooleanPath percentWise = createBoolean("percentWise");

    public final BooleanPath rLock = createBoolean("rLock");

    public QOrderLabDt(String variable) {
        this(OrderLabDt.class, forVariable(variable), INITS);
    }

    public QOrderLabDt(Path<? extends OrderLabDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderLabDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderLabDt(PathMetadata<?> metadata, PathInits inits) {
        this(OrderLabDt.class, metadata, inits);
    }

    public QOrderLabDt(Class<? extends OrderLabDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.labourType = inits.isInitialized("labourType") ? new QLabourType(forProperty("labourType")) : null;
        this.metal = inits.isInitialized("metal") ? new QMetal(forProperty("metal")) : null;
        this.orderDt = inits.isInitialized("orderDt") ? new QOrderDt(forProperty("orderDt"), inits.get("orderDt")) : null;
        this.orderMt = inits.isInitialized("orderMt") ? new QOrderMt(forProperty("orderMt"), inits.get("orderMt")) : null;
    }

}

