package com.jiyasoft.jewelplus.domain.marketing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QConsigRmMetalDt is a Querydsl query type for ConsigRmMetalDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QConsigRmMetalDt extends EntityPathBase<ConsigRmMetalDt> {

    private static final long serialVersionUID = -2130176603L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConsigRmMetalDt consigRmMetalDt = new QConsigRmMetalDt("consigRmMetalDt");

    public final NumberPath<Double> adjWt = createNumber("adjWt", Double.class);

    public final NumberPath<Double> amount = createNumber("amount", Double.class);

    public final NumberPath<Double> balance = createNumber("balance", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final QConsigMt consigMt;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal metal;

    public final NumberPath<Double> metalWt = createNumber("metalWt", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final NumberPath<Double> pureWt = createNumber("pureWt", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final NumberPath<Double> purityConv = createNumber("purityConv", Double.class);

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public final StringPath remark = createString("remark");

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public QConsigRmMetalDt(String variable) {
        this(ConsigRmMetalDt.class, forVariable(variable), INITS);
    }

    public QConsigRmMetalDt(Path<? extends ConsigRmMetalDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QConsigRmMetalDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QConsigRmMetalDt(PathMetadata<?> metadata, PathInits inits) {
        this(ConsigRmMetalDt.class, metadata, inits);
    }

    public QConsigRmMetalDt(Class<? extends ConsigRmMetalDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.consigMt = inits.isInitialized("consigMt") ? new QConsigMt(forProperty("consigMt"), inits.get("consigMt")) : null;
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.metal = inits.isInitialized("metal") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal(forProperty("metal")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

