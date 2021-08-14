package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QReadyBagRetMt is a Querydsl query type for ReadyBagRetMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QReadyBagRetMt extends EntityPathBase<ReadyBagRetMt> {

    private static final long serialVersionUID = 1260675811L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReadyBagRetMt readyBagRetMt = new QReadyBagRetMt("readyBagRetMt");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> invDate = createDateTime("invDate", java.util.Date.class);

    public final StringPath invNo = createString("invNo");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment location;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final NumberPath<Integer> srNo = createNumber("srNo", Integer.class);

    public QReadyBagRetMt(String variable) {
        this(ReadyBagRetMt.class, forVariable(variable), INITS);
    }

    public QReadyBagRetMt(Path<? extends ReadyBagRetMt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QReadyBagRetMt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QReadyBagRetMt(PathMetadata<?> metadata, PathInits inits) {
        this(ReadyBagRetMt.class, metadata, inits);
    }

    public QReadyBagRetMt(Class<? extends ReadyBagRetMt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.location = inits.isInitialized("location") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("location"), inits.get("location")) : null;
    }

}

