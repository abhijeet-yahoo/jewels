package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QDesignMoldType is a Querydsl query type for DesignMoldType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDesignMoldType extends EntityPathBase<DesignMoldType> {

    private static final long serialVersionUID = -1742956344L;

    public static final QDesignMoldType designMoldType = new QDesignMoldType("designMoldType");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final StringPath name = createString("name");

    public QDesignMoldType(String variable) {
        super(DesignMoldType.class, forVariable(variable));
    }

    public QDesignMoldType(Path<? extends DesignMoldType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDesignMoldType(PathMetadata<?> metadata) {
        super(DesignMoldType.class, metadata);
    }

}

