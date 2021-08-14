package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOrderMetalRate is a Querydsl query type for OrderMetalRate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrderMetalRate extends EntityPathBase<OrderMetalRate> {

    private static final long serialVersionUID = -1209323313L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderMetalRate orderMetalRate = new QOrderMetalRate("orderMetalRate");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> lossPerc = createNumber("lossPerc", Double.class);

    public final QMetal metal;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final QOrderMt orderMt;

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public QOrderMetalRate(String variable) {
        this(OrderMetalRate.class, forVariable(variable), INITS);
    }

    public QOrderMetalRate(Path<? extends OrderMetalRate> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderMetalRate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderMetalRate(PathMetadata<?> metadata, PathInits inits) {
        this(OrderMetalRate.class, metadata, inits);
    }

    public QOrderMetalRate(Class<? extends OrderMetalRate> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.metal = inits.isInitialized("metal") ? new QMetal(forProperty("metal")) : null;
        this.orderMt = inits.isInitialized("orderMt") ? new QOrderMt(forProperty("orderMt"), inits.get("orderMt")) : null;
    }

}

