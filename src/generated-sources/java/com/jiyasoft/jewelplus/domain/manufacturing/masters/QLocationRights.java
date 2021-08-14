package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QLocationRights is a Querydsl query type for LocationRights
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLocationRights extends EntityPathBase<LocationRights> {

    private static final long serialVersionUID = -1019190814L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLocationRights locationRights = new QLocationRights("locationRights");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final BooleanPath defaultFlg = createBoolean("defaultFlg");

    public final QDepartment department;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.admin.QUser user;

    public QLocationRights(String variable) {
        this(LocationRights.class, forVariable(variable), INITS);
    }

    public QLocationRights(Path<? extends LocationRights> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLocationRights(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLocationRights(PathMetadata<?> metadata, PathInits inits) {
        this(LocationRights.class, metadata, inits);
    }

    public QLocationRights(Class<? extends LocationRights> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.department = inits.isInitialized("department") ? new QDepartment(forProperty("department"), inits.get("department")) : null;
        this.user = inits.isInitialized("user") ? new com.jiyasoft.jewelplus.domain.admin.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

