package com.jiyasoft.jewelplus.domain.marketing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QConsigRetRmMetalDt is a Querydsl query type for ConsigRetRmMetalDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QConsigRetRmMetalDt extends EntityPathBase<ConsigRetRmMetalDt> {

    private static final long serialVersionUID = 407621778L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConsigRetRmMetalDt consigRetRmMetalDt = new QConsigRetRmMetalDt("consigRetRmMetalDt");

    public final BooleanPath adjusted = createBoolean("adjusted");

    public final NumberPath<Double> adjWt = createNumber("adjWt", Double.class);

    public final NumberPath<Double> amount = createNumber("amount", Double.class);

    public final NumberPath<Double> balance = createNumber("balance", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final QConsigRetMt consigRetMt;

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

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public QConsigRetRmMetalDt(String variable) {
        this(ConsigRetRmMetalDt.class, forVariable(variable), INITS);
    }

    public QConsigRetRmMetalDt(Path<? extends ConsigRetRmMetalDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QConsigRetRmMetalDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QConsigRetRmMetalDt(PathMetadata<?> metadata, PathInits inits) {
        this(ConsigRetRmMetalDt.class, metadata, inits);
    }

    public QConsigRetRmMetalDt(Class<? extends ConsigRetRmMetalDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.consigRetMt = inits.isInitialized("consigRetMt") ? new QConsigRetMt(forProperty("consigRetMt"), inits.get("consigRetMt")) : null;
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.metal = inits.isInitialized("metal") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal(forProperty("metal")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

