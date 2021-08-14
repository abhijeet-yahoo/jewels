package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVAddMetalInv is a Querydsl query type for VAddMetalInv
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVAddMetalInv extends EntityPathBase<VAddMetalInv> {

    private static final long serialVersionUID = 1426140085L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVAddMetalInv vAddMetalInv = new QVAddMetalInv("vAddMetalInv");

    public final BooleanPath adjusted = createBoolean("adjusted");

    public final QCostingMt costingMt;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal metal;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final NumberPath<Double> netWt = createNumber("netWt", Double.class);

    public final NumberPath<Double> pureWt = createNumber("pureWt", Double.class);

    public QVAddMetalInv(String variable) {
        this(VAddMetalInv.class, forVariable(variable), INITS);
    }

    public QVAddMetalInv(Path<? extends VAddMetalInv> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVAddMetalInv(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVAddMetalInv(PathMetadata<?> metadata, PathInits inits) {
        this(VAddMetalInv.class, metadata, inits);
    }

    public QVAddMetalInv(Class<? extends VAddMetalInv> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.costingMt = inits.isInitialized("costingMt") ? new QCostingMt(forProperty("costingMt"), inits.get("costingMt")) : null;
        this.metal = inits.isInitialized("metal") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal(forProperty("metal")) : null;
    }

}

