package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMetalOutwardMt is a Querydsl query type for MetalOutwardMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMetalOutwardMt extends EntityPathBase<MetalOutwardMt> {

    private static final long serialVersionUID = -1350174294L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMetalOutwardMt metalOutwardMt = new QMetalOutwardMt("metalOutwardMt");

    public final DateTimePath<java.util.Date> beDate = createDateTime("beDate", java.util.Date.class);

    public final StringPath beNo = createString("beNo");

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

    public final ListPath<MetalOutwardDt, QMetalOutwardDt> metalOutwardDt = this.<MetalOutwardDt, QMetalOutwardDt>createList("metalOutwardDt", MetalOutwardDt.class, QMetalOutwardDt.class, PathInits.DIRECT2);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty party;

    public final StringPath remark = createString("remark");

    public final NumberPath<Integer> srNo = createNumber("srNo", Integer.class);

    public final NumberPath<Integer> supplierId = createNumber("supplierId", Integer.class);

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public QMetalOutwardMt(String variable) {
        this(MetalOutwardMt.class, forVariable(variable), INITS);
    }

    public QMetalOutwardMt(Path<? extends MetalOutwardMt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMetalOutwardMt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMetalOutwardMt(PathMetadata<?> metadata, PathInits inits) {
        this(MetalOutwardMt.class, metadata, inits);
    }

    public QMetalOutwardMt(Class<? extends MetalOutwardMt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.inwardType = inits.isInitialized("inwardType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QInwardType(forProperty("inwardType")) : null;
        this.party = inits.isInitialized("party") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty(forProperty("party"), inits.get("party")) : null;
    }

}

