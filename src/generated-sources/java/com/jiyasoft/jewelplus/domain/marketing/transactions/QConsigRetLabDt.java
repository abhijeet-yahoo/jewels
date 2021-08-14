package com.jiyasoft.jewelplus.domain.marketing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QConsigRetLabDt is a Querydsl query type for ConsigRetLabDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QConsigRetLabDt extends EntityPathBase<ConsigRetLabDt> {

    private static final long serialVersionUID = -672516269L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConsigRetLabDt consigRetLabDt = new QConsigRetLabDt("consigRetLabDt");

    public final QConsigRetDt consigRetDt;

    public final QConsigRetMt consigRetMt;

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

    public QConsigRetLabDt(String variable) {
        this(ConsigRetLabDt.class, forVariable(variable), INITS);
    }

    public QConsigRetLabDt(Path<? extends ConsigRetLabDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QConsigRetLabDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QConsigRetLabDt(PathMetadata<?> metadata, PathInits inits) {
        this(ConsigRetLabDt.class, metadata, inits);
    }

    public QConsigRetLabDt(Class<? extends ConsigRetLabDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.consigRetDt = inits.isInitialized("consigRetDt") ? new QConsigRetDt(forProperty("consigRetDt"), inits.get("consigRetDt")) : null;
        this.consigRetMt = inits.isInitialized("consigRetMt") ? new QConsigRetMt(forProperty("consigRetMt"), inits.get("consigRetMt")) : null;
        this.labourType = inits.isInitialized("labourType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLabourType(forProperty("labourType")) : null;
        this.metal = inits.isInitialized("metal") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal(forProperty("metal")) : null;
    }

}

