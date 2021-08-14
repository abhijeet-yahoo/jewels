package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSubShape is a Querydsl query type for SubShape
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSubShape extends EntityPathBase<SubShape> {

    private static final long serialVersionUID = -805508681L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubShape subShape = new QSubShape("subShape");

    public final StringPath code = createString("code");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath name = createString("name");

    public final QShape shape;

    public QSubShape(String variable) {
        this(SubShape.class, forVariable(variable), INITS);
    }

    public QSubShape(Path<? extends SubShape> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSubShape(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSubShape(PathMetadata<?> metadata, PathInits inits) {
        this(SubShape.class, metadata, inits);
    }

    public QSubShape(Class<? extends SubShape> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.shape = inits.isInitialized("shape") ? new QShape(forProperty("shape")) : null;
    }

}

