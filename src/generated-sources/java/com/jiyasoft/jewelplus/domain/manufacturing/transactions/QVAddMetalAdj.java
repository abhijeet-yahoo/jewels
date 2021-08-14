package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVAddMetalAdj is a Querydsl query type for VAddMetalAdj
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVAddMetalAdj extends EntityPathBase<VAddMetalAdj> {

    private static final long serialVersionUID = 1426132075L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVAddMetalAdj vAddMetalAdj = new QVAddMetalAdj("vAddMetalAdj");

    public final NumberPath<Double> adjustmentWt = createNumber("adjustmentWt", Double.class);

    public final QCostingMt costingMt;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> metalInvDate = createDateTime("metalInvDate", java.util.Date.class);

    public final StringPath metalInvNo = createString("metalInvNo");

    public final QMetalPurchaseDt metalPurchaseDt;

    public final QMetalPurchaseMt metalPurchaseMt;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final QVAddMetalInv vAddMetalInv;

    public QVAddMetalAdj(String variable) {
        this(VAddMetalAdj.class, forVariable(variable), INITS);
    }

    public QVAddMetalAdj(Path<? extends VAddMetalAdj> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVAddMetalAdj(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVAddMetalAdj(PathMetadata<?> metadata, PathInits inits) {
        this(VAddMetalAdj.class, metadata, inits);
    }

    public QVAddMetalAdj(Class<? extends VAddMetalAdj> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.costingMt = inits.isInitialized("costingMt") ? new QCostingMt(forProperty("costingMt"), inits.get("costingMt")) : null;
        this.metalPurchaseDt = inits.isInitialized("metalPurchaseDt") ? new QMetalPurchaseDt(forProperty("metalPurchaseDt"), inits.get("metalPurchaseDt")) : null;
        this.metalPurchaseMt = inits.isInitialized("metalPurchaseMt") ? new QMetalPurchaseMt(forProperty("metalPurchaseMt"), inits.get("metalPurchaseMt")) : null;
        this.vAddMetalInv = inits.isInitialized("vAddMetalInv") ? new QVAddMetalInv(forProperty("vAddMetalInv"), inits.get("vAddMetalInv")) : null;
    }

}

