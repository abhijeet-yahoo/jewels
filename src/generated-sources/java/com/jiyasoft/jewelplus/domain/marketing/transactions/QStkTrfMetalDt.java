package com.jiyasoft.jewelplus.domain.marketing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStkTrfMetalDt is a Querydsl query type for StkTrfMetalDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStkTrfMetalDt extends EntityPathBase<StkTrfMetalDt> {

    private static final long serialVersionUID = -71627599L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStkTrfMetalDt stkTrfMetalDt = new QStkTrfMetalDt("stkTrfMetalDt");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

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

    public final BooleanPath perPcRate = createBoolean("perPcRate");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final NumberPath<Double> purityConv = createNumber("purityConv", Double.class);

    public final BooleanPath rLock = createBoolean("rLock");

    public final QStkTrfDt stkTrfDt;

    public final QStkTrfMt stkTrfMt;

    public QStkTrfMetalDt(String variable) {
        this(StkTrfMetalDt.class, forVariable(variable), INITS);
    }

    public QStkTrfMetalDt(Path<? extends StkTrfMetalDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStkTrfMetalDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStkTrfMetalDt(PathMetadata<?> metadata, PathInits inits) {
        this(StkTrfMetalDt.class, metadata, inits);
    }

    public QStkTrfMetalDt(Class<? extends StkTrfMetalDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.partNm = inits.isInitialized("partNm") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("partNm")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
        this.stkTrfDt = inits.isInitialized("stkTrfDt") ? new QStkTrfDt(forProperty("stkTrfDt"), inits.get("stkTrfDt")) : null;
        this.stkTrfMt = inits.isInitialized("stkTrfMt") ? new QStkTrfMt(forProperty("stkTrfMt"), inits.get("stkTrfMt")) : null;
    }

}

