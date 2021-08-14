package com.jiyasoft.jewelplus.domain.marketing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPackMetalRate is a Querydsl query type for PackMetalRate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPackMetalRate extends EntityPathBase<PackMetalRate> {

    private static final long serialVersionUID = 460915238L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPackMetalRate packMetalRate = new QPackMetalRate("packMetalRate");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> lossPerc = createNumber("lossPerc", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal metal;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final QPackMt packMt;

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public QPackMetalRate(String variable) {
        this(PackMetalRate.class, forVariable(variable), INITS);
    }

    public QPackMetalRate(Path<? extends PackMetalRate> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPackMetalRate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPackMetalRate(PathMetadata<?> metadata, PathInits inits) {
        this(PackMetalRate.class, metadata, inits);
    }

    public QPackMetalRate(Class<? extends PackMetalRate> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.metal = inits.isInitialized("metal") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal(forProperty("metal")) : null;
        this.packMt = inits.isInitialized("packMt") ? new QPackMt(forProperty("packMt"), inits.get("packMt")) : null;
    }

}

