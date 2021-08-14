package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVAddMetalRate is a Querydsl query type for VAddMetalRate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVAddMetalRate extends EntityPathBase<VAddMetalRate> {

    private static final long serialVersionUID = 1260925340L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVAddMetalRate vAddMetalRate = new QVAddMetalRate("vAddMetalRate");

    public final QCostingMt costingMt;

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> lossPerc = createNumber("lossPerc", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal metal;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public QVAddMetalRate(String variable) {
        this(VAddMetalRate.class, forVariable(variable), INITS);
    }

    public QVAddMetalRate(Path<? extends VAddMetalRate> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVAddMetalRate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVAddMetalRate(PathMetadata<?> metadata, PathInits inits) {
        this(VAddMetalRate.class, metadata, inits);
    }

    public QVAddMetalRate(Class<? extends VAddMetalRate> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.costingMt = inits.isInitialized("costingMt") ? new QCostingMt(forProperty("costingMt"), inits.get("costingMt")) : null;
        this.metal = inits.isInitialized("metal") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal(forProperty("metal")) : null;
    }

}

