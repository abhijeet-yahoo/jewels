package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVouRetCompDt is a Querydsl query type for VouRetCompDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVouRetCompDt extends EntityPathBase<VouRetCompDt> {

    private static final long serialVersionUID = 1709998436L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVouRetCompDt vouRetCompDt = new QVouRetCompDt("vouRetCompDt");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QComponent component;

    public final NumberPath<Double> compQty = createNumber("compQty", Double.class);

    public final NumberPath<Double> compRate = createNumber("compRate", Double.class);

    public final NumberPath<Double> compValue = createNumber("compValue", Double.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final NumberPath<Double> metalValue = createNumber("metalValue", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final BooleanPath perGramRate = createBoolean("perGramRate");

    public final BooleanPath perPcRate = createBoolean("perPcRate");

    public final NumberPath<Double> purityConv = createNumber("purityConv", Double.class);

    public final BooleanPath rLock = createBoolean("rLock");

    public final QVouRetDt vouRetDt;

    public final QVouRetMt vouRetMt;

    public QVouRetCompDt(String variable) {
        this(VouRetCompDt.class, forVariable(variable), INITS);
    }

    public QVouRetCompDt(Path<? extends VouRetCompDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVouRetCompDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVouRetCompDt(PathMetadata<?> metadata, PathInits inits) {
        this(VouRetCompDt.class, metadata, inits);
    }

    public QVouRetCompDt(Class<? extends VouRetCompDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.component = inits.isInitialized("component") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QComponent(forProperty("component")) : null;
        this.vouRetDt = inits.isInitialized("vouRetDt") ? new QVouRetDt(forProperty("vouRetDt"), inits.get("vouRetDt")) : null;
        this.vouRetMt = inits.isInitialized("vouRetMt") ? new QVouRetMt(forProperty("vouRetMt"), inits.get("vouRetMt")) : null;
    }

}

