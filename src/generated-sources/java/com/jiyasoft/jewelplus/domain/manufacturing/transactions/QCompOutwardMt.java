package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCompOutwardMt is a Querydsl query type for CompOutwardMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCompOutwardMt extends EntityPathBase<CompOutwardMt> {

    private static final long serialVersionUID = 1953488098L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCompOutwardMt compOutwardMt = new QCompOutwardMt("compOutwardMt");

    public final DateTimePath<java.util.Date> beDate = createDateTime("beDate", java.util.Date.class);

    public final StringPath beNo = createString("beNo");

    public final ListPath<CompOutwardDt, QCompOutwardDt> compOutwardDt = this.<CompOutwardDt, QCompOutwardDt>createList("compOutwardDt", CompOutwardDt.class, QCompOutwardDt.class, PathInits.DIRECT2);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final StringPath finYear = createString("finYear");

    public final BooleanPath forParty = createBoolean("forParty");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> invDate = createDateTime("invDate", java.util.Date.class);

    public final StringPath invNo = createString("invNo");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QInwardType inwardType;

    public final NumberPath<Integer> locationId = createNumber("locationId", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty party;

    public final StringPath remark = createString("remark");

    public final NumberPath<Integer> srNo = createNumber("srNo", Integer.class);

    public final NumberPath<Integer> supplierId = createNumber("supplierId", Integer.class);

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public QCompOutwardMt(String variable) {
        this(CompOutwardMt.class, forVariable(variable), INITS);
    }

    public QCompOutwardMt(Path<? extends CompOutwardMt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCompOutwardMt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCompOutwardMt(PathMetadata<?> metadata, PathInits inits) {
        this(CompOutwardMt.class, metadata, inits);
    }

    public QCompOutwardMt(Class<? extends CompOutwardMt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.inwardType = inits.isInitialized("inwardType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QInwardType(forProperty("inwardType")) : null;
        this.party = inits.isInitialized("party") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty(forProperty("party"), inits.get("party")) : null;
    }

}

