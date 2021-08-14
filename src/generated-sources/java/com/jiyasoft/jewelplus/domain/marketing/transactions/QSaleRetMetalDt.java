package com.jiyasoft.jewelplus.domain.marketing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSaleRetMetalDt is a Querydsl query type for SaleRetMetalDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSaleRetMetalDt extends EntityPathBase<SaleRetMetalDt> {

    private static final long serialVersionUID = 1518527749L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSaleRetMetalDt saleRetMetalDt = new QSaleRetMetalDt("saleRetMetalDt");

    public final com.jiyasoft.jewelplus.domain.manufacturing.transactions.QBagMt bagMt;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> lossPerc = createNumber("lossPerc", Double.class);

    public final BooleanPath mainMetal = createBoolean("mainMetal");

    public final NumberPath<Integer> metalPcs = createNumber("metalPcs", Integer.class);

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final NumberPath<Double> metalValue = createNumber("metalValue", Double.class);

    public final NumberPath<Double> metalWeight = createNumber("metalWeight", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast partNm;

    public final NumberPath<Double> perGramRate = createNumber("perGramRate", Double.class);

    public final BooleanPath perPcRate = createBoolean("perPcRate");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final NumberPath<Double> purityConv = createNumber("purityConv", Double.class);

    public final NumberPath<Integer> refSaleDtId = createNumber("refSaleDtId", Integer.class);

    public final StringPath refTranType = createString("refTranType");

    public final BooleanPath rLock = createBoolean("rLock");

    public final QSaleRetDt saleRetDt;

    public final QSaleRetMt saleRetMt;

    public QSaleRetMetalDt(String variable) {
        this(SaleRetMetalDt.class, forVariable(variable), INITS);
    }

    public QSaleRetMetalDt(Path<? extends SaleRetMetalDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSaleRetMetalDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSaleRetMetalDt(PathMetadata<?> metadata, PathInits inits) {
        this(SaleRetMetalDt.class, metadata, inits);
    }

    public QSaleRetMetalDt(Class<? extends SaleRetMetalDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bagMt = inits.isInitialized("bagMt") ? new com.jiyasoft.jewelplus.domain.manufacturing.transactions.QBagMt(forProperty("bagMt"), inits.get("bagMt")) : null;
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.partNm = inits.isInitialized("partNm") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("partNm")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
        this.saleRetDt = inits.isInitialized("saleRetDt") ? new QSaleRetDt(forProperty("saleRetDt"), inits.get("saleRetDt")) : null;
        this.saleRetMt = inits.isInitialized("saleRetMt") ? new QSaleRetMt(forProperty("saleRetMt"), inits.get("saleRetMt")) : null;
    }

}

