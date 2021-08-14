package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QUserDeptTrfRight is a Querydsl query type for UserDeptTrfRight
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserDeptTrfRight extends EntityPathBase<UserDeptTrfRight> {

    private static final long serialVersionUID = 916035738L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserDeptTrfRight userDeptTrfRight = new QUserDeptTrfRight("userDeptTrfRight");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final QDepartment department;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final QDepartment toDeptId;

    public final com.jiyasoft.jewelplus.domain.admin.QUser user;

    public QUserDeptTrfRight(String variable) {
        this(UserDeptTrfRight.class, forVariable(variable), INITS);
    }

    public QUserDeptTrfRight(Path<? extends UserDeptTrfRight> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserDeptTrfRight(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserDeptTrfRight(PathMetadata<?> metadata, PathInits inits) {
        this(UserDeptTrfRight.class, metadata, inits);
    }

    public QUserDeptTrfRight(Class<? extends UserDeptTrfRight> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.department = inits.isInitialized("department") ? new QDepartment(forProperty("department"), inits.get("department")) : null;
        this.toDeptId = inits.isInitialized("toDeptId") ? new QDepartment(forProperty("toDeptId"), inits.get("toDeptId")) : null;
        this.user = inits.isInitialized("user") ? new com.jiyasoft.jewelplus.domain.admin.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

