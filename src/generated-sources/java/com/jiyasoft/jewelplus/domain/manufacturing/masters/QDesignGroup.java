package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QDesignGroup is a Querydsl query type for DesignGroup
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDesignGroup extends EntityPathBase<DesignGroup> {

    private static final long serialVersionUID = 1874325291L;

    public static final QDesignGroup designGroup = new QDesignGroup("designGroup");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath name = createString("name");

    public QDesignGroup(String variable) {
        super(DesignGroup.class, forVariable(variable));
    }

    public QDesignGroup(Path<? extends DesignGroup> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDesignGroup(PathMetadata<?> metadata) {
        super(DesignGroup.class, metadata);
    }

}

