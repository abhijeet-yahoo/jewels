package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCostLabDtItem is a Querydsl query type for CostLabDtItem
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCostLabDtItem extends EntityPathBase<CostLabDtItem> {

    private static final long serialVersionUID = -632353917L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCostLabDtItem costLabDtItem = new QCostLabDtItem("costLabDtItem");

    public final QBagMt bagmt;

    public final QCostingDtItem costingDtItem;

    public final QCostingMt costingMt;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath itemNo = createString("itemNo");

    public final NumberPath<Double> labourRate = createNumber("labourRate", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLabourType labourType;

    public final NumberPath<Double> labourValue = createNumber("labourValue", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal metal;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt orderDt;

    public final BooleanPath perCaratRate = createBoolean("perCaratRate");

    public final BooleanPath percentage = createBoolean("percentage");

    public final BooleanPath perGramRate = createBoolean("perGramRate");

    public final BooleanPath perPcRate = createBoolean("perPcRate");

    public final BooleanPath rLock = createBoolean("rLock");

    public QCostLabDtItem(String variable) {
        this(CostLabDtItem.class, forVariable(variable), INITS);
    }

    public QCostLabDtItem(Path<? extends CostLabDtItem> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCostLabDtItem(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCostLabDtItem(PathMetadata<?> metadata, PathInits inits) {
        this(CostLabDtItem.class, metadata, inits);
    }

    public QCostLabDtItem(Class<? extends CostLabDtItem> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bagmt = inits.isInitialized("bagmt") ? new QBagMt(forProperty("bagmt"), inits.get("bagmt")) : null;
        this.costingDtItem = inits.isInitialized("costingDtItem") ? new QCostingDtItem(forProperty("costingDtItem"), inits.get("costingDtItem")) : null;
        this.costingMt = inits.isInitialized("costingMt") ? new QCostingMt(forProperty("costingMt"), inits.get("costingMt")) : null;
        this.labourType = inits.isInitialized("labourType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLabourType(forProperty("labourType")) : null;
        this.metal = inits.isInitialized("metal") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal(forProperty("metal")) : null;
        this.orderDt = inits.isInitialized("orderDt") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt(forProperty("orderDt"), inits.get("orderDt")) : null;
    }

}

