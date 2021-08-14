package com.jiyasoft.jewelplus.domain.admin;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QRoleMast is a Querydsl query type for RoleMast
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRoleMast extends EntityPathBase<RoleMast> {

    private static final long serialVersionUID = -1655300845L;

    public static final QRoleMast roleMast = new QRoleMast("roleMast");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath roleNm = createString("roleNm");

    public QRoleMast(String variable) {
        super(RoleMast.class, forVariable(variable));
    }

    public QRoleMast(Path<? extends RoleMast> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRoleMast(PathMetadata<?> metadata) {
        super(RoleMast.class, metadata);
    }

}

