package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QQuotLabDt is a Querydsl query type for QuotLabDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QQuotLabDt extends EntityPathBase<QuotLabDt> {

    private static final long serialVersionUID = -1775044972L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuotLabDt quotLabDt = new QQuotLabDt("quotLabDt");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final BooleanPath gramWise = createBoolean("gramWise");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> labourRate = createNumber("labourRate", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLabourType labourType;

    public final NumberPath<Double> labourValue = createNumber("labourValue", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal metal;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final BooleanPath pcsWise = createBoolean("pcsWise");

    public final BooleanPath perCaratRate = createBoolean("perCaratRate");

    public final BooleanPath percentWise = createBoolean("percentWise");

    public final QQuotDt quotDt;

    public final QQuotMt quotMt;

    public final BooleanPath rLock = createBoolean("rLock");

    public QQuotLabDt(String variable) {
        this(QuotLabDt.class, forVariable(variable), INITS);
    }

    public QQuotLabDt(Path<? extends QuotLabDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QQuotLabDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QQuotLabDt(PathMetadata<?> metadata, PathInits inits) {
        this(QuotLabDt.class, metadata, inits);
    }

    public QQuotLabDt(Class<? extends QuotLabDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.labourType = inits.isInitialized("labourType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLabourType(forProperty("labourType")) : null;
        this.metal = inits.isInitialized("metal") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal(forProperty("metal")) : null;
        this.quotDt = inits.isInitialized("quotDt") ? new QQuotDt(forProperty("quotDt"), inits.get("quotDt")) : null;
        this.quotMt = inits.isInitialized("quotMt") ? new QQuotMt(forProperty("quotMt"), inits.get("quotMt")) : null;
    }

}

