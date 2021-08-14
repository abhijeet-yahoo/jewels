package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOrderMetal is a Querydsl query type for OrderMetal
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrderMetal extends EntityPathBase<OrderMetal> {

    private static final long serialVersionUID = -832917681L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderMetal orderMetal = new QOrderMetal("orderMetal");

    public final NumberPath<Double> castWeight = createNumber("castWeight", Double.class);

    public final QColor color;

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> lossPerc = createNumber("lossPerc", Double.class);

    public final BooleanPath mainMetal = createBoolean("mainMetal");

    public final NumberPath<Integer> metalPcs = createNumber("metalPcs", Integer.class);

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final NumberPath<Double> metalValue = createNumber("metalValue", Double.class);

    public final NumberPath<Double> metalWeight = createNumber("metalWeight", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final QOrderDt orderDt;

    public final QOrderMt orderMt;

    public final QLookUpMast partNm;

    public final NumberPath<Double> perGramRate = createNumber("perGramRate", Double.class);

    public final NumberPath<Double> processLoss = createNumber("processLoss", Double.class);

    public final QPurity purity;

    public final BooleanPath rLock = createBoolean("rLock");

    public QOrderMetal(String variable) {
        this(OrderMetal.class, forVariable(variable), INITS);
    }

    public QOrderMetal(Path<? extends OrderMetal> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderMetal(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderMetal(PathMetadata<?> metadata, PathInits inits) {
        this(OrderMetal.class, metadata, inits);
    }

    public QOrderMetal(Class<? extends OrderMetal> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new QColor(forProperty("color")) : null;
        this.orderDt = inits.isInitialized("orderDt") ? new QOrderDt(forProperty("orderDt"), inits.get("orderDt")) : null;
        this.orderMt = inits.isInitialized("orderMt") ? new QOrderMt(forProperty("orderMt"), inits.get("orderMt")) : null;
        this.partNm = inits.isInitialized("partNm") ? new QLookUpMast(forProperty("partNm")) : null;
        this.purity = inits.isInitialized("purity") ? new QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

