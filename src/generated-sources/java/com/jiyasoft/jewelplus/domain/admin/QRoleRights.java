package com.jiyasoft.jewelplus.domain.admin;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRoleRights is a Querydsl query type for RoleRights
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRoleRights extends EntityPathBase<RoleRights> {

    private static final long serialVersionUID = -1456043915L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoleRights roleRights = new QRoleRights("roleRights");

    public final BooleanPath canAdd = createBoolean("canAdd");

    public final BooleanPath canCopy = createBoolean("canCopy");

    public final BooleanPath canDelete = createBoolean("canDelete");

    public final BooleanPath canEdit = createBoolean("canEdit");

    public final BooleanPath canPreview = createBoolean("canPreview");

    public final BooleanPath canView = createBoolean("canView");

    public final NumberPath<Integer> compId = createNumber("compId", Integer.class);

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMenuMast menuMast;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final QRoleMast roleMast;

    public QRoleRights(String variable) {
        this(RoleRights.class, forVariable(variable), INITS);
    }

    public QRoleRights(Path<? extends RoleRights> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRoleRights(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRoleRights(PathMetadata<?> metadata, PathInits inits) {
        this(RoleRights.class, metadata, inits);
    }

    public QRoleRights(Class<? extends RoleRights> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.menuMast = inits.isInitialized("menuMast") ? new QMenuMast(forProperty("menuMast")) : null;
        this.roleMast = inits.isInitialized("roleMast") ? new QRoleMast(forProperty("roleMast")) : null;
    }

}

