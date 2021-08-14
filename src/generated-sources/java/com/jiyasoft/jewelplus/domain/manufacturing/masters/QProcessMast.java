package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProcessMast is a Querydsl query type for ProcessMast
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProcessMast extends EntityPathBase<ProcessMast> {

    private static final long serialVersionUID = -1293349426L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProcessMast processMast = new QProcessMast("processMast");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final QDepartment department;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final QLookUpMast processLookUp;

    public final NumberPath<Integer> seqNo = createNumber("seqNo", Integer.class);

    public QProcessMast(String variable) {
        this(ProcessMast.class, forVariable(variable), INITS);
    }

    public QProcessMast(Path<? extends ProcessMast> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProcessMast(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProcessMast(PathMetadata<?> metadata, PathInits inits) {
        this(ProcessMast.class, metadata, inits);
    }

    public QProcessMast(Class<? extends ProcessMast> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.department = inits.isInitialized("department") ? new QDepartment(forProperty("department"), inits.get("department")) : null;
        this.processLookUp = inits.isInitialized("processLookUp") ? new QLookUpMast(forProperty("processLookUp")) : null;
    }

}

