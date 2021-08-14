package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCostMetalDt is a Querydsl query type for CostMetalDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCostMetalDt extends EntityPathBase<CostMetalDt> {

    private static final long serialVersionUID = -2064953718L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCostMetalDt costMetalDt = new QCostMetalDt("costMetalDt");

    public final QBagMt bagMt;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final QCostingDt costingDt;

    public final QCostingMt costingMt;

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

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

    public QCostMetalDt(String variable) {
        this(CostMetalDt.class, forVariable(variable), INITS);
    }

    public QCostMetalDt(Path<? extends CostMetalDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCostMetalDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCostMetalDt(PathMetadata<?> metadata, PathInits inits) {
        this(CostMetalDt.class, metadata, inits);
    }

    public QCostMetalDt(Class<? extends CostMetalDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bagMt = inits.isInitialized("bagMt") ? new QBagMt(forProperty("bagMt"), inits.get("bagMt")) : null;
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.costingDt = inits.isInitialized("costingDt") ? new QCostingDt(forProperty("costingDt"), inits.get("costingDt")) : null;
        this.costingMt = inits.isInitialized("costingMt") ? new QCostingMt(forProperty("costingMt"), inits.get("costingMt")) : null;
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.partNm = inits.isInitialized("partNm") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("partNm")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

