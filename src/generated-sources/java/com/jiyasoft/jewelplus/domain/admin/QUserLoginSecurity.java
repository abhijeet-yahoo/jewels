package com.jiyasoft.jewelplus.domain.admin;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QUserLoginSecurity is a Querydsl query type for UserLoginSecurity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserLoginSecurity extends EntityPathBase<UserLoginSecurity> {

    private static final long serialVersionUID = -413227978L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserLoginSecurity userLoginSecurity = new QUserLoginSecurity("userLoginSecurity");

    public final StringPath answer = createString("answer");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath question = createString("question");

    public final QUser user;

    public QUserLoginSecurity(String variable) {
        this(UserLoginSecurity.class, forVariable(variable), INITS);
    }

    public QUserLoginSecurity(Path<? extends UserLoginSecurity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserLoginSecurity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserLoginSecurity(PathMetadata<?> metadata, PathInits inits) {
        this(UserLoginSecurity.class, metadata, inits);
    }

    public QUserLoginSecurity(Class<? extends UserLoginSecurity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

