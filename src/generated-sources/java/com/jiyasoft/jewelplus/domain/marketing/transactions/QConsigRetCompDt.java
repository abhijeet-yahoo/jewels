package com.jiyasoft.jewelplus.domain.marketing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QConsigRetCompDt is a Querydsl query type for ConsigRetCompDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QConsigRetCompDt extends EntityPathBase<ConsigRetCompDt> {

    private static final long serialVersionUID = 382467689L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConsigRetCompDt consigRetCompDt = new QConsigRetCompDt("consigRetCompDt");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QComponent component;

    public final NumberPath<Double> compQty = createNumber("compQty", Double.class);

    public final NumberPath<Double> compRate = createNumber("compRate", Double.class);

    public final NumberPath<Double> compValue = createNumber("compValue", Double.class);

    public final NumberPath<Double> compWt = createNumber("compWt", Double.class);

    public final QConsigRetDt consigRetDt;

    public final QConsigRetMt consigRetMt;

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

    public QConsigRetCompDt(String variable) {
        this(ConsigRetCompDt.class, forVariable(variable), INITS);
    }

    public QConsigRetCompDt(Path<? extends ConsigRetCompDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QConsigRetCompDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QConsigRetCompDt(PathMetadata<?> metadata, PathInits inits) {
        this(ConsigRetCompDt.class, metadata, inits);
    }

    public QConsigRetCompDt(Class<? extends ConsigRetCompDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.component = inits.isInitialized("component") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QComponent(forProperty("component")) : null;
        this.consigRetDt = inits.isInitialized("consigRetDt") ? new QConsigRetDt(forProperty("consigRetDt"), inits.get("consigRetDt")) : null;
        this.consigRetMt = inits.isInitialized("consigRetMt") ? new QConsigRetMt(forProperty("consigRetMt"), inits.get("consigRetMt")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

