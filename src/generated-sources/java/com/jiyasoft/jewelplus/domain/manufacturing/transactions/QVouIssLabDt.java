package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVouIssLabDt is a Querydsl query type for VouIssLabDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVouIssLabDt extends EntityPathBase<VouIssLabDt> {

    private static final long serialVersionUID = 930593296L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVouIssLabDt vouIssLabDt = new QVouIssLabDt("vouIssLabDt");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

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

    public final BooleanPath percentWise = createBoolean("percentWise");

    public final BooleanPath rLock = createBoolean("rLock");

    public final QVouIssDt vouIssDt;

    public final QVouIssMt vouIssMt;

    public QVouIssLabDt(String variable) {
        this(VouIssLabDt.class, forVariable(variable), INITS);
    }

    public QVouIssLabDt(Path<? extends VouIssLabDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVouIssLabDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVouIssLabDt(PathMetadata<?> metadata, PathInits inits) {
        this(VouIssLabDt.class, metadata, inits);
    }

    public QVouIssLabDt(Class<? extends VouIssLabDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.labourType = inits.isInitialized("labourType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLabourType(forProperty("labourType")) : null;
        this.metal = inits.isInitialized("metal") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal(forProperty("metal")) : null;
        this.vouIssDt = inits.isInitialized("vouIssDt") ? new QVouIssDt(forProperty("vouIssDt"), inits.get("vouIssDt")) : null;
        this.vouIssMt = inits.isInitialized("vouIssMt") ? new QVouIssMt(forProperty("vouIssMt"), inits.get("vouIssMt")) : null;
    }

}

