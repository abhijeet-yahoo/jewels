package com.jiyasoft.jewelplus.domain.marketing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSaleRetRmMetalDt is a Querydsl query type for SaleRetRmMetalDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSaleRetRmMetalDt extends EntityPathBase<SaleRetRmMetalDt> {

    private static final long serialVersionUID = -503612726L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSaleRetRmMetalDt saleRetRmMetalDt = new QSaleRetRmMetalDt("saleRetRmMetalDt");

    public final BooleanPath adjusted = createBoolean("adjusted");

    public final NumberPath<Double> amount = createNumber("amount", Double.class);

    public final NumberPath<Double> balance = createNumber("balance", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath in999 = createBoolean("in999");

    public final NumberPath<Double> invWt = createNumber("invWt", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal metal;

    public final NumberPath<Double> metalWt = createNumber("metalWt", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final NumberPath<Double> pureWt = createNumber("pureWt", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final NumberPath<Double> purityConv = createNumber("purityConv", Double.class);

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public final NumberPath<Integer> RefTranDtId = createNumber("RefTranDtId", Integer.class);

    public final StringPath remark = createString("remark");

    public final QSaleRetMt saleRetMt;

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public QSaleRetRmMetalDt(String variable) {
        this(SaleRetRmMetalDt.class, forVariable(variable), INITS);
    }

    public QSaleRetRmMetalDt(Path<? extends SaleRetRmMetalDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSaleRetRmMetalDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSaleRetRmMetalDt(PathMetadata<?> metadata, PathInits inits) {
        this(SaleRetRmMetalDt.class, metadata, inits);
    }

    public QSaleRetRmMetalDt(Class<? extends SaleRetRmMetalDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.metal = inits.isInitialized("metal") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal(forProperty("metal")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
        this.saleRetMt = inits.isInitialized("saleRetMt") ? new QSaleRetMt(forProperty("saleRetMt"), inits.get("saleRetMt")) : null;
    }

}

