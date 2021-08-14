package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStateMaster is a Querydsl query type for StateMaster
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStateMaster extends EntityPathBase<StateMaster> {

    private static final long serialVersionUID = -493113827L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStateMaster stateMaster = new QStateMaster("stateMaster");

    public final StringPath code = createString("code");

    public final QCountryMaster country;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final StringPath name = createString("name");

    public final StringPath stateCode = createString("stateCode");

    public QStateMaster(String variable) {
        this(StateMaster.class, forVariable(variable), INITS);
    }

    public QStateMaster(Path<? extends StateMaster> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStateMaster(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStateMaster(PathMetadata<?> metadata, PathInits inits) {
        this(StateMaster.class, metadata, inits);
    }

    public QStateMaster(Class<? extends StateMaster> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.country = inits.isInitialized("country") ? new QCountryMaster(forProperty("country")) : null;
    }

}

