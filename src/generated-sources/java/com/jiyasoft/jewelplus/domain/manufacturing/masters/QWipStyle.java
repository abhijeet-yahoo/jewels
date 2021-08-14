package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QWipStyle is a Querydsl query type for WipStyle
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QWipStyle extends EntityPathBase<WipStyle> {

    private static final long serialVersionUID = 211844329L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWipStyle wipStyle = new QWipStyle("wipStyle");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final QDesign design;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final QParty party;

    public QWipStyle(String variable) {
        this(WipStyle.class, forVariable(variable), INITS);
    }

    public QWipStyle(Path<? extends WipStyle> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QWipStyle(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QWipStyle(PathMetadata<?> metadata, PathInits inits) {
        this(WipStyle.class, metadata, inits);
    }

    public QWipStyle(Class<? extends WipStyle> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.design = inits.isInitialized("design") ? new QDesign(forProperty("design"), inits.get("design")) : null;
        this.party = inits.isInitialized("party") ? new QParty(forProperty("party"), inits.get("party")) : null;
    }

}

