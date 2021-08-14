package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVAddCompDt is a Querydsl query type for VAddCompDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVAddCompDt extends EntityPathBase<VAddCompDt> {

    private static final long serialVersionUID = 68366090L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVAddCompDt vAddCompDt = new QVAddCompDt("vAddCompDt");

    public final NumberPath<Double> clientPurityConv = createNumber("clientPurityConv", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QComponent component;

    public final NumberPath<Double> compPcs = createNumber("compPcs", Double.class);

    public final NumberPath<Double> compRate = createNumber("compRate", Double.class);

    public final NumberPath<Double> compValue = createNumber("compValue", Double.class);

    public final QCostingMt costingMt;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath itemNo = createString("itemNo");

    public final NumberPath<Double> lossPerc = createNumber("lossPerc", Double.class);

    public final NumberPath<Double> lossValue = createNumber("lossValue", Double.class);

    public final NumberPath<Double> lossWt = createNumber("lossWt", Double.class);

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final NumberPath<Double> metalValue = createNumber("metalValue", Double.class);

    public final NumberPath<Double> metalWt = createNumber("metalWt", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt orderDt;

    public final NumberPath<Double> perGramMetalRate = createNumber("perGramMetalRate", Double.class);

    public final BooleanPath perGramRate = createBoolean("perGramRate");

    public final BooleanPath perPcRate = createBoolean("perPcRate");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final NumberPath<Double> purityConv = createNumber("purityConv", Double.class);

    public final BooleanPath rLock = createBoolean("rLock");

    public final QVAddDt vAddDt;

    public QVAddCompDt(String variable) {
        this(VAddCompDt.class, forVariable(variable), INITS);
    }

    public QVAddCompDt(Path<? extends VAddCompDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVAddCompDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVAddCompDt(PathMetadata<?> metadata, PathInits inits) {
        this(VAddCompDt.class, metadata, inits);
    }

    public QVAddCompDt(Class<? extends VAddCompDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.component = inits.isInitialized("component") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QComponent(forProperty("component")) : null;
        this.costingMt = inits.isInitialized("costingMt") ? new QCostingMt(forProperty("costingMt"), inits.get("costingMt")) : null;
        this.orderDt = inits.isInitialized("orderDt") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt(forProperty("orderDt"), inits.get("orderDt")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
        this.vAddDt = inits.isInitialized("vAddDt") ? new QVAddDt(forProperty("vAddDt"), inits.get("vAddDt")) : null;
    }

}

