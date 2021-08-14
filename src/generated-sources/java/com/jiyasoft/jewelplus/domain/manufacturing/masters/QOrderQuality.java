package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOrderQuality is a Querydsl query type for OrderQuality
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrderQuality extends EntityPathBase<OrderQuality> {

    private static final long serialVersionUID = -1874078905L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderQuality orderQuality = new QOrderQuality("orderQuality");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final QOrderMt orderMt;

    public final QQuality quality;

    public final QShape shape;

    public final QStoneType stoneType;

    public QOrderQuality(String variable) {
        this(OrderQuality.class, forVariable(variable), INITS);
    }

    public QOrderQuality(Path<? extends OrderQuality> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderQuality(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderQuality(PathMetadata<?> metadata, PathInits inits) {
        this(OrderQuality.class, metadata, inits);
    }

    public QOrderQuality(Class<? extends OrderQuality> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.orderMt = inits.isInitialized("orderMt") ? new QOrderMt(forProperty("orderMt"), inits.get("orderMt")) : null;
        this.quality = inits.isInitialized("quality") ? new QQuality(forProperty("quality"), inits.get("quality")) : null;
        this.shape = inits.isInitialized("shape") ? new QShape(forProperty("shape")) : null;
        this.stoneType = inits.isInitialized("stoneType") ? new QStoneType(forProperty("stoneType")) : null;
    }

}

