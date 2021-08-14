package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMetalLock is a Querydsl query type for MetalLock
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMetalLock extends EntityPathBase<MetalLock> {

    private static final long serialVersionUID = 137738012L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMetalLock metalLock = new QMetalLock("metalLock");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMetal metal;

    public final DateTimePath<java.util.Date> metalLockDt = createDateTime("metalLockDt", java.util.Date.class);

    public final NumberPath<Double> metalLockRate = createNumber("metalLockRate", Double.class);

    public final StringPath metalLockType = createString("metalLockType");

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final NumberPath<Integer> srNo = createNumber("srNo", Integer.class);

    public QMetalLock(String variable) {
        this(MetalLock.class, forVariable(variable), INITS);
    }

    public QMetalLock(Path<? extends MetalLock> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMetalLock(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMetalLock(PathMetadata<?> metadata, PathInits inits) {
        this(MetalLock.class, metadata, inits);
    }

    public QMetalLock(Class<? extends MetalLock> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.metal = inits.isInitialized("metal") ? new QMetal(forProperty("metal")) : null;
    }

}

