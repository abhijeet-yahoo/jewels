package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QQuotCompDt is a Querydsl query type for QuotCompDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QQuotCompDt extends EntityPathBase<QuotCompDt> {

    private static final long serialVersionUID = 563816264L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuotCompDt quotCompDt = new QQuotCompDt("quotCompDt");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QComponent component;

    public final NumberPath<Double> compQty = createNumber("compQty", Double.class);

    public final NumberPath<Double> compRate = createNumber("compRate", Double.class);

    public final NumberPath<Double> compValue = createNumber("compValue", Double.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final NumberPath<Double> metalValue = createNumber("metalValue", Double.class);

    public final NumberPath<Double> metalWt = createNumber("metalWt", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final BooleanPath perGramRate = createBoolean("perGramRate");

    public final BooleanPath perPcRate = createBoolean("perPcRate");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final NumberPath<Double> purityConv = createNumber("purityConv", Double.class);

    public final QQuotDt quotDt;

    public final QQuotMt quotMt;

    public final BooleanPath rLock = createBoolean("rLock");

    public QQuotCompDt(String variable) {
        this(QuotCompDt.class, forVariable(variable), INITS);
    }

    public QQuotCompDt(Path<? extends QuotCompDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QQuotCompDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QQuotCompDt(PathMetadata<?> metadata, PathInits inits) {
        this(QuotCompDt.class, metadata, inits);
    }

    public QQuotCompDt(Class<? extends QuotCompDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.component = inits.isInitialized("component") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QComponent(forProperty("component")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
        this.quotDt = inits.isInitialized("quotDt") ? new QQuotDt(forProperty("quotDt"), inits.get("quotDt")) : null;
        this.quotMt = inits.isInitialized("quotMt") ? new QQuotMt(forProperty("quotMt"), inits.get("quotMt")) : null;
    }

}

