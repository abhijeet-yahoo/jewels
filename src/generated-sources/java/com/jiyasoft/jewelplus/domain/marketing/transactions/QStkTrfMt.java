package com.jiyasoft.jewelplus.domain.marketing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStkTrfMt is a Querydsl query type for StkTrfMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStkTrfMt extends EntityPathBase<StkTrfMt> {

    private static final long serialVersionUID = -1211350355L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStkTrfMt stkTrfMt = new QStkTrfMt("stkTrfMt");

    public final NumberPath<Double> cgst = createNumber("cgst", Double.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final NumberPath<Double> finalPrice = createNumber("finalPrice", Double.class);

    public final StringPath finYear = createString("finYear");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment fromLocation;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QHSNMast hsnMast;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> igst = createNumber("igst", Double.class);

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

    public final NumberPath<Double> otherCharges = createNumber("otherCharges", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty party;

    public final StringPath priority = createString("priority");

    public final StringPath refNo = createString("refNo");

    public final StringPath remark = createString("remark");

    public final BooleanPath rLock = createBoolean("rLock");

    public final NumberPath<Double> sgst = createNumber("sgst", Double.class);

    public final NumberPath<Integer> srNo = createNumber("srNo", Integer.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment toLocation;

    public final StringPath tranType = createString("tranType");

    public final NumberPath<Integer> vouType = createNumber("vouType", Integer.class);

    public QStkTrfMt(String variable) {
        this(StkTrfMt.class, forVariable(variable), INITS);
    }

    public QStkTrfMt(Path<? extends StkTrfMt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStkTrfMt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStkTrfMt(PathMetadata<?> metadata, PathInits inits) {
        this(StkTrfMt.class, metadata, inits);
    }

    public QStkTrfMt(Class<? extends StkTrfMt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fromLocation = inits.isInitialized("fromLocation") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("fromLocation"), inits.get("fromLocation")) : null;
        this.hsnMast = inits.isInitialized("hsnMast") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QHSNMast(forProperty("hsnMast")) : null;
        this.insuredBy = inits.isInitialized("insuredBy") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("insuredBy")) : null;
        this.location = inits.isInitialized("location") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("location"), inits.get("location")) : null;
        this.modeOfDispatch = inits.isInitialized("modeOfDispatch") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("modeOfDispatch")) : null;
        this.modeOfTransport = inits.isInitialized("modeOfTransport") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("modeOfTransport")) : null;
        this.party = inits.isInitialized("party") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty(forProperty("party"), inits.get("party")) : null;
        this.toLocation = inits.isInitialized("toLocation") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("toLocation"), inits.get("toLocation")) : null;
    }

}

