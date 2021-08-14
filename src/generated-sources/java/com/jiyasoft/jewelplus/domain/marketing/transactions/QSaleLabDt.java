package com.jiyasoft.jewelplus.domain.marketing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSaleLabDt is a Querydsl query type for SaleLabDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSaleLabDt extends EntityPathBase<SaleLabDt> {

    private static final long serialVersionUID = -606805330L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSaleLabDt saleLabDt = new QSaleLabDt("saleLabDt");

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

    public final BooleanPath rLock = createBoolean("rLock");

    public final QSaleDt saleDt;

    public final QSaleMt saleMt;

    public QSaleLabDt(String variable) {
        this(SaleLabDt.class, forVariable(variable), INITS);
    }

    public QSaleLabDt(Path<? extends SaleLabDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSaleLabDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSaleLabDt(PathMetadata<?> metadata, PathInits inits) {
        this(SaleLabDt.class, metadata, inits);
    }

    public QSaleLabDt(Class<? extends SaleLabDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.labourType = inits.isInitialized("labourType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLabourType(forProperty("labourType")) : null;
        this.metal = inits.isInitialized("metal") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal(forProperty("metal")) : null;
        this.saleDt = inits.isInitialized("saleDt") ? new QSaleDt(forProperty("saleDt"), inits.get("saleDt")) : null;
        this.saleMt = inits.isInitialized("saleMt") ? new QSaleMt(forProperty("saleMt"), inits.get("saleMt")) : null;
    }

}

