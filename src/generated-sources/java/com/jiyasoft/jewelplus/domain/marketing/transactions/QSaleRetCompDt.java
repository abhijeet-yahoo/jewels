package com.jiyasoft.jewelplus.domain.marketing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSaleRetCompDt is a Querydsl query type for SaleRetCompDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSaleRetCompDt extends EntityPathBase<SaleRetCompDt> {

    private static final long serialVersionUID = 464469809L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSaleRetCompDt saleRetCompDt = new QSaleRetCompDt("saleRetCompDt");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QComponent component;

    public final NumberPath<Double> compQty = createNumber("compQty", Double.class);

    public final NumberPath<Double> compRate = createNumber("compRate", Double.class);

    public final NumberPath<Double> compValue = createNumber("compValue", Double.class);

    public final NumberPath<Double> compWt = createNumber("compWt", Double.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final NumberPath<Double> metalValue = createNumber("metalValue", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final BooleanPath perGramRate = createBoolean("perGramRate");

    public final BooleanPath perPcRate = createBoolean("perPcRate");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final NumberPath<Double> purityConv = createNumber("purityConv", Double.class);

    public final NumberPath<Integer> refSaleDtId = createNumber("refSaleDtId", Integer.class);

    public final StringPath refTranType = createString("refTranType");

    public final BooleanPath rLock = createBoolean("rLock");

    public final QSaleRetDt saleRetDt;

    public final QSaleRetMt saleRetMt;

    public QSaleRetCompDt(String variable) {
        this(SaleRetCompDt.class, forVariable(variable), INITS);
    }

    public QSaleRetCompDt(Path<? extends SaleRetCompDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSaleRetCompDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSaleRetCompDt(PathMetadata<?> metadata, PathInits inits) {
        this(SaleRetCompDt.class, metadata, inits);
    }

    public QSaleRetCompDt(Class<? extends SaleRetCompDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.component = inits.isInitialized("component") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QComponent(forProperty("component")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
        this.saleRetDt = inits.isInitialized("saleRetDt") ? new QSaleRetDt(forProperty("saleRetDt"), inits.get("saleRetDt")) : null;
        this.saleRetMt = inits.isInitialized("saleRetMt") ? new QSaleRetMt(forProperty("saleRetMt"), inits.get("saleRetMt")) : null;
    }

}

