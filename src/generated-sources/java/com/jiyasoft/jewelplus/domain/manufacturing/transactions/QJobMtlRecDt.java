package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QJobMtlRecDt is a Querydsl query type for JobMtlRecDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QJobMtlRecDt extends EntityPathBase<JobMtlRecDt> {

    private static final long serialVersionUID = 75137176L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJobMtlRecDt jobMtlRecDt = new QJobMtlRecDt("jobMtlRecDt");

    public final BooleanPath adjusted = createBoolean("adjusted");

    public final NumberPath<Double> amount = createNumber("amount", Double.class);

    public final NumberPath<Double> balance = createNumber("balance", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath in999 = createBoolean("in999");

    public final NumberPath<Double> invWt = createNumber("invWt", Double.class);

    public final QJobRecMt jobRecMt;

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

    public QJobMtlRecDt(String variable) {
        this(JobMtlRecDt.class, forVariable(variable), INITS);
    }

    public QJobMtlRecDt(Path<? extends JobMtlRecDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJobMtlRecDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJobMtlRecDt(PathMetadata<?> metadata, PathInits inits) {
        this(JobMtlRecDt.class, metadata, inits);
    }

    public QJobMtlRecDt(Class<? extends JobMtlRecDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.jobRecMt = inits.isInitialized("jobRecMt") ? new QJobRecMt(forProperty("jobRecMt"), inits.get("jobRecMt")) : null;
        this.metal = inits.isInitialized("metal") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal(forProperty("metal")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

