package com.jiyasoft.jewelplus.domain.marketing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QConsigMt is a Querydsl query type for ConsigMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QConsigMt extends EntityPathBase<ConsigMt> {

    private static final long serialVersionUID = 940153694L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConsigMt consigMt = new QConsigMt("consigMt");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QEmployee employee;

    public final StringPath finYear = createString("finYear");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QHSNMast hsnMast;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> insuranceAmount = createNumber("insuranceAmount", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast insuredBy;

    public final DateTimePath<java.util.Date> invDate = createDateTime("invDate", java.util.Date.class);

    public final StringPath invNo = createString("invNo");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment location;

    public final StringPath memoValidFor = createString("memoValidFor");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast modeOfDispatch;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast modeOfTransport;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final StringPath nameOfMode = createString("nameOfMode");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty party;

    public final StringPath priority = createString("priority");

    public final StringPath refNo = createString("refNo");

    public final StringPath remark = createString("remark");

    public final BooleanPath rLock = createBoolean("rLock");

    public final NumberPath<Integer> srNo = createNumber("srNo", Integer.class);

    public final NumberPath<Integer> vouType = createNumber("vouType", Integer.class);

    public QConsigMt(String variable) {
        this(ConsigMt.class, forVariable(variable), INITS);
    }

    public QConsigMt(Path<? extends ConsigMt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QConsigMt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QConsigMt(PathMetadata<?> metadata, PathInits inits) {
        this(ConsigMt.class, metadata, inits);
    }

    public QConsigMt(Class<? extends ConsigMt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.employee = inits.isInitialized("employee") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QEmployee(forProperty("employee"), inits.get("employee")) : null;
        this.hsnMast = inits.isInitialized("hsnMast") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QHSNMast(forProperty("hsnMast")) : null;
        this.insuredBy = inits.isInitialized("insuredBy") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("insuredBy")) : null;
        this.location = inits.isInitialized("location") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("location"), inits.get("location")) : null;
        this.modeOfDispatch = inits.isInitialized("modeOfDispatch") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("modeOfDispatch")) : null;
        this.modeOfTransport = inits.isInitialized("modeOfTransport") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("modeOfTransport")) : null;
        this.party = inits.isInitialized("party") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty(forProperty("party"), inits.get("party")) : null;
    }

}

