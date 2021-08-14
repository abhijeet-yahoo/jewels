package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCostJobCompDt is a Querydsl query type for CostJobCompDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCostJobCompDt extends EntityPathBase<CostJobCompDt> {

    private static final long serialVersionUID = 1579916783L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCostJobCompDt costJobCompDt = new QCostJobCompDt("costJobCompDt");

    public final QBagMt bagMt;

    public final NumberPath<Double> clientPurityConv = createNumber("clientPurityConv", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QComponent component;

    public final NumberPath<Double> compPcs = createNumber("compPcs", Double.class);

    public final NumberPath<Double> compRate = createNumber("compRate", Double.class);

    public final NumberPath<Double> compValue = createNumber("compValue", Double.class);

    public final QCostingJobDt costingJobDt;

    public final QCostingJobMt costingJobMt;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath itemNo = createString("itemNo");

    public final NumberPath<Double> manualRate = createNumber("manualRate", Double.class);

    public final NumberPath<Double> metalWt = createNumber("metalWt", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt orderDt;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final NumberPath<Double> purityConv = createNumber("purityConv", Double.class);

    public final BooleanPath rLock = createBoolean("rLock");

    public QCostJobCompDt(String variable) {
        this(CostJobCompDt.class, forVariable(variable), INITS);
    }

    public QCostJobCompDt(Path<? extends CostJobCompDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCostJobCompDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCostJobCompDt(PathMetadata<?> metadata, PathInits inits) {
        this(CostJobCompDt.class, metadata, inits);
    }

    public QCostJobCompDt(Class<? extends CostJobCompDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bagMt = inits.isInitialized("bagMt") ? new QBagMt(forProperty("bagMt"), inits.get("bagMt")) : null;
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.component = inits.isInitialized("component") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QComponent(forProperty("component")) : null;
        this.costingJobDt = inits.isInitialized("costingJobDt") ? new QCostingJobDt(forProperty("costingJobDt"), inits.get("costingJobDt")) : null;
        this.costingJobMt = inits.isInitialized("costingJobMt") ? new QCostingJobMt(forProperty("costingJobMt"), inits.get("costingJobMt")) : null;
        this.orderDt = inits.isInitialized("orderDt") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt(forProperty("orderDt"), inits.get("orderDt")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

