package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QKtConversionDt is a Querydsl query type for KtConversionDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QKtConversionDt extends EntityPathBase<KtConversionDt> {

    private static final long serialVersionUID = 565546799L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QKtConversionDt ktConversionDt = new QKtConversionDt("ktConversionDt");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor issColor;

    public final NumberPath<Double> issFreshMetalWt = createNumber("issFreshMetalWt", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity issPurity;

    public final NumberPath<Double> issUsedMetalWt = createNumber("issUsedMetalWt", Double.class);

    public final QKtConversionMt ktConversionMt;

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final NumberPath<Double> pureWt = createNumber("pureWt", Double.class);

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public QKtConversionDt(String variable) {
        this(KtConversionDt.class, forVariable(variable), INITS);
    }

    public QKtConversionDt(Path<? extends KtConversionDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QKtConversionDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QKtConversionDt(PathMetadata<?> metadata, PathInits inits) {
        this(KtConversionDt.class, metadata, inits);
    }

    public QKtConversionDt(Class<? extends KtConversionDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.issColor = inits.isInitialized("issColor") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("issColor")) : null;
        this.issPurity = inits.isInitialized("issPurity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("issPurity"), inits.get("issPurity")) : null;
        this.ktConversionMt = inits.isInitialized("ktConversionMt") ? new QKtConversionMt(forProperty("ktConversionMt"), inits.get("ktConversionMt")) : null;
    }

}

