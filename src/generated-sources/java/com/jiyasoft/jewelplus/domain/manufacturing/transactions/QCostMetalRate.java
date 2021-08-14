package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCostMetalRate is a Querydsl query type for CostMetalRate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCostMetalRate extends EntityPathBase<CostMetalRate> {

    private static final long serialVersionUID = -145229734L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCostMetalRate costMetalRate = new QCostMetalRate("costMetalRate");

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

    public QCostMetalRate(String variable) {
        this(CostMetalRate.class, forVariable(variable), INITS);
    }

    public QCostMetalRate(Path<? extends CostMetalRate> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCostMetalRate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCostMetalRate(PathMetadata<?> metadata, PathInits inits) {
        this(CostMetalRate.class, metadata, inits);
    }

    public QCostMetalRate(Class<? extends CostMetalRate> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.costingMt = inits.isInitialized("costingMt") ? new QCostingMt(forProperty("costingMt"), inits.get("costingMt")) : null;
        this.metal = inits.isInitialized("metal") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal(forProperty("metal")) : null;
    }

}

