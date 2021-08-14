package com.jiyasoft.jewelplus.domain.marketing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSaleMt is a Querydsl query type for SaleMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSaleMt extends EntityPathBase<SaleMt> {

    private static final long serialVersionUID = -1104794730L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSaleMt saleMt = new QSaleMt("saleMt");

    public final NumberPath<Double> cgst = createNumber("cgst", Double.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final NumberPath<Double> exchangeRate = createNumber("exchangeRate", Double.class);

    public final NumberPath<Double> finalPrice = createNumber("finalPrice", Double.class);

    public final StringPath finYear = createString("finYear");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QHSNMast hsnMast;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> igst = createNumber("igst", Double.class);

    public final BooleanPath in999 = createBoolean("in999");

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

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast paymentTerm;

    public final StringPath priority = createString("priority");

    public final StringPath refNo = createString("refNo");

    public final StringPath remark = createString("remark");

    public final BooleanPath rLock = createBoolean("rLock");

    public final NumberPath<Double> sgst = createNumber("sgst", Double.class);

    public final NumberPath<Integer> srNo = createNumber("srNo", Integer.class);

    public final NumberPath<Integer> vouType = createNumber("vouType", Integer.class);

    public QSaleMt(String variable) {
        this(SaleMt.class, forVariable(variable), INITS);
    }

    public QSaleMt(Path<? extends SaleMt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSaleMt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSaleMt(PathMetadata<?> metadata, PathInits inits) {
        this(SaleMt.class, metadata, inits);
    }

    public QSaleMt(Class<? extends SaleMt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hsnMast = inits.isInitialized("hsnMast") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QHSNMast(forProperty("hsnMast")) : null;
        this.insuredBy = inits.isInitialized("insuredBy") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("insuredBy")) : null;
        this.location = inits.isInitialized("location") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("location"), inits.get("location")) : null;
        this.modeOfDispatch = inits.isInitialized("modeOfDispatch") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("modeOfDispatch")) : null;
        this.modeOfTransport = inits.isInitialized("modeOfTransport") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("modeOfTransport")) : null;
        this.party = inits.isInitialized("party") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty(forProperty("party"), inits.get("party")) : null;
        this.paymentTerm = inits.isInitialized("paymentTerm") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("paymentTerm")) : null;
    }

}

