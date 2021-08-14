package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QQuotQuality is a Querydsl query type for QuotQuality
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QQuotQuality extends EntityPathBase<QuotQuality> {

    private static final long serialVersionUID = -806186L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuotQuality quotQuality = new QQuotQuality("quotQuality");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality quality;

    public final QQuotMt quotMt;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape shape;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType stoneType;

    public QQuotQuality(String variable) {
        this(QuotQuality.class, forVariable(variable), INITS);
    }

    public QQuotQuality(Path<? extends QuotQuality> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QQuotQuality(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QQuotQuality(PathMetadata<?> metadata, PathInits inits) {
        this(QuotQuality.class, metadata, inits);
    }

    public QQuotQuality(Class<? extends QuotQuality> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.quality = inits.isInitialized("quality") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality(forProperty("quality"), inits.get("quality")) : null;
        this.quotMt = inits.isInitialized("quotMt") ? new QQuotMt(forProperty("quotMt"), inits.get("quotMt")) : null;
        this.shape = inits.isInitialized("shape") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape(forProperty("shape")) : null;
        this.stoneType = inits.isInitialized("stoneType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType(forProperty("stoneType")) : null;
    }

}

