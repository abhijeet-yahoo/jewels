package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVouIssMt is a Querydsl query type for VouIssMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVouIssMt extends EntityPathBase<VouIssMt> {

    private static final long serialVersionUID = -1938333836L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVouIssMt vouIssMt = new QVouIssMt("vouIssMt");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final StringPath finYear = createString("finYear");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> insuranceAmount = createNumber("insuranceAmount", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast insuredBy;

    public final DateTimePath<java.util.Date> invDate = createDateTime("invDate", java.util.Date.class);

    public final StringPath invNo = createString("invNo");

    public final StringPath memoValidFor = createString("memoValidFor");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast modeOfDispatch;

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

    public QVouIssMt(String variable) {
        this(VouIssMt.class, forVariable(variable), INITS);
    }

    public QVouIssMt(Path<? extends VouIssMt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVouIssMt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVouIssMt(PathMetadata<?> metadata, PathInits inits) {
        this(VouIssMt.class, metadata, inits);
    }

    public QVouIssMt(Class<? extends VouIssMt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.insuredBy = inits.isInitialized("insuredBy") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("insuredBy")) : null;
        this.modeOfDispatch = inits.isInitialized("modeOfDispatch") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("modeOfDispatch")) : null;
        this.party = inits.isInitialized("party") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty(forProperty("party"), inits.get("party")) : null;
    }

}

