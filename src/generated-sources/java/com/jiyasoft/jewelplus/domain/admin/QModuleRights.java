package com.jiyasoft.jewelplus.domain.admin;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QModuleRights is a Querydsl query type for ModuleRights
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QModuleRights extends EntityPathBase<ModuleRights> {

    private static final long serialVersionUID = 1186028523L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QModuleRights moduleRights = new QModuleRights("moduleRights");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath moduleName = createString("moduleName");

    public final StringPath moduleUrl = createString("moduleUrl");

    public final QUser user;

    public QModuleRights(String variable) {
        this(ModuleRights.class, forVariable(variable), INITS);
    }

    public QModuleRights(Path<? extends ModuleRights> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QModuleRights(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QModuleRights(PathMetadata<?> metadata, PathInits inits) {
        this(ModuleRights.class, metadata, inits);
    }

    public QModuleRights(Class<? extends ModuleRights> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

