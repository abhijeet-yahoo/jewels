package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOrderMt is a Querydsl query type for OrderMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrderMt extends EntityPathBase<OrderMt> {

    private static final long serialVersionUID = 912567903L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderMt orderMt = new QOrderMt("orderMt");

    public final DateTimePath<java.util.Date> approveDate = createDateTime("approveDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> cancelDate = createDateTime("cancelDate", java.util.Date.class);

    public final QColor color;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Double> discPercent = createNumber("discPercent", Double.class);

    public final DateTimePath<java.util.Date> dispatchDate = createDateTime("dispatchDate", java.util.Date.class);

    public final NumberPath<Double> exchangeRate = createNumber("exchangeRate", Double.class);

    public final StringPath finYear = createString("finYear");

    public final QLookUpMast grading;

    public final QLookUpMast hallMark;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath in999 = createBoolean("in999");

    public final DateTimePath<java.util.Date> invDate = createDateTime("invDate", java.util.Date.class);

    public final StringPath invNo = createString("invNo");

    public final QLookUpMast laserMark;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final QLookUpMast ordDivision;

    public final BooleanPath orderClose = createBoolean("orderClose");

    public final DateTimePath<java.util.Date> orderCloseDt = createDateTime("orderCloseDt", java.util.Date.class);

    public final QOrderType orderType;

    public final QParty party;

    public final BooleanPath pendApprovalFlg = createBoolean("pendApprovalFlg");

    public final StringPath priority = createString("priority");

    public final DateTimePath<java.util.Date> productionDate = createDateTime("productionDate", java.util.Date.class);

    public final QPurity purity;

    public final StringPath refNo = createString("refNo");

    public final StringPath remark = createString("remark");

    public final BooleanPath rLock = createBoolean("rLock");

    public final StringPath salesPerson = createString("salesPerson");

    public final NumberPath<Long> srno = createNumber("srno", Long.class);

    public final DateTimePath<java.util.Date> stoneReqDate = createDateTime("stoneReqDate", java.util.Date.class);

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public QOrderMt(String variable) {
        this(OrderMt.class, forVariable(variable), INITS);
    }

    public QOrderMt(Path<? extends OrderMt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderMt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderMt(PathMetadata<?> metadata, PathInits inits) {
        this(OrderMt.class, metadata, inits);
    }

    public QOrderMt(Class<? extends OrderMt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new QColor(forProperty("color")) : null;
        this.grading = inits.isInitialized("grading") ? new QLookUpMast(forProperty("grading")) : null;
        this.hallMark = inits.isInitialized("hallMark") ? new QLookUpMast(forProperty("hallMark")) : null;
        this.laserMark = inits.isInitialized("laserMark") ? new QLookUpMast(forProperty("laserMark")) : null;
        this.ordDivision = inits.isInitialized("ordDivision") ? new QLookUpMast(forProperty("ordDivision")) : null;
        this.orderType = inits.isInitialized("orderType") ? new QOrderType(forProperty("orderType")) : null;
        this.party = inits.isInitialized("party") ? new QParty(forProperty("party"), inits.get("party")) : null;
        this.purity = inits.isInitialized("purity") ? new QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

