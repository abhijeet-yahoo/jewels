package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVAddCompAdj is a Querydsl query type for VAddCompAdj
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVAddCompAdj extends EntityPathBase<VAddCompAdj> {

    private static final long serialVersionUID = 2119345517L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVAddCompAdj vAddCompAdj = new QVAddCompAdj("vAddCompAdj");

    public final NumberPath<Double> adjustmentPcs = createNumber("adjustmentPcs", Double.class);

    public final NumberPath<Double> adjustmentWt = createNumber("adjustmentWt", Double.class);

    public final DateTimePath<java.util.Date> compInvDate = createDateTime("compInvDate", java.util.Date.class);

    public final StringPath compInvNo = createString("compInvNo");

    public final QCompInwardDt compInwardDt;

    public final QCompInwardMt compInwardMt;

    public final QComponentPurchaseDt componentPurchaseDt;

    public final QCostingMt costingMt;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final QVAddCompInv vAddCompInv;

    public QVAddCompAdj(String variable) {
        this(VAddCompAdj.class, forVariable(variable), INITS);
    }

    public QVAddCompAdj(Path<? extends VAddCompAdj> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVAddCompAdj(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVAddCompAdj(PathMetadata<?> metadata, PathInits inits) {
        this(VAddCompAdj.class, metadata, inits);
    }

    public QVAddCompAdj(Class<? extends VAddCompAdj> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.compInwardDt = inits.isInitialized("compInwardDt") ? new QCompInwardDt(forProperty("compInwardDt"), inits.get("compInwardDt")) : null;
        this.compInwardMt = inits.isInitialized("compInwardMt") ? new QCompInwardMt(forProperty("compInwardMt"), inits.get("compInwardMt")) : null;
        this.componentPurchaseDt = inits.isInitialized("componentPurchaseDt") ? new QComponentPurchaseDt(forProperty("componentPurchaseDt"), inits.get("componentPurchaseDt")) : null;
        this.costingMt = inits.isInitialized("costingMt") ? new QCostingMt(forProperty("costingMt"), inits.get("costingMt")) : null;
        this.vAddCompInv = inits.isInitialized("vAddCompInv") ? new QVAddCompInv(forProperty("vAddCompInv"), inits.get("vAddCompInv")) : null;
    }

}

