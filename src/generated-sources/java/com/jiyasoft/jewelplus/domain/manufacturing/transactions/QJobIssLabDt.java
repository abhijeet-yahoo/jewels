package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QJobIssLabDt is a Querydsl query type for JobIssLabDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QJobIssLabDt extends EntityPathBase<JobIssLabDt> {

    private static final long serialVersionUID = 1001069553L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJobIssLabDt jobIssLabDt = new QJobIssLabDt("jobIssLabDt");

    public final QBagMt bagmt;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final BooleanPath gramWise = createBoolean("gramWise");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath itemNo = createString("itemNo");

    public final QJobIssDt jobIssDt;

    public final QJobIssMt jobIssMt;

    public final NumberPath<Double> labourRate = createNumber("labourRate", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLabourType labourType;

    public final NumberPath<Double> labourValue = createNumber("labourValue", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal metal;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt orderDt;

    public final BooleanPath pcsWise = createBoolean("pcsWise");

    public final BooleanPath perCaratRate = createBoolean("perCaratRate");

    public final BooleanPath percentWise = createBoolean("percentWise");

    public final BooleanPath perGramRate = createBoolean("perGramRate");

    public final BooleanPath perPcRate = createBoolean("perPcRate");

    public final BooleanPath rLock = createBoolean("rLock");

    public QJobIssLabDt(String variable) {
        this(JobIssLabDt.class, forVariable(variable), INITS);
    }

    public QJobIssLabDt(Path<? extends JobIssLabDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJobIssLabDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJobIssLabDt(PathMetadata<?> metadata, PathInits inits) {
        this(JobIssLabDt.class, metadata, inits);
    }

    public QJobIssLabDt(Class<? extends JobIssLabDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bagmt = inits.isInitialized("bagmt") ? new QBagMt(forProperty("bagmt"), inits.get("bagmt")) : null;
        this.jobIssDt = inits.isInitialized("jobIssDt") ? new QJobIssDt(forProperty("jobIssDt"), inits.get("jobIssDt")) : null;
        this.jobIssMt = inits.isInitialized("jobIssMt") ? new QJobIssMt(forProperty("jobIssMt"), inits.get("jobIssMt")) : null;
        this.labourType = inits.isInitialized("labourType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLabourType(forProperty("labourType")) : null;
        this.metal = inits.isInitialized("metal") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal(forProperty("metal")) : null;
        this.orderDt = inits.isInitialized("orderDt") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt(forProperty("orderDt"), inits.get("orderDt")) : null;
    }

}

