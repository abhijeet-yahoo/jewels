package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QReadyBagIssMt is a Querydsl query type for ReadyBagIssMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QReadyBagIssMt extends EntityPathBase<ReadyBagIssMt> {

    private static final long serialVersionUID = 1252780235L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReadyBagIssMt readyBagIssMt = new QReadyBagIssMt("readyBagIssMt");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> invDate = createDateTime("invDate", java.util.Date.class);

    public final StringPath invNo = createString("invNo");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment location;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final NumberPath<Integer> srNo = createNumber("srNo", Integer.class);

    public QReadyBagIssMt(String variable) {
        this(ReadyBagIssMt.class, forVariable(variable), INITS);
    }

    public QReadyBagIssMt(Path<? extends ReadyBagIssMt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QReadyBagIssMt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QReadyBagIssMt(PathMetadata<?> metadata, PathInits inits) {
        this(ReadyBagIssMt.class, metadata, inits);
    }

    public QReadyBagIssMt(Class<? extends ReadyBagIssMt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.location = inits.isInitialized("location") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("location"), inits.get("location")) : null;
    }

}

