package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QLookMast is a Querydsl query type for LookMast
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLookMast extends EntityPathBase<LookMast> {

    private static final long serialVersionUID = -756794710L;

    public static final QLookMast lookMast = new QLookMast("lookMast");

    public final StringPath code = createString("code");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath name = createString("name");

    public QLookMast(String variable) {
        super(LookMast.class, forVariable(variable));
    }

    public QLookMast(Path<? extends LookMast> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLookMast(PathMetadata<?> metadata) {
        super(LookMast.class, metadata);
    }

}

