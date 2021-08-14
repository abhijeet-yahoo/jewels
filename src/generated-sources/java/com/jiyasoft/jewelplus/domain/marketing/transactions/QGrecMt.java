package com.jiyasoft.jewelplus.domain.marketing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGrecMt is a Querydsl query type for GrecMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGrecMt extends EntityPathBase<GrecMt> {

    private static final long serialVersionUID = -1432855144L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGrecMt grecMt = new QGrecMt("grecMt");

    public final DateTimePath<java.util.Date> cancelDate = createDateTime("cancelDate", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Double> discPercent = createNumber("discPercent", Double.class);

    public final DateTimePath<java.util.Date> dispatchDate = createDateTime("dispatchDate", java.util.Date.class);

    public final StringPath finYear = createString("finYear");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast grading;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast hallMark;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath in999 = createBoolean("in999");

    public final DateTimePath<java.util.Date> invDate = createDateTime("invDate", java.util.Date.class);

    public final StringPath invNo = createString("invNo");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QInwardType inwardType;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast laserMark;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment location;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final BooleanPath orderClose = createBoolean("orderClose");

    public final DateTimePath<java.util.Date> orderCloseDt = createDateTime("orderCloseDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderType orderType;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty party;

    public final BooleanPath pendApprovalFlg = createBoolean("pendApprovalFlg");

    public final StringPath priority = createString("priority");

    public final DateTimePath<java.util.Date> productionDate = createDateTime("productionDate", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final StringPath refNo = createString("refNo");

    public final StringPath remark = createString("remark");

    public final BooleanPath rLock = createBoolean("rLock");

    public final StringPath salesPerson = createString("salesPerson");

    public final NumberPath<Integer> srNo = createNumber("srNo", Integer.class);

    public final DateTimePath<java.util.Date> stoneReqDate = createDateTime("stoneReqDate", java.util.Date.class);

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public QGrecMt(String variable) {
        this(GrecMt.class, forVariable(variable), INITS);
    }

    public QGrecMt(Path<? extends GrecMt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGrecMt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGrecMt(PathMetadata<?> metadata, PathInits inits) {
        this(GrecMt.class, metadata, inits);
    }

    public QGrecMt(Class<? extends GrecMt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.grading = inits.isInitialized("grading") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("grading")) : null;
        this.hallMark = inits.isInitialized("hallMark") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("hallMark")) : null;
        this.inwardType = inits.isInitialized("inwardType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QInwardType(forProperty("inwardType")) : null;
        this.laserMark = inits.isInitialized("laserMark") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("laserMark")) : null;
        this.location = inits.isInitialized("location") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("location"), inits.get("location")) : null;
        this.orderType = inits.isInitialized("orderType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderType(forProperty("orderType")) : null;
        this.party = inits.isInitialized("party") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty(forProperty("party"), inits.get("party")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

