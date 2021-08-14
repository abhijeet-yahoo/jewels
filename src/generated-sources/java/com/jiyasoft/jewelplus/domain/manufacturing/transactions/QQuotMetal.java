package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QQuotMetal is a Querydsl query type for QuotMetal
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QQuotMetal extends EntityPathBase<QuotMetal> {

    private static final long serialVersionUID = -1773984098L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuotMetal quotMetal = new QQuotMetal("quotMetal");

    public final NumberPath<Double> castWeight = createNumber("castWeight", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

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

    public final NumberPath<Double> processLoss = createNumber("processLoss", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final QQuotDt quotDt;

    public final QQuotMt quotMt;

    public final BooleanPath rLock = createBoolean("rLock");

    public QQuotMetal(String variable) {
        this(QuotMetal.class, forVariable(variable), INITS);
    }

    public QQuotMetal(Path<? extends QuotMetal> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QQuotMetal(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QQuotMetal(PathMetadata<?> metadata, PathInits inits) {
        this(QuotMetal.class, metadata, inits);
    }

    public QQuotMetal(Class<? extends QuotMetal> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.partNm = inits.isInitialized("partNm") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("partNm")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
        this.quotDt = inits.isInitialized("quotDt") ? new QQuotDt(forProperty("quotDt"), inits.get("quotDt")) : null;
        this.quotMt = inits.isInitialized("quotMt") ? new QQuotMt(forProperty("quotMt"), inits.get("quotMt")) : null;
    }

}

