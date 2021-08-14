package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStonePurchaseMt is a Querydsl query type for StonePurchaseMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStonePurchaseMt extends EntityPathBase<StonePurchaseMt> {

    private static final long serialVersionUID = 1327883757L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStonePurchaseMt stonePurchaseMt = new QStonePurchaseMt("stonePurchaseMt");

    public final DateTimePath<java.util.Date> beDate = createDateTime("beDate", java.util.Date.class);

    public final StringPath beNo = createString("beNo");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final DateTimePath<java.util.Date> dueDate = createDateTime("dueDate", java.util.Date.class);

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

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty supplier;

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public QStonePurchaseMt(String variable) {
        this(StonePurchaseMt.class, forVariable(variable), INITS);
    }

    public QStonePurchaseMt(Path<? extends StonePurchaseMt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStonePurchaseMt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStonePurchaseMt(PathMetadata<?> metadata, PathInits inits) {
        this(StonePurchaseMt.class, metadata, inits);
    }

    public QStonePurchaseMt(Class<? extends StonePurchaseMt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.inwardType = inits.isInitialized("inwardType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QInwardType(forProperty("inwardType")) : null;
        this.party = inits.isInitialized("party") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty(forProperty("party"), inits.get("party")) : null;
        this.supplier = inits.isInitialized("supplier") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty(forProperty("supplier"), inits.get("supplier")) : null;
    }

}

