package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVouRetLabDt is a Querydsl query type for VouRetLabDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVouRetLabDt extends EntityPathBase<VouRetLabDt> {

    private static final long serialVersionUID = -75503368L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVouRetLabDt vouRetLabDt = new QVouRetLabDt("vouRetLabDt");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final BooleanPath gramWise = createBoolean("gramWise");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> labourRate = createNumber("labourRate", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLabourType labourtype;

    public final NumberPath<Double> LabourValue = createNumber("LabourValue", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal metal;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final BooleanPath pcsWise = createBoolean("pcsWise");

    public final BooleanPath perCarataRate = createBoolean("perCarataRate");

    public final BooleanPath percentWise = createBoolean("percentWise");

    public final BooleanPath rLock = createBoolean("rLock");

    public final QVouRetDt vouRetDt;

    public final QVouRetMt vouRetMt;

    public QVouRetLabDt(String variable) {
        this(VouRetLabDt.class, forVariable(variable), INITS);
    }

    public QVouRetLabDt(Path<? extends VouRetLabDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVouRetLabDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVouRetLabDt(PathMetadata<?> metadata, PathInits inits) {
        this(VouRetLabDt.class, metadata, inits);
    }

    public QVouRetLabDt(Class<? extends VouRetLabDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.labourtype = inits.isInitialized("labourtype") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLabourType(forProperty("labourtype")) : null;
        this.metal = inits.isInitialized("metal") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal(forProperty("metal")) : null;
        this.vouRetDt = inits.isInitialized("vouRetDt") ? new QVouRetDt(forProperty("vouRetDt"), inits.get("vouRetDt")) : null;
        this.vouRetMt = inits.isInitialized("vouRetMt") ? new QVouRetMt(forProperty("vouRetMt"), inits.get("vouRetMt")) : null;
    }

}

