package com.jiyasoft.jewelplus.domain.marketing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStkTrfLabDt is a Querydsl query type for StkTrfLabDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStkTrfLabDt extends EntityPathBase<StkTrfLabDt> {

    private static final long serialVersionUID = -1024597961L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStkTrfLabDt stkTrfLabDt = new QStkTrfLabDt("stkTrfLabDt");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> labourRate = createNumber("labourRate", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLabourType labourType;

    public final NumberPath<Double> labourValue = createNumber("labourValue", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal metal;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final BooleanPath perCaratRate = createBoolean("perCaratRate");

    public final BooleanPath percentage = createBoolean("percentage");

    public final BooleanPath perGramRate = createBoolean("perGramRate");

    public final BooleanPath perPcRate = createBoolean("perPcRate");

    public final BooleanPath rLock = createBoolean("rLock");

    public final QStkTrfDt stkTrfDt;

    public final QStkTrfMt stkTrfMt;

    public QStkTrfLabDt(String variable) {
        this(StkTrfLabDt.class, forVariable(variable), INITS);
    }

    public QStkTrfLabDt(Path<? extends StkTrfLabDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStkTrfLabDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStkTrfLabDt(PathMetadata<?> metadata, PathInits inits) {
        this(StkTrfLabDt.class, metadata, inits);
    }

    public QStkTrfLabDt(Class<? extends StkTrfLabDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.labourType = inits.isInitialized("labourType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLabourType(forProperty("labourType")) : null;
        this.metal = inits.isInitialized("metal") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal(forProperty("metal")) : null;
        this.stkTrfDt = inits.isInitialized("stkTrfDt") ? new QStkTrfDt(forProperty("stkTrfDt"), inits.get("stkTrfDt")) : null;
        this.stkTrfMt = inits.isInitialized("stkTrfMt") ? new QStkTrfMt(forProperty("stkTrfMt"), inits.get("stkTrfMt")) : null;
    }

}

