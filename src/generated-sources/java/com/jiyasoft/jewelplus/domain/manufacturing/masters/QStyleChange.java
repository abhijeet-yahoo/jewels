package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStyleChange is a Querydsl query type for StyleChange
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStyleChange extends EntityPathBase<StyleChange> {

    private static final long serialVersionUID = 2034669707L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStyleChange styleChange = new QStyleChange("styleChange");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final QDesign design;

    public final StringPath designNo = createString("designNo");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final StringPath newStyleNo = createString("newStyleNo");

    public final StringPath oldStyleNo = createString("oldStyleNo");

    public QStyleChange(String variable) {
        this(StyleChange.class, forVariable(variable), INITS);
    }

    public QStyleChange(Path<? extends StyleChange> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStyleChange(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStyleChange(PathMetadata<?> metadata, PathInits inits) {
        this(StyleChange.class, metadata, inits);
    }

    public QStyleChange(Class<? extends StyleChange> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.design = inits.isInitialized("design") ? new QDesign(forProperty("design"), inits.get("design")) : null;
    }

}

