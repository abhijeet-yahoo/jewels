package com.jiyasoft.jewelplus.domain.marketing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPackLabDt is a Querydsl query type for PackLabDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPackLabDt extends EntityPathBase<PackLabDt> {

    private static final long serialVersionUID = 1294767132L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPackLabDt packLabDt = new QPackLabDt("packLabDt");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> labourRate = createNumber("labourRate", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLabourType labourType;

    public final NumberPath<Double> labourValue = createNumber("labourValue", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal metal;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final QPackDt packDt;

    public final QPackMt packMt;

    public final BooleanPath perCaratRate = createBoolean("perCaratRate");

    public final BooleanPath percentage = createBoolean("percentage");

    public final BooleanPath perGramRate = createBoolean("perGramRate");

    public final BooleanPath perPcRate = createBoolean("perPcRate");

    public final BooleanPath rLock = createBoolean("rLock");

    public QPackLabDt(String variable) {
        this(PackLabDt.class, forVariable(variable), INITS);
    }

    public QPackLabDt(Path<? extends PackLabDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPackLabDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPackLabDt(PathMetadata<?> metadata, PathInits inits) {
        this(PackLabDt.class, metadata, inits);
    }

    public QPackLabDt(Class<? extends PackLabDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.labourType = inits.isInitialized("labourType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLabourType(forProperty("labourType")) : null;
        this.metal = inits.isInitialized("metal") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal(forProperty("metal")) : null;
        this.packDt = inits.isInitialized("packDt") ? new QPackDt(forProperty("packDt"), inits.get("packDt")) : null;
        this.packMt = inits.isInitialized("packMt") ? new QPackMt(forProperty("packMt"), inits.get("packMt")) : null;
    }

}

