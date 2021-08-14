package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSizeGroup is a Querydsl query type for SizeGroup
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSizeGroup extends EntityPathBase<SizeGroup> {

    private static final long serialVersionUID = 1670753608L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSizeGroup sizeGroup = new QSizeGroup("sizeGroup");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final StringPath fromSize = createString("fromSize");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath name = createString("name");

    public final QShape shape;

    public final ListPath<StoneChart, QStoneChart> stoneChart = this.<StoneChart, QStoneChart>createList("stoneChart", StoneChart.class, QStoneChart.class, PathInits.DIRECT2);

    public final StringPath toSize = createString("toSize");

    public QSizeGroup(String variable) {
        this(SizeGroup.class, forVariable(variable), INITS);
    }

    public QSizeGroup(Path<? extends SizeGroup> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSizeGroup(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSizeGroup(PathMetadata<?> metadata, PathInits inits) {
        this(SizeGroup.class, metadata, inits);
    }

    public QSizeGroup(Class<? extends SizeGroup> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.shape = inits.isInitialized("shape") ? new QShape(forProperty("shape")) : null;
    }

}

