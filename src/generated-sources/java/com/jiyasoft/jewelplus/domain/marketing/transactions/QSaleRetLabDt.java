package com.jiyasoft.jewelplus.domain.marketing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSaleRetLabDt is a Querydsl query type for SaleRetLabDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSaleRetLabDt extends EntityPathBase<SaleRetLabDt> {

    private static final long serialVersionUID = 1823980939L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSaleRetLabDt saleRetLabDt = new QSaleRetLabDt("saleRetLabDt");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

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

    public final BooleanPath percentage = createBoolean("percentage");

    public final BooleanPath percentWise = createBoolean("percentWise");

    public final BooleanPath perGramRate = createBoolean("perGramRate");

    public final BooleanPath perPcRate = createBoolean("perPcRate");

    public final NumberPath<Integer> refSaleDtId = createNumber("refSaleDtId", Integer.class);

    public final StringPath refTranType = createString("refTranType");

    public final BooleanPath rLock = createBoolean("rLock");

    public final QSaleRetDt saleRetDt;

    public final QSaleRetMt saleRetMt;

    public QSaleRetLabDt(String variable) {
        this(SaleRetLabDt.class, forVariable(variable), INITS);
    }

    public QSaleRetLabDt(Path<? extends SaleRetLabDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSaleRetLabDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSaleRetLabDt(PathMetadata<?> metadata, PathInits inits) {
        this(SaleRetLabDt.class, metadata, inits);
    }

    public QSaleRetLabDt(Class<? extends SaleRetLabDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.labourType = inits.isInitialized("labourType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLabourType(forProperty("labourType")) : null;
        this.metal = inits.isInitialized("metal") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal(forProperty("metal")) : null;
        this.saleRetDt = inits.isInitialized("saleRetDt") ? new QSaleRetDt(forProperty("saleRetDt"), inits.get("saleRetDt")) : null;
        this.saleRetMt = inits.isInitialized("saleRetMt") ? new QSaleRetMt(forProperty("saleRetMt"), inits.get("saleRetMt")) : null;
    }

}

