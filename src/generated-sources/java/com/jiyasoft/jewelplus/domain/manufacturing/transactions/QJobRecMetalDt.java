package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QJobRecMetalDt is a Querydsl query type for JobRecMetalDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QJobRecMetalDt extends EntityPathBase<JobRecMetalDt> {

    private static final long serialVersionUID = 920411140L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJobRecMetalDt jobRecMetalDt = new QJobRecMetalDt("jobRecMetalDt");

    public final QBagMt bagMt;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QJobRecDt jobRecDt;

    public final QJobRecMt jobRecMt;

    public final NumberPath<Double> lossPerc = createNumber("lossPerc", Double.class);

    public final BooleanPath mainMetal = createBoolean("mainMetal");

    public final NumberPath<Integer> metalPcs = createNumber("metalPcs", Integer.class);

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final NumberPath<Double> metalValue = createNumber("metalValue", Double.class);

    public final NumberPath<Double> metalWeight = createNumber("metalWeight", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast partNm;

    public final NumberPath<Double> perGramRate = createNumber("perGramRate", Double.class);

    public final BooleanPath perPcRate = createBoolean("perPcRate");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final BooleanPath rLock = createBoolean("rLock");

    public QJobRecMetalDt(String variable) {
        this(JobRecMetalDt.class, forVariable(variable), INITS);
    }

    public QJobRecMetalDt(Path<? extends JobRecMetalDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJobRecMetalDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJobRecMetalDt(PathMetadata<?> metadata, PathInits inits) {
        this(JobRecMetalDt.class, metadata, inits);
    }

    public QJobRecMetalDt(Class<? extends JobRecMetalDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bagMt = inits.isInitialized("bagMt") ? new QBagMt(forProperty("bagMt"), inits.get("bagMt")) : null;
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.jobRecDt = inits.isInitialized("jobRecDt") ? new QJobRecDt(forProperty("jobRecDt"), inits.get("jobRecDt")) : null;
        this.jobRecMt = inits.isInitialized("jobRecMt") ? new QJobRecMt(forProperty("jobRecMt"), inits.get("jobRecMt")) : null;
        this.partNm = inits.isInitialized("partNm") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("partNm")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

