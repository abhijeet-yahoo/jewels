package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCostJobLabDt is a Querydsl query type for CostJobLabDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCostJobLabDt extends EntityPathBase<CostJobLabDt> {

    private static final long serialVersionUID = -772436211L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCostJobLabDt costJobLabDt = new QCostJobLabDt("costJobLabDt");

    public final QBagMt bagmt;

    public final QCostingJobDt costingJobDt;

    public final QCostingJobMt costingJobMt;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final BooleanPath gramWise = createBoolean("gramWise");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath itemNo = createString("itemNo");

    public final NumberPath<Double> labourRate = createNumber("labourRate", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLabourType labourType;

    public final NumberPath<Double> labourValue = createNumber("labourValue", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt orderDt;

    public final BooleanPath pcsWise = createBoolean("pcsWise");

    public final BooleanPath percentWise = createBoolean("percentWise");

    public final BooleanPath rLock = createBoolean("rLock");

    public QCostJobLabDt(String variable) {
        this(CostJobLabDt.class, forVariable(variable), INITS);
    }

    public QCostJobLabDt(Path<? extends CostJobLabDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCostJobLabDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCostJobLabDt(PathMetadata<?> metadata, PathInits inits) {
        this(CostJobLabDt.class, metadata, inits);
    }

    public QCostJobLabDt(Class<? extends CostJobLabDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bagmt = inits.isInitialized("bagmt") ? new QBagMt(forProperty("bagmt"), inits.get("bagmt")) : null;
        this.costingJobDt = inits.isInitialized("costingJobDt") ? new QCostingJobDt(forProperty("costingJobDt"), inits.get("costingJobDt")) : null;
        this.costingJobMt = inits.isInitialized("costingJobMt") ? new QCostingJobMt(forProperty("costingJobMt"), inits.get("costingJobMt")) : null;
        this.labourType = inits.isInitialized("labourType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLabourType(forProperty("labourType")) : null;
        this.orderDt = inits.isInitialized("orderDt") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt(forProperty("orderDt"), inits.get("orderDt")) : null;
    }

}

