package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QQuality is a Querydsl query type for Quality
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QQuality extends EntityPathBase<Quality> {

    private static final long serialVersionUID = -1524074039L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuality quality = new QQuality("quality");

    public final StringPath code = createString("code");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final StringPath description = createString("description");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath intQuality = createString("intQuality");

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath name = createString("name");

    public final QParty party;

    public final StringPath qualityGroup = createString("qualityGroup");

    public final QShape shape;

    public QQuality(String variable) {
        this(Quality.class, forVariable(variable), INITS);
    }

    public QQuality(Path<? extends Quality> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QQuality(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QQuality(PathMetadata<?> metadata, PathInits inits) {
        this(Quality.class, metadata, inits);
    }

    public QQuality(Class<? extends Quality> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.party = inits.isInitialized("party") ? new QParty(forProperty("party"), inits.get("party")) : null;
        this.shape = inits.isInitialized("shape") ? new QShape(forProperty("shape")) : null;
    }

}

