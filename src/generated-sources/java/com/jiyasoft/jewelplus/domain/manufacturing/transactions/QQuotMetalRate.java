package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QQuotMetalRate is a Querydsl query type for QuotMetalRate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QQuotMetalRate extends EntityPathBase<QuotMetalRate> {

    private static final long serialVersionUID = -585537378L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuotMetalRate quotMetalRate = new QQuotMetalRate("quotMetalRate");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> lossPerc = createNumber("lossPerc", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal metal;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final QQuotMt quotMt;

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public QQuotMetalRate(String variable) {
        this(QuotMetalRate.class, forVariable(variable), INITS);
    }

    public QQuotMetalRate(Path<? extends QuotMetalRate> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QQuotMetalRate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QQuotMetalRate(PathMetadata<?> metadata, PathInits inits) {
        this(QuotMetalRate.class, metadata, inits);
    }

    public QQuotMetalRate(Class<? extends QuotMetalRate> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.metal = inits.isInitialized("metal") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal(forProperty("metal")) : null;
        this.quotMt = inits.isInitialized("quotMt") ? new QQuotMt(forProperty("quotMt"), inits.get("quotMt")) : null;
    }

}

