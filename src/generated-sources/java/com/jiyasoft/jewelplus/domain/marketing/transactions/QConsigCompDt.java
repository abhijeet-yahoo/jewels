package com.jiyasoft.jewelplus.domain.marketing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QConsigCompDt is a Querydsl query type for ConsigCompDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QConsigCompDt extends EntityPathBase<ConsigCompDt> {

    private static final long serialVersionUID = -2016605642L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConsigCompDt consigCompDt = new QConsigCompDt("consigCompDt");

    public final NumberPath<Double> adjQty = createNumber("adjQty", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QComponent component;

    public final NumberPath<Double> compQty = createNumber("compQty", Double.class);

    public final NumberPath<Double> compRate = createNumber("compRate", Double.class);

    public final NumberPath<Double> compValue = createNumber("compValue", Double.class);

    public final NumberPath<Double> compWt = createNumber("compWt", Double.class);

    public final QConsigDt consigDt;

    public final QConsigMt consigMt;

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

    public QConsigCompDt(String variable) {
        this(ConsigCompDt.class, forVariable(variable), INITS);
    }

    public QConsigCompDt(Path<? extends ConsigCompDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QConsigCompDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QConsigCompDt(PathMetadata<?> metadata, PathInits inits) {
        this(ConsigCompDt.class, metadata, inits);
    }

    public QConsigCompDt(Class<? extends ConsigCompDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.component = inits.isInitialized("component") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QComponent(forProperty("component")) : null;
        this.consigDt = inits.isInitialized("consigDt") ? new QConsigDt(forProperty("consigDt"), inits.get("consigDt")) : null;
        this.consigMt = inits.isInitialized("consigMt") ? new QConsigMt(forProperty("consigMt"), inits.get("consigMt")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

