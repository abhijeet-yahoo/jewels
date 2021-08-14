package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVouIssMetalDt is a Querydsl query type for VouIssMetalDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVouIssMetalDt extends EntityPathBase<VouIssMetalDt> {

    private static final long serialVersionUID = 1966462026L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVouIssMetalDt vouIssMetalDt = new QVouIssMetalDt("vouIssMetalDt");

    public final QBagMt bagMt;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> lossPerc = createNumber("lossPerc", Double.class);

    public final BooleanPath mainMetal = createBoolean("mainMetal");

    public final NumberPath<Integer> metalPcs = createNumber("metalPcs", Integer.class);

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final NumberPath<Double> metalValue = createNumber("metalValue", Double.class);

    public final NumberPath<Double> metalWeight = createNumber("metalWeight", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast partNm;

    public final NumberPath<Double> perGramRate = createNumber("perGramRate", Double.class);

    public final BooleanPath perPcRate = createBoolean("perPcRate");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final BooleanPath rLock = createBoolean("rLock");

    public final QVouIssDt vouIssDt;

    public final QVouIssMt vouIssMt;

    public QVouIssMetalDt(String variable) {
        this(VouIssMetalDt.class, forVariable(variable), INITS);
    }

    public QVouIssMetalDt(Path<? extends VouIssMetalDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVouIssMetalDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVouIssMetalDt(PathMetadata<?> metadata, PathInits inits) {
        this(VouIssMetalDt.class, metadata, inits);
    }

    public QVouIssMetalDt(Class<? extends VouIssMetalDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bagMt = inits.isInitialized("bagMt") ? new QBagMt(forProperty("bagMt"), inits.get("bagMt")) : null;
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.partNm = inits.isInitialized("partNm") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("partNm")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
        this.vouIssDt = inits.isInitialized("vouIssDt") ? new QVouIssDt(forProperty("vouIssDt"), inits.get("vouIssDt")) : null;
        this.vouIssMt = inits.isInitialized("vouIssMt") ? new QVouIssMt(forProperty("vouIssMt"), inits.get("vouIssMt")) : null;
    }

}

