package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCostMetalDtItem is a Querydsl query type for CostMetalDtItem
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCostMetalDtItem extends EntityPathBase<CostMetalDtItem> {

    private static final long serialVersionUID = 1783621821L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCostMetalDtItem costMetalDtItem = new QCostMetalDtItem("costMetalDtItem");

    public final NumberPath<Double> actMetalWeight = createNumber("actMetalWeight", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final QCostingDtItem costingDtItem;

    public final QCostingMt costingMt;

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> lossPerc = createNumber("lossPerc", Double.class);

    public final BooleanPath mainMetal = createBoolean("mainMetal");

    public final NumberPath<Integer> metalPcs = createNumber("metalPcs", Integer.class);

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final NumberPath<Double> metalValue = createNumber("metalValue", Double.class);

    public final NumberPath<Double> metalWeight = createNumber("metalWeight", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast partNm;

    public final NumberPath<Double> perGramRate = createNumber("perGramRate", Double.class);

    public final BooleanPath perPcRate = createBoolean("perPcRate");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final BooleanPath rLock = createBoolean("rLock");

    public QCostMetalDtItem(String variable) {
        this(CostMetalDtItem.class, forVariable(variable), INITS);
    }

    public QCostMetalDtItem(Path<? extends CostMetalDtItem> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCostMetalDtItem(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCostMetalDtItem(PathMetadata<?> metadata, PathInits inits) {
        this(CostMetalDtItem.class, metadata, inits);
    }

    public QCostMetalDtItem(Class<? extends CostMetalDtItem> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.costingDtItem = inits.isInitialized("costingDtItem") ? new QCostingDtItem(forProperty("costingDtItem"), inits.get("costingDtItem")) : null;
        this.costingMt = inits.isInitialized("costingMt") ? new QCostingMt(forProperty("costingMt"), inits.get("costingMt")) : null;
        this.partNm = inits.isInitialized("partNm") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("partNm")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

