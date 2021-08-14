package com.jiyasoft.jewelplus.domain.marketing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStkTrfCompDt is a Querydsl query type for StkTrfCompDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStkTrfCompDt extends EntityPathBase<StkTrfCompDt> {

    private static final long serialVersionUID = -1942130171L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStkTrfCompDt stkTrfCompDt = new QStkTrfCompDt("stkTrfCompDt");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QComponent component;

    public final NumberPath<Double> compQty = createNumber("compQty", Double.class);

    public final NumberPath<Double> compRate = createNumber("compRate", Double.class);

    public final NumberPath<Double> compValue = createNumber("compValue", Double.class);

    public final NumberPath<Double> compWt = createNumber("compWt", Double.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> lossPerc = createNumber("lossPerc", Double.class);

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final NumberPath<Double> metalValue = createNumber("metalValue", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final NumberPath<Double> perGramMetalRate = createNumber("perGramMetalRate", Double.class);

    public final BooleanPath perGramRate = createBoolean("perGramRate");

    public final BooleanPath perPcRate = createBoolean("perPcRate");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final NumberPath<Double> purityConv = createNumber("purityConv", Double.class);

    public final BooleanPath rLock = createBoolean("rLock");

    public final QStkTrfDt stkTrfDt;

    public final QStkTrfMt stkTrfMt;

    public QStkTrfCompDt(String variable) {
        this(StkTrfCompDt.class, forVariable(variable), INITS);
    }

    public QStkTrfCompDt(Path<? extends StkTrfCompDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStkTrfCompDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStkTrfCompDt(PathMetadata<?> metadata, PathInits inits) {
        this(StkTrfCompDt.class, metadata, inits);
    }

    public QStkTrfCompDt(Class<? extends StkTrfCompDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.component = inits.isInitialized("component") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QComponent(forProperty("component")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
        this.stkTrfDt = inits.isInitialized("stkTrfDt") ? new QStkTrfDt(forProperty("stkTrfDt"), inits.get("stkTrfDt")) : null;
        this.stkTrfMt = inits.isInitialized("stkTrfMt") ? new QStkTrfMt(forProperty("stkTrfMt"), inits.get("stkTrfMt")) : null;
    }

}

